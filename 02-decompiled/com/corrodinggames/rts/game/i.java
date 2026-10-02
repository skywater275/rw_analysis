/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.pm.Signature
 *  android.os.Debug
 *  android.util.DisplayMetrics
 */
package com.corrodinggames.rts.game;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Debug;
import android.util.DisplayMetrics;
import android.util.Log;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.e;
import com.corrodinggames.rts.game.i$a;
import com.corrodinggames.rts.game.k;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.aa;
import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.am;
import com.corrodinggames.rts.gameFramework.ba;
import com.corrodinggames.rts.gameFramework.be;
import com.corrodinggames.rts.gameFramework.bf;
import com.corrodinggames.rts.gameFramework.bg;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.bs;
import com.corrodinggames.rts.gameFramework.c.a;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.g.c;
import com.corrodinggames.rts.gameFramework.g.f;
import com.corrodinggames.rts.gameFramework.j;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.h;
import com.corrodinggames.rts.gameFramework.m.x;
import com.corrodinggames.rts.gameFramework.m.z;
import com.corrodinggames.rts.gameFramework.utility.o;
import com.corrodinggames.rts.gameFramework.utility.q;
import com.corrodinggames.rts.gameFramework.utility.r;
import com.corrodinggames.rts.gameFramework.utility.s;
import com.corrodinggames.rts.gameFramework.w;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

