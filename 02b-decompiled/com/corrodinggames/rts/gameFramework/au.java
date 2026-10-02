package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.am;

class au extends Thread {

   // $FF: synthetic field
   final am a;


   au(am var1) {
      this.a = var1;
   }

   public void run() {
      while(true) {
         float var1 = 1.0F;
         Object var2 = this.a.c;
         synchronized(this.a.c) {
            this.a.g = true;
            if(!this.a.f) {
               try {
                  this.a.c.wait((long)am.a.e());
               } catch (InterruptedException var7) {
                  ;
               }
            }

            this.a.f = false;
            var1 = this.a.d;
         }

         var2 = this.a.b;
         synchronized(this.a.b) {
            if(!this.a.b(var1)) {
               return;
            }
         }
      }
   }
}
