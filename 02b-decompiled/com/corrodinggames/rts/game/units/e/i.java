package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.e.h;
import com.corrodinggames.rts.game.units.e.i$1;
import com.corrodinggames.rts.game.units.e.i$2;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;
import java.util.Iterator;

public class i extends h implements ak {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
   float e = 0.0F;
   float f;
   boolean g;
   com.corrodinggames.rts.gameFramework.utility.m h = new com.corrodinggames.rts.gameFramework.utility.m();
   public static final s i = new i$1(109);
   public static final s j = new i$2(110);
   static ArrayList k = new ArrayList();


   public strictfp void a(as var1) {
      var1.a(this.e);
      var1.a(this.f);
      var1.a(this.g);
      var1.a(this.h.size());
      Iterator var2 = this.h.iterator();

      while(var2.hasNext()) {
         am var3 = (am)var2.next();
         var1.a(var3);
      }

      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.e = var1.g();
      this.f = var1.g();
      this.g = var1.e();
      this.h.clear();
      int var2 = var1.f();

      for(int var3 = 0; var3 < var2; ++var3) {
         am var4 = var1.o();
         if(var4 != null) {
            this.h.add(var4);
         }
      }

      super.a(var1);
   }

   public strictfp ar b() {
      return ar.s;
   }

   public static strictfp void L() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.hovercraft);
      c = var0.bO.a(R$drawable.hovercraft_shadow);
      b = var0.bO.a(R$drawable.hovercraft_dead);
      d = com.corrodinggames.rts.game.n.a(a);
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
      this.M = b;
      this.S(0);
      this.bT = false;
      this.f(true);
      this.a(ab.b);
      return true;
   }

   public strictfp void a() {
      this.f(true);
      super.a();
   }

   public strictfp void f(boolean var1) {
      Iterator var2 = this.h.iterator();

      while(var2.hasNext()) {
         am var3 = (am)var2.next();
         var3.cN = null;
         var3.eo = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -9.0F;
         var3.ep = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -9.0F;
         if(var1) {
            var3.cj();
         }
      }

      this.h.clear();
   }

   public strictfp i(boolean var1) {
      super(var1);
      this.T(20);
      this.U(32);
      this.cj = 15.0F;
      this.ck = this.cj;
      this.cv = 450.0F;
      this.cu = this.cv;
      this.M = a;
      this.N = c;
   }

   public static strictfp int a(com.corrodinggames.rts.gameFramework.utility.m var0) {
      int var1 = 0;

      am var3;
      for(Iterator var2 = var0.iterator(); var2.hasNext(); var1 += var3.cw()) {
         var3 = (am)var2.next();
      }

      return var1;
   }

   public static strictfp boolean a(com.corrodinggames.rts.gameFramework.utility.m var0, int var1, am var2) {
      int var3 = a(var0);
      return var3 + var2.cw() <= var1;
   }

   public strictfp int bY() {
      return a(this.h);
   }

   public strictfp int bZ() {
      return 4;
   }

   public static strictfp boolean a(am var0, am var1, boolean var2) {
      float var3 = 9.0F;
      float var4 = -180.0F;
      float var5 = 70.0F;
      float var6 = 0.0F;
      float var7 = 7.0F;
      return a(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   public static strictfp boolean a(am var0, am var1, boolean var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = var0.eo + com.corrodinggames.rts.gameFramework.f.k(var0.cg + var4) * var7 - com.corrodinggames.rts.gameFramework.f.j(var0.cg + var4) * var6;
      float var9 = var0.ep + com.corrodinggames.rts.gameFramework.f.j(var0.cg + var4) * var7 + com.corrodinggames.rts.gameFramework.f.k(var0.cg + var4) * var6;
      var8 += com.corrodinggames.rts.gameFramework.f.k(var0.cg + 90.0F) * (var2?-var3:var3);
      var9 += com.corrodinggames.rts.gameFramework.f.j(var0.cg + 90.0F) * (var2?-var3:var3);
      if(!y.a(var1, var8, var9)) {
         var8 += 10.0F;
      }

      if(!y.a(var1, var8, var9)) {
         var8 -= 20.0F;
      }

      if(!y.a(var1, var8, var9)) {
         var8 -= 10.0F;
         var9 += 10.0F;
      }

      if(!y.a(var1, var8, var9)) {
         var9 -= 20.0F;
      }

      if(!y.a(var1, var8, var9)) {
         return false;
      } else {
         var1.cN = null;
         var1.eo = var8;
         var1.ep = var9;
         var1.bZ += 0.1F;
         var1.cg = var0.cg + var4;
         var1.bR = var0;
         var1.bS = 45.0F;
         if(var1 instanceof com.corrodinggames.rts.game.units.y) {
            com.corrodinggames.rts.game.units.y var10 = (com.corrodinggames.rts.game.units.y)var1;
            var10.j(var1.cg);
            var10.az();
            var10.d(var1.eo + com.corrodinggames.rts.gameFramework.f.k(var1.cg + (var2?-var3:var3)) * var5, var1.ep + com.corrodinggames.rts.gameFramework.f.j(var1.cg + (var2?-var3:var3)) * var5);
            var10.ac = 0;
         }

         return true;
      }
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV && this.bT()) {
         if(this.cl == 0.0F && this.em != 3) {
            this.S(3);
         }

         if(this.g && !this.cK() && !this.cK) {
            this.f = com.corrodinggames.rts.gameFramework.f.a(this.f, var1);
            if(this.f == 0.0F) {
               this.f = 30.0F;
               if(this.h.size() == 0) {
                  this.g = false;
               } else {
                  boolean var2 = this.h.size() % 2 == 0;
                  am var3 = (am)this.h.remove(this.h.size() - 1);
                  boolean var4 = a(this, var3, var2);
                  if(!var4) {
                     this.h.add(var3);
                  }

                  if(this.h.size() == 0) {
                     this.g = false;
                  }
               }
            }
         }

         this.e += 4.0F * var1;
         if(this.e > 360.0F) {
            this.e -= 360.0F;
         }

         if(!this.g) {
            this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 3.0F + com.corrodinggames.rts.gameFramework.f.j(this.e) * 1.5F, 0.1F * var1);
         } else {
            this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 0.0F, 0.1F * var1);
         }

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
      return this.cK()?1.2F:0.9F;
   }

   public strictfp float A() {
      return this.cK()?1.8F:1.4F;
   }

   public strictfp float B() {
      return 0.1F;
   }

   public strictfp float C() {
      return 0.03F;
   }

   public strictfp float D() {
      return 0.05F;
   }

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp boolean d(am var1, boolean var2) {
      return this.g?false:(!a(this.h, 4, var1)?false:(var1 == this?false:(this.bX != var1.bX && !var2?false:y.a(var1, true, true))));
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
      this.h.add(var1);
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var2.bS.l(var1);
   }

   public strictfp void e(am var1) {
      if(var1.cN == this) {
         this.h.remove(var1);
         var1.cN = null;
      } else {
         com.corrodinggames.rts.gameFramework.l.g("Unit is not being transported");
      }

   }

   public strictfp float bN() {
      return 12000.0F;
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

   public strictfp void a(s var1, boolean var2) {
      if(var1 == i) {
         this.M();
      }

      if(var1 == j) {
         this.ds();
      }

   }

   public strictfp boolean cr() {
      return true;
   }

   public strictfp int bB() {
      return this.h.size();
   }

   public strictfp com.corrodinggames.rts.game.units.a.c cp() {
      return i.N();
   }

   public strictfp ArrayList N() {
      return k;
   }

   public strictfp boolean f() {
      return !this.cK();
   }

   public strictfp boolean j() {
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.utility.m bz() {
      return this.h;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.b();
   }

   static {
      k.add(i);
      k.add(j);
   }
}
