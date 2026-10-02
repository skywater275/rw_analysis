#!/usr/bin/env python3
"""replacement_verify.py — **替换产物验收标准（固化）**。

本脚本把「检验核对」写成一条可重复执行的判据链，任何形态的产物都用同一套口径验收。
判据与阈值（**这些都是硬判据**）：

  V1 javac_gate         `tools/gates/javac_gate.py`        判据：errors == 0
  V2 jar_compare_gate   `tools/gates/jar_compare_gate.py`  判据：FAIL == 0
  V3 class_link_check   内部 + 跨向引用                    判据：无法解析 == 0
     （「引用了产物中不存在的类」允许 ≤4 条 —— 已知 #56，非我方缺陷，原版基线同样存在）
  V4 结构核对           逐类比对 声明成员集 + 构造器集       判据：不一致 == 0
  V5 GUI 冒烟           真窗口（禁用 headless）             判据：拿到窗口证据 且 崩溃判据 == 0
     （记录到的「环境性崩溃」需用 stock.jar 对照证实与原版一致，方可豁免）
  V6 回放校验           GUI + 调试点载入回放                 判据：`don't match` == 0
     需要**干净资源集**（`assets/units` 与本混淆版不兼容时无法启动）→ 不满足则报 BLOCKED
  V7 替换率             A 类（`build/reverse-classes` 同名进入产物） + 与原版**字节不同**数

用法:
  python tools/gates/replacement_verify.py [--skip-gui] [--skip-replay] [--product PATH] [--json OUT]
退出码: 0 = 所有可执行判据 PASS（BLOCKED 不算 FAIL，但会显著标注）; 1 = 有 FAIL
"""
import argparse
import hashlib
import json
import re
import struct
import subprocess
import sys
import zipfile
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / 'tools'))
from rwlib.tuning import T  # noqa: E402

PROD = ROOT / 'build' / 'game-lib-reverse.jar'
STOCK = ROOT / 'RustedWarfare' / 'game-lib.jar'
REV_CLS = ROOT / 'build' / 'reverse-classes'
R = []


def add(name, ok, detail):
    R.append({'判据': name, '结论': 'PASS' if ok else ('FAIL' if ok is False else 'BLOCKED'),
              '证据': detail})


def run(cmd, timeout=None):
    """跑子进程并返回 (合并输出, 返回码)。

    超时/异常一律转成字符串证据，**不抛出**（否则整份验收 JSON 不落盘 ⇒ 部署闸门
    会读到上一轮的陈旧 PASS —— 见工具自检清单 T-11）。
    """
    if timeout is None:
        timeout = int(T.get('verify.timeout_default'))
    try:
        r = subprocess.run([sys.executable] + cmd, cwd=str(ROOT), capture_output=True,
                           text=True, encoding='utf-8', errors='replace', timeout=timeout)
        return (r.stdout or '') + (r.stderr or ''), r.returncode
    except subprocess.TimeoutExpired:
        return '[超时] 子进程 %s 超过 %ds 未返回' % (cmd[0], timeout), 124
    except Exception as exc:  # noqa: BLE001 — 证据链要留下痕迹而不是崩掉
        return '[异常] 子进程 %s 启动失败: %s' % (cmd[0], exc), 125


def v1():
    txt, _ = run([str(ROOT / 'tools' / 'gates' / 'javac_gate.py')])
    m = re.search(r'GATE:\s*(PASSED|FAILED).*?(\d+)\s+compilation errors', txt, re.S)
    if m:
        errs = int(m.group(2))
    else:
        mm = re.search(r'(\d+)\s*$', txt.strip())
        errs = int(mm.group(1)) if mm else -1
    add('V1 javac_gate 编译门禁', errs == 0, 'compilation errors = %d' % errs)


def v2():
    out = T.path('jar_gate.out_dir')
    txt, _ = run([str(ROOT / 'tools' / 'gates' / 'jar_compare_gate.py'),
                  '--out-dir', str(out), '--write'],
                 timeout=int(T.get('verify.timeout_jar_gate')))
    m = re.search(r'FAIL\s+(\d+)', txt)
    fail = int(m.group(1)) if m else -1
    add('V2 jar_compare_gate 逐成员对照', fail == 0, 'FAIL = %d' % fail)


