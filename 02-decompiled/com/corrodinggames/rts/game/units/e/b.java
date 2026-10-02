/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.m;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.v;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.ai;
import java.util.ArrayList;

public strictfp class b
extends j
implements d {
    static e a = null;
    public static e b = null;
    static e c = null;
    public static e[] d = new e[10];
    public static e e = null;
    public static e f = null;
    static e g = null;
    public static e[] h = new e[10];
    PointF[] i = new PointF[6];
    PointF[] j = new PointF[this.i.length];
    static Paint k;
    static Paint l;
    static Paint m;
    static s n;

    public ar f() {
        return com.corrodinggames.rts.game.units.ar.h;
    }

    @Override
    public PointF[] b() {
        return this.i;
    }

    @Override
    public PointF[] e_() {
        return this.j;
    }

    @Override
    public e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return h[this.bX.R()];
    }

    public static void K() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        a = l2.bO.a(R$drawable.builder);
        b = l2.bO.a(R$drawable.builder_dead);
        c = com.corrodinggames.rts.game.units.e.b.a(a, a.m(), a.l());
        d = com.corrodinggames.rts.game.n.a(a);
        e = l2.bO.a(R$drawable.builder_charge);
        f = l2.bO.a(R$drawable.builder_decharge);
        g = l2.bO.a(R$drawable.unit_icon_builder);
        h = com.corrodinggames.rts.game.n.a(g);
    }

    @Override
    public boolean a(am am2) {
        if (am2.q()) {
            return false;
        }
        return am2.bI();
    }

    @Override
    public e d() {
        if (this.bV) {
            return b;
        }
        return d[this.bX.R()];
    }

    @Override
    public e k() {
        return c;
    }

    @Override
    public e d(int n2) {
        return null;
    }

    @Override
    public boolean e() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.b);
        return true;
    }

    public b(boolean bl) {
        super(bl);
        k = new Paint();
        k.a(40, 0, 255, 0);
        k.a(true);
        k.a(2.0f);
        k.a(Paint$Cap.b);
        l = new Paint();
        l.a(k);
        l.a(55, 255, 60, 60);
        m = new Paint();
        m.a(60, 255, 255, 255);
        this.T(20);
        this.U(20);
        this.cj = 10.0f;
        this.ck = this.cj + 2.0f;
        this.cu = this.cv = 170.0f;
        this.M = a;
        for (int i2 = 0; i2 < this.i.length; ++i2) {
            this.i[i2] = new PointF();
            this.j[i2] = new PointF();
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

    public static void b(float f2, d d2) {
        y y2 = (y)((Object)d2);
        am am2 = y2.X();
        if (am2 != null) {
            boolean bl = y2.Y();
            if (!bl && y2.aO) {
                return;
            }
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            PointF[] pointFArray = d2.b();
            Paint paint = k;
            if (bl) {
                paint = l;
            }
            ai ai2 = y2.bn();
            for (int i2 = 0; i2 < pointFArray.length; ++i2) {
                PointF pointF = pointFArray[i2];
                float f3 = am2.eo + pointF.a - l2.cw;
                float f4 = am2.ep - am2.eq + pointF.b - l2.cx;
                l2.bO.a(ai2.a + pointF.a * 0.15f - l2.cw, ai2.b - ai2.c + pointF.b * 0.15f - l2.cx - y2.eq, f3, f4, paint);
                l2.bO.k();
                l2.bO.b(f3, f4);
                l2.bO.a(0.5f, 0.5f);
                if (bl) {
                    l2.bO.a(f, 0.0f, 0.0f, m);
                } else {
                    l2.bO.a(e, 0.0f, 0.0f, m);
                }
                l2.bO.l();
            }
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bV) {
            com.corrodinggames.rts.game.units.e.b.a(f2, this);
        }
    }

    @Override
    public void a(float f2, boolean bl) {
        super.a(f2, bl);
        if (!this.bV) {
            com.corrodinggames.rts.game.units.e.b.b(f2, this);
        }
    }

    @Override
    public float e(int n2) {
        return 30.0f;
    }

    @Override
    public float f(int n2) {
        return 1.3f;
    }

    @Override
    public boolean c(float f2) {
        float f3;
        if (!super.c(f2)) {
            return false;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!this.bV && (f3 = this.cL[0].f / this.e(0)) != 0.0f) {
            ai ai2 = this.bn();
            l2.bO.i();
            l2.bO.b(ai2.a - l2.cw, ai2.b - ai2.c - l2.cx);
            l2.bO.a(f3, f3);
            if (this.Y()) {
                l2.bO.a(f, 0.0f, 0.0f, null);
            } else {
                l2.bO.a(e, 0.0f, 0.0f, null);
            }
            l2.bO.j();
        }
        return true;
    }

    @Override
    public void a(am am2, int n2) {
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
        if (this.cK()) {
            return 0.6f;
        }
        return 0.8f;
    }

    @Override
    public float A() {
        if (this.cK()) {
            return 1.7f;
        }
        return 3.8f;
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
    public void a(s s2, boolean bl) {
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(n);
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.a, 1, 1));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.f, 1, 2));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.g, 1, 3));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.b, 1, 4));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.c, 1, 5));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.d, 1, 6));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.y, 1, 7));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.B, 1, 8));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.J, 1, 9));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.G, 1, 10));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.C, 1, 14));
        arrayList.add(new v(com.corrodinggames.rts.game.units.ar.D, 1, 15));
    }

    @Override
    public ArrayList N() {
        return this.f().a(this.V());
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
    public boolean g(am am2, boolean bl) {
        return true;
    }

    @Override
    public /* synthetic */ as r() {
        return this.f();
    }

    static {
        n = new m(false);
    }
}
