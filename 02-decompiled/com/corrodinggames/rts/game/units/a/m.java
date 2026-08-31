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

public class m
extends s {
    boolean a;

    public m(boolean bl) {
        super("c_2");
        this.a = bl;
    }

    @Override
    public String a() {
        if (!this.a) {
            return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimBuildingTarget.description", new Object[0]);
        }
        return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimTarget.description", new Object[0]);
    }

    @Override
    public String b() {
        if (!this.a) {
            return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimBuildingTarget", new Object[0]);
        }
        return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.reclaimTarget", new Object[0]);
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
        return u.e;
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
    public boolean o(am am2) {
        if (am2 == null) {
            return true;
        }
        if (!this.a) {
            return am2.bI();
        }
        return true;
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.f.g.bP) {
            return 0.6f;
        }
        return 1.0f;
    }

    @Override
    public /* synthetic */ as i() {
        return this.K();
    }
}
