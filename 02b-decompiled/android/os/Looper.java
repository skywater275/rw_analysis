package android.os;

import android.os.Message;
import android.os.MessageQueue;
import android.util.Printer;

public final class Looper {

   static final ThreadLocal a = new ThreadLocal();
   private static Looper d;
   final MessageQueue b;
   final Thread c;
   private Printer e;


   private static void a(boolean var0) {
      if(a.get() != null) {
         throw new RuntimeException("Only one Looper may be created per thread");
      } else {
         a.set(new Looper(var0));
      }
   }

   public static void a() {
      a(false);
      Class var0 = Looper.class;
      synchronized(Looper.class) {
         if(d != null) {
            throw new IllegalStateException("The main Looper has already been prepared.");
         } else {
            d = d();
         }
      }
   }

   public static Looper b() {
      Class var0 = Looper.class;
      synchronized(Looper.class) {
         return d;
      }
   }

   public static void c() {
      Looper var0 = d();
      if(var0 == null) {
         throw new RuntimeException("No Looper; Looper.prepare() wasn\'t called on this thread.");
      } else {
         MessageQueue var1 = var0.b;

         while(true) {
            Message var2 = var1.a();
            if(var2 == null) {
               return;
            }

            Printer var3 = var0.e;
            if(var3 != null) {
               var3.println(">>>>> Dispatching to " + var2.j + " " + var2.k + ": " + var2.a);
            }

            var2.j.b(var2);
            if(var3 != null) {
               var3.println("<<<<< Finished to " + var2.j + " " + var2.k);
            }

            var2.c();
         }
      }
   }

   public static Looper d() {
      return (Looper)a.get();
   }

   private Looper(boolean var1) {
      this.b = new MessageQueue(var1);
      this.c = Thread.currentThread();
   }

   public Thread e() {
      return this.c;
   }

   public String toString() {
      return "Looper (" + this.c.getName() + ", tid " + this.c.getId() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
   }

}
