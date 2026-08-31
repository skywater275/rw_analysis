package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import com.corrodinggames.rts.appFramework.c;
import com.corrodinggames.rts.appFramework.g;

class g$11 implements Runnable {

   // $FF: synthetic field
   final Activity a;
   // $FF: synthetic field
   final g b;


   g$11(g var1, Activity var2) {
      this.b = var1;
      this.a = var2;
   }

   public void run() {
      if(c.b(this.a)) {
         g.a(this.b, (String)null);
      }

   }
}
