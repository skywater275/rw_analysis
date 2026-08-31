package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Cap;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.gameFramework.bh;
import com.corrodinggames.rts.gameFramework.bi;
import com.corrodinggames.rts.gameFramework.bj;
import com.corrodinggames.rts.gameFramework.bm;
import com.corrodinggames.rts.gameFramework.bn;
import com.corrodinggames.rts.gameFramework.bo;
import com.corrodinggames.rts.gameFramework.f.aa;
import com.corrodinggames.rts.gameFramework.f.ab;
import com.corrodinggames.rts.gameFramework.f.ac;
import com.corrodinggames.rts.gameFramework.f.ad;
import com.corrodinggames.rts.gameFramework.f.e;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.f.i;
import com.corrodinggames.rts.gameFramework.f.z;
import java.util.ArrayList;
import java.util.Iterator;

public class y {

   private ArrayList e;
   private ac f;
   private z g;
   private ArrayList h;
   private ab[] i;
   private ArrayList j;
   private ab[] k;
   private ArrayList l;
   private ab[] m;
   aa a;
   private long n;
   private com.corrodinggames.rts.gameFramework.m.e o;
   private com.corrodinggames.rts.gameFramework.m.e[] p;
   private Rect q;
   private Rect r;
   private ArrayList s;
   private ArrayList t;
   private int u;
   private int v;
   private int w;
   Rect b;
   Paint c;
   Paint d;


