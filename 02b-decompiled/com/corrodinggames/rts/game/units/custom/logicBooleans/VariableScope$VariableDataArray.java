package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;

public abstract class VariableScope$VariableDataArray extends VariableScope$VariableData {

   int size;


   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.voidReturn;
   }

   public abstract LogicBoolean$ReturnType getElementReturnType();

   public abstract void setDataAtIndex(VariableScope$VariableData var1, int var2);

   public abstract VariableScope$VariableData readDataAtIndex(int var1);

   public int getArraySize(y var1) {
      return this.size;
   }

   public LogicBoolean readArrayElement(y var1, int var2) {
      return this.readDataAtIndex(var2);
   }

   public boolean readBooleanIndex(int var1) {
      return false;
   }

   public float readNumberIndex(int var1) {
      return 0.0F;
   }

   public am readUnitIndex(int var1) {
      return null;
   }

   public abstract void shrink();
}
