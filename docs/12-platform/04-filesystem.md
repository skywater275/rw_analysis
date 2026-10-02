# 文件系统与存储后端

> 版本: v19.133f99 | 日期: 2026-10-01 | 域: **12-platform**（平台层）
> 域边界与文档职责见 ORGANIZATION 规范 · 数字口径见 [STATUS.md](../STATUS.md)

## 一、域边界

`gameFramework/filesystem/*`（抽象存储层）+ `java/filesystem/*` · `java/platform/*`（平台实现）+ `FileSystem` / `Storage*` / `DualStorage` 一族，并与 `SteamRemoteStorage`（云存档）交界。

## 二、实测规模（2026-10-01，`历史探针（已清理 ✓）`（历史探针，已清理 ✓） 可复现）

| 指标 | 值 |
|---|---|
| `03` 树命中类 | **23** |
| 其中路径在 `stock.jar` 中原样存在 | **7** |

包分布：

```
com/corrodinggames/rts/gameFramework/filesystem       ×8
com/corrodinggames/rts/gameFramework/utility          ×3
com/corrodinggames/rts/java/filesystem                ×2
com/codedisaster/steamworks/SteamRemoteStorage*       ×7
其余（StorageBuilding / FileSystem / DualStorage 等）  ×3
```

## 三、关键类与角色

- **`StorageBackend`** —— 存储后端抽象（本地 / Steam 云）。
- **`FileSystem`** —— 文件系统门面。
- **`DualStorage`** —— 双写/双读存储（本地 + 云）。
- **`StorageFolderPicker` / `ReplayFolderManager` / `ReplayComparator`** —— 回放与存档目录管理（高价值已命名类，见 PENDING §6）。

> ⚠️ 本域 **仅 7/23 在 `stock` 中原样存在** ⇒ 大部分类走**改名路径**，替换时对「类映射是否齐备」敏感。

## 四、已知问题与待定

- PENDING §5-4「`NetEngine.t` 字段（UDP 广播端口）语义待验证」。
- 存档**双向兼容**（旧存档 ↔ 反向产物）未做端到端验证。

## 五、相关映射与战役

- 源码: `03-deobfuscated/com/corrodinggames/rts/gameFramework/filesystem/ · 03-deobfuscated/com/corrodinggames/rts/java/filesystem/`
- 类映射: [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)
- 成员映射: [mappings/supplement.csv](../../mappings/supplement.csv)
- 待定登记: PENDING
