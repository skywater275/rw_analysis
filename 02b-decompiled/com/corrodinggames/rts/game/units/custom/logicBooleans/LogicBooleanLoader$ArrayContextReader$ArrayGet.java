package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayFunction;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

public class LogicBooleanLoader$ArrayContextReader$ArrayGet extends LogicBooleanLoader$ArrayContextReader$ArrayFunction {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number
   )
   public LogicBoolean index;
   LogicBoolean targetArray;
   public LogicBoolean$ReturnType elementType;


   public LogicBooleanLoader$LogicBooleanContext createContext() {
      return LogicBooleanLoader.voidArrayContextReader;
   }

   public void setArrayTarget(LogicBoolean var1) {
      this.targetArray = var1;
      LogicBoolean$ReturnType var2 = var1.getReturnType();
      this.elementType = LogicBoolean$ReturnType.getArrayBaseType(var2);
   }

   public void setArgumentsRaw(String var1, l var2, String var3) {
      if(var1 != null && !"".equals(var1)) {
         ArrayList var4 = al.a(var1, ",", false);
         this.validateNumberOfArguments(var4.size());
         this.index = LogicBooleanLoader.parseNumberBlock(var2, (String)var4.get(0));
         if(this.index == null) {
            throw new BooleanParseException("Expected non-null argument");
         }
      } else {
         this.validateNumberOfArguments(0);
      }
   }

   public void validateNumberOfArguments(int var1) {
      if(var1 != 1) {
         throw new BooleanParseException("Expected 1 argument");
      }
   }

   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this.index == null) {
         throw new BooleanParseException("No array index");
      }
   }

   public LogicBoolean$ReturnType getReturnType() {
      return this.elementType;
   }

   LogicBoolean readElement(y var1) {
      int var2 = (int)this.index.readNumber(var1);
      if(this.targetArray == null) {
         com.corrodinggames.rts.gameFramework.l.b("ArrayGet readElement targetArray==null");
         return null;
      } else {
         LogicBoolean var3 = this.targetArray.readArrayElement(var1, var2);
         return var3;
      }
   }

   public boolean read(y var1) {
      LogicBoolean var2 = this.readElement(var1);
      return var2 == null?false:var2.read(var1);
   }

   public float readNumber(y var1) {
      LogicBoolean var2 = this.readElement(var1);
      return var2 == null?0.0F:var2.readNumber(var1);
   }

   public am readUnit(y var1) {
      LogicBoolean var2 = this.readElement(var1);
      return var2 == null?null:var2.readUnit(var1);
   }

   public String getName() {
      return "get";
   }

   public String getMatchFailReasonForPlayer(y var1) {
      LogicBoolean var2 = this.readElement(var1);
      int var3 = (int)this.index.readNumber(var1);
      String var4 = "";
      if(this.targetArray != null) {
         var4 = var4 + this.targetArray.getMatchFailReasonForPlayer(var1);
      }

      var4 = var4 + "." + this.getName() + "(" + var3 + ")";
      if(var2 == null) {
         var4 = var4 + "=null";
      } else {
         var4 = var4 + "=" + var2.getMatchFailReasonForPlayer(var1);
      }

      return var4;
   }
}
