package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import java.io.IOException;
import java.util.TimerTask;

class av extends TimerTask {

   private final ad c;
   public boolean a = true;
   public long b = 0L;


   av(ad var1) {
      this.c = var1;
   }

   public void run() {
      try {
         long var1 = System.currentTimeMillis();
         if(this.c.au != 0L && (var1 > this.c.au + 5L || var1 < this.c.au)) {
            this.c.au = 0L;
            this.c.Q();
         }

         if(var1 > this.b + 1000L || var1 < this.b) {
            this.b = var1;
            if(this.a) {
               as var3 = new as();
               var3.a(System.currentTimeMillis());
               var3.c(0);
               au var4 = var3.b(108);
               this.c.g(var4);
            } else {
               this.c.P();
            }

            this.a = !this.a;
         }
      } catch (IOException var5) {
         var5.printStackTrace();
      }

   }
}
