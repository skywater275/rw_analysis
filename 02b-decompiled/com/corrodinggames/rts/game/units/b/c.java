package com.corrodinggames.rts.game.units.b;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.b.b;
import com.corrodinggames.rts.game.units.b.c$1;
import com.corrodinggames.rts.game.units.b.c$2;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public class c extends b {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] f = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] g = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e o = null;
   static com.corrodinggames.rts.gameFramework.m.e p = null;
   float q;
   boolean r = true;
   boolean s = true;
   float t = 0.0F;
   float u = 0.0F;
   protected Paint v = new ag();
   PointF w = new PointF();
   Rect x = new Rect();
   public static final s y = new c$1(151);
   public static final s z = new c$2(152);
   static ArrayList A = new ArrayList();


   public strictfp void a(as var1) {
      var1.a(this.r);
      var1.a(this.t);
      var1.a(this.u);
      super.a(var1);
   }

   public strictfp void a(k var1) {
      this.r = var1.e();
      this.s = !this.Q();
      if(var1.b() >= 21) {
         this.t = var1.g();
      }

      if(var1.b() >= 22) {
         this.u = var1.g();
      }

      this.M();
      super.a(var1);
   }

   public strictfp boolean Q() {
      return this.eq < -1.0F;
   }

   public strictfp boolean b() {
      return !this.r || this.eq < 0.0F;
   }

   public strictfp ao h() {
      return this.cp?ao.d:(this.b()?ao.e:ao.d);
   }

   public strictfp ar f() {
      return ar.M;
   }

   public static strictfp void L() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.amphibious_jet);
      c = var0.bO.a(R$drawable.amphibious_jet_shadow);
      a = var0.bO.a(R$drawable.amphibious_jet_dead);
      e = n.a(b);
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.amphibious_jet_p1);
      com.corrodinggames.rts.gameFramework.m.e var2 = var0.bO.a(R$drawable.amphibious_jet_p2);
      f = n.a(var1);
      g = n.a(var2);
      o = a(var1);
      p = a(var2);
   }

   public strictfp boolean aQ() {
      if(super.aQ()) {
         this.f(true);
         return true;
      } else {
         return false;
      }
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else if(this.bV) {
         return true;
      } else {
         this.f(false);
         if(!this.bV) {
            for(int var2 = 0; var2 < this.bl(); ++var2) {
               if(var2 != this.ds()) {
                  float var3 = this.cL[var2].f / this.e(var2);
                  if(var3 != 0.0F) {
                     l var4 = l.B();
                     PointF var5 = this.E(var2);
                     var4.bO.i();
                     var4.bO.b(var5.a - var4.cw, var5.b - var4.cx - this.eq);
                     var4.bO.a(var3 * 0.7F, var3 * 0.7F);
                     var4.bO.a(com.corrodinggames.rts.game.units.e.l.e, 0.0F, 0.0F, (Paint)null);
                     var4.bO.j();
                  }
               }
            }
         }

         return true;
      }
   }

   public strictfp void f(boolean var1) {
      l var2 = l.B();
      Paint var3;
      if(!var1) {
         var3 = this.aN();
      } else {
         this.v.a(50, 255, 255, 255);
         var3 = this.v;
      }

      for(int var5 = 0; var5 <= 1; ++var5) {
         PointF var6 = this.a(var5, var1);
         float var7 = var6.a - var2.cw;
         float var8 = var6.b - var2.cx;
         float var9 = this.d(false) - 90.0F;
         if(!var1) {
            var8 -= this.eq;
         }

         com.corrodinggames.rts.gameFramework.m.e var4;
         if(var5 == 0) {
            if(var1) {
               var4 = p;
            } else {
               var4 = g[this.bX.R()];
            }

            var9 += 0.0F;
         } else {
            if(var1) {
               var4 = o;
            } else {
               var4 = f[this.bX.R()];
            }

            var9 -= 0.0F;
         }

         var2.bO.a(var4, var7, var8, var9, var3);
      }

   }

   public strictfp int bl() {
      return 3;
   }

   public strictfp PointF G(int var1) {
      if(var1 == this.ds()) {
         return super.G(var1);
      } else {
         float var2 = this.d(false) - 90.0F;
         PointF var3 = this.a(var1, false);
         float var4 = var3.a;
         float var5 = var3.b;
         var4 += com.corrodinggames.rts.gameFramework.f.k(var2) * 5.0F;
         var5 += com.corrodinggames.rts.gameFramework.f.j(var2) * 5.0F;
         bh.a(var4, var5);
         return bh;
      }
   }

   public strictfp PointF a(int var1, boolean var2) {
      float var3 = this.d(false) - 90.0F;
      if(var1 == this.ds()) {
         throw new RuntimeException("index==2 is for base");
      } else {
         float var4 = this.eo;
         float var5 = this.ep;
         float var6 = this.u * 4.0F;
         var6 = com.corrodinggames.rts.gameFramework.f.b(var6, 0.0F, 1.0F);
         float var7 = this.u * 2.0F - 1.0F;
         var7 = com.corrodinggames.rts.gameFramework.f.b(var7, 0.0F, 1.0F);
         var4 += com.corrodinggames.rts.gameFramework.f.k(var3) * (7.0F - 5.0F * var6);
         var5 += com.corrodinggames.rts.gameFramework.f.j(var3) * (7.0F - 5.0F * var6);
         float var8 = (float)(-90 + 180 * var1);
         var4 += com.corrodinggames.rts.gameFramework.f.k(var3 + var8) * (12.0F - 5.0F * var7);
         var5 += com.corrodinggames.rts.gameFramework.f.j(var3 + var8) * (12.0F - 5.0F * var7);
         this.w.a(var4, var5);
         return this.w;
      }
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:e[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return d;
   }

   public strictfp boolean e() {
      l var1 = l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = a;
      this.S(0);
      this.bT = false;
      return true;
   }

   public strictfp c(boolean var1) {
      super(var1);
      this.b(b);
      this.cj = 12.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 530.0F;
      this.cu = this.cv;
      this.M = b;
      this.N = c;
      this.eq = 0.0F;
      this.S(5);
   }

   public strictfp boolean i() {
      return !this.b();
   }

   public strictfp void M() {
      if(!this.s) {
         this.S(1);
      } else {
         this.S(5);
      }

   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         l var2 = l.B();
         this.q += 2.0F * var1;
         if(this.q > 360.0F) {
            this.q -= 360.0F;
         }

         float var3;
         if(this.r) {
            var3 = 20.0F + com.corrodinggames.rts.gameFramework.f.j(this.q) * 1.5F;
         } else {
            var3 = -8.0F;
         }

         if(this.r && !this.Q()) {
            this.u = com.corrodinggames.rts.gameFramework.f.a(this.u, 0.0F, 0.018F * var1);
         } else {
            this.u = com.corrodinggames.rts.gameFramework.f.a(this.u, 1.0F, 0.018F * var1);
         }

         if(com.corrodinggames.rts.gameFramework.f.c(this.eq - var3) > 3.0F) {
            float var4 = 0.6F;
            if(this.Q()) {
               var4 /= 6.0F;
            }

            this.t = com.corrodinggames.rts.gameFramework.f.b(this.t, var4);
            this.t = com.corrodinggames.rts.gameFramework.f.a(this.t, var4, 0.006F * var1);
         } else {
            this.t = com.corrodinggames.rts.gameFramework.f.a(this.t, 0.07F, 0.006F * var1);
         }

         this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, var3, this.t * var1);
         boolean var10 = false;
         if(this.s && this.Q()) {
            if(!this.cJ()) {
               this.r = true;
            } else {
               this.s = false;
               this.M();
               var10 = true;
            }
         }

         if(!this.s && !this.Q()) {
            this.s = true;
            this.M();
            var10 = true;
         }

         if(var10) {
            var2.bR.a(this.eo, this.ep, 0.0F, 0, 0.0F, 0.0F);

            for(int var5 = -180; var5 < 180; var5 += 45) {
               float var6 = this.cg + (float)var5;
               float var7 = (float)((double)this.eo + Math.cos(Math.toRadians((double)var6)) * -5.0D);
               float var8 = (float)((double)this.ep + Math.sin(Math.toRadians((double)var6)) * -5.0D);
               com.corrodinggames.rts.gameFramework.d.e var9 = var2.bR.b(var7, var8, 0.0F, var6);
               if(var9 != null) {
                  var9.ar = 2;
                  var9.s = true;
                  var9.t = 7.0F;
               }
            }
         }

      }
   }

   public strictfp float q(int var1) {
      return var1 == this.ds()?0.0F:45.0F;
   }

   public strictfp void a(am var1, int var2) {
      if(var2 != this.ds()) {
         PointF var3 = this.E(var2);
         com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
         var4.ar = Color.a(255, 247, 212, 129);
         var4.U = this.q(var2);
         var4.l = var1;
         var4.h = 10.0F;
         var4.t = 4.0F;
         var4.x = 2.0F;
         var4.aQ = false;
         var4.A = true;
         var4.M = true;
         var4.ai = 0.5F;
         var4.ak = 1.0F;
         var4.al = 0.1F;
         l var5 = l.B();
         var5.bR.a(var3.a, var3.b, this.eq, -1118482);
         var5.bM.a(com.corrodinggames.rts.gameFramework.a.e.x, 0.2F, this.eo, this.ep);
      }
   }

   public strictfp float m() {
      return this.b()?100.0F:170.0F;
   }

   public strictfp float b(int var1) {
      return 110.0F;
   }

   public strictfp float e(int var1) {
      return (float)(25 + var1 * 10);
   }

   public strictfp float f(int var1) {
      return 0.2F;
   }

   public strictfp float z() {
      return !this.Q()?1.4F:0.4F;
   }

   public strictfp float A() {
      return !this.Q()?3.8F:1.5F;
   }

   public strictfp float B() {
      return 0.3F;
   }

   public strictfp float c(int var1) {
      return 4.0F;
   }

   public strictfp float w(int var1) {
      return 0.35F;
   }

   public strictfp float y(int var1) {
      return 0.38F;
   }

   public strictfp boolean E() {
      return false;
   }

   public strictfp float C() {
      return 0.03F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean bi() {
      return true;
   }

   public strictfp boolean bj() {
      return true;
   }

   public strictfp void i(float var1) {
      if(!this.Z()) {
         super.i(var1);
      } else {
         this.cg += var1;
         if(this.cg > 180.0F) {
            this.cg -= 360.0F;
         }

         if(this.cg < -180.0F) {
            this.cg += 360.0F;
         }

      }
   }

   public strictfp int ds() {
      return 2;
   }

   public strictfp float d(boolean var1) {
      return this.cL[this.ds()].a + 90.0F;
   }

   public strictfp boolean ah() {
      return !this.Q();
   }

   public strictfp boolean ae() {
      return this.Q();
   }

   public strictfp boolean af() {
      return !this.Q();
   }

   public strictfp boolean ag() {
      return !this.Q()?true:true;
   }

   public strictfp void a(s var1, boolean var2) {
      if(var1 == y) {
         this.r = true;
      }

      if(var1 == z) {
         this.r = false;
      }

   }

   public strictfp ArrayList N() {
      return A;
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      y.a(this, var2);
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.f();
   }

   static {
      A.add(y);
      A.add(z);
   }
}
