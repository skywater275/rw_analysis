package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.p$1;

public enum r {

   a("grass", 0),
   b("sea", 1),
   c("sand", 2),
   d("dust", 3);
   // $FF: synthetic field
   private static final r[] e = new r[]{a, b, c, d};


   private r(String var1, int var2) {}

   public abstract String a();

   public abstract String b();

   // $FF: synthetic method
   r(String var1, int var2, p$1 var3) {
      this(var1, var2);
   }

}
