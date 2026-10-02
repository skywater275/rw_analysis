package com.corrodinggames.rts.gameFramework.b;

import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.b.b;

public class ag {

   public static void a(b var0, RectF var1) {
      byte var2 = 0;
      byte var3 = 0;
      int var4 = var0.b();
      int var5 = var0.c();
      if(var0.f()) {
         var2 = 1;
         var3 = 1;
         --var4;
         --var5;
      }

      var1.a((float)var2, (float)var3, (float)var4, (float)var5);
   }
}
