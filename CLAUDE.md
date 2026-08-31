# CLAUDE.md — Rusted Warfare 解混淆项目

> **最新**: v19.133f98 (B1-B5.5) | 2026-08-30 | **B5 行为一致性收敛完成** (运行时反馈 10 项修复, 启动 0 崩溃 + 功能全通); **B5.5 覆盖率探究**: JDK17 双 jar 方案证伪 (类包同名深包=javac 绝对硬限制), 核验覆盖 251/1,698=14.8% (天花板), 运行时反向类 14.4%, 映射 unverified 49.3%; 目标口径修订为“行为一致可运行的混合形态”; 详见 sessions/PHASE-A-v19.133f98-B5.5.md
> **Phase B 状态**: B1 编译清零 (41,402→0) → B2 反向映射核对 (0 缺口) → B3 全量构建 (0 错误) → B4 运行验证 (可启动) → **B5 行为一致性收敛完成** (运行时反馈驱动 10 项修复, 启动 0 崩溃 + 功能全通)。残余: GUI/回放深度验证 (待确认)。
> **过往战役** (v19.107 → v19.133f98-B4 共 86 篇): 见 会话总览 与 [sessions/](docs/_archive/sessions/)

## 语言规范

- **所有对话和文档使用中文** / **代码注释使用中文** / **错误提示使用中文** / **代码标识符使用英文**

## 项目结构

```
rw源码逆向/
├── README.md / CLAUDE.md / .gitignore / .gitattributes (行尾规范: 存储 LF, 工作区 CRLF) / .editorconfig (v19.133f98 格式统一)
├── RustedWarfare/     — 干净游戏本体 (v1.15, 仅磁盘保留, 不入 git; game-lib.jar 编译目标;
│                       v19.133f98 用户重放干净版本, 路径多候选自动查找见 tools/rwlib/config.py)
├── 01-classes/        — 388 原始 .class (字节码真源 T0 抽样)
├── 02-decompiled/     — 1,698 CFR 反编译源 (混淆名 T2)
├── 02b-decompiled/    — FernFlower 交叉验证源 (v19.111 起)
├── 03-deobfuscated/   — 1,739 解混淆输出 (可读名, 编译主线)
├── 04-javas/ 05-gamelib/ 06-lib/  — 已删 (v19.133f98 整理): 解包产物冗余于 jar/
│                       game-lib.jar 直接作字节码源 (gamelib_audit 改 zipfile 遍历)
├── mappings/          — supplement.csv (10,395) + class-discoveries.csv (1,294) + domains/ (12域) + generated/
├── docs/              — STATUS (口径) + ORGANIZATION (12域规范) + PENDING (待定) + 01-units/..12-utility/ (31篇)
│                       + deobfuscation/ (分析 9 篇 + sessions 86 篇 + PLAN.md v3.23 + TOOLS-TREE.md + METHODOLOGY.md)
│                       + generated/ + _archive/; 导航索引在根 README.md 第三节 (v19.133f98 四 README 合并归一)
├── mcp/               — 游戏 MCP 独立目录 (v19.133f98: 完全自包含, 与项目隔离; mcp_game_server.py 293 工具 + script_api.json 副本 + unit_type_names.json 52 内置枚举名 + unit_names.json 125 ini 注册名 + README; 不依赖项目文件; RW_GAME_ROOT 可覆盖游戏根; .mcp.json 注册)
├── tools/             — manager.py (616 注册, v19.133f98 全量自动注册) + core/ + gates/ + fixers/ + utils/ + capture/
    └── rwlib/          — 共享库 (bytecode/config/mappings, v1.1; v19.133f98 并入 tools/ 下)
```

> 完整工具链结构树 (含每个修复器条目): [docs/deobfuscation/TOOLS-TREE.md](docs/deobfuscation/TOOLS-TREE.md)

## 当前状态 (v19.133f98, B1-B5 达成)

| 分支 | 基线 | 当前 | 状态 |
|------|------|------|------|
| **old_deobfuscated** (主) | 41,402 | **0** (-100.0%) | B1 编译清零, gate PASSED |
| apply_enhanced_rebuild | 466 | 冻结保留 | 不投入 (docs/deobfuscation/PLAN.md 已证伪"2 errors"假象) |
| **B3 反向构建** | — | **0 错误** | 反向→javac→game-lib-reverse.jar (1,834 类) |
| **B4 运行验证** | — | **可启动可运行** | 反向 jar 替换 game-lib.jar headless 启动 0 异常 |
| **B5 行为一致性** | — | **0 崩溃全通** | 运行时反馈 10 项修复; 开局/建单位/存档/AI 全通 |

| 指标 | 数值 |
|------|------|
| 总映射数 | **10,395** (字段 6,072 + 方法 4,323) |
| 类映射 | **1,294** (B5: 删 Main/g/GameLauncher 6 行错误映射 + 补 GlobalStateFactory + DebugEditor 迁移) |
| 官方语义名 | **482** (game-lib.jar 审计, 0 缺口) |
| 损伤家族 | **40+** (清单见 docs/deobfuscation/PLAN.md) |
| 真实未解析 | **162** (unresolved.txt; 仲裁候选 9 条) |
| 残余清单 | 映射验证战役 (unverified 49.3%); GUI/回放深度验证; 存档双向兼容; 撞车剔除 500 项 |

