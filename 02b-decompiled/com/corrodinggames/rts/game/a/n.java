package com.corrodinggames.rts.game.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.h;
import com.corrodinggames.rts.game.a.i;
import com.corrodinggames.rts.game.a.j;
import com.corrodinggames.rts.game.a.l;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;
import java.util.Iterator;

public class n extends h {

   boolean a;
   int b;
   int c;
   i d;
   float e = 100.0F;
   float f = 4000.0F;
   float g = 100.0F;
   float h;
   float i;
   float j;
   float k;
   int l;
   h m;
   y n;
   float o = 0.0F;
   boolean p = false;
   boolean q;
   float r;
   float s;


   public void a(as var1) {
      var1.a(this.a);
      var1.a(this.b);
      var1.a(this.c);
      int var2 = this.F.size();
      var1.a(var2);
      Iterator var3 = this.F.iterator();

      while(var3.hasNext()) {
         y var4 = (y)var3.next();
         var1.a(var4);
      }

      var1.c(5);
      var1.a(this.R.a((o)this.m));
      var1.a(this.q);
      var1.a(this.n);
      var1.a(this.o);
      var1.a(this.p);
      var1.a(this.r);
      var1.a(this.s);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.a = var1.e();
      this.b = var1.f();
      this.c = var1.f();
      this.q();
      int var2 = var1.f();

      for(int var3 = 0; var3 < var2; ++var3) {
         y var4 = var1.p();
         if(var4 != null) {
            if(!this.R.g(var4)) {
               com.corrodinggames.rts.gameFramework.l.b("TransporterGroup:readIn: Unit is not transporterUnit");
            } else {
               this.a(var4);
            }
         }
      }

      byte var5 = var1.d();
      if(var5 >= 1) {
         this.m = (h)this.R.m(var1.f());
      }

      if(var5 >= 2) {
         this.q = var1.e();
      }

      if(var5 >= 3) {
         this.n = var1.p();
      }

      if(var5 >= 4) {
         this.o = var1.g();
         this.p = var1.e();
      }

      if(var5 >= 5) {
         this.r = var1.g();
         this.s = var1.g();
      }

      super.a(var1);
   }

   public n(a var1) {
      super(var1);
   }

   public void c() {
      Iterator var1 = am.bE.iterator();

      while(var1.hasNext()) {
         am var2 = (am)var1.next();
         if(!var2.bV && var2.bX == this.R && this.l > this.F.size() && var2 instanceof y) {
            y var3 = (y)var2;
            if(!var3.bN && var3.aB == null && this.R.g(var3) && this.R.i(var3)) {
               this.a(var3);
            }
         }
      }

   }

   public boolean d() {
      return this.m != null;
   }

