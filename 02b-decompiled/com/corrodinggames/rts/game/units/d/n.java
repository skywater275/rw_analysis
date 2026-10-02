package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.m;

class n extends com.corrodinggames.rts.game.units.a.w {

   public strictfp n() {
      super(m.h.a());
   }

   public strictfp boolean g() {
      return false;
   }

   public strictfp String a() {
      return com.corrodinggames.rts.gameFramework.h.a.a("units.landFactory.upgrade.description", new Object[0]);
   }

   public strictfp String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("units.landFactory.upgrade.name", new Object[0]);
   }

   public strictfp int c() {
      return ar.b.c(2);
   }

   public strictfp float K() {
      return 4.0E-4F;
   }

   public strictfp boolean a(am var1, boolean var2) {
      m var3 = (m)var1;
      return !var3.g && var3.a(this.N(), var2) <= 0?super.a(var1, var2):false;
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
