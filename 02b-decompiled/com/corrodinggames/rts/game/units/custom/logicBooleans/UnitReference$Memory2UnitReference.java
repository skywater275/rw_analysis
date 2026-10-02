package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$Memory2UnitReference extends UnitReference {

   public am getSingleRaw(y var1) {
      am var2 = var1.bv;
      return var2 != null && !var2.bV?var2:null;
   }

   public String getClassDebugName() {
      return "customTarget2";
   }
}
