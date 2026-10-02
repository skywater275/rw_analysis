package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$ParentUnitReference extends UnitReference {

   public am getSingleRaw(y var1) {
      return var1.dr();
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "parent";
   }
}
