package com.corrodinggames.rts.gameFramework;

import android.graphics.Paint;
import com.corrodinggames.rts.gameFramework.l;

class m {

   float a;
   Paint b;
   // $FF: synthetic field
   final l c;


   strictfp m(l var1) {
      this.c = var1;
   }

   strictfp void a() {
      float var1 = (float)this.c.e(this.a);
      if(this.b.k() != var1) {
         if(this.b instanceof com.corrodinggames.rts.gameFramework.m.ag) {
            ((com.corrodinggames.rts.gameFramework.m.ag)this.b).c(var1);
         } else {
            this.b.b(var1);
         }
      }

   }
}
