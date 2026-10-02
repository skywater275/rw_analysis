package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayGet;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$UnitContextChangingBooleanByLogic;

public class LogicBooleanLoader$ArrayContextReader$ArrayGetUnit extends LogicBooleanLoader$ArrayContextReader$ArrayGet {

   public LogicBooleanLoader$LogicBooleanContext createContext() {
      return UnitReference.unitContextChangingContext;
   }

   public LogicBoolean setChild(LogicBoolean var1) {
      UnitReference$UnitContextChangingBooleanByLogic var2 = UnitReference$UnitContextChangingBooleanByLogic.create(this, var1);
      return var2;
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.unit;
   }
}
