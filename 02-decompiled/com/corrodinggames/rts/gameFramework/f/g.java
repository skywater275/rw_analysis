/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.q;
import com.corrodinggames.rts.game.units.a.r;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ag;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.aq;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.ac;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.a;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.aj;
import com.corrodinggames.rts.gameFramework.f.al;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.f.ap;
import com.corrodinggames.rts.gameFramework.f.c;
import com.corrodinggames.rts.gameFramework.f.d;
import com.corrodinggames.rts.gameFramework.f.h;
import com.corrodinggames.rts.gameFramework.f.i;
import com.corrodinggames.rts.gameFramework.f.j;
import com.corrodinggames.rts.gameFramework.f.k;
import com.corrodinggames.rts.gameFramework.f.m;
import com.corrodinggames.rts.gameFramework.i.b;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.u;
import com.corrodinggames.rts.gameFramework.w;
import java.util.ArrayList;

public final class g
extends bq {
    public static boolean a = false;
    public boolean b = true;
    public boolean c = false;
    public float d = 0.0f;
    public boolean e = false;
    com.corrodinggames.rts.game.units.h f;
    public a g;
    public m h;
    public ap i;
    public k j;
    public com.corrodinggames.rts.gameFramework.f.f k;
    com.corrodinggames.rts.game.units.a.e l = new com.corrodinggames.rts.game.units.a.e();
    com.corrodinggames.rts.game.units.a.f m = new com.corrodinggames.rts.game.units.a.f();
    com.corrodinggames.rts.game.units.a.i n = new com.corrodinggames.rts.game.units.a.i();
    com.corrodinggames.rts.game.units.a.d o = new com.corrodinggames.rts.game.units.a.d();
    public com.corrodinggames.rts.game.units.a.j p = new com.corrodinggames.rts.game.units.a.j();
    r q = new r();
    q r = new q();
    com.corrodinggames.rts.gameFramework.f.a.l s = new com.corrodinggames.rts.gameFramework.f.a.a();
    boolean t = false;
    public boolean u = false;
    double v;
    float w = 0.0f;
    public float x = 0.0f;
    public float y = 0.0f;
    float z = 40.0f;
    float A = 40.0f;
    int B = 0;
    boolean C;
    boolean D;
    float E;
    public float F;
    public float G;
    boolean H = false;
    boolean I = false;
    boolean J = false;
    boolean K = false;
    boolean L = false;
    boolean M = false;
    float N = 0.0f;
    float O = 0.0f;
    float P = 0.0f;
    float Q = 0.0f;
    float R = 0.0f;
    float S = 0.0f;
    boolean T = false;
    boolean U = false;
    boolean V = false;
    public am W;
    public float X;
    public int Y;
    public float Z;
    public am aa;
    public final boolean ab = true;
    public s ac;
    public int ad;
    public boolean ae;
    public float af;
    public float ag;
    public float ah;
    public boolean ai;
    public float aj;
    public float ak;
    public float al;
    public float am;
    public float an;
    public float ao;
    public boolean ap;
    public float aq;
    public float ar;
    public int as;
    public final Paint at = new Paint();
    public Paint au;
    public Paint av;
    public Paint aw;
    public Paint ax;
    public Paint ay;
    public Paint az;
    public Paint aA;
    public Paint aB;
    public Paint aC;
    public Paint aD;
    public Paint aE;
    public Paint aF;
    public Paint aG;
    public Paint aH;
    public Paint aI;
    public Paint aJ;
    Paint aK;
    Paint aL;
    Paint aM;
    Paint aN;
    Paint aO;
    Paint aP;
    com.corrodinggames.rts.gameFramework.m.ag aQ;
    com.corrodinggames.rts.gameFramework.m.ag aR;
    com.corrodinggames.rts.gameFramework.m.ag aS;
    public float aT;
    public float aU = 0.0f;
    public float aV = 0.0f;
    public float aW = 0.0f;
    int aX;
    public float aY = 0.0f;
    public boolean aZ;
    com.corrodinggames.rts.gameFramework.m.e ba = null;
    com.corrodinggames.rts.gameFramework.m.e bb = null;
    com.corrodinggames.rts.gameFramework.m.e bc = null;
    boolean bd;
    float be;
    Paint bf;
    Paint bg;
    com.corrodinggames.rts.gameFramework.m.e bh = null;
    com.corrodinggames.rts.gameFramework.m.e bi = null;
    public com.corrodinggames.rts.gameFramework.m.e bj = null;
    public com.corrodinggames.rts.gameFramework.m.e bk = null;
    public com.corrodinggames.rts.gameFramework.m.e bl = null;
    com.corrodinggames.rts.gameFramework.m.e bm = null;
    public com.corrodinggames.rts.gameFramework.m.e bn;
    public com.corrodinggames.rts.gameFramework.m.e bo;
    com.corrodinggames.rts.gameFramework.f.a.e bp;
    com.corrodinggames.rts.gameFramework.f.a.e bq;
    com.corrodinggames.rts.gameFramework.f.a.e br;
    com.corrodinggames.rts.gameFramework.f.a.e bs;
    com.corrodinggames.rts.gameFramework.f.a.e bt;
    com.corrodinggames.rts.gameFramework.f.a.e bu;
    final Rect bv = new Rect();
    final Rect bw = new Rect();
    final Rect bx = new Rect();
    final Rect by = new Rect();
    final Rect bz = new Rect();
    final Paint bA = new Paint();
    final Paint bB = new Paint();
    final Paint bC = new com.corrodinggames.rts.gameFramework.m.ag();
    public final Paint bD = new com.corrodinggames.rts.gameFramework.m.ag();
    final Paint bE = new com.corrodinggames.rts.gameFramework.m.ag();
    final Paint bF = new Paint();
    String bG;
    String bH;
    bb bI;
    String bJ;
    String bK;
    String bL;
    public ArrayList bM = new ArrayList();
    private int cf;
    private int cg;
    private int ch;
    private float ci;
    private int cj;
    private int ck;
    private int cl;
    public boolean bN = false;
    public static boolean bO = false;
    public static boolean bP = false;
    public static boolean bQ = false;
    public static boolean bR;
    com.corrodinggames.rts.gameFramework.f.a.c bS = com.corrodinggames.rts.gameFramework.f.a.c.b(-1, -1);
    com.corrodinggames.rts.game.units.custom.e.f bT = new com.corrodinggames.rts.game.units.custom.e.f();
    long bU = -1L;
    long bV = -1L;
    long bW;
    boolean bX;
    public u bY = new u();
    public u bZ = new u();
    public static am ca;
    Paint cb = new Paint();
    Rect cc = new Rect();
    static int cd;
    static boolean ce;

    public boolean a() {
        if (com.corrodinggames.rts.gameFramework.l.aw()) {
            return false;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.bQ.useCircleSelect;
    }

    float b() {
        return Math.min(this.w * 2.5f, 290.0f) + 10.0f;
    }

    float c() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f2 = 0.7f;
        if (com.corrodinggames.rts.gameFramework.l.av()) {
            f2 = 0.9f;
        }
        if (l2.cX < 1.0f) {
            float f3 = l2.cX;
            if ((double)f3 < 0.4) {
                f3 = 0.4f;
            }
            f2 *= f3;
        }
        return f2;
    }

    public void a(String string, int n2) {
        this.g.a(string, n2);
    }

    public void b(String string, int n2) {
        this.g.b(string, n2);
    }

    public void a(String string) {
        this.g.a(string);
    }

    public void b(String string) {
        this.g.a(string, 100);
    }

    public void c(String string) {
        this.g.a(string, 50);
    }

    public void d(String string) {
        this.g.a(string, 5);
    }

    public void d() {
        this.U = false;
        this.V = false;
        this.I = false;
    }

    public boolean a(float f2, float f3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (bO && !this.g.ap) {
            return l2.bW.c(f2, f3) == null;
        }
        return f2 < l2.cl - l2.cq;
    }

    public void e() {
        if (this.g != null) {
            this.g.a();
        }
    }

    public void a(boolean bl) {
        if (bl) {
            this.g.j();
            return;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.g.k();
        this.l();
        this.u = false;
        this.c = false;
        this.d = 0.0f;
        this.bM.clear();
        if (!bl) {
            l2.bt = 1.0f;
            l2.bw = false;
            l2.bv = false;
            l2.bl = false;
            l2.bn = false;
        }
        if (l2.N() && l2.P()) {
            l2.bv = l2.bX.p;
        }
        com.corrodinggames.rts.gameFramework.f.an.a();
        com.corrodinggames.rts.gameFramework.f.g.K();
    }

    public void f() {
        bO = false;
        bP = false;
        bQ = false;
        if (com.corrodinggames.rts.gameFramework.l.av()) {
            bO = true;
            bP = true;
            a = true;
            bQ = true;
        }
        if (com.corrodinggames.rts.gameFramework.l.aY) {
            bO = true;
            bP = true;
            bQ = true;
        }
        if (com.corrodinggames.rts.gameFramework.l.at() && !com.corrodinggames.rts.gameFramework.l.B().bQ.classicInterface) {
            bO = true;
            bP = true;
            bQ = true;
        }
    }

    public void a(Context context) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (com.corrodinggames.rts.gameFramework.l.C()) {
            this.bN = true;
        }
        this.f();
        this.bG = com.corrodinggames.rts.gameFramework.h.a.a("gui.notAvailableInDemoText", new Object[0]);
        this.bH = "Locked";
        this.bI = com.corrodinggames.rts.game.units.custom.bb.b("gui.notEnoughResources");
        this.bJ = com.corrodinggames.rts.gameFramework.h.a.a("gui.cannotPlace.general", new Object[0]);
        this.bK = com.corrodinggames.rts.gameFramework.h.a.a("gui.cannotPlace.needsResourcePool", new Object[0]);
        this.bL = com.corrodinggames.rts.gameFramework.h.a.a("gui.cannotPlace.needsWater", new Object[0]);
        this.g = new a(l2, this);
        this.e();
        this.h = new m(l2, this);
        this.i = new ap(l2);
        this.j = new k(l2, this);
        this.k = new com.corrodinggames.rts.gameFramework.f.f();
        if (com.corrodinggames.rts.gameFramework.l.au()) {
            this.b = true;
        }
        this.ba = l2.bO.a(R$drawable.button_no);
        this.bb = l2.bO.a(R$drawable.button_yes);
        this.bc = l2.bO.a(R$drawable.button_more);
        this.bf = new Paint();
        this.bf.d(true);
        this.bg = new Paint();
        this.bg.d(true);
        this.bg.a(40, 255, 255, 255);
        this.bh = l2.bO.a(R$drawable.button_add);
        this.bi = l2.bO.a(R$drawable.button_subtract);
        this.bj = l2.bO.a(R$drawable.icon_rally);
        this.bn = l2.bO.a(R$drawable.rounded_glow_button);
        this.bo = l2.bO.a(R$drawable.rounded_white_button);
        this.bp = new com.corrodinggames.rts.gameFramework.f.a.e(this.bn, 32, 27);
        this.bq = new com.corrodinggames.rts.gameFramework.f.a.e(l2.bO.a(R$drawable.rounded_glow_highlight_button), 32, 27);
        this.br = this.bp.a();
        this.br.v = this.bq;
        this.bs = new com.corrodinggames.rts.gameFramework.f.a.e(l2.bO.a(R$drawable.rounded_dark_box), 32, 27);
        this.bt = new com.corrodinggames.rts.gameFramework.f.a.e(l2.bO.a(R$drawable.rounded_dark_box_titled), 36, 36);
        this.bt.r = new com.corrodinggames.rts.gameFramework.f.a.e(l2.bO.a(R$drawable.rounded_shadow), 36, 36);
        this.bt.f = true;
        this.bu = new com.corrodinggames.rts.gameFramework.f.a.e(l2.bO.a(R$drawable.rounded_green), 36, 36);
        this.bu.r = this.bt.r;
        this.bu.u = 20;
        this.bk = l2.bO.a(R$drawable.icon_upgrade);
        this.bl = l2.bO.a(R$drawable.metal_dark, false);
        this.bm = l2.bO.a(R$drawable.touch_indicator, false);
        com.corrodinggames.rts.gameFramework.f.a.h.b();
        this.bE.a(145, 0, 175, 0);
        this.bE.a(6.0f);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.bE);
        this.bD.a(true);
        this.au = new Paint();
        this.av = new com.corrodinggames.rts.gameFramework.m.ag();
        this.av.a(255, 0, 240, 0);
        this.av.a(true);
        this.av.c(true);
        this.av.a(Typeface.a(Typeface.c, 1));
        l2.a(this.av, 20.0f);
        this.av.a(Paint$Align.a);
        this.ay = new com.corrodinggames.rts.gameFramework.m.ag();
        this.ay.a(255, 0, 240, 0);
        this.ay.a(true);
        this.ay.c(true);
        this.ay.a(Typeface.a(Typeface.c, 1));
        l2.a(this.ay, 18.0f);
        this.ay.a(Paint$Align.a);
        this.aw = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aw.a(this.av);
        this.aw.a(255, 240, 240, 0);
        this.ax = new com.corrodinggames.rts.gameFramework.m.ag();
        this.ax.b(Color.a(100, 0, 0, 0));
        this.ax.a(Paint$Style.c);
        this.az = new com.corrodinggames.rts.gameFramework.m.ag();
        this.az.a(100, 30, 240, 30);
        this.az.a(Paint$Align.a);
        this.az.c(true);
        this.az.a(true);
        l2.a(this.az, 12.0f);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.az);
        this.aC = new com.corrodinggames.rts.gameFramework.m.ag();
        if (this.bN) {
            this.aC.a(255, 240, 240, 240);
        } else {
            this.aC.a(255, 30, 240, 30);
        }
        this.aC.a(Paint$Align.b);
        this.aC.c(true);
        this.aC.a(true);
        l2.a(this.aC, 12.0f);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aC);
        com.corrodinggames.rts.gameFramework.l.e("smallTextPaint size: " + this.aC.k());
        this.aB = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aB.a(this.aC);
        l2.a(this.aB, 10.0f);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aB);
        this.aA = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aA.a(this.aC);
        l2.a(this.aA, 8.0f);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aA);
        this.aD = new com.corrodinggames.rts.gameFramework.m.ag();
        if (this.bN) {
            this.aD.a(255, 240, 240, 240);
        } else {
            this.aD.a(255, 30, 240, 30);
        }
        this.aD.a(Paint$Align.b);
        this.aD.c(true);
        this.aD.a(true);
        l2.a(this.aD, 20.0f);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aD);
        this.aE = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aE.a(255, 30, 240, 30);
        this.aE.a(Paint$Align.b);
        this.aE.c(true);
        this.aE.a(true);
        l2.a(this.aE, 20.0f);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aE);
        this.aI = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aI.a(150, 20, 20, 20);
        l2.a(this.aI);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aI);
        this.aF = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aF.a(this.aD);
        this.aF.a(255, 128, 0, 0);
        l2.a(this.aF, 14.0f);
        this.aF.a(Paint$Align.b);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aF);
        this.aG = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aG.a(this.aF);
        this.aG.a(255, 220, 222, 49);
        this.aH = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aH.a(this.aD);
        l2.a(this.aH, 12.0f);
        this.aH.a(125, 230, 230, 230);
        this.aH.a(Paint$Align.b);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aH);
        this.aQ = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aQ.b(-16777216);
        this.aQ.a(true);
        this.aQ.c(true);
        this.aQ.a(Typeface.a(Typeface.c, 0));
        l2.a(this.aQ, 14.0f);
        this.aR = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aR.a(this.aQ);
        this.aR.a(Typeface.a(Typeface.c, 1));
        l2.a(this.aR, 16.0f);
        this.aS = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aS.a(this.aR);
        this.aS.b(Color.a(232, 63, 80));
        l2.a(this.aS, 16.0f);
        this.aK = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aK.b(-16777216);
        this.aK.a(Paint$Align.b);
        this.aK.a(true);
        this.aK.c(true);
        this.aK.a(Typeface.a(Typeface.c, 0));
        l2.a(this.aK, 20.0f);
        this.aL = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aL.b(-1);
        this.aL.c(160);
        if (com.corrodinggames.rts.gameFramework.l.av()) {
            this.aL.c(140);
        }
        l2.a(this.aL);
        this.aM = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aM.b(-16777216);
        this.aM.c(210);
        l2.a(this.aM);
        this.aP = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aP.b(-7829368);
        this.aP.c(240);
        this.aP.a(Paint$Style.b);
        this.aP.a(1.0f);
        l2.a(this.aP);
        this.aN = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aN.b(-16711936);
        this.aN.c(80);
        this.aN.a(Paint$Style.a);
        this.aN.a(4.0f);
        l2.a(this.aN);
        this.aO = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aO.b(Color.a(120, 235, 167, 49));
        this.aO.a(Paint$Style.a);
        this.aO.a(8.0f);
        l2.a(this.aO);
        this.aJ = new com.corrodinggames.rts.gameFramework.m.ag();
        this.aJ.c(true);
        this.aJ.a(true);
        l2.a(this.aJ, 12.0f);
        com.corrodinggames.rts.gameFramework.m.ag.b(this.aJ);
    }

    public void g() {
        this.h.b();
        this.i.b();
        this.bX = false;
    }

    public void a(float f2) {
        float f3;
        float f4;
        float f5;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.v += (double)f2;
        this.aU = com.corrodinggames.rts.gameFramework.f.a(this.aU, f2);
        this.aV = com.corrodinggames.rts.gameFramework.f.a(this.aV, f2);
        this.aY = com.corrodinggames.rts.gameFramework.f.a(this.aY, 0.08f * f2);
        this.aW = com.corrodinggames.rts.gameFramework.f.a(this.aW, f2);
        this.E += f2;
        this.aT += 0.05f * f2;
        if (this.aT > 1.0f) {
            this.aT -= 1.0f;
            if (this.aT > 1.0f) {
                this.aT = 0.0f;
            }
        }
        l2.dx = 4.0f * this.aY;
        float f6 = 1.0f * f2;
        if (!this.I) {
            f5 = this.R * f2;
            f4 = this.S * f2;
            f5 = com.corrodinggames.rts.gameFramework.f.g(80.0f, f5);
            f4 = com.corrodinggames.rts.gameFramework.f.g(80.0f, f4);
            l2.cy += f5;
            l2.cz += f4;
        } else {
            f6 *= 4.0f;
        }
        f5 = com.corrodinggames.rts.gameFramework.f.b(0.0f, 0.0f, this.R, this.S);
        f4 = com.corrodinggames.rts.gameFramework.f.d(0.0f, 0.0f, this.R, this.S);
        if (f5 > 30.0f) {
            f5 = 30.0f;
        }
        f5 = com.corrodinggames.rts.gameFramework.f.a(f5, f6);
        this.R = com.corrodinggames.rts.gameFramework.f.k(f4) * f5;
        this.S = com.corrodinggames.rts.gameFramework.f.j(f4) * f5;
        this.aZ = false;
        boolean bl = this.I = l2.ac() && l2.dM[0] && this.aU == 0.0f;
        if (this.aV != 0.0f) {
            if (!this.I) {
                this.aV = 0.0f;
            }
            this.I = false;
            this.H = false;
        }
        boolean bl2 = false;
        if (this.aW > 0.0f) {
            bl2 = true;
        }
        if (l2.ac() && l2.ae() > 1) {
            bl2 = true;
            this.aW = 4.0f;
        }
        if (bl2) {
            this.I = false;
            this.H = false;
            this.T = false;
            this.w = 0.0f;
        }
        this.M = false;
        this.L = l2.af() > l2.cF;
        this.U = !this.I && this.H;
        boolean bl3 = this.V = this.I && !this.H;
        if (com.corrodinggames.rts.gameFramework.l.av() && l2.bQ.mouseSupport) {
            this.z = l2.af();
            this.A = l2.ag();
        }
        if (!this.I && !this.U) {
            this.D = false;
        }
        if (this.I) {
            this.w += f2;
            this.x = l2.b(0);
            this.y = l2.c(0);
            this.z = this.x;
            this.A = this.y;
            this.B = l2.d(0);
            this.C = this.a(this.x, this.y);
            boolean bl4 = false;
            if (this.C && !this.H) {
                if (this.E < 30.0f) {
                    f3 = com.corrodinggames.rts.gameFramework.f.a(this.F, this.G, this.x, this.y);
                    float f7 = 10.0f * l2.cj;
                    if (com.corrodinggames.rts.gameFramework.l.au()) {
                        f7 = (float)((double)f7 * 1.5);
                    }
                    if (f3 < f7 * f7) {
                        bl4 = true;
                    }
                }
                this.E = 0.0f;
                this.F = this.x;
                this.G = this.y;
            }
            if (bl4) {
                this.D = true;
            }
            if (!this.H) {
                this.T = false;
                this.N = this.x;
                this.O = this.y;
                this.P = this.x;
                this.Q = this.y;
                this.J = l2.bW.c(this.x, this.y) != null;
                this.K = false;
                if (!this.J) {
                    this.K = this.x > l2.cF;
                }
            }
            this.H = true;
        }
        if (this.I && (this.w <= 20.0f || !this.a())) {
            float f8 = com.corrodinggames.rts.gameFramework.f.a(this.N, this.O, this.x, this.y);
            if (!this.J) {
                f3 = 30.0f * l2.cj;
                if (com.corrodinggames.rts.gameFramework.l.av() && l2.bQ.mouseSupport && l2.e(3)) {
                    f3 = 0.0f;
                }
                if (!this.T && f8 > f3 * f3) {
                    boolean bl5 = false;
                    int n2 = 1;
                    if (l2.bQ.mouseOrders == 2) {
                        n2 = 2;
                    }
                    if (!(l2.bQ.mouseSupport && this.B == n2 || this.c(l2))) {
                        bl5 = true;
                    }
                    if (bl5) {
                        this.T = true;
                    }
                    this.P = this.x;
                    this.Q = this.y;
                }
            }
        }
        if (com.corrodinggames.rts.gameFramework.l.av() && !l2.aq && l2.ao != null && (l2.ao.f() || com.corrodinggames.rts.gameFramework.l.aR) && (!this.I || this.g.c)) {
            float f9 = 24.0f * l2.bQ.edgeScrollSpeed / l2.cX;
            f3 = l2.cy;
            float f10 = l2.cz;
            float f11 = 0.0f;
            float f12 = 0.0f;
            if (this.z <= 1.0f) {
                f11 -= f9 * f2;
            }
            if (this.z >= l2.cl - 1.0f) {
                f11 += f9 * f2;
            }
            if (this.A <= 1.0f) {
                f12 -= f9 * f2;
            }
            if (this.A >= l2.cm - 1.0f) {
                f12 += f9 * f2;
            }
            l2.cy += f11;
            l2.cz += f12;
            l2.Q();
            this.g.y.a -= (l2.cy - f3) * l2.cX;
            this.g.y.b -= (l2.cz - f10) * l2.cX;
        }
        ac ac2 = l2.bT;
        if (l2.bQ.keyboardSupport) {
            if (l2.E()) {
                f3 = 12.0f * l2.bQ.scrollSpeed;
                if (ac2.p.b()) {
                    l2.cy -= f3 * f2;
                }
                if (ac2.q.b()) {
                    l2.cy += f3 * f2;
                }
                if (ac2.n.b()) {
                    l2.cz -= f3 * f2;
                }
                if (ac2.o.b()) {
                    l2.cz += f3 * f2;
                }
                if (ac2.r.b()) {
                    l2.cV += 0.1f;
                }
                if (ac2.s.b()) {
                    l2.cV -= 0.1f;
                }
            }
            if (ac2.y.a()) {
                this.l();
                this.y();
            }
            if (ac2.z.a()) {
                this.i.d();
            }
            if (ac2.A.a()) {
                this.l();
                this.y();
                for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
                    if (!(w2 instanceof y)) continue;
                    y y2 = (y)w2;
                    if (y2.bV || y2.bX != l2.bs || !y2.l() || y2.ak() || !y2.aS() || y2.cN != null) continue;
                    this.j(y2);
                }
            }
            if (ac2.B.a()) {
                this.l();
                this.y();
                for (w w3 : com.corrodinggames.rts.gameFramework.w.er) {
                    if (!(w3 instanceof y)) continue;
                    y y3 = (y)w3;
                    if (y3.bX != l2.bs || y3.r() != com.corrodinggames.rts.game.units.ar.e || y3.bV || y3.cN != null) continue;
                    this.j(y3);
                    l2.b(y3.eo, y3.ep);
                }
            }
            if (ac2.C.a()) {
                com.corrodinggames.rts.gameFramework.f.al.a(this.bM, com.corrodinggames.rts.gameFramework.f.al.a, com.corrodinggames.rts.gameFramework.f.al.b);
            }
            if (ac2.D.a()) {
                com.corrodinggames.rts.gameFramework.f.al.a(this.bM, com.corrodinggames.rts.gameFramework.f.al.c, null);
            }
            if (ac2.E.a()) {
                com.corrodinggames.rts.gameFramework.f.al.a(this.bM, com.corrodinggames.rts.gameFramework.f.al.d, null);
            }
            if (ac2.F.a()) {
                com.corrodinggames.rts.gameFramework.f.al.a(this.bM, com.corrodinggames.rts.gameFramework.f.al.e, null);
            }
            if (ac2.G.a()) {
                com.corrodinggames.rts.gameFramework.f.al.a(this.bM, com.corrodinggames.rts.gameFramework.f.al.f, null);
            }
            if (ac2.x.a()) {
                this.g.a(12);
            }
            if (ac2.N.a() && this.B() && this.C()) {
                this.l();
                this.aa = null;
                this.ac = this.l;
                return;
            }
            if (ac2.P.a() && this.A()) {
                this.l();
                this.aa = null;
                this.ac = this.m;
                return;
            }
            if (ac2.Q.a() && this.C()) {
                this.l();
                this.aa = null;
                this.ac = this.n;
                return;
            }
            if (ac2.O.a()) {
                this.v();
            }
            if (ac2.v.a()) {
                this.I();
            }
            if (ac2.t.a() && l2.N()) {
                com.corrodinggames.rts.gameFramework.l.e("showing send chat");
                this.g.a(13);
            }
            if (ac2.u.a() && l2.N()) {
                com.corrodinggames.rts.gameFramework.l.e("showing send team chat");
                this.g.a(16);
            }
            if (l2.P() || l2.cb.j()) {
                if (ac2.L.a()) {
                    if (l2.bt != 0.0f) {
                        if (!l2.cb.j()) {
                            com.corrodinggames.rts.gameFramework.j.ad.a(null, "Game paused");
                        }
                        l2.bt = 0.0f;
                    } else {
                        l2.bt = 1.0f;
                    }
                }
                boolean bl6 = ac2.J.a();
                boolean bl7 = ac2.K.a();
                if (bl6 || bl7) {
                    if (bl6) {
                        boolean bl8;
                        boolean bl9 = bl8 = l2.bt > 1.0f;
                        l2.bt = l2.bt < 2.0f ? (float)((double)l2.bt - 0.25) : (l2.bt < 6.0f ? (float)((double)l2.bt - 0.5) : (l2.bt < 16.0f ? (l2.bt -= 2.0f) : (l2.bt -= 4.0f)));
                        if (l2.bt < 0.0f) {
                            l2.bt = 0.0f;
                        }
                        if (bl8 && l2.bt < 1.0f) {
                            l2.bt = 1.0f;
                        }
                    } else if (bl7) {
                        boolean bl10;
                        boolean bl11 = bl10 = l2.bt < 1.0f;
                        l2.bt = l2.bt < 2.0f ? (float)((double)l2.bt + 0.25) : (l2.bt < 6.0f ? (float)((double)l2.bt + 0.5) : (l2.bt < 16.0f ? (l2.bt += 2.0f) : (l2.bt += 4.0f)));
                        if (l2.cb.j()) {
                            if (l2.bt > 64.0f) {
                                l2.bt = 64.0f;
                            }
                        } else if (l2.bt > 5.0f) {
                            l2.bt = 5.0f;
                        }
                        if (bl10 && l2.bt > 1.0f) {
                            l2.bt = 1.0f;
                        }
                    }
                    if (!l2.cb.j()) {
                        com.corrodinggames.rts.gameFramework.j.ad.a(null, "Game speed now: " + l2.bt);
                    }
                }
            } else if (ac2.L.a() && l2.bX.C && l2.bX.aW) {
                l2.bX.e(!l2.bX.al);
            }
            l2.cT = com.corrodinggames.rts.gameFramework.f.a(l2.cT, f2);
            if (ac2.Y.a()) {
                l2.cT = 180.0f;
            }
            if (l2.bv && ac2.ab.a()) {
                l2.bl = !l2.bl;
                com.corrodinggames.rts.gameFramework.l.e("debugTempMode now: " + l2.bl);
                this.b("debug: " + l2.bl);
            }
            if (l2.bv && l2.bl && ac2.ac.a()) {
                com.corrodinggames.rts.game.a.a.as = !com.corrodinggames.rts.game.a.a.as;
                this.b("AI debug view: " + com.corrodinggames.rts.game.a.a.as);
            }
            if (l2.bv && l2.bl && ac2.ad.a()) {
                com.corrodinggames.rts.gameFramework.n.f.a = !com.corrodinggames.rts.gameFramework.n.f.a;
                this.b("Map debug: " + com.corrodinggames.rts.gameFramework.n.f.a);
            }
            if (l2.P() || l2.cb.j()) {
                if (l2.bv) {
                    if (ac2.V.a()) {
                        boolean bl12 = l2.bp = !l2.bp;
                    }
                    if (ac2.W.a()) {
                        l2.bt = l2.bt == 1.0f ? 0.1f : 1.0f;
                    }
                    if (ac2.X.a()) {
                        com.corrodinggames.rts.gameFramework.l.e("Adding test popup");
                        l2.bX.U();
                    }
                    if (ac2.Z.a()) {
                        boolean bl13 = l2.bw = !l2.bw;
                    }
                    if (ac2.aa.a()) {
                        for (w w4 : com.corrodinggames.rts.gameFramework.w.er) {
                            if (!(w4 instanceof y)) continue;
                            y y4 = (y)w4;
                            if (!y4.cG) continue;
                            y4.U();
                        }
                    }
                }
                if (ac2.U.a()) {
                    boolean bl14 = l2.bv = !l2.bv;
                    if (l2.bv) {
                        this.y();
                    }
                }
            }
        }
        if (l2.bv && !l2.P() && !l2.cb.j()) {
            l2.bv = false;
        }
        if (l2.bv) {
            if (this.f != null && (this.f.ej || this.f.bV)) {
                this.f = null;
            }
            if (this.f == null) {
                com.corrodinggames.rts.gameFramework.l.e("Creating new debug editor");
                this.f = new com.corrodinggames.rts.game.units.h(false);
                this.f.b(l2.bs);
            }
            if (this.q() == 0) {
                this.y();
                this.j(this.f);
            }
            if (l2.bQ.liveReloading && l2.bx % 100 == 0 && !l2.cb.i()) {
                com.corrodinggames.rts.game.units.custom.ag.c();
            }
        } else {
            if (this.f != null && (this.f.ej || this.f.bV)) {
                this.f = null;
            }
            if (this.f != null && !l2.cb.j()) {
                this.h();
            }
        }
        if (this.T) {
            if (this.K) {
                this.g.ao = this.Q - this.y;
            } else {
                int n3 = 1;
                if (l2.bQ.mouseOrders == 2) {
                    n3 = 2;
                }
                if (!(l2.bQ.mouseSupport && this.B == n3 || this.c(l2))) {
                    SettingsEngine settingsEngine = l2.bQ;
                    double d2 = this.P - this.x;
                    double d3 = this.Q - this.y;
                    float f13 = com.corrodinggames.rts.gameFramework.f.b(0.0f, 0.0f, (float)d2, (float)d3);
                    d2 = d2 * (double)settingsEngine.scrollSpeed / (double)l2.cX;
                    d3 = d3 * (double)settingsEngine.scrollSpeed / (double)l2.cX;
                    if (f2 != 0.0f && (double)f13 > 50.0 * (double)f2) {
                        float f14 = 0.7f;
                        if (com.corrodinggames.rts.gameFramework.l.av()) {
                            f14 = 1.7f;
                        }
                        this.R = (float)(d2 * (double)f14);
                        this.S = (float)(d3 * (double)f14);
                    }
                    l2.cy = (float)((double)l2.cy + d2 * 2.0);
                    l2.cz = (float)((double)l2.cz + d3 * 2.0);
                }
            }
            this.P = this.x;
            this.Q = this.y;
        }
        if (l2.bQ.mouseSupport && (this.bS.a != (int)l2.af() || this.bS.b != (int)l2.ag())) {
            this.bS.a = (int)l2.af();
            this.bS.b = (int)l2.ag();
            this.s.b(this.bS);
        }
        if (this.U && this.J()) {
            com.corrodinggames.rts.gameFramework.f.a.c c2 = com.corrodinggames.rts.gameFramework.f.a.c.a((int)this.z, (int)this.A);
            this.s.b(c2);
        }
        this.s.b(f2);
        this.k.a(f2);
    }

    public void h() {
        if (this.f != null) {
            this.l(this.f);
            this.f.ci();
            this.f = null;
        }
    }

    public com.corrodinggames.rts.game.units.h i() {
        return this.f;
    }

    public void a(com.corrodinggames.rts.game.units.h h2) {
        this.f = h2;
    }

    public boolean a(l l2) {
        if (!l2.bQ.keyboardSupport) {
            return false;
        }
        return l2.c(59, 60);
    }

    public boolean b(l l2) {
        if (!l2.bQ.keyboardSupport) {
            return false;
        }
        return l2.c(113, 114);
    }

    public boolean c(l l2) {
        if (!l2.bQ.keyboardSupport) {
            return false;
        }
        return l2.c(57, 58);
    }

    public void b(float f2) {
        Object object;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.Z += 0.2f * f2;
        if (this.Z > 360.0f) {
            this.Z -= 360.0f;
        }
        this.bx.a((int)(l2.cl - l2.cq), 0, (int)l2.cl, (int)l2.cm);
        if (!bO) {
            if (this.bN) {
                this.bA.a();
                this.bA.b(Color.a(255, 33, 40, 52));
                this.bA.a(Paint$Style.a);
                l2.bO.b(this.bx, this.bA);
            } else {
                l2.bO.a(this.bl, this.bx, null);
            }
            this.bA.a();
            this.bA.b(Color.a(255, 0, 0, 0));
            this.bA.a(Paint$Style.b);
            l2.bO.b(this.bx, this.bA);
        }
        this.cf = 0;
        this.ch = 0;
        this.cg = 0;
        this.ck = this.cl;
        this.cl = 0;
        if (l2.cb.j() || l2.bs != null && l2.bs.b()) {
            object = this.s();
            if (object != null) {
                this.a(l2, ((y)object).bX, false, true);
            }
        } else {
            object = this.t();
            if (l2.bs != null && l2.bs != com.corrodinggames.rts.game.n.i && !l2.bs.b() && !l2.cb.j()) {
                this.a(l2, l2.bs, false, true);
            }
            if (object != null && l2.bs != ((am)object).bX && this.m((am)object)) {
                this.a(l2, ((am)object).bX, true, true);
            }
        }
        if (l2.bv && !l2.cb.j()) {
            object = "";
            if (l2.bv) {
                object = (String)object + "Editor Active\n";
            }
            if (l2.bt != 1.0f) {
                object = (String)object + "Game Speed: " + l2.bt + "x\n";
            }
            if (l2.bw) {
                object = (String)object + "Invincible Units\n";
            }
            boolean bl = false;
            for (n n2 : com.corrodinggames.rts.game.n.c()) {
                if (!(n2 instanceof com.corrodinggames.rts.game.a.a)) continue;
                com.corrodinggames.rts.game.a.a a2 = (com.corrodinggames.rts.game.a.a)n2;
                bl = a2.bG > 0.0f;
            }
            if (bl) {
                object = (String)object + "AIs frozen\n";
            }
            this.bA.a();
            this.bA.b(Color.a(0, 0, 0, 0));
            this.bA.a(Paint$Style.a);
            float f3 = 70.0f * l2.cj;
            float f4 = 40.0f;
            if (l2.cl < 600.0f && l2.cm > 650.0f) {
                f3 = 10.0f;
                f4 = 60.0f * l2.cj;
            }
            l2.bO.a((String)object, f3, f4, this.ay, this.bA, 6.0f);
        }
        this.j();
        this.s.f();
    }

    public void j() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
    }

    public void a(l l2, n n2, boolean bl, boolean bl2) {
        if (n2.n) {
            this.a(l2, n2, bl, com.corrodinggames.rts.game.units.custom.e.a.c.D, n2.aa(), null, 0, null);
        }
        if (bl2) {
            this.bT.g(n2.ab());
            ArrayList arrayList = com.corrodinggames.rts.game.units.custom.e.a.f();
            for (com.corrodinggames.rts.game.units.custom.e.a a2 : arrayList) {
                if (!a2.d() || !a2.p && !a2.j) continue;
                this.bT.c(a2);
            }
            this.bT.e();
            this.a(l2, n2, bl, this.bT);
        }
    }

    public void a(l l2, n n2, boolean bl, com.corrodinggames.rts.game.units.custom.e.f f2) {
        for (com.corrodinggames.rts.game.units.custom.e.e e2 : f2.b) {
            if (e2.a.a()) continue;
            com.corrodinggames.rts.game.units.custom.e.a a2 = e2.a;
            double d2 = e2.b;
            this.a(l2, n2, bl, a2, d2, f2, 0, null);
        }
    }

    public boolean a(l l2, n n2, boolean bl, com.corrodinggames.rts.game.units.custom.e.a a2, double d2, com.corrodinggames.rts.game.units.custom.e.f f2, int n3, com.corrodinggames.rts.game.units.custom.e.a a3) {
        float f3;
        float f4;
        double d3;
        int n4;
        com.corrodinggames.rts.game.units.custom.e.a a4;
        if (n3 == 0) {
            this.cj = 0;
        }
        boolean bl2 = false;
        if (n3 < 6 && f2 != null && (a4 = a2.i) != null && (a2.j || d2 != 0.0) && (n4 = (int)(this.a(l2, n2, bl, a4, d3 = f2.a(a4), f2, n3 + 1, a2) ? 1 : 0)) != 0) {
            bl2 = true;
        }
        if (d2 == 0.0 && !a2.p || n3 == 0 && !a2.l) {
            return bl2;
        }
        int n5 = 6;
        String string = a2.a(d2, true);
        int n6 = n2.b(a2);
        n4 = n2.a(a2);
        if (n4 != 0) {
            string = string + "(+" + n6 + ")(-" + n4 + ")";
        } else if (n6 != 0) {
            string = n6 >= 0 ? string + "(+" + n6 + ")" : string + "(" + n6 + ")";
        }
        int n7 = (int)(l2.cl - l2.cq);
        n7 -= this.cj;
        Paint paint = this.av;
        if (bl) {
            paint = this.aw;
        } else {
            Integer n8 = a2.h();
            if (n8 != null) {
                this.at.a(paint);
                paint = this.at;
                paint.b(n8);
            }
        }
        float f5 = l2.bO.b(string, paint);
        float f6 = l2.bO.a(string, paint);
        this.ci = f6 + (float)n5;
        if ((float)this.cl < f5) {
            this.cl = (int)f5;
        }
        int n9 = this.ch;
        if (a2.w) {
            n9 = 0;
        }
        int n10 = 0;
        int n11 = 0;
        if (n9 == 0) {
            n10 = this.cg;
        } else {
            n11 = this.cf;
        }
        int n12 = 0;
        int n13 = n5;
        int n14 = n5;
        int n15 = n5;
        int n16 = n5;
        boolean bl3 = false;
        float f7 = f5 + (float)n15 + (float)n14;
        if (a2.k) {
            f7 += 80.0f;
        }
        if ((float)n7 < f7 && a2.i != null) {
            bl3 = true;
            n11 = this.cf = (int)((float)this.cf + this.ci);
            n7 += this.cj;
            this.cj = 0;
        }
        if (n9 != 0) {
            n13 = 0;
        }
        if (a3 != null && !a3.k) {
            n14 = 0;
        }
        if (bl2 && !a2.k) {
            n7 += n15;
            n15 = 0;
        }
        if (bl2 && a2.k && !bl3) {
            n12 = l2.bO.b("AA", paint);
        }
        n7 -= n12;
        com.corrodinggames.rts.gameFramework.m.e e2 = a2.k();
        float f8 = 1.0f;
        if (e2 != null) {
            f4 = f6 - 3.0f;
            if (f4 < 3.0f) {
                f4 = 3.0f;
            }
            f8 = com.corrodinggames.rts.gameFramework.f.d.a(e2, f6 * 3.0f, f4);
            f3 = (float)e2.p * f8 + 3.0f;
            n14 += (int)f3;
        } else {
            f3 = 0.0f;
        }
        f4 = (float)n7 - f5 - (float)n10;
        com.corrodinggames.rts.gameFramework.f.d.a(string, f4 - (float)n5, n11 + n5, paint, this.ax, n14, n13, n15, n16);
        if (e2 != null) {
            int n17 = (int)(f4 - f3 / 2.0f - (float)e2.r * f8 - 3.0f);
            int n18 = (int)((float)(n11 + n5) + f6 / 2.0f - (float)e2.s * f8);
            l2.bO.a(e2, (float)n17, (float)n18, this.bD, 0.0f, f8);
        }
        if (n3 == 0) {
            if (n9 == 0) {
                this.cg = (int)((float)this.cg + (f5 + (float)n15 + (float)n14));
            }
            if (this.ch == n9) {
                this.cf = (int)((float)this.cf + this.ci);
                ++this.ch;
            }
        }
        this.cj = (int)((float)this.cj + (f5 + (float)n15 + (float)n14 + (float)n12));
        return true;
    }

    public boolean k() {
        return this.a(com.corrodinggames.rts.gameFramework.f.h.b, true);
    }

    public boolean b(boolean bl) {
        return this.a(bl ? com.corrodinggames.rts.gameFramework.f.h.a : com.corrodinggames.rts.gameFramework.f.h.b, false);
    }

    public boolean a(h h2) {
        return this.a(h2, false);
    }

    public boolean a(h h2, boolean bl) {
        int n2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (com.corrodinggames.rts.gameFramework.l.av() && !bl) {
            return false;
        }
        boolean bl2 = false;
        this.bd = true;
        float f2 = l2.cj * 0.6f;
        int n3 = (int)(100.0f * f2);
        int n4 = (int)(10.0f * f2);
        int n5 = (int)(l2.cm - (float)((int)(9.0f * f2)) - (float)n3 * this.be);
        if (bR) {
            n5 = (int)((float)n5 - l2.bW.d);
        }
        if (h2 == com.corrodinggames.rts.gameFramework.f.h.c) {
            n2 = (int)(20.0f * f2) + n3;
            this.by.a(n4 + (n2 += (int)(20.0f * f2) + n3), n5, n4 + n2 + n3, n5 + n3);
            l2.bO.a(this.bc, (float)this.by.a, (float)this.by.b, this.bf, 0.0f, f2);
        } else if (h2 == com.corrodinggames.rts.gameFramework.f.h.a) {
            this.by.a(n4, n5, n4 + n3, n5 + n3);
            l2.bO.a(this.bb, (float)this.by.a, (float)this.by.b, this.bf, 0.0f, f2);
        } else {
            n2 = (int)(20.0f * f2) + n3;
            this.by.a(n4 + n2, n5, n4 + n2 + n3, n5 + n3);
            l2.bO.a(this.ba, (float)this.by.a, (float)this.by.b, this.bf, 0.0f, f2);
        }
        n2 = 0;
        com.corrodinggames.rts.gameFramework.f.a(this.by, 10.0f * f2);
        if (this.U && !this.T && this.by.b((int)this.x, (int)this.y)) {
            n2 = 1;
        }
        this.a((float)this.by.a, (float)this.by.b, (float)this.by.b(), this.by.c());
        return n2 != 0;
    }

    public boolean l() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.ac != null) {
            if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.b) {
                this.ac = null;
                this.ae = false;
                this.ai = false;
                this.aa = null;
                this.ap = false;
                ++this.ad;
            } else {
                this.ac = null;
            }
            this.as = 0;
            return true;
        }
        return false;
    }

    public void c(float f2) {
        int n2;
        int n3;
        int n4;
        float f3;
        float f4;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        Point point = l2.bW.c(this.x, this.y);
        if (point != null) {
            f4 = point.a;
            f3 = point.b;
        } else {
            f4 = this.x / l2.cX + l2.cw;
            f3 = this.y / l2.cX + l2.cx;
        }
        this.af = com.corrodinggames.rts.gameFramework.f.a(this.af, f2);
        this.bx.a((int)(l2.cl - l2.cq), 0, (int)l2.cl, (int)l2.cm);
        if (!bO && (this.U || this.I) && this.bx.b((int)this.x, (int)this.y)) {
            this.aZ = true;
        }
        this.g.a(f2);
        this.g.b(f2);
        this.X += f2;
        if (!l2.A()) {
            n4 = this.g.d(f2);
            this.g.a(f2, n4);
            this.g.e(f2);
            this.h.a(f2, com.corrodinggames.rts.gameFramework.f.m.a);
            this.i.a(f2);
            n3 = Math.max((int)((float)this.cf + this.ci * 2.0f), 130);
            this.j.a(f2, n3);
            if (this.u) {
                this.g.c(f2);
            }
            this.k.b(f2);
            this.g.a(f2, true);
        }
        this.a(f2, f4, f3, point);
        if (!l2.A() && !this.u) {
            this.g.c(f2);
        }
        n4 = 0;
        if (!this.T) {
            n3 = 1;
            n2 = 1;
            boolean bl = true;
            if (com.corrodinggames.rts.gameFramework.l.av() && l2.bQ.mouseSupport) {
                if (l2.bQ.mouseOrders == 0) {
                    n3 = 1;
                } else {
                    n3 = 0;
                    n2 = 0;
                    bl = false;
                    if (l2.bQ.mouseOrders == 1) {
                        if (l2.e(1)) {
                            n2 = 1;
                        } else if (l2.e(2)) {
                            bl = true;
                        }
                    } else if (l2.e(2)) {
                        n2 = 1;
                    } else if (l2.e(1)) {
                        bl = true;
                    }
                }
            }
            float f5 = f4;
            float f6 = f3;
            if (this.I && point != null && this.J) {
                boolean bl2 = false;
                if (n3 == 0 && !bl) {
                    bl2 = true;
                }
                if (this.q() == 0 || !this.C()) {
                    bl2 = true;
                }
                if (n3 != 0 && this.w > 20.0f) {
                    bl2 = true;
                }
                if (bl2) {
                    l2.b(f5, f6);
                    n4 = 1;
                }
            }
            if ((this.C || point != null && (n3 != 0 || bl)) && n4 == 0 && this.ac == null && this.U) {
                if (this.w > 30.0f) {
                    if (this.a() && point == null) {
                        float f7 = this.b();
                        this.y();
                        this.b(f5, f6, f7 /= l2.cX);
                        this.E();
                    }
                } else {
                    l2.cU = false;
                    if (n3 == 0) {
                        if (n2 != 0) {
                            am am2 = null;
                            if (point == null) {
                                am2 = this.a(f5, f6, true);
                            }
                            this.a(am2);
                        } else if (bl) {
                            am am3 = null;
                            if (point == null) {
                                am3 = this.a(f5, f6, false);
                            }
                            boolean bl3 = false;
                            if (am3 == null) {
                                bl3 = true;
                            } else if (!this.a(am3, false, f5, f6, point)) {
                                bl3 = true;
                            }
                            if (bl3) {
                                this.c(f5, f6, point);
                            }
                        }
                    } else {
                        am am4 = null;
                        am am5 = null;
                        if (point == null) {
                            am4 = this.a(f5, f6, true);
                            am5 = this.a(f5, f6, false);
                        }
                        if (am4 == null && am5 == null) {
                            this.c(f5, f6, point);
                        } else if (am5 != null) {
                            if (!this.a(am5, true, f5, f6, point)) {
                                if (!am5.t()) {
                                    this.a(am5);
                                } else if (am4 != null) {
                                    this.a(am4);
                                }
                            }
                        } else {
                            this.a(am4);
                        }
                    }
                }
            }
        }
        if (this.ac == null && this.I && !this.T && !this.J && !this.aZ) {
            this.au.a(Paint$Style.a);
            this.au.a(1.0f);
            if (this.w > 20.0f && this.a()) {
                float f8 = this.b();
                this.au.a(100, 0, 255, 0);
                l2.bO.a(this.x, this.y, f8, this.au);
                this.au.a(Paint$Style.b);
                this.au.a(1.0f);
                this.au.a(200, 0, 255, 0);
                l2.bO.a(this.x, this.y, f8, this.au);
            }
        }
        if (l2.bk && l2.ac() && l2.ae() > 0) {
            Paint paint = new Paint();
            paint.c(100);
            for (n2 = 0; n2 < l2.ae(); ++n2) {
                l2.bO.i();
                l2.bO.a(0.7f, 0.7f, l2.b(n2), l2.c(n2));
                l2.bO.a(this.bm, l2.b(n2), l2.c(n2), paint);
                l2.bO.j();
            }
        }
        if (!this.I) {
            this.w = 0.0f;
            this.T = false;
        }
        this.H = this.I;
        l2.ad();
        if (ce) {
            com.corrodinggames.rts.gameFramework.f.g.K();
            ce = false;
        }
    }

    public void a(float f2, float f3, float f4, Point point) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am am2 = this.g.f();
        if (this.bd) {
            this.be = com.corrodinggames.rts.gameFramework.f.a(this.be, 1.0f, 0.05f * f2);
            this.be = (float)((double)this.be + 0.08 * (double)(1.0f - this.be));
        } else {
            this.be = com.corrodinggames.rts.gameFramework.f.a(this.be, 0.0f, 0.3f * f2);
        }
        this.bd = false;
        if (this.ac != null) {
            Object object;
            if (this.ac instanceof com.corrodinggames.rts.game.units.a.g) {
                object = (com.corrodinggames.rts.game.units.a.g)this.ac;
                if (((com.corrodinggames.rts.game.units.a.g)object).b != null) {
                    am2 = ((com.corrodinggames.rts.game.units.a.g)object).b;
                }
            }
            if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.e) {
                this.a(this.ac, false, am2, false, true);
                if (this.b(false) || this.n()) {
                    this.l();
                    this.U = false;
                } else if (this.U && !this.T && !this.m()) {
                    object = this.a(f3, f4, false);
                    if (object != null && this.ac.o((am)object)) {
                        this.b((am)object);
                        if (!this.a(l2)) {
                            this.l();
                        }
                    } else {
                        this.a(f3, f4, 0.0f);
                    }
                    this.U = false;
                }
            } else if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.f) {
                this.a(this.ac, false, am2, false, true);
                if (this.b(false) || this.n()) {
                    this.l();
                    this.U = false;
                } else if (this.U && !this.T && !this.m()) {
                    object = this.a(f3, f4, true);
                    if (object != null && this.ac.o((am)object)) {
                        this.d((am)object);
                        if (!this.a(l2)) {
                            this.l();
                        }
                    } else {
                        this.a(f3, f4, 0.0f);
                    }
                    this.U = false;
                }
            } else if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.d) {
                this.a(this.ac, false, am2, false, true);
                if (this.b(false) || this.n()) {
                    this.l();
                    this.U = false;
                } else if (this.U && !this.T && !this.m()) {
                    this.b(f3, f4);
                    this.l();
                    this.U = false;
                }
            } else if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.g) {
                boolean bl;
                boolean bl2;
                this.a(this.ac, false, am2, false, true);
                object = this.g.f();
                s s2 = this.ac;
                if (this.ac instanceof com.corrodinggames.rts.game.units.a.g) {
                    com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
                    if (g2.b != null) {
                        object = g2.b;
                    }
                    s2 = g2.a;
                }
                boolean bl3 = this.k();
                boolean bl4 = bl2 = this.U && !this.M && this.C && !this.T && !this.m();
                if (this.ac.p()) {
                    if (com.corrodinggames.rts.gameFramework.l.aw()) {
                        bl2 = this.o() && !this.M && this.C && !this.K && this.J();
                    } else {
                        boolean bl5 = bl2 = this.I && !this.M && this.C && !this.K && this.J();
                    }
                }
                if (object != null && object instanceof y) {
                    l2.bO.i();
                    l2.R();
                    bl = this.I && !this.T && !this.M && !this.K && point == null;
                    float f5 = f3;
                    float f6 = f4;
                    if (com.corrodinggames.rts.gameFramework.l.aw() && l2.bQ.mouseSupport) {
                        f5 = l2.af() / l2.cX + l2.cw;
                        f6 = l2.ag() / l2.cX + l2.cx;
                        bl = true;
                        if (this.M) {
                            bl = false;
                        }
                    }
                    if (!this.a(this.z, this.A)) {
                        bl = false;
                    }
                    ((y)object).a(s2, bl, f5, f6);
                    l2.bO.j();
                }
                if (bl3 || this.n()) {
                    this.l();
                    this.U = false;
                } else if (bl2 && point == null) {
                    bl = false;
                    if (this.a(this.ac, f3, f4)) {
                        bl = true;
                    }
                    if (!bl) {
                        this.b(this.ac, f3, f4);
                        if (!this.a(l2) && !this.ac.o()) {
                            this.l();
                        }
                    } else {
                        this.a(f3, f4, 0.0f);
                    }
                    this.U = false;
                }
            } else if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.h) {
                this.a(this.ac, false, am2, false, true);
                if (this.b(false) || this.n()) {
                    this.l();
                    this.U = false;
                } else if (this.U && !this.T && !this.m()) {
                    this.d(f3, f4, point);
                    if (!this.a(l2)) {
                        this.l();
                        this.U = false;
                    }
                }
            } else if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.l) {
                this.a(this.ac, false, am2, false, true);
                if (this.b(false) || this.n()) {
                    this.l();
                    this.U = false;
                } else if (this.U && !this.T && !this.m()) {
                    object = this.a(f3, f4, true);
                    if (object != null && this.ac.o((am)object)) {
                        this.e((am)object);
                        this.l();
                    } else {
                        this.a(f3, f4, 0.0f);
                    }
                    this.U = false;
                }
            } else if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.m) {
                this.a(this.ac, false, am2, false, true);
                if (this.a(com.corrodinggames.rts.gameFramework.f.h.a, true) || this.n()) {
                    this.l();
                    this.U = false;
                } else if (this.U && !this.T && !this.m()) {
                    this.a(f3, f4, point, this.as == 0);
                    ++this.as;
                }
            } else if (this.ac.e() == com.corrodinggames.rts.game.units.a.u.j) {
                this.a(this.ac, false, am2, false, true);
                if (this.k() || this.n()) {
                    this.l();
                    this.U = false;
                } else if (this.U && !this.T && !this.m() && point == null) {
                    if (this.ac instanceof com.corrodinggames.rts.game.units.a.j) {
                        this.a(f3, f4, point, (com.corrodinggames.rts.game.units.a.j)this.ac);
                    } else {
                        com.corrodinggames.rts.gameFramework.l.b("orderBuildingSpecialAction is not a PingMapAction, it is: " + this.ac.getClass().getName());
                    }
                    this.l();
                    this.U = false;
                }
            } else if (this.ac.i() != null && this.ac.e() == com.corrodinggames.rts.game.units.a.u.b) {
                this.a(f3, f4, point);
            }
        }
    }

    public void a(float f2, float f3, Point point) {
        am am2;
        float f4;
        float f5;
        s s2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am am3 = this.g.f();
        boolean bl2 = false;
        if (am3 != null && (s2 = am3.a(this.ac.N())) != null) {
            boolean bl3 = bl2 = this.ac.a(am3, true) && !com.corrodinggames.rts.gameFramework.f.a.a(this.ac);
            if (!this.ac.b(am3)) {
                bl2 = false;
            }
        }
        this.a(this.ac, false, am3, !bl2, true);
        float f6 = this.z / l2.cX;
        float f7 = this.A / l2.cX;
        float f8 = f6;
        float f9 = f7;
        boolean bl4 = false;
        boolean bl5 = false;
        boolean bl6 = false;
        boolean bl7 = false;
        if (com.corrodinggames.rts.gameFramework.l.aw() && l2.bQ.mouseSupport) {
            bl4 = true;
            bl7 = this.L;
        }
        if (bl4) {
            if (this.o()) {
                if (!this.ap) {
                    this.ap = true;
                    this.an = f8 + l2.cw;
                    this.ao = f9 + l2.cx;
                }
            } else {
                this.ap = false;
            }
            if (this.ap) {
                float f10 = f8 - (this.an - l2.cw);
                float f11 = f9 - (this.ao - l2.cx);
                if (com.corrodinggames.rts.gameFramework.f.c(f10) > 4.0f || com.corrodinggames.rts.gameFramework.f.c(f11) > 4.0f) {
                    bl5 = true;
                }
            }
        }
        boolean bl8 = false;
        boolean bl9 = false;
        boolean bl10 = false;
        boolean bl11 = false;
        boolean bl12 = true;
        boolean bl13 = false;
        if (com.corrodinggames.rts.gameFramework.l.av() && l2.bQ.mouseSupport) {
            bl13 = true;
        }
        if (this.ae && !bl13) {
            if (bl2) {
                if (!this.ai && this.a(com.corrodinggames.rts.gameFramework.f.h.a)) {
                    l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5f);
                    this.U = false;
                    bl8 = true;
                }
                if (this.a(com.corrodinggames.rts.gameFramework.f.h.c)) {
                    l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5f);
                    this.U = false;
                    bl10 = true;
                }
            }
            if (this.a(com.corrodinggames.rts.gameFramework.f.h.b)) {
                l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.i, 0.7f);
                this.U = false;
                bl9 = true;
            }
        }
        boolean bl14 = false;
        if (com.corrodinggames.rts.gameFramework.l.aw() && l2.bQ.mouseSupport) {
            bl14 = true;
        }
        if (this.U && !this.T) {
            bl14 = true;
        }
        if (com.corrodinggames.rts.gameFramework.l.au()) {
            bl6 = true;
            if (l2.ae() == 2) {
                bl14 = true;
                f8 = l2.b(0) / l2.cX;
                f9 = l2.c(0) / l2.cX;
                f5 = l2.b(1) / l2.cX;
                f4 = l2.c(1) / l2.cX;
                this.ap = true;
                this.an = f5;
                this.ao = f4;
            } else if (this.U && !this.T) {
                this.ap = false;
            }
            if (this.ap) {
                bl5 = true;
            }
        }
        if (bl14) {
            this.ae = true;
            this.ag = f8 * l2.cX;
            this.ah = f9 * l2.cX;
            if (!this.a(l2.af(), l2.ag())) {
                this.ae = false;
                bl12 = false;
            }
        }
        f5 = this.an;
        f4 = this.ao;
        if (bl6) {
            f5 += l2.cw + l2.cr;
            f4 += l2.cx + l2.cs;
        }
        as as2 = this.ac.i();
        int n2 = this.ac.t();
        boolean bl15 = false;
        if (com.corrodinggames.rts.gameFramework.l.aw() && l2.bQ.mouseSupport && !l2.cK.b((int)this.z, (int)this.A)) {
            bl15 = true;
        }
        if (!((am2 = com.corrodinggames.rts.game.units.am.c(as2)) != null && am2 instanceof y || com.corrodinggames.rts.game.units.custom.l.b == null)) {
            am2 = com.corrodinggames.rts.game.units.am.c(com.corrodinggames.rts.game.units.custom.l.b);
        }
        if (this.ae && !bl15) {
            float f12;
            float f13;
            am am4;
            Object object;
            y y2 = (y)am2;
            l2.bL.b(this.ag / l2.cX + l2.cw, this.ah / l2.cX + l2.cx);
            y2.eo = l2.bL.T;
            y2.ep = l2.bL.U;
            if (as2.p()) {
                bl5 = false;
                object = com.corrodinggames.rts.gameFramework.f.j.a((int)y2.eo, (int)y2.ep, 3);
                if (object != null) {
                    y2.eo = ((Point)object).a;
                    y2.ep = ((Point)object).b;
                }
            }
            y2.cg = !y2.bI() ? 0.0f : -90.0f;
            y2.eo += y2.cZ();
            y2.ep += y2.da();
            y2.b(this.aa.bX);
            y2.a(n2);
            y2.cp = true;
            object = y2.b(false, l2.bs);
            if (com.corrodinggames.rts.gameFramework.d.a.a(l2.bs, y2, this.ad)) {
                object = "{0}";
            }
            if (this.q() == 1 && am3 != null && am3 instanceof y && !((y)(am4 = (y)am3)).aR()) {
                boolean bl16;
                f13 = com.corrodinggames.rts.gameFramework.f.a(((y)am4).eo, ((y)am4).ep, y2.eo, y2.ep);
                f12 = am4.f(y2.r());
                if (f12 > 800000.0f) {
                    bl16 = true;
                } else {
                    boolean bl17 = bl16 = f13 <= f12 * f12;
                }
                if (!bl16) {
                    object = "{0}";
                }
            }
            if (bl5) {
                // empty if block
            }
            am4 = null;
            if (this.q() == 1) {
                am4 = am3;
            }
            if (bl2) {
                if (bl5) {
                    if (com.corrodinggames.rts.gameFramework.l.av() || com.corrodinggames.rts.gameFramework.l.au() && l2.ae() == 2) {
                        l2.bO.a(f8 * l2.cX, f9 * l2.cX, (f5 - l2.cw) * l2.cX, (f4 - l2.cx) * l2.cX, this.bE);
                    } else {
                        l2.bO.a((y2.eo - l2.cw) * l2.cX, (y2.ep - l2.cx) * l2.cX, (f5 - l2.cw) * l2.cX, (f4 - l2.cx) * l2.cX, this.bE);
                    }
                    boolean bl18 = true;
                    object = null;
                    this.a(y2, f5, f4, y2.eo, y2.ep, bl18, null, am4);
                } else {
                    this.a(y2, y2.eo, y2.ep, true, bl7, am4);
                }
            }
            y2.a(1);
            if (bl12 && this.p()) {
                this.U = false;
                if (this.a(l2)) {
                    bl10 = true;
                    bl11 = true;
                } else {
                    bl8 = true;
                }
            }
            if (this.n()) {
                this.U = false;
                bl9 = true;
            }
            if (this.U && !this.T) {
                f13 = f8;
                f12 = f9;
                float f14 = this.aq;
                float f15 = this.ar;
                float f16 = 15.0f;
                if (com.corrodinggames.rts.gameFramework.f.c(f14 - f13) < f16 && com.corrodinggames.rts.gameFramework.f.c(f15 - f12) < f16 && this.af != 0.0f) {
                    this.U = false;
                    l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5f);
                    if (this.ai) {
                        bl10 = true;
                    } else {
                        bl8 = true;
                    }
                }
                this.af = 80.0f;
                this.aq = f8;
                this.ar = f9;
            }
            if (bl8 || bl10) {
                if (!bl2) {
                    String string;
                    s s3;
                    l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.7f);
                    if (object == null && am3 != null && this.ac != null && (s3 = am3.a(this.ac.N())) != null && (object = this.ac.j(am3)) == null && (string = this.f(this.ac)) != null) {
                        object = this.bI.b();
                    }
                    if (object != "{0}") {
                        this.c((String)object);
                    }
                } else if (object != null) {
                    l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.7f);
                    if (object != "{0}") {
                        Object object2 = object;
                        if (object2 == "{2}") {
                            object2 = this.bK;
                        }
                        if (object2 == "{3}") {
                            object2 = this.bL;
                        }
                        if (object2 == "{1}") {
                            object2 = this.bJ;
                        }
                        this.c((String)object2);
                    }
                } else {
                    f13 = y2.eo;
                    f12 = y2.ep;
                    ArrayList<PointF> arrayList = new ArrayList<PointF>();
                    if (bl5) {
                        boolean bl19 = false;
                        this.a(y2, f5, f4, y2.eo, y2.ep, bl19, arrayList, null);
                    } else {
                        arrayList.add(new PointF(f13, f12));
                    }
                    int n3 = 0;
                    boolean bl20 = true;
                    for (PointF pointF : arrayList) {
                        Object object3;
                        e e2;
                        if (this.ac.A()) {
                            e2 = this.x();
                            this.a(e2);
                            e2.a(this.ac.N(), pointF, null);
                            continue;
                        }
                        e2 = this.x();
                        if (bl20) {
                            bl20 = false;
                            if (bl10) {
                                if (!e2.e) {
                                    e2.f = true;
                                }
                                this.ai = true;
                            }
                        } else {
                            e2.e = true;
                        }
                        y y3 = this.t();
                        if (this.ac instanceof com.corrodinggames.rts.game.units.a.g) {
                            object3 = ((com.corrodinggames.rts.game.units.a.g)this.ac).b;
                            e2.a((y)object3);
                            y3 = object3;
                        } else {
                            this.a(e2);
                        }
                        e2.a(pointF.a, pointF.b, as2, n2);
                        if (y3 != null) {
                            object3 = new com.corrodinggames.rts.gameFramework.d.a();
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).d = as2;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).g = pointF.a;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).h = pointF.b;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).n = true;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).o = y3;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).e = l2.bs;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).f = n2;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).j = l2.bs;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).r = this.ad;
                            ((com.corrodinggames.rts.gameFramework.d.a)object3).s = 1.0f + 0.15f * (float)n3;
                            if (y3.av() >= 29) {
                                ((com.corrodinggames.rts.gameFramework.d.a)object3).q = true;
                            }
                        }
                        ++n3;
                    }
                    this.aU = 5.0f;
                    if (com.corrodinggames.rts.gameFramework.l.aw()) {
                        this.aU = 1.0f;
                    }
                    this.ap = false;
                    if (!bl10) {
                        if (n3 > 0) {
                            boolean bl21 = true;
                            if (y2 != null && !this.p(y2)) {
                                bl21 = false;
                            }
                            this.ac = null;
                            this.ae = false;
                            this.ai = false;
                            this.aa = null;
                            if (bl21) {
                                this.y();
                            }
                            ++this.ad;
                        }
                    } else if (!bl11) {
                        float f17 = y2.eo;
                        float f18 = y2.ep;
                        boolean bl22 = false;
                        if (com.corrodinggames.rts.gameFramework.f.c(f17 - this.aj) < (float)(y2.cd().b() * l2.bL.n) * 2.0f + (float)(3 * l2.bL.n) && com.corrodinggames.rts.gameFramework.f.c(f18 - this.ak) < (float)(y2.cd().c() * l2.bL.o) * 2.0f + (float)(3 * l2.bL.o)) {
                            this.al = f17 - this.aj;
                            this.am = f18 - this.ak;
                            if (com.corrodinggames.rts.gameFramework.f.c(this.al) > com.corrodinggames.rts.gameFramework.f.c(this.am)) {
                                this.am = 0.0f;
                            } else {
                                this.al = 0.0f;
                            }
                        }
                        if (y2.cd().c() > y2.cd().b() + 1) {
                            this.am = 0.0f;
                        }
                        this.aj = f17;
                        this.ak = f18;
                        float f19 = 0.0f;
                        float f20 = 0.0f;
                        if (this.am < 0.0f) {
                            f19 = -1.0f;
                        }
                        if (this.al < 0.0f) {
                            f20 = -1.0f;
                        }
                        if (this.am > 0.0f) {
                            f19 = 1.0f;
                        }
                        if (this.al > 0.0f) {
                            f20 = 1.0f;
                        }
                        if (f20 == 0.0f && f19 == 0.0f) {
                            f20 = 1.0f;
                        }
                        ArrayList arrayList2 = new ArrayList();
                        float f21 = f17 + 200.0f * f20;
                        float f22 = f18 + 200.0f * f19;
                        float f23 = -y2.cZ() + 1.0f;
                        float f24 = -y2.da() + 1.0f;
                        boolean bl23 = false;
                        this.a(y2, f17 + f23, f18 + f24, f21 + f23, f22 + f24, bl23, arrayList2, null);
                        if (arrayList2.size() > 0) {
                            y2.eo = ((PointF)arrayList2.get((int)0)).a;
                            y2.ep = ((PointF)arrayList2.get((int)0)).b;
                            bl22 = true;
                        }
                        if (!bl22) {
                            f21 = f17 + 200.0f * -f20;
                            f22 = f18 + 200.0f * -f19;
                            this.a(y2, f17 + f23, f18 + f24, f21 + f23, f22 + f24, bl23, arrayList2, null);
                            if (arrayList2.size() > 0) {
                                y2.eo = ((PointF)arrayList2.get((int)0)).a;
                                y2.ep = ((PointF)arrayList2.get((int)0)).b;
                                bl22 = true;
                            }
                        }
                        if (!bl22) {
                            y2.eo += (float)(3 * l2.bL.n);
                            y2.ep += (float)l2.bL.n;
                        }
                        if (bl22) {
                            float f25 = y2.eo - f17;
                            float f26 = y2.ep - f18;
                            float f27 = l2.cy;
                            float f28 = l2.cz;
                            l2.cr += f25;
                            l2.cs += f26;
                            l2.cy += l2.cr;
                            l2.cz += l2.cs;
                            float f29 = l2.cy;
                            float f30 = l2.cz;
                            l2.Q();
                            float f31 = l2.cy - f29;
                            float f32 = l2.cz - f30;
                            l2.cr += f31;
                            l2.cs += f32;
                            float f33 = f27 + f25 - l2.cy;
                            float f34 = f28 + f26 - l2.cz;
                            if (com.corrodinggames.rts.gameFramework.f.c(f33) > 1.0f) {
                                this.ag += f33 * l2.cX;
                            }
                            if (com.corrodinggames.rts.gameFramework.f.c(f34) > 1.0f) {
                                this.ah += f34 * l2.cX;
                            }
                            l2.cy -= l2.cr;
                            l2.cz -= l2.cs;
                        }
                    }
                }
            }
            if (bl9) {
                this.l();
                if (this.ai) {
                    this.y();
                }
            }
        }
    }

    public boolean m() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return com.corrodinggames.rts.gameFramework.l.aw() && l2.bQ.mouseSupport && !this.n() && !this.p();
    }

    public boolean n() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (com.corrodinggames.rts.gameFramework.l.aw() && l2.bQ.mouseSupport && this.U && !this.T && !this.aZ) {
            int n2 = 1;
            int n3 = 2;
            if (l2.bQ.mousePlacement == 2) {
                n2 = 2;
                n3 = 1;
            }
            if (l2.e(n2)) {
                // empty if block
            }
            if (l2.e(n3)) {
                return true;
            }
        }
        return false;
    }

    public boolean o() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (com.corrodinggames.rts.gameFramework.l.av() && l2.bQ.mouseSupport && (this.U || this.I)) {
            int n2 = 1;
            int n3 = 2;
            if (l2.bQ.mousePlacement == 2) {
                n2 = 2;
                n3 = 1;
            }
            if (l2.e(n2)) {
                return true;
            }
            if (l2.e(n3)) {
                // empty if block
            }
        }
        return false;
    }

    public boolean p() {
        if (this.U && !this.T && !this.aZ) {
            return this.o();
        }
        return false;
    }

    public void a(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (am2 != null && this.W == am2 && this.X < 40.0f && !this.b(l2)) {
            if (!this.a(l2)) {
                this.y();
            }
            this.h(am2);
        } else if (am2 != null) {
            if (!this.a(l2) && !this.b(l2)) {
                this.y();
            }
            this.a(am2, this.b(l2));
            this.W = am2;
            this.X = 0.0f;
        }
    }

    public boolean a(am am2, boolean bl2, float f2, float f3, Point point) {
        am am3;
        am am4;
        boolean bl3;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        n n2 = this.r();
        boolean bl4 = n2.c(am2.bX);
        if (bl4 && this.B() && this.q(am2)) {
            this.c(am2);
            return true;
        }
        if (n2.d(am2.bX) && (am2.cu < am2.cv || am2.cm < 1.0f) && this.q() != 0) {
            Object object;
            bl3 = true;
            boolean bl5 = false;
            boolean bl6 = false;
            boolean bl7 = false;
            if (am2.cr() && this.n((am)(object = am2))) {
                bl5 = true;
            }
            for (w w2 : this.bZ) {
                au au2;
                if (!(w2 instanceof y)) continue;
                y y2 = (y)w2;
                if (!y2.cG) continue;
                if (!this.m(y2)) {
                    bl3 = false;
                    break;
                }
                if (!y2.a(am2)) {
                    bl3 = false;
                    break;
                }
                if (y2.aS()) {
                    bl7 = true;
                }
                if ((au2 = y2.ar()) == null || au2.d() != com.corrodinggames.rts.game.units.av.d) continue;
                bl6 = true;
            }
            if (!(!bl3 || bl6 && bl5)) {
                if (bl7) {
                    this.d(am2);
                } else {
                    this.d(am2);
                }
                return true;
            }
        }
        if (am2.g() > 0.0f && this.q() != 0) {
            bl3 = true;
            for (w w3 : com.corrodinggames.rts.gameFramework.w.er) {
                if (!(w3 instanceof y)) continue;
                y y3 = (y)w3;
                if (!y3.cG) continue;
                if (!this.m(y3)) {
                    bl3 = false;
                    break;
                }
                if (y3.h(am2, true)) continue;
                bl3 = false;
                break;
            }
            if (bl3) {
                this.b(am2);
                return true;
            }
        }
        if (am2.cr() && this.n(am4 = am2)) {
            this.f(am4);
            return true;
        }
        if (com.corrodinggames.rts.gameFramework.l.av() && this.C() && this.o(am3 = am2)) {
            this.g(am3);
            return true;
        }
        boolean bl8 = false;
        if (!(bl2 && !am2.t() || n2.c(am2.bX))) {
            if (am2.bI()) {
                if (am2.cc().a()) {
                    bl8 = true;
                }
            } else if (!am2.bT) {
                bl8 = true;
            }
            if (!bl8 && !am2.i() && this.D()) {
                bl8 = true;
            }
        }
        if (bl8) {
            return false;
        }
        if (bl4 && this.B()) {
            this.a(am2.eo, am2.ep, am2.eq);
            return true;
        }
        return false;
    }

    public int q() {
        return this.aX;
    }

    void a(e e2) {
        for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
            if (!(w2 instanceof y)) continue;
            y y2 = (y)w2;
            if (!y2.cG || !this.m(y2)) continue;
            e2.a(y2);
        }
    }

    public n r() {
        y y2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        for (am am2 : this.bZ) {
            if (!(am2 instanceof y)) continue;
            y2 = (y)am2;
            if (y2.bX != l2.bs) continue;
            return y2.bX;
        }
        for (am am2 : this.bZ) {
            if (!(am2 instanceof y) || !this.m(y2 = (y)am2)) continue;
            return y2.bX;
        }
        return l2.bs;
    }

    public y s() {
        for (am am2 : this.bZ) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            return y2;
        }
        return null;
    }

    public y t() {
        for (am am2 : this.bZ) {
            y y2;
            if (!(am2 instanceof y) || !this.m(y2 = (y)am2)) continue;
            return y2;
        }
        return null;
    }

    void a(e e2, s s2, boolean bl2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            e2.a(g2.b);
            return;
        }
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        y y2 = null;
        int n2 = -99;
        for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
            s s3;
            if (!(w2 instanceof y)) continue;
            y y3 = (y)w2;
            if (!y3.cG || !this.m(y3) || (s3 = y3.a(c2)) == null || !s3.b(y3) || !s3.a((am)y3, true) && !bl2) continue;
            int n3 = 0;
            if (y3 instanceof com.corrodinggames.rts.game.units.d.l) {
                boolean bl3 = true;
                n3 = ((com.corrodinggames.rts.game.units.d.l)((Object)y3)).a(c2, true);
                if (y2 != null && (!bl2 ? n3 >= n2 : n3 <= n2)) break;
            }
            y2 = y3;
            n2 = n3;
        }
        if (y2 != null) {
            e2.a(y2);
        }
    }

    boolean a(s s2, float f2, float f3) {
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            y y2 = g2.b;
            s s3 = g2.p_();
            boolean bl2 = false;
            if (s3.b(y2) && s3.a((am)y2, true) && !y2.a(s3, f2, f3)) {
                bl2 = true;
            }
            return bl2;
        }
        boolean bl3 = false;
        for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
            s s4;
            if (!(w2 instanceof y)) continue;
            y y3 = (y)w2;
            if (!y3.cG || !this.m(y3) || (s4 = y3.a(s2.N())) == null || !s4.b(y3) || !s4.a((am)y3, true)) continue;
            if (!y3.a(s4, f2, f3)) {
                bl3 = true;
                continue;
            }
            return false;
        }
        return bl3;
    }

    void a(e e2, s s2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            e2.a(g2.b);
            return;
        }
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
            s s3;
            if (!(w2 instanceof y)) continue;
            y y2 = (y)w2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null || !s3.b(y2)) continue;
            e2.a(y2);
        }
    }

    public boolean a(s s2, boolean bl2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            return g2.a((am)g2.b, true);
        }
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        for (am am2 : this.bZ) {
            s s3;
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null || !s3.a((am)y2, bl2)) continue;
            return true;
        }
        return false;
    }

    public boolean a(s s2) {
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        if (s2.o_()) {
            return false;
        }
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            return g2.a((am)g2.b);
        }
        for (am am2 : this.bZ) {
            s s3;
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null || !s3.a((am)y2)) continue;
            return true;
        }
        return false;
    }

    public boolean b(s s2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            return g2.b(g2.b);
        }
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        for (am am2 : this.bZ) {
            s s3;
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null || !s3.b(y2)) continue;
            return true;
        }
        return false;
    }

    public boolean c(s s2) {
        boolean bl2 = false;
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            return g2.g(g2.b);
        }
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        for (am am2 : this.bZ) {
            s s3;
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null) continue;
            if (!s3.g(y2)) {
                return false;
            }
            bl2 = true;
        }
        return bl2;
    }

    public String d(s s2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            return g2.j(g2.b);
        }
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        for (am am2 : this.bZ) {
            String string;
            s s3;
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || !this.m(y2) || (s3 = y2.a(c2)) == null || !s3.g(y2) || (string = s3.j(y2)) == null) continue;
            return string;
        }
        return null;
    }

    public u e(s s2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            this.bY.clear();
            if (g2.b != null) {
                this.bY.a(g2.b);
            }
            return this.bY;
        }
        return this.bZ;
    }

    public String f(s s2) {
        u u2 = this.e(s2);
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        String string = null;
        boolean bl2 = false;
        for (am am2 : u2) {
            s s3;
            y y2;
            if (!(am2 instanceof y) || !this.m(y2 = (y)am2) || (s3 = y2.a(c2)) == null) continue;
            if (s3.B() != null && !s3.B().b(y2)) {
                String string2 = s3.B().a((am)y2, 4, true);
                if (string2 == null) continue;
                string = string2;
                continue;
            }
            bl2 = true;
        }
        if (bl2) {
            return null;
        }
        return string;
    }

    public boolean u() {
        if (this.aX == 0) {
            return false;
        }
        for (am am2 : this.bZ) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG) continue;
            if (!this.m(y2)) {
                return false;
            }
            ArrayList arrayList = y2.N();
            boolean bl2 = false;
            if (arrayList != null) {
                for (s s2 : arrayList) {
                    if (s2.e() != com.corrodinggames.rts.game.units.a.u.d) continue;
                    bl2 = true;
                }
            }
            if (bl2) continue;
            return false;
        }
        return true;
    }

    public boolean a(ag ag2) {
        for (am am2 : this.bZ) {
            y y2;
            if (!(am2 instanceof y) || !this.m(y2 = (y)am2) || !this.a(ag2, y2)) continue;
            return true;
        }
        return false;
    }

    public boolean a(ag ag2, am am2) {
        if (am2 instanceof y) {
            y y2 = (y)am2;
            if (!(ag2 != com.corrodinggames.rts.game.units.ag.a && ag2 != com.corrodinggames.rts.game.units.ag.b || com.corrodinggames.rts.gameFramework.l.a(this.bU, 1000L))) {
                return true;
            }
            if (ag2 == com.corrodinggames.rts.game.units.ag.c) {
                if (com.corrodinggames.rts.gameFramework.l.B().bx < 10) {
                    return false;
                }
                if (!com.corrodinggames.rts.gameFramework.l.a(this.bV, 1000L)) {
                    return true;
                }
            }
            if (y2.a(ag2)) {
                if (ag2 == com.corrodinggames.rts.game.units.ag.a || ag2 == com.corrodinggames.rts.game.units.ag.b) {
                    this.bU = com.corrodinggames.rts.gameFramework.l.V();
                }
                if (ag2 == com.corrodinggames.rts.game.units.ag.c) {
                    this.bV = com.corrodinggames.rts.gameFramework.l.V();
                }
                return true;
            }
        }
        return false;
    }

    public void b(float f2, float f3, Point point) {
        com.corrodinggames.rts.gameFramework.d.e e2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!this.C()) {
            if (l2.bQ.quickRally && this.u()) {
                this.b(f2, f3);
                return;
            }
            return;
        }
        e e3 = this.x();
        e3.h = true;
        e3.a(f2, f3);
        this.a(e3);
        if (!this.a(com.corrodinggames.rts.game.units.ag.b)) {
            l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2f);
        }
        if ((e2 = l2.bR.a(f2, f3, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e)) != null) {
            e2.ap = 8;
            e2.W = e2.V = 30.0f;
            e2.r = true;
            e2.E = 2.0f;
            e2.G = 2.8f * this.c();
            e2.F = 1.6f * this.c();
            e2.H = true;
        }
        if (point != null) {
            Point point2 = l2.bW.b(point.a, point.b);
            com.corrodinggames.rts.gameFramework.d.e e4 = l2.bR.a((float)point2.a, (float)point2.b, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if (e4 != null) {
                e4.ar = (short)4;
                e4.ap = 8;
                e4.V = 35.0f;
                e4.W = e2.V;
                e4.r = true;
                e4.E = 2.0f;
                e4.G = 1.3f;
                e4.F = 0.6f;
            }
        }
    }

    public void c(float f2, float f3, Point point) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.D && l2.bQ.doubleClickToAttackMove && this.B() && this.C()) {
            this.d(f2, f3, point);
        } else {
            this.b(f2, f3, point);
        }
    }

    public void d(float f2, float f3, Point point) {
        com.corrodinggames.rts.gameFramework.d.e e2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e3 = this.x();
        e3.h = true;
        e3.b(f2, f3);
        this.a(e3);
        if (!this.a(com.corrodinggames.rts.game.units.ag.b)) {
            l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2f);
        }
        if ((e2 = l2.bR.a(f2, f3, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e)) != null) {
            e2.aq = 17;
            e2.ap = 2;
            e2.W = e2.V = 30.0f;
            e2.r = true;
            e2.E = 2.0f;
            e2.Z = 1.0f;
            e2.G = 1.9f * this.c();
            e2.F = 3.5f * this.c();
            e2.H = true;
        }
        if (point != null) {
            Point point2 = l2.bW.b(point.a, point.b);
            com.corrodinggames.rts.gameFramework.d.e e4 = l2.bR.a((float)point2.a, (float)point2.b, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if (e4 != null) {
                e4.ar = (short)4;
                e4.ap = 9;
                e4.V = 35.0f;
                e4.W = e2.V;
                e4.r = true;
                e4.E = 2.0f;
                e4.G = 1.3f;
                e4.F = 0.6f;
            }
        }
    }

    public void v() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.x();
        e2.h();
        this.a(e2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2f);
    }

    public void a(s s2, PointF pointF, am am2, e e2) {
        if (s2 instanceof com.corrodinggames.rts.game.units.custom.a.g) {
            com.corrodinggames.rts.game.units.custom.a.g g2 = (com.corrodinggames.rts.game.units.custom.a.g)s2;
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
        }
    }

    public void b(s s2, float f2, float f3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        PointF pointF = new PointF(f2, f3);
        e e2 = this.x();
        if (!s2.I()) {
            this.a(e2, s2);
        } else {
            this.a(e2, s2, false);
        }
        e2.a(s2.N(), pointF, null);
        this.a(s2, pointF, null, e2);
        if (!s2.a(f2, f3)) {
            l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2f);
            com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(f2, f3, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if (e3 != null) {
                e3.ap = 9;
                e3.W = e3.V = 60.0f;
                e3.r = true;
                e3.E = 2.0f;
                e3.G = 3.8f * this.c();
                e3.F = 2.0f * this.c();
                e3.H = true;
                e3.Z = 1.5f;
            }
        }
    }

    public void b(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.x();
        this.a(e2);
        e2.d(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2f);
        com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
        if (e3 != null) {
            e3.ap = 12;
            e3.W = e3.V = 25.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.G = 1.2f * this.c();
            e3.F = 1.8f * this.c();
        }
    }

    public void b(float f2, float f3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.w();
        this.a(e2);
        PointF pointF = new PointF(f2, f3);
        e2.a(pointF);
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2f);
        com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(f2, f3, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
        if (e3 != null) {
            e3.ap = 8;
            e3.W = e3.V = 65.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.Z = 2.0f;
            e3.G = 2.0f * this.c();
            e3.F = 1.5f * this.c();
        }
    }

    public void a(float f2, float f3, Point point, com.corrodinggames.rts.game.units.a.j j2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!l2.bQ.showMapPingsOnBattlefield && !l2.bQ.showMapPingsOnMinimap) {
            this.b("Cannot send map ping, you have disabled both battlefield and minimap pings in your settings");
            return;
        }
        e e2 = this.x();
        PointF pointF = new PointF(f2, f3);
        e2.a(j2.N(), pointF, null);
        if (this.bW == 0L || this.bW + 15000L < System.currentTimeMillis()) {
            this.bW = System.currentTimeMillis();
            String string = "MAP PING - [i:" + j2.K() + "]";
            l2.bX.l(string);
        }
    }

    public void a(float f2, float f3, n n2, com.corrodinggames.rts.game.units.a.j j2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f4 = 1.0f;
        int n3 = 7 + j2.a.ordinal();
        if (!l2.bQ.showMapPingsOnBattlefield && !l2.bQ.showMapPingsOnMinimap) {
            if (!this.bX && !l2.cb.j()) {
                this.bX = true;
                this.h.a(null, "[WARNING: A player send a map ping, but you have disabled both battlefield and minimap pings in your settings]");
            }
            return;
        }
        if (l2.bQ.showMapPingsOnBattlefield) {
            com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.a(f2, f3, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if (e2 != null) {
                e2.aq = 9;
                e2.ap = 6;
                e2.E = 0.7f;
                e2.W = e2.V = 490.0f;
                e2.r = true;
                e2.S = 6.0f;
                e2.T = 60.0f;
                e2.J -= e2.S;
                e2.F = e2.G = 2.0f * f4;
                e2.ao = -0.5f;
                e2.H = true;
                if (n2 != null) {
                    e2.x = n2.K();
                    if (com.corrodinggames.rts.gameFramework.l.at()) {
                        e2.B = new LightingColorFilter(e2.x, 0);
                    }
                }
            }
            if (n3 != -1 && (e2 = l2.bR.a(f2, f3, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e)) != null) {
                e2.aq = 9;
                e2.ap = n3;
                e2.W = e2.V = 490.0f;
                e2.r = true;
                e2.E = 1.2f;
                e2.S = 6.0f;
                e2.T = 60.0f;
                e2.J -= e2.S;
                e2.F = e2.G = 2.0f * f4;
                e2.ao = -0.7f;
                e2.H = true;
            }
        }
        if (l2.bQ.showMapPingsOnMinimap) {
            Point point = l2.bW.b(f2, f3);
            com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a((float)point.a, (float)point.b, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if (e3 != null) {
                e3.ar = (short)4;
                e3.aq = 9;
                e3.ap = 6;
                e3.E = 0.8f;
                e3.W = e3.V = 470.0f;
                e3.r = true;
                e3.J -= 2.0f;
                e3.S = 2.0f;
                e3.T = 60.0f;
                e3.ao = -0.5f;
                if (n2 != null) {
                    e3.x = n2.K();
                    if (com.corrodinggames.rts.gameFramework.l.at()) {
                        e3.B = new LightingColorFilter(e3.x, 0);
                    }
                }
                e3.G = 1.0f;
                e3.F = 1.0f;
            }
            if ((e3 = l2.bR.a((float)point.a, (float)point.b, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e)) != null) {
                e3.ar = (short)4;
                e3.aq = 9;
                e3.ap = n3;
                e3.W = e3.V = 470.0f;
                e3.r = true;
                e3.E = 0.8f;
                e3.J -= 2.0f;
                e3.S = 2.0f;
                e3.T = 60.0f;
                if (n2 != null) {
                    // empty if block
                }
                e3.G = 1.0f;
                e3.F = 1.0f;
                e3.ao = -0.7f;
            }
        }
    }

    public e w() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = l2.cf.b(l2.bs);
        if (l2.bX.B) {
            e2.p = l2.bs;
        }
        return e2;
    }

    public e x() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.w();
        if (this.a(l2)) {
            e2.e = true;
        }
        return e2;
    }

    public void c(am am2) {
        com.corrodinggames.rts.gameFramework.d.e e2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e3 = this.x();
        e3.a(am2);
        this.a(e3);
        if (!this.a(com.corrodinggames.rts.game.units.ag.a)) {
            l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0f);
        }
        if ((e2 = l2.bR.a(am2.eo, am2.ep, am2.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e)) != null) {
            e2.b = am2;
            e2.I = 0.0f;
            e2.J = 0.0f;
            e2.K = 0.0f;
            e2.ap = 9;
            e2.W = e2.V = 35.0f;
            e2.r = true;
            e2.E = 1.5f;
            e2.H = true;
            e2.Z = 0.8f;
            e2.G = 1.9f * this.c();
            e2.F = 3.3f * this.c();
        }
        if ((e2 = l2.bR.a(am2.eo, am2.ep, am2.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e)) != null) {
            e2.b = am2;
            e2.I = 0.0f;
            e2.J = 0.0f;
            e2.K = 0.0f;
            e2.aq = 17;
            e2.ap = 0;
            e2.W = e2.V = 25.0f;
            e2.r = true;
            e2.E = 1.0f;
            e2.H = true;
            e2.Z = 0.8f;
            e2.G = 2.2f * this.c();
            e2.F = 1.1f * this.c();
        }
    }

    public void d(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.x();
        this.a(e2);
        e2.b(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0f);
        com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
        if (e3 != null) {
            e3.ap = 10;
            e3.W = e3.V = 35.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.G = 1.5f * this.c();
            e3.F = 2.2f * this.c();
        }
    }

    public void e(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.x();
        this.a(e2);
        e2.c(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0f);
        com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
        if (e3 != null) {
            e3.aq = 17;
            e3.ap = 1;
            e3.W = e3.V = 40.0f;
            e3.r = true;
            e3.E = 1.0f;
            e3.H = true;
            e3.Z = 0.0f;
            e3.G = 1.2f * this.c();
            e3.F = 1.9f * this.c();
        }
    }

    public void a(float f2, float f3, float f4) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.2f);
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.a(f2, f3, f4, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
        if (e2 != null) {
            e2.aq = 9;
            e2.ap = 14;
            e2.W = e2.V = 10.0f;
            e2.r = true;
            e2.E = 2.0f;
            e2.Z = 0.0f;
            e2.G = 1.1f * this.c();
            e2.F = 1.6f * this.c();
            e2.H = true;
        }
    }

    public void a(float f2, float f3, Point point, boolean bl2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.x();
        this.a(e2);
        e2.c(f2, f3);
        if (!bl2) {
            e2.e = true;
        }
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2f);
        com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(f2, f3, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
        if (e3 != null) {
            e3.aq = 17;
            e3.ap = 0;
            e3.W = e3.V = 40.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.Z = 8.0f;
            e3.G = 1.1f * this.c();
            e3.F = 1.9f * this.c();
            e3.H = true;
        }
        if (point != null) {
            Point point2 = l2.bW.b(point.a, point.b);
            com.corrodinggames.rts.gameFramework.d.e e4 = l2.bR.a((float)point2.a, (float)point2.b, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if (e4 != null) {
                e4.ar = (short)4;
                e4.ap = 9;
                e4.V = 35.0f;
                e4.W = e3.V;
                e4.r = true;
                e4.E = 2.0f;
                e4.G = 1.3f;
                e4.F = 0.6f;
            }
        }
    }

    public void f(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.x();
        this.a(e2);
        e2.e(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0f);
        com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
        if (e3 != null) {
            e3.ap = 11;
            e3.W = e3.V = 25.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.G = 1.8f * this.c();
            e3.F = 1.6f * this.c();
        }
    }

    public void g(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = this.x();
        this.a(e2);
        e2.f(am2);
        l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0f);
        com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(am2.eo, am2.ep, am2.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
        if (e3 != null) {
            e3.ap = 11;
            e3.W = e3.V = 25.0f;
            e3.r = true;
            e3.E = 2.0f;
            e3.H = true;
            e3.G = 1.8f * this.c();
            e3.F = 1.6f * this.c();
        }
    }

    public am a(float f2, float f3, boolean bl2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am am2 = null;
        float f4 = -1.0f;
        float f5 = 10.0f / l2.cX;
        float f6 = 5.0f / l2.cX;
        float f7 = 5.0f / l2.cX;
        n n2 = this.r();
        for (am am3 : com.corrodinggames.rts.game.units.am.bE) {
            if (!bl2 ? am3.cV() : am3.t()) continue;
            if (am3.bV || am3.cN != null) continue;
            float f8 = com.corrodinggames.rts.gameFramework.f.a(f2, f3, am3.eo, am3.ep - am3.eq);
            float f9 = am3.do();
            f9 = !am3.cG ? (f9 += f5) : (f9 += f6);
            boolean bl3 = n2.c(am3.bX);
            if (bl3) {
                f9 += f7;
            }
            if (!(f8 < f9 * f9) || bl3 && !am3.cg() || am2 != null && !(f8 < f4)) continue;
            am2 = am3;
            f4 = f8;
        }
        if (am2 != null && am2.bX != l2.bs && !am2.cf()) {
            return null;
        }
        return am2;
    }

    public void b(float f2, float f3, float f4) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
            float f5;
            if (!(w2 instanceof am)) continue;
            am am2 = (am)w2;
            if (am2.bV || am2.cN != null || am2.bX != l2.bs || !((f5 = com.corrodinggames.rts.gameFramework.f.a(f2, f3, am2.eo, am2.ep - am2.eq)) < f4 * f4)) continue;
            this.j(am2);
        }
    }

    public void h(am am2) {
        this.W = null;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
            if (!(w2 instanceof am)) continue;
            am am3 = (am)w2;
            if (am3.bV || am3.cN != null || am3.bX != am2.bX || !am3.s_() || !com.corrodinggames.rts.gameFramework.f.a.a(am3, am2) || am3.bX != l2.bs && !am3.cf()) continue;
            this.j(am3);
        }
    }

    public void y() {
        this.W = null;
        for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
            if (!(w2 instanceof am)) continue;
            am am2 = (am)w2;
            am2.cG = false;
        }
        this.aX = 0;
        ++this.Y;
        this.bZ.clear();
        com.corrodinggames.rts.gameFramework.f.g.K();
    }

    public boolean i(am am2) {
        boolean bl2;
        if (am2.t()) {
            return false;
        }
        n n2 = com.corrodinggames.rts.gameFramework.l.B().bs;
        return n2 == null || !(bl2 = n2.c(am2.bX)) || am2.cg();
    }

    public boolean j(am am2) {
        if (am2.cG) {
            return true;
        }
        if (!this.i(am2)) {
            return false;
        }
        this.k(am2);
        this.a(com.corrodinggames.rts.game.units.ag.c, am2);
        return true;
    }

    public void k(am am2) {
        if (!am2.cG) {
            if (!this.i(am2)) {
                return;
            }
            am2.cG = true;
            am2.cH = com.corrodinggames.rts.gameFramework.l.B().bA;
            ++this.aX;
            if (!(am2 instanceof com.corrodinggames.rts.game.units.h)) {
                ca = am2;
            }
            ++this.Y;
            this.bZ.a(am2);
            com.corrodinggames.rts.gameFramework.f.g.K();
        }
    }

    public static b z() {
        am am2 = ca;
        if (am2 == null) {
            return null;
        }
        as as2 = am2.r();
        if (as2 == null || !(as2 instanceof com.corrodinggames.rts.game.units.custom.l)) {
            return null;
        }
        com.corrodinggames.rts.game.units.custom.l l2 = (com.corrodinggames.rts.game.units.custom.l)as2;
        return l2.J;
    }

    public void a(am am2, boolean bl2) {
        if (!bl2) {
            this.j(am2);
            return;
        }
        if (am2.cG) {
            this.l(am2);
        } else {
            this.j(am2);
        }
    }

    public void l(am am2) {
        if (am2.cG) {
            am2.cG = false;
            --this.aX;
            this.bZ.remove(am2);
            ++this.Y;
            com.corrodinggames.rts.gameFramework.f.g.K();
        }
    }

    public boolean A() {
        if (this.q() == 0) {
            return false;
        }
        for (am am2 : this.bZ) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || !this.m(y2)) continue;
            return true;
        }
        return false;
    }

    public boolean B() {
        if (this.q() == 0) {
            return false;
        }
        for (am am2 : this.bZ) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || !this.m(y2) || !y2.l()) continue;
            return true;
        }
        return false;
    }

    public boolean C() {
        if (this.q() == 0) {
            return false;
        }
        for (am am2 : this.bZ) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || !y2.aS() || !this.m(y2)) continue;
            return true;
        }
        return false;
    }

    public boolean D() {
        if (this.q() == 0) {
            return true;
        }
        for (am am2 : this.bZ) {
            if (!(am2 instanceof y)) continue;
            y y2 = (y)am2;
            if (!y2.cG || y2.i()) continue;
            return false;
        }
        return true;
    }

    public boolean m(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (am2.cW()) {
            return false;
        }
        if (am2.bX == l2.bs) {
            return true;
        }
        if (am2.bX != null && am2.bX.b(l2.bs)) {
            return true;
        }
        return l2.bv || l2.cb.j();
    }

    public boolean n(am am2) {
        if (this.q() == 0) {
            return false;
        }
        for (am am3 : this.bZ) {
            if (!(am3 instanceof y)) continue;
            y y2 = (y)am3;
            if (!y2.cG || y2 == am2 || !this.m(y2) || !am2.d(y2, false)) continue;
            return true;
        }
        return false;
    }

    public boolean o(am am2) {
        if (this.q() == 0) {
            return false;
        }
        for (am am3 : this.bZ) {
            if (!(am3 instanceof y)) continue;
            y y2 = (y)am3;
            if (!y2.cG || y2 == am2 || !this.m(y2) || !y2.d(am2, false)) continue;
            return true;
        }
        return false;
    }

    public boolean p(am am2) {
        if (this.q() == 0) {
            return false;
        }
        for (am am3 : this.bZ) {
            if (!(am3 instanceof y)) continue;
            y y2 = (y)am3;
            if (!y2.cG || y2 == am2 || !this.m(y2) || !y2.a(am2)) continue;
            return true;
        }
        return false;
    }

    public boolean q(am am2) {
        if (this.q() == 0) {
            return false;
        }
        for (am am3 : this.bZ) {
            if (!(am3 instanceof y)) continue;
            y y2 = (y)am3;
            if (!y2.cG || y2 == am2 || !this.m(y2) || !com.corrodinggames.rts.game.units.aq.a(y2, am2)) continue;
            return true;
        }
        return false;
    }

    public void E() {
    }

    public boolean F() {
        return false;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        this.g.a(as2);
        as2.c(1);
        as2.a(this.ad);
    }

    public void a(com.corrodinggames.rts.gameFramework.j.k k2, boolean bl2) {
        this.g.a(k2, bl2);
        byte by = k2.d();
        if (by >= 1) {
            this.ad = k2.f();
        }
    }

    public void a(y y2, float f2, float f3, float f4, float f5, boolean bl2, ArrayList arrayList, am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f6 = y2.eo;
        float f7 = y2.ep;
        y y3 = null;
        am am3 = com.corrodinggames.rts.game.units.am.d(y2.r());
        if (!(am3 instanceof y)) {
            com.corrodinggames.rts.gameFramework.l.e("buildingBlockoutUnit not OrderableUnit is: " + am3.getClass().getName());
        } else {
            y3 = (y)am3;
        }
        boolean bl3 = false;
        l2.bL.b(f2, f3);
        f2 = l2.bL.T;
        f3 = l2.bL.U;
        float f8 = com.corrodinggames.rts.gameFramework.f.b(f2 += y2.cZ(), f3 += y2.da(), f4 += y2.cZ(), f5 += y2.da());
        float f9 = com.corrodinggames.rts.gameFramework.f.d(f2, f3, f4, f5);
        int n2 = 0;
        for (float f10 = 0.0f; f10 <= f8; f10 += (float)l2.bL.p) {
            float f11 = f2 + com.corrodinggames.rts.gameFramework.f.k(f9) * f10;
            float f12 = f3 + com.corrodinggames.rts.gameFramework.f.j(f9) * f10;
            l2.bL.b(f11 -= y2.cZ(), f12 -= y2.da());
            f11 = l2.bL.T;
            f12 = l2.bL.U;
            y2.eo = f11 += y2.cZ();
            y2.ep = f12 += y2.da();
            boolean bl4 = false;
            if (bl3 && y3 != null && (com.corrodinggames.rts.gameFramework.d.a.a(y2, y3) || y2.r(y3)) || bl4) continue;
            boolean bl5 = this.a(y2, f11, f12, bl2, false, am2);
            if (arrayList != null && bl5) {
                arrayList.add(new PointF(f11, f12));
            }
            if (bl5 && ++n2 >= 29) {
                return;
            }
            bl3 = true;
            if (y3 == null) continue;
            y3.eo = f11;
            y3.ep = f12;
        }
        y2.eo = f6;
        y2.ep = f7;
    }

    public boolean a(y y2, float f2, float f3, boolean bl2, boolean bl3, am am2) {
        float f4;
        y y3;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f5 = y2.eo;
        float f6 = y2.ep;
        y2.eo = f2;
        y2.ep = f3;
        boolean bl4 = y2.c(l2.bs);
        if (com.corrodinggames.rts.gameFramework.d.a.a(l2.bs, y2, this.ad)) {
            bl4 = false;
        }
        if (am2 != null && am2 != null && am2 instanceof y && !(y3 = (y)am2).aR()) {
            boolean bl5;
            f4 = com.corrodinggames.rts.gameFramework.f.a(y3.eo, y3.ep, y2.eo, y2.ep);
            float f7 = y3.f(y2.r());
            if (f7 > 800000.0f) {
                bl5 = true;
            } else {
                boolean bl6 = bl5 = f4 <= f7 * f7;
            }
            if (!bl5) {
                bl4 = false;
            }
        }
        boolean bl7 = y2.cp;
        y2.cp = true;
        y2.cs = bl4;
        y2.ct = !bl4;
        y2.cr = bl3;
        if (bl2) {
            l2.bO.k();
            l2.R();
            y2.d(0.0f);
            y2.c(0.0f);
            y2.a(0.0f, false);
            f4 = y2.m();
            if (f4 > 30.0f) {
                com.corrodinggames.rts.gameFramework.utility.y.a((am)y2, f4, true, true);
            }
            y2.cb();
            if (!bl3) {
                y2.N(-1);
            }
            l2.bO.l();
        }
        y2.eo = f5;
        y2.ep = f6;
        y2.cr = false;
        y2.cp = bl7;
        return bl4;
    }

    public void G() {
        this.l();
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.dq = true;
        l2.bY.c();
        if (l2.by < 1500 && l2.bS.f != null) {
            l2.dr = true;
        }
        this.k.a(0.0f);
        this.k.c();
    }

    public void H() {
        this.l();
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.dt = true;
        l2.bY.c();
        this.k.a(0.0f);
        this.k.c();
    }

    public void I() {
        this.l();
        this.aa = null;
        this.ac = this.p;
    }

    public void a(String string, Rect rect, Paint paint, Paint paint2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String[] stringArray = com.corrodinggames.rts.gameFramework.f.c(string, '\n');
        int n2 = 0;
        for (String string2 : stringArray) {
            Paint paint3 = n2 == 0 ? paint : paint2;
            int n3 = com.corrodinggames.rts.gameFramework.f.d.a(paint3);
            l2.bO.a(string2, (float)rect.d(), (float)(rect.b + n3 / 2 + n2 * n3), paint3);
            ++n2;
        }
    }

    public boolean a(s s2, boolean bl2, am am2, boolean bl3, boolean bl4) {
        return this.a(s2, bl2, am2, bl3, false, -1.0f, bl4);
    }

    public boolean a(s s2, boolean bl2, am am2, boolean bl3, boolean bl4, float f2, boolean bl5) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        String string;
        boolean bl6;
        float f3;
        String string2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        String string3 = null;
        boolean bl7 = false;
        boolean bl8 = true;
        if (com.corrodinggames.rts.gameFramework.l.aw()) {
            bl8 = false;
        }
        if (am2 != null && s2.l(am2)) {
            bl8 = false;
        }
        if (bl5) {
            bl8 = false;
        }
        boolean bl9 = false;
        boolean bl10 = false;
        if (com.corrodinggames.rts.gameFramework.f.a.a(s2)) {
            bl9 = true;
            bl10 = true;
        }
        if (this.c(s2)) {
            bl9 = true;
            string3 = this.bH;
            string2 = this.d(s2);
            if (string2 != null) {
                string3 = string2;
            }
        }
        if (!bl9 && bl3 && (string2 = this.d(s2)) != null) {
            string3 = string2;
        }
        if (string3 == null && (f3 = this.g.b(s2)) > 0.0f) {
            string3 = com.corrodinggames.rts.gameFramework.f.h(f3 / 1000.0f);
        }
        boolean bl11 = bl6 = (string = this.f(s2)) != null;
        if (string != null) {
            // empty if block
        }
        if (bl3 && string3 == null && string != null) {
            string3 = this.bI.b();
        }
        ae ae2 = new ae();
        ae2.d = this.aQ;
        ae2.e = this.aR;
        Paint paint = null;
        com.corrodinggames.rts.gameFramework.m.ag ag2 = null;
        if (bl6) {
            ag2 = this.aS;
        }
        ae2.a(true);
        s2.a(am2, ae2, paint, ag2);
        if (string3 != null) {
            ae2.a("\n" + string3, this.aS);
        }
        ae2.a(false);
        s2.a(am2, ae2);
        if (bl10) {
            ae2.b();
            ae2.a(this.bG, this.aR);
        }
        this.bv.a = n6 = 20;
        this.bv.c = n5 = (int)(l2.cl - l2.cq - (float)n6);
        boolean bl12 = l2.bQ.showActionInfoHoverNearMouse;
        int n7 = bl2 ? (int)(l2.cp - 40.0f) : 40;
        if (com.corrodinggames.rts.gameFramework.l.au() && f2 > 0.0f) {
            n7 = (int)f2;
            bl7 = true;
        }
        if (com.corrodinggames.rts.gameFramework.l.av() && bl12 && bl4) {
            n7 = (int)(l2.ag() - 40.0f);
        }
        this.bv.d = this.bv.b = n7;
        boolean bl13 = true;
        boolean bl14 = true;
        boolean bl15 = false;
        int n8 = 7;
        if (com.corrodinggames.rts.gameFramework.l.av()) {
            if (!bl12) {
                bl13 = false;
                bl14 = false;
            } else if (!bl4) {
                bl14 = false;
                bl13 = true;
                n8 = 20;
            }
        } else if (!bl2) {
            bl14 = false;
        }
        if (!com.corrodinggames.rts.gameFramework.l.av() || bl2 || !bl12 || !bl4) {
            // empty if block
        }
        if (string3 != null) {
            // empty if block
        }
        com.corrodinggames.rts.gameFramework.m.ag ag3 = this.aR;
        if (bl3) {
            ag3 = this.aS;
        }
        aj aj2 = ae2.a(this.bv.b(), bl13);
        float f4 = this.bv.d();
        this.bv.a = (int)(f4 - (float)(aj2.b.b() / 2));
        this.bv.c = (int)(f4 + (float)(aj2.b.b() / 2));
        this.bv.d = this.bv.b + aj2.b.c();
        if (bl13) {
            this.bv.a = (int)((float)this.bv.a - (float)n8 * l2.cj);
            this.bv.c = (int)((float)this.bv.c + (float)n8 * l2.cj);
        }
        if (bl14) {
            n4 = (int)((float)n5 - 7.0f * l2.cj - (float)this.bv.c);
            this.bv.a(n4, 0);
        }
        this.bw.a(this.bv);
        this.bw.b -= 20;
        this.bw.d += 15;
        n4 = -1;
        if (am2 != null) {
            n4 = s2.b(am2, true);
        }
        if (am2 != null && bl8 && n4 != -1) {
            this.bw.d = (int)((float)this.bw.d + 55.0f * l2.cj);
        }
        if ((float)this.bw.d > l2.cm) {
            int n9 = (int)(l2.cm - (float)this.bw.d);
            this.bv.a(0, n9);
            this.bw.a(0, n9);
        }
        as as2 = s2.i();
        if (!s2.D()) {
            as2 = null;
        }
        if (as2 != null && am2 != null) {
            this.bw.b = (int)((float)this.bw.b - 40.0f * l2.cj);
        }
        if (bl7) {
            n3 = -this.bv.c();
            this.bv.a(0, n3);
            this.bw.a(0, n3);
        }
        if (bl15) {
            float f5 = l2.cm - 30.0f;
            n2 = (int)(f5 - (float)this.bw.d);
            this.bw.a(0, n2);
            this.bv.a(0, n2);
        }
        if (this.bw.b < 0) {
            n3 = 0 - this.bw.b;
            this.bw.a(0, n3);
            this.bv.a(0, n3);
        }
        if ((float)this.bw.d > l2.cm - 20.0f) {
            float f6 = l2.cm - 20.0f;
            n2 = (int)(f6 - (float)this.bw.d);
            this.bw.a(0, n2);
            this.bv.a(0, n2);
        }
        l2.bO.b(this.bw, this.aP);
        l2.bO.b(this.bw, this.aL);
        if (bl9) {
            // empty if block
        }
        if (as2 != null && am2 != null) {
            float f7 = 30.0f * l2.cj;
            float f8 = 100.0f * l2.cj;
            com.corrodinggames.rts.game.units.ar.a(as2, this.bw.d(), (float)this.bw.b + 22.0f * l2.cj, this.Z, 0.0f, am2.bX, f7, f8, false, false, s2.t(), null);
        }
        aj2.a(this.bv.d(), this.bv.b);
        if (am2 != null && n4 != -1 && bl8) {
            int n10;
            int n11;
            float f9;
            int n12;
            int n13;
            float f10 = l2.cj * 0.5f;
            int n14 = (int)(60.0f * f10);
            float f11 = com.corrodinggames.rts.gameFramework.f.c.b(am2, s2, true);
            if (!bl9 || n4 > 0) {
                float f12;
                this.aK.b(-16777216);
                if (f11 != 0.0f) {
                    f12 = com.corrodinggames.rts.gameFramework.f.c(f11) * 0.5f - 0.4f;
                    f12 = com.corrodinggames.rts.gameFramework.f.b(f12, 0.0f, 1.0f);
                    n13 = f11 > 0.0f ? Color.a(110, 30, 240, 30) : Color.a(110, 240, 30, 30);
                    n12 = com.corrodinggames.rts.gameFramework.f.a(n13, this.aK.e(), f12);
                }
                f12 = (float)this.bw.d - 65.0f * f10 / 2.0f + (float)(com.corrodinggames.rts.gameFramework.f.d.b(this.aK) / 2);
                if ((double)f11 > 0.5) {
                    f12 += 1.0f;
                }
                if ((double)f11 < -0.5) {
                    f12 += -1.0f;
                }
                l2.bO.a("" + n4, (float)this.bw.d(), f12, this.aK);
            }
            boolean bl16 = false;
            n13 = 0;
            n12 = !bl9 && this.a(s2, true) ? 1 : 0;
            boolean bl17 = n4 > 0 && s2.d(am2, true);
            int n15 = (int)((float)this.bw.d() + 60.0f * f10);
            int n16 = (int)((float)this.bw.d - 65.0f * f10);
            this.by.a(n15, n16, n15 + n14, n16 + n14);
            Object object = n12 != 0 ? this.bf : this.bg;
            if (f11 > 0.0f) {
                f9 = com.corrodinggames.rts.gameFramework.f.c(f11) * 0.7f - 0.3f;
                f9 = com.corrodinggames.rts.gameFramework.f.b(f9, 0.0f, 1.0f);
                n11 = f11 > 0.0f ? Color.a(110, 210, 210, 210) : Color.a(110, 210, 110, 110);
                n10 = com.corrodinggames.rts.gameFramework.f.a(n11, ((Paint)object).e(), f9);
                object = this.bA;
                ((Paint)object).b(n10);
            }
            if ((double)f11 > 0.5) {
                this.by.a(0, 1);
            }
            l2.bO.a(this.bh, (float)this.by.a, (float)this.by.b, (Paint)object, 0.0f, f10);
            com.corrodinggames.rts.gameFramework.f.a(this.by, (float)this.by.b() * 0.8f);
            if (this.U && !this.T && !bl10 && this.by.b((int)this.x, (int)this.y)) {
                this.U = false;
                bl16 = true;
            }
            n15 = (int)((float)(this.bw.d() - n14) - 60.0f * f10);
            n16 = (int)((float)this.bw.d - 65.0f * f10);
            this.by.a(n15, n16, n15 + n14, n16 + n14);
            object = bl17 ? this.bf : this.bg;
            if (f11 < 0.0f) {
                f9 = com.corrodinggames.rts.gameFramework.f.c(f11) * 0.7f - 0.3f;
                f9 = com.corrodinggames.rts.gameFramework.f.b(f9, 0.0f, 1.0f);
                n11 = f11 > 0.0f ? Color.a(110, 210, 210, 210) : Color.a(110, 210, 110, 110);
                n10 = com.corrodinggames.rts.gameFramework.f.a(n11, ((Paint)object).e(), f9);
                object = this.bA;
                ((Paint)object).b(n10);
            }
            if ((double)f11 < -0.5) {
                this.by.a(0, 1);
            }
            l2.bO.a(this.bi, (float)this.by.a, (float)this.by.b, (Paint)object, 0.0f, f10);
            com.corrodinggames.rts.gameFramework.f.a(this.by, (float)this.by.b() * 0.8f);
            if (this.U && !this.T && this.by.b((int)this.x, (int)this.y)) {
                this.U = false;
                n13 = 1;
            }
            n15 = 1;
            if ((bl16 || n13 != 0) && s2.g()) {
                if (this.a(l2)) {
                    n15 = 5;
                }
                if (this.b(l2)) {
                    n15 = 10;
                }
            }
            if (bl16) {
                if (s2.g() && l2.bs.x() <= l2.bs.w()) {
                    this.b(this.g.al);
                }
                if (n12 != 0) {
                    l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5f);
                    com.corrodinggames.rts.gameFramework.f.c.a(am2, s2, false, true);
                }
                for (n16 = 0; n16 < n15; ++n16) {
                    object = this.w();
                    if (this.a(l2)) {
                        ((e)object).e = true;
                    }
                    this.a((e)object, s2);
                    ((e)object).a(s2.z());
                    this.a(s2, null, null, (e)object);
                }
            }
            if (n13 != 0) {
                if (bl17) {
                    com.corrodinggames.rts.gameFramework.f.c.a(am2, s2, true, true);
                    l2.bM.b(com.corrodinggames.rts.gameFramework.a.e.i, 0.5f);
                }
                for (n16 = 0; n16 < n15; ++n16) {
                    object = this.w();
                    this.a((e)object, s2);
                    ((e)object).g = true;
                    ((e)object).a(s2.z());
                }
            }
            if (!bl16 && n13 == 0 && this.U && !this.T && !this.bw.b((int)this.x, (int)this.y)) {
                return true;
            }
        }
        return !bl8 && com.corrodinggames.rts.gameFramework.l.au() && this.U && !this.T && !this.bw.b((int)this.x, (int)this.y);
    }

    public void a(Rect rect, Paint paint, Paint paint2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (bO) {
            l2.bO.a(this.bl, rect, paint2, rect.a, rect.b, 0, 0);
            if (paint != null) {
                int n2 = paint.f() + 0;
                if (n2 > 255) {
                    n2 = 255;
                }
                paint.c(n2);
            }
        }
        if (paint != null) {
            l2.bO.b(rect, paint);
        }
    }

    public void a(Rect rect, int n2, boolean bl2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.bF.b(n2);
        this.bF.a(Paint$Style.b);
        this.bF.a(1.0f);
        l2.bO.b(rect, this.bF);
        if (this.bN) {
            this.bF.b(Color.a(255, 116, 136, 160));
            int n3 = 1;
            if (bl2 && rect.b() > 100) {
                n3 = 2;
            }
            this.bF.a((float)n3);
            this.bz.a(rect);
            this.bz.d -= n3;
            this.bz.b += n3;
            this.bz.a += n3;
            this.bz.c -= n3;
            l2.bO.b(this.bz, this.bF);
        }
    }

    public void a(int n2, int n3, int n4, int n5, String string, int n6, Paint paint, boolean bl2, com.corrodinggames.rts.gameFramework.f.a.h h2, com.corrodinggames.rts.gameFramework.f.a.i i2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.bx.a(n2, n3, n2 + n4, n3 + n5);
        this.bF.b(n6);
        if (h2 != null) {
            h2.a(l2.bO, this.bx, i2);
        } else if (!bl2) {
            this.bF.a(Paint$Style.a);
            l2.bO.b(this.bx, this.bF);
        } else {
            this.a(this.bx, null, this.bF);
        }
        if (h2 == null) {
            int n7 = Color.a(255, 0, 0, 0);
            if (bO) {
                n7 = Color.a(100, 0, 0, 0);
            }
            this.a(this.bx, n7, false);
        }
        this.a(n2, n3, n4, n5, string, n6, paint);
    }

    public void a(int n2, int n3, int n4, int n5, String string, int n6, Paint paint) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.bx.a(n2, n3, n2 + n4, n3 + n5);
        if (com.corrodinggames.rts.gameFramework.l.aW) {
            l2.bO.a(string, (float)this.bx.d(), (float)(this.bx.e() + l2.bO.a(string, paint) / 2), paint);
        } else {
            l2.bO.a(string, (float)this.bx.d(), (float)this.bx.e() - (paint.l() + paint.m()) / 2.0f, paint);
        }
    }

    public boolean J() {
        return !this.T;
    }

    public boolean a(int n2, int n3, int n4, int n5, String string, i i2, boolean bl2, int n6) {
        return this.a(n2, n3, n4, n5, string, i2, bl2, n6, this.aC, false, null);
    }

    public boolean b(int n2, int n3, int n4, int n5, String string, i i2, boolean bl2, int n6) {
        return this.a(n2, n3, n4, n5, string, i2, bl2, n6, this.aC, true, null);
    }

    public boolean a(int n2, int n3, int n4, int n5, String string, i i2, boolean bl2, int n6, Paint paint, com.corrodinggames.rts.gameFramework.f.a.h h2) {
        return this.a(n2, n3, n4, n5, string, i2, bl2, n6, paint, false, h2);
    }

    public boolean a(int n2, int n3, int n4, int n5, String string, i i2, boolean bl2, int n6, Paint paint, boolean bl3, com.corrodinggames.rts.gameFramework.f.a.h h2) {
        boolean bl4 = this.a(n2, n3, n4, n5, i2);
        boolean bl5 = this.a(n2, n3, n4, n5, i2, bl2);
        com.corrodinggames.rts.gameFramework.f.a.i i3 = com.corrodinggames.rts.gameFramework.f.a.i.a;
        if (bl4) {
            i3 = com.corrodinggames.rts.gameFramework.f.a.i.b;
        }
        this.a(n2, n3, n4, n5, string, n6, paint, bl3, h2, i3);
        return bl5;
    }

    public void a(Rect rect) {
        if (rect.b((int)this.z, (int)this.A)) {
            this.L = true;
            this.M = true;
            if (this.V) {
                this.K = true;
            }
        }
    }

    public void a(float f2, float f3, float f4, float f5) {
        this.cc.a((int)f2, (int)f3, (int)(f2 + f4), (int)(f3 + f5));
        this.a(this.cc);
    }

    public boolean a(int n2, int n3, int n4, int n5, i i2, boolean bl2) {
        this.a((float)n2, (float)n3, (float)n4, n5);
        this.bx.a(n2, n3, n2 + n4, n3 + n5);
        return (bl2 && this.I || this.U) && this.bx.b((int)this.x, (int)this.y);
    }

    public boolean a(int n2, int n3, int n4, int n5, i i2) {
        this.bx.a(n2, n3, n2 + n4, n3 + n5);
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return com.corrodinggames.rts.gameFramework.l.aw() && l2.bQ.mouseSupport && this.bx.b((int)l2.af(), (int)l2.ag());
    }

    public boolean b(int n2, int n3, int n4, int n5, i i2) {
        this.bx.a(n2, n3, n2 + n4, n3 + n5);
        return this.V && this.bx.b((int)this.x, (int)this.y);
    }

    public float r(am am2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (am2.cH < l2.bA && am2.cH + 200 > l2.bA) {
            float f2 = 1.0f - (float)(l2.bA - am2.cH) / 200.0f;
            return f2 * 6.0f;
        }
        return com.corrodinggames.rts.gameFramework.l.B().dx;
    }

    public void a(com.corrodinggames.rts.gameFramework.f.a.f f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        f2.u_();
        f2.c(l2.co);
        f2.d(l2.cp);
        this.s.a(f2);
    }

    public static void K() {
        ++cd;
        ce = true;
    }

    static {
        cd = 1;
    }
}
