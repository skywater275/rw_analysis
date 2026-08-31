package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.j;
import com.corrodinggames.rts.gameFramework.m.l;
import com.corrodinggames.rts.gameFramework.m.m;
import com.corrodinggames.rts.gameFramework.m.p;
import com.corrodinggames.rts.gameFramework.m.q;
import com.corrodinggames.rts.gameFramework.m.r;
import com.corrodinggames.rts.gameFramework.m.s;
import com.corrodinggames.rts.gameFramework.m.t;
import com.corrodinggames.rts.gameFramework.m.u;
import java.util.concurrent.locks.Lock;

public final class o extends u {

   l a = new j((Canvas)null);
   int b;
   final com.corrodinggames.rts.gameFramework.utility.m c = new com.corrodinggames.rts.gameFramework.utility.m();
   final q d = new q(Paint.class);
   final q e = new q(Rect.class);
   final q f = new q(RectF.class);
   final q g = new q(Matrix.class);
   final q h = new q(r.class);
   final t i = new t(200);
   int j = 0;
   volatile boolean k = false;


   public o() {
      this.c.add(this.d);
      this.c.add(this.e);
      this.c.add(this.f);
      this.c.add(this.g);
      this.c.add(this.h);
   }

   public final s a(p var1, Object var2, Object var3, Object var4, Object var5, Object var6, Object var7, Object var8, Object var9) {
      t var10 = this.i;
      int var11 = this.j;
      if(var11 >= var10.a) {
         var10.a(new s(this));
      }

      s var12 = var10.b[var11];
      var12.a = var1;
      Object[] var13 = var12.b;
      var13[0] = var2;
      var13[1] = var3;
      var13[2] = var4;
      var13[3] = var5;
      var13[4] = var6;
      var13[5] = var7;
      var13[6] = var8;
      var13[7] = var9;
      ++this.j;
      return var12;
   }

   public final void a(p var1, Object var2, Object var3, Object var4, Object var5) {
      t var6 = this.i;
      int var7 = this.j;
      if(var7 >= var6.a) {
         var6.a(new s(this));
      }

      s var8 = var6.b[var7];
      var8.a = var1;
      Object[] var9 = var8.b;
      var9[0] = var2;
      var9[1] = var3;
      var9[2] = var4;
      var9[3] = var5;
      ++this.j;
   }

   public final void a(p var1, Object var2, Object var3) {
      t var4 = this.i;
      int var5 = this.j;
      if(var5 >= var4.a) {
         var4.a(new s(this));
      }

      s var6 = var4.b[var5];
      var6.a = var1;
      Object[] var7 = var6.b;
      var7[0] = var2;
      var7[1] = var3;
      ++this.j;
   }

   public final s a(p var1) {
      t var2 = this.i;
      int var3 = this.j;
      if(var3 >= var2.a) {
         var2.a(new s(this));
      }

      s var4 = var2.b[var3];
      var4.a = var1;
      ++this.j;
      return var4;
   }

   public void a(boolean var1) {
      this.k = var1;
   }

   public boolean c() {
      return this.k;
   }

   public void a(Rect var1) {
      var1 = this.e.a(var1);
      this.a(p.g, var1, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null);
   }

   public void a(RectF var1) {
      var1 = this.f.a(var1);
      this.a(p.i, var1, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null);
   }

   public void a(e var1, float var2, float var3, Paint var4) {
      if(!(var4 instanceof ag)) {
         var4 = this.d.a(var4);
      }

      s var5 = this.a(p.m);
      var5.b[0] = var1;
      var5.b[1] = var4;
      var5.c = var2;
      var5.d = var3;
   }

   public void a(e var1, Rect var2, Rect var3, Paint var4) {
      var2 = this.e.a(var2);
      var3 = this.e.a(var3);
      if(!(var4 instanceof ag)) {
         var4 = this.d.a(var4);
      }

      this.a(p.o, var1, var2, var3, var4);
   }

