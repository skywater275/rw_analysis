package com.corrodinggames.rts.gameFramework.g;

import com.corrodinggames.rts.gameFramework.g.f;

public abstract class d implements Comparable {

   private int a;


   public void b(f var1) {
      this.a = this.a(var1);
   }

   public abstract String b();

   public abstract boolean a();

   public abstract int c();

   public abstract int d();

   public abstract int a(f var1);

   public int a(d var1) {
      return this.a == var1.a?this.b().compareTo(var1.b()):var1.a - this.a;
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((d)var1);
   }

   // $FF: synthetic method
   static int b(d var0) {
      return var0.a;
   }
}
