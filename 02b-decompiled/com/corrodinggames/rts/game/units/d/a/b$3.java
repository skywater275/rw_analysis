package com.corrodinggames.rts.game.units.d.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.d.a.b;

final class b$3 extends w {

   b$3(int var1) {
      super(var1);
   }

   public boolean g() {
      return false;
   }

   public String a() {
      return "-Large increase in range";
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.upgradeToArtillery", new Object[0]);
   }

   public int c() {
      return 1600;
   }

   public float K() {
      return 4.0E-4F;
   }

   public boolean a(am var1, boolean var2) {
      b var3 = (b)var1;
      return var3.M() == 1 && var3.a(s.i, var2) <= 0?super.a(var1, var2):false;
   }

   public boolean b(am var1) {
      b var2 = (b)var1;
      return var2.M() == 1;
   }

   public ar L() {
      return null;
   }

   public t f() {
      return t.c;
   }

   public void f(am var1) {
      b var2 = (b)var1;
      var2.b(b.w);
      b.c(var2);
   }

   // $FF: synthetic method
   public as i() {
      return this.L();
   }
}
