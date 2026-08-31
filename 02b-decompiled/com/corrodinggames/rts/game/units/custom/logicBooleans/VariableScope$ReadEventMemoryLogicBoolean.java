package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.k;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$ReadUnitMemoryLogicBoolean;

public class VariableScope$ReadEventMemoryLogicBoolean extends VariableScope$ReadUnitMemoryLogicBoolean {

   public LogicBoolean getUnitMemory(y var1) {
      k var2 = LogicBoolean.activeEvent;
      VariableScope var3 = null;
      if(var2 != null) {
         var3 = var2.e;
      }

      if(var3 == null) {
         return this.defaultValue;
      } else {
         LogicBoolean var4 = var3.getAsLogicBoolean(this._name);
         return var4 == null?this.defaultValue:var4;
      }
   }
}
