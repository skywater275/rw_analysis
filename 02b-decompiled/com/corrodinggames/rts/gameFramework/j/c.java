package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.c$1;
import com.corrodinggames.rts.gameFramework.j.d;
import com.corrodinggames.rts.gameFramework.j.e;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class c {

   private final ad W;
   volatile boolean a = false;
   volatile boolean b = false;
   public int c;
   public Socket d;
   InetAddress e;
   ConcurrentLinkedQueue f = new ConcurrentLinkedQueue();
   public long g;
   public boolean h;
   public boolean i;
   public c j;
   public int k = -1;
   au l;
   public String m;
   public String n;
   public String o;
   public boolean p;
   public boolean q;
   public boolean r;
   public boolean s;
   public boolean t;
   public boolean u;
   public boolean v;
   public boolean w;
   public int x;
   public int y;
   public com.corrodinggames.rts.game.e z;
   int A = -1;
   long B = -1L;
   boolean C = false;
   boolean D = false;
   public int E = 999999;
   d F;
   e G;
   Thread H;
   Thread I;
   boolean J = false;
   boolean K = false;
   public String L;
   public int M;
   public boolean N;
   public boolean O;
   public int P;
   public boolean Q;
   public int R;
   public long S;
   public boolean T;
   volatile int U;
   volatile int V;


   public strictfp c(ad var1, Socket var2) {
      this.W = var1;
      this.d = var2;
      Object var3 = this.W.aQ;
      synchronized(this.W.aQ) {
         this.c = this.W.aP++;
      }

      this.M = com.corrodinggames.rts.gameFramework.f.a(1, 1000000);
   }

   public strictfp boolean a() {
      if(this.S < System.currentTimeMillis() - 10000L) {
         this.S = System.currentTimeMillis();
         this.R = 0;
      }

      if(this.R > 100) {
         if(!this.T) {
            this.T = true;
            this.c("Command limit was reached");
         }

         return true;
      } else {
         ++this.R;
         return false;
      }
   }

   public strictfp int b() {
      return this.B == -1L?-2:(this.B < System.currentTimeMillis() - 5000L?-1:this.A);
   }

   strictfp int c() {
      com.corrodinggames.rts.game.e var1 = this.z;
      return var1 != null?var1.k:-1;
   }

   public synchronized strictfp void d() {
      this.G = new e(this);
      this.I = new Thread(this.G);
      this.I.setDaemon(true);
      this.I.start();
      this.F = new d(this, (c$1)null);
      this.H = new Thread(this.F);
      this.H.setDaemon(true);
      this.H.start();
   }

   private strictfp void i() {
      this.a = true;
      if(this.W.C && !this.W.n()) {
         com.corrodinggames.rts.game.e var1 = this.z;
         if(var1 != null) {
            this.z = null;
            c var2 = this.W.d((com.corrodinggames.rts.game.n)var1);
            if(var2 == null) {
               var1.I();
               this.W.P();
               com.corrodinggames.rts.appFramework.n.o();
            }
         }
      }

      if(this.H != null) {
         this.H.interrupt();
      }

      this.W.b(this);
      this.p = false;
      if(this.q) {
         this.W.c(this, "Closing");
      }

   }

   private synchronized strictfp void j() {
      if(!this.a) {
         this.b = true;
         if(this.G != null) {
            this.G.a();
         }

         if(this.H != null) {
            this.H.interrupt();
         }

         this.W.b(this);
      }
   }

   public strictfp void a(String var1) {
      as var2 = new as();

      try {
         if(var1 == null) {
            var1 = "NULL";
         }

         var2.c(var1);
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }

      this.a(var2.b(111));
      this.j();
   }

   private synchronized strictfp void a(boolean var1, boolean var2) {
      this.a(var1, var2, "Time out");
   }

   public strictfp String e() {
      String var1 = "<null>";
      if(this.z != null) {
         var1 = this.z.v;
      }

      return var1;
   }

   public strictfp String f() {
      if(this.j != null) {
         return this.n;
      } else {
         try {
            Socket var1 = this.d;
            if(var1 != null) {
               InetAddress var2 = var1.getInetAddress();
               if(var2 != null) {
                  return var2.getHostAddress();
               }
            }

            return null;
         } catch (Exception var3) {
            var3.printStackTrace();
            return null;
         }
      }
   }

   public strictfp String g() {
      if(this.j != null) {
         return this.n == null?"<forwarded unknown>":this.n;
      } else {
         String var1 = "<no socket>";

         try {
            Socket var2 = this.d;
            if(var2 != null) {
               var1 = "<no bond socket>";
               InetAddress var3 = var2.getInetAddress();
               if(var3 != null) {
                  var1 = var3.getHostAddress();
               }
            }

            return var1;
         } catch (Exception var4) {
            var4.printStackTrace();
            return "<socket error>";
         }
      }
   }

   public synchronized strictfp void a(boolean var1, boolean var2, String var3) {
      if(!this.a) {
         this.c("handleRemoteDisconnect");
         String var4 = null;
         if(this.z != null) {
            var4 = this.z.v;
         }

         String var5 = null;
         if(this.z != null) {
            String var6 = "player";
            String var7 = "";
            if(this.z.b()) {
               var6 = "spectator";
            } else if(this.W.aW) {
               int var8 = this.z.a(false, false);
               if(var8 == 0) {
                  var7 = " (Had no units)";
               } else {
                  var7 = " (Team " + this.z.h() + ")";
               }
            }

            var5 = var6 + " \'" + this.z.v + "\' disconnected" + var7;
         } else if(this.p) {
            if(this.s && this.q) {
               var5 = "relay server disconnected";
            } else {
               var5 = "a player disconnected";
            }
         }

         if(!this.W.C) {
            var5 = "The server disconnected";
         }

         if(var5 != null && var3 != null) {
            var5 = var5 + "  (" + ad.i(var3) + ")";
         }

         this.i();
         if(var5 != null) {
            boolean var10 = false;
            if(this.z != null && this.W.C) {
               c var11 = this.W.d((com.corrodinggames.rts.game.n)this.z);
               if(var11 != null) {
                  var10 = true;
               }
            }

            if(!var10) {
               if(!this.W.C) {
                  this.W.f(var5);
               } else {
                  this.W.j(var5);
               }
            } else {
               this.c("Not sending: \'" + var5 + "\' still another active connection");
            }
         }

         this.W.d.b(this, var4);
      } else {
         this.c("handleRemoteDisconnect: connection is already disconnecting");
      }

      if(!var2 && this.G != null) {
         this.G.a();
      }

      if(var2) {
         this.J = true;
      }

      if(var1) {
         this.K = true;
      }

      if(this.J && this.K) {
         try {
            this.d.close();
         } catch (IOException var9) {
            com.corrodinggames.rts.gameFramework.l.a("Error while closing network socket", (Throwable)var9);
         }

         this.I = null;
         this.H = null;
         this.G = null;
         this.F = null;
         if(this.f != null) {
            this.f.clear();
         }
      }

   }

   public strictfp void a(String var1, Throwable var2) {
      com.corrodinggames.rts.gameFramework.l.a(this.d(var1), var2);
   }

   public strictfp void b(String var1) {
      com.corrodinggames.rts.gameFramework.l.b(this.d(var1));
   }

   public strictfp void c(String var1) {
      com.corrodinggames.rts.gameFramework.l.e(this.d(var1));
   }

   public strictfp String d(String var1) {
      var1 = "id:" + this.c + ": " + var1;
      com.corrodinggames.rts.game.e var2 = this.z;
      if(var2 != null) {
         var1 = var1 + " (Player:" + var2.v + ")";
      }

      return var1;
   }

   public strictfp void a(au var1) {
      if(this.G != null || !this.a) {
         this.G.a(var1);
      }
   }

   public strictfp boolean h() {
      return !this.a;
   }

   public strictfp void finalize() {
      try {
         if(this.d == null || this.d.isClosed()) {
            return;
         }

         com.corrodinggames.rts.gameFramework.l.e("Connection::finalize called on unclosed socket (index:" + this.c + ")");
         if(this.d.getInetAddress() == null) {
            com.corrodinggames.rts.gameFramework.l.e("Skipping possible steam socket");
         }

         try {
            this.d.close();
         } catch (IOException var2) {
            var2.printStackTrace();
         }
      } catch (RuntimeException var3) {
         var3.printStackTrace();
      }

   }

   // $FF: synthetic method
   static void a(c var0, boolean var1, boolean var2) {
      var0.a(var1, var2);
   }

   // $FF: synthetic method
   static ad a(c var0) {
      return var0.W;
   }
}
