package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;

class a$12 extends d {

   // $FF: synthetic field
   final a a;


   a$12(a var1, String var2) {
      super(var1, var2);
      this.a = var1;
   }

   public boolean a(as var1) {
      return this.a.bw.a(var1) && var1.o() != ao.d;
   }
}
