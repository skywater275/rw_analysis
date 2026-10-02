package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.corrodinggames.rts.gameFramework.b.ah$1;
import com.corrodinggames.rts.gameFramework.b.ai;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.k;
import com.corrodinggames.rts.gameFramework.b.n;
import com.corrodinggames.rts.gameFramework.b.u;
import java.util.HashMap;

public abstract class ah extends b {

   private static HashMap l = new HashMap();
   private static ai o = new ai((ah$1)null);
   private boolean p;
   private boolean q;
   private boolean r;
   private boolean s;
   private boolean t;
   private static int u;
   protected Bitmap m;
   private int v;
   int n;


   protected ah() {
      this(false);
   }

   protected ah(boolean var1) {
      super((k)null, 0, 0);
      this.p = true;
      this.q = false;
      this.r = false;
      this.s = false;
      this.t = false;
      this.n = 9729;
      if(var1) {
         this.a(true);
         this.v = 1;
      }

   }

   private static Bitmap a(boolean var0, Bitmap$Config var1, int var2) {
      ai var3 = o;
      var3.a = var0;
      var3.b = var1;
      var3.c = var2;
      Bitmap var4 = (Bitmap)l.get(var3);
      if(var4 == null) {
         var4 = var0?Bitmap.a(1, var2, var1):Bitmap.a(var2, 1, var1);
         l.put(var3.a(), var4);
      }

      return var4;
   }

   private Bitmap n() {
      if(this.m == null) {
         this.m = this.k();
         int var1 = this.m.b() + this.v * 2;
         int var2 = this.m.c() + this.v * 2;
         if(this.c == -1) {
            this.a(var1, var2);
         }
      }

      return this.m;
   }

   private void o() {
      this.a(this.m);
      this.m = null;
   }

   public int b() {
      if(this.c == -1) {
         this.n();
      }

      return this.c;
   }

   public int c() {
      if(this.c == -1) {
         this.n();
      }

      return this.d;
   }

   protected abstract Bitmap k();

   protected abstract void a(Bitmap var1);

   public void l() {
      if(this.m != null) {
         this.o();
      }

      this.p = false;
   }

   public boolean m() {
      return this.i() && this.p;
   }

   public void d(k var1) {
      if(!this.i()) {
         if(this.t && ++u > 100) {
            return;
         }

         this.e(var1);
      } else if(!this.p) {
         Bitmap var2 = this.n();
         int var3 = GLUtils.getInternalFormat(var2);

         int var4;
         try {
            var4 = GLUtils.getType(var2);
         } catch (IllegalArgumentException var6) {
            com.corrodinggames.rts.gameFramework.l.a("updateContent: GLUtils.getType failed, defaulting to GL_UNSIGNED_BYTE", (Throwable)var6);
            var4 = 5121;
         }

         var1.a(this, this.v, this.v, var2, var3, var4);
         this.o();
         this.p = true;
      }

   }

   public void b(int var1) {
      if(this.n != var1) {
         if(this.p) {
            int var2 = this.g();
            GLES20.glTexParameterf(var2, 10241, (float)var1);
            GLES20.glTexParameterf(var2, 10240, (float)var1);
         }

         this.n = var1;
      }

   }

   public int h() {
      return this.n;
   }

   private void e(k var1) {
      Bitmap var2 = this.n();
      if(var2 == null) {
         this.b = -1;
         throw new RuntimeException("Texture load fail, no bitmap");
      } else {
         try {
            int var3 = var2.b();
            int var4 = var2.c();
            int var10000 = var3 + this.v * 2;
            var10000 = var4 + this.v * 2;
            int var7 = this.d();
            int var8 = this.e();
            this.a = var1.a().a();
            this.p = true;
            var1.d(this);
            if(var3 == var7 && var4 == var8) {
               byte var19 = 0;
               var1.a(this, var2, var19);
               n.q();
            } else {
               int var9 = GLUtils.getInternalFormat(var2);

               int var10;
               try {
                  var10 = GLUtils.getType(var2);
               } catch (IllegalArgumentException var17) {
                  com.corrodinggames.rts.gameFramework.l.a("uploadToCanvas: GLUtils.getType failed, defaulting to GL_UNSIGNED_BYTE", (Throwable)var17);
                  var10 = 5121;
               }

               Bitmap$Config var11 = var2.d();
               var1.a(this, var9, var10, var9);
               n.q();
               var1.a(this, this.v, this.v, var2, var9, var10);
               n.q();
               Bitmap var13;
               if(this.v > 0) {
                  var13 = a(true, var11, var8);
                  var1.a(this, 0, 0, var13, var9, var10);
                  var13 = a(false, var11, var7);
                  var1.a(this, 0, 0, var13, var9, var10);
               }

               if(this.v > 0) {
                  if(this.v + var3 < var7) {
                     var13 = a(true, var11, var8);
                     var1.a(this, this.v + var3, 0, var13, var9, var10);
                  }

                  if(this.v + var4 < var8) {
                     var13 = a(false, var11, var7);
                     var1.a(this, 0, this.v + var4, var13, var9, var10);
                  }
               }
            }
         } finally {
            this.o();
         }

         this.a(var1);
         this.b = 1;
         this.p = true;
      }
   }

   public boolean c(k var1) {
      this.d(var1);
      ++this.i;
      return this.m();
   }

   public void b(k var1) {
      u var2 = var1.a();
      this.a = var2.a();
      short var3 = 3553;
      if(var3 == 3553) {
         var1.a(this, 6408, 5121, 6408);
      }

      var1.d(this);
      this.b = 1;
      this.a(var1);
   }

   public int g() {
      return 3553;
   }

   public void j() {
      super.j();
      if(this.m != null) {
         this.o();
      }

   }

}
