package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public final class CompareJoinerBoolean$CompareEqualUnits extends CompareJoinerBoolean {

   public String type() {
      return "==";
   }

   public boolean read(y var1) {
      LogicBoolean[] var2 = this.children;
      am var3 = var2[0].readUnit(var1);

      for(int var4 = 1; var4 < var2.length; ++var4) {
         am var5 = var2[var4].readUnit(var1);
         if(var3 != var5) {
            return false;
         }

         var3 = var5;
      }

      return true;
   }
}
