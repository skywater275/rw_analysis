package com.corrodinggames.rts.game.units.e;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;

public class o extends j {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   Rect e = new Rect();


   public strictfp ar b() {
      return ar.v;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      b = var0.bO.a(R$drawable.tank2);
      a = var0.bO.a(R$drawable.tank2_dead);
      c = var0.bO.a(R$drawable.tank2_turret);
      d = com.corrodinggames.rts.game.n.a(b);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:d[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return c;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = a;
      this.S(0);
      this.bT = false;
      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.o, 0.8F, this.eo, this.ep);
      this.bq();
      return true;
   }

   public strictfp o(boolean var1) {
      super(var1);
      this.T(16);
      this.U(30);
      this.cj = 11.0F;
      this.ck = this.cj + 2.0F;
      this.cv = 350.0F;
      this.cu = this.cv;
      this.M = b;
   }

   public strictfp void a(float var1) {
      super.a(var1);
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
      var4.U = 35.0F;
      var4.l = var1;
      var4.h = 60.0F;
      var4.t = 3.0F;
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      var5.bR.a(var3.a, var3.b, this.eq, -1127220);
      var5.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      var5.bM.a(com.corrodinggames.rts.gameFramework.a.e.q, 0.3F, var3.a, var3.b);
   }

   public strictfp float m() {
      return 150.0F;
   }

   public strictfp float b(int var1) {
      return 70.0F;
   }

   public strictfp float z() {
      return 1.0F;
   }

   public strictfp float A() {
      return 1.9F;
   }

   public strictfp float c(int var1) {
      return 3.0F;
   }

   public strictfp boolean c(float var1) {
      return super.c(var1);
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
      return false;
   }

   public strictfp float g(int var1) {
      return 10.0F;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
