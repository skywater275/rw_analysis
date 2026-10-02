/*
 * ★ 2026-10-01 第 52 轮（PENDING RS-27 续）重建：枚举常量的 name() 即 INI 字符串。
 * 原文件保留了 decompiler 的自定义构造器 ⇒ javac 合成 (String,int,String,int) ✗；
 * 改成「纯名常量（= INI 字符串）」后 javac 生成 (String,int) ✓ 且保留字符串 ✓；
 * 常量名由 build_reverse_jar.rename_enum_constants 按 supplement 改成混淆字段名；
 * **常量区之后的原有成员一律原样保留** ✓。
 */
package com.corrodinggames.rts.game.units.actions;

public enum ActionCategory {
    none,
    rally,
    upgrade,
    queueUnit,
    building,
    action,
    infoOnly,
    infoOnlyNoBox,
    infoOnlyStockpile;
}
