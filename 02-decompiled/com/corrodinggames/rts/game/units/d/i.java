/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.k;
import com.corrodinggames.rts.game.units.d.l;
import com.corrodinggames.rts.gameFramework.utility.m;

public strictfp abstract class i
extends d
implements l {
    public static final Paint y = new Paint();
    k z = this.du();
    Rect A = new Rect();
    Rect B = new Rect();

    public i(boolean bl) {
        super(bl);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.r);
        this.z.a(as2);
        super.a(as2);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.k k2) {
        if (k2.b() >= 69) {
            int n2 = k2.f();
            this.R(n2);
        }
        this.z.a(k2);
        super.a(k2);
    }

    public k du() {
        return new k(this);
    }

    @Override
    public void b(j j2) {
    }

    @Override
    public boolean c(j j2) {
        return true;
    }

    @Override
    public void a(j j2) {
        float f2 = this.z.b != null ? this.cj * 2.0f : this.cj * 3.0f;
        am am2 = this.z.a(j2, f2, false, 0.0f);
        if (am2 != null) {
            if (am2.ep - am2.cj < this.ep + (float)this.dv()) {
                am2.ep = this.ep + (float)this.dv() + am2.cj;
            }
            com.corrodinggames.rts.game.n.c(am2);
        }
    }

    public int dv() {
        return -100;
    }

    @Override
    public int f(boolean bl) {
        return this.z.a(com.corrodinggames.rts.game.units.a.s.i, bl, true);
    }

    @Override
    public final int a(c c2, boolean bl) {
        return this.z.a(c2, bl);
    }

    @Override
    public j dw() {
        return this.z.b();
    }

    @Override
    public b bD() {
        return this.z.c();
    }

    @Override
    public m dx() {
        return this.z.c;
    }

    @Override
    public int h(as as2) {
        return this.z.a(as2);
    }

    @Override
    public boolean dy() {
        return this.z.a();
    }

    @Override
    public void dz() {
        this.z.e = 1.0f;
    }

    @Override
    public void a(PointF pointF) {
        this.z.b = pointF;
    }

    @Override
    public boolean dA() {
        return false;
    }

    @Override
    public float bV() {
        if (this.bT() && !this.z.a()) {
            return this.z.e;
        }
        return super.bV();
    }

    @Override
    public s e(as as2) {
        return this.z.b(as2);
    }

    @Override
    public void a(s s2, boolean bl) {
        this.z.a(s2, bl, null, null);
    }

    @Override
    public void b(s s2, boolean bl) {
        this.z.a(s2, bl);
    }

    @Override
    public void a(s s2) {
        this.z.a(s2);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
        this.z.a(f2);
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }

    @Override
    public void bv() {
        com.corrodinggames.rts.game.n.a(this);
        this.z.a(true);
        super.bv();
    }

    @Override
    public void a() {
        com.corrodinggames.rts.game.n.a(this);
        this.z.a(true);
        super.a();
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public void a(am am2, int n2) {
        throw new RuntimeException("Unit cannot shoot");
    }

    @Override
    public float m() {
        return 0.0f;
    }

    @Override
    public float b(int n2) {
        return 0.0f;
    }

    @Override
    public float c(int n2) {
        return 0.0f;
    }

    @Override
    public void ca() {
        if (this.z.b != null) {
            com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
            float f2 = (int)(this.eo - l2.cw);
            float f3 = (int)(this.ep - l2.cx);
            float f4 = (int)(this.z.b.a - l2.cw);
            float f5 = (int)(this.z.b.b - l2.cx);
            l2.bO.a(f2, f3, f4, f5, y);
        }
    }

    @Override
    public int a(g g2) {
        return this.z.a(g2);
    }

    static {
        y.a(255, 0, 255, 0);
        y.a(1.5f);
        y.a(true);
    }
}
