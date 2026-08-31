# 混淆器识别与反向推导 (v19.110)

> 2026-08-20 | 分析工具: tools/analyze/obfuscation_fingerprint.py (10 条指纹全 ✓)


> ⚠️ **已归档** (混淆器分析类历史, 方法论已由 METHODOLOGY.md 承接, 2026-09-04 域归档)


## 判定: ProGuard

| 指纹 | 证据 | 排除 |
|------|------|------|
| F1 名字工厂 | 73 个短名类 **字典序 0 空隙** (a..z → aa..az → ba..bd) = SimpleNameFactory 顺序分配 | 自定义混淆器 |
| F2 小写模式 | 1,216 个混淆短名全小写 (R.class 为 keep 例外) | — |
| F3 类-包同名 | **46 处** (类 a/a 与包 a/a/ 并存) = 类名/包名独立混淆碰撞 | — |
| F4 SourceFile | 统一值 "SourceFile" (非原文件名) + LineNumberTable 保留 | — |
| F5 字符串明文 | 游戏日志全明文 (无解密调用) | ZKM/Allatori 字符串加密 |
| F6 keep 范围 | android.* 全保留 (68 类兼容层) + Main + librocket + R | — |
| F7 内部类 | 693 个 $N 内部类 + ACC_SYNTHETIC 桥保留 (编译器产物未清理) | 激进压缩器 |
| F8 混淆率 | 类名 72% (1,229/1,698), 方法/字段名单字母 | — |
| F9 包结构 | com.corrodinggames.rts 前缀保留 + 顶层 a/ 包 (30 类压平产物) | — |
| F10 成员命名 | 方法名按原始名首次出现序分配, 重载共享字母 | — |

## 配置推断 (反向推导)

```
-dontusemixedcaseclassnames              # 短名纯小写
-renamesourcefileattribute SourceFile    # 统一 SourceFile 值
-keepattributes SourceFile,LineNumberTable
-keep class com.corrodinggames.rts.java.Main   # MANIFEST Main-Class
-keep class android.**                  # 桌面版兼容层
-keep class com.corrodinggames.librocket.**    # UI 脚本引擎 (第三方)
-keep class com.corrodinggames.rts.R          # 资源 ID
# 包名混淆开启 (46 碰撞) + 部分类压平到顶层 a/ (flatten/repackage)

## 反向推导规律 (已利用)

1. **方法名字母组 = 原始声明序**: ProGuard 按原始方法名首次出现顺序分配 a,b,c...,
   重载共享字母。铁证: gameFramework.f ↔ GameUtils 124 方法保序, 99 同名 +
   25 语义化差异全对齐 (g→formatBytes, i→smoothstep, j→sinFast...)。
2. **字段保序 zip**: 同规律字段版, 已用于 patch 管线 (fix_patch_expand.field_zip),
   修复 NoSuchFieldError: a/b 崩溃。
3. **keep 规则反推**: 未混淆类 = keep 目标 → 03 语义名直接可用 (android/librocket/Main)。
4. **类名处理序不可反推**: a.a 测试族字母序与语义无相关性 (由构建打包顺序决定, 信息已丢)。

## 工具

| 工具 | 状态 |
|------|------|
| tools/analyze/obfuscation_fingerprint.py | ✅ 10 指纹全通过 |
| tools/fixers/fix_order_align.py | ⚠️ 原型 (配对 56 类, 建议含错位噪声 — 需 javap 签名级校验后启用; A 类建议未写入 supplement) |

## PENDING

- fix_order_align: 02 CFR 丢方法导致提取序错位 → 改用 **javap 方法表** (完整保序) +
  签名参数类型匹配做对齐, 消除噪声后可批量补全 supplement
- 46 类-包同名中的压平类 (顶层 a/ 30 类) 原包归属: 可用引用图聚类反推
  (a.a 包已破译为测试族+可靠UDP栈, 其余待做)
