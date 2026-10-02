package com.corrodinggames.rts.game.units;

import android.graphics.Point;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.r;
import com.corrodinggames.rts.game.units.s;
import java.util.ArrayList;
import java.util.Iterator;

public class p {

   static s[] a;
   static int b;
   static int c;
   static com.corrodinggames.rts.game.b.g d;


   public static void a(r var0, PointF var1) {
      float var2 = var1.a;
      float var3 = var1.b;
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var5 = var4.bL;
      if(var5 == null) {
         com.corrodinggames.rts.gameFramework.l.e("setTerrainType called without map loaded");
      } else {
         int var6 = (int)(var2 * var5.r);
         int var7 = (int)(var3 * var5.s);
         if(!var5.c(var6, var7)) {
            com.corrodinggames.rts.gameFramework.l.e("setTerrainType out of map range");
         } else if(var5.u == null) {
            com.corrodinggames.rts.gameFramework.l.e("setTerrainType mainLayer missing");
         } else {
            if(!var5.R) {
               if(var5.S) {
                  return;
               }

               try {
                  if(var5.v == null) {
                     var5.v = new com.corrodinggames.rts.game.b.e(var5, "grounddetails", var5.C, var5.D);
                     var5.z.add(var5.v);
                  }

                  if(var5.w == null) {
                     var5.w = new com.corrodinggames.rts.game.b.e(var5, "grounddetails2", var5.C, var5.D);
                     var5.z.add(var5.w);
                  }

                  var5.u.w = true;
                  var5.v.w = true;
                  var5.w.w = true;
               } catch (com.corrodinggames.rts.game.b.f var17) {
                  var17.printStackTrace();
                  var4.c("Failed to edit map", var17.getMessage());
                  var5.S = true;
                  return;
               }
            }

            String var8 = var0.b();

            com.corrodinggames.rts.game.b.g var9;
            try {
               var9 = var5.a(var8, 0, 0);
            } catch (com.corrodinggames.rts.game.b.f var16) {
               var16.printStackTrace();
               return;
            }

            if(var9 == null) {
               com.corrodinggames.rts.gameFramework.l.e("setTerrainType mapTile==null");
            } else {
               com.corrodinggames.rts.game.b.g var10 = var5.u.a(var6, var7);
               if(b != var6 || c != var7 || !com.corrodinggames.rts.game.b.g.a(d, var9)) {
                  com.corrodinggames.rts.gameFramework.l.e("setTerrainType changing " + var10.b + " to " + var9.b + " at:" + var7 + "," + var7);
                  var5.u.a(var6, var7, var9, false);
                  var5.v.a(var6, var7, (com.corrodinggames.rts.game.b.g)null, false);
                  var5.w.a(var6, var7, (com.corrodinggames.rts.game.b.g)null, false);
                  b = var6;
                  c = var7;
                  d = var9;
                  ArrayList var11 = new ArrayList();
                  var11.add(new Point(var6, var7));

                  for(int var12 = 0; var12 <= 4; ++var12) {
                     ArrayList var13 = new ArrayList();
                     Iterator var14 = var11.iterator();

                     while(var14.hasNext()) {
                        Point var15 = (Point)var14.next();
                        a(var0, var9, var15.a, var15.b, var13);
                     }

                     var11 = var13;
                  }

                  var5.g();
                  var4.bU.a(var5, false);
               }
            }
         }
      }
   }

   public static void a(r var0, com.corrodinggames.rts.game.b.g var1, int var2, int var3, ArrayList var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var6 = var5.bL;

      for(int var7 = -1; var7 <= 1; ++var7) {
         for(int var8 = -1; var8 <= 1; ++var8) {
            int var9 = var2 + var7;
            int var10 = var3 + var8;
            if(var6.c(var9, var10) && (var7 != 0 || var8 != 0)) {
               String var11 = var0.a();
               if(var11 != null) {
                  boolean var12 = a(var0, var1, var9, var10, var7, var8, var11);
                  if(var12) {
                     var4.add(new Point(var9, var10));
                  }
               }
            }
         }
      }

   }

   public static void a(int[] var0, s var1) {
      for(int var2 = 0; var2 < var0.length; ++var2) {
         int var3 = var0[var2] + 128;
         a[var3] = var1;
      }

   }

