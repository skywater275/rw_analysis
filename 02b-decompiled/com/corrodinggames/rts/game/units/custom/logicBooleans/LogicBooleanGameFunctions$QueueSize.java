package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;

public final class LogicBooleanGameFunctions$QueueSize extends LogicBoolean$AbstractNumberBoolean {

   public g _withActionTag;


   @LogicBoolean$Parameter
   public void withActionTag(String var1) {
      this._withActionTag = g.c(var1);
   }

   public String getName() {
      return "QueueSize";
   }

   public float getValue(y var1) {
      return (float)var1.a(this._withActionTag);
   }

   public float getMaxValue(y var1) {
      return 2.14748365E9F;
   }
}
