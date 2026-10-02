package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.ag;
import java.io.IOException;
import java.net.Socket;

public class an implements Runnable {

   String a;
   boolean b;
   boolean c;
   Thread d;
   public String e;
   Runnable f;
   public Socket g;
   boolean h = false;


   public strictfp an(String var1, boolean var2, Runnable var3) {
      this.a = var1;
      this.b = var2;
      this.f = var3;
   }

   public strictfp boolean a() {
      if(!this.c) {
         return false;
      } else {
         this.h = true;
         return true;
      }
   }

   public strictfp void b() {
      this.c = true;
      this.d = new Thread(this);
      this.d.start();
   }

   public strictfp void run() {
      try {
         this.g = ad.b(this.a, this.b);
         return;
      } catch (IOException var14) {
         String var2 = var14.getMessage();
         this.e = var2;
         var14.printStackTrace();
      } catch (ag var15) {
         com.corrodinggames.rts.gameFramework.l.e("Cancelled connectSocketToServer");
         this.e = "CANCELLED";
         return;
      } finally {
         this.c = false;
         if(this.h) {
            if(this.g != null) {
               try {
                  this.g.close();
                  this.g = null;
                  this.e = "cancelled";
               } catch (IOException var13) {
                  var13.printStackTrace();
               }
            }
         } else {
            this.f.run();
         }

      }

   }
}
