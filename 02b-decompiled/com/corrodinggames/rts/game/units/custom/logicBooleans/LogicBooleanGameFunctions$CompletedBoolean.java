package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;

public class LogicBooleanGameFunctions$CompletedBoolean extends LogicBoolean$LogicBooleanCommonLocking {

   public boolean read(y var1) {
      boolean var2 = false;
      if(var1.cm >= 1.0F) {
         var2 = true;
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "isComplete";
   }
}
