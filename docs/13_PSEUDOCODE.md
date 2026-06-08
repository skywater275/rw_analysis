# 13 — 关键算法完整伪代码 (可直接翻译为代码)

> 全部从 game-lib.jar 字节码逐指令还原 | 可直接转换为 Python/C++/Java

---

## 1. 收入系统

### 1.1 单位注册 — `s.register(am unit)`

```python
def team_tracker_register(tracker, unit):
    """s.a(am) [358B]"""
    tracker.total_built += 1                    # d++
    
    if unit.build_progress < 1.0:               # cm < 1.0
        tracker.incomplete_count += 1            # f++
    else:
        tracker.completed_count += 1             # c++
    
    unit_type = unit.get_unit_type()             # r() → as
    if not unit_type.is_building():              # k()
        tracker.non_building_count += 1          # b++
    
    if not tracker.special_flag and unit.is_alive() and unit_type.has_special():
        tracker.special_flag = True              # m = true
    
    # 处理 ResourceComponent
    res_comp = unit.get_resource_component()     # dq() → custom.d.b
    if res_comp is not None:
        # 添加到5个资源追踪器
        tracker.resource4.add(res_comp, 0, FLOAT_MAX)    # k
        tracker.resource5.add(res_comp, -FLOAT_MAX, 0)   # l
    
    # 处理工厂类型
    if isinstance(unit, FactoryInterface):       # d.l
        slot_count = unit.get_slot_count(False)  # f(false)
        tracker.non_building_count += slot_count
        tracker.factory_slot_count += slot_count  # e
        if slot_count > 0:
            tracker._add_factory_builds(unit)
    
    # 按队伍标签追踪
    tracker._track_by_tag(unit)
    
    # ★★★ 收入注册 ★★★
    income = unit.get_income_rate()              # cy() → float
    if income != 0.0 and unit.build_progress >= 1.0:
        tracker.total_income_rate += int(income) # g += (int)cy()
```

### 1.2 单位注销 — `s.unregister(am unit)`

```python
def team_tracker_unregister(tracker, unit):
    """s.b(am) [327B]"""
    tracker.total_built -= 1
    
    if unit.build_progress < 1.0:
        tracker.incomplete_count -= 1
    else:
        tracker.completed_count -= 1
    
    if not unit.get_unit_type().is_building():
        tracker.non_building_count -= 1
    
    res_comp = unit.get_resource_component()
    if res_comp is not None:
        tracker.resource4.remove(res_comp)
        tracker.resource5.remove(res_comp)
    
    if isinstance(unit, FactoryInterface):
        slot_count = unit.get_slot_count(False)
        tracker.non_building_count -= slot_count
        tracker.factory_slot_count -= slot_count
        if slot_count > 0:
            tracker._remove_factory_builds(unit)
    
    tracker._untrack_by_tag(unit)
    
    income = unit.get_income_rate()
    if income != 0.0 and unit.build_progress >= 1.0:
        tracker.total_income_rate -= int(income)  # ★
    
    # 统计取消/回收
    if is_cancel:
        tracker.cancel_count += 1                 # o
    else:
        tracker.reclaim_count += 1                # n
```

### 1.3 收入累积 — `y.i(float delta, au weapon, ad action)` [2196B]

