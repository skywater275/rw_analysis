package a.a.a;


public class i implements Runnable {

   boolean a;
   String b;
   private Runnable c;
   private long d;
   private long e;
   private boolean f;
   private boolean g;
   private boolean h;
   private boolean i;
   private Object j = new Object();


   public i(String var1, Runnable var2) {
      this.b = var1;
      this.c = var2;
      this.d = 0L;
      this.e = 0L;
   }

   public void a() {
      this.a = true;
      Thread var1 = new Thread(this, this.b);
      var1.setDaemon(true);
      var1.start();
   }

   public void run() {
      label109:
      while(true) {
         if(!this.i) {
            label111: {
               synchronized(this) {
                  while(!this.g && !this.i) {
                     try {
                        this.wait();
                     } catch (InterruptedException var8) {
                        var8.printStackTrace();
                     }
                  }

                  if(this.i) {
                     break label111;
                  }
               }

               Object var1 = this.j;
               synchronized(this.j) {
                  this.h = false;
                  this.f = false;
                  if(this.d > 0L) {
                     try {
                        this.j.wait(this.d);
                     } catch (InterruptedException var7) {
                        var7.printStackTrace();
                     }
                  }

                  if(this.f) {
                     continue;
                  }
               }

               if(!this.h) {
                  this.c.run();
               }

               if(this.e <= 0L) {
                  continue;
               }

               while(true) {
                  var1 = this.j;
                  synchronized(this.j) {
                     this.h = false;

                     try {
                        this.j.wait(this.e);
                     } catch (InterruptedException var6) {
                        var6.printStackTrace();
                     }

                     if(this.f) {
                        continue label109;
                     }

                     if(this.h) {
                        continue;
                     }
                  }

                  this.c.run();
               }
            }
         }

         if(this.i) {
            this.c = null;
         }

         return;
      }
   }

   public synchronized void a(long var1) {
      this.a(var1, 0L);
   }

   public synchronized void a(long var1, long var3) {
      this.d = var1;
      this.e = var3;
      if(this.g) {
         throw new IllegalStateException("already scheduled");
      } else {
         this.g = true;
         this.notify();
         Object var5 = this.j;
         synchronized(this.j) {
            this.j.notify();
         }
      }
   }

   public synchronized boolean b() {
      return this.g;
   }

   public synchronized boolean c() {
      return !this.b();
   }

   public synchronized void d() {
      Object var1 = this.j;
      synchronized(this.j) {
         this.h = true;
         this.j.notify();
      }
   }

   public synchronized void e() {
      this.g = false;
      Object var1 = this.j;
      synchronized(this.j) {
         this.f = true;
         this.j.notify();
      }
   }

   public synchronized void f() {
      this.e();
      this.i = true;
      this.notify();
   }
}
