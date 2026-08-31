package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public abstract class LogicBooleanLoader$LogicBooleanContextWithDefault implements LogicBooleanLoader$LogicBooleanContext {

   public LogicBoolean parseNextElementInChain(String var1, l var2, String var3, boolean var4, String var5, String var6, LogicBoolean var7) {
      return defaultParseNextElementInChain(this, var1, var2, var3, var4, var5, var6, var7, LogicBoolean.booleans);
   }

   public static LogicBoolean defaultParseNextElementInChain(LogicBooleanLoader$LogicBooleanContext var0, String var1, l var2, String var3, boolean var4, String var5, String var6, LogicBoolean var7, HashMap var8) {
      UnitReference var9 = UnitReference.parseSingleUnitReferenceElement(var2, var3);
      if(var9 != null) {
         return var9;
      } else {
         int var12 = var3.indexOf("(");
         String var10;
         String var11;
         if(var12 == -1) {
            var10 = var3.toLowerCase(Locale.ROOT);
            var11 = "";
         } else {
            var10 = var3.substring(0, var12).trim().toLowerCase(Locale.ROOT);
            var11 = var3.substring(var12);
         }

         if(var1 != null) {
            var10 = var1 + var10;
         }

         LogicBoolean var13 = (LogicBoolean)var8.get(var10);
         String var14;
         if(var13 != null) {
            var14 = LogicBooleanLoader.fixArguments(var11);
            LogicBoolean var18 = var13.with(var2, var14, var10);
            var18 = var18.validateAndOptimize(var10, var14, var5, var0, var4);
            return var18;
         } else {
            var14 = "";
            if(var8 != null && var8.size() < 8 && var8.size() > 0) {
               var14 = " (Allowed functions: ";
               boolean var15 = true;

               String var17;
               for(Iterator var16 = var8.keySet().iterator(); var16.hasNext(); var14 = var14 + var17) {
                  var17 = (String)var16.next();
                  if(!var15) {
                     var14 = var14 + ", ";
                  }

                  var15 = false;
               }

               var14 = var14 + ")";
            }

            if(var6 != null) {
               throw new RuntimeException("Unknown function or field:\'" + var3 + "\' in \'" + var6 + "\'" + var14);
            } else {
               throw new RuntimeException("Unknown function or field:\'" + var3 + "\'" + var14);
            }
         }
      }
   }
}
