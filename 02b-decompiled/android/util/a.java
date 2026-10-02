package android.util;


class a {

   static final boolean[] a = new boolean[0];
   static final int[] b = new int[0];
   static final long[] c = new long[0];
   static final Object[] d = new Object[0];


   static int a(int[] var0, int var1, int var2) {
      int var3 = 0;
      int var4 = var1 - 1;

      while(var3 <= var4) {
         int var5 = var3 + var4 >>> 1;
         int var6 = var0[var5];
         if(var6 < var2) {
            var3 = var5 + 1;
         } else {
            if(var6 <= var2) {
               return var5;
            }

            var4 = var5 - 1;
         }
      }

      return ~var3;
   }

}
