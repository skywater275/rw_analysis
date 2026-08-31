package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.aq;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public class am extends aq {

   am(LogicBoolean var1) {
      super(var1);
   }

   String a(com.corrodinggames.rts.game.units.y var1) {
      return this.a.read(var1)?"true":"false";
   }
}
