# 09 — 工具/框架基础类

> 逆向度: 95% | 包: `gameFramework.utility.*` (56类), `gameFramework.f`, `gameFramework.bq`
> 验证: game-lib.jar 字节码 + 全局引用分析

---

## 类名意义

| 混淆名 | 完整路径 | 大小 | 实际含义 |
|--------|---------|------|---------|
| `utility.m` | `gameFramework.utility.m` | 7KB | **CustomArrayList** — 自定义动态数组 (403处引用) |
| `utility.u` | `gameFramework.utility.u` | 7KB | **UnitRegistry** — 全局单位注册表 (am.bE) |
| `utility.o` | `gameFramework.utility.o` | 6KB | **DequeList** — 双端队列 (两个 utility.m) |
| `utility.g` | `gameFramework.utility.g` | 3KB | **RingBuffer** — 环形缓冲区 |
| `utility.n` | `gameFramework.utility.n` | — | **ListIterator** — utility.m 的迭代器 |
| `utility.x` | `gameFramework.utility.x` | — | **ArrayUtils** — 数组工具 |
| `utility.q/r/p` | `gameFramework.utility.q/r/p` | — | utility.o 的内部迭代器/视图 |
| `f` | `gameFramework.f` | 36KB | **GameUtils** — 数学/字符串/IO 工具 (128方法) |
| `bq` | `gameFramework.bq` | <1KB | **BaseGameObject** — 所有游戏对象的基类 |
| `bb` | `game.units.custom.bb` | 2KB | **LocalizedString** — 多语言文本 |

---

## CustomArrayList — `utility.m` (7KB, 34方法)

### 概述
游戏中最重要的数据结构。这是一个手写的 `ArrayList` 替代品，被 **403 个地方** 引用。不使用 `java.util.ArrayList` 的原因包括：性能优化、避免自动装箱、直接数组访问、Android 兼容性。

### 类结构
```
extends java.util.AbstractList
implements java.io.Serializable, java.lang.Cloneable, java.util.RandomAccess
```

### 字段
```
a (int)         — 当前大小 (public, 可直接访问)
b (Object[])    — 内部数组 (package-private)
```

### 核心方法
```
add(Object) → boolean  (77B)
  — 追加到末尾, ensureCapacity, 数组扩容

add(int, Object) → void  (115B)
  — 在指定位置插入, System.arraycopy 移动元素

remove(int) → Object  (63B)
  — 按索引删除, 数组压缩

remove(Object) → boolean  (145B)
  — 按值删除 (遍历→找到→删除)

get(int) → Object  (24B)
  — 边界检查 + 数组访问

a(int) → Object  (7B)
  — 无边界检查的 get (性能优化版)

b() → Object  (54B)
  — pop/removeLast — 移除并返回最后一个元素

c() → Object  (34B)
  — removeFirst — 移除并返回第一个元素

clone() → Object  (33B)
  — 浅拷贝

toArray() → Object[]  (23B)
  — 返回内部数组副本

a() → Object[]  (5B)
  — 返回内部数组引用 (注意: 直接引用, 非副本!)

iterator() → Iterator  (10B)
  — 返回 utility.n (ListIterator)
```

### 扩容策略
```
private static b(int) → int  (19B)
  — 容量增长算法
  — 小容量: 翻倍增长
  — 大容量: 增量增长 (~容量/2)
```

### 使用场景
- AI 任务队列 (n.b)
- 出兵列表 (n.i)
- 网络连接列表 (j.ad.aM)
- 回放块队列 (bb.i)
- 所有需要动态数组的游戏系统

---

## UnitRegistry — `utility.u` (7KB, 36方法)

### 概述
专门为 `am` (单位实例) 类型优化的数组列表。**这是 `am.bE` 的类型** — 全局单位注册表，被 AI 条件检测和空间查询遍历。

### 类结构
```
extends java.util.AbstractList
implements java.io.Serializable, java.lang.Cloneable, java.util.RandomAccess
```

### 字段
```
a (static final am[])  — 空数组常量 (共享引用)
b (int)               — 当前大小 (public)
c (am[])              — 内部数组 (package-private, 类型化为 am[])
```

### 与 utility.m 的区别
```
utility.m: Object[] 存储, 需要转型
utility.u: am[] 存储, 类型安全, 避免转型开销

移除方法差异:
  u.b(am) → void       — 按值移除 (遍历→置null→压缩)
  u.b(int) → am        — 按索引移除并返回 (pop)
  u.a(am) → boolean    — 追加单位
```

### 全局引用
```
am.bE (static final utility.u)
  — 所有活跃单位实例的全局注册表
  — 初始化: static final am[0] (空数组)
  — 单位创建时注册, 死亡时注销
  — 被 n/a/c.e() 遍历进行单位计数
```

---

## DequeList — `utility.o` (6KB, 32方法)

### 概述
使用两个 `utility.m` 列表实现的双端队列。支持高效的 `addFirst`/`addLast` 操作。

