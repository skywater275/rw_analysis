/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public strictfp class ae
extends i {
    public int a;
    public float b;
    public boolean c;
    public boolean d;

    ae(boolean bl) {
        this.c = bl;
    }

    @Override
    public int excludeTeam(y y2) {
        return -2;
    }

    @Override
    public n onlyEnemiesOfTeam(y y2) {
        return y2.bX;
    }

    public void a(float f) {
        this.b = f * f + 1.0f;
        this.d = true;
    }

    @Override
    public void setup(y y2, float f) {
        this.a = 0;
        if (!this.d) {
            throw new RuntimeException("PassiveTargetCallback not ready");
        }
        this.d = false;
    }

    @Override
    public void callback(y y2, float f2, am am2) {
        if (y2.b(am2, true)) {
            float f3;
            ++this.a;
            if (this.c) {
                if (!(am2 instanceof y)) {
                    return;
                }
                y y3 = (y)am2;
                if (!y3.l() || !y3.k(y2)) {
                    return;
                }
            }
            if ((f3 = f.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.b) {
                this.b = f3;
                y2.R = am2;
            }
        }
    }
}
