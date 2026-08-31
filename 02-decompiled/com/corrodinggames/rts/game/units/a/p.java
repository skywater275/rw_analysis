/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;

public abstract class p
extends s {
    public p(String string) {
        super("c__cut_" + string);
        this.g = 0.0f;
    }

    @Override
    public int b(am am2, boolean bl) {
        return -1;
    }

    @Override
    public int c() {
        return 0;
    }

    @Override
    public as i() {
        return null;
    }

    @Override
    public u e() {
        return u.i;
    }

    @Override
    public t f() {
        return t.g;
    }

    @Override
    public boolean g() {
        return false;
    }

    public y K() {
        y y2 = null;
        for (w w2 : w.er) {
            if (!(w2 instanceof y)) continue;
            y y3 = (y)w2;
            if (!y3.cG) continue;
            y2 = y3;
        }
        return y2;
    }

    public boolean L() {
        l l2 = l.B();
        y y2 = this.K();
        if (y2 != null) {
            if (y2 instanceof h) {
                return true;
            }
            return l2.bs == y2.bX;
        }
        return false;
    }

    @Override
    public String d() {
        return this.b();
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public boolean s() {
        return !this.L();
    }

    @Override
    public boolean G() {
        return false;
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.f.g.bP) {
            return 1.0f;
        }
        return 1.0f;
    }
}
