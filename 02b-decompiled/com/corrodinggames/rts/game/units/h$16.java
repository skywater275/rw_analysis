package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.n;

final class h$16 extends com.corrodinggames.rts.game.units.a.b {

   public boolean isAvailable(com.corrodinggames.rts.game.units.a.s var1, am var2) {
      if(var1 instanceof com.corrodinggames.rts.game.units.a.h) {
         var1 = ((com.corrodinggames.rts.game.units.a.h)var1).q_();
      }

      h var3 = h.L();
      if(var3 == null) {
         return true;
      } else {
         n var4 = var3.G;
         if(var4 == null) {
            var4 = n.a;
         }

         if(var4 == n.a && h.a(var1, var2)) {
            return false;
         } else if(var4 == n.d && var1 == h.h) {
            return true;
         } else if(var4 == n.d && var1 == h.i) {
            return true;
         } else if(var4 == n.e && var1 == h.y) {
            return true;
         } else if(var1 == h.B && !h.B.b(var2)) {
            return false;
         } else if(var1 == h.C && !h.C.b(var2)) {
            return false;
         } else {
            as var5 = var1.i();
            return var4.a(var5);
         }
      }
   }
}
