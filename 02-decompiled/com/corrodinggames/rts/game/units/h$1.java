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

final class h$1
extends s {
    h$1(String string) {
        super(string);
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return !com.corrodinggames.rts.game.units.h.w();
    }

    @Override
    public String a() {
        return "Reload all unit data from disk (for modding)";
    }

    @Override
    public String b() {
        return "Reload units";
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    public ar g_() {
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
        return this.g_();
    }
}
