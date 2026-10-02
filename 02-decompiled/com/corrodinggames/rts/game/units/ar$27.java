/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.f;
import java.util.ArrayList;

final class ar$27
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
        return new f(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.d.f.b();
    }

    @Override
    public int c() {
        return 11000;
    }

    @Override
    public float D() {
        return 3.5E-4f;
    }

    @Override
    public void a(ArrayList arrayList, int n2) {
        com.corrodinggames.rts.game.units.d.f.a(arrayList, n2);
    }
}
