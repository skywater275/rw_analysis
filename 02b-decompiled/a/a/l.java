package a.a;

import a.a.h;
import a.a.h$1;
import java.io.IOException;

class l implements Runnable {

   // $FF: synthetic field
   final h a;


   private l(h var1) {
      this.a = var1;
   }

   public void run() {
      synchronized(h.g(this.a)) {
         if(h.g(this.a).isEmpty()) {
            try {
               h.b(this.a, new a.a.a.e(h.h(this.a).a()));
            } catch (IOException var4) {
               if(h.h()) {
                  var4.printStackTrace();
               }
            }
         }

      }
   }

   // $FF: synthetic method
   l(h var1, h$1 var2) {
      this(var1);
   }
}
