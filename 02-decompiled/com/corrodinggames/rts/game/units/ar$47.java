/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.h;
import java.util.ArrayList;

final class ar$47
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
        return new h(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.h.K();
    }

    @Override
    public int c() {
        return 500;
    }

    @Override
    public float D() {
        return 0.002f;
    }

    @Override
    public boolean l() {
        return true;
    }

    @Override
    public boolean m() {
        return false;
    }

    @Override
    public boolean n() {
        return false;
    }

    @Override
    public void a(ArrayList arrayList, int n2) {
    }
}
