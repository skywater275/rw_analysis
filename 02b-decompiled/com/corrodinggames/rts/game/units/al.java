package com.corrodinggames.rts.game.units;

import android.graphics.Paint;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.v;

public class al extends v {

   static com.corrodinggames.rts.gameFramework.m.e[] a = new com.corrodinggames.rts.gameFramework.m.e[3];
   static com.corrodinggames.rts.gameFramework.m.e b = null;
   com.corrodinggames.rts.gameFramework.m.e c;
   int d;
   int e;
   int f = 0;
   float g;
   boolean h;
   float i;
   int j = 0;
   int k = 0;
   float l = 1.0F;
   boolean m = false;


   public static void b() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      a[0] = var0.bO.a(R$drawable.palm_tree);
      a[1] = var0.bO.a(R$drawable.trees);
      a[2] = var0.bO.a(R$drawable.trees_snow);
      b = var0.bO.a(R$drawable.palm_leaves);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.a(this.d);
      var1.a(this.f);
      var1.a(this.g);
      var1.a(this.h);
      var1.a(this.i);
      var1.c(2);
      var1.a(this.l);
      var1.a(this.e);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.d = var1.f();
      this.f = var1.f();
      this.g = var1.g();
      this.h = var1.e();
      this.i = var1.g();
      byte var2 = var1.d();
      if(var2 >= 1) {
         this.l = var1.g();
      } else {
         this.l = 1.0F;
      }

      if(var2 >= 2) {
         this.e = var1.f();
      } else {
         this.e = 0;
      }

