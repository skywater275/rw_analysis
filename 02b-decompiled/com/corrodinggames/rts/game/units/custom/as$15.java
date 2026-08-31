package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ax;
import com.corrodinggames.rts.game.units.custom.j;

final class as$15 extends ax {

   as$15(int var1, String var2) {
      super(var1, var2);
   }

   public double a(j var1) {
      return (double)var1.cu;
   }

   public void b(j var1, double var2) {
      var1.cu = (float)var2;
   }

   public void a(j var1, double var2) {
      super.a(var1, var2);
      var1.o((float)var2);
   }
}
