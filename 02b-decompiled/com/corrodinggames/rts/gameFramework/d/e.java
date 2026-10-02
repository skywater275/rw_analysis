package com.corrodinggames.rts.gameFramework.d;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;
import com.corrodinggames.rts.gameFramework.d.c;
import com.corrodinggames.rts.gameFramework.d.g;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.io.IOException;

public final class e {

   private final c ay;
   public ay a;
   public w b;
   public boolean c;
   public boolean d;
   public boolean e;
   public boolean f;
   public int g;
   public static int h = 1;
   public static int i = 2;
   public static int j = 3;
   public static int k = 4;
   public static int l = 5;
   public static int m = 6;
   public static int n = 7;
   public boolean o;
   public boolean p;
   public h q;
   public boolean r;
   public boolean s;
   public float t;
   public boolean u;
   public boolean v;
   public float w;
   public int x;
   public int y;
   public float z;
   public short A;
   public LightingColorFilter B;
   public static LightingColorFilter C = null;
   public static int D = 0;
   public float E;
   public float F;
   public float G;
   public boolean H;
   public float I;
   public float J;
   public float K;
   public boolean L;
   public float M;
   public float N;
   public float O;
   public float P;
   public float Q;
   public float R;
   public float S;
   public float T;
   public float U;
   public float V;
   public float W;
   public float X;
   public float Y;
   public float Z;
   public String aa;
   public Paint ab;
   public float ac;
   public float ad;
   public boolean ae;
   public int af;
   public int ag;
   public boolean ah;
   public boolean ai;
   public float aj;
   public float ak;
   public boolean al;
   public boolean am;
   public boolean an;
   public float ao;
   public int ap;
   public int aq;
   public short ar;
   public boolean as;
   public ag at;
   public float au;
   public int av;
   public boolean aw;
   public static ag[] ax = new ag[128];


   protected e(c var1) {
      this.a = ay.defaultEffectTemplate;
      this.e = true;
      this.f = false;
      this.q = h.a;
      this.w = 1.0F;
      this.z = -1.0F;
      this.B = null;
      this.X = 0.0F;
      this.an = false;
      this.ao = 0.0F;
      this.ar = 2;
      this.as = false;
      this.at = a();
      this.ay = var1;
   }

   public static ag a() {
      return y.b();
   }

   public ag a(float var1) {
      int var2 = (int)(var1 * (float)(ax.length - 1));
      if(var2 < 0) {
         var2 = 0;
      }

      if(var2 > ax.length - 1) {
         var2 = ax.length - 1;
      }

      return ax[var2];
   }

   public void b() {
      if(this.o) {
         this.o = false;
         --this.ay.a;
         c.h = true;
         if(this.a.alsoEmitEffectsOnDeath != null && this.A < 20) {
            float var1 = this.I;
            float var2 = this.J;
            float var3 = this.K;
            if(this.b != null) {
               var1 += this.b.eo;
               var2 += this.b.ep;
               var3 += this.b.eq;
            }

            this.a.alsoEmitEffectsOnDeath.a(var1, var2, var3, this.Y, this.b, 0, this.A);
         }
      }

   }

   public void c() {
      this.a = ay.defaultEffectTemplate;
      this.q = h.a;
      this.b = null;
      this.c = false;
      this.d = false;
      this.e = true;
      this.f = false;
      this.g = 0;
      this.p = false;
      this.I = 0.0F;
      this.J = 0.0F;
      this.L = false;
      this.M = 0.0F;
      this.N = 0.0F;
      this.O = 0.0F;
      this.K = 0.0F;
      this.ar = 2;
      this.an = false;
      this.ao = 0.0F;
      this.ae = false;
      this.ak = 0.0F;
      this.aj = 0.0F;
      this.ag = 0;
      this.ah = false;
      this.ai = false;
      this.al = false;
      this.am = false;
      this.ap = 0;
      this.aq = 0;
      this.U = 0.0F;
      this.V = 15.0F;
      this.W = this.V;
      this.X = 0.0F;
      this.r = false;
      this.s = false;
      this.t = 0.0F;
      this.F = 1.0F;
      this.G = 1.0F;
      this.H = false;
      this.u = false;
      this.v = false;
      this.w = 1.0F;
      this.E = 1.0F;
      this.Y = 0.0F;
      this.Z = 0.0F;
      this.P = 0.0F;
      this.Q = 0.0F;
      this.R = 0.0F;
      this.S = 0.0F;
      this.T = 0.0F;
      this.aa = null;
      this.ab = null;
      this.ac = 0.0F;
      this.ad = 0.0F;
      this.A = 0;
      this.x = -1;
      this.B = null;
      this.y = -1;
      this.z = -1.0F;
      this.at.a((ColorFilter)null);
      this.aw = false;
      this.at.a((ae)null);
      this.as = false;
   }

