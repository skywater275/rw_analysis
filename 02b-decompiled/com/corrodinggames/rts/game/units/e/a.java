package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.utility.y;

public class a extends j {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];


   public strictfp ar b() {
      return ar.k;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.artillery2);
      b = var0.bO.a(R$drawable.artillery1_dead);
      d = com.corrodinggames.rts.game.n.a(a);
      c = a(a);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?b:d[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && !this.bV;
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
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = b;
      this.S(0);
      this.bT = false;
      this.a(ab.c);
      return true;
   }

   public strictfp a(boolean var1) {
      super(var1);
      this.T(28);
      this.U(50);
      this.cj = 18.0F;
      this.ck = this.cj;
      this.cv = 140.0F;
      this.cu = this.cv;
      this.M = a;
   }

   public strictfp void a(float var1) {
      super.a(var1);
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.h = 150.0F;
      var4.t = 4.0F;
      var4.aQ = true;
      var4.ar = Color.a(255, 190, 190, 80);
      var4.R = 2;
      var4.P = 1;
      var4.x = 0.9F;
      PointF var6 = var1.a(var3.a, var3.b, var4.t, var4.h, this.m());
      var4.aC = true;
      var4.m = true;
      var4.n = var6.a;
      var4.o = var6.b;
      var4.Y = 80.0F;
      var4.Z = 45.0F;
      var4.aa = true;
      com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
      var7.bM.a(com.corrodinggames.rts.gameFramework.a.e.r, 0.3F, var3.a, var3.b);
      var7.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      com.corrodinggames.rts.gameFramework.d.e var8 = var7.bR.a(var3.a, var3.b, this.eq, -1118482);
      if(var8 != null) {
         var8.V = 15.0F;
         var8.W = var8.V;
      }

   }

   public strictfp float bW() {
      return this.cL[0].e > 0.0F?1.0F - this.cL[0].e / this.b(0):super.bW();
   }

   public strictfp float m() {
      return 290.0F;
   }

   public strictfp float b(int var1) {
      return 240.0F;
   }

   public strictfp float z() {
      return 0.9F;
   }

   public strictfp float A() {
      return 1.7F;
   }

   public strictfp float B() {
      return 0.05F;
   }

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return false;
   }

   public strictfp boolean E() {
      return true;
   }

   public strictfp float g(int var1) {
      return 20.0F;
   }

   public strictfp float C() {
      return 0.05F;
   }

   public strictfp float D() {
      return 0.12F;
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      y.a(this, var2);
   }

   public strictfp float bN() {
      return 14000.0F;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
