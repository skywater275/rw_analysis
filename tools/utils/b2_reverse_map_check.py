#!/usr/bin/env python3
"""B2 全量反向映射核对 (B3 构建可行性)

对 03 全部 1,739 文件:
  1. 提取类名 (class/enum/interface 声明)
  2. 查 class-discoveries 映射 (语义名 → 02 包+混淆名) — 复用 runtime_patch_batch 逻辑
  3. 若 03 文件本身是混淆名 (如 ay.java): 反查 jar 类存在性 (该混淆FQN 在 jar 中?)
  4. 输出: 可反向 (语义名映射) / 混淆名直配 (jar 有) / 无映射 (重建/测试/缺口)

对 jar 全部 1,698 类:
  1. 语义名映射 → 03 文件存在?
  2. 混淆名直配 → 03 文件存在?
  3. 输出缺口清单 (jar 有 03 无 → B3 需保留 jar 原类或从 02 补)

输出: mappings/generated/b2-03-reverse.csv / b2-jar-cover.csv / b2-gaps.csv
"""
import csv
import re
import subprocess
import sys
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import (CLASS_DISCOVERIES, DEOBFUSCATED_DIR, GAME_LIB,
                          GENERATED_DIR, MAPPINGS_CSV)

GAME_PREFIXES = ('com.corrodinggames.', 'a.')
THIRD_PARTY_PREFIXES = ('android.', 'org.', 'com.codedisaster.', 'com.badlogic.',
                        'com.Element', 'com.LibRocket', 'com.a.')


def load_mapping():
    """语义类名 → [(02包, 02名)]; 混淆类名 → 语义名"""
    sem_to_obf = {}
    obf_to_sem = {}
    for r in csv.reader(open(CLASS_DISCOVERIES, encoding='utf-8')):
        if not r or r[0] != 'class' or len(r) < 4:
            continue
        pkg, obf, readable = r[1], r[2], r[3]
        if not readable or obf == readable:
            continue
        sem_to_obf.setdefault(readable, []).append((pkg, obf))
        obf_to_sem[(pkg, obf)] = readable
    for r in csv.reader(open(MAPPINGS_CSV, encoding='utf-8')):
        if not r or r[0] != 'class' or len(r) < 4:
            continue
        pkg, obf, readable = r[1], r[2], r[4] if len(r) > 4 else r[3]
        if not readable or obf == readable:
            continue
        sem_to_obf.setdefault(readable, []).append((pkg, obf))
        obf_to_sem[(pkg, obf)] = readable
    return sem_to_obf, obf_to_sem


def jar_class_set():
    out = subprocess.run(['jar', 'tf', str(GAME_LIB)], capture_output=True,
                         text=True, encoding='utf-8', errors='replace').stdout
    fqns = set()
    for line in out.splitlines():
        line = line.strip()
        if line.endswith('.class'):
            fqns.add(line[:-len('.class')].replace('/', '.'))
    return fqns


