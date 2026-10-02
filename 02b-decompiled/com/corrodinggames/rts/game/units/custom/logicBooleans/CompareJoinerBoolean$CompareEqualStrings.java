package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public final class CompareJoinerBoolean$CompareEqualStrings extends CompareJoinerBoolean {

   public String type() {
      return "==";
   }

   public boolean read(y var1) {
      LogicBoolean[] var2 = this.children;
      String var3 = var2[0].readString(var1);
      if(var3 == null) {
         var3 = "";
      }

      for(int var4 = 1; var4 < var2.length; ++var4) {
         String var5 = var2[var4].readString(var1);
         if(var5 == null) {
            var5 = "";
         }

         if(!var3.equals(var5)) {
            return false;
         }

         var3 = var5;
      }

      return true;
   }
}
