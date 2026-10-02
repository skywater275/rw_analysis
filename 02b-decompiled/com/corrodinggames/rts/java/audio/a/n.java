package com.corrodinggames.rts.java.audio.a;


class n {

   static final float[] a = new float[16384];


   static {
      int var0;
      for(var0 = 0; var0 < 16384; ++var0) {
         a[var0] = (float)Math.sin((double)(((float)var0 + 0.5F) / 16384.0F * 6.2831855F));
      }

      for(var0 = 0; var0 < 360; var0 += 90) {
         a[(int)((float)var0 * 45.511112F) & 16383] = (float)Math.sin((double)((float)var0 * 0.017453292F));
      }

   }
}
