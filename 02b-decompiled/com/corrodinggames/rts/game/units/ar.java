package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar$1;
import com.corrodinggames.rts.game.units.ar$49;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.at;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public enum ar implements as {

   a("extractor", 0),
   b("landFactory", 1),
   c("airFactory", 2),
   d("seaFactory", 3),
   e("commandCenter", 4),
   f("turret", 5),
   g("antiAirTurret", 6),
   h("builder", 7),
   i("tank", 8),
   j("hoverTank", 9),
   k("artillery", 10),
   l("helicopter", 11),
   m("airShip", 12),
   n("gunShip", 13),
   o("missileShip", 14),
   p("gunBoat", 15),
   q("megaTank", 16),
   r("laserTank", 17),
   s("hovercraft", 18),
   t("ladybug", 19),
   u("battleShip", 20),
   v("tankDestroyer", 21),
   w("heavyTank", 22),
   x("heavyHoverTank", 23),
   y("laserDefence", 24),
   z("dropship", 25),
   A("tree", 26),
   B("repairbay", 27),
   C("NukeLaucher", 28),
   D("AntiNukeLaucher", 29),
   E("mammothTank", 30),
   F("experimentalTank", 31),
   G("experimentalLandFactory", 32),
   H("crystalResource", 33),
   I("wall_v", 34),
   J("fabricator", 35),
   K("attackSubmarine", 36),
   L("builderShip", 37),
   M("amphibiousJet", 38),
   N("supplyDepot", 39),
   O("experimentalHoverTank", 40),
   P("turret_artillery", 41),
   Q("turret_flamethrower", 42),
   R("fogRevealer", 43),
   S("spreadingFire", 44),
   T("antiAirTurretT2", 45),
   U("turretT2", 46),
   V("turretT3", 47),
   W("damagingBorder", 48),
   X("zoneMarker", 49),
   Y("editorOrBuilder", 50),
   Z("dummyNonUnitWithTeam", 51);
   com.corrodinggames.rts.game.units.a.z aa;
   int ab;
   String ac;
   String ad;
   public static ArrayList ae;
   at[] af;
   public static boolean ag;
   com.corrodinggames.rts.game.units.custom.d.b ah;
   // $FF: synthetic field
   private static final ar[] ai = new ar[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};


   private ar(String var1, int var2) {
      this.aa = new com.corrodinggames.rts.game.units.a.z(this);
      this.ab = -1;
   }

   public am a() {
      return this.a(false);
   }

   public abstract am a(boolean var1);

   public abstract void b();

   public abstract int c();

   public com.corrodinggames.rts.game.units.a.z d() {
      return this.aa;
   }

   public String e() {
      if(this.ab != com.corrodinggames.rts.gameFramework.h.a.c || this.ac == null) {
         this.ab = com.corrodinggames.rts.gameFramework.h.a.c;
         String var1 = "units." + this.name() + ".name";
         this.ac = com.corrodinggames.rts.gameFramework.h.a.a(var1, (String)null, new Object[0]);
         if(this.ac == null) {
            if(com.corrodinggames.rts.gameFramework.l.B().as() && !this.A()) {
               throw new RuntimeException("Can\'t find translation text for: " + var1);
            }

            this.ac = this.name();
         }
      }

      return this.ac;
   }

   public String f() {
      if(this.ab != com.corrodinggames.rts.gameFramework.h.a.c || this.ad == null) {
         this.ab = com.corrodinggames.rts.gameFramework.h.a.c;
         String var1 = "units." + this.name() + ".description";
         this.ad = com.corrodinggames.rts.gameFramework.h.a.a(var1, (String)null, new Object[0]);
         if(this.ad == null) {
            if(com.corrodinggames.rts.gameFramework.l.B().as() && !this.A()) {
               throw new RuntimeException("Can\'t find translation text for: " + var1);
            }

            this.ad = "";
         }
      }

      return this.ad;
   }

   public int g() {
      return 1;
   }

   public void a(ArrayList var1, int var2) {}

   public void h() {
      at[] var1 = new at[3];

      for(int var2 = 1; var2 <= 3; ++var2) {
         at var3 = new at();
         this.a(var3.a, var2);
         var1[var2 - 1] = var3;
      }

      this.af = var1;
   }

   public ArrayList a(int var1) {
      if(var1 > 3) {
         throw new RuntimeException("Tech level:" + var1 + " greater than maxTechLevel");
      } else {
         return this.af[var1 - 1].a;
      }
   }

   public String i() {
      return this.name();
   }

   public boolean j() {
      return false;
   }

   public boolean k() {
      return this.j();
   }

   public boolean l() {
      return false;
   }

   public boolean m() {
      return false;
   }

   public boolean n() {
      return false;
   }

   public ao o() {
      am var1 = am.a((as)this);
      if(var1 == null) {
         throw new RuntimeException("Shared unit is null for:" + this.name());
      } else {
         return var1.h();
      }
   }

   public boolean p() {
      return false;
   }

   public be q() {
      return null;
   }

   public static as a(String var0) {
      return a(var0, true);
   }

   public static as a(String var0, boolean var1) {
      if(var1) {
         as var2 = com.corrodinggames.rts.game.units.custom.l.m(var0);
         if(var2 != null) {
            return var2;
         }
      }

      ar[] var6 = values();
      int var3 = var6.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ar var5 = var6[var4];
         if(var5.name().equalsIgnoreCase(var0)) {
            return var5;
         }
      }

      com.corrodinggames.rts.game.units.custom.l var7 = com.corrodinggames.rts.game.units.custom.l.n(var0);
      if(var7 != null) {
         return var7;
      } else {
         return null;
      }
   }

   private static String a(String var0, float var1) {
      return a(var0, var1, "");
   }

   private static String a(String var0, float var1, String var2) {
      String var3 = "" + var1;
      if(var1 % 1.0F == 0.0F) {
         var3 = "" + (int)var1;
      }

      return a(var0, var3, var2);
   }

   private static String a(String var0, String var1, String var2) {
      return var0 + ": " + var1 + var2 + "\n";
   }

   private static int a(y var0) {
      com.corrodinggames.rts.game.units.a.c var1 = var0.cm();
      if(var1 != null) {
         com.corrodinggames.rts.game.units.a.s var2 = var0.a(var1);
         if(var2 != null) {
            return var2.c();
         }
      }

      return 0;
   }

   public static void r() {
      String var0 = "output_all_unit_images/";
      (new File(var0)).mkdirs();

      for(int var1 = 0; var1 < 50; ++var1) {
         com.corrodinggames.rts.gameFramework.l.a("running outputUnitImages()");
      }

      String[] var21 = new String[]{"carrier", "experimentalGunship", "experimentalGunshipLanded", "mech_gun", "ladybug", "spiderBot", "wall_v", "crystalResource", "test_tank", "missing", "fogRevealer", "supplyDepot", "tankDestroyer", "megaTank", "crystal_mid", "mechFlyingLanded"};
      Iterator var2 = ae.iterator();

      while(var2.hasNext()) {
         as var3 = (as)var2.next();
         am var4 = am.a(var3);
         if(var4 instanceof y && !var3.i().startsWith("bug")) {
            as var5 = com.corrodinggames.rts.game.units.custom.l.c(var3);
            if(var5 == null && (!(var3 instanceof com.corrodinggames.rts.game.units.custom.l) || ((com.corrodinggames.rts.game.units.custom.l)var3).aF)) {
               y var6 = (y)var4;
               boolean var7 = false;
               String[] var8 = var21;
               int var9 = var21.length;

               for(int var10 = 0; var10 < var9; ++var10) {
                  String var11 = var8[var10];
                  if(var11.equals(var3.i())) {
                     var7 = true;
                  }
               }

               if(!var7) {
                  String var22 = var0 + var3.i().replace("/", "_").replace("\\", "_") + ".png";
                  com.corrodinggames.rts.gameFramework.l var23 = com.corrodinggames.rts.gameFramework.l.B();
                  byte var24 = 100;
                  com.corrodinggames.rts.gameFramework.m.e var25 = var23.bO.b(var24, var24, true);
                  com.corrodinggames.rts.gameFramework.m.y var12 = var23.bO.b(var25);
                  com.corrodinggames.rts.gameFramework.m.y var13 = var23.bO;
                  var23.bO = var12;
                  float var14 = 0.0F;
                  float var15 = 0.0F;
                  com.corrodinggames.rts.game.n var16 = com.corrodinggames.rts.game.n.k(0);
                  boolean var17 = false;
                  boolean var18 = false;
                  byte var19 = 1;
                  boolean var20 = true;
                  a(var3, (float)var25.r, (float)var25.s, var14, var15, var16, 20.0F, (float)var24, var17, var18, var19, var20, (am)null);
                  var23.bO = var13;
                  var12.p();
                  var23.bO.a(var25, new File(var22));
               }
            }
         }
      }

   }

   public static void s() {
      for(int var0 = 0; var0 < 50; ++var0) {
         com.corrodinggames.rts.gameFramework.l.a("running printForHelp()");
      }

      String[] var18 = new String[]{"carrier", "experimentalGunship", "experimentalGunshipLanded", "mech_gun", "ladybug", "spiderBot", "wall_v", "crystalResource", "test_tank", "missing", "fogRevealer", "supplyDepot", "tankDestroyer", "megaTank", "crystal_mid", "mechFlyingLanded"};
      String var1 = "";
      ArrayList var2 = new ArrayList();
      var2.addAll(ae);
      Collections.sort(var2, new ar$49());
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         as var4 = (as)var3.next();
         am var5 = am.a(var4);
         if(var5 instanceof y && !var4.i().startsWith("bug")) {
            as var6 = com.corrodinggames.rts.game.units.custom.l.c(var4);
            if(var6 == null && (!(var4 instanceof com.corrodinggames.rts.game.units.custom.l) || ((com.corrodinggames.rts.game.units.custom.l)var4).aF) && var4 != Y) {
               y var7 = (y)var5;
               boolean var8 = false;
               String[] var9 = var18;
               int var10 = var18.length;

               int var11;
               for(var11 = 0; var11 < var10; ++var11) {
                  String var12 = var9[var11];
                  if(var12.equals(var4.i())) {
                     var8 = true;
                  }
               }

               if(!var8) {
                  var1 = var1 + "\n";
                  var1 = var1 + "<div class=\"unit\">\n";
                  var1 = var1 + "<img src=\"unit:" + var4.i() + "\" />\n";
                  var1 = var1 + "<h4>" + var4.e() + "</h4>\n";
                  var1 = var1 + "<p>" + var4.f().replace("\n", "<br/>") + "</p>\n";
                  var1 = var1 + "<pre>";
                  var1 = var1 + a("Price", "$" + var4.c(), "");
                  int var19 = a(var7);
                  if(var19 > 0) {
                     var1 = var1 + a("T2 Upgrade Price", "$" + var19, "");
                     y var20 = (y)var4.a();
                     var20.a((int)2);
                     if(var20.V() == 2) {
                        var11 = a(var20);
                        if(var11 > 0) {
                           var1 = var1 + a("T3 Upgrade Price", "$" + var11, "");
                        }
                     }
                  }

                  var1 = var1 + a("Hp", var7.cv);
                  var1 = var1 + a("Speed", var7.z());
                  var1 = var1 + a("Turn speed", var7.A());
                  var1 = var1 + a("Mass", var7.bN());
                  if(var7.l()) {
                     var1 = var1 + a("Shoot Delay", var7.b(0));
                     var1 = var1 + a("Attack Range", var7.m());
                     float var21 = 0.0F;
                     float var22 = 0.0F;
                     float var23 = 0.0F;
                     float var13 = 0.0F;
                     int var14 = var7.bl();

                     for(int var15 = 0; var15 < var14; ++var15) {
                        int var16 = com.corrodinggames.rts.game.f.a.a;
                        var7.a(var7, var15);
                        if(var16 != com.corrodinggames.rts.game.f.a.a) {
                           com.corrodinggames.rts.game.f var17 = (com.corrodinggames.rts.game.f)com.corrodinggames.rts.game.f.a.get(com.corrodinggames.rts.game.f.a.a - 1);
                           if(var17.U > var21) {
                              var21 = var17.U;
                           }

                           if(var17.Y > var22) {
                              var22 = var17.Y;
                           }

                           var23 += var17.U;
                           var13 += var17.Y;
                        }
                     }

                     String var24;
                     if(var23 != 0.0F) {
                        var24 = "";
                        if(var23 != var21) {
                           var24 = " (total:" + var23 + ")";
                        }

                        var1 = var1 + a("Direct Damage", var21, var24);
                     }

                     if(var13 != 0.0F) {
                        var24 = "";
                        if(var13 != var22) {
                           var24 = " (total:" + var13 + ")";
                        }

                        var1 = var1 + a("Area Damage", var22, var24);
                     }
                  }

                  var1 = var1 + "</pre>";
                  var1 = var1 + "</div>\n";
               }
            }
         }
      }

      com.corrodinggames.rts.gameFramework.l.e(var1);
   }

   public static void t() {
      ar[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         ar var3 = var0[var2];
         var3.name();
         var3.e();
         var3.f();
      }

   }

   public static boolean a(as var0, float var1, float var2, float var3, float var4, com.corrodinggames.rts.game.n var5) {
      com.corrodinggames.rts.gameFramework.l var6 = com.corrodinggames.rts.gameFramework.l.B();
      am var7 = am.a(var0);
      if(var7 == null) {
         com.corrodinggames.rts.gameFramework.l.e("isValidHere: Failed to get unit from type:" + var0);
         return false;
      } else {
         var7.b(var5);
         var7.eq = var4;
         var7.eo = var1;
         var7.ep = var2;
         if(!var7.bI()) {
            var7.cg = var3;
            if(var7 instanceof y) {
               y var8 = (y)var7;
               var8.j(var3);
            }
         }

         boolean var10 = true;
         if(var7 instanceof y) {
            y var9 = (y)var7;
            var10 = var9.c(var5);
         }

         var7.eq = 0.0F;
         var7.cg = 0.0F;
         return var10;
      }
   }

   public static void a(as var0, float var1, float var2, float var3, float var4, com.corrodinggames.rts.game.n var5, float var6, float var7, boolean var8, boolean var9, int var10, am var11) {
      boolean var12 = true;
      a(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var12, var11);
   }

   public static void a(as var0, float var1, float var2, float var3, float var4, com.corrodinggames.rts.game.n var5, float var6, float var7, boolean var8, boolean var9, int var10, boolean var11, am var12) {
      com.corrodinggames.rts.gameFramework.l var13 = com.corrodinggames.rts.gameFramework.l.B();
      am var14 = am.c(var0);
      boolean var15 = var14.bI();
      var14.b(var5);
      y var16;
      if(var14 instanceof y) {
         var16 = (y)var14;
         var16.a(var10);
      }

      var14.eq = var4;
      if(var14.h() == ao.f || var14.h() == ao.g || var14.h() == ao.h) {
         var14.eq += 4.0F;
      }

      if(var14.h() == ao.d) {
         var14.eq += 10.0F;
      }

      if(!var15) {
         var14.cg = var3;
         if(var14 instanceof y) {
            var16 = (y)var14;
            var16.j(var3);
         }
      } else {
         var14.cg = -90.0F;
      }

      boolean var25 = true;
      boolean var17 = var14.cp;
      var14.cp = true;
      var14.cs = false;
      var14.ct = false;
      if(!var11) {
         var14.ct = true;
      }

      var14.co = false;
      var14.cq = false;
      var14.cr = false;
      if(!var8 && !var9) {
         var14.co = true;
      } else {
         var14.cq = var9;
         var14.cr = var8;
         var25 = false;
      }

      if(!var25) {
         var14.eo = var1;
         var14.ep = var2;
      } else {
         var14.eo = var13.cw + var1;
         var14.ep = var13.cx + var2;
      }

      float var18 = var14.cj * 2.0F * 0.8F;
      if(var14 instanceof y) {
         y var19 = (y)var14;
         if(var19.M != null) {
            float var20 = (float)var19.et * var19.cD();
            if(var20 > var18) {
               var18 = var20;
            }
         }
      }

      float var26 = 1.0F;
      if(var18 < var6) {
         var26 = var6 / var18;
      }

      if(var18 > var7) {
         var26 = var7 / var18;
      }

      var13.bO.k();
      if(var25) {
         ;
      }

      if(var26 != 1.0F) {
         var13.bO.a(var26, var26, var1, var2);
      }

      if(var26 < 1.0F) {
         ag = true;
      } else {
         ag = false;
      }

      if(var12 != null) {
         com.corrodinggames.rts.game.units.custom.e.f var27 = var14.dH;
         var14.dH = var12.dH;
         int var21 = var14.cE;
         var14.cE = var12.cE;
         float var22 = var14.cu;
         var14.cu = var12.cu;
         float var23 = var14.cB;
         var14.cB = var12.cB;
         VariableScope var24 = var14.bw;
         var14.bw = var12.bw;
         var14.d(0.0F);
         var14.c(0.0F);
         var14.a(0.0F, false);
         var14.dH = var27;
         var14.cE = var21;
         var14.cu = var22;
         var14.cB = var23;
         var14.bw = var24;
      } else {
         var14.d(0.0F);
         var14.c(0.0F);
         var14.a(0.0F, false);
      }

      var13.bO.l();
      var14.eq = 0.0F;
      if(!var15) {
         var14.cg = 0.0F;
      } else {
         var14.cg = -90.0F;
      }

      if(var14 instanceof y) {
         y var28 = (y)var14;
         var28.j(0.0F);
         var28.a((int)1);
      }

      var14.cq = false;
      var14.cr = false;
      var14.cp = var17;
      var14.co = false;
   }

   public int b(int var1) {
      int var2 = this.c();
      if(var1 >= 2) {
         var2 += this.c(2);
      }

      if(var1 >= 3) {
         var2 += this.c(2);
      }

      return var2;
   }

   public int c(int var1) {
      return 0;
   }

   public com.corrodinggames.rts.game.units.custom.d.b u() {
      int var1 = this.c();
      if(var1 == 0) {
         return com.corrodinggames.rts.game.units.custom.d.b.a;
      } else {
         if(this.ah == null || this.ah.a() != var1) {
            this.ah = com.corrodinggames.rts.game.units.custom.d.b.a(var1);
         }

         return this.ah;
      }
   }

   public com.corrodinggames.rts.game.units.custom.d.b d(int var1) {
      int var2 = this.b(var1);
      return com.corrodinggames.rts.game.units.custom.d.b.a(var2);
   }

   public String v() {
      return this.name();
   }

   public boolean w() {
      return false;
   }

   public com.corrodinggames.rts.game.units.custom.h x() {
      return null;
   }

   public boolean y() {
      return true;
   }

   public com.corrodinggames.rts.gameFramework.m.e z() {
      return null;
   }

   public int a(am var1) {
      return 0;
   }

   public boolean A() {
      return false;
   }

   public com.corrodinggames.rts.game.units.custom.d.b B() {
      return null;
   }

   // $FF: synthetic method
   ar(String var1, int var2, ar$1 var3) {
      this(var1, var2);
   }

}
