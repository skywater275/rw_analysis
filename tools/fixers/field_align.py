import csv, re, subprocess, json, sys
from collections import defaultdict
from pathlib import Path
csv.field_size_limit(10*1024*1024)
sys.path.insert(0, '.')
from rwlib.config import ROOT
os.chdir(str(ROOT)) if False else None
rows = list(csv.DictReader(open('compile-errors.csv', encoding='utf-8')))
idx = json.load(open('mappings/generated/identity-index.json', encoding='utf-8'))
rev = idx['rev']
JAVAP = "C:/Users/28210/Downloads/Rusted Warfare/jdk/jdk-17.0.2/bin/javap.exe"
FIELD_RE = re.compile(r"^\s*(?:public|private|protected|static|final|volatile|transient|\s)+([\w<>.\[\]?,]+)\s+(\w+)\s*(?:=|;)")

combos = defaultdict(list)
for r in rows:
    if 'cannot find symbol' not in r['message']:
        continue
    sym = (r['symbol'] or '').strip().split('(')[0]
    if not re.fullmatch(r"[a-z][a-zA-Z0-9]{0,3}", sym):
        continue
    m = re.search(r'variable (\w+) of type (\S+)', r['location'] or '')
    if m:
        combos[(m.group(2), sym)].append((r['file'], r['line'], m.group(1)))

def get_jar_fields(obf):
    out = subprocess.run([JAVAP, '-p', '-cp', 'RustedWarfare/game-lib.jar', obf],
                         capture_output=True, text=True, encoding='utf-8', errors='replace', timeout=30).stdout
    fields = []
    for line in out.split('\n'):
        m = re.match(r"(?:public|private|protected|static|final|volatile|transient|\s)+([\w<>.\[\]?,]+)\s+(\w+);", line.strip())
        if m:
            fields.append((m.group(1), m.group(2)))
    return fields

def get_03_fields(fqn):
    p = Path('03-deobfuscated') / (fqn.replace('.', '/') + '.java')
    if not p.exists():
        return None
    fields = []
    started = False
    for line in p.read_text(encoding='utf-8', errors='replace').split('\n'):
        if 'class ' in line and '{' in line:
            started = True
            continue
        if not started:
            continue
        if '(' in line:
            break
        m = FIELD_RE.match(line)
        if m:
            fields.append((m.group(1).strip(), m.group(2)))
        if '}' in line:
            break
    return fields

plan = []
for (typ, sym), errs in sorted(combos.items(), key=lambda x: -len(x[1]))[:400]:
    cand = {jf.relative_to('03-deobfuscated').as_posix().replace('.java','').replace('/','.')
            for jf in Path('03-deobfuscated').rglob('*.java')
            if jf.stem == typ}
    if len(cand) != 1:
        continue
    fqn = next(iter(cand))
    obf = rev.get(fqn)
    if not obf:
        continue
    try:
        jf = get_jar_fields(obf)
        f3 = get_03_fields(fqn)
    except Exception:
        continue
    if f3 is None or len(f3) < 2:
        continue
    ji = next((i for i, (t, n) in enumerate(jf) if n == sym), None)
    if ji is None or ji >= len(f3):
        continue
    newname = f3[ji][1]
    if newname == sym or len(newname) < 3 or newname in ('true', 'false', 'null'):
        continue
    plan.append((typ, sym, newname, errs))

byfile = defaultdict(list)
for typ, sym, newname, errs in plan:
    for fpath, lno, var in errs:
        byfile[fpath].append((lno, var, sym, newname))
fixed = 0
for fpath, edits in byfile.items():
    lines = Path(fpath).read_text(encoding='utf-8', errors='replace').split('\n')
    for lno, var, sym, newname in edits:
        i = int(lno) - 1
        if 0 <= i < len(lines):
            if f"{var}.{sym}" in lines[i]:
                lines[i] = re.sub(rf"\b{re.escape(var)}\.{re.escape(sym)}\b", f"{var}.{newname}", lines[i])
                fixed += 1
    Path(fpath).write_text('\n'.join(lines), encoding='utf-8')
print(f"对齐替换: {len(plan)} 组, {fixed} 处")
