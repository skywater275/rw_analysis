package com.corrodinggames.rts.game.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.b;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.a.e;
import com.corrodinggames.rts.game.a.f;
import com.corrodinggames.rts.game.a.j;
import com.corrodinggames.rts.game.a.k;
import com.corrodinggames.rts.game.a.l;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.utility.u;
import java.util.ArrayList;
import java.util.Iterator;

public class i extends o {

   float a;
   j b;
   k c;
   float d = -1.0F;
   float e;
   float f;
   float g = 100.0F;
   int h;
   float i = 50.0F;
   float j = 50.0F;
   float k;
   float l;
   float m;
   boolean n;
   boolean o;
   com.corrodinggames.rts.gameFramework.utility.m p = new com.corrodinggames.rts.gameFramework.utility.m();
   u q = new u();
   u r = new u();
   boolean s;
   boolean t;
   float u;
   float v = 0.0F;
   PointF w = new PointF();
   PointF x = new PointF();
   int y;
   com.corrodinggames.rts.game.units.as z;
   com.corrodinggames.rts.game.units.custom.d.b A;
   com.corrodinggames.rts.game.units.custom.d.b B;
   int C;
   int D;
   String E;
   int F;
   int G;
   boolean H = false;
   int I;
   int J;
   int K;
   int L;
   boolean M;
   ArrayList N = new ArrayList();
   com.corrodinggames.rts.game.units.as O;
   com.corrodinggames.rts.game.units.custom.d.b P;


   public void a(as var1) {
      var1.a((Enum)this.b);
      var1.a((Enum)this.c);
      var1.a(this.d);
      var1.a(this.e);
      var1.a(this.f);
      var1.a(this.g);
      var1.a(this.i);
      var1.a(this.j);
      var1.a(this.k);
      var1.a(this.l);
      var1.c(4);
      var1.a(this.v);
      var1.a(this.m);
      var1.a(this.n);
      var1.a(this.o);
      var1.a(this.h);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.b = (j)var1.b(j.class);
      this.c = (k)var1.b(k.class);
      this.d = var1.g();
      this.e = var1.g();
      this.f = var1.g();
      this.g = var1.g();
      this.i = var1.g();
      this.j = var1.g();
      this.k = var1.g();
      this.l = var1.g();
      byte var2 = var1.d();
      if(var2 >= 1) {
         this.v = var1.g();
      }

      if(var2 >= 2) {
         this.m = var1.g();
      }

      if(var2 >= 3) {
         this.n = var1.e();
         this.o = var1.e();
      }

      if(var2 >= 4) {
         this.h = var1.f();
      }

      super.a(var1);
   }

   public i(a var1, float var2, float var3) {
      super(var1, var2, var3);
   }

   public PointF a() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      PointF var2 = null;
      int var3 = (int)(this.U * var1.bL.r);
      var1.bL.a(this.S, this.T);
      int var4 = var1.bL.T;
      int var5 = var1.bL.U;

