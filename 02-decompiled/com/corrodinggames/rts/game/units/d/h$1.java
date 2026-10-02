/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.h;
import com.corrodinggames.rts.gameFramework.h.a;

final class h$1
extends w {
    h$1(int n2) {
        super(n2);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return a.a("units.fabricator.upgrade.description", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("units.fabricator.upgrade.name", new Object[0]);
    }

    @Override
    public int c() {
        return ar.J.c(2);
    }

    @Override
    public float K() {
        return 3.0E-4f;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        h h2 = (h)am2;
        if (h2.r != 1 || h2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    @Override
    public boolean b(am am2) {
        h h2 = (h)am2;
        return h2.r == 1;
    }

    public ar L() {
        return null;
    }

    @Override
    public t f() {
        return t.c;
    }

    @Override
    public /* synthetic */ as i() {
        return this.L();
    }
}
