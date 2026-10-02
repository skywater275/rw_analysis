package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$ResourceScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public final class LogicBooleanGameFunctions$ResourceCountBoolean extends LogicBoolean$AbstractNumberBoolean {

   l meta;
   a type;


   public void forMeta(l var1) {
      if(var1 == null) {
         throw new BooleanParseException("ResourceCountBoolean requires metadata");
      } else {
         this.meta = var1;
      }
   }

   @LogicBoolean$Parameter(
      positional = 0
   )
   public void type(String var1) {
      this.type = this.meta.j(var1);
      if(this.type == null) {
         throw new BooleanParseException("Could not find resource type: \'" + var1 + "\'");
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this.type == null) {
         ;
      }

   }

   public String getName() {
      return this.type + "";
   }

   public float getValue(y var1) {
      return this.type == null?0.0F:(float)this.type.a((am)var1);
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }

   public LogicBooleanLoader$LogicBooleanContext createContext() {
      return new LogicBooleanGameFunctions$ResourceScope();
   }

   public LogicBoolean$ReturnType getReturnType() {
      return this.type == null?LogicBoolean$ReturnType.voidReturn:(this.greaterThan == -1.0F && this.lessThan == -1.0F && !this.full && !this.empty?LogicBoolean$ReturnType.number:LogicBoolean$ReturnType.bool);
   }

   public void throwVoidReturnError(String var1) {
      throw new RuntimeException("\'" + var1 + "\' requires type");
   }

   public LogicBoolean setChild(LogicBoolean var1) {
      return var1;
   }
}
