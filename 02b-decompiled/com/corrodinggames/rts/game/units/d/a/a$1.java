package com.corrodinggames.rts.game.units.d.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.d.a.b;

final class a$1 extends w {

   a$1(int var1) {
      super(var1);
   }

   public boolean g() {
      return false;
   }

   public String a() {
      return "-Increases HP, attack damage, and range";
   }

   public String b() {
      return "Upgrade";
   }

   public int c() {
      return 1200;
   }

   public float K() {
      return 0.001F;
   }

   public boolean a(am var1, boolean var2) {
      b var3 = (b)var1;
      return !var3.j && var3.a(this.N(), var2) <= 0?super.a(var1, var2):false;
   }

   public boolean b(am var1) {
      b var2 = (b)var1;
      return !var2.j;
   }

   public ar L() {
      return null;
   }

   public t f() {
      return t.c;
   }

   // $FF: synthetic method
   public as i() {
      return this.L();
   }
}
