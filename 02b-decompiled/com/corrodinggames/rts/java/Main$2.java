package com.corrodinggames.rts.java;

import android.os.Looper;
import com.corrodinggames.rts.java.Main;
import java.util.concurrent.Semaphore;

class Main$2 implements Runnable {

   // $FF: synthetic field
   final Semaphore a;
   // $FF: synthetic field
   final Main b;


   Main$2(Main var1, Semaphore var2) {
      this.b = var1;
      this.a = var2;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      Looper.a();
      this.a.release(1);
      Looper.c();
   }
}
