package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;

public class VariableScope$VariableDataNull extends VariableScope$VariableData {

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.voidReturn;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "null";
   }
}
