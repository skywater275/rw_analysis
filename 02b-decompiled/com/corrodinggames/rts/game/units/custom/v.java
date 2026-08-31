package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;

public class v {

   String a;
   String b;
   String c;
   com.corrodinggames.rts.game.units.as d;
   boolean e;
   public boolean f;


   public strictfp void a() {
      if(!this.e) {
         this.d = l.s(this.c);
         if(this.d == null) {
            com.corrodinggames.rts.gameFramework.l.e("AllUnitTypes: " + l.E());
            if(this.f) {
               throw new bo("Could not find unit type:" + this.c + " used on:" + this.a + " in section:" + this.b + " (Note: Prefix with \'unitref\' if not using a unit type here)");
            }

            throw new bo("Could not find unit type:" + this.c + " used on:" + this.a + " in section:" + this.b);
         }
      }

   }

   public strictfp void b() {}

   public strictfp com.corrodinggames.rts.game.units.as c() {
      return this.d;
   }

   public strictfp String d() {
      return this.e?(this.d != null?this.d.i():"(Error: known type is null)"):this.c;
   }
}
