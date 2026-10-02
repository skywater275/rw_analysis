package com.corrodinggames.rts.game.units.d.a;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.game.units.d.a.c;
import com.corrodinggames.rts.gameFramework.l;

class e extends c {

   // $FF: synthetic field
   final b b;


   strictfp e(b var1) {
      super(var1);
      this.b = var1;
   }

   public strictfp String c() {
      return b.x;
   }

   public strictfp int d() {
      return ar.f.c() + b.dO.c();
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return b.dH();
   }

   strictfp float a() {
      return 155.0F;
   }

   public strictfp float a(int var1) {
      return 5.0F;
   }

   public strictfp float b(int var1) {
      return 4.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.c(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this.b, var3.a, var3.b);
      var4.h = 60.0F;
      var4.t = 3.0F + (float)(this.b.k * 13) % 2.0F;
      var4.aR = false;
      var4.G = true;
      var4.ar = Color.a(105, 255, 255, 255);
      var4.P = 3;
      var4.x = 1.3F;
      PointF var5 = var1.a(var3.a, var3.b, var4.t, var4.h, this.a());
      var4.aC = true;
      var4.m = true;
      var4.n = var5.a;
      var4.o = var5.b;
      var4.n += (float)(-15 + this.b.k * 13 % 30);
      var4.o += (float)(-15 + (63 + this.b.k * 33) % 30);
      var4.em = 3;
      var4.Y = this.b(var2);
      var4.Z = 65.0F;
      var4.aa = true;
      var4.C = true;
      l var6 = l.B();
      ++this.b.k;
      if(this.b.k > 10) {
         this.b.k = 0;
         var6.bR.a(var3.a, var3.b, this.b.eq, this.b.cL[var2].a);
      }

   }

   public strictfp int b() {
      return 2;
   }

   public strictfp void a(c var1) {
      this.b.cv += 900.0F;
      this.b.cu += 900.0F;
   }

   public strictfp void a(float var1) {
      if(this.b.cu < this.b.cv) {
         this.b.cu += 0.15F * var1;
         if(this.b.cu > this.b.cv) {
            this.b.cu = this.b.cv;
         }
      }

   }

   public strictfp float e(int var1) {
      return 11.0F;
   }

   public strictfp float f(int var1) {
      return 2.0F;
   }

   public strictfp float g(int var1) {
      return 18.0F;
   }

   public strictfp float h(int var1) {
      return 0.0F;
   }
}