   public static void a() {
      a = new s[256];
      byte var0 = 1;
      byte var1 = 2;
      byte var2 = 4;
      byte var3 = 8;
      byte var4 = 16;
      byte var5 = 32;
      byte var6 = 64;
      byte var7 = -128;
      byte var8 = -1;
      a(a(var0), new s(2, 2));
      a(a(var1), new s(0, 2));
      a(a(var2), new s(0, 0));
      a(a(var3), new s(2, 0));
      a(a(var4, new int[]{var0, var1}), new s(1, 2));
      a(a(var5, new int[]{var1, var2}), new s(0, 1));
      a(a(var6, new int[]{var3, var2}), new s(1, 0));
      a(a(var7, new int[]{var0, var3}), new s(2, 1));
      a(a(var4 + var5, new int[]{var1, var0, var2}), new s(0, 6));
      a(a(var5 + var6, new int[]{var2, var3, var1}), new s(0, 4));
      a(a(var6 + var7, new int[]{var3, var2, var0}), new s(2, 4));
      a(a(var7 + var4, new int[]{var0, var3, var1}), new s(2, 6));
      a(a(var0 + var1), new s(1, 1));
      a(a(var1 + var2), new s(1, 1));
      a(a(var2 + var3), new s(1, 1));
      a(a(var3 + var0), new s(1, 1));
      a(a(var4 + var5 + var6, new int[]{var0, var1, var2, var3}), new s(1, 1));
      a(a(var5 + var6 + var7, new int[]{var0, var1, var2, var3}), new s(1, 1));
      a(a(var6 + var7 + var4, new int[]{var0, var1, var2, var3}), new s(1, 1));
      a(a(var7 + var4 + var5, new int[]{var0, var1, var2, var3}), new s(1, 1));
      a(a(var4 + var6, new int[]{var0, var1, var3, var2}), new s(1, 1));
      a(a(var7 + var5, new int[]{var0, var1, var3, var2}), new s(1, 1));
      a(a(var0 + var2), new s(1, 1));
      a(a(var1 + var3), new s(1, 1));
      a(a(var4 + var2, new int[]{var1, var0}), new s(1, 1));
      a(a(var6 + var1, new int[]{var2, var3}), new s(1, 1));
      a(a(var7 + var1, new int[]{var0, var3}), new s(1, 1));
      a(a(var5 + var0, new int[]{var1, var2}), new s(1, 1));
      a(a(var4 + var2 + var3, new int[]{var1, var0}), new s(1, 1));
      a(a(var6 + var1 + var0, new int[]{var2, var3}), new s(1, 1));
      a(a(var7 + var1 + var2, new int[]{var0, var3}), new s(1, 1));
      a(a(var5 + var0 + var3, new int[]{var1, var2}), new s(1, 1));
      a(a(var8), new s(1, 1));
   }

   private static int[] a(int var0) {
      return new int[]{var0};
   }

