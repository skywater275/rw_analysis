package com.corrodinggames.rts.game.units.custom.a.a;

import com.corrodinggames.rts.game.q;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;

public class c extends com.corrodinggames.rts.game.units.f.i {

   public boolean a;
   public com.corrodinggames.rts.game.units.custom.h b;
   public float c;
   public boolean d;
   public q e;
   public boolean f;
   public com.corrodinggames.rts.gameFramework.utility.m g = new com.corrodinggames.rts.gameFramework.utility.m();
   public am h;


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
      if(this.b == null || var4 != null && com.corrodinggames.rts.game.units.custom.g.a(this.b, var4)) {
         float var5 = com.corrodinggames.rts.gameFramework.f.a(var1.eo, var1.ep, var3.eo, var3.ep);
         if(var5 < this.c) {
            if(var3.cm < 1.0F && !this.d) {
               return;
            }

            if(this.e != null && !var1.bX.a(this.e, var3.bX)) {
               return;
            }

            if(this.a && !com.corrodinggames.rts.gameFramework.utility.y.b(var1, var3.eo, var3.ep)) {
               return;
            }

            if(!this.f) {
               this.h = var3;
               this.c = var5;
            } else {
               this.g.add(var3);
            }
         }
      }

   }
}
