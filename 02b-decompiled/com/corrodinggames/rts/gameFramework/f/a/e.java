package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.gameFramework.f.a.h;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.y;

public class e extends h {

   int a;
   int b;
   float c;
   float d;
   public boolean e = true;
   public boolean f = false;
   public float g = 1.0F;
   static Rect h = new Rect();
   static Rect i = new Rect();


   public e() {}

   public e(com.corrodinggames.rts.gameFramework.m.e var1, int var2, int var3) {
      this.a(var1);
      this.a(var1, var2, var3);
   }

   public void a(com.corrodinggames.rts.gameFramework.m.e var1, int var2, int var3) {
      this.a = var2;
      this.b = var3;
      this.c = (float)var2 / (float)var1.p;
      this.d = (float)var3 / (float)var1.q;
   }

   public e a() {
      e var1 = new e();
      var1.a((h)this);
      return var1;
   }

   public void a(h var1) {
      e var2 = (e)var1;
      this.a = var2.a;
      this.b = var2.b;
      this.c = var2.c;
      this.d = var2.d;
      this.e = var2.e;
      super.a((h)var2);
   }

   public void a(com.corrodinggames.rts.gameFramework.m.e var1) {
      super.a(var1);
   }

   public void a(y var1, Rect var2) {
      this.b(var1, var2);
      if(this.q != null) {
         ;
      }

   }

   public void b(y var1, Rect var2) {
      com.corrodinggames.rts.gameFramework.m.e var3 = this.p;
      Paint var4 = this.o;
      this.a(var1, var3, var4, var2);
   }

   private boolean c() {
      return true;
   }

   private void a(y var1, com.corrodinggames.rts.gameFramework.m.e var2, Paint var3, Rect var4) {
      int var5 = var4.a;
      int var6 = var4.b;
      int var7 = var4.b();
      int var8 = var4.c();
      int var9 = this.a;
      int var10 = this.b;
      int var12;
      if(!this.e) {
         if(var9 > var7 / 2) {
            var9 = var7 / 2;
         }

         if(var10 > var8 / 2) {
            var10 = var8 / 2;
         }
      } else {
         float var11 = 1.0F;
         var12 = var7 / 2;
         int var13 = var8 / 2;
         if((float)var9 * var11 > (float)var12) {
            var11 = (float)var12 / (float)var9;
         }

         if((float)var10 * var11 > (float)var13) {
            var11 = (float)var13 / (float)var10;
         }

         var9 = (int)((float)this.a * var11);
         var10 = (int)((float)this.b * var11);
      }

      int var15 = var7 - 2 * var9;
      var12 = var8 - 2 * var10;
      float var16 = this.c;
      float var14 = this.d;
      if(this.c()) {
         this.a(var1, var2, var3, var5 + var9, var6 + 0, var15, var10, var16, 0.0F, 1.0F - var16, var14, this.f);
         this.a(var1, var2, var3, var5 + 0, var6 + var10, var9, var12, 0.0F, var14, var16, 1.0F - var14, this.f);
         this.a(var1, var2, var3, var5 + var9, var6 + var8 - var10, var15, var10, var16, 1.0F - var14, 1.0F - var16, 1.0F, this.f);
         this.a(var1, var2, var3, var5 + var7 - var9, var6 + var10, var9, var12, 1.0F - var16, var14, 1.0F, 1.0F - var14, this.f);
         this.a(var1, var2, var3, var5 + 0, var6 + 0, var9, var10, 0.0F, 0.0F, this.c, this.d);
         this.a(var1, var2, var3, var5 + var7 - var9, var6 + 0, var9, var10, 1.0F - this.c, 0.0F, 1.0F, this.d);
         this.a(var1, var2, var3, var5 + 0, var6 + var8 - var10, var9, var10, 0.0F, 1.0F - this.d, this.c, 1.0F);
         this.a(var1, var2, var3, var5 + var7 - var9, var6 + var8 - var10, var9, var10, 1.0F - this.c, 1.0F - this.d, 1.0F, 1.0F);
      }

      this.a(var1, var2, var3, var5 + var9, var6 + var10, var15, var12, var16, var14, 1.0F - var16, 1.0F - var14, this.f);
   }

   public void a(y var1, com.corrodinggames.rts.gameFramework.m.e var2, Paint var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10, float var11) {
      this.a(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, false);
   }

   public void a(y var1, com.corrodinggames.rts.gameFramework.m.e var2, Paint var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10, float var11, boolean var12) {
      Rect var13 = h;
      Rect var14 = i;
      var13.a((int)(var8 * (float)var2.p), (int)(var9 * (float)var2.q), (int)(var10 * (float)var2.p), (int)(var11 * (float)var2.q));
      var14.a(var4, var5, var4 + var6, var5 + var7);
      if(!var12) {
         var1.a(var2, var13, var14, var3);
      } else {
         aa.a(var1, var2, var13, var14, var3, 0, 0, 0, 0, this.g);
      }

   }

   // $FF: synthetic method
   public Object clone() {
      return this.a();
   }

}
