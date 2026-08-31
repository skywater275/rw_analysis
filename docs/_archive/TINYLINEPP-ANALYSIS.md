# tinylinepp.jar 混淆类分析 — 12 个混淆类与真实身份对应

> v19.84 | 2026-08-16 | 来源: RustedWarfare/libs/tinylinepp.jar (06-lib/tinylinepp/ 已解压)


> ⚠️ **已归档** (第三方库分析类历史 (06-lib 相关, 非游戏功能域), 2026-09-04 域归档)


## 概览

tinylinepp.jar 是 **TinyLine++ SVG 渲染库** (第三方商业库, com.tinyline.*), 共 63 条目:
51 个未混淆类 (SVGParser/TinyPath/TinyPixbuf 等) + **12 个混淆类** (svg 包 3 个, tiny2d 包 9 个)。

**混淆特征 (与 RW 主代码混淆不同)**:
- 类名压成单字母 (a..i)
- 成员名压成 **Java 关键字/保留字** (`int do`, `int if`, `char[] goto`, `TinyString new`, `boolean case`...)
  — 源码层面非法, 仅字节码合法
- 调试信息完全剥离 (无 LocalVariableTable)
- 公开 API 类 (`tiny2d.i`) 与实现类 (`tiny2d.a`) 拆成门面+实现对

结论: 这是供应商对商业库的**加固混淆** (RenamingObfuscator 关键字模式),
与 RW 自身的混淆体系 (单字母类名但方法体结构可读) 完全不同。

## 混淆类 → 真实身份对照

### com.tinyline.svg (3 个)

| 混淆 | 建议名 | 角色 | 证据锚点 |
|------|--------|------|----------|
| `svg.a` | **TinyStringTokenizer** | TinyString 版 StringTokenizer | 构造 (输入串, 分隔符串, returnDelims); 分隔符扫描逻辑与 java.util.StringTokenizer 完全一致 (maxDelimCodePoint 预扫描); 默认分隔符 `" \t\n\r\f"`; 被 SVGAttr.for(char[],int,int) 以 `;` 为分隔符循环取 token |
| `svg.b` | **XMLParserImpl** | XMLParser 接口实现 (XML 词法分析) | implements XMLParser; setInputStream/setXMLHandler/getType/getError/init/getNext; 实体表 lt/gt/apos/amp/quot + CDATA/ENTITY 处理; 被 SVGParser 实例化 |
| `svg.c` | **SVGAnimationChannel** | 单属性 SMIL 动画通道 | SVGDocument 按 `do`(属性id) 分组 SVGAnimationElem: 同属性动画聚入同一通道; 按 begin 时间冒泡排序; a() 按类型求值 (case 11=变换 → TinyTransform 插值); ctor(属性id, 动画类型) |

### com.tinyline.tiny2d (9 个) — 一个完整的扫描线填充渲染簇

| `tiny2d.a` | **TinyPathFillerImpl** | 扫描线填充引擎实现 | AA 覆盖掩码 `{255,127,63,...,1}` / `{128,192,...,255}` (8x 超采样); 逐扫描线边桶表 `g[4*height+1]`; 边池 512; 定点矩阵 2^18 (262144); a(TinyPath) 填路径 / a(TinyRect) 填矩形; 裁剪 w.intersects(n) |
| `tiny2d.b` | **TinyPathFlattener** | 路径展平器 (贝塞尔→线段) | 二次/三次贝塞尔细分 (6/8 int 参数版); 4 个多边形缓冲 (c) + 4 个多边形集 (f) + 5 个控制点; 输出点链表 (g); a(TinyPath, TinyMatrix) 启动; 被 TinyPath.pathToPoints 和 a 使用 |
| `tiny2d.c` | **TinyPolygon** | 单多边形 int 对缓冲 | 平行数组 if[]/do[] (x/y); a(int,int) 压点; a()=isClosed(首尾相同); do() 自动闭合; 扩容逻辑 |
| `tiny2d.d` | **TinyGradientStop** | 渐变色标对 | TinyColor.gStops 元素: addColorStop(offset,color) 存 (a=offset, if=color); 拷贝构造 |
| `tiny2d.e` | **TinySpanNode** | 扫描线跨度节点 | 3 int + next 链; a 中 `d.for = xmin; d.a = xmax` — 逐扫描线 x 范围跨度表 |
| `tiny2d.f` | **TinyPolygonSet** | 多边形集合 | 平行数组存全部多边形坐标 (a[]/int[]) + 每多边形偏移/计数 (case[]/try[]); a(int)=某多边形 isClosed; a(xs,ys,n) 添加; do(int) 带压缩的删除; 被 TinyPath 和 b 使用 |
| `tiny2d.g` | **TinyEdgeNode** | 边记录 (定点 x 插值) | 8 int + 前后链; `dx = ((x2-x1)<<16)/(y2-y1)` 16.16 定点斜率; a(int)/a() 逐行步进 x (case += dx, char = (case+32768)>>16); 兼作 b 的输出点节点 |
| `tiny2d.h` | **TinyHashNode** | 哈希表节点 | Object a=键, Object do=值, h if=next 链; TinyHash 构造分配 h[] 数组 |
| `tiny2d.i` | **TinyPainter** | 公开绘图门面 (SVG 元素 paint 的统一入口) | public final, 纯委托 tiny2d.a; SVGNode/SVGPathElem/SVGGroupElem 均经 `SVGRaster.if` 字段持有并使用: a(TinyMatrix) 设变换 / a(TinyColor)+if(TinyColor) 设填充+描边色 / a(int[]) 虚线 / a(TinyRect) 填矩形 / a(TinyPath) 填路径 / a(Z) 抗锯齿开关 |

### 渲染管线还原

```
SVGPathElem.paint(SVGRaster)
  → SVGRaster.if (TinyPainter / tiny2d.i)        设置绘图状态 (变换/颜色/虚线/裁剪)
    → TinyPainter.a(TinyPath) 填路径
      → TinyPathFillerImpl (tiny2d.a)           扫描线填充
        → TinyPathFlattener (tiny2d.b)          路径→多边形 (贝塞尔展平)
          → TinyPolygon (c) / TinyPolygonSet (f) 输出多边形
        → TinyEdgeNode (g) 边表 + TinySpanNode (e) 跨度 → AA 覆盖合成

## 与项目的关系

- game-lib.jar 全部类 + 02-decompiled/03-deobfuscated 中 **无任何 com.tinyline 引用**
- tinylinepp.jar 在 libs/ 中属于**潜在运行时依赖或历史遗留** (v1.15 代码未直接使用)

## 命名置信度说明

- **高**: 角色与数据流由反编译代码直接证实 (CFR 0.152 全量反编译于分析期, 临时产物已清理)
- **中 (描述性)**: 上游 TinyLine++ 官网 API 文档无法访问 (网络封锁), 类名按 TinyXxx 命名惯例
  推导 — tiny2d.i/tiny2d.a 的真实商品名可能是 TinyPathFiller 系列, 待上游文档确认后修订

## 建议

若需要将这些类纳入 RW 解混淆产物 (04-javas 风格), 可按上表生成
`06-lib/tinylinepp-deobfuscated/` 反混淆副本; 鉴于该库未被游戏引用, 优先级低。
