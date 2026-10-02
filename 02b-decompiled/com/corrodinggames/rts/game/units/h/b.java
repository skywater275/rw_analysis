package com.corrodinggames.rts.game.units.h;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.a.m;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.v;
import com.corrodinggames.rts.game.units.h.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public class b extends f implements com.corrodinggames.rts.game.units.d {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   PointF[] f = new PointF[6];
   PointF[] g;
   Rect h;
   static s i = new m(false);


   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:com.corrodinggames.rts.game.units.e.b.h[this.bX.R()];
   }

   public strictfp ar f() {
      return ar.L;
   }

   public strictfp PointF[] b() {
      return this.f;
   }

   public strictfp PointF[] e_() {
      return this.g;
   }

   public strictfp float bN() {
      return 6000.0F;
   }

   public static strictfp void t_() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.builder_ship);
      a = var0.bO.a(R$drawable.builder_ship_dead);
      c = var0.bO.a(R$drawable.builder_ship_turret);
      e = n.a(b);
      d = a(b, b.m(), b.l());
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?a:e[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return d;
   }

   public strictfp boolean F() {
      return l.B().bQ.renderExtraShadows && this.eq > -2.0F;
   }

   public strictfp float G() {
      return 3.0F;
   }

   public strictfp float H() {
      return 3.0F;
   }

   public strictfp boolean e() {
      l var1 = l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = a;
      this.S(0);
      this.bT = false;
      return true;
   }

   public strictfp b(boolean var1) {
      super(var1);
      this.g = new PointF[this.f.length];
      this.h = new Rect();
      this.b(b);
      this.cj = 13.0F;
      this.ck = this.cj;
      this.cv = 500.0F;
      this.cu = this.cv;
      this.M = b;

      for(int var2 = 0; var2 < this.f.length; ++var2) {
         this.f[var2] = new PointF();
         this.g[var2] = new PointF();
      }

   }

   public strictfp float m() {
      return 240.0F;
   }

   public strictfp float z() {
      return 0.8F;
   }

   public strictfp float A() {
      return 1.9F;
   }

   public strictfp float B() {
      return 0.12F;
   }

   public strictfp float c(int var1) {
      return 3.5F;
   }

   public strictfp float w(int var1) {
      return 0.25F;
   }

   public strictfp float C() {
      return 0.03F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         com.corrodinggames.rts.game.units.e.b.a(var1, this);
      }

   }

   public strictfp void a(float var1, boolean var2) {
      super.a(var1, var2);
      if(!this.bV) {
         com.corrodinggames.rts.game.units.e.b.b(var1, this);
      }

   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         l var2 = l.B();
         y.a((com.corrodinggames.rts.game.units.y)this);
         if(!this.bV) {
            float var3 = this.cL[0].f / this.e(0);
            if(var3 != 0.0F) {
               PointF var4 = this.E(0);
               var2.bO.i();
               var2.bO.b(var4.a - var2.cw, var4.b - var2.cx - this.eq);
               var2.bO.a(var3, var3);
               if(this.Y()) {
                  var2.bO.a(com.corrodinggames.rts.game.units.e.b.f, 0.0F, 0.0F, (Paint)null);
               } else {
                  var2.bO.a(com.corrodinggames.rts.game.units.e.b.e, 0.0F, 0.0F, (Paint)null);
               }

               var2.bO.j();
            }
         }

         return true;
      }
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp float g(int var1) {
      return 11.0F;
   }

   public strictfp int bl() {
      return 1;
   }

   public strictfp PointF G(int var1) {
      float var2 = 8.0F;
      float var3 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * var2;
      float var4 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * var2;
      bh.a(var3, var4);
      return bh;
   }

   public strictfp float b(int var1) {
      return (float)(120 - var1 * 28);
   }

   public strictfp float e(int var1) {
      return 30.0F;
   }

   public strictfp float f(int var1) {
      return 1.3F;
   }

   public strictfp boolean a(am var1) {
      return var1.q()?false:var1.bI();
   }

   public strictfp void a(s var1, boolean var2) {}

   public static strictfp void a(ArrayList var0, int var1) {
      var0.add(i);
      var0.add(new v(ar.a, 1, Integer.valueOf(1)));
      var0.add(new v(ar.f, 1, Integer.valueOf(2)));
      var0.add(new v(ar.g, 1, Integer.valueOf(3)));
      var0.add(new v(ar.b, 1, Integer.valueOf(4)));
      var0.add(new v(ar.c, 1, Integer.valueOf(5)));
      var0.add(new v(ar.d, 1, Integer.valueOf(6)));
      var0.add(new v(ar.J, 1, Integer.valueOf(7)));
      var0.add(new v(ar.y, 1, Integer.valueOf(8)));
      var0.add(new v(ar.B, 1, Integer.valueOf(9)));
   }

   public strictfp ArrayList N() {
      return this.f().a(this.V());
   }

   public strictfp void a(am var1, int var2) {}

   public strictfp int y() {
      return 145;
   }

   public strictfp boolean g(am var1, boolean var2) {
      return true;
   }

   public strictfp float f(as var1) {
      int var2 = this.y();
      int var3 = var1.a(this);
      if(var3 == 0 && var1.p()) {
         var3 = 110;
      }

      var2 += var3;
      return (float)var2;
   }

   public strictfp int u(am var1) {
      return (int)this.f(var1.r());
   }

   public strictfp int v(am var1) {
      return (int)this.f(var1.r());
   }

   // $FF: synthetic method
   public as r() {
      return this.f();
   }

}
