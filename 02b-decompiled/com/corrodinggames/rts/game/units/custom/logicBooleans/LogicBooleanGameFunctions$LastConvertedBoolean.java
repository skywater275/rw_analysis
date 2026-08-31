package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$TimeBoolean;

public final class LogicBooleanGameFunctions$LastConvertedBoolean extends LogicBoolean$TimeBoolean {

   public String getName() {
      return "LastConverted";
   }

   public int getTime(y var1) {
      return var1.bB;
   }
}
