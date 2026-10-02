package com.corrodinggames.rts.gameFramework.utility;

import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.gameFramework.utility.aa;
import com.corrodinggames.rts.gameFramework.utility.ai;
import com.corrodinggames.rts.gameFramework.utility.z;
import java.util.ArrayList;
import java.util.Iterator;

public final class y {

   static final Paint a = new Paint();
   static final RectF b = new RectF();
   static ArrayList c = new ArrayList();
   static final Rect d;
   static final RectF e;
   static Paint f;
   static z[] g;
   static boolean h;


   public static strictfp void a(com.corrodinggames.rts.game.units.am var0, float var1) {
      a(var0, var1, false, false);
   }

   public static strictfp void a(com.corrodinggames.rts.game.units.am var0, float var1, boolean var2) {
      a(var0, var1, var2, false);
   }

   public static strictfp boolean a(com.corrodinggames.rts.game.units.am var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var0.cG && var1.bS.q() == 1 && !var1.bS.g.e;
   }

   public static strictfp void a(com.corrodinggames.rts.game.units.am var0, float var1, boolean var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      if(a(var0) || var2) {
         float var5 = var0.eo - var4.cw;
         float var6 = var0.ep - var4.cx;
         Paint var7 = com.corrodinggames.rts.game.units.am.dg;
         if(var3) {
            var7 = com.corrodinggames.rts.game.units.am.dh;
         }

         var4.bO.a(var5, var6, var1, var7);
      }

   }

   public static strictfp void a(com.corrodinggames.rts.game.units.am var0, float var1, int var2, int var3, boolean var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      if(var0.cG && var5.bS.q() < 10 || var4) {
         float var6 = var0.eo - var5.cw;
         float var7 = var0.ep - var5.cx;
         Paint var8 = com.corrodinggames.rts.game.units.am.dk;
         var8.b(var2);
         var8.a((float)var3);
         var5.bO.a(var6, var7, var1, var8);
      }

   }

   public static strictfp void b(com.corrodinggames.rts.game.units.am var0, float var1, boolean var2) {
      a(var0, var1, var2, com.corrodinggames.rts.game.units.am.di);
   }

   public static strictfp void a(com.corrodinggames.rts.game.units.am var0, float var1, boolean var2, Paint var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      if(a(var0) || var2) {
         float var5 = var0.eo - var4.cw;
         float var6 = var0.ep - var4.cx;
         var4.bO.a(var5, var6, var1, var3);
      }

   }

   public static strictfp void a(com.corrodinggames.rts.gameFramework.m.e var0, float var1, float var2, float var3, float var4, float var5, Paint var6, int var7, int var8, int var9) {
      com.corrodinggames.rts.gameFramework.l var10 = com.corrodinggames.rts.gameFramework.l.B();
      byte var11 = 0;
      byte var12 = 0;
      int var17 = var11 + var9 * var7;
      d.a(var17, var12, var17 + var7, var12 + var8);
      float var13 = var5 * 0.5F;
      var2 -= var3;
      float var14 = (float)var7 * var13;
      float var15 = (float)var8 * var13;
      e.a(var1 - var14, var2 - var15, var1 + var14, var2 + var15);
      com.corrodinggames.rts.gameFramework.m.y var16 = var10.bO;
      var16.k();
      var16.a(var4 + 90.0F, var1, var2);
      if(var5 != 1.0F) {
         var16.a(var5, var5, var1, var2);
      }

      var16.a(var0, d, e, var6);
      var16.l();
   }

   public static strictfp boolean a(com.corrodinggames.rts.game.units.am var0, boolean var1, boolean var2) {
      return var0.cr() && var2?false:(var1 && (var0 instanceof com.corrodinggames.rts.game.units.b.b || var0 instanceof com.corrodinggames.rts.game.units.h.f)?false:(var0.bI()?false:(var1 && (var0.cv() || var0.ct())?false:(var0.P()?false:var0.cN == null && var0.cO == null))));
   }

