package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.k;
import com.corrodinggames.rts.game.units.d.n;
import com.corrodinggames.rts.game.units.d.o;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public class m extends i {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e f = null;
   boolean g;
   static final com.corrodinggames.rts.game.units.a.c h = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(110));


   public strictfp void a(as var1) {
      var1.a(this.g);
      var1.c(0);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      boolean var2 = var1.e();
      if(var2) {
         this.a(2);
      }

      var1.d();
      super.a(var1);
   }

   public static strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.land_factory_front);
      b = var0.bO.a(R$drawable.land_factory_front_t2);
      c = var0.bO.a(R$drawable.land_factory_back);
      f = var0.bO.a(R$drawable.land_factory_dead);
      d = com.corrodinggames.rts.game.n.a(a);
      e = com.corrodinggames.rts.game.n.a(b);
   }

   public strictfp ar K() {
      return ar.b;
   }

   public strictfp boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.a(this.eo, this.ep, this.eq);
      this.m = null;
      this.M = f;
      this.S(0);
      this.bT = false;
      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8F, this.eo, this.ep);
      return true;
   }

   public strictfp void S() {
      super.S();
      if(this.bV) {
         this.m = null;
      } else {
         this.m = c;
      }

   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?f:(this.bX == null?d[d.length - 1]:(!this.g?d[this.bX.R()]:e[this.bX.R()]));
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp m(boolean var1) {
      super(var1);
      this.M = a;
      this.m = c;
      this.b(this.M);
      this.cj = 30.0F;
      this.ck = this.cj;
      this.cv = 1200.0F;
      this.cu = this.cv;
      this.S(3);
      this.n.a(-1, -1, 1, 1);
      this.o.a(-1, -1, 1, 3);
   }

   public strictfp void a(j var1) {
      if(h.a(var1.j)) {
         com.corrodinggames.rts.game.n.b((am)this);
         this.a(2);
         com.corrodinggames.rts.game.n.c((am)this);
         this.W();
      } else {
         super.a(var1);
      }

   }

   public strictfp void a(int var1) {
      if(var1 == 1) {
         this.g = false;
      } else if(var1 == 2 && !this.g) {
         this.g = true;
      }

      this.S();
   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return !this.g?h:com.corrodinggames.rts.game.units.a.s.i;
   }

   public static strictfp void a(ArrayList var0, int var1) {
      var0.add(new com.corrodinggames.rts.game.units.a.o());
      if(var1 == 1) {
         var0.add(new n());
      }

      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.h, 1.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.i, 2.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.j, 3.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.k, 4.0F));
      if(var1 >= 2) {
         var0.add(new com.corrodinggames.rts.game.units.a.l(ar.s, 5.0F));
         var0.add(new com.corrodinggames.rts.game.units.a.l(ar.w, 6.0F));
         var0.add(new com.corrodinggames.rts.game.units.a.l(ar.x, 7.0F));
         var0.add(new com.corrodinggames.rts.game.units.a.l(ar.r, 8.0F));
      }

   }

   public strictfp ArrayList N() {
      return this.K().a(this.V());
   }

   public strictfp int V() {
      return this.g?2:1;
   }

   public strictfp k du() {
      return new o(this);
   }

   public strictfp boolean bJ() {
      return true;
   }

   public strictfp float db() {
      return super.db() - 8.0F;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

}
