package com.corrodinggames.rts.game.units.b;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.b.b;
import com.corrodinggames.rts.gameFramework.l;

public class e extends b {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   float f = 0.0F;
   Rect g = new Rect();


   public strictfp ar b() {
      return ar.n;
   }

   public static strictfp void f() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.gunship);
      c = var0.bO.a(R$drawable.gunship_shadow);
      a = var0.bO.a(R$drawable.gunship_dead);
      e = n.a(b);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:e[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return null;
   }

   public strictfp boolean e() {
      l var1 = l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = a;
      this.S(0);
      this.bT = false;
      return true;
   }

   public strictfp e(boolean var1) {
      super(var1);
      this.T(25);
      this.U(35);
      this.cj = 15.0F;
      this.ck = this.cj + 0.0F;
      this.cv = 260.0F;
      this.cu = this.cv;
      this.M = b;
      this.N = c;
      this.eq = 0.0F;
      this.S(5);
   }

   public strictfp boolean I() {
      return true;
   }

   public strictfp boolean i() {
      return true;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         this.f += 2.0F * var1;
         if(this.f > 360.0F) {
            this.f -= 360.0F;
         }

         this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 20.0F + com.corrodinggames.rts.gameFramework.f.j(this.f) * 1.5F, 0.1F * var1);
      }
   }

   public strictfp PointF E(int var1) {
      float var2 = this.g(var1);
      float var3 = this.cg;
      float var4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(var3) * var2;
      float var5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(var3) * var2;
      bg.a(var4, var5);
      return bg;
   }

   public strictfp float q(int var1) {
      return 35.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.ar = Color.a(255, 150, 230, 40);
      var4.U = this.q(var2);
      var4.l = var1;
      var4.h = 80.0F;
      var4.t = 4.0F;
      var4.x = 2.0F;
      l var6 = l.B();
      var6.bR.a(var3.a, var3.b, this.eq, -1127220);
      var6.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.u, 0.3F, this.eo, this.ep);
   }

   public strictfp float m() {
      return 140.0F;
   }

   public strictfp float b(int var1) {
      return 40.0F;
   }

   public strictfp float z() {
      return this.eq < 15.0F?0.0F:1.4F;
   }

   public strictfp float A() {
      return 4.0F;
   }

   public strictfp float B() {
      return 0.4F;
   }

   public strictfp boolean bi() {
      return true;
   }

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp boolean E() {
      return false;
   }

   public strictfp float C() {
      return 0.2F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return false;
   }

   public strictfp float g(int var1) {
      return 15.0F;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
