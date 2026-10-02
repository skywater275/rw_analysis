package com.corrodinggames.rts.gameFramework;


public enum bj {

   a("income", 0, com.corrodinggames.rts.gameFramework.g.f.b),
   b("armyValue", 1, com.corrodinggames.rts.gameFramework.g.f.c),
   c("buildingValue", 2, com.corrodinggames.rts.gameFramework.g.f.d),
   d("totalValue", 3, com.corrodinggames.rts.gameFramework.g.f.e);
   final com.corrodinggames.rts.gameFramework.g.f e;
   // $FF: synthetic field
   private static final bj[] f = new bj[]{a, b, c, d};


   private strictfp bj(String var1, int var2, com.corrodinggames.rts.gameFramework.g.f var3) {
      this.e = var3;
   }

   public strictfp com.corrodinggames.rts.gameFramework.g.f a() {
      return this.e;
   }

}
