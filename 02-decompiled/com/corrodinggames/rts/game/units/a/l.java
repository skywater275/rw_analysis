/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.gameFramework.f.a;

public strictfp class l
extends w {
    as a;

    public l(as as2) {
        this(as2, -999.0f);
    }

    public l(as as2, float f2) {
        super("u_" + as2.v());
        as as3 = com.corrodinggames.rts.game.units.custom.l.c(as2);
        if (as3 != null) {
            as2 = as3;
            this.a("u_" + as2.v());
        }
        this.g = f2;
        this.a = as2;
    }

    @Override
    public String a() {
        String string = this.a.f();
        boolean bl = false;
        boolean bl2 = true;
        string = string + "\n\n" + com.corrodinggames.rts.gameFramework.f.a.a(am.c(this.a), false, bl, bl2);
        return string;
    }

    @Override
    public String b() {
        return this.a.e();
    }

    @Override
    public int c() {
        return this.B().a();
    }

    @Override
    public b B() {
        b b2 = this.h.a();
        if (b2 != null) {
            return b2;
        }
        return this.a.u();
    }

    @Override
    public b r_() {
        b b2 = this.h.b();
        if (b2 != null) {
            return b2;
        }
        return this.a.B();
    }

    @Override
    public as i() {
        return this.a;
    }

    @Override
    public float K() {
        return this.a.D();
    }

    @Override
    public t f() {
        return t.d;
    }

    @Override
    public boolean n_() {
        return !this.a.C();
    }

    @Override
    public boolean g(am am2) {
        if (this.i().w()) {
            return true;
        }
        return super.g(am2);
    }

    @Override
    public boolean g() {
        return true;
    }
}
