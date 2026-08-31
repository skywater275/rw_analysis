package com.corrodinggames.rts.gameFramework.j;

import com.corrodinggames.rts.gameFramework.j.ae;

final class ad$1 extends ae {

   // $FF: synthetic field
   final Object a;


   strictfp ad$1(Object var1) {
      this.a = var1;
   }

   public strictfp void a(String var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.l.e("Entered password");
      if(var2.bX.C) {
         com.corrodinggames.rts.gameFramework.l.a("Cannot enter a password when we are a server");
      } else {
         var2.bX.n = var1;
      }

      Object var3 = this.a;
      synchronized(this.a) {
         this.a.notify();
      }
   }

   public strictfp void a() {
      Object var1 = this.a;
      synchronized(this.a) {
         this.a.notify();
      }
   }
}
