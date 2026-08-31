package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$PlaceholderUnitReference;
import com.corrodinggames.rts.gameFramework.f;

public class UnitReference$GetOffsetRelative extends UnitReference$PlaceholderUnitReference {

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
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number
   )
   public LogicBoolean dirOffset;


   public UnitReference$GetOffsetRelative() {
      this.x = LogicBoolean$StaticValueBoolean.static_0;
      this.y = LogicBoolean$StaticValueBoolean.static_0;
      this.height = LogicBoolean$StaticValueBoolean.static_0;
      this.dirOffset = LogicBoolean$StaticValueBoolean.static_0;
   }

   public String getClassDebugName() {
      return "getOffsetRelative";
   }

   public LogicBoolean validateAndOptimize(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      return super.validateAndOptimize(var1, var2, var3, var4, var5);
   }

   public am getSingleRaw(y var1) {
      y var2 = var1.bX.t;
      y var3 = getParameterContext(var1);
      float var4 = var1.cg + this.dirOffset.readNumber(var3);
      float var5 = f.k(var4);
      float var6 = f.j(var4);
      float var7 = this.x.readNumber(var3);
      float var8 = this.y.readNumber(var3);
      float var9 = var5 * var8 - var6 * var7;
      float var10 = var6 * var8 + var5 * var7;
      var2.cg = var4;
      var2.eo = var1.eo + var9;
      var2.ep = var1.ep + var10;
      var2.eq = var1.eq + this.height.readNumber(var3);
      return var2;
   }
}
