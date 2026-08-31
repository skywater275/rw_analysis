package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.am;

public class LogicBooleanGameFunctions$HasActiveWaypoint extends LogicBoolean {

   av type;


   @LogicBoolean$Parameter
   public void type(String var1) {
      try {
         this.type = (av)ab.a(var1, (Enum)null, av.class);
      } catch (bo var3) {
         throw new am(var3.getMessage(), var3);
      }
   }

   public boolean read(y var1) {
      boolean var2 = false;
      au var3 = var1.ar();
      if(var3 != null) {
         if(this.type == null) {
            var2 = true;
         } else {
            var2 = var3.d() == this.type;
         }
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "HasActiveWaypoint(type=" + this.type + ")";
   }
}
