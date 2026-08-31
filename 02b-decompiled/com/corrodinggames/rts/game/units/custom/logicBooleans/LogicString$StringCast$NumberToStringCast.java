package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;
import com.corrodinggames.rts.gameFramework.f;

public class LogicString$StringCast$NumberToStringCast extends LogicString$StringCast {

   public String readString(y var1) {
      float var2 = this.child.readNumber(var1);
      return f.a(var2, 2);
   }
}
