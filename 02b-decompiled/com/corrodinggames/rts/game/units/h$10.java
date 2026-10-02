package com.corrodinggames.rts.game.units;


final class h$10 extends com.corrodinggames.rts.game.units.a.x {

   h$10(String var1) {
      super(var1);
   }

   public String a() {
      return "Show hidden unit information in tooltips including flags, ammo, tags and resources";
   }

   public String b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return !var1.bl?"Debug: Off":"Debug: On";
   }
}
