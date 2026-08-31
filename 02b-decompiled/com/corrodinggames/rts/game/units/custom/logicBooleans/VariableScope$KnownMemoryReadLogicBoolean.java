package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$AbstractMemoryLogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;

public class VariableScope$KnownMemoryReadLogicBoolean extends VariableScope$AbstractMemoryLogicBoolean {

   VariableScope$VariableName name;
   LogicBoolean$ReturnType type;


   public VariableScope$KnownMemoryReadLogicBoolean(VariableScope$VariableDefinition var1) {
      this.name = var1.name;
      this.type = var1.type;
   }

   public boolean read(y var1) {
      return var1.bw == null?false:var1.bw.getBoolean(this.name);
   }

   public float readNumber(y var1) {
      return var1.bw == null?0.0F:(float)var1.bw.getNumber(this.name);
   }

   public String readString(y var1) {
      return var1.bw == null?"":var1.bw.getString(this.name);
   }

   public am readUnit(y var1) {
      return var1.bw == null?null:var1.bw.getUnit(this.name);
   }

   public LogicBoolean readAsLogicBoolean(y var1) {
      return var1.bw == null?null:var1.bw.getAsLogicBoolean(this.name);
   }

   public int getArraySize(y var1) {
      return var1.bw == null?0:var1.bw.getDataObjectRaw(this.name).getArraySize(var1);
   }

   public LogicBoolean readArrayElement(y var1, int var2) {
      return var1.bw == null?null:var1.bw.getDataObjectRaw(this.name).readArrayElement(var1, var2);
   }

   public LogicBoolean$ReturnType getReturnType() {
      return this.type;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      LogicBoolean var2 = this.readAsLogicBoolean(var1);
      if(var2 != null) {
         String var3 = "";
         if(this.type != var2.getReturnType() && var2.getReturnType() != LogicBoolean$ReturnType.voidReturn) {
            var3 = "(TYPE MISMATCH GOT: " + var2.getReturnType().name() + ")";
         }

         return "memory(" + VariableScope$VariableName.access$000(this.name) + "=" + var2.getMatchFailReasonForPlayer(var1) + var3 + ")";
      } else {
         return "memory(" + VariableScope$VariableName.access$000(this.name) + "=null)";
      }
   }
}
