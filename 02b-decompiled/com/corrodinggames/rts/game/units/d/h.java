package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.h$1;
import com.corrodinggames.rts.game.units.d.h$2;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public class h extends i {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] f = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e g = null;
   float h;
   float i = 0.0F;
   int j = 0;
   static com.corrodinggames.rts.game.units.a.s k = new h$1(102);
   static com.corrodinggames.rts.game.units.a.s l = new h$2(103);
   static ArrayList t = new ArrayList();


   public void a(as var1) {
      var1.a(this.h);
      var1.a(this.r == 2);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.h = var1.g();
      boolean var2 = var1.e();
      if(var1.b() < 51 && var2) {
         this.a(2);
      }

      super.a(var1);
   }

   public void R(int var1) {
      this.a(var1);
   }

   public ar b() {
      return ar.J;
   }

   public static void K() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.power);
      b = var0.bO.a(R$drawable.power_t2);
      c = var0.bO.a(R$drawable.power_t3);
      d = com.corrodinggames.rts.game.n.a(a);
      e = com.corrodinggames.rts.game.n.a(b);
      f = com.corrodinggames.rts.game.n.a(c);
      g = var0.bO.a(R$drawable.power_dead);
   }

   public boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.a(this.eo, this.ep, this.eq);
      this.M = g;
      this.S(0);
      this.bT = false;
      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8F, this.eo, this.ep);
      var1.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
      com.corrodinggames.rts.gameFramework.d.e var2 = var1.bR.c(this.eo, this.ep, this.eq, -1127220);
      if(var2 != null) {
         var2.G = 0.15F;
         var2.F = 1.0F;
         var2.ar = 2;
         var2.V = 35.0F;
         var2.W = var2.V;
         var2.U = 0.0F;
         var2.x = -14492382;
      }

      this.bo();
      return true;
   }

   public com.corrodinggames.rts.gameFramework.m.e d() {
      if(this.bV) {
         return g;
      } else if(this.bX == null) {
         return d[d.length - 1];
      } else if(this.r == 1) {
         return d[this.bX.R()];
      } else if(this.r == 2) {
         return e[this.bX.R()];
      } else if(this.r == 3) {
         return f[this.bX.R()];
      } else {
         com.corrodinggames.rts.gameFramework.l.e("Unknown tech level:" + this.r);
         return d[this.bX.R()];
      }
   }

   public com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public h(boolean var1) {
      super(var1);
      this.M = a;
      this.a(this.M, 3);
      this.cj = 25.0F;
      this.ck = this.cj;
      this.cv = 800.0F;
      this.cu = this.cv;
      this.n.a(-1, -1, 1, 1);
      this.o.a(this.n);
   }

   public void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         this.i = com.corrodinggames.rts.gameFramework.f.a(this.i, var1);
         if(this.i == 0.0F) {
            this.i = 17.0F;
            ++this.j;
            if(this.j > 5) {
               this.j = 0;
            }

            if(this.j <= 2) {
               this.s = this.j;
            } else {
               this.s = 5 - this.j;
            }
         }

         this.h += var1;
         if(this.h > com.corrodinggames.rts.game.n.ap - 0.1F) {
            this.h -= com.corrodinggames.rts.game.n.ap;
            this.bX.b(this.cy() * (com.corrodinggames.rts.game.n.ap / com.corrodinggames.rts.game.n.ao));
         }

      }
   }

   public float cy() {
      return this.r == 1?2.0F:(this.r == 2?7.0F:14.0F);
   }

   public void a(j var1) {
      if(var1.j.equals(k.N())) {
         this.a(2);
         this.W();
      }

      if(var1.j.equals(l.N())) {
         this.a(3);
         this.W();
      }

   }

   public com.corrodinggames.rts.game.units.a.c cm() {
      return this.r == 1?k.N():(this.r == 2?l.N():com.corrodinggames.rts.game.units.a.s.i);
   }

   public int V() {
      return this.r;
   }

   public void a(int var1) {
      com.corrodinggames.rts.game.n.b((am)this);
      if(this.r > var1) {
         this.r = 1;
         this.cv = 800.0F;
         if(this.cu > this.cv) {
            this.cu = this.cv;
         }
      }

      if(this.r < 2 && var1 >= 2) {
         this.cv += 500.0F;
         this.cu += 500.0F;
      }

      if(this.r < 3 && var1 >= 3) {
         this.cv += 1300.0F;
         this.cu += 1300.0F;
      }

      this.r = var1;
      com.corrodinggames.rts.game.n.c((am)this);
      this.S();
   }

   public ArrayList N() {
      return t;
   }

   public float db() {
      return super.db() - 8.0F;
   }

   public int bp() {
      return 12;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.b();
   }

   static {
      t.add(k);
      t.add(l);
   }
}
