package com.corrodinggames.rts.game.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.h;
import com.corrodinggames.rts.game.a.i;
import com.corrodinggames.rts.game.a.j;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.aq;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.AbstractList;
import java.util.Iterator;

public class g extends h {

   boolean a;
   String b;
   boolean c;
   boolean d;
   boolean e;
   boolean f;
   y g;
   boolean h;
   int i;
   int j;
   i k;
   float l;
   float m;
   float n;
   float o;
   float p;
   boolean q;
   boolean r;
   boolean s;
   float t;
   float u;
   boolean v;
   am w;
   float x;
   float y;
   float z;
   int A;
   boolean B;
   public int C;
   public am D;
   ao E;


   public boolean a() {
      return this.a;
   }

   public boolean b() {
      return !this.h;
   }

   public static g a(a var0, y var1) {
      g var2 = new g(var0, false);
      var2.a = true;
      var2.c = true;
      var2.d = true;
      var2.e = true;
      var2.g = var1;
      var2.a(var1);
      var2.A = 0;
      var2.k();
      return var2;
   }

   public void a(as var1) {
      var1.a(this.h);
      var1.a(this.i);
      var1.a(this.j);
      int var2 = this.F.size();
      var1.a(var2);
      Iterator var3 = this.F.iterator();

      y var4;
      while(var3.hasNext()) {
         var4 = (y)var3.next();
         var1.a(var4);
      }

      var1.c(7);
      var1.a(false);
      var1.a(this.s);
      var1.a(this.o);
      var1.a(this.G.size());
      var3 = this.G.iterator();

      while(var3.hasNext()) {
         var4 = (y)var3.next();
         var1.a(var4);
      }

      var1.a(this.B);
      var1.a(this.a);
      var1.a(this.c);
      var1.a(this.d);
      var1.a(this.e);
      var1.a(this.f);
      var1.a(this.g);
      var1.a(this.A);
      super.a(var1);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.h = var1.e();
      this.i = var1.f();
      this.j = var1.f();
      this.q();
      int var2 = var1.f();

      for(int var3 = 0; var3 < var2; ++var3) {
         y var4 = var1.p();
         if(var4 != null) {
            this.a(var4);
         }
      }

      byte var7 = var1.d();
      if(var7 >= 1) {
         var1.e();
      }

      if(var7 >= 2) {
         this.s = var1.e();
      }

      if(var7 >= 3) {
         this.o = var1.g();
      }

      if(var7 >= 4) {
         this.G.clear();
         int var8 = var1.f();

         for(int var5 = 0; var5 < var8; ++var5) {
            y var6 = var1.p();
            if(var6 != null) {
               this.G.add(var6);
            }
         }
      }

      if(var7 >= 5) {
         this.B = var1.e();
      }

      if(var7 >= 6) {
         this.a = var1.e();
         this.c = var1.e();
         this.d = var1.e();
         this.e = var1.e();
         this.f = var1.e();
         this.g = var1.p();
      }

      if(var7 >= 7) {
         this.A = var1.f();
      }

      if(!this.B) {
         Iterator var9 = this.F.iterator();

         while(var9.hasNext()) {
            y var10 = (y)var9.next();
            if(var10 instanceof com.corrodinggames.rts.game.units.h.f) {
               if(var10 != null && var10.aB == this) {
                  var10.aB = null;
               }

               if(var10 != null) {
                  this.G.remove(var10);
               }

               var9.remove();
            }
         }
      }

      super.a(var1);
   }

   public g(a var1) {
      super(var1);
      this.h = true;
      this.l = 1000.0F;
      this.m = 100.0F;
      this.n = 4000.0F;
      this.o = 0.0F;
      this.p = 1000.0F;
      this.q = false;
      this.r = false;
      this.s = false;
      this.t = 0.0F;
      this.u = 0.0F;
      this.C = -9999;
      this.D = null;
      this.E = ao.a;
   }

   public g(a var1, boolean var2) {
      this(var1);
      this.h = var2;
   }

   protected void a(y var1) {
      super.a(var1);
      this.E = this.j();
   }

