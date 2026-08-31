package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.utility.y;

public class m extends j {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   Rect e = new Rect();


   public strictfp ar b() {
      return ar.q;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      b = var0.bO.a(R$drawable.mega_tank);
      a = var0.bO.a(R$drawable.mega_tank_dead);
      c = var0.bO.a(R$drawable.mega_tank_turret);
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

   public strictfp m(boolean var1) {
      super(var1);
      this.T(20);
      this.U(25);
      this.cj = 12.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 550.0F;
      this.cu = this.cv;
      this.M = b;
   }

   public strictfp void a(float var1) {
      super.a(var1);
   }

   public strictfp float bN() {
      return 7000.0F;
   }

   public strictfp void a(am var1, int var2) {
      if(!var1.i()) {
         PointF var3 = this.E(var2);
         com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
         var4.ar = Color.a(255, 150, 230, 40);
         var4.U = 50.0F;
         var4.l = var1;
         var4.h = 60.0F;
         var4.t = 3.0F;
         var4.x = 2.0F;
         var4.aQ = true;
         com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
         var5.bR.a(var3.a, var3.b, this.eq, -1127220);
         var5.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
         var5.bM.a(com.corrodinggames.rts.gameFramework.a.e.u, 0.3F, this.eo, this.ep);
      } else {
         com.corrodinggames.rts.game.f var6 = com.corrodinggames.rts.game.f.a(this, this.eo, this.ep);
         var6.ar = Color.a(255, 230, 230, 50);
         var6.U = 40.0F;
         var6.l = var1;
         var6.h = 190.0F;
         var6.t = 4.0F;
         var6.aH = true;
         var6.aI = 10.0F;
         var6.aJ = 15.0F;
         var6.aM = true;
         var6.aQ = true;
         com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
         var7.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.2F, this.eo, this.ep);
      }

   }

   public strictfp float m() {
      return 140.0F;
   }

   public strictfp float b(int var1) {
      return 70.0F;
   }

   public strictfp float z() {
      return 0.8F;
   }

   public strictfp float A() {
      return 1.2F;
   }

   public strictfp float c(int var1) {
      return 2.0F;
   }

   public strictfp float C() {
      return 0.05F;
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
      return true;
   }

   public strictfp float g(int var1) {
      return 12.0F;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
