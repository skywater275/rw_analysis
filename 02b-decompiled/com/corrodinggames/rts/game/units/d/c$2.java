package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.c;

final class c$2 extends com.corrodinggames.rts.game.units.a.w {

   c$2(int var1) {
      super(var1);
   }

   public boolean g() {
      return false;
   }

   public String a() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.buildAntiNuke.description", new Object[0]);
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.buildAntiNuke", new Object[0]);
   }

   public int c() {
      return 4000;
   }

   public float K() {
      return 7.0E-4F;
   }

   public boolean a(am var1, boolean var2) {
      c var3 = (c)var1;
      float var4 = (float)(var3.d + var3.a(this.N(), var2));
      return var4 >= 12.0F?false:super.a(var1, var2);
   }

   public ar L() {
      return null;
   }

   public com.corrodinggames.rts.game.units.a.t f() {
      return com.corrodinggames.rts.game.units.a.t.d;
   }

   // $FF: synthetic method
   public as i() {
      return this.L();
   }
}
