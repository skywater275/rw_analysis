package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.i;

class i$1 implements Runnable {

   // $FF: synthetic field
   final String a;
   // $FF: synthetic field
   final i b;


   i$1(i var1, String var2) {
      this.b = var1;
      this.a = var2;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l.e("slick post-alert:" + this.a);
      this.b.a.p.b("", this.a);
   }
}
