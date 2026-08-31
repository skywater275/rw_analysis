package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;

final class h$7 extends com.corrodinggames.rts.game.units.a.x {

   h$7(String var1) {
      super(var1);
   }

   public String a() {
      return "Slow motion";
   }

   public String b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.bt != 0.1F?"Slow motion: Off":"Slow motion: On";
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3.cb.j()) {
         ;
      }

      if(var3.bt == 1.0F) {
         var3.bt = 0.1F;
      } else {
         var3.bt = 1.0F;
      }

      return false;
   }
}
