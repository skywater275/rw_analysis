/*
 * Decompiled with CFR 0.152.
 * 02 原稿: com.corrodinggames.rts.game.g (195行) — 游戏设置单例 (v19.107 重建)
 * 03 落盘: game/GameSettings.java (03 曾缺失; bh.java 等已引用)
 */
package com.corrodinggames.rts.game;

import android.graphics.Color;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bi;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.CustomVisuals;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.io.IOException;

public class GameSettings {
    public static final GameSettings a = new GameSettings();
    public int b;
    public int c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i = 35;
    public float j = -1.0f;
    public boolean k = false;
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;
    public float o = 1.0f;
    public boolean p = false;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;
    public float u;
    public float v;
    public float w = 5.0f;
    public short x = (short)-1;
    public short y;
    public short z = (short)-1;
    public boolean A;
    public Texture B;
    public Texture C;
    public float D;
    public float E;
    public float F;
    public float G;
    public float H;
    public boolean I = false;
    public boolean J = false;
    public float K = -1.0f;
    public boolean L = false;
    public boolean M = false;
    public boolean N = false;
    public float O = -1.0f;
    public float P = -1.0f;
    public float Q;
    public float R;
    public float S;
    public boolean T;
    public boolean U = false;
    public boolean V = false;
    public boolean W = false;
    public boolean X = false;
    public Texture Y;
    public Texture Z;
    public boolean aa;
    public Texture ab;
    public boolean ac;
    public float ad;
    public boolean ae = false;
    public boolean af = false;
    public float ag = 3.0f;
    public CustomVisuals ah;
    public CustomVisuals ai;
    public bi aj;
    public bi ak;
    public bi al;
    public float am;
    public float an = 5.0f;
    public int ao = -1;
    public float ap = 0.5f;
    public boolean aq;
    public boolean ar = false;
    public float as = -1.0f;
    public float at = -1.0f;
    public float au = -1.0f;
    public float av = 0.1f;
    public boolean aw = false;
    public float ax = 120.0f;
    public float ay = 15.0f;
    public boolean az;
    public float aA = 5.0f;
    public float aB = 120.0f;
    public float aC = 15.0f;
    public com.corrodinggames.rts.game.units.custom.UnitConfig aD;  // 02b g.java L97: custom.h (TagFilter 为幻觉名)
    public int aE = Color.a(255, 255, 255, 255);
    public float aF = 1.0f;
    public float aG = 0.0f;
    public float aH = 1.0f;
    public boolean aI;
    public boolean aJ = true;
    public float aK;
    public float aL;
    public float aM;
    public boolean aN;
    public boolean aO;
    public float aP;
    public float aQ;
    public float aR = 1.0f;
    public float aS = 1.0f;
    public float aT = 1.0f;
    public float aU = 1.0f;
    public float aV;
    public float aW = -1.0f;
    public CustomVisuals aX;
    public CustomVisuals aY;
    public bp aZ;
    public int ba;
    public boolean bb;
    public boolean bc;
    public UnitConfig bd;  // 02b game/g.java javap: public custom.h bd (TagFilter 为幻觉)
    public CustomArrayList be = null;
    public CustomArrayList bf = null;
    public CustomArrayList bg = null;

    public CustomVisuals getVisualOverride(UnitInstance am2) {
        CustomArrayList m2 = this.bg;
        if (m2 != null && m2.a > 0) {
            for (Object h2obj : m2) {
                TagFilter h2 = (TagFilter) h2obj;
                if (!h2.a(am2) || h2.isActive == null) continue;
                return h2.isActive;
            }
        }
        return null;
    }

    public float applyMultipliers(UnitInstance am2, float f, boolean bl) {
        CustomArrayList m2 = !bl ? this.be : this.bf;
        if (m2 != null && m2.a > 0) {
            for (Object h2obj : m2) {
                TagFilter h2 = (TagFilter) h2obj;
                float f2;
                if (!h2.a(am2)) continue;
                if (!bl) {
                    if (h2.excludeFilter != null) {
                        h2.excludeFilter.h(am2);
                    }
                    f2 = h2.matchType;
                } else {
                    if (h2.fallback != null) {
                        h2.fallback.h(am2);
                    }
                    f2 = h2.inclusiveFilter;
                }
                f *= f2;
            }
        }
        return f;
    }

    public static void writeOutLink(GameSettings g2, OutputNetStream as2) {
        if (g2 == a) {
            as2.c(0);
            return;
        }
        if (g2 instanceof bh) {
            as2.c(1);
            bh.a((bh)g2, as2);
            return;
        }
        GlobalState.isKeyJustPressed("writeOutLink: Unhandled projectile type");
        as2.c(0);
    }

    public static GameSettings readFromStream(InputNetStream k2) throws IOException {
        byte by = k2.d();
        if (by == 0) {
            return a;
        }
        if (by == 1) {
            GameSettings g2 = bh.b(k2);
            if (g2 == null) {
                return a;
            }
            return g2;
        }
        throw new IOException("Unknown projectile type:" + by);
    }

    public static void a(GameSettings g2, com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {  // 02b game/g.java L182: a(g,as) 写侧
        if (g2 == a) {
            as2.c(0);
        } else if (g2 instanceof com.corrodinggames.rts.game.units.custom.bh) {
            as2.c(1);
            com.corrodinggames.rts.game.units.custom.bh.a((com.corrodinggames.rts.game.units.custom.bh)g2, as2);
        } else {
            com.corrodinggames.rts.gameFramework.GlobalState.g("writeOutLink: Unhandled projectile type");
            as2.c(0);
        }
    }
}
