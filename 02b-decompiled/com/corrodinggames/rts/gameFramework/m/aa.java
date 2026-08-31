package com.corrodinggames.rts.gameFramework.m;

import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.m.ab;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.y;

public final class aa {

   static int a = -1;
   static float b;
   static float c;
   static float d;
   public static final Rect e = new Rect();
   static ab[] f;
   static Paint g;
   static final Rect h = new Rect();
   static final Rect i = new Rect();
   static final RectF j = new RectF();


   public static final Bitmap a(e var0) {
      return var0.b();
   }

   public static void a(String var0, float var1, float var2, Paint var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      String[] var5 = com.corrodinggames.rts.gameFramework.f.c(var0, '\n');
      float var6 = (float)com.corrodinggames.rts.gameFramework.f.d.b(var3);
      float var7 = (float)(var5.length - 1) * var6;
      int var8 = 0;
      String[] var9 = var5;
      int var10 = var5.length;

      for(int var11 = 0; var11 < var10; ++var11) {
         String var12 = var9[var11];
         float var15 = var2 - var7 / 2.0F + (float)var8 * var6 + var6 / 2.0F;
         var4.bO.a(var12, var1, var15, var3);
         ++var8;
      }

   }

   public static void a(y var0, float var1, float var2, float var3, Paint var4) {
      if(com.corrodinggames.rts.gameFramework.l.at()) {
         com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
         a(var0, var1, var2, var3, 40, var4, var5.cQ);
      } else {
         var0.a(var1, var2, var3, var4);
      }

   }

   public static ab a(float var0, float var1, boolean var2, y var3) {
      int var4 = (int)var0;
      int var5 = (int)var1;
      byte var6 = 2;
      byte var7 = 2;
      byte var8 = 0;
      byte var12;
      if(var4 > 20) {
         var8 = 1;
         var12 = 60;
      } else {
         var12 = 30;
      }

      byte var9 = 0;
      byte var13;
      if(var5 >= 2) {
         var9 = 1;
         var13 = 2;
      } else {
         var13 = 1;
      }

      int var10 = var8 + var9 * var6 + (var2?0:var6 + var6 * var7);
      if(f == null) {
         f = new ab[(var6 + var6 * var7) * 2 + 1];
      }

      if(f[var10] != null) {
         if(f[var10].b != var12) {
            com.corrodinggames.rts.gameFramework.l.e("Mismatch on index: " + var10 + " size:" + var12);
         }

         return f[var10];
      } else {
         ab var11 = new ab();
         var11.d = a(var12, var13, var2, var3);
         var11.b = var12;
         var11.a = var13;
         var11.c = var2;
         f[var10] = var11;
         return var11;
      }
   }

   public static e a(int var0, int var1, boolean var2, y var3) {
      Paint var4 = new Paint();
      var4.b(-1);
      var4.a(var2?Paint$Style.a:Paint$Style.b);
      var4.a((float)var1);
      e var6 = var3.b(var0 * 2 + 4, var0 * 2 + 4, true);
      y var7 = var3.b(var6);
      var7.b((float)(var0 + 2), (float)(var0 + 2), (float)var0, var4);
      var7.p();
      var6.p();
      var7.q();
      return var6;
   }

   public static void a(y var0, float var1, float var2, float var3, Paint var4, float var5) {
      if(g == null) {
         g = new Paint();
         g.a(true);
         g.b(true);
      }

      int var6 = var4.e();
      if(com.corrodinggames.rts.gameFramework.l.at()) {
         g.a((ColorFilter)(new LightingColorFilter(var6, 0)));
      }

      g.b(var6);
      ab var7 = a(var3 * var5, var4.g(), var4.d() == Paint$Style.a, var0);
      float var8 = var3 / (float)var7.b;
      float var9 = -var3 - var8 * 2.0F;
      var0.a(var7.d, var1 + var9, var2 + var9, g, 0.0F, var8);
   }

