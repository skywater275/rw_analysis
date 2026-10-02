package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.ah;

final class ag$1 implements Runnable {

   // $FF: synthetic field
   final String a;
   // $FF: synthetic field
   final ah b;


   ag$1(String var1, ah var2) {
      this.a = var1;
      this.b = var2;
   }

   public void run() {
      try {
         Thread.sleep(1500L);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      com.corrodinggames.rts.gameFramework.l.e("Running delayed close of zip: " + this.a);
      this.b.a();
   }
}
