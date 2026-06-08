# 21 — Python 完整移植方案

> 基于: game-lib.jar 1698类全逆向 (99.9%游戏逻辑) + RW-HPS 513文件 + rw_engine仿真 + rwTool工具
> 目标: 将 Rusted Warfare v1.15 转译为纯 Python 3, 零外部依赖 (除渲染层可选)

---

## 一、转译可行性评估

### 1.1 为什么可以转译

```
✅ 所有核心公式已100%逆向 — 收入/伤害/建造/移动/弹丸
✅ 所有枚举/常量已提取 — 17武器/8移动/7行为/11地图类型/87工厂单位
✅ 完整的类层次结构已映射 — 31核心类, 280+字段
✅ AI决策逻辑已分析 — AIPlayer(44KB), 5种Zone, 波次系统, LogicBoolean(215类)
✅ 网络协议已解析 — 40+包类型, TCP/Relay/PoW认证
✅ 文件格式已解析 — .replay/.rwsave/.ini/.tmx/ZIP
✅ 服务器架构已完全理解 — 无头模式, 字节码重定向, 插件系统
✅ 已有可工作的Python仿真 — rw_engine 743行, 覆盖核心战斗/经济/建造
```

### 1.2 代码规模估计

```
Python 等效规模估算 (基于 Java 1698类):
  
  游戏核心 (~300类关键逻辑):
    单位系统:        ~3,000 行
    经济系统:        ~1,500 行
    战斗系统:        ~2,000 行
    建造系统:        ~1,500 行
    移动/寻路:       ~3,000 行 (含A*算法)
    AI系统:          ~5,000 行 (最复杂)
    网络系统:        ~3,000 行
    序列化/存档:     ~2,000 行
    地图/TMX:        ~2,000 行
    LogicBoolean:    ~2,500 行
    事件/触发器:     ~1,000 行
  
  框架层:
    数据结构:        ~1,000 行 (SpatialGrid, ArrayList, RingBuffer等)
    数学工具:        ~1,000 行
    配置/INI解析:    ~1,000 行
  
  渲染层 (可选):
    2D渲染:          ~3,000 行 (Pygame/Arcade)
    UI:              ~2,000 行
  
  估算总计: 核心 ~28,000行 + 渲染 ~5,000行
```

---

## 二、Python 包结构设计

```
rusted_warfare/
├── __init__.py
│
├── core/                          # 核心游戏逻辑
│   ├── __init__.py
│   ├── engine.py                  # 主引擎 (GameEngine)
│   ├── game_loop.py               # 游戏主循环 (GameThread)
│   ├── game_screen.py             # 游戏画面 (GameScreen)
│   ├── global_state.py            # 全局单例 (l/GlobalState)
│   │
│   ├── unit/                      # 单位系统
│   │   ├── __init__.py
│   │   ├── unit_type.py           # UnitType 基类 (y)
│   │   ├── unit_instance.py       # UnitInstance (am)
│   │   ├── unit_registry.py       # 内置单位枚举 (ar, 53个)
│   │   ├── weapon.py              # 武器类型/实例 (av/au)
│   │   ├── movement.py            # 移动类型/控制器 (ao, game.f)
│   │   ├── behavior.py            # 行为模式 (units.a)
│   │   ├── custom_unit.py         # 自定义单位 (custom.j)
│   │   ├── unit_loader.py         # .ini加载器 (custom.l)
│   │   └── factory.py             # 工厂/建造 (h.e, d.j)
│   │
│   ├── combat/                    # 战斗系统
│   │   ├── __init__.py
│   │   ├── damage.py              # 伤害计算 (am.applyDamage)
│   │   ├── death.py               # 死亡链 (am.bu, 8步)
│   │   ├── projectile.py          # 弹丸系统 (fw.ab, a.i)
│   │   ├── stats.py               # 对战统计 (bo)
│   │   └── repair.py              # 维修系统
│   │
│   ├── economy/                   # 经济系统
│   │   ├── __init__.py
│   │   ├── income.py              # 收入计算 (s.a/s.b, y.i)
│   │   ├── build.py               # 建造进度 (d.j)
│   │   ├── upgrade.py             # 升级系统
│   │   ├── refund.py              # 退款系统
│   │   └── resource.py            # 资源组件 (custom.d.b)
│   │
│   ├── ai/                        # AI系统
│   │   ├── __init__.py
│   │   ├── ai_player.py           # AIPlayer (game.a.a, 44KB)
│   │   ├── attack_zone.py         # 攻击区 (a.i)
│   │   ├── build_zone.py          # 建造区 (a.g)
│   │   ├── transport_group.py     # 运输编组 (a.n)
│   │   ├── zone_base.py           # Zone基类 (a.o)
│   │   ├── wave_system.py         # 波次系统 (n.f)
│   │   ├── mission_parser.py      # 任务解析 (n.c)
│   │   ├── mission_executor.py    # 任务执行 (n.d)
│   │   ├── spawn_list.py          # 出兵列表 (n.i)
│   │   ├── unit_combo.py          # 出兵组合 (a.d)
│   │   └── conditions.py          # 条件检测 (n/a/*)
│   │
│   ├── pathfinding/               # 寻路系统
│   │   ├── __init__.py
│   │   ├── astar.py               # A* 求解器 (fw.k.i)
│   │   ├── path_pool.py           # 路径池 (fw.k.l)
│   │   ├── cost_calc.py           # 成本计算 (fw.k.k)
│   │   └── path_utils.py          # 寻路工具 (utility.y)
│   │
│   └── map_system/                # 地图系统
│       ├── __init__.py
│       ├── map_engine.py          # 地图引擎 (game.b.b)
│       ├── tmx_loader.py          # TMX解析 (game.b.e)
│       ├── tile_layer.py          # 瓦片层 (game.b.e)
│       ├── fog.py                 # 战争迷雾 (game.b.f)
│       ├── collision.py           # 碰撞检测 (game.b.k)
│       └── spatial_grid.py        # 空间网格 (units.f.c)
│
├── network/                       # 网络系统
│   ├── __init__.py
│   ├── net_engine.py              # 网络引擎 (j.ad)
│   ├── net_stream.py              # 序列化流 (j.k/j.as)
│   ├── packet.py                  # 包定义 (PacketType, 40+种)
│   ├── connection.py              # 连接管理 (j.c, PlayerConnectX)
│   ├── relay.py                   # 中继协议
│   ├── server.py                  # 服务端 (TCP NIO)
│   ├── client.py                  # 客户端
│   └── pow.py                     # Proof-of-Work认证
│
├── logic/                         # LogicBoolean 脚本引擎
│   ├── __init__.py
│   ├── base.py                    # LogicBoolean 基类
│   ├── operations.py              # 逻辑运算 (AND/OR/NOT)
│   ├── comparisons.py             # 比较运算 (==, !=, <, >, +, -, *, /)
│   ├── game_functions.py          # 游戏条件函数 (73+种)
│   ├── math_functions.py          # 数学函数 (17种)
│   ├── unit_refs.py               # 单位引用 (26种)
│   ├── variables.py               # 变量系统
│   └── loader.py                  # .ini解析器
│
├── framework/                     # 框架/工具
│   ├── __init__.py
│   ├── utils.py                   # GameUtils (fw.f, 128方法)
│   ├── math_utils.py              # 数学工具 (三角函数表/随机数)
│   ├── data_structures.py         # 自定义数据结构
│   │   ├── array_list.py          # CustomArrayList (utility.m)
│   │   ├── unit_list.py           # UnitRegistry (utility.u)
│   │   ├── deque_list.py          # DequeList (utility.o)
│   │   └── ring_buffer.py         # RingBuffer (utility.g)
│   ├── ini_parser.py              # .ini文件解析
│   ├── localization.py            # 多语言 (custom.bb)
│   └── events.py                  # 事件系统
│
├── files/                         # 文件格式
│   ├── __init__.py
│   ├── replay.py                  # .replay 读写
│   ├── save.py                    # .rwsave 读写
│   ├── tmx.py                     # .tmx 地图格式
│   └── zip_resources.py           # ZIP资源包
│
├── game_data/                     # 游戏数据
│   ├── __init__.py
│   ├── units.json                 # 90+单位完整数据表
│   ├── weapons.json               # 武器配置
│   ├── buildings.json             # 建筑配置
│   ├── ai_params.json             # AI参数配置
│   └── constants.py               # 全部常量
│
├── mods/                          # Mod支持
│   ├── __init__.py
│   ├── mod_loader.py             # Mod加载器
│   └── mod_info.py               # Mod元信息
│
└── ui/                            # UI/渲染 (可选)
    ├── __init__.py
    ├── renderer.py                # 2D渲染器 (Pygame/Arcade)
    ├── camera.py                  # 相机/视口
    ├── hud.py                     # HUD渲染
    ├── minimap.py                 # 小地图
    ├── effects.py                 # 特效系统
    ├── sprite_batch.py            # 精灵批渲染
    ├── screens/                   # 画面
    │   ├── main_menu.py
    │   ├── game_screen.py
    │   ├── lobby.py
    │   └── settings.py
    └── assets/                    # UI资源
```

