package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.b;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public class LogicBooleanActionFilter extends b {

   LogicBoolean logicBoolean;
   j target;


   public LogicBooleanActionFilter(LogicBoolean var1, j var2) {
      this.logicBoolean = var1;
   }

   public boolean isAvailable(s var1, am var2) {
      return this.logicBoolean.read(this.target);
   }
}
