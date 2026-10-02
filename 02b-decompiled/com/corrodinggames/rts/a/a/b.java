package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;

public class b extends l {

   public void a() {
      this.b();
   }

   public void b() {
      com.corrodinggames.rts.gameFramework.l.e("networkSocks");
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();

      for(int var2 = 0; var2 < 10000; ++var2) {
         var1.bX.b(false);

         try {
            Thread.sleep(50L);
         } catch (InterruptedException var5) {
            var5.printStackTrace();
         }

         var1.bX.b("test");
      }

      com.corrodinggames.rts.gameFramework.l.e("done");

      try {
         Thread.sleep(100000L);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

   }
}
