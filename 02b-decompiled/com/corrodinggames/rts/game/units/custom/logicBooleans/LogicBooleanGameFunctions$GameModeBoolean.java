package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.gameFramework.l;

public class LogicBooleanGameFunctions$GameModeBoolean extends LogicBoolean$LogicBooleanCommonLocking {

   @LogicBoolean$Parameter
   public boolean nukesEnabled;


   public boolean read(y var1) {
      boolean var2 = true;
      l var3 = l.B();
      if(this.nukesEnabled && var3.O() && var3.bX.ay.i) {
         var2 = false;
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "GameMode(" + (this.nukesEnabled?"Nukes enabled":"Nukes disabled") + ")";
   }
}
