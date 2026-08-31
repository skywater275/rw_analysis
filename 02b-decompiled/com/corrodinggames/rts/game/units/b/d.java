package com.corrodinggames.rts.game.units.b;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.b.b;
import com.corrodinggames.rts.game.units.b.d$1;
import com.corrodinggames.rts.game.units.b.d$2;
import com.corrodinggames.rts.game.units.e.i;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;
import java.util.Iterator;

public class d extends b implements ak {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   float e = 0.0F;
   float f;
   boolean g;
   m o = new m();
   Rect p = new Rect();
   public static final s q = new d$1(109);
   public static final s r = new d$2(110);
   static ArrayList s = new ArrayList();


   public strictfp void a(as var1) {
      var1.a(this.e);
      var1.a(this.f);
      var1.a(this.g);
      var1.a(this.o.size());
      Iterator var2 = this.o.iterator();

      while(var2.hasNext()) {
         am var3 = (am)var2.next();
         var1.a(var3);
      }

      super.a(var1);
   }

   public strictfp void a(k var1) {
      this.e = var1.g();
      this.f = var1.g();
      this.g = var1.e();
      this.o.clear();
      int var2 = var1.f();

      for(int var3 = 0; var3 < var2; ++var3) {
         am var4 = var1.o();
         if(var4 != null) {
            this.o.add(var4);
         }
      }

      super.a(var1);
   }

   public strictfp int bY() {
      return i.a(this.o);
   }

   public strictfp int bZ() {
      return 4;
   }

   public strictfp ar b() {
      return ar.z;
   }

