package com.corrodinggames.rts.gameFramework.utility;


public class ad {

   private final Object[] a;
   private int b;
   private final boolean c = false;


   public ad(int var1) {
      if(var1 <= 0) {
         throw new IllegalArgumentException("The max pool size must be > 0");
      } else {
         this.a = new Object[var1];
      }
   }

   public Object a() {
      if(this.b > 0) {
         int var1 = this.b - 1;
         Object var2 = this.a[var1];
         this.a[var1] = null;
         --this.b;
         return var2;
      } else {
         return null;
      }
   }

   public boolean a(Object var1) {
      if(this.b < this.a.length) {
         this.a[this.b] = var1;
         ++this.b;
         return true;
      } else {
         return false;
      }
   }
}
