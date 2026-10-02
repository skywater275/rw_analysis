/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.l;

public abstract class w
extends s {
    public w(int n2) {
        super(n2);
    }

    public w(String string) {
        super(string);
    }

    @Override
    public int b(am am2, boolean bl) {
        if (!(am2 instanceof l)) {
            return 99;
        }
        return ((l)((Object)am2)).a(this.N(), bl);
    }

    @Override
    public float p(am am2) {
        if (!(am2 instanceof l)) {
            return -1.0f;
        }
        l l2 = (l)((Object)am2);
        j j2 = l2.dw();
        if (j2 == null) {
            return -1.0f;
        }
        if (!this.d(j2.j)) {
            return -1.0f;
        }
        float f2 = j2.m;
        if (f2 < 0.0f) {
            return 0.0f;
        }
        if (f2 > 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public float K() {
        return 0.01f;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public u e() {
        return u.c;
    }
}
