package com.corrodinggames.rts.gameFramework.b;

import android.graphics.RectF;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.b.ab;
import com.corrodinggames.rts.gameFramework.b.k;
import java.util.WeakHashMap;

public abstract class b implements ab {

   protected int a;
   protected int b;
   protected int c;
   protected int d;
   protected int e;
   protected int f;
   protected float g;
   protected float h;
   private boolean l;
   private boolean m;
   public int i;
   public boolean j;
   protected k k;
   private static WeakHashMap n = new WeakHashMap();
   private static ThreadLocal o = new ThreadLocal();


   protected b(k var1, int var2, int var3) {
      this.a = -1;
      this.c = -1;
      this.d = -1;
      this.k = null;
      this.a(var1);
      this.a = var2;
      this.b = var3;
      WeakHashMap var4 = n;
      synchronized(n) {
         n.put(this, (Object)null);
      }
   }

   protected b() {
      this((k)null, 0, 0);
   }

   protected void a(k var1) {
      this.k = var1;
   }

   public void a(int var1, int var2) {
      this.c = var1;
      this.d = var2;
      this.e = var1 > 0?a(var1):0;
      this.f = var2 > 0?a(var2):0;
      if(this.e == 0) {
         this.g = 0.0F;
      } else {
         this.g = 1.0F / (float)this.e;
      }

      if(this.f == 0) {
         this.h = 0.0F;
      } else {
         this.h = 1.0F / (float)this.f;
      }

      if(this.e > 4096 || this.f > 4096) {
         Log.a("BasicTexture", String.format("secondBitmap is too large: %d x %d", new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.f)}), new Exception());
      }

   }

   public static int a(int var0) {
      if(var0 > 0 && var0 <= 1073741824) {
         --var0;
         var0 |= var0 >> 16;
         var0 |= var0 >> 8;
         var0 |= var0 >> 4;
         var0 |= var0 >> 2;
         var0 |= var0 >> 1;
         return var0 + 1;
      } else {
         throw new IllegalArgumentException("n is invalid: " + var0);
      }
   }

   public int a() {
      return this.a;
   }

   public int b() {
      return this.c;
   }

   public int c() {
      return this.d;
   }

   public int d() {
      return this.e;
   }

   public int e() {
      return this.f;
   }

   public boolean f() {
      return this.l;
   }

   protected void a(boolean var1) {
      this.l = var1;
   }

   public abstract void b(k var1);

   protected abstract boolean c(k var1);

   protected abstract int g();

   public abstract void b(int var1);

   public abstract int h();

   public boolean i() {
      return this.b == 1;
   }

   public void j() {
      this.m = true;
      this.k();
   }

   private void k() {
      k var1 = this.k;
      if(var1 != null && this.a != -1) {
         var1.a(this);
         this.a = -1;
      }

      this.b = 0;
      this.a((k)null);
   }

   protected void finalize() {
      o.set(b.class);
      this.j();
      o.set((Object)null);
   }

   public void a(RectF var1) {
      int var2 = this.b();
      int var3 = this.c();
      int var4 = this.d();
      int var5 = this.e();
      var1.a /= (float)var4;
      var1.c /= (float)var4;
      var1.b /= (float)var5;
      var1.d /= (float)var5;
      float var6 = (float)var2 / (float)var4;
      if(var1.c > var6) {
         var1.c = var6;
      }

      float var7 = (float)var3 / (float)var5;
      if(var1.d > var7) {
         var1.d = var7;
      }

   }

   public void a(RectF var1, RectF var2) {
      float var4 = (float)this.c() / (float)this.e();
      float var5 = (float)this.b() / (float)this.d();
      if(var1.c > var5) {
         var2.c = var2.a + var2.b() * (var5 - var1.a) / var1.b();
      }

      if(var1.d > var4) {
         var2.d = var2.b + var2.c() * (var4 - var1.b) / var1.c();
      }

   }

}
