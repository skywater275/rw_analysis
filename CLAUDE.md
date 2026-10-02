# CLAUDE.md — 项目操作规范

> 供 AI/开发者在本仓库工作时遵守。**数字口径一律以 [docs/STATUS.md](docs/STATUS.md) 为准**。

## 一、语言规范

- 对话 / 文档 / 代码注释 / 错误提示：**中文** ✓
- 代码标识符：**英文** ✓

## 二、目录约定

```
03-deobfuscated/   ★ 编译与开发主线（解混淆源码）
02-decompiled/     CFR 反编译（交叉参考）
02b-decompiled/    FernFlower 反编译（结构地面真值）
mappings/          supplement.csv（7,800）· class-discoveries.csv（1,202）· domains/
tools/             脚本（模块 docstring 必含 Usage）
build/             暂存区（未入 git）：池 reverse-classes/ + 交付物 game-lib-reverse.jar
docs/              文档
```

## 三、核心约束

1. **`RustedWarfare/game-lib.jar` 是编译目标**（24 个真实 jar 参与编译）
2. **`build/reverse-classes/` 是交付物唯一来源** —— **严禁删除** ✗
3. **`supplement.csv` 是唯一映射库** —— 读写走 `rwlib` 或 `csv` 模块，**禁止** `line.split(',')`
4. **脚本路径统一用 `rwlib.config` 常量**，禁止 CWD 相对路径
5. **验证必须用真实 GUI 窗口**（禁 `-nodisplay`），留窗口证据

## 四、脚本规范（MUST / SHOULD）

| # | 规则 | # | 规则 |
|---|---|---|---|
| M1 | 路径用 `rwlib.config` 常量 | S1 | 写操作脚本必须支持 `--dry-run` / `--apply` |
| M2 | javap/javac 用 `rwlib.config.find_*()` | S2 | `if __name__ == '__main__':` 保护 |
| M3 | supplement 读用 `load_supplement()` | S3 | 模块 docstring 含 Usage |
| M4 | supplement 写用 `save_supplement()` | | |
| M5 | 其他 CSV 用 `DictReader` + `field_size_limit` | | |
| M6 | 退出码：成功 0 / 失败 1 | | |
| M7 | 文件读写一律 utf-8 | | |

## 五、任务收尾清单（每次任务必做）

| # | 项 | 落点 |
|---|---|---|
| D1 | **口径同步** | `docs/STATUS.md`（唯一口径）+ 根 `README.md` |
| D2 | **会话记录** | 仅内部工作副本维护（不进公开仓库） |
| D3 | 映射库同步 | `mappings/generated/` 索引 |
| D4 | 类名改动同步 | 全 `docs/` grep 旧类名 |
| D5 | **归档与索引** | 被取代文档移出公开仓库（内部归档） |
| D6 | 新工具登记 | 落位 + docstring ⇒ `gen_tools_tree.py --apply` |

## 六、常用命令

```bash
python tools/gates/javac_gate.py                    # 编译门禁
python tools/gates/replacement_verify.py            # 完整验收 V0–V8
python tools/gates/jar_compare_gate.py              # 逐成员对照
python tools/fixers/build_reverse_jar.py --apply    # 反向构建
python tools/fixers/build_conflict_pass.py --world all --include-skip --apply
python tools/rig/verify_rig.py                      # 测试台 35 项校验
python tools/utils/gen_tools_tree.py --apply        # 重生成工具索引
python tools/analysis/check_doc_paths.py            # ★ 文档路径门禁（0 失效 ✓）
```

## 七、铁律（踩坑总结，违反必浪费时间）

1. **PowerShell 内联 `python -c "…"` 会被 `$`/引号/`<` 破坏** ✗ ⇒ **写脚本文件**或用 `edit` 工具
2. **不要清 `build/reverse-classes/`** ✗（交付物唯一来源）
3. **实验必须双度量**：可用类 **+** 编译错误行数 ✗ 单度量会误判
4. **落地纪律**：可用类 ≥ 基线 **且** 产出 ≥ 基线，否则**回退并记录负结果**
5. **改完必跑 V5（真窗口）+ V6（回放）** —— 结构判据挡不住行为致命
6. **读 `build_reverse_jar.py` 要连 `else` 一起读**（曾因读漏 `else` 误判死代码）
