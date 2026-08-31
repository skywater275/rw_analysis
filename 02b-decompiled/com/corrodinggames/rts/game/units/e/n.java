package com.corrodinggames.rts.game.units.e;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.utility.y;

public class n extends j {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   int f;
   float g;
   float h;
   Rect i = new Rect();


   public strictfp ar b() {
      return ar.i;
   }

   public static strictfp void f() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      b = var0.bO.a(R$drawable.tank2);
      a = var0.bO.a(R$drawable.tank2_dead);
      c = var0.bO.a(R$drawable.tank2_turret);
      d = var0.bO.a(R$drawable.tank2_shadow);
      e = com.corrodinggames.rts.game.n.a(b);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:e[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return d;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && !this.bV;
   }

   public strictfp float G() {
      return 3.0F;
   }

   public strictfp float H() {
      return 3.0F;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return c;
   }

   public strictfp boolean e() {
      this.M = a;
      this.S(0);
      this.bT = false;
      this.a(ab.b);
      return true;
   }

   public strictfp n(boolean var1) {
      super(var1);
      this.a(b, 3);
      this.cj = 11.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 210.0F;
      this.cu = this.cv;
      this.M = b;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         if(this.cf != 0.0F) {
            this.g += var1;
            if(this.g > 1.0F) {
               this.g = 0.0F;
               ++this.f;
               if(this.f > 2) {
                  this.f = 0;
               }
            }

            if(this.cf > 0.0F && this.el) {
               this.h += var1;
               if(this.h > 9.0F) {
                  this.h = 0.0F;
                  this.K();
               }
            }
         }

      }
   }

   public strictfp void K() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();

      for(int var2 = 0; var2 <= 1; ++var2) {
         float var3 = (float)(var2 == 0?-20:20);
         float var4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg + 180.0F + var3) * this.cj;
         float var5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg + 180.0F + var3) * this.cj;
         var1.bR.c(var4, var5, this.eq, this.cg + 180.0F, 0);
      }

   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b);
      PointF var5 = this.K(var2);
      var4.K = var5.a;
      var4.L = var5.b;
      var4.U = 30.0F;
      var4.l = var1;
      var4.h = 60.0F;
      var4.t = 3.0F;
      var4.P = 1;
      var4.x = 1.0F;
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      var6.bR.a(var3.a, var3.b, this.eq, -1127220);
      var6.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      float var7 = 1.0F + com.corrodinggames.rts.gameFramework.f.c(-0.07F, 0.07F);
      var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.q, 0.3F, var7, var3.a, var3.b);
   }

   public strictfp float m() {
      return 130.0F;
   }

   public strictfp float b(int var1) {
      return 75.0F;
   }

   public strictfp float z() {
      return 1.0F;
   }

   public strictfp float A() {
      return 4.1F;
   }

   public strictfp float c(int var1) {
      return 4.0F;
   }

   public strictfp float B() {
      return 0.25F;
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         y.a((com.corrodinggames.rts.game.units.y)this);
         if(!this.bV) {
            ;
         }

         return true;
      }
   }

   public strictfp float C() {
      return 0.07F;
   }

   public strictfp float D() {
      return 0.17F;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return false;
   }

   public strictfp float g(int var1) {
      return 20.0F;
   }

   public strictfp Rect a_(boolean var1) {
      return var1?super.a_(var1):(this.bV?super.a_(var1):super.a(var1, this.f));
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
