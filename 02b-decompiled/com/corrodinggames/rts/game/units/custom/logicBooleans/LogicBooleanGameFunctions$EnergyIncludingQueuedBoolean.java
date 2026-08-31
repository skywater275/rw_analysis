package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;

public final class LogicBooleanGameFunctions$EnergyIncludingQueuedBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "EnergyIncludingQueued";
   }

   public float getValue(y var1) {
      float var2 = var1.cB;
      b var3 = var1.by();
      var2 += var3.c;
      return var2;
   }

   public float getMaxValue(y var1) {
      return var1.bd();
   }
}
