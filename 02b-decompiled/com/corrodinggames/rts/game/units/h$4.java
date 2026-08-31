package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;

final class h$4 extends com.corrodinggames.rts.game.units.a.x {

   h$4(String var1) {
      super(var1);
   }

   public String a() {
      String var1 = "Hide interface till the screen is clicked/pressed";
      if(com.corrodinggames.rts.gameFramework.l.av()) {
         var1 = var1 + "\n-Enable mouse capture to also hide the mouse";
      }

      return var1;
   }

   public String b() {
      return "Hide interface";
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      var3.cU = true;
      return false;
   }
}
