package com.corrodinggames.rts.game.units.g;

import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.g.a;
import com.corrodinggames.rts.game.units.g.b;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.io.IOException;

public final class c {

   public static void a(y var0, float var1) {
      m var2 = var0.bp;
      if(var2 != null) {
         l var3 = l.B();
         int var4 = var3.by;
         Object[] var5 = var2.a();

         for(int var6 = var2.a - 1; var6 >= 0; --var6) {
            a var7 = (a)var5[var6];
            if(var7.a <= var4) {
               var2.remove(var6);
            } else {
               var7.a(var0, var1);
            }
         }

      }
   }

   public static void a(y var0, a var1) {
      if(var0.bp == null) {
         var0.bp = new m();
      }

      if(var0.bp.size() > 1000) {
         var0.a("status effect limit reached");
      } else {
         var0.bp.add(var1);
      }
   }

   public static void a(y var0, as var1) {
      m var3 = var0.bp;
      int var2;
      if(var3 == null) {
         var2 = 0;
      } else {
         var2 = var3.size();
      }

      var1.a((short)var2);
      if(var2 != 0) {
         var1.e("s");
         Object[] var4 = var3.a();

         for(int var5 = 0; var5 < var3.a; ++var5) {
            a var6 = (a)var4[var5];
            var1.a((Enum)var6.b());
            var6.a(var0, var1);
         }

         var1.a("s");
      }
   }

   public static void a(y var0, k var1) {
      short var2 = var1.v();
      if(var2 <= 0) {
         var0.bp = null;
      } else {
         if(var0.bp == null) {
            var0.bp = new m();
         } else {
            var0.bp.clear();
         }

         m var3 = var0.bp;
         var1.b("s");

         for(int var4 = 0; var4 < var2; ++var4) {
            b var5 = (b)var1.b(b.class);
            if(var5 == null) {
               throw new IOException("Unknown status effect type");
            }

            a var6 = var5.a();
            var6.a(var0, var1);
            var3.add(var6);
         }

         var1.d("s");
      }
   }
}
