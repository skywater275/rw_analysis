package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.gameFramework.f;

public class LogicBooleanGameFunctions$SpeedValueBoolean extends LogicBoolean$AbstractNumberBoolean {

   public String getName() {
      return "Speed";
   }

   public float getValue(y var1) {
      float var2;
      if(var1.bi()) {
         var2 = f.b(0.0F, 0.0F, var1.cc, var1.cd);
         return var2;
      } else {
         var2 = var1.cf;
         return var2 < 0.0F?-var2:var2;
      }
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
