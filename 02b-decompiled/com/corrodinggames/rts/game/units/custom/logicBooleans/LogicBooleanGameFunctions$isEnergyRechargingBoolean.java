package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommon;

public final class LogicBooleanGameFunctions$isEnergyRechargingBoolean extends LogicBoolean$LogicBooleanCommon {

   public String getName() {
      return "isEnergyRecharging";
   }

   public boolean read(y var1) {
      return var1.bX();
   }
}
