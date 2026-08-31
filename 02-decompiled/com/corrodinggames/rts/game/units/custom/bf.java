/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bg;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public class bf
extends i {
    public float a;
    public float b;
    public bg c;
    public int d;

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
        if (y2 == am2) {
            return;
        }
        h h2 = am2.de();
        h h3 = this.c.c;
        if ((h3 == null || h2 != null && g.a(h3, h2)) && (f3 = f.a(this.a, this.b, am2.eo, am2.ep)) < this.c.f) {
            if (am2.cm < 1.0f && this.c.i) {
                return;
            }
            if (this.c.j && !am2.bI()) {
                return;
            }
            if (this.c.d != null && !y2.bX.a(this.c.d, am2.bX)) {
                return;
            }
            ++this.d;
        }
    }
}
