package com.corrodinggames.rts.game.units.d;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.game.units.d.s;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public class r extends d implements com.corrodinggames.rts.game.units.d {

   static com.corrodinggames.rts.gameFramework.m.e a = null;
   static com.corrodinggames.rts.gameFramework.m.e[] b = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   float d;
   public static s e = new s(true);
   Rect f = new Rect();
   Rect g = new Rect();
   static ArrayList h = new ArrayList();
   PointF[] i = new PointF[6];
   PointF[] j;


   public strictfp void a(as var1) {
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      super.a(var1);
   }

   public strictfp ar K() {
      return ar.B;
   }

   public static strictfp void M() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a = var0.bO.a(R$drawable.repair_bay);
      c = var0.bO.a(R$drawable.repair_bay_dead);
      b = com.corrodinggames.rts.game.n.a(a);
   }

   public strictfp boolean L() {
      this.M = c;
      this.S(0);
      this.bT = false;
      this.a(ab.c);
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?c:(this.bX == null?b[b.length - 1]:b[this.bX.R()]);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp void a(int var1) {}

   public strictfp r(boolean var1) {
      super(var1);
      this.j = new PointF[this.i.length];
      this.M = a;
      this.b(a);
      this.cj = 30.0F;
      this.ck = this.cj;
      this.cv = 1000.0F;
      this.cu = this.cv;
      this.n.a(-1, -1, 1, 1);
      this.o.a(-1, -1, 1, 1);

      for(int var2 = 0; var2 < this.i.length; ++var2) {
         this.i[var2] = new PointF();
         this.j[var2] = new PointF();
      }

   }

   public strictfp int y() {
      return 230;
   }

   public strictfp float c(am var1) {
      return 0.2F;
   }

   public strictfp boolean a(am var1) {
      return !var1.q();
   }

   public static strictfp au a(y var0, float var1, float var2, boolean var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      e.a((float)var0.y() + var2, var3);
      var4.cc.a(var0.eo, var0.ep, (float)var0.y() + var2, var0, var1, e);
      am var5 = e.e;
      if(var5 != null) {
         au var6 = var0.ao();
         var6.b(var5);
         if(var6 != null) {
            var6.k = var2;
            var6.m = true;
            return var6;
         }
      }

      return null;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         this.d += var1;
         if(this.aq() && this.d > 40.0F) {
            this.d = 0.0F;
            a(this, var1, 0.0F, false);
         }

         if(!this.bV) {
            com.corrodinggames.rts.game.units.e.b.a(var1, this);
         }

      }
   }

   public strictfp boolean c(float var1) {
      return super.c(var1);
   }

   public strictfp void a(float var1, boolean var2) {
      super.a(var1, var2);
      if(!this.bV) {
         com.corrodinggames.rts.game.units.e.b.b(var1, this);
      }

   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp void a(am var1, int var2) {
      throw new RuntimeException("Unit cannot shoot");
   }

   public strictfp float b(int var1) {
      return 0.0F;
   }

   public strictfp float c(int var1) {
      return 0.0F;
   }

   public strictfp PointF E(int var1) {
      PointF var2 = this.G(var1);
      float var3 = var2.a + 0.0F;
      float var4 = var2.b - 33.0F;
      bg.a(var3, var4);
      return bg;
   }

   public strictfp ArrayList N() {
      return h;
   }

   public strictfp PointF[] b() {
      return this.i;
   }

   public strictfp PointF[] e_() {
      return this.j;
   }

   public strictfp float m() {
      return (float)this.y();
   }

   public strictfp void e(float var1) {
      super.e(var1);
      float var2 = (float)this.y();
      com.corrodinggames.rts.gameFramework.utility.y.a(this, var2);
   }

   public strictfp boolean g(am var1, boolean var2) {
      return true;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

   static {
      h.add(new com.corrodinggames.rts.game.units.a.m(true));
      h.add(new com.corrodinggames.rts.game.units.a.n());
   }
}
