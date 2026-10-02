package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$9$1;
import com.corrodinggames.rts.game.units.n;

final class h$9 extends com.corrodinggames.rts.game.units.a.x {

   h$9(String var1) {
      super(var1);
   }

   public com.corrodinggames.rts.gameFramework.m.e j() {
      return h.g;
   }

   public String a() {
      return "Search for units";
   }

   public String b() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      h var2 = h.L();
      return var2 != null && var2.G == n.e?"Search: " + com.corrodinggames.rts.gameFramework.f.b(var2.H, (int)8):"Search units";
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3.cb.i()) {
         var3.c("Reply active", "Changing search filter is currently not supported while recording a replay");
         return false;
      } else {
         h$9$1 var4 = new h$9$1(this);
         var4.b = "Search units by internal name or text title.";
         var4.e = "Search units";
         var4.f = "Search";
         var4.g = "Cancel";
         com.corrodinggames.rts.gameFramework.j.ad.a((com.corrodinggames.rts.gameFramework.j.ae)var4);
         return false;
      }
   }
}