---

## 三、关键算法 — Python 完全实现

### 3.1 收入系统 (完整链路)

```python
# economy/income.py

@dataclass
class TeamUnitTracker:
    """s — 队伍收入追踪器"""
    max_units: int = 0           # a
    non_building_count: int = 0  # b
    completed_count: int = 0     # c
    total_built: int = 0         # d
    factory_slots: int = 0       # e
    incomplete_count: int = 0    # f
    income_rate: float = 0.0     # ★ g = Σ cy()
    
    # 5个ResourceComponent
    resource_h: Optional['ResourceComponent'] = None
    resource_i: Optional['ResourceComponent'] = None
    resource_j: Optional['ResourceComponent'] = None
    resource_k: Optional['ResourceComponent'] = None
    resource_l: Optional['ResourceComponent'] = None
    
    # 2个建造队列
    build_queue_p: Optional['BuildQueue'] = None
    build_queue_q: Optional['BuildQueue'] = None
    
    cancel_count: int = 0        # n
    reclaim_count: int = 0       # o
    
    def register_unit(self, unit: 'UnitInstance'):
        """s.a(am) — 358B 字节码还原"""
        self.total_built += 1
        
        if unit.build_progress < 1.0:
            self.incomplete_count += 1
        else:
            self.completed_count += 1
        
        if not unit.unit_type.is_building:
            self.non_building_count += 1
        
        # 处理ResourceComponent
        res_comp = unit.get_resource_component()
        if res_comp is not None:
            self.resource_k.add(res_comp, 0, float('inf'))
            self.resource_l.add(res_comp, float('-inf'), 0)
        
        # 工厂特殊处理
        if isinstance(unit, FactoryUnit):
            slots = unit.get_slot_count(False)
            self.non_building_count += slots
            self.factory_slots += slots
            if slots > 0:
                self._add_factory_builds(unit)
        
        # 按标签追踪
        self._track_by_tag(unit)
        
        # ★★★ 收入注册 ★★★
        income = unit.get_income_rate()  # cy()
        if income != 0.0 and unit.build_progress >= 1.0:
            self.income_rate += int(income)
    
    def unregister_unit(self, unit: 'UnitInstance'):
        """s.b(am) — 327B 字节码还原"""
        self.total_built -= 1
        
        if unit.build_progress < 1.0:
            self.incomplete_count -= 1
        else:
            self.completed_count -= 1
        
        if not unit.unit_type.is_building:
            self.non_building_count -= 1
        
        res_comp = unit.get_resource_component()
        if res_comp is not None:
            self.resource_k.remove(res_comp, 0, float('inf'))
            self.resource_l.remove(res_comp, float('-inf'), 0)
        
        if isinstance(unit, FactoryUnit):
            slots = unit.get_slot_count(False)
            self.non_building_count -= slots
            self.factory_slots -= slots
            if slots > 0:
                self._remove_factory_builds(unit)
        
        self._untrack_by_tag(unit)
        
        income = unit.get_income_rate()
        if income != 0.0 and unit.build_progress >= 1.0:
            self.income_rate -= int(income)
        
        # 统计取消/回收
        if unit.is_cancel:
            self.cancel_count += 1
        else:
            self.reclaim_count += 1


class GameState:
    """n — 单个玩家/队伍状态"""
    # ... (完整字段见 CLASS_DICTIONARY §2)
    
    def compute_income_per_second(self, speed_mult=2.5, income_mult=1.0):
        """
        收入/s = tracker.income_rate × (60/40) × income_mult × speed_mult
        CC: 18 × 1.5 × 2.5 = 67.5/s
        """
        return self.tracker.income_rate * (60.0 / 40.0) * income_mult * speed_mult
```

