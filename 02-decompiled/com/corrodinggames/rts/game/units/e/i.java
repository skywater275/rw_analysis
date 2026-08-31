/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.h;
import com.corrodinggames.rts.game.units.e.i$1;
import com.corrodinggames.rts.game.units.e.i$2;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;

public strictfp class i
extends h
implements ak {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    float e = 0.0f;
    float f;
    boolean g;
    m h = new m();
    public static final s i = new i$1(109);
    public static final s j = new i$2(110);
    static ArrayList k = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.h.size());
        for (am am2 : this.h) {
            as2.a(am2);
        }
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.e = k2.g();
        this.f = k2.g();
        this.g = k2.e();
        this.h.clear();
        int n2 = k2.f();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = k2.o();
            if (am2 == null) continue;
            this.h.add(am2);
        }
        super.a(k2);
    }

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.s;
    }

    public static void L() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        a = l2.bO.a(R$drawable.hovercraft);
        c = l2.bO.a(R$drawable.hovercraft_shadow);
        b = l2.bO.a(R$drawable.hovercraft_dead);
        d = com.corrodinggames.rts.game.n.a(a);
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
        this.M = b;
        this.S(0);
        this.bT = false;
        this.f(true);
        this.a(com.corrodinggames.rts.game.units.ab.b);
        return true;
    }

    @Override
    public void a() {
        this.f(true);
        super.a();
    }

    public void f(boolean bl) {
        for (am am2 : this.h) {
            am2.cN = null;
            am2.eo = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -9.0f;
            am2.ep = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -9.0f;
            if (!bl) continue;
            am2.cj();
        }
        this.h.clear();
    }

    public i(boolean bl) {
        super(bl);
        this.T(20);
        this.U(32);
        this.ck = this.cj = 15.0f;
        this.cu = this.cv = 450.0f;
        this.M = a;
        this.N = c;
    }

    public static int a(m m2) {
        int n2 = 0;
        for (am am2 : m2) {
            n2 += am2.cw();
        }
        return n2;
    }

    public static boolean a(m m2, int n2, am am2) {
        int n3 = com.corrodinggames.rts.game.units.e.i.a(m2);
        return n3 + am2.cw() <= n2;
    }

    @Override
    public int bY() {
        return com.corrodinggames.rts.game.units.e.i.a(this.h);
    }

    @Override
    public int bZ() {
        return 4;
    }

    public static boolean a(am am2, am am3, boolean bl) {
        float f2 = 9.0f;
        float f3 = -180.0f;
        float f4 = 70.0f;
        float f5 = 0.0f;
        float f6 = 7.0f;
        return com.corrodinggames.rts.game.units.e.i.a(am2, am3, bl, f2, f3, f4, f5, f6);
    }

    public static boolean a(am am2, am am3, boolean bl, float f2, float f3, float f4, float f5, float f6) {
        float f7 = am2.eo + com.corrodinggames.rts.gameFramework.f.k(am2.cg + f3) * f6 - com.corrodinggames.rts.gameFramework.f.j(am2.cg + f3) * f5;
        float f8 = am2.ep + com.corrodinggames.rts.gameFramework.f.j(am2.cg + f3) * f6 + com.corrodinggames.rts.gameFramework.f.k(am2.cg + f3) * f5;
        if (!com.corrodinggames.rts.gameFramework.utility.y.a(am3, f7 += com.corrodinggames.rts.gameFramework.f.k(am2.cg + 90.0f) * (bl ? -f2 : f2), f8 += com.corrodinggames.rts.gameFramework.f.j(am2.cg + 90.0f) * (bl ? -f2 : f2))) {
            f7 += 10.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.y.a(am3, f7, f8)) {
            f7 -= 20.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.y.a(am3, f7, f8)) {
            f7 -= 10.0f;
            f8 += 10.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.y.a(am3, f7, f8)) {
            f8 -= 20.0f;
        }
        if (!com.corrodinggames.rts.gameFramework.utility.y.a(am3, f7, f8)) {
            return false;
        }
        am3.cN = null;
        am3.eo = f7;
        am3.ep = f8;
        am3.bZ += 0.1f;
        am3.cg = am2.cg + f3;
        am3.bR = am2;
        am3.bS = 45.0f;
        if (am3 instanceof y) {
            y y2 = (y)am3;
            y2.j(am3.cg);
            y2.az();
            y2.d(am3.eo + com.corrodinggames.rts.gameFramework.f.k(am3.cg + (bl ? -f2 : f2)) * f4, am3.ep + com.corrodinggames.rts.gameFramework.f.j(am3.cg + (bl ? -f2 : f2)) * f4);
            y2.ac = 0;
        }
        return true;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.bV || !this.bT()) {
            return;
        }
        if (this.cl == 0.0f && this.em != 3) {
            this.S(3);
        }
        if (this.g && !this.cK() && !this.cK) {
            this.f = com.corrodinggames.rts.gameFramework.f.a(this.f, f2);
            if (this.f == 0.0f) {
                this.f = 30.0f;
                if (this.h.size() == 0) {
                    this.g = false;
                } else {
                    boolean bl = this.h.size() % 2 == 0;
                    am am2 = (am)this.h.remove(this.h.size() - 1);
                    boolean bl2 = com.corrodinggames.rts.game.units.e.i.a(this, am2, bl);
                    if (!bl2) {
                        this.h.add(am2);
                    }
                    if (this.h.size() == 0) {
                        this.g = false;
                    }
                }
            }
        }
        this.e += 4.0f * f2;
        if (this.e > 360.0f) {
            this.e -= 360.0f;
        }
        this.eq = !this.g ? com.corrodinggames.rts.gameFramework.f.a(this.eq, 3.0f + com.corrodinggames.rts.gameFramework.f.j(this.e) * 1.5f, 0.1f * f2) : com.corrodinggames.rts.gameFramework.f.a(this.eq, 0.0f, 0.1f * f2);
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
            return 1.2f;
        }
        return 0.9f;
    }

    @Override
    public float A() {
        if (this.cK()) {
            return 1.8f;
        }
        return 1.4f;
    }

    @Override
    public float B() {
        return 0.1f;
    }

    @Override
    public float C() {
        return 0.03f;
    }

    @Override
    public float D() {
        return 0.05f;
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
    public boolean d(am am2, boolean bl) {
        if (this.g) {
            return false;
        }
        if (!com.corrodinggames.rts.game.units.e.i.a(this.h, 4, am2)) {
            return false;
        }
        if (am2 == this) {
            return false;
        }
        if (this.bX != am2.bX && !bl) {
            return false;
        }
        return com.corrodinggames.rts.gameFramework.utility.y.a(am2, true, true);
    }

    @Override
    public boolean e(am am2, boolean bl) {
        if (!this.d(am2, bl)) {
            return false;
        }
        this.C(am2);
        return true;
    }

    public void C(am am2) {
        am2.cN = this;
        this.h.add(am2);
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bS.l(am2);
    }

    @Override
    public void e(am am2) {
        if (am2.cN == this) {
            this.h.remove(am2);
            am2.cN = null;
        } else {
            com.corrodinggames.rts.gameFramework.l.g("Unit is not being transported");
        }
    }

    @Override
    public float bN() {
        return 12000.0f;
    }

    @Override
    public boolean bA() {
        return this.g;
    }

    public void M() {
        this.g = true;
        this.f = 30.0f;
    }

    public void ds() {
        this.g = false;
    }

    @Override
    public void a(s s2, boolean bl) {
        if (s2 == i) {
            this.M();
        }
        if (s2 == j) {
            this.ds();
        }
    }

    @Override
    public boolean cr() {
        return true;
    }

    @Override
    public int bB() {
        return this.h.size();
    }

    @Override
    public c cp() {
        return i.N();
    }

    @Override
    public ArrayList N() {
        return k;
    }

    @Override
    public boolean f() {
        return !this.cK();
    }

    @Override
    public boolean j() {
        return true;
    }

    @Override
    public m bz() {
        return this.h;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }

    static {
        k.add(i);
        k.add(j);
    }
}
