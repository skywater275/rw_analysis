/*
 * ★ 2026-10-01 第 61 轮（PENDING RS-36）重建：枚举常量的 name() 即 INI 字符串。
 * stock 的 <clinit> 逐条 `new X; dup; ldc "INI串"; iconst_N; invokespecial X.<init>:(String,int)`
 * ⇒ 常量由 (String,int) 直接构造、字符串是 INI 键、字段名才是混淆名。
 * 故写成「INI 字符串作常量名」⇒ javac 生成 (String,int) 且保留字符串 ✓；
 * 常量名由 build_reverse_jar.rename_enum_constants 按 supplement 改成混淆字段名。
 */
package com.corrodinggames.rts.gameFramework;

public enum GamePhase {
    total,
    update,
    draw,
    draw_game,
    draw_end,
    draw_gui,
    draw_game_effects,
    update_game_shouldDraw,
    update_game_sortRender,
    update_do_all_collisions,
    update_do_all_collisions2,
    update_all_team_and_ai,
    update_geo_indexes,
    update_minimap,
    update_groupcontroller,
    draw_game_unit,
    draw_setup,
    draw_setup_fill,
    draw_setup_clip,
    draw_setup_drawMap,
    surface_draw,
    realdraw_in_drawthread,
    update_waiting_on_draw,
    draw_waiting_on_update,
    load_total,
    load_map,
    load_units,
    load_compression,
    init_total,
    init_unitcolour;
}
