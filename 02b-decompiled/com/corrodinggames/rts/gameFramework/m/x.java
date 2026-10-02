package com.corrodinggames.rts.gameFramework.m;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.BitmapFactory.Options;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.util.Log;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.m.a;
import com.corrodinggames.rts.gameFramework.m.aa;
import com.corrodinggames.rts.gameFramework.m.ad;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.j;
import com.corrodinggames.rts.gameFramework.m.k;
import com.corrodinggames.rts.gameFramework.m.l;
import com.corrodinggames.rts.gameFramework.m.m;
import com.corrodinggames.rts.gameFramework.m.o;
import com.corrodinggames.rts.gameFramework.m.y;
import java.io.File;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;

public class x implements y {

   static x a;
   static e b;
   static int[] c;
   static Bitmap d;
   static IntBuffer e = IntBuffer.allocate(0);
   static IntBuffer f = IntBuffer.allocate(0);
   boolean g = false;
   private Context x;
   private l y;
   private l z;
   private a A;
   public boolean h;
   public Bitmap i;
   public e j;
   public int k;
   public int l;
   private x B;
   final Rect m = new Rect();
   final Rect n = new Rect();
   final RectF o = new RectF();
   final RectF p = new RectF();
   final Matrix q = new Matrix();
   final RectF r = new RectF();
   e s;
   static final RectF t = new RectF();
   static final RectF u = new RectF();
   com.corrodinggames.rts.gameFramework.utility.m v = new com.corrodinggames.rts.gameFramework.utility.m();
   static Rect w = new Rect();


   public y a(e var1) {
      x var2 = (x)this.b(var1);
      var2.h = true;
      return var2;
   }

   public y b(e var1) {
      if(this.B != null) {
         return this.B.b(var1);
      } else {
         x var2 = new x();
         var2.a(this.x);
         Canvas var3 = new Canvas();
         Bitmap var4 = var1.b();
         var3.a(var4);
         var2.y = new j(var3);
         var2.z = var2.y;
         var2.i = var4;
         var2.j = var1;
         var2.B = this;
         if(var1 != null) {
            var2.k = var1.m();
            var2.l = var1.l();
         }

         return var2;
      }
   }

   public boolean a() {
      return false;
   }

   public void a(Context var1) {
      this.x = var1;
   }

   public void b() {
      this.s = new ad(this.a(R$drawable.error_outmem));
   }

   private void t() {
      if(a != this) {
         a = this;
         x var1;
         if(this.B != null) {
            var1 = this.B;
         } else {
            var1 = this;
         }

         boolean var2 = var1.c();
         l var3;
         if(var2) {
            var3 = this.y;
            if(this.B != null) {
               var3 = this.B.y;
            }

            if(!this.h) {
               if(b != null) {
                  var3.a((e)null);
                  b = null;
               }

               if(this.B != null) {
                  this.y = this.z;
               }
            } else {
               if(this.B != null) {
                  this.y = var3;
               }

               if(this.j != b) {
                  this.y.a(this.j);
                  b = this.j;
               }
            }
         } else {
            if(b != null) {
               var3 = this.y;
               if(this.B != null) {
                  var3 = this.B.y;
               }

               var3.a((e)null);
               b = null;
            }

            if(this.B != null) {
               this.y = this.z;
            }
         }
      }

   }

   private void a(Paint var1, String var2) {
      this.a(var1, true, var2, (e)null);
   }

   private void b(Paint var1) {
      this.a(var1, false, (String)null, (e)null);
   }

   private void a(Paint var1, e var2) {
      this.a(var1, false, (String)null, var2);
   }

   public boolean c() {
      return this.y instanceof o?true:this.y instanceof k;
   }

   private void a(Paint var1, boolean var2, String var3, e var4) {
      this.t();
   }

   public l d() {
      return this.y;
   }

   public void a(l var1) {
      this.y = var1;
   }

   public void a(a var1) {
      this.A = var1;
   }

   public e a(int var1) {
      return this.a(var1, true);
   }

   public e a(int var1, boolean var2) {
      Options var3 = null;
      var3 = new Options();
      if(var2) {
         var3.inPreferredConfig = Bitmap$Config.d;
      } else {
         var3.inPreferredConfig = Bitmap$Config.b;
      }

      var3.inScaled = false;
      Bitmap var4 = BitmapFactory.decodeResource(this.x.e(), var1, var3);
      if(var4 == null) {
         throw new RuntimeException("Could not load image with resId:" + var1);
      } else {
         return this.a(var4);
      }
   }

