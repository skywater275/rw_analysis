package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.gameFramework.l;

public class LogicBooleanGameFunctions$OverPassableTileBoolean extends LogicBoolean$LogicBooleanCommonLocking {

   ao movementType;


   public LogicBooleanGameFunctions$OverPassableTileBoolean() {
      this.movementType = ao.b;
   }

   @LogicBoolean$Parameter
   public void type(String var1) {
      this.movementType = ao.a(var1, "isOverPassableTile()");
   }

   public boolean read(y var1) {
      boolean var2 = false;
      l var3 = l.B();
      if(!com.corrodinggames.rts.gameFramework.utility.y.a(var1.eo, var1.ep, this.movementType)) {
         var2 = true;
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "OverLand";
   }
}
