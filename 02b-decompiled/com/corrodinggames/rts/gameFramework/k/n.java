package com.corrodinggames.rts.gameFramework.k;


public final class n implements Comparable {

   public short a;
   public short b;
   public int c;


   public final strictfp void a(short var1, short var2) {
      this.a = var1;
      this.b = var2;
   }

   public final strictfp void a(int var1, int var2, int var3) {
      int var4 = var2 - this.a;
      int var5 = var3 - this.b;
      var4 = var4 > 0?var4:-var4;
      var5 = var5 > 0?var5:-var5;
      int var6 = var1 + (var4 + var5) * 11 + com.corrodinggames.rts.gameFramework.f.c(var4, var5) * -7;
      this.c = var6;
   }

   public final strictfp int a(n var1) {
      return this.c == var1.c?(this.a - var1.a != 0?this.a - var1.a:this.b - var1.b):this.c - var1.c;
   }

   public strictfp String toString() {
      return "PathOpenListNode [x=" + this.a + ", y=" + this.b + ", score=" + this.c + "]";
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((n)var1);
   }
}
