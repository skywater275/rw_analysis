package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;

public class LogicBooleanGameFunctions$HasFlagBoolean extends LogicBoolean {

   public int flagMask = 0;
   public int flagId = -1;


   @LogicBoolean$Parameter(
      positional = 0
   )
   public void id(int var1) {
      if(var1 >= 0 && var1 <= 31) {
         this.flagId = var1;
         this.flagMask = 1 << var1;
      } else {
         throw new BooleanParseException("Flag id must be between 0-31");
      }
   }

   public static boolean isFlagSet(int var0, int var1) {
      return (var1 & var0) == var1;
   }

   public static byte setFlag(int var0, int var1) {
      return (byte)(var1 | var0);
   }

   public static byte unsetFlag(int var0, int var1) {
      return (byte)(var1 & ~var0);
   }

   public String getMatchFailReasonForPlayer(y var1) {
      String var2 = "HasFlag";
      var2 = var2 + "(id:" + this.flagId + ")";
      return var2;
   }

   public boolean read(y var1) {
      boolean var2 = true;
      if(this.flagMask != 0 && !isFlagSet(var1.cF, this.flagMask)) {
         var2 = false;
      }

      return var2;
   }
}
