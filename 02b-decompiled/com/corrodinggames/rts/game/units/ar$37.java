package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;

enum ar$37 {

   ar$37(String var1, int var2) {}

   public boolean j() {
      return true;
   }

   public boolean C() {
      return true;
   }

   public am a(boolean var1) {
      com.corrodinggames.rts.game.units.d.a.b var2 = new com.corrodinggames.rts.game.units.d.a.b(var1);
      var2.a_("artillery");
      return var2;
   }

   public void b() {}

   public int c() {
      return ar.f.c() + com.corrodinggames.rts.game.units.d.a.b.dN.c();
   }

   public float D() {
      return 6.0E-4F;
   }
}
