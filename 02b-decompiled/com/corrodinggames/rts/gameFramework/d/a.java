package com.corrodinggames.rts.gameFramework.d;

import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.o;
import java.util.Iterator;

public class a {

   float a;
   float b;
   public boolean c;
   public as d;
   public n e;
   public int f = 1;
   public float g;
   public float h;
   public boolean i;
   public n j;
   public boolean k;
   public int l;
   public int m;
   public boolean n;
   public y o;
   boolean p = false;
   public boolean q = false;
   public int r;
   public float s;
   public float t = 0.04F;
   public boolean u;
   public am v;
   public static o w = new o();
   static Point x = new Point();
   static RectF y = new RectF();
   static RectF z = new RectF();
   static RectF A = new RectF();
   Paint B = new Paint();
   static Paint C = new ag();
   static Paint D;
   static RectF E = new RectF();


   public a() {
      w.add(this);
      w.a();
   }

   public static void a() {
      w.clear();
   }

   public static void a(float var0) {
      Iterator var1 = w.iterator();

      while(var1.hasNext()) {
         a var2 = (a)var1.next();
         var2.c(var0);
      }

      w.a();
   }

   public static void b(float var0) {
      Object[] var1 = w.b();
      int var2 = 0;

      for(int var3 = w.size(); var2 < var3; ++var2) {
         a var4 = (a)var1[var2];
         var4.d(var0);
      }

   }

   public static boolean a(n var0, int var1, int var2, int var3) {
      l var4 = l.B();
      var4.bL.a(var1, var2);
      float var5 = (float)(var4.bL.T + var4.bL.p);
      float var6 = (float)(var4.bL.U + var4.bL.q);
      y.a(var5, var6, var5 + 1.0F, var6 + 1.0F);
      return a(var0, y, var3);
   }

   public static boolean a(n var0, y var1, int var2) {
      l var3 = l.B();
      com.corrodinggames.rts.game.b.b var4 = var3.bL;
      y = var1.a(var4, y);
      return a(var0, y, var2);
   }

   public static boolean a(y var0, y var1) {
      l var2 = l.B();
      com.corrodinggames.rts.game.b.b var3 = var2.bL;
      y = var0.a(var3, y);
      z = var1.a(var3, z);
      return com.corrodinggames.rts.gameFramework.f.a(y, z);
   }

   public static boolean a(n var0, RectF var1, int var2) {
      l var3 = l.B();
      com.corrodinggames.rts.game.b.b var4 = var3.bL;
      RectF var5 = A;
      Iterator var6 = w.iterator();

      while(var6.hasNext()) {
         a var7 = (a)var6.next();
         if(var7.j == var0 && var7.n && (var2 == -1 || var2 == var7.r)) {
            am var8 = am.a(var7.d);
            if(var8 == null) {
               l.e("isTileRectOverBlueprint: Failed to get shared unit for: " + var7.d);
            } else {
               var8.eo = var7.g;
               var8.ep = var7.h;
               var5 = var8.a(var4, var5);
               if(com.corrodinggames.rts.gameFramework.f.a(var5, var1)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static a a(n var0, float var1, float var2) {
      Iterator var3 = w.iterator();

      while(var3.hasNext()) {
         a var4 = (a)var3.next();
         if(var4.j == var0 && var4.n) {
            float var5 = com.corrodinggames.rts.gameFramework.f.a(var4.g, var4.h, var1, var2);
            am var6 = am.a(var4.d);
            float var7 = var6.cj + 1.0F;
            if(var7 < 20.0F) {
               var7 = 20.0F;
            }

            if(var5 < var7 * var7) {
               return var4;
            }
         }
      }

      return null;
   }

   public boolean b() {
      if(this.n) {
         if(this.o == null || this.o.bV) {
            return false;
         }

         if(!ar.a(this.d, this.g, this.h, 0.0F, 0.0F, this.e)) {
            return false;
         }
      } else {
         if(this.v == null) {
            return false;
         }

         if(this.v.cf()) {
            return false;
         }
      }

      return true;
   }

   public void c(float var1) {
      ++this.a;
      this.b += var1;
      boolean var2 = false;
      this.s = com.corrodinggames.rts.gameFramework.f.a(this.s, this.t * var1);
      if(this.n) {
         if(this.a > 6.0F) {
            this.a = 0.0F;
            boolean var3 = this.o.a(this.d, this.g, this.h);
            if(!this.p && var3) {
               this.p = true;
            }

            if(!var3) {
               if(this.p) {
                  var2 = true;
               } else if(this.b > 180.0F) {
                  var2 = true;
               }
            }

            if(!this.b()) {
               var2 = true;
            }
         }
      } else if(this.a > 2.0F && !this.b()) {
         var2 = true;
      }

      if(var2) {
         this.c = true;
         w.b((Object)this);
      }

   }

   public void d(float var1) {
      l var2 = l.B();
      if(var2.bs == this.j) {
         if(var2.cO.b(this.g, this.h)) {
            if(!this.q || this.p) {
               float var3 = 0.0F;
               float var4 = this.g;
               float var5 = this.h;
               float var6 = 0.0F;
               float var7 = 0.0F;
               float var8 = 1.0F;
               float var9 = 500.0F;
               boolean var10 = false;
               boolean var11 = false;
               if(this.n) {
                  var11 = true;
               } else {
                  var10 = true;
               }

               boolean var12 = true;
               if(this.i) {
                  var12 = false;
               }

               if(var11) {
                  var3 = this.s;
                  if(var3 <= 0.0F) {
                     var3 = 0.0F;
                  } else if(this.s < 1.0F) {
                     var3 = 1.0F - com.corrodinggames.rts.gameFramework.f.k(var3 * 90.0F);
                  } else {
                     var3 = 1.0F;
                  }
               }

               if(var11 && this.s < 1.0F) {
                  am var13 = am.c(this.d);
                  if(var13 != null && var13.bI()) {
                     Rect var14 = var13.cd();
                     if(var14 != null) {
                        E.a(var14);
                        E.b *= (float)var2.bL.o;
                        E.d *= (float)var2.bL.o;
                        E.a *= (float)var2.bL.n;
                        E.c *= (float)var2.bL.n;
                        float var15 = (float)(var2.bL.p - 3) + var3 * 5.0F;
                        E.a(-(var13.cZ() - (float)var2.bL.p), -(var13.da() - (float)var2.bL.q));
                        com.corrodinggames.rts.gameFramework.f.a(E, var15);
                        float var16 = this.g - var2.cw;
                        float var17 = this.h - var2.cx - var7;
                        E.a(var16, var17);
                        float var18 = 3.0F + var3 * 7.0F;
                        Paint var19 = C;
                        if(this.s <= 0.0F) {
                           var19 = D;
                        }

                        var2.bO.a(E.a - var18, E.b, E.c + var18, E.b, var19);
                        var2.bO.a(E.a - var18, E.d, E.c + var18, E.d, var19);
                        var2.bO.a(E.a, E.b - var18, E.a, E.d + var18, var19);
                        var2.bO.a(E.c, E.b - var18, E.c, E.d + var18, var19);
                     }
                  }
               }

               float var20 = 0.0F;
               if(var11) {
                  var20 -= 10.0F * var3;
               }

               ar.a(this.d, var4, var5 + var20, var6, var7, this.e, var8, var9, var10, var11, this.f, var12, (am)null);
            }
         }
      }
   }

   static {
      C.a(90, 0, 0, 255);
      C.a(Paint$Style.b);
      C.a(2.0F);
      D = new ag();
      D.a(40, 0, 0, 255);
      D.a(Paint$Style.b);
      D.a(2.0F);
   }
}
