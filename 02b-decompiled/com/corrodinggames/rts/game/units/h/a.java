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

public class a extends f {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   Rect f = new Rect();


   public strictfp ar b() {
      return ar.u;
   }

   public strictfp float bN() {
      return 9000.0F;
   }

   public static strictfp void f() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.battle_ship_t2);
      a = var0.bO.a(R$drawable.battle_ship_t2_dead);
      c = var0.bO.a(R$drawable.battle_ship_t2_turret);
      e = n.a(b);
      d = a(b, b.m(), b.l());
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:e[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return d;
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
      this.b(b);
      this.cj = 20.0F;
      this.ck = this.cj;
      this.cv = 1200.0F;
      this.cu = this.cv;
      this.M = b;
   }

   public strictfp void a(float var1) {
      super.a(var1);
   }

   public strictfp float q(int var1) {
      return 65.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.U = this.q(var2);
      var4.l = var1;
      var4.h = 80.0F;
      var4.x = 2.0F;
      var4.t = 4.0F;
      var4.S = true;
      var4.ar = Color.a(255, 180, 180, 0);
      var4.aQ = true;
      l var6 = l.B();
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.r, 0.2F, var3.a, var3.b);
      var6.bR.a(var4, -1118720);
      com.corrodinggames.rts.gameFramework.d.e var7 = var6.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      if(var7 != null) {
         com.corrodinggames.rts.gameFramework.d.c.a(var7, this);
      }

      var6.bR.a(var3.a, var3.b, this.eq, -1118720);
   }

   public strictfp float m() {
      return 240.0F;
   }

   public strictfp float z() {
      return 0.8F;
   }

   public strictfp float bc() {
      return 1.0F;
   }

   public strictfp float C(int var1) {
      return this.ci && (double)this.bc() > 0.95D?(var1 == 0?this.cg + 140.0F:this.cg - 140.0F):this.cg;
   }

   public strictfp float A() {
      return 1.8F;
   }

   public strictfp float B() {
      return 0.08F;
   }

   public strictfp float c(int var1) {
      return 2.5F;
   }

   public strictfp float w(int var1) {
      return 0.08F;
   }

   public strictfp float C() {
      return 0.03F;
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
      return false;
   }

   public strictfp float g(int var1) {
      return 15.0F;
   }

   public strictfp int bl() {
      return 2;
   }

   public strictfp PointF G(int var1) {
      PointF var2 = super.G(var1);
      float var3 = var2.a;
      float var4 = var2.b;
      float var5;
      if(var1 == 0) {
         var5 = 22.0F;
      } else {
         var5 = 4.0F;
      }

      var3 += com.corrodinggames.rts.gameFramework.f.k(this.cg) * var5;
      var4 += com.corrodinggames.rts.gameFramework.f.j(this.cg) * var5;
      bh.a(var3, var4);
      return bh;
   }

   public strictfp float b(int var1) {
      return (float)(120 - var1 * 28);
   }

   public strictfp float e(int var1) {
      return (float)(var1 * 30);
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      y.a(this, var2);
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
