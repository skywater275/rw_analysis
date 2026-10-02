package com.corrodinggames.rts.game.units.f;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.f.j;

public abstract class i extends j {

   public abstract int excludeTeam(y var1);

   public abstract n onlyEnemiesOfTeam(y var1);

   public n onlyTeam(y var1) {
      return null;
   }

   public void setup(y var1, float var2) {}

   public am getResult() {
      return null;
   }
}
