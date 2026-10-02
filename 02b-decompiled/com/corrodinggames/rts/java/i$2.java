package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.i;

class i$2 implements Runnable {

   // $FF: synthetic field
   final String a;
   // $FF: synthetic field
   final String b;
   // $FF: synthetic field
   final i c;


   i$2(i var1, String var2, String var3) {
      this.c = var1;
      this.a = var2;
      this.b = var3;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l.e("slick messageBox:" + this.a);
      this.c.a.p.b(this.b, this.a);
   }
}
