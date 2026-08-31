package com.corrodinggames.rts.gameFramework.d;


public enum h {

   a("verylow", 0),
   b("low", 1),
   c("high", 2),
   d("veryhigh", 3),
   e("critical", 4);
   // $FF: synthetic field
   private static final h[] f = new h[]{a, b, c, d, e};


   private h(String var1, int var2) {}

   public boolean a(h var1) {
      return var1 == null?true:this.ordinal() < var1.ordinal();
   }

}
