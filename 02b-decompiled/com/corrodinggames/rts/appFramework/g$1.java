package com.corrodinggames.rts.appFramework;

import com.corrodinggames.rts.appFramework.g;

class g$1 implements Runnable {

   // $FF: synthetic field
   final int a;
   // $FF: synthetic field
   final g b;


   g$1(g var1, int var2) {
      this.b = var1;
      this.a = var2;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l.e("inner selectMenuOption: " + this.a);
      this.b.d(this.a);
   }
}
