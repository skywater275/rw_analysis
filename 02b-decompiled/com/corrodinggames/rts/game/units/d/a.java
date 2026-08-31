package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.b;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public class a extends i {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e[] c = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e e = null;
   int f = 1;
   float g = 0.0F;
   static final com.corrodinggames.rts.game.units.a.c h = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(110));


   public strictfp void a(as var1) {
      var1.a(this.f);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      if(var1.b() >= 17) {
         int var2 = var1.f();
         this.a(var2);
      }

      super.a(var1);
   }

   public static strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.air_factory);
      b = var0.bO.a(R$drawable.air_factory_t2);
      e = var0.bO.a(R$drawable.air_factory_dead);
      c = com.corrodinggames.rts.game.n.a(a);
      d = com.corrodinggames.rts.game.n.a(b);
   }

   public strictfp ar K() {
      return ar.c;
   }

   public strictfp boolean L() {
      this.M = e;
      this.S(0);
      this.bT = false;
      this.a(ab.d);
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?e:(this.bX == null?c[c.length - 1]:(this.f == 1?c[this.bX.R()]:d[this.bX.R()]));
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp a(boolean var1) {
      super(var1);
      this.M = a;
      this.T(40);
      this.U(61);
      this.cj = 30.0F;
      this.ck = this.cj;
      this.cv = 1000.0F;
      this.cu = this.cv;
      this.n.a(-1, -1, 1, 1);
      this.o.a(-1, -1, 1, 2);
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         this.g = com.corrodinggames.rts.gameFramework.f.a(this.g, var1);
         if(this.g == 0.0F) {
            this.g = 27.0F;
            ++this.s;
            if(this.s > 4) {
               this.s = 0;
            }
         }

      }
   }

   public strictfp void a(j var1) {
      if(var1.j.equals(h)) {
         com.corrodinggames.rts.game.n.b((am)this);
         this.a(2);
         com.corrodinggames.rts.game.n.c((am)this);
         this.W();
      } else {
         super.a(var1);
      }

   }

   public strictfp int V() {
      return this.f;
   }

   public strictfp void a(int var1) {
      if(var1 == 1) {
         this.f = 1;
      } else if(var1 == 2 && this.f == 1) {
         this.f = 2;
      }

      this.S();
   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return this.f == 1?h:com.corrodinggames.rts.game.units.a.s.i;
   }

   public static strictfp void a(ArrayList var0, int var1) {
      var0.add(new com.corrodinggames.rts.game.units.a.o());
      if(var1 == 1) {
         var0.add(new b());
      }

      if(var1 > 1) {
         var0.add(new com.corrodinggames.rts.game.units.a.l(ar.z, 3.2F));
         var0.add(new com.corrodinggames.rts.game.units.a.l(ar.n, 4.0F));
         var0.add(new com.corrodinggames.rts.game.units.a.l(ar.M, 5.0F));
      }

   }

   public strictfp ArrayList N() {
      return this.K().a(this.V());
   }

   public strictfp boolean bJ() {
      return true;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

}