   public static y a() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      ArrayList var1 = var0.bY.d();
      ArrayList var2 = e.a();
      return new y(var1, var2);
   }

   private y(ArrayList var1, ArrayList var2) {
      this.f = ac.a;
      this.g = z.a;
      this.h = new ArrayList();
      this.i = new ab[bj.values().length];
      this.j = new ArrayList();
      this.k = new ab[bj.values().length];
      this.s = new ArrayList();
      this.t = new ArrayList();
      this.u = -1;
      this.v = -1;
      this.w = -1;
      this.b = new Rect();
      this.e = var2;
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         bo var4 = (bo)var3.next();
         com.corrodinggames.rts.game.n var5 = com.corrodinggames.rts.game.n.k(var4.l.b());
         this.h.add(new aa(var4.l, var5.v, var5.K()));
      }

      ArrayList var10 = com.corrodinggames.rts.game.n.f();
      Iterator var11 = var10.iterator();

      while(var11.hasNext()) {
         Integer var13 = (Integer)var11.next();
         ArrayList var6 = new ArrayList();
         Iterator var7 = var1.iterator();

         while(var7.hasNext()) {
            bo var8 = (bo)var7.next();
            com.corrodinggames.rts.game.n var9 = com.corrodinggames.rts.game.n.k(var8.l.b());
            if(var9.r == var13.intValue()) {
               var6.add(var8);
            }
         }

         if(!var6.isEmpty()) {
            bm var16 = new bm(var6);
            this.j.add(new aa(var16.l, "Team " + com.corrodinggames.rts.game.n.a(var13.intValue()), com.corrodinggames.rts.game.n.i(var13.intValue())));
         }
      }

      bj[] var12 = bj.values();
      int var14 = var12.length;

      for(int var15 = 0; var15 < var14; ++var15) {
         bj var17 = var12[var15];
         this.i[var17.ordinal()] = new ab(var17, this.h);
         this.k[var17.ordinal()] = new ab(var17, this.j);
      }

      this.l = this.h;
      this.m = this.i;
      this.b();
   }

   public void b() {
      this.f = ac.a;
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.c = new Paint();
      this.c.a(true);
      this.c.a(Paint$Align.a);
      this.c.a(255, 0, 255, 0);
      var1.b(this.c, 16.0F);
      this.d = new Paint();
      this.d.a(true);
      this.d.a(Paint$Align.c);
      this.d.a(255, 0, 255, 0);
      var1.b(this.d, 16.0F);
      this.c();
   }

   private void c() {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      this.p = new com.corrodinggames.rts.gameFramework.m.e[ac.values().length + 2];
      this.p[0] = var1.bO.a(R$drawable.stats_button_info);
      this.p[1] = var1.bO.a(R$drawable.stats_button_income);
      this.p[2] = var1.bO.a(R$drawable.stats_button_armyvalue);
      this.p[3] = var1.bO.a(R$drawable.stats_button_buildingvalue);
      this.p[4] = var1.bO.a(R$drawable.stats_button_totalvalue);
      this.p[5] = var1.bO.a(R$drawable.stats_toggle_relative);
      this.p[6] = var1.bO.a(R$drawable.stats_toggle_teams);
      this.r = new Rect(0, 0, this.p[0].m(), this.p[0].l());
   }

   public void a(Rect var1, Rect var2, float var3, boolean var4, boolean var5) {
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      g var7 = var6.bS;
      boolean var8 = true;
      if(var5) {
         int var9 = ac.values().length;
         int var10 = var6.a(30);
         int var11 = var10 * 2;
         int var12 = var6.a(20);
         int var13 = var2.d - var10 - var12;
         byte var14 = 2;
         int var15;
         if(var7.c) {
            var15 = var9 + var14;
         } else {
            var15 = var9 - 1;
         }

         int var16 = var11 * var15 + var12 * (var15 - 1);
         int var17 = (int)(var6.cF / 2.0F - (float)(var16 / 2));
         Paint var18 = new Paint();
         Paint var19 = new Paint();
         var19.a(100, 255, 255, 255);

         Paint var22;
         for(int var20 = 0; var20 < var9; ++var20) {
            ac var21 = ac.values()[var20];
            if(var7.c || var21 != ac.a) {
               if(var7.a(var17, var13, var11, var10, i.a, false)) {
                  if(this.f != var21) {
                     this.f = var21;
                     this.n = System.currentTimeMillis();
                     this.u = -1;
                     this.v = -1;
                     this.w = -1;
                  }

                  if(this.f != ac.a) {
                     var7.c = true;
                  }
               }

               this.b.a(var17, var13, var17 + var11, var13 + var10);
               var6.bO.a(var6.bS.bn, this.r, this.b, var18);
               var22 = var19;
               if(!var7.c || this.f == var21) {
                  var22 = var18;
               }

               var6.bO.a(this.p[var20], this.r, this.b, var22);
               var17 += var12 + var11;
            }
         }

         var17 += var12;
         if(var7.c) {
            boolean var23 = this.g != z.a;
            if(var7.a(var17, var13, var11, var10, i.a, false)) {
               this.g = !var23?z.b:z.a;
               this.n = System.currentTimeMillis();
            }

            this.b.a(var17, var13, var17 + var11, var13 + var10);
            Paint var24 = var18;
            if(this.f == ac.a) {
               var24 = var19;
            }

            var6.bO.a(var6.bS.bn, this.r, this.b, var24);
            var22 = var18;
            if(!var23 || this.f == ac.a) {
               var22 = var19;
            }

            var6.bO.a(this.p[5], this.r, this.b, var22);
            var17 += var12 + var11;
            var23 = this.l == this.j;
            if(var7.a(var17, var13, var11, var10, i.a, false)) {
               if(!var23) {
                  this.l = this.j;
                  this.m = this.k;
               } else {
                  this.l = this.h;
                  this.m = this.i;
               }

               this.n = System.currentTimeMillis();
            }

            this.b.a(var17, var13, var17 + var11, var13 + var10);
            var24 = var18;
            if(this.f == ac.a) {
               var24 = var19;
            }

            var6.bO.a(var6.bS.bn, this.r, this.b, var24);
            var22 = var18;
            if(!var23 || this.f == ac.a) {
               var22 = var19;
            }

            var6.bO.a(this.p[6], this.r, this.b, var22);
            int var10000 = var17 + var12 + var11;
         }

         if(this.f == ac.a) {
            var8 = true;
         } else {
            var8 = false;
            var1.d = var13 - var6.a(10);
            if(var4) {
               this.a(this.f.a(), this.g, var1);
            }
         }
      }

      if(var8) {
         this.a(var1, var3);
      }

   }

   private void a(Rect var1, float var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      float var4 = 1.5F;
      int var5 = var1.b + var3.a(25);
      int var6 = var1.d();
      String var7 = "123|";
      this.c.a(var7, 0, var7.length(), this.b);
      float var8 = (float)(this.b.c() + 6);

      for(Iterator var9 = this.e.iterator(); var9.hasNext(); var5 = (int)((float)var5 + var8)) {
         e var10 = (e)var9.next();
         if(var10.d != 1.0F && var4 > 0.0F) {
            var10.d = com.corrodinggames.rts.gameFramework.f.a(var10.d, 1.0F, 0.01F * var4 * var2);
            var4 -= 1.0F - var10.d;
         }

         float var11 = var10.d;
         var11 = com.corrodinggames.rts.gameFramework.f.b(var11, 0.0F, 1.0F);
         String var12 = "";
         if(var10.b != null) {
            var12 = var10.b;
         } else {
            var12 = "" + (int)(var10.c * var11);
            if(var11 <= 0.0F) {
               var12 = " ";
            }
         }

         String var13 = var10.a;
         float var14 = var10.d * 2.2F;
         var14 = com.corrodinggames.rts.gameFramework.f.b(var14, 0.0F, 1.0F);
         int var15 = 0;
         if(var14 > 0.0F) {
            var15 = (int)((float)var13.length() * var14);
         }

         var15 = com.corrodinggames.rts.gameFramework.f.b(var15, 0, var13.length());
         String var16 = "";
         if(var15 > 0 && var15 < var13.length() - 1) {
            var16 = "_";
         }

         var13 = var13.substring(0, var15) + var16 + com.corrodinggames.rts.gameFramework.f.d(" ", var13.length() + var16.length() - var15);
         var3.bO.a(var13, (float)var6 - 8.0F * this.c.k(), (float)var5, this.c);
         var3.bO.a(var12, (float)var6 + 8.0F * this.c.k(), (float)var5, this.d);
      }

   }

   private void a(bj var1, z var2, Rect var3) {
      com.corrodinggames.rts.gameFramework.l var4 = com.corrodinggames.rts.gameFramework.l.B();
      this.a(var4.bO, var1, var2, var3);
   }

   private void a(com.corrodinggames.rts.gameFramework.m.y var1, bj var2, z var3, Rect var4) {
      com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
      g var6 = var5.bS;
      ab var7 = this.m[var2.ordinal()];
      float var8 = (float)(System.currentTimeMillis() - this.n) / 250.0F;
      Paint var9 = new Paint();
      var9.a(255, 0, 255, 0);
      var9.a(true);
      var9.c(true);
      var9.a(Typeface.a(Typeface.c, 0));
      var5.b(var9, 14.0F);
      Paint var10 = new Paint(var9);
      var10.a(Paint$Align.b);
      var5.b(var10, 14.0F);
      Paint var11 = new Paint();
      var11.a(2.0F);
      if(com.corrodinggames.rts.gameFramework.l.aZ) {
         var11.a(3.0F);
      }

      var11.a(Paint$Cap.b);
      Rect var12 = new Rect();
      Paint var14 = var6.aD;
      String var15 = com.corrodinggames.rts.gameFramework.h.a.a("gui.leaderboard.type." + var2.name(), new Object[0]);
      var14.a(var15, 0, var15.length(), this.b);
      var1.a(var15, (float)var4.d(), (float)(var4.b + this.b.c()), var14);
      var12.b = var4.b + this.b.c() + 3;
      var12.d = var4.d - this.b.c() - 3;
      int var35 = Math.max(1, ab.a(var7) - ab.b(var7));
      float var36 = (float)var12.c() / (float)var35;
      String var16 = com.corrodinggames.rts.gameFramework.f.a(0L);
      int var13 = var1.b(var16, var10);
      var1.a(var16, (float)(var4.a + var13 / 2), (float)var4.d, var10);
      var12.a = var4.a + var13 / 2;
      String var17 = "123|";
      var9.a(var17, 0, var17.length(), this.b);
      int var18 = this.b.c();
      String var19;
      int var21;
      int var22;
      if(var3 == z.a) {
         var19 = com.corrodinggames.rts.gameFramework.g.a.a(ab.c(var7).a(), ab.a(var7));
         String var20 = com.corrodinggames.rts.gameFramework.g.a.a(ab.c(var7).a(), ab.b(var7));
         var13 = Math.max(var1.b(var19, var9), var1.b(var20, var9));
         var12.c = var4.c - var13 - 2;
         var21 = var18 / 2;
         var1.b(var12, var6.aM);
         var11.b(-13619152);

         for(var22 = 0; var22 <= 4; ++var22) {
            float var23 = (float)ab.b(var7) + (float)var35 * (float)var22 / 4.0F;
            float var24 = (float)var12.d - (var23 - (float)ab.b(var7)) * var36;
            String var25 = com.corrodinggames.rts.gameFramework.g.a.a(ab.c(var7).a(), (int)var23);
            var1.a(var25, (float)(var12.c + 2), var24 + (float)var21, var9);
            if(var22 > 0 && var22 < 4) {
               var1.a((float)var12.a, var24, (float)var12.c, var24, var11);
            }
         }
      } else {
         var12.c = var4.c - var5.a(10);
      }

      var19 = com.corrodinggames.rts.gameFramework.f.a((long)(ab.d(var7) / 1000));
      var1.b(var19, var10);
      var1.a(var19, (float)var12.c, (float)var4.d, var10);
      float var37 = (float)var12.b() / (float)ab.d(var7);
      float var29;
      int var30;
      float var31;
      float var32;
      int var42;
      float var52;
      int var56;
      float var64;
      if(var3 == z.a) {
         for(var21 = 0; var21 <= 2; ++var21) {
            Iterator var39 = this.l.iterator();

            while(var39.hasNext()) {
               aa var41 = (aa)var39.next();
               bi var43 = var41.a.a(var2);
               boolean var26 = var21 == 0;
               short var46;
               if(!var26) {
                  var46 = 220;
                  if(this.a != null) {
                     if(var41 == this.a) {
                        var46 = 255;
                     } else {
                        var46 = 50;
                     }
                  }
               } else {
                  if(var41.c != -16777216) {
                     continue;
                  }

                  var46 = 255;
                  if(this.a != null) {
                     if(var41 == this.a) {
                        var46 = 255;
                     } else {
                        var46 = 50;
                     }
                  }
               }

               if(var21 == 2) {
                  if(var41 != this.a) {
                     continue;
                  }
               } else if(var21 == 1 && var41 == this.a) {
                  continue;
               }

               bh var27 = (bh)var43.get(0);
               float var28 = (float)var12.a;
               var29 = (float)var12.d - var36 * (float)(var27.b - ab.b(var7));

               for(var30 = 1; var30 < var43.size(); ++var30) {
                  var27 = (bh)var43.get(var30);
                  var31 = (float)var12.a + var37 * (float)var27.a;
                  var32 = (float)var12.d - var36 * (float)(var27.b - ab.b(var7));
                  int var33 = (int)((float)var46 * Math.min(1.0F, Math.max(0.0F, var8 - (float)var27.a / (float)ab.d(var7))));
                  com.corrodinggames.rts.gameFramework.m.ag var34 = var41.a(var33, var26);
                  var1.a(var28, var29, var31, var29, var34);
                  var1.a(var31, var29, var31, var32, var34);
                  var28 = var31;
                  var29 = var32;
               }
            }
         }
      } else {
         ArrayList var38 = ab.e(var7);
         ad var40 = (ad)var38.get(0);

         for(var42 = 1; var42 < var38.size(); ++var42) {
            ad var44 = (ad)var38.get(var42);
            var52 = (float)var12.a + var37 * (float)ad.a(var40);
            float var48 = (float)var12.a + var37 * (float)ad.a(var44);
            float var53 = (float)var12.d;

            for(var56 = 0; var56 < this.l.size(); ++var56) {
               var29 = var40.a(var56);
               var64 = var53 - (float)var12.c() * var29;
               if(var29 > 0.0F) {
                  aa var65 = (aa)this.l.get(var56);
                  var32 = Math.min(1.0F, Math.max(0.0F, var8 - (float)ad.a(var40) / (float)ab.d(var7)));
                  com.corrodinggames.rts.gameFramework.m.ag var67 = var65.a((int)(var32 * 255.0F), false);
                  this.b.a((int)var52, (int)(var64 + 0.5F), (int)var48, (int)(var53 + 0.5F));
                  if(this.o != null) {
                     var1.a(this.o, this.q, this.b, var67);
                  } else {
                     var1.b(this.b, var67);
                  }
               }

               var53 = var64;
            }

            var40 = var44;
         }
      }

      if(var12.b((int)var6.x, (int)var6.y)) {
         var6.a((float)var12.a, (float)var12.b, (float)var12.b(), (float)var12.c());
         var11.b(-1);
         var1.a(var6.x, (float)var12.b, var6.x, (float)var12.d, var11);
         var21 = (int)var6.x;
         var22 = (int)var6.y;
         var42 = (int)((var6.x - (float)var12.a) / var37);
         Iterator var57;
         if(this.v != var21 || this.w != var22) {
            this.v = var21;
            this.w = var22;
            this.u = var42;
            this.s.clear();
            this.t.clear();
            this.s.add(com.corrodinggames.rts.gameFramework.f.a((long)(this.u / 1000)));
            this.t.add(Integer.valueOf(-1));
            aa var45 = null;
            if(var3 == z.a) {
               var52 = 30.0F;
               Iterator var49 = this.l.iterator();

               while(var49.hasNext()) {
                  aa var55 = (aa)var49.next();
                  bn var61 = var55.a;
                  int var62 = var61.a(var2, this.u);
                  var64 = (float)var12.d - var36 * (float)(var62 - ab.b(var7));
                  var31 = com.corrodinggames.rts.gameFramework.f.c(var64 - var6.y);
                  if(var31 < var52) {
                     var52 = var31;
                     var45 = var55;
                  }
               }
            }

            this.a = var45;

            for(var57 = this.l.iterator(); var57.hasNext(); this.t.add(Integer.valueOf(var30))) {
               aa var50 = (aa)var57.next();
               bn var58 = var50.a;
               var56 = var58.a(var2, this.u);
               String var63 = com.corrodinggames.rts.gameFramework.g.a.a(ab.c(var7).a(), var56) + " " + var50.b;
               this.s.add(var63);
               var30 = var50.c;
               if(this.a != null && this.a != var50) {
                  byte var66 = 60;
                  var30 = Color.a(var66, Color.b(var30), Color.c(var30), Color.d(var30));
               }
            }
         }

         this.b.a = var12.a + var5.a(5);
         this.b.b = var12.b + var5.a(5);
         this.b.d = this.b.b + var5.a(5) + var18 * this.s.size();
         String var47 = "";
         var57 = this.s.iterator();

         while(var57.hasNext()) {
            String var51 = (String)var57.next();
            if(var47.length() < var51.length()) {
               var47 = var51;
            }
         }

         int var60 = var1.b(var47, var9);
         this.b.c = this.b.a + var5.a(10) + var60;
         var1.b(this.b, var6.aL);
         int var54 = this.b.b + var18 + 3;

         for(int var59 = 0; var59 < this.s.size(); ++var59) {
            var9.b(((Integer)this.t.get(var59)).intValue());
            var1.a((String)this.s.get(var59), (float)(this.b.a + 3), (float)var54, var9);
            var54 += var18;
         }
      } else {
         this.a = null;
      }

   }
}