   public static void a(y var0, float var1, float var2, float var3, int var4, Paint var5, Rect var6) {
      if(a != var4) {
         a = var4;
         b = 6.283185F / (float)var4;
         c = com.corrodinggames.rts.gameFramework.f.k(b);
         d = com.corrodinggames.rts.gameFramework.f.j(b);
      }

      float var7 = c;
      float var8 = d;
      int var9 = (int)(b * var3 * 0.5F);
      int var10 = var9 + 50;
      e.a = var6.a - var10;
      e.b = var6.b - var10;
      e.c = var6.c + var10;
      e.d = var6.d + var10;
      float var12 = var3;
      float var13 = 0.0F;
      ++var4;
      float var14 = 0.0F;
      float var15 = 0.0F;
      float var16 = 0.0F;
      float var17 = 0.0F;
      boolean var18 = true;

      for(int var19 = 0; var19 < var4; ++var19) {
         float var20 = var12 + var1;
         float var21 = var13 + var2;
         if(var18) {
            var18 = false;
            var14 = var20;
            var15 = var21;
         } else if(e.b((int)var20, (int)var21) || e.b((int)var16, (int)var17)) {
            var0.a(var20, var21, var16, var17, var5);
         }

         var16 = var20;
         var17 = var21;
         float var11 = var12;
         var12 = var7 * var12 - var8 * var13;
         var13 = var8 * var11 + var7 * var13;
      }

      if(e.b((int)var14, (int)var15) || e.b((int)var16, (int)var17)) {
         var0.a(var14, var15, var16, var17, var5);
      }

   }

   public static final int a(int var0) {
      return var0 >>> 24;
   }

   public static final int b(int var0) {
      return var0 >> 16 & 255;
   }

   public static final int c(int var0) {
      return var0 >> 8 & 255;
   }

   public static final int d(int var0) {
      return var0 & 255;
   }

   public static void a(y var0, e var1, Rect var2, Paint var3, int var4, int var5, int var6, int var7) {
      int var8 = var1.m();
      int var9 = var1.l();
      if(var4 != 0) {
         var4 %= var1.m();
         if(var4 < 0) {
            var4 += var1.m();
         }
      }

      if(var5 != 0) {
         var5 %= var1.l();
         if(var5 < 0) {
            var5 += var1.l();
         }
      }

      int var10 = var2.a - var4;
      int var11 = var2.b - var5;
      int var12 = var8 - var6;
      int var13 = var9 - var7;
      if(var12 > 0 && var13 > 0) {
         int var14 = 0;

         while(var10 < var2.c) {
            while(true) {
               if(var11 < var2.d) {
                  ++var14;
                  if(var14 > 2000) {
                     com.corrodinggames.rts.gameFramework.l.e("tileImage hit limit");
                     return;
                  }

                  int var15 = var2.c - var10;
                  if(var15 > var8) {
                     var15 = var8;
                  }

                  int var16 = var2.d - var11;
                  if(var16 > var9) {
                     var16 = var9;
                  }

                  if(var16 > 0 && var15 > 0) {
                     h.a(0, 0, var15, var16);
                     i.a(var10, var11, var10 + var15, var11 + var16);
                     int var17 = i.a - var2.a;
                     if(var17 < 0) {
                        h.a -= var17;
                        i.a -= var17;
                     }

                     int var18 = i.b - var2.b;
                     if(var18 < 0) {
                        h.b -= var18;
                        i.b -= var18;
                     }

                     var0.a(var1, h, i, var3);
                     var11 += var13;
                     continue;
                  }
               }

               var10 += var12;
               var11 = var2.b - var5;
               break;
            }
         }

      }
   }

   public static void a(y var0, e var1, RectF var2, Paint var3, float var4, float var5, int var6, int var7) {
      int var8 = var1.m();
      int var9 = var1.l();
      if(var4 != 0.0F) {
         var4 %= (float)var8;
         if(var4 < 0.0F) {
            var4 += (float)var8;
         }
      }

      if(var5 != 0.0F) {
         var5 %= (float)var9;
         if(var5 < 0.0F) {
            var5 += (float)var9;
         }
      }

      float var10 = var2.a - var4;
      float var11 = var2.b - var5;
      int var12 = 0;
      int var13 = var8 - var6;
      int var14 = var9 - var7;
      if(var13 > 0 && var14 > 0) {
         while(var10 < var2.c) {
            while(true) {
               if(var11 < var2.d) {
                  ++var12;
                  if(var12 > 2000) {
                     com.corrodinggames.rts.gameFramework.l.e("tileImage hit limit");
                     return;
                  }

                  float var15 = var2.c - var10;
                  if(var15 > (float)var8) {
                     var15 = (float)var8;
                  }

                  float var16 = var2.d - var11;
                  if(var16 > (float)var9) {
                     var16 = (float)var9;
                  }

                  if(var16 > 0.0F && var15 > 0.0F) {
                     h.a(0, 0, (int)var15, (int)var16);
                     j.a(var10, var11, var10 + var15, var11 + var16);
                     float var17 = j.a - var2.a;
                     if(var17 < 0.0F) {
                        h.a = (int)((float)h.a - var17);
                        j.a -= var17;
                     }

                     float var18 = j.b - var2.b;
                     if(var18 < 0.0F) {
                        h.b = (int)((float)h.b - var18);
                        j.b -= var18;
                     }

                     var0.a(var1, h, j, var3);
                     var11 += (float)var14;
                     continue;
                  }
               }

               var10 += (float)var13;
               var11 = var2.b - var5;
               break;
            }
         }

      }
   }

