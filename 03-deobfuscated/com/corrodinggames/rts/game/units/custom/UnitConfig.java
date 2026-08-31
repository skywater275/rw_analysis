/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;


import com.corrodinggames.rts.game.units.custom.TeamTag;

public final class UnitConfig {
    public final TeamTag[] a;  // 02 铁证: custom.h.g[] a

    public UnitConfig(TeamTag[] gArray) {
        this.a = gArray;
    }

    public boolean a() {
        return this.a.length == 0;
    }

    public boolean a(UnitConfig h2) {
        if (h2 == null) {
            return this.a();
        }
        if (this.a.length != h2.a.length) {
            return false;
        }
        for (TeamTag g2 : this.a) {
            boolean bl = false;
            for (TeamTag g3 : h2.a) {
                if (g2 != g3) continue;
                bl = true;
                break;
            }
            if (bl) continue;
            return false;
        }
        return true;
    }

    public int b() {
        return this.a.length;
    }

    public String toString() {
        String string = "";
        boolean bl = true;
        for (TeamTag g2 : this.a) {
            if (!bl) {
                string = string + ", ";
            }
            bl = false;
            string = string + g2.a;
        }
        return "{" + string + "}";
    }
}
