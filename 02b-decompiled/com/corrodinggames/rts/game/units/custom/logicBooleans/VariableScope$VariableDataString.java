package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;

public class VariableScope$VariableDataString extends VariableScope$VariableData {

   String text;


   public VariableScope$VariableDataString(String var1) {
      this.text = var1;
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.string;
   }

   public String readString(y var1) {
      return this.text;
   }
}
