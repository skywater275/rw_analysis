package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.c;
import com.corrodinggames.rts.gameFramework.j.n;
import java.util.List;

public class aq {

   public static aq a = new aq();
   public static int b = 2;
   static int c = 3;
   static int d = 2;
   static int e = 3;
   public static int f = 4;
   static String g = "tx";
   static String h = "_";
   public static int i = 55;
   public static int j = 66;
   public static int k = 100;
   public static boolean l = true;


   public static float a(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public void a(String var1, List var2) {
      long var3 = com.corrodinggames.rts.gameFramework.l.V();
      n.a(var2, h + "1", "" + var3);
      n.a(var2, g + "2", com.corrodinggames.rts.gameFramework.f.d("_" + var1 + (b + c)));
      n.a(var2, g + "3", com.corrodinggames.rts.gameFramework.f.d("_" + var1 + ((long)(b + c) + var3)));
   }

   public void b(String var1, List var2) {
      n.a(var2, g + "3", com.corrodinggames.rts.gameFramework.f.d("-" + var1 + (d + e) + f));
   }

   public void c(String var1, List var2) {
      if(f > 1000) {
         n.a(var2, g + "4", com.corrodinggames.rts.gameFramework.f.d("+" + var1 + (d + e) + f));
      }

   }

   public static void a(c var0) {
      if(var0.N) {
         long var1 = com.corrodinggames.rts.gameFramework.l.V();
         if(com.corrodinggames.rts.gameFramework.l.B().bx > -5) {
            var0.O = com.corrodinggames.rts.gameFramework.f.a(0.0F, 0.0F, (float)k, 0.0F) > 10.0F;
         }
      }

   }

}
