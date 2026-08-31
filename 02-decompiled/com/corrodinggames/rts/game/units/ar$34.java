/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.t;
import java.util.ArrayList;

final class ar$34
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
        return new t(bl);
    }

    @Override
    public void b() {
        com.corrodinggames.rts.game.units.d.t.b();
    }

    @Override
    public int c() {
        return 1000;
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
        return 7.0E-4f;
    }

    @Override
    public void a(ArrayList arrayList, int n2) {
        com.corrodinggames.rts.game.units.d.t.a(arrayList, n2);
    }

    @Override
    public int a(am am2) {
        return 110;
    }
}
