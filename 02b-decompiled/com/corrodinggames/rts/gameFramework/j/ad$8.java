package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.an;
import java.io.IOException;

class ad$8 implements Runnable {

   // $FF: synthetic field
   final boolean a;
   // $FF: synthetic field
   final ad b;


   strictfp ad$8(ad var1, boolean var2) {
      this.b = var1;
      this.a = var2;
   }

   public strictfp void run() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.e("startJoinServerInternalThread callback");
      an var2 = this.b.bF;
      this.b.bF = null;
      if(var2 == null) {
         com.corrodinggames.rts.gameFramework.l.e("startJoinServerInternalThread callback gameConnector==null");
      } else if(var2.e != null) {
         com.corrodinggames.rts.gameFramework.l.e("startJoinServerInternalThread failed to connect: " + var2.e);
         if(this.a) {
            var1.bX.b("Reconnect failed: " + var2.e);
            this.b.b("Reconnect failed", "reconnect failed");
            var1.d("Reconnect failed", "Reconnect failed: " + var2.e);
            var1.i("Reconnect failed: " + var2.e);
         }

      } else {
         try {
            var1.bX.b("starting new");
            var1.bX.a(var2.g);
         } catch (IOException var5) {
            String var4 = var5.getMessage();
            var1.c(var4, "Connection failed");
            var5.printStackTrace();
         }

      }
   }
}
