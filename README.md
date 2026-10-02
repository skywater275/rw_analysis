# Rusted Warfare 逆向工程

> 把 **Rusted Warfare**（Java 游戏）从混淆字节码还原为**可编译、可运行的源码工程**。
> 口径与数字：本文与 [docs/STATUS.md](docs/STATUS.md) **同源**；STATUS 是唯一口径来源。

## 一、现状（2026-10-02 实测）

| 指标 | 值 |
|---|---|
| 交付物 `build/game-lib-reverse.jar` | **2,651,989 B** · sha256 `83465acef0dc9dee…` |
| **替换率**（同名且字节不同） | **1,273 / 1,698 = 74.97%** |
| 可编译源码树 `03-deobfuscated/` | **1,577 文件 / 71 包**，编译门禁 **0 错误** |
| 候选池 `build/reverse-classes/` | **1,281** 个类（**交付物唯一来源**） |
| 映射库 | `supplement.csv` **7,800** · `class-discoveries.csv` **1,202** |
| 验收 | **V0–V8 PASS 10 / FAIL 0 / BLOCKED 0**（含真窗口 + 回放） |

## 二、三层源码树

```
02-decompiled/     CFR 反编译（混淆名，1,698 文件）—— 交叉参考
02b-decompiled/    FernFlower 反编译（混淆名，1,698 文件）—— 结构地面真值
03-deobfuscated/   ★ 解混淆源码（1,577 文件 / 71 包）—— 编译与开发主线
```

## 三、快速开始

```bash
python tools/gates/javac_gate.py            # ① 编译门禁（必须 0 错误）
python tools/gates/replacement_verify.py    # ② 完整验收（V0–V8，含真窗口 + 回放）
python tools/analysis/measure_03_funnel.py  # ③ 漏斗达成率
python tools/rig/verify_rig.py              # ④ 测试台现场校验
python tools/utils/gen_tools_tree.py --apply # ⑤ 重生成工具索引
```

**出 jar（旁路，不动交付物）**：
```bash
$env:RW_REVERSE_OUT_JAR='build/_dev.jar（历史产物，已清理 ✓）'
python tools/fixers/build_reverse_jar.py --apply
python tools/fixers/build_conflict_pass.py --world all --include-skip --apply
```

## 四、工具链（★ 一个入口 ✓）

```bash
python tools/cli.py doctor                    # 自检（路径/注册表/池/交付物 ✓）
python tools/cli.py list                      # 全部工具（60 个 ✓ 按分类）
python tools/cli.py edit  replacement_verify  # ★ 交互式调参并运行
python tools/cli.py panel                     # ★ 生成 HTML 参数面板
```

★ **路径非硬编码**（`rwlib/paths.py` 单一来源 ✓）· **参数自动规格化**（从 argparse 导出 ✓）·
历史战役脚本（研究过程）仅存于内部工作副本，公开仓库只分发活工具链 ✓。


## 四、文档导航

| 文档 | 内容 |
|---|---|
| [docs/STATUS.md](docs/STATUS.md) | ★ **唯一口径**：全部实测数字 |
| [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) | 结构、构建管线、数据流 |
| [docs/VERIFICATION.md](docs/VERIFICATION.md) | 验收体系 V0–V8 判据分工 |
| [docs/FEASIBILITY.md](docs/FEASIBILITY.md) | 能否基于本工程做功能开发 |
| [docs/STRUCTURE.md](docs/STRUCTURE.md) | ★ **项目结构与清理规则**（权威） |
| [docs/INDEX.md](docs/INDEX.md) | 全部文档索引 |

## 五、目录结构

```
rw-reverse/
├── RustedWarfare/      干净游戏本体（不入 git）· game-lib.jar = 编译目标
├── 02-decompiled/      CFR 反编译
├── 02b-decompiled/     FernFlower 反编译（结构真值）
├── 03-deobfuscated/    ★ 解混淆源码（主线）
├── mappings/           supplement + class-discoveries + domains/（14 域）
├── tools/              60 个活跃工具 + rwlib 共享库（gates / rig / analysis / registry …）
├── docs/               文档（核心 7 篇 + 14 域）
└── build/              暂存区（**未受版本管理**）· 池 + 交付物在此
```
## 六、 作者留言
在这个项目中，我有使用ai，所以还是建议ai读项目，毕竟逆向的代码可读性都不太高，以及以现在的进度对游戏进行适当修改基本上没什么大问题，基于游戏进行相关的辅助功能的开发基本上也能做到。
