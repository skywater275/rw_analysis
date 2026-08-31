package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;

public class LogicString$StaticString extends LogicString {

   String text;


   public LogicString$StaticString(String var1) {
      this.text = var1;
   }

   public String readString(y var1) {
      return this.text;
   }
}
