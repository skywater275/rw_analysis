package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;

public final class LogicBooleanGameFunctions$AmmoBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "Ammo";
   }

   public float getValue(y var1) {
      return (float)var1.cE;
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
