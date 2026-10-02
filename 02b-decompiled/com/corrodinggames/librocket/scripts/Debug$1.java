package com.corrodinggames.librocket.scripts;

import com.corrodinggames.librocket.scripts.Debug;
import com.corrodinggames.rts.gameFramework.j.c;
import java.util.Iterator;

class Debug$1 implements Runnable {

   // $FF: synthetic field
   final Debug this$0;


   Debug$1(Debug var1) {
      this.this$0 = var1;
   }

   public void run() {
      c var2;
      for(Iterator var1 = this.this$0.backgroundClientConnections.iterator(); var1.hasNext(); var2 = (c)var1.next()) {
         ;
      }

   }
}
