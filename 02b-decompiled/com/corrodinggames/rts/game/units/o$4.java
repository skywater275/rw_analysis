package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;

enum o$4 {

   o$4(String var1, int var2) {}

   public boolean a(as var1) {
      if(var1 == null) {
         return false;
      } else {
         am var2 = am.c(var1);
         return !var2.bO() && var1.j();
      }
   }
}
