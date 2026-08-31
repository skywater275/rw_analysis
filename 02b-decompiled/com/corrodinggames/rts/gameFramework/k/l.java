package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.k.f;
import com.corrodinggames.rts.gameFramework.k.i;
import com.corrodinggames.rts.gameFramework.k.k;
import com.corrodinggames.rts.gameFramework.k.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public final class l {

   static final boolean a = false;
   static boolean b = !com.corrodinggames.rts.gameFramework.l.as;
   static boolean c = false;
   static boolean d = false;
   public static f e;
   static boolean f = false;
   static boolean g = false;
   static boolean h = false;
   static int i = 20;
   static boolean j = false;
   static ArrayList k = new ArrayList();
   static boolean l = false;
   public static final boolean m = false;
   public boolean n = true;
   o o = new o(this);
   boolean p = true;
   com.corrodinggames.rts.game.b.b q;
   int r;
   short s;
   short t;
   ArrayList u = new ArrayList();
   i[] v = new i[0];
   public Paint w = new Paint();
   public i x;
   public i y;
   public i z;
   public i A;
   public i B;
   public i C;
   public i D;
   public i E;
   Paint F = new Paint();
   Object G = new Object();
   ArrayList H = new ArrayList();
   LinkedList I = new LinkedList();
   LinkedList J = new LinkedList();
   Object K = new Object();


   public strictfp i a(ao var1) {
      i[] var2 = this.v;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         i var5 = var2[var4];
         if(var5.a == var1) {
            return var5;
         }
      }

      return null;
   }

   public strictfp boolean a(ao var1, int var2, int var3) {
      i var4 = this.a(var1);
      return this.a(var4, var2, var3);
   }

   public strictfp boolean b(ao var1, int var2, int var3) {
      i var4 = this.a(var1);
      return this.a(var4, var2, var3, true);
   }

   public strictfp boolean a(i var1, int var2, int var3) {
      return this.a(var1, var2, var3, false);
   }

   public strictfp boolean a(i var1, int var2, int var3, boolean var4) {
      if(!this.q.c(var2, var3)) {
         return true;
      } else if(var1.a == ao.d) {
         return false;
      } else {
         int var5 = var2 * this.t + var3;
         return !var4 && var1.e[var5] == -1?true:var1.d[var5] == -1 || var1.f[var5] == -1;
      }
   }

   public final strictfp int b(i var1, int var2, int var3) {
      if(!this.q.c(var2, var3)) {
         return -1;
      } else if(var1.a == ao.d) {
         return 0;
      } else {
         int var4 = var2 * this.t + var3;
         return var1.d[var4] != -1 && var1.e[var4] != -1 && var1.f[var4] != -1?var1.d[var4] + var1.e[var4] + var1.f[var4] * 10:-1;
      }
   }

   public final strictfp int c(i var1, int var2, int var3) {
      if(!this.q.c(var2, var3)) {
         return -1;
      } else if(var1.a == ao.d) {
         return 4;
      } else if(var1.j == null) {
         return -1;
      } else {
         int var4 = var2 * this.t + var3;
         return var1.j[var4];
      }
   }

   public strictfp boolean a(int var1, int var2) {
      if(!this.q.c(var1, var2)) {
         return true;
      } else {
         int var3 = var1 * this.t + var2;
         return this.D.d[var3] != -1?false:this.A.d[var3] != -1;
      }
   }

   public strictfp boolean b(int var1, int var2) {
      if(!this.q.c(var1, var2)) {
         return true;
      } else {
         int var3 = var1 * this.t + var2;
         return this.C.d[var3] != -1?false:this.E.d[var3] != -1;
      }
   }

   public synchronized strictfp void a(com.corrodinggames.rts.game.b.b var1, boolean var2) {
      this.d();
      com.corrodinggames.rts.gameFramework.l.e("PathEngine: Setting up map costs");
      boolean var3 = false;
      if(var2 && this.q != null && this.q == var1 && this.s == var1.u.n && this.t == var1.u.o) {
         if(this.r == i.a(var1)) {
            com.corrodinggames.rts.gameFramework.l.e("PathEngine: Keeping existing map costs");
            var3 = true;
         } else {
            com.corrodinggames.rts.gameFramework.l.e("PathEngine: Error: Map checksum does not match!!!");
         }
      }

      if(var3) {
         ;
      }

      this.q = var1;
      this.r = i.a(var1);
      this.s = (short)var1.u.n;
      this.t = (short)var1.u.o;
      e = null;
      this.u.clear();
      this.v = new i[0];
      this.x = new i(this, ao.a, this.s, this.t);
      this.y = new i(this, ao.b, this.s, this.t);
      this.y.b();
      this.y.a((y)null);
      this.z = new i(this, ao.c, this.s, this.t);
      this.A = new i(this, ao.e, this.s, this.t);
      this.A.b();
      this.A.a((y)null);
      this.B = new i(this, ao.d, this.s, this.t);
      this.C = new i(this, ao.f, this.s, this.t);
      this.C.b();
      this.C.a((y)null);
      this.D = new i(this, ao.g, this.s, this.t);
      this.D.b();
      this.D.a((y)null);
      this.E = new i(this, ao.h, this.s, this.t);
      this.E.b();
      this.E.a((y)null);
      Iterator var4 = this.H.iterator();

      while(var4.hasNext()) {
         o var5 = (o)var4.next();
         var5.a(var1);
      }

      this.o.a(var1);
      com.corrodinggames.rts.gameFramework.l.e("PathEngine: Ready");
   }

   public strictfp void a() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      i var2 = this.y;
      Rect var3 = new Rect();
      float var4 = var1.cw;
      float var5 = var1.cx;
      float var6 = var1.cA;
      float var7 = var1.cB;
      com.corrodinggames.rts.game.b.e var8 = var1.bL.u;
      int var9 = (int)(var4 * this.q.r - 1.0F);
      if(var9 < 0) {
         var9 = 0;
      }

      int var10 = (int)(var5 * this.q.s - 1.0F);
      if(var10 < 0) {
         var10 = 0;
      }

      int var11 = (int)((var4 + var6) * this.q.r + 1.0F);
      if(var11 > this.s - 1) {
         var11 = this.s - 1;
      }

      int var12 = (int)((var5 + var7) * this.q.s + 1.0F);
      if(var12 > this.t - 1) {
         var12 = this.t - 1;
      }

      for(int var13 = var9; var13 < var11 + 1; ++var13) {
         for(int var14 = var10; var14 < var12 + 1; ++var14) {
            com.corrodinggames.rts.game.b.g var15 = var8.a(var13, var14);
            if(var15 != null) {
               int var16 = var13 * this.q.n;
               int var17 = var14 * this.q.o;
               var3.a(var16, var17, var16 + this.q.n, var17 + this.q.o);
               var3.a((int)(-var4), (int)(-var5));
               boolean var18 = var3.b((int)(var1.bS.x / var1.cX), (int)(var1.bS.y / var1.cX));
               if(!g || var18) {
                  byte var19 = var2.d[var13 * this.t + var14];
                  byte var20 = var2.e[var13 * this.t + var14];
                  int var21 = var2.f[var13 * this.t + var14];
                  int var22;
                  if(var19 == -1) {
                     var22 = 255;
                  } else {
                     var22 = var19 * 2;
                  }

                  int var23;
                  if(var20 == -1) {
                     var23 = 255;
                  } else {
                     var23 = var20 * 2;
                  }

                  if(var21 == -1) {
                     var21 = 255;
                  } else {
                     if(var21 != 0) {
                        var21 += 30;
                     }

                     var21 *= 2;
                  }

                  this.F.a(128, var22, var23, var21);
                  var1.bO.b(var3, this.F);
                  if(var18 && var2.f != null) {
                     var1.bO.a("o:" + var2.f[var13 * this.t + var14], (float)var3.d(), (float)var3.e(), var1.dp);
                  }
               }
            }
         }
      }

   }

   public strictfp void a(y var1) {
      if(var1 != null) {
         com.corrodinggames.rts.game.n.b(var1);
      }

      i[] var2 = this.v;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         i var5 = var2[var4];
         var5.c(var1);
      }

      this.y.a(var1);
      this.C.a(var1);
      this.D.a(var1);
      this.E.a(var1);
   }

   public strictfp void b() {
      i[] var1 = this.v;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         i var4 = var1[var3];
         var4.e();
      }

   }

   public strictfp l() {
      this.H.add(new o(this));
      int var1 = com.corrodinggames.rts.gameFramework.f.c();
      if(var1 > 1) {
         com.corrodinggames.rts.gameFramework.l.b("PathEngine", "We have " + var1 + " cores, creating extra solvers");
         this.H.add(new o(this));
      } else {
         com.corrodinggames.rts.gameFramework.l.b("PathEngine", "We only have one core, using single solver");
      }

      Iterator var2 = this.H.iterator();

      while(var2.hasNext()) {
         o var3 = (o)var2.next();
         var3.c();
      }

   }

   public strictfp void c() {
      k var2;
      for(Iterator var1 = this.I.iterator(); var1.hasNext(); var2.w = true) {
         var2 = (k)var1.next();
      }

      this.I.clear();
      this.h();
   }

   public strictfp void d() {
      Iterator var1 = this.I.iterator();

      while(var1.hasNext()) {
         k var2 = (k)var1.next();
         this.a(var2);
      }

      this.I.clear();
      this.h();
   }

   public strictfp void a(i var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var2) {
         if(var1.k + 50 < var3.bx) {
            var1.k = var3.bx - 40;
            var1.e();
         }

         var1.a(var2);
      } else {
         if(var1.k + 30 < var3.bx) {
            var1.k = var3.bx;
            var1.e();
         }

         var1.a(var2);
      }

   }

   public strictfp k a(boolean var1) {
      Object var2;
      if(y.L) {
         var2 = new f(this, var1);
      } else {
         var2 = new k(this, var1);
      }

      return (k)var2;
   }

   public strictfp void a(k var1, boolean var2) {
      this.a(var1, var2, false);
   }

   public strictfp void a(k var1, boolean var2, boolean var3) {
      if(!this.p) {
         com.corrodinggames.rts.gameFramework.l.b("PathEngine", "Cannot start new path, not running");
      } else {
         com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
         i var5 = this.a(var1.o);
         this.a(var5, var2);
         var1.e();
         var1.t = 300.0F;
         int var6 = com.corrodinggames.rts.gameFramework.f.d(var1.h - var1.l);
         int var7 = com.corrodinggames.rts.gameFramework.f.d(var1.i - var1.m);
         if(var6 < 15 && var7 < 15) {
            var1.t = 12.0F;
         } else if(var6 < 50 && var7 < 50) {
            var1.t = 16.0F;
         } else if(var6 < 200 && var7 < 200) {
            var1.t = 24.0F;
         } else if(var6 < 400 && var7 < 400) {
            var1.t = 50.0F;
         } else if(var6 < 1000 && var7 < 1000) {
            var1.t = 100.0F;
         } else if(var6 < 2000 && var7 < 2000) {
            var1.t = 200.0F;
         }

         if(!var4.bX.B && !var4.cb.i()) {
            if(var6 < 1000 && var7 < 1000) {
               var1.t = 180.0F;
            } else {
               var1.t = 360.0F;
            }
         }

         if(var1.r) {
            var1.t *= 2.0F;
            var1.t += 50.0F;
         }

         var1.s = var1.t;
         if(this.n && !var3) {
            this.b(var1);
            this.I.add(var1);
         } else {
            this.o.a(var1);
            this.o.b();
            this.I.add(var1);
         }

      }
   }

   public strictfp void a(float var1) {
      this.i();
   }

   public strictfp void b(float var1) {
      i[] var2 = this.v;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         i var5 = var2[var4];
         var5.p = 0;
         if(var5.o) {
            var5.o = false;
            var5.c((y)null);
         }
      }

      this.i();
      this.d(var1);
   }

   public strictfp void c(float var1) {
      if(j) {
         Iterator var2 = k.iterator();

         while(var2.hasNext()) {
            k var3 = (k)var2.next();
            var3.h();
            var3.g();
         }
      }

      if(d) {
         boolean var6 = true;
         com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
         if(var7.bS.bZ.b > 0) {
            am var4 = var7.bS.bZ.a(0);
            if(var4 instanceof y) {
               y var5 = (y)var4;
               if(var5.au != null) {
                  var5.au.d(var5);
                  var6 = false;
               }
            }
         }

         if(var6) {
            ;
         }
      }

      if(f) {
         this.a();
      }

      if(h) {
         ;
      }

   }

   public strictfp boolean e() {
      Iterator var1 = this.I.iterator();

      k var2;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         var2 = (k)var1.next();
      } while(var2.t > 0.0F || var2.c());

      return true;
   }

   public strictfp String f() {
      Iterator var1 = this.I.iterator();
      String var2 = null;
      int var3 = 0;

      while(var1.hasNext()) {
         k var4 = (k)var1.next();
         if(var4.t <= 0.0F && !var4.c()) {
            if(var2 == null) {
               float var5 = com.corrodinggames.rts.gameFramework.f.b((float)var4.h, (float)var4.i, (float)var4.l, (float)var4.m);
               var2 = "[distance:" + var5 + ", allowedDelay:" + var4.s + " lowPriority:" + var4.r + "]";
            }

            ++var3;
         }
      }

      String var6 = "(total:" + var3 + ") ";
      if(var2 != null) {
         var6 = var6 + var2;
      }

      return var6;
   }

   private strictfp void d(float var1) {
      Iterator var2 = this.I.iterator();

      while(var2.hasNext()) {
         k var3 = (k)var2.next();
         if(var3.t <= 0.0F) {
            var3.t = 0.0F;
            var3.u = true;
            if(j) {
               k.add(var3);
               if(k.size() > 10) {
                  k.remove(0);
               }
            }

            if(!var3.c()) {
               if(com.corrodinggames.rts.gameFramework.l.B().ay()) {
                  com.corrodinggames.rts.gameFramework.l.b("PathEngine", "updateUnfinishedPaths: path wasn\'t solved, isGoingToBlockThisFrame did not protect");
               }

               this.a(var3);
            }

            if(var3.c()) {
               var2.remove();
            }
         } else {
            var3.t -= var1;
         }
      }

   }

   private strictfp k g() {
      k var1 = null;
      Iterator var2 = this.J.iterator();

      while(var2.hasNext()) {
         k var3 = (k)var2.next();
         if(var1 == null || var1.t > var3.t) {
            var1 = var3;
         }
      }

      if(var1 == null) {
         throw new RuntimeException("Failed to find any paths");
      } else if(!this.J.remove(var1)) {
         throw new RuntimeException("Failed remove found path");
      } else {
         return var1;
      }
   }

   private strictfp void b(k var1) {
      Object var2 = this.K;
      synchronized(this.K) {
         this.J.add(var1);
      }
   }

   private strictfp void h() {
      Object var1 = this.K;
      synchronized(this.K) {
         this.J.clear();
      }
   }

   private strictfp void i() {
      LinkedList var1 = this.J;
      if(var1.size() > 0) {
         Object var2 = this.K;
         synchronized(this.K) {
            while(var1.size() > 0) {
               o var3 = this.j();
               if(var3 == null) {
                  break;
               }

               k var4 = this.g();
               if(!var4.v) {
                  this.a(var3, var4);
               }
            }
         }
      }

   }

   private strictfp o j() {
      Iterator var1 = this.H.iterator();

      o var2;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         var2 = (o)var1.next();
      } while(!var2.s);

      return var2;
   }

   public strictfp void a(k var1) {
      if(!var1.v) {
         while(true) {
            Object var2 = this.G;
            synchronized(this.G) {
               o var3 = this.j();
               if(var3 != null) {
                  this.a(var3, var1);
                  break;
               }

               try {
                  this.G.wait(2000L);
               } catch (InterruptedException var9) {
                  ;
               }
            }
         }
      }

      boolean var12 = false;
      long var13 = com.corrodinggames.rts.gameFramework.l.V();

      while(true) {
         Object var5 = this.G;
         synchronized(this.G) {
            if(var1.c()) {
               break;
            }

            var12 = true;
            this.i();

            try {
               this.G.wait(2000L);
            } catch (InterruptedException var8) {
               ;
            }
         }
      }

      if(var12 && b) {
         com.corrodinggames.rts.gameFramework.l.b("PathEngine", "We were blocked path(" + var1.e + ") for:" + (com.corrodinggames.rts.gameFramework.l.V() - var13));
      }

   }

   private strictfp void a(o var1, k var2) {
      synchronized(var2) {
         if(!var2.v) {
            var1.a(var2);
            var1.a();
         }

      }
   }

}
