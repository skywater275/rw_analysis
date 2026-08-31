package android.os;

import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;

public class Handler {

   final MessageQueue a;
   final Looper b;
   final Handler$Callback c;
   final boolean d;


   public void a(Message var1) {}

   public void b(Message var1) {
      if(var1.k != null) {
         d(var1);
      } else {
         if(this.c != null && this.c.a(var1)) {
            return;
         }

         this.a(var1);
      }

   }

   public Handler() {
      this((Handler$Callback)null, false);
   }

   public Handler(Looper var1) {
      this(var1, (Handler$Callback)null, false);
   }

   public Handler(Handler$Callback var1, boolean var2) {
      this.b = Looper.d();
      if(this.b == null) {
         throw new RuntimeException("Can\'t create handler inside thread that has not called Looper.prepare()");
      } else {
         this.a = this.b.b;
         this.c = var1;
         this.d = var2;
      }
   }

   public Handler(Looper var1, Handler$Callback var2, boolean var3) {
      this.b = var1;
      this.a = var1.b;
      this.c = var2;
      this.d = var3;
   }

   public final Message a() {
      return Message.a(this);
   }

   public final boolean a(Runnable var1) {
      return this.a(b(var1), 0L);
   }

   public final boolean c(Message var1) {
      return this.a(var1, 0L);
   }

   public final boolean a(Message var1, long var2) {
      if(var2 < 0L) {
         var2 = 0L;
      }

      return this.b(var1, SystemClock.a() + var2);
   }

   public boolean b(Message var1, long var2) {
      MessageQueue var4 = this.a;
      if(var4 == null) {
         RuntimeException var5 = new RuntimeException(this + " sendMessageAtTime() called with no mQueue");
         Log.a("Looper", var5.getMessage(), var5);
         return false;
      } else {
         return this.a(var4, var1, var2);
      }
   }

   private boolean a(MessageQueue var1, Message var2, long var3) {
      var2.j = this;
      if(this.d) {
         var2.a(true);
      }

      return var1.a(var2, var3);
   }

   public String toString() {
      return "Handler (" + this.getClass().getName() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
   }

   private static Message b(Runnable var0) {
      Message var1 = Message.a();
      var1.k = var0;
      return var1;
   }

   private static void d(Message var0) {
      var0.k.run();
   }
}
