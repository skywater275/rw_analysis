/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.h.b;
import java.util.ArrayList;

final class ar$32
extends ar {
    @Override
    public boolean C() {
        return true;
    }

    @Override
    public am a(boolean bl) {
        return new b(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.h.b.t_();
    }

    @Override
    public int c() {
        return 500;
    }

    @Override
    public float D() {
        return 0.001f;
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
    public void a(ArrayList arrayList, int n2) {
        com.corrodinggames.rts.game.units.h.b.a(arrayList, n2);
    }
}
