package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$LockedUnitReference extends UnitReference {

   am target;


   public UnitReference$LockedUnitReference(am var1) {
      this.target = var1;
   }

   public am getSingleRaw(y var1) {
      return this.target;
   }

   public String getClassDebugName() {
      return "unit";
   }
}
