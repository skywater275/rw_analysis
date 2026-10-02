package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$ResourceCountBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanScopeOnly;

public class LogicBooleanGameFunctions$ResourceScope extends LogicBooleanLoader$LogicBooleanScopeOnly {

   public LogicBoolean parseNextElementInChain(String var1, l var2, String var3, boolean var4, String var5, String var6, LogicBoolean var7) {
      a var9 = var2.j(var3);
      if(var9 == null) {
         throw new BooleanParseException("\'" + var5 + "\': Could not find resource: \'" + var3 + "\'");
      } else {
         LogicBooleanGameFunctions$ResourceCountBoolean var10 = new LogicBooleanGameFunctions$ResourceCountBoolean();
         var10.meta = var2;
         var10.type = var9;
         return var10;
      }
   }
}
