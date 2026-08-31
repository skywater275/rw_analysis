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
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.gameFramework.SettingsEngine;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.f.a;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.aj;
import com.corrodinggames.rts.gameFramework.f.al;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.f.ap;
import com.corrodinggames.rts.gameFramework.f.c;
import com.corrodinggames.rts.gameFramework.f.d;
import com.corrodinggames.rts.gameFramework.f.f;
import com.corrodinggames.rts.gameFramework.f.h;
import com.corrodinggames.rts.gameFramework.f.i;
import com.corrodinggames.rts.gameFramework.f.j;
import com.corrodinggames.rts.gameFramework.f.k;
import com.corrodinggames.rts.gameFramework.f.m;
import java.util.ArrayList;
import java.util.Iterator;

public final class g extends bq {

   public static boolean a = false;
   public boolean b = true;
   public boolean c = false;
   public float d = 0.0F;
   public boolean e = false;
   com.corrodinggames.rts.game.units.h f;
   public a g;
   public m h;
   public ap i;
   public k j;
   public f k;
   com.corrodinggames.rts.game.units.a.e l = new com.corrodinggames.rts.game.units.a.e();
   com.corrodinggames.rts.game.units.a.f m = new com.corrodinggames.rts.game.units.a.f();
   com.corrodinggames.rts.game.units.a.i n = new com.corrodinggames.rts.game.units.a.i();
   com.corrodinggames.rts.game.units.a.d o = new com.corrodinggames.rts.game.units.a.d();
   public com.corrodinggames.rts.game.units.a.j p = new com.corrodinggames.rts.game.units.a.j();
   com.corrodinggames.rts.game.units.a.r q = new com.corrodinggames.rts.game.units.a.r();
   com.corrodinggames.rts.game.units.a.q r = new com.corrodinggames.rts.game.units.a.q();
   com.corrodinggames.rts.gameFramework.f.a.l s = new com.corrodinggames.rts.gameFramework.f.a.a();
   boolean t = false;
   public boolean u = false;
   double v;
   float w = 0.0F;
   public float x = 0.0F;
   public float y = 0.0F;
   float z = 40.0F;
   float A = 40.0F;
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
   float N = 0.0F;
   float O = 0.0F;
   float P = 0.0F;
   float Q = 0.0F;
   float R = 0.0F;
   float S = 0.0F;
   boolean T = false;
   boolean U = false;
   boolean V = false;
   public com.corrodinggames.rts.game.units.am W;
   public float X;
   public int Y;
   public float Z;
   public com.corrodinggames.rts.game.units.am aa;
   public final boolean ab = true;
   public com.corrodinggames.rts.game.units.a.s ac;
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
   public float aU = 0.0F;
   public float aV = 0.0F;
   public float aW = 0.0F;
   int aX;
   public float aY = 0.0F;
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
   public com.corrodinggames.rts.gameFramework.utility.u bY = new com.corrodinggames.rts.gameFramework.utility.u();
   public com.corrodinggames.rts.gameFramework.utility.u bZ = new com.corrodinggames.rts.gameFramework.utility.u();
   public static com.corrodinggames.rts.game.units.am ca;
   Paint cb = new Paint();
   Rect cc = new Rect();
   static int cd = 1;
   static boolean ce;


   public boolean a() {
      if(com.corrodinggames.rts.gameFramework.l.aw()) {
         return false;
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         return var1.bQ.useCircleSelect;
      }
   }

   float b() {
      return Math.min(this.w * 2.5F, 290.0F) + 10.0F;
   }

   float c() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      float var2 = 0.7F;
      if(com.corrodinggames.rts.gameFramework.l.av()) {
         var2 = 0.9F;
      }

      if(var1.cX < 1.0F) {
         float var3 = var1.cX;
         if((double)var3 < 0.4D) {
            var3 = 0.4F;
         }

         var2 *= var3;
      }

