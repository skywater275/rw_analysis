package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class LogicBooleanGameFunctions$CompareUnitsBroken extends LogicBoolean {

   l meta;
   UnitReference sameUnitAs;


   public void forMeta(l var1) {
      if(var1 == null) {
         throw new BooleanParseException("SameUnitAs requires metadata");
      } else {
         this.meta = var1;
      }
   }

   @LogicBoolean$Parameter
   public void sameUnitAs(String var1) {
      try {
         this.sameUnitAs = UnitReference.parseUnitReference(this.meta, var1, "", "", (UnitReference)null, false);
      } catch (bo var3) {
         throw new BooleanParseException(var3.getMessage(), var3);
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this.sameUnitAs == null) {
         LogicBooleanLoader$ParameterMapping var6 = this.getParameters();
         throw new BooleanParseException("Missing required parameters (Possible parameters:" + var6.allParametersString + ")");
      }
   }

   public boolean read(y var1) {
      boolean var2 = true;
      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "SameUnitAs";
   }
}
