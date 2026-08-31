package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$LogicNumberFunctionRawArgs;
import com.corrodinggames.rts.gameFramework.f;

public class LogicNumberFunction$FunctionSquareRoot extends LogicNumberFunction$LogicNumberFunctionRawArgs {

   public String getName() {
      return "SquareRoot";
   }

   public float readNumber(y var1) {
      float var2 = this.value.readNumber(var1);
      var2 = this.doFunction(var2);
      return var2;
   }

   public float doFunction(float var1) {
      return f.a(var1);
   }
}
