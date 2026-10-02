package com.corrodinggames.rts.gameFramework.b.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint$FontMetrics;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.corrodinggames.rts.gameFramework.b.a.b;
import com.corrodinggames.rts.gameFramework.b.a.c;

public class e {

   int a;
   int b;
   Canvas c;
   public Bitmap d;
   int e;
   int f;
   int g = 0;
   int h = 0;
   int i = 0;
   int j;
   int k;
   int l;
   int m;
   int n;


   public boolean a() {
      return this.g < this.e * this.f;
   }

   public e(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.l = var2;
      this.b = var1;
      this.e = var1 / var3;
      this.f = var1 / var4;
      this.j = var3;
      this.k = var4;
      this.m = var5;
      this.n = var6;
   }

   public void b() {
      this.d = Bitmap.a(this.b, this.b, Bitmap$Config.a);
      this.c = new Canvas();
      this.c.a(this.d);
      this.d.a(0);
   }

   public void c() {
      if(this.d != null) {
         if(this.a == 0) {
            int[] var1 = new int[1];
            GLES20.glGenTextures(1, var1, 0);
            this.a = var1[0];
            if(this.a == 0) {
               b.b("Failed to gen texture page");
               return;
            }
         }

         GLES20.glBindTexture(3553, this.a);
         GLES20.glTexParameteri(3553, 10241, 9729);
         GLES20.glTexParameteri(3553, 10240, 9729);
         GLES20.glTexParameterf(3553, 10242, 33071.0F);
         GLES20.glTexParameterf(3553, 10243, 33071.0F);
         GLUtils.texImage2D(3553, 0, this.d, 0);
      }
   }

   public c a(char var1, Paint var2) {
      boolean var3 = true;
      if(this.c == null) {
         this.b();
      }

      int var4 = this.g / this.e;
      int var5 = this.g % this.e;
      int var6 = var5 * this.j;
      int var7 = var4 * this.k;
      Paint$FontMetrics var8 = var2.n();
      float var9 = (float)Math.ceil((double)Math.abs(var8.c));
      float[] var10 = new float[2];
      char[] var11 = new char[]{var1, '\u0000'};
      var2.a(var11, 0, 1, var10);
      float var12 = var10[0];
      var12 = (float)((int)var12);
      if(var12 > (float)this.j) {
         b.b("Warning chr is larger then cellWidth: " + var1);
      }

      float var13 = (float)(var6 + this.m);
      float var14 = (float)(var7 + this.k) - var9 - (float)this.n;
      var13 = (float)((int)var13);
      var14 = (float)((int)var14);
      this.c.a(var11, 0, 1, var13, var14, var2);
      c var15 = new c();
      var15.a = var1;
      var15.b = this.l;
      this.a(var15, (float)var6, (float)var7, (float)this.j, (float)this.k);
      var15.c = var12;
      ++this.g;
      return var15;
   }

   private void a(c var1, float var2, float var3, float var4, float var5) {
      var1.d = var2 / (float)this.b;
      var1.e = var3 / (float)this.b;
      var1.f = var1.d + var4 / (float)this.b;
      var1.g = var1.e + var5 / (float)this.b;
   }
}
