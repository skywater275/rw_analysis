package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;

public final class LogicBooleanGameFunctions$TeamIdBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "teamId";
   }

   public float getValue(y var1) {
      return (float)var1.bX.k;
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
