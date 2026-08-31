package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.x;

public class u extends x {

   public int a = 14;
   public float b = 60.0F;


   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.c(0);
      var1.a(this.a);
      var1.a(this.b);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      var1.d();
      this.a = var1.f();
      this.b = var1.g();
      super.a(var1);
   }

   public strictfp ar b() {
      return ar.R;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
   }

   public strictfp u(boolean var1) {
      super(var1);
   }

   public strictfp void a(float var1) {
      super.a(var1);
      this.b -= var1;
      if(this.b < 0.0F) {
         this.ci();
      }

   }

   public strictfp int s() {
      return this.a;
   }

   public strictfp boolean t() {
      return true;
   }

   public strictfp boolean u() {
      return true;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }
}
