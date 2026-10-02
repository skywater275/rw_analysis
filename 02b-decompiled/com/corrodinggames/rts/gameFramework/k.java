package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.j;

class k extends Thread {

   boolean a = true;


   public void run() {
      for(; this.a; j.b()) {
         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var2) {
            ;
         }
      }

   }
}
