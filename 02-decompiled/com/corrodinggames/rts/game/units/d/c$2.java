/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.c;
import com.corrodinggames.rts.gameFramework.h.a;

final class c$2
extends w {
    c$2(int n2) {
        super(n2);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return a.a("gui.actions.buildAntiNuke.description", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("gui.actions.buildAntiNuke", new Object[0]);
    }

    @Override
    public int c() {
        return 4000;
    }

    @Override
    public float K() {
        return 7.0E-4f;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        c c2 = (c)am2;
        float f2 = c2.d + c2.a(this.N(), bl);
        if (f2 >= 12.0f) {
            return false;
        }
        return super.a(am2, bl);
    }

    public ar L() {
        return null;
    }

    @Override
    public t f() {
        return t.d;
    }

    @Override
    public /* synthetic */ as i() {
        return this.L();
    }
}
