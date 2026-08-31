package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.w;
import java.util.Comparator;

class x implements Comparator {

   public strictfp int a(w var1, w var2) {
      return var1.em > var2.em?1:(var1.em < var2.em?-1:(var1.en > var2.en?1:(var1.en < var2.en?-1:(var1.ep > var2.ep?1:(var1.ep < var2.ep?-1:0)))));
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((w)var1, (w)var2);
   }
}
