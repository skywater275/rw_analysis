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

public class n
extends s {
    public n() {
        super("c_3");
    }

    @Override
    public String a() {
        return a.a("gui.actions.repairTarget", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("gui.actions.repairTarget", new Object[0]);
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
        return u.f;
    }

    @Override
    public boolean h_() {
        return true;
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
    public boolean h() {
        return true;
    }

    @Override
    public /* synthetic */ as i() {
        return this.K();
    }
}
