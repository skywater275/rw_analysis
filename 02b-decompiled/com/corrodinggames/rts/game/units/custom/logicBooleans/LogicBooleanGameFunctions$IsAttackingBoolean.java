package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public class LogicBooleanGameFunctions$IsAttackingBoolean extends LogicBoolean {

   public boolean read(y var1) {
      boolean var2 = false;
      if(var1.aa()) {
         var2 = true;
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "Attacking";
   }
}
