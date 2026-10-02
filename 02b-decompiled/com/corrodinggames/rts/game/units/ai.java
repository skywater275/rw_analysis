package com.corrodinggames.rts.game.units;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.aj;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.v;
import com.corrodinggames.rts.game.units.y;

public class ai extends v {

   static com.corrodinggames.rts.gameFramework.m.e[] a = new com.corrodinggames.rts.gameFramework.m.e[2];
   com.corrodinggames.rts.gameFramework.m.e b;
   int c;
   int d = 0;
   float e;
   float f;
   int g = 0;
   int h = 0;
   float i;
   float j;
   boolean k = false;
   float l;
   float m;
   float n;
   float o;
   float p;
   float q;
   boolean r;
   static Point s = new Point();
   public static aj t = new aj();
   Rect u = new Rect();


   public static void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a[0] = var0.bO.a(R$drawable.fire);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.a(this.c);
      var1.a(this.d);
      var1.a(this.e);
      var1.c(0);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.c = var1.f();
      this.d = var1.f();
      this.e = var1.g();
      var1.d();
      super.a(var1);
   }

   public com.corrodinggames.rts.gameFramework.m.e d() {
      return this.b;
   }

   public boolean e() {
      return false;
   }

   public ai(boolean var1) {
      super(var1);
      this.a(0);
      this.cj = 20.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 100.0F;
      this.cu = this.cv;
      this.cg = -90.0F;
      this.bT = false;
      this.o = 0.05F;
      this.p = 120.0F;
      this.S(3);
   }

   public void f_() {
      this.bT = false;
   }

   public void a(int var1) {
      this.c = var1;
      if(this.c == 0) {
         this.T(20);
         this.U(20);
         this.g = 0;
         this.h = 0;
         this.b = a[0];
      } else {
         throw new RuntimeException("Fire type:" + this.c + " is not supported");
      }
   }

   public void f() {
      this.k = true;
      this.i = (float)com.corrodinggames.rts.gameFramework.f.a(this, -5, 5, 1);
      this.j = (float)com.corrodinggames.rts.gameFramework.f.a(this, -5, 5, 2);
      this.e = (float)com.corrodinggames.rts.gameFramework.f.a(this, 1, 10, 3);
      this.d = com.corrodinggames.rts.gameFramework.f.a(this, 0, 2, 4);
      this.f = (float)com.corrodinggames.rts.gameFramework.f.a(this, 7, 13, 5);
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var2 = var1.bL;
      var1.bL.a(this.eo, this.ep);
      int var3 = var1.bL.T;
      int var4 = var1.bL.U;
      if(!var2.c(var3, var4)) {
         this.l = 0.0F;
         this.m = 0.0F;
         this.n = 2.0F;
      } else {
         com.corrodinggames.rts.game.b.g var5 = var1.bL.u.a(var3, var4);
         boolean var6 = false;
         if(var5.e || var5.h || var5.k || var5.f) {
            var6 = true;
         }

         if(var6) {
            this.l = 0.0F;
            this.m = 0.0F;
            this.n = 2.0F;
         } else {
            this.l = 5.0E-4F;
            this.m = 1.0F;
            this.n = 0.3F;
            this.o += (float)com.corrodinggames.rts.gameFramework.f.a(this, 0, 10, 10) / 1000.0F;
         }
      }
   }

   public void a(float var1) {
      super.a(var1);
      if(!this.k) {
         this.f();
      }

      if(this.o < this.m) {
         this.o += this.l * var1;
         if(this.o > this.m) {
            this.o = this.m;
         }
      }

      if(this.o > this.n) {
         this.q = (float)((double)this.q + 0.01D * (double)var1);
         if(!this.r && this.q > 1.0F || this.q > 8.0F) {
            this.q = (float)com.corrodinggames.rts.gameFramework.f.a(this, 0, 10, 10) / 1000.0F;
            this.k();
         }
      }

      this.e += var1;
      if(this.e > 10.0F) {
         this.e = 0.0F;
         ++this.d;
         if(this.d > 3) {
            this.d = 0;
         }
      }

      if(this.o < 0.0F) {
         this.bv();
      }

   }

   public void k() {
      this.r = true;
      this.b(-1, -1);
      this.b(0, -1);
      this.b(1, -1);
      this.b(-1, 0);
      this.b(1, 0);
      this.b(-1, 1);
      this.b(0, 1);
      this.b(1, 1);
   }

   public void b(int var1, int var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      float var4 = (float)((int)(this.eo + (float)(var1 * var3.bL.n)));
      float var5 = (float)((int)(this.ep + (float)(var2 * var3.bL.o)));
      ai var6 = a(var4, var5);
      if(var6 == null) {
         ai var7 = new ai(false);
         var7.eo = var4;
         var7.ep = var5;
         var7.b(this.bX);
         var3.cc.a((am)var7);
         com.corrodinggames.rts.game.n.c((am)var7);
         this.r = false;
      }

   }

   public static ai a(float var0, float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      t.a(var0, var1);
      var2.cc.a(var0, var1, 30.0F, (y)null, 1.0F, t);
      return t.c;
   }

   public Rect a_(boolean var1) {
      int var2 = this.g;
      int var3 = this.h;
      var2 += this.d * this.es;
      dC.a(var2, var3, var2 + this.es, var3 + this.et);
      return dC;
   }

   public boolean c(float var1) {
      com.corrodinggames.rts.gameFramework.m.e var2 = this.d();
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      du.a(this.cF());
      du.a(0.0F, (float)((int)(-this.eq)));
      du.a(this.i, this.j);
      dv.a(this.a_(false));
      var3.bO.k();
      float var4 = du.d();
      float var5 = du.e();
      var3.bO.a(this.d(false), var4, var5);
      var3.bO.a(this.o * 2.7F, this.o * 2.7F, var4, var5);
      var3.bO.a(var2, dv, du, (Paint)null);
      var3.bO.l();
      return true;
   }

   public ao h() {
      return ao.a;
   }

   public boolean i() {
      return false;
   }

   public boolean Q() {
      return false;
   }

   public boolean ak() {
      return false;
   }

   public boolean aj() {
      return false;
   }

   public boolean s_() {
      return true;
   }

   public boolean c_() {
      return false;
   }

   public ar s() {
      return ar.S;
   }

   public void n() {
      super.n();
   }

   public float x() {
      return -1.0F;
   }

   public boolean l() {
      return false;
   }

   public boolean P() {
      return true;
   }

   public float a(am var1, float var2, com.corrodinggames.rts.game.f var3) {
      this.o -= var2 / 100.0F;
      var2 = 0.0F;
      return super.a(var1, var2, var3);
   }

   // $FF: synthetic method
   public as r() {
      return this.s();
   }

}