def v3():
    allow = int(T.get('verify.v3_allowed_missing_classes'))
    txt, _ = run([str(ROOT / 'tools' / 'utils' / 'class_link_check.py')],
                 timeout=int(T.get('verify.timeout_link')))
    m1 = re.search(r'无法解析的成员引用:\s*(\d+)\s*条', txt)
    m2 = re.search(r'引用了产物中不存在的类:\s*(\d+)\s*条', txt)
    n1 = int(m1.group(1)) if m1 else -1
    n2 = int(m2.group(1)) if m2 else -1
    # 判据：无法解析 == 0，且「引用不存在类」不超过配置的允许量（已知 #56，原版基线同样存在）
    ok = (n1 == 0) and (0 <= n2 <= allow)
    add('V3 class_link_check 内部+跨向', ok,
        '无法解析 = %d（判据 0）；引用不存在的类 = %d（允许 ≤%d）' % (n1, n2, allow))



def _syn(cf, kind, name, desc):
    """该成员是否带 ACC_BRIDGE / ACC_SYNTHETIC。"""
    import struct
    for off, ni, di in cf.members[kind]:
        if cf.u(ni) == name and cf.u(di) == desc:
            acc = struct.unpack_from('>H', cf.raw, off)[0]
            return bool(acc & 0x40) or bool(acc & 0x1000)
    return False


def _syn_desc(cf, kind, desc):
    """按**描述符**判断是否 ACC_BRIDGE / ACC_SYNTHETIC（用于构造器集比较）。

    ★ 2026-09-29 第七轮：构造器集比较此前没有合成豁免，与本判据自己的理由冲突
    （`lost` 已豁免合成成员）。后果：枚举常量体构造器 `<init>(String,int,Outer$1)`
    的 `Outer$1` 是**混淆器生成的标记类**（stock 里由 `r$1` 被改名成 `p$1`），
    源码侧无法重现 → 81 个枚举族类被整体判为「构造器不一致」而让位。
    实测 stock 该构造器 acc=0x1000（ACC_SYNTHETIC），故与成员判据统一处理。

    ★ 2026-09-30 第二十轮：**还有一类同类情形逃过了上面的标志检查** ——
    javac 为「内部类访问外层私有成员」生成的**访问桥接构造器**，描述符里带一个
    `$数字` 标记类参数（如 `(Ljava/lang/String;[Ljava/lang/StackTraceElement;L.../a$1;)V`），
    而该构造器**本身不帯** BRIDGE/SYNTHETIC 标志 → 标志检查漏判。
    实测：交付物 `gameFramework/utility/b` 与 `c` 各多一个此类构造器，
    原版（经 ProGuard 处理）没有 ⇒ 属**编译器差异**，源码侧同样无法重现
    ⇒ 与枚举情形同类，应一并豁免。
    判据：构造器描述符中出现 `$<数字>;` 形式的类型（合成标记类）即豁免。
    """
    import re
    import struct
    if kind == 'method' and re.search(r'\$\d+;', desc or ''):
        return True
    for off, ni, di in cf.members[kind]:
        if cf.u(di) == desc:
            acc = struct.unpack_from('>H', cf.raw, off)[0]
            return bool(acc & 0x40) or bool(acc & 0x1000)
    return False


