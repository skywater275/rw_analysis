/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.x;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;

public strictfp class u
extends x {
    public int a = 14;
    public float b = 60.0f;

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.c(0);
        as2.a(this.a);
        as2.a(this.b);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        k2.d();
        this.a = k2.f();
        this.b = k2.g();
        super.a(k2);
    }

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.R;
    }

    public static void f() {
        l l2 = l.B();
    }

    public u(boolean bl) {
        super(bl);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        this.b -= f2;
        if (this.b < 0.0f) {
            this.ci();
        }
    }

    @Override
    public int s() {
        return this.a;
    }

    @Override
    public boolean t() {
        return true;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
