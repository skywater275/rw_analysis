package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;

final class h$8 extends com.corrodinggames.rts.game.units.a.x {

   h$8(String var1) {
      super(var1);
   }

   public String a() {
      return "Fast Forward 1-5x";
   }

   public String b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return "Fast Forward: " + var1.bt;
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3.cb.j()) {
         ;
      }

      if(var3.bt == 1.0F) {
         var3.bt = 2.0F;
      } else if(var3.bt == 2.0F) {
         var3.bt = 3.0F;
      } else if(var3.bt == 3.0F) {
         var3.bt = 4.0F;
      } else if(var3.bt == 4.0F) {
         var3.bt = 5.0F;
      } else if(var3.bt == 5.0F) {
         var3.bt = 10.0F;
      } else {
         var3.bt = 1.0F;
      }

      return false;
   }
}
