package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.g;

class h implements Runnable {

   public String a;
   // $FF: synthetic field
   final g b;


   h(g var1) {
      this.b = var1;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.L();
      var1.ca.b(this.a, false);
      var1.J();
      if(this.b.e != null && this.b.e.isShowing()) {
         this.b.b(0);
      }

   }
}
