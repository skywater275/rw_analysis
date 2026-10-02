# LibRocket UI 桥接

> 版本: v19.133f99 | 日期: 2026-10-01 | 域: **12-platform**（平台层）
> 域边界与文档职责见 ORGANIZATION 规范 · 数字口径见 [STATUS.md](../STATUS.md)

## 一、域边界

`com/corrodinggames/librocket/*` —— **Rocket（LibRocket）HTML/CSS UI 引擎的 JNI 桥接**，含 `LibRocketContext` / `LibRocketBridge` / `DocumentWrapper` / `ElementWrapper` 与 `scripts/*`（游戏侧 RML 脚本控制器）。

## 二、实测规模（2026-10-01，`历史探针（已清理 ✓）`（历史探针，已清理 ✓） 可复现）

| 指标 | 值 |
|---|---|
| `03` 树命中类 | **36** |
| 其中路径在 `stock.jar` 中原样存在 | **21** |

包分布：

```
com/corrodinggames/librocket/（含 scripts 子包）      ×36
  其中在 stock 原样存在                                ×21
```

## 三、关键类与角色

- **`LibRocketContext`** —— 引擎上下文（RML/CSS 文档宿主），带多个匿名内部类。
- **`LibRocketBridge`** —— Java ↔ 原生（C++）双向桥。
- **`DocumentWrapper` / `ElementWrapper`** —— DOM 文档/元素句柄包装。
- **`scripts/*`（`Root` · `ScriptEngine` · `ScriptContext` · `MainUIController` 等）** —— RML 事件脚本控制器；**主菜单/设置界面**即由此驱动。

> ⚠️ **B4 运行时验证**对 `NATIVE_BIND_METHODS` 有**豁免**（原生绑定方法无 Java 实现体）；对本域做替换时必须保留该豁免，否则会误判「空方法体」为缺陷。

## 四、已知问题与待定

- **RS-7 桥接残余**：`librocket/scripts/Root` 一族（20 条悬空）已在第 14 轮修正为 「指向 `stock_path` 自身」；`java/Main` → `GameLauncher` 的桥接仍会产出幽灵类 （`java/GameLauncher`，见 PENDING **RS-16** 结论文）。
- 历史战役：**v19.133f14 ModsUI/filesystem 域清零**。

## 五、相关映射与战役

- 源码: `03-deobfuscated/com/corrodinggames/librocket/`
- 类映射: [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)
- 成员映射: [mappings/supplement.csv](../../mappings/supplement.csv)
- 待定登记: PENDING
