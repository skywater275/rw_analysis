package com.corrodinggames.rts.gameFramework;

import android.os.Process;
import com.corrodinggames.rts.gameFramework.l;

public class z extends Thread {

   static int a = 0;
   public boolean b = true;
   long c;


   public synchronized strictfp void a(boolean var1) {
      this.b = var1;
   }

   public strictfp z() {
      super("GameThread" + a);
      ++a;
   }

   public strictfp void run() {
      l.aq();
      if(!l.aU) {
         Process.setThreadPriority(-4);
      }

      float var1 = 1.0F;
      this.a();
      long var2 = this.c;
      l var4 = l.B();

      while(this.b) {
         long var5 = System.nanoTime();
         var2 = this.c;
         this.a();
         var1 = (float)(this.c - var2) * 0.060000002F;
         int var7 = (int)(this.c - var2);
         var4.a(var1, var7);
         if(!var4.bQ.batterySaving) {
            ;
         }

         long var8;
         if(var4.bQ.batterySaving) {
            var8 = 32258064L;
         } else if(var4.bQ.highRefreshRate) {
            var8 = 3333333L;
         } else {
            var8 = 16393442L;
         }

         long var10 = (long)Math.round((float)(var8 - (System.nanoTime() - var5)));
         if(var10 > 0L) {
            long var12 = System.nanoTime();

            while(true) {
               long var14 = System.nanoTime() - var12;
               if(var14 > var10 || var14 < 0L) {
                  break;
               }

               long var16 = (long)((double)(var10 - var14) / 1000000.0D);
               if(var16 <= 1L) {
                  break;
               }

               try {
                  Thread.sleep(var16);
               } catch (Exception var19) {
                  ;
               }
            }
         }
      }

   }

   public strictfp void a() {
      this.c = System.currentTimeMillis();
   }

}
