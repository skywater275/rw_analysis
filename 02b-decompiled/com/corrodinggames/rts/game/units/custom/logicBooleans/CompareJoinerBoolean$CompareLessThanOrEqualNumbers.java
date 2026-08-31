package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public final class CompareJoinerBoolean$CompareLessThanOrEqualNumbers extends CompareJoinerBoolean$CompareNumbers {

   public String type() {
      return "<=";
   }

   public boolean read(y var1) {
      LogicBoolean[] var2 = this.children;
      float var3 = var2[0].readNumber(var1);

      for(int var4 = 1; var4 < var2.length; ++var4) {
         float var5 = var2[var4].readNumber(var1);
         if(var3 > var5) {
            return false;
         }

         var3 = var5;
      }

      return true;
   }
}
