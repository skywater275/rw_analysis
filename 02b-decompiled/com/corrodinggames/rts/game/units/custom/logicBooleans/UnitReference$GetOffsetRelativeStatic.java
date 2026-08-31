package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$PlaceholderUnitReference;
import com.corrodinggames.rts.gameFramework.f;

public class UnitReference$GetOffsetRelativeStatic extends UnitReference$PlaceholderUnitReference {

   @LogicBoolean$Parameter(
      positional = 0
   )
   public float x;
   @LogicBoolean$Parameter(
      positional = 1
   )
   public float y;
   @LogicBoolean$Parameter
   public float height;
   @LogicBoolean$Parameter
   public float dirOffset;


   public String getClassDebugName() {
      return "getOffsetRelativeStatic";
   }

   public am getSingleRaw(y var1) {
      y var2 = var1.bX.t;
      float var3 = var1.cg + this.dirOffset;
      float var4 = f.k(var3);
      float var5 = f.j(var3);
      float var6 = this.x;
      float var7 = this.y;
      float var8 = var4 * var7 - var5 * var6;
      float var9 = var5 * var7 + var4 * var6;
      var2.cg = var3;
      var2.eo = var1.eo + var8;
      var2.ep = var1.ep + var9;
      var2.eq = var1.eq + this.height;
      return var2;
   }
}
