package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public class LogicBooleanGameFunctions$IsOnTeam extends LogicBoolean$LogicBooleanCommonLocking {

   int teamId = -99;


   @LogicBoolean$Parameter
   public void team(int var1) {
      if(var1 >= -1 && var1 <= n.c) {
         this.teamId = var1;
      } else {
         throw new BooleanParseException("Flag id must be between 0-" + n.c);
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this.teamId == -99) {
         throw new BooleanParseException("Expended teamId argument for function:" + var1);
      }
   }

   public String getMatchFailReasonForPlayer(y var1) {
      String var2 = "Team";
      var2 = var2 + "(id:" + this.teamId + ")";
      return var2;
   }

   public boolean read(y var1) {
      boolean var2 = true;
      if(var1.bX.k != this.teamId) {
         var2 = false;
      }

      return var2;
   }
}
