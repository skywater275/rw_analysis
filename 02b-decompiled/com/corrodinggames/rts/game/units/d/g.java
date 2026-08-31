package com.corrodinggames.rts.game.units.d;

import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.g$1;
import com.corrodinggames.rts.game.units.d.g$2;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public class g extends i {

   float a;
   int b = 1;
   float c = 0.0F;
   int d = 0;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] f = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] g = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e h = null;
   static com.corrodinggames.rts.gameFramework.m.e i = null;
   Rect j = new Rect();
   Rect k = new Rect();
   public static int l = 0;
   static com.corrodinggames.rts.game.units.a.s t = new g$1(102);
   static com.corrodinggames.rts.game.units.a.s u = new g$2(103);
   static ArrayList v = new ArrayList();
   static ArrayList w;
   static ArrayList x;


   public strictfp void a(as var1) {
      var1.a(this.a);
      var1.a(this.b > 1);
      var1.a(this.b);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.a = var1.g();
      int var2 = 1;
      boolean var3 = var1.e();
      if(var3) {
         var2 = 2;
      }

      if(var1.b() >= 31) {
         var2 = var1.f();
      }

      if(var2 != 1) {
         this.a(var2);
      }

      super.a(var1);
   }

   public strictfp ar b() {
      return ar.a;
   }

   public strictfp boolean c(com.corrodinggames.rts.game.n var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var2.bL.a(this.eo, this.ep);
      com.corrodinggames.rts.game.b.g var3 = var2.bL.e(var2.bL.T, var2.bL.U);
      return var3 != null && var3.i?super.c(var1):false;
   }

   public static strictfp void K() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.extractor);
      com.corrodinggames.rts.gameFramework.m.e var2 = var0.bO.a(R$drawable.extractor_t2);
      com.corrodinggames.rts.gameFramework.m.e var3 = var0.bO.a(R$drawable.extractor_t3);
      i = var0.bO.a(R$drawable.extractor_dead);
      e = com.corrodinggames.rts.game.n.a(var1);
      f = com.corrodinggames.rts.game.n.a(var2);
      g = com.corrodinggames.rts.game.n.a(var3);
      var1.n();
      var2.n();
      var3.n();
      h = var0.bO.a(R$drawable.extractor_back);
   }

   public strictfp boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.a(this.eo, this.ep, this.eq);
      this.M = i;
      this.m = null;
      this.S(0);
      this.bT = false;
      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8F, this.eo, this.ep);
      com.corrodinggames.rts.gameFramework.d.f var2 = com.corrodinggames.rts.gameFramework.d.f.a(this.eo, this.ep);
      var2.j = -6684775;
      com.corrodinggames.rts.gameFramework.d.f var3 = com.corrodinggames.rts.gameFramework.d.f.b(this.eo, this.ep);
      var3.a = 500.0F;
      var3.j = -6684775;
      var1.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
      com.corrodinggames.rts.gameFramework.d.e var4 = var1.bR.c(this.eo, this.ep, this.eq, -1127220);
      if(var4 != null) {
         var4.G = 0.15F;
         var4.F = 1.0F;
         var4.ar = 2;
         var4.V = 35.0F;
         var4.W = var4.V;
         var4.U = 0.0F;
         var4.x = -13378253;
      }

      this.bo();
      return false;
   }

   public strictfp int bp() {
      return 16;
   }

   public strictfp void S() {
      super.S();
      if(this.bV) {
         this.m = null;
      } else {
         this.m = h;
      }

   }

   public strictfp boolean ds() {
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?i:(this.bX == null?e[e.length - 1]:(this.b == 3?g[this.bX.R()]:(this.b == 2?f[this.bX.R()]:e[this.bX.R()])));
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp g(boolean var1) {
      super(var1);
      this.M = e[9];
      this.T(37);
      this.U(56);
      this.cj = 18.0F;
      this.ck = this.cj;
      this.cv = 800.0F;
      this.cu = this.cv;
      this.n.a(0, -1, 0, 0);
      this.o.a(this.n);
      this.S();
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         this.c = com.corrodinggames.rts.gameFramework.f.a(this.c, var1 * (float)this.b);
         if(this.c == 0.0F) {
            this.c = 17.0F;
            ++this.d;
            if(this.d > 7) {
               this.d = 0;
            }

            if(this.d <= 3) {
               this.s = this.d;
            } else {
               this.s = 7 - this.d;
            }
         }

         this.a += var1;
         if(this.a > com.corrodinggames.rts.game.n.ap - 0.1F) {
            this.a -= com.corrodinggames.rts.game.n.ap;
            this.bX.b(this.cy() * (com.corrodinggames.rts.game.n.ap / com.corrodinggames.rts.game.n.ao));
         }

      }
   }

   public strictfp float cy() {
      return this.b == 3?18.0F:(this.b == 2?12.0F:8.0F);
   }

   public strictfp boolean c(float var1) {
      return super.c(var1);
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp void a(am var1, int var2) {
      throw new RuntimeException("Unit cannot shoot");
   }

   public strictfp float m() {
      return 0.0F;
   }

   public strictfp float b(int var1) {
      return 0.0F;
   }

   public strictfp float c(int var1) {
      return 0.0F;
   }

   public strictfp void a(j var1) {
      if(var1.j.equals(t.N())) {
         this.a(2);
         this.W();
      }

      if(var1.j.equals(u.N())) {
         this.a(3);
         this.W();
      }

   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return this.b == 1?t.N():(this.b == 2?u.N():com.corrodinggames.rts.game.units.a.s.i);
   }

   public strictfp int V() {
      return this.b;
   }

   public strictfp void a(int var1) {
      com.corrodinggames.rts.game.n.b((am)this);
      if(this.b > var1) {
         this.b = 1;
         this.cv = 800.0F;
         if(this.cu > this.cv) {
            this.cu = this.cv;
         }
      }

      if(this.b < 2 && var1 >= 2) {
         this.cv += 200.0F;
         this.cu += 200.0F;
      }

      if(this.b < 3 && var1 >= 3) {
         this.cv += 1000.0F;
         this.cu += 1000.0F;
      }

      this.b = var1;
      com.corrodinggames.rts.game.n.c((am)this);
      this.S();
   }

   public strictfp ArrayList N() {
      return this.b == 1?v:(this.b == 2?w:x);
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.b();
   }

   static {
      v.add(t);
      w = new ArrayList();
      w.add(u);
      x = new ArrayList();
   }
}
