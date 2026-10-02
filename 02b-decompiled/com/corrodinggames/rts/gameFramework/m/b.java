package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.m.a;
import com.corrodinggames.rts.gameFramework.m.ac;
import com.corrodinggames.rts.gameFramework.m.d;
import com.corrodinggames.rts.gameFramework.m.e;
import javax.microedition.khronos.opengles.GL10;

public class b {

   d a;
   e b;
   float c;
   float d;
   Rect e;
   RectF f;
   // $FF: synthetic field
   final a g;


   void a(GL10 var1) {
      if(this.g.i != this.b.h.intValue()) {
         var1.glBindTexture(3553, this.b.h.intValue());
         this.g.i = this.b.h.intValue();
      }

      var1.glPushMatrix();
      var1.glLoadIdentity();
      if(this.a != d.b) {
         var1.glTranslatef(this.c, this.g.c - this.d - (float)this.b.l(), 0.0F);
         throw new RuntimeException("Not supported");
      } else {
         var1.glTranslatef(this.f.a, this.g.c - this.f.b - (float)this.e.c(), 0.0F);
         ac var2 = this.g.h;
         float var3 = (float)this.e.a / (float)this.b.m();
         float var4 = (float)this.e.c / (float)this.b.m();
         float var5 = (float)this.e.b / (float)this.b.l();
         float var6 = (float)this.e.d / (float)this.b.l();
         if(this.g.j == this.e.c() && this.g.k == this.e.b()) {
            var2.a(0, 0, var3, var6);
            var2.a(1, 0, var4, var6);
            var2.a(0, 1, var3, var5);
            var2.a(1, 1, var4, var5);
         } else {
            this.g.j = this.e.c();
            this.g.k = this.e.b();
            var2.a(0, 0, 0.0F, 0.0F, 0.0F, var3, var6, (float[])null);
            var2.a(1, 0, (float)this.e.b(), 0.0F, 0.0F, var4, var6, (float[])null);
            var2.a(0, 1, 0.0F, (float)this.e.c(), 0.0F, var3, var5, (float[])null);
            var2.a(1, 1, (float)this.e.b(), (float)this.e.c(), 0.0F, var4, var5, (float[])null);
         }

         var2.b(var1, true, false);
         var1.glPopMatrix();
      }
   }
}
