package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanScopeOnly;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$KnownMemoryReadLogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import java.util.Locale;

public class VariableScope$KnownMemoryScopeLogicBoolean extends LogicBooleanLoader$LogicBooleanScopeOnly {

   public LogicBoolean parseNextElementInChain(String var1, l var2, String var3, boolean var4, String var5, String var6, LogicBoolean var7) {
      String var8 = var3.toLowerCase(Locale.ROOT);
      VariableScope$VariableDefinition var9 = var2.r.get(var8);
      if(var9 == null) {
         throw new RuntimeException("Unknown variable:\'" + var3 + "\' in \'" + var6 + "\'");
      } else {
         VariableScope$KnownMemoryReadLogicBoolean var10 = new VariableScope$KnownMemoryReadLogicBoolean(var9);
         return var10;
      }
   }
}
