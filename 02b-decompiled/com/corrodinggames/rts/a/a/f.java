package com.corrodinggames.rts.a.a;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.a.a.g;
import com.corrodinggames.rts.a.a.h;
import com.corrodinggames.rts.a.a.i;
import com.corrodinggames.rts.a.a.j;
import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.a.a.n;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.aq;
import com.corrodinggames.rts.gameFramework.br;
import java.util.Iterator;
import java.util.Random;

public class f extends l {

   int a = 1;
   int b;
   final Rect c = new Rect();
   final PointF d = new PointF();
   static final Point e = new Point();


   public void a() {
      com.corrodinggames.rts.gameFramework.l.e("Misc Performance test");
      byte var1 = 5;
      int var2 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== applyDigitGroupingStyle tests (runs:" + var1 + ")");
      Long var3 = Long.valueOf(br.a());

      int var4;
      int var5;
      for(var4 = 0; var4 < var1; ++var4) {
         for(var5 = 0; var5 < 100; ++var5) {
            if(!com.corrodinggames.rts.game.units.custom.e.a.a(var5 + "9870000001.67", com.corrodinggames.rts.game.units.custom.e.b.c).equals("")) {
               ++var2;
            }
         }
      }

      Long var19 = Long.valueOf(br.a());
      double var24 = br.a(var3.longValue(), var19.longValue());
      this.a += var2;
      com.corrodinggames.rts.gameFramework.l.e("Took: " + var24);
      var1 = 5;
      var2 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== applyDigitGroupingStyle_systemLibraryVersion tests (runs:" + var1 + ")");
      var3 = Long.valueOf(br.a());

      for(var4 = 0; var4 < var1; ++var4) {
         for(var5 = 0; var5 < 100; ++var5) {
            if(!com.corrodinggames.rts.game.units.custom.e.a.a((long)var5 + 9870000001L, com.corrodinggames.rts.game.units.custom.e.b.c).equals("")) {
               ++var2;
            }
         }
      }

      var19 = Long.valueOf(br.a());
      var24 = br.a(var3.longValue(), var19.longValue());
      this.a += var2;
      com.corrodinggames.rts.gameFramework.l.e("Took: " + var24);
      var1 = 3;
      var2 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== isLineClear tests (runs:" + var1 + ")");
      var3 = Long.valueOf(br.a());

      int var6;
      short var10;
      for(var4 = 0; var4 < var1; ++var4) {
         for(var5 = 0; var5 < 100; ++var5) {
            var6 = 1000 - var5;
            byte var7 = 50;
            byte var8 = 50;
            byte var9 = 1;
            var10 = 1000;
            byte var11 = 1;
            if(aq.b(ao.b, (float)var5, (float)var6, (float)var7, (float)var8, var10, var11, var9)) {
               ++var2;
            }
         }
      }

      var19 = Long.valueOf(br.a());
      var24 = br.a(var3.longValue(), var19.longValue());
      this.a += var2;
      com.corrodinggames.rts.gameFramework.l.e("Took: " + var24);
      var1 = 3;
      var2 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== maths tests == (runs:" + var1 + ")");
      var3 = Long.valueOf(br.a());
      byte var25 = 0;

      for(var5 = 0; var5 < var1; ++var5) {
         for(var6 = 0; var6 < 1000; ++var6) {
            Point var28 = e;
            var28.a += var6;
            var28.a += var6;
            var28.a += var6;
            var28.a += var6;
            var28.a += var6;
            var28.a += var6;
            var28.a += var6;
            var28.a += var6;
            var28.a += var6;
            ++this.b;
            var2 += var25;
         }
      }

      Long var34 = Long.valueOf(br.a());
      double var26 = br.a(var3.longValue(), var34.longValue());
      this.a += var2;
      com.corrodinggames.rts.gameFramework.l.e("Took: " + var26);
      var1 = 14;
      byte var20 = 5;
      byte var18 = 0;
      com.corrodinggames.rts.gameFramework.utility.m var27 = new com.corrodinggames.rts.gameFramework.utility.m();
      var5 = 0;

      for(var6 = 0; var6 < 20000; ++var6) {
         com.corrodinggames.rts.game.units.custom.i var29 = new com.corrodinggames.rts.game.units.custom.i();
         if(var6 % 10 != 0) {
            var29.a(com.corrodinggames.rts.game.units.custom.g.c("test"));
            var29.a(com.corrodinggames.rts.game.units.custom.g.c("test1"));
         }

         if(var6 % 2 == 0) {
            var29.a(com.corrodinggames.rts.game.units.custom.g.c("test2"));
            ++var5;
         }

         if(var6 % 3 == 0) {
            var29.a(com.corrodinggames.rts.game.units.custom.g.c("test3"));
         }

         if(var6 % 4 == 0) {
            var29.a(com.corrodinggames.rts.game.units.custom.g.c("test4"));
         }

         if(var6 % 5 == 0) {
            var27.add((Object)null);
         }

         var27.add(var29.a());
      }

      com.corrodinggames.rts.game.units.custom.h var30 = com.corrodinggames.rts.game.units.custom.g.a("test2");
      com.corrodinggames.rts.gameFramework.l.e("=== CustomTagList tests == (runs:" + var20 + ")");

      int var31;
      int var41;
      for(var31 = 0; var31 < var1; ++var31) {
         Long var32 = Long.valueOf(br.a());

         for(int var36 = 0; var36 < var20; ++var36) {
            var41 = 0;
            Iterator var45 = var27.iterator();

            while(var45.hasNext()) {
               com.corrodinggames.rts.game.units.custom.h var12 = (com.corrodinggames.rts.game.units.custom.h)var45.next();
               if(com.corrodinggames.rts.game.units.custom.g.a(var30, var12)) {
                  ++var41;
               }
            }

            n.a(var5, var41);
         }

         com.corrodinggames.rts.gameFramework.l.e("test2Expected:" + var5);
         Long var40 = Long.valueOf(br.a());
         double var43 = br.a(var32.longValue(), var40.longValue());
         this.a += var18;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var43);
      }

