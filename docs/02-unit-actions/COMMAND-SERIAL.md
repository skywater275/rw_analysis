# Command 二进制序列化 — 源码交叉验证
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 网络/回放传输的精确二进制格式, 15+字段序列化顺序, 协议版本兼容
>
> 文件: `Command.java:152-205`(序列化), `Command.java:207-312`(反序列化)

---

## 1. 序列化格式 (写入 `a(as)`)

```java
// Command.java:152-205
as2.e("c");                              // 块名 "c"
as2.c(this.playerRef.k);                 // [1B] 玩家槽位
as2.a(this.waypointAction != null);      // [1B] 有路径点?
if (this.waypointAction != null) {
    this.waypointAction.a(as2);          // [变长] ★ 路径点序列化
}
as2.a(this.e);                           // [1B] 标志e
as2.a(this.stopOrUndo);                  // [1B] 停止/撤销
as2.a(-1);                               // [4B] 占位符
as2.a(this.attackMode);                  // [变长] 攻击模式枚举
as2.a(this.z != null);                   // [1B] 有目标点?
if (this.z != null) {
    as2.a(this.z.a); as2.a(this.z.b);    // [4B+4B] 目标点
}
as2.a(this.o);                           // [1B] 标志o
as2.a(this.selectedUnits.size());        // [4B] ★ 选中单位数
for (y y2 : this.selectedUnits) {
    as2.a(y2.eh);                        // [8B×N] 单位实体ID
}
as2.a(this.preExecPlayerState != null);  // [1B] 有预执行状态?
if (this.preExecPlayerState != null) {
    as2.a(this.preExecPlayerState);      // [变长] 玩家状态
}
as2.a(this.l != null);                   // [1B] 有辅助点?
if (this.l != null) {
    as2.a(this.l.a); as2.a(this.l.b);    // [4B+4B] 辅助点
}
as2.a(this.m);                           // [变长] 目标单位
as2.c(this.specialAction.a());           // [变长] ★ ActionId 字符串
as2.a(this.f);                           // [1B] 标志f
as2.a(this.playerIndex);                 // [2B] ★ 玩家索引位图
as2.a(this.systemAction);                // [1B] 系统动作?
if (this.systemAction) {
    as2.c(0);                            // [1B]
    as2.a(this.changeStepRate);          // [4B] 变速
    as2.a(this.t);                       // [4B] 参数t
    as2.a(this.systemActionValue);       // [4B] 系统动作值
}
as2.a(this.w.size());                    // [4B] 额外数据
for (d d2 : this.w) {
    d2.a(as2);                           // [变长×N]
}
as2.a(this.h);                           // [1B] 标志h
as2.a("c");                              // 块尾 "c"
```

## 2. 反序列化格式 (读取 `a(k)`) — 协议版本演进

```java
// Command.java:207-312
k2.b("c");                               // 验证块名

// ★ 版本兼容检查点:
k2.b() >= 16  → preExecPlayerState
k2.b() >= 29  → l (辅助点) + m (目标单位)
k2.b() >= 33  → specialAction 改为字符串读取
k2.b() >= 37  → playerIndex (玩家位图)
k2.b() >= 52  → f (标志)
k2.b() >= 53  → systemAction 块
k2.b() >= 80  → h (标志)
```

## 3. waypointAction 的序列化

```java
// au.java (WeaponAction)
a(as2):
    as2.a(this.a);          // weaponType (av 枚举)
    as2.a(this.b);          // targetUnitType (as)
    as2.a(this.c);          // actionType (c)
    as2.a(this.d);          // ammoCount
    as2.a(this.e);          // damage/X坐标
    as2.a(this.f);          // range/Y坐标
    as2.a(this.h);          // targetUnit (am 实体ID)
    as2.a(this.i);          // formationManager
    as2.a(this.j);          // isActive
    as2.a(this.k);          // currentDamage
    as2.a(this.l);          // currentRange
    as2.a(this.m);          // isReady
    as2.a(this.n);          // hasFired
```

## 4. 7个协议版本演进

| 版本 | 新增字段 | RWAgent 需要? |
|------|---------|-------------|
| 16 | preExecPlayerState | 可选 |
| 29 | l (辅助点), m (目标单位) | 可选 |
| 33 | specialAction 字符串化 | ✅ 必须 |
| 37 | playerIndex (玩家位图) | ✅ 必须 |
| 52 | f (标志) | 可选 |
| 53 | systemAction 块 | ✅ 系统生成需要 |
| 80 | h (标志) | 可选 |

## 5. playerIndex 位图

```java
// 每位代表一个玩家槽位
// bit 0 = player 0, bit 1 = player 1, ...
// 例: 0b00000101 = player 0 和 player 2
```

## 6. 对 RWAgent 的影响

```java
// 最小必须序列化的字段:
cmd.playerRef       → 玩家引用
cmd.waypointAction  → 路径点 (MOVE/BUILD/ATTACK)
cmd.playerIndex     → 玩家位图 (protocol ≥37)
cmd.specialAction   → ActionId (protocol ≥33)
cmd.selectedUnits   → 单位列表
```
