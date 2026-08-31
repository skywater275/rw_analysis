/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;

final class h$18
extends s {
    h$18(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Delete all units at a point";
    }

    @Override
    public String b() {
        return "Delete units at";
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    public ar i_() {
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
        return true;
    }

    @Override
    public boolean h() {
        return true;
    }

    @Override
    public /* synthetic */ as i() {
        return this.i_();
    }
}
