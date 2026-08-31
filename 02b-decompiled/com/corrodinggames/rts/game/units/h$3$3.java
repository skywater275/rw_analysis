package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$3;

class h$3$3 implements Runnable {

   // $FF: synthetic field
   final h$3 a;


   h$3$3(h$3 var1) {
      this.a = var1;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var2 = var1.cb.j();
      if(!var2) {
         com.corrodinggames.rts.gameFramework.l.e("stopPlaybackRunnable: Already stopped");
      } else {
         var1.cb.e();
         var1.bt = 1.0F;
         var1.bv = true;
         h var3 = h.L();
         if(var3 != null) {
            var1.bs = var3.bX;
         }
      }

   }
}
