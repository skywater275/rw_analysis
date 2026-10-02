package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.Main;

class Main$3 implements Runnable {

   // $FF: synthetic field
   final Main a;


   Main$3(Main var1) {
      this.a = var1;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.e("got startGameEvent..");
      com.corrodinggames.rts.appFramework.n.r();
      if(var1.bL != null && var1.bL.W) {
         var1.bX.bd = true;
         var1.bH = false;
         var1.aq = false;
         this.a.i.c(false);
         com.corrodinggames.librocket.a.a().f();
         this.a.p.getActiveDocument();
         if(this.a.p.c != null) {
            this.a.p.c.getRoot().resumeNonMenu();
         } else {
            com.corrodinggames.rts.gameFramework.l.e("startGameEvent: scriptEngine==null");
            com.corrodinggames.rts.gameFramework.l.T();
         }

      } else {
         com.corrodinggames.rts.gameFramework.l.e("Not starting multiplayer game because map failed to load");
         var1.bX.af();
      }
   }
}
