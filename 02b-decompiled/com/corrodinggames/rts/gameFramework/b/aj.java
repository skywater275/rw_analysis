package com.corrodinggames.rts.gameFramework.b;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.ak;
import com.corrodinggames.rts.gameFramework.b.al;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.n;

public class aj {

   al a;
   float[] b;
   int c;
   int d;
   int e;
   n f;
   int g;
   public ak h;
   b i;
   com.corrodinggames.rts.gameFramework.m.ae j;
   boolean k;
   int l;
   int m;
   float n;
   float o;
   float p;
   float q;
   float r;
   float s;
   float t;


   public void a(com.corrodinggames.rts.gameFramework.m.ae var1) {
      this.c = 0;
      this.d = 0;
      this.i = null;
      this.b(var1);
      this.a();
      n.q();
      this.a.d();
      n.q();
   }

   public void a() {
      this.h.c.a(this.f.d);
   }

   void b(com.corrodinggames.rts.gameFramework.m.ae var1) {
      if(var1 != null) {
         this.f.a(var1.n);
         this.f.c(var1);
         this.k = false;
      } else {
         this.f.a(this.g);
      }

      this.j = var1;
   }

   public void b() {
      if(this.c > 0) {
         n.q();
         this.a.a(this.b, 0, this.c);
         this.a.b();
         n.q();
         this.a.a(this.l, 0, this.c);
         this.a.c();
         n.q();
         this.c = 0;
         this.d = 0;
      }

   }

   public void c() {
      this.b();
      this.i = null;
      this.a.e();
      n.q();
   }

   public void a(int var1) {
      if(this.m != var1) {
         this.m = var1;
         float var2 = (float)(var1 >>> 24 & 255) * 0.003921569F * 1.0F;
         float var3 = (float)(var1 >>> 16 & 255) * 0.003921569F * var2;
         float var4 = (float)(var1 >>> 8 & 255) * 0.003921569F * var2;
         float var5 = (float)(var1 & 255) * 0.003921569F * var2;
         this.n = var3;
         this.o = var4;
         this.p = var5;
         this.q = var2;
      }
   }

   public void b(int var1) {
      if(this.l != var1) {
         this.b();
         this.l = var1;
      }

   }

   public void a(float var1) {
      if(this.t != var1) {
         this.b();
         GLES20.glLineWidth(var1);
         this.t = var1;
      }

   }

   public void a(float var1, float var2) {
      if(this.c + 8 + 24 >= this.e && this.l == 1 && this.d % 2 == 0) {
         this.b();
      }

      if(this.c + 8 >= this.e) {
         this.b();
      }

      n.q();
      if(this.k && this.j != null) {
         this.f.c(this.j);
         this.k = false;
      }

      n.q();
      this.b[this.c++] = var1;
      this.b[this.c++] = var2;
      this.b[this.c++] = this.r;
      this.b[this.c++] = this.s;
      this.b[this.c++] = this.n;
      this.b[this.c++] = this.o;
      this.b[this.c++] = this.p;
      this.b[this.c++] = this.q;
      ++this.d;
      n.q();
   }

   public void a(float var1, float var2, float[] var3) {
      float var4 = var3[0];
      float var5 = var3[4];
      float var6 = var3[1];
      float var7 = var3[5];
      float var8 = var3[12];
      float var9 = var3[13];
      float var10 = var1 * var4 + var2 * var5 + var8;
      float var11 = var1 * var6 + var2 * var7 + var9;
      this.a(var10, var11);
   }
}
