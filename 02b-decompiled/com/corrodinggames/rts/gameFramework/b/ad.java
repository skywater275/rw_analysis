package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.k;
import com.corrodinggames.rts.gameFramework.b.n;

public class ad extends b {

   int l = 9729;


   public ad(k var1, int var2, int var3) {
      this.a(var2, var3);
      this.a = var1.a().a();
      var1.d(this);
      var1.a(this, 6408, 5121, 6408);
   }

   public void a(k var1, Bitmap var2, int var3, int var4) {
      var1.f();
      int var5 = this.g();
      var1.b(this);
      n.q();
      GLUtils.texSubImage2D(var5, 0, var3, var4, var2, 6408, 5121);
   }

   protected boolean c(k var1) {
      return false;
   }

   public void b(k var1) {
      com.corrodinggames.rts.gameFramework.l.e("BackingTexture prepare TODO");
   }

   protected int g() {
      return 3553;
   }

   public void b(int var1) {
      if(this.l != var1) {
         int var2 = this.g();
         GLES20.glTexParameterf(var2, 10241, (float)var1);
         GLES20.glTexParameterf(var2, 10240, (float)var1);
         this.l = var1;
      }

   }

   public int h() {
      return this.l;
   }
}
