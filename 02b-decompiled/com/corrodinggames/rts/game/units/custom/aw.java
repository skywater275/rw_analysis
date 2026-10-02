package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.j;

public abstract class aw extends at {

   public aw(int var1, String var2) {
      super(var1, var2);
   }

   public double a(j var1, as var2) {
      return this.a(var2);
   }

   public void a(j var1, double var2) {
      var1.dJ();
      this.a(var1.y, var2);
   }

   public abstract double a(as var1);

   public abstract void a(as var1, double var2);

   public boolean b() {
      return false;
   }
}
