package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public class LogicBooleanGameFunctions$MovingBoolean extends LogicBoolean {

   public boolean read(y var1) {
      boolean var2 = false;
      if(var1.cK) {
         var2 = true;
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "Moving";
   }
}
