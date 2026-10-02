package com.corrodinggames.rts.game.units.h;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.h.e$1;
import com.corrodinggames.rts.game.units.h.e$2;
import com.corrodinggames.rts.game.units.h.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public class e extends f {

   boolean a = false;
   boolean b = true;
   float c = 0.0F;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e e = null;
   static com.corrodinggames.rts.gameFramework.m.e f = null;
   public static com.corrodinggames.rts.gameFramework.m.e g = null;
   public static com.corrodinggames.rts.gameFramework.m.e[] h = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] i = new com.corrodinggames.rts.gameFramework.m.e[10];
   public static final s j = new e$1(151);
   public static final s k = new e$2(152);
   static ArrayList l = new ArrayList();


   public strictfp void a(as var1) {
      var1.a(this.a);
      var1.a(this.c);
      super.a(var1);
   }

   public strictfp void a(k var1) {
      this.a = var1.e();
      this.b = !this.Q();
      if(var1.b() >= 21) {
         this.c = var1.g();
      }

      this.L();
      super.a(var1);
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:h[this.bX.R()];
   }

   public static strictfp void b() {
      l var0 = l.B();
      e = var0.bO.a(R$drawable.attack_submarine);
      f = a(e, e.m(), e.l());
      d = var0.bO.a(R$drawable.attack_submarine_dead);
      g = var0.bO.a(R$drawable.unit_icon_water);
      h = n.a(g);
      i = n.a(e);
   }

   public strictfp boolean F() {
      return l.B().bQ.renderExtraShadows && this.eq >= 0.0F;
   }

   public strictfp float G() {
      return 0.0F;
   }

   public strictfp float H() {
      return 0.0F;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?d:i[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return f;
   }

   public strictfp ao h() {
      return ao.e;
   }

   public strictfp ar f() {
      return ar.K;
   }

   public strictfp boolean K() {
      return !this.Q();
   }

   public strictfp e(boolean var1) {
      super(var1);
      this.b(e);
      this.cj = 15.0F;
      this.ck = this.cj - 2.0F;
      this.cv = 260.0F;
      this.cu = this.cv;
      this.M = e;
   }

   public strictfp void L() {
      if(!this.b) {
         this.S(1);
      } else {
         this.S(2);
      }

   }

   public strictfp void s(float var1) {
      l var2 = l.B();
      float var3;
      if(this.a) {
         var3 = 1.0F;
      } else {
         var3 = -8.0F;
      }

      if(com.corrodinggames.rts.gameFramework.f.c(this.eq - var3) > 2.0F) {
         this.c = com.corrodinggames.rts.gameFramework.f.a(this.c, 0.08F, 0.003F * var1);
      } else {
         this.c = com.corrodinggames.rts.gameFramework.f.a(this.c, 0.02F, 0.003F * var1);
      }

      this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, var3, this.c * var1);
      boolean var4 = false;
      if(this.b && this.Q()) {
         this.b = false;
         this.L();
         var4 = true;
      }

      if(!this.b && !this.Q()) {
         this.b = true;
         this.L();
         var4 = true;
      }

      if(var4) {
         var2.bR.a(this.eo, this.ep, 0.0F, 0, 0.0F, 0.0F, this.cg);
      }

   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bT() || this.bV) {
         ;
      }
   }

   public strictfp float m() {
      return !this.Q()?250.0F:180.0F;
   }

   public strictfp float b(int var1) {
      return 170.0F;
   }

   public strictfp float e(int var1) {
      return 10.0F;
   }

   public strictfp float z() {
      return !this.Q()?0.8F:0.45F;
   }

   public strictfp float A() {
      return 1.2F;
   }

   public strictfp float B() {
      return 0.06F;
   }

   public strictfp float c(int var1) {
      return 2.5F;
   }

   public strictfp float w(int var1) {
      return 0.08F;
   }

   public strictfp float C() {
      return 0.018F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return null;
   }

   public strictfp boolean l() {
      return true;
   }

   public strictfp boolean Q() {
      return this.eq < -1.0F;
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

   public strictfp float q(int var1) {
      return 42.0F;
   }

   public strictfp void a(am var1, int var2) {
      PointF var3;
      com.corrodinggames.rts.game.f var4;
      PointF var5;
      l var6;
      if(!this.Q()) {
         var3 = this.E(var2);
         var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
         var5 = this.K(var2);
         var4.K = var5.a;
         var4.L = var5.b;
         var4.ar = Color.a(255, 230, 230, 50);
         var4.U = 42.0F;
         var4.l = var1;
         var4.h = 190.0F;
         var4.t = 2.0F;
         var4.aH = true;
         var4.aM = true;
         var4.aQ = true;
         var6 = l.B();
         var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.8F, this.eo, this.ep);
         var6.bR.a(this.eo, this.ep, this.eq, -1118720);
      } else {
         var3 = this.E(var2);
         var4 = com.corrodinggames.rts.game.f.a(this, var3.a, var3.b, this.eq, var2);
         var5 = this.K(var2);
         var4.K = var5.a;
         var4.L = var5.b;
         var4.ar = Color.a(255, 30, 30, 150);
         var4.x = 1.0F;
         var4.U = 42.0F;
         var4.l = var1;
         var4.h = 250.0F;
         var4.t = 1.3F;
         var4.aH = false;
         var4.aM = true;
         var4.aQ = true;
         var6 = l.B();
      }

   }

   public strictfp boolean e() {
      l var1 = l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = d;
      this.S(0);
      this.bT = false;
      return true;
   }

   public strictfp void a(s var1, boolean var2) {
      if(var1 == j) {
         this.a = true;
      }

      if(var1 == k) {
         this.a = false;
      }

   }

   public strictfp ArrayList N() {
      return l;
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
      l.add(j);
      l.add(k);
   }
}
