package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$Operator;
import java.util.Locale;

public abstract class at {

   int a;
   String b;


   public at(int var1, String var2) {
      this.a = var1;
      this.b = var2.toLowerCase(Locale.ROOT);
   }

   public LogicBoolean$ReturnType a() {
      return LogicBoolean$ReturnType.number;
   }

   public abstract double a(j var1, as var2);

   public abstract void a(j var1, double var2);

   public final void a(j var1, LogicBoolean var2, VariableScope$CachedWriter$Operator var3) {
      var1.dJ();
      double var4 = (double)var2.readNumber(var1);
      if(var3 == VariableScope$CachedWriter$Operator.set) {
         this.a(var1, var4);
      } else {
         double var6;
         if(var3 == VariableScope$CachedWriter$Operator.add) {
            var6 = this.a(var1, var1.y);
            this.a(var1, var6 + var4);
         } else if(var3 == VariableScope$CachedWriter$Operator.subtract) {
            var6 = this.a(var1, var1.y);
            this.a(var1, var6 - var4);
         } else {
            com.corrodinggames.rts.gameFramework.l.n("setUnitDataFromLogic: unsupported operator");
         }
      }

   }

   public abstract boolean b();
}
