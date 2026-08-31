package com.corrodinggames.rts.game.units;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.an;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ap;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.gameFramework.ay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;

public abstract class am extends ay {

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
   public static final com.corrodinggames.rts.gameFramework.utility.u bE = new com.corrodinggames.rts.gameFramework.utility.u();
   private static final com.corrodinggames.rts.gameFramework.utility.o a = new com.corrodinggames.rts.gameFramework.utility.o();
   public static HashMap bF = new HashMap();
   public static HashMap bG = new HashMap();
   public static HashMap bH = new HashMap();
   public static final Paint bI = new com.corrodinggames.rts.gameFramework.m.ag();
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
   public com.corrodinggames.rts.game.n bX;
   public boolean bY;
   public float bZ = 0.0F;
   public float ca = 0.0F;
   public boolean cb = false;
   public float cc = 0.0F;
   public float cd = 0.0F;
   public float ce = 0.0F;
   public float cf = 0.0F;
   public float cg;
   public float ch;
   public boolean ci;
   public float cj;
   public float ck;
   public float cl;
   public float cm = 1.0F;
   public float cn = 1.0F;
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
   public float cJ = 0.0F;
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
   public float dq = 70.0F;
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
   com.corrodinggames.rts.game.units.custom.e.f dH = new com.corrodinggames.rts.game.units.custom.e.f();
   public com.corrodinggames.rts.game.units.custom.c.c dI = new com.corrodinggames.rts.game.units.custom.c.c();
   com.corrodinggames.rts.game.units.custom.d.b dJ = null;


   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.a(this.bM);
      var1.a(this.bQ);
      var1.a(this.bR);
      var1.a(this.bS);
      var1.a(this.bT);
      var1.a(this.bV);
      var1.a(this.bW);
      var1.a(this.bX);
      var1.a(this.bZ);
      var1.a(this.ca);
      var1.a(this.cc);
      var1.a(this.cd);
      var1.a(this.cf);
      var1.a(this.cg);
      var1.a(this.cj);
      var1.a(this.ck);
      var1.a(this.cl);
      var1.a(this.cm);
      var1.a(this.cp);
      var1.a(this.cs);
      var1.a(this.cu);
      var1.a(this.cv);
      var1.a(this.cK);
      var1.a(this.cL[0].a);
      var1.a(this.cL[0].d);
      var1.a(this.cN);
      var1.c(26);
      var1.a(this.cU);
      var1.a(this.cV);
      var1.a(this.ce);
      var1.a(this.ch);
      int var2 = this.bl();
      var1.a(var2);

      int var3;
      for(var3 = 0; var3 < var2; ++var3) {
         ap var4 = this.cL[var3];
         var1.a(var4.a);
         var1.a(var4.c);
         var1.a(var4.d);
         var1.a(var4.e);
         var1.a(var4.f);
         var1.a(var4.h);
         var1.a(var4.i);
         am var5 = var4.j;
         if(var5 != null && var5.bV) {
            var5 = null;
         }

         var1.a(var5);
         var1.a(this.cM);
      }

      var1.a(this.bs);
      var1.a(this.cx);
      var1.a(this.cy);
      var1.a(this.cz);
      var1.a(this.cA);
      var1.a(this.cq);
      var1.a(this.cr);
      var1.a(this.ct);
      var1.a(this.bN);
      var1.a(this.cB);
      var1.a(this.ci);
      var1.a(this.dF != null);
      if(this.dF != null) {
         var1.a(this.dF.length);

         for(var3 = 0; var3 < this.dF.length; ++var3) {
            an var7 = this.dF[var3];
            var1.a(var7.a);
            var1.a(var7.b);
         }
      }

      var1.a(this.cw);
      var1.b(this.bt);
      var1.a(this.cE);
      var1.a(this.cF);
      var1.a(this.bz);
      var1.a(this.bA);
      var1.a(this.bB);
      var1.a(this.bC);
      var1.a(this.bO);
      var1.a(this.bP);
      this.dH.a(var1);
      this.dI.a(var1);
      var1.b((am)this.cO);
      short var6 = -1;
      if(this.cO != null && this.cP != null) {
         var6 = this.cP.a();
      }

      var1.a(var6);
      var1.a(this.cQ);
      VariableScope.writeOutUnitOrPlaceholder(var1, this.bu);
      VariableScope.writeOutUnitOrPlaceholder(var1, this.bv);
      VariableScope.writeOut(var1, this.bw);
      com.corrodinggames.rts.game.units.custom.d.b.a(var1, this.bx);
      com.corrodinggames.rts.game.units.custom.d.b.a(var1, this.by);
      var1.a(this.cn);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.bM = var1.e();
      this.bQ = var1.o();
      this.bR = var1.o();
      this.bS = var1.g();
      this.bT = var1.e();
      this.bV = var1.e();
      this.bW = var1.i();
      this.b(var1.r());
      this.bZ = var1.g();
      this.ca = var1.g();
      this.cc = var1.g();
      this.cd = var1.g();
      this.cf = var1.g();
      this.cg = var1.g();
      float var2 = var1.g();
      float var3 = var1.g();
      this.cl = var1.g();
      this.cm = var1.g();
      this.cp = var1.e();
      this.cs = var1.e();
      this.o(var1.g());
      this.cv = var1.g();
      this.cK = var1.e();
      this.cL[0].a = var1.g();
      this.cL[0].d = var1.g();
      this.cN = var1.o();
      byte var4 = var1.d();
      if(var4 >= 1) {
         this.cU = var1.f();
         this.cV = var1.g();
      }

      int var6;
      if(var4 >= 2) {
         this.ce = var1.g();
         this.ch = var1.g();
         int var5 = var1.f();
         this.O(var5);

         for(var6 = 0; var6 < var5; ++var6) {
            ap var7 = this.cL[var6];
            var7.a = var1.g();
            var7.c = var1.g();
            var7.d = var1.g();
            var7.e = var1.g();
            var7.f = var1.g();
            if(var4 >= 8) {
               var7.h = var1.g();
               var7.i = var1.g();
               var7.j = var1.o();
            }

            if(var4 >= 12) {
               this.cM = var1.e();
            }
         }
      }

      if(var4 >= 3) {
         this.bs = var1.f();
      }

      if(var4 >= 4) {
         this.cx = var1.g();
         this.cy = var1.g();
         this.cz = var1.g();
         this.cA = var1.g();
      }

      if(var4 >= 5) {
         this.cq = var1.e();
         this.cr = var1.e();
      }

      if(var4 >= 6) {
         this.ct = var1.e();
      }

      if(var4 >= 7) {
         this.bN = var1.e();
      }

      if(var4 >= 9) {
         this.cB = var1.g();
      }

      if(var4 >= 10) {
         this.ci = var1.e();
      }

      if(var4 >= 11) {
         boolean var9 = var1.e();
         if(var9) {
            this.dF = new an[var1.f()];

            for(var6 = 0; var6 < this.dF.length; ++var6) {
               this.dF[var6] = new an();
               an var12 = this.dF[var6];
               var12.a = var1.e();
               var12.b = var1.f();
            }
         }
      }

      if(var4 >= 13) {
         this.cw = var1.g();
      }

      if(var4 >= 14) {
         this.bt = var1.o();
      }

      if(var4 >= 15) {
         this.cE = var1.f();
         this.cF = var1.f();
      }

      if(var4 >= 16) {
         this.bz = var1.f();
         this.bA = var1.f();
         this.bB = var1.f();
      }

      if(var4 >= 17) {
         this.bC = var1.f();
      }

