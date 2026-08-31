package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.h;
import com.corrodinggames.rts.gameFramework.utility.y;

public class e extends h {

   float a = 0.0F;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   Rect f = new Rect();


   public strictfp ar b() {
      return ar.x;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      c = var0.bO.a(R$drawable.heavy_hover_tank);
      b = var0.bO.a(R$drawable.heavy_hover_tank_dead);
      d = var0.bO.a(R$drawable.heavy_hover_tank_shadow);
      e = com.corrodinggames.rts.game.n.a(c);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?b:e[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return d;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return null;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = b;
      this.S(0);
      this.bT = false;
      this.a(ab.c);
      return true;
   }

   public strictfp e(boolean var1) {
      super(var1);
      this.T(24);
      this.U(36);
      this.cj = 11.0F;
      this.ck = this.cj + 2.0F;
      this.cv = 450.0F;
      this.cu = this.cv;
      this.M = c;
      this.N = d;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV && this.bT()) {
         this.a += 3.0F * var1;
         if(this.a > 360.0F) {
            this.a -= 360.0F;
         }

         this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 4.0F + com.corrodinggames.rts.gameFramework.f.j(this.a) * 1.5F, 0.1F * var1);
      }
   }

   public strictfp float q(int var1) {
      return 40.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.ar = Color.a(255, 230, 0, 50);
      var4.U = this.q(var2);
      var4.l = var1;
      var4.h = 95.0F;
      var4.t = 1.0F;
      var4.r = 7.0F;
      var4.s = 0.2F;
      var4.P = 7;
      var4.x = 1.0F;
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.d.e var7 = var6.bR.a(var3.a, var3.b, this.eq, -56798);
      if(var7 != null) {
         var7.E = 0.7F;
         var7.V = 30.0F;
         var7.W = var7.V;
         com.corrodinggames.rts.gameFramework.d.c.a(var7, this);
      }

      var6.bR.a(var4, -1179648);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.z, 0.3F, var3.a, var3.b);
   }

   public strictfp boolean E() {
      return false;
   }

   public strictfp float m() {
      return 160.0F;
   }

   public strictfp float b(int var1) {
      return 75.0F;
   }

   public strictfp float z() {
      return 0.7F;
   }

   public strictfp float A() {
      return 20.0F;
   }

   public strictfp void i(float var1) {
      this.cg += var1;
      if(this.cg > 180.0F) {
         this.cg -= 360.0F;
      }

      if(this.cg < -180.0F) {
         this.cg += 360.0F;
      }

   }

   public strictfp float C() {
      return 0.06F;
   }

   public strictfp float D() {
      return 0.09F;
   }

   public strictfp float c(int var1) {
      return 2.4F;
   }

   public strictfp boolean bi() {
      return true;
   }

   public strictfp boolean bj() {
      return true;
   }

   public strictfp float d(boolean var1) {
      return this.cL[0].a + 90.0F;
   }

   public strictfp boolean c(float var1) {
      return super.c(var1);
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return true;
   }

   public strictfp float g(int var1) {
      return 16.0F;
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
