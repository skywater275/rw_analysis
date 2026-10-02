package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.i;

public class w extends i {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e[] b = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e c = null;


   public static strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.wall_v);
      c = var0.bO.a(R$drawable.wall_v);
      b = com.corrodinggames.rts.game.n.a(a);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?c:(this.bX == null?b[b.length - 1]:b[this.bX.R()]);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp void a(int var1) {}

   public strictfp w(boolean var1) {
      super(var1);
      this.b(a);
      this.cj = 15.0F;
      this.ck = this.cj;
      this.cv = 700.0F;
      this.cu = this.cv;
      this.M = a;
      this.n.a(0, 0, 1, 0);
      this.o.a(0, 0, 1, 0);
   }

   public strictfp ar K() {
      return ar.I;
   }

   // $FF: synthetic method
   public as r() {
      return this.K();
   }

}
