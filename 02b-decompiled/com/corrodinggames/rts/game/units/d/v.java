package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.v$1;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public class v extends i {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e[] c = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e e = null;
   int f = 1;
   float g = 0.0F;
   int h = 0;
   public static int i = 0;
   static com.corrodinggames.rts.game.units.a.s j = new v$1(102);
   static ArrayList k = new ArrayList();


   public void a(as var1) {
      var1.a(this.f);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      int var2 = var1.f();
      this.a(var2);
      super.a(var1);
   }

   public ar b() {
      return ar.N;
   }

   public static void K() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.supply_depot);
      b = var0.bO.a(R$drawable.supply_depot_t2);
      c = com.corrodinggames.rts.game.n.a(a);
      d = com.corrodinggames.rts.game.n.a(b);
      e = var0.bO.a(R$drawable.supply_depot_dead);
   }

   public boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.a(this.eo, this.ep, this.eq);
      this.M = e;
      this.S(0);
      this.bT = false;
      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8F, this.eo, this.ep);
      return false;
   }

   public com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?e:(this.bX == null?c[c.length - 1]:(this.f == 1?c[this.bX.R()]:d[this.bX.R()]));
   }

   public com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public v(boolean var1) {
      super(var1);
      this.M = a;
      this.a(this.M, 1);
      this.cj = 20.0F;
      this.ck = this.cj;
      this.cv = 800.0F;
      this.cu = this.cv;
      this.n.a(-1, -1, 0, 0);
      this.o.a(this.n);
   }

   public void a(float var1) {
      super.a(var1);
      if(!this.bT() || this.bV) {
         ;
      }
   }

   public void a(j var1) {
      if(var1.j.equals(j.N())) {
         this.M();
         this.W();
      }

   }

   public void a(int var1) {
      this.f = var1;
   }

   public void M() {
      if(this.f == 1) {
         this.f = 2;
         this.S();
      }

   }

   public com.corrodinggames.rts.game.units.a.c cm() {
      return this.f == 1?j.N():com.corrodinggames.rts.game.units.a.s.i;
   }

   public ArrayList N() {
      return k;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.b();
   }

   static {
      k.add(j);
   }
}
