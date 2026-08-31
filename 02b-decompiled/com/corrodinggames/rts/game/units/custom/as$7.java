package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.aw;
import com.corrodinggames.rts.game.units.custom.j;

final class as$7 extends aw {

   as$7(int var1, String var2) {
      super(var1, var2);
   }

   public double a(as var1) {
      return (double)var1.n;
   }

   public void a(as var1, double var2) {
      var1.n = (int)var2;
   }

   public void a(j var1, double var2) {
      int var4 = var1.s();
      super.a(var1, var2);
      if(var1.s() > var4 && !var1.ax) {
         var1.c(false);
      }

   }
}
