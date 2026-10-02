/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.e;

final class ar$17
extends ar {
    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new e(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.e.e.f();
    }

    @Override
    public int c() {
        return 1000;
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
