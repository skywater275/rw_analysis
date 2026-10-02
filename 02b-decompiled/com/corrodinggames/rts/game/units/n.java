package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h$1;

public enum n {

   a("all", 0),
   b("types", 1),
   c("terrain", 2),
   d("modded", 3),
   e("search", 4),
   f("actions", 5);
   // $FF: synthetic field
   private static final n[] g = new n[]{a, b, c, d, e, f};


   private strictfp n(String var1, int var2) {}

   public abstract boolean a(as var1);

   public strictfp String a() {
      return this.name();
   }

   public strictfp boolean b() {
      return true;
   }

   public strictfp n a(boolean var1) {
      return !var1?this.a(1, 0):this.a(-1, 0);
   }

   public strictfp n a(int var1, int var2) {
      int var3 = this.ordinal() + var1;
      var3 %= values().length;
      if(var3 < 0) {
         var3 += values().length;
      }

      n var4 = values()[var3];
      if(!var4.b()) {
         if(var2 > 30) {
            com.corrodinggames.rts.gameFramework.l.e("jumpBy recursion limit hit");
            return var4;
         }

         var4 = var4.a(var1, var2 + 1);
      }

      return var4;
   }

   // $FF: synthetic method
   n(String var1, int var2, h$1 var3) {
      this(var1, var2);
   }

}
