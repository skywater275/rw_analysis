/*
 * ★ 2026-10-01 第 61 轮（PENDING RS-36）重建：枚举常量的 name() 即 INI 字符串。
 * stock 的 <clinit> 逐条 `new X; dup; ldc "INI串"; iconst_N; invokespecial X.<init>:(String,int)`
 * ⇒ 常量由 (String,int) 直接构造、字符串是 INI 键、字段名才是混淆名。
 * 故写成「INI 字符串作常量名」⇒ javac 生成 (String,int) 且保留字符串 ✓；
 * 常量名由 build_reverse_jar.rename_enum_constants 按 supplement 改成混淆字段名。
 */
package com.corrodinggames.rts.game.ai;

public enum BaseZoneType {
    Pre,
    Prepare,
    Active;








    int getOrdinal() {
        return this.ordinal();
    }
}
