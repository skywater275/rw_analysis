/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.i;

final class ar$11
extends ar {
    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new i(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.e.i.L();
    }

    @Override
    public int c() {
        return 600;
    }

    @Override
    public float D() {
        return 0.003f;
    }
}
