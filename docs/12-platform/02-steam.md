# Steam 集成

> 版本: v19.133f99 | 日期: 2026-10-01 | 域: **12-platform**（平台层）
> 域边界与文档职责见 ORGANIZATION 规范 · 数字口径见 [STATUS.md](../STATUS.md)

## 一、域边界

`com/codedisaster/steamworks/*`（**vendored 的 JavaSteam 原生绑定**）+ 游戏侧封装（`SteamManager` / `SteamWorkshop` 一族）。

## 二、实测规模（2026-10-01，`历史探针（已清理 ✓）`（历史探针，已清理 ✓） 可复现）

| 指标 | 值 |
|---|---|
| `03` 树命中类 | **128** |
| 其中路径在 `stock.jar` 中原样存在 | **121** |

包分布：

```
com/codedisaster/steamworks/Steam*                     ×121  ← 原版原样存在
com/corrodinggames/rts/gameFramework/network           ×3
com/corrodinggames/rts/java/input                      ×3
```

## 三、关键类与角色

- **`com/codedisaster/steamworks/*`（121 类）** —— 第三方 `JavaSteam` 绑定，**在原版 jar 中就是这些名字**（未被 ProGuard 混淆）⇒ 与游戏代码同处一个 jar。
  代表性类：`SteamAPI` · `SteamApps` · `SteamAuth` · `SteamRemoteStorage` · `SteamWorkshop` · `SteamCallbackAdapter` 及各 `$内部类`。
- **游戏侧封装** —— `SteamManager` / `SteamWorkshop`（`gameFramework` 侧），把绑定包成游戏内 API（创意工坊上传/订阅、云存档走 `SteamRemoteStorage`）。

> ✅ **121/128 在 `stock.jar` 中原样存在** ⇒ 本域**大部分是 A 类同名替换候选**，是「替换率」的天然贡献者；但也意味着**改错会直接影响原生绑定调用**。

## 四、已知问题与待定

- 历史战役：**v19.133f15 测试族 + SteamWorkshop 清零**。
- 与 [04-filesystem](04-filesystem.md) 交界：云存档 = `SteamRemoteStorage` + `StorageBackend`。

## 五、相关映射与战役

- 源码: `03-deobfuscated/com/codedisaster/steamworks/ · 03-deobfuscated/com/corrodinggames/rts/gameFramework/（SteamManager / SteamWorkshop）`
- 类映射: [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)
- 成员映射: [mappings/supplement.csv](../../mappings/supplement.csv)
- 待定登记: PENDING
