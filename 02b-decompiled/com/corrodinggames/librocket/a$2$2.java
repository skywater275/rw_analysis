package com.corrodinggames.librocket;

import com.corrodinggames.librocket.a$2;
import com.corrodinggames.librocket.scripts.Root;
import com.corrodinggames.rts.gameFramework.l;

class a$2$2 implements Runnable {

   // $FF: synthetic field
   final Root a;
   // $FF: synthetic field
   final a$2 b;


   a$2$2(a$2 var1, Root var2) {
      this.b = var1;
      this.a = var2;
   }

   public void run() {
      if(this.b.a.a) {
         l.b("AskPasswordCallBack already called");
      } else {
         this.b.a.a = true;
         this.a.closeAlertOnly();
         this.b.c.a();
      }
   }
}
