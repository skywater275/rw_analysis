package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public abstract class LogicBooleanLoader$LogicBooleanScopeOnly extends LogicBoolean implements LogicBooleanLoader$LogicBooleanContext {

   public LogicBooleanLoader$LogicBooleanContext createContext() {
      return this;
   }

   public LogicBoolean setChild(LogicBoolean var1) {
      return var1;
   }

   public boolean read(y var1) {
      return false;
   }

   public LogicBoolean$ReturnType getReturnType() {
      return LogicBoolean$ReturnType.voidReturn;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return "<scope>";
   }
}
