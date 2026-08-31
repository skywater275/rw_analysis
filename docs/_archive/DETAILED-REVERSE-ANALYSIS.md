# Rusted Warfare v1.15 — 游戏源码逆向详细分析

> 日期: 2026-06-23 | v9.45 | 基于 javap -p 字节码交叉验证

---

## 一、源码结构总览

```
game-lib.jar (2.6 MB) — 原始混淆JAR
├── 01-classes/         231个核心.class (+140个内部类) ← 真正的游戏逻辑
├── 02-decompiled/      1,698个.java (CFR 0.152反编译) ← 含Android桩/Lib依赖
├── 03-deobfuscated/    1,698个.java (apply_enhanced.py输出) ← 可读重命名版本
├── mappings/
│   ├── supplement.csv  4,918条映射 (3,268字段 + 890方法 + 其他)
│   ├── mappings.csv    103条类重命名
│   └── mappings.json   100条类重命名 + 135条字段映射
└── docs/               40+个分析文档, 6个子目录
```

### 关键事实

- **game-lib.jar** 包含完整的游戏逻辑，但CFR反编译器输出了大量Android SDK桩类、第三方库类
- **231个核心.class** 才是真正的Rusted Warfare游戏代码
- 1,698个CFR输出中，1,467个是外部依赖/桩类（其字段/方法映射不参与字节码验证）
- supplement.csv的3,268条字段映射中，约1,857条属于非核心类（但仍对反混淆输出有贡献）

---

## 二、字节码级别覆盖率 (javap -p 验证)

### 2.1 总体覆盖率

| 维度 | 总计 | 已映射 | 覆盖率 | 未映射 |
|------|------|--------|--------|--------|
| **核心.class文件** | 231 | — | — | — |
| **字节码字段总数** | 2,492 | 1,411 | **56.6%** | 1,081 |
| **字节码方法总数** | 1,628 | 594 | **36.5%** | 1,034 |
| **完全映射的类** | — | 3 | 1.3% | — |
| **部分映射的类** | — | 42 | 18.2% | — |
| **完全未映射的类** | — | — | — | 186 (80.5%) |

### 2.2 Top 20 映射最完整的核心类

| 类 | 总计(f+m) | 已映射 | 覆盖率 | 说明 |
|----|----------|--------|--------|------|
| MovementController | 121 | 121 | **100%** | 移动/物理/碰撞控制器 |
| ProjectileDefinition | 112 | 111 | **99.1%** | 射弹/子弹配置定义 |
| NetEngine | 191 | 188 | **98.4%** | 网络引擎 |
| GlobalState | 290 | 275 | **94.8%** | 全局引擎状态 |
| PlayerState | 123 | 115 | **93.5%** | 玩家状态 |
| UnitRegistry | 85 | 79 | **92.9%** | 单位类型注册表 |
| PlayerConnect | 47 | 42 | **89.4%** | 玩家连接 |
| MovableUnit | 28 | 25 | **89.3%** | 移动单位基类 |
| InputNetStream | 28 | 25 | **89.3%** | 网络输入流 |
| UnitType | 248 | 219 | **88.3%** | 单位类型基类 |
| ResourceComponent | 23 | 20 | **87.0%** | 资源成本组件 |
| UnitInstance | 258 | 212 | **82.2%** | 单位实例 |
| ReplayEngine | 28 | 23 | **82.1%** | 回放引擎 |
| Factory | 61 | 49 | **80.3%** | 工厂/建造队列 |
| MapEngine | 64 | 48 | **75.0%** | 地图引擎 |

### 2.3 完全未映射的核心类 (186个)

这些类主要是：
- **内部/小型枚举类**: logicBooleans, UnitReference等
- **单元子类型**: game.units.d.{a,c,g,h,i,j,k} (BuilderUnit, CarrierUnit等子类)
- **框架工具类**: SettingsEngine (136成员!), GameObject, 网络内部类
- **音频/输入系统**: AudioEngine内部类
- **反编译残留**: 部分CFR生成的合成类

---

## 三、反混淆数据库分析 (supplement.csv)

### 3.1 规模

| 指标 | 数值 |
|------|------|
| 总条目 | 4,918 |
| 字段映射 | 3,268 |
| 方法映射 | 890 |
| 类重命名 (映射到CSV) | 103 |
| 覆盖的包 | 52 |
| 文件大小 | ~300 KB |

### 3.2 映射质量

| 质量指标 | 数值 |
|----------|------|
| 字节码验证通过率 (字段) | 1,411/3,268 = **43.2%** 能匹配到核心.class |
| 字节码验证通过率 (方法) | 594/890 = **66.7%** 能匹配到核心.class |
| 幻影映射 (已移除) | 102 |
| 类名不匹配 (已修正) | 186 (ModUnitRegistry d→l, CustomUnitType a→j) |

### 3.3 非核心类映射

~1,857条字段映射和~296条方法映射属于核心.class之外的类。这些类包括：
- Android SDK桩 (android.graphics.Paint, android.graphics.Rect等)
- LibRocket UI库 (com.corrodinggames.librocket.*)
- Slick2D渲染后端 (com.corrodinggames.rts.java.Slick2DRenderer)
- 可靠UDP栈 (a.a.ReliableSocket, a.a.ReliableServerSocket)
- Java标准库桩

这些映射依然有价值——它们使03-deobfuscated输出中的这些文件变得可读。

---

## 四、各系统逆向程度

### 4.1 已深入覆盖的系统

