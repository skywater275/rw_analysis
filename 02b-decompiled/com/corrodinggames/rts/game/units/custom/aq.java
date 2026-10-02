package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ak;
import com.corrodinggames.rts.game.units.custom.al;
import com.corrodinggames.rts.game.units.custom.am;
import com.corrodinggames.rts.game.units.custom.an;
import com.corrodinggames.rts.game.units.custom.ao;
import com.corrodinggames.rts.game.units.custom.ap;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;

public abstract class aq extends ak {

   LogicBoolean a;


   aq(LogicBoolean var1) {
      this.a = var1;
   }

   static aq a(LogicBoolean var0) {
      LogicBoolean$ReturnType var1 = var0.getReturnType();
      return (aq)(var1 == LogicBoolean$ReturnType.number?new an(var0):(var1 == LogicBoolean$ReturnType.string?new ao(var0):(var1 == LogicBoolean$ReturnType.unit?new ap(var0):(LogicBoolean$ReturnType.isArrayType(var1)?new al(var0):new am(var0)))));
   }
}
