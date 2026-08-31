package com.corrodinggames.rts.game.units.d;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.k;
import com.corrodinggames.rts.game.units.d.l;
import com.corrodinggames.rts.gameFramework.j.as;

public abstract class i extends d implements l {

   public static final Paint y = new Paint();
   k z = this.du();
   Rect A = new Rect();
   Rect B = new Rect();


   public strictfp i(boolean var1) {
      super(var1);
   }

   public strictfp void a(as var1) {
      var1.a(this.r);
      this.z.a(var1);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      if(var1.b() >= 69) {
         int var2 = var1.f();
         this.R(var2);
      }

      this.z.a(var1);
      super.a(var1);
   }

   public strictfp k du() {
      return new k(this);
   }

   public strictfp void b(j var1) {}

   public strictfp boolean c(j var1) {
      return true;
   }

   public strictfp void a(j var1) {
      float var2;
      if(this.z.b != null) {
         var2 = this.cj * 2.0F;
      } else {
         var2 = this.cj * 3.0F;
      }

      am var3 = this.z.a(var1, var2, false, 0.0F);
      if(var3 != null) {
         if(var3.ep - var3.cj < this.ep + (float)this.dv()) {
            var3.ep = this.ep + (float)this.dv() + var3.cj;
         }

         com.corrodinggames.rts.game.n.c(var3);
      }

   }

   public strictfp int dv() {
      return -100;
   }

   public strictfp int f(boolean var1) {
      return this.z.a(com.corrodinggames.rts.game.units.a.s.i, var1, true);
   }

   public final strictfp int a(com.corrodinggames.rts.game.units.a.c var1, boolean var2) {
      return this.z.a(var1, var2);
   }

   public strictfp j dw() {
      return this.z.b();
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b bD() {
      return this.z.c();
   }

   public strictfp com.corrodinggames.rts.gameFramework.utility.m dx() {
      return this.z.c;
   }

   public strictfp int h(com.corrodinggames.rts.game.units.as var1) {
      return this.z.a(var1);
   }

   public strictfp boolean dy() {
      return this.z.a();
   }

   public strictfp void dz() {
      this.z.e = 1.0F;
   }

   public strictfp void a(PointF var1) {
      this.z.b = var1;
   }

   public strictfp boolean dA() {
      return false;
   }

   public strictfp float bV() {
      return this.bT() && !this.z.a()?this.z.e:super.bV();
   }

   public strictfp com.corrodinggames.rts.game.units.a.s e(com.corrodinggames.rts.game.units.as var1) {
      return this.z.b(var1);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1, boolean var2) {
      this.z.a(var1, var2, (PointF)null, (am)null);
   }

   public strictfp void b(com.corrodinggames.rts.game.units.a.s var1, boolean var2) {
      this.z.a(var1, var2);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1) {
      this.z.a(var1);
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         this.z.a(var1);
      }
   }

   public strictfp boolean c(float var1) {
      return super.c(var1);
   }

   public strictfp void bv() {
      com.corrodinggames.rts.game.n.a((am)this);
      this.z.a(true);
      super.bv();
   }

   public strictfp void a() {
      com.corrodinggames.rts.game.n.a((am)this);
      this.z.a(true);
      super.a();
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp void a(am var1, int var2) {
      throw new RuntimeException("Unit cannot shoot");
   }

   public strictfp float m() {
      return 0.0F;
   }

   public strictfp float b(int var1) {
      return 0.0F;
   }

   public strictfp float c(int var1) {
      return 0.0F;
   }

   public strictfp void ca() {
      if(this.z.b != null) {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         float var2 = (float)((int)(this.eo - var1.cw));
         float var3 = (float)((int)(this.ep - var1.cx));
         float var4 = (float)((int)(this.z.b.a - var1.cw));
         float var5 = (float)((int)(this.z.b.b - var1.cx));
         var1.bO.a(var2, var3, var4, var5, y);
      }

   }

   public strictfp int a(com.corrodinggames.rts.game.units.custom.g var1) {
      return this.z.a(var1);
   }

   static {
      y.a(255, 0, 255, 0);
      y.a(1.5F);
      y.a(true);
   }
}