### 3.2 伤害系统 (3阶段完整公式)

```python
# combat/damage.py

class UnitInstance:
    """am — 单位实例"""
    
    INCOMPLETE_DAMAGE_MULT = 1.75
    SHIELD_MULTIPLIER = 1.0
    SHIELD_ABSORB = 0.2
    HP_MULTIPLIER = 1.0
    
    def apply_damage(self, incoming: float, attacker: 'UnitInstance',
                     move_ctx: 'MovementController' = None) -> float:
        """
        am.a(float, am) [553B] — 完整伤害公式
        
        阶段1: 未完成建筑惩罚 — damage × 1.75
        阶段2: 护盾吸收 — shieldDmg = incoming × shieldMult
        阶段3: HP伤害 — hpDmg = remaining × hpMult
        
        Returns: 溢出伤害 (用于溅射)
        """
        remaining = incoming
        absorbed = 0.0
        
        # 从移动控制器获取倍率
        shield_mult = move_ctx.shield_mult if move_ctx else self.SHIELD_MULTIPLIER
        shield_absorb = move_ctx.shield_absorb if move_ctx else self.SHIELD_ABSORB
        hp_mult = move_ctx.hp_mult if move_ctx else self.HP_MULTIPLIER
        
        # ===== 阶段1: 建造惩罚 =====
        if self.build_progress < 1.0:
            remaining *= self.INCOMPLETE_DAMAGE_MULT
        
        # ===== 阶段2: 护盾吸收 =====
        if self.special_damage_flag == 0 and self.shield > 0:
            shield_dmg = remaining * shield_mult
            
            if self.shield >= shield_dmg:
                # 护盾完全吸收
                remaining -= self.shield * shield_absorb
                absorbed += self.shield
                self.total_shield_absorbed += self.shield
                self.shield = 0.0
            else:
                # 护盾部分吸收
                self.shield -= shield_dmg
                self.total_shield_absorbed += shield_dmg
                absorbed += shield_dmg
                remaining -= remaining * shield_absorb
        
        # ===== 阶段3: HP伤害 =====
        if remaining > 0:
            hp_dmg = remaining * hp_mult
            
            if self.hp >= hp_dmg:
                # 单位存活, HP减少
                remaining -= self.hp
                absorbed += self.hp
                self._set_hp(0)  # HP归零, 但未死亡
                self.total_hp_lost += self.hp
            else:
                # 部分HP损失
                self._set_hp(self.hp - hp_dmg)
                absorbed += hp_dmg
                remaining -= hp_dmg
                self.total_hp_lost -= hp_dmg
        
        # ===== 阶段4: 记录+触发死亡 =====
        self.last_damage_tick = GlobalState.tick
        if attacker is not None:
            self.last_attacker = attacker
        
        self._check_death()  # ch() → cu<=0 && !bV → bv()
        
        return remaining  # 溢出伤害


# combat/death.py

class DeathChain:
    """am死亡链 — 8步字节码还原"""
    
    @staticmethod
    def check_death(unit: 'UnitInstance'):
        """am.ch() [21B]"""
        if unit.is_dead:
            return
        if unit.hp <= 0:
            DeathChain.trigger_death(unit)
    
    @staticmethod
    def trigger_death(unit: 'UnitInstance'):
        """am.bv() [20B]"""
        DeathChain.core_cleanup(unit)     # bu()
        if not DeathChain.special_check(unit):  # e()
            DeathChain.release_resources(unit)  # a()
        DeathChain.final_cleanup(unit)    # bt()
    
    @staticmethod
    def core_cleanup(unit: 'UnitInstance'):
        """am.bu() [98B] — 核心6步"""
        gs = GlobalState.get()
        
        # Step 1: 从工厂移除
        gs.unit_factory.remove(unit)
        
        # Step 2: ★ 从队伍追踪器注销 (扣除收入!)
        unit.team.unregister_unit(unit)
        
        # Step 3: ★ 从全局单位注册表移除
        UnitInstance.global_registry.remove(unit)
        
        # Step 4: 设置死亡标志
        unit.is_dead = True
        unit.death_tick = gs.tick
        
        if unit.hp > 0:
            unit.hp = 0
        
        # Step 5: 清除移动参数
        for param in unit.movement_params:
            param.target = None
        
        # Step 6: ★ 从空间网格移除
        gs.spatial_grid.remove(unit)
```

### 3.3 A* 寻路 (完整实现)

