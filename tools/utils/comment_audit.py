"""注释检测 v1: 03-deobfuscated 注释覆盖率统计。

输出: build/comment-audit.csv (每文件注释指标) + 控制台汇总。
指标:
- 总行数 / 注释行数 / 注释率
- 类头注释 (文件前 5 行内注释块)
- 类声明前注释
- 方法声明注释率 (方法前 2 行内有 // 或 /* 注释)
- 字段注释率
"""
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEOBFUSCATED_DIR

METHOD_RE = re.compile(
    r'(?m)^\s*(?:(?:public|protected|private)\s+)?'
    r'(?:(?:static|final|synchronized|strictfp|native|abstract|transient)\s+)*'
    r'(?:[\w<>\[\].,?]+\s+)+([\w$]+)\s*\(')
FIELD_RE = re.compile(
    r'(?m)^\s*(?:(?:public|protected|private)\s+)?'
    r'(?:(?:static|final|transient|volatile|synchronized)\s+)*'
    r'(?:[\w<>\[\].,?]+\s+)+([\w$]+)\s*(?:=|;)')


def audit(src):
    lines = src.split('\n')
    total = len(lines)
    # 注释行: 行首 // 或 /* 或 * (块内) 或行尾 //
    comment_lines = 0
    sem_comment_lines = 0  # 语义注释: 含中文 (项目中文注释纪律)
    in_block = False
    line_comments = {}  # 行号 -> True (该行有注释)
    for i, ln in enumerate(lines):
        s = ln.strip()
        has = False
        if in_block:
            has = True
            if '*/' in s:
                in_block = False
        if s.startswith('//'):
            has = True
        if s.startswith('/*'):
            has = True
            in_block = True
            if '*/' in s:
                in_block = False
        if has:
            comment_lines += 1
            line_comments[i] = True
            if re.search(r'[\u4e00-\u9fff]', ln):
                sem_comment_lines += 1
    # 类头注释: 前 5 行内有注释
    head_comment = any(i in line_comments for i in range(0, min(5, total)))
    # 类声明前 4 行内有语义注释
    cls_line = None
    for i, ln in enumerate(lines):
        if re.match(r'^\s*(?:public |protected |final |abstract |strictfp |static )*(?:class|enum|interface) ', ln):
            cls_line = i
            break
    cls_comment = False
    cls_sem = False
    if cls_line is not None:
        for i in range(max(0, cls_line - 4), cls_line):
            if i in line_comments:
                cls_comment = True
                if re.search(r'[\u4e00-\u9fff]', lines[i]):
                    cls_sem = True
    # 方法/字段注释率
    mdecls = list(METHOD_RE.finditer(src))
    fdecls = list(FIELD_RE.finditer(src))
    m_annotated = 0
    for m in mdecls:
        ln = src[:m.start()].count('\n')
        if any(i in line_comments for i in range(max(0, ln - 2), ln)):
            m_annotated += 1
    f_annotated = 0
    for m in fdecls:
        ln = src[:m.start()].count('\n')
        if any(i in line_comments for i in range(max(0, ln - 2), ln)):
            f_annotated += 1
    return {
        'total': total, 'comment': comment_lines,
        'sem_comment': sem_comment_lines,
        'rate': round(comment_lines / total * 100, 1) if total else 0,
        'sem_rate': round(sem_comment_lines / total * 100, 1) if total else 0,
        'head': head_comment, 'cls_comment': cls_comment, 'cls_sem': cls_sem,
        'm_total': len(mdecls), 'm_annotated': m_annotated,
        'f_total': len(fdecls), 'f_annotated': f_annotated,
    }


def main():
    rows = []
    for p in sorted(DEOBFUSCATED_DIR.rglob('*.java')):
        rel = str(p.relative_to(ROOT)).replace('\\', '/')
        src = p.read_text(encoding='utf-8', errors='ignore')
        a = audit(src)
        rows.append((rel, a))
    # CSV
    with open(ROOT / 'build' / 'comment-audit.csv', 'w', encoding='utf-8', newline='') as f:
        w = csv.writer(f)
        w.writerow(['file', 'total', 'comment', 'sem_comment', 'rate', 'sem_rate',
                    'head', 'cls_comment', 'cls_sem', 'm_total', 'm_annotated',
                    'f_total', 'f_annotated'])
        for rel, a in rows:
            w.writerow([rel, a['total'], a['comment'], a['sem_comment'], a['rate'], a['sem_rate'],
                        a['head'], a['cls_comment'], a['cls_sem'],
                        a['m_total'], a['m_annotated'], a['f_total'], a['f_annotated']])
    # 汇总
    n = len(rows)
    no_head = [r for r in rows if not r[1]['head']]
    no_cls = [r for r in rows if not r[1]['cls_comment']]
    no_cls_sem = [r for r in rows if not r[1]['cls_sem']]
    zero_comment = [r for r in rows if r[1]['comment'] == 0]
    zero_sem = [r for r in rows if r[1]['sem_comment'] == 0]
    low_rate = [r for r in rows if r[1]['rate'] < 5]
    total_lines = sum(r[1]['total'] for r in rows)
    total_comments = sum(r[1]['comment'] for r in rows)
    total_sem = sum(r[1]['sem_comment'] for r in rows)
    print(f'文件总数: {n} | 总行数: {total_lines}')
    print(f'注释行: {total_comments} ({total_comments/total_lines*100:.1f}%) | 语义注释行: {total_sem} ({total_sem/total_lines*100:.1f}%)')
    print(f'无文件头注释: {len(no_head)} | 无类声明前注释: {len(no_cls)} | 无类声明前语义注释: {len(no_cls_sem)}')
    print(f'零注释文件: {len(zero_comment)} | 零语义注释文件: {len(zero_sem)} | 注释率<5%: {len(low_rate)}')
    # 方法/字段注释率汇总
    mt = sum(r[1]['m_total'] for r in rows)
    ma = sum(r[1]['m_annotated'] for r in rows)
    ft = sum(r[1]['f_total'] for r in rows)
    fa = sum(r[1]['f_annotated'] for r in rows)
    print(f'方法声明: {mt} (注释 {ma}, {ma/mt*100:.1f}%) | 字段声明: {ft} (注释 {fa}, {fa/ft*100:.1f}%)')
    print(f'零语义注释文件样例: {[r[0] for r in zero_sem[:6]]}')
    print(f'CSV: build/comment-audit.csv')


if __name__ == '__main__':
    main()
