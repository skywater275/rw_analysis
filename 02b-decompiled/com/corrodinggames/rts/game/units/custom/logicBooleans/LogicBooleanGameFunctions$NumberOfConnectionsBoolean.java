package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.c.a;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public final class LogicBooleanGameFunctions$NumberOfConnectionsBoolean extends LogicBoolean$AbstractNumberBoolean {

   l meta;
   a connectionMetadata;


   public void forMeta(l var1) {
      if(var1 == null) {
         throw new BooleanParseException("NumberOfConnectionsBoolean requires metadata");
      } else {
         this.meta = var1;
      }
   }

   @LogicBoolean$Parameter
   public void name(String var1) {
      this.connectionMetadata = this.meta.l(var1);
      if(this.connectionMetadata == null) {
         throw new BooleanParseException("Could not find connection type with name: " + var1);
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(var4 != null && var4 != LogicBooleanLoader.defaultContextReader) {
         throw new BooleanParseException("Function:" + var1 + " only supports use with \'self.\'");
      } else if(this.connectionMetadata == null) {
         throw new BooleanParseException("requires connection name");
      }
   }

   public String getName() {
      return "NumberOfConnections";
   }

   public float getValue(y var1) {
      return (float)var1.dI.a(this.connectionMetadata);
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
