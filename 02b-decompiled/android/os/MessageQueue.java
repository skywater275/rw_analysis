package android.os;

import android.os.Binder;
import android.os.Message;
import android.os.MessageQueue$IdleHandler;
import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;

public final class MessageQueue {

   private final boolean c;
   private long d;
   Message a;
   private final ArrayList e = new ArrayList();
   private MessageQueue$IdleHandler[] f;
   private boolean g;
   private boolean h;
   static Object b = new Object();


   private long b() {
      return 100L;
   }

   private void a(long var1) {}

   private void a(long var1, int var3) {
      Object var4 = b;
      synchronized(b) {
         try {
            b.wait((long)var3);
         } catch (InterruptedException var7) {
            var7.printStackTrace();
         }

      }
   }

   private void b(long var1) {
      Object var3 = b;
      synchronized(b) {
         b.notifyAll();
      }
   }

   MessageQueue(boolean var1) {
      this.c = var1;
      this.d = this.b();
   }

   protected void finalize() {
      try {
         this.c();
      } finally {
         super.finalize();
      }

   }

   private void c() {
      if(this.d != 0L) {
         this.a(this.d);
         this.d = 0L;
      }

   }

   Message a() {
      long var1 = this.d;
      if(var1 == 0L) {
         return null;
      } else {
         int var3 = -1;
         int var4 = 0;

         while(true) {
            if(var4 != 0) {
               Binder.flushPendingCommands();
            }

            System.out.println("corroding: nativePollOnce:" + var1 + "," + var4);
            this.a(var1, var4);
            synchronized(this) {
               long var6 = SystemClock.a();
               Message var8 = null;
               Message var9 = this.a;
               if(var9 != null && var9.j == null) {
                  do {
                     var8 = var9;
                     var9 = var9.l;
                  } while(var9 != null && !var9.e());
               }

               if(var9 != null) {
                  if(var6 >= var9.h) {
                     this.h = false;
                     if(var8 != null) {
                        var8.l = var9.l;
                     } else {
                        this.a = var9.l;
                     }

                     var9.l = null;
                     return var9;
                  }

                  var4 = (int)Math.min(var9.h - var6, 2147483647L);
               } else {
                  var4 = -1;
               }

               if(this.g) {
                  this.c();
                  return null;
               }

               if(var3 < 0 && (this.a == null || var6 < this.a.h)) {
                  var3 = this.e.size();
               }

               if(var3 <= 0) {
                  this.h = true;
                  continue;
               }

               if(this.f == null) {
                  this.f = new MessageQueue$IdleHandler[Math.max(var3, 4)];
               }

               this.f = (MessageQueue$IdleHandler[])this.e.toArray(this.f);
            }

            for(int var5 = 0; var5 < var3; ++var5) {
               MessageQueue$IdleHandler var15 = this.f[var5];
               this.f[var5] = null;
               boolean var7 = false;

               try {
                  var7 = var15.a();
               } catch (Throwable var13) {
                  Log.c("MessageQueue", "IdleHandler threw exception", var13);
               }

               if(!var7) {
                  synchronized(this) {
                     this.e.remove(var15);
                  }
               }
            }

            var3 = 0;
            var4 = 0;
         }
      }
   }

   boolean a(Message var1, long var2) {
      if(var1.j == null) {
         throw new IllegalArgumentException("Message must have a target.");
      } else if(var1.f()) {
         throw new IllegalStateException(var1 + " This message is already in use.");
      } else {
         synchronized(this) {
            if(this.g) {
               IllegalStateException var10 = new IllegalStateException(var1.j + " sending message to a Handler on a dead thread");
               Log.a("MessageQueue", var10.getMessage(), var10);
               var1.b();
               return false;
            } else {
               var1.g();
               var1.h = var2;
               Message var5 = this.a;
               boolean var6;
               if(var5 != null && var2 != 0L && var2 >= var5.h) {
                  var6 = this.h && var5.j == null && var1.e();

                  while(true) {
                     Message var7 = var5;
                     var5 = var5.l;
                     if(var5 == null || var2 < var5.h) {
                        var1.l = var5;
                        var7.l = var1;
                        break;
                     }

                     if(var6 && var5.e()) {
                        var6 = false;
                     }
                  }
               } else {
                  var1.l = var5;
                  this.a = var1;
                  var6 = this.h;
               }

               this.b(this.d);
               return true;
            }
         }
      }
   }

}
