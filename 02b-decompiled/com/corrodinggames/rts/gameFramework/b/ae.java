package com.corrodinggames.rts.gameFramework.b;

import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.b.ad;
import com.corrodinggames.rts.gameFramework.b.b;
import com.corrodinggames.rts.gameFramework.b.k;

public class ae extends b {

   ad l;
   public float m;
   public float n;
   public int o;
   public int p;


   protected boolean c(k var1) {
      return false;
   }

   protected int g() {
      return 3553;
   }

   public void b(int var1) {
      this.l.b(var1);
   }

   public int h() {
      return this.l.h();
   }

   public void a(RectF var1) {
      float var2 = this.g;
      float var3 = this.h;
      var1.a = var1.a * var2 + this.m;
      var1.c = var1.c * var2 + this.m;
      var1.b = var1.b * var3 + this.n;
      var1.d = var1.d * var3 + this.n;
   }

   public void a(RectF var1, RectF var2) {}

   public void b(k var1) {
      com.corrodinggames.rts.gameFramework.l.e("SubTexture prepare TODO");
   }
}
