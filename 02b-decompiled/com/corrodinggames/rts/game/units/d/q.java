package com.corrodinggames.rts.game.units.d;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.q$1;
import com.corrodinggames.rts.game.units.d.q$2;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public class q extends i {

   static com.corrodinggames.rts.gameFramework.m.e[] a = new com.corrodinggames.rts.gameFramework.m.e[10];
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   int c;
   static com.corrodinggames.rts.gameFramework.m.e d = null;
   static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
   PointF f = new PointF();
   Rect g = new Rect();
   static com.corrodinggames.rts.game.units.a.s h = new q$1(142);
   static com.corrodinggames.rts.game.units.a.s i = new q$2(143);
   static ArrayList j = new ArrayList();


   public void a(as var1) {
      var1.a(this.c);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.c = var1.f();
      super.a(var1);
   }

   public com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:e[this.bX.R()];
   }

   public static void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      b = var0.bO.a(R$drawable.nuke_launcher_dead);
      com.corrodinggames.rts.gameFramework.m.e var1 = var0.bO.a(R$drawable.nuke_launcher);
      a = com.corrodinggames.rts.game.n.a(var1);
      var1.n();
      d = var0.bO.a(R$drawable.unit_icon_building);
      e = com.corrodinggames.rts.game.n.a(d);
   }

   public int bp() {
      return 20;
   }

   public boolean L() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.M = b;
      this.S(0);
      this.bT = false;
      this.a(ab.h);
      float var2 = this.eo;
      float var3 = this.ep;
      float var4 = 0.0F;
      var1.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
      com.corrodinggames.rts.gameFramework.d.e var5 = var1.bR.a(var2, var3, this.eq, Color.a(255, 255, 255, 255));
      if(var5 != null) {
         var5.G = 8.0F;
         var5.F = 5.0F;
         var5.E = 0.9F;
         var5.V = 20.0F;
         var5.W = var5.V;
         var5.r = true;
      }

      var1.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
      var5 = var1.bR.c(var2, var3, var4, -1127220);
      if(var5 != null) {
         var5.G = 0.2F;
         var5.F = 2.0F;
         var5.ar = 2;
         var5.V = 45.0F;
         var5.W = var5.V;
         var5.U = 0.0F;
      }

      float var6 = 40.0F;
      float var7 = 120.0F;
      var1.bR.a(this.eo, this.ep, this.eq, var6, var7);
      return true;
   }

   public com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?b:a[this.bX.R()];
   }

   public com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public void a(int var1) {}

   public q(boolean var1) {
      super(var1);
      this.M = a[a.length - 1];
      this.b(this.M);
      this.cj = 40.0F;
      this.ck = this.cj;
      this.cv = 1500.0F;
      this.cu = this.cv;
      this.n.a(-2, -1, 2, 1);
      this.o.a(-2, -1, 2, 2);
   }

   public void a(float var1) {
      super.a(var1);
      if(!this.bT() || this.bV) {
         ;
      }
   }

   public PointF E(int var1) {
      bg.a(this.eo, this.ep - 3.0F);
      return bg;
   }

   public void a(am var1, int var2) {}

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
      return ar.C;
   }

   public boolean l() {
      return false;
   }

   public float g(int var1) {
      return 1.0F;
   }

   public float bV() {
      return super.bV();
   }

   public void a(float var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.c > 0) {
         float var4 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, var1, var2);
         if(var4 < 202500.0F) {
            if(this.bX == var3.bs) {
               var3.bS.b("Nuke target too close");
            }

         } else {
            --this.c;
            byte var5 = 0;
            PointF var6 = this.E(var5);
            float var7 = 5.0F;
            com.corrodinggames.rts.game.f var8 = a(this, var6.a, var6.b, var1, var2);
            var8.i = var7;
            com.corrodinggames.rts.gameFramework.d.e var9 = var3.bR.a(var6.a, var6.b, this.eq, -1127220);
            if(var9 != null) {
               var9.U = var7;
               var9.G = 2.1F;
               var9.F = 2.1F;
               var9.ar = 2;
               var9.s = true;
               var9.t = 70.0F;
               var9.V = 370.0F;
               var9.W = var9.V;
               var9.E = 1.0F;
            }

            com.corrodinggames.rts.gameFramework.d.e var10 = var3.bR.d(var6.a, var6.b, 0.0F, -1);
            if(var10 != null) {
               var10.G = 1.0F;
               var10.F = 3.1F;
               var10.ar = 2;
               var10.V = 170.0F;
               var10.W = var10.V;
               var10.U = var7 + 20.0F;
            }

            float var11 = 0.8F;
            var3.bM.a(com.corrodinggames.rts.gameFramework.a.e.D, 0.27F, var11, var6.a, var6.b);
         }
      }
   }

   public static com.corrodinggames.rts.game.f a(am var0, float var1, float var2, float var3, float var4) {
      com.corrodinggames.rts.game.f var5 = com.corrodinggames.rts.game.f.a(var0, var1, var2);
      var5.S(10);
      var5.P = 0;
      var5.Q = 1;
      var5.R = 1;
      var5.x = 1.0F;
      var5.aC = true;
      var5.m = true;
      var5.n = var3;
      var5.o = var4;
      var5.h = 99999.0F;
      var5.t = 0.1F;
      var5.r = 2.7F;
      var5.ar = Color.a(255, 225, 225, 225);
      var5.U = 300.0F;
      var5.aH = true;
      var5.aM = true;
      var5.aQ = true;
      var5.C = true;
      var5.D = true;
      var5.aI = 80.0F;
      var5.aJ = 100.0F;
      var5.aL = 1.1F;
      var5.Y = 5400.0F;
      var5.Z = 250.0F;
      var5.ad = true;
      var5.ae = false;
      var5.ao = true;
      var5.W = 75.0F;
      var5.X = var5.W;
      var5.aY = true;
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      var6.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
      com.corrodinggames.rts.gameFramework.d.e var7 = var6.bR.a(var5, -1118720);
      if(var7 != null) {
         var7.V = 1300.0F;
         var7.W = var7.V;
         var7.E = 0.2F;
         var7.G = 1.0F;
      }

      return var5;
   }

   public void M() {
      ++this.c;
   }

   public void a(j var1) {
      if(var1.j.equals(i.N())) {
         this.M();
      }

   }

   public com.corrodinggames.rts.game.units.a.c cm() {
      return com.corrodinggames.rts.game.units.a.s.i;
   }

   public void a(com.corrodinggames.rts.game.units.a.s var1, boolean var2, PointF var3, am var4) {
      if(!var2) {
         if(var1 == h) {
            if(var3 == null) {
               ad.g("action:" + h.N() + " needs point but it is missing");
            } else {
               this.a(var3.a, var3.b);
            }
         } else {
            super.a(var1, var2, var3, var4);
         }
      }
   }

   public ArrayList N() {
      return j;
   }

   public void e(float var1) {
      super.e(var1);
      y.b(this, 450.0F, false);
   }

   // $FF: synthetic method
   public com.corrodinggames.rts.game.units.as r() {
      return this.K();
   }

   static {
      j.add(h);
      j.add(i);
   }
}
