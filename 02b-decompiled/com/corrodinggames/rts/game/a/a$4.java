package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.w;
import java.util.Iterator;

class a$4 extends d {

   // $FF: synthetic field
   final a a;


   a$4(a var1, String var2) {
      super(var1, var2);
      this.a = var1;
   }

   public boolean a(as var1) {
      am var2 = am.b(var1);
      if(var2.bI()) {
         if(var1 instanceof com.corrodinggames.rts.game.units.custom.l) {
            com.corrodinggames.rts.game.units.custom.l var3 = (com.corrodinggames.rts.game.units.custom.l)var1;
            if(var3.fw) {
               return false;
            }
         }

         boolean var8 = false;
         Iterator var4 = var2.N().iterator();

         while(var4.hasNext()) {
            s var5 = (s)var4.next();
            if(var5 != null && var5 instanceof w) {
               w var6 = (w)var5;
               if(!var6.F()) {
                  as var7 = var6.i();
                  if(var7 != null && !var7.j()) {
                     var8 = true;
                  }
               }
            }
         }

         if(var8) {
            return true;
         }
      }

      return false;
   }
}
