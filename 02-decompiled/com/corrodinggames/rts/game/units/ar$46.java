/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.f;

final class ar$46
extends ar {
    @Override
    public boolean A() {
        return true;
    }

    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        f f2 = new f(bl);
        f2.q = true;
        return f2;
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.f.d_();
    }

    @Override
    public int c() {
        return 1000;
    }

    @Override
    public float D() {
        return 6.0E-4f;
    }
}
