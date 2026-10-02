# 11-utility — 工具库

> **职责**：容器、资源加载、字符串/路径/数学基础设施

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **82** 条 |
| 涉及类 | **22** 个 |
| 有可读名 | **20** 个（91%）|
| ★ **已替换为我们的字节** | **13** 个（59%）|
| 主包 | `com.corrodinggames.rts.gameFramework.utility` · `com.corrodinggames.rts.gameFramework.utility.a` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **13** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 9 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 0 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.gameFramework.utility` | 73 |
| `com.corrodinggames.rts.gameFramework.utility.a` | 9 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/gameFramework` | 95 | 11769 |
| `com/corrodinggames/rts/gameFramework/utility` | 64 | 7954 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com` | 6 | 1080 |
| `com/corrodinggames/rts/gameFramework/utility/filesystem` | 3 | 746 |
| `com/corrodinggames/rts` | 13 | 658 |

## 三、★ 全量类清单（22 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `ab` | **IniFile** | 11 | 0 | 原版字节 |
| `d` | **ANRWatchdog** | 10 | 0 | 原版字节 |
| `x` | **EmptyArrays** | 10 | 0 | **我们的字节** ✓ |
| `l` | **BufferedLineReader** | 2 | 4 | 原版字节 |
| `b` | **b** | 0 | 5 | 原版字节 |
| `a` | **SAFFileManager** | 0 | 4 | 原版字节 |
| `aa` | **TextDrawEntry** | 3 | 0 | **我们的字节** ✓ |
| `ad` | **ObjectPool** | 2 | 1 | **我们的字节** ✓ |
| `h` | **RingBufferIterator** | 3 | 0 | 原版字节 |
| `o` | **DequeList** | 3 | 0 | 原版字节 |
| `p` | **DequeListIterator** | 3 | 0 | 原版字节 |
| `t` | **TypedObjectListIterator** | 3 | 0 | **我们的字节** ✓ |
| `z` | **PaintStyleEntry** | 3 | 0 | **我们的字节** ✓ |
| `ag` | **NetworkException** | 0 | 2 | **我们的字节** ✓ |
| `ah` | **ZipDecoder** | 0 | 2 | 原版字节 |
| `ai` | — | 2 | 0 | **我们的字节** ✓ |
| `n` | **CustomArrayListIterator** | 2 | 0 | **我们的字节** ✓ |
| `q` | **ResultState** | 2 | 0 | **我们的字节** ✓ |
| `s` | **TypedObjectList** | 0 | 2 | **我们的字节** ✓ |
| `al` | — | 0 | 1 | **我们的字节** ✓ |
| `i` | **AssetIndex** | 0 | 1 | **我们的字节** ✓ |
| `y` | **PathfindingUtils** | 0 | 1 | **我们的字节** ✓ |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `ab`（IniFile） | 11 | `boolean1(f)` · `controlCharPattern(f)` · `currentSectionName(f)` · `isParsed(f)` · `keyList(f)` · `keyValuePattern(f)` · `linkedHashSet(f)` · `sectionMap(f)` · `sectionPattern(f)` … |
| `d`（ANRWatchdog） | 10 | `defaultTimeoutHandler(f)` · `intervalChecker(f)` · `isStarted(f)` · `isStopped(f)` · `mainHandler(f)` · `tickCounter(f)` · `timeoutHandler(f)` · `timeoutMilliseconds(f)` · `watchdogName(f)` … |
| `x`（EmptyArrays） | 10 | `emptyBooleanArray(f)` · `emptyByteArray(f)` · `emptyCharArray(f)` · `emptyClassArray(f)` · `emptyDoubleArray(f)` · `emptyIntArray(f)` · `emptyObjectArray(f)` · `emptyStackTraceArray(f)` · `emptyStringArray(f)` … |
| `l`（BufferedLineReader） | 6 | `getString(m)` · `getint(m)` · `isEnabled(m)` · `lastCharValue(f)` · `lineNumber(f)` · `reset(m)` |
| `b`（b） | 5 | `createDirectory(m)` · `deleteFile(m)` · `openAssetSteam(m)` · `renameFile(m)` · `writableOutputSteam(m)` |
| `a`（SAFFileManager） | 4 | `createSAFLink(m)` · `deleteFile(m)` · `getPathInZip(m)` · `openAssetSteam(m)` |
| `aa`（TextDrawEntry） | 3 | `positionX(f)` · `positionY(f)` · `textString(f)` |
| `ad`（ObjectPool） | 3 | `isEnabled(m)` · `isFixedSize(f)` · `poolIndex(f)` |

## 五、映射备注（项目分析结论 ✓）

- **`a`**（SAFFileManager）：证据串自报名(ldc 绑定, A 级)；描述符 (Landroid/net/Uri;ZLjava/lang/String;)Lcom/corrodinggames/rts/gameFramework/utility/a/b;
- **`a`**（SAFFileManager）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;)Ljava/lang/String;
- **`a`**（SAFFileManager）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;)Z
- **`a`**（SAFFileManager）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;Z)Lcom/corrodinggames/rts/gameFramework/utility/j;
- **`b`**（b）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;)Landroid/net/Uri;
- **`b`**（b）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;)Lcom/corrodinggames/rts/gameFramework/utility/j;
- **`b`**（b）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;)Z
- **`b`**（b）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;Ljava/lang/String;)Z
- **`aa`**（TextDrawEntry）：Text string (String)
- **`aa`**（TextDrawEntry）：X position (float)
- **`aa`**（TextDrawEntry）：Y position (float)
- **`ab`**（IniFile）：Control character regex (static Pattern)
- **`ab`**（IniFile）：Current section name (String)
- **`ab`**（IniFile）：Key string list (ArrayList)

## 核心概念

**工具库**：容器、资源加载、字符串、路径、数学等基础设施。

| 组成 | 说明 |
|---|---|
| **容器/列表** | 自定义列表（避免装箱开销 ✓） |
| **资源访问** | 从 jar/磁盘/资产读取文件（含压缩包内路径 ✓） |
| **字符串/解析** | 文本处理、路径处理 |
| **数学** | 向量/矩阵/插值 |

## 关键机制

1. **零分配容器**：热路径避免 GC ⇒ 移动端可用 ✓
2. **资产路径抽象**：统一 jar 内/磁盘/模组目录三种来源
3. **压缩包访问**：可直接读 zip 内文件
4. **工具与游戏解耦**：可被 UI/渲染/网络复用

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 容器性能 | 容器类 ✓ |
| 资源加载规则 | 资源访问类 ✓ |


## 六、子文档

- [`DEVELOPER-COMMENTS.md`](DEVELOPER-COMMENTS.md)
- [`EXTERNAL-REFERENCES.md`](EXTERNAL-REFERENCES.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`DEVELOPER-COMMENTS.md`](DEVELOPER-COMMENTS.md)
- [`EXTERNAL-REFERENCES.md`](EXTERNAL-REFERENCES.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
