package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBoolean;

public final class LogicBoolean$StaticBooleanFalse extends LogicBoolean$StaticBoolean {

   public String getMatchFailReasonForPlayer(y var1) {
      return "false";
   }

   public boolean read(y var1) {
      return false;
   }
}
