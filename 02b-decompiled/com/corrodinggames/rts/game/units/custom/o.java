package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.f;
import com.corrodinggames.rts.game.units.custom.l;
import java.util.Iterator;

public class o {

   String a;
   f b;
   // $FF: synthetic field
   final l c;


   public strictfp o(l var1) {
      this.c = var1;
   }

   public strictfp void a() {
      if(this.a != null && this.b() == null) {
         throw new RuntimeException("Failed to find animation:" + this.a);
      }
   }

   public strictfp f b() {
      if(this.a == null) {
         return null;
      } else if(this.b != null) {
         return this.b;
      } else {
         Iterator var1 = this.c.dr.iterator();

         f var2;
         do {
            if(!var1.hasNext()) {
               return null;
            }

            var2 = (f)var1.next();
         } while(!var2.a.equalsIgnoreCase(this.a));

         this.b = var2;
         return var2;
      }
   }
}
