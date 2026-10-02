/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.an;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ap;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.e.f;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.game.units.d.p;
import com.corrodinggames.rts.game.units.d.r;
import com.corrodinggames.rts.game.units.e.b;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.ay;
import com.corrodinggames.rts.gameFramework.d.e;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.utility.o;
import com.corrodinggames.rts.gameFramework.utility.u;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public strictfp abstract class am
extends ay {
    public float br;
    public int bs = -9999;
    public am bt = null;
    public am bu = null;
    public am bv = null;
    public VariableScope bw;
    public com.corrodinggames.rts.game.units.custom.d.b bx;
    public com.corrodinggames.rts.game.units.custom.d.b by;
    public int bz = -9999;
    public int bA = -9999;
    public int bB = -9999;
    public int bC;
    public boolean bD;
    public static final u bE = new u();
    private static final o a = new o();
    public static HashMap bF = new HashMap();
    public static HashMap bG = new HashMap();
    public static HashMap bH = new HashMap();
    public static final Paint bI = new ag();
    public static final Paint bJ;
    static final LightingColorFilter bK;
    public boolean bL;
    public boolean bM = false;
    public boolean bN = false;
    public boolean bO = false;
    public boolean bP = false;
    public am bQ = null;
    public am bR;
    public float bS;
    public boolean bT = true;
    public int bU = 1;
    public boolean bV = false;
    public long bW = 0L;
    public n bX;
    public boolean bY;
    public float bZ = 0.0f;
    public float ca = 0.0f;
    public boolean cb = false;
    public float cc = 0.0f;
    public float cd = 0.0f;
    public float ce = 0.0f;
    public float cf = 0.0f;
    public float cg;
    public float ch;
    public boolean ci;
    public float cj;
    public float ck;
    public float cl;
    public float cm = 1.0f;
    public float cn = 1.0f;
    public boolean co = false;
    public boolean cp = false;
    public boolean cq = false;
    public boolean cr = false;
    public boolean cs = false;
    public boolean ct = false;
    public float cu;
    public float cv;
    public float cw;
    public float cx;
    public float cy;
    public float cz;
    public float cA;
    public float cB;
    public float cC;
    public float cD;
    public int cE;
    public int cF;
    public boolean cG;
    public int cH = -9999;
    public boolean cI;
    public float cJ = 0.0f;
    public boolean cK = true;
    public ap[] cL;
    public boolean cM;
    public am cN = null;
    public y cO = null;
    public com.corrodinggames.rts.game.units.custom.b.n cP = null;
    public int cQ = -9999;
    public boolean cR;
    public int cS;
    public int cT;
    public int cU;
    public float cV;
    public static final Paint cW;
    public static final Paint cX;
    public static final Paint cY;
    public static final Paint cZ;
    public static final Paint da;
    public static final Paint db;
    public static final Paint dc;
    public static final Paint dd;
    public static final Paint de;
    public static final Paint df;
    public static final Paint dg;
    public static final Paint dh;
    public static final Paint di;
    public static final Paint dj;
    public static final Paint dk;
    public int dl = -1;
    public int dm = -1;
    public int dn = -99;
    public float do;
    public float dp;
    public float dq = 70.0f;
    static final RectF dr;
    static Paint ds;
    static Paint dt;
    public static final RectF du;
    public static final Rect dv;
    static final Rect dw;
    static final ArrayList dx;
    static ArrayList dy;
    public as dz;
    static final RectF dA;
    static final RectF dB;
    static final Rect dC;
    static final PointF dD;
    static final PointF dE;
    an[] dF;
    static final PointF dG;
    f dH = new f();
    public com.corrodinggames.rts.game.units.custom.c.c dI = new com.corrodinggames.rts.game.units.custom.c.c();
    com.corrodinggames.rts.game.units.custom.d.b dJ = null;

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        Object object;
        int n2;
        as2.a(this.bM);
        as2.a(this.bQ);
        as2.a(this.bR);
        as2.a(this.bS);
        as2.a(this.bT);
        as2.a(this.bV);
        as2.a(this.bW);
        as2.a(this.bX);
        as2.a(this.bZ);
        as2.a(this.ca);
        as2.a(this.cc);
        as2.a(this.cd);
        as2.a(this.cf);
        as2.a(this.cg);
        as2.a(this.cj);
        as2.a(this.ck);
        as2.a(this.cl);
        as2.a(this.cm);
        as2.a(this.cp);
        as2.a(this.cs);
        as2.a(this.cu);
        as2.a(this.cv);
        as2.a(this.cK);
        as2.a(this.cL[0].a);
        as2.a(this.cL[0].d);
        as2.a(this.cN);
        as2.c(26);
        as2.a(this.cU);
        as2.a(this.cV);
        as2.a(this.ce);
        as2.a(this.ch);
        int n3 = this.bl();
        as2.a(n3);
        for (n2 = 0; n2 < n3; ++n2) {
            object = this.cL[n2];
            as2.a(((ap)object).a);
            as2.a(((ap)object).c);
            as2.a(((ap)object).d);
            as2.a(((ap)object).e);
            as2.a(((ap)object).f);
            as2.a(((ap)object).h);
            as2.a(((ap)object).i);
            am am2 = ((ap)object).j;
            if (am2 != null && am2.bV) {
                am2 = null;
            }
            as2.a(am2);
            as2.a(this.cM);
        }
        as2.a(this.bs);
        as2.a(this.cx);
        as2.a(this.cy);
        as2.a(this.cz);
        as2.a(this.cA);
        as2.a(this.cq);
        as2.a(this.cr);
        as2.a(this.ct);
        as2.a(this.bN);
        as2.a(this.cB);
        as2.a(this.ci);
        as2.a(this.dF != null);
        if (this.dF != null) {
            as2.a(this.dF.length);
            for (n2 = 0; n2 < this.dF.length; ++n2) {
                object = this.dF[n2];
                as2.a(((an)object).a);
                as2.a(((an)object).b);
            }
        }
        as2.a(this.cw);
        as2.b(this.bt);
        as2.a(this.cE);
        as2.a(this.cF);
        as2.a(this.bz);
        as2.a(this.bA);
        as2.a(this.bB);
        as2.a(this.bC);
        as2.a(this.bO);
        as2.a(this.bP);
        this.dH.a(as2);
        this.dI.a(as2);
        as2.b(this.cO);
        n2 = -1;
        if (this.cO != null && this.cP != null) {
            n2 = this.cP.a();
        }
        as2.a((short)n2);
        as2.a(this.cQ);
        VariableScope.writeOutUnitOrPlaceholder(as2, this.bu);
        VariableScope.writeOutUnitOrPlaceholder(as2, this.bv);
        VariableScope.writeOut(as2, this.bw);
        com.corrodinggames.rts.game.units.custom.d.b.a(as2, this.bx);
        com.corrodinggames.rts.game.units.custom.d.b.a(as2, this.by);
        as2.a(this.cn);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        Object object;
        int n2;
        int n3;
        this.bM = k2.e();
        this.bQ = k2.o();
        this.bR = k2.o();
        this.bS = k2.g();
        this.bT = k2.e();
        this.bV = k2.e();
        this.bW = k2.i();
        this.b(k2.r());
        this.bZ = k2.g();
        this.ca = k2.g();
        this.cc = k2.g();
        this.cd = k2.g();
        this.cf = k2.g();
        this.cg = k2.g();
        float f2 = k2.g();
        float f3 = k2.g();
        this.cl = k2.g();
        this.cm = k2.g();
        this.cp = k2.e();
        this.cs = k2.e();
        this.o(k2.g());
        this.cv = k2.g();
        this.cK = k2.e();
        this.cL[0].a = k2.g();
        this.cL[0].d = k2.g();
        this.cN = k2.o();
        byte by = k2.d();
        if (by >= 1) {
            this.cU = k2.f();
            this.cV = k2.g();
        }
        if (by >= 2) {
            this.ce = k2.g();
            this.ch = k2.g();
            n3 = k2.f();
            this.O(n3);
            for (n2 = 0; n2 < n3; ++n2) {
                object = this.cL[n2];
                ((ap)object).a = k2.g();
                ((ap)object).c = k2.g();
                ((ap)object).d = k2.g();
                ((ap)object).e = k2.g();
                ((ap)object).f = k2.g();
                if (by >= 8) {
                    ((ap)object).h = k2.g();
                    ((ap)object).i = k2.g();
                    ((ap)object).j = k2.o();
                }
                if (by < 12) continue;
                this.cM = k2.e();
            }
        }
        if (by >= 3) {
            this.bs = k2.f();
        }
        if (by >= 4) {
            this.cx = k2.g();
            this.cy = k2.g();
            this.cz = k2.g();
            this.cA = k2.g();
        }
        if (by >= 5) {
            this.cq = k2.e();
            this.cr = k2.e();
        }
        if (by >= 6) {
            this.ct = k2.e();
        }
        if (by >= 7) {
            this.bN = k2.e();
        }
        if (by >= 9) {
            this.cB = k2.g();
        }
        if (by >= 10) {
            this.ci = k2.e();
        }
        if (by >= 11 && (n3 = (int)(k2.e() ? 1 : 0)) != 0) {
            this.dF = new an[k2.f()];
            for (n2 = 0; n2 < this.dF.length; ++n2) {
                this.dF[n2] = new an();
                object = this.dF[n2];
                ((an)object).a = k2.e();
                ((an)object).b = k2.f();
            }
        }
        if (by >= 13) {
            this.cw = k2.g();
        }
        if (by >= 14) {
            this.bt = k2.o();
        }
        if (by >= 15) {
            this.cE = k2.f();
            this.cF = k2.f();
        }
        if (by >= 16) {
            this.bz = k2.f();
            this.bA = k2.f();
            this.bB = k2.f();
        }
        if (by >= 17) {
            this.bC = k2.f();
        }
        if (by >= 18) {
            this.bO = k2.e();
            this.bP = k2.e();
        }
        if (by >= 19) {
            this.dH.a(k2);
            this.dI.a(this, k2);
        }
        if (by >= 20) {
            y y2 = k2.p();
            n2 = k2.v();
            if (n2 != -1) {
                com.corrodinggames.rts.game.units.custom.b.n n4;
                boolean bl = false;
                if (y2 != null && this instanceof y && (n4 = y2.a((short)n2)) != null && y2.a((y)this, n4)) {
                    bl = true;
                }
                if (!bl) {
                    this.cj();
                }
            }
        }
        if (by >= 21) {
            this.cQ = k2.f();
        }
        if (by >= 22) {
            if (by < 24) {
                throw new IOException("extension >=22 but <24");
            }
            this.bu = VariableScope.readInUnitOrPlaceholder(k2);
            this.bv = VariableScope.readInUnitOrPlaceholder(k2);
        }
        if (by >= 23) {
            this.bw = VariableScope.readIn(k2);
        }
        if (by >= 25) {
            this.bx = com.corrodinggames.rts.game.units.custom.d.b.a(k2);
            this.by = com.corrodinggames.rts.game.units.custom.d.b.a(k2);
        }
        if (by >= 26) {
            this.cn = k2.g();
        }
        if (this.bV) {
            com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
            bE.remove(this);
            l2.cc.a(this);
        }
        super.a(k2);
    }

    public static com.corrodinggames.rts.gameFramework.m.e a(com.corrodinggames.rts.gameFramework.m.e e2) {
        return am.a(e2, e2.m(), e2.l());
    }

    public static com.corrodinggames.rts.gameFramework.m.e a(com.corrodinggames.rts.gameFramework.m.e e2, int n2, int n3) {
        com.corrodinggames.rts.gameFramework.m.e e3 = e2.a(n2, n3, false);
        e2.x();
        e3.j();
        int n4 = e3.m();
        int n5 = e3.l();
        for (int j = 0; j < n4; ++j) {
            for (int i2 = 0; i2 < n5; ++i2) {
                int n6 = e2.a(j, i2);
                e3.a(j, i2, Color.a(Color.a(n6), 0, 0, 0));
            }
        }
        e3.p();
        e3.s();
        e2.y();
        e3.a("shadow:" + e2.a());
        e3.n = true;
        return e3;
    }

    public static o bF() {
        a.a();
        return a;
    }

    public static void bG() {
        a.a();
    }

    public static void bH() {
        j.dt();
        d.dt();
        com.corrodinggames.rts.game.units.e.h.K();
        com.corrodinggames.rts.game.units.h.f.M();
        com.corrodinggames.rts.game.units.b.b.K();
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.as()) {
            for (ar ar2 : EnumSet.allOf(ar.class)) {
                ar2.b();
            }
        } else {
            b.K();
            p.b();
            r.M();
            al.b();
        }
        ar.t();
    }

    public boolean bI() {
        return false;
    }

    public boolean bJ() {
        return false;
    }

    public static HashMap bK() {
        am am2;
        HashMap<as, am> hashMap = new HashMap<as, am>();
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.as()) {
            for (ar as2 : EnumSet.allOf(ar.class)) {
                am2 = as2.a(true);
                am2.a();
                am2.b(n.i);
                am2.cp = true;
                hashMap.put(as2, am2);
            }
        }
        for (l l3 : l.d) {
            am2 = l3.a(true);
            am2.a();
            am2.b(n.i);
            am2.cp = true;
            hashMap.put(l3, am2);
        }
        return hashMap;
    }

    public static void bL() {
        bG = am.bK();
        bH = am.bK();
        bF = am.bK();
    }

    public static am a(as as2) {
        am am2 = (am)bF.get(as2);
        return am2;
    }

    public static am b(as as2) {
        return am.c(as2);
    }

    public static am c(as as2) {
        am am2 = (am)bG.get(as2);
        if (am2 == null) {
            if (l.b == null) {
                com.corrodinggames.rts.gameFramework.l.e("Could not find:" + as2.i() + " and missing place holder is null");
                return null;
            }
            am2 = (am)bG.get(l.b);
            if (am2 == null) {
                com.corrodinggames.rts.gameFramework.l.e("name: " + l.b.M);
                com.corrodinggames.rts.gameFramework.l.e("contains:" + bG.containsKey(l.b));
                for (as as3 : bG.keySet()) {
                    com.corrodinggames.rts.gameFramework.l.e("has:" + as3.i());
                }
                com.corrodinggames.rts.gameFramework.l.e("Could not find:" + as2.i() + " and missing place holder could not be found");
            }
        }
        return am2;
    }

    public static am d(as as2) {
        am am2 = (am)bH.get(as2);
        if (am2 == null) {
            am2 = (am)bH.get(l.b);
        }
        return am2;
    }

    public static int bM() {
        int n2 = 0;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.as()) {
            for (ar ar2 : EnumSet.allOf(ar.class)) {
                am am2 = am.a(ar2);
                n2 = n2 * 31 + am2.bw();
            }
        }
        return n2;
    }

    protected am(boolean bl2) {
        super(bl2);
        this.bS();
        if (!bl2) {
            this.bL = true;
            bE.a(this);
            a.a(this);
        }
        this.bz = com.corrodinggames.rts.gameFramework.l.B().by;
        this.dz = this.r();
    }

    @Override
    public void a() {
        n.a(this);
        if (this.bL) {
            bE.remove(this);
            a.b(this);
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bS.l(this);
        super.a();
    }

    public float bN() {
        return 3000.0f;
    }

    public int s(am am2) {
        return 0;
    }

    public boolean bO() {
        return false;
    }

    public boolean bP() {
        return false;
    }

    public float bQ() {
        return -1.0f;
    }

    final void bR() {
        int n2 = 1;
        if (this.i()) {
            n2 = 3;
        } else if (this.Q()) {
            n2 = 2;
        }
        if (this.cN != null) {
            n2 = -1;
        }
        if (!this.bT) {
            n2 = -1;
        }
        this.bU = n2;
    }

    public void o(float f2) {
        this.cu = f2;
    }

    public void bS() {
        this.O(1);
    }

    public void O(int n2) {
        int n3;
        int n4 = this.bl();
        if (n4 < n2) {
            n4 = n2;
        }
        if (this.cL == null) {
            n3 = 0;
            this.cL = new ap[n4];
        } else if (this.cL.length < n4) {
            n3 = this.cL.length;
            this.cL = Arrays.copyOf(this.cL, n4);
        } else {
            return;
        }
        for (int i2 = n3; i2 < this.cL.length; ++i2) {
            this.cL[i2] = new ap();
        }
    }

    public static void a(Paint paint) {
        am.a(paint, false);
    }

    public static void a(Paint paint, boolean bl2) {
        if (!com.corrodinggames.rts.gameFramework.l.av() && bl2) {
            paint.a(0.0f);
        }
    }

    public float d(boolean bl2) {
        return this.cg + 90.0f;
    }

    public final boolean bT() {
        if (this.cN != null) {
            return false;
        }
        return this.cm >= 1.0f;
    }

    public float x() {
        if (this.cu < this.cv) {
            return this.cu / this.cv;
        }
        return -1.0f;
    }

    public boolean bU() {
        return true;
    }

    public float bV() {
        if (this.cm < 1.0f && (this.cO == null || this.cO.cm >= 1.0f)) {
            return this.cm;
        }
        return -1.0f;
    }

    public float bW() {
        return -1.0f;
    }

    public boolean bX() {
        return false;
    }

    public int bY() {
        return -1;
    }

    public int bZ() {
        return -1;
    }

    @Override
    public void a(float f2, boolean bl2) {
        int n2;
        if (this.bV || this.cN != null) {
            return;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f3 = this.cj;
        if (bl2) {
            return;
        }
        boolean bl3 = false;
        int n3 = this.bY();
        if (this.bV() >= 0.0f) {
            bl3 = true;
        }
        if (this.bW() >= 0.0f) {
            bl3 = true;
        }
        if (this.cG || l2.bQ.showHp) {
            if (this.x() >= 0.0f) {
                bl3 = true;
            }
            if (n3 >= 0) {
                bl3 = true;
            }
        }
        if (!bl3) {
            return;
        }
        float f4 = this.eo - l2.cw;
        float f5 = this.ep - l2.cx - this.eq;
        float f6 = f3 + 4.0f;
        int n4 = 120;
        int n5 = 200;
        int n6 = 4;
        float f7 = 2.0f * f3;
        float f8 = this.co ? 1.0f : l2.cX;
        if (f8 < 1.0f) {
            l2.bO.k();
            l2.S();
            f4 *= l2.cX;
            f5 *= l2.cX;
            f6 *= l2.cX;
        }
        float f9 = 3.0f;
        if (this.cG || l2.bQ.showHp) {
            if (this.x() >= 0.0f) {
                n2 = 0;
                boolean bl4 = false;
                com.corrodinggames.rts.game.units.custom.b.n n7 = this.dn();
                if (n7 != null) {
                    n2 = n7.p ? 1 : 0;
                    bl4 = n7.q;
                }
                if (!bl4) {
                    int n8;
                    int n9;
                    if (l2.bs.c(this.bX)) {
                        n9 = com.corrodinggames.rts.gameFramework.f.b(200, 183, 44, 44);
                        n8 = com.corrodinggames.rts.gameFramework.f.b(120, 255, 60, 60);
                    } else {
                        n9 = com.corrodinggames.rts.gameFramework.f.b(200, 0, 150, 0);
                        n8 = com.corrodinggames.rts.gameFramework.f.b(120, 0, 230, 0);
                    }
                    Paint paint = com.corrodinggames.rts.gameFramework.utility.y.a(n9, Paint$Style.a);
                    Paint paint2 = com.corrodinggames.rts.gameFramework.utility.y.a(n8, Paint$Style.b);
                    int n10 = n6;
                    if (n2 != 0) {
                        n10 = 1;
                    }
                    dr.a(f4 - f3, f5 + f6, f4 - f3 + f7 * this.x(), f5 + f6 + (float)n10);
                    l2.bO.a(dr, paint);
                    dr.a(f4 - f3, f5 + f6, f4 - f3 + f7, f5 + f6 + (float)n10);
                    l2.bO.a(dr, paint2);
                    if (this.cC != 0.0f && this.bU() && l2.bQ.showHpChanges) {
                        float f10 = this.x();
                        float f11 = f10 + -this.cC / this.cv;
                        if (f11 < 0.0f) {
                            f11 = 0.0f;
                        }
                        if (f11 >= 1.0f) {
                            f11 = 1.0f;
                        }
                        int n11 = com.corrodinggames.rts.gameFramework.f.b(100, 232, 208, 26);
                        Paint paint3 = com.corrodinggames.rts.gameFramework.utility.y.a(n11, Paint$Style.a);
                        dr.a(f4 - f3 + f7 * f10, f5 + f6, f4 - f3 + f7 * f11, f5 + f6 + (float)n10);
                        l2.bO.a(dr, paint3);
                    }
                }
            }
            if (n3 >= 0) {
                n2 = this.bZ();
                float f12 = f7;
                if (n2 > 10) {
                    f12 += 20.0f;
                }
                float f13 = f4 - f12 / 2.0f;
                float f14 = (int)(f12 / (float)n2 + 0.5f);
                float f15 = f14 - 2.0f;
                float f16 = 3.0f;
                for (int i2 = 1; i2 <= n2; ++i2) {
                    float f17 = f13 + (float)(i2 - 1) * f14;
                    dr.a(f17, f5 + f6 + f9, f17 + f15, f5 + f6 + f9 + 3.0f);
                    if (n3 >= i2) {
                        l2.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(240, 0, 0, 255, Paint$Style.a));
                    }
                    l2.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(110, 0, 0, 210, Paint$Style.b));
                }
                f9 += 5.0f;
            }
        }
        if (this.bW() >= 0.0f) {
            n2 = n6;
            n6 = 2;
            int n12 = n6 + 1;
            boolean bl5 = this.bX();
            dr.a(f4 - f3, f5 + f6 + (float)n12 + f9, f4 - f3 + f7 * this.bW(), f5 + f6 + (float)n12 + (float)n6 + f9);
            int n13 = bl5 ? com.corrodinggames.rts.gameFramework.f.b(185, 103, 117, 119) : com.corrodinggames.rts.gameFramework.f.b(200, 23, 179, 207);
            l2.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(n13, Paint$Style.a));
            dr.a(f4 - f3, f5 + f6 + (float)n12 + f9, f4 - f3 + f7, f5 + f6 + (float)n12 + (float)n6 + f9);
            n13 = bl5 ? com.corrodinggames.rts.gameFramework.f.b(105, 123, 182, 193) : com.corrodinggames.rts.gameFramework.f.b(120, 45, 211, 241);
            l2.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(n13, Paint$Style.b));
            f9 += (float)n6;
            n6 = n2;
        }
        if (this.bV() >= 0.0f) {
            n2 = n6 + 1;
            dr.a(f4 - f3, f5 + f6 + (float)n2 + f9, f4 - f3 + f7 * this.bV(), f5 + f6 + (float)n2 + (float)n6 + f9);
            l2.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(200, 0, 0, 150, Paint$Style.a));
            dr.a(f4 - f3, f5 + f6 + (float)n2 + f9, f4 - f3 + f7, f5 + f6 + (float)n2 + (float)n6 + f9);
            l2.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(120, 0, 0, 230, Paint$Style.b));
            f9 += (float)n6;
        }
        if (f8 < 1.0f) {
            l2.bO.l();
        }
    }

    @Override
    public void d(float f2) {
    }

    @Override
    public void p(float f2) {
        if (this.bV || this.cN != null) {
            return;
        }
        if (this.cG) {
            com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
            if (this.bX == l2.bs || l2.bS.m(this)) {
                if (l2.bQ.showUnitWaypoints && l2.dw <= 40) {
                    ++l2.dw;
                    this.O();
                }
                this.ca();
            }
            if (com.corrodinggames.rts.gameFramework.utility.y.a(this)) {
                this.cb();
            }
        }
    }

    public void ca() {
    }

    public void O() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        au au2 = null;
        au au3 = null;
        if (this instanceof y) {
            y y2 = (y)this;
            int n2 = y2.av();
            float f2 = this.eo;
            float f3 = this.ep;
            for (int i2 = 0; i2 < n2; ++i2) {
                float f4;
                au au4 = y2.k(i2);
                if (au4 == null) continue;
                if (com.corrodinggames.rts.gameFramework.l.av()) {
                    ds.a(2.0f);
                } else {
                    ds.a(0.0f);
                }
                int n3 = 160;
                if (au4.d() == av.b) {
                    ds.b(Color.a(n3, 180, 0, 0));
                } else if (au4.d() == av.h) {
                    ds.b(Color.a(n3, 180, 180, 0));
                } else if (au4.d() == av.c) {
                    ds.b(Color.a(n3, 0, 0, 180));
                } else if (au4.d() == av.d) {
                    ds.b(Color.a(n3, 0, 0, 180));
                } else if (au4.d() == av.e || au4.d() == av.i) {
                    ds.b(Color.a(n3, 0, 180, 180));
                } else if (au4.d() == av.g) {
                    ds.b(Color.a(n3, 180, 0, 42));
                } else if (au4.d() == av.k || au4.d() == av.l) {
                    ds.b(Color.a(n3, 97, 20, 229));
                } else if (au4.d() == av.j) {
                    ds.b(Color.a(n3, 0, 210, 210));
                    if (au2 == null) {
                        au2 = au4;
                    } else {
                        au3 = au4;
                    }
                } else {
                    ds.b(Color.a(n3, 0, 180, 0));
                }
                float f5 = au4.g();
                float f6 = au4.h();
                am am2 = au4.i();
                if (am2 != null && au4.f() && !am2.bI() && !am2.d(l2.bs)) {
                    float f7 = 400.0f;
                    f4 = com.corrodinggames.rts.gameFramework.f.d(f2, f3, f5, f6);
                    f5 = f2 + com.corrodinggames.rts.gameFramework.f.k(f4) * f7;
                    f6 = f3 + com.corrodinggames.rts.gameFramework.f.j(f4) * f7;
                }
                l2.bO.a(f2 - l2.cw, f3 - l2.cx, f5 - l2.cw, f6 - l2.cx, ds);
                boolean bl2 = false;
                if (bl2) {
                    f4 = com.corrodinggames.rts.gameFramework.f.b(f2, f3, f5, f6);
                    float f8 = com.corrodinggames.rts.gameFramework.f.d(f2, f3, f5, f6);
                    float f9 = l2.bS.aT * f4;
                    float f10 = f2 + com.corrodinggames.rts.gameFramework.f.k(f8) * f9;
                    float f11 = f3 + com.corrodinggames.rts.gameFramework.f.j(f8) * f9;
                    dr.a(f10 - 1.0f, f11 - 1.0f, f10 + 1.0f, f11 + 1.0f);
                    dr.a(-l2.cw, -l2.cx);
                    l2.bO.a(dr, ds);
                }
                f2 = f5;
                f3 = f6;
            }
        }
        if (au2 != null && au3 != null && au2 != au3) {
            ds.b(Color.a(50, 0, 210, 210));
            float f12 = au3.g();
            float f13 = au3.h();
            au au5 = au2;
            l2.bO.a(f12 - l2.cw, f13 - l2.cx, au5.g() - l2.cw, au5.h() - l2.cx, ds);
        }
    }

    public void cb() {
    }

    @Override
    public void e(float f2) {
        boolean bl2 = false;
        if (this.cJ != 0.0f) {
            this.cJ = com.corrodinggames.rts.gameFramework.f.a(this.cJ, f2);
            if (this.cJ % 15.0f < 7.0f) {
                bl2 = true;
            }
        }
        if (this.cG || bl2) {
            com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
            if (this.dp()) {
                Paint paint;
                float f3 = this.eo - l2.cw;
                float f4 = this.ep - l2.cx - this.eq;
                n n2 = l2.bs;
                if (n2 == this.bX) {
                    paint = this.ck < 8.0f ? cX : cY;
                } else if (n2.c(this.bX)) {
                    paint = da;
                } else if (this.bX != null && l2.cb.j()) {
                    cW.b(n.i(this.bX.r));
                    paint = cW;
                } else {
                    paint = dc;
                }
                if (bl2) {
                    paint = db;
                }
                if (this.bI()) {
                    Rect rect;
                    if (paint == cY) {
                        paint = cZ;
                    }
                    if ((rect = this.ce()) != null) {
                        dr.a(rect);
                        am.dr.b *= (float)l2.bL.o;
                        am.dr.d *= (float)l2.bL.o;
                        am.dr.a *= (float)l2.bL.n;
                        am.dr.c *= (float)l2.bL.n;
                        float f5 = this.db();
                        dr.a(-(this.cZ() - (float)l2.bL.p), -(this.da() - (float)l2.bL.q));
                        com.corrodinggames.rts.gameFramework.f.a(dr, f5);
                        dr.a(f3, f4);
                        float f6 = 11.0f;
                        l2.bO.a(am.dr.a - f6, am.dr.b, am.dr.c + f6, am.dr.b, paint);
                        l2.bO.a(am.dr.a - f6, am.dr.d, am.dr.c + f6, am.dr.d, paint);
                        l2.bO.a(am.dr.a, am.dr.b - f6, am.dr.a, am.dr.d + f6, paint);
                        l2.bO.a(am.dr.c, am.dr.b - f6, am.dr.c, am.dr.d + f6, paint);
                    }
                } else {
                    float f7 = this.ck + l2.bS.r(this);
                    if (l2.a(f3, f4, f7)) {
                        l2.bO.a(f3, f4, f7, paint);
                    }
                }
            }
        }
    }

    @Override
    public boolean c(float f2) {
        return true;
    }

    public Rect cc() {
        return dw;
    }

    public Rect cd() {
        return dw;
    }

    public Rect ce() {
        return this.cc();
    }

    public com.corrodinggames.rts.gameFramework.m.e v() {
        return null;
    }

    @Override
    public boolean f(float f2) {
        com.corrodinggames.rts.gameFramework.m.e e2 = this.v();
        if (e2 == null) {
            return false;
        }
        if (this.bV) {
            return true;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bO.l();
        float f3 = (int)(this.eo - l2.cw);
        float f4 = (int)(this.ep - l2.cx);
        Paint paint = null;
        paint = this.cG ? bJ : bI;
        l2.bO.a(e2, f3 *= l2.cX, f4 *= l2.cX, paint);
        l2.bO.k();
        l2.R();
        return true;
    }

    @Override
    public boolean a(com.corrodinggames.rts.gameFramework.l l2) {
        if (!l2.cO.b(this.eo, this.ep)) {
            return false;
        }
        if (this.cN != null) {
            return false;
        }
        if (this.cP != null && (this.cP.I || this.cP.C)) {
            return false;
        }
        return this.d(l2.bs);
    }

    public boolean c_() {
        return true;
    }

    public final boolean cf() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return this.d(l2.bs);
    }

    public boolean d(n n2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.game.b.b b2 = l2.bL;
        if ((this.bX != n2 || this.cO != null) && b2.E && n2.N != null) {
            b2.a(this.eo, this.ep);
            int n3 = b2.T;
            int n4 = b2.U;
            if (b2.c(n3, n4) && n2.N[n3][n4] >= 5) {
                return false;
            }
        }
        return true;
    }

    public boolean cg() {
        return true;
    }

    public void f_() {
        if (this.bV) {
            this.bT = false;
            return;
        }
        this.bT = true;
    }

    @Override
    public void a(float f2) {
        if (!this.bV) {
            if (this.cw > 0.0f) {
                if (this.cw > this.cv * 2.0f) {
                    this.cw = this.cv * 2.0f;
                }
                this.cw = com.corrodinggames.rts.gameFramework.f.a(this.cw, f2);
            }
            if (this.cu < this.cv * 0.33f && this.eq > -1.0f) {
                e e2;
                com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
                this.do += f2;
                this.dp += f2;
                this.dq += f2;
                if (this.do > 10.0f && this.dp < 300.0f && !this.dl()) {
                    this.do = 0.0f;
                    if (this.el && l2.dd && (e2 = l2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.a)) != null) {
                        com.corrodinggames.rts.gameFramework.d.f.b(e2, true);
                        e2.I = this.eo;
                        e2.J = this.ep;
                        e2.K = this.eq;
                        e2.P += com.corrodinggames.rts.gameFramework.f.c(-0.1f, 0.1f) + this.cc;
                        e2.Q += com.corrodinggames.rts.gameFramework.f.c(-0.1f, 0.1f) + this.cd;
                        e2.I += com.corrodinggames.rts.gameFramework.f.c(-4.0f, 4.0f);
                        e2.J += com.corrodinggames.rts.gameFramework.f.c(-4.0f, 4.0f);
                    }
                }
                if (this.dq > 30.0f && this.dp < 600.0f && !this.dm()) {
                    this.dq = 0.0f;
                    l2.bR.a();
                    e2 = l2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.a);
                    if (e2 != null) {
                        com.corrodinggames.rts.gameFramework.d.f.a(e2, true);
                        e2.I = this.eo;
                        e2.J = this.ep;
                        e2.K = this.eq;
                        e2.P += com.corrodinggames.rts.gameFramework.f.c(-0.1f, 0.1f);
                        e2.Q += com.corrodinggames.rts.gameFramework.f.c(-0.1f, 0.1f);
                        e2.I += com.corrodinggames.rts.gameFramework.f.c(-4.0f, 4.0f);
                        e2.J += com.corrodinggames.rts.gameFramework.f.c(-4.0f, 4.0f);
                    }
                }
            } else if (this.dp != 0.0f) {
                this.dp = 0.0f;
            }
            if (this.cC != 0.0f) {
                this.cC = com.corrodinggames.rts.gameFramework.f.a(this.cC, this.cv * this.cD * 0.005f * f2);
                this.cD += f2 + 0.2f * this.cD * f2;
                if (this.cC == 0.0f) {
                    this.cD = 0.0f;
                }
            }
            if (this.cu <= 0.0f) {
                this.ch();
            }
        }
    }

    public float b(am am2, float f2, com.corrodinggames.rts.game.f f3) {
        float f4;
        float f5;
        float f6 = f2;
        float f7 = 1.0f;
        float f8 = 1.0f;
        float f9 = 1.0f;
        if (f3 != null) {
            f7 = f3.ak;
            f8 = f3.al;
            f9 = f3.am;
        }
        if (this.cx < this.cA) {
            f5 = this.cA - this.cx;
            f4 = f6 * f7;
            if (f5 > f4) {
                this.cx += f4;
                f6 -= f4 * f8;
            } else {
                this.cx = this.cA;
                f6 -= f4 * f8;
            }
        }
        if (f6 > 0.0f && this.cu < this.cv) {
            f4 = this.cv - this.cu;
            f5 = f6 * f9;
            if (f4 > f5) {
                this.o(this.cu + f5);
                f6 = 0.0f;
            } else {
                this.o(this.cv);
                f6 -= f4;
            }
        }
        return 0.0f;
    }

    public boolean J() {
        return false;
    }

    public float a(am am2, float f2, com.corrodinggames.rts.game.f f3) {
        float f4;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.cm < 1.0f) {
            f2 *= 1.75f;
        }
        float f5 = 1.0f;
        float f6 = 1.0f;
        float f7 = 1.0f;
        if (f3 != null) {
            f5 = f3.ak;
            f6 = f3.al;
            f7 = f3.am;
        }
        float f8 = f2;
        float f9 = 0.0f;
        if (this.cz == 0.0f && this.cx > 0.0f) {
            f4 = f8 * f5;
            if (this.cx < f4) {
                f8 -= this.cx * f6;
                f9 += this.cx;
                this.cy += this.cx;
                this.cx = 0.0f;
            } else {
                this.cx -= f4;
                this.cy += f4;
                f9 += f4;
                f8 -= f8 * f6;
            }
        }
        if (f8 > 0.0f) {
            f4 = f8 * f7;
            if (this.cu < f4) {
                f8 -= this.cu;
                f9 += this.cu;
                this.o(0.0f);
                this.cC += this.cu;
            } else {
                this.o(this.cu - f4);
                f9 += f4;
                f8 -= f4;
                this.cC -= f4;
            }
        }
        this.bs = l2.by;
        this.bt = am2 != null ? am2 : null;
        this.ch();
        return f8;
    }

    public am q(float f2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if ((float)l2.by - f2 * 1000.0f < (float)this.bs) {
            return this.bt;
        }
        return null;
    }

    public void ch() {
        if (!this.bV && this.cu <= 0.0f) {
            this.bv();
        }
    }

    public void n() {
    }

    public boolean e() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        return false;
    }

    public void bt() {
    }

    public void bu() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bS.l(this);
        n.a(this);
        if (bE.remove(this)) {
            // empty if block
        }
        this.bV = true;
        this.bW = l2.by;
        if (this.cu > 0.0f) {
            this.cu = 0.0f;
        }
        if (this.cL != null) {
            int n2 = this.bl();
            for (int i2 = 0; i2 < n2; ++i2) {
                this.cL[i2].j = null;
            }
        }
        l2.cc.a(this);
    }

    public void ci() {
        this.bu();
        this.a();
        this.bt();
    }

    public void cj() {
        this.cu = -1.0f;
    }

    public void bv() {
        this.bu();
        if (!this.e()) {
            this.a();
        }
        this.bt();
    }

    public boolean a(RectF rectF) {
        return this.eo + this.cj > rectF.a && this.eo - this.cj < rectF.c && this.ep + this.cj > rectF.b && this.ep - this.cj < rectF.d;
    }

    public final boolean c(float f2, float f3, float f4) {
        float f5;
        float f6 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f2, f3);
        return f6 < (f5 = this.cj + f4) * f5;
    }

    public boolean t(am am2) {
        float f2;
        float f3 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, am2.eo, am2.ep);
        return f3 < (f2 = this.cj + am2.cj) * f2;
    }

    public final void P(int n2) {
        n n3 = n.k(n2);
        if (n3 == null) {
            throw new com.corrodinggames.rts.game.b.f("Could not find team with id: " + n2);
        }
        this.e(n3);
    }

    public void e(n n2) {
        if (this.bX == n2) {
            return;
        }
        if (n2 == null) {
            throw new RuntimeException("Could not set team to null");
        }
        if (this.bX != null) {
            n.b(this);
            this.bX.d(this);
        }
        this.b(n2);
        n.c(this);
        if (n2 != n.i) {
            this.c(false);
        }
    }

    public void b(n n2) {
        if (n2 == null) {
            throw new RuntimeException("Could not set team to null");
        }
        this.bX = n2;
    }

    public final void Q(int n2) {
        this.bX = n.k(n2);
        if (this.bX == null) {
            throw new com.corrodinggames.rts.game.b.f("Could not find team with id: " + n2);
        }
        this.b(this.bX);
    }

    public ArrayList N() {
        return dx;
    }

    public int V() {
        return 1;
    }

    public void a(s s2, boolean bl2) {
    }

    public void a(s s2, boolean bl2, PointF pointF, am am2) {
        this.a(s2, bl2);
    }

    public void b(s s2, boolean bl2) {
    }

    public void a(s s2) {
    }

    public s a(c c2) {
        ArrayList arrayList = this.N();
        int n2 = arrayList.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            s s2 = (s)arrayList.get(i2);
            if (!s2.d(c2)) continue;
            return s2;
        }
        return null;
    }

    public boolean ck() {
        if (this.bI()) {
            return s.c(this.cm());
        }
        return false;
    }

    public boolean cl() {
        return false;
    }

    public c cm() {
        return s.i;
    }

    public float cn() {
        return -1.0f;
    }

    public boolean co() {
        return false;
    }

    public void a(ArrayList arrayList) {
        arrayList.clear();
    }

    public c cp() {
        return s.i;
    }

    public s e(as as2) {
        return null;
    }

    public final int cq() {
        int n2 = 0;
        for (s s2 : this.N()) {
            if (!s2.b(this) && !s2.s()) continue;
            ++n2;
        }
        return n2;
    }

    public boolean c(am am2, boolean bl2) {
        am am3 = am2.cN;
        y y2 = am2.cO;
        am2.cN = null;
        am2.cO = null;
        boolean bl3 = this.d(am2, bl2);
        am2.cN = am3;
        am2.cO = y2;
        return bl3;
    }

    public boolean d(am am2, boolean bl2) {
        return false;
    }

    public boolean e(am am2, boolean bl2) {
        return false;
    }

    public boolean cr() {
        return false;
    }

    public float cs() {
        return 21.0f;
    }

    public abstract ao h();

    public abstract boolean i();

    public boolean ct() {
        return this.i();
    }

    public abstract boolean Q();

    public abstract boolean aj();

    public abstract boolean ak();

    public boolean cu() {
        return false;
    }

    public boolean cv() {
        return false;
    }

    public boolean P() {
        return false;
    }

    public int cw() {
        return 1;
    }

    public abstract boolean s_();

    public int y() {
        return 85;
    }

    public float f(as as2) {
        int n2 = as2.a(this) + this.y();
        return n2;
    }

    public int u(am am2) {
        return this.y() + am2.r().a(this);
    }

    public int v(am am2) {
        return this.y() + am2.r().a(this);
    }

    public boolean w(am am2) {
        return false;
    }

    public boolean x(am am2) {
        return false;
    }

    public float b(am am2) {
        return 1.0f;
    }

    public float c(am am2) {
        return 0.2f;
    }

    public boolean y(am am2) {
        boolean bl2;
        boolean bl3 = false;
        boolean bl4 = bl2 = am2.g() > 0.0f;
        if (bl2) {
            bl3 = true;
        }
        return bl3;
    }

    public float z(am am2) {
        boolean bl2;
        float f2 = 5.1f;
        float f3 = this.c(am2) * f2;
        boolean bl3 = bl2 = am2.g() > 0.0f;
        if (bl2) {
            f3 = am2.g();
        }
        return f3;
    }

    public float cx() {
        return 1.0f;
    }

    public float cy() {
        return 0.0f;
    }

    public f cz() {
        float f2 = this.cy();
        if (f2 == 0.0f) {
            return f.a;
        }
        f f3 = new f();
        f3.b(com.corrodinggames.rts.game.units.custom.e.a.D, f2);
        return f3;
    }

    public f cA() {
        return f.a;
    }

    public abstract as r();

    public String cB() {
        return this.r().i() + "(id:" + this.eh + ")";
    }

    public static String f(am am2, boolean bl2) {
        if (am2 != null) {
            return am2.r().e();
        }
        return "No unit";
    }

    public static String A(am am2) {
        if (am2 != null) {
            return am2.c();
        }
        return "<null unit>";
    }

    public String c() {
        String string = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep + " id:" + this.eh + "";
        if (this.bX != null) {
            string = string + " t:" + this.bX.k;
        }
        if (this.bV) {
            string = string + " [dead]";
        }
        if (this.ej) {
            string = string + " [deleted]";
        }
        string = string + ")";
        return string;
    }

    public String cC() {
        String string = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep + " id:" + this.eh + "";
        string = string + ", hp:" + this.cu + ", dead:" + this.bV + ", deleted:" + this.ej + " tags:" + this.de();
        if (this.bX != null) {
            string = string + " t:" + this.bX.k;
        }
        string = string + ")";
        return string;
    }

    public float cD() {
        return 1.0f;
    }

    public RectF cE() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f2 = this.cD();
        dA.a(this.eo - this.eu * f2 - l2.cw, this.ep - this.ev * f2 - l2.cx, this.eo + this.eu * f2 - l2.cw, this.ep + this.ev * f2 - l2.cx);
        return dA;
    }

    public RectF cF() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        RectF rectF = dA;
        float f2 = l2.cw;
        float f3 = l2.cx;
        float f4 = this.eu;
        float f5 = this.ev;
        rectF.a = this.eo - f4 - f2;
        rectF.c = this.eo + f4 - f2;
        rectF.b = this.ep - f5 - f3;
        rectF.d = this.ep + f5 - f3;
        return rectF;
    }

    public boolean cG() {
        return false;
    }

    public Rect a_(boolean bl2) {
        int n2 = 0;
        int n3 = 0;
        am.dC.a = n2;
        am.dC.b = n3;
        am.dC.c = n2 + this.es;
        am.dC.d = n3 + this.et;
        return dC;
    }

    public Rect a(boolean bl2, int n2) {
        int n3 = 0;
        int n4 = 0;
        dC.a(n3 += n2 * this.es, n4, n3 + this.es, n4 + this.et);
        return dC;
    }

    public Rect a(boolean bl2, int n2, int n3) {
        int n4 = this.es;
        int n5 = this.et;
        int n6 = n2 * n4;
        int n7 = n3 * n5;
        am.dC.a = n6;
        am.dC.b = n7;
        am.dC.c = n6 + n4;
        am.dC.d = n7 + n5;
        return dC;
    }

    public boolean a(am am2, float f2) {
        return false;
    }

    public void a_(String string) {
    }

    public final boolean cH() {
        return this.cJ() && !(this.eq > 2.0f);
    }

    public boolean cI() {
        return com.corrodinggames.rts.gameFramework.utility.y.b(this.eo, this.ep);
    }

    public boolean cJ() {
        return com.corrodinggames.rts.gameFramework.utility.y.c(this.eo, this.ep);
    }

    public boolean cK() {
        return com.corrodinggames.rts.gameFramework.utility.y.d(this.eo, this.ep);
    }

    public int bw() {
        int n2 = 0;
        n2 = n2 * 31 + (int)this.bN();
        n2 = n2 * 31 + (int)this.cv;
        return n2;
    }

    public int cL() {
        return this.r().b(this.V());
    }

    public com.corrodinggames.rts.game.units.custom.d.b cM() {
        return this.r().d(this.V());
    }

    public com.corrodinggames.rts.game.units.custom.d.b cN() {
        return null;
    }

    public PointF a(float f2, float f3, float f4, float f5, float f6) {
        float f7 = 0.0f;
        if ((double)f4 > 0.1 && this.cK) {
            float f8 = 1.0f / f4;
            for (int i2 = 0; i2 < 3; ++i2) {
                PointF pointF = this.m(f7);
                float f9 = com.corrodinggames.rts.gameFramework.f.b(f2, f3, pointF.a, pointF.b);
                f7 = f9 * f8;
            }
        }
        if (f7 > f5) {
            f7 = f5;
        }
        PointF pointF = this.m(f7);
        float f10 = com.corrodinggames.rts.gameFramework.f.a(f2, f3, pointF.a, pointF.b);
        if (f6 >= 0.0f && f6 * f6 < f10) {
            float f11 = com.corrodinggames.rts.gameFramework.f.d(f2, f3, pointF.a, pointF.b);
            pointF.a = f2 + com.corrodinggames.rts.gameFramework.f.k(f11) * f6;
            pointF.b = f3 + com.corrodinggames.rts.gameFramework.f.j(f11) * f6;
        }
        dD.a(pointF);
        return dD;
    }

    PointF m(float f2) {
        dE.a(this.eo + this.cc * f2, this.ep + this.cd * f2);
        return dE;
    }

    public abstract boolean l();

    public boolean o() {
        return false;
    }

    public boolean p() {
        return false;
    }

    public boolean cO() {
        return false;
    }

    public void f(n n2) {
        if (this.p()) {
            this.b(n.i);
            return;
        }
        this.b(n2);
    }

    public void B(am am2) {
        if (am2 instanceof h) {
            am2 = null;
        }
        this.bu = am2;
    }

    public void cP() {
    }

    public float g() {
        return 0.0f;
    }

    public int cQ() {
        return Integer.MAX_VALUE;
    }

    public com.corrodinggames.rts.game.units.custom.h cR() {
        return null;
    }

    public boolean g(am am2, boolean bl2) {
        return false;
    }

    public boolean h(am am2, boolean bl2) {
        return this.g(am2, bl2);
    }

    public int cS() {
        return 500;
    }

    public boolean c(y y2) {
        int n2;
        int n3 = this.cQ();
        return n3 < Integer.MAX_VALUE && (n2 = this.d(y2)) >= n3;
    }

    public int d(y y2) {
        int n2 = 0;
        n n3 = y2.bX;
        am[] amArray = bE.a();
        int n4 = bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            y y3;
            au au2;
            am am2 = amArray[i2];
            if (am2.bX != n3 || !(am2 instanceof y) || (au2 = (y3 = (y)am2).ar()) == null || au2.d() != av.g || au2.h != this || am2 == y2) continue;
            ++n2;
        }
        return n2;
    }

    public int e(y y2) {
        int n2 = 0;
        n n3 = y2.bX;
        am[] amArray = bE.a();
        int n4 = bE.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            y y3;
            au au2;
            am am2 = amArray[i2];
            if (am2.bX != n3 || !(am2 instanceof y) || (au2 = (y3 = (y)am2).ar()) == null || au2.d() != av.d || au2.h != this || am2 == y2) continue;
            ++n2;
        }
        return n2;
    }

    public int bl() {
        return 1;
    }

    public boolean u() {
        return false;
    }

    public boolean cT() {
        return this.u() || this.cm < 1.0f || this.bX == n.h;
    }

    public boolean cU() {
        return !this.u();
    }

    public boolean t() {
        return false;
    }

    public boolean cV() {
        return this.t();
    }

    public boolean cW() {
        return false;
    }

    public boolean d(am am2) {
        return true;
    }

    public void g(n n2) {
        if (this.dF == null || this.dF.length != n.c) {
            this.dF = new an[n.c];
            for (int i2 = 0; i2 < this.dF.length; ++i2) {
                this.dF[i2] = new an();
            }
        }
        an an2 = this.dF[n2.k];
        if (this.bV) {
            boolean bl2;
            if (an2.a && (bl2 = this.d(n2))) {
                an2.a = false;
            }
        } else {
            boolean bl3 = this.d(n2);
            if (bl3) {
                an2.a = true;
                an2.b = this.V();
            }
        }
    }

    public void cX() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bs != null && this.bX != l2.bs && l2.bs.k >= 0 && l2.bs.k < n.c) {
            boolean bl2;
            an an2 = this.dF[l2.bs.k];
            if (an2.c != null && an2.c.c) {
                an2.c = null;
            }
            if (an2.c == null && an2.a && !(bl2 = this.d(l2.bs))) {
                com.corrodinggames.rts.gameFramework.d.a a2;
                an2.c = a2 = new com.corrodinggames.rts.gameFramework.d.a();
                a2.d = this.r();
                a2.g = this.eo;
                a2.h = this.ep;
                a2.n = false;
                a2.e = this.bX;
                a2.f = an2.b;
                a2.j = l2.bs;
                a2.u = this.c_();
                a2.v = this;
            }
        }
    }

    public PointF cY() {
        dG.a(0.0f, 0.0f);
        return dG;
    }

    public float cZ() {
        return com.corrodinggames.rts.gameFramework.l.B().bL.p;
    }

    public float da() {
        return com.corrodinggames.rts.gameFramework.l.B().bL.q;
    }

    public float db() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return (float)(l2.bL.p + 2) + l2.bS.r(this);
    }

    public Point a(com.corrodinggames.rts.game.b.b b2, Point point) {
        point.a = (int)((this.eo - this.cZ() + 1.0f) * b2.r);
        point.b = (int)((this.ep - this.da() + 1.0f) * b2.s);
        return point;
    }

    public RectF a(com.corrodinggames.rts.game.b.b b2, RectF rectF) {
        int n2 = (int)((this.eo - this.cZ() + 1.0f) * b2.r);
        int n3 = (int)((this.ep - this.da() + 1.0f) * b2.s);
        b2.a(n2, n3);
        float f2 = b2.T;
        float f3 = b2.U;
        Rect rect = this.cd();
        rectF.a(f2 + (float)(rect.a * b2.n), f3 + (float)(rect.b * b2.o), f2 + (float)((rect.c + 1) * b2.n), f3 + (float)((rect.d + 1) * b2.o));
        return rectF;
    }

    public void dc() {
    }

    public boolean dd() {
        return false;
    }

    public boolean q() {
        return false;
    }

    public com.corrodinggames.rts.game.units.custom.h de() {
        return null;
    }

    public f df() {
        return this.dH;
    }

    public double a(a a2) {
        return this.dH.a(a2);
    }

    public com.corrodinggames.rts.game.units.custom.c.c dg() {
        return this.dI;
    }

    public com.corrodinggames.rts.game.units.custom.h dh() {
        return null;
    }

    public float bd() {
        return 0.0f;
    }

    public void di() {
    }

    public void dj() {
    }

    public boolean dk() {
        return false;
    }

    public boolean dl() {
        return this.bO();
    }

    public boolean dm() {
        return this.bO();
    }

    public final com.corrodinggames.rts.game.units.custom.b.n dn() {
        return this.cP;
    }

    public String toString() {
        return "unit(id=" + this.eh + ",type=" + this.r().i() + ")";
    }

    public void r(float f2) {
        if (f2 >= 1.0f) {
            boolean bl2;
            boolean bl3 = bl2 = this.cm >= 1.0f;
            if (!bl2) {
                n.b(this);
                this.cm = 1.0f;
                n.c(this);
            }
        } else {
            boolean bl4;
            boolean bl5 = bl4 = this.cm >= 1.0f;
            if (bl4) {
                n.b(this);
                this.cm = f2;
                n.c(this);
            } else {
                this.cm = f2;
            }
        }
    }

    public final void a(af af2) {
        this.a(af2, null, null, null);
    }

    public final void a(af af2, am am2) {
        this.a(af2, am2, null, null);
    }

    public void a(af af2, am am2, com.corrodinggames.rts.game.units.custom.h h2, VariableScope variableScope) {
    }

    public void h(float f2) {
        this.cg = f2;
    }

    public int a(g g2) {
        return 0;
    }

    public m e(boolean bl2) {
        return null;
    }

    public boolean a(int n2, int n3) {
        return false;
    }

    public void c(boolean bl2) {
    }

    public float do() {
        return this.cj;
    }

    public boolean dp() {
        return true;
    }

    public void bC() {
    }

    public final com.corrodinggames.rts.game.units.custom.d.b dq() {
        return this.dJ;
    }

    public final am dr() {
        am am2 = this.cO;
        if (am2 == null && this.cN != null) {
            am2 = this.cN;
        }
        return am2;
    }

    public void f(float f2, float f3) {
        this.eo = f2;
        this.ep = f3;
        this.c(true);
    }

    static {
        bI.a(true);
        bI.a(255, 195, 195, 195);
        bJ = new ag();
        bJ.a(true);
        bK = new LightingColorFilter(Color.a(255, 255, 255), Color.a(100, 100, 100));
        bJ.a(255, 255, 255, 255);
        bJ.a(bK);
        cW = new Paint();
        cX = new ag();
        cY = new ag();
        cZ = new ag();
        da = new ag();
        db = new ag();
        dc = new ag();
        dd = new Paint();
        de = new Paint();
        df = new Paint();
        dg = new ag();
        dh = new ag();
        di = new ag();
        dj = new ag();
        dk = new Paint();
        cW.a(Paint$Style.b);
        cW.a(2.0f);
        am.a(cW);
        cX.a(180, 0, 255, 0);
        cX.a(Paint$Style.b);
        cX.a(2.0f);
        am.a(cX, true);
        cY.a(180, 0, 255, 0);
        cY.a(Paint$Style.b);
        cY.a(2.0f);
        am.a(cY);
        cZ.a(130, 0, 255, 0);
        cZ.a(Paint$Style.b);
        cZ.a(2.0f);
        am.a(cZ);
        dd.a(70, 0, 255, 0);
        dd.a(Paint$Style.b);
        dd.a(1.0f);
        am.a(dd);
        da.a(180, 255, 0, 0);
        da.a(Paint$Style.b);
        da.a(2.0f);
        am.a(da);
        de.a(70, 255, 0, 0);
        de.a(Paint$Style.b);
        de.a(1.0f);
        am.a(de);
        dc.a(180, 255, 255, 0);
        dc.a(Paint$Style.b);
        dc.a(2.0f);
        am.a(dc);
        df.a(70, 255, 255, 0);
        df.a(Paint$Style.b);
        df.a(1.0f);
        am.a(df);
        db.a(180, 255, 255, 255);
        db.a(Paint$Style.b);
        db.a(2.0f);
        am.a(db);
        dg.a(90, 235, 235, 235);
        dg.a(Paint$Style.b);
        dg.a(1.0f);
        am.a(dg);
        dh.a(100, 235, 235, 235);
        dh.a(Paint$Style.b);
        dh.a(2.0f);
        am.a(dh);
        di.a(90, 235, 0, 0);
        di.a(Paint$Style.b);
        di.a(1.0f);
        am.a(di);
        dj.a(Paint$Style.b);
        dk.a(Paint$Style.b);
        dr = new RectF();
        ds = new Paint();
        dt = new Paint();
        du = new RectF();
        dv = new Rect();
        dw = new Rect();
        dx = new ArrayList();
        dy = new ArrayList();
        dA = new RectF();
        dB = new RectF();
        dC = new Rect();
        dD = new PointF();
        dE = new PointF();
        dG = new PointF();
    }
}
