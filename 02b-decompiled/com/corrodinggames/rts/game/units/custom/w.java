package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.v;

public class w extends v {

   public strictfp void a() {
      if(!this.e) {
         this.d = l.n(this.c);
         if(this.d == null) {
            throw new bo("Could not find customUnit target:" + this.d() + " used on:" + this.a + " in section:" + this.b);
         }
      }

   }

   public strictfp l e() {
      return (l)this.d;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as c() {
      return this.e();
   }
}
