package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$PlaceholderUnitReference;

public class UnitReference$GetOffsetAbsolute extends UnitReference$PlaceholderUnitReference {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 0
   )
   public LogicBoolean x;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 1
   )
   public LogicBoolean y;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number
   )
   public LogicBoolean height;


   public UnitReference$GetOffsetAbsolute() {
      this.x = LogicBoolean$StaticValueBoolean.static_0;
      this.y = LogicBoolean$StaticValueBoolean.static_0;
      this.height = LogicBoolean$StaticValueBoolean.static_0;
   }

   public String getClassDebugName() {
      return "getOffsetAbsolute";
   }

   public am getSingleRaw(y var1) {
      y var2 = var1.bX.t;
      y var3 = getParameterContext(var1);
      var2.cg = var1.cg;
      var2.eo = var1.eo + this.x.readNumber(var3);
      var2.ep = var1.ep + this.y.readNumber(var3);
      var2.eq = var1.eq + this.height.readNumber(var3);
      return var2;
   }
}
