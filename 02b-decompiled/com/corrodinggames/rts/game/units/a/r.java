package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.p;
import com.corrodinggames.rts.gameFramework.ad;

public class r extends p {

   public r() {
      super("c__cut_ping");
   }

   public String b() {
      return "Map Ping";
   }

   public String a() {
      return "Send a map ping to your allies";
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      var3.bS.I();
      return true;
   }

   public ad M() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.bT.v;
   }
}
