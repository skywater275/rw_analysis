package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.gameFramework.f;

public class LogicBooleanGameFunctions$SpeedBoolean extends LogicBoolean {

   @LogicBoolean$Parameter
   public boolean atTopSpeed;


   public boolean read(y var1) {
      boolean var2 = false;
      float var3 = var1.z() - 0.1F;
      if(var1.bi()) {
         float var4 = f.a(0.0F, 0.0F, var1.cc, var1.cd);
         if(var4 != 0.0F && var4 > var3 * var3) {
            var2 = true;
         }
      } else if(var1.cf != 0.0F && var1.cf > var3) {
         var2 = true;
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "Speed";
   }
}
