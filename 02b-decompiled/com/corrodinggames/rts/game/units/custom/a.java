package com.corrodinggames.rts.game.units.custom;


class a implements Comparable {

   public float a;
   public float b;
   public float c;
   public float d;


   public a(float var1, float var2) {
      this.a = var1;
      this.b = var2;
   }

   public int a(a var1) {
      return this.a == var1.a?0:(this.a > var1.a?1:-1);
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((a)var1);
   }
}
