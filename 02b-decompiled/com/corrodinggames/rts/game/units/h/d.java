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
import com.corrodinggames.rts.gameFramework.utility.y;

public class d extends f {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   static PointF e = new PointF();
   Rect f = new Rect();


   public strictfp ar b() {
      return ar.o;
   }

   public static strictfp void f() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.scout_ship);
      a = var0.bO.a(R$drawable.scout_ship_dead);
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
      return 3.0F;
   }

   public strictfp float H() {
      return 3.0F;
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

   public strictfp d(boolean var1) {
      super(var1);
      this.T(17);
      this.U(31);
      this.cj = 15.0F;
      this.ck = this.cj - 2.0F;
      this.cv = 350.0F;
      this.cu = this.cv;
      this.M = b;
   }

   public strictfp void a(float var1) {
      super.a(var1);
   }

   public strictfp PointF E(int var1) {
      float var2 = 6.0F;
      float var3 = this.cg;
      float var4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(var3) * var2;
      float var5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(var3) * var2;
      e.a(var4, var5);
      return e;
   }

   public strictfp float q(int var1) {
      return 62.0F;
   }

   public strictfp void a(am var1, int var2) {
      l var3 = l.B();
      PointF var4 = this.E(var2);
      com.corrodinggames.rts.game.f var5;
      if(!var1.Q()) {
         var5 = com.corrodinggames.rts.game.f.a(this, var4.a, var4.b, this.eq, var2);
         PointF var6 = this.K(var2);
         var5.K = var6.a;
         var5.L = var6.b;
         var5.ar = Color.a(255, 230, 230, 50);
         var5.U = 62.0F;
         var5.l = var1;
         var5.h = 190.0F;
         var5.t = 2.0F;
         var5.aH = true;
         var5.aM = true;
         var5.aQ = true;
         var3.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.8F, this.eo, this.ep);
         var3.bR.a(this.eo, this.ep, this.eq, -1118720);
         var3.bR.a(var5, -1118720);
      } else {
         var5 = com.corrodinggames.rts.game.f.a(this, var4.a, var4.b, this.eq - 1.0F, var2);
         var5.ar = Color.a(255, 0, 0, 150);
         var5.x = 1.0F;
         var5.U = 42.0F;
         var5.l = var1;
         var5.h = 220.0F;
         var5.t = 1.9F;
         var5.aM = true;
         var5.aQ = true;
         var3.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.8F, this.eo, this.ep);
         var3.bR.a(this.eo, this.ep, this.eq, -1118720);
      }

   }

   public strictfp float m() {
      return 200.0F;
   }

   public strictfp float b(int var1) {
      return 170.0F;
   }

   public strictfp float z() {
      return 1.2F;
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

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp float C() {
      return 0.05F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp boolean c(float var1) {
      return super.c(var1);
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean ae() {
      return true;
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