   public void c(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.h += var1;
      this.n();
      if(this.l <= this.F.size()) {
         ;
      }

      this.i = com.corrodinggames.rts.gameFramework.f.a(this.i, var1);
      this.j = com.corrodinggames.rts.gameFramework.f.a(this.j, var1);
      this.k = com.corrodinggames.rts.gameFramework.f.a(this.k, var1);
      if(!this.d() && !this.q && this.l > this.F.size() && this.i == 0.0F) {
         this.i = 300.0F;
         this.c();
      }

      Iterator var4;
      y var5;
      ak var6;
      if(!this.d() && this.F.size() != 0) {
         if(!this.d()) {
            this.f = com.corrodinggames.rts.gameFramework.f.a(this.f, var1);
            if(this.f == 0.0F) {
               this.f = 4000.0F;
               if(this.d != null) {
                  PointF var3 = this.d.w();
                  this.S = var3.a;
                  this.T = var3.b;
               }
            }
         }

         if(this.j == 0.0F) {
            this.j = 400.0F;
            com.corrodinggames.rts.gameFramework.e var10 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
            var4 = this.F.iterator();

            while(var4.hasNext()) {
               var5 = (y)var4.next();
               if(this.c(var5) > 28900.0F && !var5.aw()) {
                  var10.a(var5);
               } else {
                  var6 = (ak)var5;
                  if(var6.bB() != 0) {
                     com.corrodinggames.rts.game.units.a.c var7 = var5.cp();
                     com.corrodinggames.rts.gameFramework.e var8 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
                     var8.a(var5);
                     var8.a(var7);
                  }
               }
            }

            var10.a(this.S, this.T);
         }

         if(this.m == null) {
            this.g = com.corrodinggames.rts.gameFramework.f.a(this.g, var1);
            if(this.g == 0.0F) {
               this.g = 100.0F;
               if(com.corrodinggames.rts.gameFramework.f.a(0, 100) < 80) {
                  this.a(var1, true);
               }

               if(this.m == null) {
                  this.a(var1, false);
               }
            }
         }
      }

      if(this.m != null && this.m.V) {
         this.m = null;
      }

      com.corrodinggames.rts.gameFramework.e var9;
      Iterator var17;
      y var21;
      if(!this.q) {
         if(this.m != null) {
            ArrayList var11 = this.m.G;
            if(this.n != null && (this.n.bV || this.n.cN != null || this.n.cO != null)) {
               var11.remove(this.n);
               this.n = null;
            }

            if(this.n == null) {
               var4 = var11.iterator();

               while(var4.hasNext()) {
                  var5 = (y)var4.next();
                  if(var5.cN == null) {
                     var17 = this.F.iterator();

                     while(var17.hasNext()) {
                        var21 = (y)var17.next();
                        if(var21.d(var5, false)) {
                           this.n = var5;
                           break;
                        }
                     }
                  }
               }

               if(this.n == null) {
                  this.q = true;
                  this.j = 0.0F;
                  this.k = 0.0F;
                  this.r = this.m.S;
                  this.s = this.m.T;
               }
            }

            if(this.n != null) {
               Iterator var15;
               y var18;
               if(this.j == 0.0F) {
                  this.j = 400.0F;
                  com.corrodinggames.rts.gameFramework.e var13 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
                  var15 = this.F.iterator();

                  while(var15.hasNext()) {
                     var18 = (y)var15.next();
                     var13.a(var18);
                  }

                  var13.a(this.n.eo, this.n.ep);
               }

               if(this.k == 0.0F) {
                  this.k = 80.0F;
                  var4 = var11.iterator();

                  while(var4.hasNext()) {
                     var5 = (y)var4.next();
                     var17 = this.F.iterator();

                     while(var17.hasNext()) {
                        var21 = (y)var17.next();
                        if(var21.d(var5, false)) {
                           float var26 = com.corrodinggames.rts.gameFramework.f.a(var21.eo, var21.ep, var5.eo, var5.ep);
                           if(var26 < 14400.0F) {
                              var9 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
                              var9.a(var5);
                              var9.e(var21);
                              break;
                           }
                        }
                     }
                  }

                  boolean var14 = false;
                  var15 = this.F.iterator();

                  while(var15.hasNext()) {
                     var18 = (y)var15.next();
                     if(var18.d(this.n, false)) {
                        var14 = true;
                     }
                  }

                  if(!var14) {
                     this.n = null;
                  }
               }
            }
         }
      } else if(this.m == null) {
         this.e();
      } else {
         if(this.j == 0.0F) {
            this.j = 400.0F;
            float var12 = this.m.S + com.corrodinggames.rts.gameFramework.f.c(-40.0F, 40.0F);
            float var16 = this.m.T + com.corrodinggames.rts.gameFramework.f.c(-40.0F, 40.0F);
            if(this.o > 600.0F) {
               var12 += com.corrodinggames.rts.gameFramework.f.c(-300.0F, 300.0F);
               var16 += com.corrodinggames.rts.gameFramework.f.c(-300.0F, 300.0F);
            }

            if(this.o > 1200.0F) {
               var12 += com.corrodinggames.rts.gameFramework.f.c(-300.0F, 300.0F);
               var16 += com.corrodinggames.rts.gameFramework.f.c(-300.0F, 300.0F);
            }

            if(com.corrodinggames.rts.gameFramework.utility.y.a(var12, var16, ao.b)) {
               var12 += com.corrodinggames.rts.gameFramework.f.c(-100.0F, 100.0F);
               var16 += com.corrodinggames.rts.gameFramework.f.c(-100.0F, 100.0F);
            }

            if(com.corrodinggames.rts.gameFramework.utility.y.a(var12, var16, ao.b)) {
               var12 += com.corrodinggames.rts.gameFramework.f.c(-200.0F, 200.0F);
               var16 += com.corrodinggames.rts.gameFramework.f.c(-200.0F, 200.0F);
            }

            if(com.corrodinggames.rts.gameFramework.utility.y.a(var12, var16, ao.b)) {
               var12 += com.corrodinggames.rts.gameFramework.f.c(-200.0F, 200.0F);
               var16 += com.corrodinggames.rts.gameFramework.f.c(-200.0F, 200.0F);
            }

            if(com.corrodinggames.rts.gameFramework.utility.y.a(var12, var16, ao.b)) {
               this.j = 30.0F;
            } else {
               this.r = var12;
               this.s = var16;
               com.corrodinggames.rts.gameFramework.e var19 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
               var17 = this.F.iterator();

               while(var17.hasNext()) {
                  var21 = (y)var17.next();
                  ak var27 = (ak)var21;
                  if(var27.bB() != 0) {
                     float var28 = com.corrodinggames.rts.gameFramework.f.a(var21.eo, var21.ep, this.r, this.s);
                     if(var28 > 1600.0F) {
                        var19.a(var21);
                     }
                  } else {
                     var9 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
                     var9.a(var21);
                     var9.a(this.S, this.T);
                  }
               }

               var19.a(this.r, this.s);
            }
         }

         if(this.k == 0.0F) {
            this.k = 100.0F;
            Iterator var22 = this.F.iterator();

            while(var22.hasNext()) {
               y var29 = (y)var22.next();
               float var20 = com.corrodinggames.rts.gameFramework.f.a(var29.eo, var29.ep, this.r, this.s);
               if(var20 < 6400.0F) {
                  this.p = true;
                  com.corrodinggames.rts.game.units.a.c var24 = var29.cp();
                  com.corrodinggames.rts.gameFramework.e var25 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
                  var25.a(var29);
                  var25.a(var24);
               }
            }
         }

         if(this.p) {
            this.m.o();
            this.o += var1;
         }

         boolean var23 = false;
         var4 = this.F.iterator();

         while(var4.hasNext()) {
            var5 = (y)var4.next();
            if(!var5.bV) {
               var6 = (ak)var5;
               if(var6.bB() != 0) {
                  var23 = true;
               }
            }
         }

         if(!var23 || this.o > 1700.0F) {
            this.e();
         }
      }

      if(this.h > 1500.0F && this.F.size() == 0) {
         this.p();
      }

   }

