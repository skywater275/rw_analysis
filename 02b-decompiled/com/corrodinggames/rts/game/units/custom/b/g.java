package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.t;
import com.corrodinggames.rts.game.units.custom.b.c;
import com.corrodinggames.rts.game.units.custom.b.d;
import com.corrodinggames.rts.game.units.custom.b.f;

public class g extends t {

   String a;
   com.corrodinggames.rts.gameFramework.utility.m b = new com.corrodinggames.rts.gameFramework.utility.m();


   public strictfp g(String var1) {
      this.a = var1;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.l var1) {
      if(this.a != null) {
         String[] var2 = this.a.split(",");
         String[] var3 = var2;
         int var4 = var2.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            var6 = var6.trim();
            d var7 = c.b(var1, var6);
            if(var7 == null) {
               throw new bo("Failed to find decal: " + var6);
            }

            this.b.add(var7);
         }

         this.a = null;
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.j var1, float var2, float var3) {
      c.i.a(var2, var3);
      c.a(var1, 0.0F, f.f, this.b, c.i);
   }
}