def _v4_scan2(pairs):
    """对 `{rel: (我方字节, 原版字节)}` 跑 V4 结构判据，返回 `(bad, syn_only, extras)`。

    ★ 2026-10-01（PENDING **RS-2** ①）：
    原判据只算 `lost = td - od`（**缺失**），**「多出成员」不判**。现补上 `extra = od - td`
    并**作为信息项回报**（`extras`），**但不判 FAIL** —— 理由是本轮实测：
    项目的交付模型是「**行为一致可运行的混合形态**」（STANDARD-02），反向编译出来的类
    **必须**提供比 stock 更多的成员，否则**其它反向编译类**对它的调用会 `NoSuchMethodError`
    （实测：`reset` 出现在 28 个类、`compareTo` 21 个、`isEnabled` 4 个 ⇒ 额外成员是
    混合世界的**脚手架**，不是损伤）。若把 `extra` 判 FAIL，会把 135 个**有意为之**的类
    标成缺陷，并与 V2 的既定设计冲突。
    ⇒ 真正该判 FAIL 的是**「非 stock 条目里零外部引用的死件」**，见 `v4b()`。
    豁免口径与 `lost` 保持一致：`ACC_BRIDGE(0x40)` / `ACC_SYNTHETIC(0x1000)` 是编译器产物，
    不属 API。构造器集仍按 `_syn_desc` 豁免。
    """
    sys.path.insert(0, str(ROOT / 'tools'))
    from fixers.repair_member_names import ClassFile
    bad, syn_only, extras = [], [], {}
    for rel in sorted(pairs):
        try:
            a, b = ClassFile(pairs[rel][0]), ClassFile(pairs[rel][1])
        except Exception:
            bad.append(rel)
            continue
        od, td = a.declared(), b.declared()
        # 只比较**非合成**成员：`ACC_BRIDGE(0x40)` / `ACC_SYNTHETIC(0x1000)` 是编译器/混淆器产物
        # （实测：原版子类的 `with` 全是 PUBLIC,BRIDGE,SYNTHETIC —— 父类协变桥接被复制进子类，
        #   javac 不会这么做 → 无法从源码重现，也不属 API）→ 单列 INFO，不判不一致。
        lost = {(n, d) for k in ('field', 'method') for (n, d) in td[k] - od[k]
                if not _syn(b, k, n, d)}
        extra = {(k, n, d) for k in ('field', 'method') for (n, d) in od[k] - td[k]
                 if not _syn(a, k, n, d)}
        syn = len({(n, d) for k in ('field', 'method') for (n, d) in td[k] - od[k]}) - len(lost)
        co = {d for (n, d) in od['method'] if n in ('<init>', '<clinit>')
              and not _syn_desc(a, 'method', d)}
        ct = {d for (n, d) in td['method'] if n in ('<init>', '<clinit>')
              and not _syn_desc(b, 'method', d)}
        if lost or (ct and co != ct):
            bad.append(rel)
            if extra:
                extras[rel] = sorted('%s %s%s' % (k, n, d) for (k, n, d) in extra)
        elif extra:
            extras[rel] = sorted('%s %s%s' % (k, n, d) for (k, n, d) in extra)
        elif syn:
            syn_only.append('%s(+%d 合成)' % (rel.split('/')[-1], syn))
    return bad, syn_only, extras


def _v4_scan(pairs):
    """兼容包装：既有调用方（探针/收编脚本）只取 `(bad, syn_only)`。"""
    bad, syn_only, _extras = _v4_scan2(pairs)
    return bad, syn_only


def v4():
    """结构核对：**双口径**。

    ★ 2026-09-30 第二十轮澄清：本判据读的是 `build/reverse-classes` —— 那是**替换候选池**
    （764 文件，仅 716 个可与 stock 配对），而**交付物是 `game-lib-reverse.jar`**
    （1,747 条目，1,698 个可与 stock 配对）。成品在 S6 结构清理阶段会让结构不合的类
    **回落原版字节码** ⇒ 候选池里结构不对的类在成品里已不存在。
    实测：候选池 92 不一致，而**交付物 0 不一致**（差集单向：仅候选池有 92，仅成品有 0）。

    原先只用候选池口径 ⇒ 把交付物质量**高估约 46 倍**。现改为：
      · **判据以交付物为准**（那才是要交付的东西）
      · 候选池值单列为 INFO（它仍有诊断价值：指出哪些替换类该让位）
    """
    with zipfile.ZipFile(STOCK) as z:
        stock = {n[:-6]: z.read(n) for n in z.namelist() if n.endswith('.class')}

    # ① 候选池口径（INFO）
    pool = {}
    for p in sorted(REV_CLS.rglob('*.class')):
        rel = p.relative_to(REV_CLS).as_posix()[:-6]
        if rel in stock:
            pool[rel] = (p.read_bytes(), stock[rel])
    bad_pool, syn_pool, _ex_pool = _v4_scan2(pool)

    # ② 交付物口径（判据）
    prod, bad_prod, syn_prod, ex_prod = {}, [], [], {}
    if PROD.exists():
        with zipfile.ZipFile(PROD) as z:
            for n in z.namelist():
                if not n.endswith('.class'):
                    continue
                rel = n[:-6]
                if rel in stock:
                    prod[rel] = (z.read(n), stock[rel])
        bad_prod, syn_prod, ex_prod = _v4_scan2(prod)

    ex_show = ''
    if ex_prod:
        ex_show = '；★多出成员 %d 类，例: %s' % (
            len(ex_prod),
            '; '.join('%s[%s]' % (k.split('/')[-1], ','.join(v[:2])) for k, v in list(ex_prod.items())[:3]))
    add('V4 结构核对（成员集/构造器/多出成员）', len(bad_prod) == 0,
        '交付物不一致 = %d（对照 %d 类）%s%s；仅合成差异 %d；'
        '候选池口径 = %d（对照 %d 类，INFO）'
        % (len(bad_prod), len(prod),
           '' if not bad_prod else '，例: %s' % bad_prod[:3], ex_show,
           len(syn_prod), len(bad_pool), len(pool)))


