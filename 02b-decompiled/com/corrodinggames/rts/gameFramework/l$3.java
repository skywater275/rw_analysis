package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.l;

class l$3 extends Thread {

   // $FF: synthetic field
   final l a;


   strictfp l$3(l var1) {
      this.a = var1;
   }

   public strictfp void run() {
      try {
         sleep(3000L);
      } catch (InterruptedException var2) {
         ;
      }

      this.a.ab();
   }
}
