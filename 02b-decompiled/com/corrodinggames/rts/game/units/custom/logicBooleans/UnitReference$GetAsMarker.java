package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$PlaceholderUnitReference;

public class UnitReference$GetAsMarker extends UnitReference$PlaceholderUnitReference {

   public String getClassDebugName() {
      return "getAsMarker";
   }

   public am getSingleRaw(y var1) {
      y var2 = var1.bX.t;
      var2.cg = var1.cg;
      var2.eo = var1.eo;
      var2.ep = var1.ep;
      var2.eq = var1.eq;
      return var2;
   }
}
