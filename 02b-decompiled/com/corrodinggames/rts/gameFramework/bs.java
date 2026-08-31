package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.br$1;

public enum bs {

   a("total", 0),
   b("update", 1),
   c("draw", 2),
   d("draw_game", 3),
   e("draw_end", 4),
   f("draw_gui", 5),
   g("draw_game_effects", 6),
   h("update_game_shouldDraw", 7),
   i("update_game_sortRender", 8),
   j("update_do_all_collisions", 9),
   k("update_do_all_collisions2", 10),
   l("update_all_team_and_ai", 11),
   m("update_geo_indexes", 12),
   n("update_minimap", 13),
   o("update_groupcontroller", 14),
   p("draw_game_unit", 15),
   q("draw_setup", 16),
   r("draw_setup_fill", 17),
   s("draw_setup_clip", 18),
   t("draw_setup_drawMap", 19),
   u("surface_draw", 20),
   v("realdraw_in_drawthread", 21),
   w("update_waiting_on_draw", 22),
   x("draw_waiting_on_update", 23),
   y("load_total", 24),
   z("load_map", 25),
   A("load_units", 26),
   B("load_compression", 27),
   C("init_total", 28),
   D("init_unitcolour", 29);
   // $FF: synthetic field
   private static final bs[] E = new bs[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D};


   private bs(String var1, int var2) {}

   // $FF: synthetic method
   bs(String var1, int var2, br$1 var3) {
      this(var1, var2);
   }

}
