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
import com.corrodinggames.rts.gameFramework.h.a;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public class o
extends s {
    public o() {
        super("c_1");
    }

    @Override
    public String a() {
        return a.a("gui.actions.setRally.description", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("gui.actions.setRally", new Object[0]);
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    public ar K() {
        return null;
    }

    @Override
    public u e() {
        return u.d;
    }

    @Override
    public t f() {
        return t.b;
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public e j() {
        return l.B().bS.bj;
    }

    @Override
    public boolean h() {
        return true;
    }

    @Override
    public /* synthetic */ as i() {
        return this.K();
    }
}