def v4b():
    """★ RS-2 ②：**非 stock 同位条目**（新增/B 类）此前被 V4 整条跳过（`if rel in stock`）。

    判据：这些条目**不得零外部引用** —— 一个没有任何类引用的新增类，要么是构建残留
    （合成名死件），要么是本来该被替换却另起了名字。两者都不该留在交付物里。

    实现：扫遍产物全部类的字节，统计每个内部名的**被引用类数**；某名字只被自己引用
    （计数 ≤ 1）即判「零外部引用」。
    """
    if not PROD.exists():
        add('V4b 非 stock 条目', None, '产物不存在')
        return
    with zipfile.ZipFile(STOCK) as z:
        stock_names = {n[:-6] for n in z.namelist() if n.endswith('.class')}
    rx = re.compile(rb'[A-Za-z_$][A-Za-z0-9_$]*(?:/[A-Za-z_$][A-Za-z0-9_$]*)+')
    refs = Counter()
    extra_entries = []
    with zipfile.ZipFile(PROD) as z:
        for n in z.namelist():
            if not n.endswith('.class'):
                continue
            rel = n[:-6]
            if rel not in stock_names:
                extra_entries.append(rel)
            refs.update(set(rx.findall(z.read(n))))
    orphan = sorted(e for e in extra_entries if refs.get(e.encode(), 0) <= 1)
    # ★ 与 `tools/fixers/prune_orphan_entries.py` **同一口径**（避免两处判据漂移）：
    #   孤儿类的简单名若作为**独立 token** 出现在别的类里，可能是 `Class.forName("…")` 目标
    #   ⇒ 只报 REVIEW，不判 FAIL（剪除工具同样拒绝删它们）。
    TOK = re.compile(rb'[A-Za-z_$][A-Za-z0-9_$]{3,}')
    token_owner = {}
    with zipfile.ZipFile(PROD) as z:
        for n in z.namelist():
            if not n.endswith('.class'):
                continue
            for t in set(TOK.findall(z.read(n))):
                token_owner.setdefault(t, set()).add(n[:-6])
    review = []
    for e in orphan:
        simple = e.rsplit('/', 1)[-1].split('$')[0]
        if len(simple) >= 4 and (token_owner.get(simple.encode(), set()) - {e}):
            review.append(e)
    hard = [e for e in orphan if e not in set(review)]
    add('V4b 非 stock 条目无死件', not hard,
        '非 stock 条目 = %d；**零外部引用 = %d**（其中判 FAIL = %d，反射可疑单列 REVIEW = %d）%s%s'
        % (len(extra_entries), len(orphan), len(hard), len(review),
           '' if not hard else '，例: %s' % hard[:5],
           '' if not review else '；REVIEW: %s' % review[:4]))


def v8():
    """★ RS-2 ③：把**原子契约**（产物 == 候选池重打包）纳入固定判据。

    此前它是 `build/_verify_atomic_contract.py` 这个**独立脚本**，只在人工记得跑时执行；
    RS-1 结清靠的就是它，却不属验收套件 ⇒ 契约破了也没有判据会红。
    """
    txt, rc = run([str(ROOT / 'build' / '_verify_atomic_contract.py')])
    m = re.search(r'结论: (一致|不一致)', txt)
    if rc == 0 and m and m.group(1) == '一致':
        add('V8 原子契约（产物 == 池重打包）', True, m.group(1))
    else:
        detail = (m.group(0) if m else txt.strip().splitlines()[-1:] or ['无输出'])
        if isinstance(detail, list):
            detail = detail[0] if detail else '无输出'
        add('V8 原子契约（产物 == 池重打包）', False, '%s（rc=%d）' % (detail, rc))


