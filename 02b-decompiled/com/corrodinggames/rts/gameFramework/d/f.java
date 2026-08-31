package com.corrodinggames.rts.gameFramework.d;

import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.d.c;
import com.corrodinggames.rts.gameFramework.d.d;
import com.corrodinggames.rts.gameFramework.d.e;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public class f extends w {

   public float a;
   public boolean b = true;
   float c;
   float d;
   float e;
   float f;
   float g;
   float h;
   e i;
   public int j = 0;
   public int k = 0;
   public int l = -1;
   float m;
   float n;
   float o;
   float p;
   float q;
   float r;
   h s;
   public float t;
   public boolean u = false;
   static e v;
   static e w;
   private final c x;


   public static strictfp void b() {
      c var0 = l.B().bR;
      e var1 = new e(var0);
      a(var1, false);
      var1.aq = 18;
      var1.t = 15.0F;
      v = var1;
      var1 = new e(var0);
      b(var1, false);
      w = var1;
   }

   public strictfp void a(as var1) {
      var1.a(this.eo);
      var1.a(this.ep);
      var1.a(this.a);
      super.a(var1);
   }

   public strictfp void a(k var1) {
      this.eo = var1.g();
      this.ep = var1.g();
      this.a = var1.g();
      this.b = false;
      super.a(var1);
   }

   public strictfp f(c var1) {
      this.x = var1;
   }

   public static strictfp void a(e var0, boolean var1) {
      var0.c();
      var0.aq = 5;
      if(var1) {
         var0.ap = com.corrodinggames.rts.gameFramework.f.a(0, 1);
      } else {
         var0.ap = 0;
      }

      var0.Y = 0.0F;
      var0.an = true;
      var0.P = 0.1F;
      var0.R = 0.5F;
      var0.u = true;
      var0.V = 300.0F;
      var0.W = var0.V;
      var0.r = true;
      var0.s = true;
      var0.t = 40.0F;
      var0.as = false;
      var0.ar = 2;
      var0.G = 0.4F;
      var0.F = 1.5F;
      var0.g = e.k;
   }

   public static strictfp void b(e var0, boolean var1) {
      var0.c();
      var0.aq = 7;
      if(var1) {
         var0.ap = com.corrodinggames.rts.gameFramework.f.a(0, 3);
      } else {
         var0.ap = 0;
      }

      var0.Y = 0.0F;
      var0.an = true;
      var0.P = 0.0F;
      var0.R = 0.2F;
      var0.u = true;
      var0.V = 50.0F;
      var0.W = var0.V;
      var0.r = true;
      var0.s = true;
      var0.t = 10.0F;
      var0.as = false;
      var0.ar = 2;
      var0.g = e.n;
   }

   public static strictfp f a(float var0, float var1) {
      f var2 = a(var0, var1, v);
      var2.a = 280.0F;
      var2.f = 10.0F;
      var2.c = 10.0F;
      var2.m = 0.03F;
      var2.n = 0.03F;
      var2.p = 6.0F;
      var2.q = 6.0F;
      var2.s = h.a;
      var2.r = 180.0F;
      var2.j = -16777216;
      return var2;
   }

   public static strictfp f b(float var0, float var1) {
      f var2 = a(var0, var1, w);
      var2.a = 330.0F;
      var2.f = 10.0F;
      var2.c = 10.0F;
      var2.m = 0.1F;
      var2.n = 0.03F;
      var2.p = 4.0F;
      var2.q = 4.0F;
      var2.s = h.a;
      return var2;
   }

   public static strictfp f a(float var0, float var1, e var2) {
      c var3 = l.B().bR;
      f var4 = new f(var3);
      var4.eo = var0;
      var4.ep = var1;
      var4.a = 100.0F;
      var4.f = 10.0F;
      var4.i = var2;
      if(var2 == null) {
         var4.i = new e(var3);
         l.b("Error: Emitter create srcEffect==null");
      }

      return var4;
   }

   public strictfp boolean c() {
      l var1 = l.B();
      return var1.cP.b(this.eo, this.ep);
   }

   public strictfp void a(float var1) {
      this.t = com.corrodinggames.rts.gameFramework.f.a(this.t, var1);
      if(this.t <= 0.0F) {
         if(this.b) {
            this.c += var1;
            boolean var2 = false;
            if(this.c > this.f) {
               this.d += var1;
               if(this.d > this.g) {
                  this.d = 0.0F;
                  ++this.e;
                  if(this.e > this.h) {
                     this.c = 0.0F;
                     this.e = 0.0F;
                  }

                  if(this.u || this.c()) {
                     e var3 = this.x.b(this.eo, this.ep, 0.0F, d.a, false, this.s);
                     if(var3 != null) {
                        var3.a(this.i);
                        var3.P += com.corrodinggames.rts.gameFramework.f.c(-this.m, this.m);
                        var3.Q += com.corrodinggames.rts.gameFramework.f.c(-this.n, this.n);
                        var3.R += com.corrodinggames.rts.gameFramework.f.c(-this.o, this.o);
                        var3.Y = com.corrodinggames.rts.gameFramework.f.c(-this.r, this.r);
                        var3.I = this.eo;
                        var3.J = this.ep;
                        var3.I += com.corrodinggames.rts.gameFramework.f.c(-this.p, this.p);
                        var3.J += com.corrodinggames.rts.gameFramework.f.c(-this.q, this.q);
                        if(this.j != 0) {
                           var3.x = this.j;
                        }

                        if(this.l >= 0) {
                           var3.y = this.k;
                           var3.z = (float)this.l;
                        }
                     }
                  }
               }
            }
         }

         this.a -= var1;
         if(this.a < 0.0F) {
            this.a();
         }

      }
   }

   public strictfp boolean a(l var1) {
      return false;
   }

   public strictfp boolean c(float var1) {
      return false;
   }

   public strictfp void e(float var1) {}

   public strictfp void a(float var1, boolean var2) {}

   public strictfp void d(float var1) {}

   public strictfp boolean f(float var1) {
      return false;
   }
}
