package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.utility.y;

public class f extends j {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e[] c = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   int e;
   float f;
   float g;
   Rect h = new Rect();


   public strictfp ar b() {
      return ar.w;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.heavy_tank);
      c = com.corrodinggames.rts.game.n.a(var1);
      a = var0.bO.a(R$drawable.heavy_tank_dead);
      b = var0.bO.a(R$drawable.heavy_tank_turret);
      d = a(var1, var1.m() / 3, var1.l());
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:c[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return d;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return b;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && !this.bV && this.cm >= 1.0F && !this.cq;
   }

   public strictfp float G() {
      return 2.0F;
   }

   public strictfp float H() {
      return 2.0F;
   }

   public strictfp boolean e() {
      this.M = a;
      this.S(0);
      this.bT = false;
      this.a(ab.c);
      return true;
   }

   public strictfp f(boolean var1) {
      super(var1);
      this.a(c[7], 3);
      this.cj = 15.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 600.0F;
      this.cu = this.cv;
      this.M = c[7];
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         if(this.cf != 0.0F) {
            this.f += var1;
            if((double)this.f > 1.4D) {
               this.f = 0.0F;
               ++this.e;
               if(this.e > 2) {
                  this.e = 0;
               }
            }

            if(this.el) {
               this.g += var1;
               if(this.g > 9.0F) {
                  this.g = 0.0F;
                  this.K();
               }
            }
         }

      }
   }

   public strictfp void K() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      float var2 = this.cg;
      if(this.cf < 0.0F) {
         var2 += 180.0F;
      }

      for(int var3 = 0; var3 <= 1; ++var3) {
         float var4 = (float)(var3 == 0?-20:20);
         float var5 = this.eo + com.corrodinggames.rts.gameFramework.f.k(var2 + 180.0F + var4) * this.cj;
         float var6 = this.ep + com.corrodinggames.rts.gameFramework.f.j(var2 + 180.0F + var4) * this.cj;
         var1.bR.c(var5, var6, this.eq, var2 + 180.0F, 0);
      }

   }

   public strictfp float bN() {
      return 7000.0F;
   }

   public strictfp float q(int var1) {
      return 50.0F;
   }

   public strictfp void a(am var1, int var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      PointF var4;
      com.corrodinggames.rts.game.f var5;
      if(!var1.i()) {
         var4 = this.E(var2);
         var5 = com.corrodinggames.rts.game.f.a(this, var4.a, var4.b);
         PointF var6 = this.K(var2);
         var5.K = var6.a;
         var5.L = var6.b;
         var5.ar = Color.a(235, 150, 230, 40);
         var5.U = this.q(var2);
         var5.l = var1;
         var5.h = 60.0F;
         var5.t = 4.0F;
         var5.x = 2.0F;
         var5.aQ = true;
         var5.z = true;
         var3.bR.a(var5, -16716288);
         var3.bR.a(var4.a, var4.b, this.eq, -1127220);
         var3.bR.a(var4.a, var4.b, this.eq, this.cL[var2].a);
         var3.bM.a(com.corrodinggames.rts.gameFramework.a.e.u, 0.3F, this.eo, this.ep);
      } else {
         var4 = this.E(var2);
         var4.a(this.eo, this.ep);
         var5 = com.corrodinggames.rts.game.f.a(this, this.eo, this.ep);
         var5.ar = Color.a(255, 230, 230, 50);
         var5.U = this.q(var2);
         var5.l = var1;
         var5.h = 190.0F;
         var5.t = 0.5F;
         var5.r = 5.0F;
         var5.aH = true;
         var5.aI = 10.0F;
         var5.aJ = 15.0F;
         var5.aM = true;
         var5.aQ = true;
         var5.aG = true;
         var3.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.2F, this.eo, this.ep);
         var3.bR.a(var5, -1118720);
         var3.bR.a(var4.a, var4.b, this.eq, -1127220);
      }

   }

   public strictfp float m() {
      return 160.0F;
   }

   public strictfp float b(int var1) {
      return 70.0F;
   }

   public strictfp float z() {
      return 0.8F;
   }

   public strictfp float bc() {
      return 1.0F;
   }

   public strictfp float A() {
      return 1.9F;
   }

   public strictfp float B() {
      return 0.2F;
   }

   public strictfp float w(int var1) {
      return 0.12F;
   }

   public strictfp float c(int var1) {
      return 3.0F;
   }

   public strictfp float C() {
      return 0.05F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         y.a((com.corrodinggames.rts.game.units.y)this);
         return true;
      }
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return true;
   }

   public strictfp float g(int var1) {
      return 21.0F;
   }

   public strictfp Rect a_(boolean var1) {
      return var1?super.a_(var1):(this.bV?super.a_(var1):super.a(var1, this.e));
   }

   public strictfp float H(int var1) {
      return -2.0F;
   }

   public strictfp float I(int var1) {
      return 4.0F;
   }

   public strictfp float J(int var1) {
      return 12.0F;
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      y.a(this, var2);
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
