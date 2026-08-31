/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.d.d;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp abstract class h
extends j {
    float l;
    public static e m = null;
    public static e[] n = new e[10];

    public h(boolean bl) {
        super(bl);
    }

    @Override
    public e v() {
        if (this.bX.k == -1) {
            return null;
        }
        if (this.dd()) {
            return j.dO[this.bX.R()];
        }
        return n[this.bX.R()];
    }

    public static void K() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        m = l2.bO.a(R$drawable.unit_icon_hover);
        n = com.corrodinggames.rts.game.n.a(m);
    }

    @Override
    public ao h() {
        return com.corrodinggames.rts.game.units.ao.f;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
        if (this.cK()) {
            if (this.cf > 0.0f) {
                this.l += f2;
            }
            if (this.l > 10.0f) {
                this.l = 0.0f;
                if (this.s_()) {
                    float f3;
                    l l2 = com.corrodinggames.rts.gameFramework.l.B();
                    float f4 = this.eo + f.k(this.cg) * 4.0f;
                    com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.b(f4, f3 = this.ep + f.j(this.cg) * 4.0f, 0.0f, d.a, false, com.corrodinggames.rts.gameFramework.d.h.b);
                    if (e2 != null) {
                        e2.aq = 0;
                        e2.ap = 13;
                        e2.ar = 1;
                        e2.r = true;
                        e2.E = 0.8f;
                        e2.W = 80.0f;
                        e2.V = 80.0f;
                        e2.P = -f.k(this.cg) * 0.1f;
                        e2.Q = -f.j(this.cg) * 0.1f;
                        e2.Y = f.c(-180.0f, 180.0f);
                    }
                }
            }
        }
    }
}