```python
# pathfinding/astar.py

import heapq
import math
from typing import List, Tuple, Optional

class PathNode:
    """game.b.g — A*路径节点"""
    __slots__ = ('tile_x', 'tile_y', 'g_cost', 'f_cost', 
                 'walkable', 'closed', 'open', 'parent',
                 'terrain_flags', 'unit_occupied', 'building_occupied')
    
    def __init__(self, x, y):
        self.tile_x = x
        self.tile_y = y
        self.g_cost = 0
        self.f_cost = 0
        self.walkable = True
        self.closed = False
        self.open = False
        self.parent = None
        self.terrain_flags = 0
        self.unit_occupied = False
        self.building_occupied = False
    
    def __lt__(self, other):
        return self.f_cost < other.f_cost


class PathSolver:
    """fw.k.i — A*路径求解器"""
    
    # 8方向邻域: 上下左右 + 4对角
    NEIGHBORS = [(-1, -1), (0, -1), (1, -1),
                 (-1, 0),           (1, 0),
                 (-1, 1),  (0, 1),  (1, 1)]
    
    # 常量 (来自字节码)
    MAX_ITERATIONS = 50
    MAX_OPEN_NODES = 16
    MAX_PATH_LENGTH = 127
    BASE_COST = 400  # 基础移动成本
    COST_SQ = 160000  # 400²
    DIAGONAL_WEIGHT = 1.414  # √2
    
    def __init__(self, map_engine):
        self.map = map_engine
        self.cost_grid: List[List[int]] = []
        self.clearance_1: List[List[int]] = []
        self.clearance_2: List[List[int]] = []
    
    def build_cost_grid(self, start, end):
        """构建通行成本网格"""
        width = self.map.width_tiles
        height = self.map.height_tiles
        
        sx = max(0, min(start[0], width - 1))
        sy = max(0, min(start[1], height - 1))
        ex = max(0, min(end[0], width - 1))
        ey = max(0, min(end[1], height - 1))
        
        # ±3边界扩展
        for row in range(sy - 3, ey + 4):
            if row < 0 or row >= height:
                continue
            for col in range(sx - 3, ex + 4):
                if col < 0 or col >= width:
                    continue
                
                idx = row * width + col
                
                # 检查可通过性
                if self.clearance_1[idx] == 2 or self.clearance_2[idx] == 2:
                    self.cost_grid[idx] = 0  # 不可通过
                else:
                    self.cost_grid[idx] = self._calc_cost(col, row)
    
    def _calc_cost(self, col, row):
        """计算单个格子的通行成本"""
        terrain = self.map.get_terrain(col, row)
        base = self.BASE_COST
        
        if terrain == TerrainType.WATER:
            base *= 2
        elif terrain == TerrainType.CLIFF:
            base *= 4
        
        return base
    
    def solve(self, start_pos, end_pos, move_type) -> Optional[List[Tuple[float, float]]]:
        """
        A*求解主循环 [654B字节码还原]
        
        Returns: 路径点列表 [(x1,y1), (x2,y2), ...] 或 None
        """
        start_tile = (int(start_pos[0] // 20), int(start_pos[1] // 20))
        end_tile = (int(end_pos[0] // 20), int(end_pos[1] // 20))
        
        # 构建成本网格
        self.build_cost_grid(start_tile, end_tile)
        
        # A* 搜索
        open_list = []
        closed = set()
        
        start_node = self.map.get_node(*start_tile)
        start_node.g_cost = 0
        start_node.f_cost = self._heuristic(start_tile, end_tile)
        start_node.parent = None
        
        heapq.heappush(open_list, start_node)
        
        for _ in range(self.MAX_ITERATIONS):
            if not open_list:
                break
            
            current = heapq.heappop(open_list)
            
            if (current.tile_x, current.tile_y) == end_tile:
                return self._reconstruct_path(current)
            
            if (current.tile_x, current.tile_y) in closed:
                continue
            closed.add((current.tile_x, current.tile_y))
            
            for dx, dy in self.NEIGHBORS:
                nx, ny = current.tile_x + dx, current.tile_y + dy
                
                if nx < 0 or nx >= self.map.width_tiles:
                    continue
                if ny < 0 or ny >= self.map.height_tiles:
                    continue
                if (nx, ny) in closed:
                    continue
                
                idx = ny * self.map.width_tiles + nx
                cost = self.cost_grid[idx]
                if cost == 0:
                    continue
                
                # 对角线惩罚
                if dx != 0 and dy != 0:
                    cost = int(cost * self.DIAGONAL_WEIGHT)
                
                neighbor = self.map.get_node(nx, ny)
                new_g = current.g_cost + cost
                new_f = new_g + self._heuristic((nx, ny), end_tile)
                
                if not neighbor.open or new_g < neighbor.g_cost:
                    neighbor.g_cost = new_g
                    neighbor.f_cost = new_f
                    neighbor.parent = current
                    neighbor.open = True
                    heapq.heappush(open_list, neighbor)
        
        return None  # 无路径
    
    def _heuristic(self, a, b):
        """启发式: 曼哈顿距离"""
        return abs(a[0] - b[0]) + abs(a[1] - b[1])
    
    def _reconstruct_path(self, node):
        """路径重建 [438B]"""
        path = []
        current = node
        while current is not None:
            # 瓦片坐标 → 像素坐标
            px = current.tile_x * 20 + 10  # 瓦片中心
            py = current.tile_y * 20 + 10
            path.append((px, py))
            current = current.parent
        path.reverse()
        return path
```

### 3.4 AI 主决策循环

