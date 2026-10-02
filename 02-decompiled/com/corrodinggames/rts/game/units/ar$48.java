/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.t;

final class ar$48
extends ar {
    @Override
    public String e() {
        return this.i();
    }

    @Override
    public String i() {
        return "marker";
    }

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
        t t2 = new t(bl);
        return t2;
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.t.b();
    }

    @Override
    public int c() {
        return 9999;
    }

    @Override
    public float D() {
        return 1.0f;
    }
}
