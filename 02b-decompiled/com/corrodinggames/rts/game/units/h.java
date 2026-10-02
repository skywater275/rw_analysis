package com.corrodinggames.rts.game.units;

import android.graphics.Paint;
import android.graphics.Paint$Cap;
import android.graphics.PointF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d;
import com.corrodinggames.rts.game.units.h$1;
import com.corrodinggames.rts.game.units.h$10;
import com.corrodinggames.rts.game.units.h$11;
import com.corrodinggames.rts.game.units.h$12;
import com.corrodinggames.rts.game.units.h$13;
import com.corrodinggames.rts.game.units.h$14;
import com.corrodinggames.rts.game.units.h$15;
import com.corrodinggames.rts.game.units.h$16;
import com.corrodinggames.rts.game.units.h$17;
import com.corrodinggames.rts.game.units.h$18;
import com.corrodinggames.rts.game.units.h$19;
import com.corrodinggames.rts.game.units.h$2;
import com.corrodinggames.rts.game.units.h$20;
import com.corrodinggames.rts.game.units.h$21;
import com.corrodinggames.rts.game.units.h$22;
import com.corrodinggames.rts.game.units.h$23;
import com.corrodinggames.rts.game.units.h$3;
import com.corrodinggames.rts.game.units.h$4;
import com.corrodinggames.rts.game.units.h$5;
import com.corrodinggames.rts.game.units.h$6;
import com.corrodinggames.rts.game.units.h$7;
import com.corrodinggames.rts.game.units.h$8;
import com.corrodinggames.rts.game.units.h$9;
import com.corrodinggames.rts.game.units.i;
import com.corrodinggames.rts.game.units.j;
import com.corrodinggames.rts.game.units.k;
import com.corrodinggames.rts.game.units.l;
import com.corrodinggames.rts.game.units.m;
import com.corrodinggames.rts.game.units.n;
import com.corrodinggames.rts.game.units.o;
import com.corrodinggames.rts.game.units.p;
import com.corrodinggames.rts.game.units.q;
import com.corrodinggames.rts.game.units.r;
import com.corrodinggames.rts.game.units.y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class h extends com.corrodinggames.rts.game.units.e.j implements d {

   PointF[] a = new PointF[6];
   PointF[] b;
   boolean c;
   static Paint d;
   static Paint e;
   static Paint f;
   static com.corrodinggames.rts.gameFramework.m.e g;
   static com.corrodinggames.rts.game.units.a.s h = new h$1("reloadUnits");
   static com.corrodinggames.rts.game.units.a.s i = new h$12("reloadOnlyActiveUnits");
   static com.corrodinggames.rts.game.units.a.s j = new h$17("unitClone");
   static com.corrodinggames.rts.game.units.a.s k = new h$18("removeUnits");
   static com.corrodinggames.rts.game.units.a.s l = new h$19("killUnits");
   static com.corrodinggames.rts.game.units.a.s m = new h$20("finishQueue");
   static com.corrodinggames.rts.game.units.a.s n = new h$21("nukeAt");
   static com.corrodinggames.rts.game.units.a.s o = new h$22("freezeAI");
   static com.corrodinggames.rts.game.units.a.s p = new h$23("changeAlliance");
   static com.corrodinggames.rts.game.units.a.s q = new h$2("startRecording");
   String r;
   static com.corrodinggames.rts.game.units.a.s s = new h$3("startReplayPlayback");
   static com.corrodinggames.rts.game.units.a.s t = new h$4("hideInterface");
   static com.corrodinggames.rts.game.units.a.s u = new h$5("freezeAllAI");
   static com.corrodinggames.rts.game.units.a.s v = new h$6("pauseGame");
   static com.corrodinggames.rts.game.units.a.s w = new h$7("slowGame");
   static com.corrodinggames.rts.game.units.a.s x = new h$8("fastForward");
   static com.corrodinggames.rts.game.units.a.s y = new h$9("search");
   static com.corrodinggames.rts.game.units.a.s z = new h$10("enableDebug");
   static com.corrodinggames.rts.game.units.a.s A = new h$11("enableAIDebug");
   static com.corrodinggames.rts.game.units.a.s B = new h$13("enableTriggerDebug");
   static com.corrodinggames.rts.game.units.a.s C = new h$14("clearSaveHistory");
   static ArrayList D;
   com.corrodinggames.rts.gameFramework.i.b E;
   o F;
   n G;
   String H;
   boolean I;
   String J;
   static com.corrodinggames.rts.game.units.a.b K = new h$16();


   public strictfp ar f() {
      return ar.Y;
   }

   public static strictfp boolean w() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      return var0.cb.i();
   }

   public strictfp PointF[] b() {
      return this.a;
   }

   public strictfp PointF[] e_() {
      return this.b;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e v() {
      return this.bX.k == -1?null:dN[this.bX.R()];
   }

   public static strictfp void K() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      g = var0.bO.a(R$drawable.icon_search);
   }

   public strictfp boolean a(am var1) {
      return true;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d() {
      return this.bV?com.corrodinggames.rts.game.units.e.b.b:com.corrodinggames.rts.game.units.e.b.d[this.bX.R()];
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return null;
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e d(int var1) {
      return null;
   }

   public strictfp boolean e() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      var1.bR.b(this.eo, this.ep, this.eq);
      this.M = com.corrodinggames.rts.game.units.e.b.b;
      this.S(0);
      this.bT = false;
      var1.bM.a(com.corrodinggames.rts.gameFramework.a.e.o, 0.8F, this.eo, this.ep);
      this.bq();
      return true;
   }

   public strictfp h(boolean var1) {
      super(var1);
      this.b = new PointF[this.a.length];
      d = new Paint();
      d.a(40, 0, 255, 0);
      d.a(true);
      d.a(2.0F);
      d.a(Paint$Cap.b);
      e = new Paint();
      e.a(d);
      e.a(55, 255, 60, 60);
      f = new Paint();
      f.a(60, 255, 255, 255);
      this.E = null;
      this.F = o.a;
      this.G = n.a;
      this.I = true;
      this.T(20);
      this.U(20);
      this.cj = 10.0F;
      this.eo = -1000.0F;
      this.ep = -1000.0F;
      this.ck = this.cj;
      this.cv = 170000.0F;
      this.cu = this.cv;
      this.M = com.corrodinggames.rts.game.units.e.b.b;

      for(int var2 = 0; var2 < this.a.length; ++var2) {
         this.a[var2] = new PointF();
         this.b[var2] = new PointF();
      }

   }

   public static strictfp void a(float var0, d var1) {
      y var2 = (y)var1;
      PointF[] var3 = var1.b();
      PointF[] var4 = var1.e_();
      am var5 = var2.X();
      var2.aN = var5 != null;
      int var6;
      PointF var7;
      PointF var8;
      if(var5 != null) {
         for(var6 = 0; var6 < var3.length; ++var6) {
            var7 = var3[var6];
            var8 = var4[var6];
            var7.a = com.corrodinggames.rts.gameFramework.f.a(var7.a, var8.a, 0.1F * var0);
            var7.b = com.corrodinggames.rts.gameFramework.f.a(var7.b, var8.b, 0.1F * var0);
            var7.a += (var8.a - var7.a) * 0.04F * var0;
            var7.b += (var8.b - var7.b) * 0.04F * var0;
            float var9 = var5.cj * 0.75F;
            if(com.corrodinggames.rts.gameFramework.f.c(var7.a - var8.a) < 1.0F) {
               var8.a = com.corrodinggames.rts.gameFramework.f.d(-var9, var9);
            }

            if(com.corrodinggames.rts.gameFramework.f.c(var7.b - var8.b) < 1.0F) {
               var8.b = com.corrodinggames.rts.gameFramework.f.d(-var9, var9);
            }
         }
      } else if(var3[0].a != 0.0F || var3[0].b != 0.0F) {
         for(var6 = 0; var6 < var3.length; ++var6) {
            var7 = var3[var6];
            var8 = var4[var6];
            var7.a = 0.0F;
            var7.b = 0.0F;
            var8.a = 0.0F;
            var8.b = 0.0F;
         }
      }

   }

   public strictfp void a(float var1) {
      if(var1 < 0.3F) {
         var1 = 0.3F;
      }

      if(this.ax && this.bX.b()) {
         for(int var2 = 0; var2 < com.corrodinggames.rts.game.n.c; ++var2) {
            com.corrodinggames.rts.game.n var3 = com.corrodinggames.rts.game.n.k(var2);
            if(var3 != null && !var3.b()) {
               this.e(var3);
               break;
            }
         }
      }

      super.a(var1);
      if(!this.bV) {
         a(var1, this);
      }

      this.cu = this.cv;
   }

   public strictfp void a(float var1, boolean var2) {
      if(!this.bV) {
         ;
      }

   }

   public strictfp float e(int var1) {
      return 0.0F;
   }

   public strictfp float f(int var1) {
      return 0.0F;
   }

   public strictfp boolean c(float var1) {
      if(!super.c(var1)) {
         return false;
      } else {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         return true;
      }
   }

   public strictfp void a(am var1, int var2) {}

   public strictfp boolean b_() {
      return false;
   }

   public strictfp int y() {
      return 850000;
   }

   public strictfp float b(am var1) {
      return 1.0E7F;
   }

   public strictfp float c(am var1) {
      return 1.0E7F;
   }

   public strictfp float m() {
      return 30.0F;
   }

   public strictfp float b(int var1) {
      return 100.0F;
   }

   public strictfp float z() {
      return 0.0F;
   }

   public strictfp float A() {
      return 9.8F;
   }

   public strictfp float B() {
      return 9.35F;
   }

   public strictfp float c(int var1) {
      return 99.0F;
   }

   public strictfp boolean l() {
      return false;
   }

   public strictfp float C() {
      return 0.04F;
   }

   public strictfp float D() {
      return 0.1F;
   }

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1, boolean var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var5;
      if(var1 instanceof k) {
         k var4 = (k)var1;
         var5 = true;
         if(var2) {
            var5 = !var5;
         }

         if(var4.a) {
            var5 = !var5;
         }

         int var7;
         com.corrodinggames.rts.game.n var8;
         if(var5) {
            Object var6 = null;

            for(var7 = this.bX.k + 1; var7 < com.corrodinggames.rts.game.n.c; ++var7) {
               var8 = com.corrodinggames.rts.game.n.k(var7);
               if(var8 != null && !var8.b()) {
                  var6 = var8;
                  break;
               }
            }

            if(var6 == null && this.bX.k < 4) {
               var6 = com.corrodinggames.rts.game.n.k(this.bX.k + 1);
               if(var6 == null) {
                  com.corrodinggames.rts.gameFramework.l.e("Sandbox adding new team:" + this.bX.k);
                  com.corrodinggames.rts.game.a.a var14 = new com.corrodinggames.rts.game.a.a(this.bX.k + 1);
                  var6 = var14;
                  var14.r = 1;
                  var14.F = true;
                  var14.G = true;
                  if(!this.c) {
                     var14.bG = 0.0F;
                  } else {
                     var14.bG = Float.MAX_VALUE;
                  }
               }
            }

            if(var6 == null) {
               for(var7 = 0; var7 < com.corrodinggames.rts.game.n.c; ++var7) {
                  var8 = com.corrodinggames.rts.game.n.k(var7);
                  if(var8 != null && !var8.b()) {
                     var6 = var8;
                     break;
                  }
               }
            }

            if(var6 != null) {
               this.e((com.corrodinggames.rts.game.n)var6);
               if(!var3.cb.j()) {
                  var3.bs = (com.corrodinggames.rts.game.n)var6;
               }
            }
         } else {
            com.corrodinggames.rts.game.n var15 = null;

            for(var7 = this.bX.k - 1; var7 >= 0; --var7) {
               var8 = com.corrodinggames.rts.game.n.k(var7);
               if(var8 != null && !var8.b()) {
                  var15 = var8;
                  break;
               }
            }

            if(var15 == null) {
               for(var7 = com.corrodinggames.rts.game.n.c - 1; var7 >= 0; --var7) {
                  var8 = com.corrodinggames.rts.game.n.k(var7);
                  if(var8 != null && !var8.b()) {
                     var15 = var8;
                     break;
                  }
               }
            }

            if(var15 != null) {
               this.e(var15);
               if(!var3.cb.j()) {
                  var3.bs = var15;
               }
            }
         }
      }

      if(var1 instanceof j) {
         j var12 = (j)var1;
         var5 = true;
         if(var2) {
            var5 = !var5;
         }

         if(var12.a) {
            var5 = !var5;
         }

         ArrayList var19 = var3.bZ.j();
         if(var19.size() == 0) {
            this.E = null;
         } else {
            boolean var17;
            com.corrodinggames.rts.gameFramework.i.b var18;
            if(var5) {
               if(this.E == null) {
                  this.E = (com.corrodinggames.rts.gameFramework.i.b)var19.get(0);
               } else {
                  var18 = null;
                  var17 = false;
                  Iterator var9 = var19.iterator();

                  while(var9.hasNext()) {
                     com.corrodinggames.rts.gameFramework.i.b var10 = (com.corrodinggames.rts.gameFramework.i.b)var9.next();
                     if(var17) {
                        var18 = var10;
                        break;
                     }

                     if(var10 == this.E) {
                        var17 = true;
                     }
                  }

                  this.E = var18;
               }
            } else if(this.E == null) {
               this.E = (com.corrodinggames.rts.gameFramework.i.b)var19.get(var19.size() - 1);
            } else {
               var18 = null;
               var17 = false;
               ArrayList var16 = new ArrayList();
               var16.addAll(var19);
               Collections.reverse(var16);
               Iterator var20 = var16.iterator();

               while(var20.hasNext()) {
                  com.corrodinggames.rts.gameFramework.i.b var11 = (com.corrodinggames.rts.gameFramework.i.b)var20.next();
                  if(var17) {
                     var18 = var11;
                     break;
                  }

                  if(var11 == this.E) {
                     var17 = true;
                  }
               }

               this.E = var18;
            }
         }
      }

      if(var1 instanceof l) {
         l var13 = (l)var1;
         var5 = true;
         if(var2) {
            var5 = !var5;
         }

         if(var13.a) {
            var5 = !var5;
         }

         this.F = this.F.a(!var5);
      }

      if(var1 instanceof i) {
         this.bX.d(10000.0F);
      }

      if(var1 instanceof m) {
         ((m)var1).n();
      }

   }

   static strictfp h L() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      return var0.bS.i();
   }

   public strictfp void a(com.corrodinggames.rts.game.units.a.s var1, boolean var2, PointF var3, am var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      if(var1 instanceof com.corrodinggames.rts.game.units.a.h) {
         var1 = ((com.corrodinggames.rts.game.units.a.h)var1).q_();
      }

      Iterator var6;
      com.corrodinggames.rts.game.n var7;
      if(var1 == h) {
         if(w()) {
            com.corrodinggames.rts.gameFramework.l.b("Not reloading units: Need to keep network sync");
            var5.bS.b("Not reloading units: Need to keep network sync");
            return;
         }

         if(var2) {
            return;
         }

         if(var5.bZ.h() == 0) {
            var5.bS.b("No custom units to reload");
            return;
         }

         com.corrodinggames.rts.gameFramework.l.e("Reload units requested");
         var5.bZ.a(true, false);
         var6 = com.corrodinggames.rts.game.n.c().iterator();

         while(var6.hasNext()) {
            var7 = (com.corrodinggames.rts.game.n)var6.next();
            if(var7 instanceof com.corrodinggames.rts.game.a.a) {
               ((com.corrodinggames.rts.game.a.a)var7).al();
            }
         }

         var5.bS.b("All custom unit files reloaded");
      }

      int var13;
      if(var1 == i) {
         if(w()) {
            com.corrodinggames.rts.gameFramework.l.b("Not reloading units: Need to keep network sync");
            return;
         }

         if(var2) {
            return;
         }

         if(var5.bZ.h() == 0) {
            var5.bS.b("No custom units to reload");
            return;
         }

         com.corrodinggames.rts.gameFramework.l.e("Reload active only requested");
         var5.bZ.a(true, true);
         var6 = com.corrodinggames.rts.game.n.c().iterator();

         while(var6.hasNext()) {
            var7 = (com.corrodinggames.rts.game.n)var6.next();
            if(var7 instanceof com.corrodinggames.rts.game.a.a) {
               ((com.corrodinggames.rts.game.a.a)var7).al();
            }
         }

         var13 = com.corrodinggames.rts.game.units.custom.ag.d;
         short var15 = 100;
         String var8 = "Quick reloaded changed data for " + var13 + " units active on map.";
         if(var5.bQ.liveReloading && var13 == 0) {
            var8 = var8 + " (Note: Live reloading is currently enabled, so changed units may have already be reloaded)";
            var15 = 170;
         }

         var5.bS.a(var8, var15);
      }

      if(var1 != k && var1 != l && var1 != j) {
         if(var1 == n) {
            if(var2) {
               return;
            }

            com.corrodinggames.rts.game.f var14 = com.corrodinggames.rts.game.units.d.q.a(this, var3.a, var3.b, var3.a, var3.b);
            if(var14 != null) {
               var14.eq = 100.0F;
               var14.j = null;
            }
         }

         if(var1 == m) {
            if(!var2) {
               var6 = am.bF().iterator();

               while(var6.hasNext()) {
                  com.corrodinggames.rts.gameFramework.w var26 = (com.corrodinggames.rts.gameFramework.w)var6.next();
                  if(var26 instanceof y && var26 instanceof com.corrodinggames.rts.game.units.d.l && com.corrodinggames.rts.gameFramework.f.a(var26.eo, var26.ep, var3.a, var3.b) < 2500.0F) {
                     com.corrodinggames.rts.game.units.d.l var28 = (com.corrodinggames.rts.game.units.d.l)var26;
                     var28.dz();
                  }
               }

            }
         } else {
            com.corrodinggames.rts.game.n var16;
            if(var1 == o) {
               var16 = this.bX;
               if(var16 instanceof com.corrodinggames.rts.game.a.a) {
                  com.corrodinggames.rts.game.a.a var20 = (com.corrodinggames.rts.game.a.a)var16;
                  if(var20.bG > 0.0F) {
                     var20.bG = 0.0F;
                  } else {
                     var20.bG = 10800.0F;
                  }
               }
            }

            if(var1 == p) {
               var16 = this.bX;
               ++var16.r;
               if(var16.r > 4) {
                  var16.r = 0;
               }
            }

            if(var1 == u) {
               boolean var18 = false;
               boolean var21 = false;
               Iterator var22 = com.corrodinggames.rts.game.n.c().iterator();

               while(var22.hasNext()) {
                  com.corrodinggames.rts.game.n var23 = (com.corrodinggames.rts.game.n)var22.next();
                  if(var23 instanceof com.corrodinggames.rts.game.a.a) {
                     com.corrodinggames.rts.game.a.a var27 = (com.corrodinggames.rts.game.a.a)var23;
                     if(var27.bG > 0.0F) {
                        var18 = true;
                     }

                     var21 = true;
                  }
               }

               boolean var25 = !var18;
               if(!var21) {
                  var25 = !this.c;
               }

               this.c = var25;
               this.M();
            }

            if(var1 == v) {
               ;
            }

            if(var1 == w) {
               ;
            }

            if(var1 == x) {
               ;
            }

            if(var1 == z) {
               var5.bl = !var5.bl;
            }

            if(var1 == A) {
               com.corrodinggames.rts.game.a.a.as = !com.corrodinggames.rts.game.a.a.as;
            }

            if(var1 == B) {
               var5.bn = !var5.bn;
            }

            if(var1 == C) {
               var5.bY.a();
            }

            if(var1 instanceof q) {
               q var24 = (q)var1;
               p.a(var24.a, var3);
            }

            super.a(var1, var2, var3, var4);
         }
      } else {
         var13 = 0;
         if(!var2) {
            Iterator var17 = am.bF().iterator();

            while(var17.hasNext()) {
               am var19 = (am)var17.next();
               if(var19 instanceof am) {
                  am var9 = var19;
                  if(com.corrodinggames.rts.gameFramework.f.a(var19.eo, var19.ep, var3.a, var3.b) < 2500.0F) {
                     if(var1 == k) {
                        if(var19.cN == null && var19.cO == null) {
                           var19.ci();
                           if(var19 instanceof y && var19.bI()) {
                              var5.bU.a((y)var19);
                           }
                        }
                     } else if(var1 == l) {
                        if(var19.cN == null && var19.cO == null) {
                           var19.cu = -1.0F;
                        }
                     } else if(var1 == j) {
                        if(var13 > 4) {
                           break;
                        }

                        if(!var19.bI() && !(var19 instanceof al) && !var19.bV && var19.cN == null && var19.cO == null) {
                           ++var13;
                           as var10 = var19.r();

                           for(int var11 = -25; var11 < 25; ++var11) {
                              am var12 = var10.a();
                              var12.eo = var9.eo + (float)var11 * 0.5F + 1.0F;
                              var12.ep = var9.ep + (float)var11 * 0.5F + 1.0F;
                              var12.b(var9.bX);
                              com.corrodinggames.rts.game.n.c(var12);
                              var12.cg = (float)com.corrodinggames.rts.gameFramework.f.a(var9, -180, 180, 25 + var11);
                              if(var12 instanceof y) {
                                 ((y)var12).ay = true;
                              }
                           }
                        }
                     }
                  }
               }
            }

         }
      }
   }

   public strictfp void M() {
      Iterator var1 = com.corrodinggames.rts.game.n.c().iterator();

      while(var1.hasNext()) {
         com.corrodinggames.rts.game.n var2 = (com.corrodinggames.rts.game.n)var1.next();
         if(var2 instanceof com.corrodinggames.rts.game.a.a) {
            com.corrodinggames.rts.game.a.a var3 = (com.corrodinggames.rts.game.a.a)var2;
            if(!this.c) {
               var3.bG = 0.0F;
            } else {
               var3.bG = Float.MAX_VALUE;
            }
         }
      }

   }

   public static strictfp boolean a(com.corrodinggames.rts.game.units.a.s var0, am var1) {
      if(var0 instanceof com.corrodinggames.rts.game.units.a.h) {
         var0 = ((com.corrodinggames.rts.game.units.a.h)var0).q_();
      }

      return var0 == o?true:(var0 == w?true:(var0 == x?true:(var0 == m?true:(var0 == k?true:(var0 == j?true:(var0 == z?true:(var0 == p?true:(var0 == q?true:(var0 == s?true:(var0 == t?true:(var0 == B?true:var0 == C)))))))))));
   }

   public static strictfp void a(ArrayList var0, int var1) {
      if(var1 == 1) {
         D = new ArrayList();
         D.add(new k(true, false));
         D.add(new k(true, true));
         D.add(new k(false, false));
         D.add(new m(true, false));
         m var2 = new m(true, true);
         D.add(var2);
         D.add(new m(false, false));
         D.add(new j(true, false));
         D.add(new j(true, true));
         D.add(new j(false, false));
         D.add(new l(true, false));
         D.add(new l(true, true));
         D.add(new l(false, false));
         D.add(new q(r.a));
         D.add(new q(r.b));
         D.add(new q(r.c));
         D.add(new q(r.d));
         ArrayList var3 = new ArrayList();
         var3.add(new i());
         var3.add(y);
         var3.add(h);
         var3.add(i);
         var3.add(k);
         var3.add(j);
         var3.add(l);
         var3.add(n);
         var3.add(m);
         var3.add(u);
         var3.add(v);
         var3.add(w);
         var3.add(x);
         var3.add(z);
         var3.add(p);
         var3.add(q);
         var3.add(s);
         var3.add(t);
         if(com.corrodinggames.rts.gameFramework.l.at) {
            var3.add(A);
         }

         var3.add(B);
         var3.add(C);
         Iterator var4 = var3.iterator();

         while(var4.hasNext()) {
            com.corrodinggames.rts.game.units.a.s var5 = (com.corrodinggames.rts.game.units.a.s)var4.next();
            boolean var6 = true;
            com.corrodinggames.rts.game.units.a.h var7 = new com.corrodinggames.rts.game.units.a.h(var5, K, var6);
            D.add(var7);
         }

         ArrayList var12 = new ArrayList();
         var12.addAll(ar.ae);
         Collections.sort(var12, new h$15());
         Iterator var13 = var12.iterator();

         while(var13.hasNext()) {
            as var14 = (as)var13.next();
            if(var14 != ar.I && !var14.i().equals("test_tank") && !var14.i().equals("missing") && var14 != ar.v && var14 != ar.q && var14 != ar.R && var14 != ar.H && var14 != ar.W && var14 != ar.X && var14 != ar.Y && var14 != ar.Z && var14 != ar.N) {
               am var15 = am.c(var14);
               if(var15 instanceof y) {
                  if(var14 instanceof com.corrodinggames.rts.game.units.custom.l) {
                     com.corrodinggames.rts.game.units.custom.l var8 = (com.corrodinggames.rts.game.units.custom.l)var14;
                     if(!var8.aF) {
                        continue;
                     }
                  }

                  com.corrodinggames.rts.game.units.a.v var16 = new com.corrodinggames.rts.game.units.a.v(var14, 1, (Integer)null);
                  com.corrodinggames.rts.game.units.a.h var17 = new com.corrodinggames.rts.game.units.a.h(var16, K);
                  boolean var9 = false;
                  Iterator var10 = D.iterator();

                  while(var10.hasNext()) {
                     com.corrodinggames.rts.game.units.a.s var11 = (com.corrodinggames.rts.game.units.a.s)var10.next();
                     if(var11.equals(var17)) {
                        var9 = true;
                     }
                  }

                  if(!var9) {
                     D.add(var17);
                  }
               }
            }
         }

      }
   }

   public strictfp ArrayList N() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      return D;
   }

   public strictfp boolean E() {
      return true;
   }

   public strictfp float g(int var1) {
      return 10.0F;
   }

   public strictfp boolean F() {
      return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && !this.bV;
   }

   public strictfp float G() {
      return 1.0F;
   }

   public strictfp float H() {
      return 1.0F;
   }

   public strictfp boolean u() {
      return true;
   }

   public strictfp boolean I() {
      return false;
   }

   public strictfp boolean d(am var1) {
      return false;
   }

   public strictfp boolean J() {
      return true;
   }

   public strictfp float a(am var1, float var2, com.corrodinggames.rts.game.f var3) {
      var2 = 0.0F;
      return super.a(var1, var2, var3);
   }

   public strictfp void O() {}

   public strictfp boolean P() {
      return true;
   }

   public strictfp void a(h var1) {
      this.r = var1.r;
      this.c = var1.c;
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.c(1);
      var1.a((Enum)this.G);
      var1.b(this.H);
      super.a(var1);
   }

   public strictfp void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      byte var2 = var1.d();
      this.G = (n)var1.b(n.class);
      if(this.G == null) {
         this.G = n.a;
      }

      if(var2 >= 1) {
         this.H = var1.j();
      }

      super.a(var1);
   }

   // $FF: synthetic method
   public as r() {
      return this.f();
   }

}
