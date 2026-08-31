package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.e.j;

public abstract class h extends j {

   float l;
   public static com.corrodinggames.rts.gameFramework.m.e m = null;
   public static com.corrodinggames.rts.gameFramework.m.e[] n = new com.corrodinggames.rts.gameFramework.m.e[10];


   public strictfp h(boolean var1) {
      super(var1);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:(this.dd()?j.dO[this.bX.R()]:n[this.bX.R()]);
   }

   public static strictfp void K() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      m = var0.bO.a(R$drawable.unit_icon_hover);
      n = com.corrodinggames.rts.game.n.a(m);
   }

   public strictfp ao h() {
      return ao.f;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         if(this.cK()) {
            if(this.cf > 0.0F) {
               this.l += var1;
            }

            if(this.l > 10.0F) {
               this.l = 0.0F;
               if(this.s_()) {
                  com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
                  float var3 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * 4.0F;
                  float var4 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * 4.0F;
                  com.corrodinggames.rts.gameFramework.d.e var5 = var2.bR.b(var3, var4, 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.b);
                  if(var5 != null) {
                     var5.aq = 0;
                     var5.ap = 13;
                     var5.ar = 1;
                     var5.r = true;
                     var5.E = 0.8F;
                     var5.W = 80.0F;
                     var5.V = 80.0F;
                     var5.P = -com.corrodinggames.rts.gameFramework.f.k(this.cg) * 0.1F;
                     var5.Q = -com.corrodinggames.rts.gameFramework.f.j(this.cg) * 0.1F;
                     var5.Y = com.corrodinggames.rts.gameFramework.f.c(-180.0F, 180.0F);
                  }
               }
            }
         }

      }
   }

}
