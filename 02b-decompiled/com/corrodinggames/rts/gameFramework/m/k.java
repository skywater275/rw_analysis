package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.l;
import com.corrodinggames.rts.gameFramework.m.m;
import java.util.concurrent.locks.Lock;

public class k implements l {

   public com.corrodinggames.rts.gameFramework.b.f a;
   public com.corrodinggames.rts.gameFramework.b.n b;
   com.corrodinggames.rts.gameFramework.b.v c;
   public static boolean d = false;
   Rect e;
   RectF f;
   com.corrodinggames.rts.gameFramework.b.c g;
   RectF h;
   float[] i;
   static e j;
   static Rect k = new Rect(0, 0, 1, 1);
   static Paint l = new Paint();
   static Rect m = new Rect();
   e n;
   boolean o;


   public void b(e var1) {
      com.corrodinggames.rts.gameFramework.b.b var2 = this.a.a(var1.b(), var1, this.g);
      this.a.a(var2);
   }

   public void d() {
      this.a.a();
   }

   public com.corrodinggames.rts.gameFramework.b.v a(Paint var1) {
      this.b.a((ae)null);
      if(var1 == null) {
         return null;
      } else {
         this.c.a(var1.d());
         this.c.a(var1.e());
         this.c.a(var1.g());
         return this.c;
      }
   }

   public void a(Rect var1) {
      this.a.a(var1.a, var1.b, var1.c, var1.d);
   }

   public void a(RectF var1) {
      this.a.a((int)var1.a, (int)var1.b, (int)var1.c, (int)var1.d);
   }

   public void a(e var1, float var2, float var3, Paint var4) {
      this.e.a(0, 0, var1.m(), var1.l());
      this.f.a(var2, var3, var2 + (float)var1.m(), var3 + (float)var1.l());
      this.b(var1, this.e, this.f, var4);
   }

   public void a(e var1, Rect var2, Rect var3, Paint var4) {
      this.f.a(var3);
      this.b(var1, var2, this.f, var4);
   }

   public com.corrodinggames.rts.gameFramework.b.b a(Bitmap var1, e var2) {
      com.corrodinggames.rts.gameFramework.b.n var3 = this.b;
      if(var3.a == null) {
         var3.a = new com.corrodinggames.rts.gameFramework.b.ac(var3, 1024, 1024);
      }

      com.corrodinggames.rts.gameFramework.b.b var4;
      if(var1.b() < 450 && var1.c() < 100) {
         var4 = var3.a.a(var1);
         if(var4 != null) {
            return var4;
         }
      }

      var4 = this.a.a(var1, var2, this.g);
      return var4;
   }

   public void a(e var1, Rect var2, RectF var3, Paint var4) {
      this.b(var1, var2, var3, var4);
   }

   public void b(e var1, Rect var2, RectF var3, Paint var4) {
      Bitmap var5 = aa.a(var1);
      this.h.a(var2);
      if(var5 == null) {
         throw new RuntimeException("bitmap==null. sourceImage: " + var1.a());
      } else {
         com.corrodinggames.rts.gameFramework.b.b var6 = this.a(var5, var1);
         com.corrodinggames.rts.gameFramework.b.n var7 = this.b;
         boolean var8 = true;
         if(var4 == null) {
            var7.w = -1;
         } else {
            int var9 = var4.e();
            if(var9 != -1 && var4.h() == null) {
               var9 = Color.a(Color.a(var9), 255, 255, 255);
            }

            var7.w = var9;
            if(var4 instanceof ag) {
               var8 = ((ag)var4).p();
            } else {
               var8 = var4.c();
            }
         }

         var7.a(var6, var8?9729:9728);
         ae var11 = null;
         if(var4 instanceof ag) {
            var11 = ((ag)var4).q();
         }

         if(var1 != null && var11 == null) {
            var11 = var1.B();
         }

         if(var11 != null) {
            boolean var10 = var11.a(var4, var1);
            this.b.a(var11);
            if(var10) {
               this.b.e();
               this.b.o();
            }
         } else {
            this.b.a((ae)null);
         }

         var7.a(var6, this.h, var3, this.g, (com.corrodinggames.rts.gameFramework.b.l)null);
      }
   }

   public void a(Bitmap var1) {
      this.a.a(var1);
   }

