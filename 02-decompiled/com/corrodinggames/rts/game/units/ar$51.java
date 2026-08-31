/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.a.a;

final class ar$51
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
        return new a(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.d.a.a.b();
    }

    @Override
    public int c() {
        return 600;
    }

    @Override
    public float D() {
        return 8.0E-4f;
    }
}
