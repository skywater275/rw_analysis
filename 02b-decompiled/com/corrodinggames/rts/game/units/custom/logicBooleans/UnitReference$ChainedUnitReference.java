package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$ChainedUnitReference extends UnitReference {

   UnitReference[] chain;


   UnitReference$ChainedUnitReference(UnitReference[] var1) {
      this.chain = var1;
   }

   public am getSingleRaw(y var1) {
      UnitReference[] var2 = this.chain;
      Object var3 = var1;
      LogicBoolean.outerUnitParameterContext = var1;

      for(int var4 = 0; var4 < var2.length; ++var4) {
         var3 = var2[var4].get((am)var3);
         if(var3 == null) {
            return null;
         }
      }

      LogicBoolean.outerUnitParameterContext = null;
      return (am)var3;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      UnitReference[] var2 = this.chain;
      Object var3 = var1;
      String var4 = "";
      if(var1 instanceof y) {
         LogicBoolean.outerUnitParameterContext = (y)var1;
      }

      var4 = var4 + "[";

      for(int var5 = 0; var5 < var2.length; ++var5) {
         var4 = var4 + var2[var5].getMatchFailReasonForPlayer(var1);
         if(var5 != var2.length - 1) {
            var4 = var4 + ",";
         }

         var3 = var2[var5].get((am)var3);
         if(var3 == null) {
            var4 = var4 + "<null>";
            break;
         }
      }

      LogicBoolean.outerUnitParameterContext = null;
      var4 = var4 + "]";
      return var4;
   }
}
