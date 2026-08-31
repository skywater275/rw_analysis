/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.m;
import java.util.ArrayList;

final class ar$12
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
        return new m(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.d.m.b();
    }

    @Override
    public int c() {
        return 700;
    }

    @Override
    public int c(int n2) {
        if (n2 == 2) {
            return 2000;
        }
        return 0;
    }

    @Override
    public float D() {
        return 0.001f;
    }

    @Override
    public void a(ArrayList arrayList, int n2) {
        com.corrodinggames.rts.game.units.d.m.a(arrayList, n2);
    }
}
