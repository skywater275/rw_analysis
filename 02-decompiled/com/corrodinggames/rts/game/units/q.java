/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.n;
import com.corrodinggames.rts.game.units.r;

class q
extends s {
    r a;

    public q(r r2) {
        super("SetTerrainType" + r2.ordinal());
        this.a = r2;
    }

    @Override
    public boolean b(am am2) {
        h h2 = com.corrodinggames.rts.game.units.h.L();
        if (h2 != null) {
            return h2.G == n.c;
        }
        return true;
    }

    @Override
    public String a() {
        return "Set terrain type to: " + this.a.name();
    }

    @Override
    public String b() {
        return "Set " + this.a.name();
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    public ar n() {
        return null;
    }

    @Override
    public u e() {
        return u.g;
    }

    @Override
    public t f() {
        return t.f;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return true;
    }

    @Override
    public boolean h() {
        return true;
    }

    @Override
    public boolean o() {
        return true;
    }

    @Override
    public boolean a(float f2, float f3) {
        return true;
    }

    @Override
    public boolean p() {
        return true;
    }

    @Override
    public /* synthetic */ as i() {
        return this.n();
    }
}
