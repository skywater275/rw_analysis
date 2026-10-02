package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;

final class h$23 extends com.corrodinggames.rts.game.units.a.x {

   h$23(String var1) {
      super(var1);
   }

   public String a() {
      return "Change selected player\'s alliance (players with the same letter are allied)";
   }

   public String b() {
      return "Ally:";
   }

   public String d() {
      String var1 = "Ally";
      h var2 = h.L();
      if(var2 != null) {
         var1 = "Ally: " + var2.bX.h();
      }

      return var1;
   }

   public boolean a(am var1, boolean var2) {
      return true;
   }
}
