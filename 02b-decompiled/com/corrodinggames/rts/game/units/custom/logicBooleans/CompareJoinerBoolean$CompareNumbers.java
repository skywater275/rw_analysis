package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public abstract class CompareJoinerBoolean$CompareNumbers extends CompareJoinerBoolean {

   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      boolean var6 = true;
      LogicBoolean[] var7 = this.children;
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         LogicBoolean var10 = var7[var9];
         if(var10.getReturnType() != LogicBoolean$ReturnType.number) {
            throw new BooleanParseException("Non-number type while using " + this.type());
         }

         if(!(var10 instanceof LogicBoolean$StaticValueBoolean)) {
            var6 = false;
         }
      }

      if(var6) {
         ;
      }

      return super.validateAndOptimize(var1, var2, var3, var4, var5);
   }
}
