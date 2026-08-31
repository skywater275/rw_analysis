/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.h;

final class ar$30
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
        return new h(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.d.h.K();
    }

    @Override
    public int c() {
        return 1500;
    }

    @Override
    public int c(int n2) {
        if (n2 == 2) {
            return 3000;
        }
        if (n2 == 3) {
            return 5000;
        }
        return 0;
    }

    @Override
    public float D() {
        return 6.0E-4f;
    }
}
