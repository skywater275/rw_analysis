package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;

public class LogicNumberFunction$FunctionMax extends LogicNumberFunction {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 0,
      required = true
   )
   public LogicBoolean a;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 1,
      required = true
   )
   public LogicBoolean b;


   public String getName() {
      return "Max";
   }

   public float readNumber(y var1) {
      float var2 = this.a.readNumber(var1);
      float var3 = this.b.readNumber(var1);
      return var2 > var3?var2:var3;
   }
}
