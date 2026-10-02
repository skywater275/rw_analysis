package com.corrodinggames.rts.game.units.d.a;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.game.units.d.a.c;
import com.corrodinggames.rts.game.units.d.a.f;
import com.corrodinggames.rts.gameFramework.l;

class g extends c {

   // $FF: synthetic field
   final b b;


   strictfp g(b var1) {
      super(var1);
      this.b = var1;
   }

   public strictfp String c() {
      return b.v;
   }

   public strictfp int d() {
      return ar.f.c() + b.dL.c() + b.dM.c();
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return b.dF();
   }

   strictfp float a() {
      return 320.0F;
   }

   public strictfp float a(int var1) {
      return 13.0F;
   }

   public strictfp float b(int var1) {
      return 40.0F;
   }

   public strictfp PointF c(int var1) {
      PointF var2 = b.c(this.b, var1);
      float var3 = this.b.E()?this.b.cg:this.b.cL[var1].a;
      var3 += (float)(this.b.k == 1?-90:90);
      var2.a += com.corrodinggames.rts.gameFramework.f.k(var3) * 4.0F;
      var2.b += com.corrodinggames.rts.gameFramework.f.j(var3) * 4.0F;
      return var2;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.c(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this.b, var3.a, var3.b);
      PointF var5 = this.b.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.l = var1;
      var4.h = 60.0F;
      var4.t = 9.0F;
      var4.ar = Color.a(255, 180, 30, 30);
      var4.U = this.b(var2);
      var4.P = 5;
      var4.x = 1.0F;
      l var6 = l.B();
      var6.bR.a(var3.a, var3.b, this.b.eq, -1127220);
      var6.bR.a(var3.a, var3.b, this.b.eq, this.b.cL[var2].a);
      float var7 = 1.0F + com.corrodinggames.rts.gameFramework.f.c(-0.07F, 0.07F);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.t, 0.15F, var7, var3.a, var3.b);
      this.b.k = this.b.k == 1?0:1;
   }

   public strictfp void a(float var1) {
      if(this.b.cu < this.b.cv) {
         this.b.cu += 0.1F * var1;
         if(this.b.cu > this.b.cv) {
            this.b.cu = this.b.cv;
         }
      }

      this.b.s(var1);
   }

   public strictfp int b() {
      return 3;
   }

   public strictfp void a(c var1) {
      if(!(var1 instanceof f)) {
         this.b.cv += 400.0F;
         this.b.cu += 400.0F;
      }

      this.b.cv += 2800.0F;
      this.b.cu += 2800.0F;
   }
}