def main():
    sem_to_obf, obf_to_sem = load_mapping()
    jar = jar_class_set()
    print(f'mapping: sem->obf {len(sem_to_obf)} 组, obf->sem {len(obf_to_sem)}')
    print(f'jar 类: {len(jar)}')

    # ============ 03 文件侧 ============
    rows03 = []
    cat = {'semantic': 0, 'obf-match': 0, 'no-map': 0, 'third': 0}
    for java in DEOBFUSCATED_DIR.rglob('*.java'):
        rel = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
        src = java.read_text(encoding='utf-8', errors='ignore')[:4000]
        m = re.search(r'(?:public |final |abstract |strictfp )*(?:class|enum|interface) (\w+)', src)
        cls = m.group(1) if m else java.stem
        pkg03 = java.parent.as_posix().replace('\\', '/').split('03-deobfuscated/')[-1].replace('/', '.')
        # 情况 A: 03 类名是语义名
        if cls in sem_to_obf:
            cands = sem_to_obf[cls]
            tgt = None
            for pkg02, obf02 in cands:
                if pkg02 == pkg03:
                    tgt = (pkg02, obf02)
                    break
            if not tgt and len(cands) == 1:
                tgt = cands[0]
            if not tgt:
                # 包重命名场景: 03 语义包与 02 混淆包同深度层级匹配 (如 game.ai ↔ game.a)
                # 取共享前缀最长的候选
                best = None
                best_len = -1
                for pkg02, obf02 in cands:
                    p2 = pkg02.split('.')
                    p3 = pkg03.split('.')
                    common = 0
                    for a, b in zip(p2, p3):
                        if a == b:
                            common += 1
                        else:
                            break
                    if common > best_len:
                        best_len = common
                        best = (pkg02, obf02)
                tgt = best
            if tgt:
                obf_fqn = tgt[0] + '.' + tgt[1]
                in_jar = obf_fqn in jar
                cat['semantic'] += 1
                rows03.append([rel, cls, 'semantic', obf_fqn, 'jar-ok' if in_jar else 'jar-MISSING'])
                continue
        # 情况 B: 03 类名是混淆名 (保留) — 用文件名+包判断
        obf_fqn = pkg03 + '.' + java.stem
        if obf_fqn in jar:
            cat['obf-match'] += 1
            rows03.append([rel, cls, 'obf-direct', obf_fqn, 'jar-ok'])
            continue
        # 情况 B0: 03 文件名为混淆名 (类声明在 4000 字符后截断) — 直接用文件名匹配
        if java.stem in {f.split('.')[-1] for f in jar if f.startswith(GAME_PREFIXES)}:
            cat['obf-match'] += 1
            rows03.append([rel, cls, 'obf-filename', pkg03 + '.' + java.stem, 'jar-ok'])
            continue
        # 情况 B2: 03 内部类 $N (类声明名=外层, 文件=$N)
        if '$' in java.stem and cls in sem_to_obf:
            outer = cls
            cands = sem_to_obf[outer]
            # 取最长共享前缀候选
            best = None
            best_len = -1
            for pkg02, obf02 in cands:
                p2 = pkg02.split('.')
                p3 = pkg03.split('.')
                common = 0
                for a, b in zip(p2, p3):
                    if a == b:
                        common += 1
                    else:
                        break
                if common > best_len:
                    best_len = common
                    best = (pkg02, obf02)
            if best:
                obf_fqn = best[0] + '.' + best[1] + java.stem[len(outer):]
                in_jar = obf_fqn in jar
                cat['semantic'] += 1
                rows03.append([rel, cls, 'semantic-inner', obf_fqn, 'jar-ok' if in_jar else 'jar-MISSING'])
                continue
        # 情况 C: 03 类名混淆但包不同 (子包重命名)
        # 尝试 jar 中同名混淆类
        jar_matches = [f for f in jar if f.split('.')[-1] == cls]
        if jar_matches and any(f.startswith(GAME_PREFIXES) for f in jar_matches):
            # 包不匹配但类名命中
            cat['obf-match'] += 1
            rows03.append([rel, cls, 'obf-simple', '|'.join(jar_matches), 'pkg-diff'])
            continue
        # 情况 D: 无映射 — 检查 jar 中同名混淆类 (跨包) 与 03 独有
        if pkg03.startswith(THIRD_PARTY_PREFIXES) or cls in ('R',):
            cat['third'] += 1
            rows03.append([rel, cls, 'third-party', '', ''])
            continue
        jar_same = sorted(f for f in jar if f.split('.')[-1] == cls and f.startswith(GAME_PREFIXES))
        if jar_same:
            cat['no-map-jarhit'] += 1
            rows03.append([rel, cls, 'no-map-jarhit', '|'.join(jar_same), ''])
            continue
        cat['no-map'] += 1
        rows03.append([rel, cls, 'no-map', '', ''])

    # ============ jar 侧覆盖 ============
    rowsjar = []
    jar_cat = {'semantic-hit': 0, 'obf-hit': 0, 'gap': 0, 'third': 0}
    for fqn in sorted(jar):
        if fqn.startswith(THIRD_PARTY_PREFIXES):
            jar_cat['third'] += 1
            rowsjar.append([fqn, 'third-party', ''])
            continue
        simple = fqn.split('.')[-1]
        # 03 文件: 语义名或混淆名匹配
        hit = None
        sem = obf_to_sem.get((fqn.rsplit('.', 1)[0], simple)) if '.' in fqn else None
        if sem:
            # 找 03 语义名文件
            for java in DEOBFUSCATED_DIR.rglob(sem + '.java'):
                hit = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
                break
        if hit:
            jar_cat['semantic-hit'] += 1
            rowsjar.append([fqn, 'semantic-hit', hit])
            continue
        # jar 内部类 $N: 外层映射推导 (内部类由 javac 从外层生成, 只要外层 03 文件存在即可)
        if '$' in simple:
            outer_obf = simple.split('$')[0]
            suffix = simple[len(outer_obf):]
            outer_pkg = fqn.rsplit('.', 1)[0]
            outer_fqn = outer_pkg + '.' + outer_obf
            sem_outer = obf_to_sem.get((outer_pkg, outer_obf))
            if sem_outer:
                sem_inner = sem_outer + suffix
                for java in DEOBFUSCATED_DIR.rglob(sem_inner + '.java'):
                    hit = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
                    break
            if hit:
                jar_cat['semantic-hit'] += 1
                rowsjar.append([fqn, 'semantic-inner', hit])
                continue
            # 03 侧混淆名文件直接匹配 (外层或 $N 文件)
            for java in DEOBFUSCATED_DIR.rglob(simple + '.java'):
                hit = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
                break
            if hit:
                jar_cat['obf-hit'] += 1
                rowsjar.append([fqn, 'obf-inner', hit])
                continue
            # 外层混淆名文件存在即可 (javac 自动生成 $N)
            for java in DEOBFUSCATED_DIR.rglob(outer_obf + '.java'):
                hit = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
                break
            if hit:
                jar_cat['obf-hit'] += 1
                rowsjar.append([fqn, 'inner-via-outer', hit])
                continue
            # 外层语义名文件存在即可
            if sem_outer:
                for java in DEOBFUSCATED_DIR.rglob(sem_outer + '.java'):
                    hit = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
                    break
                if hit:
                    jar_cat['semantic-hit'] += 1
                    rowsjar.append([fqn, 'inner-via-semantic', hit])
                    continue
        # 混淆名直配: 03 文件与 jar 类同名 (Java public 类名=文件名)
        for java in DEOBFUSCATED_DIR.rglob(simple + '.java'):
            hit = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
            break
        if hit:
            jar_cat['obf-hit'] += 1
            rowsjar.append([fqn, 'obf-hit', hit])
            continue
        jar_cat['gap'] += 1
        rowsjar.append([fqn, 'GAP', ''])

    # 输出
    out3 = GENERATED_DIR / 'b2-03-reverse.csv'
    outj = GENERATED_DIR / 'b2-jar-cover.csv'
    with open(out3, 'w', encoding='utf-8', newline='') as f:
        w = csv.writer(f)
        w.writerow(['file_03', 'class', 'kind', 'obf_fqn', 'status'])
        w.writerows(rows03)
    with open(outj, 'w', encoding='utf-8', newline='') as f:
        w = csv.writer(f)
        w.writerow(['jar_fqn', 'status', 'file_03'])
        w.writerows(rowsjar)

    print(f'\n=== 03 文件侧 ({len(rows03)}) ===')
    for k, v in cat.items():
        print(f'  {k}: {v}')
    print(f'\n=== jar 侧覆盖 ({len(rowsjar)}) ===')
    for k, v in jar_cat.items():
        print(f'  {k}: {v}')
    print(f'\n输出: {out3.relative_to(ROOT)} / {outj.relative_to(ROOT)}')


if __name__ == '__main__':
    main()
