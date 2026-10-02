package com.corrodinggames.rts.game.units.e;

import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.v;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.utility.ai;
import java.util.ArrayList;

public class b extends j implements com.corrodinggames.rts.game.units.d {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   public static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   public static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   public static com.corrodinggames.rts.gameFramework.m.e e = null;
   public static com.corrodinggames.rts.gameFramework.m.e f = null;
   static com.corrodinggames.rts.gameFramework.m.e g = null;
   public static com.corrodinggames.rts.gameFramework.m.e[] h = new com.corrodinggames.rts.gameFramework.m.e[10];
   PointF[] i = new PointF[6];
   PointF[] j;
   static Paint k;
   static Paint l;
   static Paint m;
   static s n = new com.corrodinggames.rts.game.units.a.m(false);


   public strictfp ar f() {
      return ar.h;
   }

   public strictfp PointF[] b() {
      return this.i;
   }

   public strictfp PointF[] e_() {
      return this.j;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:h[this.bX.R()];
   }

   public static strictfp void K() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.builder);
      b = var0.bO.a(R$drawable.builder_dead);
      c = a(a, a.m(), a.l());
      d = com.corrodinggames.rts.game.n.a(a);
      e = var0.bO.a(R$drawable.builder_charge);
      f = var0.bO.a(R$drawable.builder_decharge);
      g = var0.bO.a(R$drawable.unit_icon_builder);
      h = com.corrodinggames.rts.game.n.a(g);
   }

   public strictfp boolean a(am var1) {
      return var1.q()?false:var1.bI();
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?b:d[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return null;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = b;
      this.S(0);
      this.bT = false;
      this.a(ab.b);
      return true;
   }

   public strictfp b(boolean var1) {
      super(var1);
      this.j = new PointF[this.i.length];
      k = new Paint();
      k.a(40, 0, 255, 0);
      k.a(true);
      k.a(2.0F);
      k.a(Paint$Cap.b);
      l = new Paint();
      l.a(k);
      l.a(55, 255, 60, 60);
      m = new Paint();
      m.a(60, 255, 255, 255);
      this.T(20);
      this.U(20);
      this.cj = 10.0F;
      this.ck = this.cj + 2.0F;
      this.cv = 170.0F;
      this.cu = this.cv;
      this.M = a;

      for(int var2 = 0; var2 < this.i.length; ++var2) {
         this.i[var2] = new PointF();
         this.j[var2] = new PointF();
      }

   }

   public static strictfp void a(float var0, com.corrodinggames.rts.game.units.d var1) {
      y var2 = (y)var1;
      PointF[] var3 = var1.b();
      PointF[] var4 = var1.e_();
      am var5 = var2.X();
      var2.aN = var5 != null;
      int var6;
      PointF var7;
      PointF var8;
      if(var5 != null) {
         for(var6 = 0; var6 < var3.length; ++var6) {
            var7 = var3[var6];
            var8 = var4[var6];
            var7.a = com.corrodinggames.rts.gameFramework.f.a(var7.a, var8.a, 0.1F * var0);
            var7.b = com.corrodinggames.rts.gameFramework.f.a(var7.b, var8.b, 0.1F * var0);
            var7.a += (var8.a - var7.a) * 0.04F * var0;
            var7.b += (var8.b - var7.b) * 0.04F * var0;
            float var9 = var5.cj * 0.75F;
            if(com.corrodinggames.rts.gameFramework.f.c(var7.a - var8.a) < 1.0F) {
               var8.a = com.corrodinggames.rts.gameFramework.f.d(-var9, var9);
            }

            if(com.corrodinggames.rts.gameFramework.f.c(var7.b - var8.b) < 1.0F) {
               var8.b = com.corrodinggames.rts.gameFramework.f.d(-var9, var9);
            }
         }
      } else if(var3[0].a != 0.0F || var3[0].b != 0.0F) {
         for(var6 = 0; var6 < var3.length; ++var6) {
            var7 = var3[var6];
            var8 = var4[var6];
            var7.a = 0.0F;
            var7.b = 0.0F;
            var8.a = 0.0F;
            var8.b = 0.0F;
         }
      }

   }

   public static strictfp void b(float var0, com.corrodinggames.rts.game.units.d var1) {
      y var2 = (y)var1;
      am var3 = var2.X();
      if(var3 != null) {
         boolean var4 = var2.Y();
         if(!var4 && var2.aO) {
            return;
         }

         com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
         PointF[] var6 = var1.b();
         Paint var7 = k;
         if(var4) {
            var7 = l;
         }

         ai var8 = var2.bn();

         for(int var9 = 0; var9 < var6.length; ++var9) {
            PointF var10 = var6[var9];
            float var11 = var3.eo + var10.a - var5.cw;
            float var12 = var3.ep - var3.eq + var10.b - var5.cx;
            var5.bO.a(var8.a + var10.a * 0.15F - var5.cw, var8.b - var8.c + var10.b * 0.15F - var5.cx - var2.eq, var11, var12, var7);
            var5.bO.k();
            var5.bO.b(var11, var12);
            var5.bO.a(0.5F, 0.5F);
            if(var4) {
               var5.bO.a(f, 0.0F, 0.0F, m);
            } else {
               var5.bO.a(e, 0.0F, 0.0F, m);
            }

            var5.bO.l();
         }
      }

   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         a(var1, this);
      }

   }

   public strictfp void a(float var1, boolean var2) {
      super.a(var1, var2);
      if(!this.bV) {
         b(var1, this);
      }

   }

   public strictfp float e(int var1) {
      return 30.0F;
   }

   public strictfp float f(int var1) {
      return 1.3F;
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         if(!this.bV) {
            float var3 = this.cL[0].f / this.e(0);
            if(var3 != 0.0F) {
               ai var4 = this.bn();
               var2.bO.i();
               var2.bO.b(var4.a - var2.cw, var4.b - var4.c - var2.cx);
               var2.bO.a(var3, var3);
               if(this.Y()) {
                  var2.bO.a(f, 0.0F, 0.0F, (Paint)null);
               } else {
                  var2.bO.a(e, 0.0F, 0.0F, (Paint)null);
               }

               var2.bO.j();
            }
         }

         return true;
      }
   }

   public strictfp void a(am var1, int var2) {}

   public strictfp float m() {
      return 30.0F;
   }

   public strictfp float b(int var1) {
      return 100.0F;
   }

   public strictfp float z() {
      return this.cK()?0.6F:0.8F;
   }

   public strictfp float A() {
      return this.cK()?1.7F:3.8F;
   }

   public strictfp float B() {
      return 0.35F;
   }

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp float C() {
      return 0.04F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp void a(s var1, boolean var2) {}

   public static strictfp void a(ArrayList var0, int var1) {
      var0.add(n);
      var0.add(new v(ar.a, 1, Integer.valueOf(1)));
      var0.add(new v(ar.f, 1, Integer.valueOf(2)));
      var0.add(new v(ar.g, 1, Integer.valueOf(3)));
      var0.add(new v(ar.b, 1, Integer.valueOf(4)));
      var0.add(new v(ar.c, 1, Integer.valueOf(5)));
      var0.add(new v(ar.d, 1, Integer.valueOf(6)));
      var0.add(new v(ar.y, 1, Integer.valueOf(7)));
      var0.add(new v(ar.B, 1, Integer.valueOf(8)));
      var0.add(new v(ar.J, 1, Integer.valueOf(9)));
      var0.add(new v(ar.G, 1, Integer.valueOf(10)));
      var0.add(new v(ar.C, 1, Integer.valueOf(14)));
      var0.add(new v(ar.D, 1, Integer.valueOf(15)));
   }

   public strictfp ArrayList N() {
      return this.f().a(this.V());
   }

   public strictfp boolean E() {
      return true;
   }

   public strictfp float g(int var1) {
      return 10.0F;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && !this.bV;
   }

   public strictfp float G() {
      return 1.0F;
   }

   public strictfp float H() {
      return 1.0F;
   }

   public strictfp boolean g(am var1, boolean var2) {
      return true;
   }

   // $FF: synthetic method
   public as r() {
      return this.f();
   }

}
