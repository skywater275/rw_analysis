/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;

final class ar$20
extends ar {
    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new al(bl);
    }

    @Override
    public void b() {
        al.b();
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public float D() {
        return 0.0025f;
    }
}
