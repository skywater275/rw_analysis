package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.aq;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public class ap extends aq {

   ap(LogicBoolean var1) {
      super(var1);
   }

   String a(com.corrodinggames.rts.game.units.y var1) {
      com.corrodinggames.rts.game.units.am var2 = this.a.readUnit(var1);
      return com.corrodinggames.rts.game.units.am.f(var2, false);
   }
}