   public void a(e var1) {
      this.a = var1.a;
      this.q = var1.q;
      this.g = var1.g;
      this.b = var1.b;
      this.c = var1.c;
      this.d = var1.d;
      this.e = var1.e;
      this.p = var1.p;
      this.I = var1.I;
      this.J = var1.J;
      this.L = var1.L;
      this.M = var1.M;
      this.N = var1.N;
      this.O = var1.O;
      this.K = var1.K;
      this.ar = var1.ar;
      this.an = var1.an;
      this.ao = var1.ao;
      this.ae = var1.ae;
      this.ak = var1.ak;
      this.aj = var1.aj;
      this.ag = var1.ag;
      this.ah = var1.ah;
      this.ai = var1.ai;
      this.al = var1.ah;
      this.am = var1.am;
      this.ap = var1.ap;
      this.aq = var1.aq;
      this.U = var1.U;
      this.V = var1.V;
      this.W = var1.W;
      this.X = var1.X;
      this.r = var1.r;
      this.s = var1.s;
      this.t = var1.t;
      this.F = var1.F;
      this.G = var1.G;
      this.H = var1.H;
      this.u = var1.u;
      this.v = var1.v;
      this.w = var1.w;
      this.E = var1.E;
      this.Y = var1.Y;
      this.Z = var1.Z;
      this.P = var1.P;
      this.Q = var1.Q;
      this.R = var1.R;
      this.S = var1.S;
      this.T = var1.T;
      this.aa = var1.aa;
      this.ab = var1.ab;
      this.ac = var1.ac;
      this.ad = var1.ad;
      this.A = var1.A;
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.B = var1.B;
      this.as = var1.as;
   }

   public void b(float var1) {
      this.U = com.corrodinggames.rts.gameFramework.f.a(this.U, var1);
      if(this.U <= 0.0F) {
         this.V -= var1;
         if(this.b != null && this.b.ej && !this.a.liveAfterAttachedDies) {
            this.V = -1.0F;
         }

         if(this.V < 0.0F) {
            this.b();
         } else {
            if(this.ae) {
               if(this.al) {
                  this.ak -= this.aj * var1;
               } else {
                  this.ak += this.aj * var1;
               }

               if(this.ah) {
                  if(!this.al) {
                     if(this.ak >= (float)(this.ag + 1)) {
                        this.al = true;
                        this.ak = (float)this.ag;
                     }
                  } else if(this.ak < (float)this.af) {
                     if(!this.ai) {
                        this.b();
                        return;
                     }

                     this.al = false;
                     this.ak = (float)this.af;
                  }
               } else if(this.ak >= (float)(this.ag + 1)) {
                  if(!this.ai) {
                     this.b();
                     return;
                  }

                  this.ak = (float)this.af;
               }

               this.ap = (int)this.ak;
            }

            if(this.u) {
               this.R -= this.R * 0.002F * var1;
               this.P -= var1 * 0.0015F;
            }

            if(this.v) {
               if(this.K > 0.0F) {
                  this.R -= 0.1F * this.w * var1;
               } else {
                  if(this.R < 0.0F) {
                     this.R = -this.R;
                     this.R *= 0.5F;
                     this.R = com.corrodinggames.rts.gameFramework.f.a(this.R, 1.3F);
                  }

                  if(this.K < 0.0F) {
                     this.K = 0.0F;
                  }

                  if((double)this.R < 0.2D) {
                     this.ar = 1;
                  }

                  this.P = com.corrodinggames.rts.gameFramework.f.a(this.P, 0.15F * var1);
                  this.Q = com.corrodinggames.rts.gameFramework.f.a(this.Q, 0.15F * var1);
                  this.Z = com.corrodinggames.rts.gameFramework.f.a(this.Z, 1.0F * var1);
               }
            }

            this.I += this.P * var1;
            this.J += this.Q * var1;
            this.K += this.R * var1;
            this.Y += this.Z * var1;
            if(this.a.trailEffect != null) {
               this.X += var1;
               if(this.X > this.a.trailEffectRate) {
                  this.X = 0.0F;
                  if(this.A < 20) {
                     float var2 = this.I;
                     float var3 = this.J;
                     float var4 = this.K;
                     if(this.b != null) {
                        var2 += this.b.eo;
                        var3 += this.b.ep;
                        var4 += this.b.eq;
                     }

                     this.a.trailEffect.a(var2, var3, var4, this.Y, this.b, 0, this.A);
                  }
               }
            }

         }
      }
   }

