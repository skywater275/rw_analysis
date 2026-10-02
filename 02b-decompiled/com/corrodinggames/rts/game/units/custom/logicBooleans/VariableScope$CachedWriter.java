package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterFactory;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.al;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class VariableScope$CachedWriter {

   m writers = new m();


   public void writeToUnit(y var1) {
      Iterator var2 = this.writers.iterator();

      while(var2.hasNext()) {
         VariableScope$CachedWriter$WriterElement var3 = (VariableScope$CachedWriter$WriterElement)var2.next();
         var3.writeToUnit(var1);
      }

   }

   public static VariableScope$CachedWriter create(String var0, VariableScope$CachedWriter$WriterFactory var1) {
      VariableScope$CachedWriter var2 = new VariableScope$CachedWriter();
      var2.addWriterElements(var0, var1);
      return var2;
   }

   public void addWriterElements(String var1, VariableScope$CachedWriter$WriterFactory var2) {
      ArrayList var3 = al.a(var1, ",", false, false);
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         String var5 = (String)var4.next();
         String[] var8 = al.c(var5, "=");
         String var6;
         String var7;
         if(var8 == null) {
            var6 = var5;
            var7 = null;
         } else {
            var6 = var8[0];
            var7 = var8[1];
         }

         String var9 = null;
         if(f.c(var6, "[")) {
            int var10 = var6.indexOf(91);
            int var11 = al.b(var6, "]", var10);
            if(var10 == -1 || var11 == -1) {
               throw new bo("Unexpected array[] format for: " + var6);
            }

            var9 = var6.substring(var10 + 1, var11);
            if(var9.trim().equals("")) {
               throw new bo("Array [] index in: " + var6 + " is empty");
            }

            String var12 = var6.substring(var11 + 1, var6.length());

            for(int var13 = 0; var13 < var12.length(); ++var13) {
               char var14 = var12.charAt(var13);
               if(var14 != 43 && var14 != 61 && var14 != 45 && var14 != 42 && var14 != 47 && var14 != 32) {
                  throw new bo("Unexpected text:\'" + var12 + "\' after [] index of: " + var6);
               }

               if(var14 == 61) {
                  break;
               }
            }

            var6 = var6.substring(0, var10) + var12;
         }

         var6 = var6.toLowerCase(Locale.ROOT).trim();
         String var15 = "=";
         if(var6.endsWith("+") || var6.endsWith("-") || var6.endsWith("*") || var6.endsWith("/")) {
            var15 = var6.substring(var6.length() - 1, var6.length()) + "=";
            var6 = var6.substring(0, var6.length() - 1).trim();
         }

         if(var6.contains(" ")) {
            throw new bo("Key cannot contain spaces for: " + var5);
         }

         if(var6.contains("[")) {
            throw new bo("Key cannot contain [ for: " + var5);
         }

         if(var6.contains("]")) {
            throw new bo("Key cannot contain ] for: " + var5);
         }

         if(var6.contains("(")) {
            throw new bo("Key cannot contain ( for: " + var5);
         }

         if(var6.contains(")")) {
            throw new bo("Key cannot contain ) for: " + var5);
         }

         if(var6.contains(".")) {
            throw new bo("Key cannot contain . for: " + var5);
         }

         VariableScope$CachedWriter$WriterElement var16 = var2.createWriterElement(var6, var15, var7, var9);
         this.writers.add(var16);
      }

   }
}
