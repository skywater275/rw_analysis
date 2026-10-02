/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.q;

final class ar$22
extends ar {
    @Override
    public boolean j() {
        return true;
    }

    @Override
    public boolean C() {
        return false;
    }

    @Override
    public am a(boolean bl) {
        return new q(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.d.q.b();
    }

    @Override
    public int c() {
        return 45000;
    }

    @Override
    public float D() {
        return 1.0E-4f;
    }
}