   public static void a(int var0, g var1, Rect var2) {
      int var3 = 0;
      if(var0 >= var1.h) {
         var3 += var0 / var1.h;
         var0 %= var1.h;
      }

      int var4 = var1.d + var0 * var1.f;
      int var5 = var1.e + var3 * var1.g;
      var2.a = var4;
      var2.b = var5;
      var2.c = var4 + var1.b;
      var2.d = var5 + var1.c;
   }

   public boolean a(l var1, boolean var2) {
      Rect var3 = c.o;
      RectF var4 = c.n;
      if(this.U > 0.0F) {
         return false;
      } else if(var2 && this.K < 1.0F) {
         return false;
      } else {
         g var5;
         if(this.a.imageStrip != null) {
            var5 = this.a.imageStrip;
         } else {
            var5 = c.s[this.aq];
         }

         if(!var5.k) {
            int var6 = this.ap;
            a(var6, var5, var3);
         } else {
            var3.a(0, 0, var5.i.m(), var5.i.l());
         }

         PointF var23;
         if(!var2) {
            var23 = com.corrodinggames.rts.gameFramework.f.d(this.I, this.J, this.K);
         } else {
            var23 = com.corrodinggames.rts.gameFramework.f.d(this.I, this.J, 0.0F);
         }

         boolean var7 = this.ar == 4;
         float var8 = 1.0F;
         if(this.G != 1.0F || this.F != 1.0F || this.H) {
            var8 = com.corrodinggames.rts.gameFramework.f.f(this.G, this.F, 1.0F - this.V / this.W);
            boolean var9 = this.ar != 4;
            if(this.H && var9) {
               var8 *= 1.0F / var1.cX;
               var8 *= var1.cj;
            }
         }

         var4.a(var23.a, var23.b, var23.a + (float)var3.b(), var23.b + (float)var3.c());
         if(this.an) {
            var4.a(-var4.b() / 2.0F, -var4.c() / 2.0F);
         }

         if(this.ao != 0.0F) {
            var4.a(0.0F, var4.c() * this.ao * var8);
         }

         if(this.b != null) {
            if(!var2) {
               if(!this.c) {
                  var4.a(this.b.eo, this.b.ep - this.b.eq);
               } else {
                  var4.a(this.b.eo, this.b.ep);
               }
            } else {
               var4.a(this.b.eo, this.b.ep);
            }
         }

         if((!var7 || this.L) && !com.corrodinggames.rts.gameFramework.f.a(var1.cN, var4)) {
            return false;
         } else {
            float var10;
            float var24;
            if(!this.e && !var7 && !this.f) {
               var24 = var4.d();
               var10 = var4.e();
               if(!var1.bL.a(var24, var10, var1.bs)) {
                  return false;
               }

               this.f = true;
            }

            if(!var7) {
               var4.a(-var1.cw, -var1.cx);
            }

            if(this.S != 0.0F) {
               var24 = com.corrodinggames.rts.gameFramework.f.j((this.W - this.V) / this.T * 360.0F) * this.S;
               var4.a(0.0F, var24);
            }

            var24 = this.W - this.V;
            var10 = 1.0F;
            float var11 = 1.0F;
            float var12 = 1.0F;
            float var13 = 1.0F;
            boolean var14 = false;
            if(this.x != -1) {
               var10 = (float)Color.a(this.x) * 0.003921569F;
               int var15 = Color.b(this.x);
               int var16 = Color.c(this.x);
               int var17 = Color.d(this.x);
               if(var15 != 255 || var16 != 255 || var17 != 255) {
                  var14 = true;
                  var11 = (float)var15 * 0.003921569F;
                  var12 = (float)var16 * 0.003921569F;
                  var13 = (float)var17 * 0.003921569F;
               }
            }

            float var20;
            if(this.z >= 0.0F) {
               float var25 = (float)Color.a(this.y) * 0.003921569F;
               float var27 = (float)Color.b(this.y) * 0.003921569F;
               float var29 = (float)Color.c(this.y) * 0.003921569F;
               float var18 = (float)Color.d(this.y) * 0.003921569F;
               if(this.z <= var24) {
                  var10 = var25;
                  var14 = true;
                  var11 = var27;
                  var12 = var29;
                  var13 = var18;
               } else {
                  float var19 = var24 / this.z;
                  var20 = 1.0F - var19;
                  var10 = var10 * var20 + var25 * var19;
                  var14 = true;
                  var11 = var11 * var20 + var27 * var19;
                  var12 = var12 * var20 + var29 * var19;
                  var13 = var13 * var20 + var18 * var19;
               }
            }

            if(this.r && var24 >= this.t) {
               var10 *= this.V / (this.W - this.t) * this.E;
            } else if(this.s && var24 < this.t) {
               var10 *= var24 / this.t * this.E;
            } else {
               var10 *= this.E;
            }

            if(var10 > 1.0F) {
               var10 = 1.0F;
            }

            if(var10 < 0.0F) {
               var10 = 0.0F;
            }

            boolean var26 = false;
            com.corrodinggames.rts.gameFramework.m.y var28 = var1.bO;
            if(this.Y != 0.0F) {
               if(!var26) {
                  var26 = true;
                  var28.k();
               }

               var28.a(this.Y + 90.0F, var4.d(), var4.e());
            }

            if(var8 != 1.0F) {
               if(!var26) {
                  var26 = true;
                  var28.k();
               }

               var28.a(var8, var8, var4.d(), var4.e());
            }

            if(var2) {
               var10 /= 3.0F;
               var10 = com.corrodinggames.rts.gameFramework.f.b(var10, 0.0F, 1.0F);
               var11 = 0.0F;
               var12 = 0.0F;
               var13 = 0.0F;
               var14 = true;
            }

            if(var14 && l.at() && !var2 && this.B == null) {
               int var31 = com.corrodinggames.rts.gameFramework.f.b(255, (int)(var11 * 255.0F), (int)(var12 * 255.0F), (int)(var13 * 255.0F));
               if(C != null && D == var31) {
                  this.B = C;
               } else {
                  C = new LightingColorFilter(var31, 0);
                  D = var31;
                  this.B = C;
               }
            }

            LightingColorFilter var32 = this.B;
            if(var32 != null) {
               if(!this.aw) {
                  this.at.a((ColorFilter)var32);
                  this.aw = true;
               }

               var14 = true;
            } else if(this.aw) {
               this.at.a((ColorFilter)null);
               this.aw = false;
            }

            if(this.ar == 3) {
               if(c.k == null) {
                  l.e("Loading displacement shader");

                  try {
                     c.k = new ae("assets/shaders/post_displacement.frag");
                  } catch (IOException var22) {
                     throw new RuntimeException(var22);
                  }
               }

               if(this.ay.l != null) {
                  ae var33 = c.k;
                  var33.a("screenBase", this.ay.l);
                  var33.b("screenBaseSize", this.ay.l);
                  var33.a("u_resolution", var1.cl, var1.cm);
                  var33.a("u_offsetBy", 0.12F * var1.cX);
                  this.at.a(var33);
                  var14 = true;
               }
            }

            ag var30;
            if(!var14) {
               var30 = this.a(var10);
            } else {
               var30 = this.at;
               int var34 = com.corrodinggames.rts.gameFramework.f.b(255, (int)(var11 * 255.0F), (int)(var12 * 255.0F), (int)(var13 * 255.0F));
               var20 = this.au - var10;
               if(var20 < -0.01F || var20 > 0.01F || this.av != var34) {
                  this.au = var10;
                  this.av = var34;
                  int var21 = com.corrodinggames.rts.gameFramework.f.b((int)(var10 * 255.0F), (int)(var11 * 255.0F), (int)(var12 * 255.0F), (int)(var13 * 255.0F));
                  this.at.b(var21);
               }
            }

            if(this.aa != null) {
               Object var35 = var30;
               if(this.ab != null) {
                  var35 = this.ab;
               }

               var20 = var4.d() + this.ac;
               float var36 = var4.e() + this.ad;
               var28.a(this.aa, var20, var36, (Paint)var35);
            }

            if(this.L) {
               var23 = com.corrodinggames.rts.gameFramework.f.d(this.M, this.N, this.O);
               var28.a(var4.a, var4.b, var23.a - var1.cw, var23.b - var1.cx, this.ay.w);
            } else if(var2) {
               if(var5.j != null) {
                  var28.a(var5.j, var3, var4, var30);
               }
            } else {
               var28.a(var5.i, var3, var4, var30);
            }

            if(var26) {
               var28.l();
            }

            return true;
         }
      }
   }

   static {
      for(int var0 = 0; var0 < ax.length; ++var0) {
         ax[var0] = a();
         float var1 = (float)var0 / (float)(ax.length - 1);
         ax[var0].c((int)(var1 * 255.0F));
      }

   }
}
