package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualStrings;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareEqualUnits;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualNumbers;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualStrings;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$CompareNotEqualUnits;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public abstract class CompareJoinerBoolean$CompareBooleanOnly extends CompareJoinerBoolean {

   public boolean requireBooleanChildren() {
      return false;
   }

   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      if(this.children.length < 2) {
         throw new BooleanParseException("Expected 2 or more elements while using " + this.type() + " have: " + this.children.length);
      } else {
         boolean var6 = false;
         LogicBoolean$ReturnType var7 = null;
         LogicBoolean[] var8 = this.children;
         int var9 = var8.length;

         int var10;
         LogicBoolean var11;
         for(var10 = 0; var10 < var9; ++var10) {
            var11 = var8[var10];
            if(!LogicBoolean.isStaticNull(var11)) {
               var7 = var11.getReturnType();
               break;
            }

            var6 = true;
         }

         if(var7 == null) {
            var7 = LogicBoolean$ReturnType.unit;
         }

         if(var6 && var7 != LogicBoolean$ReturnType.string && var7 != LogicBoolean$ReturnType.unit) {
            throw new BooleanParseException(var7 + " cannot be compared to null using " + this.type());
         } else {
            var8 = this.children;
            var9 = var8.length;

            for(var10 = 0; var10 < var9; ++var10) {
               var11 = var8[var10];
               LogicBoolean$ReturnType var12 = var11.getReturnType();
               if(var7 != var12 && !LogicBoolean.isStaticNull(var11)) {
                  throw new BooleanParseException("Mixing types: " + var7 + " and " + var12 + " while using " + this.type());
               }
            }

            if(var7 == LogicBoolean$ReturnType.number) {
               if(this instanceof CompareJoinerBoolean$CompareEqualBoolean) {
                  CompareJoinerBoolean$CompareEqualNumbers var20 = new CompareJoinerBoolean$CompareEqualNumbers();
                  var20.children = this.children;
                  return var20.validateAndOptimize(var1, var2, var3, var4, var5);
               } else if(this instanceof CompareJoinerBoolean$CompareNotEqualBoolean) {
                  CompareJoinerBoolean$CompareNotEqualNumbers var19 = new CompareJoinerBoolean$CompareNotEqualNumbers();
                  var19.children = this.children;
                  return var19.validateAndOptimize(var1, var2, var3, var4, var5);
               } else {
                  throw new BooleanParseException("Unhandled compare type: " + this.type() + " while using numbers");
               }
            } else if(var7 == LogicBoolean$ReturnType.bool) {
               LogicBoolean$StaticBoolean var18 = null;
               LogicBoolean[] var16 = this.children;
               var10 = var16.length;

               for(int var21 = 0; var21 < var10; ++var21) {
                  LogicBoolean var22 = var16[var21];
                  if(var22 instanceof LogicBoolean$StaticBoolean) {
                     var18 = (LogicBoolean$StaticBoolean)var22;
                  }
               }

               if(var18 != null && this instanceof CompareJoinerBoolean$CompareEqualBoolean) {
                  ;
               }

               return super.validateAndOptimize(var1, var2, var3, var4, var5);
            } else if(var7 == LogicBoolean$ReturnType.string) {
               if(this instanceof CompareJoinerBoolean$CompareEqualBoolean) {
                  CompareJoinerBoolean$CompareEqualStrings var17 = new CompareJoinerBoolean$CompareEqualStrings();
                  var17.children = this.children;
                  return var17.validateAndOptimize(var1, var2, var3, var4, var5);
               } else if(this instanceof CompareJoinerBoolean$CompareNotEqualBoolean) {
                  CompareJoinerBoolean$CompareNotEqualStrings var15 = new CompareJoinerBoolean$CompareNotEqualStrings();
                  var15.children = this.children;
                  return var15.validateAndOptimize(var1, var2, var3, var4, var5);
               } else {
                  throw new BooleanParseException("Unhandled compare type: " + this.type() + " while using numbers");
               }
            } else if(var7 == LogicBoolean$ReturnType.unit) {
               if(this instanceof CompareJoinerBoolean$CompareEqualBoolean) {
                  CompareJoinerBoolean$CompareEqualUnits var14 = new CompareJoinerBoolean$CompareEqualUnits();
                  var14.children = this.children;
                  return var14.validateAndOptimize(var1, var2, var3, var4, var5);
               } else if(this instanceof CompareJoinerBoolean$CompareNotEqualBoolean) {
                  CompareJoinerBoolean$CompareNotEqualUnits var13 = new CompareJoinerBoolean$CompareNotEqualUnits();
                  var13.children = this.children;
                  return var13.validateAndOptimize(var1, var2, var3, var4, var5);
               } else {
                  throw new BooleanParseException("Unhandled compare type: " + this.type() + " while using numbers");
               }
            } else {
               throw new BooleanParseException("Unhandled type: " + var7 + " while using " + this.type());
            }
         }
      }
   }
}
