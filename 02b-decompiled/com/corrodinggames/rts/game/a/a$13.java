package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;

class a$13 extends d {

   // $FF: synthetic field
   final a a;


   a$13(a var1, String var2) {
      super(var1, var2);
      this.a = var1;
   }

   public boolean a(as var1) {
      if(var1.m()) {
         if(var1 instanceof com.corrodinggames.rts.game.units.custom.l) {
            com.corrodinggames.rts.game.units.custom.l var2 = (com.corrodinggames.rts.game.units.custom.l)var1;
            if(var2.fw) {
               return false;
            }
         }

         if(var1.o() != ao.e) {
            return true;
         }
      }

      return false;
   }
}
