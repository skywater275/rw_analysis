package com.corrodinggames.rts.gameFramework.m;

import com.corrodinggames.rts.gameFramework.m.s;

public final class t {

   public int a;
   public s[] b;


   public t(int var1) {
      if(var1 < 0) {
         throw new IllegalArgumentException("capacity < 0: " + var1);
      } else {
         this.b = var1 == 0?new s[0]:new s[var1];
      }
   }

   public final boolean a(s var1) {
      s[] var2 = this.b;
      int var3 = this.a;
      if(var3 == var2.length) {
         s[] var4 = new s[var3 + (var3 < 6?12:var3 >> 1)];
         System.arraycopy(var2, 0, var4, 0, var3);
         var2 = var4;
         this.b = var4;
      }

      var2[var3] = var1;
      this.a = var3 + 1;
      return true;
   }
}
