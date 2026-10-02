package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class g extends com.corrodinggames.rts.game.units.custom.a.a {

   com.corrodinggames.rts.game.units.custom.e.a a;
   com.corrodinggames.rts.game.units.custom.e.a b;
   double c;
   double d;
   float e;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      com.corrodinggames.rts.game.units.custom.e.a var7 = var1.a(var0, var2, "convertResource_from", (com.corrodinggames.rts.game.units.custom.e.a)null, true);
      com.corrodinggames.rts.game.units.custom.e.a var8 = var1.a(var0, var2, "convertResource_to", (com.corrodinggames.rts.game.units.custom.e.a)null, true);
      if((var7 != null || var8 != null) && (var7 == null || var8 == null)) {
         throw new bo("[" + var2 + "] Both convertResource_from and convertResource_to are required together");
      } else {
         if(var7 != null && var8 != null) {
            g var9 = new g();
            var9.a = var7;
            var9.b = var8;
            var9.c = var1.a(var2, "convertResource_minAmount", 0.0D);
            var9.d = var1.j(var2, "convertResource_maxAmount");
            if(var9.c < 0.0D) {
               throw new bo("[" + var2 + "] convertResource_minAmount cannot be < 0");
            }

            if(var9.d < 0.0D) {
               throw new bo("[" + var2 + "] convertResource_maxAmount cannot be < 0");
            }

            if(var9.d < var9.c) {
               throw new bo("[" + var2 + "] convertResource_maxAmount cannot be < convertResource_minAmount");
            }

            var9.e = var1.a(var2, "convertResource_multiplyAmountBy", Float.valueOf(1.0F)).floatValue();
            var4.ac.add(var9);
         }

      }
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      double var6 = this.a.a((am)var1);
      if(var6 > this.c) {
         double var8 = com.corrodinggames.rts.gameFramework.f.a(var6, this.d);
         this.a.b(var1, -var8);
         var8 *= (double)this.e;
         this.b.b(var1, var8);
      }

      return true;
   }
}
