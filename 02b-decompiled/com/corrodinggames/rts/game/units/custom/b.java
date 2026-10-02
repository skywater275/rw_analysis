package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.a;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.z;

class b extends a {

   public z e;
   public float f;
   public float g;
   public boolean h;


   public b(float var1, float var2) {
      super(var1, var2);
   }

   public void a(l var1, String var2, String var3) {
      try {
         if(var2.equalsIgnoreCase("x")) {
            this.f = Float.parseFloat(var3);
            return;
         }

         if(var2.equalsIgnoreCase("y")) {
            this.g = Float.parseFloat(var3);
            return;
         }
      } catch (NumberFormatException var5) {
         throw new bo("Failed to parse float:" + var3);
      }

      if(var2.equalsIgnoreCase("name")) {
         this.e = var1.a(var3, (z)null);
      } else {
         throw new bo("Unknown event key:" + var2 + " on animation");
      }
   }

   public void finalize() {
      this.h = true;
      if(this.e == null) {
         throw new bo("Animation effect missing key \'name\'");
      }
   }

   public void a(j var1) {
      if(this.e != null) {
         float var2 = var1.eo;
         float var3 = var1.ep;
         var2 += this.f;
         var3 += this.g;
         this.e.a(var2, var3, var1.eq, var1.cg, var1);
      }

   }
}
