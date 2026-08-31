package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.x;

public class t extends x {

   public static t a(com.corrodinggames.rts.game.n var0) {
      t var1 = new t(true);
      var1.b(var0);
      var1.bV = true;
      return var1;
   }

   t(boolean var1) {
      super(var1);
   }

   public as r() {
      return ar.Z;
   }

   public static void b() {}

   public String c() {
      String var1 = this.r().i() + "(pos:" + (int)this.eo + "," + (int)this.ep;
      if(this.bX != null) {
         var1 = var1 + " t:" + this.bX.k;
      }

      var1 = var1 + ")";
      return var1;
   }
}
