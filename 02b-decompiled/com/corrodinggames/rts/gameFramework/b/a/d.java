package com.corrodinggames.rts.gameFramework.b.a;

import android.opengl.GLES20;
import com.corrodinggames.rts.gameFramework.b.a.b;
import com.corrodinggames.rts.gameFramework.b.a.c;
import com.corrodinggames.rts.gameFramework.b.a.e;
import com.corrodinggames.rts.gameFramework.b.a.g;

public class d {

   b a;
   g b;
   float[] c;
   int d;
   int e;
   int f;
   private float[] h;
   private int i;
   int g;


   public d(int var1, com.corrodinggames.rts.gameFramework.b.a.a.b var2, b var3) {
      this.a = var3;
      this.c = new float[var1 * 4 * 5];
      this.b = new g(var1 * 4, var1 * 6);
      this.d = 0;
      this.e = var1;
      this.f = 0;
      short[] var4 = new short[var1 * 6];
      int var5 = var4.length;
      short var6 = 0;

      for(int var7 = 0; var7 < var5; var6 = (short)(var6 + 4)) {
         var4[var7 + 0] = (short)(var6 + 0);
         var4[var7 + 1] = (short)(var6 + 1);
         var4[var7 + 2] = (short)(var6 + 2);
         var4[var7 + 3] = (short)(var6 + 2);
         var4[var7 + 4] = (short)(var6 + 3);
         var4[var7 + 5] = (short)(var6 + 0);
         var7 += 6;
      }

      this.b.a(var4, 0, var5);
      this.i = GLES20.glGetUniformLocation(var2.b(), "u_MVPMatrix");
   }

   public void a(float[] var1) {
      this.f = 0;
      this.d = 0;
      this.h = var1;
      this.g = -1;
   }

   void a(c var1) {
      int var2 = var1.b;
      if(this.g != var2) {
         this.a();
         e var3 = (e)this.a.s.get(var2);
         GLES20.glBindTexture(3553, var3.a);
         this.g = var2;
      }
   }

   public void a() {
      if(this.f > 0) {
         GLES20.glUniformMatrix4fv(this.i, 1, false, this.h, 0);
         this.b.a(this.c, 0, this.d);
         this.b.a();
         this.b.a(4, 0, this.f * 6);
         this.b.b();
         this.f = 0;
         this.d = 0;
      }

   }

   public void a(float var1, float var2, float var3, float var4, c var5) {
      if(this.f == this.e) {
         this.a();
      }

      this.a(var5);
      float var6 = var3 / 2.0F;
      float var7 = var4 / 2.0F;
      float var8 = var1 - var6;
      float var9 = var2 - var7;
      float var10 = var1 + var6;
      float var11 = var2 + var7;
      this.c[this.d++] = var8;
      this.c[this.d++] = var9;
      this.c[this.d++] = var5.d;
      this.c[this.d++] = var5.g;
      this.c[this.d++] = var10;
      this.c[this.d++] = var9;
      this.c[this.d++] = var5.f;
      this.c[this.d++] = var5.g;
      this.c[this.d++] = var10;
      this.c[this.d++] = var11;
      this.c[this.d++] = var5.f;
      this.c[this.d++] = var5.e;
      this.c[this.d++] = var8;
      this.c[this.d++] = var11;
      this.c[this.d++] = var5.d;
      this.c[this.d++] = var5.e;
      ++this.f;
   }
}
