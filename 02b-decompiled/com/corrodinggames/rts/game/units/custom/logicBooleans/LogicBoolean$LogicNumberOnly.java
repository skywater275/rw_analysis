package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.gameFramework.f;

public abstract class LogicBoolean$LogicNumberOnly extends LogicBoolean {

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.number;
   }

   public boolean read(y var1) {
      return false;
   }

   public abstract String getName();

   public abstract float readNumber(y var1);

   public String getMatchFailReasonForPlayer(y var1) {
      String var2 = this.getName() + "(" + getAllParametersDebug(this, var1) + ")=" + f.a(this.readNumber(var1), 3) + "";
      return var2;
   }
}
