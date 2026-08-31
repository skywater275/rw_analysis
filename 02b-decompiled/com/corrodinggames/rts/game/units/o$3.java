package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;

enum o$3 {

   o$3(String var1, int var2) {}

   public boolean a(as var1) {
      if(var1 == null) {
         return false;
      } else {
         am var2 = am.c(var1);
         return !var2.bO() && !var1.j()?var2.h() == ao.e:false;
      }
   }
}
