package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h$2$1;

final class h$2 extends com.corrodinggames.rts.game.units.a.x {

   h$2(String var1) {
      super(var1);
   }

   public String a() {
      return "Start recording a replay to file";
   }

   public String b() {
      return "Start Recording";
   }

   public String d() {
      String var1 = "Start Recording";
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = var2.cb.k();
      if(!var3) {
         var1 = "Start Recording";
      } else {
         var1 = "Stop Recording";
      }

      return var1;
   }

   public boolean a(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var4 = var3.cb.j();
      return !var4;
   }

   public boolean a(am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = var2.cb.k();
      return var3;
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.e("Start recording clicked");
      if(var3.cb.j()) {
         com.corrodinggames.rts.gameFramework.l.e("Already in a replay");
         return false;
      } else {
         var3.a((Runnable)(new h$2$1(this)));
         return false;
      }
   }
}
