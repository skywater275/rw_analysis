/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.n;
import com.corrodinggames.rts.gameFramework.f.g;

strictfp class l
extends x {
    boolean a;
    boolean b;

    public l(boolean bl, boolean bl2) {
        super("changeTypeFilter" + bl + "d:" + bl2);
        this.a = bl;
        this.b = bl2;
    }

    @Override
    public boolean b(am am2) {
        h h2 = com.corrodinggames.rts.game.units.h.L();
        if (h2 != null) {
            return h2.G == n.b;
        }
        return true;
    }

    @Override
    public String b() {
        if (this.b) {
            h h2 = com.corrodinggames.rts.game.units.h.L();
            if (h2 != null) {
                if (h2.F != null) {
                    return h2.F.a();
                }
                return "All types";
            }
            return "Type Filter";
        }
        if (this.a) {
            return "<- Set type";
        }
        return "Set type ->";
    }

    @Override
    public String d() {
        if (!this.b) {
            if (this.a) {
                return "<-";
            }
            return "->";
        }
        h h2 = com.corrodinggames.rts.game.units.h.L();
        if (h2 == null) {
            return "NA";
        }
        if (h2.F == null) {
            return "All mods";
        }
        return h2.F.a();
    }

    @Override
    public String a() {
        return "Change filtered type";
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.f.g.bP) {
            return 0.8f;
        }
        return 0.5f;
    }

    @Override
    public int m() {
        if (this.b) {
            return 2;
        }
        return 4;
    }

    @Override
    public t f() {
        if (this.b) {
            return t.g;
        }
        return super.f();
    }

    @Override
    public u e() {
        if (this.b) {
            return u.i;
        }
        return super.e();
    }
}
