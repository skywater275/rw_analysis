package com.corrodinggames.rts.game.units.d;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.c$1;
import com.corrodinggames.rts.game.units.d.c$2;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;
import java.util.Iterator;

public class c extends i {

   static com.corrodinggames.rts.gameFramework.m.e[] a = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   boolean c;
   int d;
   float e;
   static com.corrodinggames.rts.gameFramework.m.e f = null;
   static com.corrodinggames.rts.gameFramework.m.e[] g = new com.corrodinggames.rts.gameFramework.m.e[10];
   PointF h = new PointF();
   Rect i = new Rect();
   static com.corrodinggames.rts.game.units.a.s j = new c$1(145);
   static com.corrodinggames.rts.game.units.a.s k = new c$2(144);
   static ArrayList l = new ArrayList();


   public strictfp void a(as var1) {
      var1.a(this.c);
      var1.a(this.d);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.c = var1.e();
      if(var1.b() >= 30) {
         this.d = var1.f();
      }

      super.a(var1);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:g[this.bX.R()];
   }

   public static strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      b = var0.bO.a(R$drawable.antinuke_launcher_dead);
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.antinuke_launcher);
      a = com.corrodinggames.rts.game.n.a(var1);
      var1.n();
      f = var0.bO.a(R$drawable.unit_icon_building_turrent);
      g = com.corrodinggames.rts.game.n.a(f);
   }

   public strictfp boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = b;
      this.S(0);
      this.bT = false;
      this.a(ab.h);
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?b:a[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp void a(int var1) {}

   public strictfp c(boolean var1) {
      super(var1);
      this.M = a[a.length - 1];
      this.b(this.M);
      this.cj = 24.0F;
      this.ck = this.cj;
      this.cv = 2800.0F;
      this.cu = this.cv;
      this.n.a(-1, -1, 1, 1);
      this.o.a(-1, -1, 1, 1);
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         if(this.d > 0) {
            com.corrodinggames.rts.game.f var2 = null;
            this.e = com.corrodinggames.rts.gameFramework.f.a(this.e, var1);
            if(this.e == 0.0F) {
               this.e = 15.0F;
               Iterator var3 = com.corrodinggames.rts.game.f.a.iterator();

               while(var3.hasNext()) {
                  com.corrodinggames.rts.game.f var4 = (com.corrodinggames.rts.game.f)var3.next();
                  if(var4.D && var4.eq > 50.0F) {
                     float var5 = 2200.0F;
                     float var6 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, var4.eo, var4.ep);
                     if(var6 < var5 * var5) {
                        float var7 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, var4.n, var4.o);
                        if(var7 < 1000000.0F && (var4.j == null || !var4.j.bX.d(this.bX) && var4.j.bX != this.bX) && !this.a(var4)) {
                           var2 = var4;
                        }
                     }
                  }
               }
            }

            if(var2 != null) {
               this.b(var2);
            }
         }

      }
   }

   public strictfp boolean a(com.corrodinggames.rts.game.f var1) {
      Object[] var2 = com.corrodinggames.rts.game.f.a.a();
      int var3 = 0;

      for(int var4 = com.corrodinggames.rts.game.f.a.a; var3 < var4; ++var3) {
         com.corrodinggames.rts.game.f var5 = (com.corrodinggames.rts.game.f)var2[var3];
         if(var5 != var1 && var5.q == var1) {
            return true;
         }
      }

      return false;
   }

   public strictfp void b(com.corrodinggames.rts.game.f var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.d > 0) {
         --this.d;
         byte var3 = 0;
         PointF var4 = this.E(var3);
         com.corrodinggames.rts.game.f var5 = com.corrodinggames.rts.game.f.a(this, var4.a, var4.b);
         var5.S(10);
         var5.P = 10;
         var5.R = 0;
         var5.x = 1.0F;
         var5.aC = true;
         var5.q = var1;
         var5.h = 99999.0F;
         var5.t = 0.2F;
         var5.r = 6.5F;
         var5.ar = Color.a(255, 80, 60, 180);
         var5.U = 600.0F;
         var5.aH = true;
         var5.aM = true;
         var5.aQ = true;
         var5.C = true;
         var5.aI = 80.0F;
         var5.aJ = 100.0F;
         var5.aL = 2.0F;
         var2.bR.a(var4.a, var4.b, this.eq, -1127220);
         com.corrodinggames.rts.gameFramework.d.e var6 = var2.bR.d(var4.a, var4.b, 0.0F, -1);
         if(var6 != null) {
            var6.G = 0.5F;
            var6.F = 2.1F;
            var6.ar = 2;
            var6.V = 90.0F;
            var6.W = var6.V;
            var6.U = 0.0F;
         }

         float var7 = 1.5F;
         var2.bM.a(com.corrodinggames.rts.gameFramework.a.e.D, 0.15F, var7, var4.a, var4.b);
      }
   }

   public strictfp PointF E(int var1) {
      bg.a(this.eo, this.ep - 9.0F);
      return bg;
   }

   public strictfp void a(am var1, int var2) {}

   public strictfp float m() {
      return 1000.0F;
   }

   public strictfp float c(int var1) {
      return 4.0F;
   }

   public strictfp boolean b(int var1, float var2) {
      return false;
   }

   public strictfp boolean c(float var1) {
      return super.c(var1);
   }

   public strictfp ar K() {
      return ar.D;
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp float g(int var1) {
      return 1.0F;
   }

   public strictfp float bV() {
      return super.bV();
   }

   public strictfp void M() {
      ++this.d;
   }

   public strictfp void a(j var1) {
      if(var1.j.equals(k.N())) {
         this.M();
      }

   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return this.d < 4?k.N():com.corrodinggames.rts.game.units.a.s.i;
   }

   public strictfp boolean ck() {
      return false;
   }

   public strictfp ArrayList N() {
      return l;
   }

   public strictfp void e(float var1) {
      super.e(var1);
   }

   public strictfp void O() {}

   public strictfp void cb() {
      float var1 = 990.0F;
      boolean var2 = false;
      boolean var3 = true;
      y.a(this, var1, var2, var3);
   }

   public strictfp boolean a(com.corrodinggames.rts.gameFramework.l var1) {
      return this.cG?true:super.a(var1);
   }

   public strictfp float a(am var1, float var2, com.corrodinggames.rts.game.f var3) {
      if(var2 > 2600.0F) {
         var2 = 2600.0F;
      }

      return super.a(var1, var2, var3);
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

   static {
      l.add(j);
      l.add(k);
   }
}
