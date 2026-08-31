package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$JoinerBoolean;

public final class LogicBoolean$OrBoolean extends LogicBoolean$JoinerBoolean {

   public String type() {
      return "or";
   }

   public boolean read(y var1) {
      LogicBoolean[] var2 = this.children;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         LogicBoolean var5 = var2[var4];
         if(var5.read(var1)) {
            return true;
         }
      }

      return false;
   }
}