      int var17 = 5000000;
      float var22 = 0.5F;

      Random var35;
      byte var38;
      int var46;
      Long var49;
      int var50;
      for(int var21 = 0; var21 < 2; ++var21) {
         var25 = 5;
         var38 = 5;
         byte var33 = 0;
         com.corrodinggames.rts.gameFramework.l.e("=== [Write]/comparison tests == (runs:" + var38 + ")");

         h var13;
         h[] var42;
         Long var48;
         double var52;
         for(var31 = 0; var31 < var25; ++var31) {
            var35 = new Random();
            var42 = new h[var17];

            for(var41 = 0; var41 < var42.length; ++var41) {
               var42[var41] = new h();
               var42[var41].d = var35.nextFloat() < var22;
            }

            var48 = Long.valueOf(br.a());

            for(var46 = 0; var46 < var38; ++var46) {
               for(var50 = 0; var50 < var42.length; ++var50) {
                  var13 = var42[var50];
                  var13.d = false;
               }
            }

            var49 = Long.valueOf(br.a());
            var52 = br.a(var48.longValue(), var49.longValue());
            this.a += var33;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + var52);
         }

         var25 = 5;
         var38 = 5;
         var33 = 0;
         com.corrodinggames.rts.gameFramework.l.e("=== Write/[comparison] tests == (runs:" + var38 + ")");

