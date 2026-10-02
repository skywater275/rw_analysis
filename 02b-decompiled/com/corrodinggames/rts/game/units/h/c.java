package com.corrodinggames.rts.game.units.h;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h.f;
import com.corrodinggames.rts.gameFramework.l;

public class c extends f {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   Rect e = new Rect();


   public strictfp ar b() {
      return ar.p;
   }

   public strictfp float bN() {
      return 1500.0F;
   }

   public static strictfp void f() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.gun_boat);
      a = var0.bO.a(R$drawable.gun_boat_dead);
      c = a(b, b.m(), b.l());
      d = n.a(b);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:d[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp boolean F() {
      return l.B().bQ.renderExtraShadows && this.eq > -2.0F;
   }

   public strictfp float G() {
      return 1.0F;
   }

   public strictfp float H() {
      return 1.0F;
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

   public strictfp c(boolean var1) {
      super(var1);
      this.T(15);
      this.U(27);
      this.cj = 12.0F;
      this.ck = this.cj - 2.0F;
      this.cv = 170.0F;
      this.cu = this.cv;
      this.M = b;
   }

   public strictfp void a(float var1) {
      super.a(var1);
   }

   public strictfp float q(int var1) {
      return 12.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.eq = this.eq;
      var4.U = this.q(var2);
      var4.l = var1;
      var4.h = 30.0F;
      var4.t = 8.0F;
      var4.S = false;
      var4.ar = Color.a(255, 180, 180, 0);
      l var6 = l.B();
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.s, 0.2F, var3.a, var3.b);
      var6.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      var6.bR.a(var3.a, var3.b, this.eq, -1118720);
   }

   public strictfp float m() {
      return 120.0F;
   }

   public strictfp float b(int var1) {
      return 60.0F;
   }

   public strictfp float z() {
      return 1.5F;
   }

   public strictfp float A() {
      return 2.8F;
   }

   public strictfp float B() {
      return 0.35F;
   }

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp float C() {
      return 0.06F;
   }

   public strictfp float D() {
      return 0.2F;
   }

   public strictfp boolean c(float var1) {
      return super.c(var1);
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return false;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
