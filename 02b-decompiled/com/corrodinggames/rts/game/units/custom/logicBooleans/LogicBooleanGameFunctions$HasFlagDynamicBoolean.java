package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$HasFlagBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public class LogicBooleanGameFunctions$HasFlagDynamicBoolean extends LogicBoolean {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 0
   )
   public LogicBoolean id;


   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      this.validate(var1, var2, var3, var4, var5);
      if(this.id == null) {
         throw new BooleanParseException("Flag id must be set");
      } else {
         Float var6 = getStaticNumber(this.id);
         if(var6 != null) {
            LogicBooleanGameFunctions$HasFlagBoolean var7 = new LogicBooleanGameFunctions$HasFlagBoolean();
            var7.id((int)var6.floatValue());
            return var7;
         } else {
            return this;
         }
      }
   }

   public boolean read(y var1) {
      y var2 = getParameterContext(var1);
      int var3 = (int)this.id.readNumber(var2);
      if(var3 >= 0 && var3 <= 31) {
         int var4 = 1 << var3;
         if(LogicBooleanGameFunctions$HasFlagBoolean.isFlagSet(var1.cF, var4)) {
            return true;
         }
      }

      return false;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      String var2 = "HasFlag";
      y var3 = getParameterContext(var1);
      var2 = var2 + "(id:" + this.id.getMatchFailReasonForPlayer(var3) + ")";
      return var2;
   }
}
