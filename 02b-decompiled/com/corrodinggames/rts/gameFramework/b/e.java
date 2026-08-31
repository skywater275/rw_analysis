package com.corrodinggames.rts.gameFramework.b;

import android.graphics.Bitmap;
import com.corrodinggames.rts.gameFramework.b.ah;

public class e extends ah {

   protected Bitmap l;


   public e(Bitmap var1) {
      this(var1, false);
   }

   public e(Bitmap var1, boolean var2) {
      super(var2);
      this.l = var1;
      this.m = this.k();
      int var3 = this.m.b() + 0;
      int var4 = this.m.c() + 0;
      this.a(var3, var4);
   }

   protected void a(Bitmap var1) {}

   protected Bitmap k() {
      return this.l;
   }
}
