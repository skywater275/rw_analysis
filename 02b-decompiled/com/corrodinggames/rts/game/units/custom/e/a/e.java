package com.corrodinggames.rts.game.units.custom.e.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.a;

public class e extends a {

   public e() {
      this.u = true;
      this.t = true;
      this.b = "hp";
      this.c = bb.a("hp");
   }

   public double a(am var1) {
      return (double)var1.cu;
   }

   public void a(am var1, double var2) {
      var1.o((float)var2);
   }

   public void b(am var1, double var2) {
      var1.o(var1.cu + (float)var2);
   }
}
