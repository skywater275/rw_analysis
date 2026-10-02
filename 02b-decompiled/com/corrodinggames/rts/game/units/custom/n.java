package com.corrodinggames.rts.game.units.custom;


enum n {

   a("move", 0),
   b("attack", 1),
   c("idle", 2),
   d("created", 3),
   e("underConstruction", 4),
   f("underConstructionWithLinkedBuiltTime", 5),
   g("queuedUnits", 6),
   h("repair", 7),
   i("reclaim", 8);
   // $FF: synthetic field
   private static final n[] j = new n[]{a, b, c, d, e, f, g, h, i};


   private strictfp n(String var1, int var2) {}

   public static strictfp n a(String var0) {
      try {
         return valueOf(var0);
      } catch (IllegalArgumentException var2) {
         return null;
      }
   }

}
