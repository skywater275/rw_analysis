package com.corrodinggames.rts.game.units.custom.e.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.a;

public class c extends a {

   public c() {
      this.u = true;
      this.t = true;
      this.b = "credits";
      this.c = bb.a("$");
      this.o = true;
      this.q = com.corrodinggames.rts.game.units.custom.e.b.b;
   }

   public double a(am var1) {
      return var1.bX.o;
   }

   public void a(am var1, double var2) {
      var1.bX.o = var2;
   }

   public void b(am var1, double var2) {
      var1.bX.o += var2;
   }

   public String a(boolean var1) {
      return "$";
   }
}
