package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import java.util.Locale;

enum n$5 {

   n$5(String var1, int var2) {}

   public boolean a(as var1) {
      h var2 = h.L();
      if(var2 == null) {
         return false;
      } else if(var2.H == null) {
         return false;
      } else {
         if(var2.I) {
            var2.I = false;
            var2.J = var2.H.toLowerCase().trim();
         }

         return var1 == null?false:(var1.i() != null && var1.i().toLowerCase(Locale.ROOT).contains(var2.J)?true:var1.i() != null && var1.e().toLowerCase(Locale.ROOT).contains(var2.J));
      }
   }

   public boolean b() {
      h var1 = h.L();
      return var1 == null?false:var1.H != null;
   }
}
