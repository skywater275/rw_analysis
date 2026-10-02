package com.corrodinggames.rts.game.units.custom.b;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bn;
import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.d.p;

public class k extends a {

   public static final a a = new k();
   static final PointF b = new PointF();


   public strictfp void b(com.corrodinggames.rts.game.units.custom.j var1, float var2) {
      com.corrodinggames.rts.game.units.custom.l var3 = var1.x;
      int var4 = var1.bl();

      for(int var5 = 0; var5 < var4; ++var5) {
         bn var6 = var3.fQ[var5];
         if(var6.aj != null && var1.cB > 0.0F && !var1.v) {
            float var7 = var6.aj.floatValue();
            b.a(var1.E(var5));
            float var8 = var1.m();
            if(var6.ab < 99999.0F) {
               var8 = var6.ab;
            }

            if(p.a(var1, b.a, b.b, var1.eq, var8, var7)) {
               ;
            }

            if(var1.cB < 0.0F) {
               var1.cB = 0.0F;
               var1.v = true;
            }
         }

         if(var6.ak != null) {
            a(var1, var6);
         }
      }

   }

   public static strictfp void a(com.corrodinggames.rts.game.units.custom.j var0, bn var1) {
      if(var0.a(var1)) {
         float var2 = var1.al;
         float var3 = var1.am;
         float var4 = var1.an;
         com.corrodinggames.rts.game.f var5 = null;
         com.corrodinggames.rts.game.units.custom.h var6 = var1.ak;
         Object[] var7 = com.corrodinggames.rts.game.f.a.a();
         int var8 = 0;

         for(int var9 = com.corrodinggames.rts.game.f.a.a; var8 < var9; ++var8) {
            com.corrodinggames.rts.game.f var10 = (com.corrodinggames.rts.game.f)var7[var8];
            if(var10.aE != null && var10.eq > var4 && com.corrodinggames.rts.game.units.custom.g.a(var10.aE, var6)) {
               float var11 = com.corrodinggames.rts.gameFramework.f.a(var0.eo, var0.ep, var10.eo, var10.ep);
               if(var11 < var3 * var3) {
                  float var12 = com.corrodinggames.rts.gameFramework.f.a(var0.eo, var0.ep, var10.n, var10.o);
                  if((var12 < var2 * var2 || var2 < 0.0F) && (var10.j == null || !var10.j.bX.d(var0.bX) && var10.j.bX != var0.bX) && var10.h > 0.0F && !a(var10)) {
                     var5 = var10;
                  }
               }
            }
         }

         if(var5 != null) {
            var0.b(var1);
            com.corrodinggames.rts.game.f var13 = var0.a((am)null, var5.eo, var5.ep, var1.e, (bh)null, 0);
            var13.aC = true;
            var13.q = var5;
         }

      }
   }

   public static strictfp boolean a(com.corrodinggames.rts.game.f var0) {
      Object[] var1 = com.corrodinggames.rts.game.f.a.a();
      int var2 = 0;

      for(int var3 = com.corrodinggames.rts.game.f.a.a; var2 < var3; ++var2) {
         com.corrodinggames.rts.game.f var4 = (com.corrodinggames.rts.game.f)var1[var2];
         if(var4 != var0 && var4.q == var0) {
            return true;
         }
      }

      return false;
   }

}
