package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.l;
import java.util.Comparator;

class q implements Comparator {

   public strictfp int a(l var1, l var2) {
      return var1.M != null && var2.M != null?var1.M.compareTo(var2.M):0;
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((l)var1, (l)var2);
   }
}