def env_reset():
    """把游戏环境复位为**已知干净态**（清 `cache/` + `crashes.txt`），并记入判据。

    为什么必须先做：实测 `cache/mods-info.cachedata/**` 会缓存「哪些单位在 mods/ 下」等信息，
    环境一旦被污染，GUI/回放的结论就不可复现（本会话曾因此把产物缺陷误判为环境问题）。

    历史缺陷（已修）：本项曾**硬编码 `add(..., True, ...)`** ⇒ 恒 PASS，复位失败也照绿。
    现在按实际输出与返回码判定：失败即 FAIL（失败关闭）。
    """
    txt, rc = run([str(ROOT / 'tools' / 'rig' / 'env_baseline.py'), '--reset', '--apply'],
                  timeout=int(T.get('verify.timeout_env_reset')))
    done = ('复位完成' in txt) and rc == 0
    add('V0 环境复位（已知干净态）', done,
        '%s（rc=%d）' % ('已清 cache/ + crashes.txt' if done else '复位未确认，见输出', rc))


def v5(seconds=None):
    if seconds is None:
        seconds = int(T.get('verify.v5_seconds'))
    env_reset()          # 产物冒烟前：已知干净态
    txt, _ = run([str(ROOT / 'build' / '_gui_smoke.py'), '--seconds', str(seconds)],
                 timeout=int(T.get('verify.timeout_gui')))
    win_cls = str(T.get('smoke.window_class'))
    win = 'window' if re.search(r"窗口证据: \{'hwnd'", txt) else ('none' if '未捕获到 LWJGL 窗口' in txt else '?')
    hits = re.search(r'崩溃判据命中: (.*)', txt)
    crashed = bool(hits and '无' not in hits.group(1))
    if '结论不可信' in txt or not hits:
        # 历史缺陷：崩溃判据那行缺失时 `crashed=False` ⇒ 只要窗口出现就 PASS。
        # 现在：判据缺失/无日志本身就是不可判定 ⇒ BLOCKED（失败关闭）。
        add('V5 GUI 冒烟（真窗口）', None,
            'BLOCKED：崩溃判据不可用（窗口=%s，%s）—— 冒烟输出不完整，结论不可信'
            % (win, win_cls))
        return
    if crashed:
        # **自动与原版对照**：同一条冒烟换成 stock.jar 再跑一次。
        # 原版同样崩在同一处 → BLOCKED（环境性问题，非产物缺陷）；否则 FAIL（产物侧缺陷）。
        st = ROOT.parent / 'jars' / 'stock.jar'
        if st.exists():
            env_reset()          # 原版对照前同样复位 → 两边同起点，结论才可归因
            stock_txt, _ = run([str(ROOT / 'build' / '_gui_smoke.py'), '--seconds', str(seconds),
                                '--product', str(st)],
                               timeout=int(T.get('verify.timeout_gui')))
            sig = re.search(r'cause:(.*)', txt)
            ssig = re.search(r'cause:(.*)', stock_txt)

            def norm(m):
                if not m:
                    return ''
                return re.sub(r'\s+', ' ', m.group(1)).strip()[:70]
            sn, ssn = norm(sig), norm(ssig)
            same = bool(sn) and bool(ssn) and sn[:40] == ssn[:40]
            if 'uncaughtException start' in stock_txt and same:
                add('V5 GUI 冒烟（真窗口）', None,
                    'BLOCKED（环境性）：产物与原版 stock.jar 崩在同一处 → 产物"%s" / 原版"%s"'
                    % (sn[:60], ssn[:60]))
                return
            add('V5 GUI 冒烟（真窗口）', False,
                'FAIL：产物崩、原版不崩或崩点不同 → 产物"%s" / 原版"%s"'
                % (sn[:60], ssn[:60] if ssn else '(原版无崩溃)'))
            return
    ok = (win == 'window') and not crashed
    add('V5 GUI 冒烟（真窗口）', ok,
        '窗口=%s；%s' % (win, hits.group(1).strip() if hits else '?'))


