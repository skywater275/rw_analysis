package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ao$1;
import com.corrodinggames.rts.gameFramework.j.ap;
import com.corrodinggames.rts.gameFramework.j.c;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ao implements Runnable {

   public final boolean a = false;
   public static boolean b = true;
   private final ad r;
   volatile boolean c = true;
   ServerSocket d;
   int e;
   boolean f;
   long g = -1L;
   final boolean h = false;
   final boolean i = true;
   final Object j = new Object();
   ArrayList k = new ArrayList();
   final Object l = new Object();
   int m = 0;
   int n = 0;
   boolean o;
   boolean p;
   boolean q;


   strictfp ao(ad var1) {
      this.r = var1;
   }

   public strictfp boolean a(InetAddress var1, boolean var2) {
      if(var1 == null) {
         com.corrodinggames.rts.gameFramework.l.e("isIpAllowed: inetAddress==null");
         return true;
      } else if(!b) {
         return true;
      } else {
         long var3 = System.currentTimeMillis();
         Object var5;
         if(var3 > this.g + 60000L) {
            this.g = var3;
            var5 = this.l;
            synchronized(this.l) {
               this.k.clear();
            }

            this.m = 0;
            this.n = 0;
            this.o = false;
            this.p = false;
            this.q = false;
         }

         var5 = this.l;
         Iterator var7;
         synchronized(this.l) {
            boolean var6 = false;
            var7 = this.k.iterator();

            while(var7.hasNext()) {
               ap var8 = (ap)var7.next();
               if(var1.equals(var8.a)) {
                  ++var8.b;
                  byte var9 = 30;
                  if(this.n > 100) {
                     var9 = 10;
                  }

                  if(this.n > 250) {
                     var9 = 5;
                  }

                  if(var8.b > var9) {
                     if(!var8.c) {
                        var8.c = true;
                        com.corrodinggames.rts.gameFramework.l.e("DOS: Too many attempts:" + var8.b + " ip:" + var1.toString());
                     }

                     if(var8.b > 300 && !var8.d) {
                        var8.d = true;
                        com.corrodinggames.rts.gameFramework.l.e("DOS: Excessive attempts:" + var8.b + " ip:" + var1.toString());
                     }

                     return false;
                  }

                  var6 = true;
                  break;
               }
            }

            if(!var6) {
               if(var2) {
                  ++this.m;
               }

               ap var15;
               if(this.k.size() > 200) {
                  var15 = null;
                  Iterator var16 = this.k.iterator();

                  while(var16.hasNext()) {
                     ap var19 = (ap)var16.next();
                     if(var15 == null || var15.b > var19.b) {
                        var15 = var19;
                     }
                  }

                  if(var15 != null) {
                     this.k.remove(var15);
                  }
               }

               var15 = new ap(this);
               var15.a = var1;
               this.k.add(var15);
            }
         }

         if(this.m > 500) {
            if(!this.p) {
               this.p = true;
               com.corrodinggames.rts.gameFramework.l.e("DOS: Too many unique attempts: " + this.m + ". udp:" + this.f);
            }

            return false;
         } else {
            int var13 = 0;
            int var14 = 0;
            var7 = this.r.aM.iterator();

            while(var7.hasNext()) {
               c var18 = (c)var7.next();
               ++var14;
               if(var18.e != null && var1.equals(var18.e)) {
                  ++var13;
               }
            }

            byte var17 = 20;
            if(var14 > 150) {
               var17 = 10;
            }

            if(var14 > 200) {
               var17 = 5;
            }

            if(var13 > var17) {
               if(!this.q) {
                  this.q = true;
                  com.corrodinggames.rts.gameFramework.l.e("DOS: Too open connections from same ip:" + var1.toString() + " (count:" + var13 + ") max:" + var17);
               }

               return false;
            } else if(var14 > 300) {
               if(!this.o) {
                  this.o = true;
                  com.corrodinggames.rts.gameFramework.l.e("DOS: Too open connections locking down:" + var1.toString() + " (count:" + var14 + ")");
               }

               return false;
            } else {
               ++this.n;
               return true;
            }
         }
      }
   }

   public strictfp void a() {
      this.r.d("Recreating server socket " + (this.f?"udp":"tcp"));
      Object var1 = this.j;
      synchronized(this.j) {
         if(this.d != null) {
            try {
               this.d.close();
            } catch (IOException var4) {
               var4.printStackTrace();
            }

            this.d = null;
         }

         if(!this.c) {
            throw new IOException("recreate on non-active socket");
         } else {
            this.a(this.f);
         }
      }
   }

   public strictfp void a(boolean var1) {
      this.e = this.r.m;
      this.r.d("starting socket.. " + (var1?"udp":"tcp") + " port: " + this.e);
      this.f = var1;
      if(!var1) {
         this.d = new ServerSocket(this.e);
      } else {
         a.a.b var2 = new a.a.b(this.r.m, 0, (InetAddress)null, true);
         var2.a((a.a.c)(new ao$1(this)));
         this.d = var2;
      }

   }

   public strictfp void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      Thread.currentThread().setName("NewConnectionWorker-" + (this.f?"udp":"tcp") + " - " + this.e);
      int var1 = 0;
      int var2 = 0;
      this.r.d("reading..");

      while(this.c) {
         Socket var3;
         String var7;
         try {
            var3 = this.d.accept();
         } catch (IOException var10) {
            if(!this.c) {
               com.corrodinggames.rts.gameFramework.l.e("ServerSocket-accept(" + (this.f?"udp":"tcp") + "): Got expected IOException after closed socket");
               break;
            }

            com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
            ++var1;
            com.corrodinggames.rts.gameFramework.l.e("ServerSocket-accept(" + (this.f?"udp":"tcp") + ") failed: " + var10.getMessage() + " (closed:" + this.d.isClosed() + ")");
            if(var1 > 100) {
               com.corrodinggames.rts.gameFramework.l.e("Too many server socket fails");
               this.b();
               return;
            }

            try {
               this.a();
            } catch (IOException var9) {
               var9.printStackTrace();
               com.corrodinggames.rts.gameFramework.l.B().i("Warning server socket got closed and could not be recreated");
               this.b();
               return;
            }

            if(var2 < 3) {
               int var6 = var5.bX.D();
               if(var6 > 0) {
                  var7 = "Warning: server socket got closed and needed to be recreated, players were likely disconnected (but can rejoin).";
                  if(com.corrodinggames.rts.gameFramework.l.aZ) {
                     var7 = var7 + "\n This likely due to iOS removing sockets of background apps. Avoid minimising the game when hosting.";
                  }

                  com.corrodinggames.rts.gameFramework.l.B().i(var7);
                  ++var2;
               }
            }
            continue;
         }

         try {
            var3.setTcpNoDelay(true);
            var3.setSoTimeout(15000);
            String var4 = "<unknown>";
            InetAddress var11 = var3.getInetAddress();
            if(var11 != null) {
               var4 = var11.getHostAddress();
            }

            if(!this.a(var11, true)) {
               var3.close();
            } else {
               c var12 = new c(this.r, var3);
               var7 = "Accepted new connection id:" + var12.c + ".. (ip:" + var4 + ")";
               if(this.f) {
                  var7 = var7 + " (udp)";
               }

               this.r.d(var7);
               var12.h = this.f;
               var12.e = var11;
               var12.d();
               this.r.aM.add(var12);
            }
         } catch (IOException var8) {
            com.corrodinggames.rts.gameFramework.l.e("Got IOException on new player connection");
            var8.printStackTrace();
         }
      }

   }

   public strictfp void b() {
      Object var1 = this.j;
      synchronized(this.j) {
         this.c = false;
         if(this.d != null) {
            try {
               this.d.close();
            } catch (IOException var4) {
               var4.printStackTrace();
            }

            this.d = null;
         }

      }
   }

}
