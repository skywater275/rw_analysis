package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.p;

public class t {

   public static final p[] a = new p[0];
   p[] b;
   int c;


   public t() {
      this.b = a;
      this.c = 0;
   }

   public boolean a(p var1) {
      p[] var2 = this.b;
      int var3 = this.c;
      if(var3 == var2.length) {
         p[] var4 = new p[var3 + (var3 < 6?12:var3 >> 1)];
         System.arraycopy(var2, 0, var4, 0, var3);
         var2 = var4;
         this.b = var4;
      }

      var2[var3] = var1;
      this.c = var3 + 1;
      return true;
   }

}
