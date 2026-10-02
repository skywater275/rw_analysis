# 输入系统（按键 / 触摸 / 鼠标）

> 版本: v19.133f99 | 日期: 2026-10-01 | 域: **12-platform**（平台层）
> 域边界与文档职责见 ORGANIZATION 规范 · 数字口径见 [STATUS.md](../STATUS.md)

## 一、域边界

`com/corrodinggames/rts/java/input/*`（平台输入后端）+ `gameFramework/InputProvider` · `KeyBinding` · `KeyBindings`（输入模型）。

## 二、实测规模（2026-10-01，`历史探针（已清理 ✓）`（历史探针，已清理 ✓） 可复现）

| 指标 | 值 |
|---|---|
| `03` 树命中类 | **18** |
| 其中路径在 `stock.jar` 中原样存在 | **0** |

包分布：

```
com/corrodinggames/rts/java/input                      ×15
com/corrodinggames/rts/gameFramework/InputProvider     ×1
com/corrodinggames/rts/gameFramework/KeyBinding        ×1
com/corrodinggames/rts/gameFramework/KeyBindings       ×1
```

## 三、关键类与角色

- **`InputProvider`**（`gameFramework`）—— 输入提供者抽象；平台后端实现之。
- **`KeyBinding` / `KeyBindings`** —— 单个按键绑定 与 绑定集合；对应游戏内「按键设置」界面（`Root` 的 `keyBindingPopup_*` 一族）。
- **`java/input/*`（15 类）** —— 平台输入后端与事件源。

> ⚠️ **`stock` 中命中 0** —— 这些是 `03` 树**改名后**的可读路径，原版对应类在 `stock.jar` 里仍是混淆名 ⇒ 本域**没有同名替换（A 类）**，相关类只能走「类映射 + 反向改名」路径进入交付物。

## 四、已知问题与待定

- PENDING §5「GUI/回放深度验证」：输入路径的**真机交互**未深测。
- 历史战役：**v19.133f36 KeyBinding 家族清零**（轨迹见内部 PLAN 会话行）。

## 五、相关映射与战役

- 源码: `03-deobfuscated/com/corrodinggames/rts/java/input/ · 03-deobfuscated/com/corrodinggames/rts/gameFramework/{InputProvider,KeyBinding,KeyBindings}.java`
- 类映射: [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)
- 成员映射: [mappings/supplement.csv](../../mappings/supplement.csv)
- 待定登记: PENDING
