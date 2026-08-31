package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.gameFramework.f.au;

class ar extends au {

   com.corrodinggames.rts.game.units.as a;
   int b;


   public strictfp ar(float var1, float var2, com.corrodinggames.rts.game.units.as var3) {
      super(var1, var2);
      this.a = var3;
      this.b = 1;
   }

   public strictfp boolean a(au var1) {
      if(super.a(var1) && var1 instanceof ar) {
         ar var2 = (ar)var1;
         return var2.a == this.a;
      } else {
         return false;
      }
   }

   public strictfp void b(au var1) {
      this.c = var1.c;
      ++this.b;
      this.g = null;
      this.h = false;
   }

   public strictfp String a() {
      if(this.g == null) {
         String var1 = "gui.log.unitCreated";
         if(this.a.j()) {
            var1 = "gui.log.buildingConstructed";
         }

         this.g = String.format(com.corrodinggames.rts.gameFramework.h.a.a(var1, new Object[0]), new Object[]{this.a.e(), Integer.valueOf(this.b)});
      }

      return this.g;
   }
}
