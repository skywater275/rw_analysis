package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$AttackingReference extends UnitReference {

   public am getSingleRaw(y var1) {
      am var2 = var1.R;
      return var2;
   }

   public String getClassDebugName() {
      return "Attacking";
   }
}
