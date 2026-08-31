package com.corrodinggames.rts.game.units.f;

import android.graphics.RectF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.e;

public final class h extends e {

   public RectF a = new RectF();
   public float b;
   public float c;
   public float d;
   public float e;


   public final boolean a(am var1) {
      float var2 = var1.cj;
      float var3 = var1.eo;
      float var4 = var1.ep;
      return this.b - var2 <= var3 && var3 <= this.c + var2 && this.d - var2 <= var4 && var4 <= this.e + var2;
   }
}
