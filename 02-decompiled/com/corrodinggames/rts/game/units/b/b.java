/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.b;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.w;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp abstract class b
extends w {
    float h;
    boolean i = false;
    float j;
    Boolean k;
    Boolean l;
    public static e m = null;
    public static e[] n = new e[10];

    public b(boolean bl) {
        super(bl);
    }

    @Override
    public void a(as as2) {
        as2.a(this.h);
        as2.a(this.i);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.h = k2.g();
        this.i = k2.e();
        super.a(k2);
    }

    @Override
    public e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return n[this.bX.R()];
    }

    public static void K() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        m = l2.bO.a(R$drawable.unit_icon_air);
        n = com.corrodinggames.rts.game.n.a(m);
    }

    @Override
    public ao h() {
        return com.corrodinggames.rts.game.units.ao.d;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.bV) {
            if (this.eq > 0.0f) {
                this.h += 0.06f * f2;
                this.eq -= this.h * f2;
            } else {
                if (this.k == null) {
                    this.k = this.cK();
                }
                if (this.l == null) {
                    this.l = this.cJ();
                }
                if (!this.i) {
                    this.i = true;
                    if (this.k.booleanValue()) {
                        this.a(com.corrodinggames.rts.game.units.ab.a);
                        if (this.l.booleanValue()) {
                            com.corrodinggames.rts.gameFramework.l.B().bR.a(this.eo, this.ep, 0.0f, 0, 0.0f, 0.0f, this.cg);
                        }
                    } else {
                        this.a(com.corrodinggames.rts.game.units.ab.b);
                    }
                    this.h = 0.0f;
                } else if (this.k.booleanValue()) {
                    if (this.eq > -10.0f) {
                        this.h += 8.0E-4f * f2;
                        this.eq -= this.h * f2;
                        if (this.l.booleanValue()) {
                            this.j += f2;
                            if (this.j > 30.0f) {
                                this.j = 0.0f;
                                if (this.s_()) {
                                    l l2 = com.corrodinggames.rts.gameFramework.l.B();
                                    com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.b(this.eo, this.ep, this.eq, this.cg);
                                    if (e2 != null) {
                                        e2.P = 0.0f;
                                        e2.Q = -0.1f;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    this.eq = 0.0f;
                }
            }
            return;
        }
    }

    @Override
    public boolean e() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.eq > -1.0f) {
            for (int j = 0; j < 3; ++j) {
                l2.bR.e(this.eo, this.ep, this.eq);
            }
        }
        return super.e();
    }
}
