package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;

public final class LogicBooleanGameFunctions$HpBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "Hp";
   }

   public float getValue(y var1) {
      return var1.cu;
   }

   public float getMaxValue(y var1) {
      return var1.cv;
   }
}
