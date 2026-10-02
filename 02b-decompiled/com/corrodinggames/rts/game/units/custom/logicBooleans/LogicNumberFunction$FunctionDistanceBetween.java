package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.gameFramework.f;

public class LogicNumberFunction$FunctionDistanceBetween extends LogicNumberFunction {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.unit,
      positional = 0,
      required = true
   )
   public LogicBoolean unit1;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.unit,
      positional = 1,
      required = true
   )
   public LogicBoolean unit2;


   public String getName() {
      return "DistanceBetween";
   }

   public float readNumber(y var1) {
      am var2 = this.unit1.readUnit(var1);
      if(var2 == null) {
         return 0.0F;
      } else {
         float var3 = var2.eo;
         float var4 = var2.ep;
         var2 = this.unit2.readUnit(var1);
         if(var2 == null) {
            return 0.0F;
         } else {
            float var5 = var2.eo;
            float var6 = var2.ep;
            return f.b(var3, var4, var5, var6);
         }
      }
   }
}
