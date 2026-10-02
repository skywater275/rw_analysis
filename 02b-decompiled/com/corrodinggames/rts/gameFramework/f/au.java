package com.corrodinggames.rts.gameFramework.f;


abstract class au implements Comparable {

   long c;
   long d = 5000L;
   float e;
   float f;
   String g;
   boolean h;
   boolean i;


   public strictfp au(float var1, float var2) {
      this.e = var1;
      this.f = var2;
   }

   public strictfp int c(au var1) {
      return (int)(var1.c - this.c);
   }

   public strictfp boolean a(au var1) {
      if(this.c + this.b() < System.currentTimeMillis()) {
         return false;
      } else {
         float var2 = com.corrodinggames.rts.gameFramework.f.a(this.e, this.f, var1.e, var1.f);
         return var2 <= 90000.0F;
      }
   }

   protected strictfp long b() {
      return 5000L;
   }

   public abstract void b(au var1);

   public abstract String a();

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.c((au)var1);
   }
}
