/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.br$1;
import com.corrodinggames.rts.gameFramework.bs$1;
import com.corrodinggames.rts.gameFramework.bs$2;
import com.corrodinggames.rts.gameFramework.bs$3;
import com.corrodinggames.rts.gameFramework.bs$4;

public class bs
extends Enum {
    public static final /* enum */ bs a = new bs$1();
    public static final /* enum */ bs b = new bs$2();
    public static final /* enum */ bs c = new bs$3();
    public static final /* enum */ bs d = new bs$4();
    public static final /* enum */ bs e = new bs("draw_end", 4);
    public static final /* enum */ bs f = new bs("draw_gui", 5);
    public static final /* enum */ bs g = new bs("draw_game_effects", 6);
    public static final /* enum */ bs h = new bs("update_game_shouldDraw", 7);
    public static final /* enum */ bs i = new bs("update_game_sortRender", 8);
    public static final /* enum */ bs j = new bs("update_do_all_collisions", 9);
    public static final /* enum */ bs k = new bs("update_do_all_collisions2", 10);
    public static final /* enum */ bs l = new bs("update_all_team_and_ai", 11);
    public static final /* enum */ bs m = new bs("update_geo_indexes", 12);
    public static final /* enum */ bs n = new bs("update_minimap", 13);
    public static final /* enum */ bs o = new bs("update_groupcontroller", 14);
    public static final /* enum */ bs p = new bs("draw_game_unit", 15);
    public static final /* enum */ bs q = new bs("draw_setup", 16);
    public static final /* enum */ bs r = new bs("draw_setup_fill", 17);
    public static final /* enum */ bs s = new bs("draw_setup_clip", 18);
    public static final /* enum */ bs t = new bs("draw_setup_drawMap", 19);
    public static final /* enum */ bs u = new bs("surface_draw", 20);
    public static final /* enum */ bs v = new bs("realdraw_in_drawthread", 21);
    public static final /* enum */ bs w = new bs("update_waiting_on_draw", 22);
    public static final /* enum */ bs x = new bs("draw_waiting_on_update", 23);
    public static final /* enum */ bs y = new bs("load_total", 24);
    public static final /* enum */ bs z = new bs("load_map", 25);
    public static final /* enum */ bs A = new bs("load_units", 26);
    public static final /* enum */ bs B = new bs("load_compression", 27);
    public static final /* enum */ bs C = new bs("init_total", 28);
    public static final /* enum */ bs D = new bs("init_unitcolour", 29);
    private static final /* synthetic */ bs[] E;

    public static bs[] values() {
        return (bs[])E.clone();
    }

    public static bs valueOf(String string) {
        return Enum.valueOf(bs.class, string);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private bs() {
        void var2_-1;
        void var1_-1;
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    /* synthetic */ bs(br.1 varnull) {
        this((String)var1_-1, (int)var2_1);
        void var2_1;
        void var1_-1;
    }

    static {
        E = new bs[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D};
    }
}