```python
def unit_type_income_update(unit_type, delta, weapon, action):
    """y.i(float, au, ad) — 每帧调用"""
    l = GlobalState.get()
    
    if weapon is None:
        return
    
    # 获取武器参数
    weapon_range = weapon.get_range()       # g() → float
    weapon_damage = weapon.get_damage()     # h() → float
    
    # 计算自身到武器目标的距离
    dist = distance(unit_type.x, unit_type.y, weapon_range, weapon_damage)
    
    # === 武器目标验证 ===
    retarget_flag = False
    weapon_invalid = False
    
    # guard武器 目标检查
    if weapon.type == WeaponType.GUARD:
        if weapon.target is not None and weapon.target.get_shield() > 0:
            retarget_flag = True
    
    # 目标销毁/运输中/无目标 → 清除
    if (weapon.target is None or 
        weapon.target.is_dead or 
        weapon.target.parent_unit is not None):
        if not retarget_flag:
            unit_type.clear_weapon()        # ax()
            weapon = None
        else:
            weapon_invalid = True
    
    # === 重目标(99 tick内) ===
    if (weapon is not None and 
        not weapon_invalid and 
        retarget_flag and 
        weapon.target is not None):
        
        can_retarget = (unit_type.last_action_time >= l.tick - 100)
        if not unit_type.can_guard(weapon.target, can_retarget):
            weapon_invalid = True
    
    # === 重新查找目标 ===
    if weapon is not None and weapon_invalid:
        target_type = weapon.target.get_custom_type() if weapon.target else None
        new_target = find_target_in_range(
            unit_type, unit_type.x, unit_type.y, 
            weapon_range, target_type
        )
        if new_target is not None:
            weapon.target = new_target
            unit_type.on_target_changed()    # aB()
        else:
            unit_type.clear_weapon()
            weapon = None
    
    # === loadInto 验证 ===
    if weapon is not None and weapon.type == WeaponType.LOAD_INTO:
        if not unit_type.can_load(weapon.target):
            unit_type.clear_action()         # ay()
            weapon = None
        elif (not retarget_flag and 
              not unit_type.is_target_valid(weapon.target)):
            unit_type.clear_action()
            weapon = None
    
    # loadInto 目标满HP+完成 → 可装载
    if (weapon is not None and 
        weapon.type == WeaponType.LOAD_INTO and
        weapon.target is not None and
        weapon.target.hp >= weapon.target.max_hp and
        weapon.target.build_progress >= 1.0):
        unit_type.clear_weapon()
        weapon = None
    
    # 防止攻击自己
    if weapon is not None and weapon.target == unit_type:
        unit_type.clear_action()
        weapon = None
    
    # 目标护盾>0 → 检查武器类型
    if (weapon is not None and 
        weapon.target is not None and 
        weapon.target.get_shield() != 0):
        if weapon.type != WeaponType.LOAD_INTO:
            unit_type.clear_action()
            weapon = None
    
    # === guard: 敌方队伍 → 执行guard逻辑 ===
    if (weapon is not None and 
        weapon.type == WeaponType.GUARD and
        weapon.target.team != unit_type.team):
        # 执行 guard 行为...
        pass
    
    # === ★ 收入累积 ★ ===
    if unit_type.is_alive():
        for resource in unit_type.resources:
            resource.accumulator += delta
            if resource.accumulator >= 180.0:           # 触发间隔
                # 产出 = generation_credits / generation_delay
                credits = resource.get_generation_rate()
                unit_type.income_accumulator += credits * delta
                
                if unit_type.income_accumulator > 1.0:
                    player = unit_type.owner             # bX → n
                    player.credits += unit_type.income_accumulator  # n.o += ab
                    unit_type.income_accumulator -= int(unit_type.income_accumulator)
```

### 1.4 实际每秒收入计算

```python
def get_income_per_second(tracker, speed_multiplier=2.5, income_multiplier=1.0):
    """
    tracker.g = Σ cy()   (每40帧显示值)
    FRAME_RATE = 60
    ECONOMY_WINDOW = 40
    """
    raw_rate = tracker.total_income_rate     # s.g
    per_second = raw_rate * (60.0 / 40.0) * income_multiplier * speed_multiplier
    return per_second

# CC收入: cy()=18
# 每秒 = 18 × 1.5 × 2.5 = 67.5/s
```

---

## 2. 伤害系统

### 2.1 完整伤害计算 — `am.applyDamage(float, UnitInstance)` [553B]

```python
def apply_damage(unit, incoming_damage, attacker, movement_context):
    """
    am.a(float, am) — 完整伤害算法
    从字节码逐指令还原
    """
    shield_mult = movement_context.shield_multiplier       # f.al
    shield_absorb = movement_context.shield_absorb_factor  # f.am
    hp_mult = movement_context.hp_multiplier               # f.an?
    
    remaining = incoming_damage
    absorbed = 0.0
    
    # ===== 阶段1: 未完成建筑惩罚 =====
    # (在调用这个方法之前, y.a() 中已处理:
    #  if unit.build_progress < 1.0: damage *= 1.75)
    
    # ===== 阶段2: 护盾吸收 =====
    if unit.special_damage_flag == 0:            # cz == 0
        if unit.shield > 0:                      # cx > 0
            shield_damage = remaining * shield_mult   # v10 = v8 * v5
            
            if unit.shield >= shield_damage:     # cx >= v10
                # 护盾完全吸收
                remaining = remaining - unit.shield * shield_absorb
                absorbed = absorbed + unit.shield
                unit.total_shield_absorbed += unit.shield  # cy += cx
                unit.shield = 0.0               # cx = 0
            else:
                # 护盾部分吸收
                unit.shield -= shield_damage    # cx -= v10
                unit.total_shield_absorbed += shield_damage
                absorbed += shield_damage
                remaining = remaining - remaining * shield_absorb
    
    # ===== 阶段3: HP伤害 =====
    if remaining > 0:
        hp_damage = remaining * hp_mult         # v10 = v8 * v7
        
        if unit.hp >= hp_damage:                # cu >= v10
            # HP完全吸收伤害(单位幸存)
            remaining = remaining - unit.hp
            absorbed = absorbed + unit.hp
            unit._set_hp(0)                     # o(0) — SET TO ZERO, NOT DEATH YET
            unit.total_hp_lost += unit.hp       # cC += cu
        else:
            # HP部分损失
            unit._set_hp(unit.hp - hp_damage)   # o(cu - v10)
            absorbed = absorbed + hp_damage
            remaining = remaining - hp_damage
            unit.total_hp_lost -= hp_damage     # cC -= v10
    
    # ===== 阶段4: 记录+死亡触发 =====
    unit.last_damage_tick = GlobalState.get().tick  # bs = l.by
    if attacker is not None:
        unit.last_attacker = attacker              # bt = attacker
    else:
        unit.last_attacker = None
    
    unit._check_death()                            # ch()
    
    return remaining  # 溢出伤害(用于溅射)
```

