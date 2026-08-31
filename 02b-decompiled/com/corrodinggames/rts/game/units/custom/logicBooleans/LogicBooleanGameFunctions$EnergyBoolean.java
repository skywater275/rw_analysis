package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;

public final class LogicBooleanGameFunctions$EnergyBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "Energy";
   }

   public float getValue(y var1) {
      return var1.cB;
   }

   public float getMaxValue(y var1) {
      return var1.bd();
   }
}
