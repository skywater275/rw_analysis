package com.corrodinggames.rts.game.units.h;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.w;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public abstract class f extends w {

   float m;
   float n;
   boolean o = false;
   public static com.corrodinggames.rts.gameFramework.m.e p = null;
   public static com.corrodinggames.rts.gameFramework.m.e[] q = new com.corrodinggames.rts.gameFramework.m.e[10];


   public strictfp f(boolean var1) {
      super(var1);
   }

   public strictfp void a(as var1) {
      var1.a(this.n);
      var1.a(this.o);
      super.a(var1);
   }

   public strictfp void a(k var1) {
      this.n = var1.g();
      this.o = var1.e();
      super.a(var1);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:q[this.bX.R()];
   }

   public static strictfp void M() {
      l var0 = l.B();
      p = var0.bO.a(R$drawable.unit_icon_water);
      q = n.a(p);
   }

   public strictfp ao h() {
      return ao.e;
   }

   public strictfp boolean cv() {
      return true;
   }

   public strictfp boolean K() {
      return true;
   }

   public strictfp void s(float var1) {
      float var2 = 0.0F;
      if(this.eq != var2) {
         this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, var2, 0.2F * var1);
      }

   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bV) {
         if(this.eq > -10.0F) {
            this.n += 0.002F * var1;
            this.eq -= this.n * var1;
         } else {
            this.eq = -10.0F;
            if(!this.o) {
               this.o = true;
            }
         }

      } else if(this.bT() && !this.bV) {
         this.s(var1);
         if(this.K()) {
            if(this.cf != 0.0F) {
               this.m += var1;
            }

            if(this.m > 10.0F) {
               this.m = 0.0F;
               if(this.s_()) {
                  l var2 = l.B();
                  float var3 = this.cg + 180.0F;
                  if(this.cf < 0.0F) {
                     var3 += 180.0F;
                  }

                  float var4 = this.cj - 6.0F;
                  if(var4 < 4.0F) {
                     var4 = 4.0F;
                  }

                  float var5 = this.eo + com.corrodinggames.rts.gameFramework.f.k(var3) * var4;
                  float var6 = this.ep + com.corrodinggames.rts.gameFramework.f.j(var3) * var4;
                  var2.bR.b(var5, var6, 0.0F, var3);
               }
            }
         }

      }
   }

}
