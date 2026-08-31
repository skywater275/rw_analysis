/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.m;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.game.units.d.s;
import com.corrodinggames.rts.game.units.e.b;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public strictfp class r
extends d
implements com.corrodinggames.rts.game.units.d {
    static e a = null;
    static e[] b = new e[10];
    static e c = null;
    float d;
    public static s e = new s(true);
    Rect f = new Rect();
    Rect g = new Rect();
    static ArrayList h = new ArrayList();
    PointF[] i = new PointF[6];
    PointF[] j = new PointF[this.i.length];

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        super.a(k2);
    }

    public ar K() {
        return com.corrodinggames.rts.game.units.ar.B;
    }

    public static void M() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.repair_bay);
        c = l2.bO.a(R$drawable.repair_bay_dead);
        b = com.corrodinggames.rts.game.n.a(a);
    }

    @Override
    public boolean L() {
        this.M = c;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.c);
        return true;
    }

    @Override
    public e d() {
        if (this.bV) {
            return c;
        }
        if (this.bX == null) {
            return b[b.length - 1];
        }
        return b[this.bX.R()];
    }

    @Override
    public e k() {
        return null;
    }

    @Override
    public void a(int n2) {
    }

    public r(boolean bl) {
        super(bl);
        this.M = a;
        this.b(a);
        this.ck = this.cj = 30.0f;
        this.cu = this.cv = 1000.0f;
        this.n.a(-1, -1, 1, 1);
        this.o.a(-1, -1, 1, 1);
        for (int j = 0; j < this.i.length; ++j) {
            this.i[j] = new PointF();
            this.j[j] = new PointF();
        }
    }

    @Override
    public int y() {
        return 230;
    }

    @Override
    public float c(am am2) {
        return 0.2f;
    }

    @Override
    public boolean a(am am2) {
        return !am2.q();
    }

    public static au a(y y2, float f2, float f3, boolean bl) {
        l l2 = l.B();
        e.a((float)y2.y() + f3, bl);
        l2.cc.a(y2.eo, y2.ep, (float)y2.y() + f3, y2, f2, e);
        am am2 = com.corrodinggames.rts.game.units.d.r.e.e;
        if (am2 != null) {
            au au2 = y2.ao();
            au2.b(am2);
            if (au2 != null) {
                au2.k = f3;
                au2.m = true;
                return au2;
            }
        }
        return null;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
        this.d += f2;
        if (this.aq() && this.d > 40.0f) {
            this.d = 0.0f;
            com.corrodinggames.rts.game.units.d.r.a(this, f2, 0.0f, false);
        }
        if (!this.bV) {
            com.corrodinggames.rts.game.units.e.b.a(f2, this);
        }
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }

    @Override
    public void a(float f2, boolean bl) {
        super.a(f2, bl);
        if (!this.bV) {
            com.corrodinggames.rts.game.units.e.b.b(f2, this);
        }
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
    public float b(int n2) {
        return 0.0f;
    }

    @Override
    public float c(int n2) {
        return 0.0f;
    }

    @Override
    public PointF E(int n2) {
        PointF pointF = this.G(n2);
        float f2 = pointF.a + 0.0f;
        float f3 = pointF.b - 33.0f;
        bg.a(f2, f3);
        return bg;
    }

    @Override
    public ArrayList N() {
        return h;
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
    public float m() {
        return this.y();
    }

    @Override
    public void e(float f2) {
        super.e(f2);
        float f3 = this.y();
        com.corrodinggames.rts.gameFramework.utility.y.a((am)this, f3);
    }

    @Override
    public boolean g(am am2, boolean bl) {
        return true;
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }

    static {
        h.add(new m(true));
        h.add(new com.corrodinggames.rts.game.units.a.n());
    }
}
