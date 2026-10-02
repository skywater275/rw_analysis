package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import java.util.Comparator;

final class h$15 implements Comparator {

   public strictfp int a(as var1, as var2) {
      am var4 = am.c(var1);
      am var5 = am.c(var2);
      Boolean var6 = Boolean.valueOf(var4.bP());
      Boolean var7 = Boolean.valueOf(var5.bP());
      int var3 = var6.compareTo(var7);
      if(var3 != 0) {
         return var3;
      } else {
         Boolean var8 = Boolean.valueOf(var1.j());
         Boolean var9 = Boolean.valueOf(var2.j());
         var3 = var8.compareTo(var9);
         if(var3 != 0) {
            return var3;
         } else {
            Boolean var10 = Boolean.valueOf(var4.bO());
            Boolean var11 = Boolean.valueOf(var5.bO());
            var3 = var10.compareTo(var11);
            if(var3 != 0) {
               return var3;
            } else {
               com.corrodinggames.rts.game.units.custom.d.b var12 = var1.u();
               com.corrodinggames.rts.game.units.custom.d.b var13 = var2.u();
               com.corrodinggames.rts.game.units.custom.d.b var14 = var1.B();
               com.corrodinggames.rts.game.units.custom.d.b var15 = var2.B();
               if(var14 != null) {
                  var12 = com.corrodinggames.rts.game.units.custom.d.b.a(var12, var14);
               }

               if(var15 != null) {
                  var13 = com.corrodinggames.rts.game.units.custom.d.b.a(var13, var15);
               }

               var3 = var12.a(var13);
               return var3 != 0?var3:0;
            }
         }
      }
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((as)var1, (as)var2);
   }
}