   private static int[] a(int var0, int ... var1) {
      ArrayList var2 = new ArrayList();
      var2.add(Integer.valueOf(var0));
      if(var1.length == 1) {
         var2.add(Integer.valueOf(var0 + var1[0]));
      } else if(var1.length == 2) {
         var2.add(Integer.valueOf(var0 + var1[0]));
         var2.add(Integer.valueOf(var0 + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1]));
      } else if(var1.length == 3) {
         var2.add(Integer.valueOf(var0 + var1[0]));
         var2.add(Integer.valueOf(var0 + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[1] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1] + var1[2]));
      } else {
         if(var1.length != 4) {
            throw new RuntimeException("unhandled:" + var1.length);
         }

         var2.add(Integer.valueOf(var0 + var1[0]));
         var2.add(Integer.valueOf(var0 + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1] + var1[2] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[1] + var1[2] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[1]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[0] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[1] + var1[2]));
         var2.add(Integer.valueOf(var0 + var1[1] + var1[3]));
         var2.add(Integer.valueOf(var0 + var1[2] + var1[3]));
      }

      int[] var3 = new int[var2.size()];

      for(int var4 = 0; var4 < var2.size(); ++var4) {
         if(var2.get(var4) != null) {
            var3[var4] = ((Integer)var2.get(var4)).intValue();
         }
      }

      return var3;
   }

   public static boolean a(r var0, com.corrodinggames.rts.game.b.g var1, int var2, int var3, int var4, int var5, String var6) {
      boolean var7 = false;
      com.corrodinggames.rts.gameFramework.l var8 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var9 = var8.bL;
      byte var11 = b(var0, var1, var2, var3);
      if(a == null) {
         a();
      }

      s var12 = a[var11 + 128];
      if(var12 == null) {
         return false;
      } else {
         com.corrodinggames.rts.game.b.g var10;
         if(var12.a == 1 && var12.b == 1) {
            var9.u.a(var2, var3, var1, false);
            var10 = null;
            var7 = true;
         } else {
            try {
               var10 = var9.a(var6, var12.a, var12.b);
            } catch (com.corrodinggames.rts.game.b.f var14) {
               var14.printStackTrace();
               return false;
            }
         }

         a(var0, var1, var10, var2, var3);
         return var7;
      }
   }

   public static void a(r var0, com.corrodinggames.rts.game.b.g var1, com.corrodinggames.rts.game.b.g var2, int var3, int var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var6 = var5.bL;
      com.corrodinggames.rts.game.b.g var7 = var6.u.a(var3, var4);
      com.corrodinggames.rts.game.b.g var8 = var6.v.a(var3, var4);
      com.corrodinggames.rts.game.b.g var9 = var6.w.a(var3, var4);
      r var10 = a(var8);
      r var11 = a(var9);
      if(var10 == var0) {
         var6.v.a(var3, var4, (com.corrodinggames.rts.game.b.g)null, false);
         var8 = null;
         var10 = null;
      }

      if(var11 == var0) {
         var6.w.a(var3, var4, (com.corrodinggames.rts.game.b.g)null, false);
         var9 = null;
         var11 = null;
      }

      if(var8 == null && var9 != null) {
         var6.v.a(var3, var4, var9, false);
         var6.w.a(var3, var4, (com.corrodinggames.rts.game.b.g)null, false);
         var8 = var9;
         var9 = null;
         var11 = null;
      }

      if(!com.corrodinggames.rts.game.b.g.a(var7, var1)) {
         if(var2 != null) {
            if(var8 != null) {
               if(var9 != null) {
                  var6.v.a(var3, var4, var9, false);
               }

               var6.w.a(var3, var4, var2, false);
            } else {
               var6.v.a(var3, var4, var2, false);
            }

         }
      }
   }

   public static r a(com.corrodinggames.rts.game.b.g var0) {
      if(var0 == null) {
         return null;
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         com.corrodinggames.rts.game.b.b var2 = var1.bL;
         r[] var3 = r.values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            r var6 = var3[var5];
            String var7 = var0.a.a;
            if(var7 != null && var7.equals(var6.b())) {
               return var6;
            }

            if(var7 != null && var7.equals(var6.a())) {
               return var6;
            }
         }

         return null;
      }
   }

   public static boolean a(r var0, com.corrodinggames.rts.game.b.g var1, int var2, int var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var5 = var4.bL;
      if(!var5.c(var2, var3)) {
         return false;
      } else {
         com.corrodinggames.rts.game.b.g var6 = var5.u.a(var2, var3);
         return com.corrodinggames.rts.game.b.g.a(var6, var1);
      }
   }

   public static byte b(r var0, com.corrodinggames.rts.game.b.g var1, int var2, int var3) {
      byte var4 = 0;
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var6 = var5.bL;
      int var7 = var6.C;
      int var8 = var6.D;
      if(var2 >= 1) {
         if(a(var0, var1, var2 - 1, var3)) {
            var4 = (byte)(var4 - 128);
         }

         if(var3 >= 1 && a(var0, var1, var2 - 1, var3 - 1)) {
            ++var4;
         }

         if(var3 < var8 - 1 && a(var0, var1, var2 - 1, var3 + 1)) {
            var4 = (byte)(var4 + 8);
         }
      }

      if(var3 >= 1) {
         if(a(var0, var1, var2, var3 - 1)) {
            var4 = (byte)(var4 + 16);
         }

         if(var2 < var7 - 1 && a(var0, var1, var2 + 1, var3 - 1)) {
            var4 = (byte)(var4 + 2);
         }
      }

      if(var2 < var7 - 1 && a(var0, var1, var2 + 1, var3)) {
         var4 = (byte)(var4 + 32);
      }

      if(var3 < var8 - 1) {
         if(a(var0, var1, var2, var3 + 1)) {
            var4 = (byte)(var4 + 64);
         }

         if(var2 < var7 - 1 && a(var0, var1, var2 + 1, var3 + 1)) {
            var4 = (byte)(var4 + 4);
         }
      }

      return var4;
   }
}
