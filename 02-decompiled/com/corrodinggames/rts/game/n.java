/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import android.graphics.Color;
import android.graphics.Paint;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.d;
import com.corrodinggames.rts.game.o;
import com.corrodinggames.rts.game.p;
import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.r;
import com.corrodinggames.rts.game.s;
import com.corrodinggames.rts.game.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.e.c;
import com.corrodinggames.rts.game.units.custom.e.f;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.i;
import com.corrodinggames.rts.game.units.d.e;
import com.corrodinggames.rts.game.units.t;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.bs;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public strictfp abstract class n
extends bq
implements Comparable {
    static m a = new m();
    static n[] b = new n[0];
    public static int c = 10;
    public static int d = 0;
    public static int e = 100;
    public static int f = c + d;
    public static final n g = new com.corrodinggames.rts.game.e(-1, false, "<blank>");
    public static final n h = new d(-2);
    public static final n i = new d(-1);
    private static n[] as = new n[f];
    public static n j = new u(-99);
    public int k = -1;
    public final String l = "Note to modifiers: Changing credits will not allow you to cheat in multiplayer games, but it will only break sync";
    public boolean m;
    public boolean n;
    public double o = 4000.0;
    public double p = 0.0;
    public int q = 0;
    public int r;
    public y s = com.corrodinggames.rts.game.units.t.a(this);
    public y t = com.corrodinggames.rts.game.units.t.a(this);
    public boolean u = false;
    public String v;
    public boolean w;
    public int x;
    public boolean y;
    public Integer z;
    public Integer A;
    public Integer B;
    public Integer C;
    public int D = -1;
    private boolean at;
    private int au = -9999;
    public boolean E;
    private int av = -9999;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public final Object K = new Object();
    public int L;
    public int M;
    public byte[][] N;
    public String O;
    public String P;
    public int Q;
    public int R;
    public boolean S = true;
    public s T = new s();
    public boolean U;
    public byte V;
    public int W = -1;
    public long X = -1L;
    public long Y = -1L;
    public int Z = -1;
    public boolean aa;
    public boolean ab;
    public int ac = 0;
    int ad;
    public Paint ae = new ag();
    public Paint af = new ag();
    static int[] ag = new int[10];
    static String[] ah = new String[10];
    int ai = -2;
    static int aj = -99;
    h ak = com.corrodinggames.rts.game.units.custom.g.d;
    f al = new f();
    public c am = new c();
    public float an;
    public static float ao = 40.0f;
    public static float ap = 10.0f;
    long aq = -9999L;
    double ar;

    public int a(n n2) {
        int n3 = this.ac - n2.ac;
        if (n3 != 0) {
            return n3;
        }
        int n4 = this.k - n2.k;
        if (n4 != 0) {
            return n4;
        }
        if (this.v != null && n2.v != null) {
            return this.v.compareTo(n2.v);
        }
        return 0;
    }

    public void b(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.c(this.k);
        as2.a((int)this.o);
        as2.a(this.r);
        as2.b(this.v);
        as2.a(this.U);
        if (as2.g() > 26) {
            as2.a(this.A());
            as2.d("lastPingTimeReceivedAt");
            as2.a(this.X);
        }
        if (as2.g() >= 55) {
            as2.a(this.w);
            as2.a(this.x);
        }
        if (as2.g() >= 91) {
            as2.a(this.ac);
            as2.c(0);
        }
        if (as2.g() >= 97) {
            as2.a(this.I);
            as2.a(this.J);
        }
        if (as2.g() >= 125) {
            as2.a(this.E);
            as2.a(this.at);
            as2.a(this.au);
        }
        if (as2.g() >= 149) {
            as2.b(this.P);
            as2.a(this.Q);
        }
        if (as2.g() >= 156) {
            as2.a(this.z);
            as2.a(this.A);
            as2.a(this.B);
            as2.a(this.C);
            as2.a(this.D);
        }
    }

    public void c(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.c(0);
        as2.a(this.A());
        as2.a(this.I);
        as2.a(this.J);
    }

    public void a(k k2) {
        k2.d();
        this.W = k2.f();
        this.X = System.currentTimeMillis();
        this.I = k2.e();
        this.J = k2.e();
    }

    public void b(k k2) {
        this.a(k2, false);
    }

    public void a(k k2, boolean bl) {
        int n2;
        boolean bl2;
        if (!bl) {
            this.f(k2.d());
            this.o = k2.f();
            this.p = 0.0;
            this.q = 0;
            this.r = k2.f();
            this.v = k2.j();
            this.U = k2.e();
        } else {
            k2.d();
            k2.f();
            k2.f();
            k2.j();
            k2.e();
        }
        if (k2.b() >= 14) {
            this.W = k2.f();
            k2.i();
            this.X = System.currentTimeMillis();
        }
        if (k2.b() >= 34 && k2.c() >= 55) {
            bl2 = k2.e();
            n2 = k2.f();
            if (!bl) {
                this.w = bl2;
                this.x = n2;
            }
        } else {
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            if (l2.bX.B) {
                com.corrodinggames.rts.gameFramework.j.ad.g("AI was skipping in networked game, steam version:" + k2.c());
            }
        }
        if (k2.b() >= 50 && k2.c() >= 91) {
            this.ac = k2.f();
            k2.d();
        }
        if (k2.b() >= 52 && k2.c() >= 97) {
            this.I = k2.e();
            this.J = k2.e();
        }
        if (k2.b() >= 70 && k2.c() >= 125) {
            bl2 = k2.e();
            n2 = k2.e() ? 1 : 0;
            int n3 = k2.f();
            if (!bl) {
                this.E = bl2;
                this.at = n2;
                this.au = n3;
            }
        }
        if (k2.b() >= 90 && k2.c() >= 149) {
            String string = k2.j();
            n2 = k2.f();
            if (!bl) {
                this.P = string;
                this.Q = n2;
            }
        }
        if (k2.b() >= 93 && k2.c() >= 156) {
            Integer n4 = k2.k();
            Integer n5 = k2.k();
            Integer n6 = k2.k();
            Integer n7 = k2.k();
            int n8 = k2.f();
            if (!bl) {
                if (this.z != n4) {
                    this.c("readIn aiDifficultyOverride was:" + this.z + " now:  " + n4);
                }
                this.z = n4;
                this.A = n5;
                this.B = n6;
                this.C = n7;
                this.D = n8;
            }
        }
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.d("Writing team: " + this.v);
        this.b(as2);
        if (as2.g() >= 44) {
            as2.c(4);
            as2.a(this.G);
            as2.a(this.F);
            boolean bl = true;
            as2.a(bl);
            if (bl) {
                this.d(as2);
            }
            this.al.a(as2);
            com.corrodinggames.rts.game.units.custom.g.a(this.ak, as2);
            as2.a(this.y);
        }
    }

    public void c(k k2) {
        this.b(k2);
        if (k2.b() >= 26) {
            boolean bl;
            byte by = k2.d();
            this.G = k2.e();
            if (by >= 1) {
                this.F = k2.e();
            }
            if (bl = k2.e()) {
                this.d(k2);
            }
            if (by >= 2) {
                this.al.a(k2);
            }
            if (by >= 3) {
                this.a(com.corrodinggames.rts.game.units.custom.g.a(k2));
            }
            if (by >= 4) {
                this.y = k2.e();
            }
        }
    }

    public void d(com.corrodinggames.rts.gameFramework.j.as as2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        as2.d("-- Saving fog --");
        as2.a(this.N != null);
        if (this.N != null) {
            as2.a(this.L);
            as2.a(this.M);
            for (int j = 0; j < this.L; ++j) {
                for (int i2 = 0; i2 < this.M; ++i2) {
                    as2.c(this.N[j][i2]);
                }
            }
        }
        as2.d("--End fog--");
    }

    public void d(k k2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl = k2.e();
        if (bl) {
            this.L = k2.f();
            this.M = k2.f();
            boolean bl2 = true;
            int n2 = this.L;
            int n3 = this.M;
            if (l2.bL != null) {
                n2 = l2.bL.C;
                n3 = l2.bL.D;
                if (this.L != n2 || this.M != n3) {
                    com.corrodinggames.rts.gameFramework.l.b("Map size does not match fog size: " + this.L + "!=" + n2 + "|" + this.M + "!=" + n3);
                }
            }
            this.N = bl2 ? new byte[n2][n3] : (byte[][])null;
            for (int i2 = 0; i2 < this.L; ++i2) {
                for (int i3 = 0; i3 < this.M; ++i3) {
                    if (bl2) {
                        this.N[i2][i3] = k2.d();
                        continue;
                    }
                    k2.d();
                }
            }
        } else {
            this.N = null;
        }
    }

    public void a() {
        if (this.N != null) {
            for (int i2 = 0; i2 < this.L; ++i2) {
                for (int i3 = 0; i3 < this.M; ++i3) {
                    this.N[i2][i3] = 0;
                }
            }
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bs == this) {
            l2.bW.O = true;
            if (l2.bL != null) {
                l2.bL.k();
                l2.bL.g();
            }
        }
    }

    public boolean b() {
        return this.r == -3;
    }

    public static ArrayList a(boolean bl) {
        ArrayList<n> arrayList = new ArrayList<n>();
        for (int i2 = 0; i2 < f; ++i2) {
            n n2 = as[i2];
            if (n2 == null || !bl && !n2.b()) continue;
            arrayList.add(n2);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static ArrayList c() {
        return com.corrodinggames.rts.game.n.b(false);
    }

    public static ArrayList b(boolean bl) {
        ArrayList<n> arrayList = new ArrayList<n>();
        for (int i2 = 0; i2 < f; ++i2) {
            n n2 = as[i2];
            if (n2 == null || !bl && n2.b()) continue;
            arrayList.add(n2);
        }
        return arrayList;
    }

    public static n[] d() {
        return b;
    }

    public static void e() {
        Object[] objectArray;
        int n2;
        m m2 = a;
        m2.clear();
        m2.add(i);
        m2.add(h);
        for (n2 = 0; n2 < c; ++n2) {
            objectArray = as[n2];
            if (objectArray == null) continue;
            m2.add(objectArray);
        }
        if (b.length != m2.a) {
            b = new n[m2.a];
        }
        n2 = m2.a;
        objectArray = m2.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.n.b[i2] = (n)objectArray[i2];
        }
    }

    public static ArrayList f() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i2 = 0; i2 < c; ++i2) {
            n n2 = as[i2];
            if (n2 == null || n2.b() || arrayList.contains(n2.r)) continue;
            arrayList.add(n2.r);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static int a(int n2, boolean bl) {
        int n3 = 0;
        for (int i2 = 0; i2 < c; ++i2) {
            n n4 = as[i2];
            if (n4 == null || n4.r != n2 || n4.b() || bl && n4.w) continue;
            ++n3;
        }
        return n3;
    }

    public static int g() {
        int n2 = 0;
        for (int i2 = 0; i2 < c; ++i2) {
            n n3 = as[i2];
            if (n3 == null || n3.b() || n3.F || n3.G) continue;
            ++n2;
        }
        return n2;
    }

    public static void b(int n2, boolean bl) {
        if (n2 < 10) {
            return;
        }
        if (n2 == c) {
            return;
        }
        if (n2 > e) {
            throw new IOException("setMaxTeamId: " + n2 + " is over limit of:" + e);
        }
        if (!bl && n2 <= c) {
            return;
        }
        int n3 = n2 + d;
        n[] nArray = new n[n3];
        for (int i2 = 0; i2 < as.length; ++i2) {
            n n4 = as[i2];
            if (i2 >= nArray.length) continue;
            nArray[i2] = n4;
        }
        as = nArray;
        c = n2;
        f = n3;
    }

    public static String a(int n2) {
        if (n2 == 0) {
            return "A";
        }
        if (n2 == 1) {
            return "B";
        }
        if (n2 == 2) {
            return "C";
        }
        if (n2 == 3) {
            return "D";
        }
        if (n2 == 4) {
            return "E";
        }
        if (n2 == 5) {
            return "F";
        }
        if (n2 == 6) {
            return "G";
        }
        if (n2 == 7) {
            return "H";
        }
        if (n2 == 8) {
            return "I";
        }
        if (n2 == 9) {
            return "J";
        }
        if (n2 == 10) {
            return "K";
        }
        if (n2 == -3) {
            return "S";
        }
        return "" + n2;
    }

    public String h() {
        return com.corrodinggames.rts.game.n.a(this.r);
    }

    public void i() {
        this.E = false;
        this.at = false;
        this.au = -9999;
    }

    public boolean j() {
        return this.E;
    }

    public boolean k() {
        return this.au >= 0;
    }

    public void l() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.au = l2.by;
    }

    public boolean m() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if ((this.F || this.G) && !l2.bX.ay.l) {
            return false;
        }
        if (this.w) {
            return false;
        }
        if (this.B()) {
            return false;
        }
        return !this.ab || this.k();
    }

    public static int b(int n2) {
        int n3 = 0;
        for (int i2 = 0; i2 < c; ++i2) {
            n n4 = as[i2];
            if (n4 == null || n4.r != n2 || !n4.k() || !n4.m()) continue;
            ++n3;
        }
        return n3;
    }

    public static int c(int n2) {
        int n3 = 0;
        for (int i2 = 0; i2 < c; ++i2) {
            n n4 = as[i2];
            if (n4 == null || n4.r != n2 || !n4.m()) continue;
            ++n3;
        }
        return n3;
    }

    public static void n() {
        for (int i2 = 0; i2 < c; ++i2) {
            n n2 = as[i2];
            if (n2 == null) continue;
            n2.Z();
        }
        com.corrodinggames.rts.game.n.Y();
    }

    public static void o() {
        for (int i2 = 0; i2 < c; ++i2) {
            n n2 = as[i2];
            if (n2 == null) continue;
            n2.au = -9999;
        }
    }

    public static void d(int n2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!l2.bX.C) {
            return;
        }
        if (l2.cb.j()) {
            return;
        }
        for (int i2 = 0; i2 < c; ++i2) {
            n n3 = as[i2];
            if (n3 == null || n3.r != n2 || n3.at) continue;
            n3.at = true;
            com.corrodinggames.rts.gameFramework.e e2 = l2.cf.b();
            e2.i = n3;
            e2.r = true;
            e2.u = 100;
            l2.bX.a(e2);
        }
    }

    public static void e(int n2) {
        int n3 = -9999;
        for (int i2 = 0; i2 < c; ++i2) {
            n n4 = as[i2];
            if (n4 == null || n4.r != n2 || !n4.k() || !n4.m() || n4.au <= n3) continue;
            n3 = n4.au;
        }
        if (n3 >= 0 && com.corrodinggames.rts.gameFramework.utility.y.a(n3, 120000)) {
            for (n n5 : as) {
                if (n5 == null || n5.r != n2) continue;
                n5.au = -9999;
            }
        }
    }

    public boolean b(n n2) {
        return this.p() && n2 != null && this.d(n2);
    }

    public boolean p() {
        return this.I || this.J;
    }

    public boolean q() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bs == this;
    }

    public void c(boolean bl) {
        this.Q = bl ? 1 : 0;
    }

    public boolean r() {
        return this.Q == 1;
    }

    public final int a(boolean bl, boolean bl2) {
        s s2 = this.T;
        int n2 = s2.c;
        if (bl) {
            n2 += s2.f;
        }
        if (bl2) {
            n2 += s2.e;
        }
        return n2;
    }

    public final int s() {
        return this.T.c + this.T.f + this.T.e;
    }

    public final int a(g g2, boolean bl, boolean bl2) {
        int n2;
        s s2 = this.T;
        if (s2.d == 0) {
            return 0;
        }
        p p2 = null;
        com.corrodinggames.rts.game.t t2 = s2.p;
        p[] pArray = t2.b;
        int n3 = t2.c;
        for (n2 = 0; n2 < n3; ++n2) {
            p p3 = pArray[n2];
            if (p3.a != g2) continue;
            p2 = p3;
            break;
        }
        if (p2 == null) {
            p2 = s2.a(g2);
            if (p2.e > 50) {
                t2.a(p2);
            }
            p2.e = (short)(p2.e + 1);
        }
        n2 = p2.b;
        if (bl) {
            n2 += p2.c;
        }
        if (bl2) {
            n2 += p2.d;
        }
        return n2;
    }

    public boolean t() {
        boolean bl = false;
        s s2 = this.e(false);
        if (this.T.b != s2.b) {
            com.corrodinggames.rts.gameFramework.l.b("unitCountExcludingBuildingsIncludingQueued: " + this.T.b + "!=" + s2.b + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.a != s2.a) {
            com.corrodinggames.rts.gameFramework.l.b("unitsMax: " + this.T.a + "!=" + s2.a + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.g != s2.g) {
            com.corrodinggames.rts.gameFramework.l.b("incomeRate: " + this.T.g + "!=" + s2.g + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.f != s2.f) {
            com.corrodinggames.rts.gameFramework.l.b("incompleteUnitCountOfAllTypes: " + this.T.f + "!=" + s2.f + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.e != s2.e) {
            com.corrodinggames.rts.gameFramework.l.b("queuedCountOfAllTypes: " + this.T.e + "!=" + s2.e + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (this.T.c != s2.c) {
            com.corrodinggames.rts.gameFramework.l.b("unitCountOfAllTypesOnlyCompleted: " + this.T.c + "!=" + s2.c + " (team:" + this.k + " fails: " + this.ad + ")");
            ++this.ad;
            bl = true;
        }
        if (!this.T.h.e(s2.h)) {
            com.corrodinggames.rts.gameFramework.l.b("customIncomeRate: " + this.T.h + "!=" + s2.h + " (team:" + this.k + " fails: " + this.ad + ")");
            com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.h.a(false, true, 30, true, true));
            com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + s2.h.a(false, true, 30, true, true));
            ++this.ad;
            bl = true;
        }
        if (!this.T.l.e(s2.l)) {
            com.corrodinggames.rts.gameFramework.l.b("streamingRateNegative (team:" + this.k + " fails: " + this.ad + ")");
            com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.l.a(false, true, 30, true, true));
            com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + s2.l.a(false, true, 30, true, true));
            ++this.ad;
            bl = true;
        }
        if (!this.T.k.e(s2.k)) {
            com.corrodinggames.rts.gameFramework.l.b("streamingRatePositive (team:" + this.k + " fails: " + this.ad + ")");
            com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.k.a(false, true, 30, true, true));
            com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + s2.k.a(false, true, 30, true, true));
            ++this.ad;
            bl = true;
        }
        if (bl) {
            // empty if block
        }
        return bl;
    }

    private s e(boolean bl) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        s s2 = new s();
        s2.a = l2.bB;
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n2 = com.corrodinggames.rts.game.units.am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (am2.bX != this) continue;
            s2.a(am2);
            if (!bl) continue;
            am2.bY = true;
        }
        if (s2.a > l2.bC) {
            s2.a = l2.bC;
        }
        return s2;
    }

    public void d(boolean bl) {
        if (!bl && !this.S) {
            return;
        }
        this.T = this.e(true);
        this.S = false;
        if (this.R < this.T.b) {
            this.R = this.T.b;
        }
        if (!this.n && this.T.m) {
            this.n = true;
        }
        if (!this.m && this.s() > 0) {
            this.m = true;
        }
        this.T();
    }

    public int u() {
        int n2 = this.T.g;
        n2 = (int)((float)n2 * this.D());
        return n2;
    }

    public int v() {
        return (int)((float)this.u() * this.E() + 0.5f);
    }

    public int a(com.corrodinggames.rts.game.units.custom.e.a a2) {
        int n2 = 0;
        return n2 -= (int)this.T.l.a(a2);
    }

    public int b(com.corrodinggames.rts.game.units.custom.e.a a2) {
        int n2 = a2 == com.corrodinggames.rts.game.units.custom.e.a.D ? this.T.g : (int)this.T.h.a(a2);
        n2 += (int)this.T.k.a(a2);
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl = false;
        if (a2 == com.corrodinggames.rts.game.units.custom.e.a.D) {
            bl = true;
        }
        if (bl) {
            n2 = (int)((float)n2 * this.D());
        }
        return n2;
    }

    public int w() {
        return this.T.b;
    }

    public int x() {
        return this.T.a;
    }

    public String y() {
        int n2 = this.A();
        if (n2 == -99) {
            return "";
        }
        if (this.w) {
            return "";
        }
        if (n2 == -2) {
            return "(disconnected)";
        }
        if (n2 == -1) {
            return "(disconnected)";
        }
        return "(" + n2 + ")";
    }

    public String z() {
        int n2 = this.A();
        if (n2 == -99) {
            return "HOST";
        }
        if (this.w) {
            return "-";
        }
        if (n2 == -1) {
            return "N/A";
        }
        if (n2 == -2) {
            return "-";
        }
        if (this.r()) {
            return n2 + " (HOST)";
        }
        return "" + n2;
    }

    public int A() {
        if (this.X == -1L) {
            return -2;
        }
        if (this.X < System.currentTimeMillis() - 5000L) {
            return -1;
        }
        return this.W;
    }

    public boolean B() {
        if (this.X == -99L) {
            return false;
        }
        return this.X != -1L && this.X < System.currentTimeMillis() - 15000L;
    }

    public void a(float f2) {
        this.an += f2;
        if (this.an > 90.0f) {
            this.an = 0.0f;
            this.am.a();
        }
        ++this.q;
        if (this.q > 1000 && this.p != 0.0) {
            com.corrodinggames.rts.gameFramework.l.e("Warning: anti-lag credits is still: " + this.p + " (force clearing)");
            this.p = 0.0;
        }
    }

    public final int C() {
        if (this.y) {
            return this.x;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if ((l2.bX.B || l2.cb.i()) && !l2.bX.F) {
            if (this.z != null && this.z != this.x) {
                this.c("aiDifficultyOverride:  " + this.z + "!=" + this.x);
            }
            return this.x;
        }
        if (this.z != null) {
            return this.z;
        }
        int n2 = com.corrodinggames.rts.gameFramework.l.B().bQ.aiDifficulty;
        return n2;
    }

    public final float D() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.O()) {
            return l2.bX.ay.h;
        }
        return 1.0f;
    }

    public final float E() {
        if (!this.w) {
            return 1.0f;
        }
        int n2 = this.C();
        float f2 = 1.0f;
        f2 = n2 > 0 ? (f2 += (float)n2 * 0.4f) : (f2 += (float)n2 * 0.3f);
        if (n2 == 3) {
            f2 += 1.5f;
        }
        if (f2 < 0.1f) {
            f2 = 0.1f;
        }
        return f2;
    }

    public final void b(float f2) {
        if (!this.w) {
            this.c(f2);
            return;
        }
        float f3 = this.E();
        this.c(f3 * f2);
    }

    public final void c(float f2) {
        this.d(f2 *= this.D());
    }

    public final void d(float f2) {
        this.o += (double)f2;
        if (this.o > 9.99999999E8) {
            this.o = 9.99999999E8;
        }
    }

    public static void F() {
        try {
            com.corrodinggames.rts.game.n.b(10, true);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        for (int i2 = 0; i2 < as.length; ++i2) {
            com.corrodinggames.rts.game.n.as[i2] = null;
        }
    }

    public static com.corrodinggames.rts.game.e a(String string) {
        if (string == null || string.equals("")) {
            com.corrodinggames.rts.gameFramework.l.b("findExistingPlayer: No clientId id");
            return null;
        }
        for (int i2 = 0; i2 < as.length; ++i2) {
            n n2 = as[i2];
            if (n2 == null || !string.equals(n2.O)) continue;
            if (n2 instanceof com.corrodinggames.rts.game.e) {
                return (com.corrodinggames.rts.game.e)n2;
            }
            com.corrodinggames.rts.gameFramework.l.b("Player:" + i2 + " with matching clientId is not an instanceof player");
        }
        return null;
    }

    public static com.corrodinggames.rts.game.e b(String string) {
        if (string == null || string.equals("")) {
            com.corrodinggames.rts.gameFramework.l.b("No id");
            return null;
        }
        for (int i2 = 0; i2 < as.length; ++i2) {
            n n2 = as[i2];
            if (n2 == null || !string.equals(n2.P)) continue;
            if (n2 instanceof com.corrodinggames.rts.game.e) {
                return (com.corrodinggames.rts.game.e)n2;
            }
            com.corrodinggames.rts.gameFramework.l.b("Player:" + i2 + " with matching clientId is not an instanceof player");
        }
        return null;
    }

    public static int G() {
        for (int i2 = 0; i2 < c; ++i2) {
            if (as[i2] != null) continue;
            return i2;
        }
        return -1;
    }

    public static int H() {
        int n2;
        for (n2 = c; n2 < f; ++n2) {
            if (as[n2] != null) continue;
            return n2;
        }
        for (n2 = c - 1; n2 >= 0; --n2) {
            if (as[n2] != null) continue;
            return n2;
        }
        return -1;
    }

    public void I() {
        for (int i2 = 0; i2 < as.length; ++i2) {
            if (as[i2] != this) continue;
            com.corrodinggames.rts.game.n.as[i2] = null;
        }
    }

    public n() {
        this.w = this instanceof a;
    }

    public n(int n2) {
        this(n2, true);
    }

    public n(int n2, boolean bl) {
        this();
        this.c(n2, bl);
    }

    public void f(int n2) {
        this.c(n2, true);
    }

    public void c(int n2, boolean bl) {
        if (this.k != n2) {
            if (bl) {
                this.I();
            }
            this.k = n2;
            this.r = n2;
            if (bl && n2 != -3) {
                n n3 = as[n2];
                if (n3 != null) {
                    n3.c("Being replaced");
                }
                com.corrodinggames.rts.game.n.as[n2] = this;
            }
            this.J();
        }
    }

    public void J() {
        int n2 = this.K();
        this.ae.b(n2);
        int n3 = Color.a(Color.a(n2), (int)((float)Color.b(n2) * 0.5f), (int)((float)Color.c(n2) * 0.5f), (int)((float)Color.d(n2) * 0.5f));
        this.af.b(n3);
    }

    public boolean a(double d2) {
        return this.o >= d2 || d2 == 0.0;
    }

    public boolean g(int n2) {
        return this.o + this.p >= (double)n2 || n2 == 0;
    }

    public final boolean c(n n2) {
        if (n2 == i || this == i) {
            return false;
        }
        return this.r != n2.r;
    }

    public final boolean d(n n2) {
        if (n2 == i && this == i) {
            return true;
        }
        if (n2 == i || this == i) {
            return false;
        }
        return this.r == n2.r;
    }

    public int K() {
        return com.corrodinggames.rts.game.n.i(this.R());
    }

    public static void L() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        try {
            com.corrodinggames.rts.game.n.d(l2.bQ.teamColors);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            com.corrodinggames.rts.gameFramework.l.a("initColors: Failed to read setting: '" + l2.bQ.teamColors + "': " + illegalArgumentException.getMessage(), (Throwable)illegalArgumentException);
            com.corrodinggames.rts.game.n.d("#00ff00,#d02013,#0463f3,#ffff40,#00ffff,#d0f8f7,#000000,#ff00ea,#ff7f18,#9368c4");
        }
        try {
            com.corrodinggames.rts.game.n.e(l2.bQ.teamColorsNames);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            com.corrodinggames.rts.gameFramework.l.a("initColors: Failed to read setting: '" + l2.bQ.teamColorsNames + "': " + illegalArgumentException.getMessage(), (Throwable)illegalArgumentException);
            com.corrodinggames.rts.game.n.e("GREEN,RED,BLUE,YELLOW,CYAN,WHITE,BLACK,PINK,ORANGE,PURPLE");
        }
    }

    private static void d(String string) {
        String[] stringArray = string.split(",");
        if (stringArray.length != 10) {
            throw new IllegalArgumentException("Expected 10 hex colors");
        }
        for (int i2 = 0; i2 < 10; ++i2) {
            String string2 = stringArray[i2];
            com.corrodinggames.rts.game.n.ag[i2] = Color.a(string2);
        }
    }

    private static void e(String string) {
        String[] stringArray = string.split(",");
        if (stringArray.length != 10) {
            throw new IllegalArgumentException("Expected 10 team color names");
        }
        for (int i2 = 0; i2 < 10; ++i2) {
            com.corrodinggames.rts.game.n.ah[i2] = stringArray[i2];
        }
    }

    public int M() {
        if (this.r == -3) {
            return com.corrodinggames.rts.game.n.i(-3);
        }
        return com.corrodinggames.rts.game.n.h(this.k);
    }

    public static int h(int n2) {
        if (n2 >= c) {
            return com.corrodinggames.rts.game.n.i(-3);
        }
        return com.corrodinggames.rts.game.n.i(n2 % 2);
    }

    public static int i(int n2) {
        if (n2 >= 0 && n2 < 10) {
            return ag[n2];
        }
        if (n2 == -3) {
            return Color.a(185, 90, 90, 90);
        }
        return -7829368;
    }

    public String N() {
        if (this.k == -1) {
            return "GRAY";
        }
        if (this.k == -2) {
            return "GRAY";
        }
        return com.corrodinggames.rts.game.n.j(this.R());
    }

    public static String j(int n2) {
        int n3 = n2;
        if (n3 >= 0 && n3 < 10) {
            return ah[n3];
        }
        return "GRAY";
    }

    public static com.corrodinggames.rts.gameFramework.m.e[] a(com.corrodinggames.rts.gameFramework.m.e e2) {
        return com.corrodinggames.rts.game.n.a(e2, com.corrodinggames.rts.game.o.a, false);
    }

    public static com.corrodinggames.rts.gameFramework.m.e[] a(com.corrodinggames.rts.gameFramework.m.e e2, o o2, boolean bl) {
        if (!bl || e2.A()) {
            return com.corrodinggames.rts.game.n.b(e2, o2);
        }
        return com.corrodinggames.rts.game.n.a(e2, o2);
    }

    public static com.corrodinggames.rts.gameFramework.m.e[] a(com.corrodinggames.rts.gameFramework.m.e e2, o o2) {
        com.corrodinggames.rts.gameFramework.m.e[] eArray = new com.corrodinggames.rts.gameFramework.m.e[10];
        if (com.corrodinggames.rts.gameFramework.l.aU && !com.corrodinggames.rts.gameFramework.l.aW || o2 == com.corrodinggames.rts.game.o.e) {
            for (int i2 = 0; i2 < eArray.length; ++i2) {
                eArray[i2] = e2;
            }
            return eArray;
        }
        com.corrodinggames.rts.gameFramework.m.e[] eArray2 = e2.a(o2);
        if (eArray2 != null) {
            return eArray2;
        }
        br br2 = com.corrodinggames.rts.gameFramework.l.B().cd;
        br2.a(bs.D);
        for (int i3 = 0; i3 < eArray.length; ++i3) {
            int n2 = com.corrodinggames.rts.game.n.i(i3);
            eArray[i3] = i3 == 0 ? e2 : new com.corrodinggames.rts.gameFramework.m.h(e2, n2, o2, i3);
        }
        br2.b(bs.D);
        e2.a(o2, eArray);
        return eArray;
    }

    public static com.corrodinggames.rts.gameFramework.m.e[] b(com.corrodinggames.rts.gameFramework.m.e e2, o o2) {
        int n2;
        com.corrodinggames.rts.gameFramework.m.e[] eArray = new com.corrodinggames.rts.gameFramework.m.e[10];
        if (com.corrodinggames.rts.gameFramework.l.aU && !com.corrodinggames.rts.gameFramework.l.aW || o2 == com.corrodinggames.rts.game.o.e || e2.A()) {
            for (int i2 = 0; i2 < eArray.length; ++i2) {
                eArray[i2] = e2;
            }
            return eArray;
        }
        com.corrodinggames.rts.gameFramework.m.e[] eArray2 = e2.a(o2);
        if (eArray2 != null) {
            return eArray2;
        }
        br br2 = com.corrodinggames.rts.gameFramework.l.B().cd;
        br2.a(bs.D);
        int[] nArray = new int[10];
        int[] nArray2 = new int[10];
        for (n2 = 0; n2 < nArray.length; ++n2) {
            nArray[n2] = com.corrodinggames.rts.game.n.i(n2);
            nArray2[n2] = n2;
        }
        for (n2 = 0; n2 < eArray.length; ++n2) {
            if (n2 == 0) continue;
            eArray[n2] = e2.h();
            eArray[n2].a("color(" + n2 + "):" + e2.a());
            eArray[n2].j();
        }
        e2.j();
        if (o2 == com.corrodinggames.rts.game.o.b) {
            com.corrodinggames.rts.game.n.b(e2, eArray, nArray);
        } else if (o2 == com.corrodinggames.rts.game.o.d) {
            com.corrodinggames.rts.game.n.a(e2, eArray, nArray, nArray2);
        } else {
            com.corrodinggames.rts.game.n.a(e2, eArray, nArray);
        }
        for (n2 = 0; n2 < eArray.length; ++n2) {
            if (eArray[n2] == null) continue;
            eArray[n2].p();
            eArray[n2].s();
        }
        e2.r();
        eArray[0] = e2;
        br2.b(bs.D);
        e2.a(o2, eArray);
        return eArray;
    }

    public static void a(com.corrodinggames.rts.gameFramework.m.e e2, com.corrodinggames.rts.gameFramework.m.e[] eArray, int[] nArray) {
        int n2 = e2.m();
        int n3 = e2.l();
        int[] nArray2 = new int[nArray.length];
        int[] nArray3 = new int[nArray.length];
        int[] nArray4 = new int[nArray.length];
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            nArray2[i2] = Color.b(nArray[i2]);
            nArray3[i2] = Color.c(nArray[i2]);
            nArray4[i2] = Color.d(nArray[i2]);
        }
        float f2 = 0.003921569f;
        for (int i3 = 0; i3 < n3; ++i3) {
            for (int i4 = 0; i4 < n2; ++i4) {
                int n4;
                int n5;
                int n6;
                int n7;
                int n8;
                int n9;
                int n10 = e2.a(i4, i3);
                int n11 = com.corrodinggames.rts.gameFramework.m.aa.a(n10);
                if (n11 == 0) {
                    if (n10 == 0) continue;
                    for (n9 = 0; n9 < eArray.length; ++n9) {
                        if (eArray[n9] == null) continue;
                        eArray[n9].a(i4, i3, 0);
                    }
                    continue;
                }
                n9 = com.corrodinggames.rts.gameFramework.m.aa.c(n10);
                if (n9 <= 0 || (n8 = com.corrodinggames.rts.gameFramework.m.aa.b(n10)) != (n7 = com.corrodinggames.rts.gameFramework.m.aa.d(n10))) continue;
                if (n8 == 0) {
                    n6 = n9;
                    for (int i5 = 0; i5 < eArray.length; ++i5) {
                        if (eArray[i5] == null) continue;
                        int n12 = nArray2[i5] * n6 >> 8;
                        n5 = nArray3[i5] * n6 >> 8;
                        n4 = nArray4[i5] * n6 >> 8;
                        eArray[i5].a(i4, i3, Color.a(n11, n12, n5, n4));
                    }
                    continue;
                }
                if (n9 == n8) continue;
                n6 = n8;
                float f3 = (float)n6 * 0.003921569f;
                float f4 = (float)n9 * 0.003921569f - f3;
                for (n5 = 0; n5 < eArray.length; ++n5) {
                    if (eArray[n5] == null) continue;
                    n4 = (int)((float)n6 + (float)nArray2[n5] * f4);
                    int n13 = (int)((float)n6 + (float)nArray3[n5] * f4);
                    int n14 = (int)((float)n6 + (float)nArray4[n5] * f4);
                    n4 = com.corrodinggames.rts.gameFramework.f.b(n4, 0, 255);
                    n13 = com.corrodinggames.rts.gameFramework.f.b(n13, 0, 255);
                    n14 = com.corrodinggames.rts.gameFramework.f.b(n14, 0, 255);
                    eArray[n5].a(i4, i3, Color.a(n11, n4, n13, n14));
                }
            }
        }
    }

    public static void a(com.corrodinggames.rts.gameFramework.m.e e2, com.corrodinggames.rts.gameFramework.m.e[] eArray, int[] nArray, int[] nArray2) {
        int n2;
        int n3 = e2.m();
        int n4 = e2.l();
        int[] nArray3 = new int[nArray.length];
        int[] nArray4 = new int[nArray.length];
        int[] nArray5 = new int[nArray.length];
        for (n2 = 0; n2 < nArray.length; ++n2) {
            nArray3[n2] = Color.b(nArray[n2]);
            nArray4[n2] = Color.c(nArray[n2]);
            nArray5[n2] = Color.d(nArray[n2]);
        }
        for (n2 = 0; n2 < n4; ++n2) {
            for (int i2 = 0; i2 < n3; ++i2) {
                int n5;
                int n6 = e2.a(i2, n2);
                int n7 = Color.a(n6);
                if (n7 == 0) {
                    if (Color.b(n6) <= 0 && Color.c(n6) <= 0 && Color.d(n6) <= 0) continue;
                    for (n5 = 0; n5 < eArray.length; ++n5) {
                        if (eArray[n5] == null) continue;
                        eArray[n5].a(i2, n2, Color.a(0, 0, 0, 0));
                    }
                    continue;
                }
                n5 = Color.c(n6);
                int n8 = Color.b(n6);
                int n9 = Color.d(n6);
                float f2 = com.corrodinggames.rts.gameFramework.f.c(com.corrodinggames.rts.gameFramework.f.c(n8, n5), n9);
                float f3 = com.corrodinggames.rts.gameFramework.f.d(n8 - n5);
                f3 = com.corrodinggames.rts.gameFramework.f.f(f3, (float)com.corrodinggames.rts.gameFramework.f.d(n5 - n9));
                if (!((f3 = com.corrodinggames.rts.gameFramework.f.f(f3, (float)com.corrodinggames.rts.gameFramework.f.d(n9 - n8))) > 15.0f)) continue;
                for (int i3 = 0; i3 < eArray.length; ++i3) {
                    if (eArray[i3] == null) continue;
                    float f4 = f3 / 255.0f;
                    int n10 = (int)(f2 + (float)nArray3[i3] * f4);
                    int n11 = (int)(f2 + (float)nArray4[i3] * f4);
                    int n12 = (int)(f2 + (float)nArray5[i3] * f4);
                    n10 = com.corrodinggames.rts.gameFramework.f.b(n10, 0, 255);
                    n11 = com.corrodinggames.rts.gameFramework.f.b(n11, 0, 255);
                    n12 = com.corrodinggames.rts.gameFramework.f.b(n12, 0, 255);
                    eArray[i3].a(i2, n2, Color.a(n7, n10, n11, n12));
                }
            }
        }
    }

    public static void b(com.corrodinggames.rts.gameFramework.m.e e2, com.corrodinggames.rts.gameFramework.m.e[] eArray, int[] nArray) {
        int n2;
        int n3 = e2.m();
        int n4 = e2.l();
        int[] nArray2 = new int[nArray.length];
        int[] nArray3 = new int[nArray.length];
        int[] nArray4 = new int[nArray.length];
        for (n2 = 0; n2 < nArray.length; ++n2) {
            nArray2[n2] = Color.b(nArray[n2]);
            nArray3[n2] = Color.c(nArray[n2]);
            nArray4[n2] = Color.d(nArray[n2]);
        }
        for (n2 = 0; n2 < n3; ++n2) {
            for (int i2 = 0; i2 < n4; ++i2) {
                int n5 = e2.a(n2, i2);
                int n6 = Color.a(n5);
                if (n6 <= 0) continue;
                int n7 = Color.b(n5);
                int n8 = Color.c(n5);
                int n9 = Color.d(n5);
                float f2 = 0.15f;
                for (int i3 = 0; i3 < eArray.length; ++i3) {
                    int n10 = (int)((float)n7 + (float)nArray2[i3] * f2);
                    int n11 = (int)((float)n8 + (float)nArray3[i3] * f2);
                    int n12 = (int)((float)n9 + (float)nArray4[i3] * f2);
                    n10 = com.corrodinggames.rts.gameFramework.f.b(n10, 0, 255);
                    n11 = com.corrodinggames.rts.gameFramework.f.b(n11, 0, 255);
                    n12 = com.corrodinggames.rts.gameFramework.f.b(n12, 0, 255);
                    if (eArray[i3] == null) continue;
                    eArray[i3].a(n2, i2, Color.a(n6, n10, n11, n12));
                }
            }
        }
    }

    public static n k(int n2) {
        if (n2 == -1) {
            return i;
        }
        if (n2 == -2) {
            return h;
        }
        if (n2 >= f) {
            com.corrodinggames.rts.gameFramework.l.g("team index too high: " + n2);
            return null;
        }
        if (n2 < 0) {
            com.corrodinggames.rts.gameFramework.l.g("team index too low: " + n2);
            return null;
        }
        return as[n2];
    }

    public void e(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.ai > 0) {
            --this.ai;
            return;
        }
        this.ai = this.ai == -2 ? this.k : 10;
        if (!this.G && !l2.cb.j()) {
            int n2;
            boolean bl2 = false;
            boolean bl3 = false;
            boolean bl4 = false;
            boolean bl5 = l2.bX.ay.l;
            boolean bl6 = false;
            am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
            int n3 = com.corrodinggames.rts.game.units.am.bE.size();
            for (n2 = 0; n2 < n3; ++n2) {
                am am2 = amArray[n2];
                if (am2.bX == this) {
                    if (!am2.cT()) {
                        bl2 = true;
                        if (this.F || !am2.bJ() && !am2.ak()) continue;
                        bl3 = true;
                        break;
                    }
                    bl6 = true;
                    continue;
                }
                if (!bl5 || am2.bX == null || !am2.bX.d(this) || am2.cT()) continue;
                bl4 = true;
            }
            if (!bl2 && !bl4) {
                n2 = 0;
                if (bl6 && l2.bx < 100 && l2.bv) {
                    n2 = 1;
                }
                this.G = true;
                this.a();
                for (am am2 : com.corrodinggames.rts.game.units.am.bE) {
                    if (am2.bX != this || am2.u()) continue;
                    if (n2 != 0 && !am2.bV && am2.cT()) {
                        as as2 = am2.r();
                        String string = am2.c() + " Warning: This unit got ignored in defeated check and now being removed";
                        if (as2 instanceof com.corrodinggames.rts.game.units.custom.l && ((com.corrodinggames.rts.game.units.custom.l)as2).aO) {
                            string = string + " (Likely due to canNotBeDirectlyAttacked:true)";
                        }
                        com.corrodinggames.rts.gameFramework.j.ad.a(null, string);
                    }
                    am2.cj();
                }
                l2.bX.i(this);
            }
            if (!(bl3 || this.F || this.G)) {
                this.F = true;
                l2.bX.h(this);
            }
        }
    }

    public void a(y y2) {
    }

    public static void b(y y2) {
        for (int i2 = 0; i2 < c; ++i2) {
            n n2 = as[i2];
            if (n2 == null) continue;
            n2.a(y2);
        }
    }

    public static void a(am am2) {
        if (am2.bX != null && am2.bY && am2.bL) {
            n n2 = am2.bX;
            am2.bY = false;
            n2.T.b(am2);
            am2.dj();
        }
    }

    public static void b(am am2) {
        com.corrodinggames.rts.game.n.a(am2);
    }

    public static void c(am am2) {
        if (am2.bX != null && !am2.bY && am2.bL && !am2.bV) {
            am2.bY = true;
            n n2 = am2.bX;
            n2.T.a(am2);
            am2.di();
            if (!n2.n && n2.T.m) {
                n2.n = true;
            }
            if (!n2.m) {
                n2.m = true;
            }
            n2.T();
        }
    }

    public static void O() {
        com.corrodinggames.rts.game.n.i.S = true;
        com.corrodinggames.rts.game.n.h.S = true;
        for (n n2 : com.corrodinggames.rts.game.n.c()) {
            n2.S = true;
        }
    }

    public static void P() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.M()) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping updateAllCachesFromChangedMetadata due to desync risk");
            return;
        }
        for (int i2 = 0; i2 < c; ++i2) {
            n n2 = as[i2];
            if (n2 == null) continue;
            n2.S = true;
        }
    }

    public static void f(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        i.a(f2);
        h.a(f2);
        block0: for (int i2 = 0; i2 < c; ++i2) {
            int n2;
            n n3 = as[i2];
            if (n3 == null) continue;
            n3.a(f2);
            n3.e(f2);
            if (!n3.at && (n2 = com.corrodinggames.rts.game.n.b(n3.r)) > 0) {
                int n4 = com.corrodinggames.rts.game.n.c(n3.r);
                if (n2 >= n4) {
                    com.corrodinggames.rts.game.n.d(n3.r);
                    com.corrodinggames.rts.game.n.o();
                } else {
                    com.corrodinggames.rts.game.n.e(n3.r);
                }
            }
            if (!n3.E) continue;
            if (n3.av < 0) {
                n3.av = l2.by;
            }
            if (n3.G) continue;
            n2 = 0;
            for (am am2 : com.corrodinggames.rts.game.units.am.bE) {
                if (am2.bX != n3 || am2.u()) continue;
                boolean bl2 = false;
                int n5 = 1;
                if (com.corrodinggames.rts.gameFramework.utility.y.a(n3.av, 10000)) {
                    bl2 = true;
                    n5 = 50;
                } else if (com.corrodinggames.rts.gameFramework.utility.y.a(n3.av, 6000)) {
                    bl2 = com.corrodinggames.rts.gameFramework.f.a(am2, 0, 100) > 90;
                    n5 = 20;
                } else if (com.corrodinggames.rts.gameFramework.utility.y.a(n3.av, 2000)) {
                    bl2 = com.corrodinggames.rts.gameFramework.f.a(am2, 0, 100) > 98;
                    n5 = 2;
                }
                if (am2 instanceof e) {
                    bl2 = true;
                }
                if (!bl2) continue;
                am2.cu = -1.0f;
                if (++n2 <= n5) continue;
                continue block0;
            }
        }
        if (l2.P() && l2.bQ.aiDifficulty != aj) {
            l2.bX.aq();
            aj = l2.bQ.aiDifficulty;
        }
    }

    public static void g(float f2) {
        n[] nArray;
        com.corrodinggames.rts.game.n.e();
        for (n n2 : nArray = com.corrodinggames.rts.game.n.d()) {
            n2.d(false);
        }
    }

    public static void Q() {
        i.d(false);
        for (int i2 = 0; i2 < c; ++i2) {
            n n2 = as[i2];
            if (n2 == null || n2.b() || n2.G || n2.F || n2.E) continue;
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            l2.bX.g(n2);
        }
    }

    public static void h(float f2) {
        for (int i2 = 0; i2 < c; ++i2) {
            n n2 = as[i2];
            if (n2 == null || !(n2 instanceof a)) continue;
            a a2 = (a)n2;
            a2.i(f2);
        }
    }

    public int R() {
        if (this.D == -1) {
            return this.S();
        }
        return this.D;
    }

    public int S() {
        n n2;
        if (this.k == -1) {
            return 5;
        }
        if (this.k == -2) {
            return 5;
        }
        int n3 = this.k;
        if (n3 >= 10) {
            n3 %= 10;
        }
        if (c > 10 && (n2 = com.corrodinggames.rts.gameFramework.l.B().bX.z) != null && n2 != this && n2.R() == n3) {
            n3 = n3 != 5 ? 5 : 4;
        }
        return n3;
    }

    public void T() {
    }

    public void a(h h2) {
        this.ak = h2;
    }

    public h U() {
        return this.ak;
    }

    public void b(h h2) {
        h h3 = this.U();
        if (h3 == null || h3.b() == 0) {
            this.a(h2);
            return;
        }
        if (com.corrodinggames.rts.game.units.custom.g.b(h3, h2)) {
            return;
        }
        i i2 = new i(h3);
        if (i2.a(h2)) {
            this.a(i2.a());
            return;
        }
    }

    public void c(h h2) {
        h h3 = this.U();
        if (h3 == null || h3.b() == 0) {
            return;
        }
        if (!com.corrodinggames.rts.game.units.custom.g.a(h2, h3)) {
            return;
        }
        i i2 = new i(h3);
        if (i2.b(h2)) {
            this.a(i2.a());
            return;
        }
    }

    public f V() {
        return this.al;
    }

    public double c(com.corrodinggames.rts.game.units.custom.e.a a2) {
        return this.al.a(a2);
    }

    public boolean a(q q2, n n2) {
        if (q2 == com.corrodinggames.rts.game.q.a) {
            return n2 == this;
        }
        if (q2 == com.corrodinggames.rts.game.q.f) {
            return true;
        }
        if (q2 == com.corrodinggames.rts.game.q.b) {
            return this.d(n2);
        }
        if (q2 == com.corrodinggames.rts.game.q.c) {
            return n2 != this && this.d(n2);
        }
        if (q2 == com.corrodinggames.rts.game.q.d) {
            return this.c(n2);
        }
        if (q2 == com.corrodinggames.rts.game.q.e) {
            return n2 == i;
        }
        if (q2 == com.corrodinggames.rts.game.q.g) {
            return n2 != this;
        }
        throw new RuntimeException("Unsupported type: " + (Object)((Object)q2));
    }

    public void d(am am2) {
    }

    public void W() {
        int n2;
        com.corrodinggames.rts.gameFramework.l.e("debugUnitCountByType for team:" + this.k);
        m m2 = new m();
        am[] amArray = com.corrodinggames.rts.game.units.am.bE.a();
        int n3 = com.corrodinggames.rts.game.units.am.bE.size();
        for (n2 = 0; n2 < n3; ++n2) {
            Object object = amArray[n2];
            if (((am)object).bX != this || ((am)object).bV) continue;
            Object object2 = ((am)object).dz;
            boolean bl2 = false;
            for (r r2 : m2) {
                if (r2.a != object2) continue;
                ++r2.b;
                bl2 = true;
                break;
            }
            if (bl2) continue;
            r r3 = new r();
            r3.a = object2;
            r3.b = 1;
            m2.add(r3);
        }
        com.corrodinggames.rts.gameFramework.l.e("--- Units ---");
        n2 = 0;
        for (Object object : m2) {
            if (((r)object).a.k()) continue;
            com.corrodinggames.rts.gameFramework.l.e(((r)object).a.i() + " - count:" + ((r)object).b);
            n2 += ((r)object).b;
        }
        com.corrodinggames.rts.gameFramework.l.e("total:" + n2);
        com.corrodinggames.rts.gameFramework.l.e("--- Buildings/Ignored in count ---");
        int n4 = 0;
        for (Object object2 : m2) {
            if (!((r)object2).a.k()) continue;
            com.corrodinggames.rts.gameFramework.l.e(((r)object2).a.i() + " - count:" + ((r)object2).b);
            n4 += ((r)object2).b;
        }
        com.corrodinggames.rts.gameFramework.l.e("total:" + n4);
    }

    public void c(String string) {
        com.corrodinggames.rts.gameFramework.l.e("Team(id: " + this.k + ", name:" + this.v + "):" + string);
    }

    public int b(g g2, boolean bl2, boolean bl3) {
        int n2 = 0;
        if (this == i) {
            return 0;
        }
        n[] nArray = as;
        int n3 = c;
        for (int i2 = 0; i2 < n3; ++i2) {
            n n4 = nArray[i2];
            if (n4 == null || this == n4 || this.r == n4.r) continue;
            if (g2 == null) {
                n2 += n4.a(bl2, bl3);
                continue;
            }
            n2 += n4.a(g2, bl2, bl3);
        }
        return n2;
    }

    public int c(g g2, boolean bl2, boolean bl3) {
        int n2 = 0;
        n[] nArray = as;
        int n3 = c;
        for (int i2 = 0; i2 < n3; ++i2) {
            n n4 = nArray[i2];
            if (n4 == null || this == n4 || !this.d(n4)) continue;
            if (g2 == null) {
                n2 += n4.a(bl2, bl3);
                continue;
            }
            n2 += n4.a(g2, bl2, bl3);
        }
        return n2;
    }

    public static void X() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.game.n.i.T.a = l2.bB;
        com.corrodinggames.rts.game.n.h.T.a = l2.bB;
        for (int i2 = 0; i2 < c; ++i2) {
            n n2 = as[i2];
            if (n2 == null) continue;
            n2.T.a = l2.bB;
        }
    }

    public static void Y() {
        i.Z();
        h.Z();
    }

    public void Z() {
        this.m = false;
        this.n = false;
        this.o = 4000.0;
        this.p = 0.0;
        this.q = 0;
        this.ai = -2;
        this.at = false;
        this.au = -9999;
        this.E = false;
        this.av = -9999;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = false;
        this.am.a();
        this.an = 0.0f;
        this.ad = 0;
        this.R = 0;
        this.S = true;
        this.T = new s();
        this.T.a = com.corrodinggames.rts.gameFramework.l.B().bB;
        this.ak = com.corrodinggames.rts.game.units.custom.g.d;
        this.al = new f();
    }

    public double aa() {
        long l2 = System.currentTimeMillis();
        if (com.corrodinggames.rts.gameFramework.f.c(this.aq - l2) > 166.66666f) {
            this.aq = l2;
            this.ar = this.o + this.p;
        }
        return this.ar;
    }

    public f ab() {
        return this.V();
    }

    public /* synthetic */ int compareTo(Object object) {
        return this.a((n)object);
    }
}
