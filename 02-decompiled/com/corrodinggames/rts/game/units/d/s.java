/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public strictfp class s
extends i {
    public float a;
    public boolean b;
    public boolean c;
    n d;
    am e;
    float f;
    float g;
    boolean h;

    s(boolean bl) {
        this.b = bl;
    }

    @Override
    public int excludeTeam(y y2) {
        return -2;
    }

    @Override
    public n onlyEnemiesOfTeam(y y2) {
        return null;
    }

    public void a(float f, boolean bl) {
        this.a = f * f;
        this.h = bl;
        this.c = true;
    }

    @Override
    public void setup(y y2, float f) {
        this.e = null;
        this.f = -1.0f;
        this.g = -1.0f;
        this.d = y2.bX;
        if (!this.c) {
            throw new RuntimeException("AutoRepairCallback not ready");
        }
        this.c = false;
    }

    @Override
    public void callback(y y2, float f2, am am2) {
        float f3;
        if (y2 == am2) {
            return;
        }
        if ((am2.cu < am2.cv || am2.cm < 1.0f) && !am2.bV && am2.cN == null && this.d.d(am2.bX) && y2.a(am2) && (f3 = com.corrodinggames.rts.gameFramework.f.a(y2.eo, y2.ep, am2.eo, am2.ep)) < this.a) {
            b b2;
            if (am2.cm < 1.0f && (b2 = y2.g(am2)) != null) {
                return;
            }
            boolean bl = false;
            if (!this.h) {
                if (this.f == -1.0f || this.f > am2.cu) {
                    bl = true;
                }
            } else if (this.g == -1.0f || this.g > f3) {
                bl = true;
            }
            if (bl && am2.g() == 0.0f) {
                this.f = am2.cu;
                this.g = f3;
                this.e = am2;
            }
        }
    }
}
