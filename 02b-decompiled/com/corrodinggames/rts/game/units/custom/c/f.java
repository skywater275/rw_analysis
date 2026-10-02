package com.corrodinggames.rts.game.units.custom.c;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.c.a;
import com.corrodinggames.rts.game.units.custom.c.c;
import com.corrodinggames.rts.game.units.custom.c.e;
import com.corrodinggames.rts.game.units.f.i;

public class f extends i {

   public c a;
   public a b;
   public am c;
   public float d;


   public strictfp void setup(y var1, float var2) {}

   public strictfp int excludeTeam(y var1) {
      return -3;
   }

   public strictfp n onlyEnemiesOfTeam(y var1) {
      return null;
   }

   public strictfp n onlyTeam(y var1) {
      return var1.bX;
   }

   public strictfp void callback(y var1, float var2, am var3) {
      if(var1 != var3) {
         h var4 = var3.dh();
         if(var4 != null && g.a(this.b.a, var4)) {
            if(var1.bX != var3.bX) {
               if(var1.bX.d(var3.bX)) {
                  if(!this.b.b) {
                     return;
                  }
               } else {
                  if(!var1.bX.c(var3.bX)) {
                     return;
                  }

                  if(!this.b.c) {
                     return;
                  }
               }
            }

            float var5 = com.corrodinggames.rts.gameFramework.f.a(var1.eo, var1.ep, var3.eo, var3.ep);
            if(var5 < this.d) {
               e var6 = this.a.a(this.b, false);
               if(var6 == null || var6.a(var3) == null) {
                  this.c = var3;
                  this.d = var5;
               }
            }
         }

      }
   }
}
