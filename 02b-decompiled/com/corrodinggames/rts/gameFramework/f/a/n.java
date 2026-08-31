package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.f.a.h;
import com.corrodinggames.rts.gameFramework.f.a.l;
import com.corrodinggames.rts.gameFramework.m.y;

public class n extends l {

   h b;


   public n() {
      this.b = h.j;
   }

   public void a(float var1, float var2) {
      super.a(var1, var2);
      y var3 = this.d();
      RectF var4 = this.a(new RectF(), var1, var2);
      this.b.a(var3, var4);
   }
}
