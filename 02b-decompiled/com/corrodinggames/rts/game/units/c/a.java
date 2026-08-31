package com.corrodinggames.rts.game.units.c;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.d.d;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.m.e;

public class a extends j {

   static e a = null;
   static e b = null;
   static e c = null;
   static e[] d = new e[10];
   int e = 0;
   float f = 0.0F;
   Rect g = new Rect();
   Rect h = new Rect();


   public ar b() {
      return ar.t;
   }

   public static void f() {
      l var0 = l.B();
      b = var0.bO.a(R$drawable.ladybug);
      d = n.a(b);
   }

   public e d() {
      return this.bV?a:d[this.bX.R()];
   }

   public e k() {
      return null;
   }

   public e d(int var1) {
      return null;
   }

   public boolean e() {
      l var1 = l.B();
      com.corrodinggames.rts.gameFramework.d.e var2 = var1.bR.b(this.eo, this.ep, this.eq, d.i, false, h.c);
      if(var2 != null) {
         ;
      }

      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.A, 0.8F, this.eo, this.ep);
      com.corrodinggames.rts.game.l.a(this, 1);
      return false;
   }

   public a(boolean var1) {
      super(var1);
      this.T(17);
      this.U(26);
      this.cj = 5.0F;
      this.ck = this.cj + 3.0F;
      this.cv = 130.0F;
      this.cu = this.cv;
      this.M = b;
      this.P = com.corrodinggames.rts.game.units.a.a;
   }

   public Rect a_(boolean var1) {
      int var2 = this.e * this.es;
      byte var3 = 0;
      this.g.a(var2, var3, var2 + this.es, var3 + this.et);
      return this.g;
   }

   public boolean bP() {
      return true;
   }

   public boolean bO() {
      return true;
   }

   public void a(float var1) {
      super.a(var1);
      if(this.cK) {
         if(this.e == 0) {
            this.e = 1;
         } else {
            this.e = 0;
         }
      }

      if(this.f != 0.0F) {
         this.f = f.a(this.f, var1);
         this.e = 2;
      }

   }

   public void a(am var1, int var2) {
      com.corrodinggames.rts.game.f.a(this, var1, 14.0F, (com.corrodinggames.rts.game.f)null, false);
      this.f = 4.0F;
      PointF var3 = this.E(var2);
      l var4 = l.B();
      var4.bM.a(com.corrodinggames.rts.gameFramework.a.e.B, 0.3F, var3.a, var3.b);
   }

   public float m() {
      return 43.0F;
   }

   public float b(int var1) {
      return 17.0F;
   }

   public float z() {
      return 1.7F;
   }

   public float A() {
      return 5.5F;
   }

   public float c(int var1) {
      return 99.0F;
   }

   public boolean c(float var1) {
      return super.c(var1);
   }

   public float C() {
      return 0.07F;
   }

   public float D() {
      return 0.12F;
   }

   public boolean l() {
      return true;
   }

   public boolean af() {
      return false;
   }

   public float g(int var1) {
      return 7.0F;
   }

   public boolean E() {
      return true;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

}