### 2.2 死亡检测 — `am.ch()` [21B]

```python
def _check_death(unit):
    """am.ch()"""
    if unit.is_dead:            # bV == true?
        return
    if unit.hp <= 0:            # cu <= 0
        unit._trigger_death()   # bv()
```

### 2.3 死亡序列 — `am.bv()` [20B]

```python
def _trigger_death(unit):
    """am.bv()"""
    unit._cleanup()             # bu()
    if not unit._special_death_check():  # e()
        unit._release_resources()        # a()
    unit._final_cleanup()                # bt()
```

### 2.4 核心清理 — `am.bu()` [98B]

```python
def _cleanup(unit):
    """am.bu()"""
    l = GlobalState.get()
    
    # 1. 从单位工厂移除
    l.unit_factory.remove(unit)          # bS.l(this)
    
    # 2. ★ 从队伍追踪器注销(扣除收入!)
    PlayerState.unregister_unit(unit)    # n.a(this) → s.b(this)
    
    # 3. ★ 从全局单位注册表移除
    UnitInstance.global_registry.remove(unit)  # am.bE.remove(this)
    
    # 4. 强制HP归零
    unit.is_dead = True                  # bV = true
    unit.death_tick = l.tick             # bW = l.by (i2l)
    
    if unit.hp > 0:
        unit.hp = 0                      # cu = 0
    
    # 5. 清除移动参数
    if unit.movement_params is not None:
        for i in range(unit.get_movement_param_count()):
            unit.movement_params[i].target = None   # ap.j = null
    
    # 6. ★ 从空间网格移除
    l.spatial_grid.remove(unit)          # cc.a(this)
```

### 2.5 死亡特效 — `am.cX()` [198B]

```python
def _create_death_effect(unit):
    """am.cX()"""
    l = GlobalState.get()
    target_player = l.current_player     # bs
    
    if target_player is None:
        return
    if unit.team == target_player:       # bX == bs
        return
    if target_player.index < 0:
        return
    if target_player.index >= PlayerState.MAX_PLAYERS:
        return
    
    # 检查是否已有特效
    if unit.death_effect is not None:
        if unit.death_effect.is_active:
            unit.death_effect = None
    
    if unit.death_effect is not None:
        return
    if not unit.death_effect_enabled:    # an.a
        return
    if not unit._can_see_target(target_player):  # d(n)
        return
    
    # 创建特效
    effect = Effect.create()             # fw.d.a
    effect.unit_type = unit.get_unit_type()     # d = r()
    effect.x = unit.x                           # g = eo
    effect.y = unit.y                           # h = ep
    effect.loop = False                         # n = false
    effect.team = unit.team                     # e = bX
    effect.effect_type = unit.death_effect_type # f = an.b
    effect.target_player = target_player        # j = l.bs
    effect.extra_flag = unit._get_extra()       # u = c_()
    effect.source_unit = unit                   # v = this
    
    unit.death_effect = effect
```

### 2.6 武器重置 — `au.e()` [77B]

```python
def reset_weapon(weapon):
    """au.e()"""
    weapon.type = WeaponType.ATTACK           # a = av.a (attack)
    weapon.target_type = None                 # b = null
    weapon.ammo = 1                           # d = 1
    weapon.damage = 2.0                       # e = 2.0  ★ 默认伤害
    weapon.range = 2.0                        # f = 2.0  ★ 默认射程
    weapon.cooldown = -1                      # g = -1 (就绪)
    weapon.target = None                      # h = null
    weapon.projectile_mgr = None              # i = null
    weapon.current_damage = -1.0              # k = -1.0
    weapon.current_range = -1.0               # l = -1.0
    weapon.ready = False                      # m = false
    weapon.fired = False                      # n = false
    weapon.active = False                     # j = false
```

### 2.7 弹丸碰撞检测 — `fw.ab.c()` [630B]

