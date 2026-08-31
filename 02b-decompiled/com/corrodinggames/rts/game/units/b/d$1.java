package com.corrodinggames.rts.game.units.b;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.b.d;

final class d$1 extends x {

   d$1(int var1) {
      super(var1);
   }

   public String a() {
      return "-Will unload all units when stopped";
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.unload", new Object[0]);
   }

   public int b(am var1, boolean var2) {
      return ((d)var1).o.size();
   }

   public boolean a(am var1, boolean var2) {
      return ((d)var1).g?false:!((y)var1).cK() && ((d)var1).o.size() > 0;
   }
}
