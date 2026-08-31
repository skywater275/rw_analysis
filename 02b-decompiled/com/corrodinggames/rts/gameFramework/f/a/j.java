package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.f.a.h;
import com.corrodinggames.rts.gameFramework.f.a.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;
import java.util.ArrayList;
import java.util.Iterator;

public class j extends l {

   String a;
   Paint b = new ag();
   h c;
   ArrayList d;


   public j() {
      this.c = h.l;
      this.b.a(Paint$Align.b);
      this.b.b(-16777216);
      this.a(18.0F);
   }

   public void a(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var2.b(this.b, var1);
      this.e();
   }

   public void a(int var1) {
      this.b.b(var1);
   }

   public String a() {
      return super.a() + " (text:" + this.a + ")";
   }

   public void a(float var1, float var2) {
      super.a(var1, var2);
      y var3 = this.d();
      RectF var4 = this.a(new RectF(), var1, var2);
      this.c.a(var3, var4);
      if(this.a != null) {
         if(this.d == null) {
            var3.a(this.a, var4.d(), var4.d - this.l, this.b);
         } else {
            int var5 = 0;

            for(Iterator var6 = this.d.iterator(); var6.hasNext(); ++var5) {
               String var7 = (String)var6.next();
               Paint var8 = this.b;
               int var9 = com.corrodinggames.rts.gameFramework.f.d.a(var8);
               var3.a(var7, var4.d(), var4.b + this.k + (float)var9 + (float)(var5 * var9), var8);
            }
         }

      }
   }

   public void a(String var1) {
      this.a = var1;
      this.e();
   }

   public Rect c() {
      RectF var1 = this.a(new RectF(), 0.0F, 0.0F);
      Rect var2 = new Rect();
      var2.d = (int)var1.d;
      var2.b = (int)var1.b;
      var2.a = (int)var1.a;
      var2.c = (int)var1.c;
      var2.c = 10000;
      return var2;
   }

   public void b() {
      super.b();
      y var1 = this.d();
      Rect var2 = this.c();
      this.d = new ArrayList(com.corrodinggames.rts.gameFramework.f.d.a(this.a, var2, this.b, this.b, true));
      this.i = (float)var2.b();
      this.j = (float)var2.c();
      this.i += this.m + this.n;
      this.j += this.k + this.l;
   }
}
