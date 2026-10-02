package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$3;

class h$3$1 implements Runnable {

   // $FF: synthetic field
   final String a;
   // $FF: synthetic field
   final h$3 b;


   h$3$1(h$3 var1, String var2) {
      this.b = var1;
      this.a = var2;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var2 = var1.cb.j();
      if(!var2) {
         boolean var3 = var1.bL.E;
         h var4 = h.L();
         boolean var5 = var1.dq;
         boolean var6 = var1.dr;
         var1.cb.h = true;
         var1.cb.c(this.a);
         var1.cb.h = false;
         var1.dq = var5;
         var1.dr = var6;
         h var7 = h.L();
         if(var7 != null && var4 != null) {
            var7.a(var4);
         } else {
            com.corrodinggames.rts.gameFramework.l.b("Failed copySettingsFromAnotherEditor");
         }

         var1.bv = true;
         if(var1.bL != null) {
            var1.bL.E = var3;
         }

         var1.cU = true;
         if(var7 != null) {
            var7.M();
         }
      } else {
         com.corrodinggames.rts.gameFramework.l.e("stopPlaybackRunnable: Already started");
      }

   }
}
