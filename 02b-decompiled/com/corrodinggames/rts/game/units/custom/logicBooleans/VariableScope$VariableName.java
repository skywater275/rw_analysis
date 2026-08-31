package com.corrodinggames.rts.game.units.custom.logicBooleans;

import java.util.HashMap;

public class VariableScope$VariableName {

   private static HashMap existingVariableName = new HashMap();
   private String id;


   public static VariableScope$VariableName getExistingOrNull(String var0) {
      HashMap var1 = existingVariableName;
      synchronized(existingVariableName) {
         VariableScope$VariableName var2 = (VariableScope$VariableName)existingVariableName.get(var0);
         return var2 != null?var2:null;
      }
   }

   public static VariableScope$VariableName get(String var0) {
      HashMap var1 = existingVariableName;
      synchronized(existingVariableName) {
         VariableScope$VariableName var2 = (VariableScope$VariableName)existingVariableName.get(var0);
         if(var2 != null) {
            return var2;
         } else {
            VariableScope$VariableName var3 = new VariableScope$VariableName();
            var3.id = var0;
            existingVariableName.put(var0, var3);
            return var3;
         }
      }
   }

   public String getId() {
      return this.id;
   }

   public String toString() {
      return this.id;
   }

   // $FF: synthetic method
   static String access$000(VariableScope$VariableName var0) {
      return var0.id;
   }

}
