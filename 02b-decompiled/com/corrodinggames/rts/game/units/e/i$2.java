package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.x;

final class i$2 extends x {

   i$2(int var1) {
      super(var1);
   }

   public String a() {
      return "-Stop unloading";
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.cancel", new Object[0]);
   }

   public boolean a(am var1, boolean var2) {
      return ((ak)var1).bA();
   }

   public boolean b(am var1) {
      return this.a(var1, false);
   }
}
