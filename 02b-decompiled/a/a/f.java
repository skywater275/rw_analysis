package a.a;

import a.a.b;
import a.a.b$1;
import a.a.e;
import a.a.h;
import a.a.s;

class f implements s {

   // $FF: synthetic field
   final b a;


   private f(b var1) {
      this.a = var1;
   }

   public void a(h var1) {
      if(var1 instanceof e) {
         synchronized(b.e(this.a)) {
            while(b.e(this.a).size() > 50) {
               try {
                  b.e(this.a).wait();
               } catch (InterruptedException var5) {
                  var5.printStackTrace();
               }
            }

            b.e(this.a).add((e)var1);
            b.e(this.a).notify();
         }
      }

   }

   public void b(h var1) {}

   public void c(h var1) {
      if(var1 instanceof e) {
         b.a(this.a, ((e)var1).c());
      }

   }

   public void d(h var1) {
      if(var1 instanceof e) {
         b.a(this.a, ((e)var1).c());
      }

   }

   public void e(h var1) {}

   // $FF: synthetic method
   f(b var1, b$1 var2) {
      this(var1);
   }
}
