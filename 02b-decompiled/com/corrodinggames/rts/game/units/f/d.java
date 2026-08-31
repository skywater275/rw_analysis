package com.corrodinggames.rts.game.units.f;

import android.graphics.RectF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.f.e;

public final class d extends e {

   public RectF a = new RectF();
   public float b;
   public float c;
   public float d;
   public float e;
   public float f;
   public float g;
   public float h;


   public final boolean a(am var1) {
      float var2 = var1.eo;
      float var3 = var1.ep;
      if(this.b <= var2 && var2 <= this.c && this.d <= var3 && var3 <= this.e) {
         float var4 = com.corrodinggames.rts.gameFramework.f.a(this.f, this.g, var2, var3);
         return var4 < this.h;
      } else {
         return false;
      }
   }
}
