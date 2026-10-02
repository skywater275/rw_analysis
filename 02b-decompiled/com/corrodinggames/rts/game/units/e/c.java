package com.corrodinggames.rts.game.units.e;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.e.h;
import com.corrodinggames.rts.game.units.e.l;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.utility.y;

public class c extends h {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   public static com.corrodinggames.rts.gameFramework.m.e d = null;
   public static com.corrodinggames.rts.gameFramework.m.e e = null;
   static com.corrodinggames.rts.gameFramework.m.e[] f = new com.corrodinggames.rts.gameFramework.m.e[10];
   int g;
   float h = 0.0F;
   com.corrodinggames.rts.game.f i;
   Rect j = new Rect();
   Paint k = y.a();


   public strictfp ar b() {
      return ar.O;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.experimental_hovertank);
      f = com.corrodinggames.rts.game.n.a(var1);
      a = var0.bO.a(R$drawable.experimental_hovertank_dead);
      b = var0.bO.a(R$drawable.experimental_hovertank_turret);
      c = a(var1, var1.m() / 1, var1.l());
      d = var0.bO.a(R$drawable.experimental_hovertank_shield);
      e = var0.bO.a(R$drawable.shield_mid);
   }

   public strictfp void a(as var1) {
      if(this.i != null && this.i.ej) {
         this.i = null;
      }

      var1.a((w)this.i);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.i = (com.corrodinggames.rts.game.f)var1.a(com.corrodinggames.rts.game.f.class);
      super.a(var1);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:f[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && this.eq > -2.0F;
   }

   public strictfp float G() {
      return 4.0F;
   }

   public strictfp float H() {
      return 4.0F;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return b;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e T() {
      return d;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = a;
      this.S(0);
      this.bT = false;
      this.a(ab.e);
      return true;
   }

   public strictfp c(boolean var1) {
      super(var1);
      this.a(f[7], 1);
      this.cj = 30.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 3500.0F;
      this.cu = this.cv;
      this.cA = 5000.0F;
      this.cx = this.cA;
      this.M = f[7];
   }

   public strictfp float bW() {
      return this.cA > 0.0F && this.cx < this.cA?this.cx / this.cA:super.bW();
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV && this.bT()) {
         if(!this.bV) {
            if(this.cl != 0.0F) {
               this.S(2);
            } else {
               this.S(4);
            }
         }

         if(this.cK) {
            ;
         }

         this.h += 1.0F * var1;
         if(this.h > 360.0F) {
            this.h -= 360.0F;
         }

         this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 4.0F + com.corrodinggames.rts.gameFramework.f.j(this.h) * 2.0F, 0.1F * var1);
         this.cx = com.corrodinggames.rts.gameFramework.f.a(this.cx, this.cA, 0.25F * var1);
         this.cy = com.corrodinggames.rts.gameFramework.f.a(this.cy, 0.0F, 4.0F * var1);
         if(this.cy > 50.0F) {
            this.cy = 50.0F;
         }

         if(this.i != null) {
            PointF var2 = this.E(0);
            this.i.eo = var2.a;
            this.i.ep = var2.b;
            this.i.eq = this.eq;
            if(this.i.ej) {
               this.i = null;
            }
         }

      }
   }

   public strictfp float bN() {
      return 80000.0F;
   }

   public strictfp float L(int var1) {
      return 0.0F;
   }

   public strictfp PointF K(int var1) {
      PointF var2 = super.K(var1);
      if(this.i != null) {
         var2.a += this.i.K;
         var2.b += this.i.L;
      }

      return var2;
   }

   public strictfp float q(int var1) {
      return 0.0F;
   }

   public strictfp void a(am var1, int var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      PointF var4 = this.E(var2);
      if(this.i != null) {
         boolean var5 = false;
         if(this.i.ej) {
            var5 = true;
         }

         if(this.i.l != var1) {
            var5 = true;
         }

         if(var5) {
            this.i = null;
         }
      }

      float var7 = this.b(var2) + this.e(var2) + 5.0F;
      if(this.i != null) {
         this.i.h = var7;
      } else {
         com.corrodinggames.rts.game.f var6 = com.corrodinggames.rts.game.f.a(this, var4.a, var4.b);
         var6.U = 380.0F;
         var6.l = var1;
         var6.h = var7;
         var6.B = true;
         var6.A = true;
         var6.aQ = true;
         var6.E = true;
         var6.J = 70.0F;
         var6.F = 230.0F;
         var6.ak = 0.75F;
         var6.em = this.em;
         this.i = var6;
      }

   }

   public strictfp float m() {
      return 180.0F;
   }

   public strictfp float b(int var1) {
      return 8.0F;
   }

   public strictfp float e(int var1) {
      return 8.0F;
   }

   public strictfp float z() {
      return 0.6F;
   }

   public strictfp float bc() {
      return 1.0F;
   }

   public strictfp float A() {
      return 1.1F;
   }

   public strictfp float B() {
      return 0.03F;
   }

   public strictfp float c(int var1) {
      return 1.5F;
   }

   public strictfp float C() {
      return 0.02F;
   }

   public strictfp float D() {
      return 0.02F;
   }

   public strictfp Rect a_(boolean var1) {
      if(this.bV && !var1) {
         return super.a_(var1);
      } else if(var1) {
         return super.a_(var1);
      } else {
         byte var2 = 0;
         byte var3 = 0;
         int var4 = var2 + this.g * this.es;
         this.j.a(var4, var3, var4 + this.es, var3 + this.et);
         return this.j;
      }
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         y.a((com.corrodinggames.rts.game.units.y)this);
         if(!this.bV) {
            float var2 = 0.0F;
            if(this.i != null) {
               var2 = com.corrodinggames.rts.gameFramework.f.b(this.i.e(), 0.25F) * 3.0F;
            }

            y.a(this, l.e, var2, 0);
         }

         com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
         if(!this.bV && this.cx > 0.0F && this.cz == 0.0F) {
            com.corrodinggames.rts.gameFramework.m.e var3 = this.T();
            if(var3 != null) {
               float var4 = 0.09F;
               var4 += this.cx / this.cA * 0.4F;
               var4 += com.corrodinggames.rts.gameFramework.f.b(this.cy, 50.0F) / 50.0F * 0.5F;
               this.k.a((int)(var4 * 255.0F), 255, 255, 255);
               float var5 = this.eo - var7.cw;
               float var6 = this.ep - var7.cx - this.eq;
               var7.bO.a(var3, var5, var6, this.d(false) - 90.0F, this.k);
            }
         }

         return true;
      }
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return true;
   }

   public strictfp float g(int var1) {
      return 8.0F;
   }

   public strictfp PointF G(int var1) {
      float var2 = this.eo;
      float var3 = this.ep;
      bh.a(var2, var3);
      return bh;
   }

   public strictfp int bl() {
      return 1;
   }

   public strictfp boolean bi() {
      return true;
   }

   public strictfp boolean bj() {
      return true;
   }

   public strictfp int cw() {
      return 5;
   }

   public strictfp boolean dd() {
      return true;
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      y.a(this, var2);
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.b();
   }

}
