package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.utility.y;

public class k extends j {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e f = null;
   Rect g = new Rect();


   public strictfp ar b() {
      return ar.r;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      b = var0.bO.a(R$drawable.laser_tank_base);
      a = var0.bO.a(R$drawable.laser_tank_dead);
      c = var0.bO.a(R$drawable.laser_tank_turrent);
      d = var0.bO.a(R$drawable.laser_tank_charge);
      e = com.corrodinggames.rts.game.n.a(b);
      f = a(b, b.m(), b.l());
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:e[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return f;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && !this.bV;
   }

   public strictfp float G() {
      return 2.0F;
   }

   public strictfp float H() {
      return 2.0F;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return c;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = a;
      this.S(0);
      this.bT = false;
      this.a(ab.b);
      return true;
   }

   public strictfp k(boolean var1) {
      super(var1);
      this.a(b, 1);
      this.cj = 14.0F;
      this.ck = this.cj + 2.0F;
      this.cv = 300.0F;
      this.cu = this.cv;
      this.M = b;
   }

   public strictfp void a(float var1) {
      super.a(var1);
   }

   public strictfp float q(int var1) {
      return 450.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
      var4.U = this.q(var2);
      var4.l = var1;
      var4.h = 8.0F;
      var4.B = true;
      var4.A = true;
      var4.aQ = true;
      var4.ar = Color.a(80, 255, 0, 0);
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      var5.bR.a(var3.a, var3.b, this.eq, -1127220);
      var5.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      var5.bM.a(com.corrodinggames.rts.gameFramework.a.e.y, 0.3F, var3.a, var3.b);
   }

   public strictfp float bW() {
      return this.cL[0].e > 0.0F?1.0F - this.cL[0].e / this.b(0):(this.cL[0].f != 0.0F?this.cL[0].f / this.e(0):super.bW());
   }

   public strictfp boolean bX() {
      return this.cL[0].e > 0.0F;
   }

   public strictfp float m() {
      return 190.0F;
   }

   public strictfp float b(int var1) {
      return 450.0F;
   }

   public strictfp float e(int var1) {
      return 80.0F;
   }

   public strictfp float z() {
      return 0.7F;
   }

   public strictfp float A() {
      return 1.5F;
   }

   public strictfp float B() {
      return 0.1F;
   }

   public strictfp float c(int var1) {
      return 3.0F;
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         y.a((com.corrodinggames.rts.game.units.y)this);
         if(!this.bV) {
            float var3 = this.cL[0].f / this.e(0);
            if(var3 != 0.0F) {
               PointF var4 = this.E(0);
               var2.bO.i();
               var2.bO.b(var4.a - var2.cw, var4.b - var2.cx);
               var2.bO.a(var3, var3);
               var2.bO.a(d, 0.0F, 0.0F, (Paint)null);
               var2.bO.j();
            }
         }

         return true;
      }
   }

   public strictfp float C() {
      return 0.07F;
   }

   public strictfp float D() {
      return 0.12F;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return true;
   }

   public strictfp float g(int var1) {
      return 19.0F;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
