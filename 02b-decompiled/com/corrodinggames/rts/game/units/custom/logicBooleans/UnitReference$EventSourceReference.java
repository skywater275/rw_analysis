package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.k;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class UnitReference$EventSourceReference extends UnitReference {

   public am getSingleRaw(y var1) {
      k var2 = LogicBoolean.activeEvent;
      return var2 == null?null:var2.c;
   }

   public String getClassDebugName() {
      return "EventSource";
   }
}
