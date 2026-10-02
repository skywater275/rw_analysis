package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$3$1;
import com.corrodinggames.rts.game.units.h$3$2;
import com.corrodinggames.rts.game.units.h$3$3;

final class h$3 extends com.corrodinggames.rts.game.units.a.x {

   h$3(String var1) {
      super(var1);
   }

   public String a() {
      return "Start playback of last a replay";
   }

   public String b() {
      return "Start Playback";
   }

   public String d() {
      String var1 = "Start Playback";
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = var2.cb.j();
      if(!var3) {
         var1 = "Start Playback";
      } else {
         var1 = "Stop Playback";
      }

      return var1;
   }

   public boolean a(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var4 = var3.cb.k();
      h var5 = h.L();
      return var5 == null?false:var5.r != null && !var4;
   }

   public boolean b(am var1) {
      h var2 = h.L();
      return var2 == null?false:var2.r != null;
   }

   public boolean a(am var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var3 = var2.cb.j();
      return var3;
   }

   public boolean c(am var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      String var4 = h.L().r;
      if(var4 == null) {
         var3.i("No last replay found");
         return false;
      } else {
         boolean var5 = var3.cb.j();
         if(!var5) {
            h$3$1 var6 = new h$3$1(this, var4);
            com.corrodinggames.rts.gameFramework.f.a.f var7 = com.corrodinggames.rts.gameFramework.f.a.f.a("Start playback of last recording?", true);
            var7.a(com.corrodinggames.rts.gameFramework.h.a.a("menus.common.ok", new Object[0]), new h$3$2(this, var7, var3, var6));
            var3.bS.a(var7);
         } else {
            h$3$3 var8 = new h$3$3(this);
            var3.a((Runnable)var8);
         }

         return false;
      }
   }
}