      if(var4 >= 18) {
         this.bO = var1.e();
         this.bP = var1.e();
      }

      if(var4 >= 19) {
         this.dH.a(var1);
         this.dI.a(this, var1);
      }

      if(var4 >= 20) {
         y var10 = var1.p();
         short var13 = var1.v();
         if(var13 != -1) {
            boolean var14 = false;
            if(var10 != null && this instanceof y) {
               com.corrodinggames.rts.game.units.custom.b.n var8 = var10.a(var13);
               if(var8 != null && var10.a((y)this, var8)) {
                  var14 = true;
               }
            }

            if(!var14) {
               this.cj();
            }
         }
      }

      if(var4 >= 21) {
         this.cQ = var1.f();
      }

      if(var4 >= 22) {
         if(var4 < 24) {
            throw new IOException("extension >=22 but <24");
         }

         this.bu = VariableScope.readInUnitOrPlaceholder(var1);
         this.bv = VariableScope.readInUnitOrPlaceholder(var1);
      }

      if(var4 >= 23) {
         this.bw = VariableScope.readIn(var1);
      }

      if(var4 >= 25) {
         this.bx = com.corrodinggames.rts.game.units.custom.d.b.a(var1);
         this.by = com.corrodinggames.rts.game.units.custom.d.b.a(var1);
      }

      if(var4 >= 26) {
         this.cn = var1.g();
      }

      if(this.bV) {
         com.corrodinggames.rts.gameFramework.l var11 = com.corrodinggames.rts.gameFramework.l.B();
         bE.remove(this);
         var11.cc.a(this);
      }

