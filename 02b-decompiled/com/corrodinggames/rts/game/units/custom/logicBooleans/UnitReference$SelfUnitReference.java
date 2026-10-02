package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$SelfUnitReference extends UnitReference {

   public am getSingleRaw(y var1) {
      return var1;
   }

   public String getClassDebugName() {
      return "self";
   }
}
