package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public final class LogicBooleanGameFunctions$HasResourcesBoolean extends LogicBoolean {

   b requiredResources;
   l meta;


   public void forMeta(l var1) {
      if(var1 == null) {
         throw new BooleanParseException("HasResourcesBoolean requires metadata");
      } else {
         this.meta = var1;
      }
   }

   public void setArgumentsRaw(String var1, l var2, String var3) {
      try {
         this.requiredResources = b.b(this.meta, var1);
      } catch (bo var5) {
         throw new BooleanParseException(var5.getMessage(), var5);
      }
   }

   public boolean read(y var1) {
      boolean var2 = false;
      if(this.requiredResources.b((am)var1)) {
         var2 = true;
      }

      return var2;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "HasResources(" + this.requiredResources.a(false, true, 8, true) + ")";
   }
}
