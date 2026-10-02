package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;

public abstract class VariableScope$VariableData extends LogicBoolean {

   public abstract LogicBoolean$ReturnType getReturnType();

   public boolean read(y var1) {
      return false;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "Data(" + this.valueToStringDebug((y)null) + ")";
   }

   public float readNumber(y var1) {
      return 0.0F;
   }

   public String readString(y var1) {
      return "";
   }
}
