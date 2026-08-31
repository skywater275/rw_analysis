package com.corrodinggames.rts.game.units.d;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.Iterator;

public abstract class d extends y {

   com.corrodinggames.rts.gameFramework.m.e m;
   public Rect n = new Rect();
   public Rect o = new Rect();
   public static com.corrodinggames.rts.gameFramework.m.e p = null;
   public static com.corrodinggames.rts.gameFramework.m.e[] q = new com.corrodinggames.rts.gameFramework.m.e[10];
   int r = 1;
   int s = 0;


   public strictfp boolean ds() {
      return false;
   }

   public strictfp void a(as var1) {
      var1.a(this.r);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      if(var1.b() >= 15) {
         int var2 = var1.f();
         this.R(var2);
      }

      super.a(var1);
   }

   public static strictfp boolean a(com.corrodinggames.rts.game.units.as var0, float var1, float var2, com.corrodinggames.rts.game.n var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      y var5 = (y)am.a(var0);
      var4.bL.b(var1, var2);
      var5.eo = (float)var4.bL.T + var5.cZ();
      var5.ep = (float)var4.bL.U + var5.cZ();
      var5.b(var3);
      boolean var6 = var5.c((com.corrodinggames.rts.game.n)null);
      return var6;
   }

   public strictfp void R(int var1) {
      this.r = var1;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return null;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:q[this.bX.R()];
   }

