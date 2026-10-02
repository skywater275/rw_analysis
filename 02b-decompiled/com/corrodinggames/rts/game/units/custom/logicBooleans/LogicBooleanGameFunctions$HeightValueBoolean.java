package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;

public final class LogicBooleanGameFunctions$HeightValueBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "Height";
   }

   public float getValue(y var1) {
      return var1.eq;
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
