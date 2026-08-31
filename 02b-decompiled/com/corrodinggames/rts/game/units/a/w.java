package com.corrodinggames.rts.game.units.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.u;

public abstract class w extends s {

   public w(int var1) {
      super(var1);
   }

   public w(String var1) {
      super(var1);
   }

   public int b(am var1, boolean var2) {
      return !(var1 instanceof com.corrodinggames.rts.game.units.d.l)?99:((com.corrodinggames.rts.game.units.d.l)var1).a(this.N(), var2);
   }

   public float p(am var1) {
      if(!(var1 instanceof com.corrodinggames.rts.game.units.d.l)) {
         return -1.0F;
      } else {
         com.corrodinggames.rts.game.units.d.l var2 = (com.corrodinggames.rts.game.units.d.l)var1;
         com.corrodinggames.rts.game.units.d.j var3 = var2.dw();
         if(var3 == null) {
            return -1.0F;
         } else if(!this.d(var3.j)) {
            return -1.0F;
         } else {
            float var4 = var3.m;
            return var4 < 0.0F?0.0F:(var4 > 1.0F?1.0F:var4);
         }
      }
   }

   public float K() {
      return 0.01F;
   }

   public boolean u() {
      return true;
   }

   public u e() {
      return u.c;
   }
}
