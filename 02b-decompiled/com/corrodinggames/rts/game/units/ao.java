package com.corrodinggames.rts.game.units;

import java.util.Locale;

public enum ao {

   a("NONE", 0),
   b("LAND", 1),
   c("BUILDING", 2),
   d("AIR", 3),
   e("WATER", 4),
   f("HOVER", 5),
   g("OVER_CLIFF", 6),
   h("OVER_CLIFF_WATER", 7);
   // $FF: synthetic field
   private static final ao[] i = new ao[]{a, b, c, d, e, f, g, h};


   private strictfp ao(String var1, int var2) {}

   public static strictfp ao a(String var0, String var1) {
      try {
         return valueOf(var0.toUpperCase(Locale.ROOT));
      } catch (IllegalArgumentException var8) {
         String var3 = "";
         ao[] var4 = values();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            ao var7 = var4[var6];
            var3 = var3 + ", " + var7.toString();
         }

         throw new IllegalArgumentException("Unknown movement type:\'" + var0 + "\' possible type:" + var3 + " on key:" + var1);
      }
   }

}
