package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;

public class VariableScope$MemoryWriterFactory$MemoryWriterElement extends VariableScope$CachedWriter$WriterElement {

   public VariableScope$VariableName name;
   public LogicBoolean value;


   public void writeToUnit(y var1) {
      if(var1.bw == null) {
         var1.bw = new VariableScope();
      }

      var1.bw.setFromLogicBoolean(this.name, var1, this.value, (LogicBoolean)null);
   }

   public void writeToMemory(VariableScope var1, y var2) {
      var1.setFromLogicBoolean(this.name, var2, this.value, (LogicBoolean)null);
   }
}
