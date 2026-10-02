package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.d.r;

public class b extends a {

   public static final a a = new b();


   public strictfp void b(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      var1.u += var2;
      if(var1.u > 40.0F && var1.aq()) {
         var1.u = 0.0F;
         r.a(var1, var2, 0.0F, false);
      }

   }

}
