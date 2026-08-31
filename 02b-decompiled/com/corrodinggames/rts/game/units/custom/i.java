package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;

public class i {

   public com.corrodinggames.rts.gameFramework.utility.m a = new com.corrodinggames.rts.gameFramework.utility.m();


   public i() {}

   public i(h var1) {
      if(var1 != null) {
         g[] var2 = var1.a;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            g var5 = var2[var4];
            this.a.add(var5);
         }

      }
   }

   public boolean a(h var1) {
      if(var1 == null) {
         return false;
      } else {
         boolean var2 = false;
         g[] var3 = var1.a;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            g var6 = var3[var5];
            if(this.a(var6)) {
               var2 = true;
            }
         }

         return var2;
      }
   }

   public boolean a(g var1) {
      if(!this.a.contains(var1)) {
         this.a.add(var1);
         return true;
      } else {
         return false;
      }
   }

   public boolean b(h var1) {
      if(var1 == null) {
         return false;
      } else {
         boolean var2 = false;
         g[] var3 = var1.a;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            g var6 = var3[var5];
            if(this.a.remove(var6)) {
               var2 = true;
            }
         }

         return var2;
      }
   }

   public h a() {
      return this.a.size() == 0?g.d:new h((g[])this.a.toArray(g.c));
   }
}
