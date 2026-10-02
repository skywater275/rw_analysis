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

public class g extends h {

   float a = 0.0F;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   Rect f = new Rect();


   public strictfp ar b() {
      return ar.j;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      c = var0.bO.a(R$drawable.hover_tank);
      b = var0.bO.a(R$drawable.hover_tank_dead);
      d = var0.bO.a(R$drawable.hover_tank_shadow);
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
      this.M = b;
      this.S(0);
      this.bT = false;
      this.a(ab.b);
      return true;
   }

   public strictfp g(boolean var1) {
      super(var1);
      this.b(c);
      this.cj = 7.0F;
      this.ck = this.cj + 2.0F;
      this.cv = 150.0F;
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
      return 23.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.ar = Color.a(255, 50, 230, 50);
      var4.U = this.q(var2);
      var4.l = var1;
      var4.h = 85.0F;
      var4.t = 2.0F;
      var4.r = 6.0F;
      var4.s = 0.2F;
      var4.P = 6;
      var4.x = 1.0F;
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      var6.bR.a(var3.a, var3.b, this.eq, -14483678);
      var6.bR.a(var4, -16716288);
      float var7 = 1.3F + com.corrodinggames.rts.gameFramework.f.c(-0.07F, 0.07F);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.z, 0.3F, var7, var3.a, var3.b);
   }

   public strictfp boolean E() {
      return false;
   }

   public strictfp float m() {
      return 140.0F;
   }

   public strictfp float b(int var1) {
      return 90.0F;
   }

   public strictfp float z() {
      return 1.0F;
   }

   public strictfp float A() {
      return 180.0F;
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
      return 0.04F;
   }

   public strictfp float D() {
      return 0.09F;
   }

   public strictfp boolean bi() {
      return true;
   }

   public strictfp boolean bj() {
      return true;
   }

   public strictfp float c(int var1) {
      return 4.0F;
   }

   public strictfp float w(int var1) {
      return 0.2F;
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
      return 2.0F;
   }

   public strictfp float B() {
      return 0.5F;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
