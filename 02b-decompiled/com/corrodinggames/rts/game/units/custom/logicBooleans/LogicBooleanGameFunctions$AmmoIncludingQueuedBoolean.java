package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;

public final class LogicBooleanGameFunctions$AmmoIncludingQueuedBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "AmmoIncludingQueued";
   }

   public float getValue(y var1) {
      int var2 = var1.cE;
      b var3 = var1.by();
      var2 += var3.f;
      return (float)var2;
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
