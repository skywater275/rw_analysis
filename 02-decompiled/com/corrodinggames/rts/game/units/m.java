/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.l;

strictfp class m
extends x {
    boolean a;
    boolean b;

    public m(boolean bl, boolean bl2) {
        super("changeUnitTab" + bl + "d:" + bl2);
        this.a = bl;
        this.b = bl2;
    }

    @Override
    public String b() {
        return this.d();
    }

    @Override
    public String d() {
        h h2 = com.corrodinggames.rts.game.units.h.L();
        if (h2 == null) {
            return "<NULL>";
        }
        if (this.b) {
            return h2.G.a();
        }
        String string = "";
        if (this.a) {
            string = string + "<- ";
        }
        if (!this.a) {
            string = string + " ->";
        }
        return string;
    }

    public void n() {
        h h2 = com.corrodinggames.rts.game.units.h.L();
        if (h2 == null) {
            l.b("Editor not active");
            return;
        }
        if (this.b) {
            return;
        }
        h2.G = h2.G.a(this.a);
    }

    @Override
    public String a() {
        return "Change unit tab in editor";
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.f.g.bP) {
            return 0.8f;
        }
        return 0.5f;
    }

    @Override
    public int m() {
        if (this.b) {
            return 2;
        }
        return 4;
    }

    @Override
    public t f() {
        if (this.b) {
            return t.g;
        }
        return super.f();
    }

    @Override
    public u e() {
        if (this.b) {
            return u.i;
        }
        return super.e();
    }
}
