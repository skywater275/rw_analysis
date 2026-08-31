/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a$1;
import com.corrodinggames.rts.game.a.a$10;
import com.corrodinggames.rts.game.a.a$11;
import com.corrodinggames.rts.game.a.a$12;
import com.corrodinggames.rts.game.a.a$13;
import com.corrodinggames.rts.game.a.a$2;
import com.corrodinggames.rts.game.a.a$3;
import com.corrodinggames.rts.game.a.a$4;
import com.corrodinggames.rts.game.a.a$5;
import com.corrodinggames.rts.game.a.a$6;
import com.corrodinggames.rts.game.a.a$7;
import com.corrodinggames.rts.game.a.a$8;
import com.corrodinggames.rts.game.a.a$9;
import com.corrodinggames.rts.game.a.b;
import com.corrodinggames.rts.game.a.c;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.a.e;
import com.corrodinggames.rts.game.a.f;
import com.corrodinggames.rts.game.a.g;
import com.corrodinggames.rts.game.a.h;
import com.corrodinggames.rts.game.a.i;
import com.corrodinggames.rts.game.a.j;
import com.corrodinggames.rts.game.a.k;
import com.corrodinggames.rts.game.a.l;
import com.corrodinggames.rts.game.a.m;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.aq;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.d.t;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.utility.u;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class a
extends n {
    public static boolean as;
    final int at = 3000;
    int au;
    int av;
    int aw;
    int ax;
    int ay;
    int az;
    int aA;
    int aB;
    int aC;
    int aD;
    int aE;
    int aF;
    int aG;
    int aH;
    public int aI;
    int aJ = 0;
    boolean aK;
    float aL;
    float aM;
    float aN;
    float aO;
    float aP;
    float aQ;
    float aR = 0.0f;
    float aS = 0.0f;
    float aT;
    float aU;
    int aV;
    float aW;
    public boolean aX;
    public boolean aY;
    public boolean aZ;
    int ba;
    int bb;
    int bc;
    boolean bd = true;
    boolean be = true;
    boolean bf = false;
    com.corrodinggames.rts.game.units.f bg;
    boolean bh;
    boolean bi;
    boolean bj;
    boolean bk;
    int bl;
    ConcurrentLinkedQueue bm = new ConcurrentLinkedQueue();
    ArrayList bn = new ArrayList();
    PointF bo = new PointF();
    Paint bp;
    ArrayList bq = new ArrayList();
    d br = new a$1(this, "attackingUnitsLand");
    d bs = new a$6(this, "attackingUnitsHover");
    d bt = new a$7(this, "attackingUnitsAir");
    d bu = new a$8(this, "attackingUnitsWater");
    d bv = new a$9(this, "buildingUnits");
    d bw = new a$10(this, "transportUnits");
    d bx = new a$11(this, "transportUnitsFlying");
    d by = new a$12(this, "transportUnitsNonFlying");
    d bz = new a$13(this, "builderUnits");
    d bA = new a$2(this, "harvesterUnits");
    d bB = new a$3(this, "extractorUnits");
    d bC = new a$4(this, "buildingFactories");
    d bD = new a$5(this, "buildingFactoriesForBuilders");
    public c bE = new c();
    int bF;
    public float bG = 0.0f;
    ArrayList bH = new ArrayList();
    private static ArrayList bK;
    public static final u bI;
    public final com.corrodinggames.rts.gameFramework.utility.m bJ = new com.corrodinggames.rts.gameFramework.utility.m();

    public boolean ac() {
        int n2 = this.ag();
        return this.ag() == 3 || n2 > 300;
    }

    public boolean ad() {
        return this.ag() >= 2;
    }

    public boolean ae() {
        return (1 & this.aJ) == 1;
    }

    public boolean af() {
        return this.ae();
    }

    public int ag() {
        return this.bF;
    }

    public boolean ah() {
        com.corrodinggames.rts.gameFramework.k.l l2 = com.corrodinggames.rts.gameFramework.l.B().bU;
        return l2.A.i > 3000;
    }

    public boolean ai() {
        if (this.ah()) {
            return true;
        }
        if (!this.bh || !this.bi) {
            return true;
        }
        if (!this.bj) {
            return true;
        }
        return !this.bk;
    }

    public boolean aj() {
        if (!this.bk) {
            return false;
        }
        return this.ai() && this.bi;
    }

    public boolean a(float f2, float f3, o o2, ao ao2) {
        if (this.a(f2, f3, o2.S, o2.T, ao2)) {
            return true;
        }
        for (float f4 = -180.0f; f4 < 180.0f; f4 += 90.0f) {
            float f5;
            float f6 = o2.S + com.corrodinggames.rts.gameFramework.f.k(f4) * o2.U * 0.4f;
            if (!this.a(f2, f3, f6, f5 = o2.T + com.corrodinggames.rts.gameFramework.f.j(f4) * o2.U * 0.4f, ao2)) continue;
            return true;
        }
        return false;
    }

    public boolean a(float f2, float f3, float f4, float f5, ao ao2) {
        if (ao2 == com.corrodinggames.rts.game.units.ao.d || ao2 == com.corrodinggames.rts.game.units.ao.a) {
            return true;
        }
        short s2 = com.corrodinggames.rts.gameFramework.utility.y.b(f2, f3, ao2);
        short s3 = com.corrodinggames.rts.gameFramework.utility.y.b(f4, f5, ao2);
        if (s2 == -3 || s3 == -3) {
            String string = "null";
            if (ao2 != null) {
                string = ao2.name();
            }
            this.d("pathPossible: no isolatedGroups found! (" + string + ")");
            com.corrodinggames.rts.gameFramework.l.T();
        }
        if (s2 == -1 || s3 == -1) {
            return false;
        }
        if (s2 == -2) {
            return false;
        }
        if (s3 == -2) {
            return false;
        }
        return s2 == s3;
    }

    public boolean a(am am2, float f2, float f3) {
        return this.a(am2.eo, am2.ep, f2, f3, am2.h());
    }

    public boolean b(am am2, float f2, float f3) {
        float f4 = 60.0f;
        ao ao2 = am2.h();
        if (this.a(am2.eo, am2.ep, f2, f3, ao2)) {
            return true;
        }
        if (this.a(am2.eo, am2.ep, f2 + f4, f3, ao2)) {
            return true;
        }
        if (this.a(am2.eo, am2.ep, f2 - f4, f3, ao2)) {
            return true;
        }
        if (this.a(am2.eo, am2.ep, f2, f3 + f4, ao2)) {
            return true;
        }
        return this.a(am2.eo, am2.ep, f2, f3 - f4, ao2);
    }

    public boolean a(am am2, am am3) {
        return this.b(am2, am3.eo, am3.ep);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.aK);
        as2.a(this.aL);
        as2.a(this.aM);
        as2.a(this.aN);
        as2.a(this.aO);
        as2.a(this.aT);
        as2.a(this.aV);
        as2.a(this.aW);
        as2.a(this.aX);
        as2.a(this.ba);
        as2.a(this.bm.size());
        for (Object object : this.bm) {
            int n2 = -1;
            if (object instanceof i) {
                n2 = 1;
            } else if (object instanceof g) {
                n2 = 2;
            } else if (object instanceof com.corrodinggames.rts.game.a.n) {
                n2 = 3;
            } else if (object instanceof m) {
                n2 = 4;
            } else if (object instanceof l) {
                n2 = 5;
            } else {
                throw new RuntimeException("zone not instance not supported:" + object.getClass().getName());
            }
            as2.c(n2);
            as2.a(((o)object).Q);
        }
        for (Object object : this.bm) {
            as2.a(((o)object).Q);
            ((o)object).a(as2);
        }
        as2.c(9);
        as2.a(this.aI);
        as2.a(this.bd);
        as2.a(this.bh);
        as2.a(this.bi);
        as2.a(this.bj);
        as2.a(this.bk);
        as2.a(this.aU);
        as2.a(this.bl);
        as2.a(this.au);
        as2.a(this.av);
        as2.a(this.aw);
        as2.a(this.aY);
        as2.a(this.aJ);
        as2.e();
        as2.a(this.bJ.a);
        for (int j = 0; j < this.bJ.a; ++j) {
            Object object;
            object = (com.corrodinggames.rts.game.a.a.a)this.bJ.get(j);
            as2.a(((com.corrodinggames.rts.game.a.a.a)object).a());
            ((com.corrodinggames.rts.game.a.a.a)object).a(as2);
        }
        as2.e();
        super.a(as2);
    }

    public o l(int n2) {
        if (n2 == 1) {
            return new i(this, -1.0f, -1.0f);
        }
        if (n2 == 2) {
            return new g(this);
        }
        if (n2 == 3) {
            return new com.corrodinggames.rts.game.a.n(this);
        }
        if (n2 == 4) {
            return new m(this);
        }
        if (n2 == 5) {
            return new l(this);
        }
        if (n2 == 0) {
            com.corrodinggames.rts.gameFramework.l.b("Found zone type 0, loading PlainZone instead");
            return new m(this);
        }
        throw new RuntimeException("Unknown zone type:" + n2);
    }

    @Override
    public void c(com.corrodinggames.rts.gameFramework.j.k k2) {
        int n2;
        this.aK = k2.e();
        this.aL = k2.g();
        this.aM = k2.g();
        this.aN = k2.g();
        this.aO = k2.g();
        this.aT = k2.g();
        this.aV = k2.f();
        this.aW = k2.g();
        this.aX = k2.e();
        this.ba = k2.f();
        int n3 = k2.f();
        this.bm.clear();
        boolean bl = false;
        if (k2.b() >= 20) {
            bl = true;
            for (n2 = 0; n2 < n3; ++n2) {
                byte by = k2.d();
                o o2 = this.l(by);
                o2.Q = k2.f();
            }
        }
        for (n2 = 0; n2 < n3; ++n2) {
            o o3;
            if (!bl) {
                byte by = k2.d();
                o3 = this.l(by);
            } else {
                o3 = this.m(k2.f());
            }
            o3.a(k2);
        }
        n2 = k2.d();
        if (n2 >= 1) {
            this.aI = k2.f();
        }
        this.bn.clear();
        this.bn.addAll(this.bm);
        if (n2 >= 2) {
            this.bd = k2.e();
            this.bh = k2.e();
            this.bi = k2.e();
        }
        if (n2 >= 3) {
            this.bj = k2.e();
            this.bk = k2.e();
        }
        if (n2 >= 4) {
            this.aU = k2.g();
        }
        if (n2 >= 5) {
            this.bl = k2.f();
        }
        if (n2 >= 6) {
            this.au = k2.f();
            this.av = k2.f();
            this.aw = k2.f();
        }
        if (n2 >= 7) {
            this.aY = k2.e();
        }
        if (n2 >= 8) {
            this.aJ = k2.f();
        }
        if (n2 >= 9) {
            k2.a("ai-c s");
            this.bJ.clear();
            int n4 = k2.f();
            for (int j = 0; j < n4; ++j) {
                com.corrodinggames.rts.game.a.a.b b2 = (com.corrodinggames.rts.game.a.a.b)k2.b(com.corrodinggames.rts.game.a.a.b.class);
                com.corrodinggames.rts.game.a.a.a a2 = b2.a();
                a2.a(k2);
                this.a(a2);
            }
            k2.a("ai-c e");
        }
        super.c(k2);
        this.ak();
    }

    public o m(int n2) {
        for (o o2 : this.bm) {
            if (o2.Q != n2) continue;
            return o2;
        }
        return null;
    }

    public int a(o o2) {
        if (o2 == null) {
            return -1;
        }
        return o2.Q;
    }

    void ak() {
        this.az = 0;
        this.aC = 0;
        this.aD = 0;
        this.aE = 0;
        this.aA = 0;
        this.aB = 0;
        this.aF = 0;
        this.aG = 0;
        this.ax = 0;
        this.ay = 0;
        this.aH = 0;
        for (o o2 : this.bn) {
            o o3;
            if (o2 instanceof i) {
                o3 = (i)o2;
                ++this.ax;
                if (((i)o3).u() >= 2) {
                    ++this.ay;
                }
                if (((i)o3).n) {
                    ++this.aH;
                }
            }
            if (o2 instanceof g) {
                o3 = (g)o2;
                if (((g)o3).a) continue;
                if (((g)o3).h) {
                    ++this.az;
                    if (!((g)o3).v && !((g)o3).d()) {
                        if (!((g)o3).B) {
                            ++this.aA;
                        } else {
                            ++this.aB;
                        }
                    }
                } else {
                    ++this.aC;
                    if (((g)o3).d()) {
                        ++this.aD;
                    }
                    this.aE += ((h)o3).l();
                }
            }
            if (!(o2 instanceof com.corrodinggames.rts.game.a.n)) continue;
            o3 = (h)o2;
            ++this.aF;
            if (((h)o3).l() <= 0) continue;
            ++this.aG;
        }
    }

    private boolean a(as as2) {
        am am2 = com.corrodinggames.rts.game.units.am.b(as2);
        if (!am2.bI() && am2 instanceof y && !this.g(am2) && !am2.aj() && ((y)am2).l()) {
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                com.corrodinggames.rts.game.units.custom.l l2 = (com.corrodinggames.rts.game.units.custom.l)as2;
                if (l2.fw || !l2.fs) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public a(int n2) {
        this(n2, true);
    }

    public a(int n2, boolean bl2) {
        super(n2, bl2);
        this.av();
    }

    private void av() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.aL = 100 + this.k * 9;
        this.aN = 202 + this.k * 19;
        this.aP = 50 + this.k * 2;
        this.aW = 4200 + this.k * 5;
        this.aT = 3500 + this.k * 5;
        this.aU = 7500 + this.k * 5;
        this.bp = new Paint();
        this.bp.b(Color.a(0, 255, 0));
        this.bp.a(Paint$Style.b);
        this.bp.a(true);
        l2.b(this.bp, 14.0f);
        this.al();
    }

    public void al() {
        for (d d2 : this.bq) {
            d2.b();
        }
    }

    public void d(String string) {
        com.corrodinggames.rts.gameFramework.l.b("ai_debug(" + this.k + ")", string);
    }

    public PointF am() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bL.a(com.corrodinggames.rts.gameFramework.f.a(0, l2.bL.C), com.corrodinggames.rts.gameFramework.f.a(0, l2.bL.D));
        this.bo.a(l2.bL.T, l2.bL.U);
        return this.bo;
    }

    public PointF an() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bL.A.size() == 0) {
            return null;
        }
        int n2 = com.corrodinggames.rts.gameFramework.f.c(l2.bL.A.size());
        Point point = (Point)l2.bL.A.get(n2);
        l2.bL.a(point.a, point.b);
        this.bo.a(l2.bL.T, l2.bL.U);
        return this.bo;
    }

    public PointF a(float f2, float f3) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f4 = -1.0f;
        PointF pointF = new PointF();
        for (int i2 = 0; i2 < l2.bL.A.size(); ++i2) {
            Point point = (Point)l2.bL.A.get(i2);
            l2.bL.a(point.a, point.b);
            this.bo.a(l2.bL.T, l2.bL.U);
            PointF pointF2 = this.bo;
            float f5 = com.corrodinggames.rts.gameFramework.f.a(pointF2.a, pointF2.b, f2, f3);
            if (!(f5 < f4) && f4 != -1.0f) continue;
            f4 = f5;
            pointF.a(pointF2);
        }
        if (f4 == -1.0f) {
            return null;
        }
        return pointF;
    }

    i e(am am2) {
        for (o o2 : this.bn) {
            i i2;
            if (!(o2 instanceof i) || !(i2 = (i)o2).b(am2)) continue;
            return i2;
        }
        return null;
    }

    i b(float f2, float f3) {
        for (o o2 : this.bn) {
            i i2;
            if (!(o2 instanceof i) || !(i2 = (i)o2).c(f2, f3)) continue;
            return i2;
        }
        return null;
    }

    i f(am am2) {
        return this.c(am2.eo, am2.ep);
    }

    i c(float f2, float f3) {
        float f4 = -1.0f;
        i i2 = null;
        for (o o2 : this.bn) {
            if (!(o2 instanceof i)) continue;
            i i3 = (i)o2;
            float f5 = i3.d(f2, f3);
            if (i2 != null && !(f5 < f4)) continue;
            f4 = f5;
            i2 = i3;
        }
        return i2;
    }

    i a(ao ao2, float f2, float f3, boolean bl2) {
        float f4 = -1.0f;
        i i2 = null;
        for (o o2 : this.bn) {
            if (!(o2 instanceof i)) continue;
            i i3 = (i)o2;
            float f5 = i3.d(f2, f3);
            if (!this.a(f2, f3, i3, ao2) || bl2 && i3.t || i2 != null && !(f5 < f4)) continue;
            f4 = f5;
            i2 = i3;
        }
        return i2;
    }

    public static boolean a(am am2, float f2, float f3, float f4) {
        float f5;
        float f6 = com.corrodinggames.rts.gameFramework.f.a(am2.eo, am2.ep, f2, f3);
        return f6 < (f5 = f4) * f5;
    }

    private boolean a(PointF pointF) {
        float f2;
        if (a.a(this, pointF.a, pointF.b, 290.0f) != null) {
            return false;
        }
        i i2 = this.c(pointF.a, pointF.b);
        if (i2 != null && i2.d(pointF.a, pointF.b) < 490000.0f) {
            return false;
        }
        PointF pointF2 = this.a(pointF.a, pointF.b);
        if (pointF2 != null && (f2 = com.corrodinggames.rts.gameFramework.f.a(pointF.a, pointF.b, pointF2.a, pointF2.b)) < 160000.0f) {
            return false;
        }
        f2 = 60.0f;
        return !com.corrodinggames.rts.gameFramework.utility.y.d(pointF.a, pointF.b) && !com.corrodinggames.rts.gameFramework.utility.y.d(pointF.a + f2, pointF.b) && !com.corrodinggames.rts.gameFramework.utility.y.d(pointF.a, pointF.b + f2) && !com.corrodinggames.rts.gameFramework.utility.y.d(pointF.a - f2, pointF.b) && !com.corrodinggames.rts.gameFramework.utility.y.d(pointF.a, pointF.b + f2);
    }

    private boolean b(PointF pointF) {
        for (am am2 : com.corrodinggames.rts.game.units.am.bE) {
            if (am2.bX == this || !(am2 instanceof com.corrodinggames.rts.game.units.d.e)) continue;
            if (am2.bX.c(this) && a.a(am2, pointF.a, pointF.b, 300.0f)) {
                return false;
            }
            if (!am2.bX.d(this) || !a.a(am2, pointF.a, pointF.b, 320.0f)) continue;
            return false;
        }
        if (a.b(this, pointF.a, pointF.b, 360.0f) >= 4) {
            return false;
        }
        boolean bl2 = true;
        return a.a(this, pointF.a, pointF.b, 360.0f, bl2) < 2;
    }

    public int a(d d2, b b2) {
        int n2 = 0;
        for (e e2 : d2.c) {
            n2 += this.a(e2.a, b2);
        }
        return n2;
    }

    public int a(as as2, b b2) {
        return this.a(as2, true, b2);
    }

    public int a(as as2, boolean bl2, b b2) {
        boolean bl3 = as2.j();
        Integer n2 = this.bE.a(bl3, as2, bl2);
        if (n2 != null) {
            return n2;
        }
        int n3 = 0;
        if (bl3) {
            bl2 = false;
        }
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n4 = com.corrodinggames.rts.game.units.am.bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            am am2 = amArray[i2];
            if (am2.bX != this || b2 != b.a && am2.bM) continue;
            if (am2.dz == as2) {
                ++n3;
            }
            if (!bl2 || !(am2 instanceof com.corrodinggames.rts.game.units.d.l)) continue;
            com.corrodinggames.rts.game.units.d.l l2 = (com.corrodinggames.rts.game.units.d.l)((Object)am2);
            n3 += l2.h(as2);
        }
        this.bE.a(bl3, as2, bl2, n3);
        return n3;
    }

    public int ao() {
        int n2 = 0;
        for (o o2 : this.bn) {
            if (!(o2 instanceof g)) continue;
            g g2 = (g)o2;
            n2 += g2.G.size();
        }
        return n2;
    }

    public boolean g(am am2) {
        y y2;
        if (am2 instanceof y && (y2 = (y)am2).cr()) {
            as as2 = y2.r();
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                com.corrodinggames.rts.game.units.custom.l l2 = (com.corrodinggames.rts.game.units.custom.l)as2;
                if (!l2.ft) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean h(am am2) {
        y y2;
        if (am2 instanceof y && !(y2 = (y)am2).bI() && y2.l() && !this.g(y2) && !y2.aj()) {
            as as2 = y2.r();
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                com.corrodinggames.rts.game.units.custom.l l2 = (com.corrodinggames.rts.game.units.custom.l)as2;
                if (!l2.fs) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean b(am am2, am am3) {
        y y2;
        if (this.U) {
            y y3;
            return am2 instanceof y && (y3 = (y)am2).aq() && aq.a(y3, am3);
        }
        return this.h(am2) && am2 instanceof y && aq.a(y2 = (y)am2, am3);
    }

    public void i(float f2) {
        float f3;
        float f4;
        float f5;
        float f6;
        Object object;
        if (!as || !com.corrodinggames.rts.gameFramework.l.B().bl) {
            return;
        }
        if (this.aZ || this.aX) {
            return;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n2 = com.corrodinggames.rts.game.units.am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            float f7;
            am am2 = amArray[i2];
            if (am2.bX != this || !l2.cN.b((int)(am2.eo - (f7 = 200.0f)), (int)(am2.ep - f7), (int)(am2.eo + f7), (int)(am2.ep + f7))) continue;
            if (am2 instanceof y) {
                object = (y)am2;
            }
            object = "";
            f6 = am2.ep - l2.cx - 60.0f;
            this.bp.b(Color.a(0, 255, 0));
            if (am2 instanceof com.corrodinggames.rts.game.units.d.e) {
                f6 -= 80.0f;
                object = (String)object + "Base ( Team:" + this.k + " )";
                object = (String)object + "\nuseTransportsOnThisMap: " + this.ai();
                object = (String)object + "\nuseHoverTransportsOnThisMap: " + this.aj();
                object = (String)object + "\nattackingCount: " + this.ba;
                object = (String)object + "\ndefendingCount: " + this.bb;
                object = (String)object + "\nnumOfUnitsNeedingTransport: " + this.ao();
                object = (String)object + "\ntransport: " + this.aG;
                if (this.ae()) {
                    object = (String)object + "\nTurtling: true";
                }
                this.bp.b(Color.a(255, 255, 255));
            }
            if (((String)object).length() == 0) continue;
            for (String object2 : ((String)object).split("\n")) {
                f5 = am2.eo - l2.cw;
                f4 = f6;
                f3 = -this.bp.l() + this.bp.m();
                l2.bO.k();
                if (l2.cX > 1.0f) {
                    l2.S();
                    f5 *= l2.cX;
                    f4 *= l2.cX;
                    f3 /= l2.cX;
                }
                l2.bO.a(object2, f5, f4, this.bp);
                l2.bO.l();
                f6 += f3;
            }
        }
        for (o o2 : this.bm) {
            Object object3;
            if (!l2.cN.b((int)(o2.S - o2.U), (int)(o2.T - o2.U), (int)(o2.S + o2.U), (int)(o2.T + o2.U))) continue;
            this.bp.b(this.K());
            l2.bO.a(o2.S - l2.cw, o2.T - l2.cx, o2.U + 2.0f, this.bp);
            int n3 = Color.a(0, 255, 0);
            String string = "";
            object = o2.getClass().getSimpleName();
            string = string + "\n" + (String)object + " ( Team:" + this.k + " )";
            f6 = o2.T - l2.cx;
            if (o2 instanceof i) {
                f6 -= 50.0f;
                object3 = (i)o2;
                string = string + "\nState: " + ((i)object3).b.name() + "(id:" + ((i)object3).Q + ")";
                string = string + "\nunsafe: " + ((i)object3).f() + " (" + ((i)object3).s + ")";
                string = string + "\nunsafeBaseTimer: " + ((i)object3).v;
                string = string + "\nallowedUnits: " + ((i)object3).d;
                if (((i)object3).z != null) {
                    string = string + "\nlastAttemptedBuilding: " + ((i)object3).z.i();
                }
                if (((i)object3).A != null) {
                    string = string + "\nlastAttemptedBuilding-cannotAffordPrice: " + ((i)object3).A.a(false, true, 4, true);
                }
                if (((i)object3).B != null) {
                    string = string + "\nlastAttemptedBuilding-cannotAffordBy: " + ((i)object3).B.a(false, true, 4, true);
                }
                string = string + "\nlastAttemptedBuildingCount: " + ((i)object3).C;
                string = string + "\nlastAttemptedBuildingFailed: " + ((i)object3).D;
                string = string + "\nlastUnitAttempt: " + ((i)object3).E + " (" + ((i)object3).F + " - " + ((i)object3).G + ")";
                string = string + "\nbuildBuildingDelay: " + ((i)object3).e;
                string = string + "\ncredits: " + com.corrodinggames.rts.gameFramework.f.c(this.o) + " (x" + com.corrodinggames.rts.gameFramework.f.g(this.E()) + ")";
                if (((i)object3).b == com.corrodinggames.rts.game.a.j.a) {
                    string = string + "\nclaimedBaseTimer: " + ((i)object3).l;
                }
                if (((i)object3).k > 100.0f) {
                    string = string + "\nabandonedTimer: " + ((i)object3).k;
                }
                if (((i)object3).g > 0.0f) {
                    string = string + "\nrequestedBuildersDelay: " + ((i)object3).g + " (" + ((i)object3).h + ")";
                }
                string = string + "\nBuilders: " + ((i)object3).J;
                string = string + "\nIdle Builders: " + ((i)object3).K;
            }
            if (o2 instanceof g) {
                object3 = (g)o2;
                if (((g)object3).c) {
                    string = string + "\nVIP Mode";
                }
                string = string + "\n" + (((g)object3).b() ? "Defensive Type" : "Attack Type");
                string = string + "\nUnits: " + ((g)object3).F.size() + " / " + ((g)object3).A;
                string = string + "\nStagingForAttack: " + ((g)object3).q;
                string = string + "\nAttackDelay: " + ((g)object3).l;
                if (((g)object3).u != 0.0f) {
                    string = string + "\nStagingTimer: " + ((g)object3).u;
                }
                string = string + "\nStagingTargetFound: " + ((g)object3).r;
                if (((g)object3).o != 0.0f) {
                    string = string + "\nattackingFor: " + ((g)object3).o;
                }
                string = string + "\ncommonMovement: " + ((g)object3).i().name();
                if (((g)object3).B) {
                    string = string + " (seaGroup)";
                }
                if (((g)object3).G.size() > 0) {
                    string = string + "\nunitsNeedingTransport:" + ((g)object3).G.size();
                }
                if (((g)object3).b != null) {
                    string = string + "\nlast action:" + ((g)object3).b;
                }
                if (!((g)object3).v && !((g)object3).q) {
                    string = string + "\nnext move:" + (int)this.k(((g)object3).n) + "s";
                }
            }
            if (o2 instanceof com.corrodinggames.rts.game.a.n) {
                object3 = (com.corrodinggames.rts.game.a.n)o2;
                string = string + "\nUnitsWanted: " + ((com.corrodinggames.rts.game.a.n)object3).l;
                string = string + "\nunits: " + ((com.corrodinggames.rts.game.a.n)object3).F.size();
                string = string + "\nreadyToMoveOut: " + ((com.corrodinggames.rts.game.a.n)object3).q;
                if (((com.corrodinggames.rts.game.a.n)object3).m != null) {
                    string = string + "\nCurrentlyHelping: " + ((com.corrodinggames.rts.game.a.n)object3).m.Q;
                }
            }
            if (o2 instanceof l) {
                object3 = (l)o2;
                string = string + "\nneedsTransportGroup: " + ((l)object3).a;
            }
            this.bp.b(this.K());
            for (String string2 : string.split("\n")) {
                if (string2.trim().equals("")) continue;
                f5 = o2.S - l2.cw;
                f4 = f6;
                f3 = -this.bp.l() + this.bp.m();
                l2.bO.k();
                if (l2.cX > 1.0f) {
                    l2.S();
                    f5 *= l2.cX;
                    f4 *= l2.cX;
                    f3 /= l2.cX;
                }
                l2.bO.a(string2, f5, f4, this.bp);
                l2.bO.l();
                f6 += f3;
                this.bp.b(n3);
            }
        }
    }

    public am e(n n2) {
        for (am am2 : com.corrodinggames.rts.game.units.am.bE) {
            if (am2.bX != n2 || !(am2 instanceof com.corrodinggames.rts.game.units.d.e) && !am2.bP) continue;
            return am2;
        }
        for (am am2 : com.corrodinggames.rts.game.units.am.bE) {
            if (am2.bX != n2 || !am2.bO) continue;
            return am2;
        }
        return null;
    }

    @Override
    public void a(float f2) {
        Object object4;
        int n2;
        Object object2;
        super.a(f2);
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.aX || this.aZ) {
            return;
        }
        if (l2.bX.B) {
            if (!l2.bX.C) {
                return;
            }
            if (l2.cb.j()) {
                return;
            }
        }
        if (this.bG > 0.0f) {
            this.bG -= f2;
            return;
        }
        this.bF = this.C();
        if (this.be && l2.by > 3000) {
            this.be = false;
            object2 = com.corrodinggames.rts.game.units.am.bE.a();
            int n3 = com.corrodinggames.rts.game.units.am.bE.size();
            for (n2 = 0; n2 < n3; ++n2) {
                object4 = object2[n2];
                if (!(object4 instanceof com.corrodinggames.rts.game.units.f)) continue;
                this.d("firstRunDelayed: Found damagingBorder");
                this.bg = (com.corrodinggames.rts.game.units.f)object4;
                break;
            }
        }
        if (this.bd) {
            this.bd = false;
            this.bh = true;
            this.bi = true;
            this.bj = true;
            this.bk = true;
            object2 = this.e(this);
            if (object2 == null) {
                this.d("firstRun: no command center found");
            }
            if (object2 != null) {
                for (n2 = 0; n2 < com.corrodinggames.rts.game.n.c; ++n2) {
                    n n4 = com.corrodinggames.rts.game.n.k(n2);
                    if (n4 == null || n4 == this || (object4 = this.e(n4)) == null) continue;
                    if (!this.a(object2.eo, object2.ep, ((am)object4).eo, ((am)object4).ep, com.corrodinggames.rts.game.units.ao.b)) {
                        this.bh = false;
                    }
                    if (this.a(object2.eo, object2.ep, ((am)object4).eo, ((am)object4).ep, com.corrodinggames.rts.game.units.ao.f)) continue;
                    this.bi = false;
                }
                for (Point point : l2.bL.A) {
                    object4 = l2.bL.a(point);
                    if (!this.a(object2.eo, object2.ep, ((PointF)object4).a, ((PointF)object4).b + (float)l2.bL.o, com.corrodinggames.rts.game.units.ao.b)) {
                        this.bj = false;
                    }
                    if (this.a(object2.eo, object2.ep, ((PointF)object4).a, ((PointF)object4).b + (float)l2.bL.o, com.corrodinggames.rts.game.units.ao.f)) continue;
                    this.bk = false;
                }
            }
        }
        this.aP += f2;
        this.aQ += f2;
        if (this.aP > 25.0f) {
            Object object3;
            this.aP -= 25.0f;
            if (this.aP > 25.0f) {
                this.aP = 25.0f;
            }
            if (this.aP < -1.0f) {
                this.aP = -1.0f;
            }
            boolean bl2 = false;
            n2 = 0;
            for (Object object4 : this.bm) {
                if (!(object4 instanceof i)) continue;
                object3 = (i)object4;
                ((i)object3).a += this.aQ;
            }
            for (int i2 = 0; i2 < 2; ++i2) {
                object4 = null;
                for (o o2 : this.bm) {
                    if (!(o2 instanceof i)) continue;
                    i i3 = (i)o2;
                    if (object4 != null && !(((i)object4).a < i3.a)) continue;
                    object4 = i3;
                }
                if (object4 == null || ((i)object4).a < 50.0f) break;
                object3 = object4;
                ((i)object3).b(((i)object3).a);
                ((i)object3).d(((i)object3).a);
                ((i)object3).a = 0.0f;
            }
            this.aQ = 0.0f;
        }
        this.aL += f2;
        this.aM += f2;
        if (this.aL > 80.0f) {
            this.n(this.aM);
            this.aL -= 80.0f;
            if (this.aL > 80.0f) {
                this.aL = 80.0f;
            }
            if (this.aL < -1.0f) {
                this.aL = -1.0f;
            }
            this.aM = 0.0f;
        }
        this.aN += f2;
        this.aO += f2;
        if (this.aN > 250.0f) {
            this.m(this.aO);
            this.aN -= 250.0f;
            if (this.aN > 250.0f) {
                this.aN = 250.0f;
            }
            if (this.aN < -1.0f) {
                this.aN = -1.0f;
            }
            this.aO = 0.0f;
        }
    }

    public float j(float f2) {
        return f2 / 60.0f * 1000.0f;
    }

    public float k(float f2) {
        return f2 / 60.0f;
    }

    public void a(y y2, com.corrodinggames.rts.game.units.a.c c2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this);
        e2.a(y2);
        e2.a(c2);
    }

    public void l(float f2) {
        for (am am2 : com.corrodinggames.rts.game.units.am.bE) {
            boolean bl2;
            Object object;
            boolean bl3;
            if (am2.bX != this || !(am2 instanceof y) || !this.i(am2)) continue;
            y y2 = (y)am2;
            if (y2 instanceof com.corrodinggames.rts.game.units.h.e) {
                bl3 = false;
                object = y2.ab();
                if (object != null && y2.h((am)object)) {
                    bl3 = !((am)object).cH();
                }
                boolean bl4 = bl2 = !y2.Q();
                if (bl3 && bl3 != bl2) {
                    this.a(y2, com.corrodinggames.rts.game.units.h.e.j.N());
                }
                if (!bl3 && bl3 != bl2) {
                    this.a(y2, com.corrodinggames.rts.game.units.h.e.k.N());
                }
            }
            if (y2 instanceof com.corrodinggames.rts.game.units.b.c) {
                bl3 = true;
                object = y2.ab();
                if (object != null && y2.h((am)object)) {
                    bl3 = !((am)object).Q();
                }
                boolean bl5 = bl2 = !y2.Q();
                if (bl3 && bl3 != bl2) {
                    this.a(y2, com.corrodinggames.rts.game.units.b.c.y.N());
                }
                if (!bl3 && bl3 != bl2) {
                    this.a(y2, com.corrodinggames.rts.game.units.b.c.z.N());
                }
            }
            if (y2.be() != com.corrodinggames.rts.game.units.b.d || !y2.aq() || y2.ab() == null) continue;
            com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
            object = l2.cf.a(this);
            ((com.corrodinggames.rts.gameFramework.e)object).a(y2);
            ((com.corrodinggames.rts.gameFramework.e)object).a(y2.ab());
        }
    }

    public com.corrodinggames.rts.game.units.a c(y y2) {
        if (y2.aS()) {
            boolean bl2 = true;
            if (y2.aj()) {
                bl2 = false;
            }
            if (this.g(y2)) {
                bl2 = false;
            }
            if (bl2) {
                if (this.aY) {
                    return com.corrodinggames.rts.game.units.a.f;
                }
                return com.corrodinggames.rts.game.units.a.a;
            }
        }
        return com.corrodinggames.rts.game.units.a.b;
    }

    public ArrayList ap() {
        bK.clear();
        return bK;
    }

    public void d(y y2) {
        for (com.corrodinggames.rts.game.a.a.a a2 : this.bJ) {
            a2.a(this, y2);
        }
    }

    public void e(y y2) {
        for (com.corrodinggames.rts.game.a.a.a a2 : this.bJ) {
            a2.b(this, y2);
        }
    }

    public void m(float f2) {
        int n2;
        Object object;
        Object object2;
        int n3;
        Object object32;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.bE.b();
        for (Object object32 : this.bJ) {
            ((com.corrodinggames.rts.game.a.a.a)object32).b(this.j(f2), this);
        }
        int n4 = 0;
        object32 = com.corrodinggames.rts.game.units.am.bE.a();
        int n5 = com.corrodinggames.rts.game.units.am.bE.size();
        for (n3 = 0; n3 < n5; ++n3) {
            object2 = object32[n3];
            if (object2.bX != this || object2.u()) continue;
            ++n4;
            if (!(object2 instanceof y)) continue;
            object = (y)object2;
            if (!((y)object).bD) {
                ((y)object).bD = true;
                this.d((y)object);
            }
            if (object2.cN != null) continue;
            Object object4 = ((y)object).aC;
            ((y)object).aC = this.f((am)object);
            if (((y)object).aC == null || object4 == ((y)object).aC) continue;
            if (((am)object).bI()) {
                ((y)object).aD = this.a(object2.eo, object2.ep, ((y)object).aC.S, ((y)object).aC.T, com.corrodinggames.rts.game.units.ao.b);
                if (((y)object).aD || !((am)object).r().p()) continue;
                ((y)object).aD = this.a(object2.eo, object2.ep + 15.0f, ((y)object).aC.S, ((y)object).aC.T, com.corrodinggames.rts.game.units.ao.b);
                continue;
            }
            ((y)object).aD = this.a(object2.eo, object2.ep, ((y)object).aC.S, ((y)object).aC.T, com.corrodinggames.rts.game.units.ao.b);
        }
        this.l(f2);
        for (am am2 : com.corrodinggames.rts.game.units.am.bE) {
            if (am2.bX != this || !(am2 instanceof y)) continue;
            y y2 = (y)am2;
            object2 = this.c(y2);
            if (y2.P != object2 && this.i(y2)) {
                object = l2.cf.a(this);
                ((com.corrodinggames.rts.gameFramework.e)object).a(y2);
                ((com.corrodinggames.rts.gameFramework.e)object).a((com.corrodinggames.rts.game.units.a)((Object)object2));
            }
            if (!y2.aj() || !y2.dd() || y2.aB != null || !this.i(y2)) continue;
            com.corrodinggames.rts.game.a.g.a(this, y2);
        }
        if (n4 == 0 && !this.U) {
            this.aZ = true;
        }
        this.aU = com.corrodinggames.rts.gameFramework.f.a(this.aU, f2);
        this.aT = com.corrodinggames.rts.gameFramework.f.a(this.aT, f2);
        if (this.ac()) {
            this.aT = com.corrodinggames.rts.gameFramework.f.a(this.aT, 4.0f * f2);
        }
        if (this.aT == 0.0f) {
            int n6 = 0;
            for (o o2 : this.bn) {
                if (!(o2 instanceof i)) continue;
                object2 = (i)o2;
                if (((i)object2).b != com.corrodinggames.rts.game.a.j.a) continue;
                ++n6;
            }
            n3 = 0;
            if (n6 > 2) {
                n3 = 1;
            }
            if (n3 != 0) {
                this.aT = 300.0f;
            } else {
                PointF pointF = this.an();
                if (pointF != null) {
                    pointF.b += (float)l2.bL.o;
                    if (this.b(pointF.a, pointF.b) == null && this.b(pointF)) {
                        this.aT = 2000.0f;
                        object2 = new i(this, pointF.a, pointF.b);
                        ((i)object2).U = 360.0f;
                        ((i)object2).b = com.corrodinggames.rts.game.a.j.a;
                        ((i)object2).c = com.corrodinggames.rts.game.a.k.b;
                        ++this.aw;
                    }
                }
            }
        }
        if (this.aU == 0.0f) {
            this.aU = 100.0f;
            int n7 = 0;
            Object object5 = this.bn.iterator();
            while (object5.hasNext()) {
                o o3 = (o)object5.next();
                if (!(o3 instanceof i)) continue;
                object2 = (i)o3;
                if (((i)object2).c != com.corrodinggames.rts.game.a.k.c) continue;
                ++n7;
            }
            if (n7 < 3 && (object5 = this.ar()) != null) {
                PointF pointF = new PointF();
                pointF.a = ((am)object5).eo;
                pointF.b = ((am)object5).ep;
                if (pointF != null && this.b(pointF.a, pointF.b) == null && this.a(pointF)) {
                    this.aU = 5000.0f;
                    object2 = new i(this, pointF.a, pointF.b);
                    ((i)object2).U = 310.0f;
                    ((i)object2).b = com.corrodinggames.rts.game.a.j.a;
                    ((i)object2).c = com.corrodinggames.rts.game.a.k.c;
                    ++this.aw;
                }
            }
        }
        this.bc = 0;
        this.ba = 0;
        this.bb = 0;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        n5 = com.corrodinggames.rts.game.units.am.bE.size();
        for (n2 = 0; n2 < n5; ++n2) {
            object2 = amArray[n2];
            if (object2.bX != this || !(object2 instanceof y)) continue;
            object = (y)object2;
            if (object2.bI()) continue;
            if (((y)object).aB != null && ((y)object).aB.b()) {
                ++this.bb;
                continue;
            }
            if (!this.h((am)object) || ((y)object).bM) continue;
            if (((am)object).h() == com.corrodinggames.rts.game.units.ao.e) {
                ++this.bc;
                continue;
            }
            ++this.ba;
        }
        this.aR = com.corrodinggames.rts.gameFramework.f.a(this.aR, f2);
        this.aS += f2;
        if (this.aR == 0.0f) {
            Object object62;
            int n8 = 0;
            n2 = 0;
            n5 = 0;
            int n9 = 0;
            for (Object object4 : com.corrodinggames.rts.game.units.am.bE) {
                if (((am)object4).bX != this || !((am)object4).bT()) continue;
                if ((object4 instanceof com.corrodinggames.rts.game.units.d.m || object4 instanceof com.corrodinggames.rts.game.units.d.a || object4 instanceof t) && object4 instanceof com.corrodinggames.rts.game.units.d.a) {
                    ++n2;
                    object62 = (com.corrodinggames.rts.game.units.d.a)object4;
                    if (((com.corrodinggames.rts.game.units.d.a)object62).V() > 1) {
                        ++n8;
                    }
                }
                if (!((am)object4).r().p()) continue;
                ++n5;
                object62 = ((am)object4).cm();
                if (!com.corrodinggames.rts.game.units.a.s.c((com.corrodinggames.rts.game.units.a.c)object62)) continue;
                ++n9;
            }
            if (this.a(4100.0) || this.aS > 2400.0f || this.aH == 0) {
                Object object7;
                Object object8;
                for (Object object4 : com.corrodinggames.rts.game.units.am.bE) {
                    if (((am)object4).bX != this || !(object4 instanceof y) || !((am)(object62 = (y)object4)).cl()) continue;
                    object8 = ((am)object62).N();
                    object7 = this.ap();
                    Iterator iterator = ((ArrayList)object8).iterator();
                    while (iterator.hasNext()) {
                        s s2 = (s)iterator.next();
                        if (!s2.n((am)object62)) continue;
                        ((ArrayList)object7).add(s2);
                    }
                    if (((ArrayList)object7).size() <= 0) continue;
                    this.a((y)object62, (s)com.corrodinggames.rts.game.a.f.a((AbstractList)object7));
                }
                boolean bl2 = false;
                if (this.a(30000.0)) {
                    bl2 = true;
                }
                for (Object object62 : com.corrodinggames.rts.game.units.am.bE) {
                    boolean bl3;
                    if (((am)object62).bX != this || !(object62 instanceof y) || !com.corrodinggames.rts.game.units.a.s.c((com.corrodinggames.rts.game.units.a.c)(object7 = ((am)(object8 = (y)object62)).cm()))) continue;
                    float f3 = ((am)object8).cn();
                    if (f3 < 0.0f) {
                        f3 = 6.0f;
                        bl3 = false;
                    } else {
                        bl3 = true;
                    }
                    if (f3 == 0.0f) continue;
                    boolean bl4 = false;
                    int n10 = com.corrodinggames.rts.gameFramework.f.c(100);
                    float f4 = 100.0f - f3;
                    if (bl2) {
                        f4 -= 4.0f;
                    }
                    if (!bl3) {
                        if (((am)object62).r().p() && n9 > 0) {
                            f4 = 50.0f;
                        }
                        if (n2 > 0 && n8 == 0) {
                            f4 = 99.0f;
                            if (object62 instanceof com.corrodinggames.rts.game.units.d.a) {
                                f4 = 40.0f;
                            }
                        }
                    }
                    if (f4 < 10.0f) {
                        f4 = 10.0f;
                    }
                    if (!(bl4 = (float)n10 > f4)) continue;
                    boolean bl5 = ((am)object8).co();
                    if (bl5) {
                        // empty if block
                    }
                    if (com.corrodinggames.rts.gameFramework.f.c(100) > 50) {
                        ((am)object8).a(this.bH);
                        if (this.bH.size() != 0) {
                            object7 = (com.corrodinggames.rts.game.units.a.c)this.bH.get(new Random().nextInt(this.bH.size()));
                        }
                    }
                    boolean bl6 = false;
                    s s3 = ((am)object8).a((com.corrodinggames.rts.game.units.a.c)object7);
                    if (s3 != null) {
                        if (s3.m((am)object8)) {
                            bl6 = true;
                        }
                        if (s3.e() == com.corrodinggames.rts.game.units.a.u.g) {
                            bl6 = true;
                        }
                        if (!s3.b((am)object8)) {
                            bl6 = true;
                        }
                        if (!s3.a((am)object8, false)) {
                            bl6 = true;
                        }
                    } else {
                        bl6 = true;
                    }
                    if (bl6) continue;
                    this.a((y)object8, (com.corrodinggames.rts.game.units.a.c)object7);
                    com.corrodinggames.rts.game.units.custom.d.b b2 = s3.B();
                    boolean bl7 = true;
                    this.a((y)object8, b2, bl7);
                    this.aR = 900.0f;
                    this.aS = 0.0f;
                    if (bl2 && !(this.a(40000.0) ? com.corrodinggames.rts.gameFramework.f.c(100) > 95 : com.corrodinggames.rts.gameFramework.f.c(100) > 80)) continue;
                    break;
                }
            }
        }
        for (o o4 : this.bm) {
            if (!(o4 instanceof h)) continue;
            h h2 = (h)o4;
            h2.b(f2);
        }
    }

    public boolean a(y y2, s s2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (s2.b(y2) && s2.a((am)y2, false)) {
            com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this);
            e2.a(y2);
            e2.a(s2.z());
            return true;
        }
        return false;
    }

    public boolean a(y y2, s s2, PointF pointF, am am2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (s2.b(y2) && s2.a((am)y2, false)) {
            com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this);
            e2.a(y2);
            e2.a(s2.z(), pointF, am2);
            return true;
        }
        return false;
    }

    public void aq() {
        for (Object object : this.bm) {
            if (!(object instanceof i)) continue;
            ((i)object).t();
        }
        for (Object object : this.bm) {
            for (o o2 : this.bm) {
                if (object == o2 || ((o)object).Q != o2.Q) continue;
                com.corrodinggames.rts.gameFramework.l.a("Id overlap on:" + ((o)object).Q);
                com.corrodinggames.rts.gameFramework.l.a("zone x:" + ((o)object).S);
                com.corrodinggames.rts.gameFramework.l.a("zone y:" + ((o)object).T);
                com.corrodinggames.rts.gameFramework.l.a("zone radius:" + ((o)object).U);
                com.corrodinggames.rts.gameFramework.l.a("zone type:" + object.getClass().getName());
            }
        }
        int n2 = 0;
        for (Object object : this.bm) {
            if (!(object instanceof i)) continue;
            ++n2;
        }
        int n3 = 0;
        for (o o2 : this.bm) {
            if (!(o2 instanceof i)) continue;
            for (o o3 : this.bm) {
                float f2;
                if (!(o3 instanceof i) || o2 == o3 || !((f2 = com.corrodinggames.rts.gameFramework.f.a(o2.S, o2.T, o3.S, o3.T)) < 400.0f)) continue;
                ++n3;
            }
        }
        if (n3 > 0) {
            this.d("baseOverlapCount:" + n3);
        }
    }

    @Override
    public void a(y y2) {
        if (y2.bX == this) {
            this.bE.a(y2);
        }
    }

    public void n(float f2) {
        Object object;
        Object object2;
        Object object3;
        bq bq2;
        int n2;
        Object object42;
        Object object5;
        Object object62;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.bE.a();
        for (Object object7 : this.bJ) {
            ((com.corrodinggames.rts.game.a.a.a)object7).a(this.j(f2), this);
        }
        for (Object object7 : this.bm) {
            if (!(object7 instanceof h)) continue;
            object62 = (h)object7;
            ((h)object62).c(f2);
        }
        if (this.bg != null) {
            for (Object object7 : this.bm) {
                if (!this.bg.a(((o)object7).S, ((o)object7).T)) continue;
                if (object7 instanceof i) {
                    ((o)object7).p();
                    break;
                }
                if (!(object7 instanceof g)) continue;
                object62 = this.bg.a(((o)object7).S, ((o)object7).T, ((o)object7).U + 20.0f);
                ((o)object7).S = ((PointF)object62).a;
                ((o)object7).T = ((PointF)object62).b;
            }
        }
        this.aW = com.corrodinggames.rts.gameFramework.f.a(this.aW, f2);
        int n3 = 0;
        for (Object object62 : this.bn) {
            if (!(object62 instanceof i)) continue;
            ++n3;
        }
        if (n3 < 1) {
            Object object8;
            for (Object object62 : com.corrodinggames.rts.game.units.am.bE) {
                if (((am)object62).bX != this || !(object62 instanceof com.corrodinggames.rts.game.units.d.e)) continue;
                object5 = new i(this, ((am)object62).eo, ((am)object62).ep);
                ((i)object5).U = 420.0f;
                ((i)object5).b = com.corrodinggames.rts.game.a.j.c;
                ((i)object5).c = com.corrodinggames.rts.game.a.k.a;
                ++n3;
                break;
            }
            if (n3 < 1) {
                for (Object object62 : com.corrodinggames.rts.game.units.am.bE) {
                    if (((am)object62).bX != this || !this.bz.b(((am)object62).r())) continue;
                    object5 = new i(this, ((am)object62).eo, ((am)object62).ep);
                    ((i)object5).U = 420.0f;
                    ((i)object5).b = com.corrodinggames.rts.game.a.j.c;
                    ((i)object5).c = com.corrodinggames.rts.game.a.k.a;
                    ++n3;
                    break;
                }
            }
            if (n3 < 1) {
                for (Object object62 : com.corrodinggames.rts.game.units.am.bE) {
                    if (((am)object62).bX != this || !(object62 instanceof y)) continue;
                    object5 = (y)object62;
                    boolean bl2 = false;
                    for (Object object42 : this.bz.c) {
                        if (!((y)object5).b(((e)object42).a, true)) continue;
                        bl2 = true;
                        break;
                    }
                    if (!bl2) continue;
                    object8 = new i(this, ((am)object62).eo, ((am)object62).ep);
                    ((i)object8).U = 420.0f;
                    ((i)object8).b = com.corrodinggames.rts.game.a.j.c;
                    ((i)object8).c = com.corrodinggames.rts.game.a.k.a;
                    ++n3;
                    break;
                }
            }
            if (n3 < 1) {
                for (Object object62 : com.corrodinggames.rts.game.units.am.bE) {
                    if (((am)object62).bX != this || !(object62 instanceof y) || !((y)(object5 = (y)object62)).ai()) continue;
                    i i2 = new i(this, ((am)object62).eo, ((am)object62).ep);
                    i2.U = 420.0f;
                    i2.b = com.corrodinggames.rts.game.a.j.c;
                    i2.c = com.corrodinggames.rts.game.a.k.a;
                    ++n3;
                    break;
                }
            }
            if (!this.bf) {
                this.bf = true;
                int n4 = this.a(this.bB, b.a);
                if (n4 >= 1) {
                    for (int i3 = 0; i3 < l2.bL.A.size(); ++i3) {
                        object5 = (Point)l2.bL.A.get(i3);
                        l2.bL.a(((Point)object5).a, ((Point)object5).b);
                        this.bo.a(l2.bL.T, l2.bL.U);
                        PointF pointF = this.bo;
                        pointF.b += (float)l2.bL.o;
                        if (this.b(pointF.a, pointF.b) != null || this.a(this.bB, pointF.a, pointF.b, 200) < 1 || !this.b(pointF)) continue;
                        object8 = new i(this, pointF.a, pointF.b);
                        ((i)object8).U = 360.0f;
                        ((i)object8).b = com.corrodinggames.rts.game.a.j.a;
                        ((i)object8).c = com.corrodinggames.rts.game.a.k.b;
                    }
                }
            }
        }
        y y2 = null;
        i i4 = null;
        object5 = com.corrodinggames.rts.game.units.am.bE.a();
        int n5 = com.corrodinggames.rts.game.units.am.bE.size();
        for (n2 = 0; n2 < n5; ++n2) {
            object42 = object5[n2];
            if (((am)object42).bX != this || ((am)object42).cN != null || !(object42 instanceof y) || !((am)object42).aj() || !this.i((am)object42)) continue;
            bq2 = (y)object42;
            object3 = this.e((am)bq2);
            if (object3 != null) {
                if (!((y)bq2).aq()) continue;
                y2 = bq2;
                i4 = object3;
                continue;
            }
            if (!((y)bq2).aq() || (object2 = this.f((am)bq2)) == null) continue;
            object = ((o)object2).w();
            com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this);
            e2.a((y)bq2);
            e2.a(((PointF)object).a, ((PointF)object).b);
        }
        n5 = com.corrodinggames.rts.game.units.am.bE.size();
        for (n2 = 0; n2 < n5; ++n2) {
            object42 = object5[n2];
            if (((am)object42).bX != this || !(object42 instanceof y)) continue;
            bq2 = (y)object42;
            if (((y)bq2).V > 2400.0f && this.i((am)bq2)) {
                if (((y)bq2).aN && ((y)bq2).V < 24000.0f) continue;
                object3 = l2.cf.a(this);
                ((com.corrodinggames.rts.gameFramework.e)object3).a((y)bq2);
                ((com.corrodinggames.rts.gameFramework.e)object3).h();
            }
            if (!((y)bq2).aj() || !this.i((am)bq2) || (object3 = ((y)bq2).ar()) == null || ((au)object3).d() != com.corrodinggames.rts.game.units.av.c || !(((y)bq2).V > 700.0f)) continue;
            object2 = l2.cf.a(this);
            ((com.corrodinggames.rts.gameFramework.e)object2).a((y)bq2);
            ((com.corrodinggames.rts.gameFramework.e)object2).h();
        }
        if (!this.U) {
            this.ak();
            n2 = 1;
            n5 = this.af() ? 1 : 0;
            boolean bl3 = true;
            if (n5 != 0) {
                ++n2;
                bl3 = false;
            }
            if (this.ay > 6) {
                n2 = 2;
            }
            if (this.ay > 11) {
                n2 = 3;
            }
            if (this.aC < n2) {
                bq2 = new g(this, false);
                ((g)bq2).A = 8;
                if (this.ac()) {
                    ((g)bq2).A = 10;
                }
                ((g)bq2).k();
                ++this.av;
            }
            if ((this.aD >= n2 || this.aE > 6) && this.aA < 1 && bl3) {
                bq2 = new g(this, true);
                if (this.au < 2) {
                    ((g)bq2).A = 3;
                } else if (this.au < 5) {
                    ((g)bq2).A = 5;
                } else {
                    ((g)bq2).A = 7;
                    if (this.ac()) {
                        ((g)bq2).A = this.au < 25 ? 14 : 18;
                    }
                }
                ((g)bq2).k();
                ++this.au;
            }
            if (this.ah() && this.aB < 1 && bl3) {
                bq2 = new g(this, true);
                ((g)bq2).B = true;
                ((g)bq2).A = 5;
                if (this.ac()) {
                    ((g)bq2).A = 10;
                }
                ((g)bq2).k();
            }
            if (this.ai() && this.aF < 3) {
                bq2 = new com.corrodinggames.rts.game.a.n(this);
                ((com.corrodinggames.rts.game.a.n)bq2).l = 1;
                ((com.corrodinggames.rts.game.a.n)bq2).f();
            }
        }
        if (this.U) {
            if (this.aW > 30.0f) {
                this.aW = 30.0f;
            }
            if (this.aW == 0.0f) {
                ++this.aV;
                if (this.aV == 1) {
                    this.aW = 1000.0f;
                } else if (this.aV == 2) {
                    this.aW = 3000.0f;
                    am am2 = this.as();
                    if (am2 != null) {
                        if (this.U) {
                            n5 = 0;
                        } else {
                            n5 = 2;
                            if (this.ba < 4) {
                                n5 = 5;
                            }
                        }
                        object42 = l2.cf.a(this);
                        int n6 = com.corrodinggames.rts.game.units.am.bE.size();
                        for (int i5 = 0; i5 < n6; ++i5) {
                            object2 = object5[i5];
                            if (((am)object2).bX != this || !(object2 instanceof y)) continue;
                            object = (y)object2;
                            if (((y)object).bM || !this.b((am)object, am2)) continue;
                            if (n5 <= 0) {
                                ((com.corrodinggames.rts.gameFramework.e)object42).a((y)object);
                                continue;
                            }
                            --n5;
                        }
                        ((com.corrodinggames.rts.gameFramework.e)object42).b(am2.eo, am2.ep);
                    }
                } else {
                    this.aV = 0;
                }
            }
        }
    }

    public boolean i(am am2) {
        if (am2.u() || am2.t()) {
            return false;
        }
        if (am2.cW()) {
            return false;
        }
        return !am2.bN;
    }

    public am ar() {
        int n2;
        am am2 = null;
        int n3 = 0;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n4 = com.corrodinggames.rts.game.units.am.bE.size();
        for (n2 = 0; n2 < n4; ++n2) {
            am am3 = amArray[n2];
            if (am3.bV || am3.cN != null || this != am3.bX || !this.h(am3)) continue;
            ++n3;
        }
        n2 = (int)(Math.random() * (double)n3);
        n4 = 0;
        for (am am4 : com.corrodinggames.rts.game.units.am.bE) {
            if (am4.bV || am4.cN != null || this != am4.bX || !this.h(am4)) continue;
            if (n4 == n2) {
                am2 = am4;
                break;
            }
            ++n4;
        }
        return am2;
    }

    public am as() {
        int n2;
        am am2 = null;
        int n3 = 0;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n4 = com.corrodinggames.rts.game.units.am.bE.size();
        for (n2 = 0; n2 < n4; ++n2) {
            am am3 = amArray[n2];
            if (am3.bV || am3.cN != null || am3.u() || !this.c(am3.bX) || !this.j(am3)) continue;
            ++n3;
        }
        n2 = (int)(Math.random() * (double)n3);
        n4 = 0;
        for (am am4 : com.corrodinggames.rts.game.units.am.bE) {
            if (am4.bV || am4.cN != null || am4.u() || !this.c(am4.bX) || !this.j(am4)) continue;
            if (n4 == n2) {
                am2 = am4;
                break;
            }
            ++n4;
        }
        return am2;
    }

    public PointF at() {
        int n2;
        am am2 = null;
        int n3 = 0;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n4 = com.corrodinggames.rts.game.units.am.bE.size();
        for (n2 = 0; n2 < n4; ++n2) {
            am am3 = amArray[n2];
            if (am3.bV || am3.cN != null || am3.u() || !this.c(am3.bX) || !this.j(am3)) continue;
            ++n3;
        }
        n2 = (int)(Math.random() * (double)n3);
        n4 = 0;
        for (am am4 : com.corrodinggames.rts.game.units.am.bE) {
            if (am4.bV || am4.cN != null || am4.u() || !this.c(am4.bX) || !this.j(am4)) continue;
            if (n4 == n2) {
                am2 = am4;
                break;
            }
            ++n4;
        }
        if (am2 != null) {
            return new PointF(am2.eo, am2.ep);
        }
        return null;
    }

    public static am a(n n2, float f2, float f3, float f4) {
        float f5 = f4;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n3 = com.corrodinggames.rts.game.units.am.bE.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            am am2 = amArray[i2];
            if (!(am2.eo + f5 > f2) || !(am2.eo - f5 < f2) || !(am2.ep + f5 > f3) || !(am2.ep - f5 < f3) || am2.bX == n2 || !a.a(am2, f2, f3, f4) || !am2.bX.c(n2)) continue;
            return am2;
        }
        return null;
    }

    public static int a(n n2, float f2, float f3, float f4, boolean bl2) {
        int n3 = 0;
        float f5 = f4;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n4 = com.corrodinggames.rts.game.units.am.bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            am am2 = amArray[i2];
            if (!(am2.eo + f5 > f2) || !(am2.eo - f5 < f2) || !(am2.ep + f5 > f3) || !(am2.ep - f5 < f3) || am2.bX == n2 || !a.a(am2, f2, f3, f4) || !am2.bX.d(n2) || bl2 && !am2.bI()) continue;
            ++n3;
        }
        return n3;
    }

    public static int b(n n2, float f2, float f3, float f4) {
        int n3 = 0;
        float f5 = f4;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n4 = com.corrodinggames.rts.game.units.am.bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            am am2 = amArray[i2];
            if (!(am2.eo + f5 > f2) || !(am2.eo - f5 < f2) || !(am2.ep + f5 > f3) || !(am2.ep - f5 < f3) || am2.bX == n2 || !a.a(am2, f2, f3, f4) || !am2.bX.c(n2)) continue;
            ++n3;
        }
        return n3;
    }

    public int a(d d2, float f2, float f3, int n2) {
        int n3 = 0;
        for (e e2 : d2.c) {
            n3 += this.a(e2.a, f2, f3, n2);
        }
        return n3;
    }

    public int a(as as2, float f2, float f3, int n2) {
        int n3 = 0;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        bI.clear();
        l2.cc.a(this, f2, f3, (float)n2, bI);
        am[] amArray = bI.a();
        int n4 = bI.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            am am2 = amArray[i2];
            if (am2.bX != this || am2.dz != as2 || !a.a(am2, f2, f3, (float)n2)) continue;
            ++n3;
        }
        return n3;
    }

    public int au() {
        int n2 = 0;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n3 = com.corrodinggames.rts.game.units.am.bE.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            am am2 = amArray[i2];
            ++n2;
        }
        return n2;
    }

    @Override
    public void T() {
        if (this.aZ && this.au() != 0) {
            com.corrodinggames.rts.gameFramework.l.e("waking up AI");
            this.aZ = false;
        }
    }

    @Override
    public void d(am am2) {
        if (!(am2 instanceof y)) {
            return;
        }
        y y2 = (y)am2;
        y2.bD = false;
        if (y2.aC != null) {
            y2.aC.a(y2);
            y2.aC = null;
        }
        if (y2.aB != null) {
            y2.aB.b(y2);
            y2.aB = null;
        }
        this.e(y2);
    }

    public void a(y y2, com.corrodinggames.rts.game.units.custom.d.b b2, boolean bl2) {
        if (y2.aC != null) {
            y2.aC.a(y2, b2, bl2);
        }
    }

    public boolean j(am am2) {
        return am2.cg() || !this.c(am2.bX);
    }

    public boolean a(com.corrodinggames.rts.game.units.custom.d.b b2, am am2) {
        return this.a(b2, am2, false);
    }

    public boolean a(com.corrodinggames.rts.game.units.custom.d.b b2, am am2, boolean bl2) {
        return b2.b(am2);
    }

    public void a(com.corrodinggames.rts.game.a.a.a a2) {
        if (!this.bJ.contains(a2)) {
            this.bJ.add(a2);
        } else {
            this.c("Skipping add of component: " + a2.a().name());
        }
    }

    static /* synthetic */ boolean a(a a2, as as2) {
        return a2.a(as2);
    }

    static {
        bK = new ArrayList();
        bI = new u();
    }
}
