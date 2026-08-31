package com.a.a.a;


public class a {

   private static Object[] a = new Object[0];
   private static Object[] b = new Object[73];


   public static int a(int var0) {
      for(int var1 = 4; var1 < 32; ++var1) {
         if(var0 <= (1 << var1) - 12) {
            return (1 << var1) - 12;
         }
      }

      return var0;
   }

   public static int b(int var0) {
      return a(var0 * 2) / 2;
   }

   public static int c(int var0) {
      return a(var0 * 4) / 4;
   }

}
