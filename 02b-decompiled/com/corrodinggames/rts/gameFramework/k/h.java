package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Point;
import com.corrodinggames.rts.game.units.af;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.k.c;
import com.corrodinggames.rts.gameFramework.k.f;

public class h extends c {

   f a;
   af b = new af();
   static Point c = new Point();


   public h(f var1) {
      this.a = var1;
   }

   public af a(am var1) {
      af var2 = this.a(var1.eo, var1.ep);
      if(var2 == null) {
         return null;
      } else {
         af var3 = this.a(var2.a, var2.b);
         if(var3 == null) {
            return var2;
         } else {
            af var4 = this.a(var3.a, var3.b);
            return var4 == null?var3:var4;
         }
      }
   }

   public void d(am var1) {
      if(this.a != null) {
         this.a.d();
      }

      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      float var3 = var2.cw;
      float var4 = var2.cx;
      af var5 = this.e(var1);
      float var7;
      if(var5 != null) {
         float var6 = var5.a;
         var7 = var5.b;
         f.c.b(-16776961);
         var2.bO.a(var1.eo - var3, var1.ep - var4, var6 - var3, var7 - var4, f.c);
         af var8 = this.b(var1);
         if(var8 != null) {
            f.c.b(-7829368);
            var2.bO.a(var6 - var3, var7 - var4, var8.a - var3, var8.b - var4, f.c);
         }
      }

      af var9 = this.a(var1);
      if(var9 != null) {
         var7 = var9.a;
         float var10 = var9.b;
         f.c.b(-256);
         var2.bO.a(var1.eo - var3, var1.ep - var4, var7 - var3, var10 - var4, f.c);
      }

   }

   public af e(am var1) {
      return this.a(var1.eo, var1.ep);
   }

   public af b(am var1) {
      af var2 = this.a(var1.eo, var1.ep);
      return var2 == null?null:this.a(var2.a, var2.b);
   }

   public void c(am var1) {}

   public af a(float var1, float var2) {
      if(this.a.b == null) {
         return null;
      } else {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         com.corrodinggames.rts.game.b.b var4 = var3.bL;
         int var5 = (int)(var1 * var4.r);
         int var6 = (int)(var2 * var4.s);
         if(!var4.c(var5, var6)) {
            return null;
         } else {
            byte var7 = this.a.a(var5, var6);
            if(var7 == 0) {
               return null;
            } else {
               f.a(var7, c);
               int var8 = var5 - c.a;
               int var9 = var6 - c.b;
               this.b.a = (float)(var8 * var4.n + var4.p);
               this.b.b = (float)(var9 * var4.o + var4.q);
               return this.b;
            }
         }
      }
   }

}
