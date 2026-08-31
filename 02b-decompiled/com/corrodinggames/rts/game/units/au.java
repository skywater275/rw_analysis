package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.y;

public final class au {

   av a;
   as b;
   com.corrodinggames.rts.game.units.a.c c;
   int d;
   float e = 1.0F;
   float f = 1.0F;
   long g = -1L;
   am h;
   public com.corrodinggames.rts.gameFramework.ab i;
   public boolean j;
   public float k = -1.0F;
   public float l = -1.0F;
   public boolean m;
   public boolean n;


   public strictfp boolean a(au var1) {
      return com.corrodinggames.rts.gameFramework.f.c(this.e - var1.e) <= 3.0F && com.corrodinggames.rts.gameFramework.f.c(this.f - var1.f) <= 3.0F;
   }

   public strictfp boolean b(au var1) {
      return var1 == null?false:(this.a != var1.a?false:(this.b != var1.b?false:(com.corrodinggames.rts.gameFramework.f.c(this.e - var1.e) <= 1.0F && com.corrodinggames.rts.gameFramework.f.c(this.f - var1.f) <= 1.0F?(this.d != var1.d?false:this.h == var1.h):false)));
   }

   public strictfp as a() {
      return this.b;
   }

   public strictfp int b() {
      return this.d;
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.a((Enum)this.a);
      var1.a(this.b);
      var1.a(this.e);
      var1.a(this.f);
      if(this.g != -1L) {
         var1.a(this.g);
      } else {
         var1.a(this.h);
      }

      var1.c(this.d);
      var1.a(this.k);
      var1.a(this.l);
      var1.a(this.m);
      var1.a(this.j);
      var1.a(this.n);
      com.corrodinggames.rts.game.units.a.c.a(var1, this.c);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.a = (av)var1.b(av.class);
      this.b = var1.q();
      this.e = var1.g();
      this.f = var1.g();
      this.g = var1.n();
      this.h = null;
      if(var1.b() >= 40) {
         this.d = var1.d();
      }

      if(var1.b() >= 46) {
         this.k = var1.g();
         this.l = var1.g();
      }

      if(var1.b() >= 58) {
         this.m = var1.e();
      }

      if(var1.b() >= 65) {
         this.j = var1.e();
      }

      if(var1.b() >= 79) {
         this.n = var1.e();
      }

      if(var1.b() >= 82) {
         this.c = com.corrodinggames.rts.game.units.a.c.a(var1);
      }

   }

   public strictfp void c() {
      if(this.g != -1L) {
         this.h = com.corrodinggames.rts.gameFramework.w.a(this.g, true);
         if(this.h == null) {
            com.corrodinggames.rts.gameFramework.l.b("convertUnitIds failed");
            if(this.a != null) {
               com.corrodinggames.rts.gameFramework.l.b("convertUnitIds: type:" + this.a.toString());
            }

            if(this.b != null) {
               com.corrodinggames.rts.gameFramework.l.b("convertUnitIds: build:" + this.b.toString());
            }

            com.corrodinggames.rts.gameFramework.l.b("convertUnitIds: x:" + this.e + ", y:" + this.f);
         }

         this.g = -1L;
      }

   }

   public strictfp av d() {
      return this.a;
   }

   public strictfp void e() {
      this.a = av.a;
      this.b = null;
      this.d = 1;
      this.e = 2.0F;
      this.f = 2.0F;
      this.g = -1L;
      this.h = null;
      this.i = null;
      this.k = -1.0F;
      this.l = -1.0F;
      this.m = false;
      this.j = false;
      this.n = false;
      this.c = null;
   }

   public strictfp boolean f() {
      return this.a == av.b || this.a == av.d || this.a == av.g || this.a == av.e || this.a == av.i || this.a == av.k || this.a == av.m || this.a == av.n;
   }

   public strictfp float g() {
      return this.f() && this.h != null?this.h.eo:this.e;
   }

   public strictfp float h() {
      return this.f() && this.h != null?this.h.ep:this.f;
   }

   public strictfp am i() {
      return this.h;
   }

   public strictfp void a(float var1, float var2) {
      this.e();
      this.a = av.a;
      this.e = var1;
      this.f = var2;
   }

   public strictfp void b(float var1, float var2) {
      this.e();
      this.a = av.h;
      this.e = var1;
      this.f = var2;
   }

   public strictfp void a(am var1) {
      this.e();
      this.a = av.b;
      this.h = var1;
   }

   public strictfp void a(float var1, float var2, as var3, int var4) {
      this.e();
      this.a = av.c;
      this.e = var1;
      this.f = var2;
      this.b = var3;
      byte var5 = (byte)var4;
      this.d = var5;
   }

   public strictfp void b(am var1) {
      this.e();
      this.a = av.d;
      this.h = var1;
   }

   public strictfp void c(am var1) {
      this.e();
      this.a = av.k;
      this.h = var1;
   }

   public strictfp void d(am var1) {
      this.e();
      this.a = av.m;
      this.h = var1;
   }

   public strictfp void e(am var1) {
      this.e();
      this.a = av.n;
      this.h = var1;
   }

   public strictfp void c(float var1, float var2) {
      this.e();
      this.a = av.j;
      this.e = var1;
      this.f = var2;
   }

   public strictfp void f(am var1) {
      this.e();
      this.a = av.g;
      this.h = var1;
   }

   public strictfp void g(am var1) {
      this.e();
      this.a = av.e;
      this.h = var1;
   }

   public strictfp void h(am var1) {
      this.e();
      this.a = av.i;
      this.h = var1;
   }

   public strictfp void c(au var1) {
      this.e();
      this.a = var1.a;
      this.b = var1.b;
      this.e = var1.e;
      this.f = var1.f;
      this.h = var1.h;
      this.i = var1.i;
      this.d = var1.d;
      this.j = var1.j;
      this.c = var1.c;
   }

   public strictfp long j() {
      long var1 = 0L;
      if(this.a != null) {
         var1 += (long)this.a.ordinal();
      }

      return var1;
   }

   public strictfp void k() {
      if(this.h != null) {
         this.g = this.h.eh;
         this.h = null;
      }

      this.i = null;
   }

   public strictfp am l() {
      if(this.f()) {
         am var2 = this.i();
         return var2;
      } else {
         y var1 = com.corrodinggames.rts.game.n.i.t;
         var1.cg = 0.0F;
         var1.eo = this.e;
         var1.ep = this.f;
         var1.eq = 0.0F;
         return var1;
      }
   }
}
