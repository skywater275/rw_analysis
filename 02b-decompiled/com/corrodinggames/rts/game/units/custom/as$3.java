package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.aw;
import com.corrodinggames.rts.game.units.custom.j;

final class as$3 extends aw {

   as$3(int var1, String var2) {
      super(var1, var2);
   }

   public double a(as var1) {
      return (double)var1.e;
   }

   public void a(as var1, double var2) {
      var1.e = (float)var2;
   }

   public void a(j var1, double var2) {
      super.a(var1, var2);
      var1.aW();
   }
}
