package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import java.util.TimerTask;

class o extends TimerTask {

   int a;


   o(int var1) {
      this.a = var1;
   }

   public void run() {
      n.a(this.a, -1);
   }
}
