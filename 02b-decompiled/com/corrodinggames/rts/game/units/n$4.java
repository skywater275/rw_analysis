package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;

enum n$4 {

   n$4(String var1, int var2) {}

   public boolean a(as var1) {
      if(var1 == null) {
         return false;
      } else if(var1 instanceof com.corrodinggames.rts.game.units.custom.l) {
         com.corrodinggames.rts.game.units.custom.l var2 = (com.corrodinggames.rts.game.units.custom.l)var1;
         if(var2.J == null) {
            return false;
         } else {
            h var3 = h.L();
            return var3 == null || var3.E == null || var2.J == var3.E;
         }
      } else {
         return false;
      }
   }
}
