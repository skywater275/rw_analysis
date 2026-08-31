# GameEngine 主更新循环 — 逐行源码追踪
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)

> 文件: `GameEngine.java:1352-1448`

---

## 1. 完整更新序列 (每帧, ~16.67ms at 60fps)

```java
// GameEngine.a(float f2) [line 1352]
public void a(float f2) {
    // ── 调速 ──
    // 1359: 应用游戏速度倍率
    if (this.bt != 1.0f && !this.bX.B && !this.cb.i()) {
        f2 *= this.bt;  // bt = 速度 (1x/2x/4x/8x)
    }
    this.J = f2;  // 保存原始 dt
    
    // ── 步骤1: 网络更新 [1364] ──
    this.bX.c(f2);                      // NetEngine.c(f2)
    
    // ── 步骤2: 帧时间累加 [1365] ──
    this.by = (int)(this.by + f2 * 16.666666f);  // currentFrame
    
    // ── 步骤3: ★ 指令处理 [1366] ──
    this.cf.c();                        // CommandController.c()
    //  ← RWAgent 注入的 Command 在此执行!
    
    // ── 步骤4: 回放更新 [1367] ──
    this.cb.a(f2);                      // ReplayEngine.a(f2)
    
    // ── 步骤5: Tick计数 [1368] ──
    ++this.bx;                          // gameTick++
    
    // ── 步骤6: 帧前清除 [1369] ──
    com.corrodinggames.rts.game.n.g(f2);// PlayerState.g() — 重置脏标志
    
    // ── 步骤7: 地图迷雾 [1370-1371] ──
    if (this.bL != null) {
        this.bL.e(f2);                  // MapEngine.e(f2) — 迷雾更新检查
    }
    
    // ── 步骤8: ★ 游戏对象更新 [1376-1388] ──
    com.corrodinggames.rts.game.units.am.bF();  // 刷新全局对象列表
    for (每个 BaseGameObject):
        w2.a(f2);                       // UnitInstance.a(f2) — 单位更新
                                        // GameWorld.a(f2) — AI更新
    //  ← AI 逻辑在此执行 (Tier1/Tier2/Tier3)
    
    // ── 步骤9: 新对象更新 [1392-1399] ──
    for (新添加的对象):
        w2.a(f2);
    
    // ── 步骤10: ★ 空间索引重建 [1401] ──
    this.cc.a();                        // SpatialGrid.a() — 每帧重建
    
    // ── 步骤11: 弹丸更新 [1403] ──
    com.corrodinggames.rts.game.units.y.g(f2);
    
    // ── 步骤12: 自定义单位逻辑 [1404-1405] ──
    com.corrodinggames.rts.game.units.custom.j.s(f2);
    com.corrodinggames.rts.game.units.custom.j.a(f2, 0);
    
    // ── 步骤13: 死亡单位清理 [1406-1427] ──
    if (this.j >= 1000) {               // 每1000帧
        if (deadCount > 70) {
            for (dead 单位):
                if (死亡 > 30秒):
                    unit.a();            // 完全清理
        }
    }
    
    // ── 步骤14: ★ 收入/战败 [1429] ──
    com.corrodinggames.rts.game.n.f(f2);// PlayerState.f(f2)
    //  ← 收入发放 (每90帧), 战败检查 (每~10帧)
    
    // ── 步骤15-18: 特效/管理器 ──
    // 粒子特效, EffectManager, GroupController, MinimapHandler, PathEngine
}
```

## 2. 与 RWAgent 交互的时间线

```
帧 N 开始
├── bX.c(f2)           ← 网络同步
├── cf.c()             ← ★★★ 注入的Command在此执行!
├── cb.a(f2)           ← 回放记录
├── n.g(f2)            ← 帧状态清除
├── w.a(f2) [GameWorld] ← AI逻辑 (看到本帧的建造结果)
├── cc.a()             ← 空间索引重建
├── n.f(f2)            ← 收入/战败
└── 帧 N 结束
```

**关键**: `cf.c()` (指令执行) 在 `w.a(f2)` (AI逻辑) **之前**!
→ 同一帧注入的建造指令 → AI 可以立即看到新单位

## 3. 定时触发汇总

| 事件 | 频率 | 源码位置 |
|------|------|---------|
| 指令执行 | 每帧 | cf.c() [1366] |
| AI更新 | 每帧 (内部Tier分层) | w.a(f2) [1387] |
| 空间索引重建 | 每帧 | cc.a() [1401] |
| 迷雾更新 | 每260帧 | bL.e(f2) [1371] |
| 收入发放 | 每90帧 | n.f(f2) [1429] |
| 战败检查 | 每~10帧 | n.f(f2) [1429] |
| 死亡清理 | 每1000帧 | [1407] |
