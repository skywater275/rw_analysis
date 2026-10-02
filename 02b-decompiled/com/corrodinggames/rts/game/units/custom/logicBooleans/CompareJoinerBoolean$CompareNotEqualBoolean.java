package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareBooleanOnly;

public final class CompareJoinerBoolean$CompareNotEqualBoolean extends CompareJoinerBoolean$CompareBooleanOnly {

   public String type() {
      return "!=";
   }

   public boolean read(y var1) {
      boolean var2 = this.children[0].read(var1);

      for(int var3 = 1; var3 < this.children.length; ++var3) {
         boolean var4 = this.children[var3].read(var1);
         if(var4 == var2) {
            return false;
         }

         var2 = var4;
      }

      return true;
   }
}
