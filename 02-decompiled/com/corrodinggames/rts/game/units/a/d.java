/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.l;

public class d
extends s {
    int a;
    a b;

    public d() {
        super("c_7");
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    @Override
    public int c() {
        return 0;
    }

    public ar n() {
        return null;
    }

    @Override
    public u e() {
        return u.k;
    }

    @Override
    public t f() {
        return t.a;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return "Attack Mode";
    }

    @Override
    public String b() {
        a a2 = this.q();
        if (a2 != null) {
            return a2.name();
        }
        return "NA";
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public void c(am am2) {
        l l2 = l.B();
        a a2 = this.r();
        a a3 = this.a(a2);
        n n2 = null;
        n2 = am2.bX;
        e e2 = l2.cf.b(n2);
        for (am am3 : am.bE) {
            if (!(am3 instanceof y)) continue;
            y y2 = (y)am3;
            if (!y2.cG) continue;
            e2.a(y2);
        }
        e2.a(a3);
        this.a = l2.bS.Y;
        this.b = a3;
    }

    public a a(a a2) {
        if (a2 == com.corrodinggames.rts.game.units.a.b) {
            return com.corrodinggames.rts.game.units.a.e;
        }
        if (a2 == com.corrodinggames.rts.game.units.a.b) {
            return com.corrodinggames.rts.game.units.a.f;
        }
        return com.corrodinggames.rts.game.units.a.b;
    }

    public a q() {
        l l2 = l.B();
        a a2 = this.r();
        this.a = l2.bS.Y;
        this.b = a2;
        return a2;
    }

    public a r() {
        l l2 = l.B();
        if (this.a == l2.bS.Y && this.b != null) {
            return this.b;
        }
        a a2 = null;
        boolean bl = false;
        boolean bl2 = false;
        for (am am2 : am.bE) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG) continue;
            if (a2 == null || a2 == y2.P) {
                a2 = y2.P;
                continue;
            }
            a2 = com.corrodinggames.rts.game.units.a.g;
        }
        return a2;
    }

    @Override
    public boolean b(am am2) {
        return true;
    }

    @Override
    public String d() {
        return this.b();
    }

    @Override
    public boolean s() {
        return true;
    }

    @Override
    public /* synthetic */ as i() {
        return this.n();
    }
}
