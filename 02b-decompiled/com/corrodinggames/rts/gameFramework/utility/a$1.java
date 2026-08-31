package com.corrodinggames.rts.gameFramework.utility;

import java.util.Comparator;

final class a$1 implements Comparator {

   // $FF: synthetic field
   final Thread a;


   a$1(Thread var1) {
      this.a = var1;
   }

   public int a(Thread var1, Thread var2) {
      return var1 == var2?0:(var1 == this.a?1:(var2 == this.a?-1:var2.getName().compareTo(var1.getName())));
   }

   // $FF: synthetic method
   public int compare(Object var1, Object var2) {
      return this.a((Thread)var1, (Thread)var2);
   }
}
