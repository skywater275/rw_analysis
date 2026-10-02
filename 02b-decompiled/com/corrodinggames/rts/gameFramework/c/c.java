package com.corrodinggames.rts.gameFramework.c;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.c.a;

class c implements Runnable {

   // $FF: synthetic field
   final a a;


   c(a var1) {
      this.a = var1;
   }

   public void run() {
      l var1 = l.B();
      if(this.a.f) {
         this.a.f = false;
      } else {
         if(a.c) {
            if(var1.bL == null) {
               return;
            }

            var1.bN.F = true;
            if(!var1.bN.j()) {
               ++a.e;
            }

            if(a.e > 5.0F) {
               a.e = 0.0F;
               System.gc();
               System.gc();
               var1.bN.e();
            }
         }

         if(a.d && var1.bL != null) {
            var1.bL.g();
         }

      }
   }
}
