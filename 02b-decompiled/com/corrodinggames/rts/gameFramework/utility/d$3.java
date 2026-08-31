package com.corrodinggames.rts.gameFramework.utility;

import com.corrodinggames.rts.gameFramework.utility.d;

class d$3 implements Runnable {

   // $FF: synthetic field
   final d a;


   d$3(d var1) {
      this.a = var1;
   }

   public void run() {
      d.a(this.a, (d.a(this.a) + 1) % Integer.MAX_VALUE);
   }
}
