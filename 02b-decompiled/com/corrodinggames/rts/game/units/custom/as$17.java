package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ax;
import com.corrodinggames.rts.game.units.custom.j;

final class as$17 extends ax {

   as$17(int var1, String var2) {
      super(var1, var2);
   }

   public double a(j var1) {
      return (double)var1.cx;
   }

   public void b(j var1, double var2) {
      var1.cx = (float)var2;
   }

   public void a(j var1, double var2) {
      super.a(var1, var2);
      var1.cx = (float)var2;
   }
}
