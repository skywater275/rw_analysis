package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;

class a$10 extends d {

   // $FF: synthetic field
   final a a;


   a$10(a var1, String var2) {
      super(var1, var2);
      this.a = var1;
   }

   public boolean a(as var1) {
      am var2 = am.b(var1);
      if(this.a.g(var2)) {
         if(var1 instanceof com.corrodinggames.rts.game.units.custom.l) {
            com.corrodinggames.rts.game.units.custom.l var3 = (com.corrodinggames.rts.game.units.custom.l)var1;
            if(var3.fw) {
               return false;
            }
         }

         if(var1.o() == ao.d || var1.o() == ao.f || var1.o() == ao.h) {
            return true;
         }
      }

      return false;
   }
}
