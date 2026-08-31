/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.PointF;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.d;
import com.corrodinggames.rts.game.units.e.b;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.w;

public strictfp class g
extends j
implements d {
    public boolean a;
    PointF[] b = new PointF[6];
    PointF[] c = new PointF[this.b.length];
    static Paint d;
    static Paint e;
    static Paint f;
    int g;
    float h;
    float i;
    int j;

    public ar f() {
        return com.corrodinggames.rts.game.units.ar.h;
    }

    @Override
    public PointF[] b() {
        return this.b;
    }

    @Override
    public PointF[] e_() {
        return this.c;
    }

    @Override
    public e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return dN[this.bX.R()];
    }

    @Override
    public boolean a(am am2) {
        return true;
    }

    @Override
    public e d() {
        if (this.bV) {
            return com.corrodinggames.rts.game.units.e.b.b;
        }
        return com.corrodinggames.rts.game.units.e.b.d[this.bX.R()];
    }

    @Override
    public e k() {
        return null;
    }

    @Override
    public e d(int n2) {
        return null;
    }

    @Override
    public boolean e() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = com.corrodinggames.rts.game.units.e.b.b;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.o, 0.8f, this.eo, this.ep);
        this.bq();
        return true;
    }

    public g(boolean bl) {
        super(bl);
        d = new Paint();
        d.a(40, 0, 255, 0);
        d.a(true);
        d.a(2.0f);
        d.a(Paint$Cap.b);
        e = new Paint();
        e.a(d);
        e.a(55, 255, 60, 60);
        f = new Paint();
        f.a(60, 255, 255, 255);
        this.T(20);
        this.U(20);
        this.cj = 10.0f;
        this.eo = -1000.0f;
        this.ep = -1000.0f;
        this.ck = this.cj;
        this.cu = this.cv = 170000.0f;
        this.M = com.corrodinggames.rts.game.units.e.b.b;
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            this.b[i2] = new PointF();
            this.c[i2] = new PointF();
        }
    }

    public static void a(float f2, d d2) {
        block4: {
            PointF[] pointFArray;
            PointF[] pointFArray2;
            block3: {
                y y2 = (y)((Object)d2);
                pointFArray2 = d2.b();
                pointFArray = d2.e_();
                am am2 = y2.X();
                boolean bl = y2.aN = am2 != null;
                if (am2 == null) break block3;
                for (int i2 = 0; i2 < pointFArray2.length; ++i2) {
                    PointF pointF = pointFArray2[i2];
                    PointF pointF2 = pointFArray[i2];
                    pointF.a = com.corrodinggames.rts.gameFramework.f.a(pointF.a, pointF2.a, 0.1f * f2);
                    pointF.b = com.corrodinggames.rts.gameFramework.f.a(pointF.b, pointF2.b, 0.1f * f2);
                    pointF.a += (pointF2.a - pointF.a) * 0.04f * f2;
                    pointF.b += (pointF2.b - pointF.b) * 0.04f * f2;
                    float f3 = am2.cj * 0.75f;
                    if (com.corrodinggames.rts.gameFramework.f.c(pointF.a - pointF2.a) < 1.0f) {
                        pointF2.a = com.corrodinggames.rts.gameFramework.f.d(-f3, f3);
                    }
                    if (!(com.corrodinggames.rts.gameFramework.f.c(pointF.b - pointF2.b) < 1.0f)) continue;
                    pointF2.b = com.corrodinggames.rts.gameFramework.f.d(-f3, f3);
                }
                break block4;
            }
            if (pointFArray2[0].a == 0.0f && pointFArray2[0].b == 0.0f) break block4;
            for (int i3 = 0; i3 < pointFArray2.length; ++i3) {
                PointF pointF = pointFArray2[i3];
                PointF pointF3 = pointFArray[i3];
                pointF.a = 0.0f;
                pointF.b = 0.0f;
                pointF3.a = 0.0f;
                pointF3.b = 0.0f;
            }
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bV) {
            com.corrodinggames.rts.game.units.g.a(f2, this);
        }
        this.cu = this.cv;
        ++this.g;
        this.h += f2;
        this.i += f2;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.a) {
            com.corrodinggames.rts.gameFramework.l.e("Stress test active");
            for (int i2 = 0; i2 < 6000; ++i2) {
                this.w();
            }
            this.ci();
            return;
        }
        if (this.i > 3.0f) {
            this.i = 0.0f;
            this.w();
        }
    }

    public void w() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ++this.j;
        int n2 = com.corrodinggames.rts.game.units.ar.ae.size();
        int n3 = com.corrodinggames.rts.gameFramework.f.a((w)this, 0, n2 - 1, 1 + this.j);
        as as2 = (as)com.corrodinggames.rts.game.units.ar.ae.get(n3);
        boolean bl2 = true;
        if (l.b == as2) {
            bl2 = false;
        }
        if (as2 == com.corrodinggames.rts.game.units.ar.S) {
            bl2 = false;
        }
        if (bl2) {
            am am2 = as2.a();
            am2.eo = com.corrodinggames.rts.gameFramework.f.a((w)this, 200, (int)l2.bL.i() - 200, 2 + this.g + this.j);
            am2.ep = com.corrodinggames.rts.gameFramework.f.a((w)this, 200, (int)l2.bL.j() - 200, 3 + this.g + this.j + this.j * 9);
            try {
                am2.Q(com.corrodinggames.rts.gameFramework.f.a((w)this, 0, 3, 4 + this.g + this.j + this.j * 9));
            }
            catch (com.corrodinggames.rts.game.b.f f2) {
                throw new RuntimeException(f2);
            }
            n.c(am2);
            if (am2.u()) {
                am2.a();
            }
            if (am2.bO()) {
                am2.a();
            }
        }
    }

    @Override
    public void a(float f2, boolean bl2) {
        if (!this.bV) {
            // empty if block
        }
    }

    @Override
    public float e(int n2) {
        return 0.0f;
    }

    @Override
    public float f(int n2) {
        return 0.0f;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return true;
    }

    @Override
    public void a(am am2, int n2) {
    }

    @Override
    public boolean b_() {
        return false;
    }

    @Override
    public int y() {
        return 850000;
    }

    @Override
    public float b(am am2) {
        return 1.0E7f;
    }

    @Override
    public float c(am am2) {
        return 1.0E7f;
    }

    @Override
    public float m() {
        return 30.0f;
    }

    @Override
    public float b(int n2) {
        return 100.0f;
    }

    @Override
    public float z() {
        return 0.0f;
    }

    @Override
    public float A() {
        if (this.cK()) {
            return 4.7f;
        }
        return 4.8f;
    }

    @Override
    public float B() {
        return 0.35f;
    }

    @Override
    public float c(int n2) {
        return 99.0f;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public float C() {
        return 0.04f;
    }

    @Override
    public float D() {
        return 0.1f;
    }

    @Override
    public boolean E() {
        return true;
    }

    @Override
    public float g(int n2) {
        return 10.0f;
    }

    @Override
    public boolean F() {
        return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && !this.bV;
    }

    @Override
    public float G() {
        return 1.0f;
    }

    @Override
    public float H() {
        return 1.0f;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean I() {
        return false;
    }

    @Override
    public boolean d(am am2) {
        return false;
    }

    @Override
    public boolean J() {
        return true;
    }

    @Override
    public float a(am am2, float f2, f f3) {
        f2 = 0.0f;
        return super.a(am2, f2, f3);
    }

    @Override
    public /* synthetic */ as r() {
        return this.f();
    }
}
