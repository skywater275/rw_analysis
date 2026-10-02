package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.o;
import com.corrodinggames.rts.java.p;

public class r extends Thread {

   // $FF: synthetic field
   final o a;


   public r(o var1) {
      this.a = var1;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l.aq();

      try {
         while(true) {
            p var1 = (p)this.a.b.take();
            var1.a();
            this.a.c.a(var1);
         }
      } catch (InterruptedException var2) {
         ;
      }
   }
}
