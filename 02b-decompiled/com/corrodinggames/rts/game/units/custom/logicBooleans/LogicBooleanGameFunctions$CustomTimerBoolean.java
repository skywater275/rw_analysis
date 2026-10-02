package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$TimeBoolean;

public final class LogicBooleanGameFunctions$CustomTimerBoolean extends LogicBoolean$TimeBoolean {

   public String getName() {
      return "CustomTimer";
   }

   public int getTime(y var1) {
      return var1.bA;
   }
}
