package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public abstract class LogicBoolean$LogicBooleanCommon extends LogicBoolean {

   public abstract String getName();

   public String getMatchFailReasonForPlayer(y var1) {
      String var2 = this.getName() + "=" + (this.read(var1)?"true":"false") + "";
      return var2;
   }
}
