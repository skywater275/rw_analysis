package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import java.util.Comparator;

final class ar$49 implements Comparator {

   public int a(as var1, as var2) {
      com.corrodinggames.rts.game.units.custom.d.b var3 = var1.u();
      com.corrodinggames.rts.game.units.custom.d.b var4 = var2.u();
      int var5 = var3.a(var4);
      return var5;
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((as)var1, (as)var2);
   }
}
