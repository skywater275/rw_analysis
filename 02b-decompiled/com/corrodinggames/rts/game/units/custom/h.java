package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.g;

public final class h {

   public final g[] a;


   public h(g[] var1) {
      this.a = var1;
   }

   public boolean a() {
      return this.a.length == 0;
   }

   public boolean a(h var1) {
      if(var1 == null) {
         return this.a();
      } else if(this.a.length != var1.a.length) {
         return false;
      } else {
         g[] var2 = this.a;
         int var3 = var2.length;
         int var4 = 0;

         while(var4 < var3) {
            g var5 = var2[var4];
            boolean var6 = false;
            g[] var7 = var1.a;
            int var8 = var7.length;
            int var9 = 0;

            while(true) {
               if(var9 < var8) {
                  g var10 = var7[var9];
                  if(var5 != var10) {
                     ++var9;
                     continue;
                  }

                  var6 = true;
               }

               if(!var6) {
                  return false;
               }

               ++var4;
               break;
            }
         }

         return true;
      }
   }

   public int b() {
      return this.a.length;
   }

   public String toString() {
      String var1 = "";
      boolean var2 = true;
      g[] var3 = this.a;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         g var6 = var3[var5];
         if(!var2) {
            var1 = var1 + ", ";
         }

         var2 = false;
         var1 = var1 + var6.a;
      }

      return "{" + var1 + "}";
   }
}
