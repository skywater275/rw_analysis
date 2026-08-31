package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.x;

final class i$1 extends x {

   i$1(int var1) {
      super(var1);
   }

   public String a() {
      return "-Will unload all units when stopped";
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.unload", new Object[0]);
   }

   public int b(am var1, boolean var2) {
      return ((ak)var1).bB();
   }

   public boolean a(am var1, boolean var2) {
      return ((ak)var1).bA()?false:((ak)var1).f() && ((ak)var1).bB() > 0;
   }

   public boolean b(am var1) {
      return ((ak)var1).j();
   }
}
