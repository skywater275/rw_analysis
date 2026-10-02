# 架构与构建管线

## 一、总览

```
RustedWarfare/game-lib.jar（混淆字节码 = 唯一真值）
        │
        ├─ CFR 反编译 ──────► 02-decompiled/（1,698）    交叉参考
        ├─ FernFlower 反编译 ► 02b-decompiled/（1,698）   ★ 结构地面真值
        │
        └─ 解混淆 + 人工修复 ─► 03-deobfuscated/（1,577 / 71 包）  ★ 开发主线
                                   │
                                   │ javac（门禁 0 错误）
                                   ▼
                          反向构建（改名回混淆名）
                                   │
                       ┌───────────┴────────────┐
                       │ build_reverse_jar.py   │  ① 逐类反向改名
                       │ build_conflict_pass.py │  ② JLS 类包同名 ⇒ 分世界 + 影子 jar
                       └───────────┬────────────┘
                                   ▼
                     build/reverse-classes/（候选池 1,281）
                                   │  tools/rig/repack_product.py（池 → jar）
                                   ▼
                     build/game-lib-reverse.jar（交付物）
                                   │
                                   ▼
                     replacement_verify.py（V0–V8 验收）
```

## 二、关键设计

### 1. 池 = 交付物唯一来源
交付物由 `build/reverse-classes/` **重打包**得到。
**重建 ≠ 池**：一次完整重建只产 **873** 个 A 类，而池可支撑 **1,273** ⇒
**池是累积产物** ⇒ **严禁删除** ✗。

### 2. 混合形态（STANDARD-02）
交付物里约 **75%** 是我们的字节码、**25%** 是原版混淆字节。
我们的类**必须提供比原版更多的成员**（脚手架），否则其它反向类调用会 `NoSuchMethodError`。
⇒ 因此结构判据**只判「缺失成员」**，「多出成员」只作信息 ✓。

### 3. JLS「类包同名」与冲突世界
混淆器会让 **类名与包名相同**（如类 `a.a.a` 与包 `a.a.a`）✗ ⇒ javac 硬限制。
解法（`build_conflict_pass.py`）：
- **影子 jar**：把冲突类改名（如 `qom.…`）并改写全库引用 ⇒ 类与包可共存
- **分世界编译**：按冲突闭包切分，逐世界编译后合并
- `RW_SHADOW_PUBLIC=1`：把影子副本里的被改名类提升为 `public`（默认关闭）

### 4. 枚举机制
stock 枚举常量的 **`name()` 就是 INI 字符串**；**字段名才是混淆名**。
⇒ `03` 源写成 `enum X { <INI 字符串…>; }`，javac 自动生成 `(String,int)` 构造器并保留字符串 ✓。

### 5. 双反编译树的分工
| 树 | 用途 |
|---|---|
| `02`（CFR） | 逻辑可读性较好 |
| `02b`（FernFlower） | **结构更接近原版** ⇒ 做**声明/成员**的地面真值 |

## 三、构建脚本

| 脚本 | 作用 |
|---|---|
| `tools/fixers/build_reverse_jar.py` | 逐类反向改名（约 3,600 行，含 A1–A80 系列修复通道） |
| `tools/fixers/build_conflict_pass.py` | 冲突世界 + 影子 jar + 注入池 + 重打包 |
| `tools/rig/repack_product.py` | 池 → 交付物 jar |
| `tools/gates/javac_gate.py` | `03` 树编译门禁（0 错误基线） |
| `tools/gates/replacement_verify.py` | V0–V8 验收总入口 |

## 四、环境变量

| 变量 | 作用 |
|---|---|
| `RW_REVERSE_OUT_JAR` | 把构建输出重定向到**旁路 jar**（不动交付物 ✓ 安全实测） |
| `RW_SHADOW_PUBLIC` | `=1` 时影子副本内被改名类提升为 public |
| `RW_KEEP_CONFLICT` | 保留原名冲突类（**已证伪** ✗ 产出 929→606） |

## 五、缓存契约（★ 可随时删 ✓）

| 路径 | 内容 | 谁生成 | 删了会怎样 |
|---|---|---|---|
| `.cache/jar-member-closure.json` | jar 成员闭包（加速查询 ✓） | 分析脚本按需生成 | 下次运行**自动重建** ✓ |
| `cache/patched-classes/*.class` | 补过 `InnerClasses` 属性的类（供门禁 classpath ✓） | `tools/cli.py` 登记的工具 | 需重跑该工具再跑门禁 ✓ |
| `RustedWarfare/cache/` | **游戏自身**的模组/音乐缓存 ✓ | 游戏运行时 | 游戏会自动重建 ✓ |
| `tools/**/__pycache__` | Python 字节码缓存 | Python | 无害 ✓ |

⇒ **这些都是派生数据** ✓，**不在版本管理内** ✓，**可随时清理** ✓；
  清理后按上表重新生成即可 ✓（不影响交付物与池 ✓）。