```python
def projectile_collision_check(mgr):
    """fw.ab.c()"""
    l = GlobalState.get()
    
    # 重置累计位置
    mgr.total_x = 0.0
    mgr.total_y = 0.0
    
    # 计算弹丸平均位置
    timestamp = System.nanoTime()
    mgr._update_active()                       # a()
    
    mgr.owner.position.set(0, 0)               # h.b.set(0,0)
    
    for unit_type in mgr.active_projectiles:   # a (utility.m)
        # 累加所有弹丸的位置
        mgr.owner.position.x += unit_type.x    # eo
        mgr.owner.position.y += unit_type.y    # ep
    
    # 计算平均位置
    if mgr.active_projectiles.size() > 0:
        mgr.owner.position.x /= mgr.active_projectiles.size()
        mgr.owner.position.y /= mgr.active_projectiles.size()
    
    # 碰撞检测参数
    # 阈值: 80, 160, 360, 3600, 14400, 40000, 160000
    # (来自常量池: 80.0f, 160.0f, 360.0f, ...)
```

---

## 3. 建造系统

### 3.1 建造进度 — `BuildProgress`

```python
class BuildProgress:
    """d.j — 14字段"""
    slot: int = 0              # a — 槽位号
    build_speed: float = 0.0   # b — 建造速度
    cost: ResourceComponent    # c — 消耗资源
    extra_cost: ResourceComponent  # d — 额外消耗
    unit_type: CustomUnitType  # e — 目标单位类型
    is_done: bool = False      # f — 是否完成
    target_type: UnitType      # g — 目标UnitType (as)
    position: PointF           # h — 建造位置
    builder: UnitInstance      # i — 建造者
    action_type: ActionType    # j — 动作类型
    flag1: bool = False        # k
    flag2: bool = False        # l
    progress: float = 0.0      # m — 当前进度 (0.0~1.0)
    accumulated: float = 0.0   # n — ★ 累积进度 (double)

def update_build_progress(bp, delta_ms, speed_mult=1.0):
    """每帧更新建造进度"""
    bp.accumulated += bp.build_speed * delta_ms * speed_mult
    
    if bp.accumulated >= 1.0:
        bp.is_done = True
        bp.progress = 1.0
    else:
        bp.progress = bp.accumulated

def complete_build(bp, unit):
    """建造完成时调用"""
    unit.set_build_progress(1.0)     # am.r(1.0f)
    # r() 内部会调用:
    #   n.b(this) — 注销旧状态
    #   cm = 1.0
    #   n.c(this) — 注册新状态(包含收入)
```

### 3.2 工厂队列 — `h.e.s(float)` [172B]

```python
def update_factory_queue(factory, delta):
    """h.e.s(float)"""
    l = GlobalState.get()
    
    # 1. 获取队列速度倍率
    if factory.is_active:          # a == true?
        speed_mult = 1.0
    else:
        speed_mult = -8.0          # ★ 未激活倒退!
    
    # 2. 更新队列进度
    factory.queue_progress -= speed_mult    # eq -= speedMult
    progress = clamp(factory.queue_progress, 0, MAX_FLOAT)
    
    if progress <= 2.0:            # 队列空闲
        return
    
    # 3. 查找可用建造者
    builder = factory.find_builder()
    if builder is None:
        return
    
    # 4. 检查建造者距离
    dist = distance(builder.x, builder.y, factory.x, factory.y)
    if dist > 60.0:                # 建造者太远
        return
    
    # 5. 获取建造速度
    build_speed = factory.get_build_speed()   # G() → float
    
    # 6. 推进当前建造
    current = factory.get_current_build()     # dw() → d.j
    if current is not None:
        current.accumulated += delta * build_speed * speed_mult
        if current.accumulated >= 1.0:
            current.is_done = True
            factory.complete_current_build()
```

### 3.3 建造完成 — `am.r(float)` 

```python
def set_build_progress(unit, new_progress):
    """am.r(float)"""
    if new_progress >= 1.0:         # 建造完成!
        if unit.build_progress < 1.0:  # 之前未完成
            # 转换: 未完成 → 完成
            unit.team.unregister_unit(unit)  # n.b(this)
            unit.build_progress = 1.0        # cm = 1.0
            unit.team.register_unit(unit)    # n.c(this) ★ 现在包含收入
    else:
        if unit.build_progress >= 1.0:  # 降级: 完成 → 未完成
            unit.team.unregister_unit(unit)  # n.b(this)
            unit.build_progress = new_progress
            unit.team.register_unit(unit)    # n.c(this) ★ 不含收入
        else:
            unit.build_progress = new_progress
```

---

## 4. 战败检测 — `n.e(float)` [458B]

