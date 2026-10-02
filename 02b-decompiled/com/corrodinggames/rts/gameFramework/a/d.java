package com.corrodinggames.rts.gameFramework.a;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.a.a;
import com.corrodinggames.rts.gameFramework.a.c;

public class d extends Thread {

   // $FF: synthetic field
   final a a;


   public d(a var1) {
      this.a = var1;
   }

   public void run() {
      l.aq();

      try {
         while(true) {
            c var1 = (c)this.a.a.take();
            var1.a();
            this.a.c.a(var1);
         }
      } catch (InterruptedException var2) {
         ;
      }
   }
}
