package com.corrodinggames.rts.game;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.m;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.j.as;

public class l extends w {

   int a;
   int b;
   int c = 50;
   int d = 40;
   m e;
   int f;
   int g = -1;
   static final Rect h = new Rect();
   static final Rect i = new Rect();
   static final Paint j = com.corrodinggames.rts.gameFramework.utility.y.b();
   static com.corrodinggames.rts.gameFramework.m.e k = null;
   static com.corrodinggames.rts.gameFramework.m.e l = null;
   static com.corrodinggames.rts.gameFramework.m.e m = null;
   static final RectF n = new RectF();


   public static strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      k = var0.bO.a(R$drawable.scorch_mark, true);
      k.m = true;
      l = var0.bO.a(R$drawable.scorch_mark_nuke, true);
      l.m = true;
      m = var0.bO.a(R$drawable.blood_mark, true);
      m.m = true;
   }

   public strictfp l() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.S(-1);
      this.f = var1.by;
   }

   public static strictfp void a(float var0, float var1) {
      a(var0, var1, m.a);
   }

   public static strictfp void a(float var0, float var1, m var2) {
      if(b(var0, var1, var2)) {
         l var3 = new l();
         var3.eo = var0;
         var3.ep = var1;
         if(var2 == m.a) {
            var3.a = 0;
            var3.b = com.corrodinggames.rts.gameFramework.f.a(var3, 0, 3, 0);
         } else {
            var3.a = 2;
         }

         if(var3.a == 2) {
            var3.c = l.m();
            var3.d = l.l();
         }

         var3.e = var2;
         var3.d();
      }
   }

   public static strictfp void a(y var0, int var1) {
      if(!var0.cJ()) {
         m var2 = var1 == 2?m.b:m.a;
         if(!b(var0.eo, var0.ep, var2)) {
            return;
         }

         l var3 = new l();
         var3.a = var1;
         if(var3.a == 2) {
            var3.c = l.m();
            var3.d = l.l();
         }

         var3.eo = var0.eo;
         var3.ep = var0.ep;
         var3.e = var2;
         var3.b = com.corrodinggames.rts.gameFramework.f.a(var3, 0, 3, 0);
         var3.d();
      }

   }

   public static strictfp boolean b(float var0, float var1, m var2) {
      int var3 = 0;
      int var4 = 0;
      byte var5 = 5;
      byte var6 = 25;
      if(var2 == m.b) {
         var6 = 45;
      }

      w[] var7 = w.er.a();
      int var8 = w.er.size();

      for(int var9 = 0; var9 < var8; ++var9) {
         w var10 = var7[var9];
         if(var10 instanceof l) {
            l var11 = (l)var10;
            if(com.corrodinggames.rts.gameFramework.f.c(var11.eo - var0) < (float)var6 && com.corrodinggames.rts.gameFramework.f.c(var11.ep - var1) < (float)var6 && var11.e == var2) {
               ++var3;
               if(com.corrodinggames.rts.gameFramework.f.c(var11.eo - var0) < (float)var5 && com.corrodinggames.rts.gameFramework.f.c(var11.ep - var1) < (float)var5) {
                  ++var4;
               }
            }
         }
      }

      if(var3 >= 3) {
         return false;
      } else if(var4 >= 1) {
         return false;
      } else {
         return true;
      }
   }

   public strictfp boolean a(com.corrodinggames.rts.gameFramework.l var1) {
      return false;
   }

   public strictfp boolean f(float var1) {
      return false;
   }

   public strictfp boolean c(float var1) {
      return true;
   }

   public strictfp RectF c() {
      n.a = this.eo - (float)this.c * 0.5F;
      n.c = this.eo + (float)this.c * 0.5F;
      n.b = this.ep - (float)this.d * 0.5F;
      n.d = this.ep + (float)this.d * 0.5F;
      return n;
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.m.y var1, int var2, int var3, float var4) {
      int var5 = this.b * this.c;
      byte var6 = 0;
      com.corrodinggames.rts.gameFramework.m.e var7 = null;
      int var8 = this.c;
      int var9 = this.d;
      if(this.a == 0) {
         var7 = k;
      } else if(this.a == 1) {
         var7 = m;
      } else if(this.a == 2) {
         var7 = l;
      }

      Rect var10 = h;
      Rect var11 = i;
      var11.a = var5;
      var11.b = var6;
      var11.c = var5 + var8;
      var11.d = var6 + var9;
      int var12 = (int)this.eo;
      int var13 = (int)this.ep;
      var12 -= var2;
      var13 -= var3;
      int var14 = var8 >> 1;
      int var15 = var9 >> 1;
      float var16 = (float)(var12 - var14);
      float var17 = (float)(var13 - var15);
      float var18 = (float)(var12 + var14);
      float var19 = (float)(var13 + var15);
      var10.a = (int)(var16 * var4);
      var10.b = (int)(var17 * var4);
      var10.c = (int)(var18 * var4);
      var10.d = (int)(var19 * var4);
      var1.b(var7, var11, var10, j);
   }

   private strictfp void d() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bL.a(this);
   }

   public strictfp void e(float var1) {}

   public strictfp void a(float var1, boolean var2) {}

   public strictfp void d(float var1) {}

   public strictfp void a(float var1) {}

   public strictfp void a(as var1) {
      var1.a(this.eo);
      var1.a(this.ep);
      var1.a(this.a);
      var1.a(this.b);
      var1.a(this.c);
      var1.a(this.d);
      var1.a((Enum)this.e);
      var1.a(this.f);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.eo = var1.g();
      this.ep = var1.g();
      this.a = var1.f();
      this.b = var1.f();
      this.c = var1.f();
      this.d = var1.f();
      if(var1.b() >= 87) {
         this.e = (m)var1.b(m.class);
         this.f = var1.f();
      } else {
         this.e = this.a == 2?m.b:m.a;
         if(this.a == 2) {
            this.c = l.m();
            this.d = l.l();
         }
      }

      super.a(var1);
   }

}