      super.a(var1);
   }

   public static strictfp com.corrodinggames.rts.gameFramework.m.e a(com.corrodinggames.rts.gameFramework.m.e var0) {
      return a(var0, var0.m(), var0.l());
   }

   public static strictfp com.corrodinggames.rts.gameFramework.m.e a(com.corrodinggames.rts.gameFramework.m.e var0, int var1, int var2) {
      com.corrodinggames.rts.gameFramework.m.e var3 = var0.a(var1, var2, false);
      var0.x();
      var3.j();
      int var4 = var3.m();
      int var5 = var3.l();

      for(int var6 = 0; var6 < var4; ++var6) {
         for(int var7 = 0; var7 < var5; ++var7) {
            int var8 = var0.a(var6, var7);
            var3.a(var6, var7, Color.a(Color.a(var8), 0, 0, 0));
         }
      }

      var3.p();
      var3.s();
      var0.y();
      var3.a("shadow:" + var0.a());
      var3.n = true;
      return var3;
   }

   public static strictfp com.corrodinggames.rts.gameFramework.utility.o bF() {
      a.a();
      return a;
   }

   public static strictfp void bG() {
      a.a();
   }

   public static strictfp void bH() {
      com.corrodinggames.rts.game.units.e.j.dt();
      com.corrodinggames.rts.game.units.d.d.dt();
      com.corrodinggames.rts.game.units.e.h.K();
      com.corrodinggames.rts.game.units.h.f.M();
      com.corrodinggames.rts.game.units.b.b.K();
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      if(var0.as()) {
         Iterator var1 = EnumSet.allOf(ar.class).iterator();

         while(var1.hasNext()) {
            ar var2 = (ar)var1.next();
            var2.b();
         }
      } else {
         com.corrodinggames.rts.game.units.e.b.K();
         com.corrodinggames.rts.game.units.d.p.b();
         com.corrodinggames.rts.game.units.d.r.M();
         al.b();
      }

      ar.t();
   }

   public strictfp boolean bI() {
      return false;
   }

   public strictfp boolean bJ() {
      return false;
   }

   public static strictfp HashMap bK() {
      HashMap var0 = new HashMap();
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      Iterator var2;
      am var4;
      if(var1.as()) {
         var2 = EnumSet.allOf(ar.class).iterator();

         while(var2.hasNext()) {
            ar var3 = (ar)var2.next();
            var4 = var3.a(true);
            var4.a();
            var4.b(com.corrodinggames.rts.game.n.i);
            var4.cp = true;
            var0.put(var3, var4);
         }
      }

      var2 = com.corrodinggames.rts.game.units.custom.l.d.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.units.custom.l var5 = (com.corrodinggames.rts.game.units.custom.l)var2.next();
         var4 = var5.a(true);
         var4.a();
         var4.b(com.corrodinggames.rts.game.n.i);
         var4.cp = true;
         var0.put(var5, var4);
      }

      return var0;
   }

   public static strictfp void bL() {
      bG = bK();
      bH = bK();
      bF = bK();
   }

   public static strictfp am a(as var0) {
      am var1 = (am)bF.get(var0);
      return var1;
   }

   public static strictfp am b(as var0) {
      return c(var0);
   }

   public static strictfp am c(as var0) {
      am var1 = (am)bG.get(var0);
      if(var1 == null) {
         if(com.corrodinggames.rts.game.units.custom.l.b == null) {
            com.corrodinggames.rts.gameFramework.l.e("Could not find:" + var0.i() + " and missing place holder is null");
            return null;
         }

         var1 = (am)bG.get(com.corrodinggames.rts.game.units.custom.l.b);
         if(var1 == null) {
            com.corrodinggames.rts.gameFramework.l.e("name: " + com.corrodinggames.rts.game.units.custom.l.b.M);
            com.corrodinggames.rts.gameFramework.l.e("contains:" + bG.containsKey(com.corrodinggames.rts.game.units.custom.l.b));
            Iterator var2 = bG.keySet().iterator();

            while(var2.hasNext()) {
               as var3 = (as)var2.next();
               com.corrodinggames.rts.gameFramework.l.e("has:" + var3.i());
            }

            com.corrodinggames.rts.gameFramework.l.e("Could not find:" + var0.i() + " and missing place holder could not be found");
         }
      }

      return var1;
   }

   public static strictfp am d(as var0) {
      am var1 = (am)bH.get(var0);
      if(var1 == null) {
         var1 = (am)bH.get(com.corrodinggames.rts.game.units.custom.l.b);
      }

      return var1;
   }

   public static strictfp int bM() {
      int var0 = 0;
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      am var4;
      if(var1.as()) {
         for(Iterator var2 = EnumSet.allOf(ar.class).iterator(); var2.hasNext(); var0 = var0 * 31 + var4.bw()) {
            ar var3 = (ar)var2.next();
            var4 = a((as)var3);
         }
      }

      return var0;
   }

   protected strictfp am(boolean var1) {
      super(var1);
      this.bS();
      if(!var1) {
         this.bL = true;
         bE.a(this);
         a.a((Object)this);
      }

      this.bz = com.corrodinggames.rts.gameFramework.l.B().by;
      this.dz = this.r();
   }

   public strictfp void a() {
      com.corrodinggames.rts.game.n.a(this);
      if(this.bL) {
         bE.remove(this);
         a.b((Object)this);
      }

      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bS.l(this);
      super.a();
   }

   public strictfp float bN() {
      return 3000.0F;
   }

   public strictfp int s(am var1) {
      return 0;
   }

   public strictfp boolean bO() {
      return false;
   }

   public strictfp boolean bP() {
      return false;
   }

   public strictfp float bQ() {
      return -1.0F;
   }

   final strictfp void bR() {
      byte var1 = 1;
      if(this.i()) {
         var1 = 3;
      } else if(this.Q()) {
         var1 = 2;
      }

      if(this.cN != null) {
         var1 = -1;
      }

      if(!this.bT) {
         var1 = -1;
      }

      this.bU = var1;
   }

   public strictfp void o(float var1) {
      this.cu = var1;
   }

   public strictfp void bS() {
      this.O(1);
   }

   public strictfp void O(int var1) {
      int var2 = this.bl();
      if(var2 < var1) {
         var2 = var1;
      }

      int var3;
      if(this.cL == null) {
         var3 = 0;
         this.cL = new ap[var2];
      } else {
         if(this.cL.length >= var2) {
            return;
         }

         var3 = this.cL.length;
         this.cL = (ap[])Arrays.copyOf(this.cL, var2);
      }

      for(int var4 = var3; var4 < this.cL.length; ++var4) {
         this.cL[var4] = new ap();
      }

   }

   public static strictfp void a(Paint var0) {
      a(var0, false);
   }

   public static strictfp void a(Paint var0, boolean var1) {
      if(!com.corrodinggames.rts.gameFramework.l.av() && var1) {
         var0.a(0.0F);
      }

   }

   public strictfp float d(boolean var1) {
      return this.cg + 90.0F;
   }

   public final strictfp boolean bT() {
      return this.cN != null?false:this.cm >= 1.0F;
   }

   public strictfp float x() {
      return this.cu < this.cv?this.cu / this.cv:-1.0F;
   }

   public strictfp boolean bU() {
      return true;
   }

   public strictfp float bV() {
      return this.cm < 1.0F && (this.cO == null || this.cO.cm >= 1.0F)?this.cm:-1.0F;
   }

   public strictfp float bW() {
      return -1.0F;
   }

   public strictfp boolean bX() {
      return false;
   }

   public strictfp int bY() {
      return -1;
   }

   public strictfp int bZ() {
      return -1;
   }

   public strictfp void a(float var1, boolean var2) {
      if(!this.bV && this.cN == null) {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         float var4 = this.cj;
         if(!var2) {
            boolean var5 = false;
            int var6 = this.bY();
            if(this.bV() >= 0.0F) {
               var5 = true;
            }

            if(this.bW() >= 0.0F) {
               var5 = true;
            }

            if(this.cG || var3.bQ.showHp) {
               if(this.x() >= 0.0F) {
                  var5 = true;
               }

               if(var6 >= 0) {
                  var5 = true;
               }
            }

            if(var5) {
               float var7 = this.eo - var3.cw;
               float var8 = this.ep - var3.cx - this.eq;
               float var9 = var4 + 4.0F;
               boolean var10 = true;
               boolean var11 = true;
               byte var12 = 4;
               float var13 = 2.0F * var4;
               float var14;
               if(this.co) {
                  var14 = 1.0F;
               } else {
                  var14 = var3.cX;
               }

               if(var14 < 1.0F) {
                  var3.bO.k();
                  var3.S();
                  var7 *= var3.cX;
                  var8 *= var3.cX;
                  var9 *= var3.cX;
               }

               float var15 = 3.0F;
               int var19;
               int var28;
               if(this.cG || var3.bQ.showHp) {
                  if(this.x() >= 0.0F) {
                     boolean var16 = false;
                     boolean var17 = false;
                     com.corrodinggames.rts.game.units.custom.b.n var18 = this.dn();
                     if(var18 != null) {
                        var16 = var18.p;
                        var17 = var18.q;
                     }

                     if(!var17) {
                        int var20;
                        if(var3.bs.c(this.bX)) {
                           var19 = com.corrodinggames.rts.gameFramework.f.b(200, 183, 44, 44);
                           var20 = com.corrodinggames.rts.gameFramework.f.b(120, 255, 60, 60);
                        } else {
                           var19 = com.corrodinggames.rts.gameFramework.f.b(200, 0, 150, 0);
                           var20 = com.corrodinggames.rts.gameFramework.f.b(120, 0, 230, 0);
                        }

                        Paint var21 = com.corrodinggames.rts.gameFramework.utility.y.a(var19, Paint$Style.a);
                        Paint var22 = com.corrodinggames.rts.gameFramework.utility.y.a(var20, Paint$Style.b);
                        byte var23 = var12;
                        if(var16) {
                           var23 = 1;
                        }

                        dr.a(var7 - var4, var8 + var9, var7 - var4 + var13 * this.x(), var8 + var9 + (float)var23);
                        var3.bO.a(dr, var21);
                        dr.a(var7 - var4, var8 + var9, var7 - var4 + var13, var8 + var9 + (float)var23);
                        var3.bO.a(dr, var22);
                        if(this.cC != 0.0F && this.bU() && var3.bQ.showHpChanges) {
                           float var24 = this.x();
                           float var25 = var24 + -this.cC / this.cv;
                           if(var25 < 0.0F) {
                              var25 = 0.0F;
                           }

                           if(var25 >= 1.0F) {
                              var25 = 1.0F;
                           }

                           int var26 = com.corrodinggames.rts.gameFramework.f.b(100, 232, 208, 26);
                           Paint var27 = com.corrodinggames.rts.gameFramework.utility.y.a(var26, Paint$Style.a);
                           dr.a(var7 - var4 + var13 * var24, var8 + var9, var7 - var4 + var13 * var25, var8 + var9 + (float)var23);
                           var3.bO.a(dr, var27);
                        }
                     }
                  }

                  if(var6 >= 0) {
                     var28 = this.bZ();
                     float var30 = var13;
                     if(var28 > 10) {
                        var30 = var13 + 20.0F;
                     }

                     float var31 = var7 - var30 / 2.0F;
                     float var34 = (float)((int)(var30 / (float)var28 + 0.5F));
                     float var35 = var34 - 2.0F;
                     float var36 = 3.0F;

                     for(int var37 = 1; var37 <= var28; ++var37) {
                        float var38 = var31 + (float)(var37 - 1) * var34;
                        dr.a(var38, var8 + var9 + var15, var38 + var35, var8 + var9 + var15 + 3.0F);
                        if(var6 >= var37) {
                           var3.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(240, 0, 0, 255, Paint$Style.a));
                        }

                        var3.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(110, 0, 0, 210, Paint$Style.b));
                     }

                     var15 += 5.0F;
                  }
               }

               if(this.bW() >= 0.0F) {
                  byte var29 = var12;
                  var12 = 2;
                  int var33 = var12 + 1;
                  boolean var32 = this.bX();
                  dr.a(var7 - var4, var8 + var9 + (float)var33 + var15, var7 - var4 + var13 * this.bW(), var8 + var9 + (float)var33 + (float)var12 + var15);
                  if(var32) {
                     var19 = com.corrodinggames.rts.gameFramework.f.b(185, 103, 117, 119);
                  } else {
                     var19 = com.corrodinggames.rts.gameFramework.f.b(200, 23, 179, 207);
                  }

                  var3.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(var19, Paint$Style.a));
                  dr.a(var7 - var4, var8 + var9 + (float)var33 + var15, var7 - var4 + var13, var8 + var9 + (float)var33 + (float)var12 + var15);
                  if(var32) {
                     var19 = com.corrodinggames.rts.gameFramework.f.b(105, 123, 182, 193);
                  } else {
                     var19 = com.corrodinggames.rts.gameFramework.f.b(120, 45, 211, 241);
                  }

                  var3.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(var19, Paint$Style.b));
                  var15 += (float)var12;
                  var12 = var29;
               }

               if(this.bV() >= 0.0F) {
                  var28 = var12 + 1;
                  dr.a(var7 - var4, var8 + var9 + (float)var28 + var15, var7 - var4 + var13 * this.bV(), var8 + var9 + (float)var28 + (float)var12 + var15);
                  var3.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(200, 0, 0, 150, Paint$Style.a));
                  dr.a(var7 - var4, var8 + var9 + (float)var28 + var15, var7 - var4 + var13, var8 + var9 + (float)var28 + (float)var12 + var15);
                  var3.bO.a(dr, com.corrodinggames.rts.gameFramework.utility.y.a(120, 0, 0, 230, Paint$Style.b));
                  float var10000 = var15 + (float)var12;
               }

               if(var14 < 1.0F) {
                  var3.bO.l();
               }

            }
         }
      }
   }

   public strictfp void d(float var1) {}

   public strictfp void p(float var1) {
      if(!this.bV && this.cN == null) {
         if(this.cG) {
            com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
            if(this.bX == var2.bs || var2.bS.m(this)) {
               if(var2.bQ.showUnitWaypoints && var2.dw <= 40) {
                  ++var2.dw;
                  this.O();
               }

               this.ca();
            }

            if(com.corrodinggames.rts.gameFramework.utility.y.a(this)) {
               this.cb();
            }
         }

      }
   }

   public strictfp void ca() {}

   public strictfp void O() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      au var2 = null;
      au var3 = null;
      if(this instanceof y) {
         y var4 = (y)this;
         int var5 = var4.av();
         float var6 = this.eo;
         float var7 = this.ep;

         for(int var8 = 0; var8 < var5; ++var8) {
            au var9 = var4.k(var8);
            if(var9 != null) {
               if(com.corrodinggames.rts.gameFramework.l.av()) {
                  ds.a(2.0F);
               } else {
                  ds.a(0.0F);
               }

               short var10 = 160;
               if(var9.d() == av.b) {
                  ds.b(Color.a(var10, 180, 0, 0));
               } else if(var9.d() == av.h) {
                  ds.b(Color.a(var10, 180, 180, 0));
               } else if(var9.d() == av.c) {
                  ds.b(Color.a(var10, 0, 0, 180));
               } else if(var9.d() == av.d) {
                  ds.b(Color.a(var10, 0, 0, 180));
               } else if(var9.d() != av.e && var9.d() != av.i) {
                  if(var9.d() == av.g) {
                     ds.b(Color.a(var10, 180, 0, 42));
                  } else if(var9.d() != av.k && var9.d() != av.l) {
                     if(var9.d() == av.j) {
                        ds.b(Color.a(var10, 0, 210, 210));
                        if(var2 == null) {
                           var2 = var9;
                        } else {
                           var3 = var9;
                        }
                     } else {
                        ds.b(Color.a(var10, 0, 180, 0));
                     }
                  } else {
                     ds.b(Color.a(var10, 97, 20, 229));
                  }
               } else {
                  ds.b(Color.a(var10, 0, 180, 180));
               }

               float var11 = var9.g();
               float var12 = var9.h();
               am var13 = var9.i();
               float var15;
               if(var13 != null && var9.f() && !var13.bI() && !var13.d(var1.bs)) {
                  float var14 = 400.0F;
                  var15 = com.corrodinggames.rts.gameFramework.f.d(var6, var7, var11, var12);
                  var11 = var6 + com.corrodinggames.rts.gameFramework.f.k(var15) * var14;
                  var12 = var7 + com.corrodinggames.rts.gameFramework.f.j(var15) * var14;
               }

               var1.bO.a(var6 - var1.cw, var7 - var1.cx, var11 - var1.cw, var12 - var1.cx, ds);
               boolean var22 = false;
               if(var22) {
                  var15 = com.corrodinggames.rts.gameFramework.f.b(var6, var7, var11, var12);
                  float var16 = com.corrodinggames.rts.gameFramework.f.d(var6, var7, var11, var12);
                  float var17 = var1.bS.aT * var15;
                  float var18 = var6 + com.corrodinggames.rts.gameFramework.f.k(var16) * var17;
                  float var19 = var7 + com.corrodinggames.rts.gameFramework.f.j(var16) * var17;
                  dr.a(var18 - 1.0F, var19 - 1.0F, var18 + 1.0F, var19 + 1.0F);
                  dr.a(-var1.cw, -var1.cx);
                  var1.bO.a(dr, ds);
               }

               var6 = var11;
               var7 = var12;
            }
         }
      }

      if(var2 != null && var3 != null && var2 != var3) {
         ds.b(Color.a(50, 0, 210, 210));
         float var20 = var3.g();
         float var21 = var3.h();
         var1.bO.a(var20 - var1.cw, var21 - var1.cx, var2.g() - var1.cw, var2.h() - var1.cx, ds);
      }

   }

   public strictfp void cb() {}

   public strictfp void e(float var1) {
      boolean var2 = false;
      if(this.cJ != 0.0F) {
         this.cJ = com.corrodinggames.rts.gameFramework.f.a(this.cJ, var1);
         if(this.cJ % 15.0F < 7.0F) {
            var2 = true;
         }
      }

      if(this.cG || var2) {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(this.dp()) {
            float var4 = this.eo - var3.cw;
            float var5 = this.ep - var3.cx - this.eq;
            com.corrodinggames.rts.game.n var7 = var3.bs;
            Paint var6;
            if(var7 == this.bX) {
               if(this.ck < 8.0F) {
                  var6 = cX;
               } else {
                  var6 = cY;
               }
            } else if(var7.c(this.bX)) {
               var6 = da;
            } else if(this.bX != null && var3.cb.j()) {
               cW.b(com.corrodinggames.rts.game.n.i(this.bX.r));
               var6 = cW;
            } else {
               var6 = dc;
            }

            if(var2) {
               var6 = db;
            }

            if(this.bI()) {
               if(var6 == cY) {
                  var6 = cZ;
               }

               Rect var8 = this.ce();
               if(var8 != null) {
                  dr.a(var8);
                  dr.b *= (float)var3.bL.o;
                  dr.d *= (float)var3.bL.o;
                  dr.a *= (float)var3.bL.n;
                  dr.c *= (float)var3.bL.n;
                  float var9 = this.db();
                  dr.a(-(this.cZ() - (float)var3.bL.p), -(this.da() - (float)var3.bL.q));
                  com.corrodinggames.rts.gameFramework.f.a(dr, var9);
                  dr.a(var4, var5);
                  float var10 = 11.0F;
                  var3.bO.a(dr.a - var10, dr.b, dr.c + var10, dr.b, var6);
                  var3.bO.a(dr.a - var10, dr.d, dr.c + var10, dr.d, var6);
                  var3.bO.a(dr.a, dr.b - var10, dr.a, dr.d + var10, var6);
                  var3.bO.a(dr.c, dr.b - var10, dr.c, dr.d + var10, var6);
               }
            } else {
               float var11 = this.ck + var3.bS.r(this);
               if(var3.a(var4, var5, var11)) {
                  var3.bO.a(var4, var5, var11, var6);
               }
            }
         }
      }

   }

   public strictfp boolean c(float var1) {
      return true;
   }

   public strictfp Rect cc() {
      return dw;
   }

   public strictfp Rect cd() {
      return dw;
   }

   public strictfp Rect ce() {
      return this.cc();
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return null;
   }

   public strictfp boolean f(float var1) {
      com.corrodinggames.rts.gameFramework.m.e var2 = this.v();
      if(var2 == null) {
         return false;
      } else if(this.bV) {
         return true;
      } else {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         var3.bO.l();
         float var4 = (float)((int)(this.eo - var3.cw));
         float var5 = (float)((int)(this.ep - var3.cx));
         var4 *= var3.cX;
         var5 *= var3.cX;
         Paint var6 = null;
         if(this.cG) {
            var6 = bJ;
         } else {
            var6 = bI;
         }

         var3.bO.a(var2, var4, var5, var6);
         var3.bO.k();
         var3.R();
         return true;
      }
   }

   public strictfp boolean a(com.corrodinggames.rts.gameFramework.l var1) {
      return !var1.cO.b(this.eo, this.ep)?false:(this.cN != null?false:(this.cP != null && (this.cP.I || this.cP.C)?false:this.d(var1.bs)));
   }

   public strictfp boolean c_() {
      return true;
   }

   public final strictfp boolean cf() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return this.d(var1.bs);
   }

   public strictfp boolean d(com.corrodinggames.rts.game.n var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var3 = var2.bL;
      if((this.bX != var1 || this.cO != null) && var3.E && var1.N != null) {
         var3.a(this.eo, this.ep);
         int var4 = var3.T;
         int var5 = var3.U;
         if(var3.c(var4, var5) && var1.N[var4][var5] >= 5) {
            return false;
         }
      }

      return true;
   }

   public strictfp boolean cg() {
      return true;
   }

   public strictfp void f_() {
      if(this.bV) {
         this.bT = false;
      } else {
         this.bT = true;
      }
   }

   public strictfp void a(float var1) {
      if(!this.bV) {
         if(this.cw > 0.0F) {
            if(this.cw > this.cv * 2.0F) {
               this.cw = this.cv * 2.0F;
            }

            this.cw = com.corrodinggames.rts.gameFramework.f.a(this.cw, var1);
         }

         if(this.cu < this.cv * 0.33F && this.eq > -1.0F) {
            com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
            this.do += var1;
            this.dp += var1;
            this.dq += var1;
            com.corrodinggames.rts.gameFramework.d.e var3;
            if(this.do > 10.0F && this.dp < 300.0F && !this.dl()) {
               this.do = 0.0F;
               if(this.el && var2.dd) {
                  var3 = var2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.a);
                  if(var3 != null) {
                     com.corrodinggames.rts.gameFramework.d.f.b(var3, true);
                     var3.I = this.eo;
                     var3.J = this.ep;
                     var3.K = this.eq;
                     var3.P += com.corrodinggames.rts.gameFramework.f.c(-0.1F, 0.1F) + this.cc;
                     var3.Q += com.corrodinggames.rts.gameFramework.f.c(-0.1F, 0.1F) + this.cd;
                     var3.I += com.corrodinggames.rts.gameFramework.f.c(-4.0F, 4.0F);
                     var3.J += com.corrodinggames.rts.gameFramework.f.c(-4.0F, 4.0F);
                  }
               }
            }

            if(this.dq > 30.0F && this.dp < 600.0F && !this.dm()) {
               this.dq = 0.0F;
               var2.bR.a();
               var3 = var2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.a);
               if(var3 != null) {
                  com.corrodinggames.rts.gameFramework.d.f.a(var3, true);
                  var3.I = this.eo;
                  var3.J = this.ep;
                  var3.K = this.eq;
                  var3.P += com.corrodinggames.rts.gameFramework.f.c(-0.1F, 0.1F);
                  var3.Q += com.corrodinggames.rts.gameFramework.f.c(-0.1F, 0.1F);
                  var3.I += com.corrodinggames.rts.gameFramework.f.c(-4.0F, 4.0F);
                  var3.J += com.corrodinggames.rts.gameFramework.f.c(-4.0F, 4.0F);
               }
            }
         } else if(this.dp != 0.0F) {
            this.dp = 0.0F;
         }

         if(this.cC != 0.0F) {
            this.cC = com.corrodinggames.rts.gameFramework.f.a(this.cC, this.cv * this.cD * 0.005F * var1);
            this.cD += var1 + 0.2F * this.cD * var1;
            if(this.cC == 0.0F) {
               this.cD = 0.0F;
            }
         }

         if(this.cu <= 0.0F) {
            this.ch();
         }
      }

   }

   public strictfp float b(am var1, float var2, com.corrodinggames.rts.game.f var3) {
      float var4 = var2;
      float var5 = 1.0F;
      float var6 = 1.0F;
      float var7 = 1.0F;
      if(var3 != null) {
         var5 = var3.ak;
         var6 = var3.al;
         var7 = var3.am;
      }

      float var8;
      float var9;
      if(this.cx < this.cA) {
         var8 = this.cA - this.cx;
         var9 = var2 * var5;
         if(var8 > var9) {
            this.cx += var9;
            var4 = var2 - var9 * var6;
         } else {
            this.cx = this.cA;
            var4 = var2 - var9 * var6;
         }
      }

      if(var4 > 0.0F && this.cu < this.cv) {
         var8 = var4 * var7;
         var9 = this.cv - this.cu;
         if(var9 > var8) {
            this.o(this.cu + var8);
            var4 = 0.0F;
         } else {
            this.o(this.cv);
            float var10000 = var4 - var9;
         }
      }

      return 0.0F;
   }

   public strictfp boolean J() {
      return false;
   }

   public strictfp float a(am var1, float var2, com.corrodinggames.rts.game.f var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.cm < 1.0F) {
         var2 *= 1.75F;
      }

      float var5 = 1.0F;
      float var6 = 1.0F;
      float var7 = 1.0F;
      if(var3 != null) {
         var5 = var3.ak;
         var6 = var3.al;
         var7 = var3.am;
      }

      float var8 = var2;
      float var9 = 0.0F;
      float var10;
      if(this.cz == 0.0F && this.cx > 0.0F) {
         var10 = var2 * var5;
         if(this.cx < var10) {
            var8 = var2 - this.cx * var6;
            var9 += this.cx;
            this.cy += this.cx;
            this.cx = 0.0F;
         } else {
            this.cx -= var10;
            this.cy += var10;
            var9 += var10;
            var8 = var2 - var2 * var6;
         }
      }

      if(var8 > 0.0F) {
         var10 = var8 * var7;
         float var10000;
         if(this.cu < var10) {
            var8 -= this.cu;
            var10000 = var9 + this.cu;
            this.o(0.0F);
            this.cC += this.cu;
         } else {
            this.o(this.cu - var10);
            var10000 = var9 + var10;
            var8 -= var10;
            this.cC -= var10;
         }
      }

      this.bs = var4.by;
      if(var1 != null) {
         this.bt = var1;
      } else {
         this.bt = null;
      }

      this.ch();
      return var8;
   }

   public strictfp am q(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      return (float)var2.by - var1 * 1000.0F < (float)this.bs?this.bt:null;
   }

   public strictfp void ch() {
      if(!this.bV && this.cu <= 0.0F) {
         this.bv();
      }

   }

   public strictfp void n() {}

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      return false;
   }

   public strictfp void bt() {}

   public strictfp void bu() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bS.l(this);
      com.corrodinggames.rts.game.n.a(this);
      if(bE.remove(this)) {
         ;
      }

      this.bV = true;
      this.bW = (long)var1.by;
      if(this.cu > 0.0F) {
         this.cu = 0.0F;
      }

      if(this.cL != null) {
         int var2 = this.bl();

         for(int var3 = 0; var3 < var2; ++var3) {
            this.cL[var3].j = null;
         }
      }

      var1.cc.a(this);
   }

   public strictfp void ci() {
      this.bu();
      this.a();
      this.bt();
   }

   public strictfp void cj() {
      this.cu = -1.0F;
   }

   public strictfp void bv() {
      this.bu();
      if(!this.e()) {
         this.a();
      }

      this.bt();
   }

   public strictfp boolean a(RectF var1) {
      return this.eo + this.cj > var1.a && this.eo - this.cj < var1.c && this.ep + this.cj > var1.b && this.ep - this.cj < var1.d;
   }

   public final strictfp boolean c(float var1, float var2, float var3) {
      float var4 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, var1, var2);
      float var5 = this.cj + var3;
      return var4 < var5 * var5;
   }

   public strictfp boolean t(am var1) {
      float var2 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, var1.eo, var1.ep);
      float var3 = this.cj + var1.cj;
      return var2 < var3 * var3;
   }

   public final strictfp void P(int var1) {
      com.corrodinggames.rts.game.n var2 = com.corrodinggames.rts.game.n.k(var1);
      if(var2 == null) {
         throw new com.corrodinggames.rts.game.b.f("Could not find team with id: " + var1);
      } else {
         this.e(var2);
      }
   }

   public strictfp void e(com.corrodinggames.rts.game.n var1) {
      if(this.bX != var1) {
         if(var1 == null) {
            throw new RuntimeException("Could not set team to null");
         } else {
            if(this.bX != null) {
               com.corrodinggames.rts.game.n.b(this);
               this.bX.d(this);
            }

            this.b(var1);
            com.corrodinggames.rts.game.n.c(this);
            if(var1 != com.corrodinggames.rts.game.n.i) {
               this.c(false);
            }

         }
      }
   }

   public strictfp void b(com.corrodinggames.rts.game.n var1) {
      if(var1 == null) {
         throw new RuntimeException("Could not set team to null");
      } else {
         this.bX = var1;
      }
   }

   public final strictfp void Q(int var1) {
      this.bX = com.corrodinggames.rts.game.n.k(var1);
      if(this.bX == null) {
         throw new com.corrodinggames.rts.game.b.f("Could not find team with id: " + var1);
      } else {
         this.b(this.bX);
      }
   }

   public strictfp ArrayList N() {
      return dx;
   }

   public strictfp int V() {
      return 1;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1, boolean var2) {}

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1, boolean var2, PointF var3, am var4) {
      this.a(var1, var2);
   }

   public strictfp void b(com.corrodinggames.rts.game.units.a.s var1, boolean var2) {}

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1) {}

   public strictfp com.corrodinggames.rts.game.units.a.s a(com.corrodinggames.rts.game.units.a.c var1) {
      ArrayList var2 = this.N();
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         com.corrodinggames.rts.game.units.a.s var5 = (com.corrodinggames.rts.game.units.a.s)var2.get(var3);
         if(var5.d(var1)) {
            return var5;
         }
      }

      return null;
   }

   public strictfp boolean ck() {
      return this.bI()?com.corrodinggames.rts.game.units.a.s.c(this.cm()):false;
   }

   public strictfp boolean cl() {
      return false;
   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return com.corrodinggames.rts.game.units.a.s.i;
   }

   public strictfp float cn() {
      return -1.0F;
   }

   public strictfp boolean co() {
      return false;
   }

   public strictfp void a(ArrayList var1) {
      var1.clear();
   }

   public strictfp com.corrodinggames.rts.game.units.a.c cp() {
      return com.corrodinggames.rts.game.units.a.s.i;
   }

   public strictfp com.corrodinggames.rts.game.units.a.s e(as var1) {
      return null;
   }

   public final strictfp int cq() {
      int var1 = 0;
      Iterator var2 = this.N().iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.units.a.s var3 = (com.corrodinggames.rts.game.units.a.s)var2.next();
         if(var3.b(this) || var3.s()) {
            ++var1;
         }
      }

      return var1;
   }

   public strictfp boolean c(am var1, boolean var2) {
      am var3 = var1.cN;
      y var4 = var1.cO;
      var1.cN = null;
      var1.cO = null;
      boolean var5 = this.d(var1, var2);
      var1.cN = var3;
      var1.cO = var4;
      return var5;
   }

   public strictfp boolean d(am var1, boolean var2) {
      return false;
   }

   public strictfp boolean e(am var1, boolean var2) {
      return false;
   }

   public strictfp boolean cr() {
      return false;
   }

   public strictfp float cs() {
      return 21.0F;
   }

   public abstract ao h();

   public abstract boolean i();

   public strictfp boolean ct() {
      return this.i();
   }

   public abstract boolean Q();

   public abstract boolean aj();

   public abstract boolean ak();

   public strictfp boolean cu() {
      return false;
   }

   public strictfp boolean cv() {
      return false;
   }

   public strictfp boolean P() {
      return false;
   }

   public strictfp int cw() {
      return 1;
   }

   public abstract boolean s_();

   public strictfp int y() {
      return 85;
   }

   public strictfp float f(as var1) {
      int var2 = var1.a(this) + this.y();
      return (float)var2;
   }

   public strictfp int u(am var1) {
      return this.y() + var1.r().a(this);
   }

   public strictfp int v(am var1) {
      return this.y() + var1.r().a(this);
   }

   public strictfp boolean w(am var1) {
      return false;
   }

   public strictfp boolean x(am var1) {
      return false;
   }

   public strictfp float b(am var1) {
      return 1.0F;
   }

   public strictfp float c(am var1) {
      return 0.2F;
   }

   public strictfp boolean y(am var1) {
      boolean var2 = false;
      boolean var3 = var1.g() > 0.0F;
      if(var3) {
         var2 = true;
      }

      return var2;
   }

   public strictfp float z(am var1) {
      float var2 = 5.1F;
      float var3 = this.c(var1) * var2;
      boolean var4 = var1.g() > 0.0F;
      if(var4) {
         var3 = var1.g();
      }

      return var3;
   }

   public strictfp float cx() {
      return 1.0F;
   }

   public strictfp float cy() {
      return 0.0F;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.e.f cz() {
      float var1 = this.cy();
      if(var1 == 0.0F) {
         return com.corrodinggames.rts.game.units.custom.e.f.a;
      } else {
         com.corrodinggames.rts.game.units.custom.e.f var2 = new com.corrodinggames.rts.game.units.custom.e.f();
         var2.b(com.corrodinggames.rts.game.units.custom.e.a.D, (double)var1);
         return var2;
      }
   }

   public strictfp com.corrodinggames.rts.game.units.custom.e.f cA() {
      return com.corrodinggames.rts.game.units.custom.e.f.a;
   }

   public abstract as r();

   public strictfp String cB() {
      return this.r().i() + "(id:" + this.eh + ")";
   }

   public static strictfp String f(am var0, boolean var1) {
      return var0 != null?var0.r().e():"No unit";
   }

   public static strictfp String A(am var0) {
      return var0 != null?var0.c():"<null unit>";
   }

   public strictfp String c() {
      String var1 = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep + " id:" + this.eh + "";
      if(this.bX != null) {
         var1 = var1 + " t:" + this.bX.k;
      }

      if(this.bV) {
         var1 = var1 + " [dead]";
      }

      if(this.ej) {
         var1 = var1 + " [deleted]";
      }

      var1 = var1 + ")";
      return var1;
   }

   public strictfp String cC() {
      String var1 = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep + " id:" + this.eh + "";
      var1 = var1 + ", hp:" + this.cu + ", dead:" + this.bV + ", deleted:" + this.ej + " tags:" + this.de();
      if(this.bX != null) {
         var1 = var1 + " t:" + this.bX.k;
      }

      var1 = var1 + ")";
      return var1;
   }

   public strictfp float cD() {
      return 1.0F;
   }

   public strictfp RectF cE() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      float var2 = this.cD();
      dA.a(this.eo - this.eu * var2 - var1.cw, this.ep - this.ev * var2 - var1.cx, this.eo + this.eu * var2 - var1.cw, this.ep + this.ev * var2 - var1.cx);
      return dA;
   }

   public strictfp RectF cF() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      RectF var2 = dA;
      float var3 = var1.cw;
      float var4 = var1.cx;
      float var5 = this.eu;
      float var6 = this.ev;
      var2.a = this.eo - var5 - var3;
      var2.c = this.eo + var5 - var3;
      var2.b = this.ep - var6 - var4;
      var2.d = this.ep + var6 - var4;
      return var2;
   }

   public strictfp boolean cG() {
      return false;
   }

   public strictfp Rect a_(boolean var1) {
      byte var2 = 0;
      byte var3 = 0;
      dC.a = var2;
      dC.b = var3;
      dC.c = var2 + this.es;
      dC.d = var3 + this.et;
      return dC;
   }

   public strictfp Rect a(boolean var1, int var2) {
      byte var3 = 0;
      byte var4 = 0;
      int var5 = var3 + var2 * this.es;
      dC.a(var5, var4, var5 + this.es, var4 + this.et);
      return dC;
   }

   public strictfp Rect a(boolean var1, int var2, int var3) {
      int var4 = this.es;
      int var5 = this.et;
      int var6 = var2 * var4;
      int var7 = var3 * var5;
      dC.a = var6;
      dC.b = var7;
      dC.c = var6 + var4;
      dC.d = var7 + var5;
      return dC;
   }

   public strictfp boolean a(am var1, float var2) {
      return false;
   }

   public strictfp void a_(String var1) {}

   public final strictfp boolean cH() {
      return this.cJ() && this.eq <= 2.0F;
   }

   public strictfp boolean cI() {
      return com.corrodinggames.rts.gameFramework.utility.y.b(this.eo, this.ep);
   }

   public strictfp boolean cJ() {
      return com.corrodinggames.rts.gameFramework.utility.y.c(this.eo, this.ep);
   }

   public strictfp boolean cK() {
      return com.corrodinggames.rts.gameFramework.utility.y.d(this.eo, this.ep);
   }

   public strictfp int bw() {
      byte var1 = 0;
      int var2 = var1 * 31 + (int)this.bN();
      var2 = var2 * 31 + (int)this.cv;
      return var2;
   }

   public strictfp int cL() {
      return this.r().b(this.V());
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b cM() {
      return this.r().d(this.V());
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b cN() {
      return null;
   }

   public strictfp PointF a(float var1, float var2, float var3, float var4, float var5) {
      float var6 = 0.0F;
      if((double)var3 > 0.1D && this.cK) {
         float var7 = 1.0F / var3;

         for(int var8 = 0; var8 < 3; ++var8) {
            PointF var9 = this.m(var6);
            float var10 = com.corrodinggames.rts.gameFramework.f.b(var1, var2, var9.a, var9.b);
            var6 = var10 * var7;
         }
      }

      if(var6 > var4) {
         var6 = var4;
      }

      PointF var11 = this.m(var6);
      float var12 = com.corrodinggames.rts.gameFramework.f.a(var1, var2, var11.a, var11.b);
      if(var5 >= 0.0F && var5 * var5 < var12) {
         float var13 = com.corrodinggames.rts.gameFramework.f.d(var1, var2, var11.a, var11.b);
         var11.a = var1 + com.corrodinggames.rts.gameFramework.f.k(var13) * var5;
         var11.b = var2 + com.corrodinggames.rts.gameFramework.f.j(var13) * var5;
      }

      dD.a(var11);
      return dD;
   }

   strictfp PointF m(float var1) {
      dE.a(this.eo + this.cc * var1, this.ep + this.cd * var1);
      return dE;
   }

   public abstract boolean l();

   public strictfp boolean o() {
      return false;
   }

   public strictfp boolean p() {
      return false;
   }

   public strictfp boolean cO() {
      return false;
   }

   public strictfp void f(com.corrodinggames.rts.game.n var1) {
      if(this.p()) {
         this.b(com.corrodinggames.rts.game.n.i);
      } else {
         this.b(var1);
      }
   }

   public strictfp void B(am var1) {
      if(var1 instanceof h) {
         var1 = null;
      }

      this.bu = var1;
   }

   public strictfp void cP() {}

   public strictfp float g() {
      return 0.0F;
   }

   public strictfp int cQ() {
      return Integer.MAX_VALUE;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.h cR() {
      return null;
   }

   public strictfp boolean g(am var1, boolean var2) {
      return false;
   }

   public strictfp boolean h(am var1, boolean var2) {
      return this.g(var1, var2);
   }

   public strictfp int cS() {
      return 500;
   }

   public strictfp boolean c(y var1) {
      int var2 = this.cQ();
      if(var2 < Integer.MAX_VALUE) {
         int var3 = this.d(var1);
         if(var3 >= var2) {
            return true;
         }
      }

      return false;
   }

   public strictfp int d(y var1) {
      int var2 = 0;
      com.corrodinggames.rts.game.n var3 = var1.bX;
      am[] var4 = bE.a();
      int var5 = 0;

      for(int var6 = bE.size(); var5 < var6; ++var5) {
         am var7 = var4[var5];
         if(var7.bX == var3 && var7 instanceof y) {
            y var8 = (y)var7;
            au var9 = var8.ar();
            if(var9 != null && var9.d() == av.g && var9.h == this && var7 != var1) {
               ++var2;
            }
         }
      }

      return var2;
   }

   public strictfp int e(y var1) {
      int var2 = 0;
      com.corrodinggames.rts.game.n var3 = var1.bX;
      am[] var4 = bE.a();
      int var5 = 0;

      for(int var6 = bE.size(); var5 < var6; ++var5) {
         am var7 = var4[var5];
         if(var7.bX == var3 && var7 instanceof y) {
            y var8 = (y)var7;
            au var9 = var8.ar();
            if(var9 != null && var9.d() == av.d && var9.h == this && var7 != var1) {
               ++var2;
            }
         }
      }

      return var2;
   }

   public strictfp int bl() {
      return 1;
   }

   public strictfp boolean u() {
      return false;
   }

   public strictfp boolean cT() {
      return this.u() || this.cm < 1.0F || this.bX == com.corrodinggames.rts.game.n.h;
   }

   public strictfp boolean cU() {
      return !this.u();
   }

   public strictfp boolean t() {
      return false;
   }

   public strictfp boolean cV() {
      return this.t();
   }

   public strictfp boolean cW() {
      return false;
   }

   public strictfp boolean d(am var1) {
      return true;
   }

   public strictfp void g(com.corrodinggames.rts.game.n var1) {
      if(this.dF == null || this.dF.length != com.corrodinggames.rts.game.n.c) {
         this.dF = new an[com.corrodinggames.rts.game.n.c];

         for(int var2 = 0; var2 < this.dF.length; ++var2) {
            this.dF[var2] = new an();
         }
      }

      an var4 = this.dF[var1.k];
      boolean var3;
      if(this.bV) {
         if(var4.a) {
            var3 = this.d(var1);
            if(var3) {
               var4.a = false;
            }
         }
      } else {
         var3 = this.d(var1);
         if(var3) {
            var4.a = true;
            var4.b = this.V();
         }
      }

   }

   public strictfp void cX() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.bs != null && this.bX != var1.bs && var1.bs.k >= 0 && var1.bs.k < com.corrodinggames.rts.game.n.c) {
         an var2 = this.dF[var1.bs.k];
         if(var2.c != null && var2.c.c) {
            var2.c = null;
         }

         if(var2.c == null && var2.a) {
            boolean var3 = this.d(var1.bs);
            if(!var3) {
               com.corrodinggames.rts.gameFramework.d.a var4 = new com.corrodinggames.rts.gameFramework.d.a();
               var2.c = var4;
               var4.d = this.r();
               var4.g = this.eo;
               var4.h = this.ep;
               var4.n = false;
               var4.e = this.bX;
               var4.f = var2.b;
               var4.j = var1.bs;
               var4.u = this.c_();
               var4.v = this;
            }
         }
      }

   }

   public strictfp PointF cY() {
      dG.a(0.0F, 0.0F);
      return dG;
   }

   public strictfp float cZ() {
      return (float)com.corrodinggames.rts.gameFramework.l.B().bL.p;
   }

   public strictfp float da() {
      return (float)com.corrodinggames.rts.gameFramework.l.B().bL.q;
   }

   public strictfp float db() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return (float)(var1.bL.p + 2) + var1.bS.r(this);
   }

   public strictfp Point a(com.corrodinggames.rts.game.b.b var1, Point var2) {
      var2.a = (int)((this.eo - this.cZ() + 1.0F) * var1.r);
      var2.b = (int)((this.ep - this.da() + 1.0F) * var1.s);
      return var2;
   }

   public strictfp RectF a(com.corrodinggames.rts.game.b.b var1, RectF var2) {
      int var3 = (int)((this.eo - this.cZ() + 1.0F) * var1.r);
      int var4 = (int)((this.ep - this.da() + 1.0F) * var1.s);
      var1.a(var3, var4);
      float var5 = (float)var1.T;
      float var6 = (float)var1.U;
      Rect var7 = this.cd();
      var2.a(var5 + (float)(var7.a * var1.n), var6 + (float)(var7.b * var1.o), var5 + (float)((var7.c + 1) * var1.n), var6 + (float)((var7.d + 1) * var1.o));
      return var2;
   }

   public strictfp void dc() {}

   public strictfp boolean dd() {
      return false;
   }

   public strictfp boolean q() {
      return false;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.h de() {
      return null;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.e.f df() {
      return this.dH;
   }

   public strictfp double a(com.corrodinggames.rts.game.units.custom.e.a var1) {
      return this.dH.a(var1);
   }

   public strictfp com.corrodinggames.rts.game.units.custom.c.c dg() {
      return this.dI;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.h dh() {
      return null;
   }

   public strictfp float bd() {
      return 0.0F;
   }

   public strictfp void di() {}

   public strictfp void dj() {}

   public strictfp boolean dk() {
      return false;
   }

   public strictfp boolean dl() {
      return this.bO();
   }

   public strictfp boolean dm() {
      return this.bO();
   }

   public final strictfp com.corrodinggames.rts.game.units.custom.b.n dn() {
      return this.cP;
   }

   public strictfp String toString() {
      return "unit(id=" + this.eh + ",type=" + this.r().i() + ")";
   }

   public strictfp void r(float var1) {
      boolean var2;
      if(var1 >= 1.0F) {
         var2 = this.cm >= 1.0F;
         if(!var2) {
            com.corrodinggames.rts.game.n.b(this);
            this.cm = 1.0F;
            com.corrodinggames.rts.game.n.c(this);
         }
      } else {
         var2 = this.cm >= 1.0F;
         if(var2) {
            com.corrodinggames.rts.game.n.b(this);
            this.cm = var1;
            com.corrodinggames.rts.game.n.c(this);
         } else {
            this.cm = var1;
         }
      }

   }

   public final strictfp void a(com.corrodinggames.rts.game.units.custom.af var1) {
      this.a(var1, (am)null, (com.corrodinggames.rts.game.units.custom.h)null, (VariableScope)null);
   }

   public final strictfp void a(com.corrodinggames.rts.game.units.custom.af var1, am var2) {
      this.a(var1, var2, (com.corrodinggames.rts.game.units.custom.h)null, (VariableScope)null);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.af var1, am var2, com.corrodinggames.rts.game.units.custom.h var3, VariableScope var4) {}

   public strictfp void h(float var1) {
      this.cg = var1;
   }

   public strictfp int a(com.corrodinggames.rts.game.units.custom.g var1) {
      return 0;
   }

   public strictfp com.corrodinggames.rts.gameFramework.utility.m e(boolean var1) {
      return null;
   }

   public strictfp boolean a(int var1, int var2) {
      return false;
   }

   public strictfp void c(boolean var1) {}

   public strictfp float do() {
      return this.cj;
   }

   public strictfp boolean dp() {
      return true;
   }

   public strictfp void bC() {}

   public final strictfp com.corrodinggames.rts.game.units.custom.d.b dq() {
      return this.dJ;
   }

   public final strictfp am dr() {
      Object var1 = this.cO;
      if(var1 == null && this.cN != null) {
         var1 = this.cN;
      }

      return (am)var1;
   }

   public strictfp void f(float var1, float var2) {
      this.eo = var1;
      this.ep = var2;
      this.c(true);
   }

   static {
      bI.a(true);
      bI.a(255, 195, 195, 195);
      bJ = new com.corrodinggames.rts.gameFramework.m.ag();
      bJ.a(true);
      bK = new LightingColorFilter(Color.a(255, 255, 255), Color.a(100, 100, 100));
      bJ.a(255, 255, 255, 255);
      bJ.a((ColorFilter)bK);
      cW = new Paint();
      cX = new com.corrodinggames.rts.gameFramework.m.ag();
      cY = new com.corrodinggames.rts.gameFramework.m.ag();
      cZ = new com.corrodinggames.rts.gameFramework.m.ag();
      da = new com.corrodinggames.rts.gameFramework.m.ag();
      db = new com.corrodinggames.rts.gameFramework.m.ag();
      dc = new com.corrodinggames.rts.gameFramework.m.ag();
      dd = new Paint();
      de = new Paint();
      df = new Paint();
      dg = new com.corrodinggames.rts.gameFramework.m.ag();
      dh = new com.corrodinggames.rts.gameFramework.m.ag();
      di = new com.corrodinggames.rts.gameFramework.m.ag();
      dj = new com.corrodinggames.rts.gameFramework.m.ag();
      dk = new Paint();
      cW.a(Paint$Style.b);
      cW.a(2.0F);
      a(cW);
      cX.a(180, 0, 255, 0);
      cX.a(Paint$Style.b);
      cX.a(2.0F);
      a(cX, true);
      cY.a(180, 0, 255, 0);
      cY.a(Paint$Style.b);
      cY.a(2.0F);
      a(cY);
      cZ.a(130, 0, 255, 0);
      cZ.a(Paint$Style.b);
      cZ.a(2.0F);
      a(cZ);
      dd.a(70, 0, 255, 0);
      dd.a(Paint$Style.b);
      dd.a(1.0F);
      a(dd);
      da.a(180, 255, 0, 0);
      da.a(Paint$Style.b);
      da.a(2.0F);
      a(da);
      de.a(70, 255, 0, 0);
      de.a(Paint$Style.b);
      de.a(1.0F);
      a(de);
      dc.a(180, 255, 255, 0);
      dc.a(Paint$Style.b);
      dc.a(2.0F);
      a(dc);
      df.a(70, 255, 255, 0);
      df.a(Paint$Style.b);
      df.a(1.0F);
      a(df);
      db.a(180, 255, 255, 255);
      db.a(Paint$Style.b);
      db.a(2.0F);
      a(db);
      dg.a(90, 235, 235, 235);
      dg.a(Paint$Style.b);
      dg.a(1.0F);
      a(dg);
      dh.a(100, 235, 235, 235);
      dh.a(Paint$Style.b);
      dh.a(2.0F);
      a(dh);
      di.a(90, 235, 0, 0);
      di.a(Paint$Style.b);
      di.a(1.0F);
      a(di);
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