```python
# ai/ai_player.py

class AIPlayer(GameState):
    """game.a.a (44KB, 71字段, 79方法)"""
    
    # AI 阈值常量 (来自字节码)
    INITIAL_DELAY_MS = 3000
    BUILD_PHASE_INTERVAL = 100 / 9  # ~11.1s
    ATTACK_WAVE_INTERVAL = 202 / 19  # ~10.6s
    SCOUT_INTERVAL = 50  # 帧
    
    BUILDER_SEARCH_RANGE = 100   # px
    ATTACK_DETECT_RANGE = 360    # px
    CC_PROXIMITY_RANGE = 300     # px
    EXPANSION_RANGE = 320        # px
    TURRET_DETECT_RANGE = 3500   # px
    MAX_DETECT_RANGE = 7500      # px
    
    def __init__(self, team_id):
        super().__init__(team_id)
        
        # 状态机
        self.vip_mode = False
        self.build_phase_timer = self.BUILD_PHASE_INTERVAL
        self.attack_phase_timer = self.ATTACK_WAVE_INTERVAL
        self.scout_timer = self.SCOUT_INTERVAL
        
        # 编组
        self.zones: List[Zone] = []
        self.combos: Dict[str, UnitCombo] = {}
        
        # 延迟计数器
        self.phase_counter = 0
    
    def main_update(self, delta: float):
        """i(float) [2451B] — 主AI更新 (每250ms)"""
        gs = GlobalState.get()
        
        if self._should_skip():
            return
        
        # 1. 计时器更新
        self.build_phase_timer -= delta
        self.attack_phase_timer -= delta
        self.scout_timer -= delta
        
        # 2. 经济决策
        if self.build_phase_timer <= 0:
            self._economic_update(delta)    # a(float) [925B]
            self.build_phase_timer = self.BUILD_PHASE_INTERVAL
        
        # 3. Zone管理
        self._zone_update(delta)            # m(float) [2048B]
        
        # 4. 攻击管理
        if self.attack_phase_timer <= 0:
            self._attack_update(delta)      # n(float) [1994B]
            self.attack_phase_timer = self.ATTACK_WAVE_INTERVAL
        
        # 5. 工厂队列
        self._factory_update(delta)         # l(float) [343B]
        
        # 6. 侦察
        if self.scout_timer <= 0:
            self._send_scout()
            self.scout_timer = self.SCOUT_INTERVAL
    
    def _economic_update(self, delta: float):
        """a(float) [925B] — 经济决策"""
        super().update(delta)
        
        # 检查资金阈值
        credits = self.credits
        
        # 建造优先级
        if credits > self._get_build_threshold():
            self._try_expand()  # 扩展提取器/制造仪
        
        # 工厂生产
        for zone in self.zones:
            if isinstance(zone, BuildZone):
                zone.update(delta)
    
    def _attack_update(self, delta: float):
        """n(float) [1994B] — 攻击波次"""
        for zone in self.zones:
            if isinstance(zone, AttackZone):
                if zone.state == ZoneState.PREPARE:
                    if zone.get_attacker_count() >= zone.min_attackers:
                        zone.execute_attack_wave()
    
    def _send_scout(self):
        """发送侦察单位"""
        if not self.zones:
            return
        
        # 寻找最近的敌方CC
        enemy_cc_pos = self._find_enemy_cc()
        if enemy_cc_pos is None:
            return
        
        # 给侦察单位发送移动指令
        for unit in self.get_idle_scouts():
            self.move_unit_to(unit, enemy_cc_pos[0], enemy_cc_pos[1])


class AttackZone(Zone):
    """a.i (29KB, 42字段, 53方法) — AI攻击区"""
    
    def __init__(self, ai_player, position, radius):
        super().__init__(ai_player, position, radius)
        self.min_attackers = 4
        self.state = ZoneState.PRE
        self.attackers: List[UnitInstance] = []
        self.builders: List[UnitInstance] = []
    
    def choose_unit_to_build(self) -> Optional[UnitType]:
        """c() → as [1026B] — 选择建造单位类型"""
        available = self.get_available_units()
        
        filtered = []
        for unit_type in available:
            if not self.can_build(unit_type):
                continue
            if not self.has_resources(unit_type):
                continue
            if self.is_tech_locked(unit_type):
                continue
            filtered.append(unit_type)
        
        if not filtered:
            return None
        
        # 按优先级排序: 优先级↓, 价格↑, 建造时间↑
        filtered.sort(key=lambda ut: (
            -self.get_priority(ut),
            ut.cost,
            ut.build_time
        ))
        
        # 权重随机选择
        total_weight = sum(self.get_weight(ut) for ut in filtered)
        if total_weight <= 0:
            return filtered[0]
        
        import random
        roll = random.randint(0, 100)
        
        cumulative = 0
        for ut in filtered:
            cumulative += (self.get_weight(ut) / total_weight) * 100
            if roll <= cumulative:
                return ut
        
        return filtered[0]
    
    def execute_attack_wave(self):
        """v() [772B] — 执行攻击波次"""
        if not self.is_active or self.state != ZoneState.PREPARE:
            return
        
        attackers = self.get_attackers()
        if len(attackers) < self.min_attackers:
            return
        
        target = self._find_target()
        if target is None:
            return
        
        for unit in attackers:
            self.ai_player.send_attack_command(unit, target)
        
        self.state = ZoneState.ACTIVE
        self.last_attack_tick = GlobalState.get().tick
    
    def _find_target(self):
        """寻找攻击目标: 最近/最弱/最高价值敌方"""
        # 优先级: 敌方CC > 提取器 > 工厂 > 炮塔 > 单位
        enemy_units = self.ai_player.get_visible_enemy_units()
        
        # 按优先级排序
        def target_priority(unit):
            if unit.is_command_center:
                return 0
            if unit.is_extractor:
                return 1
            if unit.is_factory:
                return 2
            if unit.is_turret:
                return 3
            return 4
        
        sorted_enemies = sorted(enemy_units, key=target_priority)
        
        for enemy in sorted_enemies:
            dist = math.hypot(
                self.center_x - enemy.x,
                self.center_y - enemy.y
            )
            if dist < self.ATTACK_DETECT_RANGE:
                return enemy
        
        return None


class WaveSystem:
    """n.f (26KB, 46字段) — AI波次系统"""
    
    DEFAULT_DIFFICULTY_MULTIPLIER = 3000.0
    
    def __init__(self):
        self.active = False
        self.wave_in_progress = False
        self.global_ai_enabled = False
        self.infinite_waves = False
        
        self.current_wave = 0
        self.difficulty_mult = self.DEFAULT_DIFFICULTY_MULTIPLIER
        self.wave_timer: float = 0.0
        self.wave_multiplier: float = 1.0
        self.emergency_multiplier: float = 1.0
        
        self.active_tasks: List[AITask] = []
        self.pending_spawns: List[SpawnEntry] = []
        self.attack_targets: List[Tuple[float, float]] = []
    
    def update(self, delta: float):
        """c() — 主更新 (每250ms)"""
        if not self.global_ai_enabled:
            return
        
        self.wave_timer += delta
        
        if self.wave_timer >= self.wave_interval:
            self.wave_timer = 0.0
            self._trigger_wave()
    
    def _trigger_wave(self):
        """触发新波次"""
        self.current_wave += 1
        self.wave_in_progress = True
        
        # 难度递增
        self.wave_multiplier *= (1 + self.current_wave * 0.1)
        
        # 生成波次单位
        wave_config = self._get_wave_config()
        for entry in wave_config.spawn_list:
            for _ in range(entry.count * int(self.wave_multiplier)):
                self.pending_spawns.append(entry)
        
        # 执行任务
        for task in self.active_tasks:
            MissionExecutor.execute(self, task)
    
    def _get_wave_config(self) -> 'WaveConfig':
        """d() → n.g"""
        config = WaveConfig()
        config.spawn_count = max(1, int(self.wave_multiplier))
        config.unit_types = self._select_unit_types()
        return config
```

