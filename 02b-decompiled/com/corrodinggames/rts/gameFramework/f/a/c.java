package com.corrodinggames.rts.gameFramework.f.a;

import com.corrodinggames.rts.gameFramework.f.a.d;

public class c {

   public int a;
   public int b;
   public d c;
   public int d = -1;


   public static c a(int var0, int var1) {
      c var2 = new c();
      var2.a = var0;
      var2.b = var1;
      var2.c = d.b;
      var2.d = 1;
      return var2;
   }

   public static c b(int var0, int var1) {
      c var2 = new c();
      var2.a = var0;
      var2.b = var1;
      var2.c = d.a;
      var2.d = 1;
      return var2;
   }

   public boolean a() {
      return this.c == d.b;
   }

   public boolean b() {
      return this.c == d.a;
   }
}
