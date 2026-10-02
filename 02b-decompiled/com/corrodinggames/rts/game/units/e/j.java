package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.w;
import com.corrodinggames.rts.game.units.e.h;

public abstract class j extends w {

   float dK;
   public static com.corrodinggames.rts.gameFramework.m.e dL = null;
   public static com.corrodinggames.rts.gameFramework.m.e dM = null;
   public static com.corrodinggames.rts.gameFramework.m.e[] dN = new com.corrodinggames.rts.gameFramework.m.e[10];
   public static com.corrodinggames.rts.gameFramework.m.e[] dO = new com.corrodinggames.rts.gameFramework.m.e[10];


   public strictfp j(boolean var1) {
      super(var1);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:(this.dd()?dO[this.bX.R()]:dN[this.bX.R()]);
   }

   public static strictfp void dt() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      dL = var0.bO.a(R$drawable.unit_icon_land);
      if(dL == null) {
         throw new RuntimeException("IMAGE_ICON is null");
      } else {
         dN = com.corrodinggames.rts.game.n.a(dL);
         dM = var0.bO.a(R$drawable.unit_icon_land_exp);
         if(dM == null) {
            throw new RuntimeException("IMAGE_ICON_EXP is null");
         } else {
            dO = com.corrodinggames.rts.game.n.a(dM);
         }
      }
   }

   public strictfp void a(float var1) {
      super.a(var1);
      float var2;
      if(this.bV) {
         var2 = 0.0F;
         if(this.cK()) {
            var2 = -10.0F;
         }

         if(this.eq > var2) {
            if(this.eq > 0.0F && this.dK < 0.4F) {
               this.dK = 0.4F;
            }

            this.dK += 0.002F * var1;
            this.eq -= this.dK * var1;
            if(this.eq <= var2) {
               this.eq = var2;
            }
         }
      }

      if(this.bT() && !this.bV) {
         if(!(this instanceof h)) {
            var2 = 0.0F;
            if(this.eq < var2) {
               this.eq += 0.2F * var1;
               if(this.eq >= var2) {
                  this.eq = var2;
               }
            }

            if(this.eq > 0.0F) {
               this.dK += 0.03F * var1;
               if(this.eq < 0.0F) {
                  this.dK = com.corrodinggames.rts.gameFramework.f.b(this.dK, 0.2F);
               }

               this.eq -= this.dK * var1;
               if(this.eq <= 0.0F) {
                  if(this.eq < 0.0F) {
                     this.eq = 0.0F;
                  }

                  this.dK = 0.0F;
               }
            }
         }

      }
   }

   public strictfp ao h() {
      return ao.b;
   }

}
