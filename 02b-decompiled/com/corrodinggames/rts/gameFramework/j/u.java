package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.j.s;
import java.io.BufferedReader;
import java.net.UnknownHostException;
import java.util.List;

class u implements Runnable {

   int a;
   List b;
   s c;
   String d;
   boolean e;


   public u(List var1, s var2, String var3, boolean var4, int var5) {
      this.a = var5;
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();

      try {
         if(n.a) {
            com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", this.a + ": Started doSingleRequest");
         }

         BufferedReader var2 = n.a(this.b, this.d, this.e).a;
         if(n.a) {
            com.corrodinggames.rts.gameFramework.l.b("LoadFromMasterServer", this.a + ": Ended doSingleRequest");
         }

         this.c.a(var2, this.a, this.d);
      } catch (Exception var7) {
         var7.printStackTrace();
         String var3 = com.corrodinggames.rts.gameFramework.f.a(var7, true);
         if(var7 instanceof UnknownHostException) {
            var3 = "DNS lookup failed, check your internet connection";
         }

         if(var3 != null && var3.contains("Cleartext HTTP traffic")) {
            var3 = var3 + " ( Broken apk file? - " + var1.l() + ")";
         }

         this.c.d = "#" + this.a + ": " + var3;
         String var4 = "Error getting game list from server #" + this.a;
         com.corrodinggames.rts.gameFramework.l.e(var4);
         if(var1.p()) {
            var1.a("Error getting game list from server #" + this.a, 1);
         }
      }

      s var8 = this.c;
      synchronized(this.c) {
         --this.c.f;
         if(this.c.f == 0) {
            this.c.a();
         }

      }
   }
}