         for(var31 = 0; var31 < var25; ++var31) {
            var35 = new Random();
            var42 = new h[var17];

            for(var41 = 0; var41 < var42.length; ++var41) {
               var42[var41] = new h();
               var42[var41].d = var35.nextFloat() < var22;
            }

            var48 = Long.valueOf(br.a());

            for(var46 = 0; var46 < var38; ++var46) {
               for(var50 = 0; var50 < var42.length; ++var50) {
                  var13 = var42[var50];
                  if(var13.d) {
                     var13.d = false;
                  }
               }
            }

            var49 = Long.valueOf(br.a());
            var52 = br.a(var48.longValue(), var49.longValue());
            this.a += var33;
            com.corrodinggames.rts.gameFramework.l.e("Took: " + var52);
         }
      }

      float var23 = 0.3F;
      var25 = 7;
      var38 = 5;
      var6 = 0;
      short var39 = 1000;
      com.corrodinggames.rts.gameFramework.l.e("=== [Virtual method]/if tests == (runs:" + var38 + ")");

      int var37;
      Random var44;
      int var57;
      boolean var58;
      double var59;
      Long var63;
      for(var37 = 0; var37 < var25; ++var37) {
         var44 = new Random();
         i[] var54 = new i[var39];

         for(var46 = 0; var46 < var54.length; ++var46) {
            var58 = var44.nextFloat() < var23;
            if(var58) {
               j var56 = new j(this);
               var56.c = var44.nextInt(1000);
               var54[var46] = var56;
            } else {
               var54[var46] = new i(this);
               var54[var46].a = var44.nextInt(1000);
            }
         }

         var49 = Long.valueOf(br.a());

         for(var50 = 0; var50 < var38; ++var50) {
            for(var57 = 0; var57 < var54.length; ++var57) {
               if(var54[var57].a() == 0) {
                  ++var6;
               }
            }
         }

         var63 = Long.valueOf(br.a());
         var59 = br.a(var49.longValue(), var63.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var59);
      }

      var25 = 7;
      var38 = 5;
      var6 = 0;
      var39 = 1000;
      com.corrodinggames.rts.gameFramework.l.e("=== Virtual method/[if tests] == (runs:" + var38 + ")");

      for(var37 = 0; var37 < var25; ++var37) {
         var44 = new Random();
         g[] var55 = new g[var39];

         for(var46 = 0; var46 < var55.length; ++var46) {
            var58 = var44.nextFloat() < var23;
            g var62 = new g(this);
            var62.b = var44.nextInt(1000);
            var62.a = var44.nextInt(1000);
            var62.c = var58;
            var55[var46] = var62;
         }

         var49 = Long.valueOf(br.a());

         for(var50 = 0; var50 < var38; ++var50) {
            for(var57 = 0; var57 < var55.length; ++var57) {
               if(var55[var57].a() == 0) {
                  ++var6;
               }
            }
         }

         var63 = Long.valueOf(br.a());
         var59 = br.a(var49.longValue(), var63.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var59);
      }

      var25 = 14;
      var38 = 10;
      var6 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== comparison tests 1 == (runs:" + var38 + ")");

      int var14;
      int var15;
      short var47;
      double var61;
      Long var67;
      for(var31 = 0; var31 < var25; ++var31) {
         var35 = new Random();
         var47 = 600;
         var10 = 600;
         float[] var65 = new float[var47 * var47];

         for(var50 = 0; var50 < var47; ++var50) {
            for(var57 = 0; var57 < var10; ++var57) {
               var65[var50 * var10 + var57] = var35.nextFloat();
            }
         }

         var63 = Long.valueOf(br.a());

         for(var57 = 0; var57 < var38; ++var57) {
            for(var14 = 0; var14 < var47; ++var14) {
               for(var15 = 0; var15 < var10; ++var15) {
                  int var16 = var14 * var10 + var15;
                  var6 = (int)((float)var6 + var65[var16]);
               }
            }
         }

         var67 = Long.valueOf(br.a());
         var61 = br.a(var63.longValue(), var67.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var61);
      }

      var25 = 14;
      var38 = 10;
      var6 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== comparison tests 2 == (runs:" + var38 + ")");

      for(var31 = 0; var31 < var25; ++var31) {
         var35 = new Random();
         var47 = 600;
         var10 = 600;
         float[][] var66 = new float[var47][var47];

         for(var50 = 0; var50 < var47; ++var50) {
            for(var57 = 0; var57 < var10; ++var57) {
               var66[var50][var57] = var35.nextFloat();
            }
         }

         var63 = Long.valueOf(br.a());

         for(var57 = 0; var57 < var38; ++var57) {
            for(var14 = 0; var14 < var47; ++var14) {
               for(var15 = 0; var15 < var10; ++var15) {
                  var6 = (int)((float)var6 + var66[var14][var15]);
               }
            }
         }

         var67 = Long.valueOf(br.a());
         var61 = br.a(var63.longValue(), var67.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var61);
      }

      var25 = 5;
      var38 = 5;
      var6 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== [divide]/multiply float tests == (runs:" + var38 + ")");

      float[] var51;
      float[] var60;
      for(var31 = 0; var31 < var25; ++var31) {
         var35 = new Random();
         var51 = new float[var17];
         var60 = new float[var17];

         for(var46 = 0; var46 < var51.length; ++var46) {
            var51[var46] = var35.nextFloat();
            var60[var46] = var35.nextFloat();
         }

         var49 = Long.valueOf(br.a());

         for(var50 = 0; var50 < var38; ++var50) {
            for(var57 = 0; var57 < var51.length; ++var57) {
               if(var51[var57] / var60[var57] == 0.0F) {
                  ++var6;
               }
            }
         }

         var63 = Long.valueOf(br.a());
         var59 = br.a(var49.longValue(), var63.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var59);
      }

      var25 = 5;
      var38 = 5;
      var6 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== divide/[multiply] float tests == (runs:" + var38 + ")");

      for(var31 = 0; var31 < var25; ++var31) {
         var35 = new Random();
         var51 = new float[var17];
         var60 = new float[var17];

         for(var46 = 0; var46 < var51.length; ++var46) {
            var51[var46] = var35.nextFloat();
            var60[var46] = var35.nextFloat();
         }

         var49 = Long.valueOf(br.a());

         for(var50 = 0; var50 < var38; ++var50) {
            for(var57 = 0; var57 < var51.length; ++var57) {
               if(var51[var57] * var60[var57] == 0.0F) {
                  ++var6;
               }
            }
         }

         var63 = Long.valueOf(br.a());
         var59 = br.a(var49.longValue(), var63.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var59);
      }

      var25 = 5;
      var38 = 5;
      var6 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== [divide]/multiply int tests == (runs:" + var38 + ")");

      int[] var53;
      int[] var64;
      for(var31 = 0; var31 < var25; ++var31) {
         var35 = new Random();
         var53 = new int[var17];
         var64 = new int[var17];

         for(var46 = 0; var46 < var53.length; ++var46) {
            var53[var46] = var35.nextInt();
            var64[var46] = var35.nextInt();
         }

         var49 = Long.valueOf(br.a());

         for(var50 = 0; var50 < var38; ++var50) {
            for(var57 = 0; var57 < var53.length; ++var57) {
               if(var53[var57] / var64[var57] == 0) {
                  ++var6;
               }
            }
         }

         var63 = Long.valueOf(br.a());
         var59 = br.a(var49.longValue(), var63.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var59);
      }

      var25 = 5;
      var38 = 5;
      var6 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== [float cast and divide]/multiply int tests == (runs:" + var38 + ")");

      for(var31 = 0; var31 < var25; ++var31) {
         var35 = new Random();
         var53 = new int[var17];
         var64 = new int[var17];

         for(var46 = 0; var46 < var53.length; ++var46) {
            var53[var46] = var35.nextInt();
            var64[var46] = var35.nextInt();
         }

         var49 = Long.valueOf(br.a());

         for(var50 = 0; var50 < var38; ++var50) {
            for(var57 = 0; var57 < var53.length; ++var57) {
               if((float)var53[var57] / (float)var64[var57] == 0.0F) {
                  ++var6;
               }
            }
         }

         var63 = Long.valueOf(br.a());
         var59 = br.a(var49.longValue(), var63.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var59);
      }

      var25 = 5;
      var38 = 5;
      var6 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== [mixed float and divide]/multiply int tests == (runs:" + var38 + ")");

      for(var31 = 0; var31 < var25; ++var31) {
         var35 = new Random();
         var53 = new int[var17];
         var60 = new float[var17];

         for(var46 = 0; var46 < var53.length; ++var46) {
            var53[var46] = var35.nextInt();
            var60[var46] = var35.nextFloat();
         }

         var49 = Long.valueOf(br.a());

         for(var50 = 0; var50 < var38; ++var50) {
            for(var57 = 0; var57 < var53.length; ++var57) {
               if((float)var53[var57] / var60[var57] == 0.0F) {
                  ++var6;
               }
            }
         }

         var63 = Long.valueOf(br.a());
         var59 = br.a(var49.longValue(), var63.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var59);
      }

      var25 = 5;
      var38 = 5;
      var6 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== divide/[multiply] int tests == (runs:" + var38 + ")");

      for(var31 = 0; var31 < var25; ++var31) {
         var35 = new Random();
         var53 = new int[var17];
         var64 = new int[var17];

         for(var46 = 0; var46 < var53.length; ++var46) {
            var53[var46] = var35.nextInt();
            var64[var46] = var35.nextInt();
         }

         var49 = Long.valueOf(br.a());

         for(var50 = 0; var50 < var38; ++var50) {
            for(var57 = 0; var57 < var53.length; ++var57) {
               if(var53[var57] * var64[var57] == 0) {
                  ++var6;
               }
            }
         }

         var63 = Long.valueOf(br.a());
         var59 = br.a(var49.longValue(), var63.longValue());
         this.a += var6;
         com.corrodinggames.rts.gameFramework.l.e("Took: " + var59);
      }

   }

}
