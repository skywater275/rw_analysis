/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.d;

final class ar$26
extends ar {
    @Override
    public boolean C() {
        return false;
    }

    @Override
    public am a(boolean bl) {
        return new d(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.e.d.f();
    }

    @Override
    public int c() {
        return 14000;
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