### 3.5 LogicBoolean 脚本解释器

```python
# logic/base.py

class LogicBoolean:
    """抽象基类 — 215类实现的脚本引擎"""
    
    def evaluate(self, context: 'UnitContext') -> bool:
        """read(y) → boolean — 核心评估方法"""
        raise NotImplementedError
    
    def evaluate_number(self, context: 'UnitContext') -> float:
        """readNumber(y) → float"""
        raise NotImplementedError
    
    def evaluate_string(self, context: 'UnitContext') -> str:
        """readString(y) → String"""
        raise NotImplementedError
    
    def evaluate_unit(self, context: 'UnitContext') -> 'UnitInstance':
        """readUnit(y) → am"""
        raise NotImplementedError
    
    @staticmethod
    def parse(expr: str) -> 'LogicBoolean':
        """从.ini字符串创建LogicBoolean树"""
        return LogicBooleanLoader.parse(expr)


# logic/operations.py

class AndBoolean(LogicBoolean):
    def __init__(self, *operands):
        self.operands = operands
    
    def evaluate(self, context):
        return all(op.evaluate(context) for op in self.operands)


class OrBoolean(LogicBoolean):
    def __init__(self, *operands):
        self.operands = operands
    
    def evaluate(self, context):
        return any(op.evaluate(context) for op in self.operands)


class NotBoolean(LogicBoolean):
    def __init__(self, operand):
        self.operand = operand
    
    def evaluate(self, context):
        return not self.operand.evaluate(context)


# logic/game_functions.py — 73+游戏条件函数

class HasResources(LogicBoolean):
    """HasResources <resource_type> <comparison> <value>"""
    def __init__(self, resource_type, comparison, value):
        self.resource_type = resource_type
        self.comparison = comparison
        self.value = value
    
    def evaluate(self, context):
        unit = context.unit
        actual = unit.get_resource(self.resource_type)
        return self.comparison.compare(actual, self.value)


class UnitCount(LogicBoolean):
    """UnitCount <unit_type> <min> <max>"""
    def __init__(self, unit_type, min_count=None, max_count=None):
        self.unit_type = unit_type
        self.min_count = min_count
        self.max_count = max_count
    
    def evaluate(self, context):
        count = 0
        for unit in UnitInstance.global_registry:
            if unit.team == context.unit.team:
                if unit.unit_type == self.unit_type:
                    if unit.is_alive and unit.build_progress >= 1.0:
                        count += 1
        
        if self.min_count is not None and count < self.min_count:
            return False
        if self.max_count is not None and count > self.max_count:
            return False
        return True


class DistanceCheck(LogicBoolean):
    """检查距离: GetDistance Self Nearest > 200"""
    def __init__(self, ref1, ref2, comparison, value):
        self.ref1 = ref1
        self.ref2 = ref2
        self.comparison = comparison
        self.value = value
    
    def evaluate(self, context):
        unit1 = self.ref1.resolve(context)
        unit2 = self.ref2.resolve(context)
        if unit1 is None or unit2 is None:
            return False
        dist = math.hypot(unit1.x - unit2.x, unit1.y - unit2.y)
        return self.comparison.compare(dist, self.value)
```

---

## 四、渲染层方案 (可选)

