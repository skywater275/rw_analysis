package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.f.ar;

class at extends ar {

   public strictfp at(float var1, float var2, com.corrodinggames.rts.game.units.as var3) {
      super(var1, var2, var3);
   }

   public strictfp String a() {
      if(this.g == null) {
         this.g = String.format(com.corrodinggames.rts.gameFramework.h.a.a("gui.log.upgradeCompleted", new Object[0]), new Object[]{this.a.e(), Integer.valueOf(this.b)});
      }

      return this.g;
   }
}