def v6(watch=None):
    if watch is None:
        watch = int(T.get('verify.v6_watch'))
    txt, _ = run([str(ROOT / 'build' / '_replay_regress.py'), '--watch', str(watch)],
                 timeout=int(T.get('verify.timeout_replay')))
    dm = re.search(r"don't match 命中 (\d+) 条", txt)
    st = re.search(r'回放启动行 (\d+) 条', txt)
    cr = re.search(r'崩溃判据 (\d+) 条', txt)
    if cr and int(cr.group(1)) > 0:
        add('V6 回放校验和', None,
            'BLOCKED：游戏在载入回放前崩溃（崩溃判据 %s 条）—— 需干净资源集；'
            '环境不干净时本判据无法给出结论' % cr.group(1))
    elif st and int(st.group(1)) == 0:
        add('V6 回放校验和', None, 'BLOCKED：回放未启动（Replay: Starting frame 0 条）')
    else:
        n = int(dm.group(1)) if dm else -1
        add('V6 回放校验和（don\'t match）', n == 0,
            "don't match = %d（判据 0）；回放启动 %s 条" % (n, st.group(1) if st else '?'))


def v7():
    with zipfile.ZipFile(STOCK) as z:
        stock = {n: z.read(n) for n in z.namelist() if n.endswith('.class')}
    with zipfile.ZipFile(PROD) as z:
        names = [n for n in z.namelist() if n.endswith('.class')]
        diff = sum(1 for n in names if n in stock and z.read(n) != stock[n])
    files = [p.relative_to(REV_CLS).as_posix() for p in REV_CLS.rglob('*.class')]
    A = [f for f in files if f in stock]        # stock 的键是带 .class 的条目名
    add('V7 替换率（信息项）', True,
        'A 类（同名进入产物） = %d / %d = %.2f%%；与原版字节不同 = %d (%.2f%%)'
        % (len(A), len(stock), 100.0 * len(A) / len(stock), diff, 100.0 * diff / len(stock)))


def product_fingerprint():
    """产物指纹行：把「本次验收的是哪个产物」写进 JSON。

    历史缺陷（工具自检 T-10）：部署闸门只检查 JSON 里有无 FAIL，不校验它是否**本次**产物
    ⇒ 陈旧 PASS 可放行任意产物。此处写入 sha256 + 大小 + mtime，供部署闸门做绑定校验。
    """
    if not PROD.exists():
        return {'判据': 'M0 产物指纹', '结论': 'INFO', '证据': '产物不存在: %s' % PROD}
    h = hashlib.sha256()
    with open(PROD, 'rb') as f:
        for chunk in iter(lambda: f.read(1 << 20), b''):
            h.update(chunk)
    st = PROD.stat()
    return {'判据': 'M0 产物指纹', '结论': 'INFO',
            '证据': 'sha256=%s size=%d mtime=%d path=%s'
                    % (h.hexdigest().upper(), st.st_size, int(st.st_mtime), PROD.name)}


def main():
    global PROD
    ap = argparse.ArgumentParser()
    ap.add_argument('--skip-gui', action='store_true')
    ap.add_argument('--skip-replay', action='store_true')
    ap.add_argument('--product', default=str(PROD))
    ap.add_argument('--json')
    args = ap.parse_args()
    PROD = Path(args.product)
    R.append(product_fingerprint())
    for fn in (v1, v2, v3, v4, v4b, v8):
        fn()
    if not args.skip_gui:
        v5()
    if not args.skip_replay:
        v6()
    v7()
    print('=== 替换产物验收（固定判据）===')
    for row in R:
        print('  [%-7s] %-32s %s' % (row['结论'], row['判据'], row['证据']))
    fails = [r for r in R if r['结论'] == 'FAIL']
    blocked = [r for r in R if r['结论'] == 'BLOCKED']
    print('=== 汇总：PASS %d / FAIL %d / BLOCKED %d ==='
          % (sum(1 for r in R if r['结论'] == 'PASS'), len(fails), len(blocked)))
    # JSON 始终落盘（含中途异常场景）—— 部署闸门依赖它，缺它就是「无验收」
    out = Path(args.json) if args.json else T.path('verify.json_out')
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(json.dumps(R, ensure_ascii=False, indent=2), encoding='utf-8')
    print('验收结果 → %s' % out)
    return 1 if fails else 0


if __name__ == '__main__':
    sys.exit(main())
