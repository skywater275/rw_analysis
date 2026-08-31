# 05-gamelib 资源盘点与语义名审计

> v19.133f98 | 2026-08-31 整理 | 审计脚本: tools/utils/gamelib_audit.py
> 持久知识版 (行动项过程记录已清理, 未解析清单以 unresolved.txt 重生成值为准)

## 1. 定位

**05-gamelib = RustedWarfare/game-lib.jar 的直接解包** — 完整未动的游戏引擎字节码,是比 01-classes(388 个抽样)更全面的字节码真源。

| 目录 | 内容 | 与 05-gamelib 关系 |
|------|------|------|
| 01-classes/ | 388 个原始 .class 抽样 | 子集 |
| 02-decompiled/ | 1,698 个 CFR 反编译 .java (混淆名) | **FQN 集合完全一致 (审计验证)** |
| 03-deobfuscated/ | 1,672 个可读名 .java | 解混淆输出 |
| 04-javas/ | 游戏代码解包 | — |
| 05-gamelib/ | game-lib.jar 解包 (1,698 class + MANIFEST.MF,无资源文件) | 本盘点的对象 |
| 06-lib/ | 20 个第三方 jar 解包 (编译环境) | 外部依赖 |

- `Main-Class: com.corrodinggames.rts.java.Main` (桌面版入口)
- 字节码 major 52 = **Java 8**

## 2. 包结构 (1,698 类)

```
05-gamelib/                    1698
├── a/                           30   # 混淆名网络层 (a.a 29)
├── android/                     68   # 内嵌 Android API 兼容层 (app/content/graphics/net/os/support/util/view)
├── com/                       1581
│   ├── com/                      6   # LibRocket 绑定: Element/ElementDocument/ElementList/LibRocket
│   ├── com/a/a/a/                1
│   ├── com/codedisaster/steamworks/  121   # Steam 绑定 (完整官方名)
│   └── com/corrodinggames/    1453
│       ├── librocket/           36   # scripts 27 (Debug/Mods/Multiplayer/Root/ScriptEngine)
│       └── rts/               1417
│           ├── R*               13   # 资源 ID 类 (R$drawable/R$id/R$layout...)
│           ├── a/               18
│           ├── appFramework/    47
│           ├── game/           ~712  # 含 units 642 (最大域)
│           ├── gameFramework/  ~511  # 含 utility 59; 混淆名 a~z + aa~bh (ProGuard 溢出命名)
│           └── java/           ~92   # 桌面入口 Main + audio 43 (lwjgl 17)
└── org/a/                       19   # 待识别第三方库 (a-e 五子包)
```

## 3. 语义名资源 — 482 个官方名 (28.4%)

分类规则: 基名(去 `$N`)全小写字母 → 混淆名(1,216);其余 → 官方语义名(482)。
ProGuard 混淆器不混淆包名,只混淆类名/成员名 — 因此官方包结构完整保留。

### 3.1 IDENTITY 449 个 (03 树直接使用官方名)

| 包 | 类数 | 代表类 |
|------|------|------|
| com.corrodinggames.rts.game.units.custom.logicBooleans | **215** | 自定义单位布尔逻辑系统 (全部官方名) |
| com.codedisaster.steamworks | **121** | SteamAPI/SteamApps/SteamAuth/SteamFriends |
| android.graphics | 31 | Bitmap/Canvas/Paint/Matrix (内嵌 Android 图形层) |
| com.corrodinggames.rts.java.audio.lwjgl | 17 | LWJGL 音频实现 |
| com.corrodinggames.rts | 13 | R + R$drawable/R$id/R$layout 资源 ID |
| android.os | 10 | Handler/Looper/Message |
| com / librocket | 6+6 | Element/LibRocket 绑定 + gameFramework.utility 6 |
| com.corrodinggames.rts.java.audio | 6 | Audio/AudioDevice/AudioRecorder |
| android.net.http | 5 | AndroidHttpClient/Headers |
| 其余 android.* | 13 | Activity/Context/AssetManager/Log/SparseArray/KeyEvent |

### 3.2 RENAMED 7 个 (项目给官方名改了名)

| 官方名 (jar) | 03 名 |
|------|------|
| librocket.scripts.Debug | DebugUI |
| librocket.scripts.Root | MainUIController |
| librocket.scripts.Mods | ModsUI |
| librocket.scripts.Multiplayer | MultiplayerUI |
| librocket.scripts.ScriptEngine | UIScriptEngine |
| gameFramework.SettingsEngine | SettingsEngine (恒等) |
| java.Main | GameLauncher |

### 3.3 改名级联 25 个 + 内联 1 个

- 改名级联: Root$1~$11、$TableCell/$TableData/$TableRow、Main$1~$4、Multiplayer$1/$DropdownOption、Debug$1、Mods$1、ScriptEngine$1/$Action/$RunnableAction — 03 中已随外类改名 (如 `Root$1` → `MainUIController$1`),非缺口
- 内联: `android.view.KeyEvent$Callback` — 03 合并进 KeyEvent.java (第 21 行 `public interface Callback`),非缺口

