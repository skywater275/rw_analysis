package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.gameFramework.l;

public class LogicBooleanGameFunctions$GameMapHeightBoolean extends LogicNumberFunction {

   public String getName() {
      return "game.mapHeight";
   }

   public float readNumber(y var1) {
      l var2 = l.B();
      return var2.bL.j();
   }
}
