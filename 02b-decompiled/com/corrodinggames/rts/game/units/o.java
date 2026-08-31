package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h$1;

public enum o {

   a("land", 0),
   b("air", 1),
   c("sea", 2),
   d("buildings", 3),
   e("bio", 4);
   // $FF: synthetic field
   private static final o[] f = new o[]{a, b, c, d, e};


   private strictfp o(String var1, int var2) {}

   public abstract boolean a(as var1);

   public strictfp String a() {
      return this.name();
   }

   public strictfp o a(boolean var1) {
      return !var1?this.a(1, 0):this.a(-1, 0);
   }

   public strictfp o a(int var1, int var2) {
      int var3 = this.ordinal() + var1;
      var3 %= values().length;
      if(var3 < 0) {
         var3 += values().length;
      }

      o var4 = values()[var3];
      if(!var4.b()) {
         if(var2 > 30) {
            com.corrodinggames.rts.gameFramework.l.e("jumpBy recursion limit hit");
            return var4;
         }

         var4 = var4.a(var1, var2 + 1);
      }

      return var4;
   }

   public strictfp boolean b() {
      return true;
   }

   // $FF: synthetic method
   o(String var1, int var2, h$1 var3) {
      this(var1, var2);
   }

}
