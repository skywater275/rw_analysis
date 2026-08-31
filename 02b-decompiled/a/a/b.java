package a.a;

import a.a.b$1;
import a.a.c;
import a.a.d;
import a.a.e;
import a.a.f;
import a.a.s;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

public class b extends ServerSocket {

   c a;
   private DatagramSocket d;
   private int e;
   private int f;
   private boolean g;
   private ArrayList h;
   private HashMap i;
   private HashMap j;
   private HashMap k;
   long b;
   int c;
   private s l;


   public b() {
      this(new DatagramSocket((SocketAddress)null), 0);
   }

   public b(int var1, int var2, InetAddress var3, boolean var4) {
      DatagramSocket var5 = new DatagramSocket((SocketAddress)null);
      var5.setReuseAddress(var4);
      var5.bind(new InetSocketAddress(var3, var1));
      this.a(var5, var2);
   }

   public b(DatagramSocket var1, int var2) {
      this.a(var1, var2);
   }

   public void a(DatagramSocket var1, int var2) {
      if(var1 == null) {
         throw new NullPointerException("sock");
      } else {
         this.d = var1;
         this.f = var2 <= 0?50:var2;
         this.h = new ArrayList(this.f);
         this.i = new HashMap();
         this.j = new HashMap();
         this.k = new HashMap();
         this.l = new f(this, (b$1)null);
         this.e = 0;
         this.g = false;
         (new d(this)).start();
      }
   }

   public void a(c var1) {
      this.a = var1;
   }

   public Socket accept() {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else {
         ArrayList var1 = this.h;
         synchronized(this.h) {
            do {
               if(!this.h.isEmpty()) {
                  return (Socket)this.h.remove(0);
               }

               try {
                  if(this.e == 0) {
                     this.h.wait();
                  } else {
                     long var2 = System.currentTimeMillis();
                     this.h.wait((long)this.e);
                     if(System.currentTimeMillis() - var2 >= (long)this.e) {
                        throw new SocketTimeoutException();
                     }
                  }
               } catch (InterruptedException var5) {
                  var5.printStackTrace();
               }
            } while(!this.isClosed());

            throw new SocketException("Socket is closed");
         }
      }
   }

   public synchronized void bind(SocketAddress var1) {
      this.bind(var1, 0);
   }

   public synchronized void bind(SocketAddress var1, int var2) {
      if(this.isClosed()) {
         throw new SocketException("Socket is closed");
      } else {
         this.d.setReuseAddress(true);
         this.d.bind(var1);
      }
   }

   public synchronized void close() {
      if(!this.isClosed()) {
         this.g = true;
         ArrayList var1 = this.h;
         synchronized(this.h) {
            this.h.clear();
            this.h.notify();
         }

         HashMap var6 = this.i;
         synchronized(this.i) {
            if(this.i.isEmpty()) {
               this.d.close();
            }

         }
      }
   }

   public InetAddress getInetAddress() {
      return this.d.getInetAddress();
   }

   public int getLocalPort() {
      return this.d.getLocalPort();
   }

   public SocketAddress getLocalSocketAddress() {
      return this.d.getLocalSocketAddress();
   }

   public boolean isBound() {
      return this.d.isBound();
   }

   public boolean isClosed() {
      return this.g;
   }

   public void setSoTimeout(int var1) {
      if(var1 < 0) {
         throw new IllegalArgumentException("timeout < 0");
      } else {
         this.e = var1;
      }
   }

   public int getSoTimeout() {
      return this.e;
   }

   private void a(SocketAddress var1, e var2) {
      HashMap var3 = this.i;
      synchronized(this.i) {
         var2.a(this.l);
         this.i.put(var1, var2);
      }
   }

   private e a(SocketAddress var1) {
      HashMap var2 = this.i;
      synchronized(this.i) {
         e var3 = (e)this.i.remove(var1);
         if(this.i.isEmpty() && this.isClosed()) {
            this.d.close();
         }

         return var3;
      }
   }

   private void a(String var1) {
      if(this.b + 5000L < System.currentTimeMillis()) {
         this.b = System.currentTimeMillis();
         this.c = 0;
      }

      if(this.c <= 20) {
         ++this.c;
         System.out.println(var1);
      }
   }

   // $FF: synthetic method
   static DatagramSocket a(b var0) {
      return var0.d;
   }

   // $FF: synthetic method
   static void a(b var0, String var1) {
      var0.a(var1);
   }

   // $FF: synthetic method
   static HashMap b(b var0) {
      return var0.i;
   }

   // $FF: synthetic method
   static HashMap c(b var0) {
      return var0.k;
   }

   // $FF: synthetic method
   static HashMap d(b var0) {
      return var0.j;
   }

   // $FF: synthetic method
   static void a(b var0, SocketAddress var1, e var2) {
      var0.a(var1, var2);
   }

   // $FF: synthetic method
   static ArrayList e(b var0) {
      return var0.h;
   }

   // $FF: synthetic method
   static e a(b var0, SocketAddress var1) {
      return var0.a(var1);
   }
}
