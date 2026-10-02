# 09-ui-audio — 界面与音频

> **职责**：HUD、菜单、输入映射、音效与本地化

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **433** 条 |
| 涉及类 | **25** 个 |
| 有可读名 | **25** 个（100%）|
| ★ **已替换为我们的字节** | **20** 个（80%）|
| 主包 | `com.corrodinggames.rts.gameFramework.f` · `com.corrodinggames.rts.gameFramework.f.a` · `com.corrodinggames.rts.gameFramework.g` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **20** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 5 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 0 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.gameFramework.f` | 415 |
| `com.corrodinggames.rts.gameFramework.f.a` | 12 |
| `com.corrodinggames.rts.gameFramework.g` | 6 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/gameFramework` | 95 | 11769 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/gameFramework/filesystem` | 8 | 1525 |
| `com` | 6 | 1080 |
| `com/corrodinggames/rts` | 13 | 658 |

## 三、★ 全量类清单（25 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `g` | **InGameUI** | 102 | 82 | 原版字节 |
| `y` | **StatsPanel** | 20 | 8 | **我们的字节** ✓ |
| `am` | **SelectionGroup** | 18 | 8 | 原版字节 |
| `o` | **Minimap** | 20 | 6 | 原版字节 |
| `a` | **ActionPanel** | 11 | 13 | 原版字节 |
| `f` | **RateGameDialog** | 16 | 8 | **我们的字节** ✓ |
| `ae` | **ThemeColors** | 9 | 4 | 原版字节 |
| `h` | **ChatPanel** | 11 | 1 | **我们的字节** ✓ |
| `w` | **MenuItemImpl** | 2 | 8 | **我们的字节** ✓ |
| `v` | **GameMenu** | 1 | 8 | **我们的字节** ✓ |
| `ab` | **StatsGraph** | 5 | 3 | **我们的字节** ✓ |
| `ap` | **WaypointManager** | 0 | 8 | **我们的字节** ✓ |
| `m` | **MessagePanel** | 4 | 4 | **我们的字节** ✓ |
| `x` | **BridgeUnit** | 1 | 6 | **我们的字节** ✓ |
| `e` | **GameResult** | 4 | 2 | **我们的字节** ✓ |
| `aa` | **LineGraphStyle** | 5 | 0 | **我们的字节** ✓ |
| `p` | **MinimapConfig** | 5 | 0 | **我们的字节** ✓ |
| `q` | **MinimapMarker** | 5 | 0 | **我们的字节** ✓ |
| `t` | **MinimapTile** | 5 | 0 | **我们的字节** ✓ |
| `ad` | **IntArray** | 3 | 1 | **我们的字节** ✓ |
| `r` | **MinimapMode** | 4 | 0 | **我们的字节** ✓ |
| `al` | **UnitRenderer** | 1 | 2 | **我们的字节** ✓ |
| `i` | **CameraMode** | 3 | 0 | **我们的字节** ✓ |
| `a` | **DataFieldCollector** | 3 | 0 | **我们的字节** ✓ |
| `b` | **DataField** | 3 | 0 | **我们的字节** ✓ |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `g`（InGameUI） | 184 | `PointF(m)` · `Rect(m)` · `actionPanelHeight(f)` · `actionPanelWidth(f)` · `actionPanelX(f)` · `actionPanelY(f)` · `activePlayerIndex(f)` · `addActionTarget(m)` · `addToSelection(m)` … |
| `y`（StatsPanel） | 28 | `bj(m)` · `boundsRect(f)` · `clipRect(f)` · `createPanel(m)` · `dataSamples(f)` · `displayMode(f)` · `drawGraph(m)` · `getValue(m)` · `handle(m)` … |
| `am`（SelectionGroup） | 26 | `captureSelection(m)` · `clearSelection(m)` · `field_bD(f)` · `field_bE(f)` · `field_bL(f)` · `field_bM(f)` · `field_bN(f)` · `field_bO(f)` · `field_bP(f)` … |
| `o`（Minimap） | 26 | `boundsRect(f)` · `count30(f)` · `draw(m)` · `drawMarkers(m)` · `float1(f)` · `float2(f)` · `getCount(m)` · `getValue(m)` · `isFlag30(f)` … |
| `a`（ActionPanel） | 24 | `count93(f)` · `createActionList(m)` · `draw(m)` · `drawActionBar(m)` · `drawControlGroups(m)` · `getValue(m)` · `handle(m)` · `isChatAction(m)` · `isFlag93(f)` … |
| `f`（RateGameDialog） | 24 | `buildButtons(m)` · `buttonHeightPx(f)` · `buttonSpacingPx(f)` · `buttonWidthPx(f)` · `buttons(f)` · `confettiTimer(f)` · `dialogBoxRect(f)` · `drawDialog(m)` · `drawRateNowPopup(m)` … |
| `ae`（ThemeColors） | 13 | `accentColor(f)` · `getCount(m)` · `handle(m)` · `obj8(f)` · `paint1(f)` · `paint2(f)` · `paint3(f)` · `paint4(f)` · `paint5(f)` … |
| `h`（ChatPanel） | 12 | `backgroundColor(f)` · `fieldTypeA(f)` · `fieldTypeB(f)` · `fieldTypeC(f)` · `fieldTypeD(f)` · `fieldTypeE(f)` · `inputField(f)` · `maxChars(f)` · `reset(m)` … |

## 五、映射备注（项目分析结论 ✓）

- **`a`**（ActionPanel）："(ActionPanel) 操作面板/单位命令 UI: 选中单位指令按钮"
- **`a`**（ActionPanel）：小队控制组绘制。T0: a/a.java:2311 10组方框
- **`a`**（ActionPanel）：操作面板更新2。T0: a/a.java:403
- **`a`**（ActionPanel）：操作面板更新。T0: a/a.java:206 摄像头缩放按键/滚轮/手势
- **`h`**（ChatPanel）：Background color (int); 逆4-v19.133f98: 语义名在 03 存在 — 归属待迁 (relocate-candidate); 逆4d-v19.133f98: 保持-仅异域同名声明 (TMXMapLoader 
- **`h`**（ChatPanel）：Input field reference (h)
- **`h`**（ChatPanel）：Maximum character count (int)
- **`h`**（ChatPanel）：Static field type A (static final h)
- **`aa`**（LineGraphStyle）：LineGraphStyle: ARGB team color
- **`aa`**（LineGraphStyle）：LineGraphStyle: Paint[11] alpha-bucketed line paints
- **`aa`**（LineGraphStyle）：LineGraphStyle: Paint[11] fill paints (width 5)
- **`aa`**（LineGraphStyle）：LineGraphStyle: TeamHistory data source
- **`ab`**（StatsGraph）："(LeaderboardTab) 排行榜标签页: 玩家 bj + 条目列表"
- **`ab`**（StatsGraph）：Data series array (ArrayList)

## 核心概念

**界面与音频**：HUD、菜单、输入、声音。

| 组成 | 说明 |
|---|---|
| **HUD** | 资源条、小地图、建造菜单、选中信息 |
| **菜单/控件** | 按钮、滑条、列表、对话框 |
| **输入** | 键盘/鼠标/触屏映射（含按键绑定） |
| **音频** | 音效播放、音乐切换、音量 |
| **本地化** | 多语言文本（含 CJK ✓） |

## 关键机制

1. **UI 文档驱动**：RML 布局文件 + 脚本绑定
2. **输入抽象**：键位映射表 ⇒ 可自定义
3. **音频通道**：音效/音乐分流，音量独立
4. **文本本地化**：翻译表 + 运行时替换
5. **移动端适配**：触摸输入与 UI 缩放

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| UI 布局 | `assets/gui/*.rml` ✓ |
| 文案/翻译 | `assets/translations/` ✓ |
| 音效 | 音频类 + 资源 ✓ |


## 六、子文档

- [`AUDIO-HUD.md`](AUDIO-HUD.md)
- [`AUDIO-INPUT.md`](AUDIO-INPUT.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`AUDIO-HUD.md`](AUDIO-HUD.md)
- [`AUDIO-INPUT.md`](AUDIO-INPUT.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
