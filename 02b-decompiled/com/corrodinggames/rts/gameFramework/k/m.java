package com.corrodinggames.rts.gameFramework.k;

import com.corrodinggames.rts.gameFramework.k.a;
import com.corrodinggames.rts.gameFramework.k.e;
import com.corrodinggames.rts.gameFramework.k.n;

public final class m {

   int a;
   int b;
   public static int c;
   final a d;
   final e e = new e();


   strictfp m() {
      short var1 = 1000;
      this.d = new a(var1 + 100);

      for(int var2 = 0; var2 < var1; ++var2) {
         this.d.a(new n());
      }

   }

   strictfp n a() {
      if(this.d.b == 0) {
         ++c;
         return new n();
      } else {
         return this.d.b();
      }
   }

   final strictfp void a(n var1) {
      if(var1 != null) {
         this.d.b(var1);
      }

   }

   strictfp void b() {
      if(this.d.size() > '\uc350') {
         com.corrodinggames.rts.gameFramework.l.e("PathOpenList: resetPool:memoryPool over 50000 clearing");
         this.d.clear();
      }

      this.e.a(this);
   }

   public strictfp void a(int var1, int var2) {
      this.b();
      this.a = var1;
      this.b = var2;
   }

   public final strictfp void a(int var1, short var2, short var3) {
      n var4 = this.a();
      var4.a(var2, var3);
      var4.a(var1, this.a, this.b);
      this.e.a(var4);
   }

   public final strictfp n c() {
      n var1 = this.e.a();
      if(var1 != null) {
         this.a(var1);
      }

      return var1;
   }
}
