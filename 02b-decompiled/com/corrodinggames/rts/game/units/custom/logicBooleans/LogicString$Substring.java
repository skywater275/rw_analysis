package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;

public class LogicString$Substring extends LogicString {

   @LogicBoolean$Parameter(
      required = true,
      positional = 0
   )
   public LogicBoolean text;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      required = true,
      positional = 1
   )
   public LogicBoolean start;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      required = true,
      positional = 2
   )
   public LogicBoolean end;


   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      if(this.text == null) {
         throw new BooleanParseException("Expected argument text");
      } else {
         if(this.text.getReturnType() != LogicBoolean$ReturnType.string) {
            this.text = LogicString$StringCast.createStringCast(this.text);
         }

         return super.validateAndOptimize(var1, var2, var3, var4, var5);
      }
   }

   public String readString(y var1) {
      String var2 = this.text.readString(var1);
      int var3 = (int)this.start.readNumber(var1);
      int var4 = (int)this.end.readNumber(var1);
      if(var3 < 0) {
         var3 = 0;
      }

      if(var4 < 0) {
         var4 = 0;
      }

      if(var3 > var2.length()) {
         var3 = var2.length();
      }

      if(var4 > var2.length()) {
         var4 = var2.length();
      }

      if(var4 < var3) {
         var4 = var3;
      }

      var2 = var2.substring(var3, var4);
      return var2;
   }
}
