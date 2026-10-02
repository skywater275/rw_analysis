package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$LastDamagedByUnitReference extends UnitReference {

   public am getSingleRaw(y var1) {
      am var2 = var1.bt;
      return var2 != null && !var2.bV?var2:null;
   }

   public String getClassDebugName() {
      return "lastDamagedBy";
   }
}
