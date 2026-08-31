package com.corrodinggames.rts.game.units.b;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.b.b;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public class f extends b {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e e = null;
   static com.corrodinggames.rts.gameFramework.m.e[] f = new com.corrodinggames.rts.gameFramework.m.e[10];
   boolean g = false;
   float o;
   float p = 0.0F;
   float q;
   Rect r = new Rect();
   Rect s = new Rect();


   public strictfp void a(as var1) {
      var1.a(this.p);
      var1.a(this.o);
      super.a(var1);
   }

   public strictfp void a(k var1) {
      if(var1.b() >= 9) {
         this.p = var1.g();
         this.o = var1.g();
         if(var1.b() == 8) {
            this.g = var1.e();
         }
      } else {
         this.o = 0.5F;
      }

      super.a(var1);
   }

   public strictfp ar b() {
      return ar.l;
   }

   public static strictfp void f() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.helicopter);
      c = var0.bO.a(R$drawable.helicopter_blades);
      d = var0.bO.a(R$drawable.helicopter_shadow);
      e = var0.bO.a(R$drawable.helicopter_shadow_blades);
      a = var0.bO.a(R$drawable.helicopter_dead);
      f = n.a(b);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:f[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return d;
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

   public strictfp f(boolean var1) {
      super(var1);
      this.T(26);
      this.U(46);
      this.cj = 13.0F;
      this.ck = this.cj + 2.0F;
      this.cv = 150.0F;
      this.cu = this.cv;
      this.M = b;
      this.N = d;
      this.eq = 0.0F;
      this.o = 0.14F;
      this.q = 0.0F;
      this.S(5);
   }

   public strictfp void n() {
      super.n();
      this.eq = 20.0F;
      this.o = 0.5F;
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
         this.o = com.corrodinggames.rts.gameFramework.f.a(this.o, 0.5F, 0.003F * var1);
         this.q += 70.0F * this.o * var1;
         if(this.q >= 360.0F) {
            this.q -= 360.0F;
            this.q += (float)com.corrodinggames.rts.gameFramework.f.a(this, 0, 4);
         }

         if(this.o > 0.4F) {
            this.p += 2.0F * var1;
            if(this.p > 360.0F) {
               this.p -= 360.0F;
            }

            this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 20.0F + com.corrodinggames.rts.gameFramework.f.j(this.p) * 1.5F, 0.1F * var1);
         }

      }
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.U = 17.0F;
      var4.l = var1;
      var4.h = 30.0F;
      var4.t = 8.0F;
      var4.S = false;
      var4.ar = Color.a(255, 180, 180, 0);
      var4.A = true;
      var4.aR = false;
      l var6 = l.B();
      float var7 = 1.0F + com.corrodinggames.rts.gameFramework.f.c(-0.08F, 0.08F);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.s, 0.2F, var7, var3.a, var3.b);
      var6.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      var6.bR.a(var3.a, var3.b, this.eq, -1118720);
   }

   public strictfp float m() {
      return 130.0F;
   }

   public strictfp float b(int var1) {
      return 60.0F;
   }

   public strictfp float z() {
      return this.eq < 15.0F?0.0F:2.2F;
   }

   public strictfp float bc() {
      return 0.1F;
   }

   public strictfp float A() {
      return 6.0F;
   }

   public strictfp float B() {
      return 0.4F;
   }

   public strictfp boolean bi() {
      return true;
   }

   public strictfp boolean bj() {
      return true;
   }

   public strictfp float c(int var1) {
      return 16.0F;
   }

   public strictfp Rect a_(boolean var1) {
      return super.a_(var1);
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         if(!this.bV) {
            Paint var2 = this.aN();
            l var3 = l.B();
            this.s.a(0, 0, c.m(), c.l());
            float var4 = this.q;
            if(this.co) {
               ;
            }

            var3.bO.a(c, this.s, this.eo - l.B().cw, this.ep - l.B().cx - this.eq, var4, var2);
         }

         return true;
      }
   }

   public strictfp float C() {
      return 0.07F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp float g(int var1) {
      return 7.0F;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.b();
   }

}
