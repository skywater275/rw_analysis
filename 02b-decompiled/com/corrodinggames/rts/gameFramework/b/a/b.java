package com.corrodinggames.rts.gameFramework.b.a;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Paint$FontMetrics;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.corrodinggames.rts.gameFramework.b.a.c;
import com.corrodinggames.rts.gameFramework.b.a.d;
import com.corrodinggames.rts.gameFramework.b.a.e;
import java.util.ArrayList;

public class b {

   AssetManager a;
   d b;
   int c;
   int d;
   float e;
   float f;
   float g;
   float h;
   float i;
   int j;
   int k;
   int l;
   int m;
   float n;
   float o;
   float p;
   boolean q;
   private com.corrodinggames.rts.gameFramework.b.a.a.b x;
   private int y;
   private int z;
   public Paint r;
   public ArrayList s;
   c[][] t;
   boolean u;
   int v;
   public static boolean w = true;


   public c a(char var1) {
      c var2 = this.b(var1);
      if(var2 == null && this.u) {
         b("Loading glyph:" + var1);
         this.c(var1);
         this.a();
      }

      return var2;
   }

   public c b(char var1) {
      if(var1 > '\uffff') {
         return null;
      } else {
         c[] var2 = this.t[var1 / 256];
         if(var2 != null) {
            c var3 = var2[var1 & 255];
            return var3;
         } else {
            return null;
         }
      }
   }

   public void a(char var1, c var2) {
      c[] var3 = this.t[var1 / 256];
      if(var3 == null) {
         var3 = new c[257];
         this.t[var1 / 256] = var3;
      }

      var3[var1 & 255] = var2;
   }

   public void c(char var1) {
      if(var1 <= '\uffff') {
         if(this.s.size() == 0) {
            this.b();
         }

         if(!((e)this.s.get(this.s.size() - 1)).a()) {
            if(this.s.size() >= this.v) {
               return;
            }

            this.b();
         }

         c var2 = ((e)this.s.get(this.s.size() - 1)).a(var1, this.r);
         this.a(var1, var2);
      }
   }

   public void a() {
      if(this.s.size() > 0) {
         ((e)this.s.get(this.s.size() - 1)).c();
      }

   }

   public void b() {
      this.a();
      short var1 = 512;
      e var2 = new e(var1, this.s.size(), this.j, this.k, this.c, this.d);
      this.s.add(var2);
   }

   public b(com.corrodinggames.rts.gameFramework.b.a.a.b var1, AssetManager var2) {
      this.q = true;
      this.s = new ArrayList();
      this.t = new c[256][];
      this.v = Integer.MAX_VALUE;
      if(var1 == null) {
         var1 = new com.corrodinggames.rts.gameFramework.b.a.a.a();
         ((com.corrodinggames.rts.gameFramework.b.a.a.b)var1).a();
      }

      this.a = var2;
      this.b = new d(24, (com.corrodinggames.rts.gameFramework.b.a.a.b)var1, this);
      this.c = 0;
      this.d = 0;
      this.e = 0.0F;
      this.f = 0.0F;
      this.g = 0.0F;
      this.h = 0.0F;
      this.i = 0.0F;
      this.j = 0;
      this.k = 0;
      this.l = 0;
      this.m = 0;
      this.n = 1.0F;
      this.o = 1.0F;
      this.p = 0.0F;
      this.x = (com.corrodinggames.rts.gameFramework.b.a.a.b)var1;
      this.y = GLES20.glGetUniformLocation(this.x.b(), "u_Color");
      this.z = GLES20.glGetUniformLocation(this.x.b(), "u_Texture");
   }

   public void a(boolean var1) {
      this.u = var1;
   }

   public void a(int var1) {
      this.v = var1;
   }

   public b(AssetManager var1) {
      this((com.corrodinggames.rts.gameFramework.b.a.a.b)null, var1);
   }

