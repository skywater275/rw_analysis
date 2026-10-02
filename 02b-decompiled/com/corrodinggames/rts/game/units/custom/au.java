package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.av;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$Operator;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterFactory;

public class au extends VariableScope$CachedWriter$WriterFactory {

   l a;


   public au(l var1) {
      this.a = var1;
   }

   public VariableScope$CachedWriter$WriterElement createWriterElement(String var1, String var2, String var3, String var4) {
      if(!var2.equals("=") && !var2.equals("+=") && !var2.equals("-=")) {
         throw new bo("Only \'=\',\'+=\',\'-=\'  is supported here, got:" + var2);
      } else if(var3 == null) {
         throw new bo("Expected a value for: " + var1 + " (likely missing \'=\')");
      } else {
         VariableScope$CachedWriter$Operator var5 = VariableScope$CachedWriter$Operator.set;
         if(var2.equals("+=")) {
            var5 = VariableScope$CachedWriter$Operator.add;
         }

         if(var2.equals("-=")) {
            var5 = VariableScope$CachedWriter$Operator.subtract;
         }

         LogicBoolean var6;
         try {
            var6 = LogicBooleanLoader.parseBooleanBlock(this.a, var3, false);
         } catch (RuntimeException var9) {
            throw new RuntimeException("LogicBoolean - Error: " + var9.getMessage() + ", [parsing: \'" + var3 + "\']", var9);
         }

         if(var4 != null) {
            throw new RuntimeException("Unexpected array [] index operator on " + var1);
         } else {
            at var7 = (at)as.s.get(var1);
            if(var7 == null) {
               var7 = (at)as.s.get("core." + var1);
            }

            if(var7 == null) {
               throw new bo("Unknown key: " + var1 + "");
            } else {
               av var8 = new av();
               var8.a = var7;
               var8.b = var6;
               var8.c = var5;
               if(var6.getReturnType() != var7.a()) {
                  throw new bo("Field: " + var1 + " expects " + var7.a() + " type getting: " + var6.getReturnType() + " from: " + var3);
               } else {
                  return var8;
               }
            }
         }
      }
   }
}
