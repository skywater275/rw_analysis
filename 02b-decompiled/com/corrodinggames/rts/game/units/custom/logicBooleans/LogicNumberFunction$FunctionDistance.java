package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.gameFramework.f;

public class LogicNumberFunction$FunctionDistance extends LogicNumberFunction {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 0,
      required = true
   )
   public LogicBoolean x1;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 1,
      required = true
   )
   public LogicBoolean y1;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 2,
      required = true
   )
   public LogicBoolean x2;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 3,
      required = true
   )
   public LogicBoolean y2;


   public String getName() {
      return "Distance";
   }

   public float readNumber(y var1) {
      float var2 = this.x1.readNumber(var1);
      float var3 = this.y1.readNumber(var1);
      float var4 = this.x2.readNumber(var1);
      float var5 = this.y2.readNumber(var1);
      return f.b(var2, var3, var4, var5);
   }
}
