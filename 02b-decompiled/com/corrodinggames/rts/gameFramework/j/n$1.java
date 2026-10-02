package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.n;
import com.corrodinggames.rts.gameFramework.j.t;
import java.util.List;
import java.util.concurrent.Callable;

final class n$1 implements Callable {

   // $FF: synthetic field
   final String a;
   // $FF: synthetic field
   final List b;
   // $FF: synthetic field
   final boolean c;
   // $FF: synthetic field
   final boolean d;


   n$1(String var1, List var2, boolean var3, boolean var4) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
   }

   public t a() {
      try {
         n.a("Running doSingleRequest:" + this.a);
         return n.a(this.b, this.a, this.c);
      } catch (Exception var2) {
         com.corrodinggames.rts.gameFramework.l.e("Error on doSingleRequest:" + this.a + " - " + var2.getMessage());
         if(this.d) {
            var2.printStackTrace();
         }

         return null;
      }
   }

   // $FF: synthetic method
   public Object call() {
      return this.a();
   }
}
