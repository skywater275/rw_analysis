package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.bj;

public enum ac {

   a("overallStats", 0, "A", (bj)null),
   b("incomeChart", 1, "B", bj.a),
   c("armyValueChart", 2, "C", bj.b),
   d("buildingValueChart", 3, "D", bj.c),
   e("totalValueChart", 4, "E", bj.d);
   private final String f;
   private final bj g;
   // $FF: synthetic field
   private static final ac[] h = new ac[]{a, b, c, d, e};


   private ac(String var1, int var2, String var3, bj var4) {
      this.f = var3;
      this.g = var4;
   }

   public bj a() {
      return this.g;
   }

}
