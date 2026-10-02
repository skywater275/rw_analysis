package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Paint$Cap;
import com.corrodinggames.rts.gameFramework.bn;

public class aa {

   bn a;
   String b;
   int c;
   com.corrodinggames.rts.gameFramework.m.ag[] d;
   com.corrodinggames.rts.gameFramework.m.ag[] e;


   public com.corrodinggames.rts.gameFramework.m.ag a(int var1, boolean var2) {
      int var3 = var1 / 25;
      if(var3 < 0) {
         var3 = 0;
      }

      if(var3 > 10) {
         var3 = 10;
      }

      return var2?this.e[var3]:this.d[var3];
   }

   public aa(bn var1, String var2, int var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = new com.corrodinggames.rts.gameFramework.m.ag[11];
      this.e = new com.corrodinggames.rts.gameFramework.m.ag[11];

      for(int var4 = 0; var4 < 11; ++var4) {
         int var5 = var4 * 25;
         if(var4 == 10) {
            var5 = 255;
         }

         this.d[var4] = new com.corrodinggames.rts.gameFramework.m.ag();
         this.d[var4].a(2.0F);
         if(com.corrodinggames.rts.gameFramework.l.aZ) {
            this.d[var4].a(3.0F);
         }

         this.d[var4].a(Paint$Cap.b);
         this.d[var4].b(var3);
         this.d[var4].c(var5);
         this.e[var4] = new com.corrodinggames.rts.gameFramework.m.ag();
         this.e[var4].b(-13162713);
         this.e[var4].c(var5);
         this.e[var4].a(5.0F);
         this.e[var4].a(Paint$Cap.b);
      }

   }
}
