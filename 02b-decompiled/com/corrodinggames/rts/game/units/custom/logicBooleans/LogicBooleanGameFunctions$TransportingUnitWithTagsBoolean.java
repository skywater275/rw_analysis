package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.gameFramework.utility.m;

public class LogicBooleanGameFunctions$TransportingUnitWithTagsBoolean extends LogicBoolean {

   public g includesTag;


   @LogicBoolean$Parameter
   public void includes(String var1) {
      this.includesTag = g.c(var1);
   }

   public String getMatchFailReasonForPlayer(y var1) {
      String var2 = "TransportingUnitWithTags ";
      if(this.includesTag != null) {
         var2 = var2 + " includes " + this.includesTag;
      }

      return var2;
   }

   public boolean read(y var1) {
      boolean var2 = false;
      if(this.includesTag != null) {
         m var3 = var1.bz();
         if(var3 != null) {
            Object[] var4 = var3.a();

            for(int var5 = 0; var5 < var3.a; ++var5) {
               am var6 = (am)var4[var5];
               h var7 = var6.de();
               if(var7 != null && g.a(this.includesTag, var7)) {
                  var2 = true;
               }
            }
         }
      }

      return var2;
   }
}
