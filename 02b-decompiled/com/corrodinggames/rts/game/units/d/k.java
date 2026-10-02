package com.corrodinggames.rts.game.units.d;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.d.f;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.l;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class k {

   y a;
   public PointF b = null;
   public final com.corrodinggames.rts.gameFramework.utility.m c = new com.corrodinggames.rts.gameFramework.utility.m();
   final com.corrodinggames.rts.gameFramework.utility.m d = new com.corrodinggames.rts.gameFramework.utility.m();
   public float e;
   j f;


   public strictfp k(y var1) {
      this.a = var1;
   }

   public strictfp void a(as var1) {
      var1.a(this.e);
      var1.a(this.c.size());
      Iterator var2 = this.c.iterator();

      while(var2.hasNext()) {
         bq var3 = (bq)var2.next();
         var3.a(var1);
      }

      var1.a(this.b != null);
      if(this.b != null) {
         var1.a(this.b.a);
         var1.a(this.b.b);
      }

   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.e = var1.g();
      int var2 = var1.f();
      this.c.clear();

      for(int var3 = 0; var3 < var2; ++var3) {
         j var4 = new j();
         var4.a(var1);
         if(com.corrodinggames.rts.game.units.a.s.c(var4.j)) {
            com.corrodinggames.rts.game.units.a.s var5 = this.a.a(var4.j);
            if(var5 == null) {
               com.corrodinggames.rts.gameFramework.l.b("Factory", this.a.r() + " no longer has the action:" + var4.j);
            } else {
               this.c.add(var4);
            }
         } else {
            com.corrodinggames.rts.gameFramework.l.b("Factory", "buildQueue has uIndex of -1, skipping");
         }
      }

      if(var1.b() >= 5) {
         boolean var6 = var1.e();
         if(var6) {
            if(this.b == null) {
               this.b = new PointF();
            }

            this.b.a = var1.g();
            this.b.b = var1.g();
         } else {
            this.b = null;
         }
      }

   }

   public strictfp am a(j var1, float var2, boolean var3, float var4) {
      com.corrodinggames.rts.game.units.a.s var5 = this.a.a(var1.j);
      if(var5 == null) {
         ad.a("specialAction=null on completeQueueItem for item.uIndex:" + var1.j + " id:" + this.a.eh, true);
         return null;
      } else {
         com.corrodinggames.rts.game.units.as var6 = var5.i();
         if(var6 == null) {
            ad.a("unitType=null on completeQueueItem for item.uIndex:" + var1.j + " id:" + this.a.eh, false);
            return null;
         } else {
            return this.a(var6, var2, var3, var4);
         }
      }
   }

   public strictfp void a(am var1, float var2, boolean var3) {
      var1.cl = 30.0F;
      if(this.a instanceof f) {
         var1.cl += 40.0F;
      }

      if(var1 instanceof y) {
         y var4 = (y)var1;
         var4.j(90.0F);
         if((double)var4.z() < 0.75D) {
            var1.cl += 30.0F;
         }

         if((double)var4.z() < 0.55D) {
            var1.cl += 20.0F;
         }

         float var5 = var3?0.0F:1.0F;
         float var7 = this.a.eo + com.corrodinggames.rts.gameFramework.f.k(var1.cg) * var2;
         float var8 = this.a.ep + com.corrodinggames.rts.gameFramework.f.j(var1.cg) * var2;
         if(this.b != null) {
            if(var2 != 0.0F) {
               var4.d(var7, var8);
            }

            var4.d(this.b.a + var5, this.b.b);
         } else {
            var7 -= com.corrodinggames.rts.gameFramework.f.j(var1.cg) * var5;
            var8 += com.corrodinggames.rts.gameFramework.f.k(var1.cg) * var5;
            if(var2 != 0.0F) {
               var4.d(var7, var8);
            }
         }
      }

   }

   public strictfp am a(com.corrodinggames.rts.game.units.as var1, float var2, boolean var3, float var4) {
      am var5 = null;
      var5 = var1.a();
      var5.eo = this.a.eo;
      var5.ep = this.a.ep + 5.0F;
      var5.cg = 90.0F + var4;
      var5.f(this.a.bX);
      var5.B(this.a);
      this.a(var5, var2, var3);
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      if(var5.bX == var6.bs) {
         var6.bS.i.a(var5);
      }

      return var5;
   }

   public final strictfp boolean a() {
      return this.c.a == 0;
   }

   public strictfp j a(com.corrodinggames.rts.game.units.a.w var1, boolean var2) {
      return this.a(var1, var2, (PointF)null, (am)null);
   }

   public strictfp j a(com.corrodinggames.rts.game.units.a.w var1, boolean var2, PointF var3, am var4) {
      j var5 = new j();
      var5.j = var1.N();
      var5.h = var3;
      var5.i = var4;
      if(var5.j == null) {
         throw new RuntimeException("item.uIndex==null??");
      } else {
         var5.a = 1;
         var5.b = var1.K();
         var5.c = var1.B();
         var5.d = var1.r_();
         var5.e = var1.P();
         var5.f = var1.g();
         var5.g = var1.i();
         var5.l = var1.H();
         if(!var2) {
            com.corrodinggames.rts.game.n.b((am)this.a);
            if(var5.l) {
               int var6 = 0;

               for(int var7 = 0; var7 < this.c.size() && ((j)this.c.get(var7)).l; ++var7) {
                  var6 = var7 + 1;
               }

               if(var6 == 0 && this.c.size() != 0) {
                  ;
               }

               this.c.add(var6, var5);
            } else {
               this.c.add(var5);
            }

            com.corrodinggames.rts.game.n.c((am)this.a);
         } else {
            this.d.add(var5);
         }

         return var5;
      }
   }

   public strictfp j b(com.corrodinggames.rts.game.units.a.w var1, boolean var2) {
      if(var2) {
         if(this.a(var1.N(), true) > 0) {
            j var6 = this.a(var1, true);
            var6.k = true;
            return var6;
         } else {
            return null;
         }
      } else {
         com.corrodinggames.rts.gameFramework.utility.m var3 = this.c;
         ListIterator var4 = var3.listIterator(var3.size());

         j var5;
         do {
            if(!var4.hasPrevious()) {
               return null;
            }

            var5 = (j)var4.previous();
         } while(!var5.j.equals(var1.N()));

         com.corrodinggames.rts.game.n.b((am)this.a);
         var4.remove();
         com.corrodinggames.rts.game.n.c((am)this.a);
         return var5;
      }
   }

   public strictfp void a(j var1) {
      this.f = var1;
      this.a.bC();
   }

   public strictfp j b() {
      return this.f;
   }

   public strictfp com.corrodinggames.rts.game.units.custom.d.b c() {
      if(this.f == null) {
         return null;
      } else if(this.f.d == null) {
         return null;
      } else {
         float var1 = this.f.b * this.a.cx() * 60.0F;
         return com.corrodinggames.rts.game.units.custom.d.b.a(this.f.d, -var1);
      }
   }

   public strictfp com.corrodinggames.rts.game.units.a.s d() {
      if(this.f != null) {
         com.corrodinggames.rts.game.units.a.s var1 = this.a.a(this.f.j);
         return var1;
      } else {
         return null;
      }
   }

   public strictfp void a(float var1) {
      j var2;
      if(!this.a()) {
         var2 = (j)this.f().get(0);
         if(this.f != var2) {
            if(var2.m < 0.0F) {
               var2.m = 0.0F;
               ((l)this.a).b(var2);
            }

            if(this.f != null) {
               this.e = var2.m;
            }

            this.a(var2);
         }

         float var3 = var2.b * this.a.cx() * var1;
         boolean var4 = false;
         if(var2.d != null) {
            if(this.e + var3 > 1.0F) {
               var3 = 1.0F - this.e;
               var4 = true;
            }

            double var5 = (double)(this.e + var3) - var2.n;
            double var7 = 0.0D;
            if(var4) {
               var7 = 1.0D - var2.n;
            } else {
               double var9 = 0.009999999776482582D;
               if(var5 >= var9) {
                  int var11 = (int)(var5 / var9);
                  var7 = (double)var11 * var9;
               }
            }

            boolean var14 = false;
            if(var7 > 0.0D && this.a.bX.am.a(var2.d)) {
               var14 = true;
            }

            if(!var14 && (var7 <= 0.0D || var2.d.c(this.a, var7))) {
               var2.n += var7;
            } else {
               if(!var14) {
                  this.a.bX.am.a(var2.d, this.a, var7);
               }

               var3 = 0.0F;
               var4 = false;
            }
         }

         this.e += var3;
         if(var4) {
            this.e = 1.0F;
         }

         var2.m = this.e;
         if(this.e >= 1.0F) {
            if(var2.f && ((l)this.a).dA()) {
               this.e = 1.0F;
            } else {
               com.corrodinggames.rts.game.n.b((am)this.a);
               this.e = 0.0F;
               --var2.a;
               if(var2.a <= 0) {
                  List var13 = this.f();
                  if(var13.size() == 0) {
                     com.corrodinggames.rts.gameFramework.l.b("-------------buildQueue empty for:" + var2.j);
                     com.corrodinggames.rts.gameFramework.l.b("-------------");
                  } else {
                     var13.remove(0);
                  }
               }

               com.corrodinggames.rts.game.n.c((am)this.a);
               ((l)this.a).a(var2);
            }
         }
      } else {
         this.a((j)null);
         this.e = 0.0F;
         if(this.d.a > 0) {
            var2 = (j)this.d.get(0);
            if(var2.b > 10.0F && var2.m <= 0.0F) {
               var2.m = 1.0F;
               com.corrodinggames.rts.game.units.a.s var12 = this.a.a(var2.j);
               if(var12 != null && var12.Q()) {
                  var12.a(this.a);
               }
            }
         }
      }

   }

   public strictfp void e() {
      Iterator var1 = this.c.iterator();

      while(var1.hasNext()) {
         j var2 = (j)var1.next();
         com.corrodinggames.rts.game.units.a.s var3 = this.a.a(var2.j);
         if(var3 == null) {
            this.b(var2);
            this.c(var2);
            var1.remove();
         }
      }

   }

   public strictfp void a(boolean var1) {
      Iterator var2 = this.c.iterator();

      while(var2.hasNext()) {
         j var3 = (j)var2.next();
         if(var1) {
            this.b(var3);
         }

         this.c(var3);
         var2.remove();
      }

   }

   private strictfp void b(j var1) {
      if(((l)this.a).c(var1)) {
         if(var1.d != null && var1.n > 0.0D) {
            var1.d.a(this.a, var1.n, true);
         }

         var1.c.h(this.a);
      }

   }

   private strictfp void c(j var1) {}

   public strictfp int a(com.corrodinggames.rts.game.units.as var1) {
      int var2 = 0;
      int var3 = this.c.a;
      if(var3 != 0) {
         Object[] var4 = this.c.a();

         for(int var5 = 0; var5 < var3; ++var5) {
            j var6 = (j)var4[var5];
            if(var6.f) {
               com.corrodinggames.rts.game.units.as var7 = var6.g;
               if(var7 == var1) {
                  var2 += var6.a;
               }
            }
         }
      }

      return var2;
   }

   public strictfp int a(com.corrodinggames.rts.game.units.a.c var1, boolean var2) {
      return this.a(var1, var2, false);
   }

   public strictfp int a(com.corrodinggames.rts.game.units.custom.g var1) {
      if(var1 == null) {
         return this.c.a;
      } else {
         int var2 = 0;
         Iterator var3 = this.c.iterator();

         while(var3.hasNext()) {
            j var4 = (j)var3.next();
            if(com.corrodinggames.rts.game.units.custom.g.a(var1, var4.e)) {
               ++var2;
            }
         }

         return var2;
      }
   }

   public strictfp int a(com.corrodinggames.rts.game.units.a.c var1, boolean var2, boolean var3) {
      int var4 = 0;
      Iterator var5;
      j var6;
      if(this.c.a != 0) {
         var5 = this.c.iterator();

         while(var5.hasNext()) {
            var6 = (j)var5.next();
            if((com.corrodinggames.rts.game.units.a.s.i == var1 || var6.j.equals(var1)) && (!var3 || var6.f)) {
               var4 += var6.a;
            }
         }
      }

      if(var2 && this.d.a != 0) {
         var5 = this.d.iterator();

         while(var5.hasNext()) {
            var6 = (j)var5.next();
            if((com.corrodinggames.rts.game.units.a.s.i == var1 || var6.j.equals(var1)) && (!var3 || var6.f)) {
               if(!var6.k) {
                  var4 += var6.a;
               } else {
                  var4 -= var6.a;
               }
            }
         }
      }

      return var4;
   }

   public strictfp com.corrodinggames.rts.game.units.a.s b(com.corrodinggames.rts.game.units.as var1) {
      ArrayList var2 = this.a.N();
      int var3 = 0;

      for(int var4 = var2.size(); var3 < var4; ++var3) {
         com.corrodinggames.rts.game.units.a.s var5 = (com.corrodinggames.rts.game.units.a.s)var2.get(var3);
         if(var5 != null && var5 instanceof com.corrodinggames.rts.game.units.a.w) {
            com.corrodinggames.rts.game.units.a.w var6 = (com.corrodinggames.rts.game.units.a.w)var5;
            if(var6.i() == var1) {
               return var6;
            }
         }
      }

      return null;
   }

   public strictfp j a(com.corrodinggames.rts.game.units.a.s var1, boolean var2, PointF var3, am var4) {
      if(var1 instanceof com.corrodinggames.rts.game.units.a.w) {
         com.corrodinggames.rts.game.units.a.w var5 = (com.corrodinggames.rts.game.units.a.w)var1;
         if(!var2) {
            if(var1.a(this.a, false) && var1.b((am)this.a) && (!var5.g() || this.a.bX.w() < this.a.bX.x()) && var5.B().c(this.a)) {
               return this.a(var5, false, var3, var4);
            }
         } else {
            j var6 = this.b(var5, false);
            if(var6 != null) {
               this.b(var6);
               this.c(var6);
               return var6;
            }
         }
      }

      return null;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1, boolean var2) {
      if(var1 instanceof com.corrodinggames.rts.game.units.a.w) {
         com.corrodinggames.rts.game.units.a.w var3 = (com.corrodinggames.rts.game.units.a.w)var1;
         if(!var2) {
            if(var1.a(this.a, true) && (!var3.g() || this.a.bX.w() < this.a.bX.x()) && var3.B().b(this.a, var1.Q())) {
               this.a(var3, true);
            }
         } else if(this.b(var3, true) != null) {
            var3.B().e(this.a, var1.Q());
         }
      }

   }

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1) {
      if(this.d.size() != 0) {
         j var2 = null;
         Iterator var3 = this.d.iterator();

         while(var3.hasNext()) {
            j var4 = (j)var3.next();
            if(var4.j.equals(var1.N())) {
               var2 = var4;
            }
         }

         if(var2 != null) {
            if(!var2.k) {
               var2.c.e(this.a, var1.Q());
            } else {
               var2.c.d(this.a, var1.Q());
            }

            this.d.remove(var2);
         }
      }

   }

   public strictfp List f() {
      return this.c;
   }

   public strictfp com.corrodinggames.rts.gameFramework.utility.m g() {
      return this.c;
   }
}
