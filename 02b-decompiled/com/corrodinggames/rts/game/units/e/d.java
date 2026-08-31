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

public class d extends j {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   int e;
   float f;
   Rect g = new Rect();


   public strictfp ar b() {
      return ar.F;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.experimental_tank);
      d = com.corrodinggames.rts.game.n.a(var1);
      a = var0.bO.a(R$drawable.experimental_tank_dead);
      b = var0.bO.a(R$drawable.experimental_tank_turret);
      c = a(var1, var1.m() / 2, var1.l());
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:d[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && this.eq > -2.0F && this.cm >= 1.0F;
   }

   public strictfp float G() {
      return 4.0F;
   }

   public strictfp float H() {
      return 4.0F;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return this.R(var1)?null:b;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.a(ab.e);
      this.M = a;
      this.S(0);
      this.bT = false;
      return true;
   }

   public strictfp d(boolean var1) {
      super(var1);
      this.a(d[7], 2);
      this.cj = 37.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 6000.0F;
      this.cu = this.cv;
      this.M = d[7];
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         if(this.cl != 0.0F) {
            this.S(2);
         } else {
            this.S(4);
         }
      }

      if(this.cK) {
         this.f += var1;
         if(this.f > 5.0F) {
            this.f = 0.0F;
            this.e = 1 - this.e;
         }
      }

   }

   public strictfp float bN() {
      return 80000.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3;
      com.corrodinggames.rts.game.f var4;
      if(!this.R(var2)) {
         var3 = this.E(var2);
         var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
         PointF var5 = this.K(var2);
         var4.K = var5.a;
         var4.L = var5.b;
         var4.ar = Color.a(255, 247, 212, 129);
         var4.h = 120.0F;
         var4.t = 5.0F;
         var4.l = var1;
         var4.Y = 60.0F;
         var4.U = 40.0F;
         var4.Z = 45.0F;
         var4.aa = true;
         var4.x = 2.0F;
         var4.aQ = true;
         var4.P = 9;
         var4.x = 1.0F;
         var4.em = this.em;
         com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
         var6.bR.a(var3.a, var3.b, this.eq, 16745216);
         var6.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
         var6.bR.a(var4, -1127220);
         var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.v, 0.3F, this.eo, this.ep);
      } else {
         var3 = this.E(var2);
         var3.a(this.eo, this.ep);
         var4 = com.corrodinggames.rts.game.f.a(this, this.eo, this.ep);
         var4.ar = Color.a(255, 230, 230, 50);
         var4.U = 60.0F;
         var4.l = var1;
         var4.h = 190.0F;
         var4.t = 3.0F;
         var4.r = 6.0F;
         var4.aH = true;
         var4.aI = 10.0F;
         var4.aJ = 15.0F;
         var4.aM = true;
         var4.aQ = true;
         var4.aG = true;
         var4.em = this.em;
         com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
         var7.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.2F, this.eo, this.ep);
         var7.bR.a(var4, -1118720);
         var7.bR.a(var3.a, var3.b, this.eq, -1127220);
      }

   }

   public strictfp boolean a(int var1, am var2, boolean var3, boolean var4) {
      if(!var3 && var4 && !this.h(var2)) {
         return false;
      } else {
         if(this.R(var1)) {
            if(!var2.i()) {
               return false;
            }
         } else if(var2.i()) {
            return false;
         }

         return true;
      }
   }

   public strictfp float m() {
      return 310.0F;
   }

   public strictfp float b(int var1) {
      if(this.R(var1)) {
         var1 -= 4;
      }

      return (float)(110 - var1 * 20);
   }

   public strictfp float e(int var1) {
      if(this.R(var1)) {
         var1 -= 4;
      }

      return (float)(var1 * 20);
   }

   public strictfp float z() {
      return 0.4F;
   }

   public strictfp float bc() {
      return 1.0F;
   }

   public strictfp int bh() {
      return 1;
   }

   public strictfp float A() {
      return 0.8F;
   }

   public strictfp float B() {
      return 0.04F;
   }

   public strictfp float w(int var1) {
      return this.R(var1)?1.0F:0.08F;
   }

   public strictfp float c(int var1) {
      return this.R(var1)?4.5F:2.5F;
   }

   public strictfp float C() {
      return 0.03F;
   }

   public strictfp float D() {
      return 0.08F;
   }

   public strictfp Rect a_(boolean var1) {
      if(this.bV && !var1) {
         return super.a_(var1);
      } else if(var1) {
         return super.a_(var1);
      } else {
         byte var2 = 0;
         byte var3 = 0;
         int var4 = var2 + this.e * this.es;
         this.g.a(var4, var3, var4 + this.es, var3 + this.et);
         return this.g;
      }
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
      return 20.0F;
   }

   public strictfp PointF G(int var1) {
      PointF var2 = super.G(var1);
      float var3 = var2.a;
      float var4 = var2.b;
      if(!this.R(var1)) {
         if(var1 <= 1) {
            var3 += com.corrodinggames.rts.gameFramework.f.k(this.cg) * 5.0F;
            var4 += com.corrodinggames.rts.gameFramework.f.j(this.cg) * 5.0F;
         }

         float var5 = (float)(-45 + 90 * var1);
         var3 += com.corrodinggames.rts.gameFramework.f.k(this.cg + var5) * 18.0F;
         var4 += com.corrodinggames.rts.gameFramework.f.j(this.cg + var5) * 18.0F;
      }

      bh.a(var3, var4);
      return bh;
   }

   public strictfp boolean R(int var1) {
      return var1 >= 4;
   }

   public strictfp int bl() {
      return 6;
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      y.a(this, var2);
   }

   public strictfp int cw() {
      return 5;
   }

   public strictfp boolean dd() {
      return true;
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

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