   public void c() {
      Iterator var1 = am.bE.iterator();

      while(var1.hasNext()) {
         am var2 = (am)var1.next();
         if(!var2.bV && var2.bX == this.R && this.A > this.F.size() && var2 instanceof y) {
            y var3 = (y)var2;
            if(!var3.bM && !var3.bN && var3.aB == null && this.R.h(var3) && this.R.i(var3)) {
               if(this.B) {
                  if(var2.h() == ao.b) {
                     continue;
                  }
               } else if(var2.h() == ao.e) {
                  continue;
               }

               if(this.R.a(var3, this.S, this.T) || !this.b() && com.corrodinggames.rts.gameFramework.f.a(0, 100) <= 2) {
                  this.a(var3);
               }
            }
         }
      }

   }

   public boolean d() {
      return this.A <= this.F.size();
   }

   public am a(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      return (float)var2.by - var1 * 1000.0F < (float)this.C?this.D:null;
   }

   public am e() {
      am var1 = this.a(6.0F);
      return var1 != null?var1:null;
   }

   public am f() {
      Iterator var1 = this.F.iterator();

      am var3;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         y var2 = (y)var1.next();
         var3 = var2.ab();
      } while(var3 == null);

      return var3;
   }

   public void a(com.corrodinggames.rts.gameFramework.e var1, boolean var2, am var3) {
      Iterator var4 = this.F.iterator();

      while(var4.hasNext()) {
         y var5 = (y)var4.next();
         if((!var2 || var5.aq()) && (var3 == null || this.R.a((am)var5, var3))) {
            var1.a(var5);
         }
      }

   }

   public void a(String var1) {
      this.b = var1;
   }

   public PointF a(am var1) {
      PointF var2 = new PointF();
      var2.a = this.S;
      var2.b = this.T;
      float var3 = 50.0F;
      float var4 = 100.0F;
      float var5 = (float)(Math.random() * 360.0D);
      float var6 = com.corrodinggames.rts.gameFramework.f.c(var3, var4);
      var2.a += com.corrodinggames.rts.gameFramework.f.k(var5) * var6;
      var2.b += com.corrodinggames.rts.gameFramework.f.j(var5) * var6;
      if(var1 != null) {
         var3 = 100.0F;
         var4 = 200.0F;
         var5 = com.corrodinggames.rts.gameFramework.f.d(var2.a, var2.b, var1.eo, var1.ep);
         var6 = com.corrodinggames.rts.gameFramework.f.c(var3, var4);
         var2.a += com.corrodinggames.rts.gameFramework.f.k(var5) * -var6;
         var2.b += com.corrodinggames.rts.gameFramework.f.j(var5) * -var6;
      }

      return var2;
   }

   public void b(float var1) {
      super.b(var1);
      this.n();
      this.E = this.j();
      if(!this.f) {
         am var2 = this.e();
         if(var2 != null) {
            am var3 = this.f();
            if(var3 == null) {
               if(this.a(var2, false)) {
                  this.a("fighting attacker");
                  com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
                  com.corrodinggames.rts.gameFramework.e var5 = var4.cf.a((com.corrodinggames.rts.game.n)this.R);
                  this.a(var5, true, var2);
                  boolean var6 = false;
                  var5.a(var2.eo, var2.ep, var6);
               } else {
                  this.a("flight from attacker");
                  PointF var7 = this.a(var2);
                  this.S = var7.a;
                  this.T = var7.b;
                  if(this.z > 200.0F) {
                     this.z = 200.0F;
                  }
               }
            }
         }
      }

   }

   public void c(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      this.x += var1;
      Iterator var3 = this.F.iterator();

      while(var3.hasNext()) {
         y var4 = (y)var3.next();
         if(var4 != null && this.C < var4.bs) {
            this.C = var4.bs;
            this.D = var4.bt;
         }
      }

      this.n();
      if(this.d()) {
         this.l = com.corrodinggames.rts.gameFramework.f.a(this.l, var1);
      } else if(this.v) {
         ;
      }

      this.y = com.corrodinggames.rts.gameFramework.f.a(this.y, var1);
      this.z = com.corrodinggames.rts.gameFramework.f.a(this.z, var1);
      this.p = com.corrodinggames.rts.gameFramework.f.a(this.p, var1);
      if(!this.v && !this.r && !this.d() && this.y == 0.0F) {
         this.y = (float)(200 + com.corrodinggames.rts.gameFramework.f.c(200));
         this.c();
      }

      if(!this.v || this.q) {
         if(!this.q) {
            this.n = com.corrodinggames.rts.gameFramework.f.a(this.n, var1);
            if(this.n == 0.0F) {
               if(this.k == null) {
                  this.k = this.g();
               }

               if(this.k != null) {
                  PointF var7 = this.k.w();
                  if(!this.a(var7.a, var7.b)) {
                     this.n = 100.0F;
                     this.a("random move: bad target");
                  } else {
                     this.n = 4000.0F;
                     this.S = var7.a;
                     this.T = var7.b;
                     this.a("random move");
                  }
               } else {
                  this.a("random move: no linked base");
               }
            }
         }

         if(this.z == 0.0F) {
            this.z = 800.0F;
            com.corrodinggames.rts.gameFramework.e var8 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
            Iterator var9 = this.F.iterator();

            while(var9.hasNext()) {
               y var5 = (y)var9.next();
               boolean var6 = true;
               if(this.c(var5) < 28900.0F) {
                  var6 = false;
               }

               if(!this.f && var5.aj() && !var5.aq()) {
                  var6 = false;
               }

               if(var6) {
                  var8.a(var5);
               }
            }

            if(this.f) {
               var8.a(this.S, this.T);
            } else {
               var8.b(this.S, this.T);
            }
         }
      }

      if(this.h) {
         this.e(var1);
      } else {
         this.d(var1);
      }

      if(this.A == 0 && this.F.size() == 0) {
         this.p();
      }

      if(this.c && (this.g == null || this.g.bV)) {
         this.p();
      }

   }

   i g() {
      float var1 = -1.0F;
      i var2 = null;
      Iterator var3 = this.R.bn.iterator();

      while(var3.hasNext()) {
         o var4 = (o)var3.next();
         if(var4 instanceof i) {
            i var5 = (i)var4;
            if(this.b(var5.S, var5.T)) {
               float var6 = var5.d(this.S, this.T);
               if(var2 == null || var6 < var1) {
                  var1 = var6;
                  var2 = var5;
               }
            }
         }
      }

      return var2;
   }

   public void d(float var1) {
      if(this.k == null || this.k.V) {
         this.k();
      }

      if(this.c && this.g != null) {
         if(this.e && !this.f) {
            if((double)(this.g.cu / this.g.cv) < 0.5D) {
               this.f = true;
               if(this.z > 100.0F) {
                  this.z = 100.0F;
               }
            }

            if(this.w == null) {
               this.k();
            }
         } else {
            if((double)(this.g.cu / this.g.cv) > 0.6D) {
               this.f = false;
            }

            boolean var2 = false;
            if(this.k != null && !this.k.t) {
               var2 = true;
            }

            if(!var2) {
               boolean var3 = true;
               i var4 = this.R.a(this.g.h(), this.g.eo, this.g.ep, var3);
               if(var4 != null) {
                  this.k = var4;
               }

               if(this.k != null) {
                  PointF var5 = this.k.w();
                  this.S = var5.a;
                  this.T = var5.b;
                  if(this.z > 100.0F) {
                     this.z = 100.0F;
                  }

                  this.a("moving to new base");
               }
            }
         }
      }

      if(this.k != null) {
         for(int var6 = 0; var6 < 2; ++var6) {
            if(this.p == 0.0F) {
               am var7 = this.k.g();
               if(var7 == null) {
                  break;
               }

               if(this.a(var7, false)) {
                  this.w = var7;
                  this.p = 500.0F;
                  this.n = 2000.0F;
                  if(!this.f) {
                     this.S = var7.eo;
                     this.T = var7.ep;
                  }

                  if(this.z > 100.0F) {
                     this.z = 100.0F;
                  }

                  this.a("defending base");
               }
            }
         }

         if(this.p == 0.0F) {
            this.f = false;
            this.w = null;
         }
      }

   }

   public void e(float var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(!this.v) {
         if(this.l == 0.0F) {
            this.v = true;
            this.q = true;
         }
      } else {
         if(this.w == null || !this.w.bT() || this.w.bV || !this.r) {
            this.w = this.R.as();
            if(this.w != null && !this.a(this.w, true)) {
               this.w = null;
            }
         }

         if(this.w != null) {
            boolean var3;
            if(this.q) {
               this.u += var1;
               if(!this.r) {
                  this.t = com.corrodinggames.rts.gameFramework.f.a(this.t, var1);
                  if(this.t == 0.0F) {
                     this.t = 20.0F;
                     this.h();
                  }
               } else {
                  var3 = false;
                  Iterator var4 = this.F.iterator();

                  y var5;
                  while(var4.hasNext()) {
                     var5 = (y)var4.next();
                     if(this.c(var5) > 28900.0F) {
                        var3 = true;
                     }
                  }

                  if(!var3) {
                     this.q = false;
                  }

                  var4 = this.F.iterator();

                  while(var4.hasNext()) {
                     var5 = (y)var4.next();
                     if(var5.bs > var2.by - 1000) {
                        this.q = false;
                        this.a("Not staging due to damage");
                     }
                  }
               }

               if(this.u > 17000.0F) {
                  this.q = false;
                  this.a("attacking target");
               }
            } else {
               this.o += var1;
               if(this.z == 0.0F) {
                  this.z = 800.0F;
                  var3 = false;
                  com.corrodinggames.rts.gameFramework.utility.m var8 = new com.corrodinggames.rts.gameFramework.utility.m();
                  Iterator var9 = this.F.iterator();

                  while(var9.hasNext()) {
                     y var6 = (y)var9.next();
                     boolean var7 = true;
                     if(this.w != null) {
                        if(!this.R.a((am)var6, this.w)) {
                           var7 = false;
                        }

                        if(var7 && !aq.a(var6, this.w)) {
                           var7 = false;
                        }
                     }

                     if(var7) {
                        var3 = true;
                        var8.add(var6);
                     }
                  }

                  if(!var3) {
                     this.q = false;
                     this.a("cannot reach main target");
                  } else {
                     com.corrodinggames.rts.gameFramework.e var10 = var2.cf.a((com.corrodinggames.rts.game.n)this.R);
                     var10.a((AbstractList)var8);
                     boolean var11 = true;
                     if(this.w != null && com.corrodinggames.rts.gameFramework.f.a(0, 100) < 80) {
                        var10.a(this.w.eo, this.w.ep, var11);
                     } else {
                        var10.a(this.w, var11);
                     }

                     this.a("attacking main target");
                  }
               }
            }
         }
      }

      if(this.v) {
         if(this.F.size() == 0) {
            this.p();
         }

         if(this.o > 1000.0F && this.F.size() < 3) {
            this.p();
         }

         if(this.o > 11000.0F) {
            this.p();
         }
      }

   }

   public void h() {
      float var1 = this.w.eo;
      float var2 = this.w.ep;
      float var3 = com.corrodinggames.rts.gameFramework.f.d(var1, var2, this.S, this.T);
      float var4 = com.corrodinggames.rts.gameFramework.f.b(var1, var2, this.S, this.T);
      if(com.corrodinggames.rts.gameFramework.f.a(0, 100) < 80) {
         var3 += (float)com.corrodinggames.rts.gameFramework.f.a(-110, 110);
      }

      int var5 = (int)((double)var4 * 0.6D);
      if(var5 < 720) {
         var5 = 720;
      }

      float var6 = (float)com.corrodinggames.rts.gameFramework.f.a(50, var5);
      if(com.corrodinggames.rts.gameFramework.f.a(0, 100) < 80 && var6 < 450.0F) {
         var6 = (float)com.corrodinggames.rts.gameFramework.f.a(450, var5);
      }

      var1 += com.corrodinggames.rts.gameFramework.f.k(var3) * var6;
      var2 += com.corrodinggames.rts.gameFramework.f.j(var3) * var6;
      boolean var7 = true;
      if(!this.a(var1, var2)) {
         var7 = false;
      }

      boolean var8 = false;
      boolean var9 = false;
      Iterator var10 = this.F.iterator();

      y var11;
      while(var10.hasNext()) {
         var11 = (y)var10.next();
         if(var11.h() == ao.b) {
            var8 = true;
         }

         if(var11.h() == ao.e) {
            var9 = true;
         }
      }

      if(var8) {
         if(this.R.aG == 0 && !this.b(var1, var2)) {
            var7 = false;
         }

         if(!this.R.a(var1, var2, this.w.eo, this.w.ep, ao.b) && com.corrodinggames.rts.gameFramework.f.a(0, 100) < 98) {
            var7 = false;
         }
      }

      if(var9) {
         if(!this.b(var1, var2)) {
            var7 = false;
         }

         if(!this.R.a(var1, var2, this.w.eo, this.w.ep, ao.e)) {
            var7 = false;
         }
      }

      if(var7) {
         this.S = var1;
         this.T = var2;
         this.z = 0.0F;
         this.r = true;
         this.G.clear();
         var10 = this.F.iterator();

         while(var10.hasNext()) {
            var11 = (y)var10.next();
            if(var11.h() != ao.e && !this.R.a(var11, this.S, this.T)) {
               this.G.add(var11);
            }
         }
      }

   }

   public ao i() {
      return this.E;
   }

   public ao j() {
      if(this.F.size() == 0) {
         return this.B?ao.e:ao.b;
      } else {
         boolean var1 = true;
         Iterator var2 = this.F.iterator();

         while(var2.hasNext()) {
            y var3 = (y)var2.next();
            ao var4 = var3.h();
            if(var4 != ao.d) {
               var1 = false;
               break;
            }
         }

         if(var1) {
            return ao.d;
         } else {
            ao var5;
            boolean var6;
            Iterator var7;
            y var8;
            if(this.B) {
               var6 = true;
               var7 = this.F.iterator();

               while(var7.hasNext()) {
                  var8 = (y)var7.next();
                  var5 = var8.h();
                  if(var5 == ao.e) {
                     var6 = false;
                  }
               }

               if(var6) {
                  return ao.f;
               } else {
                  return ao.e;
               }
            } else {
               var6 = true;
               var7 = this.F.iterator();

               while(var7.hasNext()) {
                  var8 = (y)var7.next();
                  var5 = var8.h();
                  if(var5 == ao.b || var5 == ao.g) {
                     var6 = false;
                  }
               }

               if(var6) {
                  return ao.f;
               } else {
                  return ao.b;
               }
            }
         }
      }
   }

   public boolean a(float var1, float var2) {
      return !com.corrodinggames.rts.gameFramework.utility.y.a(var1, var2, this.i());
   }

   public boolean b(float var1, float var2) {
      Iterator var3 = this.F.iterator();

      y var4;
      do {
         if(!var3.hasNext()) {
            return true;
         }

         var4 = (y)var3.next();
      } while(this.R.a(var4, var1, var2));

      return false;
   }

   public boolean a(am var1, boolean var2) {
      Iterator var3 = this.F.iterator();

      y var4;
      do {
         if(!var3.hasNext()) {
            return false;
         }

         var4 = (y)var3.next();
      } while(!var2 && !this.R.a(var4, var1.eo, var1.ep) || !aq.a(var4, var1));

      return true;
   }

   public void k() {
      boolean var1 = true;
      PointF var2 = null;
      if(this.c && this.g != null) {
         this.S = this.g.eo;
         this.T = this.g.ep;
         this.k = this.R.c(this.g.eo, this.g.ep);
      } else {
         if(var1) {
            for(int var3 = 0; var3 < 7; ++var3) {
               boolean var4 = var3 > 3;
               if(var2 == null) {
                  Iterator var5 = this.R.bn.iterator();

                  while(var5.hasNext()) {
                     o var6 = (o)var5.next();
                     if(var6 instanceof i) {
                        i var7 = (i)var6;
                        if(var7.b == j.c && (var7.u() > 2 || var4) && (var2 == null || com.corrodinggames.rts.gameFramework.f.c(this.R.ay + 2) == 0)) {
                           for(int var8 = 0; var8 < 10; ++var8) {
                              if(var2 == null) {
                                 PointF var9 = var7.w();
                                 if(this.a(var9.a, var9.b)) {
                                    var2 = var9;
                                 }
                              }
                           }

                           this.k = var7;
                        }
                     }
                  }
               }
            }
         }

         if(var2 == null) {
            var2 = this.R.am();
            this.k = null;
         }

         this.S = var2.a;
         this.T = var2.b;
      }
   }
}
