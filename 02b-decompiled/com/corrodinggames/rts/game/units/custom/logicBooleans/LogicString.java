package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public abstract class LogicString extends LogicBoolean {

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.string;
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "TEXT";
   }

   public boolean read(y var1) {
      return false;
   }

   public float readNumber(y var1) {
      return 0.0F;
   }

   public static String arrayToString(y var0, LogicBoolean var1) {
      int var2 = var1.getArraySize(var0);
      String var3 = "[";

      for(int var4 = 0; var4 < var2; ++var4) {
         if(var4 > 12) {
            var3 = var3 + ",...";
            break;
         }

         if(var4 != 0) {
            var3 = var3 + ",";
         }

         LogicBoolean var5 = var1.readArrayElement(var0, var4);
         if(var5 != null) {
            var3 = var3 + var5.valueToStringDebug(var0);
         }
      }

      var3 = var3 + "]";
      return var3;
   }

   public static String arraySummaryToString(y var0, LogicBoolean var1) {
      int var2 = var1.getArraySize(var0);
      LogicBoolean$ReturnType var3 = var1.getReturnType();
      LogicBoolean$ReturnType var4 = LogicBoolean$ReturnType.getArrayBaseType(var3);
      String var5 = LogicBoolean$ReturnType.toUserString(var4);
      String var6 = var5 + "[" + var2 + "]";
      return var6;
   }
}
