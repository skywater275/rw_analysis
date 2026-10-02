package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;

public final class LogicBooleanGameFunctions$HasParent extends LogicBoolean {

   public g _withTag;


   @LogicBoolean$Parameter
   public void withTag(String var1) {
      this._withTag = g.c(var1);
   }

   public boolean read(y var1) {
      boolean var2 = false;
      am var3 = var1.dr();
      if(var3 != null) {
         var2 = true;
         if(this._withTag != null) {
            h var4 = var3.de();
            if(!g.a(this._withTag, var4)) {
               var2 = false;
            }
         }
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "HasParent";
   }
}