   public void a(float var1, float var2, float var3, Paint var4) {
      this.a.a(var1, var2, var3, this.a(var4));
   }

   public void a(int var1, Mode var2) {
      this.b.a(this.b(var1));
   }

   public void a(int var1) {
      this.b.a(this.b(var1));
   }

   float[] b(int var1) {
      float var2 = (float)(var1 >>> 24 & 255) * 0.003921569F * 1.0F;
      float var3 = (float)(var1 >>> 16 & 255) * 0.003921569F * var2;
      float var4 = (float)(var1 >>> 8 & 255) * 0.003921569F * var2;
      float var5 = (float)(var1 & 255) * 0.003921569F * var2;
      this.i[0] = var2;
      this.i[1] = var3;
      this.i[2] = var4;
      this.i[3] = var5;
      return this.i;
   }

   public void a(float var1, float var2, float var3, float var4, Paint var5) {
      this.a.a(var1, var2, var3, var4, this.a(var5));
   }

   public void a(float[] var1, int var2, int var3, Paint var4) {
      com.corrodinggames.rts.gameFramework.b.v var5 = this.a(var4);
      this.a.a(var1, var2, var3, var5);
   }

   public void b(float var1, float var2, float var3, float var4, Paint var5) {
      if(j == null) {
         Bitmap var6 = Bitmap.a(1, 1, Bitmap$Config.d);
         var6.a(0, 0, -1);
         e var7 = new e();
         var7.a(var6);
         j = var7;
         l.a(false);
         l.a((ColorFilter)(new LightingColorFilter(-1, -16777216)));
      }

      l.b(var5.e());
      if(var5.d() == Paint$Style.b) {
         float var8 = var5.g();
         if(var8 == 0.0F) {
            var8 = 1.0F;
         }

         this.f.a(var1, var2, var3, var2 + var8);
         this.b(j, k, this.f, l);
         this.f.a(var1, var4, var3, var4 + var8);
         this.b(j, k, this.f, l);
         this.f.a(var1, var2, var1 + var8, var4);
         this.b(j, k, this.f, l);
         this.f.a(var3, var2, var3 + var8, var4);
         this.b(j, k, this.f, l);
      } else {
         this.f.a(var1, var2, var3, var4);
         this.b(j, k, this.f, l);
      }

   }

   public void a(Rect var1, Paint var2) {
      this.b((float)var1.a, (float)var1.b, (float)var1.c, (float)var1.d, var2);
   }

   public void a(RectF var1, Paint var2) {
      this.b(var1.a, var1.b, var1.c, var1.d, var2);
   }

   public void a(String var1, float var2, float var3, Paint var4) {
      this.b.b((ae)null);
      com.corrodinggames.rts.gameFramework.b.n.E = this;
      this.a.a(var1, var2, var3, var4);
   }

   public boolean equals(Object var1) {
      return this.a.equals(var1);
   }

   public int hashCode() {
      return this.a.hashCode();
   }

   public void a() {
      this.b.c();
   }

   public void a(float var1, float var2, float var3) {
      com.corrodinggames.rts.gameFramework.b.n var4 = this.b;
      var4.a(var2, var3);
      var4.a(var1);
      var4.a(-var2, -var3);
   }

   public void b() {
      this.b.b();
   }

   public void a(float var1, float var2, float var3, float var4) {
      com.corrodinggames.rts.gameFramework.b.n var5 = this.b;
      var5.a(var3, var4);
      var5.a(var1, var2, 1.0F);
      var5.a(-var3, -var4);
   }

   public void a(float var1, float var2) {
      this.b.a(var1, var2, 1.0F);
   }

   public void a(e var1) {
      if(this.n != null) {
         this.d();
      }

      if(var1 != null) {
         this.b(var1);
      }

      this.n = var1;
   }

   public String toString() {
      return this.a.toString();
   }

   public void b(float var1, float var2) {
      this.b.a(var1, var2);
   }

   public void a(boolean var1) {
      this.o = var1;
   }

   public boolean c() {
      return this.o;
   }

   public void a(m var1) {
      var1.a(com.corrodinggames.rts.gameFramework.l.B().bO);
   }

   public void a(Lock var1) {}

   public void b(Lock var1) {}

   public boolean a(ae var1) {
      return this.b.d(var1);
   }

}
