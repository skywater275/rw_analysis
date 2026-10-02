package com.corrodinggames.rts.gameFramework.f;


public class ad {

   private int a;
   private int[] b;
   private int c = -1;


   public ad(int var1) {
      this.a = 0;
      this.b = new int[var1];
   }

   public ad(int var1, ad var2) {
      this.a = var1;
      this.b = new int[var2.b.length];

      for(int var3 = 0; var3 < this.b.length; ++var3) {
         this.b[var3] = var2.b[var3];
      }

   }

   public void a(int var1, int var2) {
      this.b[var1] = var2;
   }

   public float a(int var1) {
      if(this.c < 0) {
         this.c = 0;

         for(int var2 = 0; var2 < this.b.length; ++var2) {
            if(this.b[var2] > 0) {
               this.c += this.b[var2];
            }
         }
      }

      return this.c != 0 && this.b[var1] > 0?(float)this.b[var1] / (float)this.c:0.0F;
   }

   // $FF: synthetic method
   static int a(ad var0) {
      return var0.a;
   }
}
