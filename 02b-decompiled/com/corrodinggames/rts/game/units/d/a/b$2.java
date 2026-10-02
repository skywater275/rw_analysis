package com.corrodinggames.rts.game.units.d.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.game.units.d.a.f;

final class b$2 extends w {

   b$2(int var1) {
      super(var1);
   }

   public boolean g() {
      return false;
   }

   public String a() {
      return "-Extra attack damage, and range.\n-Large amount of HP\n-Self repair";
   }

   public String b() {
      return com.corrodinggames.rts.gameFramework.h.a.a("gui.actions.upgradeToGunT3", new Object[0]);
   }

   public int c() {
      return 11000;
   }

   public float K() {
      return 3.0E-4F;
   }

   public boolean a(am var1, boolean var2) {
      b var3 = (b)var1;
      return var3.a(s.i, var2) > 0?false:super.a(var1, var2);
   }

   public boolean b(am var1) {
      b var2 = (b)var1;
      return var2.l instanceof f;
   }

   public ar L() {
      return null;
   }

   public t f() {
      return t.c;
   }

   public void f(am var1) {
      b var2 = (b)var1;
      var2.b(b.v);
      b.b(var2);
   }

   // $FF: synthetic method
   public as i() {
      return this.L();
   }
}
