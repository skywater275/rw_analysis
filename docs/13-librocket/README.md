# 13-librocket — LibRocket 桥

> **职责**：RML 界面系统与引擎 API 的桥接层

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **75** 条 |
| 涉及类 | **9** 个 |
| 有可读名 | **8** 个（89%）|
| ★ **已替换为我们的字节** | **8** 个（89%）|
| 主包 | `com.corrodinggames.librocket.scripts` · `com.corrodinggames.librocket` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **8** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 1 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 0 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.librocket.scripts` | 57 |
| `com.corrodinggames.librocket` | 18 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/librocket/scripts` | 27 | 5307 |
| `com` | 6 | 1080 |
| `com/corrodinggames/librocket` | 9 | 1062 |

## 三、★ 全量类清单（9 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `ScriptEngine` | **ScriptEngine** | 13 | 21 | **我们的字节** ✓ |
| `Root` | **Root** | 2 | 17 | 原版字节 |
| `d` | **DocumentWrapper** | 6 | 0 | **我们的字节** ✓ |
| `a$2` | **LibRocketContext$2** | 4 | 1 | **我们的字节** ✓ |
| `ScriptContext` | **ScriptContext** | 4 | 0 | **我们的字节** ✓ |
| `a` | **LibRocketContext** | 0 | 2 | **我们的字节** ✓ |
| `b` | **LibRocketBridge** | 0 | 2 | **我们的字节** ✓ |
| `e` | — | 2 | 0 | **我们的字节** ✓ |
| `c` | **ElementWrapper** | 0 | 1 | **我们的字节** ✓ |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `ScriptEngine`（ScriptEngine） | 34 | `addRunnableToQueue(m)` · `addScriptToQueue(m)` · `addScriptToQueueIfNotAlreadyQueued(m)` · `caughtCrash(f)` · `checkForErrors(m)` · `checkThreadAccess(m)` · `completed(f)` · `createScriptEngine(m)` · `framesDelay(f)` … |
| `Root`（Root） | 19 | `alert(m)` · `back(m)` · `closeAlertOnly(m)` · `closePopup(m)` · `exit(m)` · `getPopupText(m)` · `hostStart(m)` · `loadConfigAndStartNew(m)` · `loadConfigAndStartNewSandbox(m)` … |
| `d`（DocumentWrapper） | 6 | `elementDocument(f)` · `object1(f)` · `object2(f)` · `string1(f)` · `string2(f)` · `string3(f)` |
| `a$2`（LibRocketContext$2） | 5 | `joinQuestion(f)` · `outerContext(f)` · `run(m)` · `scriptEngine(f)` · `syncLock(f)` |
| `ScriptContext`（ScriptContext） | 4 | `guiEngine(f)` · `libRocket(f)` · `methods(f)` · `scriptEngine(f)` |
| `a`（LibRocketContext） | 2 | `endGame(m)` · `showMainMenu(m)` |
| `b`（LibRocketBridge） | 2 | `loadCharsetIfNeededOnChildren(m)` · `parseText(m)` |
| `e` | 2 | `runnable(f)` · `string(f)` |

## 五、映射备注（项目分析结论 ✓）

- **`a`**（LibRocketContext）：证据串自报名(ldc 绑定, A 级)；描述符 ()V
- **`a`**（LibRocketContext）：证据串自报名(ldc 绑定, A 级)；描述符 (Z)V
- **`a$2`**（LibRocketContext$2）：Phase3-inferred: captured ScriptEngine (librocket.scripts)
- **`a$2`**（LibRocketContext$2）：Phase3-inferred: captured j.ae data (b=question msg,  e=title
- **`a$2`**（LibRocketContext$2）：Phase3-inferred: new k(false) synchronization object (gameFramework.utility.k)
- **`a$2`**（LibRocketContext$2）：Phase3-inferred: synthetic outer ref to a (LibRocketContext)
- **`b`**（LibRocketBridge）：证据串自报名(ldc 绑定, A 级)；描述符 (Lcom/Element;Z)V
- **`b`**（LibRocketBridge）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;)Ljava/lang/String;
- **`c`**（ElementWrapper）：real-class-inferred: com.corrodinggames.librocket.c
- **`d`**（DocumentWrapper）：real-class-inferred: com.corrodinggames.librocket.d
- **`e`**：real-class-inferred: com.corrodinggames.librocket.e
- **`Root`**（Root）：v19.105: Mods 上下文引用
- **`Root`**（Root）：v19.105: Multiplayer 上下文引用
- **`Root`**（Root）：v19.105: l2.ca.b(string,false) 保存存档 (名称 . / 替换为 _)

## 核心概念

**LibRocket UI 桥**：把游戏状态接到 RML/RCSS 界面系统。

| 组成 | 说明 |
|---|---|
| **脚本桥** | `Root` 等类把引擎 API 暴露给 UI 脚本 |
| **文档加载** | 加载 `.rml`，绑定事件与数据 |
| **数据表刷新** | 把列表/表格数据推到 UI |

## 关键机制

1. **UI 与逻辑分离**：界面写在 RML，数据由桥类提供
2. **事件回调**：按钮点击 → 桥方法 → 引擎动作
3. **表格/列表**：`TableData` 之类的数据容器 ⇒ 刷新到 UI
4. ★ **本项目已知缺陷族**：桥类做成员改名时**不校验宿主** ⇒ 跨类误改
   （见 `docs/PENDING.md` 与归档的 P1 缺陷族文档 ✓）

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| UI 行为 | `assets/gui/*.rml` + 桥类 ✓ |
| 桥接新 API | `librocket/scripts/` 加方法 ✓ |
| 表格数据 | 数据容器 + 刷新方法 ✓ |


## 六、子文档

（本域暂无可拆分子文档 ✓ 本文即完整说明 ✓）

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

（本域暂无可拆分子文档 ✓ 本文即完整说明 ✓）

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
