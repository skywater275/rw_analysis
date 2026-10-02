/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.m;

public class c
extends i {
    public boolean a;
    public h b;
    public float c;
    public boolean d;
    public q e;
    public boolean f;
    public m g = new m();
    public am h;

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
        float f3;
        h h2 = am2.de();
        if ((this.b == null || h2 != null && com.corrodinggames.rts.game.units.custom.g.a(this.b, h2)) && (f3 = com.corrodinggames.rts.gameFramework.f.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.c) {
            if (am2.cm < 1.0f && !this.d) {
                return;
            }
            if (this.e != null && !y2.bX.a(this.e, am2.bX)) {
                return;
            }
            if (this.a && !com.corrodinggames.rts.gameFramework.utility.y.b((am)y2, am2.eo, am2.ep)) {
                return;
            }
            if (!this.f) {
                this.h = am2;
                this.c = f3;
            } else {
                this.g.add(am2);
            }
        }
    }
}