   public e a(InputStream var1, boolean var2) {
      if(var1 == null) {
         throw new RuntimeException("loadImage: steam is null");
      } else {
         Options var3 = new Options();
         if(var2) {
            var3.inPreferredConfig = Bitmap$Config.d;
         } else {
            var3.inPreferredConfig = Bitmap$Config.b;
         }

         var3.inScaled = false;

         Bitmap var4;
         try {
            var4 = BitmapFactory.decodeStream(var1, (Rect)null, var3);
         } catch (OutOfMemoryError var6) {
            com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.a, (Throwable)var6);
            if(this.s == null) {
               throw new RuntimeException("outOfMemoryErrorImage==null", var6);
            }

            return this.s;
         }

         if(var4 == null) {
            com.corrodinggames.rts.gameFramework.l.g("Could not load image from steam");
            return null;
         } else {
            Log.d("RustedWarfare", "load a:" + var2 + " as " + var4.d());
            return this.a(var4);
         }
      }
   }

   public e a(Bitmap var1) {
      e var2 = new e();
      var2.a(var1);
      return var2;
   }

   public e a(int var1, int var2, boolean var3) {
      return this.b(var1, var2, var3);
   }

   public e b(int var1, int var2, boolean var3) {
      Bitmap$Config var4;
      if(var3) {
         var4 = Bitmap$Config.d;
      } else {
         var4 = Bitmap$Config.d;
      }

      Bitmap var5;
      try {
         var5 = Bitmap.a(var1, var2, var4);
         if(var5 != null && !var3 && var4 == Bitmap$Config.d && VERSION.SDK_INT >= 12) {
            var5.a(false);
         }
      } catch (OutOfMemoryError var7) {
         com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.b, (Throwable)var7);
         if(this.s == null) {
            throw new RuntimeException("outOfMemoryErrorImage==null", var7);
         }

         return this.s;
      }

      if(var5 == null) {
         OutOfMemoryError var6 = new OutOfMemoryError("createBitmap returned null, possible out of memory");
         com.corrodinggames.rts.gameFramework.l.a(com.corrodinggames.rts.gameFramework.u.b, (Throwable)var6);
         if(this.s == null) {
            throw new RuntimeException("outOfMemoryErrorImage==null", var6);
         } else {
            return this.s;
         }
      } else {
         return this.a(var5);
      }
   }

   public void e() {}

   public void a(e var1, float var2, float var3, float var4, Paint var5) {
      l var6 = this.y;
      var6.b();
      var6.a(var4 + 90.0F, var2, var3);
      this.b(var1, var2 - var1.t, var3 - var1.u, var5);
      var6.a();
   }

   public void a(e var1, Rect var2, float var3, float var4, float var5, Paint var6) {
      l var7 = this.y;
      var7.b();
      int var8 = var2.b() >> 1;
      int var9 = var2.c() >> 1;
      this.p.a(var3 - (float)var8, var4 - (float)var9, var3 + (float)var8, var4 + (float)var9);
      var7.a(var5 + 90.0F, var3, var4);
      this.a(var1, var2, this.p, var6);
      var7.a();
   }

   public void a(e var1, Rect var2, Rect var3, Paint var4) {
      Bitmap var5 = this.c(var1);
      if(var5 != null) {
         this.y.a(var1, var2, var3, var4);
      } else {
         throw new RuntimeException("bitmap was not drawn");
      }
   }

   public Bitmap c(e var1) {
      Bitmap var2 = var1.b();
      if(var1.f != var1.e) {
         var1.f = var1.e;
         this.d().a(var2);
      }

      return var2;
   }

   public void b(e var1, Rect var2, Rect var3, Paint var4) {
      if(!this.g) {
         this.a(var1, var2, var3, var4);
      } else {
         Bitmap var5 = this.c(var1);
         boolean var6 = var1.f();
         if(var4.f() < 255) {
            var6 = true;
         }

         int var7 = this.i.b();
         int var8 = this.i.c();
         int var9 = var5.b();
         int var10 = var5.c();
         var1.i();
         int[] var11 = var1.j;
         if(d != this.i) {
            this.a(false);
         }

         int[] var12 = c;
         int var13 = var3.b;
         int var14 = var3.d;
         int var15 = var3.a;
         int var16 = var3.c;
         int var17 = var2.b;
         int var18 = var2.d;
         int var19 = var2.a;
         int var20 = var2.c;
         float var21 = 1.0F;
         float var22 = 1.0F;
         int var23 = var20 - var19;
         int var24 = var18 - var17;
         int var25 = var16 - var15;
         int var26 = var14 - var13;
         if(var26 != 0) {
            var21 = (float)var24 / (float)var26;
         }

         if(var25 != 0) {
            var22 = (float)var23 / (float)var25;
         }

         if(var13 < 0) {
            var17 = (int)((float)var17 + (float)(-var13) * var21);
            var13 = 0;
         }

         if(var14 > var8 - 1) {
            var14 = var8 - 1;
         }

         if(var13 <= var14) {
            if(var15 < 0) {
               var19 = (int)((float)var19 + (float)(-var15) * var22);
               var15 = 0;
            }

            if(var16 > var7 - 1) {
               var16 = var7 - 1;
            }

            if(var15 <= var16) {
               var23 = var13 * var7;
               var24 = var17 * var9;
               var25 = var14 * var7;
               float var48 = 0.0F;
               int var27 = var15;
               int var28 = var19;
               int var29 = var16;
               int var31;
               int var32;
               int var33;
               int var35;
               int var37;
               if(!var6) {
                  while(var23 < var25) {
                     var31 = var23 + var27;
                     var32 = var24 + (int)var48 * var9;
                     var33 = var32 + var28;
                     float var34 = 0.0F;
                     var35 = var23 + var29;

                     for(int var36 = var35 - 4; var31 < var36; ++var31) {
                        var37 = var33 + (int)var34;
                        var12[var31] = var11[var37];
                        var34 += var22;
                        ++var31;
                        var37 = var33 + (int)var34;
                        var12[var31] = var11[var37];
                        var34 += var22;
                        ++var31;
                        var37 = var33 + (int)var34;
                        var12[var31] = var11[var37];
                        var34 += var22;
                        ++var31;
                        var37 = var33 + (int)var34;
                        var12[var31] = var11[var37];
                        var34 += var22;
                     }

                     while(var31 < var35) {
                        var37 = var33 + (int)var34;
                        var12[var31] = var11[var37];
                        ++var31;
                        var34 += var22;
                     }

                     var23 += var7;
                     var48 += var21;
                  }
               } else {
                  int var30 = var4.e();
                  var31 = var30 >>> 24;

                  for(var32 = -16777216; var23 < var25; var48 += var21) {
                     var33 = var23 + var27;
                     int var49 = var24 + (int)var48 * var9;
                     var35 = var49 + var28;
                     float var50 = 0.0F;
                     var37 = var23 + var29;
                     int var38 = -1;
                     boolean var39 = false;
                     int var40 = 0;
                     int var41 = 0;
                     int var42 = 0;

                     while(var33 < var37) {
                        int var43 = var35 + (int)var50;
                        if(var43 != var38) {
                           int var51 = var11[var43];
                           var40 = (var51 >>> 24) * var31 >> 8;
                           var41 = var51 & 16711935;
                           var42 = var51 & '\uff00';
                           if(var40 == 0) {
                              ++var33;
                              var50 += var22;
                              continue;
                           }

                           var38 = var43;
                        }

                        int var44 = var12[var33];
                        int var45 = var44 & 16711935;
                        int var46 = var44 & '\uff00';
                        var45 += (var41 - var45) * var40 >> 8;
                        var46 += (var42 - var46) * var40 >> 8;
                        int var47 = -16777216 | var45 & 16711935 | var46 & '\uff00';
                        var12[var33] = var47;
                        ++var33;
                        var50 += var22;
                     }

                     var23 += var7;
                  }
               }

            }
         }
      }
   }

   public void a(Rect var1, Paint var2) {
      if(!this.g) {
         this.b(var1, var2);
      } else {
         if(d != this.i) {
            this.a(false);
         }

         int var3 = this.i.b();
         int var4 = this.i.c();
         int[] var5 = c;
         int var6 = var1.b;
         int var7 = var1.d;
         int var8 = var1.a;
         int var9 = var1.c;
         if(var6 < 0) {
            var6 = 0;
         }

         if(var7 > var4 - 1) {
            var7 = var4 - 1;
         }

         if(var6 <= var7) {
            if(var8 < 0) {
               var8 = 0;
            }

            if(var9 > var3 - 1) {
               var9 = var3 - 1;
            }

            if(var8 <= var9) {
               int var10 = var6 * var3;
               int var11 = var7 * var3;
               int var12 = var8;
               int var13 = var9;
               int var14 = var2.e();
               int var15 = var14 >>> 24;
               float var16 = 0.003921569F;
               boolean var17 = var15 < 255;
               int var18;
               int var19;
               if(!var17) {
                  while(var10 < var11) {
                     var18 = var10 + var12;

                     for(var19 = var10 + var13; var18 < var19; ++var18) {
                        var5[var18] = var14;
                     }

                     var10 += var3;
                  }
               } else {
                  var18 = var14 >> 16 & 255;
                  var19 = var14 >> 8 & 255;
                  int var20 = var14 & 255;
                  float var21 = (float)var15 * 0.003921569F;
                  var18 = (int)((float)var18 * var21);
                  var19 = (int)((float)var19 * var21);
                  var20 = (int)((float)var20 * var21);
                  int var22 = 255 - var15;
                  int var23 = -16777216;

                  for(int var24 = -16777216 | var18 << 16 | var19 << 8 | var20; var10 < var11; var10 += var3) {
                     int var25 = var10 + var12;

                     for(int var26 = var10 + var13; var25 < var26; ++var25) {
                        int var27 = var5[var25];
                        int var28 = var27 & 16711935;
                        int var29 = var27 & '\uff00';
                        var28 = var28 * var22 >> 8;
                        var29 = var29 * var22 >> 8;
                        int var30 = (var28 & 16711935 | var29 & '\uff00') + var24;
                        var5[var25] = var30;
                     }
                  }
               }

            }
         }
      }
   }

   public void a(boolean var1) {
      this.g = true;
      if(d != this.i) {
         int var2 = this.i.b();
         int var3 = this.i.c();
         int var4 = var2 * var3;
         if(c == null || c.length < var4) {
            c = new int[var4];
         }

         if(!var1) {
            this.i.a(c, 0, var2, 0, 0, var2, var3);
         }

         d = this.i;
      }
   }

   public void f() {
      this.g = false;
      if(d == this.i) {
         int var1 = this.i.b();
         int var2 = this.i.c();
         this.i.b(c, 0, var1, 0, 0, var1, var2);
         d = null;
      }

   }

   public void a(e var1, Rect var2, RectF var3, Paint var4) {
      Bitmap var5 = this.c(var1);
      if(var5 != null) {
         this.a(var4, var1);
         this.y.a(var1, var2, var3, var4);
      } else {
         throw new RuntimeException("bitmap was not drawn");
      }
   }

   public void a(e var1, float var2, float var3, Paint var4) {
      this.b(var1, var2 - var1.t, var3 - var1.u, var4);
   }

   public void a(e var1, float var2, float var3, Paint var4, float var5, float var6) {
      l var7 = this.y;
      var7.b();
      if(var5 != 0.0F) {
         var7.a(var5 + 90.0F, var2, var3);
      }

      var7.a(var6, var6, var2, var3);
      this.c(var1);
      var7.a(var1, var2, var3, var4);
      var7.a();
   }

   public void b(e var1, float var2, float var3, Paint var4) {
      Bitmap var5 = this.c(var1);
      if(var5 != null) {
         this.y.a(var1, var2, var3, var4);
      } else {
         throw new RuntimeException("bitmap was not drawn");
      }
   }

   public void a(e var1, Rect var2, Paint var3) {
      this.a(var1, var2, var3, 0, 0, 0, 0);
   }

   public void a(e var1, Rect var2, Paint var3, int var4, int var5, int var6, int var7) {
      aa.a(this, var1, var2, var3, var4, var5, var6, var7);
   }

   public void a(e var1, RectF var2, Paint var3, float var4, float var5, int var6, int var7) {
      aa.a(this, var1, var2, var3, var4, var5, var6, var7);
   }

   public void b(int var1) {
      this.t();
      this.y.a(var1);
   }

   public void a(int var1, Mode var2) {
      this.t();
      this.y.a(var1, var2);
   }

   public void a(String var1, float var2, float var3, Paint var4, Paint var5, float var6) {
      float var7 = var4.a(var1);
      u.a(var2, var3, var2 + var7, var3 + (float)this.a(var1, var4));
      t.a(u);
      if(var4.j() == Paint$Align.b) {
         t.a(-(var7 / 2.0F), 0.0F);
      }

      com.corrodinggames.rts.gameFramework.f.a(t, var6);
      this.b(var5);
      this.y.a(t, var5);
      this.a(var1, u.a + var6 / 2.0F, u.d - var6 / 2.0F, var4);
   }

   public void a(String var1, float var2, float var3, Paint var4) {
      this.a(var4, var1);
      this.y.a(var1, var2, var3, var4);
   }

   public void b(Rect var1, Paint var2) {
      this.b(var2);
      this.y.a(var1, var2);
   }

   public void a(RectF var1, Paint var2) {
      this.b(var2);
      this.y.a(var1, var2);
   }

   public void g() {
      if(this.v.size() > 0) {
         com.corrodinggames.rts.gameFramework.utility.m var1 = this.v;
         synchronized(this.v) {
            Iterator var2 = this.v.iterator();

            while(var2.hasNext()) {
               ae var3 = (ae)var2.next();
               this.y.a(var3);
            }

            this.v.clear();
         }
      }

   }

   public void h() {
      this.t();
   }

   public void c(Rect var1, Paint var2) {
      this.n.a(var1.a, var1.b, var1.a + var1.c, var1.b + var1.d);
      this.b(var2);
      this.d().a(this.n, var2);
   }

   public void a(Rect var1) {
      if(this.i != null) {
         var1 = new Rect(var1);
         int var2 = this.i.c() - this.l;
      }

      this.d().a(var1);
   }

   public void a(RectF var1) {
      if(this.i != null) {
         var1 = new RectF(var1);
         int var2 = this.i.c() - this.l;
      }

      this.d().a(var1);
   }

   public void a(float var1, float var2, float var3, Paint var4) {
      if(var3 < 50.0F) {
         float var5 = 1.0F;
         aa.a(this, var1, var2, var3, var4, var5);
      } else {
         this.b(var4);
         this.y.a(var1, var2, var3, var4);
      }

   }

   public void b(float var1, float var2, float var3, Paint var4) {
      this.b(var4);
      this.y.a(var1, var2, var3, var4);
   }

   public void a(float[] var1, int var2, int var3, Paint var4) {
      this.b(var4);
      this.d().a(var1, var2, var3, var4);
   }

   public void i() {
      this.t();
      this.y.b();
   }

   public void j() {
      this.t();
      this.y.a();
   }

   public void k() {
      this.t();
      this.y.b();
   }

   public void l() {
      this.t();
      this.y.a();
   }

   public void a(float var1, float var2, float var3) {
      this.y.a(var1, var2, var3);
   }

   public void a(float var1, float var2) {
      this.y.a(var1, var2);
   }

   public void a(float var1, float var2, float var3, float var4) {
      this.y.a(var1, var2, var3, var4);
   }

   public void b(float var1, float var2) {
      this.y.b(var1, var2);
   }

   public void a(float var1, float var2, float var3, float var4, Paint var5) {
      this.b(var5);
      this.d().a(var1, var2, var3, var4, var5);
   }

   public void a(m var1) {
      this.d().a(var1);
   }

   public int m() {
      if(this.i != null) {
         return this.k;
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         return (int)var1.cl;
      }
   }

   public int n() {
      if(this.i != null) {
         return this.l;
      } else {
         com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
         return (int)var1.cm;
      }
   }

   public void a(int var1, int var2) {
      this.k = var1;
      this.l = var2;
   }

   public void o() {
      this.b((Paint)null);
      this.a(0, Mode.CLEAR);
   }

   public void a(Paint var1) {}

   public void a(ae var1) {
      if(var1 != null) {
         com.corrodinggames.rts.gameFramework.utility.m var2 = this.v;
         synchronized(this.v) {
            this.v.add(var1);
         }
      }

   }

   public void p() {
      this.t();
      if(this.i != null && this.y instanceof j) {
         this.d().a(this.i);
      }

   }

   public void q() {}

   public int a(String var1, Paint var2) {
      return (int)var2.k();
   }

   public int b(String var1, Paint var2) {
      var2.a(var1, 0, var1.length(), w);
      return w.a + w.b();
   }

   public e r() {
      return this.s;
   }

   public void a(e var1, File var2) {
      throw new RuntimeException("writeImageToFile not yet supported");
   }

   public void a(Lock var1) {
      this.t();
      this.y.a(var1);
   }

   public void b(Lock var1) {
      this.t();
      this.y.b(var1);
   }

   public float s() {
      return 1.0F;
   }

}
