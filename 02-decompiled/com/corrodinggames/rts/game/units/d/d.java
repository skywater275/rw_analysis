/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.g;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.d.a;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp abstract class d
extends y {
    e m;
    public Rect n = new Rect();
    public Rect o = new Rect();
    public static e p = null;
    public static e[] q = new e[10];
    int r = 1;
    int s = 0;

    public boolean ds() {
        return false;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.r);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        if (k2.b() >= 15) {
            int n2 = k2.f();
            this.R(n2);
        }
        super.a(k2);
    }

    public static boolean a(as as2, float f2, float f3, n n2) {
        l l2 = l.B();
        y y2 = (y)com.corrodinggames.rts.game.units.am.a(as2);
        l2.bL.b(f2, f3);
        y2.eo = (float)l2.bL.T + y2.cZ();
        y2.ep = (float)l2.bL.U + y2.cZ();
        y2.b(n2);
        boolean bl = y2.c((n)null);
        return bl;
    }

    public void R(int n2) {
        this.r = n2;
    }

    @Override
    public e d(int n2) {
        return null;
    }

    @Override
    public e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return q[this.bX.R()];
    }

    public static void dt() {
        l l2 = l.B();
        p = l2.bO.a(R$drawable.unit_icon_building);
        q = com.corrodinggames.rts.game.n.a(p);
    }

    public d(boolean bl) {
        super(bl);
        this.cg = -90.0f;
        this.bT = false;
    }

    @Override
    public void f_() {
        this.bT = false;
    }

    public boolean L() {
        this.a(com.corrodinggames.rts.game.units.ab.d);
        return false;
    }

    @Override
    public boolean e() {
        l l2 = l.B();
        l2.bU.a(this);
        if (this.cm < 1.0f) {
            this.a(com.corrodinggames.rts.game.units.ab.a);
            return false;
        }
        this.s = 0;
        return this.L();
    }

    @Override
    public Rect cd() {
        return this.o;
    }

    @Override
    public Rect cc() {
        return this.n;
    }

    public static boolean a(y y2, as as2, ao ao2, int n2, int n3, int n4) {
        l l2 = l.B();
        b b2 = l2.bL;
        if (!b2.c(n2, n3)) {
            return false;
        }
        boolean bl = false;
        if (b2.E && l2.bs.N != null) {
            if (!b2.G && l2.bs.N[n2][n3] == 10) {
                return false;
            }
            boolean bl2 = bl = l2.bs.N[n2][n3] >= 5;
        }
        if (d.a(y2, as2, ao2, n2, n3, bl)) {
            if (as2.p()) {
                g g2 = b2.e(n2, n3);
                return g2 != null && g2.i;
            }
            return !a.a(l2.bs, n2, n3, n4);
        }
        return false;
    }

    public static boolean a(y y2, as as2, ao ao2, int n2, int n3, boolean bl) {
        return d.a(y2, as2, ao2, n2, n3, bl, null) == null;
    }

    public static String a(y y2, as as2, ao ao2, int n2, int n3, boolean bl, n n4) {
        Object object;
        l l2 = l.B();
        if (!l2.bL.c(n2, n3)) {
            return "{0}";
        }
        be be2 = as2.q();
        if (be2 != null && (object = be2.a(y2, n2, n3)) != null) {
            return object;
        }
        if (as2 == com.corrodinggames.rts.game.units.ar.d || ao2 == com.corrodinggames.rts.game.units.ao.e) {
            if (!l2.bU.a(l2.bU.A, n2, n3)) {
                return null;
            }
            return "{3}";
        }
        object = l2.bL.e(n2, n3);
        if (object != null && ((g)object).i) {
            if (as2.p()) {
                return null;
            }
            return "{0}";
        }
        if (ao2 == com.corrodinggames.rts.game.units.ao.d) {
            return null;
        }
        if (ao2 == com.corrodinggames.rts.game.units.ao.f) {
            if (!l2.bU.a(l2.bU.C, n2, n3)) {
                return null;
            }
            return "{0}";
        }
        if (ao2 == com.corrodinggames.rts.game.units.ao.g) {
            if (!l2.bU.a(l2.bU.D, n2, n3)) {
                return null;
            }
            return "{0}";
        }
        if (ao2 == com.corrodinggames.rts.game.units.ao.h) {
            if (!l2.bU.a(l2.bU.E, n2, n3)) {
                return null;
            }
            return "{0}";
        }
        if (l2.bU.a(l2.bU.z, n2, n3, bl)) {
            boolean bl2 = false;
            if (n4 != null && !l2.bL.a(n2, n3, n4)) {
                bl2 = true;
            }
            if (!bl2) {
                return "{0}";
            }
        }
        return null;
    }

    public static am b(int n2, int n3) {
        l l2 = l.B();
        l2.bL.a(n2, n3);
        float f2 = l2.bL.T + l2.bL.p;
        float f3 = l2.bL.U + l2.bL.q;
        for (am am2 : l2.cc.b(f2, f3, 0.0f)) {
            if (!am2.bI() || am2.bV || !am2.c(f2, f3, 0.0f)) continue;
            return am2;
        }
        return null;
    }

    @Override
    public void a(int n2) {
    }

    public static am g(as as2) {
        if (as2 == null) {
            throw new RuntimeException("type is null");
        }
        return as2.a();
    }

    @Override
    public boolean I() {
        return false;
    }

    @Override
    public ao h() {
        return com.corrodinggames.rts.game.units.ao.a;
    }

    @Override
    public boolean i() {
        return false;
    }

    @Override
    public boolean Q() {
        return false;
    }

    @Override
    public float z() {
        return 0.0f;
    }

    @Override
    public float A() {
        return 0.0f;
    }

    @Override
    public boolean b_() {
        return false;
    }

    public Paint f() {
        int n2;
        l l2 = l.B();
        PorterDuffColorFilter porterDuffColorFilter = null;
        if (this.cm < 1.0f) {
            n2 = Color.a((int)(40.0f + this.cm * 200.0f), 140, 255, 140);
            porterDuffColorFilter = aX;
        } else {
            n2 = Color.a(255, 255, 255, 255);
        }
        if (this.cp) {
            if (this.cs) {
                n2 = Color.a(200, 20, 255, 20);
                porterDuffColorFilter = aY;
            }
            if (this.ct) {
                n2 = Color.a(200, 255, 20, 20);
                porterDuffColorFilter = aZ;
            }
            if (this.cq) {
                n2 = Color.a(70, 70, 70, 245);
                porterDuffColorFilter = ba;
                if (this.ct) {
                    n2 = Color.a(70, 255, 20, 20);
                    porterDuffColorFilter = aZ;
                }
            }
            if (this.cr) {
                n2 = Color.a(150, 100, 100, 100);
            }
        }
        boolean bl = l2.bQ.renderAntiAlias;
        if (!this.dk()) {
            bl = false;
            if (l2.cX < 1.0f) {
                bl = true;
            }
        }
        if (this.co) {
            bl = com.corrodinggames.rts.game.units.ar.ag;
        }
        return this.a(n2, porterDuffColorFilter, bl);
    }

    @Override
    public boolean c(float f2) {
        l l2 = l.B();
        int n2 = this.s * this.es;
        int n3 = 0;
        RectF rectF = this.cF();
        dv.a(n2, n3, n2 + this.es, n3 + this.et);
        l2.bO.a(this.M, dv, rectF, this.f());
        return true;
    }

    @Override
    public void d(float f2) {
        super.d(f2);
        if (this.m == null) {
            return;
        }
        l l2 = l.B();
        if (this.ds()) {
            l2.bO.b(this.m, this.eo - (float)((int)(this.m.t + 0.1f)) - l2.cw, this.ep - (float)((int)(this.m.u + 0.1f)) - l2.cx, this.f());
        } else {
            int n2 = 0;
            int n3 = 0;
            RectF rectF = this.cF();
            dv.a(n2, n3, n2 + this.es, n3 + this.et);
            l2.bO.a(this.m, dv, rectF, this.f());
        }
    }

    @Override
    public boolean bI() {
        return true;
    }
}
