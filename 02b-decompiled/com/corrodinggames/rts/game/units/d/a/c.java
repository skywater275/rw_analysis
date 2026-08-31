package com.corrodinggames.rts.game.units.d.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.d.a.b;

abstract class c {

   // $FF: synthetic field
   final b a;


   strictfp c(b var1) {
      this.a = var1;
   }

   abstract float a();

   public abstract float a(int var1);

   public abstract float b(int var1);

   public strictfp PointF c(int var1) {
      return b.a(this.a, var1);
   }

   public abstract void a(am var1, int var2);

   public abstract com.corrodinggames.rts.gameFramework.m.e d(int var1);

   public abstract int b();

   public abstract String c();

   public abstract void a(c var1);

   public strictfp boolean a(String var1) {
      return this.c().equals(var1);
   }

   public strictfp void a(float var1) {}

   public strictfp float e(int var1) {
      return 5.0F;
   }

   public strictfp float f(int var1) {
      return 0.5F;
   }

   public strictfp float g(int var1) {
      return 23.0F;
   }

   public strictfp float h(int var1) {
      return -1.0F;
   }

   public abstract int d();
}
