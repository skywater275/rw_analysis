package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$JoinerBoolean;

public abstract class CompareJoinerBoolean extends LogicBoolean$JoinerBoolean {

   public boolean requireBooleanChildren() {
      return false;
   }
}
