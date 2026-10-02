/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public strictfp class ah
extends i {
    public int a;
    public float[] b = new float[31];
    public boolean[] c = new boolean[31];
    int d;
    public boolean e;
    public boolean f;

    ah(boolean bl) {
        this.e = bl;
    }

    @Override
    public int excludeTeam(y y2) {
        return -2;
    }

    @Override
    public n onlyEnemiesOfTeam(y y2) {
        return y2.bX;
    }

    public void a(y y2) {
        float f2 = y2.b(false);
        this.d = y2.bl();
        for (int i2 = 0; i2 < this.d; ++i2) {
            float f3 = y2.z(i2);
            if (f3 > f2) {
                f3 = f2;
            }
            this.b[i2] = f3 * f3 + 1.0f;
            this.c[i2] = false;
            if (y2.v(i2) != -1 || y2.cL[i2].j != null) continue;
            this.c[i2] = true;
        }
        this.f = true;
    }

    @Override
    public void setup(y y2, float f2) {
        this.a = 0;
        if (!this.f) {
            throw new RuntimeException("PassiveTargetCallback not ready");
        }
        this.f = false;
    }

    @Override
    public void callback(y y2, float f2, am am2) {
        if (y2.b(am2, true)) {
            ++this.a;
            if (this.e) {
                if (!(am2 instanceof y)) {
                    return;
                }
                y y3 = (y)am2;
                if (!y3.l() || !y3.k(y2)) {
                    return;
                }
            }
            float f3 = com.corrodinggames.rts.gameFramework.f.a(y2.eo, y2.ep, am2.eo, am2.ep);
            for (int i2 = 0; i2 < this.d; ++i2) {
                if (!this.c[i2]) continue;
                boolean bl = true;
                boolean bl2 = false;
                if (!y2.a(i2, am2, true, false) || !(f3 < this.b[i2]) || !(f3 > y2.A(i2))) continue;
                this.b[i2] = f3;
                y2.cL[i2].j = am2;
            }
        }
    }
}
