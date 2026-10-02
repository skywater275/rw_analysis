package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;

public class ah extends com.corrodinggames.rts.game.units.f.i {

   public int a;
   public float[] b = new float[31];
   public boolean[] c = new boolean[31];
   int d;
   public boolean e;
   public boolean f;


   strictfp ah(boolean var1) {
      this.e = var1;
   }

   public strictfp int excludeTeam(y var1) {
      return -2;
   }

   public strictfp com.corrodinggames.rts.game.n onlyEnemiesOfTeam(y var1) {
      return var1.bX;
   }

   public strictfp void a(y var1) {
      float var2 = var1.b(false);
      this.d = var1.bl();

      for(int var3 = 0; var3 < this.d; ++var3) {
         float var4 = var1.z(var3);
         if(var4 > var2) {
            var4 = var2;
         }

         this.b[var3] = var4 * var4 + 1.0F;
         this.c[var3] = false;
         if(var1.v(var3) == -1 && var1.cL[var3].j == null) {
            this.c[var3] = true;
         }
      }

      this.f = true;
   }

   public strictfp void setup(y var1, float var2) {
      this.a = 0;
      if(!this.f) {
         throw new RuntimeException("PassiveTargetCallback not ready");
      } else {
         this.f = false;
      }
   }

   public strictfp void callback(y var1, float var2, am var3) {
      if(var1.b(var3, true)) {
         ++this.a;
         if(this.e) {
            if(!(var3 instanceof y)) {
               return;
            }

            y var4 = (y)var3;
            if(!var4.l() || !var4.k(var1)) {
               return;
            }
         }

         float var8 = com.corrodinggames.rts.gameFramework.f.a(var1.eo, var1.ep, var3.eo, var3.ep);

         for(int var5 = 0; var5 < this.d; ++var5) {
            if(this.c[var5]) {
               boolean var6 = true;
               boolean var7 = false;
               if(var1.a(var5, var3, true, false) && var8 < this.b[var5] && var8 > var1.A(var5)) {
                  this.b[var5] = var8;
                  var1.cL[var5].j = var3;
               }
            }
         }
      }

   }
}
