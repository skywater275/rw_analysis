package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$TimeBoolean;

public class LogicBooleanGameFunctions$HasTakenDamage extends LogicBoolean$TimeBoolean {

   public int getTime(y var1) {
      return var1.bs;
   }

   public String getName() {
      return "HasTakenDamage";
   }
}
