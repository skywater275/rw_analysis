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
import com.corrodinggames.rts.game.units.d.c;
import com.corrodinggames.rts.gameFramework.h.a;

final class c$1
extends s {
    c$1(int n2) {
        super(n2);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return "";
    }

    @Override
    public String b() {
        return a.a("gui.actions.antiNukeCount", new Object[0]);
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return this.b(am2, false) != 0;
    }

    public ar K() {
        return null;
    }

    @Override
    public u e() {
        return u.a;
    }

    @Override
    public t f() {
        return t.a;
    }

    @Override
    public int b(am am2, boolean bl) {
        c c2 = (c)am2;
        return c2.d;
    }

    @Override
    public /* synthetic */ as i() {
        return this.K();
    }
}
