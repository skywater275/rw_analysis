package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;

public class VariableScope$EmptyVariableScope extends VariableScope {

   public void setDataRaw(VariableScope$VariableName var1, VariableScope$VariableData var2) {
      throw new RuntimeException("Not allowed");
   }
}
