package com.corrodinggames.rts.game.b;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.d;
import com.corrodinggames.rts.game.b.e;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.u;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.y;
import com.corrodinggames.rts.gameFramework.m.z;
import java.util.ArrayList;
import java.util.Iterator;

public final class c {

   int a = 7;
   public com.corrodinggames.rts.gameFramework.m.e b = null;
   public y c = null;
   d[][] d = (d[][])null;
   public ag e = new ag();
   int f;
   int g;
   int h;
   int i;
   int j;
   int k;
   float l;
   float m = 1.0F;
   boolean n;
   Rect o = new Rect();
   int p = 0;


   public strictfp void a() {
      l var1 = l.B();
      float var2 = this.g();
      this.m = var2;
      if(this.m > 1.0F) {
         ;
      }

      this.i = (int)((float)this.h / this.m);
      this.k = (int)((float)this.j / this.m);
      this.l = 1.0F / (float)this.k;
      this.f = var1.cu - this.i / 2;
      this.g = var1.cv - this.i / 2;
      byte var3 = 20;
      float var4 = 1.0F / (float)var3;
      this.f = (int)((float)this.f * var4) * var3;
      this.g = (int)((float)this.g * var4) * var3;

      for(int var5 = 0; var5 < this.a; ++var5) {
         for(int var6 = 0; var6 < this.a; ++var6) {
            d var7 = this.d[var5][var6];
            var7.k = true;
            var7.n = false;
         }
      }

   }

   public strictfp void b() {
      d var3;
      for(int var1 = 0; var1 < this.a; ++var1) {
         for(int var2 = 0; var2 < this.a; var3.j = var2++) {
            var3 = this.d[var1][var2];
            var3.i = var1;
         }
      }

   }

   public strictfp void a(int var1) {
      d[] var2 = new d[this.a];
      int var3;
      int var4;
      if(var1 > 0) {
         for(var3 = 0; var3 < this.a; ++var3) {
            var2[var3] = this.d[var3][0];
         }

         for(var3 = 1; var3 < this.a; ++var3) {
            for(var4 = 0; var4 < this.a; ++var4) {
               this.d[var4][var3 - 1] = this.d[var4][var3];
            }
         }

         for(var3 = 0; var3 < this.a; ++var3) {
            this.d[var3][this.a - 1] = var2[var3];
         }

         for(var3 = 0; var3 < this.a; ++var3) {
            this.d[var3][this.a - 1].k = true;
         }
      } else {
         for(var3 = 0; var3 < this.a; ++var3) {
            var2[var3] = this.d[var3][this.a - 1];
         }

         for(var3 = this.a - 2; var3 >= 0; --var3) {
            for(var4 = 0; var4 < this.a; ++var4) {
               this.d[var4][var3 + 1] = this.d[var4][var3];
            }
         }

         for(var3 = 0; var3 < this.a; ++var3) {
            this.d[var3][0] = var2[var3];
         }

         for(var3 = 0; var3 < this.a; ++var3) {
            this.d[var3][0].k = true;
         }
      }

      this.b();
   }

   public strictfp void b(int var1) {
      d[] var2 = new d[this.a];
      int var3;
      int var4;
      if(var1 > 0) {
         for(var3 = 0; var3 < this.a; ++var3) {
            var2[var3] = this.d[0][var3];
         }

         for(var3 = 1; var3 < this.a; ++var3) {
            for(var4 = 0; var4 < this.a; ++var4) {
               this.d[var3 - 1][var4] = this.d[var3][var4];
            }
         }

         for(var3 = 0; var3 < this.a; ++var3) {
            this.d[this.a - 1][var3] = var2[var3];
         }

         for(var3 = 0; var3 < this.a; ++var3) {
            this.d[this.a - 1][var3].k = true;
         }
      } else {
         for(var3 = 0; var3 < this.a; ++var3) {
            var2[var3] = this.d[this.a - 1][var3];
         }

         for(var3 = this.a - 2; var3 >= 0; --var3) {
            for(var4 = 0; var4 < this.a; ++var4) {
               this.d[var3 + 1][var4] = this.d[var3][var4];
            }
         }

         for(var3 = 0; var3 < this.a; ++var3) {
            this.d[0][var3] = var2[var3];
         }

         for(var3 = 0; var3 < this.a; ++var3) {
            this.d[0][var3].k = true;
         }
      }

      this.b();
   }

