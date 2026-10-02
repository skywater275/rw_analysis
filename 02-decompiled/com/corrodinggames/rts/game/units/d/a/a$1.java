/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d.a;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.a.b;

final class a$1
extends w {
    a$1(int n2) {
        super(n2);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return "-Increases HP, attack damage, and range";
    }

    @Override
    public String b() {
        return "Upgrade";
    }

    @Override
    public int c() {
        return 1200;
    }

    @Override
    public float K() {
        return 0.001f;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        b b2 = (b)am2;
        if (b2.j || b2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    @Override
    public boolean b(am am2) {
        b b2 = (b)am2;
        return !b2.j;
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
