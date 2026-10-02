/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import android.graphics.Color;
import com.corrodinggames.rts.game.h;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bi;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.io.IOException;

public class g {
    public static final g a = new g();
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
    public e B;
    public e C;
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
    public e Y;
    public e Z;
    public boolean aa;
    public e ab;
    public boolean ac;
    public float ad;
    public boolean ae = false;
    public boolean af = false;
    public float ag = 3.0f;
    public z ah;
    public z ai;
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
    public com.corrodinggames.rts.game.units.custom.h aD;
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
    public z aX;
    public z aY;
    public bp aZ;
    public int ba;
    public boolean bb;
    public boolean bc;
    public com.corrodinggames.rts.game.units.custom.h bd;
    public m be = null;
    public m bf = null;
    public m bg = null;

    public z a(am am2) {
        m m2 = this.bg;
        if (m2 != null && m2.a > 0) {
            for (h h2 : m2) {
                if (!h2.a(am2) || h2.g == null) continue;
                return h2.g;
            }
        }
        return null;
    }

    public float a(am am2, float f, boolean bl) {
        m m2 = !bl ? this.be : this.bf;
        if (m2 != null && m2.a > 0) {
            for (h h2 : m2) {
                float f2;
                if (!h2.a(am2)) continue;
                if (!bl) {
                    if (h2.e != null) {
                        h2.e.h(am2);
                    }
                    f2 = h2.c;
                } else {
                    if (h2.f != null) {
                        h2.f.h(am2);
                    }
                    f2 = h2.d;
                }
                f *= f2;
            }
        }
        return f;
    }

    public static void a(g g2, as as2) {
        if (g2 == a) {
            as2.c(0);
            return;
        }
        if (g2 instanceof bh) {
            as2.c(1);
            bh.a((bh)g2, as2);
            return;
        }
        com.corrodinggames.rts.gameFramework.l.g("writeOutLink: Unhandled projectile type");
        as2.c(0);
    }

    public static g a(k k2) {
        byte by = k2.d();
        if (by == 0) {
            return a;
        }
        if (by == 1) {
            g g2 = bh.b(k2);
            if (g2 == null) {
                return a;
            }
            return g2;
        }
        throw new IOException("Unknown projectile type:" + by);
    }
}
