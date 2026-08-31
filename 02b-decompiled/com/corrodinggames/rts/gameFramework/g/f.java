package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.gameFramework.g.a$1;
import java.util.ArrayList;
import java.util.Iterator;

public enum f {

   a("none", 0),
   b("income", 1),
   c("armyValue", 2),
   d("buildingValue", 3),
   e("totalValue", 4),
   f("credits", 5);
   // $FF: synthetic field
   private static final f[] g = new f[]{a, b, c, d, e, f};


   private f(String var1, int var2) {}

   public int a(n var1) {
      switch(a$1.a[this.ordinal()]) {
      case 1:
      default:
         return 0;
      case 2:
         int var2 = var1.v();
         ArrayList var3 = com.corrodinggames.rts.game.units.custom.e.a.f();
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            com.corrodinggames.rts.game.units.custom.e.a var5 = (com.corrodinggames.rts.game.units.custom.e.a)var4.next();
            if(var5.d()) {
               float var6 = var5.b();
               if(var6 != 0.0F) {
                  var2 = (int)((float)var2 + var6 * (float)var1.b(var5));
               }
            }
         }

         return var2;
      case 3:
         return var1.T.n;
      case 4:
         return var1.T.o;
      case 5:
         return var1.T.n + var1.T.o;
      case 6:
         return (int)var1.o;
      }
   }

}