| 系统 | 关键类 | 覆盖率 | 状态 |
|------|--------|--------|------|
| **全局引擎** | GlobalState (l) | 94.8% | ✅ 基本完成 |
| **网络引擎** | NetEngine (ad) | 98.4% | ✅ 基本完成 |
| **移动/物理** | MovementController (f) | 100% | ✅ 完成 |
| **射弹系统** | ProjectileDefinition (g) | 99.1% | ✅ 基本完成 |
| **单位类型** | UnitType (y) | 88.3% | ✅ 高度覆盖 |
| **单位实例** | UnitInstance (am) | 82.2% | ✅ 高度覆盖 |
| **网络序列化** | InputNetStream, OutputNetStream | 89.3% | ✅ 高度覆盖 |
| **回放系统** | ReplayEngine (ba) | 82.1% | ✅ 高度覆盖 |
| **资源经济** | ResourceComponent (d.b) | 87.0% | ✅ 高度覆盖 |
| **单位注册表** | UnitRegistry (ar) | 92.9% | ✅ 高度覆盖 |

### 4.2 部分覆盖的系统

| 系统 | 关键类 | 覆盖率 | 缺口 |
|------|--------|--------|------|
| **AI波次系统** | AIWaveSystem (n.f) | ~50% | 方法未完全映射 |
| **工厂/建造** | Factory (h) | 80.3% | 部分逻辑未映射 |
| **地图引擎** | MapEngine (b.b) | 75.0% | 渲染层字段未映射 |
| **渲染引擎** | GLRenderer, TextureManager | ~40% | 大量重载方法 |
| **游戏UI** | InGameUI | ~15% | 类解析问题, 方法重载 |
| **输入系统** | KeyBindingManager (ac) | ~68% | 刚完成字段映射 |

### 4.3 尚未开始的系统

| 系统 | 关键类 | 状态 |
|------|--------|------|
| **设置引擎** | SettingsEngine (bQ) | ❌ 136成员, 0% |
| **音频引擎** | AudioEngine (a) 及子类 | ❌ 大部分未映射 |
| **HUD管理器** | HUDManager (d) | ❌ 大部分未映射 |
| **Mod系统** | ModUnitRegistry (custom.l) | ⚠️ 刚修复类名, 尚需验证 |
| **逻辑引擎** | LogicBoolean (120+文件) | ❌ 几乎未开始 |
| **LibRocket UI** | LibRocketBridge等 | ❌ UI库, 低优先级 |
| **186个小型类** | 枚举/内部类/工具类 | ❌ 0% (占核心类80.5%) |

---

## 五、完全逆向评估

### 5.1 当前状态

| 评估维度 | 完成度 | 说明 |
|----------|--------|------|
| **核心类识别** | 100% | 231个核心.class全部识别 |
| **类重命名** | ~45% | 103/231个类有可读名称 |
| **字段映射 (核心)** | 56.6% | 1,411/2,492 |
| **方法映射 (核心)** | 36.5% | 594/1,628 |
| **字节码验证** | ~55% | 45/231类有验证映射 |
| **文档化** | ~40% | 40+文档覆盖主要系统 |
| **整体游戏逻辑理解** | ~65% | 核心循环/网络/AI/单位已理解 |

### 5.2 剩余关键挑战

1. **方法重载问题** (最大技术障碍)
   - 单字符方法名如 `a()` 在一个类中有46个重载
   - 当前工具只支持 1个旧名→1个新名 的简单映射
   - 需要签名级映射工具 (方法名+参数类型)

2. **186个完全未映射类** (80.5%)
   - 大部分是小型枚举/内部类/工具类
   - 影响面小但数量多
   - 可部分自动化处理

3. **设置/配置系统**
   - SettingsEngine (136成员, 0%) — 控制所有游戏设置
   - INI解析系统 — Mod配置读取

4. **音频/HUD/UI系统**
   - 这些系统的.class文件在01-classes中但尚未映射
   - InGameUI的.class文件需要重新识别

5. **深度逻辑验证**
   - 现有映射基于CFR反编译阅读理解
   - 少数映射可能存在语义错误（虽然字节码层面正确）

### 5.3 完成完全逆向的预估

| 目标 | 当前 | 目标 | 需要工作 |
|------|------|------|---------|
| 核心字段映射 | 56.6% | 90% | ~850个字段 |
| 核心方法映射 | 36.5% | 70% | ~550个方法 (考虑重载限制) |
| 类重命名 | ~45% | 80% | ~80个类 |
| 文档化 | ~40% | 80% | 剩余系统文档 |

**估计额外工作量**: 如果维持当前节奏，核心字段可达90%+覆盖，方法受限于重载问题可能在50-60%达到平台期。要突破方法重载限制，需要升级apply_enhanced.py支持签名级映射。

---

## 六、工具链

| 工具 | 用途 | 状态 |
|------|------|------|
| CFR 0.152 | Java字节码反编译 | ✅ |
| apply_enhanced.py | 6阶段反混淆 (类→导入→字段→方法→内部类→extends) | ✅ |
| fix_single_char_extends.py | 单字符extends修复 | ✅ |
| cross_validate_bytecode.py | javap交叉验证 (95%+准确率) | ✅ |
| remove_phantoms.py | 幻影映射移除 | ✅ |
| batch_add_v94*.py | 批量添加映射 | ✅ |
| comprehensive_analysis.py | 全量分析报告 | ✅ 新建 |

---

## 七、已归档文件

- `deobfuscation/DEOBFUSCATION-v9-FINAL.md` — v9最终状态
- `deobfuscation/CROSS-VALIDATION.md` — 交叉验证报告1
- `deobfuscation/CROSS-VALIDATION-2.md` — 交叉验证报告2
- `deobfuscation/CROSS-VALIDATION-v940.md` — v9.40验证报告
- `deobfuscation/ERRATA.md` — 已知勘误
- `deobfuscation/VALIDATION-GATES.md` — 验证检查点
- `engine-systems/SYSTEM-*.md` — 14个引擎系统文档
- `game-mechanics/*.md` — 14个游戏机制文档
