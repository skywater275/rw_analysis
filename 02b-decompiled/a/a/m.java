package a.a;

import a.a.h;
import java.io.IOException;

class m extends Thread {

   // $FF: synthetic field
   final h a;


   public m(h var1) {
      super("ReliableSocket");
      this.a = var1;
      this.setDaemon(true);
   }

   public void run() {
      while(true) {
         try {
            a.a.a.h var1;
            if((var1 = h.f(this.a)) != null) {
               if(var1 instanceof a.a.a.g) {
                  this.a.a((a.a.a.g)var1);
               } else if(var1 instanceof a.a.a.c) {
                  h.a(this.a, (a.a.a.c)var1);
               } else if(!(var1 instanceof a.a.a.a)) {
                  h.a(this.a, var1);
               }

               this.a.c(var1);
               continue;
            }
         } catch (IOException var3) {
            var3.printStackTrace();
         }

         return;
      }
   }
}