   public static strictfp void L() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.dropship);
      c = var0.bO.a(R$drawable.dropship_shadow);
      a = var0.bO.a(R$drawable.dropship_dead);
      d = n.a(b);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:d[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return null;
   }

   public strictfp boolean e() {
      l var1 = l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = a;
      this.S(0);
      this.bT = false;
      this.f(true);
      return true;
   }

   public strictfp d(boolean var1) {
      super(var1);
      this.T(45);
      this.U(47);
      this.cj = 20.0F;
      this.ck = this.cj + 0.0F;
      this.cv = 500.0F;
      this.cu = this.cv;
      this.M = b;
      this.N = c;
      this.eq = 0.0F;
   }

   public strictfp boolean I() {
      return true;
   }

   public strictfp boolean i() {
      return this.eq >= 4.0F;
   }

   public strictfp boolean ct() {
      return true;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         boolean var2 = this.cK();
         boolean var3;
         if(this.g && !var2 && !this.cK && this.eq < 4.0F) {
            this.f = com.corrodinggames.rts.gameFramework.f.a(this.f, var1);
            if(this.f == 0.0F) {
               this.f = 30.0F;
               if(this.o.size() == 0) {
                  this.g = false;
               } else {
                  var3 = this.o.size() % 2 == 0;
                  float var4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -9.0F;
                  float var5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -9.0F;
                  var4 += com.corrodinggames.rts.gameFramework.f.k(this.cg + 90.0F) * (float)(var3?-7:7);
                  var4 += com.corrodinggames.rts.gameFramework.f.j(this.cg + 90.0F) * (float)(var3?-7:7);
                  am var6 = (am)this.o.remove(this.o.size() - 1);
                  if(!y.a(var6, var4, var5)) {
                     var4 += 10.0F;
                  }

                  if(!y.a(var6, var4, var5)) {
                     var4 -= 20.0F;
                  }

                  if(!y.a(var6, var4, var5)) {
                     var4 -= 10.0F;
                     var5 += 10.0F;
                  }

                  if(!y.a(var6, var4, var5)) {
                     var5 -= 20.0F;
                  }

                  if(!y.a(var6, var4, var5)) {
                     this.o.add(var6);
                  } else {
                     var6.cN = null;
                     var6.eo = var4;
                     var6.ep = var5;
                     var6.bZ += 0.1F;
                     var6.cg = this.cg + 180.0F;
                     var6.bR = this;
                     var6.bS = 45.0F;
                     if(var6 instanceof com.corrodinggames.rts.game.units.y) {
                        com.corrodinggames.rts.game.units.y var7 = (com.corrodinggames.rts.game.units.y)var6;
                        var7.az();
                        var7.d(this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -66.0F, this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -66.0F);
                     }

                     if(this.o.size() == 0) {
                        this.g = false;
                     }
                  }
               }
            }
         }

         this.e += 2.0F * var1;
         if(this.e > 360.0F) {
            this.e -= 360.0F;
         }

         var3 = this.i();
         if(this.bT()) {
            if(this.aq() && !var2) {
               this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 2.0F, 0.4F * var1);
            } else {
               this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 35.0F + com.corrodinggames.rts.gameFramework.f.j(this.e) * 1.5F, 0.35F * var1);
            }
         }

         if(var3 != this.i()) {
            this.ay = true;
            if(this.i()) {
               this.S(5);
            } else {
               this.S(2);
            }
         }

      }
   }

   public strictfp PointF E(int var1) {
      float var2 = this.g(var1);
      float var3 = this.cg;
      float var4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(var3) * var2;
      float var5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(var3) * var2;
      bg.a(var4, var5);
      return bg;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3 = this.E(var2);
      com.corrodinggames.rts.game.f var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
      var4.ar = Color.a(255, 150, 230, 40);
      var4.U = 35.0F;
      var4.l = var1;
      var4.h = 80.0F;
      var4.t = 4.0F;
      var4.x = 2.0F;
      l var5 = l.B();
      var5.bR.a(var3.a, var3.b, this.eq, -1127220);
      var5.bR.a(var3.a, var3.b, this.eq, this.cL[var2].a);
      var5.bM.a(com.corrodinggames.rts.gameFramework.a.e.u, 0.3F, this.eo, this.ep);
   }

   public strictfp float m() {
      return 140.0F;
   }

   public strictfp float b(int var1) {
      return 40.0F;
   }

   public strictfp float z() {
      return 2.3F;
   }

   public strictfp float A() {
      return 1.4F;
   }

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp boolean E() {
      return false;
   }

   public strictfp float C() {
      return 0.03F;
   }

   public strictfp float D() {
      return 0.05F;
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp float g(int var1) {
      return 15.0F;
   }

   public strictfp void a() {
      this.f(true);
      super.a();
   }

   public strictfp void f(boolean var1) {
      Iterator var2 = this.o.iterator();

      while(var2.hasNext()) {
         am var3 = (am)var2.next();
         var3.cN = null;
         var3.eo = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -9.0F;
         var3.ep = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -9.0F;
         if(var1) {
            var3.cj();
         }
      }

      this.o.clear();
   }

   public strictfp boolean bA() {
      return this.g;
   }

   public strictfp void M() {
      this.g = true;
      this.f = 30.0F;
   }

   public strictfp void ds() {
      this.g = false;
   }

   public strictfp float bN() {
      return 16000.0F;
   }

   public strictfp boolean d(am var1, boolean var2) {
      return this.g?false:(!i.a(this.o, 4, var1)?false:(var1 == this?false:(this.bX != var1.bX && !var2?false:y.a(var1, true, true))));
   }

   public strictfp boolean e(am var1, boolean var2) {
      if(!this.d(var1, var2)) {
         return false;
      } else {
         this.C(var1);
         return true;
      }
   }

   public strictfp void C(am var1) {
      var1.cN = this;
      this.o.add(var1);
      l var2 = l.B();
      var2.bS.l(var1);
   }

   public strictfp void e(am var1) {
      if(var1.cN == this) {
         this.o.remove(var1);
         var1.cN = null;
      } else {
         l.g("Unit is not being transported");
      }

   }

   public strictfp void a(s var1, boolean var2) {
      if(var1 == q) {
         this.M();
      }

      if(var1 == r) {
         this.ds();
      }

   }

   public strictfp int bB() {
      return this.o.size();
   }

   public strictfp boolean cr() {
      return true;
   }

   public strictfp com.corrodinggames.rts.game.units.a.c cp() {
      return q.N();
   }

   public strictfp ArrayList N() {
      return s;
   }

   public strictfp boolean f() {
      return !this.cK();
   }

   public strictfp boolean j() {
      return true;
   }

   public strictfp m bz() {
      return this.o;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.b();
   }

   static {
      s.add(q);
      s.add(r);
   }
}
