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

public class a extends b {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   float f;
   Rect g = new Rect();


   public strictfp ar b() {
      return ar.m;
   }

   public static strictfp void f() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.ship);
      c = var0.bO.a(R$drawable.ship_shadow);
      a = var0.bO.a(R$drawable.ship_dead);
      e = n.a(b);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:e[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return d;
   }

   public strictfp boolean e() {
      l var1 = l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = a;
      this.S(0);
      this.bT = false;
      return true;
   }

   public strictfp a(boolean var1) {
      super(var1);
      this.T(24);
      this.U(22);
      this.cj = 11.0F;
      this.ck = this.cj + 0.0F;
      this.cv = 250.0F;
      this.cu = this.cv;
      this.M = b;
      this.N = c;
      this.eq = 0.0F;
      this.f = 0.18F;
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
         this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 20.0F, 0.3F * var1);
      }
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.U = 30.0F;
      var4.l = var1;
      var4.h = 75.0F;
      var4.t = 6.0F;
      var4.x = 2.0F;
      var4.y = 4.0F;
      var4.ar = Color.a(250, 74, 232, 255);
      l var6 = l.B();
      com.corrodinggames.rts.gameFramework.d.e var7 = var6.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      if(var7 != null) {
         var7.aq = 10;
      }

      float var8 = 1.0F + com.corrodinggames.rts.gameFramework.f.c(-0.1F, 0.1F);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.y, 0.14F, var8, var3.a, var3.b);
   }

   public strictfp float m() {
      return 170.0F;
   }

   public strictfp float b(int var1) {
      return 40.0F;
   }

   public strictfp float z() {
      return this.eq < 15.0F?0.0F:2.4F;
   }

   public strictfp float A() {
      return 3.7F;
   }

   public strictfp float B() {
      return 0.4F;
   }

   public strictfp float c(int var1) {
      return 3.7F;
   }

   public strictfp boolean bm() {
      return false;
   }

   public strictfp float w(int var1) {
      return 0.4F;
   }

   public strictfp boolean E() {
      return false;
   }

   public strictfp float g(int var1) {
      return 10.0F;
   }

   public strictfp float C() {
      return 0.1F;
   }

   public strictfp float D() {
      return 0.16F;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return true;
   }

   public strictfp boolean ag() {
      return false;
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

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
