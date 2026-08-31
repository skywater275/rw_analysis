package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

abstract class LogicNumberFunction$LogicNumberFunctionRawArgs extends LogicNumberFunction {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number
   )
   public LogicBoolean value;


   public void setArgumentsRaw(String var1, l var2, String var3) {
      if(var1 != null && !"".equals(var1)) {
         ArrayList var4 = al.a(var1, ",", false);
         this.validateNumberOfArguments(var4.size());
         this.value = LogicBooleanLoader.parseNumberBlock(var2, (String)var4.get(0));
         if(this.value == null) {
            throw new BooleanParseException("Expected non-null argument");
         }
      } else {
         this.validateNumberOfArguments(0);
      }
   }

   public void validateNumberOfArguments(int var1) {
      if(var1 != 1) {
         throw new BooleanParseException("Expected 1 argument");
      }
   }

   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      this.validate(var1, var2, var3, var4, var5);
      if(this.value instanceof LogicBoolean$StaticValueBoolean) {
         float var6 = ((LogicBoolean$StaticValueBoolean)this.value).getStaticValue();
         return new LogicBoolean$StaticValueBoolean(this.doFunction(var6));
      } else {
         return this;
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this.value == null) {
         throw new BooleanParseException("Expected parameters missing");
      } else if(var5) {
         throw new BooleanParseException("Expected function:" + var1 + " to return a boolean, but it returns a number");
      }
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return this.getName() + "(" + this.value.getMatchFailReasonForPlayer(var1) + ")";
   }

   public abstract float doFunction(float var1);
}
