/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.k;

final class ar$10
extends ar {
    @Override
    public boolean C() {
        return false;
    }

    @Override
    public am a(boolean bl) {
        return new k(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.e.k.f();
    }

    @Override
    public int c() {
        return 1300;
    }

    @Override
    public float D() {
        return 0.0013f;
    }

    @Override
    public int g() {
        return 2;
    }
}