   public void a(e var1, Rect var2, RectF var3, Paint var4) {
      var2 = this.e.a(var2);
      var3 = this.f.a(var3);
      if(!(var4 instanceof ag)) {
         var4 = this.d.a(var4);
      }

      this.a(p.p, var1, var2, var3, var4);
   }

   public void a(float var1, float var2, float var3, Paint var4) {
      if(!(var4 instanceof ag)) {
         var4 = this.d.a(var4);
      }

      this.a(p.t, Float.valueOf(var1), Float.valueOf(var2), Float.valueOf(var3), var4);
   }

   public void a(int var1, Mode var2) {
      this.a(p.u, Integer.valueOf(var1), var2);
   }

   public void a(int var1) {
      this.a(p.v, Integer.valueOf(var1), (Object)null, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null);
   }

   public void a(float var1, float var2, float var3, float var4, Paint var5) {
      if(!(var5 instanceof ag)) {
         var5 = this.d.a(var5);
      }

      s var6 = this.a(p.w);
      var6.c = var1;
      var6.d = var2;
      var6.e = var3;
      var6.f = var4;
      var6.b[0] = var5;
   }

   public void a(float[] var1, int var2, int var3, Paint var4) {
      if(!(var4 instanceof ag)) {
         var4 = this.d.a(var4);
      }

      this.a(p.G, var1, Integer.valueOf(var2), Integer.valueOf(var3), var4, (Object)null, (Object)null, (Object)null, (Object)null);
   }

   public void a(Rect var1, Paint var2) {
      var1 = this.e.a(var1);
      if(!(var2 instanceof ag)) {
         var2 = this.d.a(var2);
      }

      this.a(p.M, var1, var2);
   }

   public void a(RectF var1, Paint var2) {
      var1 = this.f.a(var1);
      if(!(var2 instanceof ag)) {
         var2 = this.d.a(var2);
      }

      this.a(p.N, var1, var2);
   }

   public void a(String var1, float var2, float var3, Paint var4) {
      if(!(var4 instanceof ag)) {
         var4 = this.d.a(var4);
      }

      this.a(p.R, var1, Float.valueOf(var2), Float.valueOf(var3), var4, (Object)null, (Object)null, (Object)null, (Object)null);
   }

   public void a() {
      this.a(p.W);
      --this.b;
      if(this.b < 0) {
         com.corrodinggames.rts.gameFramework.l.g("saveStackSize: " + this.b);
      }

   }

   public void a(float var1, float var2, float var3) {
      s var4 = this.a(p.Z);
      var4.c = var1;
      var4.d = var2;
      var4.e = var3;
   }

   public void b() {
      this.a(p.aa);
      ++this.b;
      if(this.b <= 0) {
         com.corrodinggames.rts.gameFramework.l.g("saveStackSize (on save): " + this.b);
      }

   }

   public void a(float var1, float var2) {
      s var3 = this.a(p.af);
      var3.c = var1;
      var3.d = var2;
   }

   public void a(float var1, float var2, float var3, float var4) {
      s var5 = this.a(p.ag);
      var5.c = var1;
      var5.d = var2;
      var5.e = var3;
      var5.f = var4;
   }

   public void a(e var1) {
      this.a(p.ah, var1, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null, (Object)null);
   }

   public void b(float var1, float var2) {
      s var3 = this.a(p.am);
      var3.c = var1;
      var3.d = var2;
   }

   public void a(m var1) {
      this.a(p.an, this, var1);
   }

   public void a(Bitmap var1) {
      this.a(p.ap, var1, (Object)null);
   }

   public void a(Lock var1) {
      this.a(p.aq, var1, (Object)null);
   }

   public void b(Lock var1) {
      this.a(p.ar, var1, (Object)null);
   }

   public boolean a(ae var1) {
      this.a(p.as, var1, (Object)null);
      return true;
   }
}
