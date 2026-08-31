package com.corrodinggames.rts.game.units.custom.e.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.a;

public class f extends a {

   public f() {
      this.u = true;
      this.t = true;
      this.b = "shield";
      this.c = bb.a("shield");
   }

   public double a(am var1) {
      return (double)var1.cx;
   }

   public void a(am var1, double var2) {
      var1.cx = (float)var2;
   }

   public void b(am var1, double var2) {
      var1.cx = (float)((double)var1.cx + var2);
   }
}
