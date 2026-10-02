package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.w;

public class x extends w {

   String g;
   bh h;


   public strictfp void a() {}

   public strictfp void b() {
      super.a();
      l var1 = this.e();
      this.h = var1.f(this.g);
      if(this.h == null) {
         throw new bo("Could not find projectile:" + this.g + " on unit target:" + this.d() + " used on:" + this.a + " in section:" + this.b);
      }
   }

   public strictfp bh f() {
      return this.h;
   }
}
