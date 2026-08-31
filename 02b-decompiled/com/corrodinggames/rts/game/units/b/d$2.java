package com.corrodinggames.rts.game.units.b;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.b.d;

final class d$2 extends x {

   d$2(int var1) {
      super(var1);
   }

   public String a() {
      return "-Stop unloading";
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.cancel", new Object[0]);
   }

   public boolean a(am var1, boolean var2) {
      return ((d)var1).g;
   }

   public boolean b(am var1) {
      return this.a(var1, false);
   }
}
