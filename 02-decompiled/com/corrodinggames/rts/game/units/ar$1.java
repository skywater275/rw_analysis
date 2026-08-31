/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.g;

final class ar$1
extends ar {
    @Override
    public boolean j() {
        return true;
    }

    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new g(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.d.g.K();
    }

    @Override
    public int c() {
        return 700;
    }

    @Override
    public int c(int n2) {
        if (n2 == 2) {
            return 1200;
        }
        if (n2 == 3) {
            return 2500;
        }
        return 0;
    }

    @Override
    public boolean p() {
        return true;
    }

    @Override
    public float D() {
        return 0.001f;
    }

    @Override
    public int a(am am2) {
        if (am2.cJ()) {
            return 110;
        }
        return 0;
    }
}