   public static strictfp void dt() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      p = var0.bO.a(R$drawable.unit_icon_building);
      q = com.corrodinggames.rts.game.n.a(p);
   }

   public strictfp d(boolean var1) {
      super(var1);
      this.cg = -90.0F;
      this.bT = false;
   }

   public strictfp void f_() {
      this.bT = false;
   }

   public strictfp boolean L() {
      this.a(ab.d);
      return false;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bU.a((y)this);
      if(this.cm < 1.0F) {
         this.a(ab.a);
         return false;
      } else {
         this.s = 0;
         return this.L();
      }
   }

   public strictfp Rect cd() {
      return this.o;
   }

   public strictfp Rect cc() {
      return this.n;
   }

   public static strictfp boolean a(y var0, com.corrodinggames.rts.game.units.as var1, ao var2, int var3, int var4, int var5) {
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      com.corrodinggames.rts.game.b.b var7 = var6.bL;
      if(!var7.c(var3, var4)) {
         return false;
      } else {
         boolean var8 = false;
         if(var7.E && var6.bs.N != null) {
            if(!var7.G && var6.bs.N[var3][var4] == 10) {
               return false;
            }

            var8 = var6.bs.N[var3][var4] >= 5;
         }

         if(a(var0, var1, var2, var3, var4, var8)) {
            if(var1.p()) {
               com.corrodinggames.rts.game.b.g var9 = var7.e(var3, var4);
               return var9 != null && var9.i;
            } else {
               return !com.corrodinggames.rts.gameFramework.d.a.a(var6.bs, var3, var4, var5);
            }
         } else {
            return false;
         }
      }
   }

   public static strictfp boolean a(y var0, com.corrodinggames.rts.game.units.as var1, ao var2, int var3, int var4, boolean var5) {
      return a(var0, var1, var2, var3, var4, var5, (com.corrodinggames.rts.game.n)null) == null;
   }

   public static strictfp String a(y var0, com.corrodinggames.rts.game.units.as var1, ao var2, int var3, int var4, boolean var5, com.corrodinggames.rts.game.n var6) {
      com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
      if(!var7.bL.c(var3, var4)) {
         return "{0}";
      } else {
         be var8 = var1.q();
         if(var8 != null) {
            String var9 = var8.a(var0, var3, var4);
            if(var9 != null) {
               return var9;
            }
         }

         if(var1 != ar.d && var2 != ao.e) {
            com.corrodinggames.rts.game.b.g var11 = var7.bL.e(var3, var4);
            if(var11 != null && var11.i) {
               return var1.p()?null:"{0}";
            } else if(var2 == ao.d) {
               return null;
            } else if(var2 == ao.f) {
               return !var7.bU.a(var7.bU.C, var3, var4)?null:"{0}";
            } else if(var2 == ao.g) {
               return !var7.bU.a(var7.bU.D, var3, var4)?null:"{0}";
            } else if(var2 == ao.h) {
               return !var7.bU.a(var7.bU.E, var3, var4)?null:"{0}";
            } else {
               if(var7.bU.a(var7.bU.z, var3, var4, var5)) {
                  boolean var10 = false;
                  if(var6 != null && !var7.bL.a(var3, var4, var6)) {
                     var10 = true;
                  }

                  if(!var10) {
                     return "{0}";
                  }
               }

               return null;
            }
         } else {
            return !var7.bU.a(var7.bU.A, var3, var4)?null:"{3}";
         }
      }
   }

   public static strictfp am b(int var0, int var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      var2.bL.a(var0, var1);
      float var3 = (float)(var2.bL.T + var2.bL.p);
      float var4 = (float)(var2.bL.U + var2.bL.q);
      Iterator var5 = var2.cc.b(var3, var4, 0.0F).iterator();

      am var6;
      do {
         if(!var5.hasNext()) {
            return null;
         }

         var6 = (am)var5.next();
      } while(!var6.bI() || var6.bV || !var6.c(var3, var4, 0.0F));

      return var6;
   }

   public strictfp void a(int var1) {}

   public static strictfp am g(com.corrodinggames.rts.game.units.as var0) {
      if(var0 == null) {
         throw new RuntimeException("type is null");
      } else {
         return var0.a();
      }
   }

   public strictfp boolean I() {
      return false;
   }

   public strictfp ao h() {
      return ao.a;
   }

   public strictfp boolean i() {
      return false;
   }

   public strictfp boolean Q() {
      return false;
   }

   public strictfp float z() {
      return 0.0F;
   }

   public strictfp float A() {
      return 0.0F;
   }

   public strictfp boolean b_() {
      return false;
   }

   public strictfp Paint f() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      PorterDuffColorFilter var2 = null;
      int var3;
      if(this.cm < 1.0F) {
         var3 = Color.a((int)(40.0F + this.cm * 200.0F), 140, 255, 140);
         var2 = aX;
      } else {
         var3 = Color.a(255, 255, 255, 255);
      }

      if(this.cp) {
         if(this.cs) {
            var3 = Color.a(200, 20, 255, 20);
            var2 = aY;
         }

         if(this.ct) {
            var3 = Color.a(200, 255, 20, 20);
            var2 = aZ;
         }

         if(this.cq) {
            var3 = Color.a(70, 70, 70, 245);
            var2 = ba;
            if(this.ct) {
               var3 = Color.a(70, 255, 20, 20);
               var2 = aZ;
            }
         }

         if(this.cr) {
            var3 = Color.a(150, 100, 100, 100);
         }
      }

      boolean var4 = var1.bQ.renderAntiAlias;
      if(!this.dk()) {
         var4 = false;
         if(var1.cX < 1.0F) {
            var4 = true;
         }
      }

      if(this.co) {
         var4 = ar.ag;
      }

      return this.a(var3, var2, var4);
   }

   public strictfp boolean c(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      int var3 = this.s * this.es;
      byte var4 = 0;
      RectF var5 = this.cF();
      dv.a(var3, var4, var3 + this.es, var4 + this.et);
      var2.bO.a(this.M, dv, var5, this.f());
      return true;
   }

   public strictfp void d(float var1) {
      super.d(var1);
      if(this.m != null) {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         if(this.ds()) {
            var2.bO.b(this.m, this.eo - (float)((int)(this.m.t + 0.1F)) - var2.cw, this.ep - (float)((int)(this.m.u + 0.1F)) - var2.cx, this.f());
         } else {
            byte var3 = 0;
            byte var4 = 0;
            RectF var5 = this.cF();
            dv.a(var3, var4, var3 + this.es, var4 + this.et);
            var2.bO.a(this.m, dv, var5, this.f());
         }

      }
   }

   public strictfp boolean bI() {
      return true;
   }

}
