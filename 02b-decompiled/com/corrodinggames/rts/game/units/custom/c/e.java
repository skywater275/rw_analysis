package com.corrodinggames.rts.game.units.custom.c;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.c.a;
import com.corrodinggames.rts.game.units.custom.c.d;
import com.corrodinggames.rts.gameFramework.utility.m;

public class e {

   a a;
   m b = new m();


   public strictfp e(a var1) {
      this.a = var1;
   }

   public strictfp d a(am var1) {
      int var2 = this.b.a;
      Object[] var3 = this.b.a();

      for(int var4 = 0; var4 < var2; ++var4) {
         d var5 = (d)var3[var4];
         if(var5.a == var1) {
            return var5;
         }
      }

      return null;
   }
}
