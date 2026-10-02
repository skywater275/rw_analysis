package a.a;

import a.a.h$1;
import a.a.i;
import a.a.j;
import a.a.k;
import a.a.l;
import a.a.m;
import a.a.n;
import a.a.o;
import a.a.p;
import a.a.q;
import a.a.r;
import a.a.s;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class h extends Socket {

   protected DatagramSocket c;
   protected SocketAddress d;
   protected o e;
   protected q f;
   private byte[] a;
   private boolean b;
   private boolean i;
   private boolean j;
   private boolean k;
   private int l;
   private int m;
   private boolean n;
   private boolean o;
   private int p;
   private Object q;
   private Object r;
   private ArrayList s;
   private ArrayList t;
   protected r g;
   private ArrayList u;
   private ArrayList v;
   private ArrayList w;
   private Object x;
   private i y;
   private Thread z;
   private int A;
   private int B;
   private int C;
   private int D;
   public boolean h;
   private a.a.a.i E;
   private a.a.a.i F;
   private a.a.a.i G;
   private a.a.a.i H;
   private static final boolean I = Boolean.getBoolean("net.rudp.debug");


   public h() {
      this(new r());
   }

   public h(r var1) {
      this(new DatagramSocket(), var1);
   }

   protected h(DatagramSocket var1) {
      this(var1, new r());
   }

   protected h(DatagramSocket var1, r var2) {
      this.b = false;
      this.i = false;
      this.j = false;
      this.k = true;
      this.l = 0;
      this.m = 0;
      this.n = false;
      this.o = false;
      this.p = -1;
      this.q = new Object();
      this.r = new Object();
      this.s = new ArrayList();
      this.t = new ArrayList();
      this.g = r.a;
      this.u = new ArrayList();
      this.v = new ArrayList();
      this.w = new ArrayList();
      this.x = new Object();
      this.y = new i();
      this.A = 32;
      this.B = 32;
      this.h = false;
      this.E = new a.a.a.i("rudp-NullSegmentTimer", new l(this, (h$1)null));
      this.F = new a.a.a.i("rudp-RetransmissionTimer", new n(this, (h$1)null));
      this.G = new a.a.a.i("rudp-CumulativeAckTimer", new j(this, (h$1)null));
      this.H = new a.a.a.i("rudp-KeepAliveTimer", new k(this, (h$1)null));
      if(var1 == null) {
         throw new NullPointerException("sock");
      } else {
         this.a(var1, var2);
      }
   }

   protected void a(DatagramSocket var1, r var2) {
      this.c = var1;
      this.g = var2;
      this.C = (this.g.a() - 6) * 32;
      this.D = (this.g.a() - 6) * 32;
      if(this.z == null) {
         this.z = new m(this);
         this.z.start();
      }

   }

   public void bind(SocketAddress var1) {
      this.c.bind(var1);
   }

   public void connect(SocketAddress var1) {
      this.connect(var1, 0);
   }

   public void connect(SocketAddress var1, int var2) {
      if(var1 == null) {
         throw new IllegalArgumentException("connect: The address can\'t be null");
      } else if(var2 < 0) {
         throw new IllegalArgumentException("connect: timeout can\'t be negative");
      } else if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(this.isConnected()) {
         throw new SocketException("already connected");
      } else if(!(var1 instanceof InetSocketAddress)) {
         throw new IllegalArgumentException("Unsupported address type");
      } else {
         this.d = (InetSocketAddress)var1;
         this.f();
         this.l = 2;
         Random var3 = new Random(System.currentTimeMillis());
         a.a.a.g var4 = new a.a.a.g(this.y.a(var3.nextInt(255)), this.g.b(), this.g.a(), this.g.h(), this.g.i(), this.g.g(), this.g.c(), this.g.d(), this.g.e(), this.g.f());
         this.e((a.a.a.h)var4);
         boolean var5 = false;
         synchronized(this) {
            if(!this.isConnected()) {
               try {
                  if(var2 == 0) {
                     this.wait();
                  } else {
                     long var7 = System.currentTimeMillis();
                     this.wait((long)var2);
                     if(System.currentTimeMillis() - var7 >= (long)var2) {
                        var5 = true;
                     }
                  }
               } catch (InterruptedException var12) {
                  var12.printStackTrace();
               }
            }
         }

         if(this.l != 3) {
            ArrayList var6 = this.u;
            synchronized(this.u) {
               this.u.clear();
               this.u.notifyAll();
            }

            this.y.l();
            this.F.e();
            switch(this.l) {
            case 0:
            case 4:
               this.l = 0;
               throw new SocketException("Socket closed");
            case 1:
            case 3:
            default:
               return;
            case 2:
               this.k();
               this.l = 0;
               if(var5) {
                  throw new SocketTimeoutException();
               } else {
                  throw new SocketException("Connection refused");
               }
            }
         }
      }
   }

   public SocketChannel getChannel() {
      return null;
   }

   public InetAddress getInetAddress() {
      return !this.isConnected()?null:((InetSocketAddress)this.d).getAddress();
   }

   public int getPort() {
      return !this.isConnected()?0:((InetSocketAddress)this.d).getPort();
   }

   public SocketAddress getRemoteSocketAddress() {
      return !this.isConnected()?null:new InetSocketAddress(this.getInetAddress(), this.getPort());
   }

   public SocketAddress c() {
      return this.d;
   }

   public InetAddress getLocalAddress() {
      return this.c.getLocalAddress();
   }

   public int getLocalPort() {
      return this.c.getLocalPort();
   }

   public SocketAddress getLocalSocketAddress() {
      return this.c.getLocalSocketAddress();
   }

   public synchronized InputStream getInputStream() {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(!this.isConnected()) {
         throw new SocketException("Socket is not connected");
      } else if(this.isInputShutdown()) {
         throw new SocketException("Socket input is shutdown");
      } else {
         if(this.e == null) {
            this.e = new o(this);
         }

         return this.e;
      }
   }

   public synchronized OutputStream getOutputStream() {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(!this.isConnected()) {
         throw new SocketException("Socket is not connected");
      } else if(this.isOutputShutdown()) {
         throw new SocketException("Socket output is shutdown");
      } else {
         if(this.f == null) {
            this.f = new q(this);
         }

         return this.f;
      }
   }

   public void d() {
      this.b = true;
      this.l = 0;
      this.c.close();
   }

   public synchronized void close() {
      Object var1 = this.q;
      synchronized(this.q) {
         if(!this.isClosed()) {
            this.g();
            switch(this.l) {
            case 0:
               this.c.close();
               break;
            case 1:
            case 3:
            case 4:
               this.a((a.a.a.h)(new a.a.a.d(this.y.a())));
               this.e();
               break;
            case 2:
               synchronized(this) {
                  this.notify();
               }
            }

            if(this.l != 0) {
               this.p = this.l;
            }

            this.b = true;
            this.l = 0;
            this.l();
            ArrayList var2 = this.u;
            synchronized(this.u) {
               this.u.notify();
            }

            var2 = this.w;
            synchronized(this.w) {
               this.w.notify();
            }

         }
      }
   }

   public boolean isBound() {
      return this.c.isBound();
   }

   public boolean isConnected() {
      return this.i;
   }

   public boolean isClosed() {
      Object var1 = this.q;
      synchronized(this.q) {
         return this.b;
      }
   }

   public void setSoTimeout(int var1) {
      if(var1 < 0) {
         throw new IllegalArgumentException("timeout < 0");
      } else {
         this.m = var1;
      }
   }

   public synchronized void setSendBufferSize(int var1) {
      if(var1 <= 0) {
         throw new IllegalArgumentException("negative receive size");
      } else if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(!this.isConnected()) {
         this.C = var1;
      }
   }

   public synchronized int getSendBufferSize() {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else {
         return this.C;
      }
   }

   public synchronized void setReceiveBufferSize(int var1) {
      if(var1 <= 0) {
         throw new IllegalArgumentException("negative send size");
      } else if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(!this.isConnected()) {
         this.D = var1;
      }
   }

   public synchronized int getReceiveBufferSize() {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else {
         return this.D;
      }
   }

   public void setTcpNoDelay(boolean var1) {}

   public boolean getTcpNoDelay() {
      return false;
   }

   public synchronized void setKeepAlive(boolean var1) {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(this.k ^ var1) {
         this.k = var1;
         if(this.isConnected()) {
            if(this.k) {
               this.H.a((long)(this.g.g() * 6), (long)(this.g.g() * 6));
            } else {
               this.H.e();
            }
         }

      }
   }

   public synchronized boolean getKeepAlive() {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else {
         return this.k;
      }
   }

   public void shutdownInput() {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(!this.isConnected()) {
         throw new SocketException("Socket is not connected");
      } else if(this.isInputShutdown()) {
         throw new SocketException("Socket input is already shutdown");
      } else {
         this.n = true;
         Object var1 = this.x;
         synchronized(this.x) {
            this.x.notify();
         }
      }
   }

   public void shutdownOutput() {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(!this.isConnected()) {
         throw new SocketException("Socket is not connected");
      } else if(this.isOutputShutdown()) {
         throw new SocketException("Socket output is already shutdown");
      } else {
         this.o = true;
         ArrayList var1 = this.u;
         synchronized(this.u) {
            this.u.notifyAll();
         }
      }
   }

   public boolean isInputShutdown() {
      return this.n;
   }

   public boolean isOutputShutdown() {
      return this.o;
   }

   protected void a(byte[] var1, int var2, int var3) {
      this.a(var1, var2, var3, false);
   }

   public void a(byte[] var1, int var2, int var3, boolean var4) {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else if(this.isOutputShutdown()) {
         throw new IOException("Socket output is shutdown");
      } else if(!this.isConnected()) {
         throw new SocketException("Connection reset");
      } else {
         int var5 = 0;

         while(var5 < var3) {
            Object var6 = this.r;
            synchronized(this.r) {
               while(this.j) {
                  try {
                     this.r.wait();
                  } catch (InterruptedException var10) {
                     var10.printStackTrace();
                  }
               }

               int var7 = Math.min(this.g.a() - 6, var3 - var5);
               a.a.a.b var8 = new a.a.a.b(this.y.a(), this.y.b(), var1, var2 + var5, var7);
               this.e((a.a.a.h)var8);
               if(var4) {
                  this.a((a.a.a.h)var8);
               }

               var5 += var7;
            }
         }

      }
   }

   protected int b(byte[] var1, int var2, int var3) {
      int var4 = 0;
      Object var5 = this.x;
      synchronized(this.x) {
         while(true) {
            while(!this.w.isEmpty()) {
               Iterator var6 = this.w.iterator();

               while(var6.hasNext()) {
                  a.a.a.h var7 = (a.a.a.h)var6.next();
                  if(var7 instanceof a.a.a.f) {
                     var6.remove();
                     break;
                  }

                  if(var7 instanceof a.a.a.d) {
                     if(var4 <= 0) {
                        var6.remove();
                        return -1;
                     }
                     break;
                  }

                  if(var7 instanceof a.a.a.b) {
                     byte[] var8 = ((a.a.a.b)var7).c();
                     if(var8.length + var4 > var3) {
                        if(var4 <= 0) {
                           throw new IOException("insufficient buffer space");
                        }
                        break;
                     }

                     System.arraycopy(var8, 0, var1, var2 + var4, var8.length);
                     var4 += var8.length;
                     var6.remove();
                  }
               }

               if(var4 > 0) {
                  return var4;
               }
            }

            if(this.isClosed()) {
               throw new SocketException("Socket is closed");
            }

            if(this.isInputShutdown()) {
               throw new EOFException();
            }

            if(!this.isConnected()) {
               throw new SocketException("Connection reset");
            }

            try {
               if(this.m == 0) {
                  this.x.wait();
               } else {
                  long var12 = System.currentTimeMillis();
                  this.x.wait((long)this.m);
                  if(System.currentTimeMillis() - var12 >= (long)this.m) {
                     throw new SocketTimeoutException();
                  }
               }
            } catch (InterruptedException var10) {
               if(I) {
                  var10.printStackTrace();
               }
            }
         }
      }
   }

   public void a(s var1) {
      if(var1 == null) {
         throw new NullPointerException("stateListener");
      } else {
         ArrayList var2 = this.t;
         synchronized(this.t) {
            if(!this.t.contains(var1)) {
               this.t.add(var1);
            }

         }
      }
   }

   private void a(a.a.a.h var1) {
      if(var1 instanceof a.a.a.b || var1 instanceof a.a.a.f || var1 instanceof a.a.a.d || var1 instanceof a.a.a.e) {
         this.h(var1);
      }

      if(var1 instanceof a.a.a.b || var1 instanceof a.a.a.f || var1 instanceof a.a.a.d) {
         this.E.d();
      }

      if(I) {
         this.a("sent " + var1);
      }

      this.d(var1);
   }

   private a.a.a.h i() {
      a.a.a.h var1;
      if((var1 = this.a()) != null) {
         if(I) {
            this.a("recv " + var1);
         }

         if(var1 instanceof a.a.a.b || var1 instanceof a.a.a.e || var1 instanceof a.a.a.f || var1 instanceof a.a.a.d || var1 instanceof a.a.a.g) {
            this.y.c();
         }

         if(this.k) {
            this.H.d();
         }
      }

      return var1;
   }

   private void e(a.a.a.h var1) {
      ArrayList var2 = this.u;
      synchronized(this.u) {
         while(this.u.size() >= this.A || this.y.j() > this.g.b()) {
            if(this.b) {
               throw new SocketException("Socket is closed");
            }

            try {
               this.u.wait(10000L);
            } catch (InterruptedException var8) {
               var8.printStackTrace();
            }
         }

         this.y.i();
         this.u.add(var1);
      }

      if(this.b) {
         throw new SocketException("Socket is closed");
      } else {
         if(!(var1 instanceof a.a.a.c) && !(var1 instanceof a.a.a.a)) {
            a.a.a.i var11 = this.F;
            synchronized(this.F) {
               if(this.F.c()) {
                  this.F.a((long)this.g.h(), (long)this.g.h());
               }
            }
         }

         this.a(var1);
         if(var1 instanceof a.a.a.b) {
            var2 = this.s;
            synchronized(this.s) {
               Iterator var3 = this.s.iterator();

               while(var3.hasNext()) {
                  p var4 = (p)var3.next();
                  var4.a();
               }
            }
         }

      }
   }

   private void f(a.a.a.h var1) {
      if(this.g.c() > 0) {
         var1.b(var1.o() + 1);
      }

      if(this.g.c() != 0 && var1.o() > this.g.c()) {
         this.m();
      } else {
         this.a(var1);
         if(var1 instanceof a.a.a.b) {
            ArrayList var2 = this.s;
            synchronized(this.s) {
               Iterator var3 = this.s.iterator();

               while(var3.hasNext()) {
                  p var4 = (p)var3.next();
                  var4.b();
               }
            }
         }

      }
   }

   private void j() {
      if(this.isConnected()) {
         this.E.e();
         if(this.k) {
            this.H.e();
         }

         Object var1 = this.r;
         synchronized(this.r) {
            this.j = false;
            this.r.notify();
         }
      } else {
         synchronized(this) {
            this.f();
            this.i = true;
            this.l = 3;
            this.notify();
         }

         ArrayList var8 = this.t;
         synchronized(this.t) {
            Iterator var2 = this.t.iterator();

            while(var2.hasNext()) {
               s var3 = (s)var2.next();
               var3.a(this);
            }
         }
      }

      this.E.a(0L, (long)this.g.g());
      if(this.k) {
         this.H.a((long)(this.g.g() * 6), (long)(this.g.g() * 6));
      }

   }

   private void k() {
      ArrayList var1 = this.t;
      synchronized(this.t) {
         Iterator var2 = this.t.iterator();

         while(var2.hasNext()) {
            s var3 = (s)var2.next();
            var3.b(this);
         }

      }
   }

   private void l() {
      ArrayList var1 = this.t;
      synchronized(this.t) {
         Iterator var2 = this.t.iterator();

         while(var2.hasNext()) {
            s var3 = (s)var2.next();
            var3.c(this);
         }

      }
   }

   private void m() {
      Object var1 = this.q;
      synchronized(this.q) {
         if(this.isClosed()) {
            return;
         }

         switch(this.l) {
         case 1:
         case 3:
         case 4:
            this.i = false;
            ArrayList var2 = this.u;
            synchronized(this.u) {
               this.u.notifyAll();
            }

            Object var14 = this.x;
            synchronized(this.x) {
               this.x.notify();
            }

            this.e();
            break;
         case 2:
            synchronized(this) {
               this.notify();
            }
         }

         this.l = 0;
         this.b = true;
      }

      ArrayList var13 = this.t;
      synchronized(this.t) {
         Iterator var15 = this.t.iterator();

         while(var15.hasNext()) {
            s var3 = (s)var15.next();
            var3.d(this);
         }

      }
   }

   private void n() {
      ArrayList var1 = this.t;
      synchronized(this.t) {
         Iterator var2 = this.t.iterator();

         while(var2.hasNext()) {
            s var3 = (s)var2.next();
            var3.e(this);
         }

      }
   }

   protected void a(a.a.a.g var1) {
      switch(this.l) {
      case 0:
         this.l = 1;
         this.g = new r(this.A, this.B, var1.e(), var1.c(), var1.i(), var1.j(), var1.k(), var1.l(), var1.h(), var1.f(), var1.g());
         this.y.b(var1.m());
         Random var9 = new Random(System.currentTimeMillis());
         a.a.a.g var10 = new a.a.a.g(this.y.a(var9.nextInt(255)), this.g.b(), this.g.a(), this.g.h(), this.g.i(), this.g.g(), this.g.c(), this.g.d(), this.g.e(), this.g.f());
         var10.a(var1.m());
         this.e((a.a.a.h)var10);
         break;
      case 1:
         ArrayList var2 = this.u;
         synchronized(this.u) {
            Iterator var3 = this.u.iterator();

            while(var3.hasNext()) {
               a.a.a.h var4 = (a.a.a.h)var3.next();

               try {
                  this.f(var4);
               } catch (IOException var7) {
                  var7.printStackTrace();
               }
            }

            return;
         }
      case 2:
         this.y.b(var1.m());
         this.l = 3;
         this.o();
         this.j();
      }

   }

   private void a(a.a.a.c var1) {
      int[] var3 = var1.c();
      int var4 = var1.n();
      int var5 = var3[var3.length - 1];
      ArrayList var6 = this.u;
      synchronized(this.u) {
         Iterator var2 = this.u.iterator();

         a.a.a.h var7;
         while(var2.hasNext()) {
            var7 = (a.a.a.h)var2.next();
            if(this.a(var7.m(), var4) <= 0) {
               var2.remove();
            } else {
               for(int var8 = 0; var8 < var3.length; ++var8) {
                  if(this.a(var7.m(), var3[var8]) == 0) {
                     var2.remove();
                     break;
                  }
               }
            }
         }

         var2 = this.u.iterator();

         while(var2.hasNext()) {
            var7 = (a.a.a.h)var2.next();
            if(this.a(var4, var7.m()) < 0 && this.a(var5, var7.m()) > 0) {
               try {
                  this.f(var7);
               } catch (IOException var10) {
                  var10.printStackTrace();
               }
            }
         }

         this.u.notifyAll();
      }
   }

   private void g(a.a.a.h var1) {
      if(var1 instanceof a.a.a.f) {
         Object var2 = this.r;
         synchronized(this.r) {
            this.j = true;
         }

         this.n();
      }

      if(var1 instanceof a.a.a.d) {
         switch(this.l) {
         case 0:
            break;
         case 2:
            synchronized(this) {
               this.notify();
               break;
            }
         default:
            this.l = 4;
         }
      }

      boolean var17 = false;
      Object var3 = this.x;
      synchronized(this.x) {
         if(this.a(var1.m(), this.y.b()) > 0) {
            if(this.a(var1.m(), b(this.y.b())) == 0) {
               var17 = true;
               if(this.w.size() == 0 || this.w.size() + this.v.size() < this.B) {
                  this.y.b(var1.m());
                  if(var1 instanceof a.a.a.b || var1 instanceof a.a.a.f || var1 instanceof a.a.a.d) {
                     this.w.add(var1);
                  }

                  if(var1 instanceof a.a.a.b) {
                     ArrayList var4 = this.s;
                     synchronized(this.s) {
                        Iterator var5 = this.s.iterator();

                        while(var5.hasNext()) {
                           p var6 = (p)var5.next();
                           var6.c();
                        }
                     }
                  }

                  this.r();
               }
            } else if(this.w.size() + this.v.size() < this.B) {
               boolean var18 = false;

               for(int var19 = 0; var19 < this.v.size() && !var18; ++var19) {
                  a.a.a.h var22 = (a.a.a.h)this.v.get(var19);
                  int var7 = this.a(var1.m(), var22.m());
                  if(var7 == 0) {
                     var18 = true;
                  } else if(var7 < 0) {
                     this.v.add(var19, var1);
                     var18 = true;
                  }
               }

               if(!var18) {
                  this.v.add(var1);
               }

               this.y.f();
               if(var1 instanceof a.a.a.b) {
                  ArrayList var21 = this.s;
                  synchronized(this.s) {
                     Iterator var23 = this.s.iterator();

                     while(var23.hasNext()) {
                        p var24 = (p)var23.next();
                        var24.d();
                     }
                  }
               }
            }
         }

         if(var17 && (var1 instanceof a.a.a.f || var1 instanceof a.a.a.e || var1 instanceof a.a.a.d)) {
            this.o();
         } else if(this.y.g() > 0 && (this.g.e() == 0 || this.y.g() > this.g.e())) {
            this.p();
         } else if(this.y.d() > 0 && (this.g.d() == 0 || this.y.d() > this.g.d())) {
            this.q();
         } else {
            a.a.a.i var20 = this.G;
            synchronized(this.G) {
               if(this.G.c()) {
                  this.G.a((long)this.g.i());
               }
            }
         }

      }
   }

   private void o() {
      Object var1 = this.x;
      synchronized(this.x) {
         if(!this.v.isEmpty()) {
            this.p();
         } else {
            this.q();
         }
      }
   }

   private void p() {
      Object var1 = this.x;
      synchronized(this.x) {
         if(!this.v.isEmpty()) {
            this.y.e();
            this.y.h();
            int[] var2 = new int[this.v.size()];

            int var3;
            for(var3 = 0; var3 < var2.length; ++var3) {
               a.a.a.h var4 = (a.a.a.h)this.v.get(var3);
               var2[var3] = var4.m();
            }

            try {
               var3 = this.y.b();
               this.a((a.a.a.h)(new a.a.a.c(b(var3), var3, var2)));
            } catch (IOException var6) {
               var6.printStackTrace();
            }

         }
      }
   }

   private void q() {
      if(this.y.e() != 0) {
         try {
            int var1 = this.y.b();
            this.a((a.a.a.h)(new a.a.a.a(b(var1), var1)));
         } catch (IOException var2) {
            var2.printStackTrace();
         }

      }
   }

   private void h(a.a.a.h var1) {
      if(this.y.e() != 0) {
         var1.a(this.y.b());
      }
   }

   protected boolean b(a.a.a.h var1) {
      int var2 = var1.n();
      if(var2 < 0) {
         return false;
      } else {
         Iterator var3 = this.u.iterator();

         a.a.a.h var4;
         do {
            if(!var3.hasNext()) {
               return false;
            }

            var4 = (a.a.a.h)var3.next();
         } while(this.a(var4.m(), var2) > 0);

         return true;
      }
   }

   protected void c(a.a.a.h var1) {
      int var2 = var1.n();
      if(var2 >= 0) {
         this.y.k();
         ArrayList var3 = this.u;
         synchronized(this.u) {
            Iterator var4 = this.u.iterator();

            while(var4.hasNext()) {
               a.a.a.h var5 = (a.a.a.h)var4.next();
               if(this.a(var5.m(), var2) <= 0) {
                  var4.remove();
               }
            }

            if(this.l == 1) {
               boolean var9 = false;
               if(!this.u.isEmpty()) {
                  Iterator var10 = this.u.iterator();

                  while(var10.hasNext()) {
                     a.a.a.h var6 = (a.a.a.h)var10.next();
                     if(var6 instanceof a.a.a.g) {
                        var9 = true;
                     }
                  }
               }

               if(var9) {
                  this.a("Bad first ack: " + var2);
                  return;
               }

               this.l = 3;
               this.j();
            }

            if(this.u.isEmpty()) {
               this.F.e();
            }

            this.u.notifyAll();
         }
      }
   }

   private void r() {
      Object var1 = this.x;
      synchronized(this.x) {
         Iterator var2 = this.v.iterator();

         while(var2.hasNext()) {
            a.a.a.h var3 = (a.a.a.h)var2.next();
            if(this.a(var3.m(), b(this.y.b())) == 0) {
               this.y.b(var3.m());
               if(var3 instanceof a.a.a.b || var3 instanceof a.a.a.f || var3 instanceof a.a.a.d) {
                  this.w.add(var3);
               }

               var2.remove();
            }
         }

         this.x.notify();
      }
   }

   protected void d(a.a.a.h var1) {
      try {
         DatagramPacket var2 = new DatagramPacket(var1.d(), var1.b(), this.d);
         this.c.send(var2);
      } catch (IOException var3) {
         if(!this.isClosed()) {
            var3.printStackTrace();
         }
      }

   }

   protected a.a.a.h a() {
      try {
         if(this.a == null) {
            this.a = new byte['\uffff'];
         }

         DatagramPacket var1 = new DatagramPacket(this.a, this.a.length);
         this.c.receive(var1);
         return a.a.a.h.b(var1.getData(), 0, var1.getLength());
      } catch (IOException var2) {
         if(!this.isClosed()) {
            var2.printStackTrace();
         }

         return null;
      }
   }

   protected void b() {
      this.c.close();
   }

   protected void e() {
      this.E.e();
      this.H.e();
      this.l = 4;
      h$1 var1 = new h$1(this);
      var1.setName("ReliableSocket-Closing");
      var1.setDaemon(true);
      var1.start();
   }

   protected synchronized void a(String var1) {
      System.out.println(this.getLocalPort() + ": " + var1);
   }

   private static int b(int var0) {
      return (var0 + 1) % 255;
   }

   private int a(int var1, int var2) {
      return var1 == var2?0:((var1 >= var2 || var2 - var1 <= 127) && (var1 <= var2 || var1 - var2 >= 127)?-1:1);
   }

   public synchronized void f() {
      if(!this.h) {
         this.h = true;
         this.E.a();
         this.F.a();
         this.G.a();
         this.H.a();
      }

   }

   public synchronized void g() {
      if(this.h) {
         this.h = false;
         this.F.f();
         this.G.f();
         this.H.f();
         this.E.f();
      }

   }

   // $FF: synthetic method
   static a.a.a.i a(h var0) {
      return var0.H;
   }

   // $FF: synthetic method
   static a.a.a.i b(h var0) {
      return var0.E;
   }

   // $FF: synthetic method
   static a.a.a.i c(h var0) {
      return var0.F;
   }

   // $FF: synthetic method
   static a.a.a.i d(h var0) {
      return var0.G;
   }

   // $FF: synthetic method
   static void e(h var0) {
      var0.l();
   }

   // $FF: synthetic method
   static int a(int var0) {
      return b(var0);
   }

   // $FF: synthetic method
   static a.a.a.h f(h var0) {
      return var0.i();
   }

   // $FF: synthetic method
   static void a(h var0, a.a.a.c var1) {
      var0.a(var1);
   }

   // $FF: synthetic method
   static void a(h var0, a.a.a.h var1) {
      var0.g(var1);
   }

   // $FF: synthetic method
   static ArrayList g(h var0) {
      return var0.u;
   }

   // $FF: synthetic method
   static i h(h var0) {
      return var0.y;
   }

   // $FF: synthetic method
   static void b(h var0, a.a.a.h var1) {
      var0.e(var1);
   }

   // $FF: synthetic method
   static boolean h() {
      return I;
   }

   // $FF: synthetic method
   static void c(h var0, a.a.a.h var1) {
      var0.f(var1);
   }

   // $FF: synthetic method
   static void i(h var0) {
      var0.o();
   }

   // $FF: synthetic method
   static void j(h var0) {
      var0.m();
   }

}