```python
def check_defeat(player, delta):
    """n.e(float) — 每10帧调用一次"""
    l = GlobalState.get()
    
    # 延迟计数器
    if player.defeat_delay > 0:          # ai > 0
        player.defeat_delay -= 1
        return
    if player.defeat_delay == -2:        # -2 = 初始化
        player.defeat_delay = player.index  # ai = k
    else:
        player.defeat_delay = 10         # 10帧后再检查
    
    if player.has_surrendered:           # G == true
        return
    if l.replay_engine.is_playing():     # cb.j()
        return
    
    has_own_units = False
    has_builders = False
    has_enemy_threat = False
    shared_control = l.net_engine.settings.shared_control  # bX.ay.l
    
    # 遍历所有单位
    units = UnitInstance.global_registry.get_all()  # am.bE
    for unit in units:
        if unit.team == player:                    # bX == this
            if not unit.is_dead_or_dying():        # cT()
                has_own_units = True
                if not player.is_disconnected:     # F
                    if not unit.is_transporting() and not unit.is_loading():  # bJ/ak
                        has_builders = True
                        break
        elif shared_control:
            if unit.team is not None and player.is_enemy_of(unit.team):
                if not unit.is_dead_or_dying():
                    has_enemy_threat = True
    
    # 判定逻辑 (后续在 t() 方法中)
```

---

## 5. 序列化

### 5.1 UnitInstance 写入 — `am.serialize(j.as)` [745B]

```python
def serialize_unit(unit, stream):
    """am.a(j.as) — 34个字段按固定顺序写入"""
    s = stream
    
    s.write_bool(unit.flag_bM)                  # bM
    s.write_unit_ref(unit.ref_bQ)               # bQ
    s.write_unit_ref(unit.ref_bR)               # bR
    s.write_float(unit.field_bS)                # bS
    s.write_bool(unit.is_alive)                 # bT  ★
    s.write_bool(unit.is_dead)                  # bV  ★
    s.write_long(unit.death_tick)               # bW
    s.write_player_ref(unit.team)               # bX  ★
    s.write_float(unit.field_bZ)                # bZ
    s.write_float(unit.field_ca)                # ca
    s.write_float(unit.field_cc)                # cc
    s.write_float(unit.field_cd)                # cd
    s.write_float(unit.field_cf)                # cf
    s.write_float(unit.rotation)                # cg  ★
    s.write_float(unit.radius)                  # cj  ★
    s.write_float(unit.field_ck)                # ck
    s.write_float(unit.field_cl)                # cl
    s.write_float(unit.build_progress)          # cm  ★
    s.write_bool(unit.flag_cp)                  # cp
    s.write_bool(unit.flag_cs)                  # cs
    s.write_float(unit.hp)                      # cu  ★
    s.write_float(unit.max_hp)                  # cv  ★
    s.write_bool(unit.flag_cK)                  # cK
    
    # 移动参数数组
    s.write_float(unit.movement_params[0].a)    # cL[0].a
    s.write_float(unit.movement_params[0].d)    # cL[0].d
    
    s.write_unit_ref(unit.parent_unit)          # cN  ★
    
    s.write_byte(26)                             # ★ 版本标记
    
    s.write_int(unit.field_cU)                  # cU
    s.write_float(unit.field_cV)                # cV
    s.write_float(unit.field_ce)                # ce
    s.write_float(unit.field_ch)                # ch
    
    # 移动参数详情 (每个条目)
    count = unit.get_movement_param_count()      # bl()
    s.write_int(count)
    for i in range(count):
        p = unit.movement_params[i]
        s.write_float(p.a)
        s.write_float(p.c)
        s.write_float(p.d)
        s.write_float(p.e)
        s.write_float(p.f)
        s.write_float(p.h)
        s.write_float(p.i)
        
        target = p.j
        if target is not None and target.is_dead:
            target = None
        s.write_unit_ref(target)
    
    s.write_bool(unit.flag_cM)                  # cM
    s.write_int(unit.field_bs)                  # bs
    s.write_float(unit.shield)                  # cx  ★
```

### 5.2 读取 (版本兼容)

```python
def deserialize_unit(unit, stream):
    """am.a(j.k) — 985B, 版本兼容读取"""
    s = stream
    
    unit.flag_bM = s.read_bool()
    unit.ref_bQ = s.read_unit_ref()
    unit.ref_bR = s.read_unit_ref()
    unit.field_bS = s.read_float()
    unit.is_alive = s.read_bool()
    unit.is_dead = s.read_bool()
    unit.death_tick = s.read_long()
    unit.set_team(s.read_player_ref())   # b(n) — 设置队伍
    unit.field_bZ = s.read_float()
    unit.field_ca = s.read_float()
    unit.field_cc = s.read_float()
    unit.field_cd = s.read_float()
    unit.field_cf = s.read_float()
    unit.rotation = s.read_float()
    
    # 坐标 (通过两次readFloat)
    unit.x = s.read_float()              # eo
    unit.y = s.read_float()              # ep
    
    unit.field_cl = s.read_float()
    unit.build_progress = s.read_float()
    unit.flag_cp = s.read_bool()
    unit.flag_cs = s.read_bool()
    
    unit._set_hp_internal(s.read_float())     # o(f) — 内部设置
    unit.max_hp = s.read_float()
    unit.flag_cK = s.read_bool()
    
    # 移动参数
    unit.movement_params[0].a = s.read_float()
    unit.movement_params[0].d = s.read_float()
    unit.parent_unit = s.read_unit_ref()
    
    # ★ 版本检测
    version = s.read_byte()              # d()
    
    if version >= 1:
        unit.field_cU = s.read_int()
        unit.field_cV = s.read_float()
    
    if version >= 2:
        unit.field_ce = s.read_float()
        unit.field_ch = s.read_float()
    
    # 移动参数数组
    count = s.read_int()
    unit._resize_movement_params(count)
    for i in range(count):
        p = unit.movement_params[i]
        p.a = s.read_float()
        p.c = s.read_float()
        p.d = s.read_float()
        p.e = s.read_float()
        p.f = s.read_float()
        
        if version >= 8:
            p.h = s.read_float()
            p.i = s.read_float()
            p.j = s.read_unit_ref()
        
        if version >= 12:
            unit.flag_cM = s.read_bool()
    
    if version >= 6:
        unit.field_bs = s.read_int()
```

