/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.m;
import com.corrodinggames.rts.gameFramework.h.a;

strictfp class n
extends w {
    public n() {
        super(m.h.a());
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return a.a("units.landFactory.upgrade.description", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("units.landFactory.upgrade.name", new Object[0]);
    }

    @Override
    public int c() {
        return ar.b.c(2);
    }

    @Override
    public float K() {
        return 4.0E-4f;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        m m2 = (m)am2;
        if (m2.g || m2.a(this.N(), bl) > 0) {
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
