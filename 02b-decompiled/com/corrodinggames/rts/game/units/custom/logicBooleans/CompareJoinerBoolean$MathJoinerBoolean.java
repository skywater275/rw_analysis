package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathAddJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$StringJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public abstract class CompareJoinerBoolean$MathJoinerBoolean extends CompareJoinerBoolean {

   public boolean read(y var1) {
      return false;
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.number;
   }

   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      boolean var6;
      LogicBoolean[] var7;
      int var8;
      int var9;
      LogicBoolean var10;
      if(this instanceof CompareJoinerBoolean$MathAddJoinerBoolean) {
         var6 = false;
         var7 = this.children;
         var8 = var7.length;

         for(var9 = 0; var9 < var8; ++var9) {
            var10 = var7[var9];
            if(var10.getReturnType() == LogicBoolean$ReturnType.string) {
               var6 = true;
            }
         }

         if(var6) {
            CompareJoinerBoolean$StringJoinerBoolean var12 = new CompareJoinerBoolean$StringJoinerBoolean();
            var12.children = this.children;
            return var12.validateAndOptimize(var1, var2, var3, var4, var5);
         }
      }

      var6 = true;
      var7 = this.children;
      var8 = var7.length;

      for(var9 = 0; var9 < var8; ++var9) {
         var10 = var7[var9];
         if(!(var10 instanceof LogicBoolean$StaticValueBoolean)) {
            var6 = false;
         }

         if(var10.getReturnType() != LogicBoolean$ReturnType.number) {
            throw new BooleanParseException("Unexpected type while using " + this.type() + " got: " + var10.getReturnType().name());
         }
      }

      if(var6) {
         float var11 = this.readNumber((y)null);
         return new LogicBoolean$StaticValueBoolean(var11);
      } else if(var5) {
         throw new BooleanParseException("Cannot return number here, expected boolean");
      } else {
         return super.validateAndOptimize(var1, var2, var3, var4, var5);
      }
   }
}