### 字段
```
a (utility.m)   — 前端列表
b (utility.m)   — 后端列表
c (int)         — 操作类型标记
d (Object[])    — 临时数组 (toArray缓冲)
```

### 核心方法
```
a(Object) → void  (54B)  — ★ addFirst (添加到前端)
b(Object) → void  (54B)  — ★ addLast (添加到后端)
a() → void        (177B) — ★ rebalance (重新平衡两个列表)

平衡策略: 当前端列表为空时, 从后端列表逆转元素填充
```

### 操作类型
```
"Unknown operationType:" 错误
— 表明有预定义的操作类型枚举
— 可能用于记录/撤销系统
```

### 使用场景
- 指令队列 (双端操作)
- 回放缓冲 (前后端添加)

---

## RingBuffer — `utility.g` (3KB, 24方法)

### 概述
经典的环形数组缓冲区。用于高效的 FIFO 队列操作。

### 类结构
```
extends java.util.AbstractCollection
implements java.io.Serializable, java.lang.Cloneable
```

### 字段
```
b (Object[])    — 存储数组
c (int)         — head 指针 (读取位置)
d (int)         — tail 指针 (写入位置)
a (boolean)     — 断言开关 (static final, 编译时确定)
```

### 核心方法
```
a(Object) → void    (53B) — addLast (入队)
a() → Object        (42B) — removeFirst (出队) ★
b(Object) → boolean (59B) — addFirst (前插入)
d() → void          (137B)— 扩容 (double capacity)
a(int) → boolean    (204B)— 容量检查
clone() → Object    (5B)  — 浅拷贝

错误消息: "Sorry, deque too big"
```

### 使用场景
- 网络包缓冲 (固定大小, FIFO)
- 事件队列 (高效出队)
- 渲染指令队列

---

## GameUtils — `gameFramework.f` (36KB, 128方法, 21字段)

### 概述
游戏框架的核心工具类。纯静态方法集合。128 个方法覆盖数学运算、随机数、字符串格式化、几何计算、文件IO、加密哈希。

### 关键常量
```
Random a (static final)  — 主随机数生成器
Random b (static final)  — 辅助随机数生成器

PointF c,d,e,f,g,h (static final) — 预分配的 PointF 对象 (减少GC)

float[] l,m,n,o,p,q,r,s,t,u  — ★ 三角函数查找表 (10个预计算表)
  — 避免每帧计算 sin/cos/tan
  — 包含弧度→度转换表

char[] k  — 十六进制字符表 (0123456789ABCDEF)

Integer 常量:
  4293967346  — 可能是哈希种子
  999950      — 随机数乘法器
  13131313    — 随机数加法器
  16777215    — 0xFFFFFF (RGB mask)
  133333333   — 另一个随机数常量
  1313131313  — 大随机数种子
```

### 方法分类

#### 随机数 (3参数家族)
```
a(int,int,int) → int  (166B)
  — LCG: seed = (seed * 999950 + 13131313) % range
  — 第三个参数是种子偏移量 (每个维度独立)
  — 被 n.i.a() 调用生成单位位置

a(float,float,int) → float    (19B) — 浮点随机
b(float,float,int) → float    (19B) — 浮点随机
a(float,float,float) → float  (26B) — 三点随机

a(am,int,int) → int  (8B)     — 使用单位的随机上下文
a(am,float,float,int) → float  (43B) — 单位的浮点随机
b(am,float,float,int) → float  (20B)
```

#### 几何计算
```
i(float,float) → float  (237B) ★ 复杂角度计算
j(float) → float         (14B)   — 角度归一化
k(float) → float         (14B)   — 角度归一化
c(float,float) → float   (13B)   — 距离 (2D)
d(float,float) → float   (13B)   — 距离平方
a(float,float,float,float) → float  (16B) — 距离 (4参数)
b(float,float,float,float) → float  (21B) — 距离平方
j(float,float) → bool    (18B)   — 范围检测
k(float,float) → bool    (18B)   — 范围检测
e(float,float,float) → bool (21B) — 三点范围
h(float,float) → bool    (18B)   — 精确相等检查

a(PointF,PointF,PointF,RectF) → bool  (196B) — 点投影/交叉检测
```

#### 角度/弧度
```
a(float) → float   (7B)   — 度→弧度
b(float) → float   (5B)   — 弧度→度
d(float) → float   (7B)   — 角度归一化 (-180~180)
e(float) → float   (7B)   — 角度归一化 (0~360)
f(float) → int     (22B)  — 角度→方向索引 (8方向)
```

#### 字符串格式化
```
e(int) → String          (47B)  — 整数格式化
b(double) → String       (49B)  — 浮点格式化 (精度)
g(float) → String        (6B)   — 浮点→字符串
h(float) → String        (64B)  — 字节大小格式化 "1.5 MB"
a(long) → String         (203B) — ★ 时间格式化 (秒→"1h 23m 45s")
b(long) → int[]          (55B)  — 时间→[h,m,s] 分解
b(double,int) → String   (118B) — 精度浮点格式化
```

