/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.t;
import com.corrodinggames.rts.gameFramework.h.a;

strictfp class u
extends w {
    public u() {
        super(t.g.a());
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return "-Allows factory to build Tech 2 units";
    }

    @Override
    public String b() {
        return a.a("gui.actions.upgradeT2", new Object[0]);
    }

    @Override
    public int c() {
        return ar.d.c(2);
    }

    @Override
    public float K() {
        return 4.0E-4f;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        t t2 = (t)am2;
        if (t2.r != 1 || t2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    @Override
    public boolean b(am am2) {
        t t2 = (t)am2;
        return t2.r == 1;
    }

    public ar L() {
        return null;
    }

    @Override
    public com.corrodinggames.rts.game.units.a.t f() {
        return com.corrodinggames.rts.game.units.a.t.c;
    }

    @Override
    public /* synthetic */ as i() {
        return this.L();
    }
}