      for(int var6 = var4 - var3; var6 <= var4 + var3; ++var6) {
         for(int var7 = var5 - var3; var7 <= var5 + var3; ++var7) {
            if(var1.bL.c(var6, var7)) {
               com.corrodinggames.rts.game.b.g var8 = var1.bL.e(var6, var7);
               if(var8 != null && var8.i) {
                  am var9 = com.corrodinggames.rts.game.units.d.d.b(var6, var7);
                  boolean var10 = false;
                  if(var9 == null) {
                     var10 = true;
                  }

                  if(var9 != null && var9 instanceof y) {
                     y var11 = (y)var9;
                     if(!var11.r().p()) {
                        var10 = true;
                     }
                  }

                  if(var10) {
                     var1.bL.a(var6, var7);
                     if(var2 == null || com.corrodinggames.rts.gameFramework.f.a(0, 100) < 50) {
                        this.w.a((float)(var1.bL.T + var1.bL.p), (float)(var1.bL.U + var1.bL.q));
                        var2 = this.w;
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   public void a(y var1) {
      this.q.remove(var1);
   }

   public void b() {
      this.p.clear();
      this.q.clear();
      am[] var1 = am.bE.a();
      int var2 = 0;

      for(int var3 = am.bE.size(); var2 < var3; ++var2) {
         am var4 = var1[var2];
         if(var4.bX == this.R && !var4.bV && !var4.u() && this.a(var4)) {
            this.q.a(var4);
            com.corrodinggames.rts.game.units.as var5 = var4.dz;
            if(!this.p.contains(var5)) {
               this.p.add(var5);
            }
         }
      }

   }

   public boolean a(com.corrodinggames.rts.game.units.as var1) {
      return this.a(var1, false, true) != null;
   }

   public boolean b(com.corrodinggames.rts.game.units.as var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if((!var2.ar || var1.C()) && !var1.w()) {
         Object[] var3 = this.p.a();
         int var4 = 0;
         int var5 = this.p.size();

         while(var4 < var5) {
            com.corrodinggames.rts.game.units.as var6 = (com.corrodinggames.rts.game.units.as)var3[var4];
            am[] var7 = this.q.a();
            int var8 = 0;
            int var9 = this.q.size();

            while(true) {
               if(var8 < var9) {
                  am var10 = var7[var8];
                  if(var10.r() != var6 || !(var10 instanceof y)) {
                     ++var8;
                     continue;
                  }

                  y var11 = (y)var10;
                  if(var11.b(var1, true)) {
                     return true;
                  }
               }

               ++var4;
               break;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public com.corrodinggames.rts.game.units.as c() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.R.aY) {
         return null;
      } else {
         int var2 = this.a(this.R.bC);
         com.corrodinggames.rts.game.units.as var3 = null;
         float var4 = -1.0F;
         Iterator var5 = ar.ae.iterator();

         while(var5.hasNext()) {
            com.corrodinggames.rts.game.units.as var6 = (com.corrodinggames.rts.game.units.as)var5.next();
            if(var6.j() && this.b(var6)) {
               int var7 = this.R.a(var6, b.a);
               int var8 = this.c(var6);
               boolean var9 = false;
               if(var6 instanceof com.corrodinggames.rts.game.units.custom.l) {
                  var9 = true;
                  com.corrodinggames.rts.game.units.custom.l var10 = (com.corrodinggames.rts.game.units.custom.l)var6;
                  com.corrodinggames.rts.game.units.as var12;
                  if(var10.fL.size() != 0) {
                     for(Iterator var11 = var10.fL.iterator(); var11.hasNext(); var8 += this.c(var12)) {
                        var12 = (com.corrodinggames.rts.game.units.as)var11.next();
                        var7 += this.R.a(var12, b.a);
                     }
                  }
               }

               float var13 = -2.0F;
               if(var6.p() && !var9 && this.a() != null && com.corrodinggames.rts.gameFramework.f.a(0, 100) < 90) {
                  if(var8 == 0) {
                     if(this.R.o < 5000.0D) {
                        var13 = 0.98F;
                     } else {
                        var13 = 0.58F;
                     }
                  }

                  if(var8 == 1) {
                     var13 = 0.55F;
                  }

                  if(var8 == 2) {
                     var13 = 0.4F;
                  }

                  if(var8 >= 3) {
                     var13 = 0.25F / (float)var8;
                  }

                  if(var7 >= 3) {
                     var13 = (float)((double)var13 * 0.6D);
                  }
               }

               if(var6 == ar.b && (var7 < 5 || var2 == 0)) {
                  if(var7 == 0) {
                     var13 = 0.8F;
                  } else if(var8 < 2) {
                     var13 = 0.46F / (float)(var7 + var8 * 2);
                  }
               }

               if(var6 == ar.d && this.R.ah() && (var7 < 5 || var2 == 0)) {
                  if(var7 == 0) {
                     var13 = 0.3F;
                  } else if(var8 < 1) {
                     var13 = 0.1F / (float)(var7 + var8 * 2);
                  }
               }

               if(var6 == ar.c && (var7 < 5 || var2 == 0)) {
                  if(var7 == 0) {
                     var13 = 0.48F;
                  } else if(var8 < 2) {
                     var13 = 0.29F / (float)(var7 + var8);
                  }
               }

               if(var6 == ar.f) {
                  if(var8 == 0) {
                     var13 = 0.47F;
                  } else if(var8 < 3) {
                     var13 = 0.35F / (float)var8;
                  } else if(var8 < 4) {
                     var13 = 0.025F / (float)var8;
                  }
               }

               if(var6 == ar.y && var8 == 0) {
                  var13 = 0.018F;
               }

               if(var6 == ar.B && var8 == 0) {
                  var13 = 0.02F;
               }

               if(var6 == ar.g) {
                  if(var8 == 0) {
                     var13 = 0.42F;
                  } else if(this.R.ac()) {
                     if(var8 < 4) {
                        var13 = 0.3F / (float)var8;
                     }
                  } else if(var8 < 3) {
                     var13 = 0.3F / (float)var8;
                  } else if(var8 < 4) {
                     var13 = 0.02F / (float)var8;
                  }
               }

               if(var6 == ar.J && this.R.o > 2000.0D && var8 < 5) {
                  if(var7 == 0) {
                     var13 = 0.11F;
                  } else {
                     var13 = 0.07F / (0.2F * (float)var7 + (float)var8);
                  }
               }

               if(var6 == ar.D && (!var1.O() || !var1.bX.ay.i) && this.R.o > 2200.0D && var7 < 4) {
                  if(var7 == 0) {
                     var13 = 0.06F;
                  } else if(var8 < 1) {
                     var13 = 0.05F / (float)(var7 + var8 * 2);
                  }
               }

               if(var9) {
                  com.corrodinggames.rts.game.units.custom.l var14 = (com.corrodinggames.rts.game.units.custom.l)var6;
                  if(!var14.fw && (var7 < var14.fx || var14.fx == -1) && (var8 < var14.fy || var14.fy == -1)) {
                     var13 = var14.fz;
                     if(var8 < var14.fA) {
                        var13 = var14.fB;
                     }

                     if(var8 == 0) {
                        var13 += var14.fC;
                     }

                     if(var7 == 0) {
                        var13 += var14.fD;
                     }

                     if(var6.p() && this.a() == null) {
                        var13 = -2.0F;
                     }
                  }
               }

               if(this.R.ad() && var6 == ar.G && this.R.o > 15000.0D) {
                  if(var7 == 0) {
                     var13 = 0.04F;
                  }

                  if(this.R.o > 55000.0D && var7 == 1) {
                     var13 = 0.03F;
                  }
               }

               if(var13 >= 0.0F && (var13 > var4 || (double)com.corrodinggames.rts.gameFramework.f.c(0.0F, 1.0F) < 0.01D)) {
                  var4 = var13;
                  var3 = var6;
               }
            }
         }

         this.f = var4;
         return var3;
      }
   }

   public int a(d var1) {
      int var2 = 0;

      e var4;
      for(Iterator var3 = var1.c.iterator(); var3.hasNext(); var2 += this.c(var4.a)) {
         var4 = (e)var3.next();
      }

      return var2;
   }

   public int c(com.corrodinggames.rts.game.units.as var1) {
      int var2 = 0;
      u var3 = this.q;
      am[] var4 = var3.a();
      int var5 = 0;

      for(int var6 = var3.size(); var5 < var6; ++var5) {
         am var7 = var4[var5];
         if(var7.bX == this.R && var7.dz == var1 && this.a(var7)) {
            ++var2;
         }
      }

      return var2;
   }

   public int d() {
      int var1 = 0;
      u var2 = this.q;
      am[] var3 = var2.a();
      int var4 = 0;

      for(int var5 = var2.size(); var4 < var5; ++var4) {
         am var6 = var3[var4];
         if(var6.bX == this.R && var6 instanceof y) {
            y var7 = (y)var6;
            if(this.a(var7, false) && !var7.bM && var7.aB == null && this.R.h(var7) && this.R.i(var7)) {
               ++var1;
            }
         }
      }

      return var1;
   }

   public int e() {
      return this.K;
   }

   public boolean f() {
      int var1 = this.h();
      return var1 != 0;
   }

   public am g() {
      float var1 = this.U + 120.0F;
      am[] var2 = am.bE.a();
      int var3 = 0;

      for(int var4 = am.bE.size(); var3 < var4; ++var3) {
         am var5 = var2[var3];
         if(var5.eo + var1 > this.S && var5.eo - var1 < this.S && var5.ep + var1 > this.T && var5.ep - var1 < this.T && var5.bX != this.R && this.a(var5, 120.0F) && var5.bX.c((com.corrodinggames.rts.game.n)this.R) && this.R.j(var5)) {
            return var5;
         }
      }

      return null;
   }

   public int h() {
      return this.a(60.0F);
   }

   public int a(float var1) {
      int var2 = 0;
      float var3 = this.U + var1;
      am[] var4 = am.bE.a();
      int var5 = 0;

      for(int var6 = am.bE.size(); var5 < var6; ++var5) {
         am var7 = var4[var5];
         if(var7.eo + var3 > this.S && var7.eo - var3 < this.S && var7.ep + var3 > this.T && var7.ep - var3 < this.T && var7.bX != this.R && this.a(var7, var1) && var7.bX.c((com.corrodinggames.rts.game.n)this.R) && var7.l() && this.R.j(var7)) {
            ++var2;
         }
      }

      return var2;
   }

   public void i() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      am var2 = this.g();
      if(var2 != null) {
         com.corrodinggames.rts.gameFramework.e var3 = var1.cf.a((com.corrodinggames.rts.game.n)this.R);
         am[] var4 = am.bE.a();
         int var5 = 0;

         for(int var6 = am.bE.size(); var5 < var6; ++var5) {
            am var7 = var4[var5];
            if(var7 instanceof y) {
               y var8 = (y)var7;
               if(var7.bX == this.R && this.R.b(var7, var2) && this.R.i(var8) && var8.aq()) {
                  if(!var7.bM) {
                     if(a.a(var7, this.S, this.T, 800.0F)) {
                        var3.a(var8);
                     }
                  } else if(a.a(var7, this.S, this.T, 540.0F)) {
                     var3.a(var8);
                  }
               }
            }
         }

         var3.a(var2);
      }

   }

   public boolean a(am var1) {
      return this.a(var1, false);
   }

   public boolean a(am var1, boolean var2) {
      return var1 instanceof y && ((y)var1).aC == this && (!var2 || this.b(var1));
   }

   public boolean a(y var1, boolean var2) {
      return var1.aC == this && (!var2 || this.b(var1));
   }

   public int j() {
      int var1 = 0;
      Iterator var2 = this.k().iterator();

      while(var2.hasNext()) {
         am var3 = (am)var2.next();
         if(this.R != var3.bX && var3.bX.c((com.corrodinggames.rts.game.n)this.R) && var3 instanceof y && this.b(var3)) {
            ++var1;
         }
      }

      return var1;
   }

   public com.corrodinggames.rts.game.units.f.f k() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return var1.cc.b(this.S, this.T, this.U);
   }

   private y x() {
      return this.a((com.corrodinggames.rts.game.units.as)null, (PointF)null, true);
   }

   private y y() {
      return this.f((com.corrodinggames.rts.game.units.as)null);
   }

   private y f(com.corrodinggames.rts.game.units.as var1) {
      return this.a(var1, (PointF)null, false);
   }

   private y a(com.corrodinggames.rts.game.units.as var1, PointF var2, boolean var3) {
      if(this.K == 0) {
         return null;
      } else {
         this.y = 0;
         float var4 = Float.MAX_VALUE;
         y var5 = null;
         com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
         if(var1 != null && (var6.ar && !var1.C() || var1.w())) {
            return null;
         } else {
            am[] var7 = am.bE.a();
            int var8 = 0;

            for(int var9 = am.bE.size(); var8 < var9; ++var8) {
               am var10 = var7[var8];
               if(var10.bX == this.R && this.a(var10) && var10.cN == null && var10.aj() && var10 instanceof y && this.R.i(var10)) {
                  y var11 = (y)var10;
                  boolean var12 = f.a(var11);
                  if(var12 && (!var3 || var11.I())) {
                     ++this.y;
                     if(var1 == null || var11.b(var1, true)) {
                        boolean var13 = false;
                        float var14 = -1.0F;
                        if(var2 != null) {
                           var14 = com.corrodinggames.rts.gameFramework.f.a(var2.a, var2.b, var10.eo, var10.ep);
                        }

                        if(var5 == null) {
                           var13 = true;
                        } else {
                           if(var2 != null && var14 < var4) {
                              var13 = true;
                           }

                           if((double)com.corrodinggames.rts.gameFramework.f.c(0.0F, 1.0F) < 0.2D) {
                              var13 = true;
                           }
                        }

                        if(var13) {
                           var5 = (y)var10;
                           if(var2 != null) {
                              var4 = var14;
                           }
                        }
                     }
                  }
               }
            }

            return var5;
         }
      }
   }

   private y a(am var1, PointF var2, boolean var3) {
      if(this.L == 0) {
         return null;
      } else {
         float var4 = Float.MAX_VALUE;
         y var5 = null;
         com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
         am[] var7 = this.q.a();
         int var8 = 0;

         for(int var9 = this.q.size(); var8 < var9; ++var8) {
            am var10 = var7[var8];
            if(var10.bX == this.R && this.a(var10) && var10.cN == null) {
               com.corrodinggames.rts.game.units.as var11 = var10.r();
               if(var11.n() && var10 instanceof y && this.R.i(var10)) {
                  y var12 = (y)var10;
                  boolean var13 = f.b(var12);
                  if(var13 && (!var3 || var12.I()) && (var1 == null || var12.h(var1, true))) {
                     boolean var14 = false;
                     if(var11 instanceof com.corrodinggames.rts.game.units.custom.l) {
                        com.corrodinggames.rts.game.units.custom.l var15 = (com.corrodinggames.rts.game.units.custom.l)var11;
                        if(var15.fH != null && !this.a(var15.fH)) {
                           continue;
                        }
                     }

                     float var16 = -1.0F;
                     if(var2 != null) {
                        var16 = com.corrodinggames.rts.gameFramework.f.a(var2.a, var2.b, var10.eo, var10.ep);
                     }

                     if(var5 == null) {
                        var14 = true;
                     } else {
                        if(var2 != null && var16 < var4) {
                           var14 = true;
                        }

                        if((double)com.corrodinggames.rts.gameFramework.f.c(0.0F, 1.0F) < 0.2D) {
                           var14 = true;
                        }
                     }

                     if(var14) {
                        var5 = (y)var10;
                        if(var2 != null) {
                           var4 = var16;
                        }
                     }
                  }
               }
            }
         }

         return var5;
      }
   }

   private boolean g(com.corrodinggames.rts.game.units.as var1) {
      this.z = var1;
      this.A = null;
      this.B = null;
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      PointF var3;
      if(var1.p()) {
         var3 = this.a();
      } else {
         var3 = this.e(var1);
      }

      if(var3 == null) {
         return false;
      } else {
         y var4 = this.a(var1, var3, false);
         if(var4 == null) {
            return false;
         } else {
            if(var1 == ar.d) {
               int var10000 = com.corrodinggames.rts.gameFramework.utility.y.c(var3.a, var3.b, ao.e);
               this.R.getClass();
               if(var10000 < 3000) {
                  return false;
               }
            }

            int var5 = 1;
            s var6 = var4.a(var1, true);
            if(var6 != null) {
               var5 = var6.t();
            } else {
               com.corrodinggames.rts.gameFramework.l.b("buildBuilding: could not find getBuildUnitAction for builder this shouldn\'t happen:" + var1.i());
            }

            if(var6.b((am)var4) && var6.a(var4, false)) {
               com.corrodinggames.rts.gameFramework.e var7;
               if(var6.A()) {
                  var7 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
                  var7.a(var4);
                  var7.a(var6.N(), var3, (am)null);
               } else {
                  var7 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
                  var7.a(var4);
                  var7.a(var3.a, var3.b, var1, var5);
               }
            } else if(!this.R.a(var6.B(), (am)var4)) {
               this.A = var6.B();
               this.B = this.A.i(var4);
            }

            return true;
         }
      }
   }

   private boolean z() {
      am[] var1 = am.bE.a();
      int var2 = 0;

      for(int var3 = am.bE.size(); var2 < var3; ++var2) {
         am var4 = var1[var2];
         if(var4.bX == this.R && this.a(var4) && var4.bT() && !var4.u() && var4 instanceof y) {
            y var5 = (y)var4;
            if(var5.ai()) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean a(com.corrodinggames.rts.game.units.custom.h var1) {
      am[] var2 = this.q.a();
      int var3 = 0;

      for(int var4 = this.q.size(); var3 < var4; ++var3) {
         am var5 = var2[var3];
         if(var5.bX == this.R && var5.bT()) {
            com.corrodinggames.rts.game.units.custom.h var6 = var5.de();
            if(var6 != null && com.corrodinggames.rts.game.units.custom.g.a(var1, var6)) {
               return true;
            }
         }
      }

      return false;
   }

   private y a(com.corrodinggames.rts.game.units.as var1, boolean var2, boolean var3) {
      am[] var4 = this.q.a();
      int var5 = 0;

      for(int var6 = this.q.size(); var5 < var6; ++var5) {
         am var7 = var4[var5];
         if(var7.bX == this.R && var7.bT() && this.R.i(var7) && var7 instanceof com.corrodinggames.rts.game.units.d.l && var7 instanceof y) {
            y var8 = (y)var7;
            com.corrodinggames.rts.game.units.d.l var9 = (com.corrodinggames.rts.game.units.d.l)var7;
            s var10 = var7.e(var1);
            if(var10 != null && (var9.dy() || !var2) && !var10.m(var7) && var10.b((am)var8) && var10.a(var8, false) && (!(var7 instanceof com.corrodinggames.rts.game.units.d.e) || var1.m() || this.u() <= 2 || this.s || !var2) && (!var3 || var8.aD)) {
               return var8;
            }
         }
      }

      return null;
   }

   private boolean a(d var1, boolean var2) {
      ArrayList var3 = var1.a();
      Iterator var4 = var3.iterator();

      e var5;
      do {
         if(!var4.hasNext()) {
            return false;
         }

         var5 = (e)var4.next();
      } while(!this.a(var5.a, var2));

      return true;
   }

   private boolean a(com.corrodinggames.rts.game.units.as var1, boolean var2) {
      byte var3 = 1;
      return this.a(var1, var2, var3);
   }

   private boolean a(com.corrodinggames.rts.game.units.as var1, boolean var2, int var3) {
      if(var3 < 1) {
         com.corrodinggames.rts.gameFramework.l.b("AI", "buildUnit: quantity cannot be < 1");
         return false;
      } else {
         y var4 = this.a(var1, true, var2);
         if(var4 == null) {
            ;
         }

         if(var4 == null) {
            return false;
         } else {
            com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
            s var6 = var4.e(var1);
            if(var6 == null) {
               com.corrodinggames.rts.gameFramework.l.b("AI", "buildUnit: action is null!");
               return false;
            } else if(!var6.b((am)var4)) {
               com.corrodinggames.rts.gameFramework.l.b("AI", "buildUnit: isAvailable==false");
               return false;
            } else if(!var6.a(var4, false)) {
               com.corrodinggames.rts.gameFramework.l.b("AI", "buildUnit: isActive==false");
               return false;
            } else if(!var6.g(var4) && (!var6.n_() || !var5.ar)) {
               for(int var7 = 0; var7 < var3; ++var7) {
                  com.corrodinggames.rts.gameFramework.e var8 = var5.cf.a((com.corrodinggames.rts.game.n)this.R);
                  var8.a(var4);
                  var8.a(var6.z());
               }

               return true;
            } else {
               return false;
            }
         }
      }
   }

   i l() {
      float var1 = -1.0F;
      i var2 = null;
      Iterator var3 = this.R.bn.iterator();

      while(var3.hasNext()) {
         o var4 = (o)var3.next();
         if(var4 instanceof i) {
            i var5 = (i)var4;
            if(var5 != this && var5.e() > 1) {
               float var6 = var5.a(this);
               if(var2 == null || var6 < var1) {
                  var1 = var6;
                  var2 = var5;
               }
            }
         }
      }

      return var2;
   }

   public void m() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      i var2 = this.l();
      if(var2 != null && var2.e() > 1) {
         y var3 = var2.x();
         if(var3 != null) {
            PointF var4 = this.w();
            if(com.corrodinggames.rts.gameFramework.utility.y.a(var3, var4.a, var4.b)) {
               boolean var5 = this.R.a(var3, var4.a, var4.b);
               if(var5 || this.R.aG != 0) {
                  com.corrodinggames.rts.gameFramework.e var6 = var1.cf.a((com.corrodinggames.rts.game.n)this.R);
                  var6.a(var3);
                  var6.a(var4.a, var4.b);
                  ++this.h;
                  this.g = (float)com.corrodinggames.rts.gameFramework.f.a(1800, 2500);
                  if(this.h >= 2) {
                     this.g += 11000.0F;
                  }

                  --var2.K;
                  if(!var5) {
                     boolean var7 = true;
                     if(var3.aB != null) {
                        if(!var3.aB.a()) {
                           var3.aB.b(var3);
                        } else {
                           var7 = false;
                           if(!var3.aB.G.contains(var3)) {
                              var3.aB.G.add(var3);
                           }
                        }
                     }

                     if(var7) {
                        l var8 = new l(this.R);
                        var8.c(var3);
                        var8.S = var4.a;
                        var8.T = var4.b;
                     }

                     this.g = (float)com.corrodinggames.rts.gameFramework.f.a(12000, 14000);
                  }
               }
            }
         }
      }

   }

   private am A() {
      am[] var1 = am.bE.a();
      int var2 = 0;

      for(int var3 = am.bE.size(); var2 < var3; ++var2) {
         am var4 = var1[var2];
         if(var4.bX == this.R && this.a(var4, true) && var4.bI() && (var4.cu < var4.cv - 1.0F || var4.cm < 1.0F)) {
            return var4;
         }
      }

      return null;
   }

   public void n() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      if(this.r.size() != 0) {
         if(!this.s) {
            for(int var2 = 0; var2 < 8; ++var2) {
               com.corrodinggames.rts.game.units.as var3 = this.R.bA.c();
               if(var3 != null && this.a(var3)) {
                  boolean var4 = this.d(var3);
                  if(var4) {
                     break;
                  }
               }
            }

         }
      }
   }

   public boolean d(com.corrodinggames.rts.game.units.as var1) {
      if(!(var1 instanceof com.corrodinggames.rts.game.units.custom.l)) {
         return false;
      } else {
         com.corrodinggames.rts.game.units.custom.l var2 = (com.corrodinggames.rts.game.units.custom.l)var1;
         if(var2.fE == -1 && var2.fF == -1) {
            return false;
         } else {
            int var3 = 0;
            int var4 = 0;
            boolean var5 = var2.fG;
            am[] var6 = am.bE.a();
            int var7 = 0;

            for(int var8 = am.bE.size(); var7 < var8; ++var7) {
               am var9 = var6[var7];
               if(var9.bX == this.R && var9.cN == null && var9 instanceof y && this.R.i(var9)) {
                  y var10 = (y)var9;
                  com.corrodinggames.rts.game.units.as var11 = var9.r();
                  if(var5) {
                     if(!var11.n()) {
                        continue;
                     }
                  } else if(var11 != var2 && !var2.fL.contains(var11)) {
                     continue;
                  }

                  ++var4;
                  if(this.a(var9)) {
                     ++var3;
                  }
               }
            }

            if(var2.fE != -1 && var3 >= var2.fE) {
               return false;
            } else if(var2.fF != -1 && var4 >= var2.fF) {
               return false;
            } else {
               boolean var12 = this.a((com.corrodinggames.rts.game.units.as)var2, true);
               return var12;
            }
         }
      }
   }

   public void o() {
      if(this.r.size() != 0) {
         am var1 = this.r();
         if(var1 != null) {
            this.x.a(var1.eo, var1.ep);
            y var2 = this.a(var1, this.x, true);
            if(var2 != null) {
               this.a(var2, var1);
            }
         }

      }
   }

   public void q() {
      if(this.r.size() != 0) {
         if(this.B != null) {
            am[] var1 = this.q.a();
            int var2 = 0;

            for(int var3 = this.q.size(); var2 < var3; ++var2) {
               am var4 = var1[var2];
               if(var4.bX == this.R && this.a(var4) && var4.cN == null) {
                  com.corrodinggames.rts.game.units.as var5 = var4.r();
                  if(var5.n() && var4 instanceof y && this.R.i(var4) && (double)com.corrodinggames.rts.gameFramework.f.c(0.0F, 1.0F) <= 0.3D) {
                     y var6 = (y)var4;
                     au var7 = var6.ar();
                     if(var7 != null && var7.d() == av.g) {
                        am var8 = var7.i();
                        if(var8 != null && var8.g() > 0.0F && !this.B.c(var8.cM())) {
                           am var9 = this.r();
                           this.a(var6, var9);
                           break;
                        }
                     }
                  }
               }
            }
         }

      }
   }

   public am r() {
      am var1 = null;

      for(int var2 = 0; var2 < 20; ++var2) {
         var1 = this.r.a(com.corrodinggames.rts.gameFramework.f.a(0, this.r.size() - 1));
         if(var1 == null || this.B == null || this.B.c(var1.cM())) {
            break;
         }
      }

      return var1;
   }

   public void a(y var1, am var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1.g(var2, true)) {
         com.corrodinggames.rts.gameFramework.e var4 = var3.cf.a((com.corrodinggames.rts.game.n)this.R);
         var4.a(var1);
         var4.d(var2);
      }

   }

   public void s() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      am var2 = this.A();
      if(var2 != null) {
         this.x.a(var2.eo, var2.ep);
         y var3 = this.a((com.corrodinggames.rts.game.units.as)null, this.x, true);
         if(var3 != null && var3.a(var2) && var2.e(var3) < 2) {
            com.corrodinggames.rts.gameFramework.e var4 = var1.cf.a((com.corrodinggames.rts.game.n)this.R);
            var4.a(var3);
            var4.b(var2);
         }
      }

   }

   public void b(float var1) {
      this.c(var1);
      int var2 = this.J;
      int var3 = this.I;
      this.b();
      this.n = this.z();
      if(this.n) {
         this.o = true;
      }

      if(var2 >= 1) {
         this.s();
      }

      if(this.M && this.I > 0) {
         this.n();
         this.q();
         this.o();
      }

      byte var4 = 2;
      int var5;
      if(var2 < var4 && this.i == 0.0F) {
         this.i = 300.0F;
         var5 = this.R.a(this.R.bz, b.a);
         if(!this.s || var5 <= 2) {
            boolean var6 = true;
            boolean var7 = com.corrodinggames.rts.gameFramework.f.a(0, 100) < 5;
            if(!var7 && this.a(this.R.bz, var6)) {
               this.H = false;
               this.i = 900.0F;
            } else {
               if(!var7) {
                  this.H = true;
               }

               if(!this.s && this.v == 0.0F && var2 < 1 && this.g == 0.0F) {
                  this.m();
               }
            }
         }
      }

      var5 = this.j();
      if(var2 == 0 && var3 == 0) {
         this.k += var1;
         if(var5 > 2) {
            this.k += 2.0F * var1;
         }

         if(var5 > 5) {
            this.k += 4.0F * var1;
         }
      } else {
         this.k = com.corrodinggames.rts.gameFramework.f.a(this.k, var1);
      }

      if(this.k > 11000.0F) {
         this.p();
      }

      if(this.b == j.a && (var2 != 0 && var3 != 0 || var3 > 5 && var5 == 0)) {
         this.l += var1;
         if(this.l > 2000.0F) {
            this.b = j.c;
         }
      }

      this.t();
   }

   public void t() {
      if(this.b == null) {
         com.corrodinggames.rts.gameFramework.l.a("fixOverlaps: this.state==null");
         com.corrodinggames.rts.gameFramework.l.a("id:" + this.Q);
         com.corrodinggames.rts.gameFramework.l.a("x:" + this.S);
         com.corrodinggames.rts.gameFramework.l.a("y:" + this.T);
         com.corrodinggames.rts.gameFramework.l.a("radius:" + this.U);
         if(this.R != null) {
            com.corrodinggames.rts.gameFramework.l.a("team:" + this.R.k);
         }

      } else {
         Iterator var1 = this.R.bm.iterator();

         while(var1.hasNext()) {
            o var2 = (o)var1.next();
            if(var2 instanceof i && var2 != this) {
               i var3 = (i)var2;
               float var4 = com.corrodinggames.rts.gameFramework.f.a(this.S, this.T, var3.S, var3.T);
               if(var4 < 400.0F) {
                  if(var3.b == null) {
                     com.corrodinggames.rts.gameFramework.l.a("fixOverlaps: targetBase.state==null");
                  } else if(var3.b.a() < this.b.a()) {
                     var3.p();
                  } else {
                     this.p();
                  }
               }
            }
         }

      }
   }

   public int u() {
      return this.I;
   }

   public void c(float var1) {
      this.I = 0;
      this.J = 0;
      this.L = 0;
      this.K = 0;
      this.M = false;
      this.r.clear();
      boolean var2 = true;
      if(var2) {
         Iterator var3 = this.k().iterator();

         while(var3.hasNext()) {
            am var4 = (am)var3.next();
            if(var4.g() > 0.0F && this.b(var4)) {
               this.M = true;
               this.r.a(var4);
            }
         }
      }

      am[] var10 = am.bE.a();
      int var11 = 0;

      for(int var5 = am.bE.size(); var11 < var5; ++var11) {
         am var6 = var10[var11];
         if(var6.bX == this.R && var6 instanceof y) {
            y var7 = (y)var6;
            if(this.a(var7, false) && var6.bT() && this.R.i(var6) && !var6.u()) {
               com.corrodinggames.rts.game.units.as var8 = var6.r();
               if(var8.j()) {
                  ++this.I;
               }

               if(var8.m()) {
                  ++this.J;
                  boolean var9 = f.a(var7);
                  if(var9) {
                     ++this.K;
                  }
               }

               if(var8.n()) {
                  ++this.L;
               }

               if(var6 instanceof com.corrodinggames.rts.game.units.d.l) {
                  com.corrodinggames.rts.game.units.d.l var12 = (com.corrodinggames.rts.game.units.d.l)var6;
                  this.J += var12.h(ar.h);
               }
            }
         }
      }

   }

   public void d(float var1) {
      this.s = this.f();
      this.t = this.s;
      if(this.s) {
         this.v += var1;
         this.u = 100.0F;
      } else {
         this.v = 0.0F;
      }

      if(this.v > 6000.0F) {
         this.s = false;
      }

      this.m = com.corrodinggames.rts.gameFramework.f.a(this.m, var1);
      this.e = com.corrodinggames.rts.gameFramework.f.a(this.e, var1);
      this.g = com.corrodinggames.rts.gameFramework.f.a(this.g, var1);
      this.i = com.corrodinggames.rts.gameFramework.f.a(this.i, var1);
      this.j = com.corrodinggames.rts.gameFramework.f.a(this.j, var1);
      if(this.s && this.j == 0.0F) {
         this.j = (float)(100 + this.Q % 15);
         if(!this.R.aY) {
            this.i();
         }
      }

      if(this.e <= 0.0F) {
         this.e = (float)(270 + this.Q % 15);
         if(this.R.ac()) {
            this.e = (float)(190 + this.Q % 15);
         }

         if((double)this.f < 0.2D) {
            this.e += 180.0F;
         }

         if((double)this.f < 0.08D) {
            this.e += 180.0F;
         }

         boolean var2 = this.y() != null;
         if(var2) {
            com.corrodinggames.rts.game.units.as var3 = null;
            var3 = this.c();
            if(var3 != null && ((double)this.f > 0.8D || this.R.a(1300.0D)) && ((double)this.f > 0.4D || this.R.a(1700.0D)) && ((double)this.f > 0.2D || this.R.a(2100.0D)) && ((double)this.f > 0.1D || this.R.a(2800.0D)) && ((double)this.f > 0.05D || this.R.a(3100.0D)) && ((double)this.f > 0.01D || this.R.a(4800.0D))) {
               ++this.C;
               if(!this.g(var3)) {
                  this.e -= 120.0F;
                  ++this.D;
               }
            }
         }
      }

      float var4 = (float)this.u();
      var4 /= 3.0F;
      if(var4 < 1.0F) {
         var4 = 1.0F;
      }

      if(this.s) {
         this.d = (float)((double)this.d + (double)var1 * 0.015D);
      }

      if((double)this.f < 0.6D) {
         if(this.R.bb < 2) {
            this.d = (float)((double)this.d + (double)var1 * 7.0E-4D * (double)var4);
         } else if(this.R.a(1200.0D)) {
            this.d = (float)((double)this.d + (double)var1 * 1.0E-4D * (double)var4);
         }

         if(this.R.a(1600.0D)) {
            this.d = (float)((double)this.d + (double)var1 * 0.001D);
         }

         if(this.R.a(2200.0D)) {
            this.d = (float)((double)this.d + (double)var1 * 0.001D);
         }

         if(this.R.a(2600.0D)) {
            this.d = (float)((double)this.d + (double)var1 * 0.001D);
         }

         if(this.R.a(8000.0D)) {
            this.d = (float)((double)this.d + (double)var1 * 0.005D);
         }

         if(this.R.a(9000.0D)) {
            this.d = (float)((double)this.d + (double)var1 * 0.01D);
         }

         if(this.R.a(10100.0D)) {
            this.d = (float)((double)this.d + (double)var1 * 0.01D);
         }

         if(this.R.a(30000.0D)) {
            this.d = (float)((double)this.d + (double)var1 * 0.05D);
         }
      }

      if(this.R.a(5000.0D)) {
         this.d = (float)((double)this.d + (double)var1 * 0.001D);
      }

      if(!this.R.a(800.0D) && !this.s && this.d > 1.2F) {
         this.d = 1.2F;
      }

      if(this.d > 3.5F) {
         this.d = 3.5F;
      }

      for(int var5 = 0; var5 < 12; ++var5) {
         this.v();
         if(this.d < 3.0F) {
            break;
         }
      }

   }

   public void a(ArrayList var1, d var2, ao var3, int var4) {
      this.N.clear();

      for(int var5 = 0; var5 < var4; ++var5) {
         com.corrodinggames.rts.game.units.as var6 = var2.a(var3);
         if(var6 != null && !this.N.contains(var6)) {
            this.N.add(var6);
         }
      }

      var1.addAll(this.N);
   }

   public void v() {
      int var1 = this.d();
      byte var2 = 12;
      byte var3 = 50;
      if(this.R.ac()) {
         var3 = 65;
         var2 = 16;
      }

      boolean var4 = this.R.a(25000.0D);
      boolean var5 = false;
      ArrayList var6 = new ArrayList();
      boolean var7 = this.R.ai();
      boolean var8 = this.R.aj();
      float var9 = 0.4F;
      float var10 = 0.3F;
      float var11 = 0.2F;
      if(!this.R.bh) {
         var9 = 0.1F;
         var10 = 0.5F;
         var11 = 0.4F;
      }

      if(!this.R.bi) {
         var9 = 0.2F;
         var10 = 0.1F;
         var11 = 0.7F;
      }

      float var13 = com.corrodinggames.rts.gameFramework.f.c(0.0F, 1.0F);
      ao var12;
      if(var13 < var9) {
         var12 = ao.b;
      } else if(var13 < var9 + var10) {
         var12 = ao.f;
      } else {
         var12 = ao.d;
      }

      if(this.R.a(1300.0D) && this.d >= 1.0F || this.R.a(300.0D) && this.d >= 3.0F) {
         int var14;
         if(this.R.ah() && this.R.bc < var2) {
            var14 = com.corrodinggames.rts.gameFramework.f.c(100);
            if(var14 < 35) {
               this.a(var6, this.R.bu, (ao)null, 2);
               if(var4) {
                  var5 = true;
               }
            }
         }

         if(var1 < 3 && this.R.ba < var3) {
            if(var12 == ao.b) {
               this.a(var6, this.R.br, (ao)null, 4);
               if(var4) {
                  var5 = true;
               }
            } else if(var12 == ao.f) {
               this.a(var6, this.R.bs, (ao)null, 4);
               if(var4) {
                  var5 = true;
               }
            } else {
               this.a(var6, this.R.bt, (ao)null, 4);
               if(var4) {
                  var5 = true;
               }
            }
         }

         if(this.d >= 1.0F && var7 && this.m == 0.0F) {
            var14 = this.R.a(this.R.bx, b.a);
            int var15 = this.R.a(this.R.by, b.a);
            int var16 = var14 + var15;
            int var17 = this.R.ao();
            if((this.R.a(1700.0D) || var17 > 10 || this.R.bl == 0 && var17 >= 1 && var14 == 0) && (var16 < 3 || var17 > 20 && var16 < 5)) {
               if(var8 && var16 < 2) {
                  this.a(var6, this.R.bw, (ao)null, 2);
               } else {
                  this.a(var6, this.R.bw, ao.d, 2);
               }
            }
         }
      }

      if(var6.size() == 0) {
         ++this.F;
      }

      while(var6.size() != 0) {
         com.corrodinggames.rts.game.units.as var18 = (com.corrodinggames.rts.game.units.as)var6.remove(var6.size() - 1);
         am var19 = am.b(var18);
         boolean var20 = true;
         if(this.s && com.corrodinggames.rts.gameFramework.f.c(100) < 10) {
            am var21 = this.g();
            if(var21 != null && !this.R.b(var19, var21)) {
               ++this.F;
               var20 = false;
            }
         }

         if(var20) {
            boolean var22 = false;
            if(this.a(var18, var22)) {
               ++this.F;
               this.R.bE.a(var18);
               --this.d;
               if(this.R.g(var19)) {
                  this.m = 1000.0F;
                  ++this.R.bl;
               }
               break;
            }

            ++this.G;
         }
      }

   }

   public void a(y var1, com.corrodinggames.rts.game.units.custom.d.b var2, boolean var3) {
      this.O = var1.r();
      if(var3) {
         this.P = null;
         this.B = null;
      } else {
         this.P = var2;
         this.B = var2.i(var1);
      }

   }
}
