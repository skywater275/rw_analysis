/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.units.custom.bf;
import com.corrodinggames.rts.game.units.custom.bg;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.m;

public final class be {
    m a = new m();
    m b = new m();
    boolean c;
    boolean d;
    public static final bf e = new bf();

    public static be a(l l2, ab ab2) {
        be be2 = new be();
        be2.b(l2, ab2);
        if (be2.b.size() == 0) {
            return null;
        }
        for (g g2 : be2.a) {
            if (g2 == null) continue;
            int n2 = 0;
            bg bg2 = null;
            for (bg bg3 : be2.b) {
                if (bg3.b != g2) continue;
                ++n2;
                bg2 = bg3;
            }
            if (n2 != true) continue;
            l2.r("[placementRule_" + bg2.a + "]anyRuleInGroup: No other rule with this same group name found");
        }
        return be2;
    }

    public void b(l l2, ab ab2) {
        for (String string : ab2.e("placementRule_")) {
            String string2 = string.substring("placementRule_".length());
            bg bg2 = new bg();
            bg2.a = string2;
            bg2.a(l2, ab2, string);
            if (!bg2.a()) continue;
            if (!this.a.contains(bg2.b)) {
                this.a.add(bg2.b);
            }
            if (bg2.n) {
                if (!bg2.p) {
                    this.c = true;
                } else {
                    this.d = true;
                }
            }
            this.b.add(bg2);
        }
    }

    public String a(y y2, float f2, float f3) {
        if (!this.c) {
            return null;
        }
        return this.b(y2, f2, f3);
    }

    public String a(y y2, int n2, int n3) {
        if (!this.d) {
            return null;
        }
        b b2 = com.corrodinggames.rts.gameFramework.l.B().bL;
        b2.b(n2, n3);
        return this.b(y2, b2.T, b2.U);
    }

    public String b(y y2, float f2, float f3) {
        for (g g2 : this.a) {
            boolean bl = false;
            boolean bl2 = false;
            bg bg2 = null;
            for (bg bg3 : this.b) {
                if (bg3.b != g2 || !bg3.n) continue;
                boolean bl3 = be.a(y2, bg3, f2, f3);
                if (!bl3) {
                    if (bg2 == null) {
                        bg2 = bg3;
                    }
                    bl2 = true;
                    continue;
                }
                bl = true;
            }
            boolean bl4 = g2 == null ? !bl2 : bl;
            if (bl4 || bg2 == null) continue;
            if (bg2.o != null) {
                return bg2.o.b();
            }
            return "{0}";
        }
        return null;
    }

    private static boolean a(y y2, bg bg2, float f2, float f3) {
        be.e.a = f2 + bg2.g;
        be.e.b = f3 + bg2.h;
        be.e.c = bg2;
        be.e.d = 0;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.cc.a(be.e.a, be.e.b, bg2.e, y2, 0.0f, e);
        return be.e.d >= bg2.k && be.e.d <= bg2.l;
    }
}