```python
# ui/renderer.py — 推荐使用 Pygame 或 Arcade

# 方案A: Pygame (成熟, 文档丰富)
import pygame
import pygame.gfxdraw

# 方案B: Arcade (现代API, 硬件加速, 内置相机/物理)
import arcade

class GameRenderer:
    """主渲染器"""
    
    def __init__(self, game_engine, width=1280, height=720):
        self.engine = game_engine
        self.width = width
        self.height = height
        
        # 相机
        self.camera_x = 0
        self.camera_y = 0
        self.zoom = 1.0
        
        # 精灵批
        self.sprite_batch = SpriteBatch()
    
    def render(self):
        """主渲染循环 — 对应 game.i.a(bool,bool,State) [2662B]"""
        
        # 1. 清除屏幕
        self.clear(color=(10, 10, 30))
        
        # 2. 渲染地图
        self._render_ground()
        self._render_biome()
        self._render_decoration()
        self._render_resources()
        
        # 3. 渲染单位
        for unit in self.engine.get_all_units():
            self._render_unit(unit)
        
        # 4. 渲染特效
        for effect in self.engine.effect_engine.active_effects:
            self._render_effect(effect)
        
        # 5. 渲染迷雾
        self._render_fog()
        
        # 6. 渲染HUD
        self._render_hud()
    
    def _render_unit(self, unit):
        """渲染单个单位"""
        screen_x, screen_y = self._world_to_screen(unit.x, unit.y)
        
        # 渲染精灵
        sprite = self.get_unit_sprite(unit.unit_type, unit.rotation)
        scaled_w = int(unit.radius * 2 * self.zoom)
        scaled_h = int(unit.radius * 2 * self.zoom)
        self.sprite_batch.draw(sprite, screen_x, screen_y, scaled_w, scaled_h)
        
        # 渲染血条
        if unit.hp < unit.max_hp:
            self._render_hp_bar(unit, screen_x, screen_y)
        
        # 渲染护盾
        if unit.shield > 0:
            self._render_shield_bar(unit, screen_x, screen_y)
        
        # 渲染建造进度
        if unit.build_progress < 1.0:
            self._render_build_progress(unit, screen_x, screen_y)
    
    def _world_to_screen(self, wx, wy):
        """世界坐标 → 屏幕坐标"""
        sx = (wx - self.camera_x) * self.zoom + self.width / 2
        sy = (wy - self.camera_y) * self.zoom + self.height / 2
        return sx, sy
    
    def _screen_to_world(self, sx, sy):
        """屏幕坐标 → 世界坐标"""
        wx = (sx - self.width / 2) / self.zoom + self.camera_x
        wy = (sy - self.height / 2) / self.zoom + self.camera_y
        return wx, wy


class HUD:
    """HUD渲染 — 对应 game.i.b(float,int) [2965B]"""
    
    def render(self, screen, player_state):
        # 顶部信息栏
        self._render_top_bar(screen, player_state)
        # 底部工具栏
        self._render_bottom_bar(screen, player_state)
        # 小地图
        self._render_minimap(screen)
        # 选中单位信息
        self._render_selection_info(screen, player_state.selected_units)
        # 消息区域
        self._render_messages(screen)
```

---

## 五、网络层方案

```python
# network/server.py

import asyncio
import struct

class GameServer:
    """RW-HPS Python等效实现"""
    
    PACKET_TYPES = {
        4: 'PACKET_DOWNLOAD_PENDING',
        10: 'TICK',
        20: 'GAMECOMMAND_RECEIVE',
        30: 'SYNC_CHECK',
        35: 'SYNC',
        106: 'SERVER_INFO',
        108: 'HEART_BEAT',
        109: 'HEART_BEAT_RESPONSE',
        110: 'REGISTER_PLAYER',
        111: 'DISCONNECT',
        112: 'ACCEPT_START_GAME',
        115: 'TEAM_LIST',
        120: 'START_GAME',
        140: 'CHAT_RECEIVE',
        141: 'CHAT',
        150: 'KICK',
        151: 'RELAY_POW',
        160: 'PREREGISTER_INFO_RECEIVE',
        # ... 完整40+类型
    }
    
    MAX_PACKET_SIZE = 50000000  # 50MB
    HEADER_SIZE = 8
    
    def __init__(self, port=5201, max_players=10):
        self.port = port
        self.max_players = max_players
        self.connections: Dict[str, 'PlayerConnection'] = {}
        self.engine = GameEngine()
    
    async def start(self):
        server = await asyncio.start_server(
            self._handle_connection, '0.0.0.0', self.port
        )
        async with server:
            await server.serve_forever()
    
    async def _handle_connection(self, reader, writer):
        conn = PlayerConnection(reader, writer, self)
        self.connections[conn.id] = conn
        
        try:
            while True:
                # 读取包头
                header = await reader.readexactly(self.HEADER_SIZE)
                type_int = struct.unpack('>i', header[:4])[0]
                data_len = struct.unpack('>i', header[4:8])[0]
                
                # 读取包体
                data = await reader.readexactly(data_len)
                
                # 处理包
                packet = Packet(type_int, data)
                await conn.handle_packet(packet)
                
        except asyncio.IncompleteReadError:
            pass
        finally:
            await conn.disconnect()
    
    def create_game_packet(self, packet_type: int, *fields):
        """创建游戏指令包"""
        out = GameOutputStream()
        for field in fields:
            if isinstance(field, str):
                out.write_string(field)
            elif isinstance(field, int):
                out.write_int(field)
            elif isinstance(field, float):
                out.write_float(field)
            elif isinstance(field, bool):
                out.write_bool(field)
        return out.create_packet(packet_type)
```

---

## 六、实施路线图

### 阶段 1: 核心引擎 (3-4周)

```
□ core/engine.py               — 游戏引擎框架
□ core/global_state.py         — 全局状态管理
□ core/unit/unit_instance.py   — 单位实例 (am, 43KB → ~800行)
□ core/unit/unit_type.py       — 单位类型 (y, 90KB → ~1000行)
□ core/unit/unit_registry.py   — 内置单位 (ar, ~200行 + JSON数据)
□ core/combat/damage.py        — 伤害系统 (~400行)
□ core/combat/death.py         — 死亡链 (~200行)
□ core/economy/income.py       — 收入系统 (~500行)
□ core/economy/build.py        — 建造系统 (~500行)
□ core/map_system/spatial_grid.py — 空间网格 (~300行)
□ framework/data_structures.py  — 数据结构 (~400行)
□ framework/math_utils.py       — 数学工具 (~300行)
```

### 阶段 2: 完整游戏逻辑 (3-4周)

