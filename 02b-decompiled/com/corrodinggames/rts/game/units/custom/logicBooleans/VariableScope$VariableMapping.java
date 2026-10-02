package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.Iterator;
import java.util.Locale;

public class VariableScope$VariableMapping {

   l meta;
   m mapping = new m();


   public VariableScope$VariableDefinition create(String var1, LogicBoolean$ReturnType var2) {
      VariableScope$VariableName var3 = VariableScope$VariableName.get(var1);
      VariableScope$VariableDefinition var4 = this.get(var3);
      if(var4 != null) {
         throw new RuntimeException("A variable already exists with the name: " + var1);
      } else {
         VariableScope$VariableDefinition var5 = new VariableScope$VariableDefinition();
         var5.name = var3;
         var5.type = var2;
         this.mapping.add(var5);
         return var5;
      }
   }

   public VariableScope$VariableDefinition get(String var1) {
      var1 = var1.toLowerCase(Locale.ROOT).trim();
      VariableScope$VariableName var2 = VariableScope$VariableName.get(var1);
      Iterator var3 = this.mapping.iterator();

      VariableScope$VariableDefinition var4;
      do {
         if(!var3.hasNext()) {
            return null;
         }

         var4 = (VariableScope$VariableDefinition)var3.next();
      } while(var4.name != var2);

      return var4;
   }

   public VariableScope$VariableDefinition get(VariableScope$VariableName var1) {
      Iterator var2 = this.mapping.iterator();

      VariableScope$VariableDefinition var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (VariableScope$VariableDefinition)var2.next();
      } while(var3.name != var1);

      return var3;
   }

   public boolean hasArrays() {
      Iterator var1 = this.mapping.iterator();

      VariableScope$VariableDefinition var2;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         var2 = (VariableScope$VariableDefinition)var1.next();
      } while(!LogicBoolean$ReturnType.isArrayType(var2.type));

      return true;
   }

   public String getListOfPossibleNames() {
      String var1 = null;
      Iterator var2 = this.mapping.iterator();

      while(var2.hasNext()) {
         VariableScope$VariableDefinition var3 = (VariableScope$VariableDefinition)var2.next();
         if(var1 == null) {
            var1 = "" + var3.name;
         } else {
            var1 = var1 + ", " + var3.name;
         }
      }

      return var1;
   }

   public void addDefineKey(ab var1, l var2, String var3, String var4, String var5) {
      String var6 = var1.b(var4, var5, (String)null);
      if(var6 != null && !var6.equals("")) {
         throw new RuntimeException("[" + var4 + "]" + var3 + ": Unexpected format");
      } else {
         this.defineVariablesRaw(var3, var4, var5);
      }
   }

   public void addDefineValue(l var1, String var2, String var3, String var4) {
      this.defineVariablesRaw(var3, var2, var4);
   }

   public void defineVariables(l var1, String var2) {
      this.defineVariablesRaw("define", "", var2);
   }

   public void addSingleDefine(l var1, String var2, String var3, String var4, String var5) {
      String var6 = var3.trim().toLowerCase(Locale.ROOT);
      var2 = var2.toLowerCase(Locale.ROOT).trim();
      LogicBoolean$ReturnType var7 = VariableScope.getUserType(var6);
      if(var7 == null) {
         throw new RuntimeException("[" + var4 + "]" + var5 + ": Unknown type: " + var6);
      } else {
         this.checkNameReserved(var2, var4, var5);
         VariableScope$VariableDefinition var8 = this.get(var2);
         if(var8 != null) {
            if(var8.type != var7) {
               throw new RuntimeException("[" + var4 + "]" + var5 + ": A memory variable already exists with the name: " + var2 + " and is a different type: " + var8.type.name());
            }
         } else {
            this.create(var2, var7);
         }
      }
   }

   public void defineVariablesRaw(String var1, String var2, String var3) {
      String[] var4 = f.c(var3, ',');
      String[] var5 = var4;
      int var6 = var4.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         String var8 = var5[var7];
         var8 = var8.trim();
         if(!var8.equals("")) {
            int var9 = var8.indexOf(" ");
            if(var9 == -1) {
               throw new RuntimeException("[" + var2 + "]" + var1 + ": Expected \'type name\' in each section, got: " + var8);
            }

            String var10 = var8.substring(0, var9).toLowerCase(Locale.ROOT).trim();
            String var11 = var8.substring(var9, var8.length()).toLowerCase(Locale.ROOT).trim();
            this.addSingleDefine(this.meta, var11, var10, var2, var1);
         }
      }

   }

   public void checkNameReserved(String var1, String var2, String var3) {
      boolean var4 = false;
      if(var1.equals("")) {
         var4 = true;
      }

      if(var1.equals("game") || var1.equals("parent") || var1.equals("self") || var1.equals("this")) {
         var4 = true;
      }

      if(var1.equals("boolean") || var1.equals("bool") || var1.equals("unit") || var1.equals("void") || var1.equals("null") || var1.equals("number") || var1.equals("float")) {
         var4 = true;
      }

      if(var4) {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": Variable cannot be named: \'" + var1 + "\'");
      } else if(!var1.contains(".") && !var1.contains("=") && !var1.contains("(") && !var1.contains(")") && !var1.contains("\'") && !var1.contains("\"") && !var1.contains("?") && !var1.contains("|") && !var1.contains("\\") && !var1.contains("/") && !var1.contains("[") && !var1.contains("]") && !var1.contains(":") && !var1.contains(";")) {
         if(var1.contains(" ")) {
            throw new RuntimeException("[" + var2 + "]" + var3 + ": Variable name cannot have a space: \'" + var1 + "\'");
         }
      } else {
         throw new RuntimeException("[" + var2 + "]" + var3 + ": Variable name has reserved symbols: \'" + var1 + "\'");
      }
   }
}
