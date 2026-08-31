package a.a;

import a.a.b;
import a.a.h;
import a.a.r;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

class e extends h {

   boolean a;
   private ArrayList i;
   // $FF: synthetic field
   final b b;


   public e(b var1, DatagramSocket var2, SocketAddress var3) {
      super(var2);
      this.b = var1;
      this.d = var3;
   }

   protected void a(DatagramSocket var1, r var2) {
      this.i = new ArrayList();
      this.c = var1;
      this.g = var2;
   }

   protected a.a.a.h a() {
      ArrayList var1 = this.i;
      synchronized(this.i) {
         while(this.i.isEmpty()) {
            try {
               this.i.wait();
            } catch (InterruptedException var4) {
               var4.printStackTrace();
            }
         }

         return (a.a.a.h)this.i.remove(0);
      }
   }

   protected void a(a.a.a.h var1) {
      ArrayList var2 = this.i;
      synchronized(this.i) {
         if(!this.a) {
            this.a = true;
            super.a(this.c, this.g);
         }

         this.i.add(var1);
         this.i.notify();
      }
   }

   protected void b() {
      ArrayList var1 = this.i;
      synchronized(this.i) {
         this.i.clear();
         this.i.add((Object)null);
         this.i.notify();
      }
   }

   protected void a(String var1) {
      System.out.println(this.getPort() + ": " + var1);
   }
}
