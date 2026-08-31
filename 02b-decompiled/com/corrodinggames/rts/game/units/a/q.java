package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.p;
import com.corrodinggames.rts.gameFramework.ad;

public class q extends p {

   public q() {
      super("c__cut_chat");
   }

   public String b() {
      return "Team Chat";
   }

   public String a() {
      return "Send a team chat message to your allies";
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      var3.bS.g.n();
      return true;
   }

   public ad M() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.bT.u;
   }
}
