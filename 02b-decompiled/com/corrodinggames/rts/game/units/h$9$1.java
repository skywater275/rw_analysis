package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$9;
import com.corrodinggames.rts.game.units.n;

class h$9$1 extends com.corrodinggames.rts.gameFramework.j.ae {

   // $FF: synthetic field
   final h$9 a;


   h$9$1(h$9 var1) {
      this.a = var1;
   }

   public void a(String var1) {
      com.corrodinggames.rts.gameFramework.l.e("Searching for: " + var1);
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(var2.cb.i()) {
         var2.c("Reply active", "Changing search filter is currently not supported while recording a replay");
      } else {
         h var3 = h.L();
         if(var3 == null) {
            com.corrodinggames.rts.gameFramework.l.e("search: No editor");
         } else if(var1 != null && !var1.trim().equals("")) {
            var3.G = n.e;
            var3.H = var1;
            var3.I = true;
            com.corrodinggames.rts.gameFramework.f.g.K();
         } else {
            com.corrodinggames.rts.gameFramework.l.e("search: No text entered");
            if(var3.G == n.e) {
               var3.G = n.a;
            }

            var3.H = null;
            var3.I = true;
            com.corrodinggames.rts.gameFramework.f.g.K();
         }
      }
   }

   public strictfp void a() {}
}
