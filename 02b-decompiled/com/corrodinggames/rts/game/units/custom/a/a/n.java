package com.corrodinggames.rts.game.units.custom.a.a;

import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;

public class n extends com.corrodinggames.rts.game.units.f.i {

   public com.corrodinggames.rts.game.units.custom.h a;
   public float b;
   public boolean c;
   public q d;
   public com.corrodinggames.rts.gameFramework.utility.m e;


   public void setup(y var1, float var2) {}

   public int excludeTeam(y var1) {
      return -2;
   }

   public com.corrodinggames.rts.game.n onlyEnemiesOfTeam(y var1) {
      return null;
   }

   public com.corrodinggames.rts.game.n onlyTeam(y var1) {
      return null;
   }

   public void callback(y var1, float var2, am var3) {
      com.corrodinggames.rts.game.units.custom.h var4 = var3.de();
      if(this.a == null || var4 != null && com.corrodinggames.rts.game.units.custom.g.a(this.a, var4)) {
         float var5 = com.corrodinggames.rts.gameFramework.f.a(var1.eo, var1.ep, var3.eo, var3.ep);
         if(var5 < this.b) {
            if(var3.cm < 1.0F && !this.c) {
               return;
            }

            if(this.d != null && !var1.bX.a(this.d, var3.bX)) {
               return;
            }

            this.e.add(var3);
         }
      }

   }
}
