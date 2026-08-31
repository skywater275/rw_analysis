/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.c;

final class ar$36
extends ar {
    @Override
    public boolean C() {
        return false;
    }

    @Override
    public am a(boolean bl) {
        return new c(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.e.c.f();
    }

    @Override
    public int c() {
        return 21000;
    }

    @Override
    public float D() {
        return 2.0E-4f;
    }

    @Override
    public int g() {
        return 3;
    }
}
