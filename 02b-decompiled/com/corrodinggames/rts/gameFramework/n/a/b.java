package com.corrodinggames.rts.gameFramework.n.a;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.gameFramework.n.a.a;

public class b extends a {

   n a;
   g b;


   public static b d(com.corrodinggames.rts.gameFramework.n.a var0) {
      b var1 = new b();
      var1.a = var0.a();
      if(var1.a == null) {
         throw new f("teamTagDetect requires a team set");
      } else {
         String var2 = var0.b("teamTag");
         if(var2 != null && !var2.equals("")) {
            try {
               var1.b = g.b(var2);
               return var1;
            } catch (bo var4) {
               throw new f(var4.getMessage());
            }
         } else {
            throw new f("teamTagDetect requires a teamTag set");
         }
      }
   }

   public boolean b(com.corrodinggames.rts.gameFramework.n.a var1) {
      return g.a(this.b, this.a.U());
   }
}
