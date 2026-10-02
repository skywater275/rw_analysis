package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.bm;
import com.corrodinggames.rts.game.units.custom.l;
import java.util.ArrayList;
import java.util.Iterator;

public class bl {

   ArrayList a = new ArrayList();
   int b;


   public void a(float var1) {
      bm var3;
      for(Iterator var2 = this.a.iterator(); var2.hasNext(); var3.b = var1) {
         var3 = (bm)var2.next();
      }

   }

   public boolean a() {
      return this.a(0.0F, 0.0F, 1.0F, true);
   }

   public boolean a(float var1, float var2, float var3) {
      return this.a(var1, var2, var3, false);
   }

   public boolean a(float var1, float var2, float var3, boolean var4) {
      if(this.a.size() == 0) {
         return false;
      } else {
         if(this.b >= this.a.size()) {
            this.b = 0;
         }

         bm var5 = (bm)this.a.get(this.b);
         com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
         if(var4) {
            var6.bM.c(var5.a, var5.b);
         } else {
            var6.bM.a(var5.a, var5.b, var1, var2);
         }

         ++this.b;
         return true;
      }
   }

   public static bl a(l var0, String var1) {
      return a(var0, var1, (bl)null);
   }

   public static bl a(l var0, String var1, bl var2) {
      if((var1 == null || var1.equals("")) && var2 != null) {
         return var2;
      } else {
         bl var3 = new bl(var0, var1);
         return var3;
      }
   }

   public bl() {}

   public bl(l var1, String var2) {
      if(var2 != null && !var2.equals("") && !var2.equalsIgnoreCase("NONE")) {
         String[] var3 = var2.split(",");
         String[] var4 = var3;
         int var5 = var3.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            String var7 = var4[var6];
            bm var8 = new bm(this);
            var7 = var7.trim();
            String var9 = "";
            if(var7.startsWith("ROOT:")) {
               var7 = var7.substring("ROOT:".length());
               var9 = var9 + "ROOT:";
            }

            if(var7.startsWith("SHARED:")) {
               var7 = var7.substring("SHARED:".length());
               var9 = var9 + "SHARED:";
            }

            String[] var10 = var7.split(":");
            String var11 = null;
            String var12 = var10[0].trim();
            if(var10.length != 1) {
               if(var10.length != 2) {
                  throw new RuntimeException("Unknown sound format:" + var7);
               }

               var11 = var10[1].trim();
            }

            if(var11 != null) {
               try {
                  var8.b = Float.parseFloat(var11);
               } catch (NumberFormatException var14) {
                  throw new RuntimeException("Failed to parse volume float: \'" + var11 + "\' of sound: \'" + var7 + "\'");
               }
            }

            var12 = var9 + var12;
            var8.a = ag.a(var1.F, var12, var1);
            if(var8.a != null) {
               this.a.add(var8);
            }
         }

      }
   }
}