   public static strictfp Paint a() {
      com.corrodinggames.rts.gameFramework.m.ag var0 = new com.corrodinggames.rts.gameFramework.m.ag();
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.bQ.renderAntiAlias) {
         var0.a(true);
         var0.d(true);
         var0.b(true);
      } else {
         var0.a(false);
         var0.d(false);
         var0.b(false);
      }

      return var0;
   }

   public static strictfp com.corrodinggames.rts.gameFramework.m.ag b() {
      com.corrodinggames.rts.gameFramework.m.ag var0 = new com.corrodinggames.rts.gameFramework.m.ag();
      var0.a(false);
      var0.d(false);
      var0.b(false);
      return var0;
   }

   public static strictfp void a(com.corrodinggames.rts.game.units.y var0) {
      if(!var0.bV) {
         int var1 = var0.bl();

         for(int var2 = 0; var2 < var1; ++var2) {
            a(var0, var2);
         }
      }

   }

   public static strictfp void a(com.corrodinggames.rts.game.units.y var0, com.corrodinggames.rts.gameFramework.m.e var1, float var2, int var3) {
      if(!var0.bV && var2 != 0.0F) {
         com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
         ai var5 = var0.D(var3);
         var4.bO.k();
         var4.bO.b(var5.a - var4.cw, var5.b - var5.c - var0.eq - var4.cx);
         var4.bO.a(var2, var2);
         var4.bO.a(var1, 0.0F, 0.0F, (Paint)null);
         var4.bO.l();
      }

   }

   public static strictfp void a(com.corrodinggames.rts.game.units.y var0, int var1) {
      com.corrodinggames.rts.gameFramework.m.e var2 = var0.d(var1);
      if(var2 != null) {
         float var3 = var0.p(var1);
         Paint var4 = var0.aN();
         com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
         ai var6 = var0.F(var1);
         float var7 = var6.a - com.corrodinggames.rts.gameFramework.l.B().cw;
         float var8 = var6.b - com.corrodinggames.rts.gameFramework.l.B().cx - var0.eq - var6.c;
         com.corrodinggames.rts.gameFramework.m.y var9 = var5.bO;
         var9.k();
         if(var3 != 1.0F) {
            var9.a(var3, var3, var7, var8);
         }

         var9.a(var0.cL[var1].a + 90.0F, var7, var8);
         var9.b(var2, var7 - var2.t - var0.h(var1), var8 - var2.u - var0.i(var1), var4);
         var9.l();
      }
   }

   public static strictfp boolean a(com.corrodinggames.rts.game.units.am var0, float var1, float var2) {
      return !a(var1, var2, var0.h());
   }

   public static strictfp boolean a(float var0, float var1, ao var2) {
      com.corrodinggames.rts.gameFramework.k.l var3 = com.corrodinggames.rts.gameFramework.l.B().bU;
      com.corrodinggames.rts.game.b.b var4 = com.corrodinggames.rts.gameFramework.l.B().bL;
      var4.a(var0, var1);
      int var5 = var4.T;
      int var6 = var4.U;
      return var3.a(var2, var5, var6);
   }

   public static strictfp short b(float var0, float var1, ao var2) {
      com.corrodinggames.rts.gameFramework.k.l var3 = com.corrodinggames.rts.gameFramework.l.B().bU;
      com.corrodinggames.rts.game.b.b var4 = com.corrodinggames.rts.gameFramework.l.B().bL;
      com.corrodinggames.rts.gameFramework.k.i var5 = var3.a(var2);
      if(var5.g == null) {
         return (short)-3;
      } else {
         var4.a(var0, var1);
         int var6 = var4.T;
         int var7 = var4.U;
         if(!var4.c(var6, var7)) {
            return (short)-2;
         } else {
            short var8 = var5.g[var6 * var5.c + var7];
            return var8;
         }
      }
   }

   public static strictfp int c(float var0, float var1, ao var2) {
      short var3 = b(var0, var1, var2);
      if(var3 != -3 && var3 != -2 && var3 != -1 && var3 != 0) {
         com.corrodinggames.rts.gameFramework.k.l var4 = com.corrodinggames.rts.gameFramework.l.B().bU;
         com.corrodinggames.rts.gameFramework.k.i var5 = var4.a(var2);
         Integer var6 = (Integer)var5.h.get(Short.valueOf(var3));
         if(var6 == null) {
            com.corrodinggames.rts.gameFramework.l.b("Could not find groupSize for:" + var3 + " at X:" + var0 + " y:" + var1);
            return 0;
         } else {
            return var6.intValue();
         }
      } else {
         return 0;
      }
   }

   public static strictfp boolean a(float var0, float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var3 = var2.bL;
      if(var3 == null) {
         com.corrodinggames.rts.gameFramework.l.e("isInMap called without map loaded");
         return false;
      } else {
         int var4 = (int)(var0 * var3.r);
         int var5 = (int)(var1 * var3.s);
         return var3.c(var4, var5);
      }
   }

   public static strictfp boolean b(float var0, float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var3 = var2.bL;
      if(var3 == null) {
         com.corrodinggames.rts.gameFramework.l.e("isOverClift called without map loaded");
         return false;
      } else {
         int var4 = (int)(var0 * var3.r);
         int var5 = (int)(var1 * var3.s);
         return var2.bU.b(var4, var5);
      }
   }

   public static strictfp boolean c(float var0, float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var3 = var2.bL;
      if(var3 == null) {
         com.corrodinggames.rts.gameFramework.l.e("isOverWater called without map loaded");
         return false;
      } else {
         int var4 = (int)(var0 * var3.r);
         int var5 = (int)(var1 * var3.s);
         return var2.bU.a(var4, var5);
      }
   }

   public static strictfp boolean d(float var0, float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var3 = var2.bL;
      if(var3 == null) {
         com.corrodinggames.rts.gameFramework.l.e("isOverLiquid called without map loaded");
         return false;
      } else {
         com.corrodinggames.rts.game.b.g var4 = var3.c(var0, var1);
         if(var4 == null) {
            return false;
         } else if(!var4.e && !var4.g) {
            int var5 = (int)(var0 * var3.r);
            int var6 = (int)(var1 * var3.s);
            return var2.bU.a(var5, var6);
         } else {
            return true;
         }
      }
   }

   public static final strictfp Paint a(int var0, int var1, int var2, int var3, Paint$Style var4) {
      return a(com.corrodinggames.rts.gameFramework.f.b(var0, var1, var2, var3), var4);
   }

   public static final strictfp Paint a(int var0, Paint$Style var1) {
      for(int var2 = 0; var2 < g.length; ++var2) {
         z var3;
         if(g[var2] == null) {
            var3 = new z(var0, var1);
            g[var2] = var3;
            return var3.c;
         }

         var3 = g[var2];
         if(var3.a == var0 && var3.b == var1) {
            return var3.c;
         }
      }

      if(!h) {
         h = true;
         com.corrodinggames.rts.gameFramework.l.b("----- getCachingPaint --- Paint fallback was needed!!");
      }

      f.b(var0);
      f.a(var1);
      return f;
   }

   public static strictfp void a(float var0) {
      if(c.size() != 0) {
         Iterator var1 = c.iterator();

         while(var1.hasNext()) {
            aa var2 = (aa)var1.next();
            if(var2.e <= 0.0F) {
               var1.remove();
            } else {
               var2.e -= var0;
               if(var0 == 0.0F && var2.e < 1.0F) {
                  var2.e = -1.0F;
               }
            }
         }

      }
   }

   public static strictfp void b(float var0) {
      if(c.size() != 0) {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         Iterator var2 = c.iterator();

         while(var2.hasNext()) {
            aa var3 = (aa)var2.next();
            float var4 = var3.b.a;
            float var5 = var3.b.b;
            float var6 = var3.b.c;
            float var7 = var3.b.d;
            if(var3.d) {
               var4 -= com.corrodinggames.rts.gameFramework.l.B().cw;
               var5 -= com.corrodinggames.rts.gameFramework.l.B().cx;
               var6 -= com.corrodinggames.rts.gameFramework.l.B().cw;
               var7 -= com.corrodinggames.rts.gameFramework.l.B().cx;
            }

            if(var3.c) {
               var1.bO.a(var4, var5, var6, var7, var3.a);
            } else {
               if(var3.d) {
                  ;
               }

               var1.bO.a(var3.b, var3.a);
            }

            if(var3.f != null) {
               var1.bO.i();
               var1.S();
               float var8 = var6;
               float var9 = var7;
               if(var3.d) {
                  var8 = var6 * var1.cX;
                  var9 = var7 * var1.cX;
               }

               var1.bO.a(var3.f, var8, var9, var3.a);
               var1.bO.j();
            }
         }

      }
   }

   public static final strictfp boolean a(int var0, int var1) {
      int var2 = com.corrodinggames.rts.gameFramework.l.B().by;
      return var0 + var1 < var2?true:var2 < var0 - 1000;
   }

   public static final strictfp boolean b(int var0, int var1) {
      int var2 = com.corrodinggames.rts.gameFramework.l.B().by;
      return var0 < 0?false:var0 + var1 >= var2 && var0 <= var2;
   }

   public static strictfp boolean a(float var0, float var1, float var2, float var3, ao var4) {
      if(var4 != ao.d && var4 != ao.a) {
         short var5 = b(var0, var1, var4);
         short var6 = b(var2, var3, var4);
         if(var5 == -3 || var6 == -3) {
            String var7 = "null";
            if(var4 != null) {
               var7 = var4.name();
            }

            com.corrodinggames.rts.gameFramework.l.g("pathPossible: no isolatedGroups found! (" + var7 + ")");
         }

         return var5 != -1 && var6 != -1?(var5 == -2?false:(var6 == -2?false:var5 == var6)):false;
      } else {
         return true;
      }
   }

   public static strictfp boolean b(com.corrodinggames.rts.game.units.am var0, float var1, float var2) {
      return a(var0.eo, var0.ep, var1, var2, var0.h());
   }

   public static strictfp void a(com.corrodinggames.rts.game.n var0, PointF var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();

      for(int var3 = 0; var3 <= 2; ++var3) {
         Iterator var4 = com.corrodinggames.rts.game.units.am.bF().iterator();

         while(var4.hasNext()) {
            com.corrodinggames.rts.game.units.am var5 = (com.corrodinggames.rts.game.units.am)var4.next();
            if(var5 instanceof com.corrodinggames.rts.game.units.am && !var5.bV && var5.bX == var0) {
               if(var3 == 0 && var5.bO) {
                  var1.a(var5.eo, var5.ep);
                  return;
               }

               if(var3 == 1 && var5.bP) {
                  var1.a(var5.eo, var5.ep);
                  return;
               }

               if(var3 == 2) {
                  var1.a(var5.eo, var5.ep);
                  return;
               }
            }
         }
      }

      var1.a(var2.bL.i() / 2.0F, var2.bL.j() / 2.0F);
   }

   public static strictfp void a(com.corrodinggames.rts.game.units.am var0, com.corrodinggames.rts.game.units.y var1) {
      var0.cN = null;
      com.corrodinggames.rts.game.units.custom.b.n var2 = null;
      if(var0 instanceof com.corrodinggames.rts.game.units.y) {
         com.corrodinggames.rts.game.units.y var3 = (com.corrodinggames.rts.game.units.y)var0;
         if(var3.cO == var1) {
            var2 = var3.dn();
            if(var2 == null) {
               com.corrodinggames.rts.gameFramework.l.e("Unload, attachment data is null");
            }

            var3.bx();
         }
      }

   }

   static {
      a.a(205, 255, 0, 0);
      a.a(Paint$Style.b);
      d = new Rect();
      e = new RectF();
      f = new Paint();
      g = new z[30];
      h = false;
   }
}
