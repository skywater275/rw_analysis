package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.af$1;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.g;
import com.corrodinggames.rts.gameFramework.j.k;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Timer;

final class af implements Runnable {

   boolean a;
   DatagramSocket b;
   Timer c;
   // $FF: synthetic field
   final ad d;


   public strictfp void run() {
      try {
         this.d.d("starting socket for broadcast..");
         this.b = new DatagramSocket((SocketAddress)null);
         this.b.setReuseAddress(true);
         this.b.bind(new InetSocketAddress(this.d.t));
         this.d.d("reading..");
         byte[] var1 = new byte[1500];
         DatagramPacket var2 = new DatagramPacket(var1, var1.length);
         af$1 var3 = new af$1(this);
         this.c = new Timer();
         this.c.scheduleAtFixedRate(var3, 20L, 5000L);

         while(this.a) {
            this.b.receive(var2);
            String var4 = new String(var2.getData(), var2.getOffset(), var2.getLength());
            this.d.d("accepted udp socket..");
            k var5 = new k(var4);
            if(!var5.l().equals("com.corrodinggames.rts")) {
               this.d.d("ignoring udp packet: MAGIC_GAME_ID doesn\'t match");
            } else {
               int var6 = var5.f();
               var5.f();
               String var7 = var5.l();
               if(var7.equals("ping")) {
                  this.d.d("got ping");
                  if(this.d.C) {
                     as var8 = new as();
                     var8.c("com.corrodinggames.rts");
                     var8.a(this.d.e);
                     var8.a((int)0);
                     var8.c("pong");
                     var8.a(this.d.m);
                     String var9 = var8.c();
                     DatagramPacket var10 = new DatagramPacket(var9.getBytes(), var9.length(), var2.getAddress(), this.d.t);
                     this.b.send(var10);
                  } else {
                     this.d.d("not server");
                  }
               } else if(!var7.equals("pong")) {
                  this.d.d("got pong");
                  g var13 = new g();
                  var13.a = true;
                  var13.g = var5.f();
                  var13.c = var2.getAddress().toString();
                  var13.j = "" + var6;
                  this.d.a(var13);
               } else {
                  this.d.d("ignoring udp packet: unknown mode:" + var7);
               }
            }
         }
      } catch (SocketException var11) {
         if(this.a) {
            throw new RuntimeException(var11);
         }

         var11.printStackTrace();
      } catch (IOException var12) {
         throw new RuntimeException(var12);
      }

   }

   public strictfp void a() {
      this.d.d("sending ping");
      if(this.b == null) {
         this.d.d("failed to send a broadcast ping: datagramSocket is null");
      } else {
         InetAddress var1 = this.d.al();
         if(var1 == null) {
            this.d.d("failed to send a broadcast ping: could not get a broadcast address");
         } else {
            try {
               as var2 = new as();
               var2.c("com.corrodinggames.rts");
               var2.a(this.d.e);
               var2.a((int)0);
               var2.c("ping");
               String var3 = var2.c();
               this.d.d("sending ping on :" + var1.toString());
               DatagramPacket var4 = new DatagramPacket(var3.getBytes(), var3.length(), var1, this.d.t);
               this.b.send(var4);
            } catch (IOException var5) {
               var5.printStackTrace();
               this.d.d("failed to send a broadcast ping, IOException");
            }
         }
      }
   }

   public strictfp void b() {
      this.a = false;
      if(this.b != null) {
         this.b.close();
      }

      if(this.c != null) {
         this.c.cancel();
      }

   }
}
