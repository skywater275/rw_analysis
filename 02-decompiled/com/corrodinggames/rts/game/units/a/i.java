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
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.h.a;

public class i
extends s {
    public i() {
        super("c_9");
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    @Override
    public int c() {
        return 0;
    }

    public ar w() {
        return null;
    }

    @Override
    public u e() {
        return u.m;
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
        return a.a("gui.actions.patrol.description", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("gui.actions.patrol", new Object[0]);
    }

    @Override
    public boolean s() {
        return true;
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.f.g.bP) {
            return 0.6f;
        }
        return 0.5f;
    }

    @Override
    public boolean h() {
        return true;
    }

    @Override
    public boolean o_() {
        return true;
    }

    @Override
    public /* synthetic */ as i() {
        return this.w();
    }
}