   public boolean a(Paint var1, int var2, int var3, int var4) {
      if(this.r != null) {
         throw new RuntimeException("Already loaded");
      } else {
         this.c = var3;
         this.d = var4;
         this.r = var1;
         this.r.a(true);
         this.r.b((float)var2);
         this.r.b(-1);
         Paint$FontMetrics var5 = this.r.n();
         this.e = (float)Math.ceil((double)(Math.abs(var5.d) + Math.abs(var5.a)));
         this.f = (float)Math.ceil((double)Math.abs(var5.b));
         this.g = (float)Math.ceil((double)Math.abs(var5.c));
         char[] var6 = new char[2];
         this.h = this.i = 0.0F;
         float[] var7 = new float[2];
         int var8 = 0;

         char var9;
         for(var9 = 32; var9 <= 126; ++var9) {
            var6[0] = var9;
            var1.a(var6, 0, 1, var7);
            float var10 = var7[0];
            if(var10 > this.h) {
               this.h = var10;
            }

            ++var8;
         }

         this.i = this.e;
         this.j = (int)this.h + 2 * this.c;
         this.k = (int)this.i + 2 * this.d;

         for(var9 = 32; var9 <= 126; ++var9) {
            this.c(var9);
         }

         this.a();
         return true;
      }
   }

   public void a(float var1, float var2, float var3, float var4, float[] var5) {
      this.a(var1, var2, var3, var4);
      this.b.a(var5);
   }

   public static void c() {
      if(w) {
         int var0 = GLES20.glGetError();
         if(var0 != 0) {
            Throwable var1 = new Throwable();
            Log.b("GLTEXT", "GL error: " + var0, var1);
         }
      }

   }

   void a(float var1, float var2, float var3, float var4) {
      GLES20.glUseProgram(this.x.b());
      float[] var5 = new float[]{var1, var2, var3, var4};
      GLES20.glUniform4fv(this.y, 1, var5, 0);
      GLES20.glActiveTexture('\u84c0');
      GLES20.glUniform1i(this.z, 0);
      c();
   }

   public void d() {
      this.b.a();
   }

   public void a(String var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = (float)this.k * this.o;
      float var9 = (float)this.j * this.n;
      int var10 = var1.length();
      float var11 = var9 / 2.0F - (float)this.c * this.n;
      float var12 = var8 / 2.0F - (float)this.d * this.o - this.g * this.o;
      if(this.q) {
         var11 = (float)((int)var11);
         var12 = (float)((int)var12);
      }

      var2 += var11;
      var3 += var12;
      Object var13 = null;
      boolean var14 = false;
      if(var4 == 0.0F && var7 == 0.0F && var5 == 0.0F && var6 == 0.0F) {
         var14 = true;
      } else {
         float[] var24 = new float[16];
         Matrix.setIdentityM(var24, 0);
         Matrix.translateM(var24, 0, var2, var3, var4);
         if(var7 != 0.0F || var5 != 0.0F || var6 != 0.0F) {
            Matrix.rotateM(var24, 0, var7, 0.0F, 0.0F, 1.0F);
            Matrix.rotateM(var24, 0, var5, 1.0F, 0.0F, 0.0F);
            Matrix.rotateM(var24, 0, var6, 0.0F, 1.0F, 0.0F);
         }
      }

      float var16 = 0.0F;
      float var15 = 0.0F;

      for(int var17 = 0; var17 < var10; ++var17) {
         char var18 = var1.charAt(var17);
         int var19 = var18 - 32;
         if(var19 < 0 || var19 >= 96) {
            boolean var25 = true;
         }

         float var20 = var15;
         float var21 = var16;
         if(var14) {
            var20 = var15 + var2;
            var21 = var16 + var3;
         }

         c var22 = this.a(var18);
         if(var22 != null) {
            this.b.a(var20, var21, var9, var8, var22);
            float var23 = (var22.c + this.p) * this.n;
            if(this.q) {
               var23 = (float)((int)(var23 + 0.95F));
            }

            var15 += var23;
         }
      }

   }

   public void a(String var1, float var2, float var3, float var4, float var5) {
      this.a(var1, var2, var3, var4, 0.0F, 0.0F, var5);
   }

   public void a(String var1, float var2, float var3, float var4) {
      this.a(var1, var2, var3, 0.0F, var4);
   }

   public void a(String var1, float var2, float var3) {
      this.a(var1, var2, var3, 0.0F, 0.0F);
   }

   public void a(float var1) {
      this.n = this.o = var1;
   }

   public float a(String var1) {
      float var2 = 0.0F;
      int var3 = var1.length();

      for(int var4 = 0; var4 < var3; ++var4) {
         char var5 = var1.charAt(var4);
         c var6 = this.b(var5);
         if(var6 != null) {
            var2 += var6.c * this.n;
         }
      }

      var2 += var3 > 1?(float)(var3 - 1) * this.p * this.n:0.0F;
      return var2;
   }

   public static void b(String var0) {
      Log.b("GLTEXT", "debug:" + var0);
   }

}
