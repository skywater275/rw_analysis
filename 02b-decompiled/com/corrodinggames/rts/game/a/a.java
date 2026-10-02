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
import com.corrodinggames.rts.game.a.n;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.aq;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.d.t;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class a extends com.corrodinggames.rts.game.n {

   public static boolean as;
   final int at;
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
   int aJ;
   boolean aK;
   float aL;
   float aM;
   float aN;
   float aO;
   float aP;
   float aQ;
   float aR;
   float aS;
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
   boolean bd;
   boolean be;
   boolean bf;
   com.corrodinggames.rts.game.units.f bg;
   boolean bh;
   boolean bi;
   boolean bj;
   boolean bk;
   int bl;
   ConcurrentLinkedQueue bm;
   ArrayList bn;
   PointF bo;
   Paint bp;
   ArrayList bq;
   d br;
   d bs;
   d bt;
   d bu;
   d bv;
   d bw;
   d bx;
   d by;
   d bz;
   d bA;
   d bB;
   d bC;
   d bD;
   public c bE;
   int bF;
   public float bG;
   ArrayList bH;
   private static ArrayList bK = new ArrayList();
   public static final com.corrodinggames.rts.gameFramework.utility.u bI = new com.corrodinggames.rts.gameFramework.utility.u();
   public final com.corrodinggames.rts.gameFramework.utility.m bJ;


   public boolean ac() {
      int var1 = this.ag();
      return this.ag() == 3 || var1 > 300;
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
      com.corrodinggames.rts.gameFramework.k.l var1 = com.corrodinggames.rts.gameFramework.l.B().bU;
      return var1.A.i > 3000;
   }

   public boolean ai() {
      return this.ah()?true:(this.bh && this.bi?(!this.bj?true:!this.bk):true);
   }

   public boolean aj() {
      return !this.bk?false:this.ai() && this.bi;
   }

   public boolean a(float var1, float var2, o var3, ao var4) {
      if(this.a(var1, var2, var3.S, var3.T, var4)) {
         return true;
      } else {
         for(float var5 = -180.0F; var5 < 180.0F; var5 += 90.0F) {
            float var6 = var3.S + com.corrodinggames.rts.gameFramework.f.k(var5) * var3.U * 0.4F;
            float var7 = var3.T + com.corrodinggames.rts.gameFramework.f.j(var5) * var3.U * 0.4F;
            if(this.a(var1, var2, var6, var7, var4)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean a(float var1, float var2, float var3, float var4, ao var5) {
      if(var5 != ao.d && var5 != ao.a) {
         short var6 = y.b(var1, var2, var5);
         short var7 = y.b(var3, var4, var5);
         if(var6 == -3 || var7 == -3) {
            String var8 = "null";
            if(var5 != null) {
               var8 = var5.name();
            }

            this.d("pathPossible: no isolatedGroups found! (" + var8 + ")");
            com.corrodinggames.rts.gameFramework.l.T();
         }

         return var6 != -1 && var7 != -1?(var6 == -2?false:(var7 == -2?false:var6 == var7)):false;
      } else {
         return true;
      }
   }

   public boolean a(am var1, float var2, float var3) {
      return this.a(var1.eo, var1.ep, var2, var3, var1.h());
   }

   public boolean b(am var1, float var2, float var3) {
      float var4 = 60.0F;
      ao var5 = var1.h();
      return this.a(var1.eo, var1.ep, var2, var3, var5)?true:(this.a(var1.eo, var1.ep, var2 + var4, var3, var5)?true:(this.a(var1.eo, var1.ep, var2 - var4, var3, var5)?true:(this.a(var1.eo, var1.ep, var2, var3 + var4, var5)?true:this.a(var1.eo, var1.ep, var2, var3 - var4, var5))));
   }

   public boolean a(am var1, am var2) {
      return this.b(var1, var2.eo, var2.ep);
   }

   public void a(as var1) {
      var1.a(this.aK);
      var1.a(this.aL);
      var1.a(this.aM);
      var1.a(this.aN);
      var1.a(this.aO);
      var1.a(this.aT);
      var1.a(this.aV);
      var1.a(this.aW);
      var1.a(this.aX);
      var1.a(this.ba);
      var1.a(this.bm.size());
      Iterator var2 = this.bm.iterator();

      o var3;
      while(var2.hasNext()) {
         var3 = (o)var2.next();
         boolean var4 = true;
         byte var7;
         if(var3 instanceof i) {
            var7 = 1;
         } else if(var3 instanceof g) {
            var7 = 2;
         } else if(var3 instanceof n) {
            var7 = 3;
         } else if(var3 instanceof m) {
            var7 = 4;
         } else {
            if(!(var3 instanceof l)) {
               throw new RuntimeException("zone not instance not supported:" + var3.getClass().getName());
            }

            var7 = 5;
         }

         var1.c(var7);
         var1.a(var3.Q);
      }

      var2 = this.bm.iterator();

      while(var2.hasNext()) {
         var3 = (o)var2.next();
         var1.a(var3.Q);
         var3.a(var1);
      }

      var1.c(9);
      var1.a(this.aI);
      var1.a(this.bd);
      var1.a(this.bh);
      var1.a(this.bi);
      var1.a(this.bj);
      var1.a(this.bk);
      var1.a(this.aU);
      var1.a(this.bl);
      var1.a(this.au);
      var1.a(this.av);
      var1.a(this.aw);
      var1.a(this.aY);
      var1.a(this.aJ);
      var1.e();
      var1.a(this.bJ.a);

      for(int var5 = 0; var5 < this.bJ.a; ++var5) {
         com.corrodinggames.rts.game.a.a.a var6 = (com.corrodinggames.rts.game.a.a.a)this.bJ.get(var5);
         var1.a((Enum)var6.a());
         var6.a(var1);
      }

      var1.e();
      super.a(var1);
   }

   public o l(int var1) {
      if(var1 == 1) {
         return new i(this, -1.0F, -1.0F);
      } else if(var1 == 2) {
         return new g(this);
      } else if(var1 == 3) {
         return new n(this);
      } else if(var1 == 4) {
         return new m(this);
      } else if(var1 == 5) {
         return new l(this);
      } else if(var1 == 0) {
         com.corrodinggames.rts.gameFramework.l.b("Found zone type 0, loading PlainZone instead");
         return new m(this);
      } else {
         throw new RuntimeException("Unknown zone type:" + var1);
      }
   }

   public void c(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.aK = var1.e();
      this.aL = var1.g();
      this.aM = var1.g();
      this.aN = var1.g();
      this.aO = var1.g();
      this.aT = var1.g();
      this.aV = var1.f();
      this.aW = var1.g();
      this.aX = var1.e();
      this.ba = var1.f();
      int var2 = var1.f();
      this.bm.clear();
      boolean var3 = false;
      int var4;
      if(var1.b() >= 20) {
         var3 = true;

         for(var4 = 0; var4 < var2; ++var4) {
            byte var5 = var1.d();
            o var6 = this.l(var5);
            var6.Q = var1.f();
         }
      }

      for(var4 = 0; var4 < var2; ++var4) {
         o var10;
         if(!var3) {
            byte var11 = var1.d();
            var10 = this.l(var11);
         } else {
            var10 = this.m(var1.f());
         }

         var10.a(var1);
      }

      byte var9 = var1.d();
      if(var9 >= 1) {
         this.aI = var1.f();
      }

      this.bn.clear();
      this.bn.addAll(this.bm);
      if(var9 >= 2) {
         this.bd = var1.e();
         this.bh = var1.e();
         this.bi = var1.e();
      }

      if(var9 >= 3) {
         this.bj = var1.e();
         this.bk = var1.e();
      }

      if(var9 >= 4) {
         this.aU = var1.g();
      }

      if(var9 >= 5) {
         this.bl = var1.f();
      }

      if(var9 >= 6) {
         this.au = var1.f();
         this.av = var1.f();
         this.aw = var1.f();
      }

      if(var9 >= 7) {
         this.aY = var1.e();
      }

      if(var9 >= 8) {
         this.aJ = var1.f();
      }

      if(var9 >= 9) {
         var1.a("ai-c s");
         this.bJ.clear();
         int var12 = var1.f();

         for(int var13 = 0; var13 < var12; ++var13) {
            com.corrodinggames.rts.game.a.a.b var7 = (com.corrodinggames.rts.game.a.a.b)var1.b(com.corrodinggames.rts.game.a.a.b.class);
            com.corrodinggames.rts.game.a.a.a var8 = var7.a();
            var8.a(var1);
            this.a(var8);
         }

         var1.a("ai-c e");
      }

      super.c(var1);
      this.ak();
   }

   public o m(int var1) {
      Iterator var2 = this.bm.iterator();

      o var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (o)var2.next();
      } while(var3.Q != var1);

      return var3;
   }

   public int a(o var1) {
      return var1 == null?-1:var1.Q;
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
      Iterator var1 = this.bn.iterator();

      while(var1.hasNext()) {
         o var2 = (o)var1.next();
         if(var2 instanceof i) {
            i var3 = (i)var2;
            ++this.ax;
            if(var3.u() >= 2) {
               ++this.ay;
            }

            if(var3.n) {
               ++this.aH;
            }
         }

         if(var2 instanceof g) {
            g var4 = (g)var2;
            if(var4.a) {
               continue;
            }

            if(var4.h) {
               ++this.az;
               if(!var4.v && !var4.d()) {
                  if(!var4.B) {
                     ++this.aA;
                  } else {
                     ++this.aB;
                  }
               }
            } else {
               ++this.aC;
               if(var4.d()) {
                  ++this.aD;
               }

               this.aE += var4.l();
            }
         }

         if(var2 instanceof n) {
            h var5 = (h)var2;
            ++this.aF;
            if(var5.l() > 0) {
               ++this.aG;
            }
         }
      }

   }

   private boolean a(com.corrodinggames.rts.game.units.as var1) {
      am var2 = am.b(var1);
      if(!var2.bI() && var2 instanceof com.corrodinggames.rts.game.units.y && !this.g(var2) && !var2.aj() && ((com.corrodinggames.rts.game.units.y)var2).l()) {
         if(var1 instanceof com.corrodinggames.rts.game.units.custom.l) {
            com.corrodinggames.rts.game.units.custom.l var3 = (com.corrodinggames.rts.game.units.custom.l)var1;
            if(var3.fw || !var3.fs) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public a(int var1) {
      this(var1, true);
   }

   public a(int var1, boolean var2) {
      super(var1, var2);
      this.at = 3000;
      this.aJ = 0;
      this.aR = 0.0F;
      this.aS = 0.0F;
      this.bd = true;
      this.be = true;
      this.bf = false;
      this.bm = new ConcurrentLinkedQueue();
      this.bn = new ArrayList();
      this.bo = new PointF();
      this.bq = new ArrayList();
      this.br = new a$1(this, "attackingUnitsLand");
      this.bs = new a$6(this, "attackingUnitsHover");
      this.bt = new a$7(this, "attackingUnitsAir");
      this.bu = new a$8(this, "attackingUnitsWater");
      this.bv = new a$9(this, "buildingUnits");
      this.bw = new a$10(this, "transportUnits");
      this.bx = new a$11(this, "transportUnitsFlying");
      this.by = new a$12(this, "transportUnitsNonFlying");
      this.bz = new a$13(this, "builderUnits");
      this.bA = new a$2(this, "harvesterUnits");
      this.bB = new a$3(this, "extractorUnits");
      this.bC = new a$4(this, "buildingFactories");
      this.bD = new a$5(this, "buildingFactoriesForBuilders");
      this.bE = new c();
      this.bG = 0.0F;
      this.bH = new ArrayList();
      this.bJ = new com.corrodinggames.rts.gameFramework.utility.m();
      this.av();
   }

   private void av() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.aL = (float)(100 + this.k * 9);
      this.aN = (float)(202 + this.k * 19);
      this.aP = (float)(50 + this.k * 2);
      this.aW = (float)(4200 + this.k * 5);
      this.aT = (float)(3500 + this.k * 5);
      this.aU = (float)(7500 + this.k * 5);
      this.bp = new Paint();
      this.bp.b(Color.a(0, 255, 0));
      this.bp.a(Paint$Style.b);
      this.bp.a(true);
      var1.b(this.bp, 14.0F);
      this.al();
   }

   public void al() {
      Iterator var1 = this.bq.iterator();

      while(var1.hasNext()) {
         d var2 = (d)var1.next();
         var2.b();
      }

   }

   public void d(String var1) {
      com.corrodinggames.rts.gameFramework.l.b("ai_debug(" + this.k + ")", var1);
   }

   public PointF am() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bL.a(com.corrodinggames.rts.gameFramework.f.a(0, var1.bL.C), com.corrodinggames.rts.gameFramework.f.a(0, var1.bL.D));
      this.bo.a((float)var1.bL.T, (float)var1.bL.U);
      return this.bo;
   }

   public PointF an() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.bL.A.size() == 0) {
         return null;
      } else {
         int var2 = com.corrodinggames.rts.gameFramework.f.c(var1.bL.A.size());
         Point var3 = (Point)var1.bL.A.get(var2);
         var1.bL.a(var3.a, var3.b);
         this.bo.a((float)var1.bL.T, (float)var1.bL.U);
         return this.bo;
      }
   }

   public PointF a(float var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      float var4 = -1.0F;
      PointF var5 = new PointF();

      for(int var6 = 0; var6 < var3.bL.A.size(); ++var6) {
         Point var7 = (Point)var3.bL.A.get(var6);
         var3.bL.a(var7.a, var7.b);
         this.bo.a((float)var3.bL.T, (float)var3.bL.U);
         PointF var8 = this.bo;
         float var9 = com.corrodinggames.rts.gameFramework.f.a(var8.a, var8.b, var1, var2);
         if(var9 < var4 || var4 == -1.0F) {
            var4 = var9;
            var5.a(var8);
         }
      }

      if(var4 == -1.0F) {
         return null;
      } else {
         return var5;
      }
   }

   i e(am var1) {
      Iterator var2 = this.bn.iterator();

      while(var2.hasNext()) {
         o var3 = (o)var2.next();
         if(var3 instanceof i) {
            i var4 = (i)var3;
            if(var4.b(var1)) {
               return var4;
            }
         }
      }

      return null;
   }

   i b(float var1, float var2) {
      Iterator var3 = this.bn.iterator();

      while(var3.hasNext()) {
         o var4 = (o)var3.next();
         if(var4 instanceof i) {
            i var5 = (i)var4;
            if(var5.c(var1, var2)) {
               return var5;
            }
         }
      }

      return null;
   }

   i f(am var1) {
      return this.c(var1.eo, var1.ep);
   }

   i c(float var1, float var2) {
      float var3 = -1.0F;
      i var4 = null;
      Iterator var5 = this.bn.iterator();

      while(var5.hasNext()) {
         o var6 = (o)var5.next();
         if(var6 instanceof i) {
            i var7 = (i)var6;
            float var8 = var7.d(var1, var2);
            if(var4 == null || var8 < var3) {
               var3 = var8;
               var4 = var7;
            }
         }
      }

      return var4;
   }

   i a(ao var1, float var2, float var3, boolean var4) {
      float var5 = -1.0F;
      i var6 = null;
      Iterator var7 = this.bn.iterator();

      while(var7.hasNext()) {
         o var8 = (o)var7.next();
         if(var8 instanceof i) {
            i var9 = (i)var8;
            float var10 = var9.d(var2, var3);
            if(this.a(var2, var3, var9, var1) && (!var4 || !var9.t) && (var6 == null || var10 < var5)) {
               var5 = var10;
               var6 = var9;
            }
         }
      }

      return var6;
   }

   public static boolean a(am var0, float var1, float var2, float var3) {
      float var4 = com.corrodinggames.rts.gameFramework.f.a(var0.eo, var0.ep, var1, var2);
      return var4 < var3 * var3;
   }

   private boolean a(PointF var1) {
      if(a((com.corrodinggames.rts.game.n)this, var1.a, var1.b, 290.0F) != null) {
         return false;
      } else {
         i var2 = this.c(var1.a, var1.b);
         if(var2 != null && var2.d(var1.a, var1.b) < 490000.0F) {
            return false;
         } else {
            PointF var3 = this.a(var1.a, var1.b);
            float var4;
            if(var3 != null) {
               var4 = com.corrodinggames.rts.gameFramework.f.a(var1.a, var1.b, var3.a, var3.b);
               if(var4 < 160000.0F) {
                  return false;
               }
            }

            var4 = 60.0F;
            return !y.d(var1.a, var1.b) && !y.d(var1.a + var4, var1.b) && !y.d(var1.a, var1.b + var4) && !y.d(var1.a - var4, var1.b) && !y.d(var1.a, var1.b + var4);
         }
      }
   }

   private boolean b(PointF var1) {
      Iterator var2 = am.bE.iterator();

      while(var2.hasNext()) {
         am var3 = (am)var2.next();
         if(var3.bX != this && var3 instanceof com.corrodinggames.rts.game.units.d.e) {
            if(var3.bX.c((com.corrodinggames.rts.game.n)this) && a(var3, var1.a, var1.b, 300.0F)) {
               return false;
            }

            if(var3.bX.d((com.corrodinggames.rts.game.n)this) && a(var3, var1.a, var1.b, 320.0F)) {
               return false;
            }
         }
      }

      if(b(this, var1.a, var1.b, 360.0F) >= 4) {
         return false;
      } else {
         boolean var4 = true;
         if(a(this, var1.a, var1.b, 360.0F, var4) >= 2) {
            return false;
         } else {
            return true;
         }
      }
   }

   public int a(d var1, b var2) {
      int var3 = 0;

      e var5;
      for(Iterator var4 = var1.c.iterator(); var4.hasNext(); var3 += this.a(var5.a, var2)) {
         var5 = (e)var4.next();
      }

      return var3;
   }

   public int a(com.corrodinggames.rts.game.units.as var1, b var2) {
      return this.a(var1, true, var2);
   }

   public int a(com.corrodinggames.rts.game.units.as var1, boolean var2, b var3) {
      boolean var4 = var1.j();
      Integer var5 = this.bE.a(var4, var1, var2);
      if(var5 != null) {
         return var5.intValue();
      } else {
         int var6 = 0;
         if(var4) {
            var2 = false;
         }

         am[] var7 = am.bE.a();
         int var8 = 0;

         for(int var9 = am.bE.size(); var8 < var9; ++var8) {
            am var10 = var7[var8];
            if(var10.bX == this && (var3 == b.a || !var10.bM)) {
               if(var10.dz == var1) {
                  ++var6;
               }

               if(var2 && var10 instanceof com.corrodinggames.rts.game.units.d.l) {
                  com.corrodinggames.rts.game.units.d.l var11 = (com.corrodinggames.rts.game.units.d.l)var10;
                  var6 += var11.h(var1);
               }
            }
         }

         this.bE.a(var4, var1, var2, Integer.valueOf(var6));
         return var6;
      }
   }

   public int ao() {
      int var1 = 0;
      Iterator var2 = this.bn.iterator();

      while(var2.hasNext()) {
         o var3 = (o)var2.next();
         if(var3 instanceof g) {
            g var4 = (g)var3;
            var1 += var4.G.size();
         }
      }

      return var1;
   }

   public boolean g(am var1) {
      if(var1 instanceof com.corrodinggames.rts.game.units.y) {
         com.corrodinggames.rts.game.units.y var2 = (com.corrodinggames.rts.game.units.y)var1;
         if(var2.cr()) {
            com.corrodinggames.rts.game.units.as var3 = var2.r();
            if(var3 instanceof com.corrodinggames.rts.game.units.custom.l) {
               com.corrodinggames.rts.game.units.custom.l var4 = (com.corrodinggames.rts.game.units.custom.l)var3;
               if(!var4.ft) {
                  return false;
               }
            }

            return true;
         }
      }

      return false;
   }

   public boolean h(am var1) {
      if(var1 instanceof com.corrodinggames.rts.game.units.y) {
         com.corrodinggames.rts.game.units.y var2 = (com.corrodinggames.rts.game.units.y)var1;
         if(!var2.bI() && var2.l() && !this.g(var2) && !var2.aj()) {
            com.corrodinggames.rts.game.units.as var3 = var2.r();
            if(var3 instanceof com.corrodinggames.rts.game.units.custom.l) {
               com.corrodinggames.rts.game.units.custom.l var4 = (com.corrodinggames.rts.game.units.custom.l)var3;
               if(!var4.fs) {
                  return false;
               }
            }

            return true;
         }
      }

      return false;
   }

   public boolean b(am var1, am var2) {
      com.corrodinggames.rts.game.units.y var3;
      if(this.U) {
         if(var1 instanceof com.corrodinggames.rts.game.units.y) {
            var3 = (com.corrodinggames.rts.game.units.y)var1;
            if(var3.aq() && aq.a(var3, var2)) {
               return true;
            }
         }

         return false;
      } else {
         if(this.h(var1) && var1 instanceof com.corrodinggames.rts.game.units.y) {
            var3 = (com.corrodinggames.rts.game.units.y)var1;
            if(aq.a(var3, var2)) {
               return true;
            }
         }

         return false;
      }
   }

   public void i(float var1) {
      if(as && com.corrodinggames.rts.gameFramework.l.B().bl) {
         if(!this.aZ && !this.aX) {
            com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
            am[] var3 = am.bE.a();
            int var4 = 0;

            float var9;
            String[] var10;
            int var11;
            int var12;
            String var13;
            float var14;
            float var15;
            float var16;
            String var21;
            for(int var5 = am.bE.size(); var4 < var5; ++var4) {
               am var6 = var3[var4];
               if(var6.bX == this) {
                  float var7 = 200.0F;
                  if(var2.cN.b((int)(var6.eo - var7), (int)(var6.ep - var7), (int)(var6.eo + var7), (int)(var6.ep + var7))) {
                     if(var6 instanceof com.corrodinggames.rts.game.units.y) {
                        com.corrodinggames.rts.game.units.y var8 = (com.corrodinggames.rts.game.units.y)var6;
                     }

                     var21 = "";
                     var9 = var6.ep - var2.cx - 60.0F;
                     this.bp.b(Color.a(0, 255, 0));
                     if(var6 instanceof com.corrodinggames.rts.game.units.d.e) {
                        var9 -= 80.0F;
                        var21 = var21 + "Base ( Team:" + this.k + " )";
                        var21 = var21 + "\nuseTransportsOnThisMap: " + this.ai();
                        var21 = var21 + "\nuseHoverTransportsOnThisMap: " + this.aj();
                        var21 = var21 + "\nattackingCount: " + this.ba;
                        var21 = var21 + "\ndefendingCount: " + this.bb;
                        var21 = var21 + "\nnumOfUnitsNeedingTransport: " + this.ao();
                        var21 = var21 + "\ntransport: " + this.aG;
                        if(this.ae()) {
                           var21 = var21 + "\nTurtling: true";
                        }

                        this.bp.b(Color.a(255, 255, 255));
                     }

                     if(var21.length() != 0) {
                        var10 = var21.split("\n");
                        var11 = var10.length;

                        for(var12 = 0; var12 < var11; ++var12) {
                           var13 = var10[var12];
                           var14 = var6.eo - var2.cw;
                           var15 = var9;
                           var16 = -this.bp.l() + this.bp.m();
                           var2.bO.k();
                           if(var2.cX > 1.0F) {
                              var2.S();
                              var14 *= var2.cX;
                              var15 = var9 * var2.cX;
                              var16 /= var2.cX;
                           }

                           var2.bO.a(var13, var14, var15, this.bp);
                           var2.bO.l();
                           var9 += var16;
                        }
                     }
                  }
               }
            }

            Iterator var17 = this.bm.iterator();

            while(var17.hasNext()) {
               o var18 = (o)var17.next();
               if(var2.cN.b((int)(var18.S - var18.U), (int)(var18.T - var18.U), (int)(var18.S + var18.U), (int)(var18.T + var18.U))) {
                  this.bp.b(this.K());
                  var2.bO.a(var18.S - var2.cw, var18.T - var2.cx, var18.U + 2.0F, this.bp);
                  int var19 = Color.a(0, 255, 0);
                  String var20 = "";
                  var21 = var18.getClass().getSimpleName();
                  var20 = var20 + "\n" + var21 + " ( Team:" + this.k + " )";
                  var9 = var18.T - var2.cx;
                  if(var18 instanceof i) {
                     var9 -= 50.0F;
                     i var22 = (i)var18;
                     var20 = var20 + "\nState: " + var22.b.name() + "(id:" + var22.Q + ")";
                     var20 = var20 + "\nunsafe: " + var22.f() + " (" + var22.s + ")";
                     var20 = var20 + "\nunsafeBaseTimer: " + var22.v;
                     var20 = var20 + "\nallowedUnits: " + var22.d;
                     if(var22.z != null) {
                        var20 = var20 + "\nlastAttemptedBuilding: " + var22.z.i();
                     }

                     if(var22.A != null) {
                        var20 = var20 + "\nlastAttemptedBuilding-cannotAffordPrice: " + var22.A.a(false, true, 4, true);
                     }

                     if(var22.B != null) {
                        var20 = var20 + "\nlastAttemptedBuilding-cannotAffordBy: " + var22.B.a(false, true, 4, true);
                     }

                     var20 = var20 + "\nlastAttemptedBuildingCount: " + var22.C;
                     var20 = var20 + "\nlastAttemptedBuildingFailed: " + var22.D;
                     var20 = var20 + "\nlastUnitAttempt: " + var22.E + " (" + var22.F + " - " + var22.G + ")";
                     var20 = var20 + "\nbuildBuildingDelay: " + var22.e;
                     var20 = var20 + "\ncredits: " + com.corrodinggames.rts.gameFramework.f.c(this.o) + " (x" + com.corrodinggames.rts.gameFramework.f.g(this.E()) + ")";
                     if(var22.b == j.a) {
                        var20 = var20 + "\nclaimedBaseTimer: " + var22.l;
                     }

                     if(var22.k > 100.0F) {
                        var20 = var20 + "\nabandonedTimer: " + var22.k;
                     }

                     if(var22.g > 0.0F) {
                        var20 = var20 + "\nrequestedBuildersDelay: " + var22.g + " (" + var22.h + ")";
                     }

                     var20 = var20 + "\nBuilders: " + var22.J;
                     var20 = var20 + "\nIdle Builders: " + var22.K;
                  }

                  if(var18 instanceof g) {
                     g var23 = (g)var18;
                     if(var23.c) {
                        var20 = var20 + "\nVIP Mode";
                     }

                     var20 = var20 + "\n" + (var23.b()?"Defensive Type":"Attack Type");
                     var20 = var20 + "\nUnits: " + var23.F.size() + " / " + var23.A;
                     var20 = var20 + "\nStagingForAttack: " + var23.q;
                     var20 = var20 + "\nAttackDelay: " + var23.l;
                     if(var23.u != 0.0F) {
                        var20 = var20 + "\nStagingTimer: " + var23.u;
                     }

                     var20 = var20 + "\nStagingTargetFound: " + var23.r;
                     if(var23.o != 0.0F) {
                        var20 = var20 + "\nattackingFor: " + var23.o;
                     }

                     var20 = var20 + "\ncommonMovement: " + var23.i().name();
                     if(var23.B) {
                        var20 = var20 + " (seaGroup)";
                     }

                     if(var23.G.size() > 0) {
                        var20 = var20 + "\nunitsNeedingTransport:" + var23.G.size();
                     }

                     if(var23.b != null) {
                        var20 = var20 + "\nlast action:" + var23.b;
                     }

                     if(!var23.v && !var23.q) {
                        var20 = var20 + "\nnext move:" + (int)this.k(var23.n) + "s";
                     }
                  }

                  if(var18 instanceof n) {
                     n var24 = (n)var18;
                     var20 = var20 + "\nUnitsWanted: " + var24.l;
                     var20 = var20 + "\nunits: " + var24.F.size();
                     var20 = var20 + "\nreadyToMoveOut: " + var24.q;
                     if(var24.m != null) {
                        var20 = var20 + "\nCurrentlyHelping: " + var24.m.Q;
                     }
                  }

                  if(var18 instanceof l) {
                     l var25 = (l)var18;
                     var20 = var20 + "\nneedsTransportGroup: " + var25.a;
                  }

                  this.bp.b(this.K());
                  var10 = var20.split("\n");
                  var11 = var10.length;

                  for(var12 = 0; var12 < var11; ++var12) {
                     var13 = var10[var12];
                     if(!var13.trim().equals("")) {
                        var14 = var18.S - var2.cw;
                        var15 = var9;
                        var16 = -this.bp.l() + this.bp.m();
                        var2.bO.k();
                        if(var2.cX > 1.0F) {
                           var2.S();
                           var14 *= var2.cX;
                           var15 = var9 * var2.cX;
                           var16 /= var2.cX;
                        }

                        var2.bO.a(var13, var14, var15, this.bp);
                        var2.bO.l();
                        var9 += var16;
                        this.bp.b(var19);
                     }
                  }
               }
            }

         }
      }
   }

   public am e(com.corrodinggames.rts.game.n var1) {
      Iterator var2 = am.bE.iterator();

      am var3;
      do {
         do {
            if(!var2.hasNext()) {
               var2 = am.bE.iterator();

               do {
                  if(!var2.hasNext()) {
                     return null;
                  }

                  var3 = (am)var2.next();
               } while(var3.bX != var1 || !var3.bO);

               return var3;
            }

            var3 = (am)var2.next();
         } while(var3.bX != var1);
      } while(!(var3 instanceof com.corrodinggames.rts.game.units.d.e) && !var3.bP);

      return var3;
   }

   public void a(float var1) {
      super.a(var1);
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(!this.aX && !this.aZ) {
         if(var2.bX.B) {
            if(!var2.bX.C) {
               return;
            }

            if(var2.cb.j()) {
               return;
            }
         }

         if(this.bG > 0.0F) {
            this.bG -= var1;
         } else {
            this.bF = this.C();
            int var4;
            int var5;
            am var6;
            if(this.be && var2.by > 3000) {
               this.be = false;
               am[] var3 = am.bE.a();
               var4 = 0;

               for(var5 = am.bE.size(); var4 < var5; ++var4) {
                  var6 = var3[var4];
                  if(var6 instanceof com.corrodinggames.rts.game.units.f) {
                     this.d("firstRunDelayed: Found damagingBorder");
                     this.bg = (com.corrodinggames.rts.game.units.f)var6;
                     break;
                  }
               }
            }

            if(this.bd) {
               this.bd = false;
               this.bh = true;
               this.bi = true;
               this.bj = true;
               this.bk = true;
               am var10 = this.e((com.corrodinggames.rts.game.n)this);
               if(var10 == null) {
                  this.d("firstRun: no command center found");
               }

               if(var10 != null) {
                  for(var4 = 0; var4 < com.corrodinggames.rts.game.n.c; ++var4) {
                     com.corrodinggames.rts.game.n var13 = com.corrodinggames.rts.game.n.k(var4);
                     if(var13 != null && var13 != this) {
                        var6 = this.e(var13);
                        if(var6 != null) {
                           if(!this.a(var10.eo, var10.ep, var6.eo, var6.ep, ao.b)) {
                              this.bh = false;
                           }

                           if(!this.a(var10.eo, var10.ep, var6.eo, var6.ep, ao.f)) {
                              this.bi = false;
                           }
                        }
                     }
                  }

                  Iterator var12 = var2.bL.A.iterator();

                  while(var12.hasNext()) {
                     Point var15 = (Point)var12.next();
                     PointF var17 = var2.bL.a(var15);
                     if(!this.a(var10.eo, var10.ep, var17.a, var17.b + (float)var2.bL.o, ao.b)) {
                        this.bj = false;
                     }

                     if(!this.a(var10.eo, var10.ep, var17.a, var17.b + (float)var2.bL.o, ao.f)) {
                        this.bk = false;
                     }
                  }
               }
            }

            this.aP += var1;
            this.aQ += var1;
            if(this.aP > 25.0F) {
               this.aP -= 25.0F;
               if(this.aP > 25.0F) {
                  this.aP = 25.0F;
               }

               if(this.aP < -1.0F) {
                  this.aP = -1.0F;
               }

               boolean var11 = false;
               boolean var14 = false;
               Iterator var16 = this.bm.iterator();

               while(var16.hasNext()) {
                  o var18 = (o)var16.next();
                  if(var18 instanceof i) {
                     i var7 = (i)var18;
                     var7.a += this.aQ;
                  }
               }

               for(var5 = 0; var5 < 2; ++var5) {
                  i var19 = null;
                  Iterator var20 = this.bm.iterator();

                  while(var20.hasNext()) {
                     o var8 = (o)var20.next();
                     if(var8 instanceof i) {
                        i var9 = (i)var8;
                        if(var19 == null || var19.a < var9.a) {
                           var19 = var9;
                        }
                     }
                  }

                  if(var19 == null || var19.a < 50.0F) {
                     break;
                  }

                  var19.b(var19.a);
                  var19.d(var19.a);
                  var19.a = 0.0F;
               }

               this.aQ = 0.0F;
            }

            this.aL += var1;
            this.aM += var1;
            if(this.aL > 80.0F) {
               this.n(this.aM);
               this.aL -= 80.0F;
               if(this.aL > 80.0F) {
                  this.aL = 80.0F;
               }

               if(this.aL < -1.0F) {
                  this.aL = -1.0F;
               }

               this.aM = 0.0F;
            }

            this.aN += var1;
            this.aO += var1;
            if(this.aN > 250.0F) {
               this.m(this.aO);
               this.aN -= 250.0F;
               if(this.aN > 250.0F) {
                  this.aN = 250.0F;
               }

               if(this.aN < -1.0F) {
                  this.aN = -1.0F;
               }

               this.aO = 0.0F;
            }

         }
      }
   }

   public float j(float var1) {
      return var1 / 60.0F * 1000.0F;
   }

   public float k(float var1) {
      return var1 / 60.0F;
   }

   public void a(com.corrodinggames.rts.game.units.y var1, com.corrodinggames.rts.game.units.a.c var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.e var4 = var3.cf.a((com.corrodinggames.rts.game.n)this);
      var4.a(var1);
      var4.a(var2);
   }

   public void l(float var1) {
      Iterator var2 = am.bE.iterator();

      while(var2.hasNext()) {
         am var3 = (am)var2.next();
         if(var3.bX == this && var3 instanceof com.corrodinggames.rts.game.units.y && this.i(var3)) {
            com.corrodinggames.rts.game.units.y var4 = (com.corrodinggames.rts.game.units.y)var3;
            boolean var5;
            am var6;
            boolean var7;
            if(var4 instanceof com.corrodinggames.rts.game.units.h.e) {
               var5 = false;
               var6 = var4.ab();
               if(var6 != null && var4.h(var6)) {
                  var5 = !var6.cH();
               }

               var7 = !var4.Q();
               if(var5 && var5 != var7) {
                  this.a(var4, com.corrodinggames.rts.game.units.h.e.j.N());
               }

               if(!var5 && var5 != var7) {
                  this.a(var4, com.corrodinggames.rts.game.units.h.e.k.N());
               }
            }

            if(var4 instanceof com.corrodinggames.rts.game.units.b.c) {
               var5 = true;
               var6 = var4.ab();
               if(var6 != null && var4.h(var6)) {
                  var5 = !var6.Q();
               }

               var7 = !var4.Q();
               if(var5 && var5 != var7) {
                  this.a(var4, com.corrodinggames.rts.game.units.b.c.y.N());
               }

               if(!var5 && var5 != var7) {
                  this.a(var4, com.corrodinggames.rts.game.units.b.c.z.N());
               }
            }

            if(var4.be() == com.corrodinggames.rts.game.units.b.d && var4.aq() && var4.ab() != null) {
               com.corrodinggames.rts.gameFramework.l var9 = com.corrodinggames.rts.gameFramework.l.B();
               com.corrodinggames.rts.gameFramework.e var8 = var9.cf.a((com.corrodinggames.rts.game.n)this);
               var8.a(var4);
               var8.a(var4.ab());
            }
         }
      }

   }

   public com.corrodinggames.rts.game.units.a c(com.corrodinggames.rts.game.units.y var1) {
      if(var1.aS()) {
         boolean var2 = true;
         if(var1.aj()) {
            var2 = false;
         }

         if(this.g(var1)) {
            var2 = false;
         }

         if(var2) {
            if(this.aY) {
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

   public void d(com.corrodinggames.rts.game.units.y var1) {
      Iterator var2 = this.bJ.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.a.a.a var3 = (com.corrodinggames.rts.game.a.a.a)var2.next();
         var3.a(this, var1);
      }

   }

   public void e(com.corrodinggames.rts.game.units.y var1) {
      Iterator var2 = this.bJ.iterator();

      while(var2.hasNext()) {
         com.corrodinggames.rts.game.a.a.a var3 = (com.corrodinggames.rts.game.a.a.a)var2.next();
         var3.b(this, var1);
      }

   }

   public void m(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.bE.b();
      Iterator var3 = this.bJ.iterator();

      while(var3.hasNext()) {
         com.corrodinggames.rts.game.a.a.a var4 = (com.corrodinggames.rts.game.a.a.a)var3.next();
         var4.b(this.j(var1), this);
      }

      int var23 = 0;
      am[] var24 = am.bE.a();
      int var5 = 0;

      int var6;
      am var7;
      com.corrodinggames.rts.game.units.y var8;
      for(var6 = am.bE.size(); var5 < var6; ++var5) {
         var7 = var24[var5];
         if(var7.bX == this && !var7.u()) {
            ++var23;
            if(var7 instanceof com.corrodinggames.rts.game.units.y) {
               var8 = (com.corrodinggames.rts.game.units.y)var7;
               if(!var8.bD) {
                  var8.bD = true;
                  this.d(var8);
               }

               if(var7.cN == null) {
                  i var9 = var8.aC;
                  var8.aC = this.f(var8);
                  if(var8.aC != null && var9 != var8.aC) {
                     if(var8.bI()) {
                        var8.aD = this.a(var7.eo, var7.ep, var8.aC.S, var8.aC.T, ao.b);
                        if(!var8.aD && var8.r().p()) {
                           var8.aD = this.a(var7.eo, var7.ep + 15.0F, var8.aC.S, var8.aC.T, ao.b);
                        }
                     } else {
                        var8.aD = this.a(var7.eo, var7.ep, var8.aC.S, var8.aC.T, ao.b);
                     }
                  }
               }
            }
         }
      }

      this.l(var1);
      Iterator var25 = am.bE.iterator();

      am var27;
      while(var25.hasNext()) {
         var27 = (am)var25.next();
         if(var27.bX == this && var27 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var29 = (com.corrodinggames.rts.game.units.y)var27;
            com.corrodinggames.rts.game.units.a var33 = this.c(var29);
            if(var29.P != var33 && this.i(var29)) {
               com.corrodinggames.rts.gameFramework.e var35 = var2.cf.a((com.corrodinggames.rts.game.n)this);
               var35.a(var29);
               var35.a(var33);
            }

            if(var29.aj() && var29.dd() && var29.aB == null && this.i(var29)) {
               g.a(this, var29);
            }
         }
      }

      if(var23 == 0 && !this.U) {
         this.aZ = true;
      }

      this.aU = com.corrodinggames.rts.gameFramework.f.a(this.aU, var1);
      this.aT = com.corrodinggames.rts.gameFramework.f.a(this.aT, var1);
      if(this.ac()) {
         this.aT = com.corrodinggames.rts.gameFramework.f.a(this.aT, 4.0F * var1);
      }

      int var26;
      Iterator var28;
      o var31;
      PointF var32;
      i var34;
      if(this.aT == 0.0F) {
         var26 = 0;
         var28 = this.bn.iterator();

         while(var28.hasNext()) {
            var31 = (o)var28.next();
            if(var31 instanceof i) {
               var34 = (i)var31;
               if(var34.b == j.a) {
                  ++var26;
               }
            }
         }

         boolean var30 = false;
         if(var26 > 2) {
            var30 = true;
         }

         if(var30) {
            this.aT = 300.0F;
         } else {
            var32 = this.an();
            if(var32 != null) {
               var32.b += (float)var2.bL.o;
               if(this.b(var32.a, var32.b) == null && this.b(var32)) {
                  this.aT = 2000.0F;
                  var34 = new i(this, var32.a, var32.b);
                  var34.U = 360.0F;
                  var34.b = j.a;
                  var34.c = k.b;
                  ++this.aw;
               }
            }
         }
      }

      if(this.aU == 0.0F) {
         this.aU = 100.0F;
         var26 = 0;
         var28 = this.bn.iterator();

         while(var28.hasNext()) {
            var31 = (o)var28.next();
            if(var31 instanceof i) {
               var34 = (i)var31;
               if(var34.c == k.c) {
                  ++var26;
               }
            }
         }

         if(var26 < 3) {
            var27 = this.ar();
            if(var27 != null) {
               var32 = new PointF();
               var32.a = var27.eo;
               var32.b = var27.ep;
               if(var32 != null && this.b(var32.a, var32.b) == null && this.a(var32)) {
                  this.aU = 5000.0F;
                  var34 = new i(this, var32.a, var32.b);
                  var34.U = 310.0F;
                  var34.b = j.a;
                  var34.c = k.c;
                  ++this.aw;
               }
            }
         }
      }

      this.bc = 0;
      this.ba = 0;
      this.bb = 0;
      var24 = am.bE.a();
      var5 = 0;

      for(var6 = am.bE.size(); var5 < var6; ++var5) {
         var7 = var24[var5];
         if(var7.bX == this && var7 instanceof com.corrodinggames.rts.game.units.y) {
            var8 = (com.corrodinggames.rts.game.units.y)var7;
            if(!var7.bI()) {
               if(var8.aB != null && var8.aB.b()) {
                  ++this.bb;
               } else if(this.h(var8) && !var8.bM) {
                  if(var8.h() == ao.e) {
                     ++this.bc;
                  } else {
                     ++this.ba;
                  }
               }
            }
         }
      }

      this.aR = com.corrodinggames.rts.gameFramework.f.a(this.aR, var1);
      this.aS += var1;
      if(this.aR == 0.0F) {
         var26 = 0;
         var5 = 0;
         var6 = 0;
         int var38 = 0;
         Iterator var36 = am.bE.iterator();

         am var39;
         while(var36.hasNext()) {
            var39 = (am)var36.next();
            if(var39.bX == this && var39.bT()) {
               if((var39 instanceof com.corrodinggames.rts.game.units.d.m || var39 instanceof com.corrodinggames.rts.game.units.d.a || var39 instanceof t) && var39 instanceof com.corrodinggames.rts.game.units.d.a) {
                  ++var5;
                  com.corrodinggames.rts.game.units.d.a var10 = (com.corrodinggames.rts.game.units.d.a)var39;
                  if(var10.V() > 1) {
                     ++var26;
                  }
               }

               if(var39.r().p()) {
                  ++var6;
                  com.corrodinggames.rts.game.units.a.c var43 = var39.cm();
                  if(s.c(var43)) {
                     ++var38;
                  }
               }
            }
         }

         if(this.a(4100.0D) || this.aS > 2400.0F || this.aH == 0) {
            var36 = am.bE.iterator();

            while(var36.hasNext()) {
               var39 = (am)var36.next();
               if(var39.bX == this && var39 instanceof com.corrodinggames.rts.game.units.y) {
                  com.corrodinggames.rts.game.units.y var44 = (com.corrodinggames.rts.game.units.y)var39;
                  if(var44.cl()) {
                     ArrayList var11 = var44.N();
                     ArrayList var12 = this.ap();
                     Iterator var13 = var11.iterator();

                     while(var13.hasNext()) {
                        s var14 = (s)var13.next();
                        if(var14.n(var44)) {
                           var12.add(var14);
                        }
                     }

                     if(var12.size() > 0) {
                        this.a(var44, (s)f.a((AbstractList)var12));
                     }
                  }
               }
            }

            boolean var41 = false;
            if(this.a(30000.0D)) {
               var41 = true;
            }

            Iterator var42 = am.bE.iterator();

            while(var42.hasNext()) {
               am var45 = (am)var42.next();
               if(var45.bX == this && var45 instanceof com.corrodinggames.rts.game.units.y) {
                  com.corrodinggames.rts.game.units.y var46 = (com.corrodinggames.rts.game.units.y)var45;
                  com.corrodinggames.rts.game.units.a.c var47 = var46.cm();
                  if(s.c(var47)) {
                     float var49 = var46.cn();
                     boolean var48;
                     if(var49 < 0.0F) {
                        var49 = 6.0F;
                        var48 = false;
                     } else {
                        var48 = true;
                     }

                     if(var49 != 0.0F) {
                        boolean var15 = false;
                        int var16 = com.corrodinggames.rts.gameFramework.f.c(100);
                        float var17 = 100.0F - var49;
                        if(var41) {
                           var17 -= 4.0F;
                        }

                        if(!var48) {
                           if(var45.r().p() && var38 > 0) {
                              var17 = 50.0F;
                           }

                           if(var5 > 0 && var26 == 0) {
                              var17 = 99.0F;
                              if(var45 instanceof com.corrodinggames.rts.game.units.d.a) {
                                 var17 = 40.0F;
                              }
                           }
                        }

                        if(var17 < 10.0F) {
                           var17 = 10.0F;
                        }

                        var15 = (float)var16 > var17;
                        if(var15) {
                           boolean var18 = var46.co();
                           if(var18) {
                              ;
                           }

                           if(com.corrodinggames.rts.gameFramework.f.c(100) > 50) {
                              var46.a(this.bH);
                              if(this.bH.size() != 0) {
                                 var47 = (com.corrodinggames.rts.game.units.a.c)this.bH.get((new Random()).nextInt(this.bH.size()));
                              }
                           }

                           boolean var19 = false;
                           s var20 = var46.a(var47);
                           if(var20 != null) {
                              if(var20.m(var46)) {
                                 var19 = true;
                              }

                              if(var20.e() == u.g) {
                                 var19 = true;
                              }

                              if(!var20.b((am)var46)) {
                                 var19 = true;
                              }

                              if(!var20.a(var46, false)) {
                                 var19 = true;
                              }
                           } else {
                              var19 = true;
                           }

                           if(!var19) {
                              this.a(var46, var47);
                              com.corrodinggames.rts.game.units.custom.d.b var21 = var20.B();
                              boolean var22 = true;
                              this.a(var46, var21, var22);
                              this.aR = 900.0F;
                              this.aS = 0.0F;
                              if(!var41) {
                                 break;
                              }

                              if(this.a(40000.0D)) {
                                 if(com.corrodinggames.rts.gameFramework.f.c(100) > 95) {
                                    break;
                                 }
                              } else if(com.corrodinggames.rts.gameFramework.f.c(100) > 80) {
                                 break;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      var25 = this.bm.iterator();

      while(var25.hasNext()) {
         o var40 = (o)var25.next();
         if(var40 instanceof h) {
            h var37 = (h)var40;
            var37.b(var1);
         }
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.y var1, s var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var2.b((am)var1) && var2.a(var1, false)) {
         com.corrodinggames.rts.gameFramework.e var4 = var3.cf.a((com.corrodinggames.rts.game.n)this);
         var4.a(var1);
         var4.a(var2.z());
         return true;
      } else {
         return false;
      }
   }

   public boolean a(com.corrodinggames.rts.game.units.y var1, s var2, PointF var3, am var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      if(var2.b((am)var1) && var2.a(var1, false)) {
         com.corrodinggames.rts.gameFramework.e var6 = var5.cf.a((com.corrodinggames.rts.game.n)this);
         var6.a(var1);
         var6.a(var2.z(), var3, var4);
         return true;
      } else {
         return false;
      }
   }

   public void aq() {
      Iterator var1 = this.bm.iterator();

      o var2;
      while(var1.hasNext()) {
         var2 = (o)var1.next();
         if(var2 instanceof i) {
            ((i)var2).t();
         }
      }

      var1 = this.bm.iterator();

      Iterator var3;
      o var4;
      while(var1.hasNext()) {
         var2 = (o)var1.next();
         var3 = this.bm.iterator();

         while(var3.hasNext()) {
            var4 = (o)var3.next();
            if(var2 != var4 && var2.Q == var4.Q) {
               com.corrodinggames.rts.gameFramework.l.a("Id overlap on:" + var2.Q);
               com.corrodinggames.rts.gameFramework.l.a("zone x:" + var2.S);
               com.corrodinggames.rts.gameFramework.l.a("zone y:" + var2.T);
               com.corrodinggames.rts.gameFramework.l.a("zone radius:" + var2.U);
               com.corrodinggames.rts.gameFramework.l.a("zone type:" + var2.getClass().getName());
            }
         }
      }

      int var8 = 0;
      Iterator var9 = this.bm.iterator();

      while(var9.hasNext()) {
         o var11 = (o)var9.next();
         if(var11 instanceof i) {
            ++var8;
         }
      }

      int var10 = 0;
      var3 = this.bm.iterator();

      while(var3.hasNext()) {
         var4 = (o)var3.next();
         if(var4 instanceof i) {
            Iterator var5 = this.bm.iterator();

            while(var5.hasNext()) {
               o var6 = (o)var5.next();
               if(var6 instanceof i && var4 != var6) {
                  float var7 = com.corrodinggames.rts.gameFramework.f.a(var4.S, var4.T, var6.S, var6.T);
                  if(var7 < 400.0F) {
                     ++var10;
                  }
               }
            }
         }
      }

      if(var10 > 0) {
         this.d("baseOverlapCount:" + var10);
      }

   }

   public void a(com.corrodinggames.rts.game.units.y var1) {
      if(var1.bX == this) {
         this.bE.a(var1);
      }

   }

   public void n(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.bE.a();
      Iterator var3 = this.bJ.iterator();

      com.corrodinggames.rts.game.a.a.a var4;
      while(var3.hasNext()) {
         var4 = (com.corrodinggames.rts.game.a.a.a)var3.next();
         var4.a(this.j(var1), this);
      }

      var3 = this.bm.iterator();

      h var5;
      o var16;
      while(var3.hasNext()) {
         var16 = (o)var3.next();
         if(var16 instanceof h) {
            var5 = (h)var16;
            var5.c(var1);
         }
      }

      if(this.bg != null) {
         var3 = this.bm.iterator();

         while(var3.hasNext()) {
            var16 = (o)var3.next();
            if(this.bg.a(var16.S, var16.T)) {
               if(var16 instanceof i) {
                  var16.p();
                  break;
               }

               if(var16 instanceof g) {
                  PointF var18 = this.bg.a(var16.S, var16.T, var16.U + 20.0F);
                  var16.S = var18.a;
                  var16.T = var18.b;
               }
            }
         }
      }

      this.aW = com.corrodinggames.rts.gameFramework.f.a(this.aW, var1);
      int var15 = 0;
      Iterator var17 = this.bn.iterator();

      while(var17.hasNext()) {
         o var19 = (o)var17.next();
         if(var19 instanceof i) {
            ++var15;
         }
      }

      if(var15 < 1) {
         var17 = am.bE.iterator();

         i var6;
         am var20;
         while(var17.hasNext()) {
            var20 = (am)var17.next();
            if(var20.bX == this && var20 instanceof com.corrodinggames.rts.game.units.d.e) {
               var6 = new i(this, var20.eo, var20.ep);
               var6.U = 420.0F;
               var6.b = j.c;
               var6.c = k.a;
               ++var15;
               break;
            }
         }

         if(var15 < 1) {
            var17 = am.bE.iterator();

            while(var17.hasNext()) {
               var20 = (am)var17.next();
               if(var20.bX == this && this.bz.b(var20.r())) {
                  var6 = new i(this, var20.eo, var20.ep);
                  var6.U = 420.0F;
                  var6.b = j.c;
                  var6.c = k.a;
                  ++var15;
                  break;
               }
            }
         }

         com.corrodinggames.rts.game.units.y var22;
         i var28;
         if(var15 < 1) {
            var17 = am.bE.iterator();

            while(var17.hasNext()) {
               var20 = (am)var17.next();
               if(var20.bX == this && var20 instanceof com.corrodinggames.rts.game.units.y) {
                  var22 = (com.corrodinggames.rts.game.units.y)var20;
                  boolean var7 = false;
                  Iterator var8 = this.bz.c.iterator();

                  while(var8.hasNext()) {
                     e var9 = (e)var8.next();
                     if(var22.b(var9.a, true)) {
                        var7 = true;
                        break;
                     }
                  }

                  if(var7) {
                     var28 = new i(this, var20.eo, var20.ep);
                     var28.U = 420.0F;
                     var28.b = j.c;
                     var28.c = k.a;
                     ++var15;
                     break;
                  }
               }
            }
         }

         if(var15 < 1) {
            var17 = am.bE.iterator();

            while(var17.hasNext()) {
               var20 = (am)var17.next();
               if(var20.bX == this && var20 instanceof com.corrodinggames.rts.game.units.y) {
                  var22 = (com.corrodinggames.rts.game.units.y)var20;
                  if(var22.ai()) {
                     i var26 = new i(this, var20.eo, var20.ep);
                     var26.U = 420.0F;
                     var26.b = j.c;
                     var26.c = k.a;
                     ++var15;
                     break;
                  }
               }
            }
         }

         if(!this.bf) {
            this.bf = true;
            int var21 = this.a(this.bB, b.a);
            if(var21 >= 1) {
               for(int var23 = 0; var23 < var2.bL.A.size(); ++var23) {
                  Point var24 = (Point)var2.bL.A.get(var23);
                  var2.bL.a(var24.a, var24.b);
                  this.bo.a((float)var2.bL.T, (float)var2.bL.U);
                  PointF var27 = this.bo;
                  var27.b += (float)var2.bL.o;
                  if(this.b(var27.a, var27.b) == null && this.a(this.bB, var27.a, var27.b, 200) >= 1 && this.b(var27)) {
                     var28 = new i(this, var27.a, var27.b);
                     var28.U = 360.0F;
                     var28.b = j.a;
                     var28.c = k.b;
                  }
               }
            }
         }
      }

      var4 = null;
      var5 = null;
      am[] var25 = am.bE.a();
      int var29 = 0;

      com.corrodinggames.rts.game.units.y var10;
      int var30;
      am var31;
      for(var30 = am.bE.size(); var29 < var30; ++var29) {
         var31 = var25[var29];
         if(var31.bX == this && var31.cN == null && var31 instanceof com.corrodinggames.rts.game.units.y && var31.aj() && this.i(var31)) {
            var10 = (com.corrodinggames.rts.game.units.y)var31;
            i var11 = this.e((am)var10);
            if(var11 != null) {
               if(var10.aq()) {
                  ;
               }
            } else if(var10.aq()) {
               i var12 = this.f(var10);
               if(var12 != null) {
                  PointF var13 = var12.w();
                  com.corrodinggames.rts.gameFramework.e var14 = var2.cf.a((com.corrodinggames.rts.game.n)this);
                  var14.a(var10);
                  var14.a(var13.a, var13.b);
               }
            }
         }
      }

      var29 = 0;

      for(var30 = am.bE.size(); var29 < var30; ++var29) {
         var31 = var25[var29];
         if(var31.bX == this && var31 instanceof com.corrodinggames.rts.game.units.y) {
            var10 = (com.corrodinggames.rts.game.units.y)var31;
            if(var10.V > 2400.0F && this.i(var10)) {
               if(var10.aN && var10.V < 24000.0F) {
                  continue;
               }

               com.corrodinggames.rts.gameFramework.e var35 = var2.cf.a((com.corrodinggames.rts.game.n)this);
               var35.a(var10);
               var35.h();
            }

            if(var10.aj() && this.i(var10)) {
               au var37 = var10.ar();
               if(var37 != null && var37.d() == av.c && var10.V > 700.0F) {
                  com.corrodinggames.rts.gameFramework.e var42 = var2.cf.a((com.corrodinggames.rts.game.n)this);
                  var42.a(var10);
                  var42.h();
               }
            }
         }
      }

      if(!this.U) {
         this.ak();
         var29 = 1;
         boolean var32 = this.af();
         boolean var33 = true;
         if(var32) {
            ++var29;
            var33 = false;
         }

         if(this.ay > 6) {
            var29 = 2;
         }

         if(this.ay > 11) {
            var29 = 3;
         }

         g var34;
         if(this.aC < var29) {
            var34 = new g(this, false);
            var34.A = 8;
            if(this.ac()) {
               var34.A = 10;
            }

            var34.k();
            ++this.av;
         }

         if((this.aD >= var29 || this.aE > 6) && this.aA < 1 && var33) {
            var34 = new g(this, true);
            if(this.au < 2) {
               var34.A = 3;
            } else if(this.au < 5) {
               var34.A = 5;
            } else {
               var34.A = 7;
               if(this.ac()) {
                  if(this.au < 25) {
                     var34.A = 14;
                  } else {
                     var34.A = 18;
                  }
               }
            }

            var34.k();
            ++this.au;
         }

         if(this.ah() && this.aB < 1 && var33) {
            var34 = new g(this, true);
            var34.B = true;
            var34.A = 5;
            if(this.ac()) {
               var34.A = 10;
            }

            var34.k();
         }

         if(this.ai() && this.aF < 3) {
            n var39 = new n(this);
            var39.l = 1;
            var39.f();
         }
      }

      if(this.U) {
         if(this.aW > 30.0F) {
            this.aW = 30.0F;
         }

         if(this.aW == 0.0F) {
            ++this.aV;
            if(this.aV == 1) {
               this.aW = 1000.0F;
            } else if(this.aV == 2) {
               this.aW = 3000.0F;
               am var38 = this.as();
               if(var38 != null) {
                  if(this.U) {
                     var30 = 0;
                  } else {
                     var30 = 2;
                     if(this.ba < 4) {
                        var30 = 5;
                     }
                  }

                  com.corrodinggames.rts.gameFramework.e var36 = var2.cf.a((com.corrodinggames.rts.game.n)this);
                  int var40 = 0;

                  for(int var41 = am.bE.size(); var40 < var41; ++var40) {
                     am var43 = var25[var40];
                     if(var43.bX == this && var43 instanceof com.corrodinggames.rts.game.units.y) {
                        com.corrodinggames.rts.game.units.y var44 = (com.corrodinggames.rts.game.units.y)var43;
                        if(!var44.bM && this.b(var44, var38)) {
                           if(var30 <= 0) {
                              var36.a(var44);
                           } else {
                              --var30;
                           }
                        }
                     }
                  }

                  var36.b(var38.eo, var38.ep);
               }
            } else {
               this.aV = 0;
            }
         }
      }

   }

   public boolean i(am var1) {
      return !var1.u() && !var1.t()?(var1.cW()?false:!var1.bN):false;
   }

   public am ar() {
      am var1 = null;
      int var2 = 0;
      am[] var3 = am.bE.a();
      int var4 = 0;

      int var5;
      for(var5 = am.bE.size(); var4 < var5; ++var4) {
         am var6 = var3[var4];
         if(!var6.bV && var6.cN == null && this == var6.bX && this.h(var6)) {
            ++var2;
         }
      }

      var4 = (int)(Math.random() * (double)var2);
      var5 = 0;
      Iterator var8 = am.bE.iterator();

      while(var8.hasNext()) {
         am var7 = (am)var8.next();
         if(!var7.bV && var7.cN == null && this == var7.bX && this.h(var7)) {
            if(var5 == var4) {
               var1 = var7;
               break;
            }

            ++var5;
         }
      }

      return var1;
   }

   public am as() {
      am var1 = null;
      int var2 = 0;
      am[] var3 = am.bE.a();
      int var4 = 0;

      int var5;
      for(var5 = am.bE.size(); var4 < var5; ++var4) {
         am var6 = var3[var4];
         if(!var6.bV && var6.cN == null && !var6.u() && this.c(var6.bX) && this.j(var6)) {
            ++var2;
         }
      }

      var4 = (int)(Math.random() * (double)var2);
      var5 = 0;
      Iterator var8 = am.bE.iterator();

      while(var8.hasNext()) {
         am var7 = (am)var8.next();
         if(!var7.bV && var7.cN == null && !var7.u() && this.c(var7.bX) && this.j(var7)) {
            if(var5 == var4) {
               var1 = var7;
               break;
            }

            ++var5;
         }
      }

      return var1;
   }

   public PointF at() {
      am var1 = null;
      int var2 = 0;
      am[] var3 = am.bE.a();
      int var4 = 0;

      int var5;
      for(var5 = am.bE.size(); var4 < var5; ++var4) {
         am var6 = var3[var4];
         if(!var6.bV && var6.cN == null && !var6.u() && this.c(var6.bX) && this.j(var6)) {
            ++var2;
         }
      }

      var4 = (int)(Math.random() * (double)var2);
      var5 = 0;
      Iterator var8 = am.bE.iterator();

      while(var8.hasNext()) {
         am var7 = (am)var8.next();
         if(!var7.bV && var7.cN == null && !var7.u() && this.c(var7.bX) && this.j(var7)) {
            if(var5 == var4) {
               var1 = var7;
               break;
            }

            ++var5;
         }
      }

      return var1 != null?new PointF(var1.eo, var1.ep):null;
   }

   public static am a(com.corrodinggames.rts.game.n var0, float var1, float var2, float var3) {
      float var4 = var3;
      am[] var5 = am.bE.a();
      int var6 = 0;

      for(int var7 = am.bE.size(); var6 < var7; ++var6) {
         am var8 = var5[var6];
         if(var8.eo + var4 > var1 && var8.eo - var4 < var1 && var8.ep + var4 > var2 && var8.ep - var4 < var2 && var8.bX != var0 && a(var8, var1, var2, var3) && var8.bX.c(var0)) {
            return var8;
         }
      }

      return null;
   }

   public static int a(com.corrodinggames.rts.game.n var0, float var1, float var2, float var3, boolean var4) {
      int var5 = 0;
      float var6 = var3;
      am[] var7 = am.bE.a();
      int var8 = 0;

      for(int var9 = am.bE.size(); var8 < var9; ++var8) {
         am var10 = var7[var8];
         if(var10.eo + var6 > var1 && var10.eo - var6 < var1 && var10.ep + var6 > var2 && var10.ep - var6 < var2 && var10.bX != var0 && a(var10, var1, var2, var3) && var10.bX.d(var0) && (!var4 || var10.bI())) {
            ++var5;
         }
      }

      return var5;
   }

   public static int b(com.corrodinggames.rts.game.n var0, float var1, float var2, float var3) {
      int var4 = 0;
      float var5 = var3;
      am[] var6 = am.bE.a();
      int var7 = 0;

      for(int var8 = am.bE.size(); var7 < var8; ++var7) {
         am var9 = var6[var7];
         if(var9.eo + var5 > var1 && var9.eo - var5 < var1 && var9.ep + var5 > var2 && var9.ep - var5 < var2 && var9.bX != var0 && a(var9, var1, var2, var3) && var9.bX.c(var0)) {
            ++var4;
         }
      }

      return var4;
   }

   public int a(d var1, float var2, float var3, int var4) {
      int var5 = 0;

      e var7;
      for(Iterator var6 = var1.c.iterator(); var6.hasNext(); var5 += this.a(var7.a, var2, var3, var4)) {
         var7 = (e)var6.next();
      }

      return var5;
   }

   public int a(com.corrodinggames.rts.game.units.as var1, float var2, float var3, int var4) {
      int var5 = 0;
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      bI.clear();
      var6.cc.a(this, var2, var3, (float)var4, bI);
      am[] var7 = bI.a();
      int var8 = 0;

      for(int var9 = bI.size(); var8 < var9; ++var8) {
         am var10 = var7[var8];
         if(var10.bX == this && var10.dz == var1 && a(var10, var2, var3, (float)var4)) {
            ++var5;
         }
      }

      return var5;
   }

   public int au() {
      int var1 = 0;
      am[] var2 = am.bE.a();
      int var3 = 0;

      for(int var4 = am.bE.size(); var3 < var4; ++var3) {
         am var10000 = var2[var3];
         ++var1;
      }

      return var1;
   }

   public void T() {
      if(this.aZ && this.au() != 0) {
         com.corrodinggames.rts.gameFramework.l.e("waking up AI");
         this.aZ = false;
      }

   }

   public void d(am var1) {
      if(var1 instanceof com.corrodinggames.rts.game.units.y) {
         com.corrodinggames.rts.game.units.y var2 = (com.corrodinggames.rts.game.units.y)var1;
         var2.bD = false;
         if(var2.aC != null) {
            var2.aC.a(var2);
            var2.aC = null;
         }

         if(var2.aB != null) {
            var2.aB.b(var2);
            var2.aB = null;
         }

         this.e(var2);
      }
   }

   public void a(com.corrodinggames.rts.game.units.y var1, com.corrodinggames.rts.game.units.custom.d.b var2, boolean var3) {
      if(var1.aC != null) {
         var1.aC.a(var1, var2, var3);
      }

   }

   public boolean j(am var1) {
      return var1.cg() || !this.c(var1.bX);
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.d.b var1, am var2) {
      return this.a(var1, var2, false);
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.d.b var1, am var2, boolean var3) {
      return var1.b(var2);
   }

   public void a(com.corrodinggames.rts.game.a.a.a var1) {
      if(!this.bJ.contains(var1)) {
         this.bJ.add(var1);
      } else {
         this.c("Skipping add of component: " + var1.a().name());
      }

   }

   // $FF: synthetic method
   static boolean a(a var0, com.corrodinggames.rts.game.units.as var1) {
      return var0.a(var1);
   }

}