public strictfp class i
extends l {
    public static String a;
    public static boolean b;
    public static boolean c;
    int d;
    public float e = 1.0f;
    public static String f;
    k[] g = new k[6];
    String h;
    public boolean i = false;
    public int j = 0;
    public ConcurrentLinkedQueue k = new ConcurrentLinkedQueue();
    Paint l;
    Paint m;
    Paint n;
    Paint o;
    Paint p;
    int q = 0;
    int r = 0;
    int s = 0;
    float t = 16.0f;
    public String u = "0fps";
    Rect v = new Rect();
    public ArrayList w = new ArrayList();
    Paint x;
    Paint y;
    Paint z;
    public Paint A = new Paint();
    public bf B;
    public be C;
    public com.corrodinggames.rts.gameFramework.d.b D = new com.corrodinggames.rts.gameFramework.d.b();
    com.corrodinggames.rts.game.a E;
    boolean F;
    float G = 0.0f;
    public float H = 1.0f;
    public float I;
    public float J;
    com.corrodinggames.rts.game.j K;
    com.corrodinggames.rts.game.j L;
    boolean M;
    com.corrodinggames.rts.gameFramework.m.y N;
    com.corrodinggames.rts.gameFramework.m.e O;
    com.corrodinggames.rts.gameFramework.m.e P;
    com.corrodinggames.rts.gameFramework.m.e Q;
    float R = 0.0f;
    Rect S = new Rect();
    RectF T = new RectF();
    public com.corrodinggames.rts.gameFramework.m.e U = null;
    public com.corrodinggames.rts.gameFramework.m.e V = null;
    s W = new s("allOnScreenObjects");
    s X = new s("allOnScreenObjectsDirty");
    Matrix Y = new Matrix();
    public ArrayList Z = new ArrayList();
    public ArrayList aa = new ArrayList();
    Timer ab;
    boolean ac;
    Object ad = new Object();
    int ae = 0;
    com.corrodinggames.rts.game.units.am af;
    com.corrodinggames.rts.game.units.am ag;
    float ah;
    boolean ai;

    public i(Context context) {
        super(context);
    }

    @Override
    public boolean a() {
        if (this.bS.u) {
            return true;
        }
        return this.dH != null && this.dH.b();
    }

    @Override
    public boolean a(boolean bl) {
        if (!bl || this.cb.j()) {
            if (this.bS.u) {
                return true;
            }
            if (this.bp) {
                return true;
            }
            if (this.aq && !this.bH) {
                return true;
            }
            if (this.bF && this.dH != null && this.dH.b()) {
                return true;
            }
        }
        if (bl && !this.bX.aW) {
            return true;
        }
        return this.bX.I();
    }

    @Override
    public int b() {
        return this.s;
    }

    @Override
    public boolean c() {
        return this.eh;
    }

    @Override
    public boolean d() {
        return this.ei;
    }

    @Override
    public synchronized void a(Context context) {
        Log.d("RustedWarfare", "--- ----------------- ----");
        Log.d("RustedWarfare", "--- GameEngine:init() ----");
        Log.d("RustedWarfare", "--- ----------------- ----");
        if (this.bi) {
            Log.d("RustedWarfare", "GameEngine init has already been called");
            return;
        }
        com.corrodinggames.rts.gameFramework.l.e("Version:" + this.r());
        if (com.corrodinggames.rts.game.i.C() && this.getClass().equals(i.class)) {
            throw new RuntimeException("inSpace but class is:" + this.getClass());
        }
        System.gc();
        this.h("Asset Index");
        this.bK = new com.corrodinggames.rts.gameFramework.utility.i(context);
        long l2 = com.corrodinggames.rts.gameFramework.br.a();
        this.cd = new br(this);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.C);
        if (aU) {
            this.ci = 1.0f;
        } else {
            DisplayMetrics displayMetrics = context.e().getDisplayMetrics();
            this.ci = context.e().getDisplayMetrics().density;
            com.corrodinggames.rts.gameFramework.l.e("densityScaleRaw: " + this.ci);
            this.a(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        this.ci *= this.e;
        com.corrodinggames.rts.gameFramework.l.e("densityScaleRaw*densityScaleMultiplier: " + this.ci);
        if (com.corrodinggames.rts.gameFramework.l.b(context)) {
            this.ar = true;
        }
        this.E = new com.corrodinggames.rts.game.b();
        this.bo = false;
        this.h("InputController");
        this.bT = new ac();
        this.bT.a();
        this.h("SettingsEngine");
        this.bQ = SettingsEngine.getInstance(context);
        this.bQ.loadMainExternalFolder(true);
        com.corrodinggames.rts.gameFramework.e.a.b();
        int n2 = 3;
        if (aZ) {
            n2 = 1;
        }
        if (this.bQ.numIncompleteLoadAttempts > 1 || this.bQ.numLoadsSinceRunningGameOrNormalExit > n2) {
            this.ee = true;
            if (this.bQ.numIncompleteLoadAttempts > 2 || this.bQ.numLoadsSinceRunningGameOrNormalExit > 4) {
                this.bQ.forceEnglish = true;
                this.ef = true;
            }
            if (this.bQ.numIncompleteLoadAttempts > 3) {
                this.bQ.newRender = false;
            }
            if (this.bQ.numIncompleteLoadAttempts > 4 || this.bQ.numLoadsSinceRunningGameOrNormalExit > 5) {
                com.corrodinggames.rts.gameFramework.l.e("Extra safe mode");
                this.eh = true;
            }
            if (this.bQ.numIncompleteLoadAttempts > 5) {
                com.corrodinggames.rts.gameFramework.l.e("Extra safe mode x2");
                this.ei = true;
            }
            if (this.bQ.numIncompleteLoadAttempts > 6) {
                com.corrodinggames.rts.gameFramework.l.e("Extra safe mode x3");
                this.bQ.newRender = false;
                this.bQ.shaderEffects = false;
                this.bQ.teamShaders = false;
            }
            if (this.bQ.newRender && this.bQ.numLoadsSinceRunningGameOrNormalExit > 15) {
                com.corrodinggames.rts.gameFramework.l.e("Disabling opengl mode");
                this.bQ.newRender = false;
            }
            com.corrodinggames.rts.gameFramework.l.e("starting game in safe mode, numIncompleteLoadAttempts:" + this.bQ.numIncompleteLoadAttempts + " numLoadsSinceRunningGameOrNormalExit:" + this.bQ.numLoadsSinceRunningGameOrNormalExit);
        }
        if (aO) {
            this.ee = true;
            this.eg = "<forced by command line>";
        }
        if (aP) {
            this.ee = true;
            this.eh = true;
            this.ei = true;
            this.eg = "<forced by command line>";
        }
        ++this.bQ.numLoadsSinceRunningGameOrNormalExit;
        ++this.bQ.numIncompleteLoadAttempts;
        boolean bl = this.bQ.save();
        if (!bl && aZ) {
            com.corrodinggames.rts.gameFramework.l.e("starting game in safe mode, failed to save settings");
            this.eg = "failing to write preferences data";
            this.ee = true;
        }
        com.corrodinggames.rts.gameFramework.c.a.a();
        this.cj = this.W();
        com.corrodinggames.rts.gameFramework.l.e("densityScale(): " + this.cj);
        long l3 = com.corrodinggames.rts.gameFramework.br.a();
        com.corrodinggames.rts.gameFramework.h.a.a();
        com.corrodinggames.rts.gameFramework.br.a("Locale.init took:", l3);
        com.corrodinggames.rts.game.n.L();
        this.l = new Paint();
        this.m = new Paint();
        this.m.a(255, 255, 255, 255);
        this.m.a(true);
        this.a(this.m, 16.0f);
        this.n = new Paint();
        this.n.a(255, 255, 255, 255);
        this.n.a(true);
        this.a(this.n, 16.0f);
        this.o = new Paint();
        this.o.a(100, 255, 0, 0);
        this.a(this.o, 16.0f);
        this.p = new Paint();
        this.p.a(100, 0, 255, 0);
        this.a(this.p, 16.0f);
        this.dn = new Paint();
        this.do = new Paint();
        this.do.a(Paint$Align.b);
        this.do.a(true);
        this.do.a(Typeface.a(Typeface.c, 0));
        this.a(this.do, 16.0f);
        this.dp = new Paint();
        this.dp.a(255, 230, 255, 230);
        this.dp.a(true);
        this.dp.a(Paint$Align.b);
        this.a(this.dp, 18.0f);
        this.x = new Paint();
        this.x.b(-1);
        this.x.c(100);
        this.y = new Paint();
        this.y.b(-7829368);
        this.y.c(240);
        this.y.a(Paint$Style.b);
        this.y.a(1.0f);
        long l4 = com.corrodinggames.rts.gameFramework.br.a();
        this.h("AudioEngine");
        com.corrodinggames.rts.gameFramework.a.e.b();
        this.bM = new com.corrodinggames.rts.gameFramework.a.e();
        this.bM.a(context);
        com.corrodinggames.rts.gameFramework.br.a("AudioEngine took:", l4);
        this.h("MusicController");
        this.bN = new am();
        this.bN.a(context);
        if (bh != null) {
            com.corrodinggames.rts.game.i.e("init(): using Graphics instance");
            this.bO = bh;
        } else if (bg != null) {
            com.corrodinggames.rts.game.i.e("init(): using GraphicsSlick2d");
            try {
                this.bO = (com.corrodinggames.rts.gameFramework.m.y)bg.newInstance();
            }
            catch (InstantiationException instantiationException) {
                throw new RuntimeException(instantiationException);
            }
            catch (IllegalAccessException illegalAccessException) {
                throw new RuntimeException(illegalAccessException);
            }
        } else {
            this.bO = aU ? new z() : new x();
        }
        this.h("graphics.init");
        this.bO.a(context);
        this.bO.b();
        com.corrodinggames.rts.gameFramework.j.a();
        this.h("Fonts");
        this.Y();
        this.h("effects.init");
        this.bR = new com.corrodinggames.rts.gameFramework.d.c();
        this.bR.a(context);
        this.h("minimapHandler");
        this.bW = new com.corrodinggames.rts.gameFramework.f.o();
        this.bW.a(context);
        if (ck != null) {
            com.corrodinggames.rts.gameFramework.l.e("We have an initial screen size, can do early setup of image buffers");
            this.h("Map Buffers");
            this.b(com.corrodinggames.rts.game.i.ck.a, com.corrodinggames.rts.game.i.ck.b);
            this.k();
            com.corrodinggames.rts.game.b.b.d();
            com.corrodinggames.rts.game.b.b.f();
            this.bW.e();
            boolean bl2 = com.corrodinggames.rts.gameFramework.l.aA();
            if (bl2) {
                this.h("Setting up postprocessing");
                boolean bl3 = this.i();
                if (!bl3) {
                    com.corrodinggames.rts.gameFramework.l.e("Failed to setup postprocessing");
                }
            }
        }
        this.h("PathEngine");
        this.bU = new com.corrodinggames.rts.gameFramework.k.l();
        this.h("GroupController");
        this.bV = new aa();
        this.h("CollisionEngine");
        this.bP = new com.corrodinggames.rts.gameFramework.a();
        this.h("InterfaceEngine");
        this.bS = new g();
        this.bS.a(context);
        this.C = com.corrodinggames.rts.gameFramework.be.c(context);
        this.h("NetworkEngine");
        this.bX = new ad();
        this.bX.F();
        this.h("StatsHandler");
        this.bY = new bg();
        this.h("ModEngine");
        this.bZ = new com.corrodinggames.rts.gameFramework.i.a();
        this.bZ.a();
        if (this.ee) {
            this.bZ.g();
        }
        this.h("CommandController");
        this.cf = new com.corrodinggames.rts.gameFramework.c();
        this.h("GameSaver");
        this.ca = new com.corrodinggames.rts.gameFramework.y();
        this.h("ReplayEngine");
        this.cb = new ba();
        this.cb.a(context);
        this.h("UnitGeoIndex");
        this.cc = new com.corrodinggames.rts.game.units.f.c();
        this.h("Precalculating map fog");
        com.corrodinggames.rts.game.b.b.c();
        this.h("ScorchMark.load");
        com.corrodinggames.rts.game.l.b();
        this.h("Projectile.load");
        com.corrodinggames.rts.game.f.c();
        this.h("Emitter.load");
        com.corrodinggames.rts.gameFramework.d.f.b();
        this.h("Unit.loadAllUnits");
        long l5 = com.corrodinggames.rts.gameFramework.br.a();
        com.corrodinggames.rts.game.units.am.bH();
        com.corrodinggames.rts.gameFramework.br.a("loadAllUnits took:", l5);
        this.h("Loading custom unit data");
        long l6 = com.corrodinggames.rts.gameFramework.br.a();
        com.corrodinggames.rts.game.units.custom.ag.h();
        this.h("getAllUnitsChecksum");
        com.corrodinggames.rts.gameFramework.br.a("CustomUnits took:", l6);
        long l7 = com.corrodinggames.rts.gameFramework.br.a();
        this.d = com.corrodinggames.rts.game.units.am.bM();
        com.corrodinggames.rts.gameFramework.br.a("allUnitsChecksum took:", l7);
        this.z = new Paint();
        this.z.a(50, 255, 255, 255);
        this.F();
        System.gc();
        this.bi = true;
        com.corrodinggames.rts.gameFramework.l.e("Init completed");
        com.corrodinggames.rts.gameFramework.br.a("Loading took:", l2);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.C);
        this.cd.a(true, true);
        long l8 = com.corrodinggames.rts.gameFramework.br.a();
        this.h("Loading map data");
        if (!com.corrodinggames.rts.gameFramework.l.ay) {
            this.x();
        }
        com.corrodinggames.rts.gameFramework.br.a("loadAMenuMap took:", l8);
        this.h("Last setup");
        com.corrodinggames.rts.game.i.ap();
        this.bX.m();
        this.h("init complete");
        if (aE) {
            com.corrodinggames.rts.game.units.ar.s();
            System.exit(0);
        }
        if (aF) {
            com.corrodinggames.rts.game.units.ar.r();
            System.exit(0);
        }
        this.bj = true;
    }

    public void a(int n2, int n3) {
        float f2 = 1.0f;
        float f3 = com.corrodinggames.rts.gameFramework.f.b(0.0f, 0.0f, (float)n2, (float)n3);
        float f4 = 1131.0f;
        f2 = f3 / f4;
        com.corrodinggames.rts.gameFramework.l.e("defaultViewpointZoomDensity: " + f2);
        if (f2 < 0.5f) {
            f2 = 0.5f;
        }
        if (f2 > 3.0f) {
            f2 = 3.0f;
        }
        com.corrodinggames.rts.gameFramework.l.e("defaultViewpointZoomDensity after limit: " + f2);
        this.cY = 1.0f;
        if ((double)com.corrodinggames.rts.gameFramework.f.c(f2 - 1.0f) > 0.1) {
            this.cY = f2;
            if (this.cY > 2.0f) {
                this.cY = 2.0f;
            }
            if (this.cY < 0.5f) {
                this.cY = 0.5f;
            }
            this.cX = this.cV * this.cY;
        }
    }

    @Override
    public void e() {
        this.K();
        this.f();
    }

    public void f() {
        this.b(false);
        this.bG = false;
        this.bH = false;
        this.bF = false;
        this.bp = false;
        this.bS.u = false;
    }

    @Override
    public synchronized void a(boolean bl, com.corrodinggames.rts.gameFramework.s s2) {
        this.K();
        this.a(bl, false, s2);
    }

    @Override
    public void a(boolean bl2, boolean bl3, com.corrodinggames.rts.gameFramework.s s2) {
        int n2;
        int n3;
        block81: {
            this.bC = this.bQ.teamUnitCapSinglePlayer;
            if (this.bC < 1) {
                this.bC = 1;
            }
            this.bB = this.bC;
            this.b(bl3);
            com.corrodinggames.rts.game.n.X();
            this.bo = false;
            System.gc();
            this.bI = true;
            this.bG = false;
            this.bp = false;
            this.bF = false;
            this.by = 0;
            this.ch = false;
            this.bX.a(1L);
            this.bx = 0;
            this.bJ = 0;
            com.corrodinggames.rts.gameFramework.f.a();
            this.bX.t();
            if (!bl3) {
                this.dq = false;
                this.dr = false;
                this.ds = 0.0f;
                this.du = false;
                this.dt = false;
            }
            this.j = 0;
            if (!bl3) {
                this.cV = 1.0f;
            }
            this.dx = 0.0f;
            if (!this.cb.j()) {
                if (!this.bX.B) {
                    com.corrodinggames.rts.game.units.custom.ag.b(true);
                } else {
                    com.corrodinggames.rts.game.units.custom.ag.d();
                }
            }
            if (!this.bX.B) {
                if (!this.cb.j() && bl2) {
                    this.bs = new e(0);
                    this.bs.v = "Player";
                    for (int i2 = 1; i2 < 8; ++i2) {
                        new com.corrodinggames.rts.game.a.a(i2);
                    }
                    this.bX.aq();
                }
            } else {
                this.bs = this.bX.z;
                if (this.bs == null) {
                    throw new RuntimeException("cannot find player's team");
                }
                if (this.bs != com.corrodinggames.rts.game.n.k(this.bs.k)) {
                    com.corrodinggames.rts.gameFramework.l.g("Stale playerTeam");
                }
            }
            this.ce = null;
            this.bL = new b();
            try {
                if (this.dm != null) {
                    InputStream inputStream = this.dm.w();
                    try {
                        inputStream.reset();
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                    this.bL.a(inputStream, bl3);
                    break block81;
                }
                this.bL.a(this.ak(), bl3);
            }
            catch (com.corrodinggames.rts.game.b.f f2) {
                Object object;
                f2.printStackTrace();
                this.a("Error loading map: " + f2.getMessage(), 1);
                if (aT) {
                    com.corrodinggames.rts.gameFramework.l.e("Crashing on allowed map error because automated testing is active");
                    throw new RuntimeException(f2);
                }
                if (!this.bX.B && this.ao != null && (object = this.ao.i()) != null) {
                    ((com.corrodinggames.rts.appFramework.g)object).m();
                }
                object = com.corrodinggames.rts.game.i.a(f2);
                com.corrodinggames.rts.game.i.e("Map Load Warning", (String)object);
                this.bI = false;
                return;
            }
        }
        if (!this.bL.W) {
            com.corrodinggames.rts.game.i.e("map did not load, returning");
            this.bI = false;
            return;
        }
        this.bL.G = false;
        com.corrodinggames.rts.game.n.e();
        for (n3 = 0; n3 < com.corrodinggames.rts.game.n.c; ++n3) {
            n n4 = com.corrodinggames.rts.game.n.k(n3);
            if (n4 == null) continue;
            n4.J();
        }
        if (!bl3) {
            com.corrodinggames.rts.game.units.custom.l.F();
        }
        if (!this.bX.B && !this.cb.j()) {
            this.bX.ay.h = 1.0f;
            this.bX.ay.q = com.corrodinggames.rts.gameFramework.f.a(1, 1000000000);
        }
        this.bJ = this.bX.ay.q;
        com.corrodinggames.rts.game.i.e("global Seed: " + this.bJ);
        if (this.bX.B || this.cb.j()) {
            int n5;
            int n6;
            if (!this.bX.F) {
                this.bB = this.bX.aw;
                this.bC = this.bX.ax;
            }
            com.corrodinggames.rts.gameFramework.l.e("Unit cap is now: " + this.bC);
            if (this.bX.ay.d == 0) {
                this.bL.E = false;
                this.bL.F = false;
            } else if (this.bX.ay.d == 1) {
                this.bL.E = true;
                this.bL.F = false;
            } else if (this.bX.ay.d == 2) {
                this.bL.E = true;
                this.bL.F = true;
            }
            this.bL.G = this.bX.ay.e;
            n3 = 10;
            if (this.bX.ay.e) {
                n3 = 10;
            }
            for (n6 = 0; n6 < com.corrodinggames.rts.game.n.c; ++n6) {
                n n7 = com.corrodinggames.rts.game.n.k(n6);
                if (n7 == null) continue;
                if (n7.N == null) {
                    com.corrodinggames.rts.gameFramework.l.e("Fog null for team: " + n7.k);
                    continue;
                }
                for (int i3 = 0; i3 < this.bL.C; ++i3) {
                    for (n5 = 0; n5 < this.bL.D; ++n5) {
                        n7.N[i3][n5] = n3;
                    }
                }
            }
            n6 = this.bX.k();
            for (n2 = 0; n2 < com.corrodinggames.rts.game.n.c; ++n2) {
                Object object;
                Object object2;
                n n8 = com.corrodinggames.rts.game.n.k(n2);
                if (n8 == null) continue;
                n8.o = n6;
                if (n8.w) {
                    if (!n8.y) {
                        n8.x = n8.z != null ? n8.z : this.bX.ay.f;
                    } else {
                        n8.c("aiDifficulty is locked");
                    }
                }
                n8.I = this.bX.ay.l;
                n5 = 0;
                boolean bl4 = false;
                int n9 = this.bX.ay.g;
                if (n8.A != null) {
                    n9 = n8.A;
                }
                if (n9 == 1) continue;
                boolean bl5 = true;
                boolean bl6 = true;
                Float f3 = null;
                Float f4 = null;
                Float f5 = null;
                Float f6 = null;
                if (n9 == 5 || n9 == 4 || n9 > 10) {
                    bl6 = false;
                }
                if (n9 == 5 || n9 == 4 || n9 == 3 || n9 > 10) {
                    bl5 = false;
                }
                if (n9 == 9) {
                    bl6 = false;
                    bl5 = false;
                }
                for (com.corrodinggames.rts.game.units.am am2 : com.corrodinggames.rts.game.units.am.bF()) {
                    if (!(am2 instanceof com.corrodinggames.rts.game.units.am)) continue;
                    object2 = am2;
                    if (((com.corrodinggames.rts.game.units.am)object2).bV || ((com.corrodinggames.rts.game.units.am)object2).bX != n8) continue;
                    if (((com.corrodinggames.rts.game.units.am)object2).bO && n5 == 0) {
                        n5 = 1;
                        f3 = Float.valueOf(((com.corrodinggames.rts.game.units.am)object2).eo);
                        f4 = Float.valueOf(((com.corrodinggames.rts.game.units.am)object2).ep);
                        if (!bl5) {
                            ((com.corrodinggames.rts.game.units.am)object2).ci();
                            continue;
                        }
                    }
                    if (!((com.corrodinggames.rts.game.units.am)object2).bP || bl4) continue;
                    bl4 = true;
                    f5 = Float.valueOf(((com.corrodinggames.rts.game.units.am)object2).eo);
                    f6 = Float.valueOf(((com.corrodinggames.rts.game.units.am)object2).ep);
                    if (bl6) continue;
                    ((com.corrodinggames.rts.game.units.am)object2).ci();
                }
                if (f3 == null) {
                    f3 = f5;
                    f4 = f6;
                }
                if (f3 == null) {
                    com.corrodinggames.rts.gameFramework.l.e("placementLocation==null for team:" + n8.k);
                    continue;
                }
                float f7 = f3.floatValue();
                float f8 = f4.floatValue();
                if (n9 == 2) {
                    int n10;
                    for (n10 = 0; n10 <= 2; ++n10) {
                        if (n10 == 1) continue;
                        object = com.corrodinggames.rts.game.units.ar.h.a();
                        ((com.corrodinggames.rts.game.units.am)object).b(n8);
                        ((com.corrodinggames.rts.game.units.am)object).eo = f7 - 50.0f + (float)(n10 * 50);
                        ((com.corrodinggames.rts.game.units.am)object).ep = f8;
                        com.corrodinggames.rts.game.n.c((com.corrodinggames.rts.game.units.am)object);
                    }
                    for (n10 = 0; n10 <= 2; ++n10) {
                        object = com.corrodinggames.rts.game.units.ar.w.a();
                        ((com.corrodinggames.rts.game.units.am)object).b(n8);
                        ((com.corrodinggames.rts.game.units.am)object).eo = f7 - 50.0f + (float)(n10 * 50);
                        ((com.corrodinggames.rts.game.units.am)object).ep = f8 + 50.0f;
                        com.corrodinggames.rts.game.n.c((com.corrodinggames.rts.game.units.am)object);
                    }
                    continue;
                }
                if (n9 == 3 || n9 == 4) {
                    for (int i4 = 0; i4 <= 2; ++i4) {
                        object = com.corrodinggames.rts.game.units.ar.a("combatEngineer");
                        if (object == null) {
                            com.corrodinggames.rts.gameFramework.j.ad.g("Could not find: combatEngineer on network.setup.startingUnits==3");
                            continue;
                        }
                        com.corrodinggames.rts.game.units.am am3 = object.a();
                        am3.b(n8);
                        am3.eo = f7 - 50.0f + (float)(i4 * 50);
                        am3.ep = f8 + 50.0f;
                        com.corrodinggames.rts.game.n.c(am3);
                    }
                    continue;
                }
                if (n9 == 5) {
                    object2 = com.corrodinggames.rts.game.units.ar.a("experimentalSpider");
                    if (object2 == null) {
                        com.corrodinggames.rts.gameFramework.j.ad.g("Could not find: experimentalSpider on network.setup.startingUnits==5");
                        continue;
                    }
                    object = object2.a();
                    ((com.corrodinggames.rts.game.units.am)object).b(n8);
                    ((com.corrodinggames.rts.game.units.am)object).eo = f7;
                    ((com.corrodinggames.rts.game.units.am)object).ep = f8;
                    ((com.corrodinggames.rts.game.units.am)object).cg = 90.0f;
                    ((com.corrodinggames.rts.game.units.am)object).eq = 2.0f;
                    ((com.corrodinggames.rts.game.units.am)object).dc();
                    com.corrodinggames.rts.game.n.c((com.corrodinggames.rts.game.units.am)object);
                    continue;
                }
                if (n9 == 9 || n9 <= 10) continue;
                object2 = com.corrodinggames.rts.game.units.custom.l.c(n9);
                if (object2 == null) {
                    com.corrodinggames.rts.gameFramework.j.ad.g("Could not find starting unit on startingUnits==" + n9);
                    continue;
                }
                object = ((com.corrodinggames.rts.game.units.custom.l)object2).a();
                ((com.corrodinggames.rts.game.units.am)object).b(n8);
                ((com.corrodinggames.rts.game.units.am)object).eo = f7;
                ((com.corrodinggames.rts.game.units.am)object).ep = f8;
                if (!((com.corrodinggames.rts.game.units.am)object).bI()) {
                    ((com.corrodinggames.rts.game.units.am)object).cg = 90.0f;
                }
                if (((com.corrodinggames.rts.game.units.custom.l)object2).eI) {
                    ((com.corrodinggames.rts.game.units.am)object).dc();
                    if (object instanceof com.corrodinggames.rts.game.units.custom.j) {
                        ((com.corrodinggames.rts.game.units.custom.j)object).dB();
                    }
                }
                com.corrodinggames.rts.game.n.c((com.corrodinggames.rts.game.units.am)object);
            }
        }
        if (!(bl3 || this.ce != null && this.ce.q)) {
            this.a(0.0f, 0.0f);
            n3 = 0;
            int n11 = 0;
            n2 = 0;
            for (com.corrodinggames.rts.game.units.am am4 : com.corrodinggames.rts.game.units.am.bE) {
                if (am4 instanceof al) {
                    ++n11;
                } else {
                    ++n3;
                }
                if (am4.bX != this.bs || !am4.bP) continue;
                this.b(am4.eo, am4.ep);
                n2 = 1;
            }
            if (n2 == 0) {
                for (com.corrodinggames.rts.game.units.am am5 : com.corrodinggames.rts.game.units.am.bE) {
                    if (am5.bX != this.bs || am5.t() || am5.u()) continue;
                    this.b(am5.eo, am5.ep);
                }
            }
            com.corrodinggames.rts.game.i.e("there are " + n3 + " units on this map and " + n11 + " trees");
        }
        this.B = com.corrodinggames.rts.gameFramework.be.c(this.am).b(this.ak());
        this.bU.a(this.bL, bl3);
        this.bW.a(this.bL, bl3);
        this.cf.a();
        this.bV.a();
        if (!bl3) {
            com.corrodinggames.rts.gameFramework.d.a.a();
        }
        this.ca.a(bl3);
        this.bS.a(bl3);
        if (!bl3) {
            this.bS.y();
            this.aG();
            if (this.bv) {
                this.bS.y();
            }
        } else {
            this.bS.y();
        }
        this.cc.a(this.bL);
        if (!bl3) {
            this.bN.c();
        }
        this.bY.a();
        for (com.corrodinggames.rts.game.units.am am6 : com.corrodinggames.rts.game.units.am.bE) {
            if (!(am6 instanceof y)) continue;
            y y2 = (y)am6;
            y2.c(false);
        }
        this.B.e = true;
        this.C.a(this.am);
        this.bG = true;
        this.bH = false;
        this.bI = false;
        if (s2 != com.corrodinggames.rts.gameFramework.s.a && !this.bQ.hasPlayedGameOrSeenHelp) {
            this.bQ.hasPlayedGameOrSeenHelp = true;
            this.bQ.save();
        }
        for (int i5 = 0; i5 < 5; ++i5) {
            System.gc();
        }
        if (!com.corrodinggames.rts.gameFramework.l.aU) {
            Log.a("RustedWarfare", "getNativeHeapSize" + String.valueOf(Debug.getNativeHeapSize()));
            Log.a("RustedWarfare", "getNativeHeapAllocatedSize" + String.valueOf(Debug.getNativeHeapAllocatedSize()));
            Log.a("RustedWarfare", "getNativeHeapFreeSize" + String.valueOf(Debug.getNativeHeapFreeSize()));
            Log.a("RustedWarfare", "Runtime.getRuntime().maxMemory()" + String.valueOf(Runtime.getRuntime().maxMemory()));
        }
        if (this.dk != null) {
            this.dk.a();
        }
        this.G = 0.0f;
        if (this.bX.F && this.bX.B) {
            com.corrodinggames.rts.gameFramework.l.e("Disabling network for singleplayer");
            this.bX.B = false;
        }
        if (!com.corrodinggames.rts.game.i.ax()) {
            if (s2 == com.corrodinggames.rts.gameFramework.s.c) {
                com.corrodinggames.rts.gameFramework.l.e("Not starting replay recording as we are loading a save");
            } else {
                this.cb.a(bl3);
            }
        }
        if (com.corrodinggames.rts.gameFramework.k.l.m) {
            // empty if block
        }
    }

    private void aG() {
        this.bS.y();
        for (com.corrodinggames.rts.game.units.am am2 : com.corrodinggames.rts.game.units.am.bE) {
            if (am2.bX != this.bs || !(am2 instanceof y) || !am2.ak() || !am2.s_() || !am2.bT() || am2.u() || am2.t()) continue;
            com.corrodinggames.rts.gameFramework.l.e("selectAnyOnScreenBuilder: found builder");
            this.bS.j(am2);
            return;
        }
        com.corrodinggames.rts.gameFramework.l.e("selectAnyOnScreenBuilder: no builder found");
    }

    @Override
    public void g() {
        o o2 = com.corrodinggames.rts.gameFramework.w.dK();
        for (Object object : o2) {
            ((w)object).a();
        }
        com.corrodinggames.rts.game.units.am.bF();
        com.corrodinggames.rts.gameFramework.w.dK();
        int n2 = o2.size();
        if (n2 != 0) {
            com.corrodinggames.rts.gameFramework.l.a("SHOULD_NOT_HAPPEN: we still had " + n2 + " objects in gameObjectListForLogic after removeAll");
            for (w w2 : o2) {
                String string = "Object: " + w2.eh;
                if (w2 instanceof com.corrodinggames.rts.game.units.am) {
                    string = ((com.corrodinggames.rts.game.units.am)w2).c();
                }
                com.corrodinggames.rts.gameFramework.l.a("Remaining object: " + string);
            }
            if (com.corrodinggames.rts.gameFramework.l.B().aa()) {
                throw new RuntimeException("We still had " + n2 + " objects in gameObjectListForLogic after removeAll");
            }
        }
        com.corrodinggames.rts.game.units.am.bF().clear();
        com.corrodinggames.rts.gameFramework.w.dK().clear();
        com.corrodinggames.rts.game.units.custom.j.dD();
        this.W.clear();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(boolean bl2) {
        Object object = this.aj;
        synchronized (object) {
            if (this.ao != null) {
                this.ao.l();
            }
            this.bq = false;
            if (!bl2) {
                this.cb.g();
            }
            this.bU.c();
            this.g();
            if (!com.corrodinggames.rts.game.i.av()) {
                this.bN.f();
            }
            this.bR.a(bl2);
            if (this.bL != null) {
                this.bL.h();
                this.bL = null;
            }
            if (this.ce != null) {
                this.ce = null;
            }
            if (this.cc != null) {
                this.cc.b();
            }
            this.af = null;
            this.ag = null;
            this.j = 0;
            com.corrodinggames.rts.game.n.Y();
            this.a(com.corrodinggames.rts.gameFramework.g.f.a, com.corrodinggames.rts.gameFramework.g.c.a);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(float f2, int n2) {
        Object object = this.aj;
        synchronized (object) {
            this.b(f2, n2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(float f2, int n2) {
        float f3;
        float f4;
        float f5;
        if (this.bx == 2) {
            this.aF();
        } else if (this.bx % 10000 == 0 && this.bx != 0) {
            this.aF();
        }
        if (aL && !this.aS && com.corrodinggames.rts.game.i.at() && Debug.getNativeHeapAllocatedSize() > 0xC800000L) {
            com.corrodinggames.rts.gameFramework.l.e("getNativeHeapAllocatedSize: " + com.corrodinggames.rts.gameFramework.f.g((int)Debug.getNativeHeapAllocatedSize()));
            this.aS = true;
        }
        this.aE();
        this.eb.a();
        this.ec.b();
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.a);
        this.bX.b(f2);
        this.ao = this.ap;
        if (!this.ao.b()) {
            return;
        }
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.b);
        while (this.k.peek() != null) {
            Runnable runnable = (Runnable)this.k.poll();
            runnable.run();
        }
        if (!this.bG) {
            if (this.aq) {
                return;
            }
            Log.d("RustedWarfare", "game running without a loaded level!!!");
            this.h();
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            return;
        }
        this.bq = true;
        if (!this.F && this.bx > 5) {
            this.F = true;
            boolean bl2 = false;
            if (this.bQ.numIncompleteLoadAttempts > 1) {
                bl2 = true;
            }
            this.bQ.numIncompleteLoadAttempts = 0;
            if (this.ee) {
                this.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
            }
            this.bQ.save();
            if (this.ee && (this.ef || this.bZ.c() > 0)) {
                if (this.eg != null) {
                    this.c("Safe mode", "Started game in safe mode due to " + this.eg + ". Mods have been disabled.");
                } else if (bl2) {
                    this.c("Safe mode", "Started game in safe mode due to failed loading attempts. Mods have been disabled.");
                } else {
                    this.c("Safe mode", "Started game in safe mode due to multiple loads without starting a game or exiting. Mods have been disabled.");
                }
            }
        }
        if (!this.bH && this.bG && this.bQ.numLoadsSinceRunningGameOrNormalExit != 0) {
            this.bQ.numLoadsSinceRunningGameOrNormalExit = 0;
            this.bQ.save();
        }
        this.ca.b();
        float f6 = this.cV * this.cY;
        if (f6 != this.cX) {
            f5 = this.da / this.cX + this.cy;
            f4 = this.db / this.cX + this.cz;
            this.cX = f6;
            this.k();
            if (this.cZ) {
                f3 = this.da / this.cX + this.cy;
                float f7 = this.db / this.cX + this.cz;
                this.a(this.cy - (f3 - f5), this.cz - (f7 - f4));
                this.cZ = false;
            }
        }
        if (this.cr != 0.0f || this.cs != 0.0f) {
            f5 = 3.0f * f2;
            f4 = 0.0f;
            if (this.cr > 0.0f) {
                f4 = com.corrodinggames.rts.gameFramework.f.g(this.cr, f5);
            }
            if (this.cr < 0.0f) {
                f4 = com.corrodinggames.rts.gameFramework.f.f(this.cr, -f5);
            }
            f4 += 0.15f * this.cr;
            f3 = 0.0f;
            if (this.cs > 0.0f) {
                f3 = com.corrodinggames.rts.gameFramework.f.g(this.cs, f5);
            }
            if (this.cs < 0.0f) {
                f3 = com.corrodinggames.rts.gameFramework.f.f(this.cs, -f5);
            }
            f3 += 0.15f * this.cs;
            if (com.corrodinggames.rts.gameFramework.f.c(this.cr) <= f5) {
                f4 = this.cr;
                this.cr = 0.0f;
            } else {
                this.cr -= f4;
            }
            if (com.corrodinggames.rts.gameFramework.f.c(this.cs) <= f5) {
                f3 = this.cs;
                this.cs = 0.0f;
            } else {
                this.cs -= f3;
            }
            this.cy += f4;
            this.cz += f3;
            this.a(this.cy, this.cz);
            this.Q();
        }
        if (this.cR != this.cS) {
            this.k();
        }
        if (f2 > 3.0f) {
            f2 = 3.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (this.bu >= 0.0f) {
            f2 = this.bu;
        }
        this.bA = (int)((float)this.bA + f2 * 16.666666f);
        this.d(f2);
        this.q += n2;
        ++this.r;
        if (this.r >= 40) {
            if (this.q == 0) {
                this.q = 1;
            }
            this.s = (int)((float)(this.r * 1000 / this.q) + 0.5f);
            this.t = (float)this.q / (float)this.r;
            this.q = 0;
            this.r = 0;
            if (this.bQ.showFps) {
                this.u = this.s + "fps";
            }
        }
        this.aj();
        for (int i2 = 0; i2 < this.dM.length; ++i2) {
            this.dM[i2] = true;
        }
        this.dh = com.corrodinggames.rts.gameFramework.f.a(this.dh, 0.1f * f2);
        this.di = com.corrodinggames.rts.gameFramework.f.a(this.di, 0.1f * f2);
        this.dh = com.corrodinggames.rts.gameFramework.f.b(this.dh, 5.0f);
        this.di = com.corrodinggames.rts.gameFramework.f.b(this.di, 5.0f);
        this.bS.a(f2);
        this.Q();
        com.corrodinggames.rts.game.b.b.f();
        if (this.bX.B) {
            float f8 = f2;
            if (this.cb.v != 1) {
                f8 *= (float)this.cb.v;
            }
            this.bX.a(f8);
            if (!this.a(true) && !this.bX.Y) {
                this.G += f8;
                while (this.G > this.bX.c()) {
                    if (this.bX.I()) {
                        this.bX.Y = true;
                        break;
                    }
                    this.G -= this.bX.c();
                    this.bX.a(this.bX.c(), false);
                    if (this.bX.Y) break;
                    this.a(this.bX.c());
                }
                if (!this.bX.C) {
                    if (this.bX.af || this.bX.ad) {
                        if (this.bX.af && this.bX.ad && this.bx < this.bX.X - this.bX.Q - 5) {
                            this.bX.d("nearly within frame range");
                            this.bX.af = false;
                        }
                        if (this.bx > this.bX.X - 6) {
                            this.bX.d("we have back within frame range");
                            this.bX.af = false;
                            this.bX.ad = false;
                        }
                    }
                    if (!this.bX.ad && this.bx < this.bX.X - this.bX.Q - 10) {
                        this.bX.d("we are slightly out of frame range, speeding up");
                        this.bX.ad = true;
                    }
                    if (!this.bX.af && this.bx < this.bX.X - this.bX.Q - 30) {
                        this.bX.d("we are out of frame range, fast forwarding (" + this.bx + "->" + this.bX.X + ")");
                        this.bX.af = true;
                    }
                    if (!this.bX.af && this.bX.ad) {
                        this.bX.ae += f2;
                        if (this.bX.ae > this.bX.c() * 3.0f) {
                            this.bX.ae = 0.0f;
                            this.bX.a(this.bX.c(), true);
                            if (!this.bX.Y) {
                                this.a(this.bX.c());
                            }
                        }
                    }
                    if (this.bX.af) {
                        this.bX.a(this.bX.c(), true);
                        if (!this.bX.Y) {
                            this.a(this.bX.c());
                        }
                    }
                    if (this.bx < this.bX.X - 90) {
                        this.bX.a(this.bX.c(), true);
                        if (!this.bX.Y) {
                            this.a(this.bX.c());
                        }
                    }
                    if (this.bx < this.bX.X - 120) {
                        this.bX.a(this.bX.c(), true);
                        if (!this.bX.Y) {
                            this.a(this.bX.c());
                        }
                    }
                    if (this.bx < this.bX.X - 600) {
                        this.bX.a(this.bX.c(), true);
                        if (!this.bX.Y) {
                            this.a(this.bX.c());
                        }
                    }
                }
            }
        } else if (this.cb.i()) {
            float f9 = f2;
            if (this.cb.v != 1) {
                f9 *= (float)this.cb.v;
            }
            if (this.bt != 1.0f) {
                f9 *= this.bt;
            }
            if (!this.a(false)) {
                this.G += f9;
                while (this.G > this.bX.c()) {
                    this.G -= this.bX.c();
                    if (this.bX.I()) break;
                    this.a(this.bX.c());
                }
            }
            if (this.G > 100.0f) {
                this.G = 100.0f;
            }
            if (this.G < 0.0f) {
                this.G = 0.0f;
            }
        } else if (!this.a(false)) {
            this.a(f2);
        }
        if (this.a(false)) {
            try {
                Thread.sleep(2L);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.bU.a(f2);
        this.bM.b(f2);
        this.bN.a(f2);
        this.bT.b();
        com.corrodinggames.rts.gameFramework.o.a.a().a(f2);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.b);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.c);
        if (!this.dv) {
            if (this.bO.a()) {
                this.a((com.corrodinggames.rts.gameFramework.m.l)null, f2);
            } else if (this.ao.n()) {
                com.corrodinggames.rts.gameFramework.m.l l2 = this.ao.b(true);
                this.a(l2, f2);
            } else {
                com.corrodinggames.rts.appFramework.f f10 = this.ao;
                this.ao.a(f2, n2);
                if (f10.c() && !f10.e()) {
                    Object object = f10.g();
                    synchronized (object) {
                        if (f10.c() && !f10.e()) {
                            this.cd.a(com.corrodinggames.rts.gameFramework.bs.w);
                            com.corrodinggames.rts.gameFramework.m.l l3 = f10.b(true);
                            this.cd.b(com.corrodinggames.rts.gameFramework.bs.w);
                            try {
                                if (!f10.e()) {
                                    if (l3 != null) {
                                        if (l3.c()) {
                                            com.corrodinggames.rts.gameFramework.l.e("gameengine draw: bufferedCanvas drawn on");
                                        }
                                        l3.a(true);
                                    }
                                    if (l3 == null) {
                                        com.corrodinggames.rts.gameFramework.l.f("GameEngine gameViewCanvas is null after lockCanvas - " + f10.hashCode());
                                    }
                                    this.a(l3, f2);
                                    this.bO.a((com.corrodinggames.rts.gameFramework.m.l)null);
                                }
                            }
                            finally {
                                if (l3 != null) {
                                    try {
                                        f10.a(l3, true);
                                    }
                                    catch (IllegalArgumentException illegalArgumentException) {
                                        illegalArgumentException.printStackTrace();
                                        com.corrodinggames.rts.gameFramework.l.f("GameEngine catch currentGameView - " + f10.hashCode());
                                        com.corrodinggames.rts.gameFramework.l.f("GameEngine catch currentGameView.gameThreadSync - " + f10.g().hashCode());
                                        f10.h();
                                    }
                                    catch (IllegalStateException illegalStateException) {
                                        illegalStateException.printStackTrace();
                                        com.corrodinggames.rts.gameFramework.l.f("GameEngine catch currentGameView - " + f10.hashCode());
                                        com.corrodinggames.rts.gameFramework.l.f("GameEngine catch currentGameView.gameThreadSync - " + f10.g().hashCode());
                                    }
                                }
                            }
                        }
                    }
                }
                this.ao.b(f2, n2);
            }
        }
        this.dv = false;
        this.Z();
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.c);
        if (this.du) {
            this.du = false;
            Integer n3 = com.corrodinggames.rts.game.i.l(this.dl);
            String string = null;
            if (n3 != null) {
                string = com.corrodinggames.rts.game.i.m(this.dl);
            }
            if (this.bX.B) {
                string = null;
                new i$a(this).start();
            }
            if (string != null) {
                com.corrodinggames.rts.gameFramework.l.e("gotoNextLevel: Loading next level: " + string);
                this.dl = string;
                this.bS.h.b();
                this.a(true, false, com.corrodinggames.rts.gameFramework.s.b);
            } else {
                com.corrodinggames.rts.gameFramework.l.e("gotoNextLevel: No next level, finishing");
                this.bG = false;
                com.corrodinggames.rts.appFramework.g g2 = this.ao.i();
                if (g2 != null) {
                    g2.b();
                    g2.m();
                } else {
                    com.corrodinggames.rts.gameFramework.l.e("gotoNextLevel: Error getInGameActivity==null");
                }
            }
        }
        if (!this.aq && this.bE && !this.i) {
            com.corrodinggames.rts.game.i.e("starting method trace");
            Debug.startMethodTracing((String)"lukeTrace", (int)110000000);
            this.i = true;
        }
        this.bF = true;
        this.ed.a();
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.a);
        this.cd.b();
    }

    public void h() {
        com.corrodinggames.rts.appFramework.g g2 = this.ao.i();
        if (g2 != null) {
            if (!g2.c()) {
                g2.b();
            } else {
                com.corrodinggames.rts.gameFramework.l.b("stopAndClose: inGameActivity is isFinishing");
            }
        } else {
            com.corrodinggames.rts.gameFramework.l.b("stopAndClose: Error getInGameActivity==null");
        }
    }

    public void a(float f2) {
        Object object2;
        int n2;
        int n3;
        if (this.ay() && f2 < 0.1f) {
            com.corrodinggames.rts.gameFramework.j.ad.g("updateAllGame1: deltaSpeed:" + f2 + " frame:" + this.bx + " network.currentStepRate:" + this.bX.c());
        }
        if (this.bt != 1.0f && !this.bX.B && !this.cb.i()) {
            f2 *= this.bt;
        }
        this.I = (f2 *= this.H) + 2.0f;
        this.J = f2;
        this.bX.c(f2);
        this.by = (int)((float)this.by + f2 * 16.666666f);
        this.cf.c();
        this.cb.a(f2);
        ++this.bx;
        com.corrodinggames.rts.game.n.g(f2);
        if (this.bL != null) {
            this.bL.e(f2);
        }
        if (this.ay() && f2 < 0.1f) {
            com.corrodinggames.rts.gameFramework.j.ad.g("updateAllGame2: deltaSpeed:" + f2 + " frame:" + this.bx);
        }
        com.corrodinggames.rts.game.units.am.bF();
        o o2 = com.corrodinggames.rts.gameFramework.w.dK();
        Object[] objectArray = o2.b();
        int n4 = o2.size();
        boolean bl2 = this.ay();
        for (n3 = 0; n3 < n4; ++n3) {
            w w2 = (w)objectArray[n3];
            if (bl2 && f2 != this.J) {
                com.corrodinggames.rts.gameFramework.j.ad.h("JIT bug detected, attempting to correct. before object:" + w2.eh + " frame:" + this.bx + " deltaSpeed:" + f2);
                f2 = this.J;
            }
            w2.a(f2);
        }
        if (this.ay() && f2 < 0.1f) {
            com.corrodinggames.rts.gameFramework.j.ad.g("updateAllGame3: deltaSpeed:" + f2 + " frame:" + this.bx);
        }
        n3 = o2.a.size();
        for (n2 = 0; n2 < n3; ++n2) {
            r r2 = (r)o2.a.get(n2);
            if (r2.a != com.corrodinggames.rts.gameFramework.utility.q.a) continue;
            object2 = (w)r2.b;
            if (((w)object2).ej) continue;
            ((w)object2).a(f2);
        }
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.m);
        this.cc.a();
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.m);
        com.corrodinggames.rts.game.units.y.g(f2);
        com.corrodinggames.rts.game.units.custom.j.s(f2);
        com.corrodinggames.rts.game.units.custom.j.a(f2, 0);
        ++this.j;
        if (this.j >= 1000) {
            this.j = 0;
            n2 = 0;
            for (Object object2 : com.corrodinggames.rts.game.units.am.bF()) {
                if (!((com.corrodinggames.rts.game.units.am)object2).bV || object2 instanceof al) continue;
                ++n2;
            }
            int n5 = 70;
            if (n2 > 70) {
                object2 = com.corrodinggames.rts.game.units.am.bF();
                Iterator iterator = ((o)object2).iterator();
                while (iterator.hasNext()) {
                    com.corrodinggames.rts.game.units.am am2 = (com.corrodinggames.rts.game.units.am)iterator.next();
                    if (!(am2 instanceof com.corrodinggames.rts.game.units.am)) continue;
                    com.corrodinggames.rts.game.units.am am3 = am2;
                    if (!am3.bV || am3 instanceof al || am3.bW >= (long)(this.by - 30000) || n2 <= 70) continue;
                    am3.a();
                    --n2;
                }
            }
        }
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.l);
        com.corrodinggames.rts.game.n.f(f2);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.l);
        com.corrodinggames.rts.gameFramework.d.a.a(f2);
        this.bR.a(f2);
        this.D.a(f2);
        com.corrodinggames.rts.gameFramework.utility.y.a(f2);
        if (this.ce != null) {
            this.ce.c(f2);
        }
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.o);
        this.bV.a(f2);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.o);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.n);
        this.bW.a(f2);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.n);
        this.bU.b(f2);
        if (this.cg != null) {
            this.cg.b();
        }
        this.bY.b();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(com.corrodinggames.rts.gameFramework.m.l l2, float f2) {
        Object object = this.ak;
        synchronized (object) {
            this.b(l2, f2);
        }
    }

    public boolean i() {
        if (this.K == null) {
            this.K = new com.corrodinggames.rts.game.j("assets/shaders/post_base.frag");
        }
        if (this.L == null) {
            this.L = new com.corrodinggames.rts.game.j("assets/shaders/post_displacement.frag");
        }
        this.K.a(this.bO);
        this.L.a(this.bO);
        if (this.K.g || this.L.g) {
            if (!this.M) {
                this.M = true;
                com.corrodinggames.rts.gameFramework.l.e("setupPostprocessing: failed");
            }
            return false;
        }
        return true;
    }

    public void a(com.corrodinggames.rts.game.j j2) {
        if (this.N != null) {
            throw new RuntimeException("Layer already enabled");
        }
        this.N = this.bO;
        this.bO = j2.b;
        this.bO.i();
        this.bO.a(new Rect(0, 0, this.bO.m(), this.bO.n()));
        this.bO.b(j2.f, j2.e);
    }

    public void b(com.corrodinggames.rts.game.j j2) {
        if (this.N == null) {
            throw new RuntimeException("Layer not enabled");
        }
        this.bO.j();
        this.bO.p();
        this.bO = this.N;
        this.N = null;
        this.bO.b(j2.f, j2.e);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(com.corrodinggames.rts.gameFramework.m.l l2, float f2) {
        int n2;
        boolean bl2;
        if (l2 == null) {
            com.corrodinggames.rts.game.i.b("drawAll", "canvas is null, not may not be available yet");
            return;
        }
        if (aB) {
            return;
        }
        this.bO.a(l2);
        this.bO.a(this.ao.d());
        this.bO.g();
        ++this.bz;
        com.corrodinggames.rts.gameFramework.m.h.G = 0.0f;
        if (this.du) {
            this.bO.b(Color.a(0, 0, 0));
            this.bO.a("Loading..", this.co, this.cp, this.dp);
            return;
        }
        float f3 = this.cn;
        if (f3 != 1.0f) {
            this.bO.i();
            this.bO.a(f3, f3);
        }
        if ((bl2 = com.corrodinggames.rts.gameFramework.l.aA()) && this.h(113) && this.h(44)) {
            bl2 = false;
        }
        if (bl2 && (n2 = this.i()) == 0) {
            bl2 = false;
        }
        if (bl2) {
            this.a(this.K);
            try {
                this.bO.b(Color.a(0, 0, 0));
                this.cd.a(com.corrodinggames.rts.gameFramework.bs.d);
                this.c(null, f2);
                this.cd.b(com.corrodinggames.rts.gameFramework.bs.d);
            }
            finally {
                this.b(this.K);
            }
            this.K.b();
            if (!this.L.a()) {
                this.a(this.L);
                try {
                    this.bO.b(Color.a(128, 128, 255));
                    this.R();
                    n2 = this.bR.a(f2, 3);
                    this.bR.l = null;
                }
                finally {
                    this.b(this.L);
                }
                if (n2 > 0) {
                    float f4 = this.bO.s();
                    this.L.d.a("screenBase", this.K.a);
                    this.L.d.b("screenBaseSize", this.K.a);
                    this.L.d.a("u_resolution", this.cl, this.cm);
                    this.L.d.a("u_offsetBy", 0.2f * this.cX);
                    this.L.d.a("u_uiScaling", f4);
                    this.L.b();
                }
            }
        } else {
            this.cd.a(com.corrodinggames.rts.gameFramework.bs.d);
            this.c(l2, f2);
            this.cd.b(com.corrodinggames.rts.gameFramework.bs.d);
        }
        if (!this.A()) {
            this.cd.a(com.corrodinggames.rts.gameFramework.bs.f);
            this.d(l2, f2);
            this.cd.b(com.corrodinggames.rts.gameFramework.bs.f);
        }
        if (this.bQ.showFps && this.cT == 0.0f && !this.cU && !this.cS) {
            this.bO.a(this.u, 100.0f, 35.0f, this.m);
        }
        if (f != null) {
            this.bO.a(f, 100.0f, 85.0f, this.m);
        }
        if (!this.aq && (this.bO.d() != null || com.corrodinggames.rts.gameFramework.l.aW)) {
            this.bS.c(f2);
        }
        if (!this.A()) {
            this.bR.a(f2, 4);
        }
        com.corrodinggames.rts.game.units.custom.j.dE();
        this.bO.h();
        if (f3 != 1.0f) {
            l2.a();
        }
    }

    public boolean j() {
        if (!this.bQ.showUnitIcons) {
            return false;
        }
        if ((double)this.cX < 0.7 && this.cE >= this.bL.i() - 5.0f && this.cB >= this.bL.j() - 5.0f) {
            return true;
        }
        if (com.corrodinggames.rts.game.i.C()) {
            return (double)this.cX < 0.1;
        }
        if (com.corrodinggames.rts.game.i.av()) {
            return (double)this.cX < 0.27;
        }
        return (double)this.cX < 0.4;
    }

    public void b(float f2) {
        boolean bl2 = false;
        if (this.cQ.a < 0 || this.cQ.b < 0 || (float)this.cQ.c > this.bL.i() || (float)this.cQ.d > this.bL.j()) {
            bl2 = true;
        }
        if (bl2) {
            this.bO.b(Color.a(0, 0, 0));
        }
    }

    public void c(float f2) {
    }

    public void c(com.corrodinggames.rts.gameFramework.m.l l2, float f2) {
        w w2;
        int n2;
        int n3;
        w[] wArray;
        int n4;
        if (!this.bG) {
            return;
        }
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.h);
        this.X.b();
        this.dw = 0;
        boolean bl2 = false;
        w[] wArray2 = com.corrodinggames.rts.game.units.am.er.a();
        int n5 = com.corrodinggames.rts.gameFramework.w.er.size();
        for (n4 = 0; n4 < n5; ++n4) {
            wArray = wArray2[n4];
            n3 = wArray.el;
            wArray.el = n2 = wArray.a(this);
            if (n3 != n2) {
                bl2 = true;
            }
            if (n2 == 0) continue;
            this.X.a((w)wArray);
        }
        if (this.W.size() != this.X.size()) {
            bl2 = true;
        }
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.h);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.i);
        if (bl2) {
            s s2 = this.W;
            this.W = this.X;
            this.X = s2;
        }
        if (!this.j()) {
            Collections.sort(this.W, com.corrodinggames.rts.gameFramework.w.ei);
        }
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.i);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.q);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.s);
        this.bO.i();
        this.bO.a(this.cK);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.s);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.r);
        this.b(f2);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.r);
        if (this.bQ.renderFancyWater) {
            if (this.O == null) {
                this.O = this.bO.a(R$drawable.water_cloud);
            }
            if (this.P == null) {
                this.P = this.bO.a(R$drawable.water_layer1);
            }
            if (this.Q == null) {
                this.Q = this.bO.a(R$drawable.water_layer2);
            }
            this.S.a(this.cK);
            this.R += 0.05f * f2;
            if (this.R > 100.0f) {
                this.R -= 100.0f;
            }
            this.bO.a(this.O, this.S, null, this.cu / 6, this.cv / 6, 1, 1);
            this.S.a(this.cL);
            this.T.a(this.cL);
            this.bO.i();
            this.R();
            this.bO.a(this.Q, this.T, null, (float)this.cu + this.R, (float)this.cv + this.R, 0, 0);
            this.bO.a(this.P, this.T, null, (float)this.cu, (float)this.cv, 0, 0);
            this.bO.j();
        }
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.t);
        if (this.bL != null && this.ar()) {
            this.bL.d(f2);
        }
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.t);
        this.R();
        this.bO.a(this.cL);
        n4 = this.j() ? 1 : 0;
        this.bU.c(f2);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.q);
        wArray = this.W.a();
        n3 = this.W.size();
        this.dc = true;
        this.dd = true;
        this.de = true;
        this.df = true;
        this.dg = true;
        if ((double)this.cX < 0.45) {
            this.de = false;
            this.dc = false;
            this.dg = false;
        }
        if ((double)this.cX < 0.3) {
            this.df = false;
            this.dd = false;
        }
        if (n4 == 0) {
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                if (w2.em != 0) continue;
                w2.c(f2);
            }
        }
        com.corrodinggames.rts.gameFramework.d.a.b(f2);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.g);
        this.bR.b(f2);
        this.bR.a(f2, 1);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.g);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.p);
        if (n4 != 0) {
            if (this.bS.q() == 0) {
                com.corrodinggames.rts.game.units.am.bI.a(255, 195, 195, 195);
                com.corrodinggames.rts.game.units.am.bJ.a(255, 255, 255, 255);
            } else {
                com.corrodinggames.rts.game.units.am.bI.a(175, 175, 175, 175);
                com.corrodinggames.rts.game.units.am.bJ.a(255, 255, 255, 255);
            }
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                if (w2.f(f2)) continue;
                w2.c(f2);
            }
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                w2.a(f2, true);
                w2.p(f2);
            }
        } else {
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                w2.d(f2);
            }
            for (n2 = 0; n2 < n5; ++n2) {
                w2 = wArray2[n2];
                if (!w2.el) {
                    if (!(w2 instanceof com.corrodinggames.rts.game.units.am)) continue;
                    com.corrodinggames.rts.game.units.am am2 = (com.corrodinggames.rts.game.units.am)w2;
                    if (!am2.cG || am2.bX != this.bs && !am2.cf()) continue;
                }
                w2.e(f2);
                if (w2.el) continue;
                w2.p(f2);
            }
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                if (w2.em == 0 || w2.em == 10) continue;
                w2.c(f2);
            }
            for (n2 = 0; n2 < n3; ++n2) {
                w2 = wArray[n2];
                w2.a(f2, false);
                w2.p(f2);
            }
            com.corrodinggames.rts.game.n.h(f2);
        }
        this.de = true;
        this.df = true;
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.p);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.g);
        this.bR.a(f2, 2);
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.g);
        for (n2 = 0; n2 < n3; ++n2) {
            w2 = wArray[n2];
            if (w2.em != 10) continue;
            w2.c(f2);
        }
        this.D.b(f2);
        if (this.ce != null) {
            this.ce.a(f2);
        }
        this.c(f2);
        com.corrodinggames.rts.gameFramework.utility.y.b(f2);
        this.cc.c(f2);
        this.cd.a(com.corrodinggames.rts.gameFramework.bs.e);
        this.bO.j();
        this.cd.b(com.corrodinggames.rts.gameFramework.bs.e);
    }

    public void d(com.corrodinggames.rts.gameFramework.m.l l2, float f2) {
        this.bS.b(f2);
        if (this.ce != null) {
            this.ce.b(f2);
        }
        this.bW.e(f2);
        if (this.bQ.showFps && this.cT == 0.0f) {
            this.cd.c();
        }
        if (this.ch) {
            this.bO.a("Look Mode", this.co, this.cp, this.dp);
        }
        if (this.bm) {
            int n2 = 20;
            for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
                n n3 = com.corrodinggames.rts.game.n.k(i2);
                if (n3 == null || !(n3 instanceof com.corrodinggames.rts.game.a.a)) continue;
                com.corrodinggames.rts.game.a.a a2 = (com.corrodinggames.rts.game.a.a)n3;
                this.bO.a(a2.k + "| c:" + a2.o, 20.0f, (float)n2, this.dn);
                n2 += 20;
            }
        }
    }

    public void k() {
        float f2;
        this.cj = this.W();
        this.X();
        this.co = this.cl / 2.0f;
        this.cp = this.cm / 2.0f;
        this.cq = (int)(this.cm / 3.0f);
        if (com.corrodinggames.rts.game.i.av()) {
            this.cq = (int)(this.cm / 2.5f);
        }
        if (this.cq > (f2 = (float)((int)(this.cl / 3.0f)))) {
            this.cq = f2;
        }
        int n2 = (int)(250.0f * this.cj);
        this.cq = com.corrodinggames.rts.gameFramework.f.b(this.cq, 60.0f, (float)n2);
        float f3 = this.cy + this.cI;
        float f4 = this.cz + this.cJ;
        if (this.cS) {
            this.cF = this.cl;
            this.cG = this.cl;
        } else {
            this.cG = this.cl - this.cq + 1.0f;
            this.cF = com.corrodinggames.rts.gameFramework.f.g.bO ? this.cl : this.cG;
        }
        if (this.cF < 1.0f) {
            this.cF = 1.0f;
        }
        if (this.cG < 1.0f) {
            this.cG = 1.0f;
        }
        if (this.cR != this.cS) {
            f3 = !this.cS ? (f3 -= this.cq / 2.0f / this.cX) : (f3 += this.cq / 2.0f / this.cX);
        }
        this.cR = this.cS;
        this.cH = this.cm;
        this.cA = this.cF / this.cX;
        this.cB = this.cH / this.cX;
        this.cE = this.cG / this.cX;
        this.cI = this.cA / 2.0f;
        this.cJ = this.cB / 2.0f;
        this.cK.a(0, 0, (int)this.cF, (int)this.cH);
        this.cL.a(0, 0, (int)this.cA + 1, (int)this.cB + 1);
        this.cM.a(0.0f, 0.0f, this.cA + 1.0f, this.cB + 1.0f);
        this.a(f3 - this.cI, f4 - this.cJ);
    }

    @Override
    public void b(int n2, int n3) {
        this.a(n2, n3, 1.0f);
    }

    public void a(int n2, int n3, float f2) {
        this.cl = n2;
        this.cm = n3;
        this.cn = f2;
        this.k();
    }

    @Override
    public String l() {
        if (com.corrodinggames.rts.gameFramework.l.aX) {
            return "com.corrodinggames.rts.java";
        }
        if (com.corrodinggames.rts.gameFramework.l.aY) {
            return "com.corrodinggames.rts.gdx";
        }
        if (aU) {
            return "com.corrodinggames.rts.server";
        }
        if (this.am == null) {
            return "<null context>";
        }
        return this.am.h();
    }

    @Override
    public String m() {
        if (com.corrodinggames.rts.gameFramework.l.aX) {
            return "java";
        }
        if (com.corrodinggames.rts.gameFramework.l.aY) {
            return "java-gdx";
        }
        if (aU) {
            return "dedicatedServer";
        }
        if (this.am == null) {
            return "<null context>";
        }
        try {
            PackageManager packageManager = this.am.f();
            String string = packageManager.getInstallerPackageName(this.l());
            return string;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return "IllegalArgumentException: " + illegalArgumentException.getMessage();
        }
    }

    @Override
    public boolean n() {
        return this.v().contains("p");
    }

    @Override
    public int c(boolean bl2) {
        if (aU || bl2) {
            return 176;
        }
        try {
            PackageInfo packageInfo = this.am.f().getPackageInfo(this.am.h(), 0);
            int n2 = packageInfo.versionCode;
            return n2;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            throw new RuntimeException(nameNotFoundException);
        }
    }

    public String o() {
        if (!com.corrodinggames.rts.game.i.at()) {
            return null;
        }
        try {
            PackageInfo packageInfo = this.am.f().getPackageInfo(this.am.h(), 64);
            Signature[] signatureArray = packageInfo.signatures;
            int n2 = signatureArray.length;
            int n3 = 0;
            if (n3 < n2) {
                Signature signature = signatureArray[n3];
                String string = com.corrodinggames.rts.gameFramework.f.b(signature.toByteArray());
                return string;
            }
            return null;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            throw new RuntimeException(nameNotFoundException);
        }
    }

    @Override
    public boolean p() {
        if (!com.corrodinggames.rts.gameFramework.l.aZ) {
            if (this.q()) {
                return true;
            }
            if (aV) {
                return true;
            }
        }
        return false;
    }

    public boolean q() {
        return y.class.getSimpleName().equals("OrderableUnit");
    }

    @Override
    public String r() {
        String string = this.t();
        if ("" != null && !"".equals("")) {
            string = string + "-";
        }
        return string;
    }

    @Override
    public void s() {
        a = null;
        this.t();
    }

    @Override
    public String t() {
        if (a != null) {
            return a;
        }
        String string = "v" + this.u();
        if (!com.corrodinggames.rts.gameFramework.l.as || aV) {
            string = "DEBUG BUILD - " + string;
        } else if (com.corrodinggames.rts.gameFramework.l.at) {
            string = "TESTING BUILD - " + string;
        } else if (string.contains("p")) {
            string = "BETA VERSION - " + string;
        }
        if (!com.corrodinggames.rts.gameFramework.l.aZ && this.q()) {
            string = "RAW - " + string;
        }
        a = string;
        return a;
    }

    @Override
    public String u() {
        return "1.15";
    }

    @Override
    public String v() {
        return "1.15";
    }

    public synchronized void w() {
        this.ac = false;
        if (this.ab != null) {
            this.ab.cancel();
            this.ab = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized void a(Activity activity, com.corrodinggames.rts.appFramework.f f2, boolean bl2) {
        Object object = this.ad;
        synchronized (object) {
            if (!aU) {
                f2.a();
            }
            this.an = activity;
            this.cS = this.aq = bl2;
            if (!(!bl2 || this.bG || this.bI || com.corrodinggames.rts.gameFramework.l.ay || this.bX.B)) {
                this.x();
            }
            com.corrodinggames.rts.appFramework.f f3 = this.ap;
            if (this.ao == null) {
                this.ao = f2;
            }
            this.ap = f2;
            if (f3 != null && f3 != f2) {
                f3.j();
            }
            if (f2 != null) {
                f2.m();
            }
            if (this.bS != null) {
                this.bS.e();
            }
            this.w();
            this.J();
        }
    }

    @Override
    public synchronized void x() {
        if (this.ae > 20) {
            return;
        }
        int n2 = 3;
        int n3 = this.bQ.nextBackgroundMap++;
        if (this.bQ.nextBackgroundMap > 3) {
            this.bQ.nextBackgroundMap = 1;
        }
        this.bQ.save();
        n3 = com.corrodinggames.rts.gameFramework.f.b(n3, 1, 3);
        this.dm = null;
        this.dl = "maps/menu_background/menu" + n3 + ".tmx";
        try {
            com.corrodinggames.rts.game.n.b(10, true);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        for (int i2 = 0; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
            com.corrodinggames.rts.game.a.a a2 = new com.corrodinggames.rts.game.a.a(i2);
            if (i2 != 0) continue;
            this.bs = a2;
        }
        this.a(false, com.corrodinggames.rts.gameFramework.s.a);
        this.bH = true;
        this.bS.y();
        if (!this.bG) {
            com.corrodinggames.rts.gameFramework.l.g("Menu load failed");
            ++this.ae;
        }
    }

    void d(float f2) {
        if (this.aq && !this.bH) {
            if (this.ag == null) {
                this.ag = this.y();
                if (this.af == this.ag) {
                    this.ag = null;
                }
            }
            if (this.af == null) {
                this.af = this.ag;
                this.ag = null;
            }
            if (this.ah != 0.0f && this.ag != null) {
                this.a(f2, this.ag.eo, this.ag.ep, this.ah * 0.5f);
            }
            if (this.af != null) {
                boolean bl2 = this.a(f2, this.af.eo, this.af.ep, (1.0f - this.ah) * 0.5f);
                float f3 = com.corrodinggames.rts.gameFramework.f.a(this.cy + this.cI, this.cz + this.cJ, this.af.eo, this.af.ep);
                if (f3 < 6400.0f) {
                    bl2 = true;
                }
                if (bl2) {
                    this.ai = true;
                }
            }
            if (this.ai) {
                this.ah += 0.01f * f2;
                if (this.ah >= 1.0f) {
                    this.ah = 0.0f;
                    this.af = null;
                    this.ai = false;
                }
            }
        }
    }

    com.corrodinggames.rts.game.units.am a(n n2) {
        int n3 = 0;
        for (com.corrodinggames.rts.game.units.am am2 : com.corrodinggames.rts.game.units.am.bE) {
            if (am2.u() || am2.bX != n2 && n2 != null) continue;
            ++n3;
        }
        if (n3 > 0) {
            int n4 = com.corrodinggames.rts.gameFramework.f.a(0, n3 - 1);
            int n5 = 0;
            for (com.corrodinggames.rts.game.units.am am3 : com.corrodinggames.rts.game.units.am.bE) {
                if (am3.u() || am3.bX != n2 && n2 != null) continue;
                if (n5 == n4) {
                    return am3;
                }
                ++n5;
            }
        }
        return null;
    }

    com.corrodinggames.rts.game.units.am y() {
        com.corrodinggames.rts.game.units.am am2 = this.a(this.bs);
        if (am2 != null) {
            return am2;
        }
        return this.a((n)null);
    }

    public boolean a(float f2, float f3, float f4, float f5) {
        float f6 = com.corrodinggames.rts.gameFramework.f.d(this.cy + this.cI, this.cz + this.cJ, f3, f4);
        float f7 = com.corrodinggames.rts.gameFramework.f.a(this.cy + this.cI, this.cz + this.cJ, f3, f4);
        float f8 = 15.0f;
        float f9 = f5 * f2;
        if (f8 < f9 + 1.0f) {
            f8 = f9 + 1.0f;
        }
        if (f7 < f8 * f8 || this.ct) {
            return true;
        }
        this.cC += com.corrodinggames.rts.gameFramework.f.k(f6) * f9;
        this.cD += com.corrodinggames.rts.gameFramework.f.j(f6) * f9;
        if (com.corrodinggames.rts.gameFramework.f.c(this.cC) >= 1.0f || com.corrodinggames.rts.gameFramework.f.c(this.cD) >= 1.0f) {
            this.cy += this.cC;
            this.cz += this.cD;
            this.cC = 0.0f;
            this.cD = 0.0f;
            this.a(this.cy, this.cz);
        }
        return false;
    }

    @Override
    public int z() {
        return this.d;
    }

    static {
        f = null;
    }
}
