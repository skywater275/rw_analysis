package com.corrodinggames.rts.game.units.b;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.w;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public abstract class b extends w {

   float h;
   boolean i = false;
   float j;
   Boolean k;
   Boolean l;
   public static com.corrodinggames.rts.gameFramework.m.e m = null;
   public static com.corrodinggames.rts.gameFramework.m.e[] n = new com.corrodinggames.rts.gameFramework.m.e[10];


   public strictfp b(boolean var1) {
      super(var1);
   }

   public strictfp void a(as var1) {
      var1.a(this.h);
      var1.a(this.i);
      super.a(var1);
   }

   public strictfp void a(k var1) {
      this.h = var1.g();
      this.i = var1.e();
      super.a(var1);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:n[this.bX.R()];
   }

   public static strictfp void K() {
      l var0 = l.B();
      m = var0.bO.a(R$drawable.unit_icon_air);
      n = n.a(m);
   }

   public strictfp ao h() {
      return ao.d;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bV) {
         if(this.eq > 0.0F) {
            this.h += 0.06F * var1;
            this.eq -= this.h * var1;
         } else {
            if(this.k == null) {
               this.k = Boolean.valueOf(this.cK());
            }

            if(this.l == null) {
               this.l = Boolean.valueOf(this.cJ());
            }

            if(!this.i) {
               this.i = true;
               if(this.k.booleanValue()) {
                  this.a(ab.a);
                  if(this.l.booleanValue()) {
                     l.B().bR.a(this.eo, this.ep, 0.0F, 0, 0.0F, 0.0F, this.cg);
                  }
               } else {
                  this.a(ab.b);
               }

               this.h = 0.0F;
            } else if(this.k.booleanValue()) {
               if(this.eq > -10.0F) {
                  this.h += 8.0E-4F * var1;
                  this.eq -= this.h * var1;
                  if(this.l.booleanValue()) {
                     this.j += var1;
                     if(this.j > 30.0F) {
                        this.j = 0.0F;
                        if(this.s_()) {
                           l var2 = l.B();
                           com.corrodinggames.rts.gameFramework.d.e var3 = var2.bR.b(this.eo, this.ep, this.eq, this.cg);
                           if(var3 != null) {
                              var3.P = 0.0F;
                              var3.Q = -0.1F;
                           }
                        }
                     }
                  }
               }
            } else {
               this.eq = 0.0F;
            }
         }

      }
   }

   public strictfp boolean e() {
      l var1 = l.B();
      if(this.eq > -1.0F) {
         for(int var2 = 0; var2 < 3; ++var2) {
            var1.bR.e(this.eo, this.ep, this.eq);
         }
      }

      return super.e();
   }

}
