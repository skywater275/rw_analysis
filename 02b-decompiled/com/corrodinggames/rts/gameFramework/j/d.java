package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.au;
import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.c$1;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

final class d implements Runnable {

   Boolean a;
   // $FF: synthetic field
   final c b;


   private strictfp d(c var1) {
      this.b = var1;
      this.a = Boolean.valueOf(true);
   }

   public strictfp void run() {
      com.corrodinggames.rts.gameFramework.l.aq();
      Thread.currentThread().setName("ReceiveWorker-" + this.b.g());

      try {
         this.a();
      } catch (EOFException var4) {
         this.b.a("network:ReceiveWorker: EOF reading packet", var4);
      } catch (IOException var5) {
         if(!this.b.a) {
            var5.printStackTrace();
         }

         if(com.corrodinggames.rts.gameFramework.l.aZ && var5 instanceof SocketException && !this.b.a) {
            com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
            if(!var2.bX.C && var2.bX.aW) {
               String var3 = var5.getMessage();
               if(var3 != null && var3.contains("EBADF")) {
                  var2.i("Warning: This disconnect likely due to iOS removing sockets of background apps. Avoid minimising the game in multiplayer. Note: Games can be rejoined.");
               }
            }
         }

         this.b.c("network:ReceiveWorker: " + var5.getMessage());
      } catch (OutOfMemoryError var6) {
         com.corrodinggames.rts.gameFramework.l.c((Throwable)var6);
         this.b.c("network:ReceiveWorker OutOfMemoryError: " + var6.getMessage());
      }

      c.a(this.b, true, false);
   }

   strictfp void a() {
      InputStream var1 = this.b.d.getInputStream();
      DataInputStream var2 = new DataInputStream(var1);

      while(this.a.booleanValue() && !this.b.a && !this.b.d.isClosed()) {
         int var3 = var2.readInt();
         int var4 = var2.readInt();
         if(var3 > 20000000) {
            this.b.b("readData(): new packet of type:" + var4 + " has size of:" + var3);
         }

         if(var3 > 10000) {
            int var5 = 50000000;
            if(c.a(this.b).C) {
               var5 = 1000000;
            }

            if(!this.b.p) {
               var5 = 10000;
            }

            if(var3 > var5) {
               this.b.b("Requested packet too large rejecting (max:" + var5 + ")");
               return;
            }
         }

         if(var3 < 0) {
            this.b.b("Requested packet negative size:" + var3 + " rejecting");
            return;
         }

         au var8 = new au(var4);
         var8.c = new byte[var3];
         this.b.V = 0;
         this.b.U = var3;
         int var6 = 0;

         for(var8.a = this.b; var6 < var3 && !this.b.a; this.b.V = var6) {
            int var7 = var2.read(var8.c, var6, var3 - var6);
            if(var7 == -1) {
               this.b.b("we got to the end of the stream?!?");
               return;
            }

            var6 += var7;
            ++this.b.P;
         }

         this.b.U = 0;
         this.b.V = 0;
         if(!this.b.a) {
            if(var8.b > 100) {
               c.a(this.b).c(var8);
            } else {
               c.a(this.b).aN.add(var8);
            }
         }
      }

   }

   // $FF: synthetic method
   d(c var1, c$1 var2) {
      this(var1);
   }
}
