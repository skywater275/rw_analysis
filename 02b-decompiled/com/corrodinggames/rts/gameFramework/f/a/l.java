package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.PointF;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.f.a.c;
import com.corrodinggames.rts.gameFramework.f.a.h;
import com.corrodinggames.rts.gameFramework.f.a.k;
import com.corrodinggames.rts.gameFramework.f.a.m;
import com.corrodinggames.rts.gameFramework.m.y;
import java.util.Iterator;

public abstract class l {

   static final PointF e = new PointF();
   static final RectF f = new RectF();
   float g;
   float h;
   float i = 50.0F;
   float j = 50.0F;
   float k;
   float l;
   float m;
   float n;
   float o;
   float p;
   float q;
   float r;
   boolean s = false;
   boolean t = false;
   boolean u;
   l v;
   com.corrodinggames.rts.gameFramework.utility.m w = new com.corrodinggames.rts.gameFramework.utility.m();
   m x;
   float y;
   float z;
   static final PointF A = new PointF();
   k B;


   public l() {
      this.x = m.b;
   }

   public String a() {
      return this.getClass().getSimpleName();
   }

   public y d() {
      return com.corrodinggames.rts.gameFramework.l.B().bO;
   }

   public RectF a(RectF var1, float var2, float var3) {
      var1.a = 0.0F + var2;
      var1.b = 0.0F + var3;
      var1.c = 0.0F + this.i + var2;
      var1.d = 0.0F + this.j + var3;
      return var1;
   }

   public RectF a(RectF var1) {
      A.a = this.g;
      A.b = this.h;
      if(this.v != null) {
         this.v.a(A);
      }

      var1.a = 0.0F + A.a;
      var1.b = 0.0F + A.b;
      var1.c = 0.0F + this.i + A.a;
      var1.d = 0.0F + this.j + A.b;
      return var1;
   }

   public void b() {
      Iterator var1 = this.w.iterator();

      while(var1.hasNext()) {
         l var2 = (l)var1.next();
         var2.b();
      }

      this.y = 0.0F;
      this.z = 0.0F;
      if(this.x != m.a) {
         Iterator var3;
         l var4;
         float var5;
         float var6;
         if(this.x == m.b) {
            var5 = 0.0F;
            var6 = 0.0F;

            for(var3 = this.w.iterator(); var3.hasNext(); var6 += var4.h()) {
               var4 = (l)var3.next();
               if(var4.i > var5) {
                  var5 = var4.g();
               }
            }

            this.y = var6;
            this.z = var5;
            b(this.z * 0.5F, this.y * 0.5F, this.w);
         } else {
            if(this.x != m.c) {
               throw new RuntimeException("Unknown layout style:" + this.x);
            }

            var5 = 0.0F;
            var6 = 0.0F;

            for(var3 = this.w.iterator(); var3.hasNext(); var6 += var4.g()) {
               var4 = (l)var3.next();
               if(var4.j > var5) {
                  var5 = var4.h();
               }
            }

            this.y = var5;
            this.z = var6;
            a(this.z * 0.5F, this.y * 0.5F, this.w);
         }
      }

      this.s = false;
   }

   public static void a(float var0, float var1, com.corrodinggames.rts.gameFramework.utility.m var2) {
      float var4 = var1;
      float var5 = 0.0F;

      Iterator var6;
      l var7;
      for(var6 = var2.iterator(); var6.hasNext(); var5 += var7.g()) {
         var7 = (l)var6.next();
      }

      float var3 = var0 - var5 * 0.5F;
      var6 = var2.iterator();

      while(var6.hasNext()) {
         var7 = (l)var6.next();
         var3 += var7.q;
         var7.g = var3;
         var3 += var7.i;
         var3 += var7.r;
         var7.d(var4);
      }

   }

   public static void b(float var0, float var1, com.corrodinggames.rts.gameFramework.utility.m var2) {
      float var3 = var0;
      float var5 = 0.0F;

      Iterator var6;
      l var7;
      for(var6 = var2.iterator(); var6.hasNext(); var5 += var7.h()) {
         var7 = (l)var6.next();
      }

      float var4 = var1 - var5 * 0.5F;
      var6 = var2.iterator();

      while(var6.hasNext()) {
         var7 = (l)var6.next();
         var4 += var7.o;
         var7.h = var4;
         var4 += var7.j;
         var4 += var7.p;
         var7.c(var3);
      }

   }

   public void a(PointF var1) {
      if(this.v != null) {
         this.v.a(var1);
      }

      var1.a += this.g;
      var1.b += this.h;
   }

   public void a(l var1) {
      var1.b(this);
   }

   public void b(l var1) {
      this.a(var1, false);
   }

   public void a(l var1, boolean var2) {
      if(this.v != var1) {
         if(this.v != null) {
            this.v.w.remove(this);
         }

         this.v = var1;
         if(var1 != null) {
            if(!var2) {
               var1.w.add(this);
            } else {
               var1.w.add(0, this);
            }
         }

         this.e();
      }
   }

   public void e() {
      this.s = true;
      if(this.v != null) {
         this.v.e();
      }

   }

   public void b(float var1) {
      if(this.w.size() > 0) {
         Iterator var2 = this.w.iterator();

         while(var2.hasNext()) {
            l var3 = (l)var2.next();
            var3.b(var1);
         }
      }

   }

   public void f() {
      A.a = this.g;
      A.b = this.h;
      if(this.v != null) {
         this.v.a(A);
      }

      this.a(A.a, A.b);
      if(this.w.size() > 0) {
         Iterator var1 = this.w.iterator();

         while(var1.hasNext()) {
            l var2 = (l)var1.next();
            var2.f();
         }
      }

   }

   public void a(float var1, float var2) {
      if(this.t) {
         y var3 = this.d();
         RectF var4 = this.a(new RectF(), var1, var2);
         h.m.a(var3, var4);
      }

   }

   public void a(k var1) {
      this.B = var1;
   }

   public boolean a(c var1) {
      if(var1.a() && this.c(var1)) {
         com.corrodinggames.rts.gameFramework.l.e("UI click " + this.a());
         return this.B != null?this.B.a(var1):false;
      } else if(var1.b()) {
         if(this.c(var1)) {
            this.u = true;
         } else {
            this.u = false;
         }

         return false;
      } else {
         return false;
      }
   }

   public boolean b(c var1) {
      if(this.w.size() > 0) {
         Iterator var2 = this.w.iterator();

         while(var2.hasNext()) {
            l var3 = (l)var2.next();
            if(var3.b(var1)) {
               return true;
            }
         }
      }

      return this.a(var1);
   }

   public boolean c(c var1) {
      this.a(f);
      return f.b((float)var1.a, (float)var1.b);
   }

   public void c(float var1) {
      this.g = var1 - this.i * 0.5F;
   }

   public void d(float var1) {
      this.h = var1 - this.j * 0.5F;
   }

   public void e(float var1) {
      this.o = var1;
      this.p = var1;
      this.q = var1;
      this.r = var1;
   }

   public void f(float var1) {
      this.k = var1;
      this.l = var1;
      this.m = var1;
      this.n = var1;
   }

   public float g() {
      return this.q + this.i + this.r;
   }

   public float h() {
      return this.o + this.j + this.p;
   }

   public void i() {
      this.b((l)null);
   }

}
