package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.utility.y;

public class l extends j {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e[] c = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   public static com.corrodinggames.rts.gameFramework.m.e e = null;
   int f;
   float g;
   Rect h = new Rect();


   public strictfp ar b() {
      return ar.E;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.mammoth_tank);
      c = com.corrodinggames.rts.game.n.a(var1);
      a = var0.bO.a(R$drawable.mammoth_tank_dead);
      b = var0.bO.a(R$drawable.mammoth_tank_turret);
      e = var0.bO.a(R$drawable.lighting_charge);
      d = a(var1, var1.m() / 2, var1.l());
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:c[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return d;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return b;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && this.eq > -2.0F && !this.bV;
   }

   public strictfp float G() {
      return 3.0F;
   }

   public strictfp float H() {
      return 3.0F;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = a;
      this.S(0);
      this.bT = false;
      this.a(ab.e);
      return true;
   }

   public strictfp l(boolean var1) {
      super(var1);
      this.a(c[7], 2);
      this.cj = 21.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 2900.0F;
      this.cu = this.cv;
      this.M = c[7];
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.cK) {
         this.g += var1;
         if(this.g > 3.0F) {
            this.g = 0.0F;
            this.f = 1 - this.f;
         }
      }

   }

   public strictfp float bN() {
      return 14000.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
      var4.ar = Color.a(255, 247, 212, 129);
      var4.U = 260.0F;
      var4.l = var1;
      var4.h = 20.0F;
      var4.t = 4.0F;
      var4.x = 2.0F;
      var4.aQ = true;
      var4.A = true;
      var4.M = true;
      var4.ai = 0.5F;
      var4.ak = 1.0F;
      var4.al = 0.0F;
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      var5.bR.a(var3.a, var3.b, this.eq, -1118482);
      var5.bM.a(com.corrodinggames.rts.gameFramework.a.e.x, 0.2F, this.eo, this.ep);
   }

   public strictfp float m() {
      return 210.0F;
   }

   public strictfp float b(int var1) {
      return 140.0F;
   }

   public strictfp float z() {
      return 0.5F;
   }

   public strictfp float bc() {
      return 1.0F;
   }

   public strictfp float A() {
      return 1.0F;
   }

   public strictfp float B() {
      return 0.5F;
   }

   public strictfp float w(int var1) {
      return 0.08F;
   }

   public strictfp float c(int var1) {
      return 2.5F;
   }

   public strictfp float C() {
      return 0.04F;
   }

   public strictfp float D() {
      return 0.08F;
   }

   public strictfp Rect a_(boolean var1) {
      return this.bV && !var1?super.a_(var1):super.a(var1, this.f);
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         y.a((com.corrodinggames.rts.game.units.y)this);
         float var2 = this.cL[0].f / this.e(0);
         y.a(this, e, var2, 0);
         return true;
      }
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return true;
   }

   public strictfp float g(int var1) {
      return 22.0F;
   }

   public strictfp float e(int var1) {
      return 60.0F;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
