package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class j extends a {

   LogicBoolean a;
   float b;
   float c;
   com.corrodinggames.rts.game.units.custom.h d;
   boolean e;


   public static strictfp void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1) {
      String var2 = "movement_repelFromUnits";
      if(var1.g(var2)) {
         j var3 = new j();
         var3.a(var0, var1, var2, var2);
         if(!LogicBoolean.isStaticFalse(var3.a)) {
            var0.a((a)var3);
         }
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.l var1, ab var2, String var3, String var4) {
      this.a = var2.a(var1, var3, "enabled");
      this.b = var2.i(var3, "speed");
      this.c = var2.a(var3, "maxSpeed", Float.valueOf(5.0F)).floatValue();
      this.d = com.corrodinggames.rts.game.units.custom.g.a(var2.b(var3, "otherUnitHasTag", (String)null), (com.corrodinggames.rts.game.units.custom.h)null);
      this.e = var2.a(var3, "onlySameTeam", Boolean.valueOf(false)).booleanValue();
   }

   public strictfp void b(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      if(this.a.read(var1)) {
         ;
      }
   }
}
