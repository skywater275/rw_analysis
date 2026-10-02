package com.corrodinggames.rts.game.units.d.a;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.a.a;
import com.corrodinggames.rts.game.units.d.a.b$1;
import com.corrodinggames.rts.game.units.d.a.b$2;
import com.corrodinggames.rts.game.units.d.a.b$3;
import com.corrodinggames.rts.game.units.d.a.b$4;
import com.corrodinggames.rts.game.units.d.a.c;
import com.corrodinggames.rts.game.units.d.a.d;
import com.corrodinggames.rts.game.units.d.a.e;
import com.corrodinggames.rts.game.units.d.a.f;
import com.corrodinggames.rts.game.units.d.a.g;
import com.corrodinggames.rts.game.units.d.a.h;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public class b extends i {

   static com.corrodinggames.rts.gameFramework.m.e g = null;
   private static com.corrodinggames.rts.gameFramework.m.e a = null;
   private static com.corrodinggames.rts.gameFramework.m.e b = null;
   private static com.corrodinggames.rts.gameFramework.m.e c = null;
   private static com.corrodinggames.rts.gameFramework.m.e d = null;
   private static com.corrodinggames.rts.gameFramework.m.e e = null;
   static com.corrodinggames.rts.gameFramework.m.e[] h = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e i = null;
   boolean j;
   int k;
   c l = new h(this);
   static String t = "gun";
   static String u = "gunT2";
   static String v = "gunT3";
   static String w = "artillery";
   static String x = "flamethrower";
   static String C = "aa_t1";
   static String D = "aa_t2";
   static String E = "aa_flak";
   static com.corrodinggames.rts.gameFramework.m.e F = null;
   static com.corrodinggames.rts.gameFramework.m.e[] G = new com.corrodinggames.rts.gameFramework.m.e[10];
   boolean H = true;
   float I;
   float J;
   boolean K;
   Rect dK = new Rect();
   public static s dL = new b$1(101);
   public static s dM = new b$2(104);
   public static s dN = new b$3(102);
   public static s dO = new b$4(103);
   static ArrayList dP = new ArrayList();


   public strictfp int M() {
      return this.l.b();
   }

   public strictfp float H(int var1) {
      return this.l.h(var1);
   }

   public strictfp void a_(String var1) {
      this.b(var1);
   }

   public strictfp void b(String var1) {
      if(!this.l.a(var1)) {
         c var2 = this.l;
         this.l = this.c(var1);
         this.l.a(var2);
      }

   }

   public strictfp c c(String var1) {
      return (c)(var1.equals(t)?new h(this):(var1.equals(u)?new f(this):(var1.equals(v)?new g(this):(var1.equals(w)?new d(this):(var1.equals(x)?new e(this):null)))));
   }

   public strictfp void a(as var1) {
      var1.a(this.j);
      var1.a(this.k == 1);
      var1.c(this.l.c());
      var1.a(this.k);
      super.a(var1);
   }

   public strictfp void a(k var1) {
      boolean var2 = var1.e();
      if(var2) {
         this.a(2);
      }

      if(var1.b() >= 27) {
         this.k = var1.e()?1:0;
      }

      if(var1.b() >= 35) {
         String var3 = var1.l();
         if(!this.l.a(var3)) {
            this.b(var3);
         }

         this.k = var1.f();
      } else if(var2 && !(this instanceof a)) {
         this.b(u);
      }

      super.a(var1);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:G[this.bX.R()];
   }

   public static strictfp void dB() {
      l var0 = l.B();
      g = var0.bO.a(R$drawable.turret_base);
      i = var0.bO.a(R$drawable.turret_base_dead);
      a = var0.bO.a(R$drawable.turret_top);
      b = var0.bO.a(R$drawable.turret_top_l2);
      c = var0.bO.a(R$drawable.turret_top_l3);
      d = var0.bO.a(R$drawable.turret_top_artillery);
      e = var0.bO.a(R$drawable.turret_top_flame);
      h = n.a(g);
      F = var0.bO.a(R$drawable.unit_icon_building_turrent);
      G = n.a(F);
   }

   public strictfp boolean L() {
      this.M = i;
      this.S(0);
      this.bT = false;
      this.a(ab.d);
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?i:(this.bX == null?h[h.length - 1]:h[this.bX.R()]);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return this.l.d(var1);
   }

   public strictfp b(boolean var1) {
      super(var1);
      this.T(35);
      this.U(42);
      this.cj = 16.0F;
      this.ck = this.cj;
      this.cv = 700.0F;
      this.cu = this.cv;
      this.M = g;
      this.cL[0].a = (float)com.corrodinggames.rts.gameFramework.f.a(this, -180, 180);
      this.n.a(0, 0, 1, 1);
      this.o.a(0, 0, 1, 1);
   }

   public strictfp void s(float var1) {
      byte var2 = 0;
      if(this.cL[var2].a()) {
         if(this.H) {
            this.I = this.cL[var2].a;
            this.H = false;
            this.J = (float)com.corrodinggames.rts.gameFramework.f.a(this, 0, 120);
         }

         this.J += var1;
         if(this.J > 450.0F) {
            this.J = (float)com.corrodinggames.rts.gameFramework.f.a(this, 0, 30);
            this.K = !this.K;
         }

         if(this.J < 120.0F) {
            if(this.K) {
               this.a(var1 * 0.3F, this.I - 20.0F, var2);
            } else {
               this.a(var1 * 0.3F, this.I + 20.0F, var2);
            }
         }
      } else {
         this.H = true;
      }

   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bT()) {
         this.l.a(var1);
      }

   }

   public strictfp void a(am var1, int var2) {
      this.l.a(var1, var2);
   }

   public strictfp float m() {
      return this.l.a();
   }

   public strictfp float b(int var1) {
      return this.l.a(var1);
   }

   public strictfp float c(int var1) {
      return this.l.e(var1);
   }

   public strictfp float w(int var1) {
      return this.l.f(var1);
   }

   public strictfp boolean b(int var1, float var2) {
      return false;
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         if(!this.bV) {
            this.dC();
         }

         return true;
      }
   }

   strictfp void dC() {
      l var1 = l.B();
      com.corrodinggames.rts.gameFramework.m.e var2 = null;
      byte var3 = 0;
      var2 = this.d(var3);
      PointF var4 = this.G(var3);
      var1.bO.a(var2, var4.a - l.B().cw, var4.b - l.B().cx, this.cL[var3].a, this.f());
   }

   public strictfp ar K() {
      return ar.f;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean af() {
      return false;
   }

   public strictfp float g(int var1) {
      return this.l.g(var1);
   }

   public strictfp void M(int var1) {
      if(this.b(var1) >= 10.0F) {
         super.M(var1);
      }
   }

   public strictfp void a(j var1) {
      s var2 = this.a(var1.j);
      if(var2 != null) {
         var2.f(this);
      } else {
         ad.a("specialAction=null on completeQueueItem(turret) for item.uIndex:" + var1.j + " id:" + this.eh, true);
      }

   }

   public strictfp com.corrodinggames.rts.game.units.a.c cm() {
      return this.M() == 1?dL.N():(this.l instanceof f?dM.N():s.i);
   }

   public strictfp void a(ArrayList var1) {
      var1.clear();
      if(this.M() == 1) {
         var1.add(dN.N());
         var1.add(dO.N());
      }

   }

   public strictfp void a(int var1) {
      if(var1 == 1) {
         this.j = false;
      } else if(var1 == 2 && !this.j) {
         this.j = true;
      }

   }

   public strictfp PointF E(int var1) {
      return this.l.c(var1);
   }

   public strictfp float bV() {
      return this.cL[0].e > 0.0F && this.l.a(w)?1.0F - this.cL[0].e / this.b(0):super.bV();
   }

   public strictfp PointF G(int var1) {
      bh.a(super.G(var1));
      bh.b(0.0F, -5.0F);
      return bh;
   }

   public strictfp ArrayList N() {
      return dP;
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      y.a(this, var2);
   }

   public strictfp float cZ() {
      return (float)l.B().bL.n;
   }

   public strictfp float da() {
      return (float)l.B().bL.o;
   }

   public strictfp float db() {
      return super.db() - 8.0F;
   }

   public strictfp int cL() {
      return this.l.d();
   }

   public strictfp float q(int var1) {
      return this.l.b(var1);
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

   // $FF: synthetic method
   static PointF a(b var0, int var1) {
      return var0.E(var1);
   }

   // $FF: synthetic method
   static com.corrodinggames.rts.gameFramework.m.e dD() {
      return a;
   }

   // $FF: synthetic method
   static com.corrodinggames.rts.gameFramework.m.e dE() {
      return b;
   }

   // $FF: synthetic method
   static PointF b(b var0, int var1) {
      return var0.E(var1);
   }

   // $FF: synthetic method
   static com.corrodinggames.rts.gameFramework.m.e dF() {
      return c;
   }

   // $FF: synthetic method
   static PointF c(b var0, int var1) {
      return var0.E(var1);
   }

   // $FF: synthetic method
   static com.corrodinggames.rts.gameFramework.m.e dG() {
      return d;
   }

   // $FF: synthetic method
   static com.corrodinggames.rts.gameFramework.m.e dH() {
      return e;
   }

   // $FF: synthetic method
   static void a(b var0) {
      var0.W();
   }

   // $FF: synthetic method
   static void b(b var0) {
      var0.W();
   }

   // $FF: synthetic method
   static void c(b var0) {
      var0.W();
   }

   // $FF: synthetic method
   static void d(b var0) {
      var0.W();
   }

   static {
      dP.add(dL);
      dP.add(dM);
      dP.add(dN);
      dP.add(dO);
   }
}
