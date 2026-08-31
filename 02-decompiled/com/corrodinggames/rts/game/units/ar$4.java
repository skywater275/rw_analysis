/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.b.f;

final class ar$4
extends ar {
    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new f(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.b.f.f();
    }

    @Override
    public int c() {
        return 650;
    }

    @Override
    public float D() {
        return 0.0012f;
    }
}