   public strictfp d a(int var1, int var2) {
      return var1 >= 0 && var1 < this.a?(var2 >= 0 && var2 < this.a?(this.d == null?null:this.d[var1][var2]):null):null;
   }

   public strictfp void a(int var1, int var2, boolean var3) {
      l var4 = l.B();
      b var5 = var4.bL;
      int var6 = var5.n;
      int var7 = var5.o;
      int var8 = var1 * var6;
      int var9 = var2 * var7;
      int var10 = var8 - this.f;
      int var11 = var9 - this.g;
      this.a(var10 - var6, var11 - var7, 3 * var6, 3 * var7, var3);
   }

   public strictfp void c() {
      if(this.d != null) {
         for(int var1 = 0; var1 < this.a; ++var1) {
            for(int var2 = 0; var2 < this.a; ++var2) {
               d var3 = this.d[var1][var2];
               var3.k = true;
            }
         }
      }

   }

   public strictfp void a(int var1, int var2, int var3, int var4, boolean var5) {
      int var6 = (int)((float)var1 * this.l);
      int var7 = (int)((float)var2 * this.l);
      d var8 = this.a(var6, var7);
      if(var8 != null) {
         if(var5) {
            var8.l = true;
         } else {
            var8.k = true;
         }

         boolean var9 = false;
         boolean var10 = false;
         if(var1 + var3 >= var8.i * this.k + this.i) {
            var9 = true;
         }

         if(var2 + var4 >= var8.j * this.k + this.i) {
            var10 = true;
         }

         d var11;
         if(var9) {
            var11 = this.a(var6 + 1, var7);
            if(var11 != null) {
               if(var5) {
                  var11.l = true;
               } else {
                  var11.k = true;
               }
            }
         }

         if(var10) {
            var11 = this.a(var6, var7 + 1);
            if(var11 != null) {
               if(var5) {
                  var11.l = true;
               } else {
                  var11.k = true;
               }
            }
         }

         if(var9 && var10) {
            var11 = this.a(var6 + 1, var7 + 1);
            if(var11 != null) {
               if(var5) {
                  var11.l = true;
               } else {
                  var11.k = true;
               }
            }
         }
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.l var1) {
      RectF var2 = var1.c();

      for(int var3 = 0; var3 < this.a; ++var3) {
         for(int var4 = 0; var4 < this.a; ++var4) {
            if(this.d != null) {
               d var5 = this.d[var3][var4];
               Rect var6 = var5.b();
               if(com.corrodinggames.rts.gameFramework.f.a(var6, var2)) {
                  boolean var7 = this.m != 1.0F;
                  if(var7) {
                     ;
                  }

                  var1.a(var5.a, var5.c(), var5.d(), this.m);
                  var5.d.p();
                  if(var7) {
                     ;
                  }
               }
            }
         }
      }

   }

   public strictfp void a(int var1, int var2, y var3) {
      d var4 = this.d[var1][var2];
      boolean var5 = this.m != 1.0F;
      if(var5) {
         ;
      }

      Rect var6 = var4.b();
      com.corrodinggames.rts.gameFramework.f.a(var6, 95.0F);
      w[] var7 = w.er.a();
      int var8 = w.er.size();

      for(int var9 = 0; var9 < var8; ++var9) {
         w var10 = var7[var9];
         if(var10 instanceof com.corrodinggames.rts.game.l) {
            com.corrodinggames.rts.game.l var11 = (com.corrodinggames.rts.game.l)var10;
            if(var6.b((int)var11.eo, (int)var11.ep)) {
               var11.a(var3, var4.c(), var4.d(), this.m);
            }
         }
      }

      if(var5) {
         ;
      }

   }

   public strictfp void b(int var1, int var2, y var3) {
      l var4 = l.B();
      b var5 = var4.bL;
      if(var4.bS.F()) {
         int var6 = this.f + var1 * this.k;
         int var7 = this.g + var2 * this.k;
         int var10 = this.i;
         int var11 = this.i;
         int var12 = var5.u.n;
         int var13 = var5.u.o;
         int var14 = (int)((float)var6 * var5.r);
         if(var14 < 0) {
            boolean var19 = false;
         }

         int var15 = (int)((float)var7 * var5.s);
         if(var15 < 0) {
            boolean var20 = false;
         }

         int var16 = (int)((float)(var6 + var10) * var5.r);
         if(var16 > var12 - 1) {
            var16 = var12 - 1;
         }

         int var17 = (int)((float)(var7 + var11) * var5.s);
         if(var17 > var13 - 1) {
            var17 = var13 - 1;
         }

         if((double)this.m < 0.4D) {
            return;
         }

         boolean var18 = this.m != 1.0F;
         if(var18) {
            var3.i();
            var3.a(this.m, this.m);
         }

         if(var18) {
            var3.j();
         }
      }

   }

   public strictfp void b(int var1, int var2) {
      d var3 = b.al.d[var1][var2];
      l var4 = l.B();
      b var5 = var4.bL;
      var3.n = true;
      this.c.b(-16777216);
      com.corrodinggames.rts.gameFramework.m.e var6 = var4.bW.J;
      if(var6 != null) {
         Rect var7 = new Rect();
         RectF var8 = new RectF();
         float var9 = (float)(this.f + var1 * this.k) / (float)(var5.n * var5.C);
         float var10 = (float)(this.g + var2 * this.k) / (float)(var5.o * var5.D);
         float var11 = (float)(this.f + (var1 + 1) * this.k) / (float)(var5.n * var5.C);
         float var12 = (float)(this.g + (var2 + 1) * this.k) / (float)(var5.o * var5.D);
         var7.a((int)(var9 * (float)var6.p), (int)(var10 * (float)var6.q), (int)(var11 * (float)var6.p), (int)(var12 * (float)var6.q));
         var8.a(0.0F, 0.0F, (float)this.h, (float)this.h);
         this.c.a(var6, var7, var8, this.e);
      }

      this.c.p();
      if(l.aW) {
         var3.a.a(0, Mode.CLEAR);
      }

      var3.a.b(this.b, 0.0F, 0.0F, (Paint)null);
      var3.d.p();
   }

   public strictfp void c(int var1, int var2) {
      this.c(var1, var2, this.c);
   }

   public strictfp void c(int var1, int var2, y var3) {
      d var4 = b.al.d[var1][var2];
      l var9 = l.B();
      b var10 = var9.bL;
      boolean var11 = false;
      if(var9.bQ.renderFancyWater) {
         var11 = true;
      }

      if(l.C() || l.D()) {
         var11 = true;
      }

      if(var11) {
         var3.a(0, Mode.CLEAR);
      } else {
         boolean var12 = false;
         if(l.C()) {
            var12 = true;
         }

         if(l.aX) {
            var12 = true;
         }

         if(com.corrodinggames.rts.gameFramework.f.g.bO) {
            ;
         }

         if(var10.E) {
            ;
         }

         if(var12) {
            var3.b(-16777216);
         }
      }

      if(l.aX) {
         var3.a(0, Mode.CLEAR);
      }

      int var22 = this.f + var1 * this.k;
      int var13 = this.g + var2 * this.k;
      boolean var14 = true;
      boolean var15 = false;
      boolean var16 = true;
      boolean var17 = false;
      boolean var18 = false;
      boolean var19 = false;
      if(!var10.u.w) {
         var18 = true;
      }

      if(var10.E) {
         var19 = true;
      }

      if(b.d) {
         var18 = false;
         var19 = false;
      }

      if(var18) {
         var3.a(true);
      }

      var10.u.a(var3, (float)var22, (float)var13, (float)var22, (float)var13, (float)this.i, (float)this.i, this.m, this.m, var10.E, false, false);
      if(var10.v != null) {
         if(var18 && var10.v.w) {
            var3.f();
            l.e("Ending blit early");
         }

         var10.v.a(var3, (float)var22, (float)var13, (float)var22, (float)var13, (float)this.i, (float)this.i, this.m, this.m, var10.E, false, false);
      }

      if(var10.w != null) {
         if(var18 && var10.w.w) {
            var3.f();
            l.e("Ending blit early");
         }

         var10.w.a(var3, (float)var22, (float)var13, (float)var22, (float)var13, (float)this.i, (float)this.i, this.m, this.m, var10.E, false, false);
      }

      Iterator var20 = var10.z.iterator();

      while(var20.hasNext()) {
         e var21 = (e)var20.next();
         if(var21.m) {
            if(var18 && var21.w) {
               var3.f();
               l.e("Ending blit early");
            }

            var21.a(var3, (float)var22, (float)var13, (float)var22, (float)var13, (float)this.i, (float)this.i, this.m, this.m, var10.E, false, false);
         }
      }

      this.a(var1, var2, var3);
      if(var10.E) {
         if(var19) {
            var3.a(false);
         }

         var10.u.a(var3, (float)var22, (float)var13, (float)var22, (float)var13, (float)this.i, (float)this.i, this.m, this.m, var10.E, true, true);
      }

      if(var18 || var19) {
         var3.f();
      }

      if(var9.bS.F()) {
         this.b(var1, var2, var3);
      }

      var4.k = false;
      var4.l = false;
      var4.m = 0;
      var4.n = false;
      var3.p();
      if(var11 || l.aW) {
         var4.a.a(0, Mode.CLEAR);
      }

      var4.a.b(this.b, 0.0F, 0.0F, (Paint)null);
      var4.d.p();
      if(b.c) {
         var4.a.a("" + var4.c, 40.0F, 40.0F, b.h);
      }

      ++var4.c;
   }

   public strictfp void d() {
      if(!l.aU || l.aX || l.aY) {
         l var1 = l.B();
         int var2 = Math.max((int)var1.cF, (int)var1.cH) + 3;
         if(this.d != null && this.h * this.a < var2 + this.h + 1) {
            l.b("map", "screen must have changed size, layerBufferSize too small at " + this.a + ", adding to LayerBitmapBuffer");
            l.b("map", "new viewpoint:" + var1.cF + ", " + var1.cH);
            this.c(this.a + 1);
         }

         if(this.d == null) {
            l.b("map", "setupLayerBuffers for size:" + var2);
            long var3 = System.nanoTime();
            if(!l.aX && !l.aY) {
               var2 = Math.max(600, var2);
               this.h = var2 / (this.a - 2) + 7 + 4;
               byte var5 = 20;
               float var6 = 1.0F / (float)var5;
               this.h = (int)((float)this.h * var6 + 0.5F) * var5;
            } else {
               this.h = 1024;
               this.a = (int)((float)var2 / (float)this.h + 1.5F);
            }

            if(this.h * this.a < var2 + this.h + 1) {
               l.b("layerBufferSize is too small");
               l.b("layerBufferCount:" + this.a);
               l.b("(layerBufferSize*(layerBufferCount):" + this.h * this.a);
               l.b("longest+layerBufferSize+1:" + (var2 + this.h + 1));
               l.b("longest:" + var2);
               if(!l.aX && !l.aY) {
                  this.h += 100;
               } else {
                  ++this.a;
               }
            }

            l.e("layerBufferSize:" + this.h);
            this.j = this.h - 4;
            l.b("layerBuffer:" + this.a + "x" + this.a + " = " + this.a * this.a + (b.I?" x2 for soft fade ":""));
            this.d = new d[this.a][this.a];
            boolean var8 = false;
            if(var1.bQ.renderFancyWater) {
               var8 = true;
            }

            if(l.C() || l.D()) {
               var8 = true;
            }

            if(this.h <= 0) {
               l.b("layerBuffer buffer size was too small at: " + this.h);
               this.h = 512;
            }

            if(var8) {
               this.b = var1.bO.a(this.h, this.h, true);
            } else {
               this.b = var1.bO.a(this.h, this.h, false);
            }

            this.b.b(true);
            this.c = var1.bO.b(this.b);
            this.f();
            long var9 = System.nanoTime();
            l.e("----- layerBuffers create in:" + (double)(var9 - var3) / 1000000.0D + " ms");
         }

      }
   }

   public strictfp void c(int var1) {
      if(var1 < this.a) {
         l.g("newLayerBufferCount:" + var1);
      } else {
         d[][] var2 = new d[var1][var1];

         for(int var3 = 0; var3 < this.a; ++var3) {
            for(int var4 = 0; var4 < this.a; ++var4) {
               var2[var3][var4] = this.d[var3][var4];
            }
         }

         this.d = var2;
         this.a = var1;
         this.f();
      }
   }

   public strictfp void e() {
      b.I = false;
      b.J = true;

      for(int var1 = 0; var1 < this.a; ++var1) {
         for(int var2 = 0; var2 < this.a; ++var2) {
            d var3 = this.d[var1][var2];
            if(var3 != null) {
               if(var3.f != null) {
                  var3.f.q();
                  var3.f = null;
               }

               if(var3.e != null) {
                  var3.e.o();
                  var3.e = null;
               }
            }
         }
      }

   }

   public strictfp void f() {
      l var1 = l.B();
      ArrayList var2 = null;
      boolean var3 = false;

      for(int var4 = 0; var4 < this.a; ++var4) {
         for(int var5 = 0; var5 < this.a; ++var5) {
            d var6 = this.d[var4][var5];
            if(var6 == null) {
               var6 = new d(this, var4, var5);
               var6.b = this.p++;
               this.d[var4][var5] = var6;
               if(this.h <= 0) {
                  l.b("initMissingLayerBufferImages: layerBuffer buffer size was too small at: " + this.h);
                  this.h = 512;
               }

               if(var3) {
                  var6.d = var1.bO.r();
               } else if(var1.bQ.renderFancyWater) {
                  var6.d = var1.bO.a(this.h, this.h, true);
               } else {
                  var6.d = var1.bO.a(this.h, this.h, false);
               }

               var6.d.b(true);
               if(var6.d.A()) {
                  if(!var3) {
                     l.b("initMissingLayerBufferImages: Failed to create map buffer at :" + this.h + "px");
                  }

                  var6.a = new z();
               } else {
                  try {
                     var6.a = var1.bO.b(var6.d);
                  } catch (OutOfMemoryError var9) {
                     if(!var3) {
                        l.a(u.b, (Throwable)var9);
                     }

                     var3 = true;
                     var6.a = new z();
                  }
               }

               if(var2 == null) {
                  var2 = new ArrayList();
               }

               var2.add(var6);
            }
         }
      }

      if(var3 && b.I) {
         this.e();
      }

      if(var2 != null) {
         Iterator var10 = var2.iterator();

         while(var10.hasNext()) {
            d var11 = (d)var10.next();
            if(b.I) {
               try {
                  var11.a();
               } catch (OutOfMemoryError var8) {
                  this.e();
                  l.b("Not enough free memory to enable smooth fog fading");
                  System.gc();
                  break;
               }
            }
         }
      }

      this.a();
   }

   public strictfp float g() {
      l var1 = l.B();
      return var1.cX > 1.0F?1.0F:var1.cX;
   }

   public strictfp void a(float var1) {
      l var2 = l.B();
      b var3 = var2.bL;
      Long var4 = null;
      boolean var5 = false;
      float var6 = this.g();
      boolean var7 = false;
      float var8 = var6 / this.m;
      if(com.corrodinggames.rts.gameFramework.f.c(var8 - 1.0F) < 0.01F) {
         var8 = 1.0F;
      }

      float var9;
      if((double)var6 > 0.6D) {
         var9 = 0.3F;
         if(l.av()) {
            var9 = 0.1F;
         }

         if(var6 - this.m > var9) {
            var7 = true;
         }

         if(var6 == 1.0F && this.m != 1.0F) {
            var7 = true;
         }
      }

      float var10;
      float var11;
      if(var8 != 1.0F) {
         int var39 = 10;
         var10 = 0.03F;
         var11 = 0.03F;
         if(var6 < 0.3F) {
            var39 = 20;
            var10 = 0.09F;
         } else if(var6 < 0.5F) {
            var39 = 20;
            var10 = 0.07F;
         }

         if(var6 > 1.3F) {
            var39 = 7;
         }

         if(!l.av()) {
            var39 += 10;
         }

         if(com.corrodinggames.rts.gameFramework.f.c(var3.aj - var6) > var11) {
            var3.aj = var2.cX;
            var3.ak = 0;
         } else {
            ++var3.ak;
         }

         if(var3.ak < 3) {
            var3.ai = 0.0F;
         } else if(com.corrodinggames.rts.gameFramework.f.c(var6 - this.m) > var10) {
            ++var3.ai;
         }

         if(var3.ai > (float)var39) {
            var3.ai = 0.0F;
            var7 = true;
         }
      }

      if((float)var2.cu + var2.cA + 4.0F > (float)(this.f + this.a * this.k)) {
         this.f += this.k;
         this.b(1);
      }

      if(var2.cu - 1 < this.f) {
         this.f -= this.k;
         this.b(-1);
      }

      if((float)var2.cv + var2.cB + 4.0F > (float)(this.g + this.a * this.k)) {
         this.g += this.k;
         this.a(1);
      }

      if(var2.cv - 1 < this.g) {
         this.g -= this.k;
         this.a(-1);
      }

      if((float)var2.cu + var2.cA + 4.0F > (float)(this.f + this.a * this.k)) {
         var7 = true;
      }

      if(var2.cu - 1 < this.f) {
         var7 = true;
      }

      if((float)var2.cv + var2.cB + 4.0F > (float)(this.g + this.a * this.k)) {
         var7 = true;
      }

      if(var2.cv - 1 < this.g) {
         var7 = true;
      }

      if(var7) {
         this.a();
      }

      var8 = var2.cX / this.m;
      if(com.corrodinggames.rts.gameFramework.f.c(var8 - 1.0F) < 1.0E-4F) {
         var8 = 1.0F;
      }

      var9 = var2.cF / var8 + 2.0F;
      var10 = var2.cH / var8 + 2.0F;
      if(var8 != 1.0F) {
         var2.bO.k();
         var2.bO.a(var8, var8);
         var3.ao.a(var2.cK);
         var3.ao.c = (int)((float)var3.ao.a + (float)var3.ao.b() / var8) + 2;
         var3.ao.d = (int)((float)var3.ao.b + (float)var3.ao.c() / var8) + 2;
         var2.bO.a(var3.ao);
      }

      var11 = ((float)this.f - var2.cw) * this.m;
      float var12 = ((float)this.g - var2.cx) * this.m;
      float var13 = (float)((int)var11);
      float var14 = (float)((int)var12);
      int var15 = 0;
      boolean var16 = false;
      if(l.av() && (double)var2.cX < 0.3D) {
         var16 = true;
      }

      this.e.a(var16);
      this.e.d(var16);
      this.e.b(var16);
      boolean var17 = false;

      try {
         for(int var18 = 0; var18 < this.a; ++var18) {
            for(int var19 = 0; var19 < this.a; ++var19) {
               d var20 = this.d[var18][var19];
               int var21 = (int)(var13 + (float)(var18 * this.k) * this.m);
               int var22 = (int)(var14 + (float)(var19 * this.k) * this.m);
               if(var20.l && !this.n) {
                  ++var20.m;
               }

               var20.p.a(var21 + 1, var22 + 1, var21 + this.h - 2, var22 + this.h - 2);
               if((float)var20.p.a <= var9 && (float)var20.p.b <= var10) {
                  if((float)var20.p.c > var9) {
                     var20.p.c = (int)var9;
                  }

                  if((float)var20.p.d > var10) {
                     var20.p.d = (int)var10;
                  }

                  int var23 = (int)((0.0F - var2.cw) * this.m);
                  int var24 = (int)((0.0F - var2.cx) * this.m);
                  int var25 = (int)((var3.i() - var2.cw) * this.m);
                  int var26 = (int)((var3.j() - var2.cx) * this.m);
                  if(var20.p.a < var23) {
                     var20.p.a = var23;
                  }

                  if(var20.p.b < var24) {
                     var20.p.b = var24;
                  }

                  if(var20.p.c > var25) {
                     var20.p.c = var25;
                  }

                  if(var20.p.d > var26) {
                     var20.p.d = var26;
                  }

                  if(!var20.p.a()) {
                     boolean var27 = false;
                     boolean var28 = true;
                     if(var20.k) {
                        var27 = true;
                        var28 = false;
                     }

                     if(var20.l) {
                        int var29 = 10;
                        if(var15 > 3) {
                           var29 += 2;
                        }

                        if(var15 > 6) {
                           var29 += 2;
                        }

                        if(var20.m > var29) {
                           var20.m = 0;
                           var27 = true;
                           ++var15;
                        }
                     }

                     if(var27) {
                        var5 = true;
                        boolean var40 = false;
                        long var30 = br.a();
                        if(var4 == null) {
                           var4 = Long.valueOf(var30);
                        } else {
                           short var32 = 200;
                           if(this.n) {
                              var32 = 30;
                           }

                           if(br.a(var4.longValue(), var30) > (double)var32) {
                              var40 = true;
                              this.n = true;
                           }
                        }

                        if(var40 && var20.k && !var20.n) {
                           this.b(var18, var19);
                        }

                        if(!var40) {
                           if(b.I) {
                              if(var20.e != null && var20.e.p != var20.d.p) {
                                 l.e("wrong sized fadeOutBitmap width:" + var20.e.p + " vs " + var20.d.p);
                                 var20.e.o();
                                 var20.e = null;
                              }

                              if(var20.e == null) {
                                 try {
                                    var20.a();
                                 } catch (OutOfMemoryError var37) {
                                    var37.printStackTrace();
                                    l.a(u.b, (Throwable)var37);
                                    this.e();
                                    l.b("Not enough free memory to keep smooth fog fading");
                                    System.gc();
                                 }

                                 if(b.I && var20.e == null) {
                                    var2.i("Disabling smooth fog fading due to error");
                                    this.e();
                                    l.b("fadeOutBitmap == null");
                                    System.gc();
                                 }
                              }
                           }

                           if(b.I) {
                              if(var20.g > 0.0F) {
                                 ;
                              }

                              com.corrodinggames.rts.gameFramework.m.e var41 = var20.d;
                              var20.d = var20.e;
                              var20.e = var41;
                              y var33 = var20.a;
                              var20.a = var20.f;
                              var20.f = var33;
                              if(var28) {
                                 var20.g = 1.0F;
                              } else {
                                 var20.g = 0.0F;
                              }
                           } else {
                              var20.g = 0.0F;
                           }

                           if(l.at() && !var17) {
                              b.a();
                              var17 = true;
                           }

                           var2.bO.i();
                           this.c(var18, var19);
                           var2.bO.j();
                           if(b.a) {
                              br.a("re-drawTile", var30);
                           }
                        }
                     }

                     var20.o.a(var20.p);
                     var20.o.a(-var21, -var22);
                     var20.q.a(var20.p);
                     var20.q.a(-var13, -var14);
                     var20.q.a(var11, var12);
                     if(var20.g > 0.0F) {
                        var20.h.a(var16);
                        var20.h.c((int)((1.0F - var20.g) * 255.0F));
                        var2.bO.a(var20.e, var20.o, var20.q, this.e);
                        if((double)var20.g < 0.98D) {
                           var2.bO.a(var20.d, var20.o, var20.q, var20.h);
                        }

                        var20.g -= 0.1F * var1;
                     } else if(var20.d.A()) {
                        var2.bO.a(var20.d, var20.q, this.e, 0.0F, 0.0F, 0, 0);
                     } else {
                        var2.bO.a(var20.d, var20.o, var20.q, this.e);
                     }
                  }
               }
            }
         }
      } finally {
         if(var17) {
            b.b();
         }

      }

      if(var8 != 1.0F) {
         var2.bO.l();
      }

      if(!var5) {
         this.n = false;
      }

   }
}
