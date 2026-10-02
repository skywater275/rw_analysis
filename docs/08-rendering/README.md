# 08-rendering — 渲染系统

> **职责**：OpenGL 渲染链、贴图图集、特效、相机

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **712** 条 |
| 涉及类 | **57** 个 |
| 有可读名 | **48** 个（84%）|
| ★ **已替换为我们的字节** | **46** 个（81%）|
| 主包 | `com.corrodinggames.rts.gameFramework.m` · `com.corrodinggames.rts.gameFramework.d` · `com.corrodinggames.rts.gameFramework.b` · `com.corrodinggames.rts.gameFramework.m.e` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **46** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 7 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 4 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.gameFramework.m` | 401 |
| `com.corrodinggames.rts.gameFramework.d` | 202 |
| `com.corrodinggames.rts.gameFramework.b` | 81 |
| `com.corrodinggames.rts.gameFramework.m.e` | 24 |
| `gameFramework.m.e` | 3 |
| `gameFramework.effects` | 1 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/gameFramework` | 95 | 11769 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/gameFramework/mods` | 3 | 1139 |
| `com` | 6 | 1080 |
| `com/corrodinggames/rts` | 13 | 658 |

## 三、★ 全量类清单（57 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `f` | **DrawEffect** | 45 | 7 | **我们的字节** ✓ |
| `e` | **Texture** | 22 | 20 | 原版字节 |
| `n` | **GLRenderer** | 17 | 23 | **我们的字节** ✓ |
| `a` | **GameHUD** | 31 | 9 | **我们的字节** ✓ |
| `e` | **HUDElement** | 35 | 5 | **我们的字节** ✓ |
| `x` | **TextureManager** | 28 | 7 | **我们的字节** ✓ |
| `p` | **EffectConfig** | 28 | 4 | **我们的字节** ✓ |
| `y` | **TextureManagerInterface** | 0 | 30 | **我们的字节** ✓ |
| `c` | **HUDManager** | 20 | 9 | **我们的字节** ✓ |
| `ac` | **GL10Renderer** | 20 | 2 | 原版字节 |
| `ae` | **Shader** | 17 | 4 | **我们的字节** ✓ |
| `k` | **BitmapDrawer** | 15 | 6 | **我们的字节** ✓ |
| `f` | — | 20 | 0 | 未进交付物 |
| `f` | **Sprite** | 10 | 10 | **我们的字节** ✓ |
| `g` | **HUDElementRenderer** | 14 | 4 | **我们的字节** ✓ |
| `h` | **TeamColorTexture** | 12 | 6 | **我们的字节** ✓ |
| `n` | **NullRenderer** | 1 | 16 | **我们的字节** ✓ |
| `aa` | **FontRenderer** | 10 | 4 | **我们的字节** ✓ |
| `o` | **DrawContext** | 11 | 2 | **我们的字节** ✓ |
| `b` | **GLObject** | 5 | 7 | **我们的字节** ✓ |
| `h` | **DrawLayer** | 6 | 5 | **我们的字节** ✓ |
| `af` | **ShaderUniform** | 7 | 3 | **我们的字节** ✓ |
| `a` | **OpenGLRenderer** | 7 | 2 | **我们的字节** ✓ |
| `ah` | **GLTexture** | 7 | 1 | 原版字节 |
| `ag` | **UniquePaint** | 3 | 5 | **我们的字节** ✓ |
| `b` | **GLTextureRegion** | 7 | 1 | **我们的字节** ✓ |
| `j` | **CanvasRenderer** | 2 | 6 | **我们的字节** ✓ |
| `s` | — | 7 | 1 | **我们的字节** ✓ |
| `g` | **TextureFrame** | 6 | 1 | **我们的字节** ✓ |
| `b` | **CloudRenderer** | 0 | 6 | 原版字节 |
| `d` | **HUDAnchor** | 2 | 4 | **我们的字节** ✓ |
| `ad` | **TextureProxy** | 1 | 5 | **我们的字节** ✓ |
| `d` | **ShapeType** | 2 | 4 | **我们的字节** ✓ |
| `w` | — | 2 | 4 | **我们的字节** ✓ |
| `z` | **NullSpriteBatchBackend** | 1 | 5 | **我们的字节** ✓ |
| `ab` | — | 3 | 2 | **我们的字节** ✓ |
| `i` | **FileShader** | 1 | 4 | **我们的字节** ✓ |
| `l` | **Renderer** | 0 | 5 | **我们的字节** ✓ |
| `q` | **ObjectPool** | 3 | 2 | **我们的字节** ✓ |
| `t` | **t** | 2 | 3 | **我们的字节** ✓ |
| `al` | **DrawCallBuffer** | 1 | 3 | **我们的字节** ✓ |
| `n` | — | 0 | 4 | 未进交付物 |
| `ai` | **TextureConfig** | 3 | 0 | 原版字节 |
| `q` | **GLUniform** | 3 | 0 | **我们的字节** ✓ |
| `v` | **LineStyle** | 3 | 0 | **我们的字节** ✓ |
| `m` | **DrawCommand** | 0 | 3 | **我们的字节** ✓ |
| `e` | — | 0 | 3 | 未进交付物 |
| `e` | **BitmapTexture** | 1 | 1 | **我们的字节** ✓ |
| `i` | **BlurEffect** | 2 | 0 | **我们的字节** ✓ |
| `w` | **IntStack** | 2 | 0 | **我们的字节** ✓ |
| `c` | — | 2 | 0 | **我们的字节** ✓ |
| `v` | **CustomColorFilter** | 1 | 1 | **我们的字节** ✓ |
| `o` | **AttributeLocation** | 0 | 1 | 原版字节 |
| `s` | **UniformLocation** | 0 | 1 | 原版字节 |
| `r` | — | 0 | 1 | **我们的字节** ✓ |
| `u` | **RenderBatch** | 0 | 1 | **我们的字节** ✓ |
| `f` | — | 0 | 1 | 未进交付物 |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `f`（DrawEffect） | 52 | `blendMode(f)` · `burstCount(f)` · `burstInterval(f)` · `burstTimer(f)` · `collideWithMap(f)` · `effectBlendColor(f)` · `effectDuration(f)` · `effectIntensity(f)` · `effectType(f)` … |
| `e`（Texture） | 42 | `bitmap(f)` · `clearPixelData(m)` · `clearTeamVariants(m)` · `copy(m)` · `dataGeneration(f)` · `ensurePixelData(m)` · `getCount(m)` · `getHeight(m)` · `getName(m)` … |
| `n`（GLRenderer） | 40 | `beginBatch(m)` · `bindFramebuffer(m)` · `boundTexture(f)` · `checkGLError(m)` · `deleteTexture(m)` · `endBatch(m)` · `field_am(f)` · `field_an(f)` · `field_ao(f)` … |
| `a`（GameHUD） | 40 | `autoScroll(f)` · `currentPlayer(f)` · `damageDisplayTimer(f)` · `displayMode(f)` · `hoveredUnitIndex(f)` · `hudInstance(f)` · `hudScaleX(f)` · `hudScaleY(f)` · `hudTextSize(f)` … |
| `e`（HUDElement） | 40 | `anchorBottomLeft(f)` · `anchorCenter(f)` · `anchorCenterLeft(f)` · `anchorCenterRight(f)` · `anchorMode(f)` · `anchorTopCenter(f)` · `anchorTopLeft(f)` · `anchorTopRight(f)` · `attachedObject(f)` … |
| `x`（TextureManager） | 35 | `a_3p(m)` · `activeTexture(f)` · `clipRect(f)` · `clipRectF(f)` · `context(f)` · `currentTexture(f)` · `drawRectF(f)` · `dstRect(f)` · `dstRectF(f)` … |
| `p`（EffectConfig） | 32 | `effectTypeA(f)` · `effectTypeB(f)` · `effectTypeC(f)` · `effectTypeD(f)` · `effectTypeE(f)` · `effectTypeF(f)` · `effectTypeG(f)` · `effectTypeH(f)` · `effectTypeI(f)` … |
| `y`（TextureManagerInterface） | 30 | `bindShader(m)` · `clearAll(m)` · `clearScreen(m)` · `clipRect(m)` · `clipRectF(m)` · `createRenderTarget(m)` · `createRendererForTexture(m)` · `createRendererForTextureDirty(m)` · `flush(m)` … |

## 五、映射备注（项目分析结论 ✓）

- **`ah`**（GLTexture）：Cached bitmap edge pixels (static HashMap)
- **`ah`**（GLTexture）：GL content in sync with bitmap (private boolean)
- **`ah`**（GLTexture）：GL texture filter mode (9729=GL_LINEAR,  package-private int)
- **`ah`**（GLTexture）：Pixel padding at texture edges (private int)
- **`ai`**（TextureConfig）：GL filter mode (int)
- **`ai`**（TextureConfig）：GL wrap mode (int)
- **`ai`**（TextureConfig）：Generate mipmaps flag (boolean)
- **`al`**（DrawCallBuffer）：Parent DrawCall reference (final aj)
- **`al`**（DrawCallBuffer）：[V] b()->void; upload vertex buffer to GPU (al.java:37)
- **`al`**（DrawCallBuffer）：real-class-inferred: com.corrodinggames.rts.gameFramework.b.al
- **`b`**（GLObject）：Dirty/invalidation flag (boolean)
- **`b`**（GLObject）：GLRenderer reference (k = GLRenderer)
- **`b`**（GLObject）：Texture coordinate U scale (1.0/textureWidth)
- **`b`**（GLObject）：Texture coordinate V scale (1.0/textureHeight)

## 核心概念

**渲染系统**：OpenGL 渲染链、贴图、图集、粒子、特效。

| 组成 | 说明 |
|---|---|
| **渲染器** | 绘制批次、着色器、变换栈 |
| **贴图/图集** | 纹理加载、图集打包、队伍色变体 |
| **精灵** | 单位/建筑绘制（含动画帧） |
| **特效** | 爆炸、烟雾、弹道、伤害数字 |
| **相机** | 平移/缩放/裁剪 |

## 关键机制

1. **批次合并**：同材质合并绘制 ⇒ 千单位可流畅 ✓
2. **图集**：把大量小图打包 ⇒ 减少纹理切换
3. **队伍色变体**：同一贴图按队伍染色（缓存变体 ✓）
4. **渲染目标**：离屏纹理（用于小地图/后处理）
5. **GL 资源生命周期**：创建/上传/释放（注意内存与上下文）

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 加特效 | 特效类 + INI 引用 ✓ |
| 改绘制 | 渲染类（**改后必跑 V5 真窗口** ✓） |
| 贴图/图集 | 资源文件 + 图集配置 ✓ |


## 六、子文档

- [`RENDERING.md`](RENDERING.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`RENDERING.md`](RENDERING.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
