package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.ai;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;

public class aj extends com.corrodinggames.rts.game.units.f.i {

   float a;
   float b;
   public ai c;


   public int excludeTeam(y var1) {
      return -2;
   }

   public com.corrodinggames.rts.game.n onlyEnemiesOfTeam(y var1) {
      return null;
   }

   public void setup(y var1, float var2) {
      this.c = null;
   }

   public void a(float var1, float var2) {
      this.a = var1;
      this.b = var2;
   }

   public void callback(y var1, float var2, am var3) {
      if(var3 instanceof ai && !var3.bV && var3.c(this.a, this.b, 0.0F)) {
         this.c = (ai)var3;
      }

   }
}
