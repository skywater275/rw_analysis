package com.corrodinggames.rts.game.units;


final class h$11 extends com.corrodinggames.rts.game.units.a.x {

   h$11(String var1) {
      super(var1);
   }

   public String a() {
      return "AI debug view";
   }

   public String b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return !com.corrodinggames.rts.game.a.a.as?"AI Debug: Off":"AI Debug: On";
   }
}
