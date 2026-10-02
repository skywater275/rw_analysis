/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.g;
import com.corrodinggames.rts.gameFramework.h.a;

final class g$2
extends w {
    g$2(int n2) {
        super(n2);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public String a() {
        return a.a("units.extractor.upgrade.descriptionT3", new Object[0]);
    }

    @Override
    public String b() {
        return a.a("gui.actions.upgradeT3", new Object[0]);
    }

    @Override
    public int c() {
        return ar.a.c(3);
    }

    @Override
    public float K() {
        return 3.0E-4f;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        g g2 = (g)am2;
        if (g2.b != 2 || g2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    public ar L() {
        return null;
    }

    @Override
    public t f() {
        return t.c;
    }

    @Override
    public /* synthetic */ as i() {
        return this.L();
    }
}
