package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.w;
import com.corrodinggames.rts.gameFramework.j.x;

final class ad$2 extends w {

   // $FF: synthetic field
   final Object d;


   strictfp ad$2(Object var1) {
      this.d = var1;
   }

   public strictfp void a(String var1) {
      super.a(var1);
      Object var2 = this.d;
      synchronized(this.d) {
         this.d.notify();
      }
   }

   public strictfp void a(String var1, x var2, Exception var3) {
      super.a(var1, var2, var3);
      Object var4 = this.d;
      synchronized(this.d) {
         this.d.notify();
      }
   }
}
