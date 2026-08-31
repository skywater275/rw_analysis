package com.corrodinggames.rts.gameFramework.d;

import android.graphics.Paint;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;

public class b {

   boolean a = false;
   com.corrodinggames.rts.gameFramework.m.e b = null;
   Paint c = new ag();
   RectF d = new RectF();
   float e = 0.0F;
   float f = 0.0F;


   public boolean a() {
      l var1 = l.B();
      return var1.bQ.renderClouds;
   }

   public void b() {
      l var1 = l.B();
      this.b = var1.bO.a(R$drawable.noise, false);
      this.a = true;
   }

   public void a(float var1) {
      if(this.a()) {
         if(!this.a) {
            this.b();
         }

         this.e += 0.2F * var1;
         this.f += 0.07F * var1;
         this.e %= (float)this.b.m();
         this.f %= (float)this.b.l();
      }
   }

   public void b(float var1) {
      if(this.a()) {
         if(!this.a) {
            this.b();
         }

         l var2 = l.B();
         var2.bO.i();
         float var3 = 3.0F;
         var2.bO.a(var3, var3);
         float var4 = (float)((int)com.corrodinggames.rts.gameFramework.f.f(-var2.cw / var3, 0.0F));
         float var5 = (float)((int)com.corrodinggames.rts.gameFramework.f.f(-var2.cx / var3, 0.0F));
         this.d.a(var4, var5, (float)((int)(var2.cA / var3) + 1), (float)((int)(var2.cB / var3) + 1));
         this.c.b(-16777216);
         this.c.c(40);
         float var6 = var2.cw / var3 + var4;
         float var7 = var2.cx / var3 + var5;
         var6 += this.e;
         var7 += this.f;
         var2.bO.a(this.b, this.d, this.c, var6, var7, 0, 0);
         var2.bO.j();
      }
   }
}
