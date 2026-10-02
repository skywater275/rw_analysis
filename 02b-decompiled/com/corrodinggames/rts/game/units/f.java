package com.corrodinggames.rts.game.units;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.x;
import java.util.Iterator;

public class f extends x {

   public float a = 2000.0F;
   public float b = 0.0F;
   public float c = 0.0F;
   public float d = 2000.0F;
   public float e;
   public float f;
   public boolean g = true;
   public float h = 1.0F;
   public boolean i;
   public float j;
   static Paint k = new Paint();
   static Paint l;
   static Paint m;
   static Paint n;
   static Paint o;
   static Paint p;
   boolean q;
   static final PointF r;


   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.c(0);
      var1.a(this.a);
      var1.a(this.b);
      var1.a(this.c);
      var1.a(this.d);
      var1.a(this.e);
      var1.a(this.f);
      var1.a(this.g);
      var1.a(this.h);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      var1.d();
      this.a = var1.g();
      this.b = var1.g();
      this.c = var1.g();
      this.d = var1.g();
      this.e = var1.g();
      this.f = var1.g();
      this.g = var1.e();
      this.h = var1.g();
      super.a(var1);
      if(!this.bV) {
         com.corrodinggames.rts.gameFramework.l.B().bW.a((am)this);
      }

   }

   public strictfp ar b() {
      return this.q?ar.X:ar.W;
   }

   public static strictfp void d_() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
   }

   public strictfp f(boolean var1) {
      super(var1);
   }

   public strictfp f f() {
      Iterator var1 = am.bF().iterator();

      while(var1.hasNext()) {
         am var2 = (am)var1.next();
         if(var2 instanceof f && !var2.bV && var2 != this) {
            f var3 = (f)var2;
            if(var3.q == this.q) {
               return var3;
            }
         }
      }

      return null;
   }

   public strictfp void a(float var1) {
      super.a(var1);
      if(!this.bV) {
         if(this.g) {
            this.g = false;
            f var2 = this.f();
            if(var2 != null) {
               var2.e = this.eo;
               var2.f = this.ep;
               var2.d = this.d;
               this.ci();
            } else {
               this.e = this.eo;
               this.f = this.ep;
               if(!this.q) {
                  com.corrodinggames.rts.gameFramework.l.e("DamagingBorder created " + this.e + "," + this.f + " size:" + this.d);
               }

               com.corrodinggames.rts.gameFramework.l.B().bW.a((am)this);
            }
         }

         float var3;
         float var4;
         float var12;
         if(this.q) {
            this.a = this.d;
            this.eo = this.e;
            this.ep = this.f;
         } else if(this.a > this.d) {
            this.b += 2.5E-4F * var1;
            this.a -= this.b;
            this.i = true;
            var12 = com.corrodinggames.rts.gameFramework.f.b(this.eo, this.ep, this.e, this.f);
            var3 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, this.e, this.f);
            if(var12 > 1.0F) {
               var4 = this.b;
               if(var4 > var12 * var1) {
                  var4 = var12 * var1;
               }

               this.eo += var4 * com.corrodinggames.rts.gameFramework.f.k(var3) * var1;
               this.ep += var4 * com.corrodinggames.rts.gameFramework.f.j(var3) * var1;
            }
         } else {
            this.i = false;
            this.eo = (float)((double)this.eo + (double)(this.e - this.eo) * 0.003D * (double)var1);
            this.ep = (float)((double)this.ep + (double)(this.f - this.ep) * 0.003D * (double)var1);
         }

         if(this.a < this.d) {
            this.a = this.d;
            this.b = 0.0F;
         }

         if(this.d < 0.0F) {
            this.ci();
         } else {
            this.c -= var1;
            float var5;
            if(!this.bV && this.c <= 0.0F && !this.q) {
               this.c = 2.0F;
               var12 = this.a * com.corrodinggames.rts.gameFramework.f.k(45.0F);
               var3 = this.eo - var12;
               var4 = this.eo + var12;
               var5 = this.ep - var12;
               float var6 = this.ep + var12;
               float var7 = this.a * this.a;
               Iterator var8 = am.bF().iterator();

               while(var8.hasNext()) {
                  am var9 = (am)var8.next();
                  if(var9.eo <= var3 || var9.eo >= var4 || var9.ep <= var5 || var9.ep >= var6) {
                     float var10 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, var9.eo, var9.ep);
                     if(var10 >= var7 && !var9.bV && !(var9 instanceof al) && !var9.u() && var9.cN == null) {
                        float var11 = 0.5F + var9.cu * 0.002F + var9.cv * 0.001F;
                        var11 *= this.h;
                        var9.a(this, var11, (com.corrodinggames.rts.game.f)null);
                     }
                  }
               }
            }

            if(!this.q) {
               com.corrodinggames.rts.gameFramework.l var13 = com.corrodinggames.rts.gameFramework.l.B();
               this.j += var1;
               if(this.j > 3.0F) {
                  this.j = 0.0F;
                  int var14 = var13.cu + com.corrodinggames.rts.gameFramework.f.a(0, (int)var13.cA);
                  int var15 = var13.cv + com.corrodinggames.rts.gameFramework.f.a(0, (int)var13.cB);
                  var5 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, (float)var14, (float)var15);
                  if(var5 > (this.a + 30.0F) * (this.a + 30.0F)) {
                     var13.bL.a((float)var14, (float)var15);
                     int var16 = var13.bL.T;
                     int var17 = var13.bL.U;
                     var13.bL.a(var16, var17);
                     com.corrodinggames.rts.gameFramework.d.e var18 = var13.bR.b((float)(var13.bL.T + 10), (float)(var13.bL.U - 10 + 10), 0.0F, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.a);
                     if(var18 != null) {
                        var18.aq = 19;
                        var18.Y = com.corrodinggames.rts.gameFramework.f.c(-180.0F, 180.0F);
                        var18.r = true;
                        var18.ar = 1;
                        var18.E = 0.7F;
                        var18.V = 30.0F;
                        var18.W = var18.V;
                        var18.G = 0.2F;
                        var18.F = 1.2F;
                        var18.x = Color.a(255, 173, 12, 12);
                     }
                  }
               }
            }

         }
      }
   }

   public strictfp int s() {
      return 0;
   }

   public strictfp boolean t() {
      return true;
   }

   public strictfp boolean u() {
      return true;
   }

   public strictfp boolean a(com.corrodinggames.rts.gameFramework.l var1) {
      return true;
   }

   public strictfp void a(float var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      float var4 = this.eo - var3.cw;
      float var5 = this.ep - var3.cx;
      Paint var6 = this.i?m:k;
      if(this.q) {
         var6 = o;
      }

      float var7 = this.a;
      if(this.g) {
         f var8 = this.f();
         if(var8 != null) {
            var7 = var8.d - 300.0F;
         }
      }

      var3.bO.a(var4, var5, var7, var6);
   }

   public strictfp boolean a(int var1, int var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      var3.bO.i();
      var3.bO.a(var3.bW.w);
      float var4 = var3.bW.b(this.a);
      Paint var5 = this.i?n:l;
      if(this.q) {
         var5 = p;
      }

      com.corrodinggames.rts.gameFramework.m.aa.a(var3.bO, (float)var1, (float)var2, var4, var5);
      var3.bO.j();
      return true;
   }

   public strictfp void a(int var1) {
      this.a = (float)(var1 * 100);
      this.d = (float)(var1 * 100);
   }

   public strictfp boolean a(float var1, float var2) {
      float var3 = this.d * this.d;
      float var4 = com.corrodinggames.rts.gameFramework.f.a(this.e, this.f, var1, var2);
      return var4 >= var3;
   }

   public strictfp PointF a(float var1, float var2, float var3) {
      if(var3 > this.d) {
         var3 = this.d;
      }

      float var4 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, var1, var2);
      float var5 = this.d - var3;
      float var6 = this.eo + com.corrodinggames.rts.gameFramework.f.k(var4) * var5;
      float var7 = this.ep + com.corrodinggames.rts.gameFramework.f.j(var4) * var5;
      r.a = var6;
      r.b = var7;
      return r;
   }

   // $FF: synthetic method
   public as r() {
      return this.b();
   }

   static {
      k.a(10.0F);
      k.b(Color.a(100, 160, 0, 0));
      k.a(Paint$Style.b);
      m = new Paint();
      m.a(k);
      m.b(Color.a(180, 160, 0, 0));
      l = new Paint();
      l.a(2.0F);
      l.b(Color.a(100, 160, 0, 0));
      l.a(Paint$Style.b);
      n = new Paint();
      n.a(l);
      n.b(Color.a(180, 160, 0, 0));
      o = new Paint();
      o.a(2.0F);
      o.b(Color.a(50, 255, 255, 255));
      o.a(Paint$Style.b);
      p = new Paint(o);
      r = new PointF();
   }
}
