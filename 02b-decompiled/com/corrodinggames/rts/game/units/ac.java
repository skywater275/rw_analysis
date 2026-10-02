package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;

public class ac extends com.corrodinggames.rts.game.units.f.i {

   public float a;
   public float b;
   public com.corrodinggames.rts.game.units.custom.h c;
   public float d;
   public am e;
   public boolean f;
   public boolean g = false;


   public strictfp void setup(y var1, float var2) {}

   public strictfp int excludeTeam(y var1) {
      return -2;
   }

   public strictfp com.corrodinggames.rts.game.n onlyEnemiesOfTeam(y var1) {
      return null;
   }

   public strictfp com.corrodinggames.rts.game.n onlyTeam(y var1) {
      return null;
   }

   public strictfp void callback(y var1, float var2, am var3) {
      if(!this.f || var3.g() > 0.0F) {
         float var4 = com.corrodinggames.rts.gameFramework.f.a(this.a, this.b, var3.eo, var3.ep);
         if(var4 < this.d) {
            if(var3.cm < 1.0F && !this.g) {
               return;
            }

            if(this.c != null && !com.corrodinggames.rts.game.units.custom.g.a(this.c, var3.de())) {
               return;
            }

            if(this.f && !var1.g(var3, true)) {
               return;
            }

            if(var3.cN != null) {
               return;
            }

            this.e = var3;
            this.d = var4;
         }

      }
   }
}
