/*
 * ★ 2026-10-01 第 47/49 轮（PENDING RS-27/RS-28）重建：枚举常量的 name() 即 INI 字符串。
 * stock 的 <clinit> 逐条 `new X; dup; ldc "INI串"; iconst_N; invokespecial X.<init>:(String,int)`
 * ⇒ 常量由 (String,int) 直接构造、字符串是 INI 键、字段名才是混淆名。
 * 本文件写成「纯名常量（= INI 字符串）」⇒ javac 生成 (String,int) 且保留字符串 ✓；
 * 常量名由 build_reverse_jar.rename_enum_constants 按 supplement 改成混淆字段名；
 * **常量区之后的原有成员一律原样保留** ✓（第 49 轮修正：整文件覆盖会丢成员 ✗）。
 */
package com.corrodinggames.rts.game.units.actions;

public enum PingType {
    normal,
    attack,
    defend,
    nuke,
    build,
    upgrade,
    ok,
    no,
    happy,
    sad,
    retreat;




    public String a() {
        return " - " + this.b();
    }

    public String b() {
        return com.corrodinggames.rts.gameFramework.steam.Localization.a(this.c(), new Object[0]);
    }

    public String c() {
        return "menus.ingame.ping.type." + this.name();
    }
}
