package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.b.d;

public class e {

   public com.corrodinggames.rts.gameFramework.m.e a;
   public com.corrodinggames.rts.gameFramework.m.e[] b;
   public int c;
   public int d;
   public int e = 1;
   public int f = 1;


   public strictfp void a(d var1) {
      int var3 = this.a.p;
      int var4 = this.a.q;
      this.c = var3;
      this.d = var4;
      if(var1.K > 0) {
         this.c = var1.K;
      } else if(var1.J > 0) {
         this.c = var3 / var1.J;
      }

      if(var1.L > 0) {
         this.d = var1.L;
      }

      if(this.c > 0) {
         this.f = var3 / this.c;
      }

      if(this.d > 0) {
         this.e = var4 / this.d;
      }

      if(this.f <= 0) {
         this.f = 1;
      }

      if(this.e <= 0) {
         this.e = 1;
      }

   }
}
