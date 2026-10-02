package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;

public class l extends w {

   as a;


   public strictfp l(as var1) {
      this(var1, -999.0F);
   }

   public strictfp l(as var1, float var2) {
      super("u_" + var1.v());
      as var3 = com.corrodinggames.rts.game.units.custom.l.c(var1);
      if(var3 != null) {
         var1 = var3;
         this.a("u_" + var3.v());
      }

      this.g = var2;
      this.a = var1;
   }

   public strictfp String a() {
      String var1 = this.a.f();
      boolean var2 = false;
      boolean var3 = true;
      var1 = var1 + "\n\n" + com.corrodinggames.rts.gameFramework.f.a.a(am.c(this.a), false, var2, var3);
      return var1;
   }

   public strictfp String b() {
      return this.a.e();
   }

   public strictfp int c() {
      return this.B().a();
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b B() {
      com.corrodinggames.rts.game.units.custom.d.b var1 = this.h.a();
      return var1 != null?var1:this.a.u();
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b r_() {
      com.corrodinggames.rts.game.units.custom.d.b var1 = this.h.b();
      return var1 != null?var1:this.a.B();
   }

   public strictfp as i() {
      return this.a;
   }

   public strictfp float K() {
      return this.a.D();
   }

   public strictfp t f() {
      return t.d;
   }

   public strictfp boolean n_() {
      return !this.a.C();
   }

   public strictfp boolean g(am var1) {
      return this.i().w()?true:super.g(var1);
   }

   public strictfp boolean g() {
      return true;
   }
}
