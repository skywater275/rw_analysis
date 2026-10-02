package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;

final class h$5 extends com.corrodinggames.rts.game.units.a.x {

   h$5(String var1) {
      super(var1);
   }

   public String a() {
      return "Freeze full high level logic for all AI forever";
   }

   public String b() {
      return "Freeze AI";
   }

   public String d() {
      String var1 = "Freeze AI";
      h var2 = h.L();
      if(var2 != null) {
         boolean var3 = var2.c;
         if(var3) {
            var1 = "Unfreeze AIs";
         }
      }

      return var1;
   }

   public boolean a(am var1, boolean var2) {
      return true;
   }
}
