package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$Operator;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;

public class av extends VariableScope$CachedWriter$WriterElement {

   public at a;
   public LogicBoolean b;
   public VariableScope$CachedWriter$Operator c;


   public void writeToUnit(com.corrodinggames.rts.game.units.y var1) {
      if(!(var1 instanceof j)) {
         com.corrodinggames.rts.gameFramework.l.n("Cannot change data on non custom unit:" + com.corrodinggames.rts.game.units.am.A(var1));
      } else {
         j var2 = (j)var1;
         this.a.a(var2, this.b, this.c);
      }
   }
}
