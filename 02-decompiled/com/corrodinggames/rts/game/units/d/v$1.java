/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.v;
import com.corrodinggames.rts.gameFramework.h.a;

final class v$1
extends w {
    v$1(int n2) {
        super(n2);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return a.a("units.supplyDepot.upgrade.description", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("units.supplyDepot.upgrade.name", new Object[0]);
    }

    @Override
    public int c() {
        return 1000;
    }

    @Override
    public float K() {
        return 4.0E-4f;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        v v2 = (v)am2;
        if (v2.f != 1 || v2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
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