      return var2;
   }

   public void a(String var1, int var2) {
      this.g.a(var1, var2);
   }

   public void b(String var1, int var2) {
      this.g.b(var1, var2);
   }

   public void a(String var1) {
      this.g.a(var1);
   }

   public void b(String var1) {
      this.g.a(var1, 100);
   }

   public void c(String var1) {
      this.g.a(var1, 50);
   }

   public void d(String var1) {
      this.g.a(var1, 5);
   }

   public void d() {
      this.U = false;
      this.V = false;
      this.I = false;
   }

   public boolean a(float var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      return bO && !this.g.ap?var3.bW.c(var1, var2) == null:var1 < var3.cl - var3.cq;
   }

   public void e() {
      if(this.g != null) {
         this.g.a();
      }

   }

   public void a(boolean var1) {
      if(var1) {
         this.g.j();
      } else {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         this.g.k();
         this.l();
         this.u = false;
         this.c = false;
         this.d = 0.0F;
         this.bM.clear();
         if(!var1) {
            var2.bt = 1.0F;
            var2.bw = false;
            var2.bv = false;
            var2.bl = false;
            var2.bn = false;
         }

         if(var2.N() && var2.P()) {
            var2.bv = var2.bX.p;
         }

         an.a();
         K();
      }
   }

   public void f() {
      bO = false;
      bP = false;
      bQ = false;
      if(com.corrodinggames.rts.gameFramework.l.av()) {
         bO = true;
         bP = true;
         a = true;
         bQ = true;
      }

      if(com.corrodinggames.rts.gameFramework.l.aY) {
         bO = true;
         bP = true;
         bQ = true;
      }

      if(com.corrodinggames.rts.gameFramework.l.at() && !com.corrodinggames.rts.gameFramework.l.B().bQ.classicInterface) {
         bO = true;
         bP = true;
         bQ = true;
      }

   }

   public void a(Context var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(com.corrodinggames.rts.gameFramework.l.C()) {
         this.bN = true;
      }

      this.f();
      this.bG = com.corrodinggames.rts.gameFramework.h.a.a("gui.notAvailableInDemoText", new Object[0]);
      this.bH = "Locked";
      this.bI = bb.b("gui.notEnoughResources");
      this.bJ = com.corrodinggames.rts.gameFramework.h.a.a("gui.cannotPlace.general", new Object[0]);
      this.bK = com.corrodinggames.rts.gameFramework.h.a.a("gui.cannotPlace.needsResourcePool", new Object[0]);
      this.bL = com.corrodinggames.rts.gameFramework.h.a.a("gui.cannotPlace.needsWater", new Object[0]);
      this.g = new a(var2, this);
      this.e();
      this.h = new m(var2, this);
      this.i = new ap(var2);
      this.j = new k(var2, this);
      this.k = new f();
      if(com.corrodinggames.rts.gameFramework.l.au()) {
         this.b = true;
      }

      this.ba = var2.bO.a(R$drawable.button_no);
      this.bb = var2.bO.a(R$drawable.button_yes);
      this.bc = var2.bO.a(R$drawable.button_more);
      this.bf = new Paint();
      this.bf.d(true);
      this.bg = new Paint();
      this.bg.d(true);
      this.bg.a(40, 255, 255, 255);
      this.bh = var2.bO.a(R$drawable.button_add);
      this.bi = var2.bO.a(R$drawable.button_subtract);
      this.bj = var2.bO.a(R$drawable.icon_rally);
      this.bn = var2.bO.a(R$drawable.rounded_glow_button);
      this.bo = var2.bO.a(R$drawable.rounded_white_button);
      this.bp = new com.corrodinggames.rts.gameFramework.f.a.e(this.bn, 32, 27);
      this.bq = new com.corrodinggames.rts.gameFramework.f.a.e(var2.bO.a(R$drawable.rounded_glow_highlight_button), 32, 27);
      this.br = this.bp.a();
      this.br.v = this.bq;
      this.bs = new com.corrodinggames.rts.gameFramework.f.a.e(var2.bO.a(R$drawable.rounded_dark_box), 32, 27);
      this.bt = new com.corrodinggames.rts.gameFramework.f.a.e(var2.bO.a(R$drawable.rounded_dark_box_titled), 36, 36);
      this.bt.r = new com.corrodinggames.rts.gameFramework.f.a.e(var2.bO.a(R$drawable.rounded_shadow), 36, 36);
      this.bt.f = true;
      this.bu = new com.corrodinggames.rts.gameFramework.f.a.e(var2.bO.a(R$drawable.rounded_green), 36, 36);
      this.bu.r = this.bt.r;
      this.bu.u = 20;
      this.bk = var2.bO.a(R$drawable.icon_upgrade);
      this.bl = var2.bO.a(R$drawable.metal_dark, false);
      this.bm = var2.bO.a(R$drawable.touch_indicator, false);
      com.corrodinggames.rts.gameFramework.f.a.h.b();
      this.bE.a(145, 0, 175, 0);
      this.bE.a(6.0F);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.bE);
      this.bD.a(true);
      this.au = new Paint();
      this.av = new com.corrodinggames.rts.gameFramework.m.ag();
      this.av.a(255, 0, 240, 0);
      this.av.a(true);
      this.av.c(true);
      this.av.a(Typeface.a(Typeface.c, 1));
      var2.a(this.av, 20.0F);
      this.av.a(Paint$Align.a);
      this.ay = new com.corrodinggames.rts.gameFramework.m.ag();
      this.ay.a(255, 0, 240, 0);
      this.ay.a(true);
      this.ay.c(true);
      this.ay.a(Typeface.a(Typeface.c, 1));
      var2.a(this.ay, 18.0F);
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
      var2.a(this.az, 12.0F);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.az);
      this.aC = new com.corrodinggames.rts.gameFramework.m.ag();
      if(this.bN) {
         this.aC.a(255, 240, 240, 240);
      } else {
         this.aC.a(255, 30, 240, 30);
      }

      this.aC.a(Paint$Align.b);
      this.aC.c(true);
      this.aC.a(true);
      var2.a(this.aC, 12.0F);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aC);
      com.corrodinggames.rts.gameFramework.l.e("smallTextPaint size: " + this.aC.k());
      this.aB = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aB.a(this.aC);
      var2.a(this.aB, 10.0F);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aB);
      this.aA = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aA.a(this.aC);
      var2.a(this.aA, 8.0F);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aA);
      this.aD = new com.corrodinggames.rts.gameFramework.m.ag();
      if(this.bN) {
         this.aD.a(255, 240, 240, 240);
      } else {
         this.aD.a(255, 30, 240, 30);
      }

      this.aD.a(Paint$Align.b);
      this.aD.c(true);
      this.aD.a(true);
      var2.a(this.aD, 20.0F);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aD);
      this.aE = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aE.a(255, 30, 240, 30);
      this.aE.a(Paint$Align.b);
      this.aE.c(true);
      this.aE.a(true);
      var2.a(this.aE, 20.0F);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aE);
      this.aI = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aI.a(150, 20, 20, 20);
      var2.a(this.aI);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aI);
      this.aF = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aF.a(this.aD);
      this.aF.a(255, 128, 0, 0);
      var2.a(this.aF, 14.0F);
      this.aF.a(Paint$Align.b);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aF);
      this.aG = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aG.a(this.aF);
      this.aG.a(255, 220, 222, 49);
      this.aH = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aH.a(this.aD);
      var2.a(this.aH, 12.0F);
      this.aH.a(125, 230, 230, 230);
      this.aH.a(Paint$Align.b);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aH);
      this.aQ = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aQ.b(-16777216);
      this.aQ.a(true);
      this.aQ.c(true);
      this.aQ.a(Typeface.a(Typeface.c, 0));
      var2.a((Paint)this.aQ, 14.0F);
      this.aR = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aR.a((Paint)this.aQ);
      this.aR.a(Typeface.a(Typeface.c, 1));
      var2.a((Paint)this.aR, 16.0F);
      this.aS = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aS.a((Paint)this.aR);
      this.aS.b(Color.a(232, 63, 80));
      var2.a((Paint)this.aS, 16.0F);
      this.aK = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aK.b(-16777216);
      this.aK.a(Paint$Align.b);
      this.aK.a(true);
      this.aK.c(true);
      this.aK.a(Typeface.a(Typeface.c, 0));
      var2.a(this.aK, 20.0F);
      this.aL = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aL.b(-1);
      this.aL.c(160);
      if(com.corrodinggames.rts.gameFramework.l.av()) {
         this.aL.c(140);
      }

      var2.a(this.aL);
      this.aM = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aM.b(-16777216);
      this.aM.c(210);
      var2.a(this.aM);
      this.aP = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aP.b(-7829368);
      this.aP.c(240);
      this.aP.a(Paint$Style.b);
      this.aP.a(1.0F);
      var2.a(this.aP);
      this.aN = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aN.b(-16711936);
      this.aN.c(80);
      this.aN.a(Paint$Style.a);
      this.aN.a(4.0F);
      var2.a(this.aN);
      this.aO = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aO.b(Color.a(120, 235, 167, 49));
      this.aO.a(Paint$Style.a);
      this.aO.a(8.0F);
      var2.a(this.aO);
      this.aJ = new com.corrodinggames.rts.gameFramework.m.ag();
      this.aJ.c(true);
      this.aJ.a(true);
      var2.a(this.aJ, 12.0F);
      com.corrodinggames.rts.gameFramework.m.ag.b(this.aJ);
   }

   public void g() {
      this.h.b();
      this.i.b();
      this.bX = false;
   }

   public void a(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.v += (double)var1;
      this.aU = com.corrodinggames.rts.gameFramework.f.a(this.aU, var1);
      this.aV = com.corrodinggames.rts.gameFramework.f.a(this.aV, var1);
      this.aY = com.corrodinggames.rts.gameFramework.f.a(this.aY, 0.08F * var1);
      this.aW = com.corrodinggames.rts.gameFramework.f.a(this.aW, var1);
      this.E += var1;
      this.aT += 0.05F * var1;
      if(this.aT > 1.0F) {
         --this.aT;
         if(this.aT > 1.0F) {
            this.aT = 0.0F;
         }
      }

      var2.dx = 4.0F * this.aY;
      float var3 = 1.0F * var1;
      float var4;
      float var5;
      if(!this.I) {
         var4 = this.R * var1;
         var5 = this.S * var1;
         var4 = com.corrodinggames.rts.gameFramework.f.g(80.0F, var4);
         var5 = com.corrodinggames.rts.gameFramework.f.g(80.0F, var5);
         var2.cy += var4;
         var2.cz += var5;
      } else {
         var3 *= 4.0F;
      }

      var4 = com.corrodinggames.rts.gameFramework.f.b(0.0F, 0.0F, this.R, this.S);
      var5 = com.corrodinggames.rts.gameFramework.f.d(0.0F, 0.0F, this.R, this.S);
      if(var4 > 30.0F) {
         var4 = 30.0F;
      }

      var4 = com.corrodinggames.rts.gameFramework.f.a(var4, var3);
      this.R = com.corrodinggames.rts.gameFramework.f.k(var5) * var4;
      this.S = com.corrodinggames.rts.gameFramework.f.j(var5) * var4;
      this.aZ = false;
      this.I = var2.ac() && var2.dM[0] && this.aU == 0.0F;
      if(this.aV != 0.0F) {
         if(!this.I) {
            this.aV = 0.0F;
         }

         this.I = false;
         this.H = false;
      }

      boolean var6 = false;
      if(this.aW > 0.0F) {
         var6 = true;
      }

      if(var2.ac() && var2.ae() > 1) {
         var6 = true;
         this.aW = 4.0F;
      }

      if(var6) {
         this.I = false;
         this.H = false;
         this.T = false;
         this.w = 0.0F;
      }

      this.M = false;
      this.L = var2.af() > var2.cF;
      this.U = !this.I && this.H;
      this.V = this.I && !this.H;
      if(com.corrodinggames.rts.gameFramework.l.av() && var2.bQ.mouseSupport) {
         this.z = var2.af();
         this.A = var2.ag();
      }

      if(!this.I && !this.U) {
         this.D = false;
      }

      float var8;
      float var9;
      if(this.I) {
         this.w += var1;
         this.x = var2.b(0);
         this.y = var2.c(0);
         this.z = this.x;
         this.A = this.y;
         this.B = var2.d(0);
         this.C = this.a(this.x, this.y);
         boolean var7 = false;
         if(this.C && !this.H) {
            if(this.E < 30.0F) {
               var8 = com.corrodinggames.rts.gameFramework.f.a(this.F, this.G, this.x, this.y);
               var9 = 10.0F * var2.cj;
               if(com.corrodinggames.rts.gameFramework.l.au()) {
                  var9 = (float)((double)var9 * 1.5D);
               }

               if(var8 < var9 * var9) {
                  var7 = true;
               }
            }

            this.E = 0.0F;
            this.F = this.x;
            this.G = this.y;
         }

         if(var7) {
            this.D = true;
         }

         if(!this.H) {
            this.T = false;
            this.N = this.x;
            this.O = this.y;
            this.P = this.x;
            this.Q = this.y;
            this.J = var2.bW.c(this.x, this.y) != null;
            this.K = false;
            if(!this.J) {
               this.K = this.x > var2.cF;
            }
         }

         this.H = true;
      }

      float var16;
      boolean var18;
      if(this.I && (this.w <= 20.0F || !this.a())) {
         var16 = com.corrodinggames.rts.gameFramework.f.a(this.N, this.O, this.x, this.y);
         if(!this.J) {
            var8 = 30.0F * var2.cj;
            if(com.corrodinggames.rts.gameFramework.l.av() && var2.bQ.mouseSupport && var2.e(3)) {
               var8 = 0.0F;
            }

            if(!this.T && var16 > var8 * var8) {
               var18 = false;
               byte var10 = 1;
               if(var2.bQ.mouseOrders == 2) {
                  var10 = 2;
               }

               if((!var2.bQ.mouseSupport || this.B != var10) && !this.c(var2)) {
                  var18 = true;
               }

               if(var18) {
                  this.T = true;
               }

               this.P = this.x;
               this.Q = this.y;
            }
         }
      }

      if(com.corrodinggames.rts.gameFramework.l.av() && !var2.aq && var2.ao != null && (var2.ao.f() || com.corrodinggames.rts.gameFramework.l.aR) && (!this.I || this.g.c)) {
         var16 = 24.0F * var2.bQ.edgeScrollSpeed / var2.cX;
         var8 = var2.cy;
         var9 = var2.cz;
         float var21 = 0.0F;
         float var11 = 0.0F;
         if(this.z <= 1.0F) {
            var21 -= var16 * var1;
         }

         if(this.z >= var2.cl - 1.0F) {
            var21 += var16 * var1;
         }

         if(this.A <= 1.0F) {
            var11 -= var16 * var1;
         }

         if(this.A >= var2.cm - 1.0F) {
            var11 += var16 * var1;
         }

         var2.cy += var21;
         var2.cz += var11;
         var2.Q();
         this.g.y.a -= (var2.cy - var8) * var2.cX;
         this.g.y.b -= (var2.cz - var9) * var2.cX;
      }

      com.corrodinggames.rts.gameFramework.ac var17 = var2.bT;
      if(var2.bQ.keyboardSupport) {
         if(var2.E()) {
            var8 = 12.0F * var2.bQ.scrollSpeed;
            if(var17.p.b()) {
               var2.cy -= var8 * var1;
            }

            if(var17.q.b()) {
               var2.cy += var8 * var1;
            }

            if(var17.n.b()) {
               var2.cz -= var8 * var1;
            }

            if(var17.o.b()) {
               var2.cz += var8 * var1;
            }

            if(var17.r.b()) {
               var2.cV += 0.1F;
            }

            if(var17.s.b()) {
               var2.cV -= 0.1F;
            }
         }

         if(var17.y.a()) {
            this.l();
            this.y();
         }

         if(var17.z.a()) {
            this.i.d();
         }

         Iterator var19;
         com.corrodinggames.rts.gameFramework.w var22;
         com.corrodinggames.rts.game.units.y var25;
         if(var17.A.a()) {
            this.l();
            this.y();
            var19 = com.corrodinggames.rts.gameFramework.w.er.iterator();

            while(var19.hasNext()) {
               var22 = (com.corrodinggames.rts.gameFramework.w)var19.next();
               if(var22 instanceof com.corrodinggames.rts.game.units.y) {
                  var25 = (com.corrodinggames.rts.game.units.y)var22;
                  if(!var25.bV && var25.bX == var2.bs && var25.l() && !var25.ak() && var25.aS() && var25.cN == null) {
                     this.j(var25);
                  }
               }
            }
         }

         if(var17.B.a()) {
            this.l();
            this.y();
            var19 = com.corrodinggames.rts.gameFramework.w.er.iterator();

            while(var19.hasNext()) {
               var22 = (com.corrodinggames.rts.gameFramework.w)var19.next();
               if(var22 instanceof com.corrodinggames.rts.game.units.y) {
                  var25 = (com.corrodinggames.rts.game.units.y)var22;
                  if(var25.bX == var2.bs && var25.r() == com.corrodinggames.rts.game.units.ar.e && !var25.bV && var25.cN == null) {
                     this.j(var25);
                     var2.b(var25.eo, var25.ep);
                  }
               }
            }
         }

         if(var17.C.a()) {
            al.a(this.bM, al.a, al.b);
         }

         if(var17.D.a()) {
            al.a(this.bM, al.c, (al)null);
         }

         if(var17.E.a()) {
            al.a(this.bM, al.d, (al)null);
         }

         if(var17.F.a()) {
            al.a(this.bM, al.e, (al)null);
         }

         if(var17.G.a()) {
            al.a(this.bM, al.f, (al)null);
         }

         if(var17.x.a()) {
            this.g.a(12);
         }

         if(var17.N.a() && this.B() && this.C()) {
            this.l();
            this.aa = null;
            this.ac = this.l;
            return;
         }

         if(var17.P.a() && this.A()) {
            this.l();
            this.aa = null;
            this.ac = this.m;
            return;
         }

         if(var17.Q.a() && this.C()) {
            this.l();
            this.aa = null;
            this.ac = this.n;
            return;
         }

         if(var17.O.a()) {
            this.v();
         }

         if(var17.v.a()) {
            this.I();
         }

         if(var17.t.a() && var2.N()) {
            com.corrodinggames.rts.gameFramework.l.e("showing send chat");
            this.g.a(13);
         }

         if(var17.u.a() && var2.N()) {
            com.corrodinggames.rts.gameFramework.l.e("showing send team chat");
            this.g.a(16);
         }

         if(!var2.P() && !var2.cb.j()) {
            if(var17.L.a() && var2.bX.C && var2.bX.aW) {
               var2.bX.e(!var2.bX.al);
            }
         } else {
            if(var17.L.a()) {
               if(var2.bt != 0.0F) {
                  if(!var2.cb.j()) {
                     com.corrodinggames.rts.gameFramework.j.ad.a((String)null, "Game paused");
                  }

                  var2.bt = 0.0F;
               } else {
                  var2.bt = 1.0F;
               }
            }

            boolean var20 = var17.J.a();
            var18 = var17.K.a();
            if(var20 || var18) {
               boolean var27;
               if(var20) {
                  var27 = var2.bt > 1.0F;
                  if(var2.bt < 2.0F) {
                     var2.bt = (float)((double)var2.bt - 0.25D);
                  } else if(var2.bt < 6.0F) {
                     var2.bt = (float)((double)var2.bt - 0.5D);
                  } else if(var2.bt < 16.0F) {
                     var2.bt -= 2.0F;
                  } else {
                     var2.bt -= 4.0F;
                  }

                  if(var2.bt < 0.0F) {
                     var2.bt = 0.0F;
                  }

                  if(var27 && var2.bt < 1.0F) {
                     var2.bt = 1.0F;
                  }
               } else if(var18) {
                  var27 = var2.bt < 1.0F;
                  if(var2.bt < 2.0F) {
                     var2.bt = (float)((double)var2.bt + 0.25D);
                  } else if(var2.bt < 6.0F) {
                     var2.bt = (float)((double)var2.bt + 0.5D);
                  } else if(var2.bt < 16.0F) {
                     var2.bt += 2.0F;
                  } else {
                     var2.bt += 4.0F;
                  }

                  if(var2.cb.j()) {
                     if(var2.bt > 64.0F) {
                        var2.bt = 64.0F;
                     }
                  } else if(var2.bt > 5.0F) {
                     var2.bt = 5.0F;
                  }

                  if(var27 && var2.bt > 1.0F) {
                     var2.bt = 1.0F;
                  }
               }

               if(!var2.cb.j()) {
                  com.corrodinggames.rts.gameFramework.j.ad.a((String)null, "Game speed now: " + var2.bt);
               }
            }
         }

         var2.cT = com.corrodinggames.rts.gameFramework.f.a(var2.cT, var1);
         if(var17.Y.a()) {
            var2.cT = 180.0F;
         }

         if(var2.bv && var17.ab.a()) {
            var2.bl = !var2.bl;
            com.corrodinggames.rts.gameFramework.l.e("debugTempMode now: " + var2.bl);
            this.b("debug: " + var2.bl);
         }

         if(var2.bv && var2.bl && var17.ac.a()) {
            com.corrodinggames.rts.game.a.a.as = !com.corrodinggames.rts.game.a.a.as;
            this.b("AI debug view: " + com.corrodinggames.rts.game.a.a.as);
         }

         if(var2.bv && var2.bl && var17.ad.a()) {
            com.corrodinggames.rts.gameFramework.n.f.a = !com.corrodinggames.rts.gameFramework.n.f.a;
            this.b("Map debug: " + com.corrodinggames.rts.gameFramework.n.f.a);
         }

         if(var2.P() || var2.cb.j()) {
            if(var2.bv) {
               if(var17.V.a()) {
                  var2.bp = !var2.bp;
               }

               if(var17.W.a()) {
                  if(var2.bt == 1.0F) {
                     var2.bt = 0.1F;
                  } else {
                     var2.bt = 1.0F;
                  }
               }

               if(var17.X.a()) {
                  com.corrodinggames.rts.gameFramework.l.e("Adding test popup");
                  var2.bX.U();
               }

               if(var17.Z.a()) {
                  var2.bw = !var2.bw;
               }

               if(var17.aa.a()) {
                  var19 = com.corrodinggames.rts.gameFramework.w.er.iterator();

                  while(var19.hasNext()) {
                     var22 = (com.corrodinggames.rts.gameFramework.w)var19.next();
                     if(var22 instanceof com.corrodinggames.rts.game.units.y) {
                        var25 = (com.corrodinggames.rts.game.units.y)var22;
                        if(var25.cG) {
                           var25.U();
                        }
                     }
                  }
               }
            }

            if(var17.U.a()) {
               var2.bv = !var2.bv;
               if(var2.bv) {
                  this.y();
               }
            }
         }
      }

      if(var2.bv && !var2.P() && !var2.cb.j()) {
         var2.bv = false;
      }

      if(var2.bv) {
         if(this.f != null && (this.f.ej || this.f.bV)) {
            this.f = null;
         }

         if(this.f == null) {
            com.corrodinggames.rts.gameFramework.l.e("Creating new debug editor");
            this.f = new com.corrodinggames.rts.game.units.h(false);
            this.f.b(var2.bs);
         }

         if(this.q() == 0) {
            this.y();
            this.j(this.f);
         }

         if(var2.bQ.liveReloading && var2.bx % 100 == 0 && !var2.cb.i()) {
            com.corrodinggames.rts.game.units.custom.ag.c();
         }
      } else {
         if(this.f != null && (this.f.ej || this.f.bV)) {
            this.f = null;
         }

         if(this.f != null && !var2.cb.j()) {
            this.h();
         }
      }

      if(this.T) {
         if(this.K) {
            this.g.ao = this.Q - this.y;
         } else {
            byte var23 = 1;
            if(var2.bQ.mouseOrders == 2) {
               var23 = 2;
            }

            if((!var2.bQ.mouseSupport || this.B != var23) && !this.c(var2)) {
               SettingsEngine var26 = var2.bQ;
               double var28 = (double)(this.P - this.x);
               double var12 = (double)(this.Q - this.y);
               float var14 = com.corrodinggames.rts.gameFramework.f.b(0.0F, 0.0F, (float)var28, (float)var12);
               var28 = var28 * (double)var26.scrollSpeed / (double)var2.cX;
               var12 = var12 * (double)var26.scrollSpeed / (double)var2.cX;
               if(var1 != 0.0F && (double)var14 > 50.0D * (double)var1) {
                  float var15 = 0.7F;
                  if(com.corrodinggames.rts.gameFramework.l.av()) {
                     var15 = 1.7F;
                  }

                  this.R = (float)(var28 * (double)var15);
                  this.S = (float)(var12 * (double)var15);
               }

               var2.cy = (float)((double)var2.cy + var28 * 2.0D);
               var2.cz = (float)((double)var2.cz + var12 * 2.0D);
            }
         }

         this.P = this.x;
         this.Q = this.y;
      }

      if(var2.bQ.mouseSupport && (this.bS.a != (int)var2.af() || this.bS.b != (int)var2.ag())) {
         this.bS.a = (int)var2.af();
         this.bS.b = (int)var2.ag();
         this.s.b(this.bS);
      }

      if(this.U && this.J()) {
         com.corrodinggames.rts.gameFramework.f.a.c var24 = com.corrodinggames.rts.gameFramework.f.a.c.a((int)this.z, (int)this.A);
         this.s.b(var24);
      }

      this.s.b(var1);
      this.k.a(var1);
   }

   public void h() {
      if(this.f != null) {
         this.l(this.f);
         this.f.ci();
         this.f = null;
      }

   }

   public com.corrodinggames.rts.game.units.h i() {
      return this.f;
   }

   public void a(com.corrodinggames.rts.game.units.h var1) {
      this.f = var1;
   }

   public boolean a(com.corrodinggames.rts.gameFramework.l var1) {
      return !var1.bQ.keyboardSupport?false:var1.c(59, 60);
   }

   public boolean b(com.corrodinggames.rts.gameFramework.l var1) {
      return !var1.bQ.keyboardSupport?false:var1.c(113, 114);
   }

   public boolean c(com.corrodinggames.rts.gameFramework.l var1) {
      return !var1.bQ.keyboardSupport?false:var1.c(57, 58);
   }

   public void b(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.Z += 0.2F * var1;
      if(this.Z > 360.0F) {
         this.Z -= 360.0F;
      }

      this.bx.a((int)(var2.cl - var2.cq), 0, (int)var2.cl, (int)var2.cm);
      if(!bO) {
         if(this.bN) {
            this.bA.a();
            this.bA.b(Color.a(255, 33, 40, 52));
            this.bA.a(Paint$Style.a);
            var2.bO.b(this.bx, this.bA);
         } else {
            var2.bO.a(this.bl, this.bx, (Paint)null);
         }

         this.bA.a();
         this.bA.b(Color.a(255, 0, 0, 0));
         this.bA.a(Paint$Style.b);
         var2.bO.b(this.bx, this.bA);
      }

      this.cf = 0;
      this.ch = 0;
      this.cg = 0;
      this.ck = this.cl;
      this.cl = 0;
      com.corrodinggames.rts.game.units.y var3;
      if(!var2.cb.j() && (var2.bs == null || !var2.bs.b())) {
         var3 = this.t();
         if(var2.bs != null && var2.bs != com.corrodinggames.rts.game.n.i && !var2.bs.b() && !var2.cb.j()) {
            this.a(var2, var2.bs, false, true);
         }

         if(var3 != null && var2.bs != var3.bX && this.m(var3)) {
            this.a(var2, var3.bX, true, true);
         }
      } else {
         var3 = this.s();
         if(var3 != null) {
            this.a(var2, var3.bX, false, true);
         }
      }

      if(var2.bv && !var2.cb.j()) {
         String var8 = "";
         if(var2.bv) {
            var8 = var8 + "Editor Active\n";
         }

         if(var2.bt != 1.0F) {
            var8 = var8 + "Game Speed: " + var2.bt + "x\n";
         }

         if(var2.bw) {
            var8 = var8 + "Invincible Units\n";
         }

         boolean var4 = false;
         Iterator var5 = com.corrodinggames.rts.game.n.c().iterator();

         while(var5.hasNext()) {
            com.corrodinggames.rts.game.n var6 = (com.corrodinggames.rts.game.n)var5.next();
            if(var6 instanceof com.corrodinggames.rts.game.a.a) {
               com.corrodinggames.rts.game.a.a var7 = (com.corrodinggames.rts.game.a.a)var6;
               var4 = var7.bG > 0.0F;
            }
         }

         if(var4) {
            var8 = var8 + "AIs frozen\n";
         }

         this.bA.a();
         this.bA.b(Color.a(0, 0, 0, 0));
         this.bA.a(Paint$Style.a);
         float var9 = 70.0F * var2.cj;
         float var10 = 40.0F;
         if(var2.cl < 600.0F && var2.cm > 650.0F) {
            var9 = 10.0F;
            var10 = 60.0F * var2.cj;
         }

         var2.bO.a(var8, var9, var10, this.ay, this.bA, 6.0F);
      }

      this.j();
      this.s.f();
   }

   public void j() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
   }

   public void a(com.corrodinggames.rts.gameFramework.l var1, com.corrodinggames.rts.game.n var2, boolean var3, boolean var4) {
      if(var2.n) {
         this.a(var1, var2, var3, com.corrodinggames.rts.game.units.custom.e.a.c.D, var2.aa(), (com.corrodinggames.rts.game.units.custom.e.f)null, 0, (com.corrodinggames.rts.game.units.custom.e.a)null);
      }

      if(var4) {
         this.bT.g(var2.ab());
         ArrayList var5 = com.corrodinggames.rts.game.units.custom.e.a.f();
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            com.corrodinggames.rts.game.units.custom.e.a var7 = (com.corrodinggames.rts.game.units.custom.e.a)var6.next();
            if(var7.d() && (var7.p || var7.j)) {
               this.bT.c(var7);
            }
         }

         this.bT.e();
         this.a(var1, var2, var3, this.bT);
      }

   }

   public void a(com.corrodinggames.rts.gameFramework.l var1, com.corrodinggames.rts.game.n var2, boolean var3, com.corrodinggames.rts.game.units.custom.e.f var4) {
      Iterator var5 = var4.b.iterator();

      while(var5.hasNext()) {
         com.corrodinggames.rts.game.units.custom.e.e var6 = (com.corrodinggames.rts.game.units.custom.e.e)var5.next();
         if(!var6.a.a()) {
            com.corrodinggames.rts.game.units.custom.e.a var7 = var6.a;
            double var8 = var6.b;
            this.a(var1, var2, var3, var7, var8, var4, 0, (com.corrodinggames.rts.game.units.custom.e.a)null);
         }
      }

   }

   public boolean a(com.corrodinggames.rts.gameFramework.l var1, com.corrodinggames.rts.game.n var2, boolean var3, com.corrodinggames.rts.game.units.custom.e.a var4, double var5, com.corrodinggames.rts.game.units.custom.e.f var7, int var8, com.corrodinggames.rts.game.units.custom.e.a var9) {
      if(var8 == 0) {
         this.cj = 0;
      }

      boolean var10 = false;
      if(var8 < 6 && var7 != null) {
         com.corrodinggames.rts.game.units.custom.e.a var11 = var4.i;
         if(var11 != null && (var4.j || var5 != 0.0D)) {
            double var12 = var7.a(var11);
            boolean var14 = this.a(var1, var2, var3, var11, var12, var7, var8 + 1, var4);
            if(var14) {
               var10 = true;
            }
         }
      }

      if((var5 != 0.0D || var4.p) && (var8 != 0 || var4.l)) {
         byte var35 = 6;
         String var36 = var4.a(var5, true);
         int var13 = var2.b(var4);
         int var37 = var2.a(var4);
         if(var37 != 0) {
            var36 = var36 + "(+" + var13 + ")(-" + var37 + ")";
         } else if(var13 != 0) {
            if(var13 >= 0) {
               var36 = var36 + "(+" + var13 + ")";
            } else {
               var36 = var36 + "(" + var13 + ")";
            }
         }

         int var15 = (int)(var1.cl - var1.cq);
         var15 -= this.cj;
         Paint var16 = this.av;
         if(var3) {
            var16 = this.aw;
         } else {
            Integer var17 = var4.h();
            if(var17 != null) {
               this.at.a(var16);
               var16 = this.at;
               var16.b(var17.intValue());
            }
         }

         float var38 = (float)var1.bO.b(var36, var16);
         float var18 = (float)var1.bO.a(var36, var16);
         this.ci = var18 + (float)var35;
         if((float)this.cl < var38) {
            this.cl = (int)var38;
         }

         int var19 = this.ch;
         if(var4.w) {
            var19 = 0;
         }

         int var20 = 0;
         int var21 = 0;
         if(var19 == 0) {
            var20 = this.cg;
         } else {
            var21 = this.cf;
         }

         int var22 = 0;
         byte var23 = var35;
         int var24 = var35;
         byte var25 = var35;
         boolean var27 = false;
         float var28 = var38 + (float)var35 + (float)var35;
         if(var4.k) {
            var28 += 80.0F;
         }

         if((float)var15 < var28 && var4.i != null) {
            var27 = true;
            this.cf = (int)((float)this.cf + this.ci);
            var21 = this.cf;
            var15 += this.cj;
            this.cj = 0;
         }

         if(var19 != 0) {
            var23 = 0;
         }

         if(var9 != null && !var9.k) {
            var24 = 0;
         }

         if(var10 && !var4.k) {
            var15 += var35;
            var25 = 0;
         }

         if(var10 && var4.k && !var27) {
            var22 = var1.bO.b("AA", var16);
         }

         var15 -= var22;
         com.corrodinggames.rts.gameFramework.m.e var29 = var4.k();
         float var30 = 1.0F;
         float var31;
         float var32;
         if(var29 != null) {
            var32 = var18 - 3.0F;
            if(var32 < 3.0F) {
               var32 = 3.0F;
            }

            var30 = d.a(var29, var18 * 3.0F, var32);
            var31 = (float)var29.p * var30 + 3.0F;
            var24 += (int)var31;
         } else {
            var31 = 0.0F;
         }

         var32 = (float)var15 - var38 - (float)var20;
         d.a(var36, var32 - (float)var35, (float)(var21 + var35), var16, this.ax, (float)var24, (float)var23, (float)var25, (float)var35);
         if(var29 != null) {
            int var33 = (int)(var32 - var31 / 2.0F - (float)var29.r * var30 - 3.0F);
            int var34 = (int)((float)(var21 + var35) + var18 / 2.0F - (float)var29.s * var30);
            var1.bO.a(var29, (float)var33, (float)var34, this.bD, 0.0F, var30);
         }

         if(var8 == 0) {
            if(var19 == 0) {
               this.cg = (int)((float)this.cg + var38 + (float)var25 + (float)var24);
            }

            if(this.ch == var19) {
               this.cf = (int)((float)this.cf + this.ci);
               ++this.ch;
            }
         }

         this.cj = (int)((float)this.cj + var38 + (float)var25 + (float)var24 + (float)var22);
         return true;
      } else {
         return var10;
      }
   }

   public boolean k() {
      return this.a(h.b, true);
   }

   public boolean b(boolean var1) {
      return this.a(var1?h.a:h.b, false);
   }

   public boolean a(h var1) {
      return this.a(var1, false);
   }

   public boolean a(h var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(com.corrodinggames.rts.gameFramework.l.av() && !var2) {
         return false;
      } else {
         boolean var4 = false;
         this.bd = true;
         float var5 = var3.cj * 0.6F;
         int var6 = (int)(100.0F * var5);
         int var7 = (int)(10.0F * var5);
         int var8 = (int)(var3.cm - (float)((int)(9.0F * var5)) - (float)var6 * this.be);
         if(bR) {
            var8 = (int)((float)var8 - var3.bW.d);
         }

         int var9;
         if(var1 == h.c) {
            var9 = (int)(20.0F * var5) + var6;
            var9 += (int)(20.0F * var5) + var6;
            this.by.a(var7 + var9, var8, var7 + var9 + var6, var8 + var6);
            var3.bO.a(this.bc, (float)this.by.a, (float)this.by.b, this.bf, 0.0F, var5);
         } else if(var1 == h.a) {
            this.by.a(var7, var8, var7 + var6, var8 + var6);
            var3.bO.a(this.bb, (float)this.by.a, (float)this.by.b, this.bf, 0.0F, var5);
         } else {
            var9 = (int)(20.0F * var5) + var6;
            this.by.a(var7 + var9, var8, var7 + var9 + var6, var8 + var6);
            var3.bO.a(this.ba, (float)this.by.a, (float)this.by.b, this.bf, 0.0F, var5);
         }

         boolean var10 = false;
         com.corrodinggames.rts.gameFramework.f.a(this.by, 10.0F * var5);
         if(this.U && !this.T && this.by.b((int)this.x, (int)this.y)) {
            var10 = true;
         }

         this.a((float)this.by.a, (float)this.by.b, (float)this.by.b(), (float)this.by.c());
         return var10;
      }
   }

   public boolean l() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.ac != null) {
         if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.b) {
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
      } else {
         return false;
      }
   }

   public void c(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      Point var3 = var2.bW.c(this.x, this.y);
      float var4;
      float var5;
      if(var3 != null) {
         var4 = (float)var3.a;
         var5 = (float)var3.b;
      } else {
         var4 = this.x / var2.cX + var2.cw;
         var5 = this.y / var2.cX + var2.cx;
      }

      this.af = com.corrodinggames.rts.gameFramework.f.a(this.af, var1);
      this.bx.a((int)(var2.cl - var2.cq), 0, (int)var2.cl, (int)var2.cm);
      if(!bO && (this.U || this.I) && this.bx.b((int)this.x, (int)this.y)) {
         this.aZ = true;
      }

      this.g.a(var1);
      this.g.b(var1);
      this.X += var1;
      if(!var2.A()) {
         int var6 = this.g.d(var1);
         this.g.a(var1, var6);
         this.g.e(var1);
         this.h.a(var1, m.a);
         this.i.a(var1);
         int var7 = Math.max((int)((float)this.cf + this.ci * 2.0F), 130);
         this.j.a(var1, var7);
         if(this.u) {
            this.g.c(var1);
         }

         this.k.b(var1);
         this.g.a(var1, true);
      }

      this.a(var1, var4, var5, var3);
      if(!var2.A() && !this.u) {
         this.g.c(var1);
      }

      boolean var14 = false;
      if(!this.T) {
         boolean var15 = true;
         boolean var8 = true;
         boolean var9 = true;
         if(com.corrodinggames.rts.gameFramework.l.av() && var2.bQ.mouseSupport) {
            if(var2.bQ.mouseOrders == 0) {
               var15 = true;
            } else {
               var15 = false;
               var8 = false;
               var9 = false;
               if(var2.bQ.mouseOrders == 1) {
                  if(var2.e(1)) {
                     var8 = true;
                  } else if(var2.e(2)) {
                     var9 = true;
                  }
               } else if(var2.e(2)) {
                  var8 = true;
               } else if(var2.e(1)) {
                  var9 = true;
               }
            }
         }

         if(this.I && var3 != null && this.J) {
            boolean var12 = false;
            if(!var15 && !var9) {
               var12 = true;
            }

            if(this.q() == 0 || !this.C()) {
               var12 = true;
            }

            if(var15 && this.w > 20.0F) {
               var12 = true;
            }

            if(var12) {
               var2.b(var4, var5);
               var14 = true;
            }
         }

         if((this.C || var3 != null && (var15 || var9)) && !var14 && this.ac == null && this.U) {
            if(this.w > 30.0F) {
               if(this.a() && var3 == null) {
                  float var19 = this.b();
                  var19 /= var2.cX;
                  this.y();
                  this.b(var4, var5, var19);
                  this.E();
               }
            } else {
               var2.cU = false;
               com.corrodinggames.rts.game.units.am var21;
               if(!var15) {
                  if(var8) {
                     var21 = null;
                     if(var3 == null) {
                        var21 = this.a(var4, var5, true);
                     }

                     this.a(var21);
                  } else if(var9) {
                     var21 = null;
                     if(var3 == null) {
                        var21 = this.a(var4, var5, false);
                     }

                     boolean var13 = false;
                     if(var21 == null) {
                        var13 = true;
                     } else if(!this.a(var21, false, var4, var5, var3)) {
                        var13 = true;
                     }

                     if(var13) {
                        this.c(var4, var5, var3);
                     }
                  }
               } else {
                  var21 = null;
                  com.corrodinggames.rts.game.units.am var20 = null;
                  if(var3 == null) {
                     var21 = this.a(var4, var5, true);
                     var20 = this.a(var4, var5, false);
                  }

                  if(var21 == null && var20 == null) {
                     this.c(var4, var5, var3);
                  } else if(var20 != null) {
                     if(!this.a(var20, true, var4, var5, var3)) {
                        if(!var20.t()) {
                           this.a(var20);
                        } else if(var21 != null) {
                           this.a(var21);
                        }
                     }
                  } else {
                     this.a(var21);
                  }
               }
            }
         }
      }

      if(this.ac == null && this.I && !this.T && !this.J && !this.aZ) {
         this.au.a(Paint$Style.a);
         this.au.a(1.0F);
         if(this.w > 20.0F && this.a()) {
            float var16 = this.b();
            this.au.a(100, 0, 255, 0);
            var2.bO.a(this.x, this.y, var16, this.au);
            this.au.a(Paint$Style.b);
            this.au.a(1.0F);
            this.au.a(200, 0, 255, 0);
            var2.bO.a(this.x, this.y, var16, this.au);
         }
      }

      if(var2.bk && var2.ac() && var2.ae() > 0) {
         Paint var17 = new Paint();
         var17.c(100);

         for(int var18 = 0; var18 < var2.ae(); ++var18) {
            var2.bO.i();
            var2.bO.a(0.7F, 0.7F, var2.b(var18), var2.c(var18));
            var2.bO.a(this.bm, var2.b(var18), var2.c(var18), var17);
            var2.bO.j();
         }
      }

      if(!this.I) {
         this.w = 0.0F;
         this.T = false;
      }

      this.H = this.I;
      var2.ad();
      if(ce) {
         K();
         ce = false;
      }

   }

   public void a(float var1, float var2, float var3, Point var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      Object var6 = this.g.f();
      if(this.bd) {
         this.be = com.corrodinggames.rts.gameFramework.f.a(this.be, 1.0F, 0.05F * var1);
         this.be = (float)((double)this.be + 0.08D * (double)(1.0F - this.be));
      } else {
         this.be = com.corrodinggames.rts.gameFramework.f.a(this.be, 0.0F, 0.3F * var1);
      }

      this.bd = false;
      if(this.ac != null) {
         if(this.ac instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g var7 = (com.corrodinggames.rts.game.units.a.g)this.ac;
            if(var7.b != null) {
               var6 = var7.b;
            }
         }

         com.corrodinggames.rts.game.units.am var14;
         if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.e) {
            this.a(this.ac, false, (com.corrodinggames.rts.game.units.am)var6, false, true);
            if(!this.b(false) && !this.n()) {
               if(this.U && !this.T && !this.m()) {
                  var14 = this.a(var2, var3, false);
                  if(var14 != null && this.ac.o(var14)) {
                     this.b(var14);
                     if(!this.a(var5)) {
                        this.l();
                     }
                  } else {
                     this.a(var2, var3, 0.0F);
                  }

                  this.U = false;
               }
            } else {
               this.l();
               this.U = false;
            }
         } else if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.f) {
            this.a(this.ac, false, (com.corrodinggames.rts.game.units.am)var6, false, true);
            if(!this.b(false) && !this.n()) {
               if(this.U && !this.T && !this.m()) {
                  var14 = this.a(var2, var3, true);
                  if(var14 != null && this.ac.o(var14)) {
                     this.d(var14);
                     if(!this.a(var5)) {
                        this.l();
                     }
                  } else {
                     this.a(var2, var3, 0.0F);
                  }

                  this.U = false;
               }
            } else {
               this.l();
               this.U = false;
            }
         } else if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.d) {
            this.a(this.ac, false, (com.corrodinggames.rts.game.units.am)var6, false, true);
            if(!this.b(false) && !this.n()) {
               if(this.U && !this.T && !this.m()) {
                  this.b(var2, var3);
                  this.l();
                  this.U = false;
               }
            } else {
               this.l();
               this.U = false;
            }
         } else if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.g) {
            this.a(this.ac, false, (com.corrodinggames.rts.game.units.am)var6, false, true);
            Object var15 = this.g.f();
            com.corrodinggames.rts.game.units.a.s var8 = this.ac;
            if(this.ac instanceof com.corrodinggames.rts.game.units.a.g) {
               com.corrodinggames.rts.game.units.a.g var9 = (com.corrodinggames.rts.game.units.a.g)var8;
               if(var9.b != null) {
                  var15 = var9.b;
               }

               var8 = var9.a;
            }

            boolean var16 = this.k();
            boolean var10 = this.U && !this.M && this.C && !this.T && !this.m();
            if(this.ac.p()) {
               if(com.corrodinggames.rts.gameFramework.l.aw()) {
                  var10 = this.o() && !this.M && this.C && !this.K && this.J();
               } else {
                  var10 = this.I && !this.M && this.C && !this.K && this.J();
               }
            }

            boolean var11;
            if(var15 != null && var15 instanceof com.corrodinggames.rts.game.units.y) {
               var5.bO.i();
               var5.R();
               var11 = this.I && !this.T && !this.M && !this.K && var4 == null;
               float var12 = var2;
               float var13 = var3;
               if(com.corrodinggames.rts.gameFramework.l.aw() && var5.bQ.mouseSupport) {
                  var12 = var5.af() / var5.cX + var5.cw;
                  var13 = var5.ag() / var5.cX + var5.cx;
                  var11 = true;
                  if(this.M) {
                     var11 = false;
                  }
               }

               if(!this.a(this.z, this.A)) {
                  var11 = false;
               }

               ((com.corrodinggames.rts.game.units.y)var15).a(var8, var11, var12, var13);
               var5.bO.j();
            }

            if(!var16 && !this.n()) {
               if(var10 && var4 == null) {
                  var11 = false;
                  if(this.a(this.ac, var2, var3)) {
                     var11 = true;
                  }

                  if(!var11) {
                     this.b(this.ac, var2, var3);
                     if(!this.a(var5) && !this.ac.o()) {
                        this.l();
                     }
                  } else {
                     this.a(var2, var3, 0.0F);
                  }

                  this.U = false;
               }
            } else {
               this.l();
               this.U = false;
            }
         } else if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.h) {
            this.a(this.ac, false, (com.corrodinggames.rts.game.units.am)var6, false, true);
            if(!this.b(false) && !this.n()) {
               if(this.U && !this.T && !this.m()) {
                  this.d(var2, var3, var4);
                  if(!this.a(var5)) {
                     this.l();
                     this.U = false;
                  }
               }
            } else {
               this.l();
               this.U = false;
            }
         } else if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.l) {
            this.a(this.ac, false, (com.corrodinggames.rts.game.units.am)var6, false, true);
            if(!this.b(false) && !this.n()) {
               if(this.U && !this.T && !this.m()) {
                  var14 = this.a(var2, var3, true);
                  if(var14 != null && this.ac.o(var14)) {
                     this.e(var14);
                     this.l();
                  } else {
                     this.a(var2, var3, 0.0F);
                  }

                  this.U = false;
               }
            } else {
               this.l();
               this.U = false;
            }
         } else if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.m) {
            this.a(this.ac, false, (com.corrodinggames.rts.game.units.am)var6, false, true);
            if(!this.a(h.a, true) && !this.n()) {
               if(this.U && !this.T && !this.m()) {
                  this.a(var2, var3, var4, this.as == 0);
                  ++this.as;
               }
            } else {
               this.l();
               this.U = false;
            }
         } else if(this.ac.e() == com.corrodinggames.rts.game.units.a.u.j) {
            this.a(this.ac, false, (com.corrodinggames.rts.game.units.am)var6, false, true);
            if(!this.k() && !this.n()) {
               if(this.U && !this.T && !this.m() && var4 == null) {
                  if(this.ac instanceof com.corrodinggames.rts.game.units.a.j) {
                     this.a(var2, var3, var4, (com.corrodinggames.rts.game.units.a.j)this.ac);
                  } else {
                     com.corrodinggames.rts.gameFramework.l.b("orderBuildingSpecialAction is not a PingMapAction, it is: " + this.ac.getClass().getName());
                  }

                  this.l();
                  this.U = false;
               }
            } else {
               this.l();
               this.U = false;
            }
         } else if(this.ac.i() != null && this.ac.e() == com.corrodinggames.rts.game.units.a.u.b) {
            this.a(var2, var3, var4);
         }
      }

   }

   public void a(float var1, float var2, Point var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.am var5 = this.g.f();
      boolean var6 = false;
      if(var5 != null) {
         com.corrodinggames.rts.game.units.a.s var7 = var5.a(this.ac.N());
         if(var7 != null) {
            var6 = this.ac.a(var5, true) && !a.a(this.ac);
            if(!this.ac.b(var5)) {
               var6 = false;
            }
         }
      }

      this.a(this.ac, false, var5, !var6, true);
      float var57 = this.z / var4.cX;
      float var8 = this.A / var4.cX;
      float var9 = var57;
      float var10 = var8;
      boolean var11 = false;
      boolean var12 = false;
      boolean var13 = false;
      boolean var14 = false;
      if(com.corrodinggames.rts.gameFramework.l.aw() && var4.bQ.mouseSupport) {
         var11 = true;
         var14 = this.L;
      }

      if(var11) {
         if(this.o()) {
            if(!this.ap) {
               this.ap = true;
               this.an = var57 + var4.cw;
               this.ao = var8 + var4.cx;
            }
         } else {
            this.ap = false;
         }

         if(this.ap) {
            float var15 = var57 - (this.an - var4.cw);
            float var16 = var8 - (this.ao - var4.cx);
            if(com.corrodinggames.rts.gameFramework.f.c(var15) > 4.0F || com.corrodinggames.rts.gameFramework.f.c(var16) > 4.0F) {
               var12 = true;
            }
         }
      }

      boolean var58 = false;
      boolean var59 = false;
      boolean var17 = false;
      boolean var18 = false;
      boolean var19 = true;
      boolean var20 = false;
      if(com.corrodinggames.rts.gameFramework.l.av() && var4.bQ.mouseSupport) {
         var20 = true;
      }

      if(this.ae && !var20) {
         if(var6) {
            if(!this.ai && this.a(h.a)) {
               var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5F);
               this.U = false;
               var58 = true;
            }

            if(this.a(h.c)) {
               var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5F);
               this.U = false;
               var17 = true;
            }
         }

         if(this.a(h.b)) {
            var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.i, 0.7F);
            this.U = false;
            var59 = true;
         }
      }

      boolean var21 = false;
      if(com.corrodinggames.rts.gameFramework.l.aw() && var4.bQ.mouseSupport) {
         var21 = true;
      }

      if(this.U && !this.T) {
         var21 = true;
      }

      float var22;
      float var23;
      if(com.corrodinggames.rts.gameFramework.l.au()) {
         var13 = true;
         if(var4.ae() == 2) {
            var21 = true;
            var9 = var4.b(0) / var4.cX;
            var10 = var4.c(0) / var4.cX;
            var22 = var4.b(1) / var4.cX;
            var23 = var4.c(1) / var4.cX;
            this.ap = true;
            this.an = var22;
            this.ao = var23;
         } else if(this.U && !this.T) {
            this.ap = false;
         }

         if(this.ap) {
            var12 = true;
         }
      }

      if(var21) {
         this.ae = true;
         this.ag = var9 * var4.cX;
         this.ah = var10 * var4.cX;
         if(!this.a(var4.af(), var4.ag())) {
            this.ae = false;
            var19 = false;
         }
      }

      var22 = this.an;
      var23 = this.ao;
      if(var13) {
         var22 += var4.cw + var4.cr;
         var23 += var4.cx + var4.cs;
      }

      com.corrodinggames.rts.game.units.as var24 = this.ac.i();
      int var25 = this.ac.t();
      boolean var26 = false;
      if(com.corrodinggames.rts.gameFramework.l.aw() && var4.bQ.mouseSupport && !var4.cK.b((int)this.z, (int)this.A)) {
         var26 = true;
      }

      com.corrodinggames.rts.game.units.am var27 = com.corrodinggames.rts.game.units.am.c(var24);
      if((var27 == null || !(var27 instanceof com.corrodinggames.rts.game.units.y)) && com.corrodinggames.rts.game.units.custom.l.b != null) {
         var27 = com.corrodinggames.rts.game.units.am.c((com.corrodinggames.rts.game.units.as)com.corrodinggames.rts.game.units.custom.l.b);
      }

      if(this.ae && !var26) {
         com.corrodinggames.rts.game.units.y var28 = (com.corrodinggames.rts.game.units.y)var27;
         var4.bL.b(this.ag / var4.cX + var4.cw, this.ah / var4.cX + var4.cx);
         var28.eo = (float)var4.bL.T;
         var28.ep = (float)var4.bL.U;
         if(var24.p()) {
            var12 = false;
            Point var29 = j.a((int)var28.eo, (int)var28.ep, 3);
            if(var29 != null) {
               var28.eo = (float)var29.a;
               var28.ep = (float)var29.b;
            }
         }

         if(!var28.bI()) {
            var28.cg = 0.0F;
         } else {
            var28.cg = -90.0F;
         }

         var28.eo += var28.cZ();
         var28.ep += var28.da();
         var28.b(this.aa.bX);
         var28.a(var25);
         var28.cp = true;
         String var60 = var28.b(false, var4.bs);
         if(com.corrodinggames.rts.gameFramework.d.a.a(var4.bs, var28, this.ad)) {
            var60 = "{0}";
         }

         float var31;
         float var32;
         if(this.q() == 1 && var5 != null && var5 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var30 = (com.corrodinggames.rts.game.units.y)var5;
            if(!var30.aR()) {
               var31 = com.corrodinggames.rts.gameFramework.f.a(var30.eo, var30.ep, var28.eo, var28.ep);
               var32 = var30.f(var28.r());
               boolean var33;
               if(var32 > 800000.0F) {
                  var33 = true;
               } else {
                  var33 = var31 <= var32 * var32;
               }

               if(!var33) {
                  var60 = "{0}";
               }
            }
         }

         if(var12) {
            ;
         }

         com.corrodinggames.rts.game.units.am var61 = null;
         if(this.q() == 1) {
            var61 = var5;
         }

         if(var6) {
            if(var12) {
               if(!com.corrodinggames.rts.gameFramework.l.av() && (!com.corrodinggames.rts.gameFramework.l.au() || var4.ae() != 2)) {
                  var4.bO.a((var28.eo - var4.cw) * var4.cX, (var28.ep - var4.cx) * var4.cX, (var22 - var4.cw) * var4.cX, (var23 - var4.cx) * var4.cX, this.bE);
               } else {
                  var4.bO.a(var9 * var4.cX, var10 * var4.cX, (var22 - var4.cw) * var4.cX, (var23 - var4.cx) * var4.cX, this.bE);
               }

               boolean var62 = true;
               var60 = null;
               this.a(var28, var22, var23, var28.eo, var28.ep, var62, (ArrayList)null, var61);
            } else {
               this.a(var28, var28.eo, var28.ep, true, var14, var61);
            }
         }

         var28.a((int)1);
         if(var19 && this.p()) {
            this.U = false;
            if(this.a(var4)) {
               var17 = true;
               var18 = true;
            } else {
               var58 = true;
            }
         }

         if(this.n()) {
            this.U = false;
            var59 = true;
         }

         if(this.U && !this.T) {
            float var66 = this.aq;
            float var34 = this.ar;
            float var35 = 15.0F;
            if(com.corrodinggames.rts.gameFramework.f.c(var66 - var9) < var35 && com.corrodinggames.rts.gameFramework.f.c(var34 - var10) < var35 && this.af != 0.0F) {
               this.U = false;
               var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5F);
               if(this.ai) {
                  var17 = true;
               } else {
                  var58 = true;
               }
            }

            this.af = 80.0F;
            this.aq = var9;
            this.ar = var10;
         }

         if(var58 || var17) {
            if(!var6) {
               var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.7F);
               if(var60 == null && var5 != null && this.ac != null) {
                  com.corrodinggames.rts.game.units.a.s var63 = var5.a(this.ac.N());
                  if(var63 != null) {
                     var60 = this.ac.j(var5);
                     if(var60 == null) {
                        String var65 = this.f(this.ac);
                        if(var65 != null) {
                           var60 = this.bI.b();
                        }
                     }
                  }
               }

               if(var60 != "{0}") {
                  this.c(var60);
               }
            } else if(var60 != null) {
               var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.7F);
               if(var60 != "{0}") {
                  String var64 = var60;
                  if(var60 == "{2}") {
                     var64 = this.bK;
                  }

                  if(var64 == "{3}") {
                     var64 = this.bL;
                  }

                  if(var64 == "{1}") {
                     var64 = this.bJ;
                  }

                  this.c(var64);
               }
            } else {
               var31 = var28.eo;
               var32 = var28.ep;
               ArrayList var67 = new ArrayList();
               if(var12) {
                  boolean var68 = false;
                  this.a(var28, var22, var23, var28.eo, var28.ep, var68, var67, (com.corrodinggames.rts.game.units.am)null);
               } else {
                  var67.add(new PointF(var31, var32));
               }

               int var69 = 0;
               boolean var70 = true;
               Iterator var36 = var67.iterator();

               while(var36.hasNext()) {
                  PointF var37 = (PointF)var36.next();
                  com.corrodinggames.rts.gameFramework.e var38;
                  if(this.ac.A()) {
                     var38 = this.x();
                     this.a(var38);
                     var38.a(this.ac.N(), var37, (com.corrodinggames.rts.game.units.am)null);
                  } else {
                     var38 = this.x();
                     if(var70) {
                        var70 = false;
                        if(var17) {
                           if(!var38.e) {
                              var38.f = true;
                           }

                           this.ai = true;
                        }
                     } else {
                        var38.e = true;
                     }

                     com.corrodinggames.rts.game.units.y var39 = this.t();
                     if(this.ac instanceof com.corrodinggames.rts.game.units.a.g) {
                        com.corrodinggames.rts.game.units.y var40 = ((com.corrodinggames.rts.game.units.a.g)this.ac).b;
                        var38.a(var40);
                        var39 = var40;
                     } else {
                        this.a(var38);
                     }

                     var38.a(var37.a, var37.b, var24, var25);
                     if(var39 != null) {
                        com.corrodinggames.rts.gameFramework.d.a var76 = new com.corrodinggames.rts.gameFramework.d.a();
                        var76.d = var24;
                        var76.g = var37.a;
                        var76.h = var37.b;
                        var76.n = true;
                        var76.o = var39;
                        var76.e = var4.bs;
                        var76.f = var25;
                        var76.j = var4.bs;
                        var76.r = this.ad;
                        var76.s = 1.0F + 0.15F * (float)var69;
                        if(var39.av() >= 29) {
                           var76.q = true;
                        }
                     }

                     ++var69;
                  }
               }

               this.aU = 5.0F;
               if(com.corrodinggames.rts.gameFramework.l.aw()) {
                  this.aU = 1.0F;
               }

               this.ap = false;
               if(!var17) {
                  if(var69 > 0) {
                     boolean var71 = true;
                     if(var28 != null && !this.p(var28)) {
                        var71 = false;
                     }

                     this.ac = null;
                     this.ae = false;
                     this.ai = false;
                     this.aa = null;
                     if(var71) {
                        this.y();
                     }

                     ++this.ad;
                  }
               } else if(!var18) {
                  float var72 = var28.eo;
                  float var73 = var28.ep;
                  boolean var74 = false;
                  if(com.corrodinggames.rts.gameFramework.f.c(var72 - this.aj) < (float)(var28.cd().b() * var4.bL.n) * 2.0F + (float)(3 * var4.bL.n) && com.corrodinggames.rts.gameFramework.f.c(var73 - this.ak) < (float)(var28.cd().c() * var4.bL.o) * 2.0F + (float)(3 * var4.bL.o)) {
                     this.al = var72 - this.aj;
                     this.am = var73 - this.ak;
                     if(com.corrodinggames.rts.gameFramework.f.c(this.al) > com.corrodinggames.rts.gameFramework.f.c(this.am)) {
                        this.am = 0.0F;
                     } else {
                        this.al = 0.0F;
                     }
                  }

                  if(var28.cd().c() > var28.cd().b() + 1) {
                     this.am = 0.0F;
                  }

                  this.aj = var72;
                  this.ak = var73;
                  float var75 = 0.0F;
                  float var77 = 0.0F;
                  if(this.am < 0.0F) {
                     var75 = -1.0F;
                  }

                  if(this.al < 0.0F) {
                     var77 = -1.0F;
                  }

                  if(this.am > 0.0F) {
                     var75 = 1.0F;
                  }

                  if(this.al > 0.0F) {
                     var77 = 1.0F;
                  }

                  if(var77 == 0.0F && var75 == 0.0F) {
                     var77 = 1.0F;
                  }

                  ArrayList var41 = new ArrayList();
                  float var42 = var72 + 200.0F * var77;
                  float var43 = var73 + 200.0F * var75;
                  float var44 = -var28.cZ() + 1.0F;
                  float var45 = -var28.da() + 1.0F;
                  boolean var46 = false;
                  this.a(var28, var72 + var44, var73 + var45, var42 + var44, var43 + var45, var46, var41, (com.corrodinggames.rts.game.units.am)null);
                  if(var41.size() > 0) {
                     var28.eo = ((PointF)var41.get(0)).a;
                     var28.ep = ((PointF)var41.get(0)).b;
                     var74 = true;
                  }

                  if(!var74) {
                     var42 = var72 + 200.0F * -var77;
                     var43 = var73 + 200.0F * -var75;
                     this.a(var28, var72 + var44, var73 + var45, var42 + var44, var43 + var45, var46, var41, (com.corrodinggames.rts.game.units.am)null);
                     if(var41.size() > 0) {
                        var28.eo = ((PointF)var41.get(0)).a;
                        var28.ep = ((PointF)var41.get(0)).b;
                        var74 = true;
                     }
                  }

                  if(!var74) {
                     var28.eo += (float)(3 * var4.bL.n);
                     var28.ep += (float)var4.bL.n;
                  }

                  if(var74) {
                     float var47 = var28.eo - var72;
                     float var48 = var28.ep - var73;
                     float var49 = var4.cy;
                     float var50 = var4.cz;
                     var4.cr += var47;
                     var4.cs += var48;
                     var4.cy += var4.cr;
                     var4.cz += var4.cs;
                     float var51 = var4.cy;
                     float var52 = var4.cz;
                     var4.Q();
                     float var53 = var4.cy - var51;
                     float var54 = var4.cz - var52;
                     var4.cr += var53;
                     var4.cs += var54;
                     float var55 = var49 + var47 - var4.cy;
                     float var56 = var50 + var48 - var4.cz;
                     if(com.corrodinggames.rts.gameFramework.f.c(var55) > 1.0F) {
                        this.ag += var55 * var4.cX;
                     }

                     if(com.corrodinggames.rts.gameFramework.f.c(var56) > 1.0F) {
                        this.ah += var56 * var4.cX;
                     }

                     var4.cy -= var4.cr;
                     var4.cz -= var4.cs;
                  }
               }
            }
         }

         if(var59) {
            this.l();
            if(this.ai) {
               this.y();
            }
         }
      }

   }

   public boolean m() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return com.corrodinggames.rts.gameFramework.l.aw() && var1.bQ.mouseSupport && !this.n() && !this.p();
   }

   public boolean n() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(com.corrodinggames.rts.gameFramework.l.aw() && var1.bQ.mouseSupport && this.U && !this.T && !this.aZ) {
         byte var2 = 1;
         byte var3 = 2;
         if(var1.bQ.mousePlacement == 2) {
            var2 = 2;
            var3 = 1;
         }

         if(var1.e(var2)) {
            ;
         }

         if(var1.e(var3)) {
            return true;
         }
      }

      return false;
   }

   public boolean o() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(com.corrodinggames.rts.gameFramework.l.av() && var1.bQ.mouseSupport && (this.U || this.I)) {
         byte var2 = 1;
         byte var3 = 2;
         if(var1.bQ.mousePlacement == 2) {
            var2 = 2;
            var3 = 1;
         }

         if(var1.e(var2)) {
            return true;
         }

         if(var1.e(var3)) {
            ;
         }
      }

      return false;
   }

   public boolean p() {
      return this.U && !this.T && !this.aZ?this.o():false;
   }

   public void a(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1 != null && this.W == var1 && this.X < 40.0F && !this.b(var2)) {
         if(!this.a(var2)) {
            this.y();
         }

         this.h(var1);
      } else if(var1 != null) {
         if(!this.a(var2) && !this.b(var2)) {
            this.y();
         }

         this.a(var1, this.b(var2));
         this.W = var1;
         this.X = 0.0F;
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.am var1, boolean var2, float var3, float var4, Point var5) {
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.n var7 = this.r();
      boolean var8 = var7.c(var1.bX);
      if(var8 && this.B() && this.q(var1)) {
         this.c(var1);
         return true;
      } else {
         boolean var9;
         if(var7.d(var1.bX) && (var1.cu < var1.cv || var1.cm < 1.0F) && this.q() != 0) {
            var9 = true;
            boolean var10 = false;
            boolean var11 = false;
            boolean var12 = false;
            if(var1.cr() && this.n(var1)) {
               var10 = true;
            }

            Iterator var13 = this.bZ.iterator();

            while(var13.hasNext()) {
               com.corrodinggames.rts.gameFramework.w var14 = (com.corrodinggames.rts.gameFramework.w)var13.next();
               if(var14 instanceof com.corrodinggames.rts.game.units.y) {
                  com.corrodinggames.rts.game.units.y var15 = (com.corrodinggames.rts.game.units.y)var14;
                  if(var15.cG) {
                     if(!this.m(var15)) {
                        var9 = false;
                        break;
                     }

                     if(!var15.a(var1)) {
                        var9 = false;
                        break;
                     }

                     if(var15.aS()) {
                        var12 = true;
                     }

                     com.corrodinggames.rts.game.units.au var16 = var15.ar();
                     if(var16 != null && var16.d() == av.d) {
                        var11 = true;
                     }
                  }
               }
            }

            if(var9 && (!var11 || !var10)) {
               if(var12) {
                  this.d(var1);
               } else {
                  this.d(var1);
               }

               return true;
            }
         }

         if(var1.g() > 0.0F && this.q() != 0) {
            var9 = true;
            Iterator var17 = com.corrodinggames.rts.gameFramework.w.er.iterator();

            while(var17.hasNext()) {
               com.corrodinggames.rts.gameFramework.w var18 = (com.corrodinggames.rts.gameFramework.w)var17.next();
               if(var18 instanceof com.corrodinggames.rts.game.units.y) {
                  com.corrodinggames.rts.game.units.y var19 = (com.corrodinggames.rts.game.units.y)var18;
                  if(var19.cG) {
                     if(!this.m(var19)) {
                        var9 = false;
                        break;
                     }

                     if(!var19.h(var1, true)) {
                        var9 = false;
                        break;
                     }
                  }
               }
            }

            if(var9) {
               this.b(var1);
               return true;
            }
         }

         if(var1.cr() && this.n(var1)) {
            this.f(var1);
            return true;
         } else if(com.corrodinggames.rts.gameFramework.l.av() && this.C() && this.o(var1)) {
            this.g(var1);
            return true;
         } else {
            var9 = false;
            if((!var2 || var1.t()) && !var7.c(var1.bX)) {
               if(var1.bI()) {
                  if(var1.cc().a()) {
                     var9 = true;
                  }
               } else if(!var1.bT) {
                  var9 = true;
               }

               if(!var9 && !var1.i() && this.D()) {
                  var9 = true;
               }
            }

            if(var9) {
               return false;
            } else if(var8 && this.B()) {
               this.a(var1.eo, var1.ep, var1.eq);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public int q() {
      return this.aX;
   }

   void a(com.corrodinggames.rts.gameFramework.e var1) {
      Iterator var2 = com.corrodinggames.rts.gameFramework.w.er.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.gameFramework.w var3 = (com.corrodinggames.rts.gameFramework.w)var2.next();
         if(var3 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var4 = (com.corrodinggames.rts.game.units.y)var3;
            if(var4.cG && this.m(var4)) {
               var1.a(var4);
            }
         }
      }

   }

   public com.corrodinggames.rts.game.n r() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      Iterator var2 = this.bZ.iterator();

      com.corrodinggames.rts.game.units.am var3;
      com.corrodinggames.rts.game.units.y var4;
      while(var2.hasNext()) {
         var3 = (com.corrodinggames.rts.game.units.am)var2.next();
         if(var3 instanceof com.corrodinggames.rts.game.units.y) {
            var4 = (com.corrodinggames.rts.game.units.y)var3;
            if(var4.bX == var1.bs) {
               return var4.bX;
            }
         }
      }

      var2 = this.bZ.iterator();

      while(var2.hasNext()) {
         var3 = (com.corrodinggames.rts.game.units.am)var2.next();
         if(var3 instanceof com.corrodinggames.rts.game.units.y) {
            var4 = (com.corrodinggames.rts.game.units.y)var3;
            if(this.m(var4)) {
               return var4.bX;
            }
         }
      }

      return var1.bs;
   }

   public com.corrodinggames.rts.game.units.y s() {
      Iterator var1 = this.bZ.iterator();

      com.corrodinggames.rts.game.units.am var2;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         var2 = (com.corrodinggames.rts.game.units.am)var1.next();
      } while(!(var2 instanceof com.corrodinggames.rts.game.units.y));

      com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
      return var3;
   }

   public com.corrodinggames.rts.game.units.y t() {
      Iterator var1 = this.bZ.iterator();

      while(var1.hasNext()) {
         com.corrodinggames.rts.game.units.am var2 = (com.corrodinggames.rts.game.units.am)var1.next();
         if(var2 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
            if(this.m(var3)) {
               return var3;
            }
         }
      }

      return null;
   }

   void a(com.corrodinggames.rts.gameFramework.e var1, com.corrodinggames.rts.game.units.a.s var2, boolean var3) {
      if(var2 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var13 = (com.corrodinggames.rts.game.units.a.g)var2;
         var1.a(var13.b);
      } else {
         com.corrodinggames.rts.game.units.a.c var4 = var2.N();
         com.corrodinggames.rts.game.units.y var5 = null;
         int var6 = -99;
         Iterator var7 = com.corrodinggames.rts.gameFramework.w.er.iterator();

         while(var7.hasNext()) {
            com.corrodinggames.rts.gameFramework.w var8 = (com.corrodinggames.rts.gameFramework.w)var7.next();
            if(var8 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var9 = (com.corrodinggames.rts.game.units.y)var8;
               if(var9.cG && this.m(var9)) {
                  com.corrodinggames.rts.game.units.a.s var10 = var9.a(var4);
                  if(var10 != null && var10.b((com.corrodinggames.rts.game.units.am)var9) && (var10.a(var9, true) || var3)) {
                     int var11 = 0;
                     if(var9 instanceof com.corrodinggames.rts.game.units.d.l) {
                        boolean var12 = true;
                        var11 = ((com.corrodinggames.rts.game.units.d.l)var9).a(var4, true);
                        if(var5 != null) {
                           if(!var3) {
                              if(var11 >= var6) {
                                 break;
                              }
                           } else if(var11 <= var6) {
                              break;
                           }
                        }
                     }

                     var5 = var9;
                     var6 = var11;
                  }
               }
            }
         }

         if(var5 != null) {
            var1.a(var5);
         }

      }
   }

   boolean a(com.corrodinggames.rts.game.units.a.s var1, float var2, float var3) {
      if(var1 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var9 = (com.corrodinggames.rts.game.units.a.g)var1;
         com.corrodinggames.rts.game.units.y var10 = var9.b;
         com.corrodinggames.rts.game.units.a.s var11 = var9.p_();
         boolean var12 = false;
         if(var11.b((com.corrodinggames.rts.game.units.am)var10) && var11.a(var10, true) && !var10.a(var11, var2, var3)) {
            var12 = true;
         }

         return var12;
      } else {
         boolean var4 = false;
         Iterator var5 = com.corrodinggames.rts.gameFramework.w.er.iterator();

         while(var5.hasNext()) {
            com.corrodinggames.rts.gameFramework.w var6 = (com.corrodinggames.rts.gameFramework.w)var5.next();
            if(var6 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var7 = (com.corrodinggames.rts.game.units.y)var6;
               if(var7.cG && this.m(var7)) {
                  com.corrodinggames.rts.game.units.a.s var8 = var7.a(var1.N());
                  if(var8 != null && var8.b((com.corrodinggames.rts.game.units.am)var7) && var8.a(var7, true)) {
                     if(var7.a(var8, var2, var3)) {
                        return false;
                     }

                     var4 = true;
                  }
               }
            }
         }

         if(!var4) {
            return false;
         } else {
            return true;
         }
      }
   }

   void a(com.corrodinggames.rts.gameFramework.e var1, com.corrodinggames.rts.game.units.a.s var2) {
      if(var2 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var8 = (com.corrodinggames.rts.game.units.a.g)var2;
         var1.a(var8.b);
      } else {
         com.corrodinggames.rts.game.units.a.c var3 = var2.N();
         Iterator var4 = com.corrodinggames.rts.gameFramework.w.er.iterator();

         while(var4.hasNext()) {
            com.corrodinggames.rts.gameFramework.w var5 = (com.corrodinggames.rts.gameFramework.w)var4.next();
            if(var5 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var6 = (com.corrodinggames.rts.game.units.y)var5;
               if(var6.cG && this.m(var6)) {
                  com.corrodinggames.rts.game.units.a.s var7 = var6.a(var3);
                  if(var7 != null && var7.b((com.corrodinggames.rts.game.units.am)var6)) {
                     var1.a(var6);
                  }
               }
            }
         }

      }
   }

   public boolean a(com.corrodinggames.rts.game.units.a.s var1, boolean var2) {
      if(var1 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var8 = (com.corrodinggames.rts.game.units.a.g)var1;
         return var8.a(var8.b, true);
      } else {
         com.corrodinggames.rts.game.units.a.c var3 = var1.N();
         Iterator var4 = this.bZ.iterator();

         while(var4.hasNext()) {
            com.corrodinggames.rts.game.units.am var5 = (com.corrodinggames.rts.game.units.am)var4.next();
            if(var5 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var6 = (com.corrodinggames.rts.game.units.y)var5;
               if(var6.cG && this.m(var6)) {
                  com.corrodinggames.rts.game.units.a.s var7 = var6.a(var3);
                  if(var7 != null && var7.a(var6, var2)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   public boolean a(com.corrodinggames.rts.game.units.a.s var1) {
      com.corrodinggames.rts.game.units.a.c var2 = var1.N();
      if(var1.o_()) {
         return false;
      } else if(var1 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var7 = (com.corrodinggames.rts.game.units.a.g)var1;
         return var7.a((com.corrodinggames.rts.game.units.am)var7.b);
      } else {
         Iterator var3 = this.bZ.iterator();

         while(var3.hasNext()) {
            com.corrodinggames.rts.game.units.am var4 = (com.corrodinggames.rts.game.units.am)var3.next();
            if(var4 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var5 = (com.corrodinggames.rts.game.units.y)var4;
               if(var5.cG && this.m(var5)) {
                  com.corrodinggames.rts.game.units.a.s var6 = var5.a(var2);
                  if(var6 != null && var6.a((com.corrodinggames.rts.game.units.am)var5)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   public boolean b(com.corrodinggames.rts.game.units.a.s var1) {
      if(var1 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var7 = (com.corrodinggames.rts.game.units.a.g)var1;
         return var7.b(var7.b);
      } else {
         com.corrodinggames.rts.game.units.a.c var2 = var1.N();
         Iterator var3 = this.bZ.iterator();

         while(var3.hasNext()) {
            com.corrodinggames.rts.game.units.am var4 = (com.corrodinggames.rts.game.units.am)var3.next();
            if(var4 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var5 = (com.corrodinggames.rts.game.units.y)var4;
               if(var5.cG && this.m(var5)) {
                  com.corrodinggames.rts.game.units.a.s var6 = var5.a(var2);
                  if(var6 != null && var6.b((com.corrodinggames.rts.game.units.am)var5)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   public boolean c(com.corrodinggames.rts.game.units.a.s var1) {
      boolean var2 = false;
      if(var1 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var8 = (com.corrodinggames.rts.game.units.a.g)var1;
         return var8.g(var8.b);
      } else {
         com.corrodinggames.rts.game.units.a.c var3 = var1.N();
         Iterator var4 = this.bZ.iterator();

         while(var4.hasNext()) {
            com.corrodinggames.rts.game.units.am var5 = (com.corrodinggames.rts.game.units.am)var4.next();
            if(var5 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var6 = (com.corrodinggames.rts.game.units.y)var5;
               if(var6.cG && this.m(var6)) {
                  com.corrodinggames.rts.game.units.a.s var7 = var6.a(var3);
                  if(var7 != null) {
                     if(!var7.g(var6)) {
                        return false;
                     }

                     var2 = true;
                  }
               }
            }
         }

         if(!var2) {
            return false;
         } else {
            return true;
         }
      }
   }

   public String d(com.corrodinggames.rts.game.units.a.s var1) {
      if(var1 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var8 = (com.corrodinggames.rts.game.units.a.g)var1;
         return var8.j(var8.b);
      } else {
         com.corrodinggames.rts.game.units.a.c var2 = var1.N();
         Iterator var3 = this.bZ.iterator();

         while(var3.hasNext()) {
            com.corrodinggames.rts.game.units.am var4 = (com.corrodinggames.rts.game.units.am)var3.next();
            if(var4 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var5 = (com.corrodinggames.rts.game.units.y)var4;
               if(var5.cG && this.m(var5)) {
                  com.corrodinggames.rts.game.units.a.s var6 = var5.a(var2);
                  if(var6 != null && var6.g(var5)) {
                     String var7 = var6.j(var5);
                     if(var7 != null) {
                        return var7;
                     }
                  }
               }
            }
         }

         return null;
      }
   }

   public com.corrodinggames.rts.gameFramework.utility.u e(com.corrodinggames.rts.game.units.a.s var1) {
      if(var1 instanceof com.corrodinggames.rts.game.units.a.g) {
         com.corrodinggames.rts.game.units.a.g var2 = (com.corrodinggames.rts.game.units.a.g)var1;
         this.bY.clear();
         if(var2.b != null) {
            this.bY.a((com.corrodinggames.rts.game.units.am)var2.b);
         }

         return this.bY;
      } else {
         return this.bZ;
      }
   }

   public String f(com.corrodinggames.rts.game.units.a.s var1) {
      com.corrodinggames.rts.gameFramework.utility.u var2 = this.e(var1);
      com.corrodinggames.rts.game.units.a.c var3 = var1.N();
      String var4 = null;
      boolean var5 = false;
      Iterator var6 = var2.iterator();

      while(var6.hasNext()) {
         com.corrodinggames.rts.game.units.am var7 = (com.corrodinggames.rts.game.units.am)var6.next();
         if(var7 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var8 = (com.corrodinggames.rts.game.units.y)var7;
            if(this.m(var8)) {
               com.corrodinggames.rts.game.units.a.s var9 = var8.a(var3);
               if(var9 != null) {
                  if(var9.B() != null && !var9.B().b((com.corrodinggames.rts.game.units.am)var8)) {
                     String var10 = var9.B().a(var8, 4, true);
                     if(var10 != null) {
                        var4 = var10;
                     }
                  } else {
                     var5 = true;
                  }
               }
            }
         }
      }

      if(var5) {
         return null;
      } else {
         return var4;
      }
   }

   public boolean u() {
      if(this.aX == 0) {
         return false;
      } else {
         Iterator var1 = this.bZ.iterator();

         while(var1.hasNext()) {
            com.corrodinggames.rts.game.units.am var2 = (com.corrodinggames.rts.game.units.am)var1.next();
            if(var2 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
               if(var3.cG) {
                  if(!this.m(var3)) {
                     return false;
                  }

                  ArrayList var4 = var3.N();
                  boolean var5 = false;
                  if(var4 != null) {
                     Iterator var6 = var4.iterator();

                     while(var6.hasNext()) {
                        com.corrodinggames.rts.game.units.a.s var7 = (com.corrodinggames.rts.game.units.a.s)var6.next();
                        if(var7.e() == com.corrodinggames.rts.game.units.a.u.d) {
                           var5 = true;
                        }
                     }
                  }

                  if(!var5) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   public boolean a(com.corrodinggames.rts.game.units.ag var1) {
      Iterator var2 = this.bZ.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.units.am var3 = (com.corrodinggames.rts.game.units.am)var2.next();
         if(var3 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var4 = (com.corrodinggames.rts.game.units.y)var3;
            if(this.m(var4) && this.a(var1, (com.corrodinggames.rts.game.units.am)var4)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean a(com.corrodinggames.rts.game.units.ag var1, com.corrodinggames.rts.game.units.am var2) {
      if(var2 instanceof com.corrodinggames.rts.game.units.y) {
         com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
         if((var1 == com.corrodinggames.rts.game.units.ag.a || var1 == com.corrodinggames.rts.game.units.ag.b) && !com.corrodinggames.rts.gameFramework.l.a(this.bU, 1000L)) {
            return true;
         }

         if(var1 == com.corrodinggames.rts.game.units.ag.c) {
            if(com.corrodinggames.rts.gameFramework.l.B().bx < 10) {
               return false;
            }

            if(!com.corrodinggames.rts.gameFramework.l.a(this.bV, 1000L)) {
               return true;
            }
         }

         if(var3.a(var1)) {
            if(var1 == com.corrodinggames.rts.game.units.ag.a || var1 == com.corrodinggames.rts.game.units.ag.b) {
               this.bU = com.corrodinggames.rts.gameFramework.l.V();
            }

            if(var1 == com.corrodinggames.rts.game.units.ag.c) {
               this.bV = com.corrodinggames.rts.gameFramework.l.V();
            }

            return true;
         }
      }

      return false;
   }

   public void b(float var1, float var2, Point var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      if(!this.C()) {
         if(var4.bQ.quickRally && this.u()) {
            this.b(var1, var2);
         }
      } else {
         com.corrodinggames.rts.gameFramework.e var5 = this.x();
         var5.h = true;
         var5.a(var1, var2);
         this.a(var5);
         if(!this.a(com.corrodinggames.rts.game.units.ag.b)) {
            var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2F);
         }

         com.corrodinggames.rts.gameFramework.d.e var6 = var4.bR.a(var1, var2, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
         if(var6 != null) {
            var6.ap = 8;
            var6.V = 30.0F;
            var6.W = var6.V;
            var6.r = true;
            var6.E = 2.0F;
            var6.G = 2.8F * this.c();
            var6.F = 1.6F * this.c();
            var6.H = true;
         }

         if(var3 != null) {
            Point var7 = var4.bW.b((float)var3.a, (float)var3.b);
            com.corrodinggames.rts.gameFramework.d.e var8 = var4.bR.a((float)var7.a, (float)var7.b, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if(var8 != null) {
               var8.ar = 4;
               var8.ap = 8;
               var8.V = 35.0F;
               var8.W = var6.V;
               var8.r = true;
               var8.E = 2.0F;
               var8.G = 1.3F;
               var8.F = 0.6F;
            }
         }

      }
   }

   public void c(float var1, float var2, Point var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.D && var4.bQ.doubleClickToAttackMove && this.B() && this.C()) {
         this.d(var1, var2, var3);
      } else {
         this.b(var1, var2, var3);
      }

   }

   public void d(float var1, float var2, Point var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var5 = this.x();
      var5.h = true;
      var5.b(var1, var2);
      this.a(var5);
      if(!this.a(com.corrodinggames.rts.game.units.ag.b)) {
         var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2F);
      }

      com.corrodinggames.rts.gameFramework.d.e var6 = var4.bR.a(var1, var2, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var6 != null) {
         var6.aq = 17;
         var6.ap = 2;
         var6.V = 30.0F;
         var6.W = var6.V;
         var6.r = true;
         var6.E = 2.0F;
         var6.Z = 1.0F;
         var6.G = 1.9F * this.c();
         var6.F = 3.5F * this.c();
         var6.H = true;
      }

      if(var3 != null) {
         Point var7 = var4.bW.b((float)var3.a, (float)var3.b);
         com.corrodinggames.rts.gameFramework.d.e var8 = var4.bR.a((float)var7.a, (float)var7.b, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
         if(var8 != null) {
            var8.ar = 4;
            var8.ap = 9;
            var8.V = 35.0F;
            var8.W = var6.V;
            var8.r = true;
            var8.E = 2.0F;
            var8.G = 1.3F;
            var8.F = 0.6F;
         }
      }

   }

   public void v() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var2 = this.x();
      var2.h();
      this.a(var2);
      var1.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2F);
   }

   public void a(com.corrodinggames.rts.game.units.a.s var1, PointF var2, com.corrodinggames.rts.game.units.am var3, com.corrodinggames.rts.gameFramework.e var4) {
      if(var1 instanceof com.corrodinggames.rts.game.units.custom.a.g) {
         com.corrodinggames.rts.game.units.custom.a.g var5 = (com.corrodinggames.rts.game.units.custom.a.g)var1;
         com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      }

   }

   public void b(com.corrodinggames.rts.game.units.a.s var1, float var2, float var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      PointF var5 = new PointF(var2, var3);
      com.corrodinggames.rts.gameFramework.e var6 = this.x();
      if(!var1.I()) {
         this.a(var6, var1);
      } else {
         this.a(var6, var1, false);
      }

      var6.a(var1.N(), var5, (com.corrodinggames.rts.game.units.am)null);
      this.a(var1, var5, (com.corrodinggames.rts.game.units.am)null, var6);
      if(!var1.a(var2, var3)) {
         var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2F);
         com.corrodinggames.rts.gameFramework.d.e var7 = var4.bR.a(var2, var3, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
         if(var7 != null) {
            var7.ap = 9;
            var7.V = 60.0F;
            var7.W = var7.V;
            var7.r = true;
            var7.E = 2.0F;
            var7.G = 3.8F * this.c();
            var7.F = 2.0F * this.c();
            var7.H = true;
            var7.Z = 1.5F;
         }
      }

   }

   public void b(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var3 = this.x();
      this.a(var3);
      var3.d(var1);
      var2.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2F);
      com.corrodinggames.rts.gameFramework.d.e var4 = var2.bR.a(var1.eo, var1.ep, var1.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var4 != null) {
         var4.ap = 12;
         var4.V = 25.0F;
         var4.W = var4.V;
         var4.r = true;
         var4.E = 2.0F;
         var4.H = true;
         var4.G = 1.2F * this.c();
         var4.F = 1.8F * this.c();
      }

   }

   public void b(float var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var4 = this.w();
      this.a(var4);
      PointF var5 = new PointF(var1, var2);
      var4.a(var5);
      var3.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2F);
      com.corrodinggames.rts.gameFramework.d.e var6 = var3.bR.a(var1, var2, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var6 != null) {
         var6.ap = 8;
         var6.V = 65.0F;
         var6.W = var6.V;
         var6.r = true;
         var6.E = 2.0F;
         var6.H = true;
         var6.Z = 2.0F;
         var6.G = 2.0F * this.c();
         var6.F = 1.5F * this.c();
      }

   }

   public void a(float var1, float var2, Point var3, com.corrodinggames.rts.game.units.a.j var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var5.bQ.showMapPingsOnBattlefield && !var5.bQ.showMapPingsOnMinimap) {
         this.b("Cannot send map ping, you have disabled both battlefield and minimap pings in your settings");
      } else {
         com.corrodinggames.rts.gameFramework.e var6 = this.x();
         PointF var7 = new PointF(var1, var2);
         var6.a(var4.N(), var7, (com.corrodinggames.rts.game.units.am)null);
         if(this.bW == 0L || this.bW + 15000L < System.currentTimeMillis()) {
            this.bW = System.currentTimeMillis();
            String var8 = "MAP PING - [i:" + var4.K() + "]";
            var5.bX.l(var8);
         }

      }
   }

   public void a(float var1, float var2, com.corrodinggames.rts.game.n var3, com.corrodinggames.rts.game.units.a.j var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      float var7 = 1.0F;
      int var8 = 7 + var4.a.ordinal();
      if(!var5.bQ.showMapPingsOnBattlefield && !var5.bQ.showMapPingsOnMinimap) {
         if(!this.bX && !var5.cb.j()) {
            this.bX = true;
            this.h.a((String)null, "[WARNING: A player send a map ping, but you have disabled both battlefield and minimap pings in your settings]");
         }

      } else {
         if(var5.bQ.showMapPingsOnBattlefield) {
            com.corrodinggames.rts.gameFramework.d.e var6 = var5.bR.a(var1, var2, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if(var6 != null) {
               var6.aq = 9;
               var6.ap = 6;
               var6.E = 0.7F;
               var6.V = 490.0F;
               var6.W = var6.V;
               var6.r = true;
               var6.S = 6.0F;
               var6.T = 60.0F;
               var6.J -= var6.S;
               var6.G = 2.0F * var7;
               var6.F = var6.G;
               var6.ao = -0.5F;
               var6.H = true;
               if(var3 != null) {
                  var6.x = var3.K();
                  if(com.corrodinggames.rts.gameFramework.l.at()) {
                     var6.B = new LightingColorFilter(var6.x, 0);
                  }
               }
            }

            if(var8 != -1) {
               var6 = var5.bR.a(var1, var2, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
               if(var6 != null) {
                  var6.aq = 9;
                  var6.ap = var8;
                  var6.V = 490.0F;
                  var6.W = var6.V;
                  var6.r = true;
                  var6.E = 1.2F;
                  var6.S = 6.0F;
                  var6.T = 60.0F;
                  var6.J -= var6.S;
                  var6.G = 2.0F * var7;
                  var6.F = var6.G;
                  var6.ao = -0.7F;
                  var6.H = true;
               }
            }
         }

         if(var5.bQ.showMapPingsOnMinimap) {
            Point var9 = var5.bW.b(var1, var2);
            com.corrodinggames.rts.gameFramework.d.e var10 = var5.bR.a((float)var9.a, (float)var9.b, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if(var10 != null) {
               var10.ar = 4;
               var10.aq = 9;
               var10.ap = 6;
               var10.E = 0.8F;
               var10.V = 470.0F;
               var10.W = var10.V;
               var10.r = true;
               var10.J -= 2.0F;
               var10.S = 2.0F;
               var10.T = 60.0F;
               var10.ao = -0.5F;
               if(var3 != null) {
                  var10.x = var3.K();
                  if(com.corrodinggames.rts.gameFramework.l.at()) {
                     var10.B = new LightingColorFilter(var10.x, 0);
                  }
               }

               var10.G = 1.0F;
               var10.F = 1.0F;
            }

            var10 = var5.bR.a((float)var9.a, (float)var9.b, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
            if(var10 != null) {
               var10.ar = 4;
               var10.aq = 9;
               var10.ap = var8;
               var10.V = 470.0F;
               var10.W = var10.V;
               var10.r = true;
               var10.E = 0.8F;
               var10.J -= 2.0F;
               var10.S = 2.0F;
               var10.T = 60.0F;
               if(var3 != null) {
                  ;
               }

               var10.G = 1.0F;
               var10.F = 1.0F;
               var10.ao = -0.7F;
            }
         }

      }
   }

   public com.corrodinggames.rts.gameFramework.e w() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var2 = var1.cf.b(var1.bs);
      if(var1.bX.B) {
         var2.p = var1.bs;
      }

      return var2;
   }

   public com.corrodinggames.rts.gameFramework.e x() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var2 = this.w();
      if(this.a(var1)) {
         var2.e = true;
      }

      return var2;
   }

   public void c(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var3 = this.x();
      var3.a(var1);
      this.a(var3);
      if(!this.a(com.corrodinggames.rts.game.units.ag.a)) {
         var2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0F);
      }

      com.corrodinggames.rts.gameFramework.d.e var4 = var2.bR.a(var1.eo, var1.ep, var1.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var4 != null) {
         var4.b = var1;
         var4.I = 0.0F;
         var4.J = 0.0F;
         var4.K = 0.0F;
         var4.ap = 9;
         var4.V = 35.0F;
         var4.W = var4.V;
         var4.r = true;
         var4.E = 1.5F;
         var4.H = true;
         var4.Z = 0.8F;
         var4.G = 1.9F * this.c();
         var4.F = 3.3F * this.c();
      }

      var4 = var2.bR.a(var1.eo, var1.ep, var1.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var4 != null) {
         var4.b = var1;
         var4.I = 0.0F;
         var4.J = 0.0F;
         var4.K = 0.0F;
         var4.aq = 17;
         var4.ap = 0;
         var4.V = 25.0F;
         var4.W = var4.V;
         var4.r = true;
         var4.E = 1.0F;
         var4.H = true;
         var4.Z = 0.8F;
         var4.G = 2.2F * this.c();
         var4.F = 1.1F * this.c();
      }

   }

   public void d(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var3 = this.x();
      this.a(var3);
      var3.b(var1);
      var2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0F);
      com.corrodinggames.rts.gameFramework.d.e var4 = var2.bR.a(var1.eo, var1.ep, var1.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var4 != null) {
         var4.ap = 10;
         var4.V = 35.0F;
         var4.W = var4.V;
         var4.r = true;
         var4.E = 2.0F;
         var4.H = true;
         var4.G = 1.5F * this.c();
         var4.F = 2.2F * this.c();
      }

   }

   public void e(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var3 = this.x();
      this.a(var3);
      var3.c(var1);
      var2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0F);
      com.corrodinggames.rts.gameFramework.d.e var4 = var2.bR.a(var1.eo, var1.ep, var1.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var4 != null) {
         var4.aq = 17;
         var4.ap = 1;
         var4.V = 40.0F;
         var4.W = var4.V;
         var4.r = true;
         var4.E = 1.0F;
         var4.H = true;
         var4.Z = 0.0F;
         var4.G = 1.2F * this.c();
         var4.F = 1.9F * this.c();
      }

   }

   public void a(float var1, float var2, float var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      var4.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.2F);
      com.corrodinggames.rts.gameFramework.d.e var5 = var4.bR.a(var1, var2, var3, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var5 != null) {
         var5.aq = 9;
         var5.ap = 14;
         var5.V = 10.0F;
         var5.W = var5.V;
         var5.r = true;
         var5.E = 2.0F;
         var5.Z = 0.0F;
         var5.G = 1.1F * this.c();
         var5.F = 1.6F * this.c();
         var5.H = true;
      }

   }

   public void a(float var1, float var2, Point var3, boolean var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var6 = this.x();
      this.a(var6);
      var6.c(var1, var2);
      if(!var4) {
         var6.e = true;
      }

      var5.bM.b(com.corrodinggames.rts.gameFramework.a.e.f, 0.2F);
      com.corrodinggames.rts.gameFramework.d.e var7 = var5.bR.a(var1, var2, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var7 != null) {
         var7.aq = 17;
         var7.ap = 0;
         var7.V = 40.0F;
         var7.W = var7.V;
         var7.r = true;
         var7.E = 2.0F;
         var7.Z = 8.0F;
         var7.G = 1.1F * this.c();
         var7.F = 1.9F * this.c();
         var7.H = true;
      }

      if(var3 != null) {
         Point var8 = var5.bW.b((float)var3.a, (float)var3.b);
         com.corrodinggames.rts.gameFramework.d.e var9 = var5.bR.a((float)var8.a, (float)var8.b, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
         if(var9 != null) {
            var9.ar = 4;
            var9.ap = 9;
            var9.V = 35.0F;
            var9.W = var7.V;
            var9.r = true;
            var9.E = 2.0F;
            var9.G = 1.3F;
            var9.F = 0.6F;
         }
      }

   }

   public void f(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var3 = this.x();
      this.a(var3);
      var3.e(var1);
      var2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0F);
      com.corrodinggames.rts.gameFramework.d.e var4 = var2.bR.a(var1.eo, var1.ep, var1.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var4 != null) {
         var4.ap = 11;
         var4.V = 25.0F;
         var4.W = var4.V;
         var4.r = true;
         var4.E = 2.0F;
         var4.H = true;
         var4.G = 1.8F * this.c();
         var4.F = 1.6F * this.c();
      }

   }

   public void g(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var3 = this.x();
      this.a(var3);
      var3.f(var1);
      var2.bM.b(com.corrodinggames.rts.gameFramework.a.e.d, 1.0F);
      com.corrodinggames.rts.gameFramework.d.e var4 = var2.bR.a(var1.eo, var1.ep, var1.eq, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.e);
      if(var4 != null) {
         var4.ap = 11;
         var4.V = 25.0F;
         var4.W = var4.V;
         var4.r = true;
         var4.E = 2.0F;
         var4.H = true;
         var4.G = 1.8F * this.c();
         var4.F = 1.6F * this.c();
      }

   }

   public com.corrodinggames.rts.game.units.am a(float var1, float var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.units.am var5 = null;
      float var6 = -1.0F;
      float var7 = 10.0F / var4.cX;
      float var8 = 5.0F / var4.cX;
      float var9 = 5.0F / var4.cX;
      com.corrodinggames.rts.game.n var10 = this.r();
      Iterator var11 = com.corrodinggames.rts.game.units.am.bE.iterator();

      while(var11.hasNext()) {
         com.corrodinggames.rts.game.units.am var12 = (com.corrodinggames.rts.game.units.am)var11.next();
         if(var3) {
            if(var12.t()) {
               continue;
            }
         } else if(var12.cV()) {
            continue;
         }

         if(!var12.bV && var12.cN == null) {
            float var13 = com.corrodinggames.rts.gameFramework.f.a(var1, var2, var12.eo, var12.ep - var12.eq);
            float var14 = var12.do();
            if(!var12.cG) {
               var14 += var7;
            } else {
               var14 += var8;
            }

            boolean var15 = var10.c(var12.bX);
            if(var15) {
               var14 += var9;
            }

            if(var13 < var14 * var14 && (!var15 || var12.cg()) && (var5 == null || var13 < var6)) {
               var5 = var12;
               var6 = var13;
            }
         }
      }

      if(var5 != null && var5.bX != var4.bs && !var5.cf()) {
         return null;
      } else {
         return var5;
      }
   }

   public void b(float var1, float var2, float var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      Iterator var5 = com.corrodinggames.rts.gameFramework.w.er.iterator();

      while(var5.hasNext()) {
         com.corrodinggames.rts.gameFramework.w var6 = (com.corrodinggames.rts.gameFramework.w)var5.next();
         if(var6 instanceof com.corrodinggames.rts.game.units.am) {
            com.corrodinggames.rts.game.units.am var7 = (com.corrodinggames.rts.game.units.am)var6;
            if(!var7.bV && var7.cN == null && var7.bX == var4.bs) {
               float var8 = com.corrodinggames.rts.gameFramework.f.a(var1, var2, var7.eo, var7.ep - var7.eq);
               if(var8 < var3 * var3) {
                  this.j(var7);
               }
            }
         }
      }

   }

   public void h(com.corrodinggames.rts.game.units.am var1) {
      this.W = null;
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      Iterator var3 = com.corrodinggames.rts.gameFramework.w.er.iterator();

      while(var3.hasNext()) {
         com.corrodinggames.rts.gameFramework.w var4 = (com.corrodinggames.rts.gameFramework.w)var3.next();
         if(var4 instanceof com.corrodinggames.rts.game.units.am) {
            com.corrodinggames.rts.game.units.am var5 = (com.corrodinggames.rts.game.units.am)var4;
            if(!var5.bV && var5.cN == null && var5.bX == var1.bX && var5.s_() && a.a(var5, var1) && (var5.bX == var2.bs || var5.cf())) {
               this.j(var5);
            }
         }
      }

   }

   public void y() {
      this.W = null;
      Iterator var1 = com.corrodinggames.rts.gameFramework.w.er.iterator();

      while(var1.hasNext()) {
         com.corrodinggames.rts.gameFramework.w var2 = (com.corrodinggames.rts.gameFramework.w)var1.next();
         if(var2 instanceof com.corrodinggames.rts.game.units.am) {
            com.corrodinggames.rts.game.units.am var3 = (com.corrodinggames.rts.game.units.am)var2;
            var3.cG = false;
         }
      }

      this.aX = 0;
      ++this.Y;
      this.bZ.clear();
      K();
   }

   public boolean i(com.corrodinggames.rts.game.units.am var1) {
      if(var1.t()) {
         return false;
      } else {
         com.corrodinggames.rts.game.n var2 = com.corrodinggames.rts.gameFramework.l.B().bs;
         if(var2 != null) {
            boolean var3 = var2.c(var1.bX);
            if(var3 && !var1.cg()) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean j(com.corrodinggames.rts.game.units.am var1) {
      if(var1.cG) {
         return true;
      } else if(!this.i(var1)) {
         return false;
      } else {
         this.k(var1);
         this.a(com.corrodinggames.rts.game.units.ag.c, var1);
         return true;
      }
   }

   public void k(com.corrodinggames.rts.game.units.am var1) {
      if(!var1.cG) {
         if(!this.i(var1)) {
            return;
         }

         var1.cG = true;
         var1.cH = com.corrodinggames.rts.gameFramework.l.B().bA;
         ++this.aX;
         if(!(var1 instanceof com.corrodinggames.rts.game.units.h)) {
            ca = var1;
         }

         ++this.Y;
         this.bZ.a(var1);
         K();
      }

   }

   public static com.corrodinggames.rts.gameFramework.i.b z() {
      com.corrodinggames.rts.game.units.am var0 = ca;
      if(var0 == null) {
         return null;
      } else {
         com.corrodinggames.rts.game.units.as var1 = var0.r();
         if(var1 != null && var1 instanceof com.corrodinggames.rts.game.units.custom.l) {
            com.corrodinggames.rts.game.units.custom.l var2 = (com.corrodinggames.rts.game.units.custom.l)var1;
            return var2.J;
         } else {
            return null;
         }
      }
   }

   public void a(com.corrodinggames.rts.game.units.am var1, boolean var2) {
      if(!var2) {
         this.j(var1);
      } else {
         if(var1.cG) {
            this.l(var1);
         } else {
            this.j(var1);
         }

      }
   }

   public void l(com.corrodinggames.rts.game.units.am var1) {
      if(var1.cG) {
         var1.cG = false;
         --this.aX;
         this.bZ.remove(var1);
         ++this.Y;
         K();
      }

   }

   public boolean A() {
      if(this.q() == 0) {
         return false;
      } else {
         Iterator var1 = this.bZ.iterator();

         while(var1.hasNext()) {
            com.corrodinggames.rts.game.units.am var2 = (com.corrodinggames.rts.game.units.am)var1.next();
            if(var2 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
               if(var3.cG && this.m(var3)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean B() {
      if(this.q() == 0) {
         return false;
      } else {
         Iterator var1 = this.bZ.iterator();

         while(var1.hasNext()) {
            com.corrodinggames.rts.game.units.am var2 = (com.corrodinggames.rts.game.units.am)var1.next();
            if(var2 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
               if(var3.cG && this.m(var3) && var3.l()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean C() {
      if(this.q() == 0) {
         return false;
      } else {
         Iterator var1 = this.bZ.iterator();

         while(var1.hasNext()) {
            com.corrodinggames.rts.game.units.am var2 = (com.corrodinggames.rts.game.units.am)var1.next();
            if(var2 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
               if(var3.cG && var3.aS() && this.m(var3)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean D() {
      if(this.q() == 0) {
         return true;
      } else {
         Iterator var1 = this.bZ.iterator();

         while(var1.hasNext()) {
            com.corrodinggames.rts.game.units.am var2 = (com.corrodinggames.rts.game.units.am)var1.next();
            if(var2 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var2;
               if(var3.cG && !var3.i()) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public boolean m(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.cW()?false:(var1.bX == var2.bs?true:(var1.bX != null && var1.bX.b(var2.bs)?true:var2.bv || var2.cb.j()));
   }

   public boolean n(com.corrodinggames.rts.game.units.am var1) {
      if(this.q() == 0) {
         return false;
      } else {
         Iterator var2 = this.bZ.iterator();

         while(var2.hasNext()) {
            com.corrodinggames.rts.game.units.am var3 = (com.corrodinggames.rts.game.units.am)var2.next();
            if(var3 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var4 = (com.corrodinggames.rts.game.units.y)var3;
               if(var4.cG && var4 != var1 && this.m(var4) && var1.d(var4, false)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean o(com.corrodinggames.rts.game.units.am var1) {
      if(this.q() == 0) {
         return false;
      } else {
         Iterator var2 = this.bZ.iterator();

         while(var2.hasNext()) {
            com.corrodinggames.rts.game.units.am var3 = (com.corrodinggames.rts.game.units.am)var2.next();
            if(var3 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var4 = (com.corrodinggames.rts.game.units.y)var3;
               if(var4.cG && var4 != var1 && this.m(var4) && var4.d(var1, false)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean p(com.corrodinggames.rts.game.units.am var1) {
      if(this.q() == 0) {
         return false;
      } else {
         Iterator var2 = this.bZ.iterator();

         while(var2.hasNext()) {
            com.corrodinggames.rts.game.units.am var3 = (com.corrodinggames.rts.game.units.am)var2.next();
            if(var3 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var4 = (com.corrodinggames.rts.game.units.y)var3;
               if(var4.cG && var4 != var1 && this.m(var4) && var4.a(var1)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean q(com.corrodinggames.rts.game.units.am var1) {
      if(this.q() == 0) {
         return false;
      } else {
         Iterator var2 = this.bZ.iterator();

         while(var2.hasNext()) {
            com.corrodinggames.rts.game.units.am var3 = (com.corrodinggames.rts.game.units.am)var2.next();
            if(var3 instanceof com.corrodinggames.rts.game.units.y) {
               com.corrodinggames.rts.game.units.y var4 = (com.corrodinggames.rts.game.units.y)var3;
               if(var4.cG && var4 != var1 && this.m(var4) && com.corrodinggames.rts.game.units.aq.a(var4, var1)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public void E() {}

   public boolean F() {
      return false;
   }

   public void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      this.g.a(var1);
      var1.c(1);
      var1.a(this.ad);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1, boolean var2) {
      this.g.a(var1, var2);
      byte var3 = var1.d();
      if(var3 >= 1) {
         this.ad = var1.f();
      }

   }

   public void a(com.corrodinggames.rts.game.units.y var1, float var2, float var3, float var4, float var5, boolean var6, ArrayList var7, com.corrodinggames.rts.game.units.am var8) {
      com.corrodinggames.rts.gameFramework.l var9 = com.corrodinggames.rts.gameFramework.l.B();
      float var10 = var1.eo;
      float var11 = var1.ep;
      com.corrodinggames.rts.game.units.y var12 = null;
      com.corrodinggames.rts.game.units.am var13 = com.corrodinggames.rts.game.units.am.d(var1.r());
      if(!(var13 instanceof com.corrodinggames.rts.game.units.y)) {
         com.corrodinggames.rts.gameFramework.l.e("buildingBlockoutUnit not OrderableUnit is: " + var13.getClass().getName());
      } else {
         var12 = (com.corrodinggames.rts.game.units.y)var13;
      }

      boolean var14 = false;
      var9.bL.b(var2, var3);
      var2 = (float)var9.bL.T;
      var3 = (float)var9.bL.U;
      var2 += var1.cZ();
      var3 += var1.da();
      var4 += var1.cZ();
      var5 += var1.da();
      float var15 = com.corrodinggames.rts.gameFramework.f.b(var2, var3, var4, var5);
      float var16 = com.corrodinggames.rts.gameFramework.f.d(var2, var3, var4, var5);
      int var17 = 0;

      for(float var18 = 0.0F; var18 <= var15; var18 += (float)var9.bL.p) {
         float var19 = var2 + com.corrodinggames.rts.gameFramework.f.k(var16) * var18;
         float var20 = var3 + com.corrodinggames.rts.gameFramework.f.j(var16) * var18;
         var19 -= var1.cZ();
         var20 -= var1.da();
         var9.bL.b(var19, var20);
         var19 = (float)var9.bL.T;
         var20 = (float)var9.bL.U;
         var19 += var1.cZ();
         var20 += var1.da();
         var1.eo = var19;
         var1.ep = var20;
         boolean var21 = false;
         if((!var14 || var12 == null || !com.corrodinggames.rts.gameFramework.d.a.a(var1, var12) && !var1.r(var12)) && !var21) {
            boolean var22 = this.a(var1, var19, var20, var6, false, var8);
            if(var7 != null && var22) {
               var7.add(new PointF(var19, var20));
            }

            if(var22) {
               ++var17;
               if(var17 >= 29) {
                  return;
               }
            }

            var14 = true;
            if(var12 != null) {
               var12.eo = var19;
               var12.ep = var20;
            }
         }
      }

      var1.eo = var10;
      var1.ep = var11;
   }

   public boolean a(com.corrodinggames.rts.game.units.y var1, float var2, float var3, boolean var4, boolean var5, com.corrodinggames.rts.game.units.am var6) {
      com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
      float var8 = var1.eo;
      float var9 = var1.ep;
      var1.eo = var2;
      var1.ep = var3;
      boolean var10 = var1.c(var7.bs);
      if(com.corrodinggames.rts.gameFramework.d.a.a(var7.bs, var1, this.ad)) {
         var10 = false;
      }

      float var12;
      if(var6 != null && var6 != null && var6 instanceof com.corrodinggames.rts.game.units.y) {
         com.corrodinggames.rts.game.units.y var11 = (com.corrodinggames.rts.game.units.y)var6;
         if(!var11.aR()) {
            var12 = com.corrodinggames.rts.gameFramework.f.a(var11.eo, var11.ep, var1.eo, var1.ep);
            float var13 = var11.f(var1.r());
            boolean var14;
            if(var13 > 800000.0F) {
               var14 = true;
            } else {
               var14 = var12 <= var13 * var13;
            }

            if(!var14) {
               var10 = false;
            }
         }
      }

      boolean var15 = var1.cp;
      var1.cp = true;
      var1.cs = var10;
      var1.ct = !var10;
      var1.cr = var5;
      if(var4) {
         var7.bO.k();
         var7.R();
         var1.d(0.0F);
         var1.c(0.0F);
         var1.a(0.0F, false);
         var12 = var1.m();
         if(var12 > 30.0F) {
            com.corrodinggames.rts.gameFramework.utility.y.a(var1, var12, true, true);
         }

         var1.cb();
         if(!var5) {
            var1.N(-1);
         }

         var7.bO.l();
      }

      var1.eo = var8;
      var1.ep = var9;
      var1.cr = false;
      var1.cp = var15;
      return var10;
   }

   public void G() {
      this.l();
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.dq = true;
      var1.bY.c();
      if(var1.by < 1500 && var1.bS.f != null) {
         var1.dr = true;
      }

      this.k.a(0.0F);
      this.k.c();
   }

   public void H() {
      this.l();
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.dt = true;
      var1.bY.c();
      this.k.a(0.0F);
      this.k.c();
   }

   public void I() {
      this.l();
      this.aa = null;
      this.ac = this.p;
   }

   public void a(String var1, Rect var2, Paint var3, Paint var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      String[] var6 = com.corrodinggames.rts.gameFramework.f.c(var1, '\n');
      int var7 = 0;
      String[] var8 = var6;
      int var9 = var6.length;

      for(int var10 = 0; var10 < var9; ++var10) {
         String var11 = var8[var10];
         Paint var12;
         if(var7 == 0) {
            var12 = var3;
         } else {
            var12 = var4;
         }

         int var13 = d.a(var12);
         var5.bO.a(var11, (float)var2.d(), (float)(var2.b + var13 / 2 + var7 * var13), var12);
         ++var7;
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.a.s var1, boolean var2, com.corrodinggames.rts.game.units.am var3, boolean var4, boolean var5) {
      return this.a(var1, var2, var3, var4, false, -1.0F, var5);
   }

   public boolean a(com.corrodinggames.rts.game.units.a.s var1, boolean var2, com.corrodinggames.rts.game.units.am var3, boolean var4, boolean var5, float var6, boolean var7) {
      com.corrodinggames.rts.gameFramework.l var8 = com.corrodinggames.rts.gameFramework.l.B();
      String var9 = null;
      boolean var10 = false;
      boolean var11 = true;
      if(com.corrodinggames.rts.gameFramework.l.aw()) {
         var11 = false;
      }

      if(var3 != null && var1.l(var3)) {
         var11 = false;
      }

      if(var7) {
         var11 = false;
      }

      boolean var12 = false;
      boolean var13 = false;
      if(a.a(var1)) {
         var12 = true;
         var13 = true;
      }

      String var14;
      if(this.c(var1)) {
         var12 = true;
         var9 = this.bH;
         var14 = this.d(var1);
         if(var14 != null) {
            var9 = var14;
         }
      }

      if(!var12 && var4) {
         var14 = this.d(var1);
         if(var14 != null) {
            var9 = var14;
         }
      }

      if(var9 == null) {
         float var45 = this.g.b(var1);
         if(var45 > 0.0F) {
            var9 = com.corrodinggames.rts.gameFramework.f.h(var45 / 1000.0F);
         }
      }

      var14 = this.f(var1);
      boolean var15 = var14 != null;
      if(var14 != null) {
         ;
      }

      if(var4 && var9 == null && var14 != null) {
         var9 = this.bI.b();
      }

      ae var16 = new ae();
      var16.d = this.aQ;
      var16.e = this.aR;
      Object var17 = null;
      com.corrodinggames.rts.gameFramework.m.ag var18 = null;
      if(var15) {
         var18 = this.aS;
      }

      var16.a(true);
      var1.a(var3, var16, (Paint)var17, var18);
      if(var9 != null) {
         var16.a("\n" + var9, this.aS);
      }

      var16.a(false);
      var1.a(var3, var16);
      if(var13) {
         var16.b();
         var16.a(this.bG, this.aR);
      }

      byte var19 = 20;
      this.bv.a = var19;
      int var20 = (int)(var8.cl - var8.cq - (float)var19);
      this.bv.c = var20;
      boolean var22 = var8.bQ.showActionInfoHoverNearMouse;
      int var21;
      if(var2) {
         var21 = (int)(var8.cp - 40.0F);
      } else {
         var21 = 40;
      }

      if(com.corrodinggames.rts.gameFramework.l.au() && var6 > 0.0F) {
         var21 = (int)var6;
         var10 = true;
      }

      if(com.corrodinggames.rts.gameFramework.l.av() && var22 && var5) {
         var21 = (int)(var8.ag() - 40.0F);
      }

      this.bv.b = var21;
      this.bv.d = this.bv.b;
      boolean var23 = true;
      boolean var24 = true;
      boolean var25 = false;
      byte var26 = 7;
      if(com.corrodinggames.rts.gameFramework.l.av()) {
         if(!var22) {
            var23 = false;
            var24 = false;
         } else if(!var5) {
            var24 = false;
            var23 = true;
            var26 = 20;
         }
      } else if(!var2) {
         var24 = false;
      }

      if(com.corrodinggames.rts.gameFramework.l.av() && !var2 && var22 && !var5) {
         ;
      }

      if(var9 != null) {
         ;
      }

      com.corrodinggames.rts.gameFramework.m.ag var27 = this.aR;
      if(var4) {
         var27 = this.aS;
      }

      aj var28 = var16.a(this.bv.b(), var23);
      float var29 = (float)this.bv.d();
      this.bv.a = (int)(var29 - (float)(var28.b.b() / 2));
      this.bv.c = (int)(var29 + (float)(var28.b.b() / 2));
      this.bv.d = this.bv.b + var28.b.c();
      if(var23) {
         this.bv.a = (int)((float)this.bv.a - (float)var26 * var8.cj);
         this.bv.c = (int)((float)this.bv.c + (float)var26 * var8.cj);
      }

      int var30;
      if(var24) {
         var30 = (int)((float)var20 - 7.0F * var8.cj - (float)this.bv.c);
         this.bv.a(var30, 0);
      }

      this.bw.a(this.bv);
      this.bw.b -= 20;
      this.bw.d += 15;
      var30 = -1;
      if(var3 != null) {
         var30 = var1.b(var3, true);
      }

      if(var3 != null && var11 && var30 != -1) {
         this.bw.d = (int)((float)this.bw.d + 55.0F * var8.cj);
      }

      if((float)this.bw.d > var8.cm) {
         int var31 = (int)(var8.cm - (float)this.bw.d);
         this.bv.a(0, var31);
         this.bw.a(0, var31);
      }

      com.corrodinggames.rts.game.units.as var46 = var1.i();
      if(!var1.D()) {
         var46 = null;
      }

      if(var46 != null && var3 != null) {
         this.bw.b = (int)((float)this.bw.b - 40.0F * var8.cj);
      }

      int var32;
      if(var10) {
         var32 = -this.bv.c();
         this.bv.a(0, var32);
         this.bw.a(0, var32);
      }

      int var33;
      float var47;
      if(var25) {
         var47 = var8.cm - 30.0F;
         var33 = (int)(var47 - (float)this.bw.d);
         this.bw.a(0, var33);
         this.bv.a(0, var33);
      }

      if(this.bw.b < 0) {
         var32 = 0 - this.bw.b;
         this.bw.a(0, var32);
         this.bv.a(0, var32);
      }

      if((float)this.bw.d > var8.cm - 20.0F) {
         var47 = var8.cm - 20.0F;
         var33 = (int)(var47 - (float)this.bw.d);
         this.bw.a(0, var33);
         this.bv.a(0, var33);
      }

      var8.bO.b(this.bw, this.aP);
      var8.bO.b(this.bw, this.aL);
      if(var12) {
         ;
      }

      if(var46 != null && var3 != null) {
         var47 = 30.0F * var8.cj;
         float var48 = 100.0F * var8.cj;
         com.corrodinggames.rts.game.units.ar.a(var46, (float)this.bw.d(), (float)this.bw.b + 22.0F * var8.cj, this.Z, 0.0F, var3.bX, var47, var48, false, false, var1.t(), (com.corrodinggames.rts.game.units.am)null);
      }

      var28.a((float)this.bv.d(), (float)this.bv.b);
      if(var3 != null && var30 != -1 && var11) {
         var47 = var8.cj * 0.5F;
         var33 = (int)(60.0F * var47);
         float var34 = c.b(var3, var1, true);
         if(!var12 || var30 > 0) {
            this.aK.b(-16777216);
            float var35;
            if(var34 != 0.0F) {
               var35 = com.corrodinggames.rts.gameFramework.f.c(var34) * 0.5F - 0.4F;
               var35 = com.corrodinggames.rts.gameFramework.f.b(var35, 0.0F, 1.0F);
               int var36;
               if(var34 > 0.0F) {
                  var36 = Color.a(110, 30, 240, 30);
               } else {
                  var36 = Color.a(110, 240, 30, 30);
               }

               com.corrodinggames.rts.gameFramework.f.a(var36, this.aK.e(), var35);
            }

            var35 = (float)this.bw.d - 65.0F * var47 / 2.0F + (float)(d.b(this.aK) / 2);
            if((double)var34 > 0.5D) {
               ++var35;
            }

            if((double)var34 < -0.5D) {
               var35 += -1.0F;
            }

            var8.bO.a("" + var30, (float)this.bw.d(), var35, this.aK);
         }

         boolean var50 = false;
         boolean var49 = false;
         boolean var37 = !var12 && this.a(var1, true);
         boolean var38 = var30 > 0 && var1.d(var3, true);
         int var39 = (int)((float)this.bw.d() + 60.0F * var47);
         int var40 = (int)((float)this.bw.d - 65.0F * var47);
         this.by.a(var39, var40, var39 + var33, var40 + var33);
         Paint var41;
         if(var37) {
            var41 = this.bf;
         } else {
            var41 = this.bg;
         }

         float var42;
         int var43;
         int var44;
         if(var34 > 0.0F) {
            var42 = com.corrodinggames.rts.gameFramework.f.c(var34) * 0.7F - 0.3F;
            var42 = com.corrodinggames.rts.gameFramework.f.b(var42, 0.0F, 1.0F);
            if(var34 > 0.0F) {
               var43 = Color.a(110, 210, 210, 210);
            } else {
               var43 = Color.a(110, 210, 110, 110);
            }

            var44 = com.corrodinggames.rts.gameFramework.f.a(var43, var41.e(), var42);
            var41 = this.bA;
            var41.b(var44);
         }

         if((double)var34 > 0.5D) {
            this.by.a(0, 1);
         }

         var8.bO.a(this.bh, (float)this.by.a, (float)this.by.b, var41, 0.0F, var47);
         com.corrodinggames.rts.gameFramework.f.a(this.by, (float)this.by.b() * 0.8F);
         if(this.U && !this.T && !var13 && this.by.b((int)this.x, (int)this.y)) {
            this.U = false;
            var50 = true;
         }

         var39 = (int)((float)(this.bw.d() - var33) - 60.0F * var47);
         var40 = (int)((float)this.bw.d - 65.0F * var47);
         this.by.a(var39, var40, var39 + var33, var40 + var33);
         if(var38) {
            var41 = this.bf;
         } else {
            var41 = this.bg;
         }

         if(var34 < 0.0F) {
            var42 = com.corrodinggames.rts.gameFramework.f.c(var34) * 0.7F - 0.3F;
            var42 = com.corrodinggames.rts.gameFramework.f.b(var42, 0.0F, 1.0F);
            if(var34 > 0.0F) {
               var43 = Color.a(110, 210, 210, 210);
            } else {
               var43 = Color.a(110, 210, 110, 110);
            }

            var44 = com.corrodinggames.rts.gameFramework.f.a(var43, var41.e(), var42);
            var41 = this.bA;
            var41.b(var44);
         }

         if((double)var34 < -0.5D) {
            this.by.a(0, 1);
         }

         var8.bO.a(this.bi, (float)this.by.a, (float)this.by.b, var41, 0.0F, var47);
         com.corrodinggames.rts.gameFramework.f.a(this.by, (float)this.by.b() * 0.8F);
         if(this.U && !this.T && this.by.b((int)this.x, (int)this.y)) {
            this.U = false;
            var49 = true;
         }

         byte var51 = 1;
         if((var50 || var49) && var1.g()) {
            if(this.a(var8)) {
               var51 = 5;
            }

            if(this.b(var8)) {
               var51 = 10;
            }
         }

         com.corrodinggames.rts.gameFramework.e var52;
         if(var50) {
            if(var1.g() && var8.bs.x() <= var8.bs.w()) {
               this.b(this.g.al);
            }

            if(var37) {
               var8.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5F);
               c.a(var3, var1, false, true);
            }

            for(var40 = 0; var40 < var51; ++var40) {
               var52 = this.w();
               if(this.a(var8)) {
                  var52.e = true;
               }

               this.a(var52, var1);
               var52.a(var1.z());
               this.a(var1, (PointF)null, (com.corrodinggames.rts.game.units.am)null, var52);
            }
         }

         if(var49) {
            if(var38) {
               c.a(var3, var1, true, true);
               var8.bM.b(com.corrodinggames.rts.gameFramework.a.e.i, 0.5F);
            }

            for(var40 = 0; var40 < var51; ++var40) {
               var52 = this.w();
               this.a(var52, var1);
               var52.g = true;
               var52.a(var1.z());
            }
         }

         if(!var50 && !var49 && this.U && !this.T && !this.bw.b((int)this.x, (int)this.y)) {
            return true;
         }
      }

      return !var11 && com.corrodinggames.rts.gameFramework.l.au() && this.U && !this.T && !this.bw.b((int)this.x, (int)this.y);
   }

   public void a(Rect var1, Paint var2, Paint var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      if(bO) {
         var4.bO.a(this.bl, var1, var3, var1.a, var1.b, 0, 0);
         if(var2 != null) {
            int var5 = var2.f() + 0;
            if(var5 > 255) {
               var5 = 255;
            }

            var2.c(var5);
         }
      }

      if(var2 != null) {
         var4.bO.b(var1, var2);
      }

   }

   public void a(Rect var1, int var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      this.bF.b(var2);
      this.bF.a(Paint$Style.b);
      this.bF.a(1.0F);
      var4.bO.b(var1, this.bF);
      if(this.bN) {
         this.bF.b(Color.a(255, 116, 136, 160));
         byte var5 = 1;
         if(var3 && var1.b() > 100) {
            var5 = 2;
         }

         this.bF.a((float)var5);
         this.bz.a(var1);
         this.bz.d -= var5;
         this.bz.b += var5;
         this.bz.a += var5;
         this.bz.c -= var5;
         var4.bO.b(this.bz, this.bF);
      }

   }

   public void a(int var1, int var2, int var3, int var4, String var5, int var6, Paint var7, boolean var8, com.corrodinggames.rts.gameFramework.f.a.h var9, com.corrodinggames.rts.gameFramework.f.a.i var10) {
      com.corrodinggames.rts.gameFramework.l var11 = com.corrodinggames.rts.gameFramework.l.B();
      this.bx.a(var1, var2, var1 + var3, var2 + var4);
      this.bF.b(var6);
      if(var9 != null) {
         var9.a(var11.bO, this.bx, var10);
      } else if(!var8) {
         this.bF.a(Paint$Style.a);
         var11.bO.b(this.bx, this.bF);
      } else {
         this.a(this.bx, (Paint)null, this.bF);
      }

      if(var9 == null) {
         int var12 = Color.a(255, 0, 0, 0);
         if(bO) {
            var12 = Color.a(100, 0, 0, 0);
         }

         this.a(this.bx, var12, false);
      }

      this.a(var1, var2, var3, var4, var5, var6, var7);
   }

   public void a(int var1, int var2, int var3, int var4, String var5, int var6, Paint var7) {
      com.corrodinggames.rts.gameFramework.l var8 = com.corrodinggames.rts.gameFramework.l.B();
      this.bx.a(var1, var2, var1 + var3, var2 + var4);
      if(com.corrodinggames.rts.gameFramework.l.aW) {
         var8.bO.a(var5, (float)this.bx.d(), (float)(this.bx.e() + var8.bO.a(var5, var7) / 2), var7);
      } else {
         var8.bO.a(var5, (float)this.bx.d(), (float)this.bx.e() - (var7.l() + var7.m()) / 2.0F, var7);
      }

   }

   public boolean J() {
      return !this.T;
   }

   public boolean a(int var1, int var2, int var3, int var4, String var5, i var6, boolean var7, int var8) {
      return this.a(var1, var2, var3, var4, var5, var6, var7, var8, this.aC, false, (com.corrodinggames.rts.gameFramework.f.a.h)null);
   }

   public boolean b(int var1, int var2, int var3, int var4, String var5, i var6, boolean var7, int var8) {
      return this.a(var1, var2, var3, var4, var5, var6, var7, var8, this.aC, true, (com.corrodinggames.rts.gameFramework.f.a.h)null);
   }

   public boolean a(int var1, int var2, int var3, int var4, String var5, i var6, boolean var7, int var8, Paint var9, com.corrodinggames.rts.gameFramework.f.a.h var10) {
      return this.a(var1, var2, var3, var4, var5, var6, var7, var8, var9, false, var10);
   }

   public boolean a(int var1, int var2, int var3, int var4, String var5, i var6, boolean var7, int var8, Paint var9, boolean var10, com.corrodinggames.rts.gameFramework.f.a.h var11) {
      boolean var12 = this.a(var1, var2, var3, var4, var6);
      boolean var13 = this.a(var1, var2, var3, var4, var6, var7);
      com.corrodinggames.rts.gameFramework.f.a.i var14 = com.corrodinggames.rts.gameFramework.f.a.i.a;
      if(var12) {
         var14 = com.corrodinggames.rts.gameFramework.f.a.i.b;
      }

      this.a(var1, var2, var3, var4, var5, var8, var9, var10, var11, var14);
      return var13;
   }

   public void a(Rect var1) {
      if(var1.b((int)this.z, (int)this.A)) {
         this.L = true;
         this.M = true;
         if(this.V) {
            this.K = true;
         }
      }

   }

   public void a(float var1, float var2, float var3, float var4) {
      this.cc.a((int)var1, (int)var2, (int)(var1 + var3), (int)(var2 + var4));
      this.a(this.cc);
   }

   public boolean a(int var1, int var2, int var3, int var4, i var5, boolean var6) {
      this.a((float)var1, (float)var2, (float)var3, (float)var4);
      this.bx.a(var1, var2, var1 + var3, var2 + var4);
      return (var6 && this.I || this.U) && this.bx.b((int)this.x, (int)this.y);
   }

   public boolean a(int var1, int var2, int var3, int var4, i var5) {
      this.bx.a(var1, var2, var1 + var3, var2 + var4);
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      return com.corrodinggames.rts.gameFramework.l.aw() && var6.bQ.mouseSupport && this.bx.b((int)var6.af(), (int)var6.ag());
   }

   public boolean b(int var1, int var2, int var3, int var4, i var5) {
      this.bx.a(var1, var2, var1 + var3, var2 + var4);
      return this.V && this.bx.b((int)this.x, (int)this.y);
   }

   public float r(com.corrodinggames.rts.game.units.am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.cH < var2.bA && var1.cH + 200 > var2.bA) {
         float var3 = 1.0F - (float)(var2.bA - var1.cH) / 200.0F;
         return var3 * 6.0F;
      } else {
         return com.corrodinggames.rts.gameFramework.l.B().dx;
      }
   }

   public void a(com.corrodinggames.rts.gameFramework.f.a.f var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var1.u_();
      var1.c(var2.co);
      var1.d(var2.cp);
      this.s.a((com.corrodinggames.rts.gameFramework.f.a.l)var1);
   }

   public static void K() {
      ++cd;
      ce = true;
   }

}
