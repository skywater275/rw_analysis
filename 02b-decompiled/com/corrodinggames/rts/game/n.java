package com.corrodinggames.rts.game;

import android.graphics.Color;
import android.graphics.Paint;
import com.corrodinggames.rts.game.d;
import com.corrodinggames.rts.game.e;
import com.corrodinggames.rts.game.o;
import com.corrodinggames.rts.game.p;
import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.r;
import com.corrodinggames.rts.game.s;
import com.corrodinggames.rts.game.t;
import com.corrodinggames.rts.game.u;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.bs;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class n extends bq implements Comparable {

   static com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();
   static n[] b = new n[0];
   public static int c = 10;
   public static int d = 0;
   public static int e = 100;
   public static int f = c + d;
   public static final n g = new e(-1, false, "<blank>");
   public static final n h = new d(-2);
   public static final n i = new d(-1);
   private static n[] as = new n[f];
   public static n j = new u(-99);
   public int k;
   public final String l;
   public boolean m;
   public boolean n;
   public double o;
   public double p;
   public int q;
   public int r;
   public com.corrodinggames.rts.game.units.y s;
   public com.corrodinggames.rts.game.units.y t;
   public boolean u;
   public String v;
   public boolean w;
   public int x;
   public boolean y;
   public Integer z;
   public Integer A;
   public Integer B;
   public Integer C;
   public int D;
   private boolean at;
   private int au;
   public boolean E;
   private int av;
   public boolean F;
   public boolean G;
   public boolean H;
   public boolean I;
   public boolean J;
   public final Object K;
   public int L;
   public int M;
   public byte[][] N;
   public String O;
   public String P;
   public int Q;
   public int R;
   public boolean S;
   public s T;
   public boolean U;
   public byte V;
   public int W;
   public long X;
   public long Y;
   public int Z;
   public boolean aa;
   public boolean ab;
   public int ac;
   int ad;
   public Paint ae;
   public Paint af;
   static int[] ag = new int[10];
   static String[] ah = new String[10];
   int ai;
   static int aj = -99;
   com.corrodinggames.rts.game.units.custom.h ak;
   com.corrodinggames.rts.game.units.custom.e.f al;
   public com.corrodinggames.rts.game.units.custom.e.c am;
   public float an;
   public static float ao = 40.0F;
   public static float ap = 10.0F;
   long aq;
   double ar;


   public strictfp int a(n var1) {
      int var2 = this.ac - var1.ac;
      if(var2 != 0) {
         return var2;
      } else {
         int var3 = this.k - var1.k;
         return var3 != 0?var3:(this.v != null && var1.v != null?this.v.compareTo(var1.v):0);
      }
   }

   public strictfp void b(as var1) {
      var1.c(this.k);
      var1.a((int)this.o);
      var1.a(this.r);
      var1.b(this.v);
      var1.a(this.U);
      if(var1.g() > 26) {
         var1.a(this.A());
         var1.d("lastPingTimeReceivedAt");
         var1.a(this.X);
      }

      if(var1.g() >= 55) {
         var1.a(this.w);
         var1.a(this.x);
      }

      if(var1.g() >= 91) {
         var1.a(this.ac);
         var1.c(0);
      }

      if(var1.g() >= 97) {
         var1.a(this.I);
         var1.a(this.J);
      }

      if(var1.g() >= 125) {
         var1.a(this.E);
         var1.a(this.at);
         var1.a(this.au);
      }

      if(var1.g() >= 149) {
         var1.b(this.P);
         var1.a(this.Q);
      }

      if(var1.g() >= 156) {
         var1.a(this.z);
         var1.a(this.A);
         var1.a(this.B);
         var1.a(this.C);
         var1.a(this.D);
      }

   }

   public strictfp void c(as var1) {
      var1.c(0);
      var1.a(this.A());
      var1.a(this.I);
      var1.a(this.J);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      var1.d();
      this.W = var1.f();
      this.X = System.currentTimeMillis();
      this.I = var1.e();
      this.J = var1.e();
   }

   public strictfp void b(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.a(var1, false);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1, boolean var2) {
      if(!var2) {
         this.f(var1.d());
         this.o = (double)var1.f();
         this.p = 0.0D;
         this.q = 0;
         this.r = var1.f();
         this.v = var1.j();
         this.U = var1.e();
      } else {
         var1.d();
         var1.f();
         var1.f();
         var1.j();
         var1.e();
      }

      if(var1.b() >= 14) {
         this.W = var1.f();
         var1.i();
         this.X = System.currentTimeMillis();
      }

      int var4;
      boolean var8;
      if(var1.b() >= 34 && var1.c() >= 55) {
         var8 = var1.e();
         var4 = var1.f();
         if(!var2) {
            this.w = var8;
            this.x = var4;
         }
      } else {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(var3.bX.B) {
            ad.g("AI was skipping in networked game, steam version:" + var1.c());
         }
      }

      if(var1.b() >= 50 && var1.c() >= 91) {
         this.ac = var1.f();
         var1.d();
      }

      if(var1.b() >= 52 && var1.c() >= 97) {
         this.I = var1.e();
         this.J = var1.e();
      }

      if(var1.b() >= 70 && var1.c() >= 125) {
         var8 = var1.e();
         boolean var10 = var1.e();
         int var5 = var1.f();
         if(!var2) {
            this.E = var8;
            this.at = var10;
            this.au = var5;
         }
      }

      if(var1.b() >= 90 && var1.c() >= 149) {
         String var9 = var1.j();
         var4 = var1.f();
         if(!var2) {
            this.P = var9;
            this.Q = var4;
         }
      }

      if(var1.b() >= 93 && var1.c() >= 156) {
         Integer var11 = var1.k();
         Integer var12 = var1.k();
         Integer var13 = var1.k();
         Integer var6 = var1.k();
         int var7 = var1.f();
         if(!var2) {
            if(this.z != var11) {
               this.c("readIn aiDifficultyOverride was:" + this.z + " now:  " + var11);
            }

            this.z = var11;
            this.A = var12;
            this.B = var13;
            this.C = var6;
            this.D = var7;
         }
      }

   }

   public strictfp void a(as var1) {
      var1.d("Writing team: " + this.v);
      this.b(var1);
      if(var1.g() >= 44) {
         var1.c(4);
         var1.a(this.G);
         var1.a(this.F);
         boolean var2 = true;
         var1.a(var2);
         if(var2) {
            this.d(var1);
         }

         this.al.a(var1);
         com.corrodinggames.rts.game.units.custom.g.a(this.ak, var1);
         var1.a(this.y);
      }

   }

   public strictfp void c(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.b(var1);
      if(var1.b() >= 26) {
         byte var2 = var1.d();
         this.G = var1.e();
         if(var2 >= 1) {
            this.F = var1.e();
         }

         boolean var3 = var1.e();
         if(var3) {
            this.d(var1);
         }

         if(var2 >= 2) {
            this.al.a(var1);
         }

         if(var2 >= 3) {
            this.a(com.corrodinggames.rts.game.units.custom.g.a(var1));
         }

         if(var2 >= 4) {
            this.y = var1.e();
         }
      }

   }

   public strictfp void d(as var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var1.d("-- Saving fog --");
      var1.a(this.N != null);
      if(this.N != null) {
         var1.a(this.L);
         var1.a(this.M);

         for(int var3 = 0; var3 < this.L; ++var3) {
            for(int var4 = 0; var4 < this.M; ++var4) {
               var1.c(this.N[var3][var4]);
            }
         }
      }

      var1.d("--End fog--");
   }

   public strictfp void d(com.corrodinggames.rts.gameFramework.j.k var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = var1.e();
      if(var3) {
         this.L = var1.f();
         this.M = var1.f();
         boolean var4 = true;
         int var5 = this.L;
         int var6 = this.M;
         if(var2.bL != null) {
            var5 = var2.bL.C;
            var6 = var2.bL.D;
            if(this.L != var5 || this.M != var6) {
               com.corrodinggames.rts.gameFramework.l.b("Map size does not match fog size: " + this.L + "!=" + var5 + "|" + this.M + "!=" + var6);
            }
         }

         if(var4) {
            this.N = new byte[var5][var6];
         } else {
            this.N = (byte[][])null;
         }

         for(int var7 = 0; var7 < this.L; ++var7) {
            for(int var8 = 0; var8 < this.M; ++var8) {
               if(var4) {
                  this.N[var7][var8] = var1.d();
               } else {
                  var1.d();
               }
            }
         }
      } else {
         this.N = (byte[][])null;
      }

   }

   public strictfp void a() {
      if(this.N != null) {
         for(int var1 = 0; var1 < this.L; ++var1) {
            for(int var2 = 0; var2 < this.M; ++var2) {
               this.N[var1][var2] = 0;
            }
         }
      }

      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3.bs == this) {
         var3.bW.O = true;
         if(var3.bL != null) {
            var3.bL.k();
            var3.bL.g();
         }
      }

   }

   public strictfp boolean b() {
      return this.r == -3;
   }

   public static strictfp ArrayList a(boolean var0) {
      ArrayList var1 = new ArrayList();

      for(int var2 = 0; var2 < f; ++var2) {
         n var3 = as[var2];
         if(var3 != null && (var0 || var3.b())) {
            var1.add(var3);
         }
      }

      Collections.sort(var1);
      return var1;
   }

   public static strictfp ArrayList c() {
      return b(false);
   }

   public static strictfp ArrayList b(boolean var0) {
      ArrayList var1 = new ArrayList();

      for(int var2 = 0; var2 < f; ++var2) {
         n var3 = as[var2];
         if(var3 != null && (var0 || !var3.b())) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public static strictfp n[] d() {
      return b;
   }

   public static strictfp void e() {
      com.corrodinggames.rts.gameFramework.utility.m var0 = a;
      var0.clear();
      var0.add(i);
      var0.add(h);

      int var1;
      for(var1 = 0; var1 < c; ++var1) {
         n var2 = as[var1];
         if(var2 != null) {
            var0.add(var2);
         }
      }

      if(b.length != var0.a) {
         b = new n[var0.a];
      }

      var1 = var0.a;
      Object[] var4 = var0.a();

      for(int var3 = 0; var3 < var1; ++var3) {
         b[var3] = (n)var4[var3];
      }

   }

   public static strictfp ArrayList f() {
      ArrayList var0 = new ArrayList();

      for(int var1 = 0; var1 < c; ++var1) {
         n var2 = as[var1];
         if(var2 != null && !var2.b() && !var0.contains(Integer.valueOf(var2.r))) {
            var0.add(Integer.valueOf(var2.r));
         }
      }

      Collections.sort(var0);
      return var0;
   }

   public static strictfp int a(int var0, boolean var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < c; ++var3) {
         n var4 = as[var3];
         if(var4 != null && var4.r == var0 && !var4.b() && (!var1 || !var4.w)) {
            ++var2;
         }
      }

      return var2;
   }

   public static strictfp int g() {
      int var0 = 0;

      for(int var1 = 0; var1 < c; ++var1) {
         n var2 = as[var1];
         if(var2 != null && !var2.b() && !var2.F && !var2.G) {
            ++var0;
         }
      }

      return var0;
   }

   public static strictfp void b(int var0, boolean var1) {
      if(var0 >= 10) {
         if(var0 != c) {
            if(var0 > e) {
               throw new IOException("setMaxTeamId: " + var0 + " is over limit of:" + e);
            } else if(var1 || var0 > c) {
               int var2 = var0 + d;
               n[] var3 = new n[var2];

               for(int var4 = 0; var4 < as.length; ++var4) {
                  n var5 = as[var4];
                  if(var4 < var3.length) {
                     var3[var4] = var5;
                  }
               }

               as = var3;
               c = var0;
               f = var2;
            }
         }
      }
   }

   public static strictfp String a(int var0) {
      return var0 == 0?"A":(var0 == 1?"B":(var0 == 2?"C":(var0 == 3?"D":(var0 == 4?"E":(var0 == 5?"F":(var0 == 6?"G":(var0 == 7?"H":(var0 == 8?"I":(var0 == 9?"J":(var0 == 10?"K":(var0 == -3?"S":"" + var0)))))))))));
   }

   public strictfp String h() {
      return a(this.r);
   }

   public strictfp void i() {
      this.E = false;
      this.at = false;
      this.au = -9999;
   }

   public strictfp boolean j() {
      return this.E;
   }

   public strictfp boolean k() {
      return this.au >= 0;
   }

   public strictfp void l() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.au = var1.by;
   }

   public strictfp boolean m() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return (this.F || this.G) && !var1.bX.ay.l?false:(this.w?false:(this.B()?false:!this.ab || this.k()));
   }

   public static strictfp int b(int var0) {
      int var1 = 0;

      for(int var2 = 0; var2 < c; ++var2) {
         n var3 = as[var2];
         if(var3 != null && var3.r == var0 && var3.k() && var3.m()) {
            ++var1;
         }
      }

      return var1;
   }

   public static strictfp int c(int var0) {
      int var1 = 0;

      for(int var2 = 0; var2 < c; ++var2) {
         n var3 = as[var2];
         if(var3 != null && var3.r == var0 && var3.m()) {
            ++var1;
         }
      }

      return var1;
   }

   public static strictfp void n() {
      for(int var0 = 0; var0 < c; ++var0) {
         n var1 = as[var0];
         if(var1 != null) {
            var1.Z();
         }
      }

      Y();
   }

   public static strictfp void o() {
      for(int var0 = 0; var0 < c; ++var0) {
         n var1 = as[var0];
         if(var1 != null) {
            var1.au = -9999;
         }
      }

   }

   public static strictfp void d(int var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.bX.C) {
         if(!var1.cb.j()) {
            for(int var2 = 0; var2 < c; ++var2) {
               n var3 = as[var2];
               if(var3 != null && var3.r == var0 && !var3.at) {
                  var3.at = true;
                  com.corrodinggames.rts.gameFramework.e var4 = var1.cf.b();
                  var4.i = var3;
                  var4.r = true;
                  var4.u = 100;
                  var1.bX.a(var4);
               }
            }

         }
      }
   }

   public static strictfp void e(int var0) {
      int var1 = -9999;

      for(int var2 = 0; var2 < c; ++var2) {
         n var3 = as[var2];
         if(var3 != null && var3.r == var0 && var3.k() && var3.m() && var3.au > var1) {
            var1 = var3.au;
         }
      }

      if(var1 >= 0 && y.a(var1, 120000)) {
         n[] var6 = as;
         int var7 = var6.length;

         for(int var4 = 0; var4 < var7; ++var4) {
            n var5 = var6[var4];
            if(var5 != null && var5.r == var0) {
               var5.au = -9999;
            }
         }
      }

   }

   public strictfp boolean b(n var1) {
      return this.p() && var1 != null && this.d(var1);
   }

   public strictfp boolean p() {
      return this.I || this.J;
   }

   public strictfp boolean q() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.bs == this;
   }

   public strictfp void c(boolean var1) {
      if(var1) {
         this.Q = 1;
      } else {
         this.Q = 0;
      }

   }

   public strictfp boolean r() {
      return this.Q == 1;
   }

   public final strictfp int a(boolean var1, boolean var2) {
      s var3 = this.T;
      int var4 = var3.c;
      if(var1) {
         var4 += var3.f;
      }

      if(var2) {
         var4 += var3.e;
      }

      return var4;
   }

   public final strictfp int s() {
      return this.T.c + this.T.f + this.T.e;
   }

   public final strictfp int a(com.corrodinggames.rts.game.units.custom.g var1, boolean var2, boolean var3) {
      s var4 = this.T;
      if(var4.d == 0) {
         return 0;
      } else {
         p var5 = null;
         t var6 = var4.p;
         p[] var7 = var6.b;
         int var8 = 0;

         for(int var9 = var6.c; var8 < var9; ++var8) {
            p var10 = var7[var8];
            if(var10.a == var1) {
               var5 = var10;
               break;
            }
         }

         if(var5 == null) {
            var5 = var4.a(var1);
            if(var5.e > 50) {
               var6.a(var5);
            }

            ++var5.e;
         }

         var8 = var5.b;
         if(var2) {
            var8 += var5.c;
         }

         if(var3) {
            var8 += var5.d;
         }

         return var8;
      }
   }

   public strictfp boolean t() {
      boolean var1 = false;
      s var2 = this.e(false);
      if(this.T.b != var2.b) {
         com.corrodinggames.rts.gameFramework.l.b("unitCountExcludingBuildingsIncludingQueued: " + this.T.b + "!=" + var2.b + " (team:" + this.k + " fails: " + this.ad + ")");
         ++this.ad;
         var1 = true;
      }

      if(this.T.a != var2.a) {
         com.corrodinggames.rts.gameFramework.l.b("unitsMax: " + this.T.a + "!=" + var2.a + " (team:" + this.k + " fails: " + this.ad + ")");
         ++this.ad;
         var1 = true;
      }

      if(this.T.g != var2.g) {
         com.corrodinggames.rts.gameFramework.l.b("incomeRate: " + this.T.g + "!=" + var2.g + " (team:" + this.k + " fails: " + this.ad + ")");
         ++this.ad;
         var1 = true;
      }

      if(this.T.f != var2.f) {
         com.corrodinggames.rts.gameFramework.l.b("incompleteUnitCountOfAllTypes: " + this.T.f + "!=" + var2.f + " (team:" + this.k + " fails: " + this.ad + ")");
         ++this.ad;
         var1 = true;
      }

      if(this.T.e != var2.e) {
         com.corrodinggames.rts.gameFramework.l.b("queuedCountOfAllTypes: " + this.T.e + "!=" + var2.e + " (team:" + this.k + " fails: " + this.ad + ")");
         ++this.ad;
         var1 = true;
      }

      if(this.T.c != var2.c) {
         com.corrodinggames.rts.gameFramework.l.b("unitCountOfAllTypesOnlyCompleted: " + this.T.c + "!=" + var2.c + " (team:" + this.k + " fails: " + this.ad + ")");
         ++this.ad;
         var1 = true;
      }

      if(!this.T.h.e(var2.h)) {
         com.corrodinggames.rts.gameFramework.l.b("customIncomeRate: " + this.T.h + "!=" + var2.h + " (team:" + this.k + " fails: " + this.ad + ")");
         com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.h.a(false, true, 30, true, true));
         com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + var2.h.a(false, true, 30, true, true));
         ++this.ad;
         var1 = true;
      }

      if(!this.T.l.e(var2.l)) {
         com.corrodinggames.rts.gameFramework.l.b("streamingRateNegative (team:" + this.k + " fails: " + this.ad + ")");
         com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.l.a(false, true, 30, true, true));
         com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + var2.l.a(false, true, 30, true, true));
         ++this.ad;
         var1 = true;
      }

      if(!this.T.k.e(var2.k)) {
         com.corrodinggames.rts.gameFramework.l.b("streamingRatePositive (team:" + this.k + " fails: " + this.ad + ")");
         com.corrodinggames.rts.gameFramework.l.b("currentCaches:" + this.T.k.a(false, true, 30, true, true));
         com.corrodinggames.rts.gameFramework.l.b("targetUnitCache:" + var2.k.a(false, true, 30, true, true));
         ++this.ad;
         var1 = true;
      }

      if(var1) {
         ;
      }

      return var1;
   }

   private strictfp s e(boolean var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      s var3 = new s();
      var3.a = var2.bB;
      am[] var4 = am.bE.a();
      int var5 = 0;

      for(int var6 = am.bE.size(); var5 < var6; ++var5) {
         am var7 = var4[var5];
         if(var7.bX == this) {
            var3.a(var7);
            if(var1) {
               var7.bY = true;
            }
         }
      }

      if(var3.a > var2.bC) {
         var3.a = var2.bC;
      }

      return var3;
   }

   public strictfp void d(boolean var1) {
      if(var1 || this.S) {
         this.T = this.e(true);
         this.S = false;
         if(this.R < this.T.b) {
            this.R = this.T.b;
         }

         if(!this.n && this.T.m) {
            this.n = true;
         }

         if(!this.m && this.s() > 0) {
            this.m = true;
         }

         this.T();
      }
   }

   public strictfp int u() {
      int var1 = this.T.g;
      var1 = (int)((float)var1 * this.D());
      return var1;
   }

   public strictfp int v() {
      return (int)((float)this.u() * this.E() + 0.5F);
   }

   public strictfp int a(com.corrodinggames.rts.game.units.custom.e.a var1) {
      byte var2 = 0;
      int var3 = var2 - (int)this.T.l.a(var1);
      return var3;
   }

   public strictfp int b(com.corrodinggames.rts.game.units.custom.e.a var1) {
      int var2;
      if(var1 == com.corrodinggames.rts.game.units.custom.e.a.D) {
         var2 = this.T.g;
      } else {
         var2 = (int)this.T.h.a(var1);
      }

      var2 += (int)this.T.k.a(var1);
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var4 = false;
      if(var1 == com.corrodinggames.rts.game.units.custom.e.a.D) {
         var4 = true;
      }

      if(var4) {
         var2 = (int)((float)var2 * this.D());
      }

      return var2;
   }

   public strictfp int w() {
      return this.T.b;
   }

   public strictfp int x() {
      return this.T.a;
   }

   public strictfp String y() {
      int var1 = this.A();
      return var1 == -99?"":(this.w?"":(var1 == -2?"(disconnected)":(var1 == -1?"(disconnected)":"(" + var1 + ")")));
   }

   public strictfp String z() {
      int var1 = this.A();
      return var1 == -99?"HOST":(this.w?"-":(var1 == -1?"N/A":(var1 == -2?"-":(this.r()?var1 + " (HOST)":"" + var1))));
   }

   public strictfp int A() {
      return this.X == -1L?-2:(this.X < System.currentTimeMillis() - 5000L?-1:this.W);
   }

   public strictfp boolean B() {
      return this.X == -99L?false:this.X != -1L && this.X < System.currentTimeMillis() - 15000L;
   }

   public strictfp void a(float var1) {
      this.an += var1;
      if(this.an > 90.0F) {
         this.an = 0.0F;
         this.am.a();
      }

      ++this.q;
      if(this.q > 1000 && this.p != 0.0D) {
         com.corrodinggames.rts.gameFramework.l.e("Warning: anti-lag credits is still: " + this.p + " (force clearing)");
         this.p = 0.0D;
      }

   }

   public final strictfp int C() {
      if(this.y) {
         return this.x;
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         if((var1.bX.B || var1.cb.i()) && !var1.bX.F) {
            if(this.z != null && this.z.intValue() != this.x) {
               this.c("aiDifficultyOverride:  " + this.z + "!=" + this.x);
            }

            return this.x;
         } else if(this.z != null) {
            return this.z.intValue();
         } else {
            int var2 = com.corrodinggames.rts.gameFramework.l.B().bQ.aiDifficulty;
            return var2;
         }
      }
   }

   public final strictfp float D() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.O()?var1.bX.ay.h:1.0F;
   }

   public final strictfp float E() {
      if(!this.w) {
         return 1.0F;
      } else {
         int var1 = this.C();
         float var2 = 1.0F;
         if(var1 > 0) {
            var2 += (float)var1 * 0.4F;
         } else {
            var2 += (float)var1 * 0.3F;
         }

         if(var1 == 3) {
            ++var2;
         }

         if(var2 < 0.1F) {
            var2 = 0.1F;
         }

         return var2;
      }
   }

   public final strictfp void b(float var1) {
      if(!this.w) {
         this.c(var1);
      } else {
         float var2 = this.E();
         this.c(var2 * var1);
      }
   }

   public final strictfp void c(float var1) {
      var1 *= this.D();
      this.d(var1);
   }

   public final strictfp void d(float var1) {
      this.o += (double)var1;
      if(this.o > 9.99999999E8D) {
         this.o = 9.99999999E8D;
      }

   }

   public static strictfp void F() {
      try {
         b(10, true);
      } catch (IOException var1) {
         throw new RuntimeException(var1);
      }

      for(int var0 = 0; var0 < as.length; ++var0) {
         as[var0] = null;
      }

   }

   public static strictfp e a(String var0) {
      if(var0 != null && !var0.equals("")) {
         for(int var1 = 0; var1 < as.length; ++var1) {
            n var2 = as[var1];
            if(var2 != null && var0.equals(var2.O)) {
               if(var2 instanceof e) {
                  return (e)var2;
               }

               com.corrodinggames.rts.gameFramework.l.b("Player:" + var1 + " with matching clientId is not an instanceof player");
            }
         }

         return null;
      } else {
         com.corrodinggames.rts.gameFramework.l.b("findExistingPlayer: No clientId id");
         return null;
      }
   }

   public static strictfp e b(String var0) {
      if(var0 != null && !var0.equals("")) {
         for(int var1 = 0; var1 < as.length; ++var1) {
            n var2 = as[var1];
            if(var2 != null && var0.equals(var2.P)) {
               if(var2 instanceof e) {
                  return (e)var2;
               }

               com.corrodinggames.rts.gameFramework.l.b("Player:" + var1 + " with matching clientId is not an instanceof player");
            }
         }

         return null;
      } else {
         com.corrodinggames.rts.gameFramework.l.b("No id");
         return null;
      }
   }

   public static strictfp int G() {
      for(int var0 = 0; var0 < c; ++var0) {
         if(as[var0] == null) {
            return var0;
         }
      }

      return -1;
   }

   public static strictfp int H() {
      int var0;
      for(var0 = c; var0 < f; ++var0) {
         if(as[var0] == null) {
            return var0;
         }
      }

      for(var0 = c - 1; var0 >= 0; --var0) {
         if(as[var0] == null) {
            return var0;
         }
      }

      return -1;
   }

   public strictfp void I() {
      for(int var1 = 0; var1 < as.length; ++var1) {
         if(as[var1] == this) {
            as[var1] = null;
         }
      }

   }

   public strictfp n() {
      this.k = -1;
      this.l = "Note to modifiers: Changing credits will not allow you to cheat in multiplayer games, but it will only break sync";
      this.o = 4000.0D;
      this.p = 0.0D;
      this.q = 0;
      this.s = com.corrodinggames.rts.game.units.t.a(this);
      this.t = com.corrodinggames.rts.game.units.t.a(this);
      this.u = false;
      this.D = -1;
      this.au = -9999;
      this.av = -9999;
      this.K = new Object();
      this.S = true;
      this.T = new s();
      this.W = -1;
      this.X = -1L;
      this.Y = -1L;
      this.Z = -1;
      this.ac = 0;
      this.ae = new ag();
      this.af = new ag();
      this.ai = -2;
      this.ak = com.corrodinggames.rts.game.units.custom.g.d;
      this.al = new com.corrodinggames.rts.game.units.custom.e.f();
      this.am = new com.corrodinggames.rts.game.units.custom.e.c();
      this.aq = -9999L;
      this.w = this instanceof com.corrodinggames.rts.game.a.a;
   }

   public strictfp n(int var1) {
      this(var1, true);
   }

   public strictfp n(int var1, boolean var2) {
      this();
      this.c(var1, var2);
   }

   public strictfp void f(int var1) {
      this.c(var1, true);
   }

   public strictfp void c(int var1, boolean var2) {
      if(this.k != var1) {
         if(var2) {
            this.I();
         }

         this.k = var1;
         this.r = var1;
         if(var2 && var1 != -3) {
            n var3 = as[var1];
            if(var3 != null) {
               var3.c("Being replaced");
            }

            as[var1] = this;
         }

         this.J();
      }

   }

   public strictfp void J() {
      int var1 = this.K();
      this.ae.b(var1);
      int var2 = Color.a(Color.a(var1), (int)((float)Color.b(var1) * 0.5F), (int)((float)Color.c(var1) * 0.5F), (int)((float)Color.d(var1) * 0.5F));
      this.af.b(var2);
   }

   public strictfp boolean a(double var1) {
      return this.o >= var1 || var1 == 0.0D;
   }

   public strictfp boolean g(int var1) {
      return this.o + this.p >= (double)var1 || var1 == 0;
   }

   public final strictfp boolean c(n var1) {
      return var1 != i && this != i?this.r != var1.r:false;
   }

   public final strictfp boolean d(n var1) {
      return var1 == i && this == i?true:(var1 != i && this != i?this.r == var1.r:false);
   }

   public strictfp int K() {
      return i(this.R());
   }

   public static strictfp void L() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();

      try {
         d(var0.bQ.teamColors);
      } catch (IllegalArgumentException var3) {
         com.corrodinggames.rts.gameFramework.l.a("initColors: Failed to read setting: \'" + var0.bQ.teamColors + "\': " + var3.getMessage(), (Throwable)var3);
         d("#00ff00,#d02013,#0463f3,#ffff40,#00ffff,#d0f8f7,#000000,#ff00ea,#ff7f18,#9368c4");
      }

      try {
         e(var0.bQ.teamColorsNames);
      } catch (IllegalArgumentException var2) {
         com.corrodinggames.rts.gameFramework.l.a("initColors: Failed to read setting: \'" + var0.bQ.teamColorsNames + "\': " + var2.getMessage(), (Throwable)var2);
         e("GREEN,RED,BLUE,YELLOW,CYAN,WHITE,BLACK,PINK,ORANGE,PURPLE");
      }

   }

   private static strictfp void d(String var0) {
      String[] var1 = var0.split(",");
      if(var1.length != 10) {
         throw new IllegalArgumentException("Expected 10 hex colors");
      } else {
         for(int var2 = 0; var2 < 10; ++var2) {
            String var3 = var1[var2];
            ag[var2] = Color.a(var3);
         }

      }
   }

   private static strictfp void e(String var0) {
      String[] var1 = var0.split(",");
      if(var1.length != 10) {
         throw new IllegalArgumentException("Expected 10 team color names");
      } else {
         for(int var2 = 0; var2 < 10; ++var2) {
            ah[var2] = var1[var2];
         }

      }
   }

   public strictfp int M() {
      return this.r == -3?i(-3):h(this.k);
   }

   public static strictfp int h(int var0) {
      return var0 >= c?i(-3):i(var0 % 2);
   }

   public static strictfp int i(int var0) {
      return var0 >= 0 && var0 < 10?ag[var0]:(var0 == -3?Color.a(185, 90, 90, 90):-7829368);
   }

   public strictfp String N() {
      return this.k == -1?"GRAY":(this.k == -2?"GRAY":j(this.R()));
   }

   public static strictfp String j(int var0) {
      return var0 >= 0 && var0 < 10?ah[var0]:"GRAY";
   }

   public static strictfp com.corrodinggames.rts.gameFramework.m.e[] a(com.corrodinggames.rts.gameFramework.m.e var0) {
      return a(var0, o.a, false);
   }

   public static strictfp com.corrodinggames.rts.gameFramework.m.e[] a(com.corrodinggames.rts.gameFramework.m.e var0, o var1, boolean var2) {
      return var2 && !var0.A()?a(var0, var1):b(var0, var1);
   }

   public static strictfp com.corrodinggames.rts.gameFramework.m.e[] a(com.corrodinggames.rts.gameFramework.m.e var0, o var1) {
      com.corrodinggames.rts.gameFramework.m.e[] var2 = new com.corrodinggames.rts.gameFramework.m.e[10];
      if((!com.corrodinggames.rts.gameFramework.l.aU || com.corrodinggames.rts.gameFramework.l.aW) && var1 != o.e) {
         com.corrodinggames.rts.gameFramework.m.e[] var7 = var0.a(var1);
         if(var7 != null) {
            return var7;
         } else {
            br var4 = com.corrodinggames.rts.gameFramework.l.B().cd;
            var4.a(bs.D);

            for(int var5 = 0; var5 < var2.length; ++var5) {
               int var6 = i(var5);
               if(var5 == 0) {
                  var2[var5] = var0;
               } else {
                  var2[var5] = new com.corrodinggames.rts.gameFramework.m.h(var0, var6, var1, var5);
               }
            }

            var4.b(bs.D);
            var0.a(var1, var2);
            return var2;
         }
      } else {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = var0;
         }

         return var2;
      }
   }

   public static strictfp com.corrodinggames.rts.gameFramework.m.e[] b(com.corrodinggames.rts.gameFramework.m.e var0, o var1) {
      com.corrodinggames.rts.gameFramework.m.e[] var2 = new com.corrodinggames.rts.gameFramework.m.e[10];
      if((!com.corrodinggames.rts.gameFramework.l.aU || com.corrodinggames.rts.gameFramework.l.aW) && var1 != o.e && !var0.A()) {
         com.corrodinggames.rts.gameFramework.m.e[] var8 = var0.a(var1);
         if(var8 != null) {
            return var8;
         } else {
            br var4 = com.corrodinggames.rts.gameFramework.l.B().cd;
            var4.a(bs.D);
            int[] var5 = new int[10];
            int[] var6 = new int[10];

            int var7;
            for(var7 = 0; var7 < var5.length; var6[var7] = var7++) {
               var5[var7] = i(var7);
            }

            for(var7 = 0; var7 < var2.length; ++var7) {
               if(var7 != 0) {
                  var2[var7] = var0.h();
                  var2[var7].a("color(" + var7 + "):" + var0.a());
                  var2[var7].j();
               }
            }

            var0.j();
            if(var1 == o.b) {
               b(var0, var2, var5);
            } else if(var1 == o.d) {
               a(var0, var2, var5, var6);
            } else {
               a(var0, var2, var5);
            }

            for(var7 = 0; var7 < var2.length; ++var7) {
               if(var2[var7] != null) {
                  var2[var7].p();
                  var2[var7].s();
               }
            }

            var0.r();
            var2[0] = var0;
            var4.b(bs.D);
            var0.a(var1, var2);
            return var2;
         }
      } else {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = var0;
         }

         return var2;
      }
   }

   public static strictfp void a(com.corrodinggames.rts.gameFramework.m.e var0, com.corrodinggames.rts.gameFramework.m.e[] var1, int[] var2) {
      int var3 = var0.m();
      int var4 = var0.l();
      int[] var5 = new int[var2.length];
      int[] var6 = new int[var2.length];
      int[] var7 = new int[var2.length];

      for(int var8 = 0; var8 < var2.length; ++var8) {
         var5[var8] = Color.b(var2[var8]);
         var6[var8] = Color.c(var2[var8]);
         var7[var8] = Color.d(var2[var8]);
      }

      float var23 = 0.003921569F;

      for(int var9 = 0; var9 < var4; ++var9) {
         for(int var10 = 0; var10 < var3; ++var10) {
            int var11 = var0.a(var10, var9);
            int var12 = aa.a(var11);
            int var13;
            if(var12 == 0) {
               if(var11 != 0) {
                  for(var13 = 0; var13 < var1.length; ++var13) {
                     if(var1[var13] != null) {
                        var1[var13].a(var10, var9, 0);
                     }
                  }
               }
            } else {
               var13 = aa.c(var11);
               if(var13 > 0) {
                  int var14 = aa.b(var11);
                  int var15 = aa.d(var11);
                  if(var14 == var15) {
                     int var16;
                     int var19;
                     int var20;
                     if(var14 == 0) {
                        var16 = var13;

                        for(int var17 = 0; var17 < var1.length; ++var17) {
                           if(var1[var17] != null) {
                              int var18 = var5[var17] * var16 >> 8;
                              var19 = var6[var17] * var16 >> 8;
                              var20 = var7[var17] * var16 >> 8;
                              var1[var17].a(var10, var9, Color.a(var12, var18, var19, var20));
                           }
                        }
                     } else if(var13 != var14) {
                        var16 = var14;
                        float var24 = (float)var14 * 0.003921569F;
                        float var25 = (float)var13 * 0.003921569F - var24;

                        for(var19 = 0; var19 < var1.length; ++var19) {
                           if(var1[var19] != null) {
                              var20 = (int)((float)var16 + (float)var5[var19] * var25);
                              int var21 = (int)((float)var16 + (float)var6[var19] * var25);
                              int var22 = (int)((float)var16 + (float)var7[var19] * var25);
                              var20 = com.corrodinggames.rts.gameFramework.f.b(var20, 0, 255);
                              var21 = com.corrodinggames.rts.gameFramework.f.b(var21, 0, 255);
                              var22 = com.corrodinggames.rts.gameFramework.f.b(var22, 0, 255);
                              var1[var19].a(var10, var9, Color.a(var12, var20, var21, var22));
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   public static strictfp void a(com.corrodinggames.rts.gameFramework.m.e var0, com.corrodinggames.rts.gameFramework.m.e[] var1, int[] var2, int[] var3) {
      int var4 = var0.m();
      int var5 = var0.l();
      int[] var6 = new int[var2.length];
      int[] var7 = new int[var2.length];
      int[] var8 = new int[var2.length];

      int var9;
      for(var9 = 0; var9 < var2.length; ++var9) {
         var6[var9] = Color.b(var2[var9]);
         var7[var9] = Color.c(var2[var9]);
         var8[var9] = Color.d(var2[var9]);
      }

      for(var9 = 0; var9 < var5; ++var9) {
         for(int var10 = 0; var10 < var4; ++var10) {
            int var11 = var0.a(var10, var9);
            int var12 = Color.a(var11);
            int var13;
            if(var12 == 0) {
               if(Color.b(var11) > 0 || Color.c(var11) > 0 || Color.d(var11) > 0) {
                  for(var13 = 0; var13 < var1.length; ++var13) {
                     if(var1[var13] != null) {
                        var1[var13].a(var10, var9, Color.a(0, 0, 0, 0));
                     }
                  }
               }
            } else {
               var13 = Color.c(var11);
               int var14 = Color.b(var11);
               int var15 = Color.d(var11);
               float var16 = (float)com.corrodinggames.rts.gameFramework.f.c(com.corrodinggames.rts.gameFramework.f.c(var14, var13), var15);
               float var17 = (float)com.corrodinggames.rts.gameFramework.f.d(var14 - var13);
               var17 = com.corrodinggames.rts.gameFramework.f.f(var17, (float)com.corrodinggames.rts.gameFramework.f.d(var13 - var15));
               var17 = com.corrodinggames.rts.gameFramework.f.f(var17, (float)com.corrodinggames.rts.gameFramework.f.d(var15 - var14));
               if(var17 > 15.0F) {
                  for(int var18 = 0; var18 < var1.length; ++var18) {
                     if(var1[var18] != null) {
                        float var19 = var17 / 255.0F;
                        int var20 = (int)(var16 + (float)var6[var18] * var19);
                        int var21 = (int)(var16 + (float)var7[var18] * var19);
                        int var22 = (int)(var16 + (float)var8[var18] * var19);
                        var20 = com.corrodinggames.rts.gameFramework.f.b(var20, 0, 255);
                        var21 = com.corrodinggames.rts.gameFramework.f.b(var21, 0, 255);
                        var22 = com.corrodinggames.rts.gameFramework.f.b(var22, 0, 255);
                        var1[var18].a(var10, var9, Color.a(var12, var20, var21, var22));
                     }
                  }
               }
            }
         }
      }

   }

   public static strictfp void b(com.corrodinggames.rts.gameFramework.m.e var0, com.corrodinggames.rts.gameFramework.m.e[] var1, int[] var2) {
      int var3 = var0.m();
      int var4 = var0.l();
      int[] var5 = new int[var2.length];
      int[] var6 = new int[var2.length];
      int[] var7 = new int[var2.length];

      int var8;
      for(var8 = 0; var8 < var2.length; ++var8) {
         var5[var8] = Color.b(var2[var8]);
         var6[var8] = Color.c(var2[var8]);
         var7[var8] = Color.d(var2[var8]);
      }

      for(var8 = 0; var8 < var3; ++var8) {
         for(int var9 = 0; var9 < var4; ++var9) {
            int var10 = var0.a(var8, var9);
            int var11 = Color.a(var10);
            if(var11 > 0) {
               int var12 = Color.b(var10);
               int var13 = Color.c(var10);
               int var14 = Color.d(var10);
               float var15 = 0.15F;

               for(int var16 = 0; var16 < var1.length; ++var16) {
                  int var17 = (int)((float)var12 + (float)var5[var16] * var15);
                  int var18 = (int)((float)var13 + (float)var6[var16] * var15);
                  int var19 = (int)((float)var14 + (float)var7[var16] * var15);
                  var17 = com.corrodinggames.rts.gameFramework.f.b(var17, 0, 255);
                  var18 = com.corrodinggames.rts.gameFramework.f.b(var18, 0, 255);
                  var19 = com.corrodinggames.rts.gameFramework.f.b(var19, 0, 255);
                  if(var1[var16] != null) {
                     var1[var16].a(var8, var9, Color.a(var11, var17, var18, var19));
                  }
               }
            }
         }
      }

   }

   public static strictfp n k(int var0) {
      if(var0 == -1) {
         return i;
      } else if(var0 == -2) {
         return h;
      } else if(var0 >= f) {
         com.corrodinggames.rts.gameFramework.l.g("team index too high: " + var0);
         return null;
      } else if(var0 < 0) {
         com.corrodinggames.rts.gameFramework.l.g("team index too low: " + var0);
         return null;
      } else {
         return as[var0];
      }
   }

   public strictfp void e(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.ai > 0) {
         --this.ai;
      } else {
         if(this.ai == -2) {
            this.ai = this.k;
         } else {
            this.ai = 10;
         }

         if(!this.G && !var2.cb.j()) {
            boolean var3 = false;
            boolean var4 = false;
            boolean var5 = false;
            boolean var6 = var2.bX.ay.l;
            boolean var7 = false;
            am[] var8 = am.bE.a();
            int var9 = 0;

            am var11;
            for(int var10 = am.bE.size(); var9 < var10; ++var9) {
               var11 = var8[var9];
               if(var11.bX == this) {
                  if(!var11.cT()) {
                     var3 = true;
                     if(!this.F && (var11.bJ() || var11.ak())) {
                        var4 = true;
                        break;
                     }
                  } else {
                     var7 = true;
                  }
               } else if(var6 && var11.bX != null && var11.bX.d(this) && !var11.cT()) {
                  var5 = true;
               }
            }

            if(!var3 && !var5) {
               boolean var14 = false;
               if(var7 && var2.bx < 100 && var2.bv) {
                  var14 = true;
               }

               this.G = true;
               this.a();
               Iterator var15 = am.bE.iterator();

               while(var15.hasNext()) {
                  var11 = (am)var15.next();
                  if(var11.bX == this && !var11.u()) {
                     if(var14 && !var11.bV && var11.cT()) {
                        com.corrodinggames.rts.game.units.as var12 = var11.r();
                        String var13 = var11.c() + " Warning: This unit got ignored in defeated check and now being removed";
                        if(var12 instanceof com.corrodinggames.rts.game.units.custom.l && ((com.corrodinggames.rts.game.units.custom.l)var12).aO) {
                           var13 = var13 + " (Likely due to canNotBeDirectlyAttacked:true)";
                        }

                        ad.a((String)null, var13);
                     }

                     var11.cj();
                  }
               }

               var2.bX.i(this);
            }

            if(!var4 && !this.F && !this.G) {
               this.F = true;
               var2.bX.h(this);
            }
         }

      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.y var1) {}

   public static strictfp void b(com.corrodinggames.rts.game.units.y var0) {
      for(int var1 = 0; var1 < c; ++var1) {
         n var2 = as[var1];
         if(var2 != null) {
            var2.a(var0);
         }
      }

   }

   public static strictfp void a(am var0) {
      if(var0.bX != null && var0.bY && var0.bL) {
         n var1 = var0.bX;
         var0.bY = false;
         var1.T.b(var0);
         var0.dj();
      }

   }

   public static strictfp void b(am var0) {
      a(var0);
   }

   public static strictfp void c(am var0) {
      if(var0.bX != null && !var0.bY && var0.bL && !var0.bV) {
         var0.bY = true;
         n var1 = var0.bX;
         var1.T.a(var0);
         var0.di();
         if(!var1.n && var1.T.m) {
            var1.n = true;
         }

         if(!var1.m) {
            var1.m = true;
         }

         var1.T();
      }

   }

   public static strictfp void O() {
      i.S = true;
      h.S = true;

      n var1;
      for(Iterator var0 = c().iterator(); var0.hasNext(); var1.S = true) {
         var1 = (n)var0.next();
      }

   }

   public static strictfp void P() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      if(var0.M()) {
         com.corrodinggames.rts.gameFramework.l.e("Skipping updateAllCachesFromChangedMetadata due to desync risk");
      } else {
         for(int var1 = 0; var1 < c; ++var1) {
            n var2 = as[var1];
            if(var2 != null) {
               var2.S = true;
            }
         }

      }
   }

   public static strictfp void f(float var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      i.a(var0);
      h.a(var0);

      for(int var2 = 0; var2 < c; ++var2) {
         n var3 = as[var2];
         if(var3 != null) {
            var3.a(var0);
            var3.e(var0);
            int var4;
            if(!var3.at) {
               var4 = b(var3.r);
               if(var4 > 0) {
                  int var5 = c(var3.r);
                  if(var4 >= var5) {
                     d(var3.r);
                     o();
                  } else {
                     e(var3.r);
                  }
               }
            }

            if(var3.E) {
               if(var3.av < 0) {
                  var3.av = var1.by;
               }

               if(!var3.G) {
                  var4 = 0;
                  Iterator var9 = am.bE.iterator();

                  while(var9.hasNext()) {
                     am var6 = (am)var9.next();
                     if(var6.bX == var3 && !var6.u()) {
                        boolean var7 = false;
                        byte var8 = 1;
                        if(y.a(var3.av, 10000)) {
                           var7 = true;
                           var8 = 50;
                        } else if(y.a(var3.av, 6000)) {
                           var7 = com.corrodinggames.rts.gameFramework.f.a(var6, 0, 100) > 90;
                           var8 = 20;
                        } else if(y.a(var3.av, 2000)) {
                           var7 = com.corrodinggames.rts.gameFramework.f.a(var6, 0, 100) > 98;
                           var8 = 2;
                        }

                        if(var6 instanceof com.corrodinggames.rts.game.units.d.e) {
                           var7 = true;
                        }

                        if(var7) {
                           var6.cu = -1.0F;
                           ++var4;
                           if(var4 > var8) {
                              break;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if(var1.P() && var1.bQ.aiDifficulty != aj) {
         var1.bX.aq();
         aj = var1.bQ.aiDifficulty;
      }

   }

   public static strictfp void g(float var0) {
      e();
      n[] var1 = d();
      n[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         n var5 = var2[var4];
         var5.d(false);
      }

   }

   public static strictfp void Q() {
      i.d(false);

      for(int var0 = 0; var0 < c; ++var0) {
         n var1 = as[var0];
         if(var1 != null && !var1.b() && !var1.G && !var1.F && !var1.E) {
            com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
            var2.bX.g(var1);
         }
      }

   }

   public static strictfp void h(float var0) {
      for(int var1 = 0; var1 < c; ++var1) {
         n var2 = as[var1];
         if(var2 != null && var2 instanceof com.corrodinggames.rts.game.a.a) {
            com.corrodinggames.rts.game.a.a var3 = (com.corrodinggames.rts.game.a.a)var2;
            var3.i(var0);
         }
      }

   }

   public strictfp int R() {
      return this.D == -1?this.S():this.D;
   }

   public strictfp int S() {
      if(this.k == -1) {
         return 5;
      } else if(this.k == -2) {
         return 5;
      } else {
         int var1 = this.k;
         if(var1 >= 10) {
            var1 %= 10;
         }

         if(c > 10) {
            n var2 = com.corrodinggames.rts.gameFramework.l.B().bX.z;
            if(var2 != null && var2 != this && var2.R() == var1) {
               if(var1 != 5) {
                  var1 = 5;
               } else {
                  var1 = 4;
               }
            }
         }

         return var1;
      }
   }

   public strictfp void T() {}

   public strictfp void a(com.corrodinggames.rts.game.units.custom.h var1) {
      this.ak = var1;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.h U() {
      return this.ak;
   }

   public strictfp void b(com.corrodinggames.rts.game.units.custom.h var1) {
      com.corrodinggames.rts.game.units.custom.h var2 = this.U();
      if(var2 != null && var2.b() != 0) {
         if(!com.corrodinggames.rts.game.units.custom.g.b(var2, var1)) {
            com.corrodinggames.rts.game.units.custom.i var3 = new com.corrodinggames.rts.game.units.custom.i(var2);
            if(var3.a(var1)) {
               this.a(var3.a());
            }
         }
      } else {
         this.a(var1);
      }
   }

   public strictfp void c(com.corrodinggames.rts.game.units.custom.h var1) {
      com.corrodinggames.rts.game.units.custom.h var2 = this.U();
      if(var2 != null && var2.b() != 0) {
         if(com.corrodinggames.rts.game.units.custom.g.a(var1, var2)) {
            com.corrodinggames.rts.game.units.custom.i var3 = new com.corrodinggames.rts.game.units.custom.i(var2);
            if(var3.b(var1)) {
               this.a(var3.a());
            }
         }
      }
   }

   public strictfp com.corrodinggames.rts.game.units.custom.e.f V() {
      return this.al;
   }

   public strictfp double c(com.corrodinggames.rts.game.units.custom.e.a var1) {
      return this.al.a(var1);
   }

   public strictfp boolean a(q var1, n var2) {
      if(var1 == q.a) {
         return var2 == this;
      } else if(var1 == q.f) {
         return true;
      } else if(var1 == q.b) {
         return this.d(var2);
      } else if(var1 != q.c) {
         if(var1 == q.d) {
            return this.c(var2);
         } else if(var1 == q.e) {
            return var2 == i;
         } else if(var1 == q.g) {
            return var2 != this;
         } else {
            throw new RuntimeException("Unsupported type: " + var1);
         }
      } else {
         return var2 != this && this.d(var2);
      }
   }

   public strictfp void d(am var1) {}

   public strictfp void W() {
      com.corrodinggames.rts.gameFramework.l.e("debugUnitCountByType for team:" + this.k);
      com.corrodinggames.rts.gameFramework.utility.m var1 = new com.corrodinggames.rts.gameFramework.utility.m();
      am[] var2 = am.bE.a();
      int var3 = 0;

      int var4;
      for(var4 = am.bE.size(); var3 < var4; ++var3) {
         am var5 = var2[var3];
         if(var5.bX == this && !var5.bV) {
            com.corrodinggames.rts.game.units.as var6 = var5.dz;
            boolean var7 = false;
            Iterator var8 = var1.iterator();

            while(var8.hasNext()) {
               r var9 = (r)var8.next();
               if(var9.a == var6) {
                  ++var9.b;
                  var7 = true;
                  break;
               }
            }

            if(!var7) {
               r var14 = new r();
               var14.a = var6;
               var14.b = 1;
               var1.add(var14);
            }
         }
      }

      com.corrodinggames.rts.gameFramework.l.e("--- Units ---");
      var3 = 0;
      Iterator var10 = var1.iterator();

      while(var10.hasNext()) {
         r var11 = (r)var10.next();
         if(!var11.a.k()) {
            com.corrodinggames.rts.gameFramework.l.e(var11.a.i() + " - count:" + var11.b);
            var3 += var11.b;
         }
      }

      com.corrodinggames.rts.gameFramework.l.e("total:" + var3);
      com.corrodinggames.rts.gameFramework.l.e("--- Buildings/Ignored in count ---");
      var4 = 0;
      Iterator var12 = var1.iterator();

      while(var12.hasNext()) {
         r var13 = (r)var12.next();
         if(var13.a.k()) {
            com.corrodinggames.rts.gameFramework.l.e(var13.a.i() + " - count:" + var13.b);
            var4 += var13.b;
         }
      }

      com.corrodinggames.rts.gameFramework.l.e("total:" + var4);
   }

   public strictfp void c(String var1) {
      com.corrodinggames.rts.gameFramework.l.e("Team(id: " + this.k + ", name:" + this.v + "):" + var1);
   }

   public strictfp int b(com.corrodinggames.rts.game.units.custom.g var1, boolean var2, boolean var3) {
      int var4 = 0;
      if(this == i) {
         return 0;
      } else {
         n[] var5 = as;
         int var6 = c;

         for(int var7 = 0; var7 < var6; ++var7) {
            n var8 = var5[var7];
            if(var8 != null && this != var8 && this.r != var8.r) {
               if(var1 == null) {
                  var4 += var8.a(var2, var3);
               } else {
                  var4 += var8.a(var1, var2, var3);
               }
            }
         }

         return var4;
      }
   }

   public strictfp int c(com.corrodinggames.rts.game.units.custom.g var1, boolean var2, boolean var3) {
      int var4 = 0;
      n[] var5 = as;
      int var6 = c;

      for(int var7 = 0; var7 < var6; ++var7) {
         n var8 = var5[var7];
         if(var8 != null && this != var8 && this.d(var8)) {
            if(var1 == null) {
               var4 += var8.a(var2, var3);
            } else {
               var4 += var8.a(var1, var2, var3);
            }
         }
      }

      return var4;
   }

   public static strictfp void X() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      i.T.a = var0.bB;
      h.T.a = var0.bB;

      for(int var1 = 0; var1 < c; ++var1) {
         n var2 = as[var1];
         if(var2 != null) {
            var2.T.a = var0.bB;
         }
      }

   }

   public static strictfp void Y() {
      i.Z();
      h.Z();
   }

   public strictfp void Z() {
      this.m = false;
      this.n = false;
      this.o = 4000.0D;
      this.p = 0.0D;
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
      this.an = 0.0F;
      this.ad = 0;
      this.R = 0;
      this.S = true;
      this.T = new s();
      this.T.a = com.corrodinggames.rts.gameFramework.l.B().bB;
      this.ak = com.corrodinggames.rts.game.units.custom.g.d;
      this.al = new com.corrodinggames.rts.game.units.custom.e.f();
   }

   public strictfp double aa() {
      long var1 = System.currentTimeMillis();
      if(com.corrodinggames.rts.gameFramework.f.c((float)(this.aq - var1)) > 166.66666F) {
         this.aq = var1;
         this.ar = this.o + this.p;
      }

      return this.ar;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.e.f ab() {
      return this.V();
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((n)var1);
   }

}
