package com.corrodinggames.rts.java;

import com.corrodinggames.rts.java.o;
import com.corrodinggames.rts.java.p;
import com.corrodinggames.rts.java.audio.Sound;

public class q extends com.corrodinggames.rts.gameFramework.a.i {

   Sound a;
   // $FF: synthetic field
   final o b;


   public q(o var1, String var2, com.corrodinggames.rts.gameFramework.a.h var3) {
      super(var2, var3);
      this.b = var1;
   }

   public void a(float var1, float var2, int var3, int var4, float var5) {
      p var6 = (p)this.b.c.a();
      if(var6 != null) {
         var6.b = var1;
         var6.c = var2;
         var6.d = var3;
         var6.e = var4;
         var6.f = var5;
         var6.a = this;
         this.b.b.offer(var6);
      }
   }

   public void b(float var1, float var2, int var3, int var4, float var5) {
      if(this.a == null) {
         com.corrodinggames.rts.gameFramework.l.e("Sound not loaded");
      } else {
         synchronized(this.b.b()) {
            float var7 = 0.0F;
            float var8 = com.corrodinggames.rts.gameFramework.f.f(var1, var2);
            this.a.play(var8, var5, var7);
         }
      }
   }

   public int a() {
      return this.a.getBytesUsed();
   }
}
