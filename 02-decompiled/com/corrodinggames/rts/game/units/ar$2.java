/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.g;

final class ar$2
extends ar {
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
        com.corrodinggames.rts.game.units.e.g.f();
    }

    @Override
    public int c() {
        return 450;
    }

    @Override
    public float D() {
        return 0.002f;
    }
}
