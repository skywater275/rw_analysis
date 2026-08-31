package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareGreaterThanNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareGreaterThanOrEqualNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareLessThanNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareLessThanOrEqualNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathAddJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathDivideJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathModulusJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathMultiplyJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathSubtractJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AndBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$OrBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public abstract class LogicBoolean$JoinerBoolean extends LogicBoolean {

   LogicBoolean[] children;


   public abstract String type();

   public static LogicBoolean$JoinerBoolean getNewJoiner(String var0) {
      Object var1;
      if(var0.equals("or")) {
         var1 = new LogicBoolean$OrBoolean();
      } else if(var0.equals("and")) {
         var1 = new LogicBoolean$AndBoolean();
      } else if(var0.equals("==")) {
         var1 = new CompareJoinerBoolean$CompareEqualBoolean();
      } else if(var0.equals("!=")) {
         var1 = new CompareJoinerBoolean$CompareNotEqualBoolean();
      } else if(var0.equals(">")) {
         var1 = new CompareJoinerBoolean$CompareGreaterThanNumbers();
      } else if(var0.equals(">=")) {
         var1 = new CompareJoinerBoolean$CompareGreaterThanOrEqualNumbers();
      } else if(var0.equals("<")) {
         var1 = new CompareJoinerBoolean$CompareLessThanNumbers();
      } else if(var0.equals("<=")) {
         var1 = new CompareJoinerBoolean$CompareLessThanOrEqualNumbers();
      } else if(var0.equals("%")) {
         var1 = new CompareJoinerBoolean$MathModulusJoinerBoolean();
      } else if(var0.equals("+")) {
         var1 = new CompareJoinerBoolean$MathAddJoinerBoolean();
      } else if(var0.equals("-")) {
         var1 = new CompareJoinerBoolean$MathSubtractJoinerBoolean();
      } else if(var0.equals("*")) {
         var1 = new CompareJoinerBoolean$MathMultiplyJoinerBoolean();
      } else {
         if(!var0.equals("/")) {
            throw new BooleanParseException("Unknown joiner:\'" + var0 + "\'");
         }

         var1 = new CompareJoinerBoolean$MathDivideJoinerBoolean();
      }

      return (LogicBoolean$JoinerBoolean)var1;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      String var2 = "(";
      boolean var3 = true;
      LogicBoolean[] var4 = this.children;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         LogicBoolean var7 = var4[var6];
         if(var3) {
            var3 = false;
         } else {
            var2 = var2 + " " + this.type() + " ";
         }

         var2 = var2 + var7.getMatchFailReasonForPlayer(var1);
      }

      var2 = var2 + ")";
      return var2;
   }

   public String getDebugDetails(j var1) {
      String var2 = "(";
      boolean var3 = true;
      LogicBoolean[] var4 = this.children;
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         LogicBoolean var7 = var4[var6];
         if(var3) {
            var3 = false;
         } else {
            var2 = var2 + " " + this.type() + " ";
         }

         var2 = var2 + var7.getDebugDetails(var1);
      }

      var2 = var2 + ")";
      return var2;
   }

   public final void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {}

   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      return this;
   }

   public boolean requireBooleanChildren() {
      return true;
   }
}
