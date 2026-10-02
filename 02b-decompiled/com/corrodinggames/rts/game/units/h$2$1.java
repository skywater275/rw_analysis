package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$2;

class h$2$1 implements Runnable {

   // $FF: synthetic field
   final h$2 a;


   h$2$1(h$2 var1) {
      this.a = var1;
   }

   public void run() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var2 = var1.cb.k();
      if(!var2) {
         h var3 = h.L();
         var1.bS.e = true;
         if(!var1.bX.B) {
            long var4 = var1.bX.w;
            var1.bX.o = true;
            int var6 = var1.bX.ay.d;
            var1.bX.R();
            var1.bX.ay.d = var6;
            var1.bX.w = var4;
            var1.bX.aW = true;
            var1.bx = 0;
            var1.bX.X = var1.bx + 1;
            var1.bX.w();
         }

         String var7 = "[sandbox]" + var1.al() + " [v" + var1.v() + "] (" + com.corrodinggames.rts.gameFramework.f.a("d MMM yyyy HH.mm.ss") + ").replay";
         var1.cb.d(var7);
         var1.bS.e = false;
         com.corrodinggames.rts.gameFramework.l.f((String)null, "Replay started as: " + var7);
         h var5 = h.L();
         if(var5 != null && var3 != null) {
            var5.a(var3);
            var5.r = var7;
         } else {
            com.corrodinggames.rts.gameFramework.l.b("Failed copySettingsFromAnotherEditor");
         }
      } else {
         var1.cb.e();
      }

   }
}
