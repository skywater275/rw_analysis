package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.aq;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;

public class al extends aq {

   al(LogicBoolean var1) {
      super(var1);
   }

   String a(com.corrodinggames.rts.game.units.y var1) {
      return LogicString.arrayToString(var1, this.a);
   }
}
