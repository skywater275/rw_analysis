/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.Notification$Builder
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.Intent
 *  android.net.DhcpInfo
 *  android.net.wifi.WifiManager
 *  android.os.Build$VERSION
 */
package com.corrodinggames.rts.gameFramework.j;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.appFramework.i;
import com.corrodinggames.rts.appFramework.p;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.custom.bd;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.a;
import com.corrodinggames.rts.gameFramework.j.ac;
import com.corrodinggames.rts.gameFramework.j.ad$1;
import com.corrodinggames.rts.gameFramework.j.ad$2;
import com.corrodinggames.rts.gameFramework.j.ad$3;
import com.corrodinggames.rts.gameFramework.j.ad$4;
import com.corrodinggames.rts.gameFramework.j.ad$5;
import com.corrodinggames.rts.gameFramework.j.ad$6;
import com.corrodinggames.rts.gameFramework.j.ad$7;
import com.corrodinggames.rts.gameFramework.j.ad$8;
import com.corrodinggames.rts.gameFramework.j.ae;
import com.corrodinggames.rts.gameFramework.j.af;
import com.corrodinggames.rts.gameFramework.j.ag;
import com.corrodinggames.rts.gameFramework.j.ah;
import com.corrodinggames.rts.gameFramework.j.ai;
import com.corrodinggames.rts.gameFramework.j.aj;
import com.corrodinggames.rts.gameFramework.j.ak;
import com.corrodinggames.rts.gameFramework.j.al;
import com.corrodinggames.rts.gameFramework.j.am;
import com.corrodinggames.rts.gameFramework.j.an;
import com.corrodinggames.rts.gameFramework.j.ao;
import com.corrodinggames.rts.gameFramework.j.aq;
import com.corrodinggames.rts.gameFramework.j.ar;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.av;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.h;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.j.w;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public strictfp final class ad {
    public static final boolean a = false;
    public static boolean b = true;
    public static boolean c = false;
    public ac d = new ac();
    public int e;
    ArrayList f;
    public boolean g;
    public int h = 25;
    public boolean i;
    public float j;
    public float k;
    public boolean l = false;
    public int m;
    public String n;
    public boolean o;
    public boolean p;
    public boolean q;
    public static boolean r = true;
    public boolean s;
    public int t = 5005;
    public String u;
    public boolean v = false;
    public long w = 1L;
    public boolean x = false;
    public String y;
    private boolean bG;
    public n z;
    public boolean A;
    private boolean bH = false;
    public volatile boolean B = false;
    public boolean C;
    public boolean D;
    public String E;
    public boolean F = false;
    public boolean G;
    public boolean H;
    public int I = 0;
    private volatile float bI = 1.0f;
    public volatile float J = 1.0f;
    public Float K;
    public String L;
    public ArrayList M = new ArrayList();
    public boolean N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public String S;
    public int T = -1;
    public int U = -1;
    public int V = -1;
    public int W = com.corrodinggames.rts.gameFramework.f.a(1, 9000000);
    public int X = 0;
    public boolean Y;
    public float Z;
    boolean aa;
    public float ab;
    public float ac;
    public boolean ad;
    public float ae;
    public boolean af;
    public boolean ag;
    public int ah = -1;
    public int ai = 300;
    public boolean aj;
    public boolean ak;
    public boolean al;
    public ak am = new ak();
    public boolean an;
    public boolean ao = true;
    public int ap;
    public int aq;
    public int ar;
    public static boolean as;
    float at = 0.0f;
    long au;
    public boolean av;
    public int aw = 5;
    public int ax = 5;
    public ah ay = new ah();
    public String az = null;
    public k aA;
    public k aB;
    public a aC = new a();
    Thread aD;
    ao aE;
    Thread aF;
    ao aG;
    Timer aH;
    av aI;
    Thread aJ;
    af aK;
    c aL;
    public ConcurrentLinkedQueue aM = new ConcurrentLinkedQueue();
    ConcurrentLinkedQueue aN = new ConcurrentLinkedQueue();
    boolean aO;
    volatile int aP = 1;
    Object aQ = new Object();
    String aR;
    String aS;
    public String aT;
    public Boolean aU;
    public Boolean aV;
    public boolean aW;
    public boolean aX = false;
    boolean aY = false;
    boolean aZ = false;
    public float ba;
    public boolean bb;
    public boolean bc;
    public boolean bd;
    public boolean be;
    public boolean bf;
    public String bg;
    public String bh = null;
    public ConcurrentLinkedQueue bi = new ConcurrentLinkedQueue();
    public com.corrodinggames.rts.game.e bj;
    public com.corrodinggames.rts.game.e bk;
    public final Object bl = new Object();
    public boolean bm = false;
    float bn;
    float bo;
    int bp;
    int bq;
    boolean br = false;
    public long bs;
    public long bt;
    boolean bu = false;
    public Socket bv = null;
    public String bw = null;
    public boolean bx;
    boolean by = false;
    boolean bz = false;
    static ArrayList bA;
    boolean bB = false;
    final Object bC = new Object();
    Timer bD;
    public static ae bE;
    an bF;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public aj a(c c2) {
        String string = c2.f();
        long l = System.currentTimeMillis();
        if (string == null) {
            c2.b("Is banned: No target");
            return null;
        }
        ArrayList arrayList = this.M;
        synchronized (arrayList) {
            for (aj aj2 : this.M) {
                if (!string.equals(aj2.a) || aj2.b <= l) continue;
                return aj2;
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean a(c c2, String string, int n2) {
        if (c2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("Ban failed: No connection");
            return false;
        }
        String string2 = c2.f();
        if (string2 == null) {
            c2.b("Ban failed: No target");
            return false;
        }
        aj aj2 = new aj();
        aj2.a = c2.f();
        aj2.b = System.currentTimeMillis() + (long)(n2 * 1000);
        aj2.c = string;
        ArrayList arrayList = this.M;
        synchronized (arrayList) {
            this.b();
            this.M.add(aj2);
        }
        c2.c("Banned " + string2 + " for " + n2 + "s");
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a() {
        ArrayList arrayList = this.M;
        synchronized (arrayList) {
            this.M.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b() {
        ArrayList arrayList = this.M;
        synchronized (arrayList) {
            int n2 = 0;
            long l2 = System.currentTimeMillis();
            Iterator iterator = this.M.iterator();
            while (iterator.hasNext()) {
                ++n2;
                aj aj2 = (aj)iterator.next();
                boolean bl = false;
                if (aj2.b < l2) {
                    bl = true;
                }
                if (n2 > 1000) {
                    bl = true;
                }
                if (!bl) continue;
                iterator.remove();
            }
        }
    }

    public String a(String string) {
        string = string.trim();
        this.y = string = string.replace(" ", "_");
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.y != null && !this.y.equals(l2.bQ.lastNetworkPlayerName)) {
            l2.bQ.lastNetworkPlayerName = this.y;
            l2.bQ.save();
        }
        return string;
    }

    public void a(float f2, String string) {
        if ((double)f2 < 0.1) {
            com.corrodinggames.rts.gameFramework.j.ad.a("setCurrentStepRate:" + f2 + " is too small, source:" + string, true);
            return;
        }
        this.bI = f2;
    }

    public float c() {
        return this.bI;
    }

    public void d() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.ah = l2.bx;
        this.am.b();
        this.an = false;
    }

    public void a(as as2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        as2.c(0);
        this.ay.a(as2);
        as2.a(l2.bB);
        as2.a(l2.bC);
    }

    public void a(k k2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        k2.d();
        this.ay.a(k2);
        l2.bB = k2.f();
        l2.bC = k2.f();
    }

    public ah e() {
        ah ah2;
        if (this.C) {
            ah2 = this.ay;
        } else if (this.H) {
            ah2 = this.ay.c();
        } else {
            ah2 = null;
            com.corrodinggames.rts.gameFramework.l.b("getChangeableSetup", "Clicked but not server or proxy controller");
        }
        return ah2;
    }

    public void f() {
        if (this.F) {
            com.corrodinggames.rts.gameFramework.l.B().bQ.aiDifficulty = this.ay.f;
        }
        if (!this.C && !this.F) {
            return;
        }
        if (this.aW) {
            com.corrodinggames.rts.gameFramework.l.g("updateAIDifficulty with gameHasBeenStarted=true");
        } else {
            for (int j = 0; j < com.corrodinggames.rts.game.n.c; ++j) {
                n n2 = com.corrodinggames.rts.game.n.k(j);
                if (n2 == null) continue;
                this.a(n2);
            }
        }
        this.aq();
    }

    public void a(n n2) {
        if (n2.w) {
            n2.c("aiDifficultyOverride=" + n2.z);
            n2.x = n2.z != null ? n2.z : this.ay.f;
        }
    }

    public boolean b(n n2) {
        String string;
        boolean bl = false;
        if (n2.w && !(string = "AI - " + this.b(n2.C())).equals(n2.v)) {
            n2.v = string;
            bl = true;
        }
        return bl;
    }

    public void a(ah ah2) {
        if (this.C) {
            this.f();
            this.P();
            this.L();
            com.corrodinggames.rts.appFramework.n.o();
        } else if (this.H) {
            this.b(ah2);
        } else {
            com.corrodinggames.rts.gameFramework.l.e("applyChangedSetup but not server or proxy controller");
        }
    }

    private void b(ah ah2) {
        String string;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.l.e("applyProxyControl");
        ah ah3 = this.ay;
        if (!ah3.b.equals(ah2.b)) {
            string = com.corrodinggames.rts.appFramework.i.e(ah2.b);
            string = com.corrodinggames.rts.gameFramework.e.a.o(string);
            l2.bX.k("-map '" + string + "'");
        }
        if (ah3.e != ah2.e) {
            string = !ah2.e ? "true" : "false";
            l2.bX.k("-revealedmap " + string);
        }
        if (ah3.d != ah2.d) {
            string = l2.bX.a(ah2.d);
            l2.bX.k("-fog " + string);
        }
        if (ah3.c != ah2.c) {
            int n2 = l2.bX.e(ah2.c);
            l2.bX.k("-credits " + n2);
        }
        if (!com.corrodinggames.rts.gameFramework.f.k(ah3.h, ah2.h)) {
            l2.bX.k("-income " + com.corrodinggames.rts.gameFramework.f.a(ah2.h, 1));
        }
        if (ah3.i != ah2.i) {
            String string2 = !ah2.i ? "true" : "false";
            l2.bX.k("-nukes " + string2);
        }
        if (ah3.f != ah2.f) {
            l2.bX.k("-ai " + ah2.f);
        }
        if (ah3.g != ah2.g) {
            l2.bX.k("-startingunits " + ah2.g);
        }
        if (ah3.l != ah2.l) {
            String string3 = ah2.l ? "true" : "false";
            l2.bX.k("-sharedControl " + string3);
        }
    }

    public String g() {
        if (this.ay.d == 0) {
            return "No fog";
        }
        if (this.ay.d == 1) {
            return "Basic fog";
        }
        if (this.ay.d == 2) {
            return "Line of Sight";
        }
        return "Unknown";
    }

    public String a(int n2) {
        if (n2 == 0) {
            return "off";
        }
        if (n2 == 1) {
            return "basic";
        }
        if (n2 == 2) {
            return "los";
        }
        return "Unknown";
    }

    public String b(int n2) {
        return this.c(n2);
    }

    public String c(int n2) {
        if (n2 == -2) {
            return "Very Easy";
        }
        if (n2 == -1) {
            return "Easy";
        }
        if (n2 == 0) {
            return "Medium";
        }
        if (n2 == 1) {
            return "Hard";
        }
        if (n2 == 2) {
            return "Very Hard";
        }
        if (n2 == 3) {
            return "Impossible";
        }
        return "Unknown";
    }

    public String h() {
        return this.d(this.ay.g);
    }

    public ArrayList i() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i2 = 1; i2 <= 4; ++i2) {
            arrayList.add(i2);
        }
        arrayList.addAll(com.corrodinggames.rts.game.units.custom.l.s());
        return arrayList;
    }

    public String d(int n2) {
        if (n2 == 1) {
            return "Normal (1 builder)";
        }
        if (n2 == 2) {
            return "Small Army";
        }
        if (n2 == 3) {
            return "3 Engineers";
        }
        if (n2 == 4) {
            return "3 Engineers (No Command Center)";
        }
        if (n2 == 5) {
            return "Experimental Spider";
        }
        if (n2 == 9) {
            return "Custom";
        }
        com.corrodinggames.rts.game.units.custom.l l2 = com.corrodinggames.rts.game.units.custom.l.c(n2);
        if (l2 != null) {
            return l2.e();
        }
        return "Unknown";
    }

    public String j() {
        if (this.ay.c == 0) {
            return "Default ($" + this.k() + ")";
        }
        return "$" + this.k();
    }

    public final int k() {
        return this.e(this.ay.c);
    }

    public int e(int n2) {
        if (n2 == 0) {
            return 4000;
        }
        if (n2 == 1) {
            return 0;
        }
        if (n2 == 2) {
            return 1000;
        }
        if (n2 == 3) {
            return 2000;
        }
        if (n2 == 4) {
            return 5000;
        }
        if (n2 == 5) {
            return 10000;
        }
        if (n2 == 6) {
            return 50000;
        }
        if (n2 == 7) {
            return 100000;
        }
        if (n2 == 8) {
            return 200000;
        }
        return 999;
    }

    public String l() {
        return com.corrodinggames.rts.gameFramework.e.a.o(this.az);
    }

    public void m() {
        m m2 = new m();
        com.corrodinggames.rts.gameFramework.f.b(256);
        com.corrodinggames.rts.gameFramework.j.aq.a(5.0f, 6.0f, 7.0f);
        com.corrodinggames.rts.gameFramework.j.ar.a(5);
        this.bg = com.corrodinggames.rts.gameFramework.j.ar.a();
        this.bf = true;
    }

    public boolean n() {
        return this.aW;
    }

    public boolean o() {
        return this.d.e();
    }

    public synchronized void a(boolean bl, String string, Boolean bl2) {
        this.aV = bl;
        this.aT = string;
        this.aU = bl2;
        com.corrodinggames.rts.appFramework.n.o();
    }

    void a(g g2) {
        for (g g3 : this.bi) {
            if (!g3.a || !g3.c.equals(g2.c) || g3.g != g2.g) continue;
            g3.o = this.p();
        }
        g2.o = this.p();
        this.bi.add(g2);
        com.corrodinggames.rts.appFramework.p.l();
    }

    public long p() {
        return System.currentTimeMillis();
    }

    public ad() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.e = l2.c(true);
        this.aR = com.corrodinggames.rts.gameFramework.f.e(40);
        this.aL = new c(this, null);
        this.aL.p = true;
        this.bj = new com.corrodinggames.rts.game.e(-3, false);
        this.bj.v = "SPECTATOR";
        this.bk = new com.corrodinggames.rts.game.e(-1, false);
        this.bk.v = "ADMIN";
    }

    public void q() {
        this.a(false);
    }

    public void r() {
        this.a(true);
    }

    public void s() {
        this.bH = false;
        this.bG = false;
        this.z = null;
        this.p = false;
        this.bs = System.currentTimeMillis();
        this.X = 0;
        this.I = 0;
        this.w = 1L;
        this.a(1.0f, "new");
        this.Z = 10.0f;
        this.N = false;
        this.Q = 10;
        this.R = 0;
        this.Y = false;
        this.aa = false;
        this.al = false;
        this.ak = false;
        this.ab = 0.0f;
        this.ac = 0.0f;
        this.ad = false;
        this.af = false;
        this.aW = false;
        this.aY = false;
        this.aZ = false;
        this.ba = 0.0f;
        this.aX = false;
        this.bb = false;
        this.bc = false;
        this.bd = false;
        this.be = false;
        this.ag = false;
        this.ah = -1;
        this.am.a = 0L;
        this.br = false;
        this.am.a();
        this.an = false;
        this.ao = true;
        this.ap = 0;
        this.aq = 0;
        this.ar = 0;
        this.at = 0.0f;
        this.bn = 0.0f;
        this.bo = 0.0f;
        this.bp = 0;
        this.bq = -1000;
        com.corrodinggames.rts.gameFramework.j.aq.i = 55;
        com.corrodinggames.rts.gameFramework.j.aq.j = 66;
    }

    public void a(boolean bl) {
        this.B = false;
        this.C = false;
        this.f = null;
        this.F = false;
        this.D = false;
        this.E = null;
        this.x = false;
        this.H = false;
        this.G = false;
        this.av = false;
        this.A = false;
        this.s();
        this.S = null;
        this.m = 0;
        this.i = false;
        this.j = 0.0f;
        this.k = 0.0f;
        this.bz = false;
        this.aB = null;
        this.ax = com.corrodinggames.rts.gameFramework.l.B().bQ.teamUnitCapHostedGame;
        if (this.ax < 1) {
            this.ax = 1;
        }
        this.aw = this.ax;
        this.ay.g = 1;
        this.ay.h = 1.0f;
        this.ay.i = false;
        this.ay.j = false;
        this.ay.l = false;
        this.ay.c = 0;
        this.ay.m = false;
        this.ay.n = false;
        this.ay.o = true;
        this.ay.p = false;
        this.ay.q = 0;
        this.a();
        this.aC.c();
        com.corrodinggames.rts.gameFramework.l.B().bS.g();
        if ("<CHAT ONLY>".equals(this.ay.b)) {
            com.corrodinggames.rts.gameFramework.l.e("Chat only map selection - restarting");
            this.ay.a();
        }
        if (!bl) {
            com.corrodinggames.rts.game.n.F();
        }
        String string = com.corrodinggames.rts.game.units.custom.ag.b(this.o);
    }

    public void t() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void b(String string) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.l.e("Disconnect: " + string);
        if (this.C) {
            this.ar();
            com.corrodinggames.rts.gameFramework.j.n.d();
            if (this.aE != null) {
                this.aE.b();
                try {
                    if (this.aD != null) {
                        this.aD.join();
                    }
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
                this.aE = null;
                this.aD = null;
            }
            if (this.aG != null) {
                this.aG.b();
                try {
                    if (this.aF != null) {
                        this.aF.join();
                    }
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
                this.aG = null;
                this.aF = null;
            }
            if (this.aH != null) {
                this.aH.cancel();
                this.aH = null;
                this.aI = null;
            }
            if (this.aK != null) {
                this.aK.b();
                this.aK = null;
                this.aJ = null;
            }
        }
        this.q(string);
        com.corrodinggames.rts.gameFramework.o.a.a().j();
        Object object = this.bl;
        synchronized (object) {
            this.B = false;
            this.C = false;
            this.F = false;
            this.f = null;
            try {
                this.wait(50L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            this.aW = false;
            l2.cb.e();
            l2.e();
            this.am();
            this.bm = false;
            this.bl.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void u() {
        Object object = this.bl;
        synchronized (object) {
            if (!this.B) {
                return;
            }
            this.bm = true;
            try {
                this.bl.wait();
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    public void b(c c2) {
        this.aM.remove(c2);
    }

    private synchronized void ay() {
        Iterator iterator = this.aM.iterator();
        while (iterator.hasNext()) {
            c c2 = (c)iterator.next();
            if (!c2.a) continue;
            iterator.remove();
        }
    }

    void a(byte[] byArray, c c2) {
        if (!com.corrodinggames.rts.gameFramework.l.ax()) {
            Log.d("RustedWarfare", "Ignoring incoming resync tagged as debug only");
        } else {
            if (c2.u) {
                Log.d("RustedWarfare", "Ignoring desync client save, as past desync was already saved");
                return;
            }
            c2.u = true;
            Log.d("RustedWarfare", "Saving client save for debugging");
            String string = "desyncs/";
            String string2 = "desync_" + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy HH.mm.ss") + "_" + c2.c;
            File file = new File(string + string2);
            file.getParentFile().mkdirs();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(byArray);
                fileOutputStream.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    public void v() {
        if (this.br) {
            return;
        }
        com.corrodinggames.rts.gameFramework.l.e("Adding quick resync command");
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = l2.cf.b();
        e2.i = com.corrodinggames.rts.game.n.i;
        e2.r = true;
        e2.u = 200;
        l2.bX.a(e2);
        this.br = true;
    }

    public void w() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        as as2 = new as();
        try {
            l2.ca.a(as2);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        try {
            as2.a();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        byte[] byArray = as2.d();
        as2.h();
        if (this.C) {
            for (c c2 : this.aM) {
                if (!c2.w) continue;
                c2.w = false;
                c2.v = false;
                this.a(c2, byArray, this.l, false);
            }
        }
        com.corrodinggames.rts.gameFramework.l.e("Loading quick resync save data (bytes:" + byArray.length + ")");
        k k2 = new k(byArray);
        l2.a("Game resync (quick)...", true);
        int n2 = l2.bx;
        int n3 = l2.by;
        l2.ca.a(k2, true, true, true);
        l2.bx = n2;
        l2.by = n3;
        this.X = l2.bx + 1;
        this.ag = false;
        this.ah = this.X + 1;
        this.am.a = 0L;
        for (c c3 : this.aM) {
            c3.v = false;
        }
        this.br = false;
        ++this.ar;
        this.bn = 0.0f;
        this.bo = 0.0f;
        if (this.bp < 1) {
            ++this.bp;
        }
        this.bq = l2.bx;
    }

    public synchronized void x() {
        for (c c2 : this.aM) {
            if (c2.w) {
                throw new RuntimeException("Player: " + c2.e() + " has complete desync");
            }
            if (c2.v) {
                throw new RuntimeException("Player: " + c2.e() + " has minor desync");
            }
            if (c2.x != 0) continue;
            throw new RuntimeException("Player: " + c2.e() + " has no sync matches");
        }
    }

    private synchronized void e(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        this.bn += f2;
        for (Object object : this.aM) {
            if (((c)object).w) {
                bl = true;
            }
            if (!((c)object).v) continue;
            if (this.g) {
                com.corrodinggames.rts.gameFramework.l.e("desync_count:" + ((c)object).y + " lastResyncTimer:" + this.bn);
            }
            if (((c)object).y >= 4 && !(this.bn > 3600.0f)) continue;
            bl3 = true;
        }
        if (bl3) {
            this.bo += f2;
            if (c && this.bo > 5.0f) {
                bl2 = true;
            }
            if (this.bp == 0) {
                if (this.bo > 60.0f) {
                    bl2 = true;
                }
            } else if (this.bp == 1) {
                if (this.bo > 420.0f) {
                    bl2 = true;
                }
            } else if (this.bp == 2) {
                if (this.bo > 3600.0f) {
                    bl2 = true;
                }
            } else if (this.bp == 3 && this.bo > 14400.0f) {
                bl2 = true;
            }
        }
        if (as && bl2) {
            com.corrodinggames.rts.gameFramework.l.e("disableDesyncFixing==true, running quick resync instead");
            bl2 = false;
            bl = true;
        }
        if (!bl2 && bl) {
            if (b) {
                this.v();
            } else {
                bl2 = true;
            }
        }
        if (bl2) {
            Object object = "";
            for (c c2 : this.aM) {
                if (!c2.w && !c2.v) continue;
                if (!((String)object).equals("")) {
                    object = (String)object + ", ";
                }
                object = (String)object + c2.e();
            }
            this.j("Resyncing game for " + (String)object + "...");
            this.az();
            this.a(this.l, false, true);
        }
    }

    private void az() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.bn = 0.0f;
        this.bo = 0.0f;
        ++this.bp;
        this.bq = l2.bx;
        for (c c2 : this.aM) {
            c2.w = false;
            c2.v = false;
            c2.x = 0;
        }
    }

    public void c(String string) {
        this.q(string);
    }

    private void q(String string) {
        for (c c2 : this.aM) {
            c2.a(string);
        }
        this.aM.clear();
        this.aN.clear();
        this.aP = 1;
        this.aO = false;
    }

    public long y() {
        long l2;
        boolean bl = false;
        if (bl) {
            com.corrodinggames.rts.gameFramework.l.e("New id set:" + this.w + 1);
            com.corrodinggames.rts.gameFramework.l.T();
        }
        if ((l2 = this.w++) == 0L) {
            com.corrodinggames.rts.gameFramework.l.e("getNextUnitId: id==0");
            com.corrodinggames.rts.gameFramework.l.T();
        }
        return l2;
    }

    public long z() {
        return this.w;
    }

    public void a(long l2) {
        this.w = l2;
    }

    public boolean a(boolean bl, int n2) {
        for (c c2 : this.aM) {
            if (!c2.p || !c2.h() || c2.s || c2.D) continue;
            if (bl) {
                this.j("Still waiting on: " + c2.e());
            }
            return false;
        }
        return true;
    }

    public void A() {
        for (c c2 : this.aM) {
            c2.C = false;
            c2.D = false;
        }
    }

    public int B() {
        int n2 = 0;
        for (c c2 : this.aM) {
            if (!c2.p || !c2.h() || c2.s) continue;
            ++n2;
        }
        return n2;
    }

    public int C() {
        ArrayList<com.corrodinggames.rts.game.e> arrayList = new ArrayList<com.corrodinggames.rts.game.e>();
        int n2 = 0;
        for (c c2 : this.aM) {
            if (!c2.p || !c2.h() || c2.s) continue;
            com.corrodinggames.rts.game.e e2 = c2.z;
            if (e2 != null) {
                if (arrayList.contains(e2)) continue;
                arrayList.add(e2);
            }
            ++n2;
        }
        return n2;
    }

    public int D() {
        int n2 = 0;
        for (c c2 : this.aM) {
            if (!c2.p || c2.s) continue;
            ++n2;
        }
        return n2;
    }

    public int E() {
        int n2 = 0;
        n2 += this.C();
        if (!com.corrodinggames.rts.gameFramework.l.ax()) {
            ++n2;
        }
        return n2;
    }

    public void d(String string) {
        Log.b("RustedWarfare", "network:" + string);
    }

    public static void e(String string) {
        com.corrodinggames.rts.gameFramework.l.e("network debug: " + string);
    }

    public void f(String string) {
        Log.d("RustedWarfare", "reportProblem:" + string);
        if (this.aW) {
            this.b(null, -1, null, string);
        } else {
            this.b(null, -1, null, string);
        }
    }

    public static void g(String string) {
        com.corrodinggames.rts.gameFramework.j.ad.a(string, false);
    }

    public static void h(String string) {
        com.corrodinggames.rts.gameFramework.j.ad.a(string, true);
    }

    public static void a(String string, boolean bl) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ad ad2 = l2.bX;
        String string2 = "desync:" + string;
        com.corrodinggames.rts.gameFramework.l.b(string2);
        com.corrodinggames.rts.gameFramework.l.T();
        ++ad2.ap;
        if (ad2.ao) {
            String string3;
            if (ad2.ap > 2 || as) {
                bl = true;
            }
            if (ad2.ap > 10) {
                string3 = "<suppressing desync errors>";
                ad2.ao = false;
                bl = true;
            } else {
                string3 = string2;
            }
            if (bl) {
                string3 = "-i " + string3;
            }
            ad2.m(string3);
        }
    }

    public static void a(String string, String string2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String string3 = string2;
        l2.cb.a(-1, string, string3, l2.bx);
        if (l2.bS != null && l2.bS.h != null) {
            l2.bS.h.a(string, string3);
        } else {
            com.corrodinggames.rts.gameFramework.l.g("interfaceEngine/messageInterface==null");
        }
    }

    public void F() {
    }

    public void a(e e2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e2.c = this.X;
        e2.g();
        l2.cf.b.add(e2);
    }

    public void G() {
        for (c c2 : this.aM) {
            if (c2.p && c2.b() != -2 && c2.b() <= 500 && c2.b() >= 0) continue;
        }
    }

    public void H() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String string = "";
        ArrayList arrayList = com.corrodinggames.rts.game.n.a(true);
        for (n n2 : arrayList) {
            if (n2 == null) continue;
            String string2 = "unnamed";
            if (n2.v != null) {
                string2 = n2.v;
            }
            String string3 = " " + n2.y();
            String string4 = "\u2022";
            string = string + string4 + n2.N().toLowerCase() + " [Team " + n2.h() + "] - " + string2 + string3 + "\n";
        }
        com.corrodinggames.rts.gameFramework.l.e("showPlayerListPopup(): Showing playlist messagebox.");
        l2.c("Players", string);
    }

    public void a(float f2) {
        Object object;
        Object object2;
        int n2;
        int n3;
        Object object3;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.at += f2;
        if (this.aZ) {
            if (this.ba > 0.0f) {
                this.ba -= f2 / 60.0f;
                com.corrodinggames.rts.gameFramework.l.B().bS.a("Returning to battleroom in " + (int)this.ba + "...", 3500);
            } else {
                com.corrodinggames.rts.gameFramework.l.e("Sending returnToBattleroomEvent...");
                this.aZ = false;
                this.i((c)null);
            }
        }
        if (this.aY) {
            this.aD();
        }
        if (this.at > 60.0f) {
            this.G();
            this.at = 0.0f;
        }
        if (this.aW && !this.aX) {
            this.aX = true;
            object3 = com.corrodinggames.rts.game.n.f();
            n3 = 0;
            n2 = 0;
            object2 = ((ArrayList)object3).iterator();
            while (object2.hasNext()) {
                object = (Integer)object2.next();
                int n4 = com.corrodinggames.rts.game.n.a((Integer)object, false);
                if (n4 > n2) {
                    n2 = n4;
                }
                ++n3;
            }
            if (n3 > 2 && n2 <= 1) {
                this.bb = true;
            }
        }
        if (!this.C && !this.bH) {
            this.ad();
            this.bH = true;
        }
        if (this.C) {
            if (!this.aa && this.aW) {
                if (this.a(false, 0)) {
                    this.Z = com.corrodinggames.rts.gameFramework.f.a(this.Z, f2);
                    if (this.Z == 0.0f) {
                        this.aa = true;
                        com.corrodinggames.rts.gameFramework.j.ad.a("", "<All players ready>");
                        this.d.a();
                    }
                } else {
                    this.ab += f2;
                    this.ac += f2;
                    float f3 = 900.0f;
                    if (this.ab > f3) {
                        this.aa = true;
                        com.corrodinggames.rts.gameFramework.j.ad.a("", "Starting game without all players ready!");
                    } else if (this.ac > 180.0f) {
                        this.ac = 0.0f;
                        this.a(true, (int)((f3 - this.ab) / 60.0f));
                    }
                }
            }
            if (this.aa) {
                boolean bl = false;
                if (this.ak) {
                    bl = true;
                }
                if (this.al) {
                    bl = true;
                }
                if (l2.bx >= this.X - this.R && !bl) {
                    n3 = this.X + this.Q;
                    ++this.O;
                    n2 = 0;
                    for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
                        object = com.corrodinggames.rts.game.n.k(i2);
                        if (object == null || ((n)object).V == 0 || ((n)object).B() || ((n)object).V >= 40) continue;
                        n2 = 1;
                    }
                    if (l2.b() != 0 && l2.b() < 40 && !com.corrodinggames.rts.gameFramework.l.ax()) {
                        n2 = 1;
                    }
                    if (n2 != 0) {
                        ++this.P;
                    }
                    if (this.O > 8) {
                        float f4 = 1.0f;
                        if (this.P > 4) {
                            f4 = 2.0f;
                        }
                        if (this.K != null) {
                            f4 = this.K.floatValue();
                        }
                        if (f4 != this.c()) {
                            com.corrodinggames.rts.gameFramework.l.e("Changing step rate to " + f4);
                            object = l2.cf.b();
                            ((e)object).i = com.corrodinggames.rts.game.n.i;
                            ((e)object).r = true;
                            ((e)object).s = f4;
                            this.a((e)object);
                        }
                        this.O = 0;
                        this.P = 0;
                    }
                    object2 = new as();
                    try {
                        ((as)object2).a(n3);
                        int n5 = 0;
                        for (e e2 : l2.cf.b) {
                            if (e2.c != this.X) continue;
                            ++n5;
                        }
                        ((as)object2).a(n5);
                        for (e e2 : l2.cf.b) {
                            if (e2.c != this.X) continue;
                            e2.a((as)object2);
                        }
                    }
                    catch (IOException iOException) {
                        throw new RuntimeException(iOException);
                    }
                    object = ((as)object2).b(10);
                    ((au)object).e = true;
                    this.d((au)object);
                    this.X = n3;
                }
            }
        }
        if (!l2.cf.d.isEmpty()) {
            object3 = l2.cf.d.iterator();
            while (object3.hasNext()) {
                e e3 = (e)object3.next();
                n2 = 0;
                if (n2 != 0) {
                    l2.cf.c.add(e3);
                    object3.remove();
                    continue;
                }
                if (!e3.x) {
                    e3.b();
                }
                if (!e3.a()) continue;
                l2.cf.c.add(e3);
                object3.remove();
            }
        }
        if (!this.C) {
            if (!l2.cf.c.isEmpty()) {
                for (e e4 : l2.cf.c) {
                    if (e4.e()) continue;
                    e4.j();
                    as as2 = new as();
                    try {
                        e4.a(as2);
                    }
                    catch (IOException iOException) {
                        throw new RuntimeException(iOException);
                    }
                    this.d(as2.b(20));
                }
                l2.cf.c.clear();
            }
        } else if (!l2.cf.c.isEmpty()) {
            for (e e5 : l2.cf.c) {
                if (e5.e()) continue;
                if (!e5.l()) {
                    com.corrodinggames.rts.gameFramework.j.ad.g("Skipped command issued from server");
                    continue;
                }
                e5.j();
                this.a(e5);
            }
            l2.cf.c.clear();
        }
        while (!this.aN.isEmpty()) {
            object3 = (au)this.aN.remove();
            try {
                this.a((au)object3);
            }
            catch (IOException iOException) {
                String string = "None";
                object2 = ((au)object3).a;
                if (object2 != null) {
                    string = ((c)object2).g();
                    object = iOException.getMessage();
                    if (object == null) {
                        object = "IO error";
                    }
                    ((c)object2).a((String)object);
                    com.corrodinggames.rts.gameFramework.j.ad.g("IO error on processGamePacket for " + ((c)object2).e());
                }
                com.corrodinggames.rts.gameFramework.l.a("Error on processGamePacket ip:" + string, (Throwable)iOException);
            }
        }
        if (this.C) {
            if (!this.B) {
                com.corrodinggames.rts.gameFramework.l.e("Skipping server updates, not networked");
            } else {
                this.ay();
                if (!this.aj) {
                    this.e(f2);
                }
            }
        }
        if (this.B) {
            object3 = "Game paused.";
            if (this.al) {
                l2.bS.b("Game paused.", 100);
            } else {
                l2.bS.a("Game paused.");
            }
        }
        if (l2.bx < this.X) {
            this.Y = false;
        }
        if (this.bm) {
            this.b("queDisconnect");
        }
    }

    public void b(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2 == null) {
            return;
        }
        if (!this.C && this.B) {
            Object object;
            boolean bl = false;
            Object object2 = this.aM.iterator();
            while (object2.hasNext()) {
                object = (c)object2.next();
                if (!((c)object).p || ((c)object).a) continue;
                bl = true;
            }
            if (this.be && this.n()) {
                l2.bS.b("Game ended by server.");
                com.corrodinggames.rts.appFramework.n.o();
            } else if (!bl && this.n()) {
                l2.bS.b("Server Disconnected.");
                com.corrodinggames.rts.appFramework.n.o();
            }
            if (bl && (this.Y || this.bs + 1000L < System.currentTimeMillis()) && !this.C && (object2 = this.W()) != null && ((c)object2).U > 20000) {
                object = "Receiving network data: " + ((c)object2).V + "/" + ((c)object2).U;
                com.corrodinggames.rts.gameFramework.l.e((String)object);
                l2.bS.d((String)object);
                if (!this.aW && this.bt + 4000L < System.currentTimeMillis()) {
                    this.bt = System.currentTimeMillis();
                    this.o((String)object);
                }
                this.a((c)object2, ((c)object2).V, ((c)object2).U);
            }
        }
    }

    public void c(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.bs = System.currentTimeMillis();
        if (this.B && (this.ah + this.ai < l2.bx || this.ah == -1)) {
            this.d();
            l2.cb.a(this.am);
        }
        if ((this.B || l2.cb.j()) && this.N) {
            this.N = false;
            this.w();
        }
        if (!this.B) {
            return;
        }
        if (this.C && !this.an && this.ah + this.ai / 2 < l2.bx && this.ah != -1) {
            try {
                as as2 = new as();
                as2.a(this.ah);
                as2.a(this.am.a);
                as2.a(this.am.b.size());
                for (al al2 : this.am.b) {
                    as2.a(al2.b);
                }
                au au2 = as2.b(30);
                this.h(au2);
                if (this.g) {
                    com.corrodinggames.rts.gameFramework.l.e("Sent checksum to client [" + this.ah + "]");
                }
                this.an = true;
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
        }
    }

    public boolean I() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bU.e()) {
            if (!this.bu) {
                com.corrodinggames.rts.gameFramework.l.e("shouldGameBePaused: isGoingToBlockThisFrame()==true: " + l2.bU.f());
            }
            this.bu = true;
            return true;
        }
        if (this.bu) {
            com.corrodinggames.rts.gameFramework.l.e("shouldGameBePaused: isGoingToBlockThisFrame()==false");
        }
        this.bu = false;
        return false;
    }

    public void a(float f2, boolean bl) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bx >= this.X) {
            if (l2.bx > this.X) {
                throw new RuntimeException("game frame:" + l2.bx + " is greater then nest step:" + this.X);
            }
            this.Y = true;
        }
        if (bl && this.I()) {
            this.Y = true;
        }
    }

    public void a(au au2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.b(au2)) {
            this.d("filtered packet (type:" + au2.b + ")");
            return;
        }
        switch (au2.b) {
            case 10: {
                if (this.C) {
                    this.d("we are a server! we don't follow orders");
                    break;
                }
                if (au2.a.t) {
                    this.d("ignoring command");
                    break;
                }
                k k2 = new k(au2);
                int n2 = k2.f();
                int n3 = k2.f();
                for (int i2 = 0; i2 < n3; ++i2) {
                    e e2 = l2.cf.b();
                    e2.c = this.X;
                    e2.a(k2);
                    this.a(e2);
                }
                if (n2 < this.X) {
                    String string = "New nextBlockingFrame:" + n2 + " is smaller than current step:" + this.X;
                    com.corrodinggames.rts.gameFramework.j.ad.g(string);
                }
                this.X = n2;
                break;
            }
            case 20: {
                if (!this.C) {
                    this.d("we are not a server! skipping");
                    break;
                }
                k k3 = new k(au2);
                c c2 = au2.a;
                if (c2.a()) break;
                com.corrodinggames.rts.game.e e3 = c2.z;
                if (e3 == null) {
                    this.d("Player is null for message ADDCLIENTCOMMAND, skipping");
                    break;
                }
                e e4 = l2.cf.b();
                e4.a(k3);
                e4.p = e3;
                if (e4.r) {
                    this.d("Got system action from client, ignoring (" + c2.c + ")");
                    e4.r = false;
                }
                if (e4.c() == null) {
                    com.corrodinggames.rts.gameFramework.j.ad.g("Invalid command from '" + e3.v + "', no team found");
                    break;
                }
                if (!e4.l()) {
                    com.corrodinggames.rts.gameFramework.j.ad.g("Ignored command from '" + e3.v + "', check failed");
                    break;
                }
                this.a(e4);
                break;
            }
            case 35: {
                k k4 = new k(au2);
                k4.d();
                int n4 = k4.f();
                int n5 = k4.f();
                float f2 = k4.g();
                float f3 = k4.g();
                if (!this.C && (double)f2 < 0.1) {
                    com.corrodinggames.rts.gameFramework.j.ad.a("resync packet with setCurrentStepRate:" + f2 + " is too small", true);
                }
                c c3 = au2.a;
                if (c3.t) {
                    this.d("ignoring resync command");
                    break;
                }
                boolean bl = k4.e();
                boolean bl2 = k4.e();
                if (bl2) {
                    if (!this.C) {
                        this.d("we are not a server, but got a debug game save! skipping");
                        break;
                    }
                    byte[] byArray = k4.c("gameSave");
                    this.a(byArray, c3);
                    break;
                }
                com.corrodinggames.rts.gameFramework.l.e("Reloading from network save");
                if (bl && !this.C) {
                    this.a(false, true, false);
                }
                byte[] byArray = k4.c("gameSave");
                com.corrodinggames.rts.gameFramework.l.e("Save size: " + byArray.length);
                if (this.l) {
                    this.a(byArray, c3);
                }
                l2.cb.a(byArray, l2.bx, n4, n5, f2, f3);
                k k5 = new k(byArray);
                l2.a("Resyncing game from server...", true);
                l2.ca.a(k5, true, true, true);
                l2.Z();
                ++this.ar;
                l2.bx = n4;
                l2.by = n5;
                this.X = n4 + 1;
                this.ag = false;
                this.ah = this.X + 1;
                this.am.a = 0L;
                if ((double)f2 < 0.1) {
                    com.corrodinggames.rts.gameFramework.j.ad.a("resync setCurrentStepRate:" + f2 + " is too small", true);
                }
                this.a(f2, "rsync");
                this.J = f3;
                break;
            }
            case 30: {
                c c4 = au2.a;
                k k6 = new k(au2);
                int n6 = k6.f();
                long l3 = k6.i();
                if (this.ag) {
                    this.d("PACKET_SYNCCHECKSUM: skipping frame:" + n6 + ", we were told to wait for resync");
                    break;
                }
                as as2 = new as();
                as2.c(0);
                as2.a(n6);
                as2.a(this.ah);
                if (this.ah != n6 || this.am.a == 0L) {
                    as2.a(false);
                    Log.d("RustedWarfare", "got remoteSyncFrame for:" + n6 + " needed:" + this.ah + " lastSyncCheckSum:" + this.am.a);
                } else {
                    int n7;
                    as2.a(true);
                    Log.d("RustedWarfare", "Running checksum");
                    as2.a(l3);
                    as2.a(this.am.a);
                    boolean bl = false;
                    if (l3 != this.am.a) {
                        com.corrodinggames.rts.gameFramework.j.ad.g("Checksum doesn't match. Got:" + l3 + " expected:" + this.am.a);
                        bl = true;
                        com.corrodinggames.rts.gameFramework.l.e("--- Desync for frame: " + n6 + " ---");
                        for (Object object : com.corrodinggames.rts.game.n.c()) {
                            ((n)object).t();
                        }
                    } else {
                        ++this.aq;
                    }
                    if ((n7 = k6.f()) != this.am.b.size()) {
                        Log.d("RustedWarfare", "checkSumSize!=syncCheckList.size()");
                    }
                    as2.e("checkList");
                    as2.a(n7);
                    as2.a(this.am.b.size());
                    for (al al2 : this.am.b) {
                        long l4 = k6.i();
                        as2.a(l4);
                        as2.a(al2.b);
                        if (l4 == al2.b || !al2.c) continue;
                        com.corrodinggames.rts.gameFramework.j.ad.g("[" + n6 + "] check(" + al2.a + "): " + l4 + "!=" + al2.b);
                        bl = true;
                    }
                    as2.a("checkList");
                    as2.a(bl);
                }
                if (this.C) break;
                au au3 = as2.b(31);
                this.a(c4, au3);
                break;
            }
            case 31: {
                if (!this.C) {
                    this.d("we are not a server, but got PACKET_SYNCCHECKSUM_STATUS");
                    break;
                }
                c c5 = au2.a;
                k k7 = new k(au2);
                k7.d();
                int n8 = k7.f();
                int n9 = k7.f();
                boolean bl = k7.e();
                if (bl) {
                    long l5 = k7.i();
                    long l6 = k7.i();
                    k7.b("checkList");
                    k7.f();
                    int n10 = k7.f();
                    if (n10 != this.am.b.size()) {
                        Log.d("RustedWarfare", "checkSumSize!=syncCheckList.size()");
                    }
                    for (al al3 : this.am.b) {
                        long l7;
                        long l8 = k7.i();
                        if (l8 == (l7 = k7.i())) continue;
                        com.corrodinggames.rts.gameFramework.l.b(al3.a + " Checksum [" + n8 + "]. server:" + l8 + " client:" + l7);
                    }
                    k7.d("checkList");
                    boolean bl3 = k7.e();
                    if (this.bq >= n8) {
                        this.d("Not marking desync, already resynced before frame: " + this.bq + "<=" + n8);
                        break;
                    }
                    if (!c5.v && bl3) {
                        ++c5.y;
                    }
                    c5.v = bl3;
                    if (!bl3) {
                        if (this.g) {
                            com.corrodinggames.rts.gameFramework.l.e("checksum: client checksum match [" + n8 + "]");
                        }
                        ++c5.x;
                        break;
                    }
                    com.corrodinggames.rts.gameFramework.l.e("client:" + c5.e() + " desync [" + n8 + "]");
                    if (!this.aj || this.ak) break;
                    com.corrodinggames.rts.gameFramework.j.ad.g("pauseOnDesync is active, pausing");
                    this.ak = true;
                    break;
                }
                if (!this.g) break;
                com.corrodinggames.rts.gameFramework.l.e("checksum for:" + c5.e() + " frameMatch==false client:" + n9 + " server:[" + n8 + "]");
                break;
            }
            default: {
                this.d("we did not handle packet:" + au2.b);
            }
        }
    }

    public synchronized boolean b(au au2) {
        if (this.C) {
            c c2 = au2.a;
            if (c2 == null) {
                return false;
            }
            if (!c2.p && au2.b != 105 && au2.b != 110 && au2.b != 111 && au2.b != 108 && au2.b != 160) {
                return true;
            }
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public synchronized void c(au au2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.b(au2)) {
            this.d("filtered packet (type:" + au2.b + ")");
            return;
        }
        switch (au2.b) {
            case 105: {
                this.d("got PACKET_GET_SERVER_INFO");
                if (this.C) break;
                this.d("we are not a server! skipping");
                break;
            }
            case 106: {
                boolean bl;
                if (this.C) {
                    this.d("we are a server! we don't follow orders");
                    break;
                }
                k k2 = new k(au2);
                c c2 = au2.a;
                k2.l();
                k2.f();
                this.ay.a = (ai)k2.b(ai.class);
                this.ay.b = k2.l();
                this.ay.c = k2.f();
                this.ay.d = k2.f();
                this.ay.e = k2.e();
                this.ay.f = k2.f();
                byte by = k2.d();
                this.G = k2.e();
                this.H = k2.e();
                this.av = true;
                if (by >= 1) {
                    this.aw = k2.f();
                    this.ax = k2.f();
                }
                if (by >= 2) {
                    this.ay.g = k2.f();
                    this.ay.h = k2.g();
                    this.ay.i = k2.e();
                    this.ay.j = k2.e();
                }
                if (by >= 3 && (bl = k2.e())) {
                    try {
                        com.corrodinggames.rts.game.units.custom.l.a(k2);
                        this.x = true;
                    }
                    catch (bd bd2) {
                        this.b("Missing unit:" + bd2.getMessage() + " d:" + bd2.b);
                        this.b("Server sync mismatch", bd2.getMessage());
                        if (!com.corrodinggames.rts.gameFramework.l.av()) {
                            l2.i(bd2.getMessage());
                        }
                        String string = "Server sync mismatch";
                        if (bd2.a != null) {
                            string = bd2.a;
                        }
                        l2.d(string, bd2.getMessage());
                        break;
                    }
                }
                if (by >= 4) {
                    this.ay.l = k2.e();
                }
                if (by >= 5) {
                    this.ay.m = k2.e();
                }
                if (by >= 6) {
                    this.ay.n = k2.e();
                }
                if (by >= 7) {
                    this.ay.o = k2.e();
                    this.ay.p = k2.e();
                }
                if (by >= 8) {
                    this.ay.q = k2.f();
                }
                com.corrodinggames.rts.appFramework.n.o();
                break;
            }
            case 115: {
                boolean bl;
                int n2;
                if (this.C) {
                    this.d("we are a server! we don't follow orders");
                    break;
                }
                k k3 = new k(au2);
                k3.b(au2.a.E);
                c c3 = au2.a;
                int n3 = k3.f();
                n n4 = null;
                int n5 = 8;
                boolean bl2 = false;
                if (k3.c() >= 90) {
                    n2 = 0;
                    if (k3.c() >= 141) {
                        n2 = 1;
                        bl2 = k3.e();
                    }
                    n5 = k3.f();
                    com.corrodinggames.rts.game.n.b(n5, false);
                    k3.a("teams", n2 != 0);
                    if (n5 > com.corrodinggames.rts.game.n.c) {
                        throw new IOException("Cannot load:" + n5 + " teams");
                    }
                } else if (this.aW) {
                    com.corrodinggames.rts.gameFramework.j.ad.g("Warning old team system used in started game, stream version:" + k3.c());
                }
                for (n2 = 0; n2 < n5; ++n2) {
                    n n6 = com.corrodinggames.rts.game.n.k(n2);
                    boolean bl3 = k3.e();
                    if (!bl3) {
                        if (n6 != null) {
                            if (this.aW) {
                                com.corrodinggames.rts.gameFramework.j.ad.g("Warning team:" + n2 + " removed while game is running");
                            }
                            n6.I();
                        }
                    } else {
                        int n7 = k3.f();
                        if (n6 == null) {
                            if (this.aW) {
                                com.corrodinggames.rts.gameFramework.j.ad.g("Warning team:" + n2 + " added while game is running");
                            }
                            if (!this.C && n6 instanceof com.corrodinggames.rts.game.a.a) {
                                com.corrodinggames.rts.gameFramework.j.ad.g("Warning we are a client with an AI team");
                            }
                            n6 = new com.corrodinggames.rts.game.e(n2);
                        }
                        if (bl2) {
                            n6.a(k3);
                        } else {
                            n6.a(k3, this.aW);
                        }
                    }
                    if (n6 == null || n6.k != n3) continue;
                    n4 = n6;
                }
                if (k3.c() >= 90) {
                    k3.d("teams");
                }
                this.z = n4;
                this.ay.d = k3.f();
                this.ay.c = k3.f();
                this.ay.e = k3.e();
                this.ay.f = k3.f();
                n2 = k3.d();
                this.aw = k3.f();
                this.ax = k3.f();
                if (n2 >= 2) {
                    this.ay.g = k3.f();
                    this.ay.h = k3.g();
                    this.ay.i = k3.e();
                    this.ay.j = k3.e();
                }
                if (n2 >= 3 && (bl = k3.e())) {
                    try {
                        com.corrodinggames.rts.game.units.custom.l.a(k3);
                        this.x = true;
                    }
                    catch (bd bd3) {
                        this.b("Missing unit:" + bd3.getMessage() + " d:" + bd3.b);
                        this.b("Connection Failed", bd3.getMessage());
                        if (!com.corrodinggames.rts.gameFramework.l.av()) {
                            l2.i(bd3.getMessage());
                        }
                        l2.d("Connection Failed", bd3.getMessage());
                        break;
                    }
                }
                if (n2 >= 4) {
                    this.ay.l = k3.e();
                }
                if (n2 >= 5) {
                    this.al = k3.e();
                }
                com.corrodinggames.rts.appFramework.n.o();
                break;
            }
            case 116: {
                if (this.C) {
                    this.d("we are a server! we don't follow orders");
                    break;
                }
                k k4 = new k(au2);
                c c4 = au2.a;
                int n8 = k4.f();
                boolean bl = k4.e();
                if (!bl || this.be) break;
                this.be = bl;
                break;
            }
            case 160: {
                String string;
                k k5 = new k(au2);
                c c5 = au2.a;
                String string2 = k5.l();
                int n9 = k5.f();
                int n10 = k5.f();
                int n11 = 1;
                if (n9 >= 1) {
                    n11 = k5.f();
                }
                if (c5.i) {
                    com.corrodinggames.rts.gameFramework.l.e("steam: request info packet");
                }
                if (n9 >= 2 && (string = k5.j()) != null) {
                    c5.c("Using query string: " + string);
                    c5.o = string;
                }
                if (n9 >= 3) {
                    k5.l();
                }
                if (n9 >= 4) {
                    string = k5.l();
                    String string3 = k5.l();
                    if (com.corrodinggames.rts.gameFramework.l.ax()) {
                        c5.c("Misc: " + string3);
                    }
                }
                this.g(c5);
                break;
            }
            case 161: {
                if (this.C) {
                    this.d("we are a server! we don't PREREGISTER_INFO");
                    break;
                }
                k k6 = new k(au2);
                c c6 = au2.a;
                if (c6.i) {
                    com.corrodinggames.rts.gameFramework.l.e("steam: got info packet");
                }
                String string = k6.l();
                int n12 = k6.f();
                int n13 = k6.f();
                int n14 = k6.f();
                String string4 = k6.l();
                this.S = k6.l();
                c6.E = n13;
                if (n12 >= 1) {
                    this.T = k6.f();
                }
                if (n12 >= 2) {
                    this.U = k6.f();
                    this.V = k6.f();
                }
                if (this.bz) {
                    this.d("PACKET_SEND_PREREGISTER_INFO: Register connection has already been sent (resending)");
                }
                this.h(c6);
                break;
            }
            case 163: {
                if (this.C) {
                    this.d("we are already a server");
                    break;
                }
                k k7 = new k(au2);
                k7.d();
                int n15 = k7.f();
                int n16 = k7.f();
                String string = k7.j();
                this.d("Relay version: " + n15);
                break;
            }
            case 170: {
                this.d("Got 'become server' packet");
                if (this.C) {
                    this.d("we are already a server");
                    break;
                }
                c c7 = au2.a;
                k k8 = new k(au2);
                byte by = k8.d();
                boolean bl = k8.e();
                boolean bl4 = k8.e();
                String string = k8.j();
                boolean bl5 = k8.e();
                boolean bl6 = k8.e();
                String string5 = k8.j();
                boolean bl7 = false;
                if (by >= 1) {
                    bl7 = k8.e();
                }
                String string6 = null;
                if (by >= 2) {
                    string6 = k8.j();
                }
                this.d("Multicast:" + bl7);
                c7.r = bl7;
                if (bl) {
                    c7.q = true;
                }
                if (bl4) {
                    c7.s = true;
                }
                this.D = true;
                this.E = string5;
                l2.bX.n = null;
                l2.bX.o = bl5;
                l2.bX.q = bl6;
                this.c(false);
                if (string6 != null) {
                    if (this.z != null) {
                        this.z.P = string6;
                    } else {
                        com.corrodinggames.rts.gameFramework.l.e("Become server: No local team");
                    }
                }
                if (l2.bX.q) {
                    // empty if block
                }
                if (string != null) {
                    l2.bQ.networkServerId = string;
                }
                if (l2.bx > 60) {
                    this.aa = true;
                }
                if (this.x || this.aW) break;
                com.corrodinggames.rts.gameFramework.l.e("enableAllCustomUnitsPossible mods:" + this.o);
                com.corrodinggames.rts.game.units.custom.ag.b(this.o);
                this.x = true;
                break;
            }
            case 172: {
                c c8 = au2.a;
                if (!c8.q) {
                    this.d("forwarding not allowed on this connection");
                    break;
                }
                this.d("got FORWARD_CLIENT_ADD");
                k k9 = new k(au2);
                byte by = k9.d();
                int n17 = k9.f();
                String string = k9.l();
                String string7 = k9.j();
                String string8 = null;
                if (by >= 1) {
                    string8 = k9.j();
                }
                if (this.a(c8, n17) != null) {
                    this.d("Not adding client:" + n17 + " already exists");
                    break;
                }
                c c9 = this.a(c8, n17, string, string8);
                if (c9 == null || string7 == null) break;
                com.corrodinggames.rts.game.e e2 = com.corrodinggames.rts.game.n.b(string);
                if (e2 == null) {
                    this.d("PACKET_FORWARD_CLIENT_ADD: Failed to find existing player with id:" + string);
                    for (n n18 : com.corrodinggames.rts.game.n.c()) {
                        if (n18 == null) continue;
                        this.d("option: " + n18.v + " - " + n18.P + " - localPlayer:" + (this.z == n18));
                    }
                    break;
                }
                e2.O = string7;
                break;
            }
            case 173: {
                c c10 = au2.a;
                if (!c10.q) {
                    this.d("forwarding not allowed on this connection");
                    break;
                }
                this.d("got FORWARD_CLIENT_REMOVE");
                k k10 = new k(au2);
                byte by = k10.d();
                int n19 = k10.f();
                String string = null;
                c c11 = this.a(c10, n19);
                if (c11 == null) break;
                this.b(c11, string);
                break;
            }
            case 174: {
                c c12 = au2.a;
                if (!c12.q) {
                    this.d("forwarding not allowed on this connection");
                    break;
                }
                k k11 = new k(au2);
                int n20 = k11.f();
                byte[] byArray = k11.t();
                c c13 = this.a(c12, n20);
                if (c13 == null) {
                    this.d("PACKET_FORWARD_CLIENT_FROM failed, cannot find client");
                    break;
                }
                if (!(c13.d instanceof h)) {
                    this.d("PACKET_FORWARD_CLIENT_FROM failed, socket is wrong type");
                    break;
                }
                h h2 = (h)c13.d;
                h2.d.a(byArray);
                break;
            }
            case 175: {
                this.d("got PACKET_FORWARD_CLIENT_TO");
                break;
            }
            case 176: {
                this.d("got PACKET_FORWARD_CLIENT_TO_REPEATED");
                break;
            }
            case 178: {
                this.d("got PACKET_RECONNECT_TO");
                c c14 = au2.a;
                if (this.C && !c14.q) {
                    this.d("we are a server, ");
                    break;
                }
                k k12 = new k(au2);
                k12.d();
                int n21 = k12.f();
                boolean bl = k12.e();
                int n22 = k12.f();
                ArrayList<String> arrayList = new ArrayList<String>();
                for (int i2 = 0; i2 < n22; ++i2) {
                    String string = k12.l();
                    arrayList.add(string);
                }
                this.a(arrayList, bl);
                break;
            }
            case 110: {
                String string;
                aj aj2;
                this.d("got REGISTER_CONNECTION");
                if (!this.C) {
                    this.d("we are not a server! skipping");
                    break;
                }
                k k13 = new k(au2);
                c c15 = au2.a;
                String string9 = k13.l();
                int n23 = k13.f();
                int n24 = k13.f();
                int n25 = k13.f();
                String string10 = k13.l();
                String string11 = k13.j();
                String string12 = null;
                c15.E = n24;
                if (n23 >= 1) {
                    c15.L = k13.l();
                }
                if (n23 >= 2) {
                    string12 = k13.l();
                }
                int n26 = -1;
                if (n23 >= 3) {
                    n26 = k13.f();
                }
                String string13 = "MISSING";
                if (n23 >= 4) {
                    string13 = k13.l();
                }
                String string14 = "";
                if (n23 >= 5) {
                    string14 = k13.l();
                }
                if (string10.length() > 20) {
                    this.a(c15, "Your username is too long");
                    c15.a("kicked");
                    break;
                }
                if ((string10 = com.corrodinggames.rts.gameFramework.j.ad.p(string10)).length() < 2) {
                    this.a(c15, "Your username is too short");
                    c15.a("kicked");
                    break;
                }
                com.corrodinggames.rts.game.e e3 = null;
                if (string12 != null && (e3 = com.corrodinggames.rts.game.n.a(string12)) != null) {
                    this.d("Existing player: " + e3.k + " - " + e3.v);
                }
                if ((aj2 = this.a(c15)) != null) {
                    com.corrodinggames.rts.gameFramework.l.e("Connection banned for " + aj2.b() + " more seconds");
                    String string15 = aj2.a();
                    this.a(c15, string15);
                    c15.a("kicked");
                    break;
                }
                String string16 = this.d.a(c15, string10, n24, n25, c15.L, e3);
                if (string16 != null) {
                    this.a(c15, string16);
                    c15.a("kicked");
                    break;
                }
                if (n24 < this.e && !this.v) {
                    this.a(c15, "Game is out of date, please update to v" + l2.u());
                    c15.a("kicked");
                    break;
                }
                if (n24 > this.e && !this.v) {
                    this.a(c15, "Your client is newer then the server. Server is on: v" + l2.u());
                    c15.a("kicked");
                    break;
                }
                if (!this.v && n26 != l2.z()) {
                    com.corrodinggames.rts.gameFramework.l.e("New Player kicked: Unit checksum mismatch: clientUnitsChecksum=" + n26 + " game.getAllUnitsChecksum():" + l2.z());
                    this.a(c15, "Your core units are different to the server's core units. Game can not be synchronized");
                    c15.a("kicked");
                    break;
                }
                if (!this.v && !(string = this.g(c15.M)).equals(string13)) {
                    com.corrodinggames.rts.gameFramework.l.e("New Player kicked: Integrity Check Failed: expectedResponse=" + string + " clientResponse=" + string13);
                    this.a(c15, "Your 'Rusted Warfare' client is different to the server. Game can not be synchronized.");
                    c15.a("kicked");
                    break;
                }
                if (!this.aW && this.ay.p) {
                    this.a(c15, "Room is locked. New players cannot join this server.");
                    c15.a("kicked");
                    break;
                }
                if (this.aW && e3 == null && !this.s) {
                    this.a(c15, "A game has already been started on this server");
                    c15.a("kicked");
                    break;
                }
                if (this.n != null && e3 == null && !(string = com.corrodinggames.rts.gameFramework.f.e(this.n)).equals(string11)) {
                    if (string11 == null) {
                        com.corrodinggames.rts.gameFramework.l.b("processSystemPacket", "Player tried to join but needs a password");
                    } else {
                        com.corrodinggames.rts.gameFramework.l.b("processSystemPacket", "Player tried to join but had an incorrect password");
                    }
                    this.d(c15);
                    break;
                }
                string = this.h(this.W);
                if (!string.equals(string14)) {
                    c15.c("no extra");
                    c15.N = true;
                }
                if (c15.z == null) {
                    Object object = this.bC;
                    synchronized (object) {
                        int n27 = e3 == null ? com.corrodinggames.rts.game.n.G() : e3.k;
                        if (n27 == -1 && !this.v) {
                            this.a(c15, "No free slots on server");
                            c15.a("no free slots");
                            break;
                        }
                        String string17 = this.d.a(c15, string10);
                        if (string17 != null) {
                            this.a(c15, string17);
                            c15.a("kicked");
                        } else {
                            Object object2;
                            com.corrodinggames.rts.gameFramework.j.aq.a(c15);
                            if (!this.v && c15.O) {
                                this.a(c15, "");
                                c15.a("kicked");
                                break;
                            }
                            String string18 = null;
                            if (e3 != null) {
                                c15.z = e3;
                                object2 = "";
                                if (this.aW) {
                                    object2 = e3.b() ? " (Spectator)" : " (Team " + e3.h() + ")";
                                }
                                this.j("'" + c15.z.v + "' reconnected. " + (String)object2);
                                c15.w = true;
                                string18 = e3.v;
                                e3.P = c15.m;
                            } else {
                                if (this.v && n27 == -1) {
                                    c15.z = new com.corrodinggames.rts.game.e(-3);
                                } else {
                                    c15.z = new com.corrodinggames.rts.game.e(n27);
                                    c15.z.r = n27 % 2;
                                }
                                if (this.aW && this.s) {
                                    c15.w = true;
                                }
                            }
                            if (e3 == null && string10 != null) {
                                object2 = this.ax();
                                for (int i3 = 0; i3 < 10; ++i3) {
                                    boolean bl = false;
                                    String string19 = string10;
                                    if (i3 > 0) {
                                        string19 = string19 + "(" + i3 + ")";
                                    }
                                    Iterator iterator = ((ArrayList)object2).iterator();
                                    while (iterator.hasNext()) {
                                        n n28 = (n)iterator.next();
                                        if (!string19.equalsIgnoreCase(n28.v)) continue;
                                        bl = true;
                                    }
                                    if (bl) continue;
                                    string10 = string19;
                                    break;
                                }
                            }
                            c15.z.v = string10;
                            c15.z.O = string12;
                            c15.z.P = c15.m;
                            c15.E = n24;
                            com.corrodinggames.rts.gameFramework.l.b("processSystemPacket", "New player: " + string10 + ", networkVersion:" + c15.E + " existing:" + (e3 != null));
                            c15.p = true;
                            if (e3 == null) {
                                this.d.a(c15.z);
                            }
                            com.corrodinggames.rts.appFramework.n.o();
                            this.e(c15);
                            this.c(c15);
                            this.d.c(c15, string10, string18);
                            if ((e3 != null || this.s) && this.aW) {
                                boolean bl = true;
                                this.a(c15, bl);
                            }
                        }
                        break;
                    }
                }
                com.corrodinggames.rts.gameFramework.l.b("processSystemPacket", "This connection already has a player");
                break;
            }
            case 113: {
                if (this.C) {
                    this.d("we are a server! skipping: " + au2.b);
                    break;
                }
                com.corrodinggames.rts.gameFramework.j.ad.a(bE);
                break;
            }
            case 117: {
                c c16 = au2.a;
                if (this.C && !c16.q) {
                    this.d("we are a server! skipping: " + au2.b);
                    break;
                }
                k k14 = new k(au2);
                k14.d();
                int n29 = k14.f();
                String string = k14.l();
                ae ae2 = new ae();
                ae2.d = true;
                ae2.c = n29;
                ae2.b = string;
                com.corrodinggames.rts.gameFramework.j.ad.a(ae2);
                break;
            }
            case 118: {
                break;
            }
            case 140: {
                if (!this.C) {
                    this.d("we are not a server! skipping");
                    break;
                }
                c c17 = au2.a;
                k k15 = new k(au2);
                com.corrodinggames.rts.game.e e4 = c17.z;
                if (e4 == null) {
                    if (c17.q) {
                        this.d("Allowing message from non player on forwarding connection");
                        e4 = this.bk;
                    } else {
                        this.d("player is null for message, skipping");
                        break;
                    }
                }
                String string = k15.l();
                k15.d();
                string = com.corrodinggames.rts.gameFramework.j.ad.i(string);
                if (!this.d.a(c17, e4.v, string)) break;
                if (this.aC.a(c17, 60000) > this.h) {
                    if (com.corrodinggames.rts.gameFramework.f.a(c17.g, System.nanoTime()) > 60000L) {
                        c17.g = System.nanoTime();
                        this.j("Anti-spam: Too many messages from '" + c17.e() + "'");
                    }
                    if (!this.g) break;
                    com.corrodinggames.rts.gameFramework.l.e("extraDebug:" + string);
                    break;
                }
                this.a(c17, e4, e4.v, string);
                this.d.b(c17, e4.v, string);
                this.b(c17, e4, e4.v, string);
                break;
            }
            case 141: {
                Object object;
                if (this.C) {
                    object = au2.a;
                    if (!((c)object).q) {
                        this.d("error, we are a server but got: PACKET_RECEIVE_CHAT_FROM_SERVER");
                        break;
                    }
                }
                object = new k(au2);
                String string = ((k)object).l();
                byte by = ((k)object).d();
                String string20 = ((k)object).j();
                ((k)object).f();
                int n30 = -1;
                if (by >= 3) {
                    n30 = ((k)object).f();
                }
                this.b(null, n30, string20, string);
                break;
            }
            case 122: {
                if (this.C) {
                    this.d("error, we are a server but got: PACKET_RETURN_TO_BATTLEROOM");
                    break;
                }
                this.aC();
                break;
            }
            case 120: {
                if (this.C) {
                    this.d("error, we are a server but got: PACKET_START_GAME");
                    break;
                }
                k k16 = new k(au2);
                k16.d();
                this.ay.a = (ai)k16.b(ai.class);
                if (this.ay.a == com.corrodinggames.rts.gameFramework.j.ai.c) {
                    this.aA = k16.u();
                } else if (this.ay.a == com.corrodinggames.rts.gameFramework.j.ai.b) {
                    this.aB = k16.u();
                }
                this.az = k16.l();
                this.aB();
                break;
            }
            case 150: {
                if (this.C) {
                    this.d("error, we are a server but got: PACKET_SEND_KICK");
                    break;
                }
                k k17 = new k(au2);
                String string = k17.l();
                string = com.corrodinggames.rts.gameFramework.h.a.c(string);
                this.d("we got kicked, reason:" + string);
                this.b("I was kicked");
                this.b("Kicked", "Kicked: " + string);
                l2.d("Kicked", "Kicked: " + string);
                l2.i("Kicked: " + string);
                break;
            }
            case 151: {
                int n31;
                String string;
                c c18 = au2.a;
                if (this.C && !c18.q) {
                    this.d("error, we are a server but got: 151");
                    break;
                }
                long l3 = com.corrodinggames.rts.gameFramework.br.a();
                k k18 = new k(au2);
                int n32 = k18.f();
                int n33 = k18.f();
                if (k18.e()) {
                    com.corrodinggames.rts.gameFramework.j.aq.i = k18.f();
                }
                if (k18.e()) {
                    com.corrodinggames.rts.gameFramework.j.aq.j = k18.f();
                }
                String string21 = "";
                if (n33 == 0) {
                    string21 = "" + com.corrodinggames.rts.gameFramework.j.aq.i;
                }
                if (n33 == 1) {
                    string21 = "" + com.corrodinggames.rts.gameFramework.j.aq.j;
                }
                if (n33 == 2) {
                    string21 = this.g(com.corrodinggames.rts.gameFramework.j.aq.i);
                }
                if (n33 == 3) {
                    string21 = com.corrodinggames.rts.gameFramework.f.c(com.corrodinggames.rts.gameFramework.j.aq.i + "|" + com.corrodinggames.rts.gameFramework.j.aq.j);
                }
                if (n33 == 4) {
                    string21 = com.corrodinggames.rts.gameFramework.f.c(com.corrodinggames.rts.gameFramework.j.aq.i + "|" + com.corrodinggames.rts.gameFramework.j.aq.j);
                }
                if (n33 == 5 || n33 == 6) {
                    string = k18.l();
                    String string22 = k18.l();
                    n31 = k18.f();
                    if (n33 == 6) {
                        string22 = string22 + com.corrodinggames.rts.gameFramework.j.aq.i;
                    }
                    if (n31 > 10000000) {
                        string21 = "max";
                    } else {
                        string21 = "-1";
                        for (int i4 = 0; i4 <= n31; ++i4) {
                            if (!com.corrodinggames.rts.gameFramework.f.c(string22 + i4).equals(string)) continue;
                            string21 = "" + i4;
                            break;
                        }
                    }
                }
                if (n33 == 7) {
                    string = k18.l();
                    int n34 = k18.f();
                    if (n34 > 10000) {
                        string21 = "max";
                    } else {
                        string21 = "";
                        for (n31 = 0; n31 < n34; ++n31) {
                            string21 = string21 + string;
                        }
                    }
                }
                float f2 = com.corrodinggames.rts.gameFramework.br.a(l3);
                as as2 = new as();
                as2.a(n32);
                as2.a(n33);
                as2.c(string21);
                as2.a(f2);
                this.a(c18, as2.b(152));
                break;
            }
            case 112: {
                if (!this.C) {
                    this.d("we are not a server! skipping");
                    break;
                }
                c c19 = au2.a;
                k k19 = new k(au2);
                c19.C = k19.e();
                c19.D = k19.e();
                break;
            }
            case 108: {
                c c20 = au2.a;
                k k20 = new k(au2);
                long l4 = k20.i();
                k20.d();
                as as3 = new as();
                as3.a(l4);
                as3.c(1);
                int n35 = l2.b();
                if (n35 > 130) {
                    n35 = 130;
                }
                as3.c(n35);
                au au3 = as3.b(109);
                this.a(c20, au3);
                break;
            }
            case 109: {
                int n36;
                if (!this.C) {
                    this.d("we are not a server! skipping");
                    break;
                }
                long l5 = System.currentTimeMillis();
                c c21 = au2.a;
                k k21 = new k(au2);
                long l6 = k21.i();
                byte by = k21.d();
                byte by2 = 0;
                if (by >= 1) {
                    by2 = k21.d();
                }
                c21.A = n36 = (int)(l5 - l6);
                c21.B = l5;
                if (c21.z != null) {
                    c21.z.W = n36;
                    c21.z.X = l5;
                    c21.z.V = by2;
                }
                if (c21.q && this.C && this.D && this.z != null) {
                    this.z.W = n36;
                    this.z.X = l5;
                }
                if (this.aW) break;
                com.corrodinggames.rts.appFramework.n.o();
                break;
            }
            case 4: {
                c c22 = au2.a;
                k k22 = new k(au2);
                byte by = k22.d();
                k22.f();
                k22.f();
                break;
            }
            case 111: {
                k k23 = new k(au2);
                c c23 = au2.a;
                String string = null;
                try {
                    string = k23.l();
                }
                catch (IOException iOException) {
                    com.corrodinggames.rts.gameFramework.l.a("Error reading disconnect reason", (Throwable)iOException);
                }
                this.d("Got a disconnect packet:" + string);
                if (c23 != null) {
                    c23.a(false, false, string);
                }
                if (this.C) break;
                break;
            }
            default: {
                this.d("we did not handle packet:" + au2.b);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public static String i(String string) {
        if (string == null) {
            return null;
        }
        if (string.length() > 250) {
            string = string.substring(0, 250);
        }
        if (string.contains("\n")) {
            string = string.replace("\n", "?");
        }
        string = string.replace("\u0000", ".");
        boolean bl = false;
        for (char n2 : string.toCharArray()) {
            if (!Character.isISOControl(n2)) continue;
            bl = true;
            break;
        }
        if (bl) {
            void var5_8;
            Object object = new StringBuilder();
            char[] cArray = string.toCharArray();
            int n2 = cArray.length;
            boolean bl2 = false;
            while (var5_8 < n2) {
                char c2 = cArray[var5_8];
                if (!Character.isISOControl(c2)) {
                    ((StringBuilder)object).append(c2);
                }
                ++var5_8;
            }
            string = ((StringBuilder)object).toString();
        }
        return string;
    }

    public void J() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bS.g.m();
    }

    public void K() {
        this.b((String)null, (String)null);
    }

    public void b(String string, String string2) {
        com.corrodinggames.rts.gameFramework.l.e("closeBattleroom..");
        com.corrodinggames.rts.appFramework.n.a(string, string2);
        this.d.d();
    }

    public synchronized void L() {
        for (c c2 : this.aM) {
            if (!c2.p) continue;
            this.c(c2);
        }
    }

    public synchronized void c(c c2) {
        if (!this.C) {
            this.d("sendServerInfo: we are not a server!");
            return;
        }
        as as2 = new as();
        try {
            as2.c("com.corrodinggames.rts");
            as2.a(this.e);
            as2.a(this.ay.a);
            if (this.v) {
                as2.c("<CHAT ONLY>");
            } else {
                as2.c(this.ay.b == null ? "<NULL>" : com.corrodinggames.rts.gameFramework.e.a.o(this.ay.b));
            }
            as2.a(this.ay.c);
            as2.a(this.ay.d);
            as2.a(this.ay.e);
            as2.a(this.ay.f);
            as2.c(8);
            as2.a(this.d.a(c2));
            boolean bl = this.d.b(c2);
            as2.a(bl);
            as2.a(this.aw);
            as2.a(this.ax);
            as2.a(this.ay.g);
            as2.a(this.ay.h);
            as2.a(this.ay.i);
            as2.a(this.ay.j);
            if (this.v) {
                as2.a(false);
            } else {
                as2.a(true);
                com.corrodinggames.rts.game.units.custom.l.a(as2);
            }
            as2.a(this.ay.l);
            as2.a(this.ay.m);
            as2.a(this.ay.n);
            as2.a(this.ay.o);
            as2.a(this.ay.p);
            as2.a(this.ay.q);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.a(c2, as2.b(106));
    }

    public synchronized void a(c c2, String string) {
        if (!this.C) {
            this.d("sendKick: we are not a server!");
            return;
        }
        this.d("kicking client reason:" + string);
        as as2 = new as();
        try {
            as2.c(string);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.a(c2, as2.b(150));
    }

    public synchronized void d(c c2) {
        if (!this.C) {
            this.d("sendIncorrectPassword: we are not a server!");
            return;
        }
        this.d("sendIncorrectPassword");
        as as2 = new as();
        try {
            as2.a(0);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.a(c2, as2.b(113));
    }

    public void M() {
        if (this.C) {
            n n2;
            int n3;
            for (n3 = 0; n3 < com.corrodinggames.rts.game.n.f; ++n3) {
                n2 = com.corrodinggames.rts.game.n.k(n3);
                if (n2 == null) continue;
                n2.ac = this.v ? 0 : (n2.b() ? 100 : n2.r);
                if (n2.b()) {
                    n2.D = -1;
                    continue;
                }
                int n4 = n2.S();
                if (n2.C != null) {
                    n4 = n2.C;
                } else if (this.a(n4, (n)null)) {
                    n4 = -1;
                }
                n2.D = n4;
            }
            for (n3 = 0; n3 < com.corrodinggames.rts.game.n.f; ++n3) {
                n2 = com.corrodinggames.rts.game.n.k(n3);
                if (n2 == null || n2.D != -1 || n2.b()) continue;
                n2.D = this.N();
            }
        }
    }

    public int N() {
        for (int i2 = 0; i2 < 10; ++i2) {
            if (this.f(i2)) continue;
            return i2;
        }
        return -1;
    }

    public boolean f(int n2) {
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.f; ++i2) {
            n n3 = com.corrodinggames.rts.game.n.k(i2);
            if (n3 == null || n3.D != n2 || n3.b()) continue;
            return true;
        }
        return false;
    }

    public boolean a(int n2, n n3) {
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.f; ++i2) {
            n n4 = com.corrodinggames.rts.game.n.k(i2);
            if (n4 == null || n4 == n3 || n4.C == null || n4.C != n2 || n4.b()) continue;
            return true;
        }
        return false;
    }

    public void O() {
        if (this.C) {
            long l2 = System.currentTimeMillis();
            int n2 = com.corrodinggames.rts.gameFramework.l.B().by;
            if (this.z != null && !this.D) {
                this.z.W = -99;
                this.z.X = l2;
            }
            this.M();
            for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
                n n3 = com.corrodinggames.rts.game.n.k(i2);
                if (n3 == null) continue;
                boolean bl = this.z == n3;
                n3.c(bl);
                if (!this.aW) {
                    // empty if block
                }
                if (!this.aW || this.F || n3.w) continue;
                boolean bl2 = false;
                if (n3.B()) {
                    bl2 = true;
                }
                long l3 = 60000L;
                if (n3.Z > 180000) {
                    l3 = 160000L;
                }
                boolean bl3 = false;
                if (this.aa) {
                    if (n3.Y == -1L) {
                        n3.Y = l2;
                        n3.Z = n2;
                    }
                    if ((this.ak || this.al) && !n3.ab) {
                        n3.Y = l2;
                        n3.Z = n2;
                    }
                    if (n3.Y + l3 < l2) {
                        bl3 = true;
                    }
                }
                if (n3.ab != bl3) {
                    n3.ab = bl3;
                }
                if (bl3) {
                    bl2 = true;
                    if (!n3.aa) {
                        boolean bl4;
                        boolean bl5 = bl4 = n3.G || n3.F || n3.J || n3.b();
                        if (!bl4) {
                            n3.aa = true;
                        }
                    }
                }
                if (n3.J == bl2) continue;
                if (!(!bl2 || n3.G || n3.F || n3.I || n3.b())) {
                    String string = "-t [Sharing control due to disconnect]";
                    if (bl3) {
                        string = "-t [Sharing control due to afk]";
                    }
                    com.corrodinggames.rts.gameFramework.l.e(n3.v + " - " + string);
                    int n4 = com.corrodinggames.rts.game.n.a(n3.r, true);
                    if (n4 > 1) {
                        this.a(null, n3, n3.v, string);
                    }
                }
                n3.J = bl2;
            }
        }
    }

    public void P() {
        if (this.au == 0L) {
            this.au = System.currentTimeMillis();
        }
    }

    public void Q() {
        this.au = 0L;
        this.e((c)null);
    }

    public void e(c c2) {
        if (!this.C) {
            this.d("sendUpdatePlayer: we are not a server!");
            return;
        }
        this.O();
        for (c c3 : this.aM) {
            int n2;
            if (!c3.p) continue;
            as as2 = new as(c3.E);
            try {
                int n3;
                as2.a(c3.c());
                n2 = com.corrodinggames.rts.game.n.c;
                boolean bl = false;
                if (as2.g() >= 90) {
                    n3 = 0;
                    if (as2.g() >= 141) {
                        n3 = 1;
                        if (this.aW && c3.Q) {
                            bl = true;
                        }
                        as2.a(bl);
                    }
                    as2.a(n2);
                    as2.a("teams", n3 != 0);
                } else {
                    n2 = 8;
                    if (!this.v) {
                        this.d("sendUpdatePlayer: warning saving with lower team count");
                    }
                }
                for (n3 = 0; n3 < n2; ++n3) {
                    n n4 = com.corrodinggames.rts.game.n.k(n3);
                    as2.a(n4 != null);
                    if (n4 == null) continue;
                    int n5 = 0;
                    if (n4 instanceof com.corrodinggames.rts.game.a.a) {
                        n5 = 1;
                    }
                    as2.a(n5);
                    if (bl) {
                        n4.c(as2);
                        continue;
                    }
                    n4.b(as2);
                }
                if (as2.g() >= 90) {
                    as2.a("teams");
                }
                as2.a(this.ay.d);
                as2.a(this.ay.c);
                as2.a(this.ay.e);
                as2.a(this.ay.f);
                as2.c(5);
                as2.a(this.aw);
                as2.a(this.ax);
                as2.a(this.ay.g);
                as2.a(this.ay.h);
                as2.a(this.ay.i);
                as2.a(this.ay.j);
                as2.a(false);
                as2.a(this.ay.l);
                as2.a(this.al);
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
            n2 = -1;
            if (c2 == c3 && c3.E <= 26) {
                n2 = 1000;
            }
            c3.Q = true;
            this.a(c3, as2.a(115, n2));
        }
    }

    public void a(c c2, int n2, int n3) {
        as as2 = new as();
        try {
            as2.c(0);
            as2.a(n2);
            as2.a(n3);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.a(c2, as2.b(4));
    }

    public synchronized boolean R() {
        if (this.S()) {
            this.p = true;
            this.ay.d = 0;
            return true;
        }
        return false;
    }

    public synchronized boolean S() {
        if (this.B) {
            this.b("Started singleplayer");
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.r();
        this.B = true;
        this.C = true;
        this.F = true;
        this.ay.a = l2.an();
        this.ay.b = l2.am();
        this.aa();
        this.z = l2.bs;
        com.corrodinggames.rts.appFramework.n.o();
        this.m = l2.bQ.networkPort;
        this.d("singleplayer server started");
        return true;
    }

    private void aA() {
        this.ay.q = com.corrodinggames.rts.gameFramework.f.a(1, 1000000000);
    }

    public synchronized boolean b(boolean bl) {
        if (this.B) {
            throw new RuntimeException("networking already started");
        }
        this.q();
        this.B = true;
        this.C = true;
        this.aa();
        this.aA();
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.c(bl);
        com.corrodinggames.rts.appFramework.n.o();
        this.m = l2.bQ.networkPort;
        com.corrodinggames.rts.gameFramework.o.a.a().i();
        this.aE = new ao(this);
        try {
            this.aE.a(false);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            l2.a("Could not open tcp port:" + this.m + ", check this port is not in use or change the port in the game settings", 1);
            this.b("Could not open tcp port");
            return false;
        }
        this.aD = new Thread(this.aE);
        this.aD.setDaemon(true);
        this.aD.start();
        boolean bl2 = true;
        if (bl2) {
            this.aG = new ao(this);
            try {
                this.aG.a(true);
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                l2.a("Could not open udp port:" + this.m + ", check this port is not in use or change the port in the game settings", 1);
                this.b("Could not open udp port");
                return false;
            }
            this.aF = new Thread(this.aG);
            this.aF.start();
        }
        this.am();
        if (this.q) {
            com.corrodinggames.rts.gameFramework.j.n.b();
        }
        this.aV = null;
        if (r) {
            com.corrodinggames.rts.gameFramework.j.n.a();
        }
        this.d("server started");
        return true;
    }

    public void c(boolean bl) {
        this.C = true;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.z == null) {
            int n2;
            com.corrodinggames.rts.game.e e2 = null;
            if (!bl) {
                n2 = com.corrodinggames.rts.game.n.G();
                if (n2 == -1) {
                    throw new RuntimeException("playerId is -1 for server player");
                }
            } else {
                e2 = this.bk;
                n2 = this.bk.k;
            }
            if (e2 == null) {
                e2 = new com.corrodinggames.rts.game.e(n2);
                e2.v = this.y;
                l2.bs = e2;
            }
            this.z = e2;
        }
        if (this.aI == null) {
            com.corrodinggames.rts.gameFramework.l.e("pingerTask starting");
            this.aI = new av(this);
            this.aH = new Timer();
            this.aH.schedule((TimerTask)this.aI, 100L, 100L);
        } else {
            com.corrodinggames.rts.gameFramework.l.e("pingerTask already active");
        }
        com.corrodinggames.rts.appFramework.n.o();
    }

    public boolean T() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bQ.udpInMultiplayer;
    }

    public an a(String string, boolean bl, Runnable runnable) {
        an an2 = new an(string, bl, runnable);
        an2.b();
        return an2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Socket b(String string, boolean bl) {
        InetSocketAddress inetSocketAddress;
        Object object;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.l.e("Connect to server: " + string + " (force tcp:" + bl + ")");
        boolean bl2 = false;
        String string2 = string.trim();
        if (string2.startsWith("get|")) {
            Object object2;
            Object object3;
            Object object4;
            boolean bl3;
            int n2;
            String string3;
            String[] stringArray = string2.split("\\|");
            try {
                String string4 = stringArray[0];
                string3 = stringArray[1];
                n2 = Integer.parseInt(stringArray[2]);
                bl3 = Boolean.parseBoolean(stringArray[3]);
                int n3 = Integer.parseInt(stringArray[4]);
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
                String string5 = "Bad server connect string";
                throw new IOException(string5);
            }
            if (bl3) {
                l2.bX.n = null;
                object4 = new Object();
                object3 = new ad$1(object4);
                com.corrodinggames.rts.gameFramework.l.e("Asking for password..");
                object2 = object4;
                synchronized (object2) {
                    com.corrodinggames.rts.gameFramework.j.ad.a((ae)object3);
                    try {
                        object4.wait();
                    }
                    catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                if (l2.bX.n == null) {
                    com.corrodinggames.rts.gameFramework.l.b("No password entered");
                    throw new ag();
                }
                com.corrodinggames.rts.gameFramework.l.e("Password has been entered");
            }
            object4 = null;
            if (bl3 && (object4 = l2.bX.n) == null) {
                throw new IOException("This server requires a password but no password was provided");
            }
            object3 = new Object();
            object2 = new ad$2(object3);
            Object object5 = object3;
            synchronized (object5) {
                com.corrodinggames.rts.gameFramework.j.n.a((w)object2, string3, n2, (String)object4);
                try {
                    object3.wait(15000L);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
            if (((w)object2).b != null) {
                throw new IOException(((w)object2).b);
            }
            if (((w)object2).a == null) {
                throw new IOException("Failed to get game server info.");
            }
            object5 = com.corrodinggames.rts.gameFramework.j.ad.b(((w)object2).a, bl);
            return object5;
        }
        if (string2.toLowerCase(Locale.ENGLISH).endsWith(".relay")) {
            string2 = string2 + ".corrodinggames.com";
        }
        if (string2.startsWith("[TCP]")) {
            string2 = string2.substring("[TCP]".length());
            bl = true;
        }
        if (!(string2.length() <= 4 || string2.contains(":") || string2.contains(".") || string2.equals("localhost") || string2.contains("/") || string2.contains("\\"))) {
            String string6 = ".relay.corrodinggames.com";
            String string7 = "" + string2.charAt(0);
            String string8 = string7 + string6 + "/" + string2;
            com.corrodinggames.rts.gameFramework.l.e("Converting connect string to: " + string8);
            string2 = string8;
        }
        l2.bX.L = null;
        if (string2.contains("/") || string2.contains("\\")) {
            int n4 = string2.indexOf("/");
            int n5 = string2.indexOf("\\");
            if (n4 == -1) {
                n4 = string2.length();
            }
            if (n5 == -1) {
                n5 = string2.length();
            }
            int n6 = com.corrodinggames.rts.gameFramework.f.c(n4, n5);
            object = string2.substring(n6 + 1);
            if (!((String)(object = ((String)object).trim())).equals("")) {
                l2.bX.L = object;
            }
            string2 = string2.substring(0, n6);
        }
        String string9 = string2;
        int n7 = 5123;
        String[] stringArray = string2.split(":");
        if (stringArray.length > 1) {
            string9 = null;
            for (int i2 = 0; i2 < stringArray.length - 1; ++i2) {
                string9 = string9 == null ? "" : string9 + ":";
                string9 = string9 + stringArray[i2];
            }
            object = stringArray[stringArray.length - 1];
            try {
                n7 = Integer.parseInt((String)object);
            }
            catch (NumberFormatException numberFormatException) {
                String string10 = "Bad port number:" + (String)object;
                numberFormatException.printStackTrace();
                throw new IOException(string10);
            }
        }
        if (!bl && l2.bX.T()) {
            bl2 = true;
        }
        int n8 = 7000;
        com.corrodinggames.rts.gameFramework.l.e("");
        com.corrodinggames.rts.gameFramework.l.e("===============================");
        com.corrodinggames.rts.gameFramework.l.e("Connect to: " + string2);
        if (!bl2) {
            object = new Socket();
            com.corrodinggames.rts.gameFramework.l.e("connecting to Server.. (tcp)");
        } else {
            object = new a.a.h();
            com.corrodinggames.rts.gameFramework.l.e("connecting to Server.. (udp)");
            n8 = 5000;
        }
        ((Socket)object).setTcpNoDelay(true);
        try {
            inetSocketAddress = new InetSocketAddress(InetAddress.getByName(string9), n7);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            String string11 = "Incorrect server format";
            com.corrodinggames.rts.gameFramework.l.b("IllegalArgumentException.." + string11);
            illegalArgumentException.printStackTrace();
            throw new IOException(string11, illegalArgumentException);
        }
        try {
            ((Socket)object).connect(inetSocketAddress, n8);
        }
        catch (UnknownHostException unknownHostException) {
            String string12 = "Failed to connect to host";
            if (bl2) {
                string12 = string12 + " (udp)";
            }
            com.corrodinggames.rts.gameFramework.l.e("UnknownHostException.." + string12);
            unknownHostException.printStackTrace();
            throw new IOException(string12, unknownHostException);
        }
        catch (IOException iOException) {
            String string13 = "Failed to connect to host";
            if (bl2) {
                string13 = string13 + " (udp)";
            }
            string13 = string13 + " - " + iOException.getMessage();
            com.corrodinggames.rts.gameFramework.l.e("IOException.." + string13);
            iOException.printStackTrace();
            throw new IOException(string13, iOException);
        }
        return object;
    }

    public void U() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.f.a.f f2 = com.corrodinggames.rts.gameFramework.f.a.f.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.multiplayerReconnect.message", new Object[0]), false);
        f2.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.resume", new Object[0]), new ad$3(this, f2));
        f2.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.reconnect", new Object[0]), new ad$4(this, f2));
        f2.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.disconnect", new Object[0]), new ad$5(this, f2, l2));
        l2.bS.a(f2);
        this.bx = true;
    }

    public synchronized boolean V() {
        Socket socket = this.bv;
        if (socket == null) {
            com.corrodinggames.rts.gameFramework.l.e("reconnectToServer: lastConnectedTo==null");
            return false;
        }
        com.corrodinggames.rts.gameFramework.l.e("reconnectToServer attempted");
        if (this.B) {
            com.corrodinggames.rts.gameFramework.l.e("reconnectToServer: disconnecting");
            this.b("reconnecting");
        }
        if (socket.getInetAddress() == null) {
            com.corrodinggames.rts.gameFramework.l.e("reconnectToServer: lastConnectedTo.getInetAddress()==null");
            return false;
        }
        String string = socket.getInetAddress().getHostAddress();
        int n2 = socket.getPort();
        String string2 = string + ":" + n2;
        com.corrodinggames.rts.gameFramework.l.e("reconnectToServer: connecting to: " + string2);
        try {
            boolean bl = false;
            Socket socket2 = com.corrodinggames.rts.gameFramework.j.ad.b(string2, bl);
            boolean bl2 = this.a(socket2);
            return bl2;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return false;
        }
        catch (ag ag2) {
            ag2.printStackTrace();
            return false;
        }
    }

    public synchronized boolean a(Socket socket) {
        if (this.B) {
            this.b("starting new");
        }
        if (socket == null) {
            throw new RuntimeException("connectedSocket==null");
        }
        this.q();
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.m = socket.getPort();
        this.B = true;
        this.C = false;
        this.d("connected to Server..");
        c c2 = new c(this, socket);
        c2.p = true;
        c2.d();
        this.aM.add(c2);
        this.f(c2);
        this.am();
        this.bv = socket;
        return true;
    }

    public c c(n n2) {
        for (c c2 : this.aM) {
            if (c2.z != n2) continue;
            return c2;
        }
        return null;
    }

    public c d(n n2) {
        for (c c2 : this.aM) {
            if (c2.a || c2.z != n2) continue;
            return c2;
        }
        return null;
    }

    public c W() {
        if (this.C) {
            return null;
        }
        for (c c2 : this.aM) {
            if (c2.a) continue;
            return c2;
        }
        return null;
    }

    public void d(au au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToAll, not networked");
            return;
        }
        this.i(au2);
    }

    private void i(au au2) {
        for (c c2 : this.aM) {
            if (!c2.p || c2.a || c2.s) continue;
            c2.a(au2);
        }
    }

    public void e(au au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToAllIncludingRelay, not networked");
            return;
        }
        for (c c2 : this.aM) {
            if (!c2.p || c2.a) continue;
            c2.a(au2);
        }
    }

    public void f(au au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToServer, not networked");
            return;
        }
        if (this.C) {
            throw new RuntimeException("We are a server");
        }
        this.d(au2);
    }

    public void g(au au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToClients, not networked");
            return;
        }
        if (!this.C) {
            throw new RuntimeException("We are not a server");
        }
        this.e(au2);
    }

    public void h(au au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketToClients, not networked");
            return;
        }
        if (!this.C) {
            throw new RuntimeException("We are not a server");
        }
        this.d(au2);
    }

    public void a(c c2, au au2) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping sendPacketOnConnection, not networked");
            return;
        }
        c2.a(au2);
    }

    public void X() {
        if (this.C) {
            this.d("registerConnection: We are a server");
        }
        for (c c2 : this.aM) {
            this.h(c2);
        }
    }

    public void Y() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bQ.networkClientId = null;
        if (this.S == null) {
            com.corrodinggames.rts.gameFramework.l.e("generateNewClientId: serverUUID==null");
            this.S = "x";
        }
        this.Z();
        l2.bQ.save();
    }

    public String Z() {
        String string;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl = false;
        if (l2.bQ.networkClientId == null) {
            bl = true;
        }
        if (!this.by) {
            this.by = true;
            if (com.corrodinggames.rts.gameFramework.l.av() && !(string = this.ak()).equals(l2.bQ.networkClientIdMachineKey)) {
                if (l2.bQ.networkClientIdMachineKey != null) {
                    com.corrodinggames.rts.gameFramework.l.e("Machine appears to have changed: " + l2.bQ.networkClientIdMachineKey + " vs " + string);
                }
                l2.bQ.networkClientIdMachineKey = string;
                bl = true;
            }
        }
        if (bl) {
            com.corrodinggames.rts.gameFramework.l.e("new networkClientId needed");
            l2.bQ.networkClientId = UUID.randomUUID().toString();
            l2.bQ.save();
        }
        string = l2.bQ.networkClientId;
        if (this.S == null) {
            throw new RuntimeException("getOwnClientIdHashed: serverUUID==null");
        }
        String string2 = com.corrodinggames.rts.gameFramework.f.e(string + this.S);
        return string2;
    }

    public void aa() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bQ.networkServerId = UUID.randomUUID().toString();
        l2.bQ.save();
    }

    public String ab() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bQ.networkServerId == null) {
            this.aa();
        }
        return l2.bQ.networkServerId;
    }

    public String ac() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.C) {
            return l2.bQ.networkServerId;
        }
        return this.S;
    }

    public void f(c c2) {
        as as2 = new as();
        try {
            int n2 = 4;
            int n3 = 1;
            if (com.corrodinggames.rts.gameFramework.l.av()) {
                n3 = 2;
            }
            if (com.corrodinggames.rts.gameFramework.l.aZ) {
                n3 = 3;
            }
            as2.c("com.corrodinggames.rts");
            as2.a(n2);
            as2.a(this.e);
            as2.a(n3);
            as2.b(this.L);
            as2.c(this.y);
            as2.c(com.corrodinggames.rts.gameFramework.h.a.c());
            String string = "";
            if (com.corrodinggames.rts.gameFramework.l.aT) {
                string = string + "d";
            }
            as2.c(string);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.a(c2, as2.b(160));
    }

    public void g(c c2) {
        as as2 = new as();
        try {
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            as2.c("com.corrodinggames.rts");
            as2.a(2);
            as2.a(this.e);
            as2.a(l2.c(true));
            as2.c(l2.l());
            as2.c(this.ab());
            as2.a(c2.M);
            as2.a(this.W);
            as2.a(0);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.a(c2, as2.b(161));
    }

    public void h(c c2) {
        com.corrodinggames.rts.gameFramework.l.e("sendRegisterConnection...");
        as as2 = new as();
        try {
            as2.c("com.corrodinggames.rts");
            as2.a(5);
            as2.a(this.e);
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            as2.a(l2.c(true));
            as2.c(this.y);
            String string = null;
            if (this.n != null) {
                string = com.corrodinggames.rts.gameFramework.f.e(this.n);
            }
            as2.b(string);
            as2.c(l2.l());
            as2.c(this.Z());
            as2.a(l2.z());
            as2.c(this.g(this.T));
            as2.c(this.h(this.U));
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.a(c2, as2.b(110));
        this.bz = true;
    }

    public String g(int n2) {
        String string = "";
        string = string + "c:" + n2;
        string = string + "m:" + (n2 * 87 + 24);
        string = string + "0:" + this.e(0) * 11 * n2;
        string = string + "1:" + (this.e(1) * 12 + n2);
        string = string + "2:" + this.e(2) * 13 * n2;
        string = string + "3:" + (this.e(3) * 14 + n2);
        string = string + "4:" + this.e(4) * 15 * n2;
        string = string + "5:" + (this.e(5) * 16 + n2);
        string = string + "6:" + this.e(6) * 17 * n2;
        string = string + "7:" + this.e(7) * 18 * n2;
        string = string + "8:" + this.e(8) * 19 * n2;
        string = string + "t1:" + com.corrodinggames.rts.game.n.j.o * 11.0 * (double)n2;
        int n3 = 5 * n2;
        if (this.k() != this.e(this.ay.c)) {
            n3 = 7 * n2;
        }
        string = string + "d:" + n3;
        return string;
    }

    public String h(int n2) {
        return com.corrodinggames.rts.gameFramework.f.h(n2);
    }

    public void ad() {
        if (this.C) {
            throw new RuntimeException("We are a server");
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        as as2 = new as();
        try {
            as2.a(this.bG);
            as2.a(l2.bq);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.f(as2.b(112));
    }

    public void j(String string) {
        if (!this.C) {
            this.d("cannot send sendSystemMessage:" + string + ", we are not a server");
            return;
        }
        if (!this.B || this.F) {
            this.d("cannot send sendSystemMessage:" + string + ", not networked");
            return;
        }
        com.corrodinggames.rts.gameFramework.l.e("sendSystemMessage:" + string);
        this.a(null, null, null, string);
    }

    public void k(String string) {
        this.m("-qc " + string);
    }

    public void l(String string) {
        String string2;
        boolean bl = true;
        String string3 = null;
        if (string != null && ((string2 = string.trim()).startsWith("-") || string2.startsWith(".") || string2.startsWith("_")) && string2.length() >= 2) {
            String string4 = string2.substring(1).trim();
            int n2 = string4.indexOf(" ");
            if (n2 == -1) {
                n2 = string4.length();
            }
            string3 = string4.substring(0, n2).toLowerCase(Locale.ENGLISH);
        }
        if ("share".equals(string3)) {
            bl = false;
        }
        if ("t".equals(string3)) {
            bl = false;
        }
        if (bl) {
            string = "-t " + string;
        }
        this.m(string);
    }

    public void m(String string) {
        if (!this.B) {
            com.corrodinggames.rts.gameFramework.l.e("sendChatMessage: not networked:" + string);
            this.b(null, -1, null, string);
            return;
        }
        if (this.C) {
            this.a(null, this.z, this.y, string);
            this.b(null, this.z, this.y, string);
            return;
        }
        try {
            as as2 = new as();
            as2.c(string);
            as2.c(0);
            this.f(as2.b(140));
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(c c2, n n2, String string, String string2) {
        this.a(c2, n2, string, string2, null);
    }

    public void a(c c2, n n2, String string, String string2, c c3) {
        try {
            boolean bl = false;
            boolean bl2 = false;
            String string3 = com.corrodinggames.rts.gameFramework.j.ad.n(string2);
            if ("t".equalsIgnoreCase(string3)) {
                if (n2 != null) {
                    bl = true;
                    string2 = string2.substring("-t".length());
                    string2 = "[TEAM] " + string2;
                } else {
                    com.corrodinggames.rts.gameFramework.l.b("toOnlyTeams failed team==null");
                }
            }
            if (n2 != null && "surrender".equalsIgnoreCase(string3)) {
                bl = true;
                string2 = "[TEAM] " + string2;
            }
            if (n2 != null && "i".equalsIgnoreCase(string3)) {
                bl2 = true;
                string2 = string2.substring("-i".length());
                string2 = "[INFO] " + string2;
            }
            if (n2 != null && "qc".equalsIgnoreCase(string3)) {
                bl2 = true;
                string2 = string2.substring("-qc".length());
                string2 = "[COMMAND] " + string2;
            }
            if (!bl2 && n2 != null && n2 != this.bj && n2 != this.bk && !this.d.a(c2, n2, string2, bl)) {
                bl2 = true;
            }
            as as2 = new as();
            as2.c(string2);
            as2.c(3);
            as2.b(string);
            as2.a(c2);
            int n3 = -1;
            if (n2 != null) {
                n3 = n2.k;
            }
            as2.a(n3);
            au au2 = as2.b(141);
            if (bl) {
                for (c c4 : this.aM) {
                    com.corrodinggames.rts.game.e e2;
                    if (!c4.p || c4.a || (e2 = c4.z) == null || !e2.d(n2)) continue;
                    c4.a(au2);
                }
                n n4 = this.z;
                if (n4 != null && n4.d(n2)) {
                    this.b(c2, n3, string, string2);
                }
            } else if (bl2) {
                com.corrodinggames.rts.gameFramework.l.b("info message:" + com.corrodinggames.rts.gameFramework.j.ad.c(string, string2));
            } else {
                if (c3 != null) {
                    this.a(c3, au2);
                } else {
                    this.g(au2);
                }
                this.b(c2, n3, string, string2);
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static String n(String string) {
        if (string == null) {
            return null;
        }
        String string2 = string.trim();
        if ((string2.startsWith("-") || string2.startsWith(".") || string2.startsWith("_")) && string2.length() >= 2) {
            String string3 = string2.substring(1).trim();
            int n2 = string3.indexOf(" ");
            if (n2 == -1) {
                n2 = string3.length();
            }
            return string3.substring(0, n2).toLowerCase(Locale.ENGLISH);
        }
        return null;
    }

    public static String c(String string, String string2) {
        if (string != null) {
            return string + ": " + string2;
        }
        return string2;
    }

    public void o(String string) {
        string = com.corrodinggames.rts.gameFramework.h.a.c(string);
        int n2 = -1;
        String string2 = null;
        c c2 = null;
        this.aC.a(n2, string2, string, c2);
        this.d.a(n2, string2, string, c2);
        boolean bl = false;
        if (this.aW) {
            bl = true;
        }
        if (!this.B) {
            bl = true;
        }
        if (bl) {
            com.corrodinggames.rts.gameFramework.j.ad.a(string2, string);
        } else {
            String string3 = com.corrodinggames.rts.gameFramework.j.ad.c(string2, string);
            if (!com.corrodinggames.rts.gameFramework.l.aU) {
                com.corrodinggames.rts.appFramework.n.d(string3);
            }
        }
    }

    private void b(c c2, int n2, String string, String string2) {
        if (!this.B && string2.startsWith("-i ")) {
            return;
        }
        if (!this.B && string2.startsWith("-qc ")) {
            return;
        }
        string2 = com.corrodinggames.rts.gameFramework.h.a.c(string2);
        if (string != null) {
            boolean bl = true;
            if (string2 != null) {
                if (string2.equals("-surrender")) {
                    // empty if block
                }
                if (this.z == null || n2 < 0 || this.z.k == n2) {
                    // empty if block
                }
            }
            if (bl) {
                this.d("New Message", string + ": " + string2);
            }
        }
        c c3 = null;
        if (this.C) {
            c3 = c2;
        }
        this.aC.a(n2, string, string2, c3);
        this.d.a(n2, string, string2, c2);
        boolean bl = false;
        if (this.aW) {
            bl = true;
        }
        if (!this.B) {
            bl = true;
        }
        if (bl) {
            com.corrodinggames.rts.gameFramework.j.ad.a(string, string2);
        } else {
            String string3 = com.corrodinggames.rts.gameFramework.j.ad.c(string, string2);
            if (!com.corrodinggames.rts.gameFramework.l.aU) {
                com.corrodinggames.rts.appFramework.n.d(string3);
            }
        }
    }

    public void a(c c2, byte[] byArray, boolean bl, boolean bl2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        try {
            as as2 = new as();
            as2.c(0);
            as2.a(l2.bx);
            as2.a(l2.by);
            as2.a(this.c());
            as2.a(1.0f);
            as2.a(bl);
            as2.a(bl2);
            as2.e("gameSave");
            as2.b(byArray);
            as2.a("gameSave");
            au au2 = as2.b(35);
            this.a(c2, au2);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public void a(boolean bl, boolean bl2, boolean bl3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        try {
            as as2 = new as();
            as2.c(0);
            as2.a(l2.bx);
            as2.a(l2.by);
            as2.a(this.c());
            as2.a(1.0f);
            as2.a(bl);
            as2.a(bl2);
            as2.e("gameSave");
            l2.ca.a(as2);
            as2.a("gameSave");
            if (bl) {
                // empty if block
            }
            au au2 = as2.b(35);
            this.d(au2);
            if (bl3) {
                if (!this.C) {
                    throw new RuntimeException("sendResyncSave: reloadCreatedSave: We are not a server");
                }
                au2.a = this.aL;
                this.a(au2);
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public boolean ae() {
        this.Q();
        this.L();
        return this.a((c)null, false);
    }

    public boolean a(c c2, boolean bl) {
        com.corrodinggames.rts.gameFramework.l.e("Sending start game....");
        if (!this.C) {
            throw new RuntimeException("We are not a server");
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        as as2 = new as();
        try {
            as2.c(0);
            as2.a(this.ay.a);
            if (this.ay.a == com.corrodinggames.rts.gameFramework.j.ai.c) {
                try {
                    l2.ca.a(this.ay.b, as2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    l2.c("Map error starting game", "Map error: " + iOException.getMessage());
                    return false;
                }
                as2.c("SAVE:" + this.ay.b);
            } else if (this.ay.a == com.corrodinggames.rts.gameFramework.j.ai.b) {
                com.corrodinggames.rts.gameFramework.l.e("Starting with custom map: " + this.l());
                try {
                    com.corrodinggames.rts.game.b.b.a(this.az, as2);
                }
                catch (IOException iOException) {
                    iOException.printStackTrace();
                    l2.c("Map error starting game", "Map error: " + iOException.getMessage());
                    return false;
                }
                as2.c("STEAM:" + this.l());
            } else {
                as2.c(this.l());
            }
            as2.a(bl);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        au au2 = as2.b(120);
        if (c2 == null) {
            this.g(au2);
        } else {
            this.a(c2, au2);
        }
        if (!this.aW) {
            this.aB();
        }
        return true;
    }

    public void af() {
        this.bc = true;
        com.corrodinggames.rts.gameFramework.l.e("onStartGameFailed");
        if (this.C) {
            this.aW = false;
            this.j("Map load failed.");
        } else {
            this.b("Map load failed");
        }
    }

    private void aB() {
        this.aY = false;
        this.aW = true;
        this.bc = false;
        this.bd = false;
        com.corrodinggames.rts.gameFramework.l.e("Starting new network game (" + this.ac() + ")");
        if (this.q && this.C) {
            com.corrodinggames.rts.gameFramework.j.n.c();
        }
        if (!com.corrodinggames.rts.gameFramework.l.aU) {
            com.corrodinggames.rts.appFramework.n.p();
        }
        this.d.b();
    }

    public void ag() {
        this.d(5.0f);
    }

    public void d(float f2) {
        if (!this.C) {
            throw new RuntimeException("We are not a server");
        }
        if (this.aZ) {
            return;
        }
        com.corrodinggames.rts.gameFramework.l.e("Setting up return to battleroom timer...");
        this.ba = f2;
        this.aZ = true;
        this.j("Game ended by host. Returning to battleroom in " + (int)f2 + " seconds...");
    }

    public void i(c c2) {
        if (!this.C) {
            throw new RuntimeException("We are not a server");
        }
        try {
            as as2 = new as();
            as2.c(0);
            au au2 = as2.b(122);
            if (c2 == null) {
                this.h(au2);
            } else {
                this.a(c2, au2);
            }
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        this.aC();
    }

    private void aC() {
        this.aY = true;
    }

    private void aD() {
        com.corrodinggames.rts.gameFramework.l.e("----- returnToBattleroom -----");
        this.aY = false;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.cb.e();
        n n2 = this.z;
        l2.g();
        this.s();
        this.z = n2;
        l2.bx = 0;
        l2.by = 0;
        this.A();
        com.corrodinggames.rts.game.n.n();
        if (this.C) {
            this.aA();
        }
        this.J();
        if (this.q && this.C) {
            com.corrodinggames.rts.gameFramework.j.n.c();
        }
        if (!com.corrodinggames.rts.gameFramework.l.aU) {
            // empty if block
        }
    }

    public String ah() {
        ArrayList arrayList = this.aj();
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        return (String)arrayList.get(0);
    }

    public String ai() {
        ArrayList arrayList = this.aj();
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        String string = "";
        boolean bl = true;
        for (String string2 : arrayList) {
            if (bl) {
                bl = false;
            } else {
                string = string + ", ";
            }
            string = string + string2;
        }
        return string;
    }

    public ArrayList aj() {
        if (bA != null) {
            return new ArrayList(bA);
        }
        long l2 = com.corrodinggames.rts.gameFramework.br.a();
        ArrayList arrayList = null;
        ArrayList arrayList2 = this.d(true);
        arrayList = arrayList2 != null && arrayList2.size() > 0 ? arrayList2 : this.d(false);
        double d2 = com.corrodinggames.rts.gameFramework.br.a(l2);
        if (d2 > 2.0) {
            com.corrodinggames.rts.gameFramework.l.b("getLocalIpAddressList was slow, taking:" + com.corrodinggames.rts.gameFramework.br.a(d2));
        }
        if (d2 > 10.0 && arrayList != null && arrayList.size() > 0) {
            com.corrodinggames.rts.gameFramework.l.e("getLocalIpAddressList: creating cache");
            bA = new ArrayList(arrayList);
        }
        return arrayList;
    }

    public String ak() {
        String string = null;
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                byte[] byArray = networkInterface.getHardwareAddress();
                if (byArray == null) continue;
                String string2 = new String(byArray);
                if ((string2 = string2.trim()).length() <= 2) continue;
                string = string2;
                break;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (string != null) {
            return com.corrodinggames.rts.gameFramework.f.c(string);
        }
        return "[blank]";
    }

    public ArrayList d(boolean bl) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                Enumeration<InetAddress> enumeration2 = networkInterface.getInetAddresses();
                while (enumeration2.hasMoreElements()) {
                    String string;
                    InetAddress inetAddress = enumeration2.nextElement();
                    if (inetAddress.isLoopbackAddress() || (string = inetAddress.getHostAddress().toString()).contains("%")) continue;
                    if (!bl) {
                        arrayList.add(string);
                        continue;
                    }
                    if (!string.contains(".")) continue;
                    arrayList.add(string);
                }
            }
        }
        catch (SocketException socketException) {
            Log.d("RustedWarfare", socketException.toString());
        }
        return arrayList;
    }

    InetAddress al() {
        try {
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            WifiManager wifiManager = (WifiManager)l2.am.c("wifi");
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
            int n2 = dhcpInfo.ipAddress & dhcpInfo.netmask | ~dhcpInfo.netmask;
            byte[] byArray = new byte[4];
            for (int i2 = 0; i2 < 4; ++i2) {
                byArray[i2] = (byte)(n2 >> i2 * 8 & 0xFF);
            }
            return InetAddress.getByAddress(byArray);
        }
        catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
            return null;
        }
    }

    public void d(String string, String string2) {
        if (com.corrodinggames.rts.gameFramework.l.aU) {
            return;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.F || l2.cb.j()) {
            return;
        }
        boolean bl = com.corrodinggames.rts.appFramework.n.l();
        com.corrodinggames.rts.appFramework.f f2 = l2.ao;
        if (f2 != null && !f2.e()) {
            bl = true;
        }
        if (bl) {
            if (this.bB) {
                this.i(2);
            }
            return;
        }
        NotificationManager notificationManager = (NotificationManager)l2.am.c("notification");
        Intent intent = new Intent(l2.am, com.corrodinggames.rts.appFramework.a.class);
        PendingIntent pendingIntent = PendingIntent.getActivity((Context)l2.am, (int)0, (Intent)intent, (int)2);
        if (Build.VERSION.SDK_INT >= 11) {
            Notification.Builder builder = new Notification.Builder(l2.am);
            builder.setContentTitle((CharSequence)"Rusted Warfare Multiplayer");
            builder.setContentText((CharSequence)(string + ": " + string2));
            builder.setSmallIcon(R$drawable.icon);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(false);
            builder.setAutoCancel(true);
            this.a(notificationManager);
            this.a(builder, "multiplayerChatId");
            Notification notification = builder.getNotification();
            notificationManager.notify(2, notification);
            this.bB = true;
        }
    }

    public void am() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.B && l2 != null && l2.M()) {
            this.aE();
        } else {
            this.i(1);
            this.i(2);
        }
    }

    private void a(Notification.Builder builder, String string) {
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Method method = builder.getClass().getDeclaredMethod("setChannelId", String.class);
                method.invoke(builder, string);
            }
            catch (Exception exception) {
                com.corrodinggames.rts.gameFramework.l.a("setChannelId failed", (Throwable)exception);
            }
        }
    }

    private void a(NotificationManager notificationManager) {
        this.a(notificationManager, "multiplayerChatId", "Multiplayer Chat");
        this.a(notificationManager, "multiplayerStatusId", "Multiplayer Status");
    }

    private void a(NotificationManager notificationManager, String string, String string2) {
        if (Build.VERSION.SDK_INT >= 26) {
            int n2 = 3;
            try {
                Class<?> clazz = Class.forName("android.app.NotificationChannel");
                Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, CharSequence.class, Integer.TYPE);
                Object obj = constructor.newInstance(string, string2, n2);
                Method method = notificationManager.getClass().getDeclaredMethod("createNotificationChannel", clazz);
                method.invoke(notificationManager, obj);
            }
            catch (Exception exception) {
                com.corrodinggames.rts.gameFramework.l.a("Creating notification channel failed", (Throwable)exception);
            }
        }
    }

    private void aE() {
        if (com.corrodinggames.rts.gameFramework.l.aU) {
            return;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        Intent intent = new Intent(l2.am, com.corrodinggames.rts.appFramework.a.class);
        PendingIntent pendingIntent = PendingIntent.getActivity((Context)l2.am, (int)0, (Intent)intent, (int)2);
        NotificationManager notificationManager = (NotificationManager)l2.am.c("notification");
        if (Build.VERSION.SDK_INT >= 11) {
            if (Build.VERSION.SDK_INT >= 26) {
                // empty if block
            }
            Notification.Builder builder = new Notification.Builder(l2.am);
            builder.setContentTitle((CharSequence)"Rusted Warfare Multiplayer");
            builder.setContentText((CharSequence)"A multiplayer game is in progress");
            builder.setSmallIcon(R$drawable.icon);
            builder.setContentIntent(pendingIntent);
            builder.setOngoing(true);
            this.a(notificationManager);
            this.a(builder, "multiplayerStatusId");
            if (Build.VERSION.SDK_INT >= 16) {
                builder.build();
            }
            Notification notification = builder.getNotification();
            notificationManager.notify(1, notification);
        }
    }

    private void i(int n2) {
        if (com.corrodinggames.rts.gameFramework.l.aU) {
            return;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        NotificationManager notificationManager = (NotificationManager)l2.am.c("notification");
        notificationManager.cancel(n2);
    }

    public int an() {
        int n2 = 0;
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
            n n3 = com.corrodinggames.rts.game.n.k(i2);
            if (n3 == null || n3.w) continue;
            ++n2;
        }
        return n2;
    }

    public int ao() {
        int n2 = 0;
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
            n n3 = com.corrodinggames.rts.game.n.k(i2);
            if (n3 == null) continue;
            ++n2;
        }
        return n2;
    }

    public void e(n n2) {
        if (this.C) {
            this.f(n2);
        } else if (this.H) {
            this.k("-kick " + (n2.k + 1));
        } else {
            com.corrodinggames.rts.gameFramework.l.b("kickTeamAndAttachedPlayer: but not server or proxy controller");
        }
    }

    public void f(n n2) {
        if (n2 instanceof com.corrodinggames.rts.game.a.a) {
            n2.I();
        } else {
            if (this.z == n2) {
                com.corrodinggames.rts.gameFramework.l.b("kickTeamAndAttachedPlayer", "Cannot kick self");
                return;
            }
            c c2 = this.c(n2);
            if (c2 == null) {
                com.corrodinggames.rts.gameFramework.j.ad.g("Kick player: cannot find connection for team");
            } else {
                int n3 = com.corrodinggames.rts.gameFramework.l.B().bQ.banTimeInSecondsAfterKick;
                if (n3 > 0) {
                    this.a(c2, "Temporarily banned due to recent kick", n3);
                }
                this.a(c2, "Kicked by host");
                c2.a("Kicked by host");
            }
            n2.I();
        }
        this.P();
        com.corrodinggames.rts.appFramework.n.o();
    }

    public void ap() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!this.C) {
            com.corrodinggames.rts.gameFramework.l.b("addAIToGame", "We are not a server");
            return;
        }
        int n2 = com.corrodinggames.rts.game.n.G();
        if (n2 == -1) {
            l2.a("No free slots for AI", 1);
        }
        com.corrodinggames.rts.game.a.a a2 = new com.corrodinggames.rts.game.a.a(n2);
        a2.v = "AI";
        a2.r = n2 % 2;
        a2.x = this.ay.f;
        this.aq();
        l2.bX.d.a(a2);
        l2.bX.e((c)null);
        com.corrodinggames.rts.appFramework.n.o();
    }

    public boolean aq() {
        if (!this.C && this.B) {
            com.corrodinggames.rts.gameFramework.l.b("updateNamesOfAI", "We are not a server");
            return false;
        }
        boolean bl = false;
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
            n n2 = com.corrodinggames.rts.game.n.k(i2);
            if (n2 == null || !this.b(n2)) continue;
            bl = true;
        }
        return bl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(n n2, int n3) {
        Object object = this.bC;
        synchronized (object) {
            this.c(n2, n3);
        }
    }

    private void c(n n2, int n3) {
        if (n2.k != n3) {
            int n4 = n2.k;
            int n5 = n2.r;
            boolean bl = false;
            if (n3 == -3) {
                bl = true;
                n3 = com.corrodinggames.rts.game.n.H();
                if (n3 == -1) {
                    com.corrodinggames.rts.gameFramework.j.ad.e("No free spectator slots");
                    return;
                }
            }
            n n6 = com.corrodinggames.rts.game.n.k(n3);
            n2.f(n3);
            n2.r = n5;
            if (bl) {
                n2.r = -3;
            }
            if (n6 != null) {
                int n7 = n6.r;
                n6.f(n4);
                n6.r = n7 == -3 ? -3 : n5;
            }
            this.M();
            this.P();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(am am2) {
        Object object = this.bC;
        synchronized (object) {
            this.b(am2);
        }
    }

    private synchronized void b(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!l2.bX.C) {
            com.corrodinggames.rts.gameFramework.l.e("Not server");
            return;
        }
        if (am2 == com.corrodinggames.rts.gameFramework.j.am.a) {
            int n2;
            int n3;
            ArrayList<n> arrayList = new ArrayList<n>();
            for (n3 = 0; n3 < com.corrodinggames.rts.game.n.c; ++n3) {
                n n4 = com.corrodinggames.rts.game.n.k(n3);
                if (n4 == null) continue;
                arrayList.add(n4);
            }
            Collections.shuffle(arrayList);
            n3 = arrayList.size() / 2;
            if (arrayList.size() % 2 != 0) {
                n3 += com.corrodinggames.rts.gameFramework.f.a(0, 1);
            }
            if (n3 >= arrayList.size()) {
                n3 = arrayList.size();
            }
            int n5 = 0;
            int n6 = 0;
            for (n2 = n5; n2 < n3; ++n2) {
                ((n)arrayList.get(n2)).f(n6);
                n6 += 2;
                ((n)arrayList.get((int)n2)).r = 0;
            }
            n6 = 1;
            for (n2 = n5 += n3; n2 < arrayList.size(); ++n2) {
                ((n)arrayList.get(n2)).f(n6);
                n6 += 2;
                ((n)arrayList.get((int)n2)).r = 1;
            }
        } else if (am2 == com.corrodinggames.rts.gameFramework.j.am.b) {
            n n7;
            int n8;
            int n9;
            int n10;
            ArrayList<n> arrayList = new ArrayList<n>();
            for (n10 = 0; n10 < com.corrodinggames.rts.game.n.c; ++n10) {
                n n11 = com.corrodinggames.rts.game.n.k(n10);
                if (n11 == null) continue;
                arrayList.add(n11);
            }
            Collections.shuffle(arrayList);
            n10 = arrayList.size() / 3;
            if (n10 >= arrayList.size()) {
                n10 = arrayList.size();
            }
            int n12 = 0;
            int n13 = 0;
            for (n9 = n12; n9 < n10; ++n9) {
                n n14 = (n)arrayList.get(n9);
                n14.f(n13);
                n14.r = 0;
                n13 += 3;
                arrayList.set(n9, null);
            }
            n9 = (n12 += n10) + arrayList.size() / 3;
            if (n9 >= arrayList.size()) {
                n9 = arrayList.size();
            }
            if (n12 >= arrayList.size()) {
                n12 = arrayList.size();
            }
            n13 = 1;
            for (n8 = n12; n8 < n9; ++n8) {
                n7 = (n)arrayList.get(n8);
                n7.f(n13);
                n7.r = 1;
                n13 += 3;
                arrayList.set(n8, null);
            }
            if ((n12 += n10) >= arrayList.size()) {
                n12 = arrayList.size();
            }
            n13 = 2;
            for (n8 = n12; n8 < arrayList.size(); ++n8) {
                n7 = (n)arrayList.get(n8);
                if (n13 < com.corrodinggames.rts.game.n.c) continue;
                n7.f(n13);
                n7.r = 2;
                n13 += 3;
                arrayList.set(n8, null);
            }
            for (n8 = 0; n8 < arrayList.size(); ++n8) {
                n7 = (n)arrayList.get(n8);
                if (n7 == null) continue;
                for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
                    if (com.corrodinggames.rts.game.n.k(i2) != null) continue;
                    n7.f(i2);
                    n7.r = 2;
                    arrayList.set(n8, null);
                }
            }
        } else if (am2 == com.corrodinggames.rts.gameFramework.j.am.c) {
            int n15;
            ArrayList<n> arrayList = new ArrayList<n>();
            for (n15 = 0; n15 < com.corrodinggames.rts.game.n.c; ++n15) {
                n n16 = com.corrodinggames.rts.game.n.k(n15);
                if (n16 == null) continue;
                arrayList.add(n16);
            }
            Collections.shuffle(arrayList);
            n15 = 0;
            for (int i3 = 0; i3 < arrayList.size(); ++i3) {
                ((n)arrayList.get(i3)).f(n15);
                ((n)arrayList.get((int)i3)).r = n15++;
            }
        } else if (am2 == com.corrodinggames.rts.gameFramework.j.am.d) {
            int n17;
            ArrayList<n> arrayList = new ArrayList<n>();
            for (n17 = 0; n17 < com.corrodinggames.rts.game.n.c; ++n17) {
                n n18 = com.corrodinggames.rts.game.n.k(n17);
                if (n18 == null) continue;
                arrayList.add(n18);
            }
            Collections.shuffle(arrayList);
            n17 = 0;
            for (int i4 = 0; i4 < arrayList.size(); ++i4) {
                int n19 = com.corrodinggames.rts.game.n.H();
                if (n19 != -1) {
                    ((n)arrayList.get(i4)).f(n19);
                }
                ((n)arrayList.get((int)i4)).r = -3;
                ++n17;
            }
        } else {
            throw new RuntimeException("overrideTeamLayout: unhandled layout: " + (Object)((Object)am2));
        }
        this.M();
    }

    public void a(n n2, int n3, Integer n4) {
        String string = "";
        if (n4 != null) {
            string = " " + n4;
        }
        if (!this.H && this.z == n2) {
            this.k("-self_move " + (n3 + 1) + string);
            return;
        }
        this.k("-move " + (n2.k + 1) + " " + (n3 + 1) + string);
    }

    public void b(n n2, int n3) {
        if (n3 != -1) {
            ++n3;
        }
        if (!this.H && this.z == n2) {
            this.k("-self_team " + n3);
            return;
        }
        this.k("-team " + (n2.k + 1) + " " + n3);
    }

    public void g(n n2) {
        if (!n2.H) {
            n2.H = true;
            String string = n2.v;
            if (string == null) {
                string = "Player - " + (n2.k + 1) + "";
            }
            String string2 = string + " is victorious!";
            this.j(string2);
        }
    }

    public void h(n n2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl = false;
        String string = n2.v;
        if (string == null) {
            string = "Player - " + (n2.k + 1) + "";
        }
        String string2 = string + " was defeated";
        if (!this.bb) {
            string2 = string2 + " (Team: " + n2.h() + ")";
        } else {
            int n3 = com.corrodinggames.rts.game.n.g();
            string2 = string2 + " (" + n3 + " players remaining)";
            if (n3 == 1) {
                bl = true;
            }
        }
        if (!l2.N() && l2.bx < 60) {
            com.corrodinggames.rts.gameFramework.l.e("Not showing defeated message: " + string2);
            string2 = null;
        }
        if (n2.E) {
            string2 = null;
        }
        if (string2 != null) {
            this.j(string2);
        }
        if (bl) {
            com.corrodinggames.rts.game.n.Q();
        }
    }

    public void i(n n2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String string = n2.v;
        if (string == null) {
            string = "Player - " + (n2.k + 1) + "";
        }
        boolean bl = false;
        String string2 = l2.bx < 10 ? string + " had no starting units" : string + " has been wiped out";
        if (!this.bb) {
            string2 = string2 + " (Team: " + n2.h() + ")";
        } else {
            int n3 = com.corrodinggames.rts.game.n.g();
            string2 = string2 + " (" + n3 + " players remaining)";
            if (n3 == 1) {
                bl = true;
            }
        }
        if (!l2.N() && l2.bx < 60) {
            com.corrodinggames.rts.gameFramework.l.e("Not showing defeated message: " + string2);
            string2 = null;
        }
        if (n2.E) {
            string2 = null;
        }
        if (n2.b()) {
            string2 = null;
        }
        if (string2 != null) {
            this.j(string2);
        }
        if (bl) {
            com.corrodinggames.rts.game.n.Q();
        }
    }

    public synchronized void ar() {
        if (this.bD != null) {
            this.bD.cancel();
            this.bD = null;
        }
    }

    public synchronized void as() {
        if (this.q && this.C && this.bD == null) {
            this.bD = new Timer();
            ad$6 ad$6 = new ad$6(this);
            this.bD.schedule((TimerTask)ad$6, 60000L, 60000L);
        }
    }

    public String at() {
        Object object;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String string = "";
        if (l2.bX.C && !l2.bX.F) {
            String string2 = l2.bX.ai();
            if (this.D) {
                if (this.E != null) {
                    object = this.E;
                    string = string + (String)object;
                }
            } else if (string2 != null) {
                object = "Local IP address: " + string2 + " port: " + l2.bX.m;
                if (l2.bX.aV != null) {
                    if (!l2.bX.aV.booleanValue()) {
                        object = (String)object + "\nUnable to get a public IP address, check your internet connection";
                    } else if (l2.bX.aT != null && l2.bX.aU != null) {
                        object = (String)object + "\nYour public address is " + (l2.bX.aU != false ? "<Open>" : "<CLOSED>") + " to the internet";
                    }
                } else {
                    object = (String)object + "\nRetrieving your public IP...";
                }
                string = string + (String)object;
            } else {
                string = string + "You do not have a network connection";
            }
        }
        if (l2.P()) {
            string = this.p ? string + "SandBox Mode!\nPlace any unit, Control all teams, Special powers" : string + "Local skirmish";
        }
        boolean bl = true;
        if (com.corrodinggames.rts.gameFramework.l.at() && l2.bX.C) {
            bl = false;
        }
        if (string.length() != 0) {
            string = string + "\n";
            if (com.corrodinggames.rts.gameFramework.l.av()) {
                string = string + "\n";
            }
        }
        if (l2.bX.av || l2.bX.C) {
            if (bl) {
                if (l2.bX.ay.a != null) {
                    string = string + "Game Mode: " + l2.bX.ay.a.a();
                }
                if (l2.bX.ay.b != null) {
                    string = string + "\nMap: " + com.corrodinggames.rts.appFramework.i.e(l2.bX.ay.b);
                }
            }
            string = string + "\nStarting Credits: " + l2.bX.j();
            string = string + "\nFog: " + l2.bX.g();
            if (l2.bX.ay.g != 1) {
                string = string + "\nStarting Units: " + l2.bX.h();
            }
            if (l2.bX.ay.h != 1.0f) {
                string = string + "\n" + com.corrodinggames.rts.gameFramework.f.a(l2.bX.ay.h, 1) + "X income";
            }
            if (l2.bX.ay.i) {
                string = string + "\nNo nukes";
            }
            if (l2.bX.ay.l) {
                string = string + "\nShared control: On";
            }
            if (this.C) {
                if (l2.bX.n != null) {
                    string = string + "\nPassword Protection: On";
                }
                if (!l2.bX.q && !l2.bX.F) {
                    string = string + "\nServer Visibility: Hidden";
                }
                if (l2.bX.o && !l2.bX.F) {
                    object = l2.bZ.j();
                    string = string + "\n-- Required Mods: --\n";
                    int n2 = 0;
                    Iterator iterator = ((ArrayList)object).iterator();
                    while (iterator.hasNext()) {
                        com.corrodinggames.rts.gameFramework.i.b b2 = (com.corrodinggames.rts.gameFramework.i.b)iterator.next();
                        if (n2 > 2 && n2 < ((ArrayList)object).size() - 1) {
                            string = string + "" + (((ArrayList)object).size() - n2) + " more mods...";
                            break;
                        }
                        ++n2;
                        String string3 = b2.b();
                        string3.replace("\"", "'");
                        string3.replace(";", ".");
                        string = string + " mod: \"" + string3 + "\"\n";
                    }
                }
            }
        }
        return string;
    }

    public String au() {
        if (!this.o) {
            return null;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ArrayList arrayList = l2.bZ.j();
        String string = "";
        int n2 = 0;
        for (com.corrodinggames.rts.gameFramework.i.b b2 : arrayList) {
            if (n2 != 0) {
                string = string + "; ";
            }
            if (n2 > 1 && n2 < arrayList.size() - 1) {
                string = string + "" + (arrayList.size() - n2) + " more...";
                break;
            }
            ++n2;
            String string2 = b2.b();
            string2.replace(";", ".");
            string = string + string2;
        }
        return string;
    }

    public String av() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bX.ay.b == null) {
            return null;
        }
        if (l2.bX.ay.a == null) {
            return null;
        }
        if (l2.bX.ay.a == com.corrodinggames.rts.gameFramework.j.ai.a) {
            return "maps/skirmish/" + l2.bX.ay.b;
        }
        if (l2.bX.ay.a == com.corrodinggames.rts.gameFramework.j.ai.b) {
            return "/SD/rusted_warfare_maps/" + l2.bX.ay.b;
        }
        com.corrodinggames.rts.gameFramework.l.e("getNetworkMapPath: unhandled type:" + (Object)((Object)l2.bX.ay.a));
        return null;
    }

    public boolean aw() {
        return this.C || this.H;
    }

    public void a(String string, c c2) {
        com.corrodinggames.rts.gameFramework.l.e("sendCommandError: " + string);
        if (c2 == null) {
            this.b(null, -1, null, string);
        } else {
            this.a(null, null, null, string, c2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean b(c c2, n n2, String string, String string2) {
        int n3;
        String string3;
        String string4 = null;
        String string5 = "";
        String[] stringArray = new String[]{};
        String string6 = string2.trim();
        boolean bl = false;
        if (string6.startsWith("-qc ")) {
            string6 = string6.substring("-qc ".length());
            string6 = string6.trim();
            bl = true;
        }
        if ((string6.startsWith("-") || string6.startsWith(".") || string6.startsWith("_")) && string6.length() >= 2) {
            string3 = string6.substring(1).trim();
            n3 = string3.indexOf(" ");
            if (n3 == -1) {
                n3 = string3.length();
            }
            string4 = string3.substring(0, n3).toLowerCase(Locale.ENGLISH);
            if (n3 != -1 && string3.length() >= n3 + 1) {
                string5 = string3.substring(n3 + 1).trim();
                stringArray = string5.split(" ");
            }
        }
        if (string4 == null) {
            return false;
        }
        if (bl && !"self_move".equals(string4) && !"self_team".equals(string4)) {
            return false;
        }
        if ("pause".equals(string4) || "unpause".equals(string4)) {
            boolean bl2;
            if (n2 == null) {
                this.a("[Could not find player]", c2);
                return true;
            }
            if (!(this.C && n2 == this.z || this.d.b(c2))) {
                this.a("[Only the host can change pause state]", c2);
                return true;
            }
            if (!this.aW) {
                this.a("[Game not yet started]", c2);
                return true;
            }
            boolean bl3 = bl2 = !this.al;
            if ("unpause".equals(string4)) {
                bl2 = false;
            }
            this.e(bl2);
            return true;
        }
        if ("endgame".equals(string4)) {
            if (n2 == null) {
                this.a("[Could not find player]", c2);
                return true;
            }
            if (!this.C || n2 != this.z) {
                this.a("[Only the host can end game]", c2);
                return true;
            }
            if (!this.aW) {
                this.a("[Game not yet started]", c2);
                return true;
            }
            this.ag();
            return true;
        }
        if ("teamlock".equals(string4)) {
            if (n2 == null) {
                this.a("[Could not find player]", c2);
                return true;
            }
            if (!(this.C && n2 == this.z || this.d.b(c2))) {
                this.a("[Only the host can change teamlock]", c2);
                return true;
            }
            if ("true".equalsIgnoreCase(string5) || "on".equalsIgnoreCase(string5)) {
                this.ay.m = true;
                this.a("[teams are locked]", c2);
                return true;
            }
            if ("false".equalsIgnoreCase(string5) || "off".equalsIgnoreCase(string5)) {
                this.ay.m = false;
                this.a("[teams are unlocked]", c2);
                return true;
            }
            this.a("[Expected true or false]", c2);
            return true;
        }
        if ("roomlock".equals(string4)) {
            if (n2 == null) {
                this.a("[Could not find player]", c2);
                return true;
            }
            if (!this.C || n2 != this.z) {
                this.a("[Only the host can change roomlock]", c2);
                return true;
            }
            if ("true".equalsIgnoreCase(string5) || "on".equalsIgnoreCase(string5)) {
                this.ay.p = true;
                this.a("[room is locked]", c2);
                return true;
            }
            if ("false".equalsIgnoreCase(string5) || "off".equalsIgnoreCase(string5)) {
                this.ay.p = false;
                this.a("[room is unlocked]", c2);
                return true;
            }
            this.a("[Expected true or false]", c2);
            return true;
        }
        if ("share".equals(string4)) {
            if (n2 == null) {
                this.a("[Could not find player]", c2);
                return true;
            }
            if (!this.ay.l) {
                this.a("[Shared control is not enabled in this game]", c2);
                return true;
            }
            if ("true".equalsIgnoreCase(string5) || "on".equalsIgnoreCase(string5)) {
                if (!n2.I) {
                    n2.I = true;
                    this.j("[shared control now on for " + string + "]");
                } else {
                    this.j("[shared control already on for " + string + "]");
                }
                return true;
            }
            if ("false".equalsIgnoreCase(string5) || "off".equalsIgnoreCase(string5)) {
                if (n2.I) {
                    n2.I = false;
                    this.j("[shared control now off for " + string + "]");
                } else {
                    this.j("[shared control already off for " + string + "]");
                }
                return true;
            }
            this.a("[Expected true or false]", c2);
            return true;
        }
        if ("self_move".equals(string4)) {
            int n4;
            boolean bl4;
            int n5;
            if (n2 == null) {
                this.a("[Cannot Move - Player not found]", c2);
                return true;
            }
            if (this.aW) {
                this.a("[Cannot Move '" + n2.v + "' - Game has been started]", c2);
                return true;
            }
            if (this.o()) {
                this.a("[Cannot Move '" + n2.v + "' - Game is starting]", c2);
                return true;
            }
            if (this.ay.m) {
                this.a("[Cannot Move '" + n2.v + "' - Teams locked]", c2);
                return true;
            }
            if (stringArray.length > 0) {
                try {
                    n5 = Integer.valueOf(stringArray[0]);
                }
                catch (NumberFormatException numberFormatException) {
                    this.a("[Cannot Move '" + n2.v + "' - team '" + stringArray[0] + "' is not a number]", c2);
                    return true;
                }
            } else {
                this.a("[Cannot Move '" + n2.v + "' - No target]", c2);
                return true;
            }
            Integer n6 = null;
            if (stringArray.length > 1) {
                try {
                    n6 = Integer.valueOf(stringArray[1]);
                }
                catch (NumberFormatException numberFormatException) {
                    this.a("[Cannot Move '" + n2.v + "' - ally group '" + stringArray[1] + "' is not a number]", c2);
                    return true;
                }
                if (n6 != -1 && (n6 < 1 || n6 > 99)) {
                    this.a("[Cannot Move Team - Ally group - Out of range]", c2);
                    return true;
                }
            }
            boolean bl5 = false;
            if (n5 - 1 == -3) {
                if (!this.ay.o) {
                    this.a("[Spectators are disabled on this server]", c2);
                    return true;
                }
                Object object = this.bC;
                synchronized (object) {
                    n5 = com.corrodinggames.rts.game.n.H();
                    if (n5 != -1) {
                        this.a(n2, -3);
                    }
                }
                bl5 = true;
            }
            boolean bl6 = bl4 = (n4 = n2.r) == -3;
            if (!bl5) {
                if (n5 < 1 || n5 > com.corrodinggames.rts.game.n.c) {
                    this.a("[Cannot Move '" + n2.v + "' - target slotId must between 1-" + com.corrodinggames.rts.game.n.c + "]", c2);
                    return true;
                }
                Object object = this.bC;
                synchronized (object) {
                    n n7;
                    if (this.z != n2 && (n7 = com.corrodinggames.rts.game.n.k(n5 - 1)) != null && !n7.w && !n7.b()) {
                        this.a("[Cannot move '" + n2.v + "' to slot: " + n5 + " - Player: " + n7.v + " is in that slot.]", c2);
                        return true;
                    }
                    this.a(n2, n5 - 1);
                }
            }
            n2.r = n4;
            if (n6 != null) {
                n2.r = n6 == -1 ? n2.k % 2 : n6;
            }
            if (this.ay.n) {
                n2.r = n2.k % 2;
            }
            if (bl5) {
                n2.r = -3;
            }
            if (bl5) {
                if (!bl4) {
                    this.j("Player '" + n2.v + "' is now a spectator");
                }
            } else {
                this.j("Player '" + n2.v + "' moved themselves to: " + n5);
            }
            this.P();
            com.corrodinggames.rts.appFramework.n.o();
            return true;
        }
        if ("self_team".equals(string4)) {
            int n8;
            if (n2 == null) {
                this.a("[Cannot Set Team - Player not found]", c2);
                return true;
            }
            if (this.aW) {
                this.a("[" + n2.v + ": Cannot Set Team - Game has been started]", c2);
                return true;
            }
            if (this.o()) {
                this.a("[" + n2.v + ": Cannot Set Team - Game is starting]", c2);
                return true;
            }
            if (this.ay.m) {
                this.a("[" + n2.v + ": Cannot Set Team - Teams locked]", c2);
                return true;
            }
            if (this.ay.n) {
                return true;
            }
            try {
                n8 = Integer.valueOf(string5);
            }
            catch (NumberFormatException numberFormatException) {
                this.m("'" + string5 + "' is not a number");
                return true;
            }
            if (n8 == -1) {
                n3 = n2.k % 2;
            } else {
                if (n8 < 1 || n8 > 99) {
                    this.a("[Cannot Set Team - Out of range]", c2);
                    return true;
                }
                n3 = n8 - 1;
            }
            if (n2.r != n3) {
                n2.r = n3;
                this.a("Player '" + n2.v + "' team changed to: " + n8, c2);
            }
            this.P();
            com.corrodinggames.rts.appFramework.n.o();
            return true;
        }
        if ("surrender".equals(string4)) {
            if (!this.aW) {
                this.a("[Cannot Surrender - Game has not started]", c2);
                return true;
            }
            if (n2 == null) {
                this.a("[Could not find player]", c2);
                return true;
            }
            string3 = "";
            if (!n2.k()) {
                n2.l();
                n3 = n2.m() ? 1 : 0;
                com.corrodinggames.rts.gameFramework.l.e(string + ": Is voting to surrender (can surrender:" + (n3 != 0) + ", afk:" + n2.ab + ", defeated:" + n2.G + ", disconnected:" + n2.B() + ")");
                string3 = n3 != 0 ? "" : "(Cannot vote) ";
            } else {
                com.corrodinggames.rts.gameFramework.l.e(string + ": Is already voting to surrender but updating timestamp");
                n2.l();
                string3 = "(Already voted) ";
            }
            String string7 = com.corrodinggames.rts.game.n.b(n2.r) + "/" + com.corrodinggames.rts.game.n.c(n2.r);
            String string8 = "-t " + string3 + "[Votes to surrender " + string7 + "]";
            this.a(c2, n2, string, string8);
            return true;
        }
        return false;
    }

    public static void a(ae ae2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bX != null) {
            l2.bX.d.a(ae2);
        }
        if (com.corrodinggames.rts.gameFramework.l.aU) {
            return;
        }
        ad$7 ad$7 = new ad$7(ae2);
        com.corrodinggames.rts.appFramework.c.a(ad$7);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ArrayList ax() {
        Object object = this.bC;
        synchronized (object) {
            return com.corrodinggames.rts.game.n.c();
        }
    }

    public void e(boolean bl) {
        this.al = bl;
        if (this.al) {
            this.j("Game Paused");
        } else {
            this.j("Game unpaused");
        }
    }

    public void b(c c2, String string) {
        c2.a(false, false, string);
    }

    public void c(c c2, String string) {
        for (c c3 : this.aM) {
            if (c3.j != c2) continue;
            this.b(c3, string);
        }
    }

    public c a(c c2, int n2, String string, String string2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        h h2 = new h(c2, n2);
        c c3 = new c(this, h2);
        c3.k = n2;
        c3.j = c2;
        c3.m = string;
        c3.n = string2;
        try {
            c3.d();
            l2.bX.aM.add(c3);
            l2.bX.Q();
            return c3;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            c3.a("crash");
            return null;
        }
    }

    public c a(c c2, int n2) {
        for (c c3 : this.aM) {
            if (c3.k != n2 || c3.j != c2) continue;
            return c3;
        }
        return null;
    }

    public static String p(String string) {
        string = string.trim();
        string = string.replace("\n", ".");
        string = string.replace("\r", ".");
        string = string.replace("\t", ".");
        string = string.replace("\u0000", ".");
        string = string.replace(" ", "_");
        while (string.startsWith(".") || string.startsWith("-") || string.startsWith(" ")) {
            string = string.substring(1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c2 : string.toCharArray()) {
            if (Character.isISOControl(c2)) continue;
            stringBuilder.append(c2);
        }
        string = stringBuilder.toString();
        return string;
    }

    public void a(ArrayList arrayList, boolean bl) {
        if (this.bF != null) {
            com.corrodinggames.rts.gameFramework.l.e("startJoinServerInternalThread: Already joining");
            return;
        }
        if (arrayList.size() == 0) {
            com.corrodinggames.rts.gameFramework.l.e("startJoinServerInternalThread: no servers");
            return;
        }
        String string = (String)arrayList.get(0);
        boolean bl2 = false;
        ad$8 ad$8 = new ad$8(this, bl);
        this.bF = this.a(string, bl2, ad$8);
    }

    static {
        bE = new ae();
    }
}