## 4. 字节码元数据保留现状

| 属性 | 保留 | 结论 |
|------|------|------|
| LineNumberTable | 1,622/1,698 (95.5%) | 行号表完整,反编译定位可用 |
| SourceFile | 1,694 — 但**全是伪值 `"SourceFile"`** | ProGuard `-renamesourcefileattribute` 行为,无命名价值 |
| LocalVariableTable | **0** | 参数名/局部变量名全部剥离 — 参数名只能靠签名/调用点推理 |
| InnerClasses | **0** | 内部类属性剥离 (与损伤家族"InnerClasses 剥离"一致) |
| Signature | 1 | 泛型签名几乎全剥 |
| Exceptions | 0 | throws 子句剥离 |

## 5. 字符串资源

- **启动参数表** (Main 常量池, 30+ 项): `-lang`、`-fullscreen`、`-nomods`、`-nomusic`、`-nologfile`、`-nopostprocessing`、`-outputunitimages`、`-debugscript`、`-connect_lobby`、`-disable_vbos`、`-force_vbos`、`-oldreplays`、`-extrasafemode` 等
- 常量池类引用集: `mappings/generated/class-refs.json` (1,698 类)

## 6. 交叉核对结果 (tools/utils/gamelib_audit.py)

### 6.1 02 完整性验证 ✅

05-gamelib 1,698 类 vs 02-decompiled 1,698 文件 — **FQN 集合完全一致**,02-decompiled 是 game-lib.jar 的完整 CFR 输出。

### 6.2 语义名 × identity-index 核对 (482 个)

| 状态 | 数量 | 含义 |
|------|------|------|
| RENAMED | 7 | fwd 表已记录 (官方名 → 03 名) |
| IDENTITY | 449 | 03 直接使用官方名,索引不记录恒等映射 |
| RENAMED-CASCADE | 25 | 内部类随外类改名,03 已有 |
| INLINED | 1 | 03 合并进外类文件 |
| MISSING | **0** | **官方名无真实缺口** |

### 6.3 混淆名 × fwd 核对 (1,216 个)

- 已映射: 654 (fwd 表)
- 未映射: **162** (真实待解析, v19.133f98)

### 6.4 unresolved.txt 修正 (1,037 → 562)

旧版 unresolved.txt 把官方语义名也计为未解析 — 审计脚本重生成后只含真混淆类 (当前 162)。

### 6.5 supplement 覆盖

482 个官方语义类中仅 SettingsEngine 有成员映射 (139 条) — 官方名类的成员本就未混淆,无需映射,符合预期。

## 7. org/a 库已识别: joda-primitives ✅

**org/a 19 类 = joda-primitives 库** (org.joda.primitives, ProGuard 混淆, 保留 Float/Int/Short 3 种原始类型)。字节码接口层级+方法签名与官方 API 完全吻合 (v19.86 验证):

| 混淆 | joda 官方名 | 证据 |
|------|------|------|
| org.a.a.a.c | PrimitiveCollection | `extends java.util.Collection` (空接口) |
| org.a.a.a.a/b/d | Float/Int/ShortCollection | extends a.c + b.* |
| org.a.a.b.a/b/c | Float/Int/ShortIterable | `extends java.lang.Iterable` + `default iterator()` |
| org.a.a.c.c | PrimitiveIterator | `extends java.util.Iterator` |
| org.a.a.c.a/b/d | Float/Int/ShortIterator | extends c.c |
| org.a.a.d.c | PrimitiveList | `extends java.util.List + a.c` |
| org.a.a.d.a/b/d | Float/Int/ShortList | subList/listIterator/get 返回对应原始类型 |
| org.a.a.e.c | PrimitiveListIterator | `extends java.util.ListIterator + c.c` |
| org.a.a.e.a/b/d | Float/Int/ShortListIterator | extends c.* + e.c |

- 19 条映射已写入 class-discoveries.csv (real_pkg: org.joda.primitives.*),fwd 表已生成
- **修正 3 条旧误标**: org.a.a.c.a/b/c 曾被 Phase 4.2 误标为 LibRocketElement/RocketDocument/RocketElement — 字节码显示它们是空接口且 extends java.util.Iterator,与 UI 元素无关(真正的 libRocket 绑定在 com/ 根包: Element/ElementDocument/ElementList/LibRocket)
- **03 子树修复**: org/a/a/* 曾遭错误改名(包 org.network.reliableudp.* + 类名 LibRocketElement 等,含包/文件路径错配),已从 02 原稿整体恢复 19 文件
- 参考: [joda-primitives 官方 API](https://www.joda.org/joda-primitives/apidocs/overview-tree.html)
