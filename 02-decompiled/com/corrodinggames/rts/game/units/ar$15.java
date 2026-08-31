/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.o;

final class ar$15
extends ar {
    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new o(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.e.o.f();
    }

    @Override
    public int c() {
        return 800;
    }

    @Override
    public float D() {
        return 0.003f;
    }
}
