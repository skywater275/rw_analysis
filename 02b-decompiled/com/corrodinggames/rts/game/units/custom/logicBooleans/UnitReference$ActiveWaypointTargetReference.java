package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$ActiveWaypointTargetReference extends UnitReference {

   public am getSingleRaw(y var1) {
      au var2 = var1.ar();
      if(var2 == null) {
         return null;
      } else {
         am var3 = var2.l();
         return var3;
      }
   }

   public String getClassDebugName() {
      return "ActiveWaypointTarget";
   }
}
