/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.h.c;

final class ar$8
extends ar {
    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new c(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.h.c.f();
    }

    @Override
    public int c() {
        return 300;
    }

    @Override
    public float D() {
        return 0.005f;
    }
}
