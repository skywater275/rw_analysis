package com.corrodinggames.rts.game.units.b;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.b.c;

final class c$2 extends x {

   c$2(int var1) {
      super(var1);
   }

   public String a() {
      return "-Dive unit underwater.";
   }

   public String b() {
      return "Dive";
   }

   public boolean a(am var1, boolean var2) {
      return ((c)var1).r && ((y)var1).cJ();
   }
}
