package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$AbstractMemoryLogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import java.util.Locale;

public class VariableScope$ReadUnitMemoryLogicBoolean extends VariableScope$AbstractMemoryLogicBoolean {

   VariableScope$VariableName _name;
   LogicBoolean$ReturnType _type;
   @LogicBoolean$Parameter(
      key = "default"
   )
   public LogicBoolean defaultValue;
   @LogicBoolean$Parameter(
      key = "index"
   )
   public LogicBoolean index;


   @LogicBoolean$Parameter(
      required = true,
      positional = 0
   )
   public void name(String var1) {
      var1 = var1.toLowerCase(Locale.ROOT).trim();
      this._name = VariableScope$VariableName.get(var1);
   }

   @LogicBoolean$Parameter(
      required = true
   )
   public void type(String var1) {
      this._type = VariableScope.getUserType(var1);
      if(this._type == null) {
         throw new RuntimeException("Unknown type: " + var1);
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this.defaultValue != null && this.defaultValue.getReturnType() != this._type) {
         throw new BooleanParseException("defaultValue type:" + this.defaultValue.getReturnType() + " does not match requested type: " + this._type);
      } else {
         if(this.defaultValue == null) {
            this.defaultValue = VariableScope.variableDataNull;
         }

      }
   }

   public LogicBoolean getUnitMemory(y var1) {
      if(var1.bw == null) {
         return this.defaultValue;
      } else {
         LogicBoolean var2 = var1.bw.getAsLogicBoolean(this._name);
         if(var2 == null) {
            return this.defaultValue;
         } else {
            if(this.index != null) {
               int var3 = (int)this.index.readNumber(var1);
               LogicBoolean var4 = var2.readArrayElement(var1, var3);
               if(var4 == null) {
                  return this.defaultValue;
               }

               var2 = var4;
            }

            return var2;
         }
      }
   }

   public boolean read(y var1) {
      return this.getUnitMemory(var1).read(var1);
   }

   public float readNumber(y var1) {
      return this.getUnitMemory(var1).readNumber(var1);
   }

   public String readString(y var1) {
      LogicBoolean var2 = this.getUnitMemory(var1);
      return LogicString$StringCast.castToString(var2.getReturnType(), var2, var1);
   }

   public am readUnit(y var1) {
      return this.getUnitMemory(var1).readUnit(var1);
   }

   public int getArraySize(y var1) {
      return this.getUnitMemory(var1).getArraySize(var1);
   }

   public LogicBoolean readArrayElement(y var1, int var2) {
      return this.getUnitMemory(var1).readArrayElement(var1, var2);
   }

   public LogicBoolean$ReturnType getReturnType() {
      return this._type;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      if(this._type != null && this._name != null) {
         LogicBoolean var2 = this.getUnitMemory(var1);
         if(var2 != null) {
            String var3 = "";
            if(this._type != var2.getReturnType() && var2.getReturnType() != LogicBoolean$ReturnType.voidReturn) {
               var3 = "(TYPE MISMATCH GOT: " + var2.getReturnType().name() + ")";
            }

            return "memory(" + VariableScope$VariableName.access$000(this._name) + " as " + this._type.name() + "=" + var2.getMatchFailReasonForPlayer(var1) + var3 + ")";
         } else {
            return "memory(" + VariableScope$VariableName.access$000(this._name) + " as " + this._type.name() + ")";
         }
      } else {
         return "<memory with type/name == null>";
      }
   }
}
