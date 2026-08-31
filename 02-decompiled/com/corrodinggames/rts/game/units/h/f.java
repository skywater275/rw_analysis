/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.h;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.w;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp abstract class f
extends w {
    float m;
    float n;
    boolean o = false;
    public static e p = null;
    public static e[] q = new e[10];

    public f(boolean bl) {
        super(bl);
    }

    @Override
    public void a(as as2) {
        as2.a(this.n);
        as2.a(this.o);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.n = k2.g();
        this.o = k2.e();
        super.a(k2);
    }

    @Override
    public e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return q[this.bX.R()];
    }

    public static void M() {
        l l2 = l.B();
        p = l2.bO.a(R$drawable.unit_icon_water);
        q = com.corrodinggames.rts.game.n.a(p);
    }

    @Override
    public ao h() {
        return com.corrodinggames.rts.game.units.ao.e;
    }

    @Override
    public boolean cv() {
        return true;
    }

    public boolean K() {
        return true;
    }

    public void s(float f2) {
        float f3 = 0.0f;
        if (this.eq != f3) {
            this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, f3, 0.2f * f2);
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.bV) {
            if (this.eq > -10.0f) {
                this.n += 0.002f * f2;
                this.eq -= this.n * f2;
            } else {
                this.eq = -10.0f;
                if (!this.o) {
                    this.o = true;
                }
            }
            return;
        }
        if (!this.bT() || this.bV) {
            return;
        }
        this.s(f2);
        if (this.K()) {
            if (this.cf != 0.0f) {
                this.m += f2;
            }
            if (this.m > 10.0f) {
                this.m = 0.0f;
                if (this.s_()) {
                    float f3;
                    l l2 = l.B();
                    float f4 = this.cg + 180.0f;
                    if (this.cf < 0.0f) {
                        f4 += 180.0f;
                    }
                    if ((f3 = this.cj - 6.0f) < 4.0f) {
                        f3 = 4.0f;
                    }
                    float f5 = this.eo + com.corrodinggames.rts.gameFramework.f.k(f4) * f3;
                    float f6 = this.ep + com.corrodinggames.rts.gameFramework.f.j(f4) * f3;
                    l2.bR.b(f5, f6, 0.0f, f4);
                }
            }
        }
    }
}
