package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.am;

public class LogicNumberFunction$FunctionRnd extends LogicNumberFunction {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 0,
      required = true
   )
   public LogicBoolean min;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 1,
      required = true
   )
   public LogicBoolean max;
   int randomIndex;


   public void forMeta(l var1) {
      if(var1 == null) {
         throw new am("FunctionRnd requires metadata");
      } else {
         ++var1.S;
         this.randomIndex = var1.S;
      }
   }

   public String getName() {
      return "Rnd";
   }

   public float readNumber(y var1) {
      float var2 = this.min.readNumber(var1);
      float var3 = this.max.readNumber(var1);
      int var4 = 0;
      if(var1 != null) {
         var4 = var1.bC;
      }

      return f.b(var2, var3, this.randomIndex + var4);
   }
}
