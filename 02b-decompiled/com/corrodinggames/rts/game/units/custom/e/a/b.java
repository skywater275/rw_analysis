package com.corrodinggames.rts.game.units.custom.e.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.a;

public class b extends a {

   public b() {
      this.u = true;
      this.t = true;
      this.b = "ammo";
      this.c = bb.a("ammo");
   }

   public double a(am var1) {
      return (double)var1.cE;
   }

   public void a(am var1, double var2) {
      var1.cE = (int)var2;
   }

   public void b(am var1, double var2) {
      var1.cE = (int)((double)var1.cE + var2);
   }
}
