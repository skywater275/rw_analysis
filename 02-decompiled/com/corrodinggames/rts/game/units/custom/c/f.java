/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.c;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.c.a;
import com.corrodinggames.rts.game.units.custom.c.c;
import com.corrodinggames.rts.game.units.custom.c.e;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;

public strictfp class f
extends i {
    public c a;
    public a b;
    public am c;
    public float d;

    @Override
    public void setup(y y2, float f2) {
    }

    @Override
    public int excludeTeam(y y2) {
        return -3;
    }

    @Override
    public n onlyEnemiesOfTeam(y y2) {
        return null;
    }

    @Override
    public n onlyTeam(y y2) {
        return y2.bX;
    }

    @Override
    public void callback(y y2, float f2, am am2) {
        if (y2 == am2) {
            return;
        }
        h h2 = am2.dh();
        if (h2 != null && g.a(this.b.a, h2)) {
            e e2;
            float f3;
            if (y2.bX != am2.bX) {
                if (y2.bX.d(am2.bX)) {
                    if (!this.b.b) {
                        return;
                    }
                } else if (y2.bX.c(am2.bX)) {
                    if (!this.b.c) {
                        return;
                    }
                } else {
                    return;
                }
            }
            if ((f3 = com.corrodinggames.rts.gameFramework.f.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.d && ((e2 = this.a.a(this.b, false)) == null || e2.a(am2) == null)) {
                this.c = am2;
                this.d = f3;
            }
        }
    }
}
