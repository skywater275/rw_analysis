package com.corrodinggames.rts.game.units.f;

import android.graphics.RectF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.e;

public final class g extends e {

   public RectF a = new RectF();
   public float b;
   public float c;
   public float d;
   public float e;


   public void a(float var1, float var2, float var3, float var4) {
      this.b = var1;
      this.c = var3;
      this.d = var2;
      this.e = var4;
      this.a.a(var1, var2, var3, var4);
   }

   public final boolean a(am var1) {
      float var2 = var1.eo;
      float var3 = var1.ep;
      return this.b <= var2 && var2 <= this.c && this.d <= var3 && var3 <= this.e;
   }
}
