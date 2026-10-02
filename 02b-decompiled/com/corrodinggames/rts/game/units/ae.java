package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;

public class ae extends com.corrodinggames.rts.game.units.f.i {

   public int a;
   public float b;
   public boolean c;
   public boolean d;


   strictfp ae(boolean var1) {
      this.c = var1;
   }

   public strictfp int excludeTeam(y var1) {
      return -2;
   }

   public strictfp com.corrodinggames.rts.game.n onlyEnemiesOfTeam(y var1) {
      return var1.bX;
   }

   public strictfp void a(float var1) {
      this.b = var1 * var1 + 1.0F;
      this.d = true;
   }

   public strictfp void setup(y var1, float var2) {
      this.a = 0;
      if(!this.d) {
         throw new RuntimeException("PassiveTargetCallback not ready");
      } else {
         this.d = false;
      }
   }

   public strictfp void callback(y var1, float var2, am var3) {
      if(var1.b(var3, true)) {
         ++this.a;
         if(this.c) {
            if(!(var3 instanceof y)) {
               return;
            }

            y var4 = (y)var3;
            if(!var4.l() || !var4.k(var1)) {
               return;
            }
         }

         float var5 = com.corrodinggames.rts.gameFramework.f.a(var1.eo, var1.ep, var3.eo, var3.ep);
         if(var5 < this.b) {
            this.b = var5;
            var1.R = var3;
         }
      }

   }
}
