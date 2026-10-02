package a.a;

import a.a.a;
import a.a.b;
import a.a.c;
import a.a.e;
import a.a.g;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map.Entry;

class d extends Thread {

   // $FF: synthetic field
   final b a;


   public d(b var1) {
      super("ReliableServerSocket");
      this.a = var1;
      this.setDaemon(true);
   }

   public void run() {
      byte[] var1 = new byte['\uffff'];

      while(true) {
         DatagramPacket var2 = new DatagramPacket(var1, var1.length);
         e var3 = null;
         SocketAddress var4 = null;

         try {
            try {
               b.a(this.a).receive(var2);
            } catch (IOException var13) {
               b.a(this.a, "IOException receiving packet:" + var13.getMessage() + " isConnected:" + b.a(this.a).isConnected());
               if(!b.a(this.a).isConnected()) {
                  this.a.close();
               }

               throw new IOException(var13);
            }

            var4 = var2.getSocketAddress();
            synchronized(b.b(this.a)) {
               a var6 = (a)b.c(this.a).get(var4);
               if(var6 != null) {
                  var6.a(var2.getData(), var2.getLength());
                  continue;
               }
            }

            synchronized(b.b(this.a)) {
               var3 = (e)b.b(this.a).get(var4);
            }

            if(var3 == null) {
               c var5 = this.a.a;
               if(var5 != null && !var5.a(var4)) {
                  continue;
               }
            }

            a.a.a.h var16 = a.a.a.h.b(var2.getData(), 0, var2.getLength());
            if(!this.a.isClosed() && var3 == null) {
               if(var16 instanceof a.a.a.g) {
                  long var17 = System.currentTimeMillis();
                  if(b.d(this.a).size() > 0) {
                     short var8 = 10000;
                     if(b.d(this.a).size() > 20) {
                        var8 = 5000;
                     }

                     if(b.d(this.a).size() > 200) {
                        var8 = 3000;
                     }

                     Iterator var9 = b.d(this.a).entrySet().iterator();

                     while(var9.hasNext()) {
                        Entry var10 = (Entry)var9.next();
                        if(((g)var10.getValue()).a + (long)var8 < var17) {
                           var9.remove();
                        }
                     }
                  }

                  g var19 = (g)b.d(this.a).get(var4);
                  if(var19 != null) {
                     var19.b.a((a.a.a.g)var16);
                  } else {
                     var19 = new g();
                     var19.a = var17;
                     var19.b = new e(this.a, b.a(this.a), var4);
                     var19.b.a((a.a.a.g)var16);
                     b.d(this.a).put(var4, var19);
                  }
               }

               if(var16 instanceof a.a.a.a) {
                  g var18 = (g)b.d(this.a).get(var4);
                  if(var18 != null) {
                     e var7 = var18.b;
                     if(!var7.b(var16)) {
                        b.a(this.a, "lightweight ack failed ack:" + var16.n());
                        continue;
                     }

                     b.a(this.a, var4, var7);
                     var3 = var7;
                     b.d(this.a).remove(var4);
                  }
               }
            }

            if(var3 != null) {
               var3.a(var16);
            }
            continue;
         } catch (IOException var14) {
            if(!this.a.isClosed()) {
               b.a(this.a, "IOException client " + var4 + " - " + var14.getMessage());
               continue;
            }
         } catch (IllegalArgumentException var15) {
            if(!this.a.isClosed()) {
               b.a(this.a, "IllegalArgumentException " + var4 + " - " + var15.getMessage());
               continue;
            }
         }

         return;
      }
   }
}
