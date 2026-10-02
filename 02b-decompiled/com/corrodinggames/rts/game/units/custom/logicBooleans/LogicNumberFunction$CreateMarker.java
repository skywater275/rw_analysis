package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;

public class LogicNumberFunction$CreateMarker extends UnitReference {

   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 0,
      required = true
   )
   public LogicBoolean x;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 1,
      required = true
   )
   public LogicBoolean y;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number,
      positional = 2,
      required = false
   )
   public LogicBoolean height;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number
   )
   public LogicBoolean teamId;
   @LogicBoolean$Parameter(
      type = LogicBoolean$ReturnType.number
   )
   public LogicBoolean dir;


   public void validate(String var1, String var2, String var3, LogicBooleanLoader$LogicBooleanContext var4, boolean var5) {
      super.validate(var1, var2, var3, var4, var5);
      if(this.height == null) {
         this.height = LogicBoolean$StaticValueBoolean.static_0;
      }

      if(this.dir == null) {
         this.dir = LogicBoolean$StaticValueBoolean.static_0;
      }

      if(this.teamId == null) {
         this.teamId = LogicBoolean$StaticValueBoolean.static_neg1;
      }

   }

   public am getSingleRaw(y var1) {
      float var2 = this.x.readNumber(var1);
      float var3 = this.y.readNumber(var1);
      float var4 = this.height.readNumber(var1);
      float var5 = this.dir.readNumber(var1);
      n var6 = n.k((int)this.teamId.readNumber(var1));
      if(var6 == null) {
         var6 = n.i;
      }

      y var7 = var6.t;
      var7.cg = var5;
      var7.eo = var2;
      var7.ep = var3;
      var7.eq = var4;
      return var7;
   }

   public String getClassDebugName() {
      return "createMarker";
   }
}
