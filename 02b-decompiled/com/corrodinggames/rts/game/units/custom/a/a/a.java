package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class a extends com.corrodinggames.rts.game.units.custom.a.a {

   com.corrodinggames.rts.game.units.custom.e.a a;
   double b = -1.7976931348623157E308D;
   com.corrodinggames.rts.game.units.custom.e.a c;
   float d = 1.0F;
   com.corrodinggames.rts.game.units.custom.d.c e;
   com.corrodinggames.rts.game.units.custom.d.c f;


   public static void a(com.corrodinggames.rts.game.units.custom.l var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.a.d var4, String var5, boolean var6) {
      com.corrodinggames.rts.game.units.custom.e.a var7 = var1.a(var0, var2, var3 + "resourceAmount", (com.corrodinggames.rts.game.units.custom.e.a)null, true);
      if(var7 != null) {
         a var8 = new a();
         var8.a = var7;
         var8.b = var1.a(var2, var3 + "resourceAmount_setValue", -1.7976931348623157E308D);
         var8.c = var1.a(var0, var2, var3 + "resourceAmount_addOtherResource", (com.corrodinggames.rts.game.units.custom.e.a)null, true);
         var8.d = var1.a(var2, var3 + "resourceAmount_multiplyBy", Float.valueOf(1.0F)).floatValue();
         var4.ac.add(var8);
      }

      com.corrodinggames.rts.game.units.custom.d.c var11 = com.corrodinggames.rts.game.units.custom.d.c.a(var0, var1, var2, var3 + "addResourcesWithLogic", (com.corrodinggames.rts.game.units.custom.d.c)null);
      com.corrodinggames.rts.game.units.custom.d.c var9 = com.corrodinggames.rts.game.units.custom.d.c.a(var0, var1, var2, var3 + "setResourcesWithLogic", (com.corrodinggames.rts.game.units.custom.d.c)null);
      if(var11 != null || var9 != null) {
         a var10 = new a();
         var10.f = var9;
         var10.e = var11;
         var4.ac.add(var10);
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.custom.j var1, s var2, PointF var3, am var4, int var5) {
      if(this.a != null) {
         double var6;
         if(this.b != -1.7976931348623157E308D) {
            var6 = this.b;
         } else {
            var6 = this.a.a((am)var1);
         }

         if(this.c != null) {
            var6 += this.c.a((am)var1);
         }

         var6 *= (double)this.d;
         this.a.a(var1, var6);
      }

      if(this.f != null) {
         this.f.d(var1);
      }

      if(this.e != null) {
         this.e.e(var1);
      }

      return true;
   }
}
