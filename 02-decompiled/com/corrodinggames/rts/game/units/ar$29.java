/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.w;

final class ar$29
extends ar {
    @Override
    public boolean j() {
        return true;
    }

    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new w(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.d.w.b();
    }

    @Override
    public int c() {
        return 100;
    }

    @Override
    public float D() {
        return 0.003f;
    }
}
