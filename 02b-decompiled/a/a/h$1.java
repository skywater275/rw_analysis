package a.a;

import a.a.h;

class h$1 extends Thread {

   // $FF: synthetic field
   final h a;


   h$1(h var1) {
      this.a = var1;
   }

   public void run() {
      h.a(this.a).f();
      h.b(this.a).f();

      try {
         Thread.sleep((long)(this.a.g.g() * 2));
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      h.c(this.a).f();
      h.d(this.a).f();
      this.a.b();
      h.e(this.a);
   }
}
