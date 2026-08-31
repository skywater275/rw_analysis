/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d.a;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.gameFramework.h.a;

final class b$4
extends w {
    b$4(int n2) {
        super(n2);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return "-Short range area affect\n-Adds self-repair";
    }

    @Override
    public String b() {
        return a.a("gui.actions.upgradeToFlamethrower", new Object[0]);
    }

    @Override
    public int c() {
        return 700;
    }

    @Override
    public float K() {
        return 0.002f;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        b b2 = (b)am2;
        if (b2.M() != 1 || b2.a(s.i, bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    @Override
    public boolean b(am am2) {
        b b2 = (b)am2;
        return b2.M() == 1;
    }

    public ar L() {
        return null;
    }

    @Override
    public t f() {
        return t.c;
    }

    @Override
    public void f(am am2) {
        b b2 = (b)am2;
        b2.b(b.x);
        b.d(b2);
    }

    @Override
    public /* synthetic */ as i() {
        return this.L();
    }
}
