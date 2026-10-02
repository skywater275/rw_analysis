package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.f$1;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public class f extends i {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e[] c = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e e = null;
   boolean f;
   static com.corrodinggames.rts.game.units.a.s g = new f$1(110);


   public strictfp void a(as var1) {
      var1.a(this.f);
      var1.c(0);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      boolean var2 = var1.e();
      if(var2) {
         this.M();
      }

      var1.d();
      super.a(var1);
   }

   public static strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.experimental_unit_factory_front);
      b = var0.bO.a(R$drawable.experimental_unit_factory_base);
      e = var0.bO.a(R$drawable.experimental_unit_factory_dead);
      c = com.corrodinggames.rts.game.n.a(a);
   }

   public strictfp ar K() {
      return ar.G;
   }

   public strictfp boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.m = null;
      this.M = e;
      this.S(0);
      this.bT = false;
      this.a(ab.h);
      return true;
   }

   public strictfp void a(int var1) {}

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?e:(this.bX == null?c[c.length - 1]:(!this.f?c[this.bX.R()]:d[this.bX.R()]));
   }

   public strictfp void S() {
      super.S();
      if(this.bV) {
         this.m = null;
      } else {
         this.m = b;
      }

   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp f(boolean var1) {
      super(var1);
      this.M = a;
      this.m = b;
      this.b(this.M);
      this.cj = 55.0F;
      this.ck = this.cj;
      this.cv = 3200.0F;
      this.cu = this.cv;
      this.S(4);
      this.n.a(-2, -2, 2, 2);
      this.o.a(-2, -2, 2, 4);
   }

   public strictfp void a(j var1) {
      if(var1.j.equals(g.N())) {
         this.M();
      } else {
         super.a(var1);
      }

   }

   public strictfp void M() {
      if(!this.f) {
         this.f = true;
         this.S();
      }

   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return com.corrodinggames.rts.game.units.a.s.i;
   }

   public static strictfp void a(ArrayList var0, int var1) {
      var0.add(new com.corrodinggames.rts.game.units.a.o());
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.F, 2.0F));
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.O, 3.0F));
   }

   public strictfp ArrayList N() {
      return this.K().a(this.V());
   }

   public strictfp boolean bJ() {
      return true;
   }

   public strictfp int V() {
      return 2;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

}
