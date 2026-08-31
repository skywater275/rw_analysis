/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.b;
import com.corrodinggames.rts.game.units.h;
import java.util.ArrayList;

final class ar$52
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
        com.corrodinggames.rts.game.units.e.b.K();
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
        return true;
    }

    @Override
    public boolean n() {
        return true;
    }

    @Override
    public void a(ArrayList arrayList, int n2) {
        com.corrodinggames.rts.game.units.e.b.a(arrayList, n2);
        com.corrodinggames.rts.game.units.h.a(null, n2);
    }
}
