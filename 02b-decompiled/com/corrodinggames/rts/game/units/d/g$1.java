package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.g;

final class g$1 extends com.corrodinggames.rts.game.units.a.w {

   g$1(int var1) {
      super(var1);
   }

   public boolean g() {
      return false;
   }

   public String a() {
      return com.corrodinggames.rts.gameFramework.h.a.a("units.extractor.upgrade.description", new Object[0]);
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.upgradeT2", new Object[0]);
   }

   public int c() {
      return ar.a.c(2);
   }

   public float K() {
      return 6.0E-4F;
   }

   public boolean a(am var1, boolean var2) {
      g var3 = (g)var1;
      return var3.b == 1 && var3.a(this.N(), var2) <= 0?super.a(var1, var2):false;
   }

   public ar L() {
      return null;
   }

   public com.corrodinggames.rts.game.units.a.t f() {
      return com.corrodinggames.rts.game.units.a.t.c;
   }

   // $FF: synthetic method
   public as i() {
      return this.L();
   }
}
