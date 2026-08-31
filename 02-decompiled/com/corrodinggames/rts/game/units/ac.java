/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public strictfp class ac
extends i {
    public float a;
    public float b;
    public h c;
    public float d;
    public am e;
    public boolean f;
    public boolean g = false;

    @Override
    public void setup(y y2, float f) {
    }

    @Override
    public int excludeTeam(y y2) {
        return -2;
    }

    @Override
    public n onlyEnemiesOfTeam(y y2) {
        return null;
    }

    @Override
    public n onlyTeam(y y2) {
        return null;
    }

    @Override
    public void callback(y y2, float f2, am am2) {
        if (this.f && am2.g() <= 0.0f) {
            return;
        }
        float f3 = com.corrodinggames.rts.gameFramework.f.a(this.a, this.b, am2.eo, am2.ep);
        if (f3 < this.d) {
            if (am2.cm < 1.0f && !this.g) {
                return;
            }
            if (this.c != null && !com.corrodinggames.rts.game.units.custom.g.a(this.c, am2.de())) {
                return;
            }
            if (this.f && !y2.g(am2, true)) {
                return;
            }
            if (am2.cN != null) {
                return;
            }
            this.e = am2;
            this.d = f3;
        }
    }
}
