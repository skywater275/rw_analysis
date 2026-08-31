package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.bg;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;

public class bf extends com.corrodinggames.rts.game.units.f.i {

   public float a;
   public float b;
   public bg c;
   public int d;


   public void setup(com.corrodinggames.rts.game.units.y var1, float var2) {}

   public int excludeTeam(com.corrodinggames.rts.game.units.y var1) {
      return -2;
   }

   public com.corrodinggames.rts.game.n onlyEnemiesOfTeam(com.corrodinggames.rts.game.units.y var1) {
      return null;
   }

   public com.corrodinggames.rts.game.n onlyTeam(com.corrodinggames.rts.game.units.y var1) {
      return null;
   }

   public void callback(com.corrodinggames.rts.game.units.y var1, float var2, com.corrodinggames.rts.game.units.am var3) {
      if(var1 != var3) {
         h var4 = var3.de();
         h var5 = this.c.c;
         if(var5 == null || var4 != null && g.a(var5, var4)) {
            float var6 = com.corrodinggames.rts.gameFramework.f.a(this.a, this.b, var3.eo, var3.ep);
            if(var6 < this.c.f) {
               if(var3.cm < 1.0F && this.c.i) {
                  return;
               }

               if(this.c.j && !var3.bI()) {
                  return;
               }

               if(this.c.d != null && !var1.bX.a(this.c.d, var3.bX)) {
                  return;
               }

               ++this.d;
            }
         }

      }
   }
}
