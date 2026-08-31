package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.u;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

class u$1 implements Runnable {

   // $FF: synthetic field
   final String a;
   // $FF: synthetic field
   final ByteArrayOutputStream b;
   // $FF: synthetic field
   final String c;
   // $FF: synthetic field
   final u d;


   u$1(u var1, String var2, ByteArrayOutputStream var3, String var4) {
      this.d = var1;
      this.a = var2;
      this.b = var3;
      this.c = var4;
   }

   public void run() {
      try {
         FileOutputStream var1 = new FileOutputStream(this.a);

         try {
            var1.write(this.b.toByteArray());
         } finally {
            var1.close();
         }

         com.corrodinggames.rts.gameFramework.l.e("Screenshot saved: " + this.c);
      } catch (Exception var6) {
         var6.printStackTrace();
         com.corrodinggames.rts.gameFramework.l.n("Failed to write screenshot:" + var6.getMessage());
      }

   }
}
