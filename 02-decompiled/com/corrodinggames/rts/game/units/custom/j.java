/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.ag;
import com.corrodinggames.rts.game.units.ai;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ap;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.b;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.a.e;
import com.corrodinggames.rts.game.units.custom.a.g;
import com.corrodinggames.rts.game.units.custom.ae;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.b.h;
import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bn;
import com.corrodinggames.rts.game.units.custom.e.f;
import com.corrodinggames.rts.game.units.custom.i;
import com.corrodinggames.rts.game.units.custom.k;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.m;
import com.corrodinggames.rts.game.units.custom.r;
import com.corrodinggames.rts.game.units.d.q;
import com.corrodinggames.rts.game.units.w;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.ad;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public strictfp class j
extends w
implements ak,
com.corrodinggames.rts.game.units.d,
com.corrodinggames.rts.game.units.d.l {
    public int a;
    public final com.corrodinggames.rts.game.units.custom.e b = new com.corrodinggames.rts.game.units.custom.e(this);
    float c = 1.0f;
    float d;
    float e;
    public float f;
    boolean g;
    public boolean h = true;
    public boolean i = true;
    float j = 1.0f;
    boolean k = false;
    boolean l;
    float m;
    boolean n;
    float o;
    public boolean p = true;
    float q;
    boolean r;
    float s;
    float t;
    public float u;
    public boolean v;
    float w;
    public l x;
    public com.corrodinggames.rts.game.units.custom.as y;
    public l z;
    public at[] A;
    public final com.corrodinggames.rts.gameFramework.utility.m B = new com.corrodinggames.rts.gameFramework.utility.m();
    public com.corrodinggames.rts.gameFramework.utility.m C = null;
    public float D;
    PointF[] E = null;
    PointF[] F = null;
    com.corrodinggames.rts.game.f[] G;
    static final PointF H = new PointF();
    static final PointF I = new PointF();
    Paint J;
    protected static final PointF K = new PointF();
    protected static final com.corrodinggames.rts.gameFramework.utility.ai dK = new com.corrodinggames.rts.gameFramework.utility.ai();
    final com.corrodinggames.rts.game.units.d.k dL = new com.corrodinggames.rts.game.units.d.k(this);
    public static PointF dM;
    public static am dN;
    public static int dO;
    public float dP;
    public float dQ;
    public float dR;
    public float dS;
    public com.corrodinggames.rts.game.units.custom.b.i[] dT = null;
    static ArrayList dU;
    public static com.corrodinggames.rts.gameFramework.utility.m dV;
    public static com.corrodinggames.rts.gameFramework.utility.m dW;
    public static com.corrodinggames.rts.gameFramework.utility.m dX;
    static boolean dY;
    static final HashMap dZ;
    static int ea;
    static String eb;
    static final PointF ec;
    com.corrodinggames.rts.game.units.custom.h ed;
    protected static final com.corrodinggames.rts.gameFramework.utility.ai ee;
    protected static final PointF ef;
    com.corrodinggames.rts.gameFramework.utility.m eg = new com.corrodinggames.rts.gameFramework.utility.m();

    public void K() {
        this.E = new PointF[6];
        this.F = new PointF[this.E.length];
        for (int i2 = 0; i2 < this.E.length; ++i2) {
            this.E[i2] = new PointF();
            this.F[i2] = new PointF();
        }
    }

    @Override
    public PointF[] b() {
        if (this.E == null) {
            this.K();
        }
        return this.E;
    }

    @Override
    public PointF[] e_() {
        if (this.E == null) {
            this.K();
        }
        return this.F;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(11);
        as2.a(this.e);
        as2.a(this.m);
        as2.a(this.n);
        as2.a(this.B.size());
        for (am am2 : this.B) {
            as2.a(am2);
        }
        as2.a(this.r);
        as2.a(this.o);
        as2.a(this.f);
        as2.a(this.s);
        as2.a(this.v);
        int n2 = 0;
        if (this.dT != null) {
            n2 = (byte)this.dT.length;
        }
        as2.c(n2);
        if (this.dT != null) {
            for (int i2 = 0; i2 < n2; ++i2) {
                com.corrodinggames.rts.game.units.custom.b.i i3 = this.dT[i2];
                as2.a(i3.b);
                as2.a(i3.c);
                as2.a(i3.d);
                as2.a(i3.i);
                as2.a(i3.k);
                as2.a(i3.j);
                as2.a(i3.m);
                as2.a(i3.n);
            }
        }
        this.dL.a(as2);
        as2.a(this.z);
        as2.a(this.i);
        as2.a(this.h);
        boolean bl2 = this.ed != this.x.O;
        as2.a(bl2);
        if (bl2) {
            com.corrodinggames.rts.game.units.custom.g.a(this.ed, as2);
        }
        com.corrodinggames.rts.game.units.custom.as.a(this.y, this, as2);
        as2.a(this.q);
        super.a(as2);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.k k2) {
        if (k2.b() >= 32) {
            boolean bl2;
            as as2;
            int n2;
            int n3 = k2.f();
            this.e = k2.g();
            this.m = k2.g();
            this.n = k2.e();
            this.B.clear();
            int n4 = k2.f();
            for (n2 = 0; n2 < n4; ++n2) {
                am am2 = k2.o();
                if (am2 == null) continue;
                this.B.add(am2);
            }
            if (n3 >= 1) {
                this.r = k2.e();
            }
            if (n3 >= 2) {
                this.o = k2.g();
            }
            if (n3 >= 3) {
                this.f = k2.g();
                this.s = k2.g();
            }
            if (n3 >= 4) {
                this.v = k2.e();
            }
            if (n3 >= 5 && (n2 = (int)k2.d()) != 0) {
                this.du();
                if (this.dT == null) {
                    com.corrodinggames.rts.gameFramework.l.e("readIn: legs==null but leg data found in save, this might be due to missing or changed mods");
                }
                for (int i2 = 0; i2 < n2; ++i2) {
                    com.corrodinggames.rts.game.units.custom.b.i i3;
                    if (this.dT == null) {
                        i3 = new com.corrodinggames.rts.game.units.custom.b.i();
                    } else if (i2 >= this.dT.length) {
                        com.corrodinggames.rts.gameFramework.l.e("readIn: legs " + i2 + ">=" + this.dT.length);
                        i3 = new com.corrodinggames.rts.game.units.custom.b.i();
                    } else {
                        i3 = this.dT[i2];
                    }
                    i3.b = k2.g();
                    i3.c = k2.g();
                    i3.d = k2.g();
                    i3.i = k2.g();
                    i3.k = k2.e();
                    i3.o = true;
                    i3.j = k2.e();
                    i3.m = k2.e();
                    i3.n = k2.e();
                }
            }
            if (n3 >= 6) {
                this.dL.a(k2);
            }
            if (n3 >= 7 && (as2 = k2.q()) != null) {
                if (as2 instanceof l) {
                    this.z = (l)as2;
                } else {
                    com.corrodinggames.rts.gameFramework.l.b("Got non CustomUnitMetadata object of:" + as2.i() + " loading real_meta");
                    this.z = com.corrodinggames.rts.game.units.custom.l.b;
                }
            }
            if (n3 >= 8) {
                this.i = k2.e();
                this.h = k2.e();
            }
            if (n3 >= 9 && (bl2 = k2.e())) {
                this.a(com.corrodinggames.rts.game.units.custom.g.a(k2), true);
            }
            if (n3 >= 10) {
                com.corrodinggames.rts.game.units.custom.as.a(this, k2, n3);
            }
            if (n3 >= 11) {
                this.q = k2.g();
            }
        }
        super.a(k2);
        if (this.dT != null) {
            this.dP = this.eo;
            this.dQ = this.ep;
            this.dR = this.eq;
            this.dS = this.cg;
        }
    }

    @Override
    public boolean cr() {
        return this.x.eM > 0;
    }

    @Override
    public int bB() {
        return this.B.size();
    }

    @Override
    public boolean bA() {
        return this.n;
    }

    @Override
    public boolean d(am am2, boolean bl2) {
        boolean bl3;
        if (this.x.eM == 0) {
            return false;
        }
        if (this.n) {
            return false;
        }
        if (this.cm < 1.0f) {
            return false;
        }
        if (!this.G(am2)) {
            return false;
        }
        if (am2 == this) {
            return false;
        }
        if (!(this.bX == am2.bX || bl2 || this.x.cB && this.bX == com.corrodinggames.rts.game.n.i)) {
            return false;
        }
        if (this.x.eP != null && !this.x.eP.a() && !com.corrodinggames.rts.game.units.custom.g.a(this.x.eP, am2.de())) {
            return false;
        }
        if (this.x.eQ.size() > 0) {
            bl3 = false;
            for (ao ao2 : this.x.eQ) {
                if (ao2 != am2.h()) continue;
                bl3 = true;
                break;
            }
            if (!bl3) {
                return false;
            }
        }
        bl3 = this.x.eR;
        return com.corrodinggames.rts.gameFramework.utility.y.a(am2, bl3, this.x.eS);
    }

    @Override
    public boolean e(am am2, boolean bl2) {
        if (!this.d(am2, bl2)) {
            return false;
        }
        this.C(am2);
        return true;
    }

    public void C(am am2) {
        am2.cN = this;
        this.B.add(am2);
        if (this.x.cC && this.bX == com.corrodinggames.rts.game.n.i) {
            this.e(am2.bX);
        }
        this.a(com.corrodinggames.rts.game.units.custom.af.l, am2);
        am2.a(com.corrodinggames.rts.game.units.custom.af.o, this);
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bS.l(am2);
    }

    public void D(am am2) {
        this.a(com.corrodinggames.rts.game.units.custom.af.m, am2);
        am2.a(com.corrodinggames.rts.game.units.custom.af.p, this);
        if (this.x.cD && this.B.size() == 0) {
            this.e(com.corrodinggames.rts.game.n.i);
        }
    }

    @Override
    public void e(am am2) {
        if (am2.cN == this) {
            this.B.remove(am2);
            am2.cN = null;
            this.D(am2);
        } else {
            com.corrodinggames.rts.gameFramework.l.g("Unit is not being transported");
        }
    }

    public void L() {
        if (this.x.eM == 0) {
            return;
        }
        this.n = true;
        this.m = 30.0f;
    }

    public void M() {
        if (this.x.eM == 0) {
            return;
        }
        this.n = false;
    }

    public boolean g(boolean bl2) {
        if (this.B.size() == 0) {
            return false;
        }
        boolean bl3 = this.B.size() % 2 == 0;
        am am2 = (am)this.B.remove(this.B.size() - 1);
        return this.b(am2, bl2, bl3);
    }

    public boolean a(am am2, boolean bl2, boolean bl3) {
        this.B.remove(am2);
        return this.b(am2, bl2, bl3);
    }

    private boolean b(am am2, boolean bl2, boolean bl3) {
        boolean bl4;
        float f2 = 180.0f;
        if (this.x.dk != null) {
            f2 = this.x.dk.floatValue();
        }
        float f3 = 70.0f;
        if (this.x.dn != null) {
            f3 = this.x.dn.floatValue();
        }
        float f4 = 7.0f;
        float f5 = this.x.di;
        float f6 = this.x.dj;
        float f7 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg + f2) * f6 - com.corrodinggames.rts.gameFramework.f.j(this.cg + f2) * f5;
        float f8 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg + f2) * f6 + com.corrodinggames.rts.gameFramework.f.k(this.cg + f2) * f5;
        f7 += com.corrodinggames.rts.gameFramework.f.k(this.cg + 90.0f) * (bl3 ? -f4 : f4);
        f8 += com.corrodinggames.rts.gameFramework.f.j(this.cg + 90.0f) * (bl3 ? -f4 : f4);
        if (!bl2 && !this.bI()) {
            if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f7, f8)) {
                f7 += 10.0f;
            }
            if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f7, f8)) {
                f7 -= 20.0f;
            }
            if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f7, f8)) {
                f7 -= 10.0f;
                f8 += 10.0f;
            }
            if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f7, f8)) {
                f8 -= 20.0f;
            }
        }
        if (!(bl2 || com.corrodinggames.rts.gameFramework.utility.y.a(am2, f7, f8) || this.bI())) {
            bl4 = false;
        } else {
            bl4 = true;
            boolean bl5 = false;
            com.corrodinggames.rts.game.units.custom.b.n n2 = null;
            if (am2 instanceof y) {
                y y2 = (y)am2;
                if (y2.cO == this) {
                    n2 = y2.dn();
                    if (n2 == null) {
                        com.corrodinggames.rts.gameFramework.l.e("Unload, attachment data is null");
                    }
                    if (n2 != null) {
                        bl5 = n2.E;
                    }
                }
            }
            com.corrodinggames.rts.gameFramework.utility.y.a(am2, this);
            float f9 = this.cg + f2;
            if (!bl5) {
                am2.eo = f7;
                am2.ep = f8;
                am2.cg = f9;
                am2.cd = 0.0f;
                am2.cc = 0.0f;
                am2.bZ = 0.0f;
                am2.ca = 0.0f;
                am2.bZ += 0.1f;
            }
            am2.bR = this;
            am2.bS = 45.0f;
            if (bl5) {
                am2.bS = 85.0f;
            }
            if (am2 instanceof y) {
                y y3 = (y)am2;
                if (!bl5) {
                    y3.j(am2.cg);
                }
                if (!this.x.eW.read(this)) {
                    y3.az();
                    if (f3 != 0.0f) {
                        y3.d(am2.eo + com.corrodinggames.rts.gameFramework.f.k(f9 + (bl3 ? -f4 : f4)) * f3, am2.ep + com.corrodinggames.rts.gameFramework.f.j(f9 + (bl3 ? -f4 : f4)) * f3);
                    }
                    y3.ac = 0;
                } else {
                    y3.aH();
                }
            }
            if (!bl5) {
                if (!this.x.dm) {
                    am2.eq = this.eq;
                }
                am2.eq += this.x.dl;
            }
            if (am2 instanceof j) {
                ((j)am2).dF();
            }
        }
        if (!bl4) {
            this.B.add(am2);
        } else {
            this.D(am2);
        }
        return bl4;
    }

    @Override
    public int bY() {
        if (this.x.eM == 0 || !this.x.x) {
            return -1;
        }
        return this.dI();
    }

    @Override
    public int bZ() {
        if (this.x.eM == 0 || !this.x.x) {
            return -1;
        }
        return this.x.eM;
    }

    public void ds() {
        if (this.B.a > 0) {
            boolean bl2 = this.x.eV.read(this);
            this.h(bl2);
        }
    }

    public void h(boolean bl2) {
        for (am am2 : this.B) {
            am2.cN = null;
            am2.eo = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -9.0f;
            am2.ep = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -9.0f;
            if (!bl2) continue;
            am2.cj();
        }
        this.B.clear();
    }

    @Override
    public void bu() {
        if (!this.bV) {
            this.a(com.corrodinggames.rts.game.units.custom.af.c);
        }
        Object[] objectArray = this.x.h.a();
        for (int i2 = this.x.h.a - 1; i2 >= 0; --i2) {
            com.corrodinggames.rts.game.units.custom.b.a a2 = (com.corrodinggames.rts.game.units.custom.b.a)objectArray[i2];
            a2.b(this);
        }
        super.bu();
    }

    @Override
    public void a() {
        this.ds();
        Object[] objectArray = this.x.h.a();
        for (int i2 = this.x.h.a - 1; i2 >= 0; --i2) {
            com.corrodinggames.rts.game.units.custom.b.a a2 = (com.corrodinggames.rts.game.units.custom.b.a)objectArray[i2];
            a2.c(this);
        }
        com.corrodinggames.rts.game.n.a(this);
        this.dL.a(true);
        super.a();
    }

    public l dt() {
        return this.x;
    }

    @Override
    public boolean I() {
        if (this.x.aH) {
            return false;
        }
        if (this.g) {
            return false;
        }
        return this.cO == null;
    }

    @Override
    public boolean aR() {
        if (this.x.dP) {
            return false;
        }
        com.corrodinggames.rts.game.units.custom.b.n n2 = this.dn();
        return n2 == null || n2.O;
    }

    @Override
    public boolean aS() {
        if (this.x.aH) {
            return false;
        }
        com.corrodinggames.rts.game.units.custom.b.n n2 = this.dn();
        return n2 == null || n2.H;
    }

    @Override
    public ao h() {
        return this.x.fh;
    }

    @Override
    public boolean i() {
        if (this.x.fh == com.corrodinggames.rts.game.units.ao.d) {
            return this.eq >= 4.0f;
        }
        return false;
    }

    @Override
    public boolean Q() {
        return this.eq <= -1.0f;
    }

    @Override
    public boolean cv() {
        return this.h() == com.corrodinggames.rts.game.units.ao.e;
    }

    @Override
    public boolean ct() {
        return this.h() == com.corrodinggames.rts.game.units.ao.d;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e v() {
        if (this.bX.k == -1 || this.x.as == null) {
            return null;
        }
        return this.x.as[this.bX.R()];
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d() {
        if (this.bV && this.x.an != null) {
            return this.x.an;
        }
        return this.x.ar[this.bX.R()];
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e k() {
        return this.x.ap;
    }

    @Override
    public boolean F() {
        return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && (!this.bV || this.eq >= 1.0f) && this.eq >= -1.0f;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d(int n2) {
        bn bn2 = this.x.fQ[n2];
        if (bn2.C != null && bn2.C.read(this)) {
            return null;
        }
        if (bn2.aE != null) {
            return bn2.aE[this.bX.R()];
        }
        if (bn2.aD != null) {
            return bn2.aD;
        }
        if (this.x.at != null) {
            return this.x.at[this.bX.R()];
        }
        return this.x.ao;
    }

    @Override
    public float h(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.aG;
    }

    @Override
    public float i(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.aH;
    }

    @Override
    public boolean e() {
        Object object;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.bI()) {
            l2.bU.a(this);
        }
        if (this.cm < 1.0f) {
            if (this.x.bx != null && this.x.bx.b()) {
                this.x.bx.a(this.eo, this.ep, this.eq, this.cg, null);
                return false;
            }
            if (this.x.by != null && this.x.by.b()) {
                this.x.by.a(this.eo, this.ep, this.eq, this.cg, null);
                return false;
            }
            this.a(com.corrodinggames.rts.game.units.ab.a);
            return false;
        }
        if (!this.x.fi) {
            this.S(0);
        }
        if (this.x.by != null && this.x.by.b()) {
            this.x.by.a(this.eo, this.ep, this.eq, this.cg, null);
        }
        if (this.x.bB != -1) {
            this.a(null, this.eo, this.ep, this.x.bB, null, 0);
        }
        if (this.x.bC != null) {
            n n2 = this.x.bD && this.bt != null && this.bt.bX != null ? this.bt.bX : this.bX;
            if (!n2.E) {
                this.x.bC.a(this.eo, this.ep, this.eq, this.cg, n2, this.cG, this);
            }
        }
        this.bT = false;
        if (!this.x.fi) {
            this.ds();
        }
        if (this.x.bn) {
            boolean bl2 = false;
            if (this.x.br && l2.O() && l2.bX.ay.i) {
                bl2 = true;
            }
            if (!bl2) {
                object = com.corrodinggames.rts.game.units.d.q.a((am)this, this.eo, this.ep, this.eo, this.ep);
                ((com.corrodinggames.rts.game.f)object).aH = false;
                ((com.corrodinggames.rts.game.f)object).Z = this.x.bo;
                ((com.corrodinggames.rts.game.f)object).Y = this.x.bp;
            }
        }
        if (this.x.bm != 0) {
            ai ai2 = new ai(false);
            ai2.eo = this.eo;
            ai2.ep = this.ep;
            ai2.b(com.corrodinggames.rts.game.n.h);
            com.corrodinggames.rts.game.n.c(ai2);
        }
        if (this.x.bz != null) {
            this.x.bz.a(this.eo, this.ep, 1.0f);
        }
        if (this.x.eD) {
            if (this.x.bz == null) {
                l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.A, 0.8f, this.eo, this.ep);
            }
            if (this.x.bu) {
                if (!this.i() && !this.x.eJ) {
                    com.corrodinggames.rts.game.l.a(this, 1);
                }
                if (this.x.bt != null) {
                    this.a(this.x.bt, true);
                } else if (this.x.bj) {
                    this.a(com.corrodinggames.rts.game.units.ab.d);
                }
            }
            if (this.x.eE) {
                for (int i2 = 0; i2 < this.bp(); ++i2) {
                    l2.bR.d(this.eo, this.ep, this.eq);
                }
            }
        } else {
            if (this.dT != null) {
                this.dv();
                for (int i3 = 0; i3 < this.dT.length; ++i3) {
                    object = this.dT[i3];
                    ba ba2 = this.x.ax[i3];
                    float f2 = this.eo + ((com.corrodinggames.rts.game.units.custom.b.i)object).b;
                    float f3 = this.ep + ((com.corrodinggames.rts.game.units.custom.b.i)object).c;
                    if (!ba2.J || ba2.p || ba2.q != null && ba2.q.read(this)) continue;
                    if (!com.corrodinggames.rts.gameFramework.utility.y.d(f2, f3) && !this.x.eJ) {
                        com.corrodinggames.rts.game.l.a(f2, f3);
                    }
                    l2.bR.b(f2, f3, 0.0f);
                }
            }
            if (!this.i()) {
                if (this.x.bu) {
                    if (this.x.bt != null) {
                        this.a(this.x.bt, true);
                    } else {
                        this.a(com.corrodinggames.rts.game.units.ab.b);
                    }
                }
            } else {
                if (this.x.bu) {
                    if (this.x.bt != null) {
                        this.a(this.x.bt, false);
                    } else {
                        l2.bR.b(this.eo, this.ep, this.eq);
                    }
                }
                for (int i4 = 0; i4 < this.bp(); ++i4) {
                    l2.bR.e(this.eo, this.ep, this.eq);
                }
            }
        }
        if (this.x.fi) {
            return true;
        }
        if (this.x.an != null) {
            this.M = this.x.an;
            this.a = 0;
            this.ew = true;
            return true;
        }
        return false;
    }

    @Override
    public void bq() {
        if (!this.x.eJ) {
            super.bq();
        }
    }

    @Override
    public void U() {
        super.U();
        for (int i2 = 0; i2 < this.x.fQ.length; ++i2) {
            ap ap2 = this.cL[i2];
            com.corrodinggames.rts.gameFramework.l.e("Dir was:" + ap2.a + " for name:" + this.x.fQ[i2].a);
            com.corrodinggames.rts.gameFramework.l.e("lockDelay:" + ap2.d + " shootCooldown:" + ap2.e);
            com.corrodinggames.rts.gameFramework.l.e("updateAndShouldResetTurret:" + this.b(i2, 0.0f));
            float f2 = this.C(i2);
            com.corrodinggames.rts.gameFramework.l.e("idleDir:" + f2);
            float f3 = com.corrodinggames.rts.gameFramework.f.c(ap2.a, f2, 360.0f);
            com.corrodinggames.rts.gameFramework.l.e("diffDir:" + f3);
        }
    }

    public void a(l l2, boolean bl2, boolean bl3) {
        this.a(l2, bl2, bl3, null);
    }

    public void a(l l2, boolean bl2, boolean bl3, at[] atArray) {
        Object object;
        Object[] objectArray;
        com.corrodinggames.rts.game.units.custom.as as2;
        l l3;
        com.corrodinggames.rts.gameFramework.l l4;
        block52: {
            int n2;
            block51: {
                int n3;
                bn bn2;
                l4 = com.corrodinggames.rts.gameFramework.l.B();
                l3 = this.x;
                as2 = this.y;
                this.x = l2;
                this.dz = this.x;
                this.y = l2.cL;
                if (bl3) {
                    com.corrodinggames.rts.game.units.custom.as.a(this, as2, l3);
                } else if (atArray != null) {
                    com.corrodinggames.rts.game.units.custom.as.a(this, as2, atArray);
                }
                this.bS();
                if (!bl3) {
                    this.j(true);
                }
                if (bl2) break block51;
                if (l2.fQ.length <= 1) break block52;
                n2 = 1;
                if (l2.fQ.length != l3.fQ.length) {
                    n2 = 0;
                } else {
                    for (int i2 = 0; i2 < l2.fQ.length; ++i2) {
                        objectArray = l2.fQ[i2];
                        bn2 = l3.fQ[i2];
                        if (objectArray.a.equalsIgnoreCase(bn2.a)) continue;
                        n2 = 0;
                        break;
                    }
                }
                if (n2 != 0) break block52;
                ap[] apArray = new ap[l2.fQ.length];
                for (n3 = 0; n3 < l2.fQ.length; ++n3) {
                    bn2 = l2.fQ[n3];
                    object = l3.e(bn2.a);
                    if (object == null) continue;
                    apArray[bn2.e] = this.cL[((bn)object).e];
                    this.cL[((bn)object).e] = null;
                }
                for (n3 = 0; n3 < l2.fQ.length; ++n3) {
                    if (apArray[n3] != null) continue;
                    for (int i3 = 0; i3 < l2.fQ.length; ++i3) {
                        if (this.cL[i3] == null) continue;
                        apArray[n3] = this.cL[i3];
                        this.cL[i3] = null;
                        break;
                    }
                    if (apArray[n3] == null) {
                        apArray[n3] = new ap();
                    }
                    apArray[n3].a(this.cg);
                }
                this.cL = apArray;
                break block52;
            }
            for (n2 = 0; n2 < l2.fQ.length; ++n2) {
                float f2 = this.cg + this.B(n2);
                this.cL[n2].a(f2);
            }
        }
        if (this.x.ae) {
            this.V(this.x.af);
            this.W(this.x.ag);
        } else {
            this.T(this.x.af);
            this.U(this.x.ag);
        }
        this.ew = false;
        this.cj = this.x.cW;
        this.ck = this.x.dd;
        this.a = this.bV ? 0 : this.x.Y;
        this.g = false;
        if (bl2) {
            this.eq += this.x.dS;
        }
        float f3 = this.cv;
        this.cv = this.y.c;
        if (bl2) {
            this.o(this.cv);
        } else if (f3 == 0.0f) {
            this.o(this.cv);
        } else {
            this.o(this.cu / f3 * this.cv);
        }
        float f4 = this.cA;
        this.cA = this.y.g;
        if (this.x.cM) {
            if (this.cx > this.cA) {
                this.cx = this.cA;
            }
        } else {
            this.cx = bl2 ? this.cA : (f4 == 0.0f ? this.cA : this.cx / f4 * this.cA);
        }
        if (this.x.cO) {
            if (this.cB > this.y.d) {
                this.cB = this.y.d;
            }
        } else {
            this.cB = bl2 ? this.y.d * this.x.cS : (as2.d == 0.0f ? this.y.d : this.cB / as2.d * this.y.d);
        }
        if (this.bX == null) {
            this.M = this.x.ad;
        } else {
            this.S();
        }
        if (this.x.aH && bl2) {
            this.cg = -90.0f;
        }
        this.f_();
        if (!(bl2 || this.bx == null && this.by == null || com.corrodinggames.rts.game.units.custom.d.b.b(this.x.ch, l3.ch) && com.corrodinggames.rts.game.units.custom.d.b.b(this.x.cj, l3.cj))) {
            this.bx = null;
            this.by = null;
        }
        if (!bl2) {
            boolean bl4;
            boolean bl5 = bl4 = this.x.j() != l3.j();
            if (this.x.j()) {
                this.cd = 0.0f;
                this.cc = 0.0f;
                if (l3.j() && !this.x.cX.equals(l3.cX)) {
                    bl4 = true;
                }
            }
            if (bl4) {
                l4.bU.a(this);
            }
        }
        this.j = 1.0f;
        if (this.x.cI != -2) {
            // empty if block
        }
        if (!this.bV) {
            this.dF();
        }
        if (this.x.eF) {
            // empty if block
        }
        this.du();
        this.dg().a(this.x);
        if (!bl2) {
            int n4 = this.bl();
            for (int i4 = 0; i4 < n4; ++i4) {
                object = this.cL[i4];
                bn bn3 = this.x.fQ[i4];
                if (bn3 == null) continue;
                if (((ap)object).e > bn3.m) {
                    ((ap)object).e = bn3.m;
                }
                if (!(((ap)object).f > bn3.n)) continue;
                ((ap)object).f = bn3.n;
            }
        }
        if (!bl2) {
            if (!this.x.dc) {
                this.dL.b = null;
            }
            if (this.x.fg != l3.fg) {
                this.aH();
            }
        }
        if (this.x.cD && this.B.size() == 0) {
            this.b(com.corrodinggames.rts.game.n.i);
        }
        if (this.cG && !l4.bS.i(this)) {
            l4.bS.l(this);
        }
        if (!bl2) {
            int n5;
            objectArray = this.x.h.a();
            for (n5 = this.x.h.a - 1; n5 >= 0; --n5) {
                object = (com.corrodinggames.rts.game.units.custom.b.a)objectArray[n5];
                ((com.corrodinggames.rts.game.units.custom.b.a)object).a(this, l3);
            }
            if (this.cm >= 1.0f) {
                if (this.y.n > as2.n) {
                    this.c(false);
                }
            } else {
                int n6;
                n5 = l3.dh != -1 ? l3.dh : as2.n;
                int n7 = n6 = this.x.dh != -1 ? this.x.dh : this.y.n;
                if (n6 > n5) {
                    this.c(false);
                }
            }
        }
        if (this.i && this.x.dv != null) {
            this.b.a(this.x.dv, 7, true);
        }
    }

    @Override
    public void f_() {
        if (this.x.aH) {
            this.bT = false;
        } else if (!this.bV) {
            this.bT = true;
            if (this.x.eC) {
                this.bT = false;
            }
        } else {
            this.bT = false;
        }
        if (this.cO != null) {
            this.bT = false;
        }
    }

    public j(boolean bl2, l l2) {
        super(bl2);
        this.a(l2, true, false);
    }

    @Override
    public void a(float f2) {
        float f3;
        Object object;
        float f4;
        float f5;
        int n2;
        Object object2;
        Object object3;
        boolean bl2 = this.i;
        if (bl2) {
            this.i = false;
            object3 = this.x.h.a();
            for (int i2 = this.x.h.a - 1; i2 >= 0; --i2) {
                object2 = (com.corrodinggames.rts.game.units.custom.b.a)object3[i2];
                ((com.corrodinggames.rts.game.units.custom.b.a)object2).a(this);
            }
            this.b(com.corrodinggames.rts.game.units.custom.af.a);
        }
        object3 = this.x;
        super.a(f2);
        if (this.bV && !this.l) {
            if (this.eq > 0.0f) {
                if (object3.fi && (this.cf != 0.0f || this.cc != 0.0f || this.cd != 0.0f)) {
                    this.f += 0.017f * f2;
                    this.eq -= this.f * f2;
                    PointF pointF = this.n(f2);
                    this.eo += pointF.a;
                    this.ep += pointF.b;
                    if (object3.fg == com.corrodinggames.rts.game.units.ao.d) {
                        com.corrodinggames.rts.gameFramework.d.e e2;
                        object2 = com.corrodinggames.rts.gameFramework.l.B();
                        this.dq += f2;
                        this.do += f2;
                        if (object3.fj && this.dq > 9.0f) {
                            this.dq = com.corrodinggames.rts.gameFramework.f.c(1.0f, 3.0f);
                            e2 = ((com.corrodinggames.rts.gameFramework.l)object2).bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.b);
                            if (e2 != null) {
                                e2.aq = 0;
                                e2.ap = 0;
                                e2.ar = (short)2;
                                e2.r = true;
                                e2.E = 0.5f;
                                e2.W = 60.0f;
                                e2.V = 60.0f;
                                e2.G = 0.9f;
                                e2.F = 1.2f;
                                e2.as = false;
                                e2.P = 0.0f;
                                e2.Q = 0.0f;
                            }
                        }
                        if (this.do > 7.0f) {
                            this.do = 0.0f;
                            e2 = ((com.corrodinggames.rts.gameFramework.l)object2).bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.a);
                            if (e2 != null) {
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
                    }
                } else {
                    this.f += object3.dW * f2;
                    this.eq -= this.f * f2;
                }
            } else if (!this.k) {
                this.k = true;
                if (object3.fi) {
                    this.ds();
                    this.S(0);
                    if (object3.an != null) {
                        this.M = object3.an;
                        this.a = 0;
                        this.ew = true;
                    } else {
                        this.ci();
                    }
                }
                if ((double)this.f > 0.5) {
                    if (object3.bw != null && object3.bw.a()) {
                        object3.bw.a(this.eo, this.ep, this.eq, this.cg, null);
                    }
                    if (this.cK()) {
                        if (object3.bv) {
                            this.a(com.corrodinggames.rts.game.units.ab.a);
                        }
                        if (this.cJ()) {
                            com.corrodinggames.rts.gameFramework.l.B().bR.a(this.eo, this.ep, 0.0f, 0, 0.0f, 0.0f, this.cg);
                        }
                    } else if (object3.bv) {
                        this.a(com.corrodinggames.rts.game.units.ab.b);
                    }
                }
                this.f = 0.0f;
            } else if (this.cK()) {
                if (this.eq > -10.0f) {
                    this.f += 8.0E-4f * f2;
                    this.eq -= this.f * f2;
                    if (this.cJ()) {
                        this.t += f2;
                        if (this.t > 30.0f) {
                            this.t = 0.0f;
                            if (this.s_()) {
                                com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
                                object2 = l2.bR.b(this.eo, this.ep, this.eq, this.cg);
                                if (object2 != null) {
                                    ((com.corrodinggames.rts.gameFramework.d.e)object2).P = 0.0f;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object2).Q = -0.1f;
                                }
                            }
                        }
                    }
                } else {
                    this.l = true;
                }
            } else {
                this.eq = 0.0f;
                this.l = true;
            }
        }
        if (this.bV) {
            return;
        }
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        if (object3.bh != 0.0f && this.cm < 1.0f) {
            float f6 = this.cm + object3.bh * f2;
            if (f6 >= 1.0f) {
                com.corrodinggames.rts.game.n.b((am)this);
                this.cm = 1.0f;
                this.cn = 1.0f;
                com.corrodinggames.rts.game.n.c(this);
            } else {
                this.cm = f6;
                this.cn = f6;
            }
        }
        if (!this.bT()) {
            int n3;
            if (this.cm < 1.0f) {
                if (object3.dw != null) {
                    this.b.a(object3.dw, 8);
                    this.b.a(f2);
                } else if (object3.dx != null) {
                    this.b.a(object3.dx, 99);
                    this.b.b = this.cm;
                    this.b.d = 0.0f;
                    this.b.a(f2);
                }
            }
            Object[] objectArray = object3.h.a();
            for (n3 = object3.h.a - 1; n3 >= 0; --n3) {
                com.corrodinggames.rts.game.units.custom.b.a a2 = (com.corrodinggames.rts.game.units.custom.b.a)objectArray[n3];
                a2.a(this, f2);
            }
            n3 = 1;
            if (this.cm < 1.0f && !object3.cd || this.cN != null && !object3.cc) {
                n3 = 0;
            }
            if (n3 != 0) {
                this.b(f2, bl2);
                object3 = this.x;
            }
            return;
        }
        Object[] objectArray = object3.h.a();
        for (n2 = object3.h.a - 1; n2 >= 0; --n2) {
            com.corrodinggames.rts.game.units.custom.b.a a3 = (com.corrodinggames.rts.game.units.custom.b.a)objectArray[n2];
            a3.b(this, f2);
        }
        n2 = this.h ? 1 : 0;
        if (n2 != 0) {
            this.h = false;
            this.a(com.corrodinggames.rts.game.units.custom.af.b);
        }
        if (this.y.p != 0.0f && (this.cu < this.cv || this.y.p < 0.0f)) {
            this.cu += this.y.p * f2;
            if (this.cu > this.cv) {
                this.cu = this.cv;
            }
        }
        if (this.y.d != 0.0f) {
            float f7 = !this.v ? object3.cP : object3.cQ;
            if (this.cB < this.y.d || f7 < 0.0f) {
                this.cB += f7 * f2;
            }
            if (this.cB >= this.y.d) {
                this.cB = this.y.d;
                this.v = false;
            }
            if (this.cB <= 0.0f) {
                if (object3.bk) {
                    this.bv();
                    return;
                }
                this.cB = 0.0f;
            }
        }
        if (this.cA != 0.0f) {
            if (this.y.h != 0.0f) {
                this.cx += this.y.h * f2;
                if (this.cx > this.cA) {
                    this.cx = this.cA;
                }
            }
            if (this.cx < 0.0f) {
                this.cx = 0.0f;
            }
            if (this.cy != 0.0f) {
                this.cy -= this.cy * 0.02f * f2;
                this.cy = com.corrodinggames.rts.gameFramework.f.a(this.cy, 0.0f, object3.cV * f2);
                if (this.cy > 50.0f) {
                    this.cy = 50.0f;
                }
            }
        }
        this.dL.a(f2);
        object3 = this.x;
        if (object3.fp) {
            com.corrodinggames.rts.game.units.e.b.a(f2, this);
        }
        if (object3.cn) {
            this.o += f2;
            if (this.o > (float)object3.cr - 0.1f) {
                this.o -= (float)object3.cr;
                boolean bl3 = object3.cx.read(this);
                if (this.p != bl3) {
                    com.corrodinggames.rts.game.n.a(this);
                    this.p = bl3;
                    com.corrodinggames.rts.game.n.c(this);
                }
                if (this.p) {
                    object3.co.g(this);
                }
            }
        }
        if (object3.ct != null) {
            this.q += f2;
            if (this.q >= object3.cu) {
                this.q = 0.0f;
                object3.ct.writeToUnit(this);
            }
        }
        if (this.cK) {
            this.b.a(object3.ds, 3);
        } else if (!this.b.e || this.b.a == object3.dt) {
            this.b.a(object3.dt, 2);
        }
        if (object3.bL) {
            float f8;
            if (object3.bJ && l3.dd) {
                if (this.cf > 0.0f || this.cf < 0.0f && object3.bK) {
                    this.t += f2;
                }
                if (this.t > 10.0f) {
                    this.t = 0.0f;
                    if (this.el && this.cJ()) {
                        float f9 = this.cg + 180.0f;
                        if (this.cf < 0.0f) {
                            f9 += 180.0f;
                        }
                        if ((f8 = this.cj - 6.0f) < 4.0f) {
                            f8 = 4.0f;
                        }
                        f5 = this.eo + com.corrodinggames.rts.gameFramework.f.k(f9) * f8;
                        f4 = this.ep + com.corrodinggames.rts.gameFramework.f.j(f9) * f8;
                        l3.bR.b(f5, f4, 0.0f, f9);
                    }
                }
            }
            if (l3.dc && (this.cf > 0.0f || this.cf < 0.0f) && this.el) {
                this.d += f2;
                if (this.d > object3.bR) {
                    this.d = 0.0f;
                    if (this.cf > 0.0f) {
                        if (object3.bO != null) {
                            object3.bO.a(this.eo, this.ep, this.eq, this.cg, this);
                        }
                    } else if (object3.bP != null) {
                        float f10 = this.cg;
                        if (object3.bQ) {
                            f10 += 180.0f;
                        }
                        object3.bP.a(this.eo, this.ep, this.eq, f10, this);
                    }
                    if (object3.bM && (this.cf > 0.0f || object3.bN) && !this.cJ()) {
                        for (int i3 = 0; i3 <= 1; ++i3) {
                            f8 = i3 == 0 ? -20 : 20;
                            f5 = this.cg + 180.0f;
                            if (this.cf < 0.0f) {
                                f8 += 180.0f;
                                f5 += 180.0f;
                            }
                            if ((object = l3.bR.c(f4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg + 180.0f + f8) * (this.cj - 1.0f), f3 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg + 180.0f + f8) * (this.cj - 1.0f), this.eq, f5 += com.corrodinggames.rts.gameFramework.f.c(-7.0f, 7.0f), 0)) == null) continue;
                            ((com.corrodinggames.rts.gameFramework.d.e)object).P += com.corrodinggames.rts.gameFramework.f.c(-0.15f, 0.15f);
                            ((com.corrodinggames.rts.gameFramework.d.e)object).Q += com.corrodinggames.rts.gameFramework.f.c(-0.15f, 0.15f);
                        }
                    }
                }
            }
        }
        if (object3.eM > 0) {
            if (object3.eY != 0.0f && this.B.a > 0) {
                Object[] objectArray2 = this.B.a();
                for (int i4 = 0; i4 < this.B.a; ++i4) {
                    am am2 = (am)objectArray2[i4];
                    if (!(am2.cu < am2.cv) && !(object3.eY < 0.0f)) continue;
                    am2.cu += object3.eY * f2;
                    if (!(am2.cu > am2.cv)) continue;
                    am2.cu = am2.cv;
                }
            }
            if (this.n && object3.fc.read(this)) {
                this.m = com.corrodinggames.rts.gameFramework.f.a(this.m, f2);
                if (this.m == 0.0f) {
                    this.m = object3.eN;
                    if (this.B.size() == 0) {
                        this.n = false;
                    } else {
                        this.g(false);
                        if (this.B.size() == 0) {
                            this.n = false;
                        }
                    }
                }
            }
        }
        this.s = !this.cK ? (this.s += f2) : 0.0f;
        if (object3.fg != com.corrodinggames.rts.game.units.ao.d && this.cO == null) {
            this.dF();
        }
        if ((object3.fg == com.corrodinggames.rts.game.units.ao.g || object3.fg == com.corrodinggames.rts.game.units.ao.h) && this.cK) {
            this.j = this.cI() ? 0.7f : 1.0f;
        }
        if (object3.fg == com.corrodinggames.rts.game.units.ao.d) {
            this.e += 2.0f * f2;
            if (this.e > 360.0f) {
                this.e -= 360.0f;
            }
            boolean bl4 = this.i();
            boolean bl5 = false;
            if (object3.dQ) {
                boolean bl6 = this.cK();
                if (!(this.cK || bl6 || !(this.s > 3.0f) || object3.dR && !this.aq())) {
                    bl5 = true;
                }
            }
            if (this.cO == null) {
                if (bl5) {
                    f5 = object3.dU < 0.0f ? com.corrodinggames.rts.gameFramework.f.c(this.eq - 2.0f) * 0.05f * 0.4f + 0.2f : object3.dU;
                    this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 2.0f, f5 * f2);
                } else {
                    f5 = this.y.q + com.corrodinggames.rts.gameFramework.f.j(this.e) * object3.dT;
                    f4 = object3.dU < 0.0f ? com.corrodinggames.rts.gameFramework.f.c(this.eq - 2.0f) * 0.05f * 0.4f + 0.2f : object3.dU;
                    f3 = com.corrodinggames.rts.gameFramework.f.c(this.eq - f5) * 0.05f * 0.3f + 0.1f;
                    f4 = com.corrodinggames.rts.gameFramework.f.b(f4, f3);
                    this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, f5, f4 * f2);
                }
                if (bl4 != this.i()) {
                    this.ay = true;
                    this.dF();
                }
            }
        } else {
            float f11 = this.y.q - object3.dT;
            if (this.eq < f11) {
                this.eq += 0.2f * f2;
                if (this.eq >= f11) {
                    this.eq = f11;
                }
            }
            if ((this.y.q != 0.0f || object3.dT != 0.0f || this.eq > 0.0f) && this.cO == null) {
                float f12 = this.y.q;
                if (object3.dT != 0.0f) {
                    this.e += 2.0f * f2;
                    if (this.e > 360.0f) {
                        this.e -= 360.0f;
                    }
                    f12 += com.corrodinggames.rts.gameFramework.f.j(this.e) * object3.dT;
                }
                f5 = object3.dU < 0.0f ? com.corrodinggames.rts.gameFramework.f.c(this.eq - 2.0f) * 0.05f * 0.4f + 0.2f : object3.dU;
                f4 = com.corrodinggames.rts.gameFramework.f.c(this.eq - f12) * 0.05f * 0.3f + 0.1f;
                f5 = com.corrodinggames.rts.gameFramework.f.b(f5, f4);
                this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, f12, f5 * f2);
                boolean bl7 = false;
                if (this.eq > this.y.q + object3.dT + 1.0f) {
                    this.f += object3.dV * f2;
                    if (this.eq < 0.0f) {
                        this.f = com.corrodinggames.rts.gameFramework.f.b(this.f, 0.2f);
                    }
                    this.eq -= this.f * f2;
                    if ((double)this.f > 1.5) {
                        this.dq += f2;
                        if ((double)this.dq > 0.5) {
                            this.dq = 0.0f;
                            object = l3.bR.b(this.eo + com.corrodinggames.rts.gameFramework.f.c(-this.cj, this.cj), this.ep + com.corrodinggames.rts.gameFramework.f.c(-this.cj, this.cj), this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.c);
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).aq = 0;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).ap = 0;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).ar = (short)2;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).r = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).s = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).t = 40.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).an = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).P = 0.1f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).R = 0.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).u = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.4f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).V = ((com.corrodinggames.rts.gameFramework.d.e)object).W = 380.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 0.8f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 1.7f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).as = false;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).P += com.corrodinggames.rts.gameFramework.f.c(-0.04f, 0.04f);
                                ((com.corrodinggames.rts.gameFramework.d.e)object).Q += com.corrodinggames.rts.gameFramework.f.c(-0.04f, 0.04f);
                            }
                        }
                    }
                    if (this.eq <= this.y.q + object3.dT + 1.0f) {
                        if (this.f > 2.0f) {
                            bl7 = true;
                        }
                        if (this.eq < this.y.q + object3.dT) {
                            this.eq = this.y.q + object3.dT;
                        }
                        this.f = 0.0f;
                    }
                } else {
                    if (this.f > 2.0f) {
                        bl7 = true;
                    }
                    this.f = 0.0f;
                }
                if (bl7 && (object = l3.bR.c(this.eo, this.ep, this.eq, 0)) != null) {
                    ((com.corrodinggames.rts.gameFramework.d.e)object).G = 0.8f;
                    ((com.corrodinggames.rts.gameFramework.d.e)object).F = 1.4f;
                    ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 60.0f;
                }
            }
        }
        boolean bl8 = false;
        boolean bl9 = false;
        if (object3.bg) {
            bl9 = true;
        }
        if (this.z != null && this.z.bg) {
            bl9 = true;
        }
        if (bl9) {
            s s2 = this.dL.d();
            boolean bl10 = false;
            if (s2 != null) {
                if (s2 instanceof g) {
                    as as2;
                    g g2 = (g)s2;
                    object = g2.a;
                    boolean bl11 = false;
                    bl10 = g2.L();
                    if (((d)object).l != null && (as2 = ((d)object).l.c()) != null && as2 instanceof l) {
                        bl8 = true;
                        if (as2 != object3) {
                            if (this.z != null) {
                                com.corrodinggames.rts.game.n.b((am)this);
                                this.a(this.z, false, false, this.A);
                                this.z = null;
                                this.A = null;
                                object3 = this.x;
                                com.corrodinggames.rts.game.n.c(this);
                            }
                            com.corrodinggames.rts.game.n.b((am)this);
                            this.z = object3;
                            this.A = ((d)object).m;
                            this.a((l)as2, false, false, ((d)object).m);
                            object3 = this.x;
                            com.corrodinggames.rts.game.n.c(this);
                        }
                    }
                    if (((d)object).W != null) {
                        Object object4;
                        float f13;
                        float f14 = ((d)object).W.floatValue();
                        if (((d)object).Z) {
                            f13 = this.eo;
                            float f15 = this.ep;
                            object4 = this.dL.b();
                            if (object4 != null) {
                                float f16 = Float.MIN_VALUE;
                                float f17 = Float.MIN_VALUE;
                                if (((com.corrodinggames.rts.game.units.d.j)object4).i != null) {
                                    f16 = ((com.corrodinggames.rts.game.units.d.j)object4).i.eo;
                                    f17 = ((com.corrodinggames.rts.game.units.d.j)object4).i.ep;
                                } else if (((com.corrodinggames.rts.game.units.d.j)object4).h != null) {
                                    f16 = ((com.corrodinggames.rts.game.units.d.j)object4).h.a;
                                    f17 = ((com.corrodinggames.rts.game.units.d.j)object4).h.b;
                                }
                                if (f16 > Float.MIN_VALUE) {
                                    float f18 = com.corrodinggames.rts.gameFramework.f.d(f13, f15, f16, f17);
                                    f14 += f18;
                                }
                            }
                        }
                        if (((d)object).aa == null) {
                            f13 = this.a(f2, f14, true, ((d)object).X);
                        } else {
                            int n4 = ((d)object).aa.e;
                            f13 = this.a(f2, f14, n4);
                            object4 = this.cL[n4];
                            ((ap)object4).b(5);
                            ((ap)object4).b = ((ap)object4).a;
                        }
                        if (((d)object).Y && com.corrodinggames.rts.gameFramework.f.c(f13) > 5.0f) {
                            bl11 = true;
                        }
                    }
                    if (((d)object).V != null && !bl11) {
                        this.b.a(((d)object).V.b(), 10);
                    }
                    if (bl11) {
                        this.dL.e = 0.0f;
                    }
                }
                if (object3.dy != null && s2.i() != null && this.dL.e >= object3.dy.q) {
                    this.b.a(object3.dy, 5);
                }
            }
            this.g = bl10;
            if (this.g) {
                this.cc = 0.0f;
                this.cd = 0.0f;
                this.cf = 0.0f;
            }
        }
        if (this.z != null && !bl8) {
            com.corrodinggames.rts.game.n.b((am)this);
            this.a(this.z, false, false, this.A);
            this.z = null;
            this.A = null;
            object3 = this.x;
            com.corrodinggames.rts.game.n.c(this);
        }
        this.b.a(f2);
        this.b(f2, bl2);
        object3 = this.x;
    }

    @Override
    public float cy() {
        int n2 = this.x.co.b;
        if (!this.p) {
            return 0.0f;
        }
        return (float)n2 * this.x.cs;
    }

    @Override
    public f cz() {
        if (!this.p) {
            return com.corrodinggames.rts.game.units.custom.e.f.a;
        }
        return this.x.cp;
    }

    @Override
    public f cA() {
        if (!this.p) {
            return com.corrodinggames.rts.game.units.custom.e.f.a;
        }
        return this.x.cq;
    }

    @Override
    public boolean a(int n2, am am2, boolean bl2, boolean bl3) {
        bn bn2 = this.x.fQ[n2];
        return this.a(bn2, n2, am2, bl2, bl3);
    }

    public final boolean a(bn bn2, int n2, float f2, float f3, boolean bl2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f4 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f2, f3);
        if (f4 > bn2.ae) {
            if (this.bX == l2.bs) {
                l2.bS.b("Location too far");
            }
            return false;
        }
        if (f4 < bn2.ah) {
            if (this.bX == l2.bs) {
                l2.bS.b("Location too close");
            }
            return false;
        }
        return true;
    }

    public final boolean a(bn bn2, int n2, am am2, boolean bl2, boolean bl3) {
        float f2;
        float f3;
        float f4;
        if (!bl2 && (bn2.I || bl3)) {
            f4 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, am2.eo, am2.ep);
            if (f4 > bn2.ae) {
                return false;
            }
            if (f4 < bn2.ah) {
                return false;
            }
        }
        if (!bn2.H) {
            return true;
        }
        if (bn2.ai != -1.0f && com.corrodinggames.rts.gameFramework.f.c(f3 = com.corrodinggames.rts.gameFramework.f.c(f4 = bn2.w != -1 ? this.cL[bn2.w].a + bn2.j : this.cg + bn2.j, f2 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, am2.eo, am2.ep), 360.0f)) > bn2.ai) {
            return false;
        }
        if (bn2.N != null && !bn2.N.read(this)) {
            return false;
        }
        if (bn2.O != null && !com.corrodinggames.rts.game.units.custom.g.a(bn2.O, am2.de())) {
            return false;
        }
        if (bn2.P != null && com.corrodinggames.rts.game.units.custom.g.a(bn2.P, am2.de())) {
            return false;
        }
        if (am2.i()) {
            if (bn2.J != null) {
                return bn2.J.read(this);
            }
            return true;
        }
        if (am2.Q()) {
            if (bn2.L != null) {
                return bn2.L.read(this);
            }
            return true;
        }
        if (bn2.M != null && !bn2.M.read(this) && !am2.cH()) {
            return false;
        }
        if (bn2.K != null) {
            return bn2.K.read(this);
        }
        return true;
    }

    @Override
    public void a(am am2, int n2) {
        bn bn2 = this.x.fQ[n2];
        if (!bn2.B) {
            return;
        }
        if (!this.a(bn2, n2, am2, false, false)) {
            return;
        }
        for (int i2 = 0; i2 < this.x.fQ.length; ++i2) {
            bn bn3 = this.x.fQ[i2];
            if (bn3 == null || i2 == n2 || bn3.ao == null || bn3.ao != bn2 || !(this.cL[i2].e < 9000.0f - (this.b(n2) * 0.5f - bn3.n))) continue;
            this.cL[i2].e = 0.0f;
        }
        if (bn2.w != -1) {
            bn bn4 = this.x.fQ[bn2.w];
            if (!bn4.B && bn4.p != 0.0f) {
                this.cL[bn2.w].e = this.b(bn2.w) + this.t(bn2.w);
            }
        }
        this.b.a(this.x.du, 11, true);
        this.b(bn2);
        this.a(am2, -1.0f, -1.0f, n2, null, 0);
    }

    public boolean a(bn bn2) {
        if (bn2.u > 0.0f) {
            if (bn2.u > this.cB) {
                return false;
            }
            if (this.v) {
                return false;
            }
        }
        return bn2.v == null || bn2.v.b(this);
    }

    public void b(bn bn2) {
        if (bn2.u > 0.0f) {
            this.cB -= bn2.u;
            if (this.cB < bn2.u && this.x.cR) {
                this.v = true;
            }
        }
        if (bn2.v != null) {
            bn2.v.a(this);
        }
    }

    public static com.corrodinggames.rts.game.f a(am am2, int n2, bh bh2, float f2, float f3, float f4, float f5) {
        com.corrodinggames.rts.game.f f6 = com.corrodinggames.rts.game.f.a(am2, f2, f3, f4, n2);
        com.corrodinggames.rts.game.units.custom.j.a(f6, am2, n2, bh2, f2, f3, f4, f5);
        return f6;
    }

    public static void a(com.corrodinggames.rts.game.f f2, am am2, int n2, bh bh2, float f3, float f4, float f5, float f6) {
        Object object;
        y y2;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        f2.az = f6;
        f2.aT = f6;
        if (am2 == null) {
            throw new RuntimeException("Source cannot be null");
        }
        f2.g = bh2;
        f2.G = bh2.aI;
        f2.aR = bh2.aJ;
        f2.U = bh2.b;
        f2.Y = bh2.c;
        if (!bh2.aN && am2 instanceof j) {
            y2 = (j)am2;
            f2.U *= ((j)y2).y.f;
            f2.Y *= ((j)y2).y.f;
        }
        f2.Z = bh2.i;
        if (bh2.l) {
            f2.aa = false;
            f2.ab = true;
        } else {
            f2.aa = !bh2.k;
        }
        f2.ac = bh2.n;
        if (bh2.m) {
            f2.ad = true;
            f2.ae = false;
        }
        f2.D = bh2.p;
        f2.aY = bh2.q;
        f2.aZ = bh2.r;
        if ((double)bh2.o < 0.5) {
            f2.C = true;
        } else {
            f2.H = bh2.o;
        }
        f2.h = bh2.v;
        f2.i = bh2.u;
        f2.t = bh2.w;
        if (bh2.aM != 0.0f) {
            f2.t += (float)com.corrodinggames.rts.gameFramework.f.a((com.corrodinggames.rts.gameFramework.w)am2, (int)(-bh2.aM * 100.0f), (int)(bh2.aM * 100.0f), 1) / 100.0f;
        }
        if (bh2.T && n2 != -1) {
            f2.au = am2;
            if (am2 instanceof y) {
                y2 = (y)am2;
                f2.av = n2;
                object = y2.D(n2);
                f2.aw = ((com.corrodinggames.rts.gameFramework.utility.ai)object).a;
                f2.ax = ((com.corrodinggames.rts.gameFramework.utility.ai)object).b;
                f2.ay = am2.eq + ((com.corrodinggames.rts.gameFramework.utility.ai)object).c;
            } else {
                f2.aw = am2.eo;
                f2.ax = am2.ep;
                f2.ay = am2.eq;
            }
        }
        f2.w = bh2.D;
        f2.u = bh2.E;
        f2.v = bh2.F;
        f2.af = bh2.aO;
        f2.ag = bh2.aP;
        f2.ah = bh2.aQ;
        f2.ai = bh2.aR;
        f2.ak = bh2.aS;
        f2.al = bh2.aT;
        f2.am = bh2.aU;
        f2.an = bh2.aV;
        if (bh2.aW > 0.0f) {
            f2.ao = true;
            f2.X = f2.W = bh2.aW;
        }
        f2.ar = bh2.aE;
        if (bh2.aG != 0.0f) {
            float f7 = bh2.aH;
            int n3 = Color.a(f2.ar);
            int n4 = (int)((float)Color.b(f2.ar) * f7);
            int n5 = (int)((float)Color.c(f2.ar) * f7);
            int n6 = (int)((float)Color.d(f2.ar) * f7);
            int n7 = am2.bX.K();
            n4 = (int)((float)n4 + (float)Color.b(n7) * bh2.aG);
            n5 = (int)((float)n5 + (float)Color.c(n7) * bh2.aG);
            n6 = (int)((float)n6 + (float)Color.d(n7) * bh2.aG);
            n4 = com.corrodinggames.rts.gameFramework.f.b(n4, 0, 255);
            n5 = com.corrodinggames.rts.gameFramework.f.b(n5, 0, 255);
            n6 = com.corrodinggames.rts.gameFramework.f.b(n6, 0, 255);
            f2.ar = Color.a(n3, n4, n5, n6);
        }
        f2.P = bh2.x;
        f2.R = bh2.y;
        f2.S = !bh2.A;
        f2.Q = bh2.z;
        if (bh2.B != null) {
            f2.P = 0;
            f2.R = 0;
        }
        f2.x = bh2.aF;
        f2.m = bh2.s;
        f2.A = bh2.I;
        f2.M = bh2.V;
        f2.B = bh2.W;
        f2.aH = bh2.ae;
        f2.aG = bh2.aw;
        f2.aM = bh2.af;
        if (bh2.ai != null) {
            bh2.ai.a(f2.eo, f2.ep, f2.eq, f2.az, f2);
        }
        if (bh2.ao != -1) {
            com.corrodinggames.rts.gameFramework.d.e e2;
            boolean bl2 = false;
            object = f2.aP;
            if (object != null && ((com.corrodinggames.rts.gameFramework.d.e)object).b == f2 && ((com.corrodinggames.rts.gameFramework.d.e)object).d && object != null) {
                if (((com.corrodinggames.rts.gameFramework.d.e)object).V < 150.0f) {
                    ((com.corrodinggames.rts.gameFramework.d.e)object).V = 200.0f;
                }
                bl2 = true;
            }
            if (!bl2 && (e2 = l2.bR.a(f2, bh2.ao, bh2.ap)) != null) {
                if (bh2.aq) {
                    e2.c = true;
                }
                if (bh2.L) {
                    f2.aP = e2;
                }
            }
        }
        f2.aQ = bh2.ar;
        if (bh2.as != -1.0f) {
            f2.aI = bh2.as;
        }
        if (bh2.at != -1.0f) {
            f2.aJ = bh2.at;
        }
        f2.aL = -1.0f;
        if (bh2.au != -1.0f) {
            f2.r = bh2.au;
        }
        f2.s = bh2.av;
        if (bh2.aZ != null) {
            // empty if block
        }
        f2.aE = bh2.bd;
        f2.em = am2.em;
        if (f2.em < 4 && f5 >= -1.0f) {
            f2.em = 4;
        }
        if (bh2.U) {
            f2.em = 1;
        }
    }

    public com.corrodinggames.rts.game.f a(am am2, float f2, float f3, int n2, bh bh2, int n3) {
        Object object;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        bn bn2 = this.x.fQ[n2];
        if (bn2.aA != null) {
            this.b.a(bn2.aA.b(), 6, true);
        }
        if (bn2.aB > 0.0f) {
            this.b(bn2.aB);
        }
        if (bn2.aC != null) {
            object = new PointF(f2, f3);
            bn2.aC.a(this, (PointF)object, am2, n3 + 1, 0);
        }
        object = bh2 == null ? this.x.fR[bn2.a(this)] : bh2;
        PointF pointF = this.K(n2);
        com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.D(n2);
        if (bn2.aI > 0) {
            for (int i2 = 0; i2 < bn2.aI; ++i2) {
                if (this.B == null || this.B.size() <= 0) continue;
                am am3 = (am)this.B.remove(this.B.size() - 1);
                ap ap2 = this.cL[n2];
                com.corrodinggames.rts.gameFramework.utility.y.a(am3, this);
                am3.eo = ai2.a;
                am3.ep = ai2.b;
                am3.cg = ap2.a;
                if (!(am3 instanceof y)) continue;
                y y2 = (y)am3;
                y2.az();
                if (am2 != null) {
                    y2.n(am2);
                    continue;
                }
                y2.e(f2, f3);
            }
        }
        com.corrodinggames.rts.game.f f4 = null;
        if (((bh)object).L && bh2 == null) {
            if (this.G == null) {
                this.G = new com.corrodinggames.rts.game.f[31];
            }
            if (this.G[n2] != null && !this.G[n2].ej) {
                f4 = this.G[n2];
                f4.a(this, ai2.a, ai2.b, this.eq + ai2.c);
                if (!((bh)object).N && f4.ap != null) {
                    f4.ap.clear();
                }
            }
        }
        this.bC = (int)((long)this.bC + (1L + this.eh));
        float f5 = this.cL[n2].a;
        boolean bl2 = false;
        if (f4 == null) {
            f4 = com.corrodinggames.rts.game.f.a((am)this, ai2.a, ai2.b, this.eq + ai2.c, n2);
            if (((bh)object).L && bh2 == null) {
                this.G[n2] = f4;
            }
        } else {
            f4.g = object;
            bl2 = true;
        }
        com.corrodinggames.rts.game.units.custom.j.a(f4, this, n2, (bh)object, ai2.a, ai2.b, this.eq + ai2.c, f5);
        ((bh)object).a(this, f4, am2, f2, f3, this.m());
        if (!bl2 && ((bh)object).R == 0.0f && ((bh)object).S == 0.0f) {
            f4.K = pointF.a;
            f4.L = pointF.b;
        }
        if (bn2.G != null) {
            l2.bR.a(ai2.a, ai2.b, this.eq + ai2.c, bn2.G);
        }
        if (bn2.E != null) {
            bn2.E.a(ai2.a, ai2.b, this.eq + ai2.c, this.cL[n2].a, this);
        }
        if (bn2.D != null) {
            float f6 = 1.0f + com.corrodinggames.rts.gameFramework.f.c(-0.07f, 0.07f);
            bn2.D.a(ai2.a, ai2.b, f6);
        }
        if (this.x.eg) {
            this.R = null;
        }
        if (bn2.ay) {
            this.cL[n2].j = null;
        }
        if (this.x.bj && !this.bV) {
            this.bv();
        }
        if (this.x.bl && !this.bV) {
            this.a();
            this.bV = true;
        }
        return f4;
    }

    @Override
    public float m() {
        return this.y.i;
    }

    @Override
    public int y() {
        if (this.y.o != -1) {
            return this.y.o;
        }
        return super.y();
    }

    @Override
    public int u(am am2) {
        int n2 = am2.r().a(this);
        if (this.x.aX != -1) {
            if (this.x.aY) {
                int n3 = (int)((float)this.x.aX + this.cj);
                if (am2 != null) {
                    n3 = (int)((float)n3 + am2.cj);
                }
                return n3 + n2;
            }
            return this.x.aX + n2;
        }
        return this.y() + n2;
    }

    @Override
    public int v(am am2) {
        int n2 = am2.r().a(this);
        if (this.x.aZ != -1) {
            if (this.x.ba) {
                int n3 = (int)((float)this.x.aZ + this.cj);
                if (am2 != null) {
                    n3 = (int)((float)n3 + am2.cj);
                }
                return n3 + n2;
            }
            return this.x.aZ + n2;
        }
        return this.y() + n2;
    }

    @Override
    public boolean w(am am2) {
        return this.x.ba;
    }

    @Override
    public boolean x(am am2) {
        return this.x.aY;
    }

    @Override
    public float cx() {
        return this.y.r;
    }

    @Override
    public float c(am am2) {
        return this.x.bb;
    }

    @Override
    public float z(am am2) {
        boolean bl2;
        float f2 = this.x.bc;
        boolean bl3 = bl2 = am2.g() > 0.0f;
        if (bl2) {
            f2 = am2.g() * this.x.bd;
        }
        return f2;
    }

    @Override
    public float f(am am2) {
        return this.x.be;
    }

    @Override
    public float b(am am2) {
        return this.x.bf;
    }

    @Override
    public float z() {
        return this.y.j * this.j;
    }

    @Override
    public float aZ() {
        return this.x.ej;
    }

    @Override
    public float ba() {
        return this.x.ek;
    }

    @Override
    public float A() {
        return this.y.k;
    }

    @Override
    public float c(int n2) {
        bn bn2 = this.x.fQ[n2];
        if (bn2.U != null) {
            return bn2.U.floatValue();
        }
        return this.x.eb;
    }

    @Override
    public float x(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.Q;
    }

    @Override
    public float w(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.V;
    }

    @Override
    public float y(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.W;
    }

    @Override
    public float B() {
        return this.x.eo;
    }

    @Override
    public float cD() {
        return this.x.bH * this.c;
    }

    @Override
    public float p(int n2) {
        return this.x.bI;
    }

    @Override
    public float d(boolean bl2) {
        if (!this.x.dB) {
            return 0.0f;
        }
        if (bl2 && this.x.dD) {
            return this.cL[this.x.dG].a + 90.0f;
        }
        if (this.x.dC) {
            return this.cL[this.x.dG].a + 90.0f;
        }
        return super.d(bl2);
    }

    @Override
    public PointF cY() {
        PointF pointF = H;
        l l2 = this.x;
        if (l2.dC && this.cL[l2.dG].e != 0.0f) {
            bn bn2 = l2.dF;
            if (bn2.p != 0.0f) {
                pointF.a(this.G(l2.dG));
                pointF.b(-this.eo, -this.ep);
                return pointF;
            }
        }
        pointF.a = 0.0f;
        pointF.b = 0.0f;
        return pointF;
    }

    @Override
    public PointF aP() {
        l l2 = this.x;
        PointF pointF = this.cY();
        com.corrodinggames.rts.game.units.custom.j.I.a = pointF.a + l2.cJ;
        com.corrodinggames.rts.game.units.custom.j.I.b = pointF.b + l2.cK;
        return I;
    }

    @Override
    public boolean c(float f2) {
        com.corrodinggames.rts.gameFramework.m.e e2;
        float f3;
        float f4;
        float f5;
        Object object;
        float f6;
        Object object2;
        l l2 = this.x;
        boolean bl2 = this.bV;
        if (this.dT != null && !bl2) {
            com.corrodinggames.rts.game.units.custom.b.h.a(this, f2, false, false);
        }
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.m.y y2 = l3.bO;
        Paint paint = this.aN();
        float f7 = this.cD();
        PointF pointF = this.cY();
        this.aQ();
        com.corrodinggames.rts.gameFramework.utility.m m2 = l2.i;
        int n2 = m2.a;
        if (n2 > 0) {
            object2 = l2.i.a();
            for (int i2 = n2 - 1; i2 >= 0; --i2) {
                com.corrodinggames.rts.game.units.custom.b.a a2 = (com.corrodinggames.rts.game.units.custom.b.a)object2[i2];
                a2.d(this, f2);
            }
        }
        if (this.ew) {
            float f8 = this.eo + pointF.a - l3.cw;
            f6 = this.ep + pointF.b - l3.cx - this.eq;
            if (f7 != 1.0f) {
                y2.k();
                y2.a(f7, f7, f8, f6);
            }
            y2.a(this.M, f8, f6, this.d(false) - 90.0f, paint);
            if (f7 != 1.0f) {
                y2.l();
            }
        } else {
            object2 = this.cF();
            f6 = pointF.a;
            float f9 = pointF.b - this.eq;
            object2.a += f6;
            object2.b += f9;
            object2.c += f6;
            object2.d += f9;
            object = this.a_(false);
            f5 = (object2.a + object2.c) * 0.5f;
            f4 = (object2.b + object2.d) * 0.5f;
            y2.k();
            if (f7 != 1.0f) {
                y2.a(f7, f7, f5, f4);
            }
            y2.a(this.d(false), f5, f4);
            y2.a(this.M, (Rect)object, (RectF)object2, paint);
            y2.l();
        }
        if (n2 > 0) {
            Object[] objectArray = l2.i.a();
            for (int i3 = n2 - 1; i3 >= 0; --i3) {
                com.corrodinggames.rts.game.units.custom.b.a a3 = (com.corrodinggames.rts.game.units.custom.b.a)objectArray[i3];
                a3.e(this, f2);
            }
        }
        com.corrodinggames.rts.gameFramework.utility.y.a(this);
        if (this.dT != null && !bl2 && l2.ay) {
            com.corrodinggames.rts.game.units.custom.b.h.a(this, f2, true, false);
        }
        if (this.ak() && l2.fV != null && !bl2 && (f3 = this.cL[l2.fV.e].f / this.e(l2.fV.e)) != 0.0f) {
            boolean bl3 = true;
            boolean bl4 = this.Y();
            if (bl4 && l2.bW) {
                bl3 = false;
            } else if (!bl4 && l2.bS) {
                bl3 = false;
            }
            if (bl3 && l2.fQ[l2.fV.e].aF == null) {
                object = this.bn();
                l3.bO.k();
                l3.bO.b(((com.corrodinggames.rts.gameFramework.utility.ai)object).a - l3.cw, ((com.corrodinggames.rts.gameFramework.utility.ai)object).b - ((com.corrodinggames.rts.gameFramework.utility.ai)object).c - l3.cx - this.eq);
                l3.bO.a(f3, f3);
                if (bl4) {
                    l3.bO.a(com.corrodinggames.rts.game.units.e.b.f, 0.0f, 0.0f, null);
                } else {
                    l3.bO.a(com.corrodinggames.rts.game.units.e.b.e, 0.0f, 0.0f, null);
                }
                l3.bO.l();
            }
        }
        if (l2.fP && !bl2) {
            int n3 = this.bl();
            for (int i4 = 0; i4 < n3; ++i4) {
                float f10 = this.cL[i4].f / this.e(i4);
                object = l2.fQ[i4];
                if (object == null || f10 == 0.0f || ((bn)object).aF == null) continue;
                com.corrodinggames.rts.gameFramework.utility.y.a(this, ((bn)object).aF, f10, i4);
            }
        }
        if (!bl2 && this.cx > 0.0f && this.cz == 0.0f && (e2 = this.T()) != null) {
            float f11 = 0.0f;
            if (!l2.cU) {
                f11 += 0.09f;
                f11 += this.cx / this.cA * 0.4f;
                f11 += com.corrodinggames.rts.gameFramework.f.b(this.cy, 50.0f) / 50.0f * 0.5f;
            } else {
                f11 += com.corrodinggames.rts.gameFramework.f.b(this.cy, 50.0f) / 50.0f * 0.5f;
                float f12 = this.cy;
                if (f12 > 5.0f) {
                    f12 = 5.0f;
                }
                f11 += f12 / 5.0f * 0.2f;
            }
            if (f11 > 0.0f) {
                float f13;
                if (f11 > 1.0f) {
                    f11 = 1.0f;
                }
                if (this.J == null) {
                    this.J = com.corrodinggames.rts.gameFramework.utility.y.a();
                }
                Paint paint2 = this.J;
                paint2.a((int)(f11 * 255.0f), 255, 255, 255);
                float f14 = this.eo - l3.cw;
                f5 = this.ep - l3.cx - this.eq;
                if (!l2.av) {
                    f13 = 87.0f;
                    f4 = (float)(l2.df * 2) / f13 * 1.25f;
                } else {
                    f13 = e2.p;
                    f4 = (float)(l2.df * 2) / f13 * 1.25f;
                }
                l3.bO.k();
                l3.bO.a(f4, f4, f14, f5);
                l3.bO.a(e2, f14, f5, this.d(false) - 90.0f, paint2);
                l3.bO.l();
            }
        }
        if (n2 > 0) {
            Object[] objectArray = l2.i.a();
            for (int i5 = n2 - 1; i5 >= 0; --i5) {
                com.corrodinggames.rts.game.units.custom.b.a a4 = (com.corrodinggames.rts.game.units.custom.b.a)objectArray[i5];
                a4.c(this, f2);
            }
        }
        return true;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e T() {
        return this.x.au;
    }

    @Override
    public float C() {
        return this.x.dN;
    }

    @Override
    public float D() {
        return this.x.dO;
    }

    @Override
    public boolean bi() {
        return this.x.dX;
    }

    @Override
    public boolean bj() {
        return this.x.dY;
    }

    @Override
    public boolean l() {
        return this.x.ep;
    }

    @Override
    public boolean ag() {
        return this.x.er.read(this);
    }

    @Override
    public boolean af() {
        return this.x.eq.read(this);
    }

    @Override
    public boolean ae() {
        return this.x.es.read(this);
    }

    @Override
    public boolean ah() {
        if (this.x.et == null) {
            return true;
        }
        return this.x.et.read(this);
    }

    @Override
    public boolean k(am am2) {
        l l2 = this.x;
        if (l2.eu) {
            if (l2.ev != null && !com.corrodinggames.rts.game.units.custom.g.a(l2.ev, am2.de())) {
                return false;
            }
            if (l2.ew != null && com.corrodinggames.rts.game.units.custom.g.a(l2.ew, am2.de())) {
                return false;
            }
            if (l2.ex) {
                boolean bl2 = false;
                for (int i2 = 0; i2 < l2.fQ.length; ++i2) {
                    bn bn2 = l2.fQ[i2];
                    if (bn2.P != null && com.corrodinggames.rts.game.units.custom.g.a(bn2.P, am2.de()) || bn2.O != null && !com.corrodinggames.rts.game.units.custom.g.a(bn2.O, am2.de())) continue;
                    bl2 = true;
                    break;
                }
                if (!bl2) {
                    return false;
                }
            }
        }
        if (am2.i()) {
            return this.af();
        }
        if (am2.Q()) {
            return this.ae();
        }
        if (!this.ah() && !am2.cH()) {
            return false;
        }
        return this.ag();
    }

    @Override
    public boolean E() {
        return this.x.ey;
    }

    @Override
    public float g(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.X;
    }

    @Override
    public float z(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.ab;
    }

    @Override
    public float A(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.ah;
    }

    @Override
    public float B(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.j;
    }

    @Override
    public float C(int n2) {
        bn bn2 = this.x.fQ[n2];
        float f2 = bn2.w != -1 ? this.cL[bn2.w].a : this.cg;
        f2 = this.ci && (double)this.bc() > 0.95 ? (f2 += bn2.k) : (f2 += bn2.j);
        if (bn2.ar != 0.0f) {
            return 999.0f;
        }
        return f2;
    }

    @Override
    public boolean bm() {
        return this.x.dL;
    }

    @Override
    public float q(int n2) {
        bn bn2 = this.x.fQ[n2];
        if (!bn2.B) {
            return 0.0f;
        }
        bh bh2 = this.x.fR[bn2.a(this)];
        float f2 = 0.0f;
        if (!bh2.s) {
            f2 += (float)bh2.b;
        }
        f2 += (float)bh2.c;
        if (!bh2.aN) {
            f2 *= this.y.f;
        }
        return f2;
    }

    @Override
    public float b(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.m * this.y.e;
    }

    @Override
    public float e(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.n;
    }

    @Override
    public float f(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.o;
    }

    @Override
    public boolean s(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.s;
    }

    @Override
    public float t(int n2) {
        bn bn2 = this.x.fQ[n2];
        if (bn2.t == 0.0f || bn2.n == 0.0f) {
            return 0.0f;
        }
        return -(bn2.t * (this.cL[n2].f / bn2.n));
    }

    @Override
    public boolean r(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.B;
    }

    @Override
    public void b(am am2, int n2) {
        bn bn2 = this.x.fQ[n2];
        if (bn2.F != null) {
            PointF pointF = this.E(n2);
            bn2.F.a(pointF.a, pointF.b, this.eq, this.cL[n2].a, this);
        }
    }

    @Override
    public boolean u(int n2) {
        bn bn2 = this.x.fQ[n2];
        if (!this.a(bn2)) {
            return false;
        }
        return super.u(n2);
    }

    @Override
    public int s(am am2) {
        return this.x.eB;
    }

    @Override
    public boolean bO() {
        return this.x.eD;
    }

    @Override
    public boolean bP() {
        return this.x.eE;
    }

    @Override
    public float bN() {
        return this.y.b;
    }

    @Override
    public boolean cG() {
        return this.x.aq;
    }

    @Override
    public Rect a_(boolean bl2) {
        if (bl2 && !this.x.aq) {
            return super.a_(bl2);
        }
        if (this.bV) {
            return super.a_(bl2);
        }
        l l2 = this.x;
        int n2 = this.a;
        int n3 = 0;
        m m2 = l2.dI;
        if (this.b.a != null && this.b.a.k != null) {
            m2 = this.b.a.k;
        }
        if (m2 != null) {
            float f2;
            float f3 = m2.b;
            if (f3 < 0.0f) {
                f3 = -f3;
                f2 = -this.cg;
                if (m2.a) {
                    f2 = -this.cL[l2.dG].a;
                }
            } else {
                f2 = this.cg;
                if (m2.a) {
                    f2 = this.cL[l2.dG].a;
                }
            }
            int n4 = (int)((f2 - m2.e - f3 * 0.5f) / f3);
            int n5 = (int)(360.0f / f3);
            if ((n4 %= n5) < 0) {
                n4 += n5;
            }
            if (m2.c > 0) {
                n2 += n4 * m2.c;
            }
            if (m2.d > 0) {
                n3 += n4 * m2.d;
            }
        }
        if (n2 >= l2.V) {
            n3 += n2 / l2.V;
            n2 %= l2.V;
        }
        return super.a(bl2, n2, n3);
    }

    @Override
    public RectF cF() {
        RectF rectF = super.cF();
        if (this.x.ak) {
            rectF.a(this.x.ah, (float)this.x.ai - this.x.aj);
        }
        return rectF;
    }

    @Override
    public int bl() {
        if (this.x == null) {
            return 1;
        }
        return this.x.fQ.length;
    }

    @Override
    public int v(int n2) {
        return this.x.fQ[n2].x;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.utility.ai F(int n2) {
        return this.a(n2, false);
    }

    @Override
    public PointF G(int n2) {
        com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.a(n2, false);
        com.corrodinggames.rts.game.units.custom.j.K.a = ai2.a;
        com.corrodinggames.rts.game.units.custom.j.K.b = ai2.b;
        return K;
    }

    public com.corrodinggames.rts.gameFramework.utility.ai a(int n2, boolean bl2) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        bn bn2 = this.x.fQ[n2];
        if (bn2.w == -1) {
            f6 = this.eo;
            f5 = this.ep;
            f4 = 0.0f;
            f3 = this.cg;
        } else {
            if (bl2) {
                throw new RuntimeException("Turret can not be attached to turret that is not attached to the body");
            }
            com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.a(bn2.w, true);
            f6 = ai2.a;
            f5 = ai2.b;
            f4 = ai2.c;
            f3 = this.cL[bn2.w].a;
        }
        if (this.cL[n2].e > 0.0f && bn2.p != 0.0f) {
            float f7 = 0.0f;
            f2 = this.b(n2) + this.t(n2) - this.cL[n2].e;
            if (f2 < bn2.q) {
                f7 = f2 / bn2.q * bn2.p;
            } else if (f2 < bn2.q + bn2.r) {
                f7 = bn2.p - (f2 - bn2.q) / bn2.r * bn2.p;
            }
            if (f7 != 0.0f) {
                f6 += com.corrodinggames.rts.gameFramework.f.k(this.cL[n2].a) * f7;
                f5 += com.corrodinggames.rts.gameFramework.f.j(this.cL[n2].a) * f7;
            }
        }
        float f8 = bn2.f;
        f2 = bn2.g;
        float f9 = bn2.h;
        if (f8 != 0.0f || f2 != 0.0f) {
            float f10 = com.corrodinggames.rts.gameFramework.f.j(f3);
            float f11 = com.corrodinggames.rts.gameFramework.f.k(f3);
            f6 += f11 * f2 - f10 * f8;
            f5 += (f10 * f2 + f11 * f8) * bn2.i;
        }
        com.corrodinggames.rts.game.units.custom.j.dK.a = f6;
        com.corrodinggames.rts.game.units.custom.j.dK.b = f5;
        com.corrodinggames.rts.game.units.custom.j.dK.c = f4 += f9;
        return dK;
    }

    @Override
    public ArrayList N() {
        if (this.z != null) {
            return this.z.a(this.V());
        }
        return this.x.a(this.V());
    }

    @Override
    public s a(c c2) {
        l l2 = this.z != null ? this.z : this.x;
        return l2.a(c2);
    }

    @Override
    public int V() {
        return this.x.cl;
    }

    @Override
    public s e(as as2) {
        return this.dL.b(as2);
    }

    @Override
    public void a(s s2, boolean bl2, PointF pointF, am am2) {
        Object object;
        Object object2;
        if (s2 == com.corrodinggames.rts.game.units.e.i.i) {
            if (!bl2) {
                this.L();
            } else {
                this.M();
            }
            return;
        }
        if (s2 == com.corrodinggames.rts.game.units.e.i.j) {
            if (!bl2) {
                this.M();
            }
            return;
        }
        if (!bl2) {
            if (pointF != null && !this.a(s2, pointF.a, pointF.b)) {
                return;
            }
            if (s2 instanceof g) {
                object2 = (g)s2;
                if (((g)object2).a.ax != null) {
                    object = com.corrodinggames.rts.gameFramework.l.B();
                    if (this.bX == ((com.corrodinggames.rts.gameFramework.l)object).bs && !((com.corrodinggames.rts.gameFramework.l)object).I()) {
                        ((g)object2).a.ax.a();
                    }
                }
                if (((g)object2).a.at != null) {
                    ((g)object2).a.at.a(this.eo, this.ep, this.eq, this.cg, this);
                }
            }
        }
        if (bl2 && s2 instanceof g) {
            object2 = (g)s2;
            if (!((g)object2).a.M) {
                return;
            }
        }
        object2 = this.dL.a(s2, bl2, pointF, am2);
        if (!bl2) {
            if (object2 != null) {
                object = s2.P();
                this.a(com.corrodinggames.rts.game.units.custom.af.f, null, (com.corrodinggames.rts.game.units.custom.h)object, null);
            }
        } else if (object2 != null) {
            this.a(com.corrodinggames.rts.game.units.custom.af.g, null, s2.P(), null);
        }
    }

    @Override
    public void a(s s2, boolean bl2) {
        this.a(s2, bl2, null, null);
    }

    @Override
    public void b(s s2, boolean bl2) {
        this.dL.a(s2, bl2);
    }

    @Override
    public void a(s s2) {
        this.dL.a(s2);
    }

    @Override
    public boolean a(s s2, float f2, float f3) {
        if (s2 instanceof g) {
            com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
            g g2 = (g)s2;
            if (g2.a.ag != null && g2.a.ah == null) {
                if (g2.a.ag >= this.x.fQ.length) {
                    this.a("checkTargetedActionOrder: " + g2.a.ag + " larger than max turret size");
                    return true;
                }
                bn bn2 = this.x.fQ[g2.a.ag];
                boolean bl2 = true;
                if (!this.a(bn2, (int)g2.a.ag, f2, f3, bl2)) {
                    return false;
                }
                if (g2.a.al != null && com.corrodinggames.rts.gameFramework.utility.y.a(f2, f3, g2.a.al)) {
                    if (this.bX == l2.bs) {
                        l2.bS.b("Invalid map location (Must be passable by " + g2.a.al.name() + ")");
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void a(s s2, boolean bl2, float f2, float f3) {
        s s3;
        if (s2 instanceof g && (s3 = this.a(s2.N())) != null) {
            g g2 = (g)s3;
            Integer n2 = g2.a.ag;
            if (n2 != null && g2.a.ah == null && n2 < this.x.fQ.length) {
                bn bn2 = this.x.fQ[n2];
                if (bn2.ag > 0.0f) {
                    com.corrodinggames.rts.gameFramework.utility.y.b((am)this, bn2.ag, true);
                }
                com.corrodinggames.rts.gameFramework.utility.y.a((am)this, bn2.ad, true, true);
            }
            if (bl2 && g2.a.am != null) {
                g2.a.am.a(this, f2, f3);
            }
        }
        super.a(s2, bl2, f2, f3);
    }

    public boolean a(s s2, PointF pointF, am am2, int n2, int n3) {
        PointF pointF2 = null;
        am am3 = null;
        int n4 = 0;
        if (n2 > 0) {
            pointF2 = dM;
            am3 = dN;
            n4 = dO;
        }
        dM = pointF;
        dN = am2;
        dO = n3;
        boolean bl2 = this.a(s2, pointF, am2, n2);
        dM = pointF2;
        dN = am3;
        dO = n4;
        return bl2;
    }

    public boolean a(s s2, PointF objectArray, am am2, int n2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (n2 > 10) {
            return false;
        }
        s2.a((am)this, am2);
        if (s2 instanceof g) {
            float f2;
            float f3;
            int n3;
            Object object;
            g g2 = (g)s2;
            d d2 = g2.a;
            if (d2.t != null && !d2.t.read(this)) {
                return true;
            }
            boolean bl2 = false;
            if (d2.ad != null) {
                this.cB += g2.a.ad.floatValue();
                bl2 = true;
            }
            if (d2.ae != null) {
                d2.ae.h(this);
                bl2 = true;
            }
            if (d2.af != null) {
                d2.af.a((am)this, this.bX.E(), true);
                bl2 = true;
            }
            if (d2.aH != null) {
                if (d2.aH.read(this)) {
                    this.bA = l2.by;
                }
                bl2 = true;
            }
            if (d2.ag != null) {
                object = objectArray;
                for (n3 = 0; n3 < d2.ak; ++n3) {
                    if (d2.ah != null) {
                        object = new PointF();
                        if (d2.ai == null) {
                            ((PointF)object).a(this.eo, this.ep);
                        } else {
                            am am3 = d2.ai.readUnit(this);
                            if (am3 != null) {
                                ((PointF)object).a(am3.eo, am3.ep);
                            } else {
                                ((PointF)object).a(this.eo, this.ep);
                            }
                        }
                        float f4 = com.corrodinggames.rts.gameFramework.f.k(this.cg);
                        float f5 = com.corrodinggames.rts.gameFramework.f.j(this.cg);
                        f3 = d2.ah.a;
                        f2 = d2.ah.b;
                        float f6 = f4 * f2 - f5 * f3;
                        float f7 = f5 * f2 + f4 * f3;
                        ((PointF)object).b(f6, f7);
                    }
                    if (object == null) {
                        com.corrodinggames.rts.gameFramework.j.ad.g("completeQueueItem:" + g2.N() + " for fireTurretXAtGround needs points but it is missing");
                        continue;
                    }
                    this.a(null, ((PointF)object).a, ((PointF)object).b, g2.a.ag, g2.a.aj, n2);
                }
                bl2 = true;
            }
            if (d2.as != null) {
                d2.as.a(this.eo, this.ep, this.eq, this.cg, this);
                bl2 = true;
            }
            if (d2.au != null) {
                d2.au.a(this.eo, this.ep, 1.0f);
                bl2 = true;
            }
            if (d2.av != null && !l2.I()) {
                d2.av.a();
                bl2 = true;
            }
            if (d2.aw != null) {
                if (this.bX == l2.bs && !l2.I()) {
                    d2.aw.a();
                }
                bl2 = true;
            }
            if (d2.ac.a > 0) {
                object = d2.ac.a();
                for (n3 = 0; n3 < d2.ac.a; ++n3) {
                    a a2 = (a)object[n3];
                    if (!a2.a(this, s2, (PointF)objectArray, am2, n2)) continue;
                    bl2 = true;
                }
            }
            object = objectArray;
            am am4 = am2;
            if ((d2.ap != null || g2.a.aq != null) && d2.an != null) {
                am4 = d2.an.readUnit(this);
                object = new PointF();
                if (am4 != null) {
                    ((PointF)object).a = am4.eo;
                    ((PointF)object).b = am4.ep;
                } else {
                    ((PointF)object).a = this.eo;
                    ((PointF)object).b = this.ep;
                }
            }
            if (d2.ap != null) {
                if (d2.ao == null || d2.ao.read(this)) {
                    int n4 = 1;
                    if (d2.ar != null && (n4 = (int)d2.ar.readNumber(this)) > 10000) {
                        n4 = 10000;
                    }
                    for (int i2 = 0; i2 < n4; ++i2) {
                        g2.a.ap.a(this, (PointF)object, am4, n2 + 1, i2);
                    }
                }
                bl2 = true;
            }
            if (g2.a.aq != null) {
                if (d2.ao == null || d2.ao.read(this)) {
                    g2.a.aq.a(this, (PointF)object, am4);
                }
                bl2 = true;
            }
            as as2 = null;
            if (d2.H != null) {
                as2 = d2.H.c();
            }
            if (as2 != null) {
                if (com.corrodinggames.rts.gameFramework.l.aw) {
                    String string = this.c() + ": converting unit: " + s2.O();
                    com.corrodinggames.rts.gameFramework.l.b(string);
                }
                if (!(as2 instanceof l)) {
                    am am5 = as2.a();
                    am5.eo = this.eo;
                    am5.ep = this.ep;
                    if (!am5.bI()) {
                        am5.cg = this.cg;
                    }
                    am5.f(this.bX);
                    am5.B(null);
                    f3 = this.cv;
                    f2 = am5.cv;
                    if (f3 == 0.0f) {
                        am5.o(am5.cv);
                    } else {
                        am5.o(this.cu / f3 * f2);
                    }
                    if (this.cG) {
                        com.corrodinggames.rts.gameFramework.l.B().bS.k(am5);
                    }
                    com.corrodinggames.rts.game.n.c(am5);
                    this.ci();
                } else {
                    com.corrodinggames.rts.game.units.custom.h h2 = null;
                    if (d2.Q) {
                        h2 = this.de();
                    }
                    com.corrodinggames.rts.game.n.b((am)this);
                    this.z = null;
                    this.a((l)as2, false, false, d2.R);
                    if (h2 != null) {
                        this.a(h2, true);
                    }
                    this.S();
                    this.dL.e();
                    this.bB = com.corrodinggames.rts.gameFramework.l.B().by;
                    com.corrodinggames.rts.game.n.c(this);
                }
                bl2 = true;
                if (!g2.B().c()) {
                    this.W();
                }
            }
            if (!bl2 && d2.n) {
                com.corrodinggames.rts.gameFramework.l.e("completeQueueItem:" + g2.N() + " had no effect (but should have)");
            }
            return true;
        }
        return false;
    }

    @Override
    public void b(com.corrodinggames.rts.game.units.d.j j2) {
        s s2 = this.a(j2.j);
        if (s2 != null && s2 instanceof g) {
            g g2 = (g)s2;
            d d2 = g2.a;
            if (d2.ab != null) {
                com.corrodinggames.rts.game.units.custom.j.ec.a = this.eo;
                com.corrodinggames.rts.game.units.custom.j.ec.b = this.ep;
                PointF pointF = ec;
                am am2 = null;
                d2.ab.a(this, pointF, am2, 0, 0);
            }
        }
    }

    @Override
    public boolean c(com.corrodinggames.rts.game.units.d.j j2) {
        return true;
    }

    public void i(boolean bl2) {
        this.dL.a(bl2);
    }

    @Override
    public void a(com.corrodinggames.rts.game.units.d.j j2) {
        float f2;
        am am2;
        boolean bl2;
        s s2 = this.a(j2.j);
        if (s2 != null && (bl2 = this.a(s2, j2.h, j2.i, 0, 0))) {
            return;
        }
        float f3 = 0.0f;
        if (this.x.aH && this.x.dk != null) {
            f3 = this.cg + this.x.dk.floatValue();
            f3 += 90.0f;
        }
        if ((am2 = this.dL.a(j2, f2 = this.x.dn != null ? this.x.dn.floatValue() : (this.dL.b != null ? this.cj * 3.0f : this.cj * 2.0f), this.r, f3)) != null) {
            this.F(am2);
            com.corrodinggames.rts.game.n.c(am2);
            this.a(com.corrodinggames.rts.game.units.custom.af.e, am2);
        }
    }

    public void E(am am2) {
        float f2 = 0.0f;
        if (this.x.aH && this.x.dk != null) {
            f2 = this.cg + this.x.dk.floatValue();
            f2 += 90.0f;
        }
        am2.cg = 90.0f + f2;
        float f3 = 70.0f;
        if (this.x.dn != null) {
            f3 = this.x.dn.floatValue();
        }
        this.dL.a(am2, f3, this.r);
    }

    public void F(am am2) {
        am2.eo = this.eo + this.x.di;
        am2.ep = this.ep + this.x.dj;
        if (!this.x.aH) {
            float f2 = 180.0f;
            if (this.x.dk != null) {
                f2 = this.x.dk.floatValue();
            }
            float f3 = 70.0f;
            if (this.x.dn != null) {
                f3 = this.x.dn.floatValue();
            }
            float f4 = 7.0f;
            boolean bl2 = com.corrodinggames.rts.game.units.e.i.a(this, am2, this.r, f4, f2, f3, this.x.di, this.x.dj);
            if (!this.x.dm) {
                am2.eq = this.eq;
            }
            am2.eq += this.x.dl;
            if (am2 instanceof j) {
                ((j)am2).dF();
            }
            if ((this.i() || !bl2 || this.x.eU.read(this)) && this.cr()) {
                this.C(am2);
            }
        }
        this.r = !this.r;
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.d.b by() {
        com.corrodinggames.rts.gameFramework.utility.m m2 = this.dL.g();
        int n2 = m2.size();
        if (n2 == 0) {
            return com.corrodinggames.rts.game.units.custom.d.b.a;
        }
        com.corrodinggames.rts.game.units.custom.d.b b2 = new com.corrodinggames.rts.game.units.custom.d.b();
        Object[] objectArray = m2.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.custom.d.b b3;
            com.corrodinggames.rts.game.units.d.j j2 = (com.corrodinggames.rts.game.units.d.j)objectArray[i2];
            s s2 = this.a(j2.j);
            if (s2 == null || !(s2 instanceof g)) continue;
            g g2 = (g)s2;
            if (g2.a.ad != null) {
                b2.c += g2.a.ad.floatValue();
            }
            if (g2.a.ae != null && !(b3 = g2.a.ae).c()) {
                b2 = com.corrodinggames.rts.game.units.custom.d.b.a(b2, b3);
            }
            if (g2.a.af == null || (b3 = g2.a.af).c()) continue;
            b2 = com.corrodinggames.rts.game.units.custom.d.b.a(b2, b3);
        }
        return b2;
    }

    @Override
    public boolean dA() {
        if (this.x.eM > 0) {
            return this.dI() > this.x.eM;
        }
        return false;
    }

    @Override
    public int h(as as2) {
        return this.dL.a(as2);
    }

    @Override
    public int f(boolean bl2) {
        return this.dL.a(com.corrodinggames.rts.game.units.a.s.i, bl2, true);
    }

    @Override
    public int a(c c2, boolean bl2) {
        return this.dL.a(c2, bl2);
    }

    @Override
    public com.corrodinggames.rts.game.units.d.j dw() {
        return this.dL.b();
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.d.b bD() {
        return this.dL.c();
    }

    @Override
    public com.corrodinggames.rts.gameFramework.utility.m dx() {
        return this.dL.c;
    }

    @Override
    public void dz() {
        this.dL.e = 1.0f;
    }

    @Override
    public boolean dy() {
        return this.dL.a();
    }

    @Override
    public int a(com.corrodinggames.rts.game.units.custom.g g2) {
        return this.dL.a(g2);
    }

    @Override
    public void a(PointF pointF) {
        if (this.x.dc) {
            this.dL.b = pointF;
        }
    }

    @Override
    public float x() {
        if (!this.x.t) {
            return -1.0f;
        }
        return super.x();
    }

    @Override
    public boolean bU() {
        return this.x.u;
    }

    @Override
    public float bV() {
        if (this.bT() && !this.dL.a() && this.x.z) {
            return this.dL.e;
        }
        return super.bV();
    }

    @Override
    public float bW() {
        if (this.y.d > 0.0f && this.cB < this.y.d && this.x.v) {
            return this.cB / this.y.d;
        }
        if (this.cA > 0.0f && this.cx < this.cA && this.x.y) {
            return this.cx / this.cA;
        }
        if (this.y.d == 0.0f && this.cA == 0.0f) {
            if (this.x.em != -1 && this.cL[this.x.em].e > 0.0f) {
                return 1.0f - this.cL[this.x.em].e / this.b(this.x.em);
            }
            if (this.x.en != -1 && this.cL[this.x.en].f != 0.0f) {
                return this.cL[this.x.en].f / this.e(this.x.en);
            }
        }
        return super.bW();
    }

    @Override
    public boolean f(float f2) {
        return super.f(f2);
    }

    @Override
    public void p(float f2) {
        com.corrodinggames.rts.gameFramework.utility.m m2 = this.x.i;
        int n2 = m2.a;
        if (n2 > 0) {
            Object[] objectArray = this.x.i.a();
            for (int i2 = n2 - 1; i2 >= 0; --i2) {
                com.corrodinggames.rts.game.units.custom.b.a a2 = (com.corrodinggames.rts.game.units.custom.b.a)objectArray[i2];
                a2.f(this, f2);
            }
        }
        super.p(f2);
    }

    @Override
    public void e(float f2) {
        super.e(f2);
    }

    @Override
    public void ca() {
        if (this.dL.b != null) {
            com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
            float f2 = (int)(this.eo - l2.cw);
            float f3 = (int)(this.ep - l2.cx);
            float f4 = (int)(this.dL.b.a - l2.cw);
            float f5 = (int)(this.dL.b.b - l2.cx);
            l2.bO.a(f2, f3, f4, f5, com.corrodinggames.rts.game.units.d.i.y);
        }
    }

    @Override
    public void cb() {
        float f2;
        boolean bl2 = true;
        boolean bl3 = false;
        if (this.y.i > 70.0f && this.x.ep && this.x.dK == null || this.x.dK != null && this.x.dK.booleanValue()) {
            f2 = this.m();
            com.corrodinggames.rts.gameFramework.utility.y.a((am)this, f2, bl2);
            bl3 = true;
        } else if (this.x.aH && this.y.o > 50 && !this.x.ep) {
            f2 = this.y.o;
            com.corrodinggames.rts.gameFramework.utility.y.a((am)this, f2, bl2);
            bl3 = true;
        }
        if (this.x.o.size() != 0) {
            for (com.corrodinggames.rts.game.units.custom.y y2 : this.x.o) {
                com.corrodinggames.rts.gameFramework.utility.y.a((am)this, y2.a, bl2);
                bl3 = true;
            }
        }
        if (this.x.bF) {
            int n2 = this.bl();
            for (int i2 = 0; i2 < n2; ++i2) {
                bn bn2 = this.x.fQ[i2];
                if (bn2.ak == null || !(bn2.al > 0.0f)) continue;
                int n3 = 90;
                if (bl3) {
                    n3 = 40;
                }
                com.corrodinggames.rts.gameFramework.utility.y.a(this, bn2.al, Color.a(n3, 35, 235, 35), 1, bl2);
            }
        }
    }

    @Override
    public void d(float f2) {
        super.d(f2);
        if (this.bV) {
            return;
        }
        this.dI.a(f2, this);
        if (this.x.al != null) {
            com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
            int n2 = 0;
            int n3 = 0;
            float f3 = this.eo - l2.cw;
            float f4 = this.ep - l2.cx - this.eq;
            float f5 = this.cD();
            if (f5 != 1.0f) {
                l2.bO.k();
                l2.bO.a(f5, f5, f3, f4);
            }
            if (this.x.am) {
                int n4 = this.x.al.p;
                int n5 = this.x.al.q;
                int n6 = n4 / 2;
                int n7 = n5 / 2;
                du.a(f3 - (float)n6, f4 - (float)n7, f3 + (float)n6, f4 + (float)n7);
                dv.a(n2, n3, n2 + n4, n3 + n5);
            } else {
                du.a(f3 - this.eu, f4 - this.ev, f3 + this.eu, f4 + this.ev);
                dv.a(n2, n3, n2 + this.es, n3 + this.et);
            }
            l2.bO.a(this.x.al, dv, du, this.aN());
            if (f5 != 1.0f) {
                l2.bO.l();
            }
        }
        if (this.x.az && this.dT != null && !this.bV) {
            com.corrodinggames.rts.game.units.custom.b.h.a(this, f2, false, true);
        }
    }

    @Override
    public float aM() {
        return this.x.dH;
    }

    @Override
    public Paint aN() {
        Paint paint = super.aN();
        return paint;
    }

    @Override
    public boolean aV() {
        return this.x.eF;
    }

    @Override
    public boolean bI() {
        return this.x.aH;
    }

    @Override
    public boolean q() {
        if (this.cO != null && this.cO.cm < 1.0f) {
            return true;
        }
        return this.x.aK;
    }

    private boolean H(am am2) {
        if (am2.q()) {
            return false;
        }
        if (am2 == this) {
            return false;
        }
        return am2.bI() ? this.x.aU : this.x.aV;
    }

    @Override
    public boolean a(am am2) {
        if (this.x.fo != null && !com.corrodinggames.rts.game.units.custom.g.a(this.x.fo, am2.de())) {
            return false;
        }
        return this.H(am2);
    }

    @Override
    public boolean l(am am2) {
        if (am2.g() != 0.0f && this.h(am2, true)) {
            return true;
        }
        if (this.x.fn != null && !com.corrodinggames.rts.game.units.custom.g.a(this.x.fn, am2.de())) {
            return false;
        }
        return this.H(am2);
    }

    @Override
    public void m(am am2) {
        if (this.x.bi) {
            com.corrodinggames.rts.gameFramework.ab ab2;
            au au2 = this.ar();
            if (au2 != null && (ab2 = au2.i) != null) {
                ab2.a(au2);
            }
            if (this.cG && am2 != null) {
                com.corrodinggames.rts.gameFramework.l.B().bS.k(am2);
            }
            this.ci();
        }
    }

    @Override
    public boolean al() {
        return this.x.bi;
    }

    @Override
    public boolean aj() {
        return this.x.fq;
    }

    @Override
    public boolean cu() {
        return this.x.fN;
    }

    @Override
    public boolean ak() {
        return this.x.fp;
    }

    @Override
    public boolean g(am am2, boolean bl2) {
        if (!this.h(am2, bl2)) {
            return false;
        }
        return !bl2 || !am2.c(this);
    }

    @Override
    public boolean h(am am2, boolean bl2) {
        if (!this.x.fk) {
            return false;
        }
        return this.x.fl == null || com.corrodinggames.rts.game.units.custom.g.a(this.x.fl, am2.de());
    }

    @Override
    public int cS() {
        return this.x.fm;
    }

    @Override
    public boolean bJ() {
        return this.x.fu;
    }

    @Override
    public void a(float f2, boolean bl2) {
        super.a(f2, bl2);
        if (!this.bV && this.ak()) {
            if (this.Y()) {
                if (!this.x.bW) {
                    com.corrodinggames.rts.game.units.e.b.b(f2, this);
                }
            } else if (!this.x.bS) {
                com.corrodinggames.rts.game.units.e.b.b(f2, this);
            }
        }
    }

    @Override
    public boolean o() {
        return this.x.cy;
    }

    @Override
    public boolean p() {
        return this.x.cz;
    }

    @Override
    public boolean cO() {
        return this.x.cA;
    }

    @Override
    public void f(n n2) {
        if (this.x.cE) {
            this.b(com.corrodinggames.rts.game.n.h);
            return;
        }
        if (this.x.cD && this.B.size() == 0) {
            this.b(com.corrodinggames.rts.game.n.i);
            return;
        }
        super.f(n2);
    }

    @Override
    public void B(am am2) {
        super.B(am2);
    }

    @Override
    public float g() {
        return this.x.cF;
    }

    @Override
    public int cQ() {
        return this.x.cG;
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.h cR() {
        return this.x.cH;
    }

    @Override
    public void cP() {
        if (this.x.bh == 0.0f && this.g() > 0.0f) {
            com.corrodinggames.rts.game.n.b((am)this);
            this.cm = 1.0f;
            com.corrodinggames.rts.game.n.c(this);
        }
    }

    @Override
    public c cp() {
        if (this.x.eM != 0) {
            return com.corrodinggames.rts.game.units.e.i.i.N();
        }
        return super.cp();
    }

    @Override
    public float L(int n2) {
        return this.x.fQ[n2].af;
    }

    @Override
    public PointF K(int n2) {
        PointF pointF = super.K(n2);
        if (this.x.eA) {
            com.corrodinggames.rts.game.f f2;
            bn bn2 = this.x.fQ[n2];
            bh bh2 = this.x.fR[bn2.a(this)];
            if (bh2.M && this.G != null && (f2 = this.G[n2]) != null && !f2.ej) {
                pointF.a += f2.K;
                pointF.b += f2.L;
            }
        }
        return pointF;
    }

    @Override
    public float bd() {
        return this.y.d;
    }

    @Override
    public b be() {
        return this.x.ec;
    }

    @Override
    public boolean bf() {
        if (this.x.ef) {
            return false;
        }
        if (this.x.ec == com.corrodinggames.rts.game.units.b.d) {
            au au2 = this.ar();
            if (au2 != null && (au2.d() == com.corrodinggames.rts.game.units.av.h || au2.d() == com.corrodinggames.rts.game.units.av.j)) {
                return true;
            }
            return this.P == com.corrodinggames.rts.game.units.a.a;
        }
        return true;
    }

    @Override
    public boolean bX() {
        return this.v;
    }

    @Override
    public boolean bg() {
        return this.x.ei;
    }

    @Override
    public float bc() {
        return this.x.el;
    }

    @Override
    public void f(float f2, float f3) {
        super.f(f2, f3);
        this.a(com.corrodinggames.rts.game.units.custom.af.h);
        float f4 = this.cg;
        if (this.x.dE) {
            f4 = this.cL[this.x.dG].a;
        }
        this.dP = this.eo;
        this.dQ = this.ep;
        this.dR = this.eq;
        this.dS = f4;
    }

    public void du() {
        if (this.x.ax == null && this.dT == null) {
            return;
        }
        if (this.x.ax == null || this.x.ax.length == 0) {
            this.dT = null;
            return;
        }
        if (this.dT != null && this.dT.length == this.x.ax.length) {
            return;
        }
        this.dT = new com.corrodinggames.rts.game.units.custom.b.i[this.x.ax.length];
        for (int i2 = 0; i2 < this.x.ax.length; ++i2) {
            com.corrodinggames.rts.game.units.custom.b.i i3;
            this.dT[i2] = i3 = new com.corrodinggames.rts.game.units.custom.b.i();
            i3.a = i2;
            i3.s = this.x.ax[i2].r;
        }
        float f2 = this.cg;
        if (this.x.dE) {
            f2 = this.cL[this.x.dG].a;
        }
        this.dP = this.eo;
        this.dQ = this.ep;
        this.dR = this.eq;
        this.dS = f2;
        this.dv();
        for (int i4 = 0; i4 < this.x.ax.length; ++i4) {
            this.dT[i4].m = true;
        }
    }

    public void dv() {
        com.corrodinggames.rts.game.units.custom.b.h.a.b(this, 0.0f);
    }

    public void dB() {
        if (this.dT != null) {
            for (int i2 = 0; i2 < this.dT.length; ++i2) {
                this.dT[i2].n = true;
                this.dT[i2].m = true;
            }
            this.dv();
        }
    }

    @Override
    public int aT() {
        if (this.x.fV == null) {
            return -1;
        }
        return this.x.fV.e;
    }

    @Override
    public int s() {
        return this.y.n;
    }

    @Override
    public void c(boolean bl2) {
        l l2 = this.x;
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.cN != null || this.cO != null) {
            return;
        }
        int n2 = this.y.n;
        if (this.cm < 1.0f && l2.dh != -1) {
            n2 = l2.dh;
        }
        if (n2 > 0) {
            l3.bL.a(this.eo, this.ep, n2, this.bX, bl2);
        }
    }

    @Override
    public Rect cc() {
        return this.x.cX;
    }

    @Override
    public Rect ce() {
        return this.x.cZ;
    }

    @Override
    public Rect cd() {
        return this.x.cY;
    }

    @Override
    public boolean b(int n2, float f2) {
        bn bn2 = this.x.fQ[n2];
        if (bn2.as != 0.0f) {
            boolean bl2 = true;
            if (bn2.av != null && !bn2.av.read(this)) {
                bl2 = false;
            }
            if (bl2) {
                float f3;
                ap ap2 = this.cL[n2];
                float f4 = bn2.ar != 0.0f ? ap2.a : (!bn2.aq ? ap2.b : this.C(n2));
                ap2.k += f2;
                float f5 = f2 * bn2.au;
                if (ap2.l > 0.0f) {
                    if (ap2.l < Float.POSITIVE_INFINITY && (f3 = this.a(f5, f4 + ap2.l, n2)) == 0.0f) {
                        ap2.l = Float.POSITIVE_INFINITY;
                    }
                } else if (ap2.l > Float.NEGATIVE_INFINITY && (f3 = this.a(f5, f4 + ap2.l, n2)) == 0.0f) {
                    ap2.l = Float.NEGATIVE_INFINITY;
                }
                if (ap2.k > bn2.at) {
                    ap2.k = -com.corrodinggames.rts.gameFramework.f.a(this, 0, (int)bn2.aw);
                    f3 = bn2.as;
                    if (bn2.ax > 0.0f) {
                        f3 += com.corrodinggames.rts.gameFramework.f.b(this, 0.0f, bn2.ax, n2);
                    }
                    ap2.l = ap2.l > 0.0f ? -f3 : f3;
                }
                return false;
            }
        }
        if (bn2.ar != 0.0f) {
            this.cL[n2].a += bn2.ar * f2;
            if (this.cL[n2].a > 180.0f) {
                this.cL[n2].a -= 360.0f;
            }
            if (this.cL[n2].a < -180.0f) {
                this.cL[n2].a += 360.0f;
            }
            return false;
        }
        return bn2.aq;
    }

    @Override
    public int cw() {
        return this.x.eZ;
    }

    public ArrayList dC() {
        dU.clear();
        ArrayList arrayList = this.N();
        if (arrayList.size() != 0) {
            for (s s2 : arrayList) {
                if (!(s2 instanceof g)) continue;
                g g2 = (g)s2;
                if (g2.c != com.corrodinggames.rts.game.units.custom.a.e.c) continue;
                dU.add(g2);
            }
        }
        return dU;
    }

    @Override
    public c cm() {
        ArrayList arrayList = this.dC();
        if (arrayList.size() > 0) {
            return ((s)arrayList.get(0)).N();
        }
        return null;
    }

    @Override
    public void a(ArrayList arrayList) {
        arrayList.clear();
        ArrayList arrayList2 = this.dC();
        if (arrayList2.size() < 2) {
            return;
        }
        arrayList2.remove(0);
        for (s s2 : arrayList2) {
            arrayList.add(s2.N());
        }
    }

    @Override
    public float cZ() {
        return this.x.da;
    }

    @Override
    public float da() {
        return this.x.db;
    }

    @Override
    public void bv() {
        com.corrodinggames.rts.game.n.a(this);
        this.dL.a(true);
        super.bv();
    }

    @Override
    public void dc() {
        this.eq = 170.0f;
        this.f = 1.5f;
        this.dB();
        super.dc();
    }

    @Override
    public boolean dd() {
        return this.x.cm;
    }

    @Override
    public int bp() {
        return this.x.bq;
    }

    @Override
    public void a(int n2, float f2) {
        this.cL[n2].a += f2;
        if (this.x.fU) {
            for (int i2 = 0; i2 < this.x.fQ.length; ++i2) {
                bn bn2 = this.x.fQ[i2];
                if (bn2.w != n2) continue;
                this.cL[i2].a += f2;
                this.cL[i2].a(2);
            }
        }
    }

    @Override
    public float db() {
        return super.db() + (float)this.x.dg;
    }

    @Override
    public float H(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.p;
    }

    @Override
    public float I(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.q;
    }

    @Override
    public float J(int n2) {
        bn bn2 = this.x.fQ[n2];
        return bn2.r;
    }

    @Override
    public float a(am am2, float f2, com.corrodinggames.rts.game.f f3) {
        com.corrodinggames.rts.game.units.custom.b.n n2 = this.dn();
        if (n2 != null && this.cO != null && n2.j) {
            int n3 = 0;
            if (f3 != null) {
                n3 = f3.aD;
            }
            if (n3 >= 0) {
                float f4;
                com.corrodinggames.rts.game.f f5 = com.corrodinggames.rts.game.f.a(f3);
                if (n2.k) {
                    f5.am = 0.0f;
                }
                if ((f2 = (f4 = this.cO.a(am2, f2, f5))) < 0.0f) {
                    f2 = 0.0f;
                }
            }
        }
        if (this.J()) {
            f2 = 0.0f;
        }
        if (this.y.l > 0.0f && f2 > this.x.cN) {
            float f6 = this.y.l;
            if (f3 != null) {
                f6 -= f3.an;
            }
            if (f6 < 0.0f) {
                f6 = 0.0f;
            }
            if ((f2 -= f6) < this.x.cN) {
                f2 = this.x.cN;
            }
        }
        if (f3 != null) {
            this.a(com.corrodinggames.rts.game.units.custom.af.n, am2, f3.aE, null);
        } else {
            this.a(com.corrodinggames.rts.game.units.custom.af.n, am2);
        }
        return super.a(am2, f2, f3);
    }

    @Override
    public float aU() {
        return this.x.dJ;
    }

    @Override
    public boolean ac() {
        if (!this.x.eh) {
            return false;
        }
        return super.ac();
    }

    @Override
    public boolean a(ag ag2) {
        if (ag2 == com.corrodinggames.rts.game.units.ag.a) {
            return this.x.do.a();
        }
        if (ag2 == com.corrodinggames.rts.game.units.ag.b) {
            return this.x.dp.a();
        }
        if (ag2 == com.corrodinggames.rts.game.units.ag.c) {
            return this.x.dq.a();
        }
        return false;
    }

    public void b(af af2) {
        l l2 = this.x;
        if (l2.gq.a == 0) {
            return;
        }
        Object[] objectArray = l2.gq.a();
        for (int i2 = l2.gq.a - 1; i2 >= 0; --i2) {
            ae ae2 = (ae)objectArray[i2];
            if (ae2.a != af2) continue;
            com.corrodinggames.rts.game.units.custom.j.ec.a = this.eo;
            com.corrodinggames.rts.game.units.custom.j.ec.b = this.ep;
            PointF pointF = ec;
            am am2 = null;
            this.a(ae2.b, pointF, am2, 0, 0);
        }
    }

    @Override
    public void a(af af2, am am2, com.corrodinggames.rts.game.units.custom.h h2, VariableScope variableScope) {
        l l2 = this.x;
        if (l2.gq.a == 0) {
            return;
        }
        Object[] objectArray = l2.gq.a();
        for (int i2 = l2.gq.a - 1; i2 >= 0; --i2) {
            ae ae2 = (ae)objectArray[i2];
            if (ae2.a != af2 || ae2.d != null && !com.corrodinggames.rts.game.units.custom.g.a(ae2.d, h2)) continue;
            k k2 = dX.size() > 0 ? (k)dX.b() : new k();
            k2.a = ae2;
            k2.b = this;
            k2.c = am2;
            k2.d = h2;
            k2.e = variableScope;
            dW.add(k2);
        }
    }

    public static void s(float f2) {
    }

    public static void dD() {
        if (com.corrodinggames.rts.game.units.custom.j.dW.a == 0) {
            return;
        }
        dW = new com.corrodinggames.rts.gameFramework.utility.m();
    }

    public static void a(float f2, int n2) {
        Object object;
        int n3;
        if (com.corrodinggames.rts.game.units.custom.j.dW.a == 0) {
            return;
        }
        for (int i2 = 0; i2 < 105 && (n3 = com.corrodinggames.rts.game.units.custom.j.dW.a) != 0; ++i2) {
            Object object2;
            Object object3;
            Object object4;
            Object object5;
            int n4;
            object = dW.a();
            for (n4 = n3 - 1; n4 >= 0; --n4) {
                object5 = (k)object[n4];
                object4 = ((k)object5).a;
                object3 = ((k)object5).b;
                if (((ae)object4).c != ((j)object3).x) continue;
                com.corrodinggames.rts.game.units.custom.j.ec.a = ((j)object3).eo;
                com.corrodinggames.rts.game.units.custom.j.ec.b = ((j)object3).ep;
                object2 = ec;
                am am2 = null;
                LogicBoolean.setContextEventSource((k)object5);
                ((j)object3).a(((ae)object4).b, (PointF)object2, am2, 0, 0);
                LogicBoolean.clearContext();
            }
            if (i2 < 105) {
                int n5;
                if (n3 == com.corrodinggames.rts.game.units.custom.j.dW.a) break;
                object = dW.a();
                n4 = 0;
                for (n5 = n3; n5 < com.corrodinggames.rts.game.units.custom.j.dW.a; ++n5) {
                    object4 = (k)object[n5];
                    object3 = ((k)object4).a;
                    if (i2 >= ((ae)object3).e) continue;
                    ++n4;
                }
                if (n4 <= 0) break;
                dV.clear();
                for (n5 = 0; n5 < com.corrodinggames.rts.game.units.custom.j.dW.a; ++n5) {
                    object4 = (k)object[n5];
                    boolean bl2 = true;
                    if (n5 < n3) {
                        bl2 = false;
                    } else {
                        object2 = ((k)object4).a;
                        if (i2 >= ((ae)object2).e) {
                            bl2 = false;
                        }
                    }
                    if (!bl2) {
                        ((k)object4).a();
                        dX.add(object4);
                        continue;
                    }
                    dV.add(object4);
                }
                dW.clear();
                object5 = dV;
                dV = dW;
                dW = object5;
                if (n4 == com.corrodinggames.rts.game.units.custom.j.dW.a) continue;
                com.corrodinggames.rts.gameFramework.l.e("processAllQueuedEvents: " + n4 + "!=" + com.corrodinggames.rts.game.units.custom.j.dW.a);
                continue;
            }
            com.corrodinggames.rts.gameFramework.l.e("processAllQueuedEvents: recursion limit reached");
            break;
        }
        Object[] objectArray = dW.a();
        for (n3 = com.corrodinggames.rts.game.units.custom.j.dW.a - 1; n3 >= 0; --n3) {
            object = (k)objectArray[n3];
            ((k)object).a();
            dX.add(object);
        }
        dW.clear();
    }

    public static void dE() {
    }

    public void b(float f2, boolean bl2) {
        r[] rArray;
        r[] rArray2;
        r[] rArray3;
        l l2 = this.x;
        if (!l2.fX) {
            return;
        }
        if (this.w != 0.0f) {
            this.w = com.corrodinggames.rts.gameFramework.f.a(this.w, f2);
            if (this.w == 0.0f) {
                bl2 = true;
            } else {
                return;
            }
        }
        if ((rArray3 = l2.fY) != null) {
            this.a(f2, rArray3);
            if (l2 != this.x) {
                return;
            }
        }
        if ((rArray2 = l2.fZ) != null) {
            rArray = com.corrodinggames.rts.gameFramework.l.B();
            int n2 = (int)((long)rArray.bx + this.eh) % 4;
            if (n2 == 0 || bl2) {
                this.a(f2, rArray2);
                if (l2 != this.x) {
                    return;
                }
            }
        }
        if ((rArray = l2.ga) != null) {
            com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
            int n3 = (int)((long)l3.bx + this.eh) % 8;
            if (n3 == 0 || bl2) {
                this.a(f2, rArray);
                if (l2 != this.x) {
                    return;
                }
            }
        }
    }

    public void a(float f2, r[] rArray) {
        l l2 = this.x;
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl2 = false;
        for (int i2 = 0; i2 < rArray.length; ++i2) {
            String string;
            Object object;
            r r2 = rArray[i2];
            long l4 = 0L;
            boolean bl3 = r2.a.read(this);
            if (!bl3) continue;
            if (l3.bl && l3.bn && this.cG) {
                object = null;
                if (r2.d != null) {
                    object = "" + r2.d.O();
                }
                string = "autoTrigger fired on: " + this.cB() + " details: " + r2.a.getDebugDetails(this);
                com.corrodinggames.rts.gameFramework.l.e(string);
                l3.bS.i.a(string, 2000);
            }
            com.corrodinggames.rts.game.units.custom.j.ec.a = this.eo;
            com.corrodinggames.rts.game.units.custom.j.ec.b = this.ep;
            object = ec;
            string = null;
            long l5 = 0L;
            this.a(r2.d, (PointF)object, (am)((Object)string), 0, 0);
            this.w = this.x.ca;
            if (l2 == this.x) continue;
            return;
        }
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.h de() {
        return this.ed;
    }

    public void a(com.corrodinggames.rts.game.units.custom.h h2, boolean bl2) {
        if (bl2) {
            this.ed = h2;
            return;
        }
        com.corrodinggames.rts.game.n.b((am)this);
        this.ed = h2;
        com.corrodinggames.rts.game.n.c(this);
    }

    public void j(boolean bl2) {
        this.a(this.x.O, bl2);
    }

    public void a(com.corrodinggames.rts.game.units.custom.h h2) {
        com.corrodinggames.rts.game.units.custom.h h3 = this.de();
        if (h3 == null || h3.b() == 0) {
            this.a(h2, false);
            return;
        }
        if (com.corrodinggames.rts.game.units.custom.g.b(h3, h2)) {
            return;
        }
        i i2 = new i(h3);
        if (i2.a(h2)) {
            this.a(i2.a(), false);
            return;
        }
    }

    public void b(com.corrodinggames.rts.game.units.custom.h h2) {
        com.corrodinggames.rts.game.units.custom.h h3 = this.de();
        if (h3 == null || h3.b() == 0) {
            return;
        }
        if (!com.corrodinggames.rts.game.units.custom.g.a(h2, h3)) {
            return;
        }
        i i2 = new i(h3);
        if (i2.b(h2)) {
            this.a(i2.a(), false);
            return;
        }
    }

    public final void dF() {
        if (this.x.fg == com.corrodinggames.rts.game.units.ao.d) {
            if (this.i()) {
                this.S(5);
            } else if (this.cr() && this.cl == 0.0f) {
                this.S(3);
            } else {
                this.S(2);
            }
        } else if (this.cl == 0.0f) {
            this.S(this.x.cI);
        } else {
            this.S(2);
        }
        this.en = 0;
    }

    @Override
    public boolean ck() {
        return this.x.gi;
    }

    @Override
    public boolean f() {
        return this.x.fd.read(this);
    }

    @Override
    public boolean j() {
        return true;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.utility.ai D(int n2) {
        ap ap2 = this.cL[n2];
        bn bn2 = this.x.fQ[n2];
        float f2 = bn2.X;
        float f3 = bn2.Y;
        if (bn2.Z != 0.0f && ap2.m) {
            f3 += bn2.Z;
        }
        float f4 = this.E() ? this.cg : ap2.a;
        com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.F(n2);
        float f5 = com.corrodinggames.rts.gameFramework.f.k(f4);
        float f6 = com.corrodinggames.rts.gameFramework.f.j(f4);
        float f7 = ai2.a;
        float f8 = ai2.b;
        float f9 = ai2.c;
        com.corrodinggames.rts.game.units.custom.j.ee.a = f7 += f5 * f2 - f6 * f3;
        com.corrodinggames.rts.game.units.custom.j.ee.b = f8 += f6 * f2 + f5 * f3;
        com.corrodinggames.rts.game.units.custom.j.ee.c = f9 + bn2.aa;
        return ee;
    }

    @Override
    public PointF E(int n2) {
        com.corrodinggames.rts.gameFramework.utility.ai ai2 = this.D(n2);
        com.corrodinggames.rts.game.units.custom.j.ef.a = ai2.a;
        com.corrodinggames.rts.game.units.custom.j.ef.b = ai2.b;
        return ef;
    }

    @Override
    public boolean cl() {
        return this.x.fJ;
    }

    @Override
    public float cn() {
        return this.x.fK;
    }

    @Override
    public void a(am am2, float f2, int n2) {
        if (this.x.dz != null) {
            this.b.a(this.x.dz, 5);
        }
        if (this.x.bS) {
            this.U = com.corrodinggames.rts.gameFramework.f.a(this.U, f2);
            if (this.U == 0.0f) {
                this.U = this.x.bT;
                if (this.x.bU != null) {
                    ap ap2 = this.cL[n2];
                    PointF pointF = this.E(n2);
                    this.x.bU.a(pointF.a, pointF.b, this.eq, ap2.a, this);
                }
                if (this.x.bV != null) {
                    this.x.bV.a(am2.eo, am2.ep, am2.eq, am2.cg, am2);
                }
            }
        } else {
            super.a(am2, f2, n2);
        }
    }

    @Override
    public void b(am am2, float f2, int n2) {
        if (this.x.dA != null) {
            this.b.a(this.x.dA, 5);
        }
        if (this.x.bW) {
            this.U = com.corrodinggames.rts.gameFramework.f.a(this.U, f2);
            if (this.U == 0.0f) {
                this.U = this.x.bX;
                if (this.x.bY != null) {
                    ap ap2 = this.cL[n2];
                    PointF pointF = this.E(n2);
                    this.x.bY.a(pointF.a, pointF.b, this.eq, ap2.a, this);
                }
                if (this.x.bZ != null) {
                    this.x.bZ.a(am2.eo, am2.ep, am2.eq, am2.cg, am2);
                }
            }
        } else {
            super.b(am2, f2, n2);
        }
    }

    @Override
    public boolean cg() {
        return this.y.m;
    }

    public boolean dG() {
        Object object;
        if (this.x.Z != null && !this.x.Z.read(this)) {
            return false;
        }
        if (!this.y.m) {
            object = com.corrodinggames.rts.gameFramework.l.B();
            if (((com.corrodinggames.rts.gameFramework.l)object).bs.c(this.bX) && !((com.corrodinggames.rts.gameFramework.l)object).bs.b()) {
                return false;
            }
        }
        return (object = this.dn()) == null || ((com.corrodinggames.rts.game.units.custom.b.n)object).o;
    }

    @Override
    public boolean t() {
        com.corrodinggames.rts.game.units.custom.b.n n2 = this.dn();
        if (n2 != null && n2.m) {
            return true;
        }
        return this.x.aM;
    }

    @Override
    public boolean cV() {
        com.corrodinggames.rts.game.units.custom.b.n n2 = this.dn();
        if (n2 != null && n2.n) {
            return true;
        }
        return this.x.aN;
    }

    @Override
    public boolean d(am am2) {
        l l2 = this.x;
        if (l2.aS != null && !com.corrodinggames.rts.game.units.custom.g.a(l2.aS, am2.de())) {
            return false;
        }
        if (this.dH()) {
            return false;
        }
        return !l2.aO;
    }

    @Override
    public boolean cW() {
        return this.x.aT;
    }

    @Override
    public boolean cT() {
        if (this.x.aO) {
            return true;
        }
        return this.u() || this.cm < 1.0f && this.x.bh <= 0.0f;
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.h dh() {
        return this.x.P;
    }

    @Override
    public float am() {
        return this.x.eG;
    }

    @Override
    public boolean an() {
        return super.an() || this.x.eF;
    }

    @Override
    public boolean a(com.corrodinggames.rts.gameFramework.l l2) {
        if (!l2.cO.b(this.eo, this.ep)) {
            if (!this.x.B) {
                return false;
            }
            boolean bl2 = false;
            if (this.x.C != null) {
                com.corrodinggames.rts.game.units.custom.l.a.a(this.x.C);
                com.corrodinggames.rts.game.units.custom.l.a.a((int)this.eo, (int)this.ep);
                if (l2.cQ.b(com.corrodinggames.rts.game.units.custom.l.a)) {
                    bl2 = true;
                }
            }
            if (l2.cO.b(this.eo, this.ep - this.eq)) {
                bl2 = true;
            }
            if (!bl2) {
                return false;
            }
        }
        if (this.cN != null) {
            return false;
        }
        if (this.cP != null && this.cP.I) {
            return false;
        }
        if (!this.d(l2.bs)) {
            return false;
        }
        return this.dG();
    }

    public y a(com.corrodinggames.rts.game.units.custom.b.n n2) {
        y y2 = com.corrodinggames.rts.game.units.custom.b.m.a(this, n2);
        return y2;
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.b.n a(short s2) {
        com.corrodinggames.rts.game.units.custom.b.n n2 = com.corrodinggames.rts.game.units.custom.b.m.a(this, s2);
        return n2;
    }

    @Override
    public boolean a(y y2, com.corrodinggames.rts.game.units.custom.b.n n2) {
        if (y2 == this) {
            return false;
        }
        if (n2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("attachRequest: No attachedSlotData");
            return false;
        }
        y y3 = com.corrodinggames.rts.game.units.custom.b.m.a(this, n2);
        if (y3 != null) {
            com.corrodinggames.rts.gameFramework.l.b("attachRequest: a unit is already in slot (parent:" + this.cB() + " slot:" + n2.b() + " existing:" + y3.cC() + ")");
            return false;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.game.units.custom.b.m.a(this, n2, y2);
        y2.cO = this;
        y2.cP = n2;
        y2.cQ = l2.by;
        y2.bT = false;
        return true;
    }

    @Override
    public boolean b(y y2) {
        if (y2.cO != this) {
            com.corrodinggames.rts.gameFramework.l.b("deattachRequest: unit is not attached");
            return false;
        }
        com.corrodinggames.rts.game.units.custom.b.n n2 = y2.cP;
        if (n2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("deattachRequest: unit has no attachedSlotData");
            return false;
        }
        y y3 = com.corrodinggames.rts.game.units.custom.b.m.a(this, n2);
        if (y3 == null) {
            com.corrodinggames.rts.gameFramework.l.b("deattachRequest: Failed, no unit in slot");
            com.corrodinggames.rts.gameFramework.l.g("deattachRequest");
            return false;
        }
        if (y2 != y3) {
            String string = "null";
            if (y3 != null) {
                string = y3.cB();
            }
            com.corrodinggames.rts.gameFramework.l.b("deattachRequest: unit and slot don't match - requested:" + y2.cB() + " current:" + string);
            return false;
        }
        if (this.B.remove(y2)) {
            this.D(y2);
        }
        com.corrodinggames.rts.game.units.custom.b.m.a(this, n2, null);
        y2.cO = null;
        y2.cP = null;
        y2.f_();
        this.a(com.corrodinggames.rts.game.units.custom.af.r, this);
        return true;
    }

    public boolean dH() {
        com.corrodinggames.rts.game.units.custom.b.n n2 = this.dn();
        return n2 != null && !n2.l;
    }

    @Override
    public boolean J() {
        if (this.dH()) {
            return true;
        }
        return this.x.aP;
    }

    @Override
    public void di() {
        l l2 = this.x;
        if (!l2.cv.c()) {
            l2.cv.a(this);
        }
        if (!l2.cw.c() && this.cm >= 1.0f) {
            l2.cw.a(this);
        }
        super.di();
    }

    @Override
    public void dj() {
        l l2 = this.x;
        if (!l2.cv.c()) {
            l2.cv.h(this);
        }
        if (!l2.cw.c() && this.cm >= 1.0f) {
            l2.cw.h(this);
        }
        super.dj();
    }

    @Override
    public void a(au au2) {
        av av2;
        this.a(com.corrodinggames.rts.game.units.custom.af.j);
        com.corrodinggames.rts.game.units.custom.b.n n2 = this.dn();
        if (n2 != null && n2.H && ((av2 = au2.d()) == com.corrodinggames.rts.game.units.av.h || av2 == com.corrodinggames.rts.game.units.av.a)) {
            this.bx();
        }
    }

    @Override
    public boolean c_() {
        l l2 = this.x;
        if (!l2.aR) {
            com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
            if (l3.bs.c(this.bX)) {
                return false;
            }
        }
        return l2.aQ;
    }

    @Override
    public boolean dk() {
        return this.x.aa;
    }

    @Override
    public boolean dl() {
        return this.x.eK;
    }

    @Override
    public boolean dm() {
        return this.x.eL;
    }

    public boolean G(am am2) {
        int n2 = this.dI();
        int n3 = am2.cw();
        if (this.x.eO) {
            n3 = 1;
        }
        return n2 + n3 <= this.x.eM;
    }

    public int dI() {
        int n2 = 0;
        if (this.x.eO) {
            n2 += this.B.size();
        } else if (this.B.a > 0) {
            for (am am2 : this.B) {
                n2 += am2.cw();
            }
        }
        return n2;
    }

    @Override
    public void e(n n2) {
        if (this.bX == n2) {
            return;
        }
        super.e(n2);
        if (this.B != null && !this.x.eX) {
            for (am am2 : this.B) {
                am2.e(n2);
            }
        }
        if (this.C != null) {
            for (am am2 : this.C) {
                com.corrodinggames.rts.game.units.custom.b.n n3;
                if (am2 == null || (n3 = am2.dn()) == null || n3.z) continue;
                am2.e(n2);
            }
        }
        this.a(com.corrodinggames.rts.game.units.custom.af.k);
    }

    @Override
    public com.corrodinggames.rts.game.units.custom.d.b cN() {
        return this.x.ci;
    }

    @Override
    public void ch() {
        if (this.x.bs) {
            if (this.cu <= -1.0f) {
                this.cu = -1.0f;
            }
            return;
        }
        super.ch();
    }

    @Override
    public com.corrodinggames.rts.gameFramework.utility.m bz() {
        return this.B;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.utility.m e(boolean bl2) {
        this.eg.clear();
        if (this.x.aA.a > 0) {
            com.corrodinggames.rts.game.units.custom.b.m.a(this, this.eg, bl2);
        }
        if (this.eg.a > 0) {
            return this.eg;
        }
        return null;
    }

    @Override
    public float do() {
        return this.x.de;
    }

    @Override
    public boolean dp() {
        return this.x.A;
    }

    public void dJ() {
        if (this.y.a) {
            this.y = this.y.b();
        }
    }

    @Override
    public /* synthetic */ as r() {
        return this.dt();
    }

    static {
        dU = new ArrayList();
        dV = new com.corrodinggames.rts.gameFramework.utility.m();
        dW = new com.corrodinggames.rts.gameFramework.utility.m();
        dX = new com.corrodinggames.rts.gameFramework.utility.m();
        for (int i2 = 0; i2 < 10; ++i2) {
            dX.add(new k());
        }
        dY = true;
        dZ = new HashMap();
        ea = 0;
        eb = "";
        ec = new PointF();
        ee = new com.corrodinggames.rts.gameFramework.utility.ai();
        ef = new PointF();
    }
}
