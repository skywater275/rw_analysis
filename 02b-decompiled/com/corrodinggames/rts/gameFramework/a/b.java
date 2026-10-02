package com.corrodinggames.rts.gameFramework.a;

import android.util.Log;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.a.a;
import com.corrodinggames.rts.gameFramework.a.c;
import com.corrodinggames.rts.gameFramework.a.h;
import com.corrodinggames.rts.gameFramework.a.i;

public class b extends i {

   a a;
   int b;
   // $FF: synthetic field
   final a c;


   public b(a var1, String var2, h var3) {
      super(var2, var3);
      this.c = var1;
      this.b = -1;
   }

   public void a(float var1, float var2, int var3, int var4, float var5) {
      c var6 = (c)this.c.c.a();
      if(var6 != null) {
         var6.b = var1;
         var6.c = var2;
         var6.e = var4;
         var6.f = var5;
         var6.a = this;
         boolean var7 = false;
         l var8 = l.B();
         if(var8 != null && var8.bQ != null && var8.bQ.androidNoSoundPrioritiesDebug) {
            var7 = true;
         }

         if(var7) {
            var6.d = 0;
         } else {
            --this.c.e;
            if(this.c.e <= 1) {
               this.c.e = 1000;
            }

            var6.d = this.c.e;
         }

         this.c.a.offer(var6);
      }
   }

   public void b(float var1, float var2, int var3, int var4, float var5) {
      if(this.b == 0) {
         Log.d("RustedWarfare", "Sound not loaded");
      } else {
         int var6 = this.a.g.play(this.b, var1, var2, var3, var4, var5);
         if(var6 == 0) {
            ;
         }

      }
   }

   public int a() {
      return 0;
   }
}
