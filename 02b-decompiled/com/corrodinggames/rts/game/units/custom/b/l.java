package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class l extends a {

   LogicBoolean a;
   float b;
   float c;
   int d;


   public static strictfp void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1) {
      String var2 = "movement_random";
      if(var1.g(var2)) {
         l var3 = new l();
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
      this.d = var2.b(var3, "awayFromEdge", Integer.valueOf(75)).intValue();
   }

   public strictfp void b(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      if(this.a.read(var1)) {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(var1.bi()) {
            if(com.corrodinggames.rts.gameFramework.f.c(var1.cc) < this.c) {
               var1.cc += com.corrodinggames.rts.gameFramework.f.b(var1, -this.b, this.b, 1);
            }

            if(com.corrodinggames.rts.gameFramework.f.c(var1.cd) < this.c) {
               var1.cd += com.corrodinggames.rts.gameFramework.f.b(var1, -this.b, this.b, 2);
            }
         } else {
            if(com.corrodinggames.rts.gameFramework.f.c(var1.cf) < this.c) {
               var1.cf += com.corrodinggames.rts.gameFramework.f.b(var1, -this.b, this.b, 1);
            }

            var1.cg += com.corrodinggames.rts.gameFramework.f.b(var1, -1.0F, 1.0F, 2);
         }

         if(this.d > 0) {
            if(var1.ep > var3.bL.j() - (float)this.d) {
               var1.cd -= com.corrodinggames.rts.gameFramework.f.b(var1, 0.0F, this.b * 0.25F, 10);
            }

            if(var1.ep < (float)this.d) {
               var1.cd += com.corrodinggames.rts.gameFramework.f.b(var1, 0.0F, this.b * 0.25F, 11);
            }

            if(var1.eo > var3.bL.i() - (float)this.d) {
               var1.cc -= com.corrodinggames.rts.gameFramework.f.b(var1, 0.0F, this.b * 0.25F, 12);
            }

            if(var1.eo < (float)this.d) {
               var1.cc += com.corrodinggames.rts.gameFramework.f.b(var1, 0.0F, this.b * 0.25F, 13);
            }
         }

         var1.ay = true;
      }
   }
}
