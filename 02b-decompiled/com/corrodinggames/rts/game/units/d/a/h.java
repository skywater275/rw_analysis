package com.corrodinggames.rts.game.units.d.a;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.game.units.d.a.c;
import com.corrodinggames.rts.gameFramework.l;

class h extends c {

   // $FF: synthetic field
   final b b;


   strictfp h(b var1) {
      super(var1);
      this.b = var1;
   }

   public strictfp String c() {
      return b.t;
   }

   public strictfp int d() {
      return ar.f.c();
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return b.dD();
   }

   strictfp float a() {
      return 165.0F;
   }

   public strictfp float b(int var1) {
      return 41.0F;
   }

   public strictfp float a(int var1) {
      return 30.0F;
   }

   public strictfp float g(int var1) {
      return 21.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.c(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this.b, var3.a, var3.b);
      PointF var5 = this.b.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.l = var1;
      var4.h = 60.0F;
      var4.t = 5.0F;
      var4.ar = Color.a(255, 100, 30, 30);
      var4.U = this.b(var2);
      var4.P = 5;
      var4.x = 1.0F;
      l var6 = l.B();
      var6.bR.a(var3.a, var3.b, this.b.eq, -1127220);
      var6.bR.a(var3.a, var3.b, this.b.eq, this.b.cL[var2].a);
      float var7 = 1.0F + com.corrodinggames.rts.gameFramework.f.c(-0.07F, 0.07F);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.t, 0.3F, var7, var3.a, var3.b);
   }

   public strictfp int b() {
      return 1;
   }

   public strictfp void a(c var1) {}

   public strictfp void a(float var1) {
      this.b.s(var1);
   }
}
