package com.corrodinggames.rts.appFramework;

import java.util.Comparator;

final class p$1 implements Comparator {

   public int a(com.corrodinggames.rts.gameFramework.j.g var1) {
      if(var1.d()) {
         return 0;
      } else if(var1.x && var1.s.equals("chat")) {
         return 1;
      } else if(var1.a) {
         return 2;
      } else if(!var1.s.equals("battleroom")) {
         return 99;
      } else {
         if(var1.v != -1 && var1.v < var1.w) {
            if(var1.x) {
               if(var1.v != 0) {
                  return 3;
               }

               return 4;
            }

            if(var1.h && !var1.x) {
               return 7;
            }
         } else {
            if(var1.x) {
               return 6;
            }

            if(var1.h && !var1.x) {
               return 8;
            }
         }

         return 9;
      }
   }

   public int a(com.corrodinggames.rts.gameFramework.j.g var1, com.corrodinggames.rts.gameFramework.j.g var2) {
      Integer var3 = Integer.valueOf(this.a(var1));
      Integer var4 = Integer.valueOf(this.a(var2));
      if(!var1.g()) {
         var3 = Integer.valueOf(var3.intValue() + 20);
      }

      if(!var2.g()) {
         var4 = Integer.valueOf(var4.intValue() + 20);
      }

      int var5 = var3.compareTo(var4);
      return var5 != 0?var5:var1.q.compareTo(var2.q);
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((com.corrodinggames.rts.gameFramework.j.g)var1, (com.corrodinggames.rts.gameFramework.j.g)var2);
   }
}
