#!/usr/bin/env python3
"""存档差分对齐工具 (v19.113g) — 纯文本存档单位段 ↔ 02b 序列化模板 ↔ 03 对照.

方法 (存档差分法):
1. 沙盒操作注入独特值 (createUnit 坐标 / setTeamCredits 资金)
2. plainTextDebugSave(true) + saveGame → 纯文本存档
3. 本工具: 按 "Saving unit:<类型> (id<eh>)" 分段 → 值流解析 → 与模板字段序对照
4. 输出: 位置: 值 → 02b 字段 (模板) — 供 03 语义名对齐

Usage: python tools/capture/save_diff_align.py <存档路径> [--unit c_tank] [--template am]
"""
import re
import sys
from pathlib import Path

# 02b 序列模板 (字段序) — 02b 各序列化方法逐项提取 (v19.113g)
# 格式: (字段名, 写方法类型) 写方法: a=值(int/float/boolean 自动) g=游戏对象引用 s=字符串
#        c=byte e=枚举(下一行 ordinal) u=#unitType 标记 m=#writeMark d=调试注释
TEMPLATES = {
    'custom.j': [  # 02b custom/j.java:121 a(as) — CustomUnitType 序列化头
        ('版本', 'a:int'), ('e', 'a:float'), ('m', 'a:float'), ('n', 'a:boolean'),
        ('B.size(附件)', 'a:int'), ('r', 'a:boolean'), ('o', 'a:float'), ('f', 'a:float'),
        ('s', 'a:float'), ('v', 'a:boolean'), ('dT长度', 'c:byte'),
    ],
    'y': [  # 02b units/y.java:205 a(as) — UnitType 序列化 (pathing 段)
        ('b', 'a'), ('c', 'a'), ('cL[0].e', 'a:float'), ('f', 'a'), ('f(重复)', 'a:int'),
        ('P', 'e:Enum'), ('R(引用)', 'g'), ('S', 'a'), ('U', 'a'), ('V', 'a'),
        ('注释', 'd:pathing_active'), ('k', 'a:boolean'), ('l', 'a:float'), ('m', 'a:float'),
        ('s', 'a:float'), ('ad(引用)', 'g'), ('ae', 'a:boolean'), ('af', 'a:boolean'),
        ('aj', 'a:boolean'), ('ak', 'a:float'), ('al', 'a:float'), ('am', 'a:float'),
        ('an', 'a:float'), ('ac', 'a:float'), ('注释', 'd:activePathCount'),
        ('aw(路径数)', 'a:int'),
    ],
    'l': [  # 02b game/l.java:199 a(as) — 弹丸序列化
        ('eo(弹丸x)', 'a:float'), ('ep(弹丸y)', 'a:float'), ('a(类型标记:2=核弹)', 'a:int'),
        ('b', 'a:int'), ('c(地图宽)', 'a:int'), ('d(地图高)', 'a:int'),
        ('e(弹丸类型)', 'e:Enum'), ('f', 'a:int'),
    ],
    'am': [  # 02b units/am.java:159 a(as) — UnitInstance 序列化 (循环感知版 v19.113j)
        ('bM', 'a:boolean'), ('bQ(引用)', 'g'), ('bR(引用)', 'g'), ('bS', 'a:float'),
        ('bT', 'a:boolean'), ('isDead', 'a:boolean'), ('bW', 'a:long'), ('player', 'g'),
        ('bZ', 'a:float'), ('ca', 'a:float'), ('cc', 'a:float'), ('cd', 'a:float'),
        ('cf', 'a:float'), ('cg(朝向角)', 'a:float'), ('cj', 'a:float'), ('ck', 'a:float'),
        ('cl', 'a:float'), ('cm', 'a:float'), ('cp', 'a:boolean'), ('cs', 'a:boolean'),
        ('hp', 'a:float'), ('maxHp', 'a:float'), ('cK', 'a:boolean'),
        ('cL[0].a(炮塔角)', 'a:float'), ('cL[0].d', 'a:float'), ('cN(挂载)', 'g'),
        ('版本26', 'c:int'), ('cU', 'a:float'), ('cV', 'a:float'), ('ce(方向)', 'a:float'),
        ('ch(朝向角)', 'a:float'), ('turretCount', 'a:int', 'TURRETS'),
        ('bs', 'a:int'), ('cx', 'a:float'), ('cy', 'a:float'), ('cz', 'a:float'), ('cA', 'a:float'),
        ('cq', 'a:boolean'), ('cr', 'a:boolean'), ('ct', 'a:boolean'), ('bN', 'a:boolean'),
        ('cB', 'a:float'), ('ci', 'a:boolean'), ('dF!=null', 'a:boolean'),
        ('cw', 'a:float'), ('bt(引用)', 'g'), ('cE', 'a:int'), ('cF', 'a:int'),
        ('creationSequence', 'a:int'), ('bA', 'a:int'), ('bB', 'a:int'), ('bC', 'a:int'),
        ('bO', 'a:boolean'), ('bP', 'a:boolean'),
    ],
}
TURRETS = ('炮塔组', [('a', 'a:float'), ('c', 'a:float'), ('d', 'a:float'), ('e', 'a:float'),
                     ('f', 'a:float'), ('h', 'a:float'), ('i', 'a:float'), ('j(目标)', 'g')])


