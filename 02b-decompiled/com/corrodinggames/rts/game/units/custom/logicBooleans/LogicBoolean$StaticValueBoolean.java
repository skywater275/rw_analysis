package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicNumberOnly;
import com.corrodinggames.rts.gameFramework.f;

public final class LogicBoolean$StaticValueBoolean extends LogicBoolean$LogicNumberOnly {

   public static final LogicBoolean$StaticValueBoolean static_0 = new LogicBoolean$StaticValueBoolean(0.0F);
   public static final LogicBoolean$StaticValueBoolean static_neg1 = new LogicBoolean$StaticValueBoolean(-1.0F);
   public static final LogicBoolean$StaticValueBoolean static_1 = new LogicBoolean$StaticValueBoolean(1.0F);
   float staticNumber;


   public static LogicBoolean$StaticValueBoolean getStaticNumber(String var0) {
      try {
         float var1 = Float.parseFloat(var0);
         return getStaticNumber(var1);
      } catch (NumberFormatException var2) {
         throw new RuntimeException("Error reading number: " + var0, var2);
      }
   }

   public static LogicBoolean$StaticValueBoolean getStaticNumber(float var0) {
      return var0 == 0.0F?static_0:(var0 == 1.0F?static_1:(var0 == -1.0F?static_neg1:new LogicBoolean$StaticValueBoolean(var0)));
   }

   LogicBoolean$StaticValueBoolean(float var1) {
      this.staticNumber = var1;
   }

   public String getName() {
      return "" + this.staticNumber;
   }

   public final float readNumber(y var1) {
      return this.staticNumber;
   }

   public float getStaticValue() {
      return this.staticNumber;
   }

   public String getMatchFailReasonForPlayer(y var1) {
      return f.a(this.staticNumber, 3);
   }

}