   public static void a(y var0, e var1, Rect var2, Rect var3, Paint var4, int var5, int var6, int var7, int var8, float var9) {
      int var10 = var2.b();
      int var11 = var2.c();
      if(var5 != 0) {
         var5 %= var10;
         if(var5 < 0) {
            var5 += var10;
         }
      }

      if(var6 != 0) {
         var6 %= var11;
         if(var6 < 0) {
            var6 += var11;
         }
      }

      int var12 = var3.a - var5;
      int var13 = var3.b - var6;
      int var14 = var3.c - var12;
      int var15 = var3.d - var13;
      float var16 = (float)var14 / (float)var10;
      float var17 = (float)var15 / (float)var11;
      int var18 = (int)(var16 + 0.5F);
      int var19 = (int)(var17 + 0.5F);
      if(var18 < 1) {
         var18 = 1;
      }

      if(var19 < 1) {
         var19 = 1;
      }

      float var20 = (float)var14 / (float)(var18 * var10);
      float var21 = (float)var15 / (float)(var19 * var11);
      var20 = com.corrodinggames.rts.gameFramework.f.f(1.0F, var20, var9);
      var21 = com.corrodinggames.rts.gameFramework.f.f(1.0F, var21, var9);
      int var22 = (int)((float)var10 * var20);
      int var23 = (int)((float)var11 * var21);
      float var24 = 1.0F / var20;
      float var25 = 1.0F / var21;
      int var26 = var22 - var7;
      int var27 = var23 - var8;
      if(var26 > 0 && var27 > 0) {
         int var28 = 0;

         while(var12 < var3.c) {
            while(true) {
               if(var13 < var3.d) {
                  ++var28;
                  if(var28 > 2000) {
                     com.corrodinggames.rts.gameFramework.l.e("tileImage hit limit");
                     return;
                  }

                  int var29 = var3.c - var12;
                  if(var29 > var22) {
                     var29 = var22;
                  }

                  int var30 = var3.d - var13;
                  if(var30 > var23) {
                     var30 = var23;
                  }

                  if(var30 > 0 && var29 > 0) {
                     int var31 = (int)((float)var29 * var24);
                     int var32 = (int)((float)var30 * var25);
                     h.a(0, 0, var31, var32);
                     h.a(var2.a, var2.b);
                     i.a(var12, var13, var12 + var29, var13 + var30);
                     int var33 = i.a - var3.a;
                     if(var33 < 0) {
                        h.a -= var33;
                        i.a -= var33;
                     }

                     int var34 = i.b - var3.b;
                     if(var34 < 0) {
                        h.b -= var34;
                        i.b -= var34;
                     }

                     var0.a(var1, h, i, var4);
                     var13 += var27;
                     continue;
                  }
               }

               var12 += var26;
               var13 = var3.b - var6;
               break;
            }
         }

      }
   }

   public static void a(Paint var0) {
      if(com.corrodinggames.rts.gameFramework.l.at()) {
         int var1 = var0.e();
         int var2 = var1 >> 16 & 255;
         int var3 = var1 >> 8 & 255;
         int var4 = var1 & 255;
         int var5 = com.corrodinggames.rts.gameFramework.f.b(255, var2, var3, var4);
         var0.a((ColorFilter)(new LightingColorFilter(var5, 0)));
      }

   }

}
