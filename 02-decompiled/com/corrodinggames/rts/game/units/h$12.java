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
import com.corrodinggames.rts.game.units.h;

final class h$12
extends s {
    h$12(String string) {
        super(string);
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return !com.corrodinggames.rts.game.units.h.w();
    }

    @Override
    public String a() {
        return "Reload data only for active units on map (for modding). This is a faster than reload but will be incomplete.";
    }

    @Override
    public String b() {
        return "Quick reload";
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    public ar k() {
        return null;
    }

    @Override
    public u e() {
        return u.a;
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
        return this.k();
    }
}
