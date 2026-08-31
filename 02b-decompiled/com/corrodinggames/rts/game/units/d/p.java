package com.corrodinggames.rts.game.units.d;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.p$1;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public class p extends i {

   static com.corrodinggames.rts.gameFramework.m.e[] a = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e[] b = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e c = null;
   boolean d;
   boolean e;
   float f;
   static com.corrodinggames.rts.gameFramework.m.e g = null;
   static com.corrodinggames.rts.gameFramework.m.e[] h = new com.corrodinggames.rts.gameFramework.m.e[10];
   PointF i = new PointF();
   Rect j = new Rect();
   static com.corrodinggames.rts.game.units.a.s k = new p$1(102);
   static ArrayList l = new ArrayList();


   public void a(as var1) {
      var1.a(this.d);
      var1.a(this.cB);
      var1.a(this.e);
      var1.a(this.f);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.d = var1.e();
      this.cB = var1.g();
      this.e = var1.e();
      if(var1.b() >= 38) {
         this.f = var1.g();
      }

      super.a(var1);
   }

   public com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:h[this.bX.R()];
   }

   public static void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      c = var0.bO.a(R$drawable.laser_defence_dead);
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.laser_defence);
      com.corrodinggames.rts.gameFramework.m.e var2 = var0.bO.a(R$drawable.laser_defence_t2);
      a = com.corrodinggames.rts.game.n.a(var1);
      b = com.corrodinggames.rts.game.n.a(var2);
      var1.n();
      var1 = null;
      var2.n();
      var2 = null;
      g = var0.bO.a(R$drawable.unit_icon_building_turrent);
      h = com.corrodinggames.rts.game.n.a(g);
   }

   public boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.a(this.eo, this.ep, this.eq);
      this.M = c;
      this.S(0);
      this.bT = false;
      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8F, this.eo, this.ep);
      return true;
   }

   public com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?c:(this.bX == null?a[a.length - 1]:(!this.d?a[this.bX.R()]:b[this.bX.R()]));
   }

   public com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public p(boolean var1) {
      super(var1);
      this.a(a[0], 2);
      this.cB = 1.0F;
      this.cj = 19.0F;
      this.ck = this.cj;
      this.cv = 500.0F;
      this.cu = this.cv;
      this.M = a[a.length - 1];
      this.n.a(0, 0, 1, 1);
      this.o.a(0, 0, 1, 1);
   }

   public void a(float var1) {
      super.a(var1);
      if(this.bT() && !this.bV) {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         float var3 = 4.0E-4F * var1;
         if(this.d) {
            var3 += 2.0E-4F * var1;
         }

         this.cB = com.corrodinggames.rts.gameFramework.f.a(this.cB, 1.0F, var3);
         if(this.cB >= 1.0F) {
            this.e = false;
         }

         this.f -= var1;
         this.i.a(this.E(0));
         if(this.cB > 0.0F && !this.e) {
            float var4;
            if(!this.d) {
               var4 = 0.11F;
            } else {
               var4 = 0.05F;
            }

            if(a(this, this.i.a, this.i.b, this.eq, this.m(), var4)) {
               this.f = 3.0F;
            }

            if(this.cB < 0.0F) {
               this.cB = 0.0F;
               this.e = true;
            }
         }

         if(this.e) {
            this.s = 1;
         } else {
            this.s = 0;
         }

      }
   }

   public static boolean a(y var0, float var1, float var2, float var3, float var4, float var5) {
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      float var7 = var4 * var4;
      Object[] var8 = com.corrodinggames.rts.game.f.a.a();
      int var9 = 0;

      for(int var10 = com.corrodinggames.rts.game.f.a.a; var9 < var10; ++var9) {
         com.corrodinggames.rts.game.f var11 = (com.corrodinggames.rts.game.f)var8[var9];
         if(!var11.A && !var11.C && (var11.J > 7.0F || var11.J > 2.0F && var11.t > 8.0F) && !var11.aS) {
            float var12 = (var11.eo - var1) * (var11.eo - var1) + (var11.ep - var2) * (var11.ep - var2);
            if(var12 < var7 && var11.eq >= -1.0F) {
               boolean var13 = false;
               if(var11.l != null && var0.bX.d(var11.l.bX)) {
                  var13 = true;
               }

               if(!var13 && var11.j != null && var0.bX.c(var11.j.bX)) {
                  var13 = true;
               }

               if(var13) {
                  com.corrodinggames.rts.gameFramework.d.e var14 = var6.bR.a(var1, var2, var3, var11.eo, var11.ep, var11.eq);
                  if(var14 != null) {
                     var14.V = 10.0F;
                     var14.W = var14.V;
                  }

                  com.corrodinggames.rts.gameFramework.d.e var15 = var6.bR.b(var1, var2, var3, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.c);
                  if(var15 != null) {
                     var15.P = 0.0F;
                     var15.Q = 0.0F;
                     var15.ap = 4;
                     var15.V = 39.0F;
                     var15.W = var15.V;
                     var15.r = true;
                     var15.E = 1.3F;
                     var15.G = 1.1F;
                     var15.F = 0.7F;
                  }

                  --var11.H;
                  if(var11.H <= 0.0F) {
                     var11.d();
                     var15 = var6.bR.b(var11.eo, var11.ep, var11.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.c);
                     if(var15 != null) {
                        var15.P = 0.0F;
                        var15.Q = 0.0F;
                        var15.ap = 4;
                        var15.V = 23.0F;
                        var15.W = var15.V;
                        var15.r = true;
                        var15.E = 0.9F;
                        var15.G = 0.5F;
                        var15.F = 0.2F;
                     }

                     float var16 = 1.0F + com.corrodinggames.rts.gameFramework.f.c(-0.07F, 0.07F);
                     var6.bM.a(com.corrodinggames.rts.gameFramework.a.e.F, 0.2F, var16, var11.eo, var11.ep);
                  }

                  var0.cB -= var5;
                  return true;
               }
            }
         }
      }

      return false;
   }

   public PointF E(int var1) {
      bg.a(this.eo, this.ep - 13.0F);
      return bg;
   }

   public void a(am var1, int var2) {}

   public float m() {
      return !this.d?160.0F:210.0F;
   }

   public float c(int var1) {
      return 4.0F;
   }

   public boolean b(int var1, float var2) {
      return false;
   }

   public boolean c(float var1) {
      return super.c(var1);
   }

   public ar K() {
      return ar.y;
   }

   public boolean l() {
      return false;
   }

   public float g(int var1) {
      return 1.0F;
   }

   public float bW() {
      return this.cB != 1.0F?this.cB:super.bW();
   }

   public boolean bX() {
      return this.e;
   }

   public float bd() {
      return 1.0F;
   }

   public void a(j var1) {
      if(var1.j.equals(k.N())) {
         com.corrodinggames.rts.game.n.b((am)this);
         this.a(2);
         com.corrodinggames.rts.game.n.c((am)this);
         this.W();
      }

   }

   public com.corrodinggames.rts.game.units.a.c cm() {
      return !this.d?k.N():com.corrodinggames.rts.game.units.a.s.i;
   }

   public int V() {
      return this.d?2:1;
   }

   public void a(int var1) {
      if(var1 == 1) {
         this.d = false;
      } else if(var1 == 2 && !this.d) {
         this.d = true;
         this.cv += 900.0F;
         this.cu += 900.0F;
      }

      this.S();
   }

   public ArrayList N() {
      return l;
   }

   public void e(float var1) {
      super.e(var1);
      float var2 = this.m();
      com.corrodinggames.rts.gameFramework.utility.y.a(this, var2);
   }

   public float cZ() {
      return (float)com.corrodinggames.rts.gameFramework.l.B().bL.n;
   }

   public float da() {
      return (float)com.corrodinggames.rts.gameFramework.l.B().bL.o;
   }

   public float db() {
      return super.db() - 8.0F;
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

   static {
      l.add(k);
   }
}
