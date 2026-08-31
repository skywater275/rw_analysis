/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp class w
extends i {
    static e a = null;
    static e[] b = new e[10];
    static e c = null;

    public static void b() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.wall_v);
        c = l2.bO.a(R$drawable.wall_v);
        b = com.corrodinggames.rts.game.n.a(a);
    }

    @Override
    public e d() {
        if (this.bV) {
            return c;
        }
        if (this.bX == null) {
            return b[b.length - 1];
        }
        return b[this.bX.R()];
    }

    @Override
    public e k() {
        return null;
    }

    @Override
    public void a(int n2) {
    }

    public w(boolean bl) {
        super(bl);
        this.b(a);
        this.ck = this.cj = 15.0f;
        this.cu = this.cv = 700.0f;
        this.M = a;
        this.n.a(0, 0, 1, 0);
        this.o.a(0, 0, 1, 0);
    }

    public ar K() {
        return com.corrodinggames.rts.game.units.ar.I;
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }
}
