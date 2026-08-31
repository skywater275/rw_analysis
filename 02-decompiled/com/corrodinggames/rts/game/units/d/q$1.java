/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.q;
import com.corrodinggames.rts.gameFramework.h.a;

final class q$1
extends s {
    q$1(int n2) {
        super(n2);
    }

    @Override
    public String a() {
        return a.a("gui.actions.launchNuke", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("gui.actions.launchNuke", new Object[0]);
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public int b(am am2, boolean bl) {
        q q2 = (q)am2;
        return q2.c;
    }

    public ar K() {
        return null;
    }

    @Override
    public u e() {
        return u.g;
    }

    @Override
    public t f() {
        return t.f;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        q q2 = (q)am2;
        return q2.c > 0;
    }

    @Override
    public /* synthetic */ as i() {
        return this.K();
    }
}
