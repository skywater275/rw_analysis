package com.corrodinggames.rts.game.b;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.game.b.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.y;

public class h {

   int a = 0;
   public com.corrodinggames.rts.gameFramework.m.e b;
   y c;
   Paint d = new Paint();
   int e;
   int f;
   int g;
   int h;
   float i = 1.0F;
   boolean j;
   h k;
   static final Rect l = new Rect();
   static final Rect m = new Rect();
   static final Rect n = new Rect();
   Rect o = new Rect();
   int p = -1;


   public h(float var1, boolean var2) {
      this.i = var1;
      this.j = var2;
      this.e = (int)(20.0F * var1);
      this.f = (int)(20.0F * var1);
      this.g = this.e + 2;
      this.h = this.f + 2;
      if(!var2) {
         this.k = new h(var1, true);
      }

   }

   public void a() {
      l var1 = l.B();
      this.b = var1.bO.b(20 * this.g, 20 * this.h, this.j);
      this.c = var1.bO.b(this.b);
      if(this.j) {
         this.b.m = true;
      }

      if(this.k != null) {
         this.k.a();
      }

   }

   public void b() {
      this.a = 0;
      this.c.b(-16777216);
      this.c.o();
      this.b.r();
      if(this.k != null) {
         this.k.b();
      }

   }

   public int a(j var1, int var2) {
      var1.a(var2, l);
      if(this.a >= 400) {
         return -1;
      } else {
         boolean var3 = true;
         if(!this.j) {
            var3 = !a(var1.b, l);
         }

         int var4;
         if(var3) {
            var4 = this.a++;
            this.a(var4, var1.b, l);
            return var4;
         } else {
            if(this.k != null) {
               var4 = this.k.a(var1, var2);
               if(var4 != -1) {
                  return var4 + 500;
               }
            }

            return -1;
         }
      }
   }

   public static boolean a(com.corrodinggames.rts.gameFramework.m.e var0, Rect var1) {
      int var2 = var1.a;
      int var3 = var1.c;
      int var4 = var1.b;
      int var5 = var1.d;
      if(var2 < 0) {
         var2 = 0;
      }

      if(var3 < 0) {
         var3 = 0;
      }

      if(var4 < 0) {
         var4 = 0;
      }

      if(var5 < 0) {
         var5 = 0;
      }

      if(var2 > var0.m()) {
         var2 = var0.m();
      }

      if(var3 > var0.m()) {
         var3 = var0.m();
      }

      if(var4 > var0.l()) {
         var4 = var0.l();
      }

      if(var5 > var0.l()) {
         var5 = var0.l();
      }

      if(!var0.k()) {
         l.e("hasImageAlpha: Cannot get pixel data for: " + var0.a());
         return true;
      } else {
         var0.x();

         for(int var6 = var4; var6 < var5; ++var6) {
            for(int var7 = var2; var7 < var3; ++var7) {
               int var8 = var0.a(var7, var6);
               int var9 = Color.a(var8);
               if(var9 != 255) {
                  return true;
               }
            }
         }

         var0.y();
         return false;
      }
   }

   public void c() {
      this.c.p();
      if(this.k != null) {
         this.k.c();
      }

   }

   public void a(int var1, com.corrodinggames.rts.gameFramework.m.e var2, Rect var3) {
      Rect var4 = new Rect();
      this.a(var1, var4);
      boolean var5 = false;
      if(this.i < 1.0F) {
         var5 = true;
      }

      this.d.a(var5);
      this.d.d(var5);
      this.d.b(var5);
      a(this.c, var2, var3, var4, this.d);
   }

   public static void a(y var0, com.corrodinggames.rts.gameFramework.m.e var1, Rect var2, Rect var3, Paint var4) {
      int var5;
      for(var5 = 0; var5 <= 3; ++var5) {
         a(var2, m, var5, 0);
         a(var3, n, var5, 1);
         var0.a(var1, m, n, var4);
      }

      for(var5 = 0; var5 <= 3; ++var5) {
         a(var2, m, var5, 1, -1);
         a(var3, n, var5, 1, 0);
         var0.a(var1, m, n, var4);
      }

      var0.a(var1, var2, var3, var4);
   }

   public static Rect a(Rect var0, Rect var1, int var2, int var3, int var4) {
      byte var5 = 0;
      if(var2 == 0) {
         var1.a = var0.a - var5;
         var1.c = var0.c + var5;
         var1.b = var0.b - var3 - var4;
         var1.d = var0.b - var4;
      } else if(var2 == 1) {
         var1.a = var0.c + var4;
         var1.c = var0.c + var3 + var4;
         var1.b = var0.b - var5;
         var1.d = var0.d + var5;
      } else if(var2 == 2) {
         var1.a = var0.a - var5;
         var1.c = var0.c + var5;
         var1.b = var0.d + var4;
         var1.d = var0.d + var3 + var4;
      } else {
         var1.a = var0.a - var4;
         var1.c = var0.a - var3 - var4;
         var1.b = var0.b - var5;
         var1.d = var0.d + var5;
      }

      return var1;
   }

   public static Rect a(Rect var0, Rect var1, int var2, int var3) {
      if(var2 == 0) {
         var1.a = var0.c - 1 + var3;
         var1.b = var0.b - var3;
      } else if(var2 == 1) {
         var1.a = var0.c - 1 + var3;
         var1.b = var0.d - 1 + var3;
      } else if(var2 == 2) {
         var1.a = var0.a - var3;
         var1.b = var0.d - 1 + var3;
      } else {
         var1.a = var0.a - var3;
         var1.b = var0.b - var3;
      }

      var1.c = var1.a + 1;
      var1.d = var1.b + 1;
      return var1;
   }

   public final com.corrodinggames.rts.gameFramework.m.e a(int var1) {
      return var1 >= 500?this.k.a(var1 - 500):this.b;
   }

   public final Rect b(int var1) {
      if(var1 >= 500) {
         return this.k.b(var1 - 500);
      } else if(this.p == var1) {
         return this.o;
      } else {
         this.p = var1;
         this.a(var1, this.o);
         return this.o;
      }
   }

   public void a(int var1, Rect var2) {
      int var3 = var1 % 20;
      int var4 = (int)((float)var1 * 0.05F);
      int var5 = var3 * this.g + 1;
      int var6 = var4 * this.h + 1;
      var2.a = var5;
      var2.b = var6;
      var2.c = var5 + this.e;
      var2.d = var6 + this.f;
   }

}
