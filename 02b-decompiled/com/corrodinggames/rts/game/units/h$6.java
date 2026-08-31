package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;

final class h$6 extends com.corrodinggames.rts.game.units.a.x {

   h$6(String var1) {
      super(var1);
   }

   public String a() {
      return "Pause Game";
   }

   public String b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.bt != 0.0F?"Pause: Off":"Pause: On";
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3.cb.j()) {
         ;
      }

      if(var3.bt != 0.0F) {
         var3.bt = 0.0F;
      } else {
         var3.bt = 1.0F;
      }

      return false;
   }
}
