/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.n;

final class ar$53
extends ar {
    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new n(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.e.n.f();
    }

    @Override
    public int c() {
        return 350;
    }

    @Override
    public float D() {
        return 0.002f;
    }
}
