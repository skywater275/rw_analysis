package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.a;

class b extends com.corrodinggames.rts.game.units.a.w {

   public strictfp b() {
      super(a.h.a());
   }

   public strictfp boolean g() {
      return false;
   }

   public strictfp String a() {
      return "-Allows factory to build Tech 2 units";
   }

   public strictfp String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.upgradeT2", new Object[0]);
   }

   public strictfp int c() {
      return ar.c.c(2);
   }

   public strictfp float K() {
      return 4.0E-4F;
   }

   public strictfp boolean a(am var1, boolean var2) {
      a var3 = (a)var1;
      return var3.f == 1 && var3.a(this.N(), var2) <= 0?super.a(var1, var2):false;
   }

   public strictfp ar L() {
      return null;
   }

   public strictfp com.corrodinggames.rts.game.units.a.t f() {
      return com.corrodinggames.rts.game.units.a.t.c;
   }

   // $FF: synthetic method
   public as i() {
      return this.L();
   }
}
