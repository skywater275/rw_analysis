package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class d extends com.corrodinggames.rts.game.units.custom.a.a {

   boolean a;
   boolean b;
   com.corrodinggames.rts.game.units.custom.o c;
   com.corrodinggames.rts.game.units.custom.o d;
   boolean e;
   int f = Integer.MIN_VALUE;
   int g = Integer.MIN_VALUE;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      boolean var7 = var1.a(var2, var3 + "finishPlayingLastAnimation", Boolean.valueOf(false)).booleanValue();
      boolean var8 = var1.a(var2, var3 + "stopLastAnimation", Boolean.valueOf(false)).booleanValue();
      com.corrodinggames.rts.game.units.custom.o var9 = var0.a(var1.b(var2, var3 + "playAnimation", (String)null), (com.corrodinggames.rts.game.units.custom.o)null);
      com.corrodinggames.rts.game.units.custom.o var10 = var0.a(var1.b(var2, var3 + "playAnimationIfNotPlaying", (String)null), (com.corrodinggames.rts.game.units.custom.o)null);
      if(var9 != null && var10 != null) {
         throw new RuntimeException("Cannot use playAnimation and playAnimationIfNotPlaying at same time");
      } else if(var8 && var7) {
         throw new RuntimeException("Cannot use stopLastAnimation and finishPlayingLastAnimation at same time");
      } else {
         if(var9 != null || var10 != null || var7 || var8) {
            d var11 = new d();
            var11.a = var7;
            var11.b = var8;
            var11.c = var9;
            var11.d = var10;
            var11.e = var1.a(var2, var3 + "playAnimation_lowPriority", Boolean.valueOf(false)).booleanValue();
            var4.ac.add(var11);
         }

      }
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      if(this.a) {
         var1.b.b();
      }

      if(this.b) {
         var1.b.a();
      }

      byte var6;
      if(this.c != null) {
         var6 = 15;
         if(this.e) {
            var6 = 4;
         }

         var1.b.a(this.c.b(), var6, true);
      }

      if(this.d != null) {
         var6 = 15;
         if(this.e) {
            var6 = 4;
         }

         com.corrodinggames.rts.game.units.custom.f var7 = this.d.b();
         if(!var1.b.a(var7)) {
            var1.b.a(this.d.b(), var6, true);
         }
      }

      return true;
   }
}
