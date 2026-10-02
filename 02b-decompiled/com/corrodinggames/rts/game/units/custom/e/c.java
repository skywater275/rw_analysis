package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.e.e;
import com.corrodinggames.rts.game.units.custom.e.f;
import com.corrodinggames.rts.gameFramework.utility.m;

public class c {

   public final m a = new m();


   public void a(a var1) {
      if(!this.a.contains(var1)) {
         this.a.add(var1);
      }

   }

   public void a(f var1, am var2, double var3) {
      int var5 = var1.b.a;
      Object[] var6 = var1.b.a();

      for(int var7 = 0; var7 < var5; ++var7) {
         e var8 = (e)var6[var7];
         double var9 = var8.a.a(var2);
         if(var9 < var8.b * var3) {
            this.a(var8.a);
         }
      }

   }

   public void a(com.corrodinggames.rts.game.units.custom.d.b var1, am var2, double var3) {
      if(!var1.k.c()) {
         this.a(var1.k, var2, var3);
      }

      if(var1.b > 0 && var2.bX.o < (double)var1.b * var3) {
         this.a(com.corrodinggames.rts.game.units.custom.e.a.a.D);
      }

   }

   public boolean a(f var1) {
      int var2 = var1.b.a;
      Object[] var3 = var1.b.a();

      for(int var4 = 0; var4 < var2; ++var4) {
         e var5 = (e)var3[var4];
         if(this.a.contains(var5.a)) {
            return true;
         }
      }

      return false;
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.d.b var1) {
      return var1.b > 0 && this.a.contains(com.corrodinggames.rts.game.units.custom.e.a.a.D)?true:!var1.k.c() && this.a(var1.k);
   }

   public void a() {
      this.a.clear();
   }
}
