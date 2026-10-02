/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.b.c;

final class ar$33
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
        com.corrodinggames.rts.game.units.b.c.L();
    }

    @Override
    public int c() {
        return 2000;
    }

    @Override
    public float D() {
        return 0.001f;
    }

    @Override
    public int g() {
        return 2;
    }
}