      this.b(this.d, this.e);
      super.a(var1);
      if(this.bV) {
         this.m = false;
      }

   }

   public com.corrodinggames.rts.gameFramework.m.e d() {
      return this.c;
   }

   public boolean e() {
      this.k();
      return true;
   }

   public al(boolean var1) {
      super(var1);
      this.b(1, -1);
      this.cj = 3.0F;
      this.ck = this.cj + 1.0F;
      this.cv = 100.0F;
      this.cu = this.cv;
      this.cg = -90.0F;
      this.S(3);
   }

   public void a_(String var1) {
      int var3 = -1;
      String[] var4 = var1.split("\\.");
      if(var4.length != 0 && var4.length != 1) {
         if(var4.length != 2) {
            throw new RuntimeException("Tree sub unknown format with parts:" + var4.length);
         }

         var1 = var4[0];

         try {
            var3 = Integer.parseInt(var4[1]);
         } catch (NumberFormatException var7) {
            throw new RuntimeException("Tree sub type format error:" + var4[1]);
         }
      }

      int var2;
      try {
         var2 = Integer.parseInt(var1);
      } catch (NumberFormatException var6) {
         throw new RuntimeException("Tree type format error:" + var1);
      }

      this.b(var2, var3);
   }

   public void b(int var1, int var2) {
      this.d = var1;
      this.e = var2;
      if(this.d == 0) {
         this.T(27);
         this.U(41);
         this.j = 1;
         this.k = 1;
         this.c = a[0];
      } else {
         if(this.d != 1 && this.d != 2) {
            throw new RuntimeException("Tree type:" + this.d + " is not supported");
         }

         if(var2 == -1) {
            var2 = com.corrodinggames.rts.gameFramework.f.a(0, 4, (int)this.eh);
         }

         if(var2 < 0 || var2 > 4) {
            throw new RuntimeException("Tree subType out of range:" + var2);
         }

         this.T(25);
         this.U(30);
         if(this.d == 1) {
            this.c = a[1];
         } else {
            this.c = a[2];
         }

         this.j = 0;
         this.k = 30 * var2;
         if(var2 == 0) {
            this.l = com.corrodinggames.rts.gameFramework.f.a(1.0F, 1.2F, (int)this.eh + 1);
         } else {
            this.l = com.corrodinggames.rts.gameFramework.f.a(1.0F, 2.0F, (int)this.eh + 1);
         }

         this.m = true;
      }

   }

   public void a(float var1) {
      if(this.d == 0) {
         if(this.h) {
            if(this.f < 4) {
               this.g += var1;
               if(this.g > 5.0F) {
                  this.g = 0.0F;
                  ++this.f;
               }
            }
         } else if(this.i != 0.0F) {
            this.i = com.corrodinggames.rts.gameFramework.f.a(this.i, 0.1F * var1);
            this.f = 2;
         } else if(this.f > 1) {
            this.f = 1;
         }
      }

   }

   public Rect a_(boolean var1) {
      int var2 = this.j;
      int var3 = this.k;
      var2 += this.f * (this.es + 1);
      dC.a(var2, var3, var2 + this.es, var3 + this.et);
      return dC;
   }

   public boolean c(float var1) {
      com.corrodinggames.rts.gameFramework.m.e var2 = this.d();
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if((double)var3.cX < 0.15D) {
         return false;
      } else {
         du.a(this.cF());
         du.a(0.0F, (float)((int)(-this.eq)));
         float var4 = du.d();
         float var5 = du.e();
         dv.a(this.a_(false));
         com.corrodinggames.rts.gameFramework.m.y var6 = var3.bO;
         var6.k();
         if(this.l != 1.0F) {
            var6.a(this.l, this.l, var4, var5);
         }

         if(this.m) {
            dv.a(this.es, 0);
            var3.bO.a(var2, dv, du, (Paint)null);
            dv.a(-this.es, 0);
         }

         var6.a(this.d(false), var4, var5);
         var6.a(var2, dv, du, (Paint)null);
         var6.l();
         return true;
      }
   }

   public ao h() {
      return ao.a;
   }

   public boolean i() {
      return false;
   }

   public boolean Q() {
      return false;
   }

   public boolean aj() {
      return false;
   }

   public boolean ak() {
      return false;
   }

   public boolean s_() {
      return true;
   }

   public boolean c_() {
      return false;
   }

   public ar f() {
      return ar.A;
   }

   public boolean a(am var1, float var2) {
      if(!this.h) {
         if(this.i == 0.0F) {
            ;
         }

         this.cu -= var1.bN() / 3000.0F * this.cv * 0.06F * var2;
         this.i = 1.0F;
         this.dp = 1000.0F;
         if(this.cu <= 0.0F) {
            float var3 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, var1.eo, var1.ep) + 180.0F;
            this.cg = var3;
            this.k();
         }

         if(!this.h) {
            return false;
         }
      }

      return true;
   }

   public void k() {
      if(!this.h) {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         this.f = 2;
         this.g = 0.0F;
         this.S(0);
         this.bT = false;
         this.bV = true;
         this.bW = (long)var1.by;
         this.h = true;
         this.m = false;

         for(int var2 = 0; var2 < 1; ++var2) {
            var1.bR.a();
            com.corrodinggames.rts.gameFramework.d.e var3 = var1.bR.b(this.eo + com.corrodinggames.rts.gameFramework.f.c(-12.0F, 12.0F), this.ep + com.corrodinggames.rts.gameFramework.f.c(-12.0F, 12.0F), this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.c);
            if(var3 != null) {
               var3.aq = 9;
               var3.ap = com.corrodinggames.rts.gameFramework.f.a(4, 5);
               var3.Y = com.corrodinggames.rts.gameFramework.f.c(-180.0F, 180.0F);
               var3.an = true;
               var3.K = 5.0F + com.corrodinggames.rts.gameFramework.f.c(0.0F, 3.0F);
               var3.P = com.corrodinggames.rts.gameFramework.f.c(-0.05F, 0.05F) + com.corrodinggames.rts.gameFramework.f.k(this.cg) * 0.4F;
               var3.Q = com.corrodinggames.rts.gameFramework.f.c(-0.05F, 0.05F) + com.corrodinggames.rts.gameFramework.f.j(this.cg) * 0.4F;
               var3.v = true;
               var3.w = 0.2F;
               var3.G = 0.4F * this.l;
               var3.F = 0.4F * this.l;
               var3.V = (float)(90 + com.corrodinggames.rts.gameFramework.f.a(0, 40));
               var3.W = var3.V;
               var3.r = true;
               var3.ar = 2;
            }
         }

         float var8 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * (float)(this.et - 5);
         float var9 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * (float)(this.et - 5);
         boolean var4 = true;

         for(int var5 = 0; var5 < 1; ++var5) {
            byte var6 = 17;
            var1.bR.a();
            com.corrodinggames.rts.gameFramework.d.e var7 = var1.bR.b(var8 + com.corrodinggames.rts.gameFramework.f.c((float)(-var6), (float)var6), var9 + com.corrodinggames.rts.gameFramework.f.c((float)(-var6), (float)var6), this.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.c);
            if(var7 != null) {
               var7.aq = 9;
               var7.ap = com.corrodinggames.rts.gameFramework.f.a(4, 5);
               if(var4) {
                  var4 = false;
                  var7.ap = 3;
               }

               var7.Y = com.corrodinggames.rts.gameFramework.f.c(-180.0F, 180.0F);
               var7.an = true;
               if(var7.ap == 3) {
                  var7.P = com.corrodinggames.rts.gameFramework.f.c(-0.05F, 0.05F);
                  var7.Q = com.corrodinggames.rts.gameFramework.f.c(-0.05F, 0.05F);
                  var7.G = 1.5F * this.l;
                  var7.F = 2.2F * this.l;
                  var7.V = (float)(90 + com.corrodinggames.rts.gameFramework.f.a(0, 40));
                  var7.ar = 2;
               } else {
                  var7.P = com.corrodinggames.rts.gameFramework.f.c(-0.05F, 0.05F);
                  var7.Q = com.corrodinggames.rts.gameFramework.f.c(-0.05F, 0.0F);
                  var7.G = 1.3F;
                  var7.F = 1.3F;
                  var7.V = (float)(60 + com.corrodinggames.rts.gameFramework.f.a(0, 40));
                  var7.ar = 1;
               }

               var7.W = var7.V;
               var7.r = true;
            }
         }

         if(this.d == 1 || this.d == 2) {
            this.eo += com.corrodinggames.rts.gameFramework.f.k(this.cg) * (float)(this.et / 2 - 3);
            this.ep += com.corrodinggames.rts.gameFramework.f.j(this.cg) * (float)(this.et / 2 - 3);
         }
      }

   }

   public void n() {
      super.n();
      this.cg = com.corrodinggames.rts.gameFramework.f.a(this.ep * 5.0F + this.eo * 3.0F, false);
      if(this.d == 0) {
         this.f = (int)(this.ep * 5.0F + this.eo * 3.0F) % 1;
      }

      if(this.d == 1) {
         ;
      }

   }

   public float x() {
      return -1.0F;
   }

   public boolean l() {
      return false;
   }

   public float a(am var1, float var2, com.corrodinggames.rts.game.f var3) {
      boolean var4 = this.bV;
      float var5 = super.a(var1, var2, var3);
      if(!var4 && this.bV && var3 != null) {
         float var6 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, var3.eo, var3.ep) + 180.0F;
         this.cg = var6;
      }

      return var5;
   }

   public boolean q() {
      return true;
   }

   public boolean t() {
      return true;
   }

   // $FF: synthetic method
   public as r() {
      return this.f();
   }

}
