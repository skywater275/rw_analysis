package com.corrodinggames.rts.gameFramework;

// 02b gameFramework/bs.java 直译: 30 常量纯 enum (a("total")...D("init_unitcolour"))
public enum GamePhase {
    a("total"),
    b("update"),
    c("draw"),
    d("draw_game"),
    e("draw_end"),
    f("draw_gui"),
    g("draw_game_effects"),
    h("update_game_shouldDraw"),
    i("update_game_sortRender"),
    j("update_do_all_collisions"),
    k("update_do_all_collisions2"),
    l("update_all_team_and_ai"),
    m("update_geo_indexes"),
    n("update_minimap"),
    o("update_groupcontroller"),
    p("draw_game_unit"),
    q("draw_setup"),
    r("draw_setup_fill"),
    s("draw_setup_clip"),
    t("draw_setup_drawMap"),
    u("surface_draw"),
    v("realdraw_in_drawthread"),
    w("update_waiting_on_draw"),
    x("draw_waiting_on_update"),
    y("load_total"),
    z("load_map"),
    A("load_units"),
    B("load_compression"),
    C("init_total"),
    D("init_unitcolour");

    private GamePhase(String string) {
    }
}