#### 哈希/编码
```
b(byte[]) → String      (8B)    — MD5 哈希
a(byte[]) → String      (47B)   — SHA-256 哈希
c(byte[]) → byte[]      (31B)   — 字节→十六进制
f(String) → byte[]      (34B)   — 十六进制→字节 (MD5输入)
```

#### 文件/IO
```
a(File) → String        (112B)  — 读取文件内容
a(InputStream) → String (58B)   — 读取流内容
b(InputStream) → String (96B)   — 读取流 (带编码)
a(InputStream,OutputStream) → void (28B) — 流复制
```

#### 字符串工具
```
a(String) → String      (22B)   — XML转义
b(String) → String      (119B)  — HTML/XML解码
l(String) → Integer     (15B)   — 解析整数
m(String) → Long        (15B)   — 解析长整数
n(String) → boolean     (39B)   — 解析布尔
c(String,char) → String[] (171B)— 分割字符串
o(String) → String      (56B)   — 去空白
p(String) → String      (135B)  — 模板替换 "${var}"
q(String) → String      (75B)   — 模板替换 "$ {var}"
```

#### CPU/系统
```
c() → int               (65B)   — 获取CPU核心数
  — 读取 "/sys/devices/system/cpu/" (Android)
```

---

## BaseGameObject — `gameFramework.bq`

### 概述
所有游戏对象的基类。提供统一的序列化接口。

### 方法
```
<init>() → void
  — 空构造函数

a(j.as) → void  (0B!)
  — 序列化钩子 (abstract/nop)
  — 子类覆盖以实现存档/回放支持
```

### 继承链
```
bq (BaseGameObject)
├── n.f (AIWaveSystem)
├── ba (ReplayEngine)
├── bd (DataBlock)
├── d.j (BuildProgress)
└── ... 所有需要序列化的游戏对象
```

---

## LocalizedString — `custom.bb`

### 概述
多语言文本字符串。支持通过 key 查找翻译，支持参数替换。

### 字段
```
a (static final bb) — 空实例 (单例)
b (bc[])            — 翻译条目数组
c (String)          — 原始 key
d (int)             — 哈希缓存
e (String)          — 缓存的结果字符串
```

### 方法
```
a(String) → bb      (62B) — 从字符串创建 (查找翻译)
b(String) → bb      (20B) — 从字符串创建 (不翻译)
a() → boolean       (58B) — 是否有翻译内容
b() → String        (191B) — ★ 获取最终文本
  — 1. 遍历 bc[] 找到当前语言的翻译
  — 2. 应用参数替换
  — 3. 缓存结果到 e
  — 4. 如果没找到 → 返回 "<NO DEFAULT TEXT FOUND>"

a(String,String) → void (59B) — 添加翻译条目
```

### 使用场景
```
n.a.z (AITask 显示名)
n.a.A (全局消息文本)
单位名称/描述
UI文本
```

---

## 工具包全部56类概览

| 类 | 含义 | 逆向度 |
|----|------|--------|
| `utility.a` | 自定义错误类 (extends Error) | 90% |
| `utility.b` | 位运算工具 | 50% |
| `utility.c` | 颜色转换 | 50% |
| `utility.d` | 工作线程 (extends Thread) | 80% |
| `utility.e` | 事件分发/回调接口 | 50% |
| `utility.f` | 文件工具 | 50% |
| `utility.g` | **环形缓冲区** (RingBuffer) | 100% |
| `utility.h` | HashMap 扩展 | 50% |
| `utility.i` | 资源加载器 | 70% |
| `utility.j` | InputStream 包装器 | 70% |
| `utility.k` | 键值存储 | 50% |
| `utility.l` | 缓冲字符读取器 | 70% |
| `utility.m` | **自定义 ArrayList** (核心) | 100% |
| `utility.n` | ListIterator (utility.m 用) | 90% |
| `utility.o` | **双端队列** (DequeList) | 100% |
| `utility.p` | o 的前端迭代器 | 80% |
| `utility.q` | o 的后端迭代器 | 80% |
| `utility.r` | o 的视图 | 70% |
| `utility.s` | — | 30% |
| `utility.t` | — | 30% |
| `utility.u` | **全局单位注册表** (UnitRegistry) | 100% |
| `utility.v` | u 的迭代器 | 90% |
| `utility.w` | — | 30% |
| `utility.x` | 数组工具 (ArrayUtils) | 70% |
| `utility.y` | — | 30% |
| `utility.z` | — | 30% |
| 剩余30+类 | 其他工具 | 20% |

---

## 待逆向

```
- 三角函数查找表的具体精度和大小
- 随机数生成器的完整 LCG 参数
- utility 包中 s~z 的具体功能
- utility.h (HashMap扩展) 的具体实现
```