   public void e() {
      this.q = false;
      this.m = null;
      this.o = 0.0F;
      this.j = 0.0F;
      this.k = 0.0F;
      this.p = false;
      this.f();
   }

   public void a(float var1, boolean var2) {
      Iterator var3 = this.R.bn.iterator();

      while(var3.hasNext()) {
         o var4 = (o)var3.next();
         if(var4 instanceof h && !(var4 instanceof n) && (!var2 || var4 instanceof l)) {
            h var5 = (h)var4;
            if(var5.G.size() != 0 && !var5.m()) {
               this.m = var5;
               this.n = null;
               return;
            }
         }
      }

   }

   public i a(boolean var1) {
      i var2 = null;
      Iterator var3 = this.R.bn.iterator();

      while(var3.hasNext()) {
         o var4 = (o)var3.next();
         if(var4 instanceof i) {
            i var5 = (i)var4;
            if((!var5.s || !var1) && var5.b == j.c) {
               var2 = var5;
               if(com.corrodinggames.rts.gameFramework.f.c(3) == 0) {
                  return var5;
               }
            }
         }
      }

      return var2;
   }

   public void f() {
      boolean var1 = true;
      PointF var2 = null;
      if(var1) {
         this.d = this.a(true);
         if(this.d == null) {
            this.d = this.a(false);
         }

         if(this.d != null) {
            var2 = this.d.w();
         }
      }

      if(var2 == null) {
         var2 = this.R.am();
         this.d = null;
      }

      this.S = var2.a;
      this.T = var2.b;
   }
}
