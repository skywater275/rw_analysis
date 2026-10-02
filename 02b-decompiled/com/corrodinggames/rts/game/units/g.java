package com.corrodinggames.rts.game.units;

import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d;
import com.corrodinggames.rts.game.units.y;

public class g extends com.corrodinggames.rts.game.units.e.j implements d {

   public boolean a;
   PointF[] b = new PointF[6];
   PointF[] c;
   static Paint d;
   static Paint e;
   static Paint f;
   int g;
   float h;
   float i;
   int j;


   public strictfp ar f() {
      return ar.h;
   }

   public strictfp PointF[] b() {
      return this.b;
   }

   public strictfp PointF[] e_() {
      return this.c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:dN[this.bX.R()];
   }

   public strictfp boolean a(am var1) {
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?com.corrodinggames.rts.game.units.e.b.b:com.corrodinggames.rts.game.units.e.b.d[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return null;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = com.corrodinggames.rts.game.units.e.b.b;
      this.S(0);
      this.bT = false;
      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.o, 0.8F, this.eo, this.ep);
      this.bq();
      return true;
   }

   public strictfp g(boolean var1) {
      super(var1);
      this.c = new PointF[this.b.length];
      d = new Paint();
      d.a(40, 0, 255, 0);
      d.a(true);
      d.a(2.0F);
      d.a(Paint$Cap.b);
      e = new Paint();
      e.a(d);
      e.a(55, 255, 60, 60);
      f = new Paint();
      f.a(60, 255, 255, 255);
      this.T(20);
      this.U(20);
      this.cj = 10.0F;
      this.eo = -1000.0F;
      this.ep = -1000.0F;
      this.ck = this.cj;
      this.cv = 170000.0F;
      this.cu = this.cv;
      this.M = com.corrodinggames.rts.game.units.e.b.b;

      for(int var2 = 0; var2 < this.b.length; ++var2) {
         this.b[var2] = new PointF();
         this.c[var2] = new PointF();
      }

   }

   public static strictfp void a(float var0, d var1) {
      y var2 = (y)var1;
      PointF[] var3 = var1.b();
      PointF[] var4 = var1.e_();
      am var5 = var2.X();
      var2.aN = var5 != null;
      int var6;
      PointF var7;
      PointF var8;
      if(var5 != null) {
         for(var6 = 0; var6 < var3.length; ++var6) {
            var7 = var3[var6];
            var8 = var4[var6];
            var7.a = com.corrodinggames.rts.gameFramework.f.a(var7.a, var8.a, 0.1F * var0);
            var7.b = com.corrodinggames.rts.gameFramework.f.a(var7.b, var8.b, 0.1F * var0);
            var7.a += (var8.a - var7.a) * 0.04F * var0;
            var7.b += (var8.b - var7.b) * 0.04F * var0;
            float var9 = var5.cj * 0.75F;
            if(com.corrodinggames.rts.gameFramework.f.c(var7.a - var8.a) < 1.0F) {
               var8.a = com.corrodinggames.rts.gameFramework.f.d(-var9, var9);
            }

            if(com.corrodinggames.rts.gameFramework.f.c(var7.b - var8.b) < 1.0F) {
               var8.b = com.corrodinggames.rts.gameFramework.f.d(-var9, var9);
            }
         }
      } else if(var3[0].a != 0.0F || var3[0].b != 0.0F) {
         for(var6 = 0; var6 < var3.length; ++var6) {
            var7 = var3[var6];
            var8 = var4[var6];
            var7.a = 0.0F;
            var7.b = 0.0F;
            var8.a = 0.0F;
            var8.b = 0.0F;
         }
      }

   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         a(var1, this);
      }

      this.cu = this.cv;
      ++this.g;
      this.h += var1;
      this.i += var1;
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(!this.a) {
         if(this.i > 3.0F) {
            this.i = 0.0F;
            this.w();
         }

      } else {
         com.corrodinggames.rts.gameFramework.l.e("Stress test active");

         for(int var3 = 0; var3 < 6000; ++var3) {
            this.w();
         }

         this.ci();
      }
   }

   public strictfp void w() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      ++this.j;
      int var2 = ar.ae.size();
      int var3 = com.corrodinggames.rts.gameFramework.f.a(this, 0, var2 - 1, 1 + this.j);
      as var4 = (as)ar.ae.get(var3);
      boolean var5 = true;
      if(com.corrodinggames.rts.game.units.custom.l.b == var4) {
         var5 = false;
      }

      if(var4 == ar.S) {
         var5 = false;
      }

      if(var5) {
         am var6 = var4.a();
         var6.eo = (float)com.corrodinggames.rts.gameFramework.f.a(this, 200, (int)var1.bL.i() - 200, 2 + this.g + this.j);
         var6.ep = (float)com.corrodinggames.rts.gameFramework.f.a(this, 200, (int)var1.bL.j() - 200, 3 + this.g + this.j + this.j * 9);

         try {
            var6.Q(com.corrodinggames.rts.gameFramework.f.a(this, 0, 3, 4 + this.g + this.j + this.j * 9));
         } catch (com.corrodinggames.rts.game.b.f var8) {
            throw new RuntimeException(var8);
         }

         com.corrodinggames.rts.game.n.c(var6);
         if(var6.u()) {
            var6.a();
         }

         if(var6.bO()) {
            var6.a();
         }
      }

   }

   public strictfp void a(float var1, boolean var2) {
      if(!this.bV) {
         ;
      }

   }

   public strictfp float e(int var1) {
      return 0.0F;
   }

   public strictfp float f(int var1) {
      return 0.0F;
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         return true;
      }
   }

   public strictfp void a(am var1, int var2) {}

   public strictfp boolean b_() {
      return false;
   }

   public strictfp int y() {
      return 850000;
   }

   public strictfp float b(am var1) {
      return 1.0E7F;
   }

   public strictfp float c(am var1) {
      return 1.0E7F;
   }

   public strictfp float m() {
      return 30.0F;
   }

   public strictfp float b(int var1) {
      return 100.0F;
   }

   public strictfp float z() {
      return 0.0F;
   }

   public strictfp float A() {
      return this.cK()?4.7F:4.8F;
   }

   public strictfp float B() {
      return 0.35F;
   }

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp float C() {
      return 0.04F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp boolean E() {
      return true;
   }

   public strictfp float g(int var1) {
      return 10.0F;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && !this.bV;
   }

   public strictfp float G() {
      return 1.0F;
   }

   public strictfp float H() {
      return 1.0F;
   }

   public strictfp boolean u() {
      return true;
   }

   public strictfp boolean I() {
      return false;
   }

   public strictfp boolean d(am var1) {
      return false;
   }

   public strictfp boolean J() {
      return true;
   }

   public strictfp float a(am var1, float var2, com.corrodinggames.rts.game.f var3) {
      var2 = 0.0F;
      return super.a(var1, var2, var3);
   }

   // $FF: synthetic method
   public as r() {
      return this.f();
   }
}
