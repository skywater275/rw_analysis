package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.f.au;

class as extends au {

   private boolean a;


   public strictfp as(float var1, float var2, boolean var3) {
      super(var1, var2);
      this.a = var3;
   }

   public strictfp boolean a(au var1) {
      if(super.a(var1) && var1 instanceof as) {
         as var2 = (as)var1;
         return var2.a == this.a;
      } else {
         return false;
      }
   }

   public strictfp void b(au var1) {}

   protected strictfp long b() {
      return 20000L;
   }

   public strictfp String a() {
      if(this.g == null) {
         if(this.a) {
            this.g = com.corrodinggames.rts.gameFramework.h.a.a("gui.log.baseDamaged", new Object[0]);
         } else {
            this.g = com.corrodinggames.rts.gameFramework.h.a.a("gui.log.unitDamaged", new Object[0]);
         }
      }

      return this.g;
   }
}
