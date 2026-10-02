/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d.a;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.a.a$1;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public strictfp class a
extends b {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    public static s e = new a$1(102);
    static ArrayList f = new ArrayList();

    @Override
    public e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return d[this.bX.R()];
    }

    public static void b() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        a = l2.bO.a(R$drawable.anti_air_top);
        b = l2.bO.a(R$drawable.anti_air_top_l2);
        c = l2.bO.a(R$drawable.unit_icon_building_air_turrent);
        d = com.corrodinggames.rts.game.n.a(c);
    }

    public a(boolean bl) {
        super(bl);
        this.cu = this.cv = 800.0f;
    }

    @Override
    public float m() {
        if (!this.j) {
            return 250.0f;
        }
        return 320.0f;
    }

    @Override
    public float b(int n2) {
        if (!this.j) {
            return 80.0f;
        }
        return 70.0f;
    }

    @Override
    public float e(int n2) {
        if (!this.j) {
            return super.e(n2);
        }
        if (n2 == 2) {
            return 25.0f;
        }
        return super.e(n2);
    }

    @Override
    public PointF E(int n2) {
        if (!this.j || n2 == 0) {
            return super.E(n2);
        }
        float f2 = this.E() ? this.cg : this.cL[n2].a;
        PointF pointF = this.G(n2);
        float f3 = pointF.a + com.corrodinggames.rts.gameFramework.f.k(f2 += n2 == 1 ? -30.0f : 30.0f) * 10.0f;
        float f4 = pointF.b + com.corrodinggames.rts.gameFramework.f.j(f2) * 10.0f;
        bg.a(f3, f4);
        return bg;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = com.corrodinggames.rts.game.f.a(this, pointF.a, pointF.b);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.t = 0.3f;
        f2.r = 6.0f;
        if (!this.j) {
            f2.ar = Color.a(255, 230, 230, 50);
            f2.U = 60.0f;
            f2.h = 220.0f;
        } else {
            f2.ar = Color.a(255, 230, 50, 50);
            f2.U = 60.0f;
            f2.h = 250.0f;
            f2.t = 0.5f;
            f2.r = 7.0f;
        }
        f2.P = (short)4;
        f2.x = 1.0f;
        f2.l = am2;
        f2.aH = false;
        f2.aI = 0.0f;
        f2.aJ = 0.0f;
        f2.aM = true;
        f2.aQ = true;
        f2.aG = true;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.f.c(-0.07f, 0.07f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.3f, f3, pointF.a, pointF.b);
        l2.bR.a(f2, -1118720);
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
    }

    @Override
    public ar K() {
        if (this.j) {
            return com.corrodinggames.rts.game.units.ar.T;
        }
        return com.corrodinggames.rts.game.units.ar.g;
    }

    @Override
    public e d(int n2) {
        if (!this.j) {
            return a;
        }
        return b;
    }

    @Override
    public boolean af() {
        return true;
    }

    @Override
    public boolean ag() {
        return false;
    }

    @Override
    public void s(float f2) {
        int n2 = 0;
        if (this.cL[n2].a()) {
            this.cL[n2].a += this.c(n2) * f2 * 0.1f;
        }
    }

    @Override
    public float g(int n2) {
        return 9.0f;
    }

    @Override
    public float c(int n2) {
        return 6.0f;
    }

    @Override
    public float B() {
        return 1.0f;
    }

    @Override
    public boolean u(int n2) {
        if (!this.j) {
            return super.u(n2);
        }
        if (n2 == 0) {
            return false;
        }
        return super.u(n2);
    }

    @Override
    public int v(int n2) {
        if (!this.j) {
            return -1;
        }
        if (n2 == 1) {
            return 0;
        }
        if (n2 == 2) {
            return 0;
        }
        return -1;
    }

    @Override
    public int bl() {
        return 3;
    }

    @Override
    public boolean r(int n2) {
        return this.j || n2 <= 1;
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(e.N())) {
            this.a(2);
            this.W();
        }
    }

    @Override
    public c cm() {
        if (!this.j) {
            return e.N();
        }
        return s.i;
    }

    @Override
    public void a(ArrayList arrayList) {
        arrayList.clear();
    }

    @Override
    public void a(int n2) {
        if (n2 == 1) {
            this.j = false;
        } else if (n2 == 2 && !this.j) {
            this.j = true;
            this.cv += 600.0f;
            this.cu += 600.0f;
        }
    }

    @Override
    public ArrayList N() {
        return f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }

    static {
        f.add(e);
    }
}