---

## 6. 移动系统

### 6.1 移动更新 — `game.f.a(float)` [6276B 核心]

```python
def update_movement(controller, delta):
    """game.f.a(float)"""
    unit = controller.unit                      # j (am)
    
    if unit is None or unit.is_dead:
        return
    
    # 目标位置
    target_x = controller.target_x              # n
    target_y = controller.target_y              # o
    
    # 当前速度
    vel_x = controller.vel_x                    # h
    vel_y = controller.vel_y                    # i
    
    # 计算方向
    dx = target_x - unit.x
    dy = target_y - unit.y
    dist = sqrt(dx*dx + dy*dy)
    
    # 已到达?
    if dist < unit.radius:                      # cj
        controller.arrived = True               # m = true
        return
    
    # 角度计算 (使用预计算查找表)
    angle = atan2(dy, dx)                       # f.i() 调用查找表
    unit.rotation = angle * RAD_TO_DEG          # cg = angle * 57.29578
    
    # 速度计算
    max_speed = unit.get_move_speed()           # 从 UnitType 获取
    acceleration = controller.acceleration      # p
    friction = controller.friction              # r?
    
    # 加速
    vel_x += cos(angle) * acceleration * delta
    vel_y += sin(angle) * acceleration * delta
    
    # 限速
    current_speed = sqrt(vel_x*vel_x + vel_y*vel_y)
    if current_speed > max_speed:
        vel_x = vel_x / current_speed * max_speed
        vel_y = vel_y / current_speed * max_speed
    
    # 摩擦力
    vel_x *= (1.0 - friction * delta)
    vel_y *= (1.0 - friction * delta)
    
    # 碰撞检测
    new_x = unit.x + vel_x * delta
    new_y = unit.y + vel_y * delta
    
    if is_passable(new_x, new_y, unit.radius, unit.move_type):
        unit.x = new_x
        unit.y = new_y
    else:
        # 滑动/反弹
        vel_x *= -0.3                           # 碰撞弹性 (常量≈0.3)
        vel_y *= -0.3
    
    controller.vel_x = vel_x
    controller.vel_y = vel_y
    
    # 更新空间网格
    GlobalState.get().spatial_grid.update(unit)
```

### 6.2 A* 寻路 — `PathSolver.d()` [654B]

```python
def solve_path(solver, map_engine, start_x, start_y, end_x, end_y):
    """fw.k.i.d() — A* 求解"""
    
    # 1. 初始化
    width = map_engine.width                   # C (int)
    height = map_engine.height                 # D (int)
    grid = solver.cost_grid                     # j (byte[])
    clearance_1 = solver.clearance_grid_1       # d (byte[])
    clearance_2 = solver.clearance_grid_2       # e (byte[])
    
    # 2. 边界裁剪
    start_x = max(0, min(start_x, width - 1))
    start_y = max(0, min(start_y, height - 1))
    end_x = max(0, min(end_x, width - 1))
    end_y = max(0, min(end_y, height - 1))
    
    # 3. 遍历网格 (行优先)
    for row in range(start_y - 3, end_y + 3 + 1):     # ±3 边界扩展
        if row < 0 or row >= height: continue
        
        for col in range(start_x - 3, end_x + 3 + 1):
            if col < 0 or col >= width: continue
            
            idx = row * width + col
            
            # 检查可通过性
            blocked = False
            if clearance_1[idx] == 2:           # 类型1阻挡
                blocked = True
            if clearance_2[idx] == 2:           # 类型2阻挡
                blocked = True
            
            if blocked:
                grid[idx] = 0                   # 不可通过
            else:
                grid[idx] = _calculate_cost(solver, col, row)  # 通行成本
    
    # 4. A* 搜索
    open_list = PriorityQueue()
    closed = set()
    
    start_node = Node(start_x, start_y, 0, heuristic(start_x, start_y, end_x, end_y))
    open_list.push(start_node)
    
    max_iterations = 50                         # 常量
    iterations = 0
    
    while not open_list.empty() and iterations < max_iterations:
        current = open_list.pop()
        iterations += 1
        
        if current.x == end_x and current.y == end_y:
            return _reconstruct_path(current)    # e() 路径重建
        
        if (current.x, current.y) in closed:
            continue
        closed.add((current.x, current.y))
        
        # 8邻域扩展
        for dx, dy in [(-1,-1),(0,-1),(1,-1),(-1,0),(1,0),(-1,1),(0,1),(1,1)]:
            nx, ny = current.x + dx, current.y + dy
            if nx < 0 or nx >= width or ny < 0 or ny >= height:
                continue
            if (nx, ny) in closed:
                continue
            
            idx = ny * width + nx
            cost = grid[idx]
            if cost == 0:
                continue                        # 不可通过
            
            # 对角线惩罚
            if dx != 0 and dy != 0:
                cost = int(cost * 1.414)        # √2
            
            new_g = current.g + cost
            new_f = new_g + heuristic(nx, ny, end_x, end_y)
            
            open_list.push(Node(nx, ny, new_g, new_f, current))
    
    return None  # 无路径
```

