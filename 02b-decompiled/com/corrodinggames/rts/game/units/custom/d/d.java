package com.corrodinggames.rts.game.units.custom.d;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;

class d {

   public final com.corrodinggames.rts.game.units.custom.e.a a;
   public double b;
   public LogicBoolean c;


   public d(com.corrodinggames.rts.game.units.custom.e.a var1, LogicBoolean var2) {
      this.a = var1;
      if(this.c instanceof LogicBoolean$StaticValueBoolean) {
         this.b = (double)((LogicBoolean$StaticValueBoolean)this.c).getStaticValue();
      } else {
         this.c = var2;
      }

   }
}
