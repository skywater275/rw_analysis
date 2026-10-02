"""
rwlib.mappings — supplement.csv 统一读写封装

提供映射数据库的加载、保存、去重功能。
所有 batch_add 脚本和验证器都应该通过此模块访问 supplement.csv。

当前解决了以下重复代码:
    - CSV 加载/解析 — 29 个脚本各自用 csv.DictReader 直接操作
    - 去重键计算 — 15+ batch_add 脚本各有不同的 dedup 逻辑
    - append 模式 — 8 个脚本逐字复制了相同的 csv.DictWriter 模式
    - 类映射加载 — 5 个脚本各自从 mappings.csv/json + class-discoveries.csv 构建

使用方式:
    from rwlib.mappings import load_supplement, save_supplement, load_class_map
    rows = load_supplement()
    existing_keys = {(r['type'], r['obfuscated_package'], r['obfuscated_class'],
                      r['obfuscated_member']) for r in rows}
"""

import csv
import os
import shutil
from collections import defaultdict
from pathlib import Path

from .config import SUPPLEMENT_CSV, MAPPINGS_CSV, MAPPINGS_JSON, CLASS_DISCOVERIES, ROOT

# supplement.csv 列结构
SUPPLEMENT_COLS = [
    'type', 'obfuscated_package', 'obfuscated_class',
    'obfuscated_member', 'meaningful_name', 'notes', 'verified'
]


def load_supplement(csv_path=None):
    """
    加载 supplement.csv, 返回 (header, rows)。

    返回:
        header: str — 原始表头行
        rows: List[Dict] — 解析后的数据行 (仅 type in ('field','method') 的有效行)

    自动跳过注释行 (# 开头) 和空行。
    使用 csv.reader 正确解析引号字段 (修复 v18.2 发现的引号膨胀bug)。
    """
    import io
    path = Path(csv_path) if csv_path else SUPPLEMENT_CSV
    rows = []
    header = ','.join(SUPPLEMENT_COLS)

    if not path.exists():
        return header, rows

    # 增大字段限制，防止大notes字段导致崩溃
    csv.field_size_limit(10 * 1024 * 1024)

    with open(path, encoding='utf-8', errors='replace') as f:
        raw = f.read()

    # 用 csv.reader 正确解析引号字段 (修复 line.split(',') 导致的引号膨胀)
    reader = csv.reader(io.StringIO(raw))
    header_row = next(reader)
    header = ','.join(header_row) if len(header_row) == len(SUPPLEMENT_COLS) else ','.join(SUPPLEMENT_COLS)

    for cols in reader:
        if len(cols) < 2 or cols[0].strip() not in ('field', 'method'):
            continue
        row = {}
        for i, col_name in enumerate(SUPPLEMENT_COLS):
            row[col_name] = cols[i] if i < len(cols) else ''
        rows.append(row)

    return header, rows


def dedup_key(row):
    """
    计算去重键: (type, package, class, member_with_signature)

    用于判断两个映射行是否指向同一个混淆成员。
    member 字段可能包含方法签名 (如 "a(int,float)"), 需要精确匹配。

    这是所有 batch_add 脚本共用的核心逻辑。
    """
    return (
        row.get('type', '').strip(),
        row.get('obfuscated_package', '').strip(),
        row.get('obfuscated_class', '').strip(),
        row.get('obfuscated_member', '').strip(),
    )


def existing_keys(rows):
    """
    从已加载的行构建去重键集合。

    返回: Set[Tuple[str,str,str,str]]
    """
    return {dedup_key(r) for r in rows}


