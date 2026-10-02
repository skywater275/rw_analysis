package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.j;

public abstract class ax extends at {

   public ax(int var1, String var2) {
      super(var1, var2);
   }

   public double a(j var1, as var2) {
      return this.a(var1);
   }

   public void a(j var1, double var2) {
      var1.dJ();
      this.b(var1, var2);
   }

   public abstract double a(j var1);

   public abstract void b(j var1, double var2);

   public boolean b() {
      return true;
   }
}
