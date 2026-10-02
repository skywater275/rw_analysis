package a.a;

import a.a.h;
import a.a.h$1;
import java.io.IOException;
import java.util.Iterator;

class n implements Runnable {

   // $FF: synthetic field
   final h a;


   private n(h var1) {
      this.a = var1;
   }

   public void run() {
      synchronized(h.g(this.a)) {
         Iterator var2 = h.g(this.a).iterator();

         while(var2.hasNext()) {
            a.a.a.h var3 = (a.a.a.h)var2.next();

            try {
               h.c(this.a, var3);
            } catch (IOException var6) {
               var6.printStackTrace();
            }
         }

      }
   }

   // $FF: synthetic method
   n(h var1, h$1 var2) {
      this(var1);
   }
}
