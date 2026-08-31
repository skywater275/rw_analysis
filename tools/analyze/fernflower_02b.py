#!/usr/bin/env python3
"""FernFlower 第二反编译源生成 (v19.111) — 02b-decompiled/ 交叉验证 CFR 缺陷.

实测对比 (v19.111):
- gameFramework.m.y (TextureManagerInterface): FernFlower 67/67 方法, CFR 丢 37 (曾需 javap 手工重建)
- 输出风格: var1 参数名 / 隐式接口方法 / strictfp 保留 / jar 级输出
- -ren=1 模式: 混淆名重命名 class_0/method_0 (解混淆重命名)

Usage: python tools/analyze/fernflower_02b.py [--classes pkg/Cls,...] [--full]
  --classes: 反编译指定类 (02b 补充验证)
  --full:    全 jar 反编译 (1,698 类, ~15-20 分钟)
"""
import subprocess
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
GAME_DIR = ROOT.parent
FF_JAR = Path('C:/tmp/fernflower.jar')
OUT_DIR = ROOT / '02b-decompiled'
JAVA = GAME_DIR / 'jvm/bin/java.exe'
sys.stdout.reconfigure(encoding='utf-8', errors='replace')


def main():
    cls_arg = None
    full = '--full' in sys.argv
    for idx, a in enumerate(sys.argv):
        if a == '--classes' and idx + 1 < len(sys.argv):
            cls_arg = sys.argv[idx + 1]
    if not FF_JAR.exists():
        print(f'缺少 {FF_JAR} — 下载: https://the.bytecode.club/fernflower.jar')
        return 1
    if cls_arg:
        # 语义名 → 混淆名 (class-discoveries)
        import csv
        csv.field_size_limit(10 * 1024 * 1024)
        sem2obf = {}
        for r in csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')):
            if r and r[0] == 'class' and len(r) >= 4:
                sem2obf[r[3]] = (r[1], r[2])
        resolved = []
        for c in cls_arg.split(','):
            c = c.strip()
            if c in sem2obf:
                pkg, obf = sem2obf[c]
                resolved.append(pkg + '/' + obf)
            else:
                resolved.append(c.replace('.', '/'))
        cls_arg = ','.join(resolved)
        print('混淆名解析:', cls_arg)
        # 指定类: 提取 class 到临时目录反编译
        tmp = Path('C:/tmp/ff_single')
        import shutil
        if tmp.exists():
            shutil.rmtree(tmp)
        (tmp / 'in').mkdir(parents=True)
        with zipfile.ZipFile(GAME_DIR / 'game-lib.jar') as z:
            for c in cls_arg.split(','):
                c = c.strip().replace('.', '/')
                if c.endswith('.class'):
                    src = c
                else:
                    src = c + '.class'
                if src in z.namelist():
                    out = tmp / 'in' / src
                    out.parent.mkdir(parents=True, exist_ok=True)
                    out.write_bytes(z.read(src))
                    print(f'提取 {src}')
                else:
                    print(f'[缺] {src}')
        r = subprocess.run([str(JAVA), '-jar', str(FF_JAR),
                            str(tmp / 'in'), str(tmp / 'out')],
                           capture_output=True, text=True, encoding='utf-8',
                           errors='replace', timeout=300)
        print(r.stderr[-400:] if r.stderr else '')
        # 拷贝到 02b (包结构)
        for java in (tmp / 'out').rglob('*.java'):
            rel = java.relative_to(tmp / 'out')
            dst = OUT_DIR / rel
            dst.parent.mkdir(parents=True, exist_ok=True)
            dst.write_bytes(java.read_bytes())
            print(f'02b: {rel}')
    elif full:
        r = subprocess.run([str(JAVA), '-jar', str(FF_JAR),
                            str(GAME_DIR / 'game-lib.jar'), str(OUT_DIR)],
                           capture_output=True, text=True, encoding='utf-8',
                           errors='replace', timeout=1800)
        n = len(list(OUT_DIR.rglob('*.java')))
        print(f'全量反编译完成: {n} 个源文件')
    else:
        print('用法: --classes pkg/Cls 或 --full')
        return 1
    return 0


if __name__ == '__main__':
    sys.exit(main())
