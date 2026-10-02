package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.ArrayList;

public class d {

   static Rect a = new Rect();
   static ArrayList b = new ArrayList();
   static final RectF c = new RectF();
   static final RectF d = new RectF();


   public static int a(Paint var0) {
      String var1 = "abcABC123!|";
      return com.corrodinggames.rts.gameFramework.l.B().bO.a(var1, var0) + 4;
   }

   public static int b(Paint var0) {
      String var1 = "abcABC123!|";
      int var2 = com.corrodinggames.rts.gameFramework.l.B().bO.a(var1, var0);
      return com.corrodinggames.rts.gameFramework.l.aY?var2 + 2:var2;
   }

   public static ArrayList a(String var0, Rect var1, Paint var2, Paint var3, boolean var4) {
      b.clear();
      String var5 = "";
      int var6 = 0;

      int var8;
      for(int var7 = 0; var7 < var0.length(); var7 += var8) {
         var8 = var3.a(var0, var7, var0.length(), true, (float)(var1.b() - 5), (float[])null);
         if(var8 == 0) {
            break;
         }

         int var9 = var0.indexOf("\n", var7 + 1);
         String var10;
         if(var9 != -1 && var9 < var7 + var8) {
            var8 = var9 - var7;
         } else if(var7 + var8 < var0.length()) {
            var10 = var0.substring(var7, var7 + var8);
            int var11 = var10.lastIndexOf(" ");
            if(var11 != -1 && var11 != 0) {
               var8 = var11;
            }
         }

         var10 = var0.substring(var7, var7 + var8);
         var10 = var10.replaceAll("(\\n)", "");
         if(var10.length() > var5.length()) {
            var5 = var10;
            var6 = b.size();
         }

         b.add(var10);
      }

      var1.d = var1.b + b.size() * a(var3);
      if(var4) {
         float var12 = (float)var1.d();
         Paint var13 = var3;
         if(var6 == 0) {
            var13 = var2;
         }

         float var14 = (float)com.corrodinggames.rts.gameFramework.l.B().bO.b(var5, var13);
         if(var14 < (float)var1.b()) {
            var1.a = (int)(var12 - var14 / 2.0F);
            var1.c = (int)(var12 + var14 / 2.0F);
         }
      }

      return b;
   }

   public static void a(String var0, float var1, float var2, Paint var3, Paint var4, float var5, float var6, float var7, float var8) {
      com.corrodinggames.rts.gameFramework.m.y var9 = com.corrodinggames.rts.gameFramework.l.B().bO;
      float var10 = (float)var9.b(var0, var3);
      d.a(var1, var2, var1 + var10, var2 + (float)var9.a(var0, var3));
      c.a(d);
      if(var3.j() == Paint$Align.b) {
         c.a(-(var10 / 2.0F), 0.0F);
      }

      c.a -= var5;
      c.b -= var6;
      c.c += var7;
      c.d += var8;
      var9.a(c, var4);
      var9.a(var0, d.a, d.d, var3);
   }

   public static float a(com.corrodinggames.rts.gameFramework.m.e var0, float var1, float var2) {
      return a(var0, var1, var2, var1, var2);
   }

   public static float a(com.corrodinggames.rts.gameFramework.m.e var0, float var1, float var2, float var3, float var4) {
      float var5 = (float)var0.p;
      float var6 = (float)var0.q;
      float var7 = 1.0F;
      float var8;
      if(var5 * var7 < var1) {
         var8 = var1 / var5;
         if(var8 > var7) {
            var7 = var8;
         }
      }

      if(var6 * var7 < var2) {
         var8 = var2 / var6;
         if(var8 > var7) {
            var7 = var8;
         }
      }

      if(var5 * var7 > var3) {
         var8 = var3 / var5;
         if(var8 < var7) {
            var7 = var8;
         }
      }

      if(var6 * var7 > var4) {
         var8 = var4 / var6;
         if(var8 < var7) {
            var7 = var8;
         }
      }

      return var7;
   }

}
