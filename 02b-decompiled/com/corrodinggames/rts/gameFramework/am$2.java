package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.am;

class am$2 extends Thread {

   // $FF: synthetic field
   final am a;


   am$2(am var1) {
      this.a = var1;
   }

   public void run() {
      Object var1 = this.a.b;
      synchronized(this.a.b) {
         if(this.a.l) {
            this.a.k.b();
            if(!this.a.C) {
               this.a.k.a(this.a.a(), this.a.a());
            }
         }

         if(this.a.B) {
            this.a.A.b();
         }

      }
   }
}
