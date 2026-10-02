package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;

public final class LogicBooleanGameFunctions$RotationBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "dir";
   }

   public float getValue(y var1) {
      return var1.cg;
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