## 核心约束

1. **01-classes/ 是唯一字节码真源** — 所有验证基于 javap
2. **RustedWarfare/game-lib.jar 是编译目标** — 使用 24 个真实 jars 编译
3. **supplement.csv 是唯一映射数据库** (10,395 条)
4. **javap CJK路径已修复** — rwlib/bytecode.py v2
5. **脚本以 tools/rwlib/ 统一管理** (新脚本强制)
6. **RustedWarfare/** 是完整游戏安装, 也是项目解析目标
7. **每次任务完成必须执行「任务收尾更新清单」** (见下), 禁止只改一个导航文件
8. **tools/ 脚本开发规范** (见下)

### 脚本结构模板

```python
#!/usr/bin/env python3
"""<用途>. Usage: python tools/<path> [--dry-run] [--apply]"""
import sys; from pathlib import Path
ROOT = Path(__file__).resolve().parents[3]  # tools/*/ → 项目根
sys.path.insert(0, str(ROOT))
from rwlib.config import ROOT, CLASSES_DIR, DEOBFUSCATED_DIR, MAPPINGS_DIR
import csv; csv.field_size_limit(10 * 1024 * 1024)  # 如读 supplement
```

### 必须 (MUST) / 建议 (SHOULD)

| # | 规则 | # | 规则 |
|---|------|---|------|
| M1 | 路径统一: rwlib.config 常量, 禁 CWD 相对路径 | S1 | `--dry-run` 标志: 所有写操作脚本必须支持 |
| M2 | javap/javac 用 rwlib.config.find_*() | S2 | `if __name__ == '__main__':` 保护 |
| M3 | supplement 读用 load_supplement()/csv, 禁 line.split(',') | S3 | 模块文档字符串含 Usage |
| M4 | supplement 写用 save_supplement()/DictWriter, 禁 ','.join() | | |
| M5 | 其他 CSV 读用 csv_read()/DictReader+field_size_limit | | |
| M6 | 退出码: 成功 0, 失败 1 | | |
| M7 | 所有文件读写 utf-8 | | |

## 任务收尾更新清单 (每次任务必做)

1. **D1 口径实测同步**: javac_gate 错误数 / supplement / class-discoveries 实测 → CLAUDE.md + README.md (根, 四合一) + docs/STATUS.md 全库同步; supplement 变化后重跑 `python tools/utils/split_mappings.py`
2. **D2 会话记录**: 每个会话新开 sessions/PHASE-A-vNNN-*.md (四要素: 批次轨迹表/方法论沉淀/新工具清单/残余清单), 旧记录头加"已被 vNNN 超越"标注
3. **D3 映射库同步**: 根 README.md 映射章节三源行数表同步 + mappings/generated/ 新文件入分类索引
4. **D4 类名改动同步**: grep 全 docs/ 旧类名引用并同步
5. **D5 归档与索引**: 被取代文档/工具 git mv 入 _archive/; 根 README.md 索引补新删旧; docs/deobfuscation/PLAN.md 追加会话行; 新文档符合 ORGANIZATION.md 规范; PENDING.md 立即登记/裁决后移出
6. **D6 新工具登记**: 新脚本入 CLAUDE.md 结构树 (即 TOOLS-TREE.md) + mappings/generated/ 索引

## 修复脚本工作规范

> 管线链原则 (广播源优先/CSV 行号驱动/层级坍缩警惕/广播原子提交) + 四层指纹配对器 + 字段保序同步 + F1-F27 全部方法论:
> **[docs/deobfuscation/METHODOLOGY.md](docs/deobfuscation/METHODOLOGY.md)** (v19.108 管线链奠基, v19.115r/t, v19.132wz 战役补充)

## 目标

使 **RustedWarfare/** 以未混淆的源码状态运行

> 当前进度: 映射数据库 10,395 条, 工具链 85+ 脚本, 编译 41,402 → 0 (B1), 反向构建 0 错误 (B3), 运行验证通过 (B4), 行为一致性收敛完成 (B5)
> 下一步: GUI/回放深度验证 + 撞车剔除残余验证 (待确认)

## 常用命令

```bash
python tools/manager.py list|check|status   # 工具管理 (list: --all/--group <战役>/--phase; check: --all 全量)
python tools/gates/javac_gate.py            # 全量编译门禁 (输出 compile-errors.csv)
python tools/fixers/build_reverse_jar.py --apply   # B3 反向构建 (→ game-lib-reverse.jar)
python tools/core/cross_validate.py         # 字节码交叉验证
python tools/core/sig_renamer.py --dry-run  # 签名驱动方法重命名
python tools/utils/split_mappings.py        # 映射域拆分
python tools/utils/comment_audit.py         # 注释覆盖率审计
python -m rwlib.bytecode                    # 字节码分析 (CJK 安全)
```
