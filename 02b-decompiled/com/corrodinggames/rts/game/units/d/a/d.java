package com.corrodinggames.rts.game.units.d.a;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.game.units.d.a.c;
import com.corrodinggames.rts.gameFramework.l;

class d extends c {

   // $FF: synthetic field
   final b b;


   strictfp d(b var1) {
      super(var1);
      this.b = var1;
   }

   public strictfp String c() {
      return b.w;
   }

   public strictfp int d() {
      return ar.f.c() + b.dN.c();
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return b.dG();
   }

   strictfp float a() {
      return 350.0F;
   }

   public strictfp float a(int var1) {
      return 220.0F;
   }

   public strictfp float b(int var1) {
      return 100.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.c(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this.b, var3.a, var3.b);
      PointF var5 = this.b.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.h = 150.0F;
      var4.t = 4.0F;
      var4.aQ = true;
      var4.ar = Color.a(255, 190, 190, 80);
      var4.R = 2;
      var4.P = 0;
      var4.x = 0.9F;
      PointF var6 = var1.a(var3.a, var3.b, var4.t, var4.h, this.a());
      var4.aC = true;
      var4.m = true;
      var4.n = var6.a;
      var4.o = var6.b;
      var4.Y = this.b(var2);
      var4.Z = 55.0F;
      var4.aa = true;
      l var7 = l.B();
      var7.bM.a(com.corrodinggames.rts.gameFramework.a.e.r, 0.3F, var3.a, var3.b);
      var7.bR.a(var3.a, var3.b, this.b.eq, this.b.cL[var2].a);
      var7.bR.a(var4, -1118482);
      com.corrodinggames.rts.gameFramework.d.e var8 = var7.bR.a(var3.a, var3.b, this.b.eq, -1118482);
      if(var8 != null) {
         var8.V = 15.0F;
         var8.W = var8.V;
      }

   }

   public strictfp int b() {
      return 2;
   }

   public strictfp void a(c var1) {
      this.b.cv += 300.0F;
      this.b.cu += 300.0F;
   }

   public strictfp float e(int var1) {
      return 2.5F;
   }

   public strictfp float f(int var1) {
      return 0.2F;
   }

   public strictfp float h(int var1) {
      return -2.0F;
   }
}
