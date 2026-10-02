package com.corrodinggames.rts.gameFramework.d;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.l;

public final class g {

   public String a;
   public int b = 25;
   public int c = 25;
   public int d = 1;
   public int e = 1;
   public int f = 26;
   public int g = 26;
   public int h = Integer.MAX_VALUE;
   public com.corrodinggames.rts.gameFramework.m.e i = null;
   public com.corrodinggames.rts.gameFramework.m.e j = null;
   public boolean k = false;
   static final Rect l = new Rect();
   static final RectF m = new RectF();


   public void a() {
      this.j = this.i.h();
      this.j.j();

      for(int var1 = 0; var1 < this.j.m(); ++var1) {
         for(int var2 = 0; var2 < this.j.l(); ++var2) {
            int var3 = this.j.a(var1, var2);
            this.j.a(var1, var2, Color.a(Color.a(var3), 0, 0, 0));
         }
      }

      this.j.p();
      this.j.s();
   }

   public void a(int var1, float var2, float var3, Paint var4) {
      Rect var5 = l;
      RectF var6 = m;
      boolean var7 = true;
      l var8 = l.B();
      int var9 = var1;
      int var10 = 0;
      if(var1 >= this.h) {
         var10 += var1 / this.h;
         var9 = var1 % this.h;
      }

      int var11 = this.d + var9 * this.f;
      int var12 = this.e + var10 * this.g;
      l.a(var11, var12, var11 + this.b, var12 + this.c);
      var6.a(var2, var3, var2 + (float)var5.b(), var3 + (float)var5.c());
      if(var7) {
         var6.a(-var6.b() / 2.0F, -var6.c() / 2.0F);
      }

      var8.bO.a(this.i, var5, var6, var4);
   }

}