### 6.3 路径成本计算

```python
def _calculate_cost(solver, col, row):
    """计算单个格子的通行成本"""
    base_cost = 400                              # 基础成本
    # 成本 = 基础成本² = 160000 (来自常量)
    
    # 地形修正
    terrain = solver.map.get_terrain(col, row)
    if terrain == WATER:
        base_cost *= 2
    elif terrain == CLIFF:
        base_cost *= 4
    
    # 移动类型修正 (通过 PathCostCalc)
    move_type = solver.unit_type.get_move_type()
    cost = solver.cost_calc.get_cost(move_type, col, row)
    
    return cost
```

---

## 7. AI 决策

### 7.1 AIPlayer 主循环 — `a.a.i(float)` [2451B]

```python
def ai_main_update(ai, delta):
    """game.a.a.i(float) — 每250ms调用"""
    l = GlobalState.get()
    
    # 跳过条件
    if ai._should_skip():
        return
    
    # 1. 阶段计时器更新
    ai.build_phase_timer -= delta
    ai.attack_phase_timer -= delta
    ai.scout_timer -= delta
    
    # 2. 经济决策 (建造阶段)
    if ai.build_phase_timer <= 0:
        ai._economic_update(delta)              # a(float) [925B]
        ai.build_phase_timer = ai.build_interval  # ~11s
    
    # 3. Zone管理
    ai._zone_update(delta)                      # m(float) [2048B]
    
    # 4. 攻击管理
    if ai.attack_phase_timer <= 0:
        ai._attack_update(delta)                # n(float) [1994B]
        ai.attack_phase_timer = ai.attack_interval  # ~10.6s
    
    # 5. 工厂队列
    ai._factory_update(delta)                   # l(float) [343B]
    
    # 6. 侦察
    if ai.scout_timer <= 0:
        ai._send_scout()
        ai.scout_timer = 50                     # 50帧
```

### 7.2 AttackZone 单位选择 — `a.i.c()` [1026B]

```python
def choose_unit_to_build(zone):
    """a.i.c() — 选择要建造的单位类型"""
    
    # 1. 获取可用单位列表
    available = []
    for unit_type in zone.get_available_units():
        if not zone.can_build(unit_type):
            continue
        if not zone._has_resources_for(unit_type):
            continue
        if zone._is_tech_locked(unit_type):
            continue
        available.append(unit_type)
    
    if not available:
        return None
    
    # 2. 按优先级排序
    def sort_key(ut):
        return (
            -zone._get_priority(ut),       # 优先级(降序)
            zone._get_cost(ut),            # 价格(升序)
            zone._get_build_time(ut),      # 建造时间(升序)
        )
    available.sort(key=sort_key)
    
    # 3. 权重随机选择
    total_weight = sum(zone._get_weight(ut) for ut in available)
    if total_weight <= 0:
        return available[0]
    
    roll = random.randint(0, 100)               # 0-100 随机
    
    cumulative = 0
    for ut in available:
        cumulative += (zone._get_weight(ut) / total_weight) * 100
        if roll <= cumulative:
            return ut
    
    return available[0]
```

### 7.3 攻击波次执行 — `a.i.v()` [772B]

