package com.corrodinggames.rts.game.units.d;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public class e extends i {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e[] b = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   float e;
   public float f;
   public float g;
   public int h;
   public float i;
   public float j;
   float k = 20.0F;
   int l = 0;


   public strictfp void a(as var1) {
      var1.a(this.e);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.e = var1.g();
      super.a(var1);
   }

   public static strictfp void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.base);
      c = var0.bO.a(R$drawable.base_dead);
      d = var0.bO.a(R$drawable.base_back);
      b = com.corrodinggames.rts.game.n.a(a);
   }

   public strictfp ar K() {
      return ar.e;
   }

   public strictfp boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = c;
      this.m = null;
      this.S(0);
      this.bT = false;
      this.a(ab.d);
      float var2 = this.eo;
      float var3 = this.ep;
      float var4 = 0.0F;
      var1.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
      com.corrodinggames.rts.gameFramework.d.e var5 = var1.bR.a(var2, var3, this.eq, Color.a(255, 255, 255, 255));
      if(var5 != null) {
         var5.G = 8.0F;
         var5.F = 5.0F;
         var5.E = 0.9F;
         var5.V = 20.0F;
         var5.W = var5.V;
         var5.r = true;
      }

      var1.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
      var5 = var1.bR.c(var2, var3, var4, -1127220);
      if(var5 != null) {
         var5.G = 0.2F;
         var5.F = 2.0F;
         var5.ar = 2;
         var5.V = 45.0F;
         var5.W = var5.V;
         var5.U = 0.0F;
      }

      float var6 = 40.0F;
      float var7 = 70.0F;
      var1.bR.a(this.eo, this.ep, this.eq, var6, var7);
      com.corrodinggames.rts.gameFramework.d.f.a(this.eo, this.ep);
      com.corrodinggames.rts.gameFramework.d.f.b(this.eo, this.ep).a = 800.0F;
      return true;
   }

   public strictfp void a(int var1) {}

   public strictfp void S() {
      super.S();
      if(this.bV) {
         this.m = null;
      } else {
         this.m = d;
      }

   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?c:b[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp e(boolean var1) {
      super(var1);
      this.M = a;
      this.m = d;
      this.T(53);
      this.U(68);
      this.cj = 30.0F;
      this.ck = this.cj;
      this.cv = 4000.0F;
      this.cu = this.cv;
      this.S(3);
      this.n.a(-1, -1, 1, 1);
      this.o.a(-1, -1, 1, 2);
   }

   public strictfp RectF cF() {
      RectF var1 = super.cF();
      var1.a(6.0F, 0.0F);
      return var1;
   }

   public strictfp void a(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      super.a(var1);
      if(this.bT() && !this.bV) {
         this.k = com.corrodinggames.rts.gameFramework.f.a(this.k, var1);
         if(this.k == 0.0F) {
            this.k = 5.0F;
            ++this.l;
            if(this.l > 6) {
               this.l = 0;
               this.k = 70.0F;
            }

            if(this.l <= 3) {
               this.s = this.l;
            } else {
               this.s = 6 - this.l;
            }
         }

         this.f += var1;
         ++this.h;
         this.i += 10.0F;
         if(this.j > var1) {
            this.j = var1;
         }

         this.g += var1;
         this.e += var1;
         if(this.e > com.corrodinggames.rts.game.n.ap - 0.1F) {
            this.e -= com.corrodinggames.rts.game.n.ap;
            this.bX.b(this.cy() * (com.corrodinggames.rts.game.n.ap / com.corrodinggames.rts.game.n.ao));
         }

      }
   }

   public strictfp float cy() {
      return 18.0F;
   }

   public strictfp float q(int var1) {
      return 70.0F;
   }

   public strictfp void a(am var1, int var2) {
      com.corrodinggames.rts.game.f var3 = com.corrodinggames.rts.game.f.a(this, this.eo, this.ep);
      PointF var4 = this.K(var2);
      var3.K = var4.a;
      var3.L = var4.b;
      var3.ar = Color.a(255, 230, 230, 50);
      var3.U = this.q(var2);
      var3.l = var1;
      var3.h = 180.0F;
      var3.t = 2.0F;
      var3.r = 5.0F;
      var3.aH = true;
      var3.aM = true;
      var3.aQ = true;
      var3.aG = true;
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      var5.bR.a(var3, -1118720);
      var5.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.8F, this.eo, this.ep);
   }

   public strictfp float m() {
      return 280.0F;
   }

   public strictfp float b(int var1) {
      return 70.0F;
   }

   public strictfp float c(int var1) {
      return 999.0F;
   }

   public strictfp boolean b(int var1, float var2) {
      return false;
   }

   public strictfp boolean l() {
      return true;
   }

   public static strictfp void a(ArrayList var0, int var1) {
      var0.add(new com.corrodinggames.rts.game.units.a.o());
      var0.add(new com.corrodinggames.rts.game.units.a.l(ar.h, 1.0F));
   }

   public strictfp ArrayList N() {
      return this.K().a(this.V());
   }

   public strictfp float a(am var1, float var2, com.corrodinggames.rts.game.f var3) {
      if(var2 > 2500.0F) {
         var2 = 2500.0F;
      }

      return super.a(var1, var2, var3);
   }

   public strictfp boolean bJ() {
      return true;
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      y.a(this, var2);
   }

   public strictfp int s() {
      return 20;
   }

   public strictfp int bp() {
      return 35;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

}
