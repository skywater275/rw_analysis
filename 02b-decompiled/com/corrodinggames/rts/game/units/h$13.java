package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;

final class h$13 extends com.corrodinggames.rts.game.units.a.x {

   h$13(String var1) {
      super(var1);
   }

   public String a() {
      return "For debugging autoTriggers. When enabled will log a message when any auto triggers fire on any selected units";
   }

   public String b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return !var1.bn?"Trigger Debug: Off":"Trigger Debug: On";
   }

   public boolean b(am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      return var2.bl;
   }
}
