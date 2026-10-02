package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;

public class LogicBooleanGameFunctions$EventTagsBoolean extends LogicBoolean {

   public g includesTag;


   @LogicBoolean$Parameter
   public void includes(String var1) {
      this.includesTag = g.c(var1);
   }

   public String getMatchFailReasonForPlayer(y var1) {
      String var2 = "EventTag";
      if(this.includesTag != null) {
         var2 = var2 + " includes " + this.includesTag;
      }

      return var2;
   }

   public boolean read(y var1) {
      boolean var2 = true;
      if(this.includesTag != null) {
         h var3 = null;
         if(LogicBoolean.activeEvent != null) {
            var3 = LogicBoolean.activeEvent.d;
         }

         if(var3 == null || !g.a(this.includesTag, var3)) {
            var2 = false;
         }
      }

      return var2;
   }
}