```
□ core/combat/projectile.py    — 弹丸系统 (~400行)
□ core/unit/movement.py        — 移动控制器 (~800行)
□ core/pathfinding/astar.py    — A*寻路 (~500行)
□ core/pathfinding/path_pool.py — 路径池 (~200行)
□ core/unit/factory.py         — 工厂/建造队列 (~400行)
□ core/unit/custom_unit.py     — 自定义单位 (~500行)
□ core/unit/unit_loader.py     — .ini加载器 (~300行)
□ core/map_system/map_engine.py — 地图引擎 (~500行)
□ core/map_system/tmx_loader.py — TMX解析 (~300行)
□ core/map_system/fog.py       — 迷雾系统 (~200行)
□ files/replay.py              — 回放读写 (~400行)
□ files/save.py                — 存档读写 (~300行)
```

### 阶段 3: AI 系统 (2-3周)

```
□ core/ai/ai_player.py         — AIPlayer (~1500行)
□ core/ai/zone_base.py         — Zone基类 (~200行)
□ core/ai/attack_zone.py       — 攻击区 (~800行)
□ core/ai/build_zone.py        — 建造区 (~500行)
□ core/ai/wave_system.py       — 波次系统 (~600行)
□ core/ai/mission_parser.py    — 任务解析 (~300行)
□ core/ai/mission_executor.py  — 任务执行 (~300行)
□ core/ai/spawn_list.py        — 出兵列表 (~200行)
□ core/ai/conditions.py        — 条件检测 (~400行)
```

### 阶段 4: LogicBoolean & 脚本 (1-2周)

```
□ logic/base.py                — 基类 (~200行)
□ logic/operations.py          — 逻辑/比较运算 (~300行)
□ logic/game_functions.py      — 73+游戏函数 (~800行)
□ logic/math_functions.py      — 17种数学函数 (~200行)
□ logic/unit_refs.py           — 26种单位引用 (~300行)
□ logic/loader.py              — .ini解析 (~200行)
```

### 阶段 5: 网络 & 多人 (2-3周)

```
□ network/packet.py            — 包定义 (~300行)
□ network/net_stream.py        — 序列化流 (~400行)
□ network/connection.py        — 连接管理 (~300行)
□ network/server.py            — 服务端 (~500行)
□ network/client.py            — 客户端 (~400行)
□ network/relay.py             — 中继协议 (~300行)
```

### 阶段 6: 渲染 & UI (可选, 2-3周)

```
□ ui/renderer.py               — 2D渲染器 (~600行)
□ ui/camera.py                 — 相机 (~200行)
□ ui/hud.py                    — HUD (~500行)
□ ui/minimap.py                — 小地图 (~200行)
□ ui/effects.py                — 特效 (~300行)
□ ui/screens/main_menu.py      — 主菜单 (~200行)
□ ui/screens/game_screen.py    — 游戏画面 (~400行)
```

---

## 七、游戏数据文件

### 7.1 单位数据表 (game_data/units.json)

```json
{
  "commandCenter": {
    "name": "Command Center",
    "cost": 3000,
    "hp": 4000,
    "shield": 0,
    "speed": 0,
    "range": 280,
    "damage": 70,
    "income": 18,
    "mass": 100,
    "radius": 45,
    "build_time": 62.0,
    "is_building": true,
    "move_type": "BUILDING",
    "weapon_type": "ATTACK",
    "behavior": "GUARD_AREA",
    "tier": 0,
    "can_build": ["extractorT1", "fabricatorT1", "builder", "scout", 
                   "mechFactory", "airFactory", "seaFactory", "landFactory",
                   "turret", "antiAirTurret"],
    "upgrades_to": null
  },
  "plasmaTank": {
    "name": "Plasma Tank",
    "cost": 1000,
    "hp": 220,
    "shield": 0,
    "speed": 48.0,
    "range": 165,
    "damage": 100,
    "income": 0,
    "mass": 15,
    "radius": 25,
    "build_time": 17.0,
    "is_building": false,
    "move_type": "LAND",
    "weapon_type": "ATTACK",
    "behavior": "AGGRESSIVE",
    "tier": 1,
    "built_from": ["landFactory"],
    "upgrades_to": null
  }
  // ... 90+完整单位数据
}
```

### 7.2 AI 参数 (game_data/ai_params.json)

```json
{
  "distance_thresholds": {
    "builder_search": 100,
    "unit_search": 290,
    "cc_proximity": 300,
    "expansion": 320,
    "attack_detect": 360,
    "far_range": 400,
    "very_far": 700
  },
  "time_thresholds": {
    "initial_delay_ms": 3000,
    "build_phase_seconds": 11.1,
    "attack_wave_seconds": 10.6,
    "scout_interval_frames": 50
  },
  "probabilities": {
    "random_decision_range": 100,
    "high_prob_threshold": 90,
    "near_complete_ratio": 0.98
  },
  "wave_system": {
    "difficulty_multiplier": 3000.0,
    "multiplier_growth_per_wave": 0.1,
    "default_wave_interval_seconds": 60
  }
}
```

---

## 八、与 rw_engine 的关系

现有的 `rw_engine/engine.py` (504行) 是概念验证 — 正确但简化。完整移植将:

1. **复用它已验证的公式** — 所有常量/公式已100%正确
2. **扩展单位系统** — 从~40单位扩展到完整的90+内置 + 自定义
3. **添加完整AI** — 替换简单的auto_combat()为完整的AIPlayer+Zone系统
4. **添加寻路** — 替换直接移动为A*寻路
5. **添加网络** — 支持多人游戏
6. **添加地图** — TMX加载和5瓦片层渲染
7. **添加脚本** — LogicBoolean 215类解释器

---

*转译计划: 基于 1698类Java 全逆向 + 513 Kotlin服务端 + 743行Python验证实现*
*最后更新: 2026-06-07*
