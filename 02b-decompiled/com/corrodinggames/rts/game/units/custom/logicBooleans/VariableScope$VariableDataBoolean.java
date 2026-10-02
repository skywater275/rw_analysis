package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;

public class VariableScope$VariableDataBoolean extends VariableScope$VariableData {

   boolean bool;


   public VariableScope$VariableDataBoolean(boolean var1) {
      this.bool = var1;
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.bool;
   }

   public boolean read(y var1) {
      return this.bool;
   }
}
