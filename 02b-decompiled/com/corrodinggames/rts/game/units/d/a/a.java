package com.corrodinggames.rts.game.units.d.a;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.a.a$1;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public class a extends b {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   public static s e = new a$1(102);
   static ArrayList f = new ArrayList();


   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:d[this.bX.R()];
   }

   public static strictfp void b() {
      l var0 = l.B();
      a = var0.bO.a(R$drawable.anti_air_top);
      b = var0.bO.a(R$drawable.anti_air_top_l2);
      c = var0.bO.a(R$drawable.unit_icon_building_air_turrent);
      d = n.a(c);
   }

   public strictfp a(boolean var1) {
      super(var1);
      this.cv = 800.0F;
      this.cu = this.cv;
   }

   public strictfp float m() {
      return !this.j?250.0F:320.0F;
   }

   public strictfp float b(int var1) {
      return !this.j?80.0F:70.0F;
   }

   public strictfp float e(int var1) {
      return !this.j?super.e(var1):(var1 == 2?25.0F:super.e(var1));
   }

   public strictfp PointF E(int var1) {
      if(this.j && var1 != 0) {
         float var2 = this.E()?this.cg:this.cL[var1].a;
         PointF var3 = this.G(var1);
         var2 += var1 == 1?-30.0F:30.0F;
         float var4 = var3.a + com.corrodinggames.rts.gameFramework.f.k(var2) * 10.0F;
         float var5 = var3.b + com.corrodinggames.rts.gameFramework.f.j(var2) * 10.0F;
         bg.a(var4, var5);
         return bg;
      } else {
         return super.E(var1);
      }
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.t = 0.3F;
      var4.r = 6.0F;
      if(!this.j) {
         var4.ar = Color.a(255, 230, 230, 50);
         var4.U = 60.0F;
         var4.h = 220.0F;
      } else {
         var4.ar = Color.a(255, 230, 50, 50);
         var4.U = 60.0F;
         var4.h = 250.0F;
         var4.t = 0.5F;
         var4.r = 7.0F;
      }

      var4.P = 4;
      var4.x = 1.0F;
      var4.l = var1;
      var4.aH = false;
      var4.aI = 0.0F;
      var4.aJ = 0.0F;
      var4.aM = true;
      var4.aQ = true;
      var4.aG = true;
      l var6 = l.B();
      float var7 = 1.0F + com.corrodinggames.rts.gameFramework.f.c(-0.07F, 0.07F);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.3F, var7, var3.a, var3.b);
      var6.bR.a(var4, -1118720);
      var6.bR.a(var3.a, var3.b, this.eq, -1127220);
   }

   public strictfp ar K() {
      return this.j?ar.T:ar.g;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return !this.j?a:b;
   }

   public strictfp boolean af() {
      return true;
   }

   public strictfp boolean ag() {
      return false;
   }

   public strictfp void s(float var1) {
      byte var2 = 0;
      if(this.cL[var2].a()) {
         this.cL[var2].a += this.c(var2) * var1 * 0.1F;
      }

   }

   public strictfp float g(int var1) {
      return 9.0F;
   }

   public strictfp float c(int var1) {
      return 6.0F;
   }

   public strictfp float B() {
      return 1.0F;
   }

   public strictfp boolean u(int var1) {
      return !this.j?super.u(var1):(var1 == 0?false:super.u(var1));
   }

   public strictfp int v(int var1) {
      return !this.j?-1:(var1 == 1?0:(var1 == 2?0:-1));
   }

   public strictfp int bl() {
      return 3;
   }

   public strictfp boolean r(int var1) {
      return this.j || var1 <= 1;
   }

   public strictfp void a(j var1) {
      if(var1.j.equals(e.N())) {
         this.a(2);
         this.W();
      }

   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return !this.j?e.N():s.i;
   }

   public strictfp void a(ArrayList var1) {
      var1.clear();
   }

   public strictfp void a(int var1) {
      if(var1 == 1) {
         this.j = false;
      } else if(var1 == 2 && !this.j) {
         this.j = true;
         this.cv += 600.0F;
         this.cu += 600.0F;
      }

   }

   public strictfp ArrayList N() {
      return f;
   }

   // $FF: synthetic method
   public as r() {
      return this.K();
   }

   static {
      f.add(e);
   }
}
