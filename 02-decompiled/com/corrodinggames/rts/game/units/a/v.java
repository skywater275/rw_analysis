/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f.a;
import com.corrodinggames.rts.gameFramework.l;

public class v
extends s {
    as a;
    int b = 1;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        v v2 = (v)object;
        if (this.b != v2.b) {
            return false;
        }
        if (this.a != v2.a) {
            return false;
        }
        return super.equals(object);
    }

    public v(as as2) {
        this(as2, 1, null);
    }

    public v(as as2, int n2, Integer n3) {
        super("b_" + as2.v());
        as as3 = com.corrodinggames.rts.game.units.custom.l.c(as2);
        if (as3 != null) {
            as2 = as3;
            this.a("b_" + as2.v());
        }
        if (n2 != 1) {
            this.a(this.N() + "_" + n2);
        }
        this.a = as2;
        this.b = n2;
        if (n3 != null) {
            this.g = n3.intValue();
        }
    }

    @Override
    public as i() {
        return this.a;
    }

    @Override
    public as y() {
        return this.a;
    }

    @Override
    public int t() {
        return this.b;
    }

    @Override
    public String a() {
        String string = this.i().f();
        boolean bl = false;
        boolean bl2 = true;
        am am2 = am.c(this.i());
        if (this.b != 1 && am2 instanceof y) {
            ((y)am2).a(this.b);
        }
        string = string + "\n\n" + com.corrodinggames.rts.gameFramework.f.a.a(am2, false, bl, bl2);
        if (this.b != 1 && am2 instanceof y) {
            ((y)am2).a(1);
        }
        return string;
    }

    @Override
    public String b() {
        as as2 = this.i();
        String string = this.i().e();
        if (!(as2 instanceof com.corrodinggames.rts.game.units.custom.l)) {
            if (this.t() == 2) {
                string = string + " T-2";
            }
            if (this.t() == 3) {
                string = string + " T-3";
            }
        }
        return string;
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
        return this.i().d(this.t());
    }

    @Override
    public b r_() {
        b b2 = this.h.b();
        if (b2 != null) {
            return b2;
        }
        return this.i().B();
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    @Override
    public u e() {
        return u.b;
    }

    @Override
    public t f() {
        return t.e;
    }

    @Override
    public boolean n_() {
        return !this.i().C();
    }

    @Override
    public boolean g(am am2) {
        l l2 = l.B();
        if ((this.i() == ar.D || this.i() == ar.C) && l2.O() && l2.bX.ay.i) {
            return true;
        }
        if (this.i().w()) {
            return true;
        }
        return super.g(am2);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean D() {
        return false;
    }

    @Override
    public float p(am am2) {
        if (!(am2 instanceof y)) {
            return -1.0f;
        }
        y y2 = (y)am2;
        am am3 = y2.X();
        if (am3 != null && am3.cm < 1.0f && am3.r() == this.i()) {
            return am3.cm;
        }
        return -1.0f;
    }

    @Override
    public boolean r(am am2) {
        return this.h.a(am2, true);
    }

    @Override
    public boolean b(am am2) {
        return this.h.a(am2, false);
    }
}