def save_supplement(rows, header=None, csv_path=None, backup=True):
    """
    原子写入 supplement.csv (先写 .tmp 再 rename)。

    参数:
        rows: List[Dict] — 要写入的行
        header: str — 表头 (默认使用标准 SUPPLEMENT_COLS)
        csv_path: Path — 目标文件路径 (默认 SUPPLEMENT_CSV)
        backup: bool — 是否在写入前创建 .bak 备份
    """
    path = Path(csv_path) if csv_path else SUPPLEMENT_CSV

    # 备份
    if backup and path.exists():
        bak = path.with_suffix(path.suffix + '.bak')
        shutil.copy2(path, bak)

    # 原子写入
    tmp = path.with_suffix(path.suffix + '.tmp')
    with open(tmp, 'w', encoding='utf-8', newline='') as f:
        h = header if header else ','.join(SUPPLEMENT_COLS)
        f.write(h + '\n')
        writer = csv.DictWriter(f, fieldnames=SUPPLEMENT_COLS, extrasaction='ignore')
        for row in rows:
            writer.writerow(row)

    tmp.replace(path)


def append_mappings(new_rows, csv_path=None, dry_run=False):
    """
    去重追加新映射到 supplement.csv。

    参数:
        new_rows: List[Dict] — 要添加的新行
        csv_path: Path — 目标文件 (默认 SUPPLEMENT_CSV)
        dry_run: bool — True 时只打印日志, 不实际写入

    返回:
        (added, skipped): 新增行数, 跳过行数
    """
    header, existing = load_supplement(csv_path)
    keys = existing_keys(existing)
    added = 0
    skipped = 0

    for row in new_rows:
        key = dedup_key(row)
        # 跳过空 meaningful_name 的行
        if not row.get('meaningful_name', '').strip():
            skipped += 1
            continue
        if key in keys:
            skipped += 1
            continue
        existing.append(row)
        keys.add(key)
        added += 1

    if not dry_run and added > 0:
        save_supplement(existing, header=header, csv_path=csv_path)

    return added, skipped


def load_class_map():
    """
    加载所有类重命名映射: {混淆FQN: 可读类名}

    合并三个来源:
        mappings.csv — 类重命名 (238条)
        mappings.json — 类重命名 (100条, JSON格式)
        class-discoveries.csv — 类发现 (486条)

    返回: Dict[str, str]

    当前有 5 个脚本各自实现了此逻辑:
        apply_enhanced.py, type_position_renamer.py, reference_priority.py,
        batch_add_v1000.py, batch_add_v1001.py
    """
    class_map = {}

    # 1. CSV 格式
    if MAPPINGS_CSV.exists():
        with open(MAPPINGS_CSV, encoding='utf-8') as f:
            for row in csv.DictReader(f):
                if row.get('type') == 'class' and row.get('meaningful_name'):
                    pkg = row.get('obfuscated_package', '')
                    cls = row.get('obfuscated_class', '')
                    fqn = f"{pkg}.{cls}" if pkg and cls else (pkg or cls)
                    class_map[fqn] = row['meaningful_name']

    # 2. JSON 格式
    if MAPPINGS_JSON.exists():
        import json
        with open(MAPPINGS_JSON, encoding='utf-8') as f:
            data = json.load(f)
            if isinstance(data, dict):
                class_map.update(data)

    # 3. class-discoveries.csv
    if CLASS_DISCOVERIES.exists():
        with open(CLASS_DISCOVERIES, encoding='utf-8') as f:
            for row in csv.DictReader(f):
                pkg = row.get('obfuscated_package', '')
                cls = row.get('obfuscated_class', '')
                name = row.get('meaningful_name', '')
                if pkg and cls and name:
                    fqn = f"{pkg}.{cls}"
                    if fqn not in class_map:
                        class_map[fqn] = name

    return class_map


def get_mappings_for_class(fqn, rows=None):
    """
    获取指定类的所有映射。

    参数:
        fqn: 完全限定类名 (如 "com.corrodinggames.rts.game.units.am")
        rows: 预加载的行列表 (可选, 不传则从文件加载)

    返回: Dict[str, List[Dict]] — {'fields': [...], 'methods': [...]}
    """
    if rows is None:
        _, rows = load_supplement()

    result = {'fields': [], 'methods': []}
    pkg, cls = (fqn.rsplit('.', 1) + [''])[:2] if '.' in fqn else ('', fqn)

    for row in rows:
        if row.get('obfuscated_package') == pkg and row.get('obfuscated_class') == cls:
            result['fields' if row.get('type') == 'field' else 'methods'].append(row)

    return result
