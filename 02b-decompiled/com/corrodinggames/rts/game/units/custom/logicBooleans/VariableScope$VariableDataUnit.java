package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;

public class VariableScope$VariableDataUnit extends VariableScope$VariableData {

   am unit;


   public VariableScope$VariableDataUnit(am var1) {
      this.unit = var1;
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.unit;
   }

   public am readUnit(y var1) {
      return this.unit;
   }
}
