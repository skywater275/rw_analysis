package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.gameFramework.l;

public class LogicBooleanGameFunctions$IsGameFrameBoolean extends LogicBoolean {

   @LogicBoolean$Parameter
   public int mod;
   @LogicBoolean$Parameter
   public int equalTo;
   @LogicBoolean$Parameter
   public boolean offset;


   @LogicBoolean$Parameter
   public void mod(int var1) {
      this.mod = var1;
   }

   public boolean read(y var1) {
      l var2 = l.B();
      boolean var3;
      if(this.mod >= 0) {
         if(this.offset) {
            var3 = ((long)var2.bx + var1.eh) % (long)this.mod == (long)this.equalTo;
         } else {
            var3 = var2.bx % this.mod == this.equalTo;
         }
      } else if(this.offset) {
         var3 = (long)var2.bx + var1.eh == (long)this.equalTo;
      } else {
         var3 = var2.bx == this.equalTo;
      }

      return var3;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "IsGameFrame(mod=" + this.mod + ")";
   }
}
