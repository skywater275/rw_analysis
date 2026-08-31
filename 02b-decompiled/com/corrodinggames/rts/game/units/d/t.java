package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.u;
import java.util.ArrayList;

public class t extends i {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e f = null;
   static final com.corrodinggames.rts.game.units.a.c g = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(110));


   public static strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.sea_factory);
      b = var0.bO.a(R$drawable.sea_factory_t2);
      f = var0.bO.a(R$drawable.sea_factory_dead);
      d = com.corrodinggames.rts.game.n.a(a);
      e = com.corrodinggames.rts.game.n.a(b);
   }

   public strictfp ar K() {
      return ar.d;
   }

   public strictfp boolean L() {
      this.m = null;
      this.M = f;
      this.S(0);
      this.bT = false;
      this.a(ab.d);
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?f:(this.bX == null?d[d.length - 1]:(this.r == 1?d[this.bX.R()]:e[this.bX.R()]));
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp t(boolean var1) {
      super(var1);
      this.M = a;
      this.b(a);
      this.cj = 45.0F;
      this.ck = this.cj;
      this.cv = 1000.0F;
      this.cu = this.cv;
      this.S(2);
      this.n.a(-1, -1, 1, 2);
      this.o.a(-2, -1, 2, 4);
   }

   public strictfp void a(j var1) {
      if(var1.j.equals(g)) {
         com.corrodinggames.rts.game.n.b((am)this);
         this.a(2);
         com.corrodinggames.rts.game.n.c((am)this);
         this.W();
      } else {
         super.a(var1);
      }

   }

   public strictfp int dv() {
      return -20;
   }

   public strictfp int V() {
      return this.r;
   }

   public strictfp void a(int var1) {
      if(var1 == 1) {
         this.r = 1;
      } else if(var1 == 2 && this.r == 1) {
         this.r = 2;
      }

      this.S();
   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return this.r == 1?g:com.corrodinggames.rts.game.units.a.s.i;
   }

   public static strictfp void a(ArrayList var0, int var1) {
      var0.add(new com.corrodinggames.rts.game.units.a.o());
      var0.add(new u());
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.L, 1.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.p, 2.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.o, 3.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.s, 4.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.u, 5.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.K, 6.0F));
      if(var1 > 1) {
         ;
      }

   }

   public strictfp ArrayList N() {
      return this.K().a(this.V());
   }

   public strictfp boolean bJ() {
      return true;
   }

   // $FF: synthetic method
   public as r() {
      return this.K();
   }

}
