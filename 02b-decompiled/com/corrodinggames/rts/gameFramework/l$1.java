package com.corrodinggames.rts.gameFramework;

import android.widget.Toast;
import com.corrodinggames.rts.gameFramework.l;

class l$1 implements Runnable {

   // $FF: synthetic field
   final l a;


   l$1(l var1) {
      this.a = var1;
   }

   public void run() {
      String var1 = this.a.dE;

      try {
         if(var1 == null) {
            l.b("Cannot show toast, no message");
            return;
         }

         byte var2 = 1;
         Toast var3 = Toast.makeText(this.a.am, var1, var2);
         var3.show();
      } catch (Exception var4) {
         l.b("Error showing toast: " + var1);
         var4.printStackTrace();
      }

   }
}
