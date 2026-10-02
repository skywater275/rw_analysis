package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;

public class s extends com.corrodinggames.rts.game.units.f.i {

   public float a;
   public boolean b;
   public boolean c;
   com.corrodinggames.rts.game.n d;
   am e;
   float f;
   float g;
   boolean h;


   strictfp s(boolean var1) {
      this.b = var1;
   }

   public strictfp int excludeTeam(y var1) {
      return -2;
   }

   public strictfp com.corrodinggames.rts.game.n onlyEnemiesOfTeam(y var1) {
      return null;
   }

   public strictfp void a(float var1, boolean var2) {
      this.a = var1 * var1;
      this.h = var2;
      this.c = true;
   }

   public strictfp void setup(y var1, float var2) {
      this.e = null;
      this.f = -1.0F;
      this.g = -1.0F;
      this.d = var1.bX;
      if(!this.c) {
         throw new RuntimeException("AutoRepairCallback not ready");
      } else {
         this.c = false;
      }
   }

   public strictfp void callback(y var1, float var2, am var3) {
      if(var1 != var3) {
         if((var3.cu < var3.cv || var3.cm < 1.0F) && !var3.bV && var3.cN == null && this.d.d(var3.bX) && var1.a(var3)) {
            float var4 = com.corrodinggames.rts.gameFramework.f.a(var1.eo, var1.ep, var3.eo, var3.ep);
            if(var4 < this.a) {
               if(var3.cm < 1.0F) {
                  com.corrodinggames.rts.game.units.custom.d.b var5 = var1.g(var3);
                  if(var5 != null) {
                     return;
                  }
               }

               boolean var6 = false;
               if(!this.h) {
                  if(this.f == -1.0F || this.f > var3.cu) {
                     var6 = true;
                  }
               } else if(this.g == -1.0F || this.g > var4) {
                  var6 = true;
               }

               if(var6 && var3.g() == 0.0F) {
                  this.f = var3.cu;
                  this.g = var4;
                  this.e = var3;
               }
            }
         }

      }
   }
}