# 存档标记 → 值解析
def parse_values(text):
    """存档文本 → [(类型, 值)] 值流. 类型: int/float/bool/string/enum/gobj/byte/mark/comment/unittype"""
    vals = []
    lines = text.split('\n')
    i = 0
    while i < len(lines):
        l = lines[i].rstrip()
        m = re.match(r'#writeIfDebugOnly: (.*)', l)
        if m:
            vals.append(('comment', m.group(1)))
            i += 1
            continue
        if l.startswith('#int:'):
            vals.append(('int', lines[i + 1].strip()))
            i += 2
            continue
        if l.startswith('#writeFloat'):
            vals.append(('float', lines[i + 1].strip()))
            i += 2
            continue
        if l.startswith('#writeLong'):
            vals.append(('long', lines[i + 1].strip()))
            i += 2
            continue
        if l.startswith('#writeGameObject:'):
            vals.append(('gobj', lines[i + 1].strip()))
            i += 2
            continue
        if l.startswith('#unitType:'):
            vals.append(('unittype', lines[i + 1].strip()))
            i += 2
            continue
        if l.startswith('#Enum:'):
            vals.append(('enum', l.split(':', 1)[1].strip()))
            vals.append(('int', lines[i + 1].strip()))  # ordinal
            i += 2
            continue
        if l.startswith('#writeMark:'):
            vals.append(('mark', lines[i + 1].strip()))
            i += 2
            continue
        if l.startswith('#writeShort'):
            vals.append(('short', lines[i + 1].strip()))
            i += 2
            continue
        if l in ('true', 'false'):
            vals.append(('bool', l))
            i += 1
            continue
        if re.match(r'^-?\d+$', l):
            vals.append(('int', l))
            i += 1
            continue
        if re.match(r'^-?\d+\.\d+$', l):
            vals.append(('float', l))
            i += 1
            continue
        i += 1
    return vals


TURRET_BODY = [('a', 'a:float'), ('c', 'a:float'), ('d', 'a:float'), ('e', 'a:float'),
               ('f', 'a:float'), ('h', 'a:float'), ('i', 'a:float'), ('j(目标)', 'g')]
TURRET_TRAIL = ('cM(循环内)', 'a:boolean')


def align(text, template_key, unit=None):
    """单位段文本 → 模板对照表."""
    vals = parse_values(text)
    # am 段自动定位: bM(bool)+bQ(gobj)+bR(gobj)+bS(float) 四连特征 (v19.113h)
    if template_key == 'am':
        for i in range(len(vals) - 3):
            if vals[i][0] == 'bool' and vals[i+1][0] == 'gobj' and vals[i+2][0] == 'gobj' and vals[i+3][0] == 'float':
                vals = vals[i:]
                break
    tmpl = TEMPLATES[template_key]
    out = []
    vi = 0
    for item in tmpl:
        name = item[0]
        if len(item) >= 3 and item[2] == 'TURRETS':
            # 循环感知: 读炮塔数 N → 展开 N 个循环体 + cM
            if vi < len(vals):
                n_tur = int(vals[vi][1]) if vals[vi][1].lstrip('-').isdigit() else 0
                out.append(f'{vi:3d}  [{vals[vi][0]:8s}] {vals[vi][1]:>14s}  ← {name} = {n_tur}')
                vi += 1
                for t_i in range(n_tur):
                    for tname, tkind in TURRET_BODY:
                        if vi < len(vals):
                            out.append(f'{vi:3d}  [{vals[vi][0]:8s}] {vals[vi][1]:>14s}  ← 炮塔{t_i}.{tname}')
                            vi += 1
                    if vi < len(vals):
                        out.append(f'{vi:3d}  [{vals[vi][0]:8s}] {vals[vi][1]:>14s}  ← 炮塔{t_i}.{TURRET_TRAIL[0]}')
                        vi += 1
                continue
        if vi < len(vals):
            t, v = vals[vi]
            out.append(f'{vi:3d}  [{t:8s}] {v:>14s}  ← {name}')
            vi += 1
    return out, len(vals), len(tmpl)


def split_units(save_text):
    """按 Saving unit 分段 → {id: (类型, 段文本)}"""
    segs = {}
    lines = save_text.split('\n')
    cur_id = None
    cur = []
    for l in lines:
        m = re.search(r'Saving unit:(\S+) \(id(\d+)\)', l)
        if m:
            if cur_id is not None:
                segs[cur_id] = (cur_type, '\n'.join(cur))
            cur_id = m.group(2)
            cur_type = m.group(1)
            cur = []
            continue
        if cur_id is not None:
            cur.append(l)
    if cur_id is not None:
        segs[cur_id] = (cur_type, '\n'.join(cur))
    return segs


def main():
    args = [a for a in sys.argv[1:] if not a.startswith('--')]
    if not args:
        print(__doc__)
        return 1
    save_path = args[0]
    unit = None
    template = None
    for i, a in enumerate(sys.argv):
        if a == '--unit' and i + 1 < len(sys.argv):
            unit = sys.argv[i + 1]
        if a == '--template' and i + 1 < len(sys.argv):
            template = sys.argv[i + 1]
    text = open(save_path, encoding='utf-8', errors='ignore').read()
    segs = split_units(text)
    if unit is None:
        print(f'存档单位段: {len(segs)} 个 (--unit <类型> 指定; 已知类型: {sorted(set(t for t, _ in segs.values()))[:12]})')
        return 0
    target = None
    for uid, (t, seg) in segs.items():
        if t == unit:
            target = (uid, seg)
    if target is None:
        print(f'未找到单位类型 {unit}')
        return 1
    uid, seg = target
    print(f'=== 单位 {unit} (id{uid}) ===')
    if template:
        rows, nv, nt = align(seg, template)
        print('\n'.join(rows))
        print(f'--- 值流 {nv} 项 (模板 {nt} 项) ---')
    else:
        vals = parse_values(seg)
        print('值流 (前 40 项):')
        for i, (t, v) in enumerate(vals[:40]):
            print(f'{i:3d}  [{t:8s}] {v}')
        print(f'--- 共 {len(vals)} 项 ---')
    return 0


if __name__ == '__main__':
    sys.exit(main())
