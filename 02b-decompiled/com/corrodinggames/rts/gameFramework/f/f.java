package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.f.b;
import com.corrodinggames.rts.gameFramework.f.f$1;
import com.corrodinggames.rts.gameFramework.f.f$2;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.f.i;
import com.corrodinggames.rts.gameFramework.f.y;
import java.util.ArrayList;

public class f {

   Paint a;
   boolean b = false;
   float c = 0.0F;
   float d = 0.0F;
   Rect e = new Rect();
   Rect f = new Rect();
   Rect g = new Rect();
   y h = null;
   ArrayList i = new ArrayList();
   int j = 30;
   int k = 140;
   int l = 30;
   final Rect m = new Rect();
   boolean n;
   boolean o;
   static String p = com.corrodinggames.rts.gameFramework.h.a.a("gui.rategame.text", new Object[0]);
   static String q = com.corrodinggames.rts.gameFramework.h.a.a("gui.rategame.yes", new Object[0]);
   static String r = com.corrodinggames.rts.gameFramework.h.a.a("gui.rategame.no", new Object[0]);
   boolean s = false;
   float t = 0.0F;


   public f() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.a();
      this.a = new Paint();
      this.a.a(true);
      this.a.a(Paint$Align.b);
      this.a.a(255, 0, 255, 0);
      var1.a(this.a, 34.0F);
   }

   void a() {
      this.i.clear();
      this.i.add(new f$1(this, "Finish game"));
      this.i.add(new f$2(this, "Keep playing"));
   }

   boolean b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return (var1.dq || var1.dt) && !var1.dr;
   }

   public void a(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      g var3 = var2.bS;
      boolean var4 = this.b();
      this.m.h();
      this.n = false;
      if(var4 && !var3.u) {
         int var5 = var2.a(this.j);
         int var6 = var2.a(this.k);
         int var10000 = var5 + var2.a(this.l);
         int var8 = var6 + var2.a(this.l);
         int var9 = this.i.size();
         float var12 = 0.0F;
         if(var3.c) {
            var3.d += 2.0F * var1 / 60.0F;
            var12 = var3.d;
            var12 = com.corrodinggames.rts.gameFramework.f.b(var12, 0.0F, 1.0F);
            var12 = com.corrodinggames.rts.gameFramework.f.i(var12);
         }

         int var10 = var2.a(40) + var8 * var9;
         int var11 = var2.a(140);
         if(var3.b) {
            var11 += var2.a(50);
         }

         if(var3.c) {
            var10 = (int)com.corrodinggames.rts.gameFramework.f.f((float)var10, var2.cF * 0.9F, var12);
            var11 = (int)com.corrodinggames.rts.gameFramework.f.f((float)var11, var2.cH * 0.9F, var12);
         }

         float var13 = var2.cp - (float)(var11 / 2);
         if(!var3.c) {
            var13 = com.corrodinggames.rts.gameFramework.f.f(var13, var13 / 2.0F, 1.0F - var12);
         }

         if(var13 < 20.0F) {
            var13 = 20.0F;
         }

         this.g.b = (int)var13;
         this.g.d = this.g.b + var11;
         this.g.a = (int)(var2.cF / 2.0F - (float)(var10 / 2));
         this.g.c = (int)(var2.cF / 2.0F + (float)(var10 / 2));
         this.m.a(this.g);
         if(this.m.b((int)var3.z, (int)var3.A)) {
            this.n = var3.U;
            var3.U = false;
            this.o = var3.I;
            var3.I = false;
         }

         var3.a(this.m);
      }

   }

   public void b(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      g var3 = var2.bS;
      boolean var4 = this.b();
      if(!var2.dq) {
         this.b = false;
      } else if(!this.b) {
         this.b = true;
         if(!var2.ar && var2.bQ.numberOfWins >= 5 && !var2.bQ.rateGameShown && com.corrodinggames.rts.gameFramework.l.au) {
            this.s = true;
            var2.bQ.rateGameShown = true;
            var2.bQ.save();
         }
      }

      if(!var4) {
         this.c = 0.0F;
      }

      if(var4 && !var3.u) {
         this.c += var1;
         if(var2.bx < 120) {
            this.c = 100000.0F;
         }

         if(this.n) {
            var3.U = true;
         }

         if(this.o) {
            var3.I = true;
         }

         boolean var5 = this.c > 80.0F;
         float var6 = 95.0F;
         boolean var7 = this.c > 100.0F;
         boolean var8 = this.c > 110.0F;
         int var9 = var2.a(this.j);
         int var10 = var2.a(this.k);
         int var11 = var9 + var2.a(this.l);
         int var10000 = var10 + var2.a(this.l);
         int var13 = this.i.size();
         int var14 = var10 * var13 + (var13 - 1) * var11;
         int var15 = (int)(var2.cF / 2.0F - (float)(var14 / 2));
         float var16 = 0.0F;
         float var17 = 0.0F;
         if(var3.c) {
            var16 = var3.d;
            var16 = com.corrodinggames.rts.gameFramework.f.b(var16, 0.0F, 1.0F);
            var16 = com.corrodinggames.rts.gameFramework.f.i(var16);
            var17 = var16 >= 1.0F?1.0F:0.0F;
         }

         if(var5) {
            float var18 = var3.bt.g;
            var3.bt.g = var17;
            var3.bt.c(var2.bO, this.m);
            var3.bt.g = var18;
         }

         int var29 = this.m.b + var2.a(40);
         int var19 = (int)(var2.cF / 2.0F);
         int var20 = this.m.d - var2.a(45);
         int var21 = var20;
         int var22 = Color.a(140, 100, 100, 100);
         Paint var23 = this.a;
         String var24 = "Victory!";
         if(var2.dt) {
            var24 = "Defeat";
         }

         float var25 = 1.0F;
         if(this.c < var6) {
            var25 = this.c / var6;
         }

         var29 = (int)((float)var29 - com.corrodinggames.rts.gameFramework.f.k(var25 * 90.0F) * 100.0F);
         var23.a(var24, 0, var24.length(), this.e);
         var2.bO.a(var24, (float)var19, (float)var29 - (var23.l() + var23.m()) / 2.0F, var23);
         int var26;
         if(this.c < 100.0F && !var2.dt) {
            this.d += var1;
            if(this.d > 0.5F) {
               this.d = 0.0F;
               var2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
               var2.bR.b();
               var26 = Color.a(255, com.corrodinggames.rts.gameFramework.f.a(0, 255), com.corrodinggames.rts.gameFramework.f.a(0, 255), com.corrodinggames.rts.gameFramework.f.a(0, 255));
               com.corrodinggames.rts.gameFramework.d.e var27 = var2.bR.b(0.0F, 0.0F, 0.0F, var26);
               if(var27 != null) {
                  var27.ar = 4;
                  var27.I = (float)var19 + com.corrodinggames.rts.gameFramework.f.c(-70.0F, 70.0F);
                  var27.J = (float)var29 + com.corrodinggames.rts.gameFramework.f.c(-15.0F, 15.0F);
                  var27.J += var2.cp / 2.0F;
                  var27.K += var2.cp / 2.0F;
                  var27.V = com.corrodinggames.rts.gameFramework.f.c(140.0F, 380.0F);
                  var27.W = var27.V;
                  var27.r = true;
                  var27.s = true;
                  var27.t = 5.0F;
                  var27.E = 2.0F;
                  var27.Q = com.corrodinggames.rts.gameFramework.f.c(-2.7F, 2.7F);
                  var27.P = com.corrodinggames.rts.gameFramework.f.c(-12.7F, 12.7F);
                  var27.G = 0.4F;
                  var27.F = 0.2F;
                  var27.R = com.corrodinggames.rts.gameFramework.f.c(2.0F, 4.0F);
                  var27.w = 2.0F;
                  var27.v = true;
                  var27.p = true;
               }
            }
         }

         var29 += 60;
         if(var8) {
            Rect var30 = this.e;
            Rect var31 = this.f;
            var30.a(this.m.a + var2.a(10), this.m.b + var2.a(60), this.m.c - var2.a(10), var20 - var2.a(10));
            var31.a(var30);
            if(!var3.c) {
               var30.b = this.m.d + var2.a(15);
               var30.d = var30.b + var2.a(200);
            }

            boolean var28 = var3.d >= 1.0F;
            if(this.h != null) {
               this.h.a(var30, var31, var1, var28, var3.b);
            }
         }

         for(var26 = 0; var26 < this.i.size(); ++var26) {
            if(var7) {
               b var32 = (b)this.i.get(var26);
               if(var3.a(var15, var21, var10, var9, var32.a(), i.a, false, var22, var3.aD, var3.br)) {
                  this.s = false;
                  var32.b();
               }
            }

            var15 += var11 + var10;
         }

         if(this.s) {
            this.c(var1);
         }

         if(this.m.b((int)var3.z, (int)var3.A)) {
            ;
         }

         var3.a(this.m);
      }

   }

   void c(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      g var3 = var2.bS;
      int var4 = var2.a(180);
      int var5 = (int)(var2.cF / 2.0F - (float)(var4 / 2));
      int var6 = var2.a(120);
      int var7 = (int)(var2.cH - (float)var6);
      this.g.a(var5, var7, var4, var6);
      var2.bO.b(this.g, var3.aP);
      int var8 = var5 + var4 / 2;
      Paint var10 = this.a;
      String var11 = p;
      var10.a(var11, 0, var11.length(), this.e);
      var2.bO.a(var11, (float)var8, (float)var7 - (var10.l() + var10.m()) / 2.0F, var10);
      int var12 = var7 + this.e.c();
      int var13 = var2.a(70);
      int var14 = var2.a(30);
      int var16 = var5 + var4 / 2 - var2.a(10) - var13;
      int var17 = Color.a(140, 100, 100, 100);
      String var15 = q;
      if(var3.a(var16, var12, var13, var14, var15, i.a, false, var17, var3.aD, (com.corrodinggames.rts.gameFramework.f.a.h)null)) {
         this.s = false;
         com.corrodinggames.rts.appFramework.f var18 = var2.ao;
         if(var18 == null) {
            com.corrodinggames.rts.gameFramework.l.b("showRateNow: gameView==null");
            return;
         }

         com.corrodinggames.rts.appFramework.g var19 = var18.i();
         if(var19 == null) {
            com.corrodinggames.rts.gameFramework.l.b("showRateNow: inGameActivity==null");
            return;
         }

         var19.l();
      }

      var16 = var5 + var4 / 2 + var2.a(10);
      var15 = r;
      if(var3.a(var16, var12, var13, var14, var15, i.a, false, var17, var3.aD, (com.corrodinggames.rts.gameFramework.f.a.h)null)) {
         this.s = false;
      }

   }

   public void c() {
      this.h = y.a();
   }

}
