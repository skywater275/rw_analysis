package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;

final class h$22 extends com.corrodinggames.rts.game.units.a.x {

   h$22(String var1) {
      super(var1);
   }

   public String a() {
      return "Freeze high level AI logic (120secs)";
   }

   public String b() {
      return "Freeze AI";
   }

   public String d() {
      String var1 = "Freeze AI";
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      h var3 = h.L();
      if(var3 != null) {
         int var4 = -1;
         if(var3.bX instanceof com.corrodinggames.rts.game.a.a) {
            com.corrodinggames.rts.game.a.a var5 = (com.corrodinggames.rts.game.a.a)var3.bX;
            var4 = (int)var5.bG / 60;
         }

         if(var4 > 0) {
            var1 = var1 + "(" + var4 + ")";
         }
      }

      return var1;
   }

   public boolean a(am var1, boolean var2) {
      return var1.bX instanceof com.corrodinggames.rts.game.a.a;
   }
}
