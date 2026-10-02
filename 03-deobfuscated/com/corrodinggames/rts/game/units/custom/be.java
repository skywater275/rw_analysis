/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.map.MapEngine;
import com.corrodinggames.rts.game.units.custom.bf;
import com.corrodinggames.rts.game.units.custom.bg;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public final class be {
    CustomArrayList a = new CustomArrayList();
    CustomArrayList b = new CustomArrayList();
    boolean c;
    boolean d;
    public static final bf e = new bf();

    public static be a(ModUnitRegistry l2, ab ab2) throws bo {
        be be2 = new be();
        be2.b(l2, ab2);
        if (be2.b.size() == 0) {
            return null;
        }
        for (TeamTag g2 : (java.util.Collection<TeamTag>) (java.util.Collection) be2.a) {
            if (g2 == null) continue;
            int n2 = 0;
            bg bg2 = null;
            for (bg bg3 : (java.util.Collection<bg>) (java.util.Collection) be2.b) {
                if (bg3.b != g2) continue;
                ++n2;
                bg2 = bg3;
            }
            if (n2 != 1) continue;
            l2.r("[placementRule_" + bg2.a + "]anyRuleInGroup: No other rule with this same group name found");
        }
        return be2;
    }

    public void b(ModUnitRegistry l2, ab ab2) throws bo {
        for (String string : (java.util.Collection<String>) (java.util.Collection) ab2.e("placementRule_")) {
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

    public String a(UnitType y2, float f2, float f3) {
        if (!this.c) {
            return null;
        }
        return this.b(y2, f2, f3);
    }

    public String a(UnitType y2, int n2, int n3) {
        if (!this.d) {
            return null;
        }
        MapEngine b2 = com.corrodinggames.rts.gameFramework.GlobalState.B().bL;
        b2.b(n2, n3);
        return this.b(y2, b2.T, b2.U);
    }

    public String b(UnitType y2, float f2, float f3) {
        for (TeamTag g2 : (java.util.Collection<TeamTag>) (java.util.Collection) this.a) {
            boolean bl = false;
            boolean bl2 = false;
            bg bg2 = null;
            for (bg bg3 : (java.util.Collection<bg>) (java.util.Collection) this.b) {
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
                return bg2.o.getLocalizedText();
            }
            return "{0}";
        }
        return null;
    }

    private static boolean a(UnitType y2, bg bg2, float f2, float f3) {
        be.e.colorName = f2 + bg2.g;
        be.e.colorValue = f3 + bg2.h;
        be.e.gradientColor1 = bg2;
        be.e.gradientColor2 = 0;
        GlobalState l2 = com.corrodinggames.rts.gameFramework.GlobalState.B();
        l2.cc.a(be.e.colorName, be.e.colorValue, bg2.e, y2, 0.0f, e);
        return be.e.gradientColor2 >= bg2.k && be.e.gradientColor2 <= bg2.l;
    }
}
