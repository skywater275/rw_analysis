package com.corrodinggames.rts.java.audio.a;

import com.corrodinggames.rts.java.audio.a.n;
import java.util.Random;

public final class m {

   public static Random a = new Random();


   public static float a(float var0) {
      return n.a[(int)(var0 * 2607.5945F) & 16383];
   }

   public static float b(float var0) {
      return n.a[(int)((var0 + 1.5707964F) * 2607.5945F) & 16383];
   }

   public static int a(int var0) {
      return a.nextInt(var0 + 1);
   }

   public static int b(int var0) {
      if(var0 == 0) {
         return 1;
      } else {
         --var0;
         var0 |= var0 >> 1;
         var0 |= var0 >> 2;
         var0 |= var0 >> 4;
         var0 |= var0 >> 8;
         var0 |= var0 >> 16;
         return var0 + 1;
      }
   }

   public static float a(float var0, float var1, float var2) {
      return var0 < var1?var1:(var0 > var2?var2:var0);
   }

}