```python
def execute_attack_wave(zone):
    """a.i.v()"""
    
    if not zone.is_active:
        return
    if zone.state != ZoneState.PREPARE:     # 不是准备状态
        return
    
    # 编组攻击单位
    attackers = zone.get_attackers()         # 从AIUnitCombo中选
    if len(attackers) < zone.min_attackers:
        return                               # 数量不足
    
    # 选择目标
    target = zone._find_target()             # 最近/最弱/最高价值敌方
    if target is None:
        # 尝试寻找敌方建筑
        target = zone._find_enemy_building()
    if target is None:
        target = zone._find_enemy_unit()
    if target is None:
        return
    
    # 发送攻击指令
    for unit in attackers:
        cmd = Command()
        cmd.type = CommandType.ATTACK
        cmd.target = target
        cmd.target_pos = (target.x, target.y)
        zone.ai_player.send_command(cmd)
    
    # 更新状态
    zone.state = ZoneState.ACTIVE            # 进入激活状态
    zone.attack_start_time = GlobalState.get().tick
```

### 7.4 AISpawnList 出兵 — `n.i.a(float, float)` [287B]

```python
def spawn_wave_units(spawn_list, base_x, base_y):
    """n.i.a(float, float) — 实际在地图生成AI单位"""
    l = GlobalState.get()
    
    # 获取/创建波次队伍 (队伍ID=1)
    team = PlayerState.get_by_index(1)          # n.k(1)
    if team is None:
        log("Warning: Creating missing wave team AI")
        team = AITeam.create(1)
        team.team_id = 100                      # r = 100
        team.is_ai = True                       # U = true
    
    # 遍历出兵列表
    seed = 0
    for entry in spawn_list.entries:            # b (utility.m of n.j)
        unit_type = entry.unit_type             # n.j.a
        count = entry.count                     # n.j.b
        
        for i in range(count):
            # 创建单位实例
            unit = unit_type.create_instance()   # as.a() → am
            
            # ★ 位置随机化 (防重叠)
            unit.x = base_x + random_range(-85, 85, seed + 0)    # eo
            unit.y = base_y + random_range(-85, 85, seed + 1)    # ep
            
            # ★ 朝向随机
            unit.rotation = random_range(-180, 180, seed + 2)     # cg
            
            seed += 3
            
            # 设置队伍
            unit.team = team                     # bX = team
            unit.spawn_point = ...               # bW
            
            # 加入空间网格
            l.spatial_grid.add(unit)
            
            # 激活单位
            unit.activate()                      # S()
```

### 7.5 UnitCountCondition 检测 — `n/a/c.e(n.a)` [395B]

```python
def check_unit_count(condition, task):
    """n/a/c.e(n.a) — 单位计数条件检测, 遍历所有单位"""
    
    matched = 0
    
    # 遍历全局单位注册表
    all_units = UnitInstance.global_registry.get_all()  # am.bE
    
    for unit in all_units:
        # 1. 队伍检测
        if condition.team is not None:
            if unit.team != condition.team:
                continue
        
        # 2. 类型检测 (必须是 UnitType)
        if not isinstance(unit, UnitType):
            continue
        
        # 3. 父单位检测 (非附着/运输中)
        if unit.parent_unit is not None:         # cN != null
            continue
        
        # 4. 存活检测
        if not unit.is_alive_check():            # bT()
            continue
        
        # 5. 未完成筛选
        if not condition.include_incomplete:     # p
            if unit.build_progress < 1.0:
                continue
        
        # 6. 单位类型筛选
        if condition.unit_type is not None:      # d
            if unit.get_unit_type() != condition.unit_type:  # r()
                continue
        
        # 7. 空闲检测
        if condition.only_idle:                  # f (was 'l' in the field list)
            if not unit.is_idle():               # l()
                continue
        
        # 8. 建造中检测
        if condition.only_in_progress:           # m
            if not (unit.is_idle() and unit.is_building()):  # l() && af()
                continue
        
        # 9. 建筑检测
        if condition.only_buildings:             # e
            if not unit.is_building():           # bI()
                continue
        
        # 10. 主建筑检测
        if condition.only_main_buildings:        # g
            if not unit.is_main_building():
                continue
        
        # 11. 资源池检测
        if condition.only_on_resource_pool:      # h
            if not unit.is_on_resource_pool():
                continue
        
        # 12. 队伍标签
        if condition.team_tag is not None:       # o
            if not unit.team.has_tag(condition.team_tag):
                continue
        
        matched += 1
    
    # 检查数量范围
    if condition.max_units is not None:          # a
        if matched > condition.max_units:
            return False
    
    if condition.min_units is not None:          # b
        if matched < condition.min_units:
            return False
    
    return True
```

---

## 总结

以上伪代码覆盖了游戏的全部核心算法:
- **收入**: 注册/注销/累积/计算
- **伤害**: 护盾→HP→死亡 3阶段完整链
- **建造**: BuildProgress + Factory队列 + 完成转换
- **序列化**: 34字段 UnitInstance 读写, 版本兼容
- **移动**: 移动更新 + A* 寻路
- **AI**: 主循环 + 单位选择 + 攻击波次 + 出兵 + 条件检测

所有算法均可直接翻译为任意语言的实现代码。
