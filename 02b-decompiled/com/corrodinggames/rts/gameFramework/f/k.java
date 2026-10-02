package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.f.l;
import java.util.ArrayList;
import java.util.Iterator;

public class k {

   g a;
   com.corrodinggames.rts.gameFramework.l b;
   Paint c;
   Paint d;
   RectF e = new RectF();


   k(com.corrodinggames.rts.gameFramework.l var1, g var2) {
      this.a = var2;
      this.b = var1;
      this.a();
   }

   public void a() {
      this.d = new Paint();
      this.d.a(255, 255, 255, 255);
      this.d.a(true);
      this.d.c(true);
      this.d.a(Typeface.a(Typeface.c, 1));
      this.b.a(this.d, 16.0F);
      this.c = new Paint();
      this.c.a(255, 255, 255, 255);
      this.c.a(true);
      this.c.c(true);
      this.c.a(Typeface.a(Typeface.c, 0));
      this.b.a(this.c, 16.0F);
   }

   public int a(float var1, int var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.gameFramework.g.a var4 = var3.cg;
      if(var4.e() == com.corrodinggames.rts.gameFramework.g.f.a) {
         return var2;
      } else {
         ArrayList var5 = var4.d();
         ArrayList var6 = new ArrayList();
         String var7 = var4.e().name();
         var7 = com.corrodinggames.rts.gameFramework.h.a.a("gui.leaderboard.type." + var7, new Object[0]);
         l var8 = new l("", this.c, var7, this.d);
         var6.add(var8);
         int var9 = var2;
         byte var10 = 6;
         int var11 = (int)(var3.cl - var3.cq) - var10;
         int var12 = (int)(20.0F * var3.cj);
         Iterator var13 = var5.iterator();

         l var16;
         while(var13.hasNext()) {
            com.corrodinggames.rts.gameFramework.g.d var14 = (com.corrodinggames.rts.gameFramework.g.d)var13.next();
            Paint var15 = var14.a()?this.d:this.c;
            var16 = new l(var4.a(var14), this.d, " " + var14.b(), var15);
            var16.g = var14.c();
            var16.c = var14.d();
            var6.add(var16);
         }

         float var18 = (float)var3.bO.b(var7, this.c);
         float var19 = 0.0F;
         Iterator var20 = var6.iterator();

         while(var20.hasNext()) {
            var16 = (l)var20.next();
            if(var16.d > var19) {
               var19 = var16.d;
            }
         }

         var20 = var6.iterator();

         while(var20.hasNext()) {
            var16 = (l)var20.next();
            if(com.corrodinggames.rts.gameFramework.f.c(var16.d - var19) < 40.0F) {
               var16.d = var19;
            }

            float var17 = var16.d + var16.h;
            if(var17 > var18) {
               var18 = var17;
            }
         }

         var18 = com.corrodinggames.rts.gameFramework.f.e(var18 / 20.0F) * 20.0F;
         var11 -= (int)(var18 + 0.5F);
         this.e.a = (float)(var11 - var10);
         this.e.c = (float)(var11 + var10) + var18;
         this.e.b = (float)(var2 - var10 - var12);
         this.e.d = (float)(var2 + var10 + (var6.size() - 1) * var12);
         com.corrodinggames.rts.gameFramework.m.ag var21 = new com.corrodinggames.rts.gameFramework.m.ag();
         var21.b(Color.a(100, 0, 0, 0));
         var21.a(Paint$Style.c);
         var3.bO.a(this.e, (Paint)var21);

         for(int var22 = 0; var22 < var6.size(); ++var22) {
            l var23 = (l)var6.get(var22);
            var23.a.b(var23.c);
            var3.bO.a(var23.b, (float)var11, (float)var9, var23.a);
            var23.e.b(var23.g);
            var3.bO.a(var23.f, (float)var11 + var23.d, (float)var9, var23.e);
            var9 += var12;
         }

         return var9 + var12;
      }
   }
}
