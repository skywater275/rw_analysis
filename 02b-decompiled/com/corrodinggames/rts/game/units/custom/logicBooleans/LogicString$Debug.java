package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$WrappingLogicString;

public class LogicString$Debug extends LogicString$WrappingLogicString {

   public String readString(y var1) {
      return this.children[0].getMatchFailReasonForPlayer(var1);
   }
}
