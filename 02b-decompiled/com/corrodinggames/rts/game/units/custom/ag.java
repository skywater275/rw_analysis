package com.corrodinggames.rts.game.units.custom;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.units.custom.aa;
import com.corrodinggames.rts.game.units.custom.ad;
import com.corrodinggames.rts.game.units.custom.ae;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.ah;
import com.corrodinggames.rts.game.units.custom.ai;
import com.corrodinggames.rts.game.units.custom.aj;
import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.at;
import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.be;
import com.corrodinggames.rts.game.units.custom.bh;
import com.corrodinggames.rts.game.units.custom.bl;
import com.corrodinggames.rts.game.units.custom.bn;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.f;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.m;
import com.corrodinggames.rts.game.units.custom.n;
import com.corrodinggames.rts.game.units.custom.p;
import com.corrodinggames.rts.game.units.custom.q;
import com.corrodinggames.rts.game.units.custom.r;
import com.corrodinggames.rts.game.units.custom.s;
import com.corrodinggames.rts.game.units.custom.t;
import com.corrodinggames.rts.game.units.custom.u;
import com.corrodinggames.rts.game.units.custom.v;
import com.corrodinggames.rts.game.units.custom.y;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$UnitReferenceOrUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.gameFramework.br;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class ag {

   static boolean a = false;
   static int b;
   static int c;
   public static int d;
   static com.corrodinggames.rts.gameFramework.i.b e;
   static boolean f;
   public static HashMap g = new HashMap();
   public static HashMap h = new HashMap();
   static int i;
   static int j;
   static boolean k;
   static int l;
   public static com.corrodinggames.rts.gameFramework.utility.m m = new com.corrodinggames.rts.gameFramework.utility.m();
   static HashMap n = new HashMap();
   static final Object o = new Object();
   public static float p = 50.0F;
   public static float q = 50.0F;
   static com.corrodinggames.rts.gameFramework.i.b r = null;
   static String s = null;


   public static void a(int var0) {
      if(e != null) {
         e.G += (long)var0;
      }

   }

   public static void a() {
      i();
      j();
   }

   public static void a(com.corrodinggames.rts.gameFramework.m.e var0) {
      if(var0 != null && !var0.v) {
         if(com.corrodinggames.rts.gameFramework.l.az() && var0 instanceof com.corrodinggames.rts.gameFramework.m.h) {
            return;
         }

         var0.v = true;
         a(var0.u());
      }

   }

   public static void a(com.corrodinggames.rts.gameFramework.m.e[] var0) {
      if(var0 != null) {
         com.corrodinggames.rts.gameFramework.m.e var1 = null;
         com.corrodinggames.rts.gameFramework.m.e[] var2 = var0;
         int var3 = var0.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            com.corrodinggames.rts.gameFramework.m.e var5 = var2[var4];
            if(var5 != var1) {
               a(var5);
            }

            if(var1 == null) {
               var1 = var5;
            }
         }
      }

   }

   public static void a(com.corrodinggames.rts.gameFramework.a.i var0) {
      if(!var0.g) {
         var0.g = true;
         if(e != null) {
            e.H += (long)var0.a();
         }
      }

   }

   public static boolean a(com.corrodinggames.rts.gameFramework.utility.m var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();
      boolean var2 = false;
      boolean var3 = false;
      boolean var4 = false;
      ArrayList var5 = new ArrayList(l.c);
      ArrayList var6 = new ArrayList(l.d);
      com.corrodinggames.rts.gameFramework.utility.m var7 = new com.corrodinggames.rts.gameFramework.utility.m();
      String var8 = null;
      Iterator var9 = var0.iterator();

      l var10;
      while(var9.hasNext()) {
         var10 = (l)var9.next();
         l var11 = a(var10);
         if(var11 == null) {
            com.corrodinggames.rts.gameFramework.l.e("Failed to apply changes to unit type: " + var10.M);
            var2 = true;
            if(var8 == null && s != null) {
               var8 = s;
            }
         } else {
            com.corrodinggames.rts.gameFramework.l.e("Changes applied to unit type: " + var10.M);
            var3 = true;
            var7.add(var11);
         }
      }

      if(var8 != null && com.corrodinggames.rts.gameFramework.l.at()) {
         var1.c("Unit errors", var8);
      }

      if(var3 && !c(false)) {
         var2 = true;
      }

      if(var3 && !var2) {
         l.e = null;
         e();
         s = null;
         com.corrodinggames.rts.game.n.P();
         com.corrodinggames.rts.gameFramework.f.g.K();
         var4 = true;
         if(!var2) {
            var9 = var7.iterator();

            while(var9.hasNext()) {
               var10 = (l)var9.next();
               if(var10.gt.size() > 0) {
                  var1.a(var10.gt.size() + " Warning(s) loading: " + var10.b() + " \n" + (String)var10.gt.get(0), 1);
                  var10.gt.clear();
                  var4 = false;
                  break;
               }
            }
         }
      }

      if(var2) {
         com.corrodinggames.rts.gameFramework.l.e("Failed to load some units, keeping old config");
         ArrayList var14 = l.c;
         synchronized(l.c) {
            l.c.clear();
            l.c.addAll(var5);
         }

         l.d = var6;
      }

      return var4;
   }

   public static void b() {
      com.corrodinggames.rts.gameFramework.utility.m var0 = new com.corrodinggames.rts.gameFramework.utility.m();
      b = 0;
      c = 0;
      d = 0;
      Iterator var1 = com.corrodinggames.rts.game.units.am.bF().iterator();

      while(var1.hasNext()) {
         com.corrodinggames.rts.game.units.am var2 = (com.corrodinggames.rts.game.units.am)var1.next();
         com.corrodinggames.rts.game.units.as var3 = var2.r();
         if(var3 instanceof l && !var0.contains(var3)) {
            var0.add((l)var3);
         }
      }

      if(var0.size() > 0) {
         boolean var4 = a(var0);
      }

   }

   public static void c() {
      boolean var0 = false;
      com.corrodinggames.rts.gameFramework.utility.m var1 = new com.corrodinggames.rts.gameFramework.utility.m();
      Iterator var2 = l.c.iterator();

      while(var2.hasNext()) {
         l var3 = (l)var2.next();
         boolean var4 = false;
         Iterator var5 = var3.k.iterator();

         while(var5.hasNext()) {
            aa var6 = (aa)var5.next();
            long var7 = var6.a(false);
            if(var7 != var6.a) {
               var4 = true;
               var6.a = var7;
            }
         }

         if(var4) {
            if(!var0) {
               com.corrodinggames.rts.gameFramework.l.e("Detected unit changes");
               var0 = true;
            }

            var1.add(var3);
         }
      }

      if(var1.size() > 0) {
         a(var1);
      }

   }

   public static void d() {
      if(l.e != null) {
         com.corrodinggames.rts.gameFramework.l.e("applyPendingNetworkUnits: Applying new network units from server (" + l.e.size() + " units)");
         l.d = l.e;
         l.e = null;
         e();
      } else {
         com.corrodinggames.rts.gameFramework.l.e("applyPendingNetworkUnits: no server units list found");
      }

   }

   public static ArrayList a(boolean var0) {
      ArrayList var1 = new ArrayList();
      ArrayList var2 = l.c;
      synchronized(l.c) {
         Iterator var3 = l.c.iterator();

         while(var3.hasNext()) {
            l var4 = (l)var3.next();
            if(var4.J == null || var4.J.m() && var0) {
               var1.add(var4);
            }
         }

         return var1;
      }
   }

   public static com.corrodinggames.rts.gameFramework.utility.ab a(String var0) {
      HashMap var1 = n;
      synchronized(n) {
         com.corrodinggames.rts.gameFramework.utility.ab var2 = (com.corrodinggames.rts.gameFramework.utility.ab)n.get(var0);
         if(var2 != null) {
            return var2;
         } else {
            com.corrodinggames.rts.gameFramework.utility.j var3 = b(var0);
            if(var3 == null) {
               return null;
            } else {
               BufferedInputStream var4 = new BufferedInputStream(var3);

               com.corrodinggames.rts.gameFramework.utility.ab var5;
               try {
                  var5 = new com.corrodinggames.rts.gameFramework.utility.ab(var4, var0);
                  var5.a();
                  var5.f = var3.d();
               } catch (IOException var8) {
                  var8.printStackTrace();
                  throw new bo("Load of \'" + var0 + "\' failed: " + var8.getMessage());
               }

               n.put(var0, var5);
               return var5;
            }
         }
      }
   }

   public static void a(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3, boolean var4) {
      com.corrodinggames.rts.gameFramework.utility.ab var5 = a(var2);
      if(var5 == null) {
         if(!var4) {
            throw new bo("[" + var3 + "] Could not find conf target:" + var2);
         }
      } else {
         var0.o(var5.f);
         var1.a(var5);
         a(var0, var1, var5, var2, 1);
      }
   }

   public static void a(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, com.corrodinggames.rts.gameFramework.utility.ab var2, String var3, int var4) {
      if(var4 > 10) {
         throw new bo("copyFrom can only be 10 levels deep, maybe you have a loop?");
      } else {
         String var5 = var2.b("core", "copyFrom", (String)null);
         if(var5 != null) {
            String[] var6 = var5.split(",");
            Collections.reverse(Arrays.asList(var6));
            String[] var7 = var6;
            int var8 = var6.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               String var10 = var7[var9];
               var10 = var10.trim();
               if(!var10.equals("")) {
                  if(var10.contains("..")) {
                     throw new bo("\'..\' not supported in copyFrom");
                  }

                  String var11;
                  String var12;
                  if(var10.startsWith("ROOT:")) {
                     var10 = var10.substring("ROOT:".length());
                     if(var0.J == null) {
                        var12 = "units/common.ini";
                     } else {
                        var12 = var0.J.q + "/common.ini";
                     }

                     var11 = a(com.corrodinggames.rts.gameFramework.f.h(var12), var10);
                  } else if(var10.startsWith("CORE:")) {
                     var10 = var10.substring("CORE:".length());
                     var12 = "units/common.ini";
                     var11 = a(com.corrodinggames.rts.gameFramework.f.h(var12), var10);
                  } else {
                     var11 = a(com.corrodinggames.rts.gameFramework.f.h(var3), var10);
                  }

                  com.corrodinggames.rts.gameFramework.utility.ab var14 = a(var11);
                  if(var14 == null) {
                     String var13 = "Could not find copyFrom target:" + var11;
                     if(var4 != 0) {
                        var13 = var13 + " (while loading: " + var3 + ")";
                     }

                     throw new bo(var13);
                  }

                  var0.o(var14.f);
                  var1.a(var14);
                  a(var0, var1, var14, var11, var4 + 1);
               }
            }
         }

      }
   }

   public static void a(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3, int var4) {
      if(var4 > 10) {
         throw new bo("@copyFromSection can only be 10 levels deep, maybe you have a loop?");
      } else {
         String var5 = var1.b(var3, "@copyFromSection", (String)null);
         if(var5 != null && !var5.equals("")) {
            String[] var6 = var5.split(",");
            Collections.reverse(Arrays.asList(var6));
            String[] var7 = var6;
            int var8 = var6.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               String var10 = var7[var9];
               var10 = var10.trim();
               if(!var10.equals("")) {
                  com.corrodinggames.rts.gameFramework.utility.m var11 = var1.k(var10, "");
                  if(var11.size() == 0) {
                     throw new bo("[" + var3 + "]@copyFromSection: Could not find keys in target section: " + var10);
                  }

                  Iterator var12 = var11.iterator();

                  while(var12.hasNext()) {
                     String var13 = (String)var12.next();
                     String var14 = var1.b(var10, var13);
                     if(var14 != null) {
                        var1.d(var2, var13, var14);
                     }
                  }

                  a(var0, var1, var2, var10, var4 + 1);
               }
            }

         }
      }
   }

   public static bb a(com.corrodinggames.rts.gameFramework.utility.ab var0, String var1, String var2, String var3) {
      return var0.a(var1, var2, var3, false);
   }

   public static aj a(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3, String var4) {
      return var1.a(var0, var2, var3, var4);
   }

   public static l a(l var0) {
      String var1 = var0.D;
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      l var3 = null;
      String var4 = null;
      if(var0.J != null) {
         var4 = var0.J.R;
      }

      HashMap var5 = n;
      synchronized(n) {
         n.clear();
      }

      s = null;

      try {
         var3 = a(var1, var0.J, var0.K, var0.L);
      } catch (RuntimeException var10) {
         var10.printStackTrace();
         if(s == null) {
            String var6 = "Error loading unit:" + a(var0.J, var1, true) + "\n" + var10.getMessage();
            var2.a(var6, 1);
         }
      }

      if(var3 == null && var0.J != null) {
         var0.J.R = var4;
      }

      if(var3 != null) {
         ArrayList var11 = l.c;
         synchronized(l.c) {
            l.c.remove(var0);
         }

         a((com.corrodinggames.rts.game.units.as)var0, var3, true);
         if(l.d.remove(var0)) {
            l.d.add(var3);
            if(var0.H != var3.H) {
               ++d;
            }
         } else {
            com.corrodinggames.rts.gameFramework.l.e("Changed unit was not enabled (original not found in customUnitTypes)");
         }

         com.corrodinggames.rts.game.n.P();
         com.corrodinggames.rts.gameFramework.f.g.K();
      }

      return var3;
   }

   public static void a(com.corrodinggames.rts.game.units.as var0, l var1, boolean var2) {
      Iterator var3 = com.corrodinggames.rts.game.units.am.bF().iterator();

      while(var3.hasNext()) {
         com.corrodinggames.rts.game.units.am var4 = (com.corrodinggames.rts.game.units.am)var3.next();
         if(var4 instanceof j) {
            j var5 = (j)var4;
            if(var5.x == var0) {
               com.corrodinggames.rts.game.n.b((com.corrodinggames.rts.game.units.am)var5);
               var5.a(var1, false, var2);
               var5.S();
               if(var5.dg() != null) {
                  var5.dg().a(var1);
               }

               com.corrodinggames.rts.game.n.c((com.corrodinggames.rts.game.units.am)var5);
            }

            if(var5.z == var0) {
               var5.z = var1;
            }
         }
      }

   }

   public static String a(ArrayList var0) {
      HashMap var1 = new HashMap();
      Iterator var2 = var0.iterator();

      com.corrodinggames.rts.gameFramework.i.b var4;
      Integer var5;
      while(var2.hasNext()) {
         l var3 = (l)var2.next();
         var4 = var3.J;
         if(var4 != null) {
            var5 = (Integer)var1.get(var4);
            if(var5 == null) {
               var5 = Integer.valueOf(1);
            } else {
               var5 = Integer.valueOf(var5.intValue() + 1);
            }

            var1.put(var4, var5);
         }
      }

      String var6 = "";

      for(Iterator var7 = var1.keySet().iterator(); var7.hasNext(); var6 = var6 + var4.a() + "(unitCount: " + var5 + (var4.m()?"":"[disabled]") + "), ") {
         var4 = (com.corrodinggames.rts.gameFramework.i.b)var7.next();
         var5 = (Integer)var1.get(var4);
      }

      return var6;
   }

   public static String b(boolean var0) {
      ArrayList var1 = a(var0);
      l.e = null;
      l.d = var1;
      s = null;
      com.corrodinggames.rts.gameFramework.l.e("enableAll: " + a(l.d));
      e();
      return s;
   }

   public static boolean c(boolean var0) {
      ArrayList var1 = l.d;
      ArrayList var2;
      if(var0) {
         var2 = a(true);
      } else {
         var2 = l.d;
      }

      boolean var3 = true;
      s = null;
      l.d = var2;
      g();
      if(s != null) {
         var3 = false;
      }

      l.d = var1;
      g();
      return var3;
   }

   public static void e() {
      Object var0 = o;
      synchronized(o) {
         n();
      }
   }

   private static void n() {
      l var0 = null;
      ArrayList var1 = new ArrayList();
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      if(var2.as()) {
         com.corrodinggames.rts.game.units.ar[] var3 = com.corrodinggames.rts.game.units.ar.values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            com.corrodinggames.rts.game.units.ar var6 = var3[var5];
            var1.add(var6);
         }
      }

      Iterator var7 = l.d.iterator();

      l var8;
      while(var7.hasNext()) {
         var8 = (l)var7.next();
         var1.add(var8);
         if(var8.M.equals("missing") && var8.J == null) {
            var0 = var8;
         }
      }

      com.corrodinggames.rts.game.units.ar.ae = var1;
      com.corrodinggames.rts.game.units.am.bL();
      g();
      f();
      com.corrodinggames.rts.game.units.custom.e.a.e();
      if(var0 == null) {
         com.corrodinggames.rts.gameFramework.l.e("missingPlaceHolder is not an active unit, searching for new target");
         var7 = l.d.iterator();

         while(var7.hasNext()) {
            var8 = (l)var7.next();
            if(var8.M.equals("missing")) {
               com.corrodinggames.rts.gameFramework.l.e("Found a missing placeholder");
               var0 = var8;
            }
         }
      }

      l.b = var0;
   }

   public static void f() {
      float var0 = 50.0F;
      float var1 = 50.0F;
      Iterator var2 = l.d.iterator();

      while(var2.hasNext()) {
         l var3 = (l)var2.next();
         float var4 = (float)var3.cW;
         if(var4 > 250.0F) {
            var4 = 250.0F;
         }

         if(var0 < var4) {
            var0 = var4;
         }

         if(var3.aH && var1 < var4) {
            var1 = var4;
         }
      }

      p = var0;
      q = var1;
   }

   public static com.corrodinggames.rts.gameFramework.utility.j b(String var0) {
      String var1 = "" + var0;
      return com.corrodinggames.rts.gameFramework.e.a.k(var1);
   }

   public static void b(ArrayList var0) {
      Collections.sort(var0);
   }

   public static void a(com.corrodinggames.rts.game.units.as var0) {
      com.corrodinggames.rts.gameFramework.l var1 = com.corrodinggames.rts.gameFramework.l.B();

      try {
         var0.h();
         l var2;
         v var4;
         Iterator var17;
         if(var0 instanceof l) {
            var2 = (l)var0;
            if(var2.fI != null) {
               com.corrodinggames.rts.game.units.as var3 = l.s(var2.fI);
               if(var3 == null) {
                  throw new bo("Could not find [ai]upgradedFrom target:" + var2.fI);
               }

               var2.b(var3);
            }

            var17 = var2.p.iterator();

            while(var17.hasNext()) {
               var4 = (v)var17.next();
               var4.a();
            }

            if(var2.eH) {
               l.g.add(var2);
            }
         }

         Iterator var16 = l.d.iterator();

         while(var16.hasNext()) {
            l var19 = (l)var16.next();
            if(var0 instanceof l) {
               l var20 = (l)var0;
               if(var19.fI != null && var19.fI.equalsIgnoreCase(var20.i())) {
                  var20.b((com.corrodinggames.rts.game.units.as)var19);
               }
            }

            Iterator var22 = var19.gg.iterator();

            while(var22.hasNext()) {
               p var5 = (p)var22.next();
               if(var5.a.equalsIgnoreCase(var0.i())) {
                  var5.e = true;
                  int var6 = var19.cl;
                  boolean var7 = false;

                  for(int var8 = var6; var8 <= 3; ++var8) {
                     ArrayList var9 = var0.a(var8);
                     Object var10;
                     if(!var19.aH && !var5.c) {
                        var10 = new com.corrodinggames.rts.game.units.a.l(var19);
                     } else {
                        var10 = new com.corrodinggames.rts.game.units.a.v(var19);
                     }

                     if(var5.b != -999.0F) {
                        ((com.corrodinggames.rts.game.units.a.s)var10).g = var5.b;
                     }

                     boolean var11;
                     if(var5.f != null) {
                        var11 = false;
                        if(!(var0 instanceof l)) {
                           com.corrodinggames.rts.game.units.am var12 = com.corrodinggames.rts.game.units.am.a(var0);
                           if(!(var12 instanceof com.corrodinggames.rts.game.units.y)) {
                              var11 = true;
                           }
                        }

                        if(!var11) {
                           ((com.corrodinggames.rts.game.units.a.s)var10).h = com.corrodinggames.rts.game.units.custom.a.c.a(var5);
                        } else if(!var7) {
                           var7 = true;
                           var19.r("builtFrom isLocked currently cannot be used when targeting old-style unit:" + var0.i());
                        }
                     }

                     var11 = false;
                     Iterator var41 = var9.iterator();

                     while(var41.hasNext()) {
                        com.corrodinggames.rts.game.units.a.s var13 = (com.corrodinggames.rts.game.units.a.s)var41.next();
                        if(((com.corrodinggames.rts.game.units.a.s)var10).equals(var13)) {
                           var11 = true;
                        }
                     }

                     if(!var11) {
                        var9.add(var10);
                     }

                     b(var9);
                  }
               }
            }
         }

         if(var0 instanceof l) {
            var2 = (l)var0;
            var17 = var2.gh.iterator();

            while(var17.hasNext()) {
               com.corrodinggames.rts.game.units.custom.a.d var23 = (com.corrodinggames.rts.game.units.custom.a.d)var17.next();
               int var27;
               ArrayList var28;
               if(var23.k != null && var23.k.equalsIgnoreCase("setRally")) {
                  for(var27 = 1; var27 <= 3; ++var27) {
                     var28 = var0.a(var27);
                     com.corrodinggames.rts.game.units.a.o var35 = new com.corrodinggames.rts.game.units.a.o();
                     if(var23.p != -999.0F) {
                        var35.g = var23.p;
                     }

                     var28.add(var35);
                     var2.dc = true;
                     b(var28);
                  }
               } else if(var23.k != null && var23.k.equalsIgnoreCase("reclaim")) {
                  for(var27 = 1; var27 <= 3; ++var27) {
                     var28 = var0.a(var27);
                     com.corrodinggames.rts.game.units.a.m var33 = new com.corrodinggames.rts.game.units.a.m(true);
                     if(var23.p != -999.0F) {
                        var33.g = var23.p;
                     }

                     var28.add(var33);
                     b(var28);
                  }
               } else if(var23.k != null && var23.k.equalsIgnoreCase("repair")) {
                  for(var27 = 1; var27 <= 3; ++var27) {
                     var28 = var0.a(var27);
                     com.corrodinggames.rts.game.units.a.n var31 = new com.corrodinggames.rts.game.units.a.n();
                     if(var23.p != -999.0F) {
                        var31.g = var23.p;
                     }

                     var28.add(var31);
                     b(var28);
                  }
               } else {
                  com.corrodinggames.rts.game.units.as var25 = null;
                  if(var23.k != null) {
                     var25 = com.corrodinggames.rts.game.units.ar.a(var23.k);
                     if(var25 == null) {
                        throw new bo("Could not find canBuild target:" + var23.k);
                     }
                  } else if(var23.aM != com.corrodinggames.rts.game.units.custom.a.f.b) {
                     throw new bo("\'Target\' required for action:" + var23.a());
                  }

                  byte var26 = 1;

                  for(int var30 = var26; var30 <= 3; ++var30) {
                     ArrayList var34 = var0.a(var30);
                     Object var38;
                     if(var23.aM == com.corrodinggames.rts.game.units.custom.a.f.a) {
                        if(!var25.j() && !var23.aK) {
                           var38 = new com.corrodinggames.rts.game.units.a.l(var25);
                           ((com.corrodinggames.rts.game.units.a.s)var38).h = com.corrodinggames.rts.game.units.custom.a.c.a(var23);
                        } else {
                           var38 = new com.corrodinggames.rts.game.units.a.v(var25, var23.aJ, (Integer)null);
                           ((com.corrodinggames.rts.game.units.a.s)var38).h = com.corrodinggames.rts.game.units.custom.a.c.a(var23);
                        }
                     } else {
                        if(var23.aM != com.corrodinggames.rts.game.units.custom.a.f.b) {
                           throw new bo("Could not find actionType:" + var23.aM);
                        }

                        var38 = new com.corrodinggames.rts.game.units.custom.a.g(var23, l.a(var25));
                     }

                     if(var23.p != -999.0F) {
                        ((com.corrodinggames.rts.game.units.a.s)var38).g = var23.p;
                     }

                     boolean var40 = false;
                     Iterator var42 = var34.iterator();

                     while(var42.hasNext()) {
                        com.corrodinggames.rts.game.units.a.s var43 = (com.corrodinggames.rts.game.units.a.s)var42.next();
                        if(((com.corrodinggames.rts.game.units.a.s)var38).equals(var43)) {
                           var40 = true;
                        }
                     }

                     if(!var40) {
                        var34.add(var38);
                     }

                     b(var34);
                  }
               }
            }
         }

         int var21;
         ArrayList var24;
         com.corrodinggames.rts.game.units.a.s var29;
         Iterator var32;
         if(var0 instanceof l) {
            var2 = (l)var0;
            var2.fu = false;

            for(var21 = 1; var21 <= 3; ++var21) {
               var24 = var0.a(var21);
               var32 = var24.iterator();

               while(var32.hasNext()) {
                  var29 = (com.corrodinggames.rts.game.units.a.s)var32.next();
                  if(!(var29 instanceof com.corrodinggames.rts.game.units.custom.a.g) && var29.i() != null) {
                     var2.fu = true;
                  }
               }
            }

            var17 = var2.p.iterator();

            while(var17.hasNext()) {
               var4 = (v)var17.next();
               var4.b();
            }
         }

         boolean var18 = var1.O() && var1.bX.ay.k;

         for(var21 = 1; var21 <= 3; ++var21) {
            var24 = var0.a(var21);
            var32 = var24.iterator();

            while(var32.hasNext()) {
               var29 = (com.corrodinggames.rts.game.units.a.s)var32.next();
               if(var29.h instanceof com.corrodinggames.rts.game.units.custom.a.b) {
                  com.corrodinggames.rts.gameFramework.l.a("=== ChainedActionConfig already on: " + var0.i() + " action:" + var29.b());
                  var29.h = ((com.corrodinggames.rts.game.units.custom.a.b)var29.h).b;
               }

               if(var18) {
                  com.corrodinggames.rts.game.units.custom.d.b var37 = var29.B();
                  com.corrodinggames.rts.game.units.custom.d.b var36 = var29.r_();
                  if(!var37.c() && var36 == null) {
                     com.corrodinggames.rts.game.units.custom.a.b var39 = new com.corrodinggames.rts.game.units.custom.a.b(var29.h);
                     var29.h = var39;
                     var39.c = com.corrodinggames.rts.game.units.custom.d.b.a;
                     var39.d = var37;
                  }
               }
            }
         }
      } catch (bo var14) {
         a(var0.i(), (Exception)var14, var0);
      } catch (RuntimeException var15) {
         a(var0.i(), (Exception)var15, var0);
      }

   }

   public static void g() {
      Object var0 = o;
      synchronized(o) {
         o();
      }
   }

   private static void o() {
      l.g.clear();
      l.f.clear();
      Iterator var0 = l.d.iterator();

      l var1;
      p var3;
      Iterator var13;
      while(var0.hasNext()) {
         var1 = (l)var0.next();
         if(var1.J != null) {
            String var2 = var1.J.R;
            if(var2 != null) {
               com.corrodinggames.rts.gameFramework.l.b(var1.i() + "(mod:" + var1.t() + "): Getting setup while mod has error: " + var2);
            }
         }

         for(var13 = var1.gg.iterator(); var13.hasNext(); var3.e = false) {
            var3 = (p)var13.next();
         }

         var1.fL.clear();
      }

      var0 = l.d.iterator();

      while(var0.hasNext()) {
         var1 = (l)var0.next();

         try {
            if(var1.Q != null) {
               String[] var14 = var1.Q.split(",");
               String[] var16 = var14;
               int var4 = var14.length;

               for(int var5 = 0; var5 < var4; ++var5) {
                  String var6 = var16[var5];
                  var6 = var6.trim();
                  boolean var7 = false;
                  com.corrodinggames.rts.game.units.as var8 = l.a(var6, var7);
                  if(var8 == null) {
                     throw new bo("Could not find overrideAndReplace target:" + var6);
                  }

                  if(var8 instanceof l) {
                     com.corrodinggames.rts.gameFramework.l.e("Replacing:" + var8.i() + " with " + var1.i());
                  }

                  l.f.put(var8, var1);
               }
            }
         } catch (bo var10) {
            a(var1.i(), (Exception)var10, (com.corrodinggames.rts.game.units.as)var1);
         }
      }

      com.corrodinggames.rts.game.units.ar[] var11 = com.corrodinggames.rts.game.units.ar.values();
      int var12 = var11.length;

      for(int var15 = 0; var15 < var12; ++var15) {
         com.corrodinggames.rts.game.units.ar var17 = var11[var15];
         a((com.corrodinggames.rts.game.units.as)var17);
      }

      var0 = l.d.iterator();

      while(var0.hasNext()) {
         var1 = (l)var0.next();
         a((com.corrodinggames.rts.game.units.as)var1);
      }

      var0 = l.d.iterator();

      while(var0.hasNext()) {
         var1 = (l)var0.next();
         var13 = var1.gg.iterator();

         while(var13.hasNext()) {
            var3 = (p)var13.next();
            if(!var3.e) {
               String var18 = var3.d + " failed to find target:" + var3.a;
               var1.q(var18);
               if(var1.R >= 1) {
                  com.corrodinggames.rts.gameFramework.l.e("Converting warning to error (meta.strictLevel=" + var1.R + ")");
                  var1.p(var18);
               }
            }
         }

         if(var1.gp != null && var1.gp.size() > 0) {
            var13 = var1.gp.iterator();

            while(var13.hasNext()) {
               u var19 = (u)var13.next();

               try {
                  var19.b(var1);
               } catch (bo var9) {
                  a(var1.i(), (Exception)var9, (com.corrodinggames.rts.game.units.as)var1);
               }
            }
         }
      }

      var0 = l.d.iterator();

      while(var0.hasNext()) {
         var1 = (l)var0.next();
         var1.r();
      }

      Collections.sort(l.g, new q());
   }

   public static l a(String var0, com.corrodinggames.rts.gameFramework.i.b var1, String var2, String var3) {
      try {
         long var4 = br.a();
         com.corrodinggames.rts.gameFramework.utility.j var6 = b(var0);
         if(var6 == null) {
            throw new RuntimeException("Failed to open unit config file:" + var0);
         } else {
            BufferedInputStream var7 = new BufferedInputStream(var6);
            a(var4, ah.g);
            ++b;
            if(var1 != null) {
               ++c;
            }

            com.corrodinggames.rts.gameFramework.l var8 = com.corrodinggames.rts.gameFramework.l.B();
            String var9 = "core units";
            if(var1 != null) {
               var9 = var1.a();
            }

            var8.h("Loading units - " + b + " (" + var9 + ")");
            l var10 = a(var0, var7, var6.c(), var1, var6, var2, var3);
            long var11 = br.a();

            try {
               var7.close();
               var6.close();
            } catch (IOException var14) {
               var14.printStackTrace();
            }

            a(var11, ah.h);
            return var10;
         }
      } catch (RuntimeException var15) {
         a(var0, (Exception)var15, var1);
         return null;
      }
   }

   public static void h() {
      com.corrodinggames.rts.gameFramework.l var0 = com.corrodinggames.rts.gameFramework.l.B();
      ArrayList var1 = var0.bZ.k();
      j = 0;
      i = 0;
      l = 0;
      k = false;
      long var2 = br.a();
      Iterator var4 = g.values().iterator();

      com.corrodinggames.rts.gameFramework.m.e[] var6;
      while(var4.hasNext()) {
         com.corrodinggames.rts.gameFramework.m.e var5 = (com.corrodinggames.rts.gameFramework.m.e)var4.next();
         var5.v = false;
         int var7;
         int var8;
         com.corrodinggames.rts.gameFramework.m.e var9;
         if(var5.a != null) {
            var6 = var5.a;
            var7 = var6.length;

            for(var8 = 0; var8 < var7; ++var8) {
               var9 = var6[var8];
               var9.v = false;
            }
         }

         if(var5.b != null) {
            var6 = var5.b;
            var7 = var6.length;

            for(var8 = 0; var8 < var7; ++var8) {
               var9 = var6[var8];
               var9.v = false;
            }
         }

         if(var5.c != null) {
            var6 = var5.c;
            var7 = var6.length;

            for(var8 = 0; var8 < var7; ++var8) {
               var9 = var6[var8];
               var9.v = false;
            }
         }
      }

      com.corrodinggames.rts.gameFramework.a.i var16;
      for(var4 = h.values().iterator(); var4.hasNext(); var16.g = false) {
         var16 = (com.corrodinggames.rts.gameFramework.a.i)var4.next();
      }

      byte[] var15 = null;
      byte[][] var17 = (byte[][])null;
      ByteBuffer[] var18 = null;

      try {
         var15 = new byte[8000000];
         var15[0] = var0.dZ;
         var0.ea = var15[1];
         var17 = new byte[][]{new byte[3000000], new byte[3000000]};
         var17[0][0] = var0.dZ;
         var17[1][0] = var0.dZ;
         if(!com.corrodinggames.rts.gameFramework.l.at()) {
            var18 = new ByteBuffer[]{ByteBuffer.allocateDirect(5000000), ByteBuffer.allocateDirect(5000000), ByteBuffer.allocateDirect(5000000), ByteBuffer.allocateDirect(5000000)};
         }
      } catch (OutOfMemoryError var14) {
         System.gc();
         com.corrodinggames.rts.gameFramework.l.e("Failed to reserve memory pre-mod load");
      }

      ArrayList var19 = l.c;
      synchronized(l.c) {
         l.c.clear();
      }

      l.d.clear();
      l.e = null;
      l.f.clear();
      var0.bZ.n();
      b = 0;
      c = 0;
      HashMap var20 = n;
      synchronized(n) {
         n.clear();
      }

      a(com.corrodinggames.rts.gameFramework.e.a.p("units"), 1, false, (com.corrodinggames.rts.gameFramework.i.b)null, com.corrodinggames.rts.gameFramework.e.a.p("units"), (String)null);
      if(!com.corrodinggames.rts.gameFramework.l.aJ && !var0.ar) {
         String var21 = m();
         if(!com.corrodinggames.rts.gameFramework.e.a.f(var21)) {
            com.corrodinggames.rts.gameFramework.l.e("Modded Custom \'" + var21 + "\' directory not found");
         }

         Iterator var23 = var1.iterator();

         String var10;
         com.corrodinggames.rts.gameFramework.i.b var24;
         while(var23.hasNext()) {
            var24 = (com.corrodinggames.rts.gameFramework.i.b)var23.next();
            if(!var24.y && var24.q != null) {
               var10 = var24.j();
               if(var24.m) {
                  var10 = com.corrodinggames.rts.gameFramework.e.a.p(var10);
               }

               if(var24.f) {
                  com.corrodinggames.rts.gameFramework.l.e("Disabled mod at:" + var10 + " (name:" + var24.a() + ")");
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("Loading mod at:" + var10 + " (name:" + var24.a() + ")");
               }

               a(var10, 2, true, var24, var10, (String)null);
            }
         }

         var23 = var1.iterator();

         while(var23.hasNext()) {
            var24 = (com.corrodinggames.rts.gameFramework.i.b)var23.next();
            if(var24.y && var24.q != null) {
               var10 = var24.i();
               if(var24.f) {
                  com.corrodinggames.rts.gameFramework.l.e("Disabled workshop mod at:" + var10 + " (name:" + var24.a() + ")");
               } else {
                  com.corrodinggames.rts.gameFramework.l.e("Loading workshop mod at:" + var10 + " (name:" + var24.a() + ")");
               }

               a(var10, 2, true, var24, var10, (String)null);
            }
         }
      }

      a();
      b(true);
      com.corrodinggames.rts.gameFramework.l.e("Done loading custom units. image cacheHits:" + j + " image cacheMisses:" + i + " (in: " + br.a(var2) + "ms)");
      com.corrodinggames.rts.gameFramework.l.e("========= Mods data loaded ===========");
      com.corrodinggames.rts.gameFramework.l.e("Number of mods:" + var1.size());
      Iterator var22 = var1.iterator();

      while(var22.hasNext()) {
         com.corrodinggames.rts.gameFramework.i.b var25 = (com.corrodinggames.rts.gameFramework.i.b)var22.next();
         var25.t();
      }

      com.corrodinggames.rts.gameFramework.l.e("================================");
      if(var18 != null) {
         var18[0] = null;
         var18[1] = null;
         var18[2] = null;
         var18[3] = null;
         var6 = null;
      }

      if(var17 != null) {
         var17[0] = null;
         var17[1] = null;
         var17 = (byte[][])null;
      }

      if(var15 != null) {
         var15[1] = var0.dZ;
         var0.ea = var15[1];
         var4 = null;
         System.gc();
         System.gc();
      }

   }

   public static void a(String var0, int var1, boolean var2, com.corrodinggames.rts.gameFramework.i.b var3, String var4, String var5) {
      boolean var6 = var2 && var1 == 1;
      com.corrodinggames.rts.gameFramework.l var7 = com.corrodinggames.rts.gameFramework.l.B();
      if(var3 != null) {
         if(var3.f && !var7.bQ.loadDisabledModData) {
            var3.C = true;
            return;
         }

         var3.C = false;
      }

      if(var3 != null && var3.f) {
         com.corrodinggames.rts.gameFramework.l.e("Note: Loading disabled mod: " + var0);
      }

      com.corrodinggames.rts.gameFramework.e.a.c();
      String[] var8 = com.corrodinggames.rts.gameFramework.e.a.h(var0);
      if(var8 == null) {
         String var21 = com.corrodinggames.rts.gameFramework.e.a.c();
         com.corrodinggames.rts.gameFramework.l.b("readAllCustomUnitConfigs: ERROR");
         com.corrodinggames.rts.gameFramework.l.b("readAllCustomUnitConfigs: Failed to load:" + var0);
         if(var3 != null) {
            if(!var3.D) {
               if(var21 == null) {
                  var3.R = "Failed to list directory, check file permissions";
               } else {
                  var3.R = "Failed to list directory: " + var21;
               }
            } else {
               var3.S = "Failed to list subdirectory: \'" + var0 + "\' check file permissions";
               if(var21 != null) {
                  var3.S = var3.S + ": " + var21;
               }
            }
         }

      } else {
         if(var3 != null) {
            var3.D = true;
         }

         String[] var9;
         int var10;
         int var11;
         String var12;
         if(!var6) {
            var9 = var8;
            var10 = var8.length;

            for(var11 = 0; var11 < var10; ++var11) {
               var12 = var9[var11];
               if(var12.equalsIgnoreCase("all-units.template")) {
                  var5 = var0;
               }
            }
         }

         var9 = var8;
         var10 = var8.length;

         for(var11 = 0; var11 < var10; ++var11) {
            var12 = var9[var11];
            if(!var12.equals("custom_units_here.txt") && !var12.equals("mods_here_will_be_enabled_by_default.txt") && !var12.equals("__MACOSX")) {
               boolean var13 = false;
               com.corrodinggames.rts.gameFramework.i.b var14 = var3;
               if(var2 && var1 == 1 && var3 == null) {
                  var14 = var7.bZ.e(var12);
                  if(var14 == null) {
                     com.corrodinggames.rts.gameFramework.l.b("readAllCustomUnitConfigs: Could not find linked mod:" + var12);
                     var14 = var7.bZ.c;
                  }

                  var13 = true;
               }

               String var15;
               if(var12.toLowerCase(Locale.ENGLISH).endsWith(".ini") && !var6) {
                  var15 = var0 + "/" + var12;
                  if(r != var14 && var14 != null) {
                     r = var14;
                     a();
                     com.corrodinggames.rts.gameFramework.l.e("Loading units from mod: " + var14.c);
                  }

                  if(var12.equalsIgnoreCase("desktop.ini")) {
                     com.corrodinggames.rts.gameFramework.l.e("Skipping possible system file: " + var15);
                  } else {
                     long var22 = br.a();
                     a(var15, var14, var4, var5);
                     a(var22, ah.f);
                  }
               } else if(var12.toLowerCase(Locale.ENGLISH).endsWith(".tmx")) {
                  var15 = var0 + "/" + var12;
                  com.corrodinggames.rts.gameFramework.l.e("Found map: " + var15);
                  if(var14 != null && var14.B) {
                     var7.bZ.a(var15, var14);
                  } else {
                     com.corrodinggames.rts.gameFramework.l.e("Skipping map due to mod settings");
                  }
               } else {
                  var15 = var0 + "/" + var12;
                  if(var1 < 10) {
                     if(com.corrodinggames.rts.gameFramework.e.a.f(var15)) {
                        String var16 = var4;
                        if(var4 == null) {
                           var16 = var15;
                        }

                        long var17 = -1L;
                        if(var13) {
                           var17 = br.a();
                           com.corrodinggames.rts.gameFramework.l.e("============");
                           com.corrodinggames.rts.gameFramework.l.e(">>> Mod \'" + var14.c() + "\'" + (var14.m()?"":" (disabled)"));
                        }

                        a(var15, var1 + 1, var2, var14, var16, var5);
                        if(var13 && var14 != null && var14.m()) {
                           double var19 = (double)br.a(var17);
                           com.corrodinggames.rts.gameFramework.l.e("Mod \'" + var14.c() + "\' load took:" + br.a(var19));
                        }
                     }
                  } else {
                     com.corrodinggames.rts.gameFramework.l.e("Too many levels:" + var15);
                  }
               }
            }
         }

      }
   }

   public static l a(String var0, InputStream var1, long var2, com.corrodinggames.rts.gameFramework.i.b var4, com.corrodinggames.rts.gameFramework.utility.j var5, String var6, String var7) {
      com.corrodinggames.rts.gameFramework.l var8 = com.corrodinggames.rts.gameFramework.l.B();

      try {
         if(a) {
            String var9 = "CORE";
            if(var4 != null) {
               var9 = var4.j();
            }

            com.corrodinggames.rts.gameFramework.l.e("Loading unit config: " + var0 + " [" + var9 + "]");
         }

         var8.bO.e();
         long var94 = br.a();

         com.corrodinggames.rts.gameFramework.utility.ab var11;
         try {
            var11 = new com.corrodinggames.rts.gameFramework.utility.ab(var1, var0);
         } catch (IOException var90) {
            throw new RuntimeException(var90);
         }

         a(var94, ah.e);
         String var12 = "core";
         String var13 = "graphics";
         String var14 = "attack";
         String var15 = "movement";
         String var16 = "ai";
         l var17 = new l();
         if(var11.a(var12, "dont_load", Boolean.valueOf(false)).booleanValue()) {
            return null;
         } else {
            var17.D = var0;
            var17.E = var5.d();
            var17.F = var17.D;
            var17.J = var4;
            var17.K = var6;
            var17.L = var7;
            e = var4;
            f = false;
            if(var17.J != null) {
               ;
            }

            long var18 = br.a();
            a(var17, var11, var11, var0, 0);
            if(var17.L != null) {
               a(var17, var11, var17.L + "/" + "all-units.template", "AUTO units.template", true);
            }

            var11.a("core", "copyFrom");
            var17.R = var11.b(var12, "strictLevel", Integer.valueOf(0)).intValue();
            if(var17.R < 0) {
               throw new bo("[core]strictLevel cannot be < 0");
            } else if(var17.R > 1) {
               throw new bo("[core]strictLevel cannot yet be > 1");
            } else {
               var17.gs = var11.a(var12, "logIfCreditResourceUsed", Boolean.valueOf(false)).booleanValue();
               var11.a(var12, "dont_load");
               var11.b(var12, "class", "CustomUnitMetadata");
               com.corrodinggames.rts.gameFramework.utility.m var21 = var11.c("@copyFrom_skipThisSection");
               Iterator var22 = var21.iterator();

               String var23;
               while(var22.hasNext()) {
                  var23 = (String)var22.next();
                  var11.a(var23, "@copyFrom_skipThisSection");
               }

               com.corrodinggames.rts.gameFramework.utility.m var95 = var11.c("@copyFromSection");
               Iterator var96 = var95.iterator();

               String var24;
               while(var96.hasNext()) {
                  var24 = (String)var96.next();
                  a(var17, var11, var24, var24, 0);
               }

               com.corrodinggames.rts.game.units.custom.f.a.a(var17, var11);
               var23 = var11.b(var12, "overrideResourceLoadPath", (String)null);
               if(var23 != null) {
                  var17.F = a(var17, var0, var23);
               }

               a(var18, ah.i);
               var17.M = var11.e(var12, "name");
               var17.H = var11.c();
               if(var17.M.equals("self")) {
                  throw new bo("Unit name: " + var17.M + " is reserved");
               } else if(var17.M.startsWith("self.")) {
                  throw new bo("Unit name cannot start with self.");
               } else {
                  var24 = var11.b(var12, "altNames", (String)null);
                  int var26;
                  int var27;
                  String var28;
                  if(var24 != null && !var24.equalsIgnoreCase("NONE")) {
                     String[] var25 = var24.split(",");
                     var26 = var25.length;

                     for(var27 = 0; var27 < var26; ++var27) {
                        var28 = var25[var27];
                        var28 = var28.trim();
                        var17.N.add(var28);
                     }
                  }

                  var17.O = g.a(var11.b(var12, "tags", (String)null));
                  if(var17.R >= 1 && var17.O != null) {
                     g[] var97 = var17.O.a;
                     var26 = var97.length;

                     for(var27 = 0; var27 < var26; ++var27) {
                        g var102 = var97[var27];
                        if(var102.a.contains(" ")) {
                           throw new bo("(strictLevel 1) [core]tags: space in tag: \'" + var102.a + "\'");
                        }
                     }
                  }

                  var17.Q = var11.b(var12, "overrideAndReplace", (String)null);
                  if(var17.Q != null && var17.Q.equalsIgnoreCase("NONE")) {
                     var17.Q = null;
                  }

                  String var98 = var11.b(var12, "defineUnitMemory", (String)null);
                  if(var98 != null) {
                     var17.r.addDefineValue(var17, var12, "defineUnitMemory", var98);
                     if(var17.r.hasArrays()) {
                        var17.a("1.15p11", 115011, var12, "Memory arrays (in defineUnitMemory)");
                     }
                  }

                  Iterator var99 = var11.k(var12, "@memory ").iterator();

                  String var29;
                  String var101;
                  while(var99.hasNext()) {
                     var101 = (String)var99.next();
                     var28 = var101.substring("@memory ".length()).trim();
                     var29 = var11.b(var12, var101, (String)null);
                     if(var29 != null) {
                        if(var29.contains(",")) {
                           throw new bo("[" + var12 + "]" + var101 + ": Only a single variable can be defined per @memory");
                        }

                        var17.r.addSingleDefine(var17, var28, var29, var12, var101);
                        if(var17.r.hasArrays()) {
                           var17.a("1.15p11", 115011, var12, "Memory arrays (in " + var101 + ")");
                        }
                     }
                  }

                  var17.T = (ad)var11.a(var12, "onNewMapSpawn", (Enum)null, ad.class);
                  var17.aG = var11.a(var12, "globalScale", Float.valueOf(1.0F)).floatValue();
                  var17.o(var17.E);
                  if(var17.M.equals("missing")) {
                     if(var4 == null) {
                        com.corrodinggames.rts.gameFramework.l.e("Setting missingPlaceHolder");
                        l.b = var17;
                     } else {
                        com.corrodinggames.rts.gameFramework.l.e("Not setting missingPlaceHolder, as we are in a mod");
                     }
                  }

                  var17.aE = var11.b(var12, "displayLocaleKey", (String)null);
                  var17.aC = a(var11, var12, "displayText", (String)null);
                  var17.aD = a(var11, var12, "displayDescription", (String)null);
                  var17.eD = var11.a(var12, "isBio", Boolean.valueOf(false)).booleanValue();
                  var17.eE = var11.a(var12, "isBug", Boolean.valueOf(false)).booleanValue();
                  var17.eH = var11.a(var12, "isPickableStartingUnit", Boolean.valueOf(false)).booleanValue();
                  var17.eI = var11.a(var12, "startFallingWhenStartingUnit", Boolean.valueOf(false)).booleanValue();
                  var17.cy = var11.a(var12, "stayNeutral", Boolean.valueOf(false)).booleanValue();
                  var17.cz = var11.a(var12, "createNeutral", Boolean.valueOf(false)).booleanValue();
                  var17.cA = var11.a(var12, "allowCaptureWhenNeutralByAI", Boolean.valueOf(false)).booleanValue();
                  if(var11.a(var12, "createOnNeutralTeam", Boolean.valueOf(false)).booleanValue()) {
                     var17.cz = true;
                  }

                  var17.cB = var11.a(var12, "whileNeutralTransportAnyTeam", Boolean.valueOf(false)).booleanValue();
                  var17.cC = var11.a(var12, "whileNeutralConvertToTransportedTeam", Boolean.valueOf(false)).booleanValue();
                  var17.cD = var11.a(var12, "convertToNeutralIfNotTransporting", Boolean.valueOf(false)).booleanValue();
                  if(var17.cD) {
                     var17.cy = true;
                  }

                  var17.cE = var11.a(var12, "createOnAggressiveTeam", Boolean.valueOf(false)).booleanValue();
                  var17.aF = var11.a(var12, "showInEditor", Boolean.valueOf(true)).booleanValue();
                  var17.U = var11.b(var13, "total_frames", Integer.valueOf(1)).intValue();
                  if(var17.U < 1) {
                     throw new bo("TOTAL_FRAMES cannot be: " + var17.U + " (must be 1 or more)");
                  } else {
                     var17.W = var11.b(var13, "frame_width", Integer.valueOf(-1)).intValue();
                     var17.X = var11.b(var13, "frame_height", Integer.valueOf(-1)).intValue();
                     var17.Y = var11.b(var13, "default_frame", Integer.valueOf(0)).intValue();
                     var17.ah = var11.b(var13, "image_offsetX", Integer.valueOf(0)).intValue();
                     var17.ai = var11.b(var13, "image_offsetY", Integer.valueOf(0)).intValue();
                     var17.aj = var11.a(var13, "image_offsetH", Float.valueOf(0.0F)).floatValue();
                     if(var17.ah != 0 || var17.ai != 0 || var17.aj != 0.0F) {
                        var17.ak = true;
                     }

                     var17.ac = com.corrodinggames.rts.game.o.a;
                     if(var11.a(var13, "teamColorsUseHue", Boolean.valueOf(false)).booleanValue()) {
                        var17.ac = com.corrodinggames.rts.game.o.b;
                     }

                     String var100 = var11.b(var13, "teamColoringMode", (String)null);
                     if(var100 != null) {
                        if(var11.a(var13, "teamColorsUseHue", (Boolean)null) != null) {
                           throw new bo("Cannot use teamColoringMode and teamColorsUseHue at the same time");
                        }

                        if(var100.equalsIgnoreCase("pureGreen")) {
                           var17.ac = com.corrodinggames.rts.game.o.a;
                        } else if(var100.equalsIgnoreCase("hueAdd")) {
                           var17.ac = com.corrodinggames.rts.game.o.b;
                        } else if(var100.equalsIgnoreCase("hueShift")) {
                           var17.ac = com.corrodinggames.rts.game.o.d;
                        } else {
                           if(!var100.equalsIgnoreCase("disabled")) {
                              throw new bo("Unknown teamColoringMode:" + var100);
                           }

                           var17.ac = com.corrodinggames.rts.game.o.e;
                        }
                     }

                     var17.ab = var11.a(var13, "imageSmoothing", Boolean.valueOf(false)).booleanValue();
                     var17.aa = var11.a(var13, "imageSmoothingWhenZoomedIn", Boolean.valueOf(false)).booleanValue();
                     var17.Z = var11.a(var17, var13, "isVisible", (LogicBoolean)null);
                     if(var17.Z == LogicBoolean.trueBoolean) {
                        var17.Z = null;
                     }

                     var17.cL.m = var11.a(var13, "isVisibleToEnemies", Boolean.valueOf(true)).booleanValue();
                     var101 = var11.e(var13, "image");
                     var17.ad = var17.a(var17.F, var101, var17.ab, var13, "image");
                     if(var17.ad == null) {
                        throw new bo("Main unit image must be set on custom unit");
                     } else {
                        var17.ae = var11.a(var13, "image_floatingPointSize", Boolean.valueOf(false)).booleanValue();
                        var17.af = var17.ad.m() / var17.U;
                        var17.ag = var17.ad.l();
                        if(var17.af < 1) {
                           var17.af = 1;
                        }

                        if(var17.W > 0) {
                           var17.af = var17.W;
                        }

                        if(var17.X > 0) {
                           var17.ag = var17.X;
                           if(var17.ag < var17.ad.l()) {
                              var17.V = var17.ad.m() / var17.af;
                              if(var17.V < 1) {
                                 var17.V = 1;
                              }
                           }
                        }

                        var17.al = var17.a(var11, var13, "image_back");
                        var17.am = var11.a(var13, "image_back_always_use_full_image", Boolean.valueOf(false)).booleanValue();
                        var17.an = var17.a(var11, var13, "image_wreak");
                        var17.ao = var17.a(var11, var13, "image_turret");
                        var17.as = com.corrodinggames.rts.game.units.e.j.dN;
                        var28 = var11.b(var13, "image_shadow", "NONE");
                        com.corrodinggames.rts.gameFramework.m.e var30;
                        if(var28.equalsIgnoreCase("AUTO")) {
                           var29 = "[autoShadow:" + var17.af + "," + var17.ag + "]" + var17.ad.d + "-" + var17.ad.e;
                           var30 = c(var29);
                           if(var30 != null) {
                              var17.ap = var30;
                           } else {
                              var17.ap = com.corrodinggames.rts.game.units.am.a(var17.ad, var17.af, var17.ag);
                              a(var17.ap);
                              if(var17.ap != null) {
                                 a(var29, var17.ap);
                              }
                           }
                        } else if(var28.equalsIgnoreCase("AUTO_ANIMATED")) {
                           var29 = "[autoShadowAnimated:" + var17.af + "," + var17.ag + "]" + var17.ad.d + "-" + var17.ad.e;
                           var30 = c(var29);
                           if(var30 != null) {
                              var17.ap = var30;
                           } else {
                              var17.ap = com.corrodinggames.rts.game.units.am.a(var17.ad, var17.ad.m(), var17.ad.l());
                              a(var17.ap);
                              if(var17.ap != null) {
                                 a(var29, var17.ap);
                              }
                           }

                           var17.aq = true;
                        } else {
                           var17.ap = var17.a(var17.F, var28, var17.ab, var13, "image_shadow");
                        }

                        if(var11.a(var13, "image_shadow_frames", Boolean.valueOf(false)).booleanValue()) {
                           var17.aq = true;
                        }

                        var17.ar = var17.a(var17.ad, var17.ac);
                        var17.s = var11.a(var13, "teamColorsOnTurret", Boolean.valueOf(false)).booleanValue();
                        if(var17.s && var17.ao != null) {
                           var17.at = var17.a(var17.ao, var17.ac);
                        }

                        float var103 = var11.a(var13, "scaleImagesTo", Float.valueOf(-1.0F)).floatValue();
                        if(var103 > 0.0F) {
                           var103 *= var17.aG;
                           var17.bH = var103 / (float)var17.af;
                        }

                        float var104 = var11.a(var13, "imageScale", Float.valueOf(1.0F)).floatValue();
                        if(var104 != 1.0F) {
                           var17.bH *= var104;
                        }

                        float var31 = var11.a(var13, "scaleTurretImagesTo", Float.valueOf(-1.0F)).floatValue();
                        if(var31 > 0.0F) {
                           var31 *= var17.aG;
                           if(var17.ao == null) {
                              throw new RuntimeException("scaleTurretImagesTo needs image_turret set");
                           }

                           var17.bI = var31 / (float)var17.ao.p;
                        }

                        float var32 = var11.a(var13, "turretImageScale", Float.valueOf(1.0F)).floatValue();
                        if(var32 != 1.0F) {
                           var17.bI *= var32;
                        }

                        var17.au = com.corrodinggames.rts.game.units.e.c.e;
                        com.corrodinggames.rts.gameFramework.m.e var33 = var17.a(var11, var13, "image_shield");
                        if(var33 != null) {
                           var17.au = var33;
                           var17.av = true;
                        }

                        var17.aw = var17.a(var11, var13, "icon_build", false);
                        float var34 = (float)var17.ad.m() * var17.bH;
                        float var35 = (float)var17.ad.l() * var17.bH;
                        if(var34 / 2.0F > 90.0F || var35 / 2.0F > 90.0F) {
                           var17.C = new Rect();
                           var17.C.a = (int)(-var34 / 2.0F);
                           var17.C.c = (int)(var34 / 2.0F);
                           var17.C.b = (int)(-var35 / 2.0F);
                           var17.C.d = (int)(var35 / 2.0F);
                           var17.B = true;
                        }

                        Iterator var36 = var11.m("resource_", "global_resource_").iterator();

                        while(true) {
                           String var108;
                           if(var36.hasNext()) {
                              String var107 = (String)var36.next();
                              boolean var110;
                              if(var107.startsWith("resource_")) {
                                 var108 = var107.substring("resource_".length());
                                 var110 = false;
                              } else {
                                 var108 = var107.substring("global_resource_".length());
                                 var110 = true;
                              }

                              var108 = var108.trim();
                              if(var108.contains(" ")) {
                                 throw new RuntimeException("[" + var107 + "] resource codename cannot contain a space");
                              }

                              if(!var108.contains("=") && !var108.contains("|") && !var108.contains(":") && !var108.contains(",") && !var108.contains("(") && !var108.contains(")") && !var108.contains("<") && !var108.contains(">") && !var108.contains("$")) {
                                 com.corrodinggames.rts.game.units.custom.e.d var112 = new com.corrodinggames.rts.game.units.custom.e.d(var110);
                                 var112.a(var17, var11, var107, var108);
                                 if(var17.k(var112.a) != null) {
                                    throw new RuntimeException("[" + var107 + "] resource with name:" + var112.a + " already exists in this file");
                                 }

                                 var17.j.add(var112);
                                 continue;
                              }

                              throw new RuntimeException("[" + var107 + "] resource codename cannot contain the symbols: =|:,()<>$");
                           }

                           var36 = var17.j.iterator();

                           while(var36.hasNext()) {
                              com.corrodinggames.rts.game.units.custom.e.d var37 = (com.corrodinggames.rts.game.units.custom.e.d)var36.next();
                              var37.a(var17);
                           }

                           if(var8.p()) {
                              com.corrodinggames.rts.game.units.custom.b.l.a(var17, var11);
                              com.corrodinggames.rts.game.units.custom.b.j.a(var17, var11);
                           }

                           com.corrodinggames.rts.game.units.custom.b.m.a(var17, var11);
                           var17.ca = var11.b(var12, "autoTriggerCooldownTime", Float.valueOf(60.0F)).floatValue();
                           if(var17.ca < 0.0F) {
                              throw new RuntimeException("autoTriggerCooldownTime cannot be < 0");
                           }

                           if(var17.ca > 120.0F) {
                              throw new RuntimeException("autoTriggerCooldownTime cannot be more than 2 seconds");
                           }

                           if(!var11.a(var12, "autoTriggerCooldownTime_allowDangerousHighCPU", Boolean.valueOf(false)).booleanValue() && var17.ca < 5.0F) {
                              throw new RuntimeException("autoTriggerCooldownTime cannot be this low (without override). Note this cooldown is only applied after triggering an action not for the detection.");
                           }

                           var17.cb = (s)var11.a(var12, "autoTriggerCheckRate", (Enum)s.a, s.class);
                           var17.cd = var11.a(var12, "autoTriggerCheckWhileNotBuilt", Boolean.valueOf(false)).booleanValue();
                           var17.cL.b = (float)var11.g(var12, "mass");
                           var17.ce = var11.a(var12, "availableInDemo", Boolean.valueOf(true)).booleanValue();
                           var17.cf = var11.a(var12, "isLocked", Boolean.valueOf(false)).booleanValue();
                           var17.cg = var11.a(var12, "isLockedIfGameModeNoNuke", Boolean.valueOf(false)).booleanValue();
                           var17.ch = com.corrodinggames.rts.game.units.custom.d.b.a(var17, var11, var12, "price", false);
                           var17.ci = com.corrodinggames.rts.game.units.custom.d.b.a(var17, var11, var12, "reclaimPrice", (com.corrodinggames.rts.game.units.custom.d.b)null);
                           var17.cj = com.corrodinggames.rts.game.units.custom.d.b.b(var17, var11, var12, "streamingCost", (com.corrodinggames.rts.game.units.custom.d.b)null);
                           boolean var105 = var11.a(var12, "switchPriceWithStreamingCost", Boolean.valueOf(false)).booleanValue();
                           if(var105) {
                              if(var17.cj != null) {
                                 throw new RuntimeException("[" + var12 + "]streamingCost and switchPriceWithStreamingCost=true cannot be used at the same time");
                              }

                              var17.cj = com.corrodinggames.rts.game.units.custom.d.b.b(var17, var11, var12, "price", (com.corrodinggames.rts.game.units.custom.d.b)null);
                              var17.ch = com.corrodinggames.rts.game.units.custom.d.b.a;
                           }

                           var17.ck = var11.d(var12, "buildSpeed", Float.valueOf(1.0F)).floatValue();
                           var17.cl = var11.b(var12, "techLevel", Integer.valueOf(1)).intValue();
                           if(var17.cl > 3) {
                              throw new RuntimeException("techLevel cannot be greater than max tech level of:3");
                           }

                           if(var17.cl < 1) {
                              throw new RuntimeException("techLevel cannot be less than 1, it is:" + var17.cl);
                           }

                           var17.cm = var11.a(var12, "experimental", Boolean.valueOf(false)).booleanValue();
                           var17.cv = com.corrodinggames.rts.game.units.custom.d.b.a(var17, var11, var12, "borrowResourcesWhileAlive", true);
                           var17.cw = com.corrodinggames.rts.game.units.custom.d.b.a(var17, var11, var12, "borrowResourcesWhileBuilt", true);
                           var17.co = com.corrodinggames.rts.game.units.custom.d.b.a(var17, var11, var12, "generation_resources", true);
                           int var106 = var11.b(var12, "generation_credits", Integer.valueOf(0)).intValue();
                           if(var106 != 0) {
                              var17.co = com.corrodinggames.rts.game.units.custom.d.b.a(var17.co, com.corrodinggames.rts.game.units.custom.d.b.a(var106));
                           }

                           var17.cr = var11.b(var12, "generation_delay", Integer.valueOf(40)).intValue();
                           if(var17.cr == 0) {
                              var17.cr = 1;
                           }

                           if(var17.cr < 0) {
                              throw new RuntimeException("[" + var12 + "]generation_delay cannot be < 0");
                           }

                           var17.cs = 40.0F / (float)var17.cr;
                           if(!var17.co.c()) {
                              var17.cp = new com.corrodinggames.rts.game.units.custom.e.f();
                              var17.cp.a(var17.co);
                              var17.cp.a((double)var17.cs);
                              var17.cn = true;
                           }

                           if(!var17.cp.c()) {
                              Iterator var38 = var17.cp.b.iterator();

                              while(var38.hasNext()) {
                                 com.corrodinggames.rts.game.units.custom.e.e var39 = (com.corrodinggames.rts.game.units.custom.e.e)var38.next();
                                 if(!var39.a.c() && var39.a.d()) {
                                    if(var17.cq == com.corrodinggames.rts.game.units.custom.e.f.a) {
                                       var17.cq = new com.corrodinggames.rts.game.units.custom.e.f();
                                    }

                                    var17.cq.b(var39.a, var39.b);
                                 }
                              }
                           }

                           var17.cx = var11.a(var17, var12, "generation_active", (LogicBoolean)LogicBoolean.trueBoolean);
                           var17.a(var17.co);
                           var17.cF = var11.a(var12, "resourceRate", Float.valueOf(0.0F)).floatValue();
                           if(var105 && var17.cF != 0.0F) {
                              throw new RuntimeException("To avoid mistakes [" + var12 + "]resourceRate cannot be used with switchPriceWithStreamingCost=true");
                           }

                           var108 = var11.b(var12, "updateUnitMemory", (String)null);
                           if(var108 != null) {
                              var17.ct = VariableScope.createMemoryWriter(var108, var17, var12, "updateUnitMemory");
                           }

                           var17.cu = var11.b(var12, "updateUnitMemoryRate", Float.valueOf(60.0F)).floatValue();
                           var17.cG = var11.b(var12, "resourceMaxConcurrentReclaimingThis", Integer.valueOf(Integer.MAX_VALUE)).intValue();
                           var17.cH = var11.a(var17, var12, "similarResourcesHaveTag", (h)null);
                           var17.do = bl.a(var17, var11.b(var12, "soundOnAttackOrder", (String)null));
                           var17.dp = bl.a(var17, var11.b(var12, "soundOnMoveOrder", (String)null));
                           var17.dq = bl.a(var17, var11.b(var12, "soundOnNewSelection", (String)null));
                           String var109 = var11.b(var13, "drawLayer", (String)null);
                           if(var109 != null) {
                              if(var109.equals("experimentals")) {
                                 var17.cI = 4;
                              } else if(var109.equals("underwater")) {
                                 var17.cI = 1;
                              } else if(var109.equals("bottom")) {
                                 var17.cI = 1;
                              } else if(var109.equals("ground")) {
                                 var17.cI = 2;
                              } else if(var109.equals("ground2")) {
                                 var17.cI = 3;
                              } else if(var109.equals("air")) {
                                 var17.cI = 5;
                              } else if(var109.equals("top")) {
                                 var17.cI = 10;
                              } else {
                                 if(!var109.equals("wreaks")) {
                                    throw new RuntimeException("unknown drawLayer:" + var109);
                                 }

                                 var17.cI = 0;
                              }
                           }

                           var17.cJ = var11.a(var13, "shadowOffsetX", Float.valueOf(0.0F)).floatValue();
                           var17.cK = var11.a(var13, "shadowOffsetY", Float.valueOf(0.0F)).floatValue();
                           var17.dB = var11.a(var13, "rotate_with_direction", Boolean.valueOf(true)).booleanValue();
                           var17.dC = var11.a(var13, "lock_body_rotation_with_main_turret", Boolean.valueOf(false)).booleanValue();
                           var17.dD = var11.a(var13, "lock_shadow_rotation_with_main_turret", Boolean.valueOf(var17.dC)).booleanValue();
                           var17.dE = var11.a(var13, "lock_leg_rotation_with_main_turret", Boolean.valueOf(false)).booleanValue();
                           var17.dH = var11.a(var13, "whenBeingBuiltMakeTransparentTill", Float.valueOf(1.0F)).floatValue();
                           var17.dI = m.a(var17, var11, var13, "animation_", false);
                           Iterator var40 = var11.e("effect_").iterator();

                           String var41;
                           String var42;
                           while(var40.hasNext()) {
                              var41 = (String)var40.next();
                              var42 = var41.substring("effect_".length());
                              ay var43 = new ay(var42);
                              var43.a(var17, var11, var41);
                              var17.gd.add(var43);
                           }

                           var40 = var17.gd.iterator();

                           while(var40.hasNext()) {
                              ay var113 = (ay)var40.next();
                              if(var113.alsoEmitEffects != null) {
                                 var113.alsoEmitEffects.c();
                              }

                              if(var113.alsoEmitEffectsOnDeath != null) {
                                 var113.alsoEmitEffectsOnDeath.c();
                              }

                              if(var113.ifSpawnFailsEmitEffects != null) {
                                 var113.ifSpawnFailsEmitEffects.c();
                              }

                              if(var113.trailEffect != null) {
                                 var113.trailEffect.c();
                              }
                           }

                           var17.bJ = var11.a(var13, "splastEffect", Boolean.valueOf(false)).booleanValue();
                           var17.bM = var11.a(var13, "dustEffect", Boolean.valueOf(false)).booleanValue();
                           var17.bK = var11.a(var13, "splastEffectReverse", Boolean.valueOf(true)).booleanValue();
                           var17.bN = var11.a(var13, "dustEffectReverse", Boolean.valueOf(true)).booleanValue();
                           var17.bL = var17.bM || var17.bJ;
                           String var111 = var11.b(var13, "movementEffect", (String)null);
                           if(var111 != null) {
                              var17.bO = var17.a(var111, (z)null);
                              if(var17.bO != null && var17.bO.a()) {
                                 var17.bL = true;
                              }
                           }

                           var41 = var11.b(var13, "movementEffectReverse", (String)null);
                           if(var41 != null) {
                              var17.bP = var17.a(var41, (z)null);
                              if(var17.bP != null && var17.bP.a()) {
                                 var17.bL = true;
                              }
                           }

                           var17.bR = var11.a(var13, "movementEffectRate", Float.valueOf(11.0F)).floatValue();
                           var17.bQ = var11.a(var13, "movementEffectReverseFlipEffects", Boolean.valueOf(false)).booleanValue();
                           var17.bT = var11.a(var13, "repairEffectRate", Float.valueOf(5.0F)).floatValue();
                           var42 = var11.b(var13, "repairEffect", (String)null);
                           if(var42 != null) {
                              var17.bU = var17.a(var42, (z)null);
                              if(var17.bU != null && var17.bU.b()) {
                                 var17.bS = true;
                              }
                           }

                           String var114 = var11.b(var13, "repairEffectAtTarget", (String)null);
                           if(var114 != null) {
                              var17.bV = var17.a(var114, (z)null);
                              if(var17.bV != null && var17.bV.b()) {
                                 var17.bS = true;
                              }
                           }

                           var17.bX = var11.a(var13, "reclaimEffectRate", Float.valueOf(5.0F)).floatValue();
                           String var44 = var11.b(var13, "reclaimEffect", (String)null);
                           if(var44 != null) {
                              var17.bY = var17.a(var44, (z)null);
                              if(var17.bY != null && var17.bY.b()) {
                                 var17.bW = true;
                              }
                           }

                           String var45 = var11.b(var13, "reclaimEffectAtTarget", (String)null);
                           if(var45 != null) {
                              var17.bZ = var17.a(var45, (z)null);
                              if(var17.bZ != null && var17.bZ.b()) {
                                 var17.bW = true;
                              }
                           }

                           var17.ds.a(var17, var11, var13, "animation_" + var17.ds.a + "_");
                           var17.dt.a(var17, var11, var13, "animation_" + var17.dt.a + "_");
                           var17.du.a(var17, var11, var13, "animation_" + var17.du.a + "_");
                           Iterator var46 = var11.e("animation_").iterator();

                           while(var46.hasNext()) {
                              String var47 = (String)var46.next();
                              String var48 = var47.substring("animation_".length());
                              f var49 = new f(var48);
                              var49.a(var17, var11, var47, "");
                              var17.dr.add(var49);
                           }

                           var17.ds = var17.a(n.a, var17.ds, true);
                           var17.dt = var17.a(n.c, var17.dt, true);
                           var17.du = var17.a(n.b, var17.du, true);
                           var17.dw = var17.a(n.e);
                           var17.dx = var17.a(n.f);
                           if(var17.dw != null && var17.dx != null) {
                              throw new RuntimeException("Cannot use underConstruction and underConstructionWithLinkedBuiltTime animations at the same time");
                           }

                           var17.dv = var17.a(n.d);
                           var17.dy = var17.a(n.g);
                           if(var17.dy != null) {
                              var17.bg = true;
                           }

                           var17.dz = var17.a(n.h);
                           var17.dA = var17.a(n.i);
                           var17.cL.c = var11.g(var12, "maxHp");
                           var17.cL.g = var11.b(var12, "maxShield", Integer.valueOf(0)).intValue();
                           var17.cM = var11.a(var12, "startShieldAtZero", Boolean.valueOf(false)).booleanValue();
                           var17.cL.h = var11.a(var12, "shieldRegen", Float.valueOf(0.25F)).floatValue();
                           var17.cU = var11.a(var12, "shieldDisplayOnlyDeflection", Boolean.valueOf(false)).booleanValue();
                           var17.cV = var11.a(var12, "shieldDeflectionDisplayRate", Float.valueOf(4.0F)).floatValue();
                           var17.cL.l = var11.a(var12, "armour", Float.valueOf(0.0F)).floatValue();
                           var17.cN = var11.a(var12, "armourMinDamageToKeep", Float.valueOf(1.0F)).floatValue();
                           var17.cL.d = var11.a(var12, "energyMax", Float.valueOf(0.0F)).floatValue();
                           var17.cO = var11.a(var12, "startEnergyAtZero", Boolean.valueOf(false)).booleanValue();
                           var17.cP = var11.a(var12, "energyRegen", Float.valueOf(0.0F)).floatValue();
                           var17.cS = var11.a(var12, "energyStartingPercentage", Float.valueOf(1.0F)).floatValue();
                           var17.cR = var11.a(var12, "energyNeedsToRechargeToFull", Boolean.valueOf(false)).booleanValue();
                           var17.cQ = var11.a(var12, "energyRegenWhenRecharging", Float.valueOf(var17.cP)).floatValue();
                           var17.cT = a(var11, var12, "energyDisplayName", (String)null);
                           var17.cW = var11.g(var12, "radius");
                           var17.dd = var11.b(var12, "displayRadius", Integer.valueOf(var17.cW)).intValue();
                           float var115 = (float)var17.cW;
                           if(var115 < 6.0F) {
                              var115 = 6.0F;
                           }

                           var17.de = var11.a(var12, "uiTargetRadius", Float.valueOf(var115)).floatValue();
                           var17.df = var11.b(var12, "shieldRenderRadius", Integer.valueOf(var17.cW)).intValue();
                           var17.dg = var11.b(var12, "buildingSelectionOffset", Integer.valueOf(0)).intValue();
                           var17.cX = var11.a(var12, "footprint", var17.cX);
                           var17.cY = var11.a(var12, "constructionFootprint", var17.cY);
                           var17.cZ.a(var17.cX);
                           var17.cZ = var11.a(var12, "displayFootprint", var17.cZ);
                           var17.da = var11.a(var12, "buildingToFootprintOffsetX", Float.valueOf(10.0F)).floatValue();
                           var17.db = var11.a(var12, "buildingToFootprintOffsetY", Float.valueOf(10.0F)).floatValue();
                           var17.cW = (int)((float)var17.cW * var17.aG);
                           var17.dd = (int)((float)var17.dd * var17.aG);
                           var17.cL.n = var11.b(var12, "fogOfWarSightRange", Integer.valueOf(15)).intValue();
                           var17.dh = var11.b(var12, "fogOfWarSightRangeWhileNotBuilt", Integer.valueOf(-1)).intValue();
                           var17.di = var11.a(var12, "exit_x", Float.valueOf(0.0F)).floatValue();
                           var17.dj = var11.a(var12, "exit_y", Float.valueOf(9.0F)).floatValue();
                           var17.dk = var11.a(var12, "exit_dirOffset", (Float)null);
                           var17.dl = var11.a(var12, "exit_heightOffset", Float.valueOf(0.0F)).floatValue();
                           var17.dm = var11.a(var12, "exitHeightIgnoreParent", Boolean.valueOf(false)).booleanValue();
                           var17.dn = var11.a(var12, "exit_moveAwayAmount", Float.valueOf(70.0F));
                           var17.eB = var11.b(var12, "softCollisionOnAll", Integer.valueOf(0)).intValue();
                           var17.eC = var11.a(var12, "disableAllUnitCollisions", Boolean.valueOf(false)).booleanValue();
                           if(var17.eC) {
                              var17.cX.a(0, 0, -1, -1);
                           }

                           var17.eJ = var11.a(var12, "hideScorchMark", Boolean.valueOf(false)).booleanValue();
                           var17.eK = var11.a(var13, "disableLowHpFire", Boolean.valueOf(var17.eD)).booleanValue();
                           var17.eL = var11.a(var13, "disableLowHpSmoke", Boolean.valueOf(var17.eD)).booleanValue();
                           var17.aH = var11.a(var12, "isBuilding", Boolean.valueOf(false)).booleanValue();
                           var17.aI = var11.a(var12, "ignoreInUnitCapCalculation", Boolean.valueOf(var17.aH)).booleanValue();
                           var17.aJ = var11.a(var12, "placeOnlyOnResPool", Boolean.valueOf(false)).booleanValue();
                           var17.aK = var11.a(var12, "isUnrepairableUnit", Boolean.valueOf(false)).booleanValue();
                           var17.aL = var11.a(var12, "extraBuildRangeWhenBuildingThis", Float.valueOf(0.0F)).floatValue();
                           var17.aM = var11.a(var12, "isUnselectable", Boolean.valueOf(false)).booleanValue();
                           var17.aN = var11.a(var12, "isUnselectableAsTarget", Boolean.valueOf(var17.aM)).booleanValue();
                           var17.fO = var11.a(var17, var12, "showActionsWithMixedSelectionIfOtherUnitsHaveTag", (h)null);
                           var17.aO = var11.a(var12, "canNotBeDirectlyAttacked", Boolean.valueOf(false)).booleanValue();
                           var17.aP = var11.a(var12, "canNotBeDamaged", Boolean.valueOf(var17.aO)).booleanValue();
                           var17.aQ = var11.a(var12, "showOnMinimap", Boolean.valueOf(true)).booleanValue();
                           var17.aR = var11.a(var12, "showOnMinimapToEnemies", Boolean.valueOf(var17.cL.m)).booleanValue();
                           var17.aS = var11.a(var17, var12, "canOnlyBeAttackedByUnitsWithTags", (h)null);
                           if(var17.aO && var17.aS != null) {
                              throw new RuntimeException("canNotBeDirectlyAttacked and canOnlyBeAttackedByUnitsWithTags cannot be used at the same time");
                           }

                           var17.aT = var11.a(var12, "canNotBeGivenOrdersByPlayer", Boolean.valueOf(false)).booleanValue();
                           var17.aU = var11.a(var12, "canRepairBuildings", Boolean.valueOf(false)).booleanValue();
                           var17.aV = var11.a(var12, "canRepairUnits", Boolean.valueOf(false)).booleanValue();
                           var17.aW = var11.a(var12, "autoRepair", Boolean.valueOf(false)).booleanValue();
                           if(var17.aW) {
                              var17.a(com.corrodinggames.rts.game.units.custom.b.b.a);
                           }

                           var17.cL.o = var11.b(var12, "nanoRange", Integer.valueOf(-1)).intValue();
                           if(var17.cL.o != -1) {
                              var17.cL.o = (int)((float)var17.cL.o * var17.aG);
                           }

                           var17.aY = var11.a(var12, "nanoRangeForRepairIsMelee", Boolean.valueOf(false)).booleanValue();
                           if(var17.aY) {
                              var17.aX = 5;
                           }

                           int var116 = var11.b(var12, "nanoRangeForRepair", Integer.valueOf(-1)).intValue();
                           if(var116 != -1) {
                              var17.aX = var116;
                              var17.aX = (int)((float)var17.aX * var17.aG);
                           }

                           var17.ba = var11.a(var12, "nanoRangeForReclaimIsMelee", Boolean.valueOf(false)).booleanValue();
                           if(var17.ba) {
                              var17.aZ = 5;
                           }

                           int var117 = var11.b(var12, "nanoRangeForReclaim", Integer.valueOf(-1)).intValue();
                           if(var117 != -1) {
                              var17.aZ = var117;
                              var17.aZ = (int)((float)var17.aZ * var17.aG);
                           }

                           var17.bb = var11.a(var12, "nanoRepairSpeed", Float.valueOf(0.2F)).floatValue();
                           float var118 = 5.1F;
                           var17.bc = var11.a(var12, "nanoReclaimSpeed", Float.valueOf(var17.bb * 5.1F)).floatValue();
                           var17.bd = var11.a(var12, "resourceReclaimMultiplier", Float.valueOf(1.0F)).floatValue();
                           var17.be = var11.a(var12, "nanoUnbuildSpeed", Float.valueOf(1.0F)).floatValue() * 0.001F * 5.1F;
                           var17.bf = var11.a(var12, "nanoBuildSpeed", Float.valueOf(1.0F)).floatValue();
                           var17.cL.r = var11.a(var12, "nanoFactorySpeed", Float.valueOf(1.0F)).floatValue();
                           var17.cL.p = var11.a(var12, "selfRegenRate", Float.valueOf(0.0F)).floatValue();
                           var17.bh = var11.d(var12, "selfBuildRate", Float.valueOf(0.0F)).floatValue();
                           var17.bi = var11.a(var12, "dieOnConstruct", Boolean.valueOf(false)).booleanValue();
                           var17.bk = var11.a(var12, "dieOnZeroEnergy", Boolean.valueOf(false)).booleanValue();
                           byte var50 = 4;
                           if(var17.cL.b > 30000.0F) {
                              var50 = 8;
                           }

                           if(var17.aH) {
                              var50 = 7;
                           }

                           var17.bq = var11.b(var12, "numBitsOnDeath", Integer.valueOf(var50)).intValue();
                           var17.bn = var11.a(var12, "nukeOnDeath", Boolean.valueOf(false)).booleanValue();
                           var17.bo = var11.a(var12, "nukeOnDeathRange", Float.valueOf(250.0F)).floatValue();
                           var17.bp = var11.a(var12, "nukeOnDeathDamage", Float.valueOf(5400.0F)).floatValue();
                           var17.br = var11.a(var12, "nukeOnDeathDisableWhenNoNuke", Boolean.valueOf(false)).booleanValue();
                           var17.bm = var11.b(var12, "fireOnDeath", Integer.valueOf(0)).intValue();
                           var17.bt = (com.corrodinggames.rts.game.units.ab)var11.a(var12, "explodeTypeOnDeath", (Enum)null, com.corrodinggames.rts.game.units.ab.class);
                           var17.bu = var11.a(var12, "explodeOnDeath", Boolean.valueOf(true)).booleanValue();
                           var17.bs = var11.a(var12, "disableDeathOnZeroHp", Boolean.valueOf(false)).booleanValue();
                           boolean var51 = var11.a(var12, "explodeOnDeathGroundCollosion", Boolean.valueOf(true)).booleanValue();
                           var51 = var11.a(var12, "explodeOnDeathGroundCollision", Boolean.valueOf(var51)).booleanValue();
                           var17.bv = var51;
                           var17.by = var17.a(var11.b(var12, "effectOnDeath", (String)null), (z)null);
                           var17.bx = var17.a(var11.b(var12, "effectOnDeathIfUnbuilt", (String)null), (z)null);
                           var17.bz = bl.a(var17, var11.b(var12, "soundOnDeath", (String)null));
                           String var52 = var11.b(var12, "effectOnDeathGroundCollosion", (String)null);
                           var52 = var11.b(var12, "effectOnDeathGroundCollision", var52);
                           var17.bw = var17.a(var52, (z)null);
                           var17.bC = bp.a(var17, var11, var12, "unitsSpawnedOnDeath");
                           var17.bD = var11.a(var12, "unitsSpawnedOnDeath_setToTeamOfLastAttacker", Boolean.valueOf(false)).booleanValue();
                           var17.fk = var11.a(var12, "canReclaimResources", Boolean.valueOf(false)).booleanValue();
                           var17.fl = var11.a(var17, var12, "canReclaimResourcesOnlyWithTags", (h)null);
                           var17.fm = var11.b(var12, "canReclaimResourcesNextSearchRange", Integer.valueOf(500)).intValue();
                           var17.fn = var11.a(var17, var12, "canReclaimUnitsOnlyWithTags", (h)null);
                           var17.fo = var11.a(var17, var12, "canRepairUnitsOnlyWithTags", (h)null);
                           if(var17.fn != null && !var17.aV && !var17.aU) {
                              throw new RuntimeException("canReclaimUnitsOnlyWithTags requires canRepairUnits:true or canRepairBuildings:true");
                           }

                           if(var17.fo != null && !var17.aV && !var17.aU) {
                              throw new RuntimeException("canRepairUnitsOnlyWithTags requires canRepairUnits:true or canRepairBuildings:true");
                           }

                           var17.eM = var11.b(var12, "maxTransportingUnits", Integer.valueOf(0)).intValue();
                           if(var17.eM < 0) {
                              throw new RuntimeException("maxTransportingUnits cannot be < 0");
                           }

                           var17.eN = var11.b(var12, "transportUnitsUnloadDelayBetweenEachUnit", Float.valueOf(30.0F)).floatValue();
                           var17.eP = g.a(var11.b(var12, "transportUnitsRequireTag", (String)null));
                           String var53 = var11.b(var12, "transportUnitsRequireMovementType", (String)null);
                           String var57;
                           if(var53 != null) {
                              String[] var54 = var53.split(",");
                              int var55 = var54.length;

                              for(int var56 = 0; var56 < var55; ++var56) {
                                 var57 = var54[var56];
                                 var57 = var57.trim();
                                 var17.eQ.add(com.corrodinggames.rts.game.units.ao.a(var57, "transportUnitsRequireMovementType"));
                              }
                           }

                           var17.eO = var11.a(var12, "transportUnitsEachUnitAlwaysUsesSingleSlot", Boolean.valueOf(false)).booleanValue();
                           var17.eR = var11.a(var12, "transportUnitsBlockAirAndWaterUnits", Boolean.valueOf(var17.eQ.size() == 0)).booleanValue();
                           var17.eS = var11.a(var12, "transportUnitsBlockOtherTransports", Boolean.valueOf(true)).booleanValue();
                           var17.eU = var11.a(var17, var12, "transportUnitsKeepBuiltUnits", (LogicBoolean)LogicBoolean.falseBoolean);
                           var17.eV = var11.a(var17, var12, "transportUnitsKillOnDeath", (LogicBoolean)LogicBoolean.trueBoolean);
                           var17.eW = var11.a(var17, var12, "transportUnitsKeepWaypoints", (LogicBoolean)LogicBoolean.falseBoolean);
                           var17.eY = var11.a(var12, "transportUnitsHealBy", Float.valueOf(0.0F)).floatValue();
                           var17.fc = var11.a(var17, var12, "transportUnitsCanUnloadUnits", (LogicBoolean)null);
                           if(var17.fc != null) {
                              var17.fd = var17.fc;
                           } else {
                              var17.fc = l.fa;
                              var17.fd = l.fb;
                           }

                           var17.eT = var11.a(var12, "transportUnitsAddUnloadOption", Boolean.valueOf(var17.fc != LogicBoolean.falseBoolean)).booleanValue();
                           var17.eX = var11.a(var12, "transportUnitsOnTeamChangeKeepCurrentTeam", Boolean.valueOf(var17.eX)).booleanValue();
                           var17.eZ = var11.b(var12, "transportSlotsNeeded", Integer.valueOf(1)).intValue();

                           int var119;
                           String var120;
                           String var123;
                           for(var119 = -1; var119 <= 29; ++var119) {
                              var120 = "builtFrom_" + var119 + "_";
                              if(var119 == -1) {
                                 var120 = "builtFrom_";
                              }

                              var123 = var120 + "name";
                              var57 = var11.b(var12, var123, (String)null);
                              if(var57 != null) {
                                 String[] var58 = var57.split(",");
                                 String[] var59 = var58;
                                 int var60 = var58.length;

                                 for(int var61 = 0; var61 < var60; ++var61) {
                                    String var62 = var59[var61];
                                    var62 = var62.trim();
                                    if(!var62.equals("")) {
                                       p var63 = new p();
                                       var63.a = var62;
                                       var63.b = var11.a(var12, var120 + "pos", Float.valueOf(999.0F)).floatValue();
                                       var63.c = var11.a(var12, var120 + "forceNano", Boolean.valueOf(false)).booleanValue();
                                       var63.d = "[" + var12 + "]" + var123;
                                       var63.f = var11.a(var17, var12, var120 + "isLocked", (LogicBoolean)null);
                                       var63.g = a(var11, var12, var120 + "isLockedMessage", (String)null);
                                       if(var63.f == LogicBoolean.falseBoolean) {
                                          var63.f = null;
                                       }

                                       if(!"NONE".equalsIgnoreCase(var62)) {
                                          var17.gg.add(var63);
                                       }
                                    }
                                 }
                              }
                           }

                           for(var119 = 0; var119 <= 50; ++var119) {
                              var120 = var11.b(var12, "canBuild_" + var119 + "_name", (String)null);
                              if(var120 != null) {
                                 var123 = "canBuild_" + var119 + "_";
                                 b(var17, var11, var12, var123, false);
                              }
                           }

                           Iterator var121 = var11.e("canBuild_").iterator();

                           while(var121.hasNext()) {
                              var120 = (String)var121.next();
                              b(var17, var11, var120, "", true);
                           }

                           var17.ff = be.a(var17, var11);
                           String var122 = var11.e(var15, "movementType");
                           var17.fg = com.corrodinggames.rts.game.units.ao.a(var122, "movementType");
                           if(!var17.aH) {
                              var17.fh = var17.fg;
                           } else {
                              var17.fh = com.corrodinggames.rts.game.units.ao.a;
                           }

                           Boolean var124 = var11.a(var16, "useAsBuilder", (Boolean)null);
                           var17.fs = var11.a(var16, "useAsAttacker", Boolean.valueOf(true)).booleanValue();
                           Boolean var125 = var11.a(var12, "isBuilder", (Boolean)null);
                           if(var125 == null) {
                              if(var124 == null) {
                                 var125 = Boolean.valueOf(false);
                              } else {
                                 var125 = var124;
                              }
                           } else if(var124 == null) {
                              var124 = var125;
                           }

                           if(var124 == null) {
                              var124 = Boolean.valueOf(false);
                           }

                           var17.fp = var125.booleanValue();
                           var17.fq = var124.booleanValue();
                           if(!var17.fp && var17.fq) {
                              throw new RuntimeException("Cannot tell AI to use a non-builder as builder [ai]useAsBuilder:" + var17.fq + " [core]isBuilder:" + var17.fp);
                           }

                           if(var17.fk) {
                              var17.fr = true;
                           }

                           Boolean var126 = var11.a(var16, "useAsHarvester", (Boolean)null);
                           if(var126 != null) {
                              var17.fr = var126.booleanValue();
                           }

                           Boolean var127 = var11.a(var16, "useAsTransport", (Boolean)null);
                           if(var127 == null) {
                              var127 = Boolean.valueOf(var17.eM > 0 && !var17.fq && !var17.aH);
                              if(!var17.eT) {
                                 var127 = Boolean.valueOf(false);
                              }
                           }

                           var17.ft = var127.booleanValue();
                           if(var17.aH) {
                              var17.as = com.corrodinggames.rts.game.units.d.d.q;
                           } else if(var17.fg == com.corrodinggames.rts.game.units.ao.d) {
                              var17.as = com.corrodinggames.rts.game.units.b.b.n;
                           } else if(var17.fg == com.corrodinggames.rts.game.units.ao.e) {
                              var17.as = com.corrodinggames.rts.game.units.h.f.q;
                           } else if(var17.fg == com.corrodinggames.rts.game.units.ao.f) {
                              if(var17.cm) {
                                 var17.as = com.corrodinggames.rts.game.units.e.j.dO;
                              } else if(var17.l()) {
                                 var17.as = com.corrodinggames.rts.game.units.e.b.h;
                              } else {
                                 var17.as = com.corrodinggames.rts.game.units.e.h.n;
                              }
                           } else if(var17.cm) {
                              var17.as = com.corrodinggames.rts.game.units.e.j.dO;
                           } else if(var17.l()) {
                              var17.as = com.corrodinggames.rts.game.units.e.b.h;
                           } else {
                              var17.as = com.corrodinggames.rts.game.units.e.j.dN;
                           }

                           com.corrodinggames.rts.gameFramework.m.e var128 = var17.a(var11, var13, "icon_zoomed_out", false);
                           if(var128 != null) {
                              var17.as = var17.a(var128, var17.ac);
                           }

                           if(var11.a(var13, "icon_zoomed_out_neverShow", Boolean.valueOf(false)).booleanValue()) {
                              var17.as = null;
                           }

                           var17.t = var11.a(var13, "showHealthBar", Boolean.valueOf(true)).booleanValue();
                           var17.u = var11.a(var13, "showHealthBarChanges", Boolean.valueOf(true)).booleanValue();
                           var17.v = var11.a(var13, "showEnergyBar", Boolean.valueOf(true)).booleanValue();
                           var17.w = var11.a(var13, "showShotDelayBar", Boolean.valueOf(true)).booleanValue();
                           var17.x = var11.a(var13, "showTransportBar", Boolean.valueOf(true)).booleanValue();
                           var17.y = var11.a(var13, "showShieldBar", Boolean.valueOf(true)).booleanValue();
                           var17.z = var11.a(var13, "showQueueBar", Boolean.valueOf(true)).booleanValue();
                           var17.A = var11.a(var13, "showSelectionIndicator", Boolean.valueOf(true)).booleanValue();
                           var17.fi = var11.a(var15, "slowDeathFall", Boolean.valueOf(false)).booleanValue();
                           var17.fj = var11.a(var15, "slowDeathFallSmoke", Boolean.valueOf(true)).booleanValue();
                           var17.cL.j = var11.a(var15, "moveSpeed", Float.valueOf(1.0F)).floatValue() * var17.aG;
                           var17.dN = var11.a(var15, "moveAccelerationSpeed", Float.valueOf(1.0F)).floatValue() * var17.aG;
                           var17.dO = var11.a(var15, "moveDecelerationSpeed", Float.valueOf(1.0F)).floatValue() * var17.aG;
                           Boolean var129 = var11.a(var15, "ignoreMoveOrders", (Boolean)null);
                           if(var17.aH) {
                              var17.dP = true;
                           }

                           if(var129 != null) {
                              if(var129.booleanValue()) {
                                 var17.dP = true;
                                 if(var17.cL.j > 0.0F) {
                                    throw new RuntimeException("[movement]ignoreMoveOrders expects moveSpeed=0");
                                 }
                              } else if(var17.aH) {
                                 throw new RuntimeException("[movement]ignoreMoveOrders=false not yet supported on buildings");
                              }
                           }

                           var17.ej = var11.a(var15, "moveYAxisScaling", Float.valueOf(1.0F)).floatValue();
                           if(var17.ej <= 0.0F) {
                              throw new RuntimeException("[movement]moveYAxisScaling must be > 0");
                           }

                           var17.ek = 1.0F / var17.ej;
                           var17.el = var11.a(var15, "reverseSpeedPercentage", Float.valueOf(0.6F)).floatValue();
                           String var130 = var11.b(var15, "landOnGround", "false");
                           if(var130.equalsIgnoreCase("false")) {
                              var17.dQ = false;
                           } else if(var130.equalsIgnoreCase("onlyIdle")) {
                              var17.dQ = true;
                              var17.dR = true;
                           } else {
                              if(!var130.equalsIgnoreCase("true")) {
                                 throw new RuntimeException("landOnGround expected:true, false, onlyIdle, not:" + var130);
                              }

                              var17.dQ = true;
                           }

                           float var131 = 0.0F;
                           float var132 = 0.0F;
                           if(var17.fg == com.corrodinggames.rts.game.units.ao.d) {
                              var131 = 35.0F;
                              var132 = 1.5F;
                           }

                           var17.dS = var11.a(var15, "startingHeightOffset", Float.valueOf(0.0F)).floatValue();
                           var17.cL.q = var11.a(var15, "targetHeight", Float.valueOf(var131)).floatValue();
                           var17.dT = var11.a(var15, "targetHeightDrift", Float.valueOf(var132)).floatValue();
                           if(var17.cL.q > 80.0F) {
                              var17.B = true;
                           }

                           var17.dU = var11.a(var15, "heightChangeRate", Float.valueOf(var17.dU)).floatValue();
                           var17.dV = var11.a(var15, "fallingAcceleration", Float.valueOf(var17.dV)).floatValue();
                           var17.dW = var11.a(var15, "fallingAccelerationDead", Float.valueOf(var17.dW)).floatValue();
                           var17.cL.k = var11.a(var15, "maxTurnSpeed", Float.valueOf(1.0F)).floatValue();
                           var17.eo = var11.a(var15, "turnAcceleration", Float.valueOf(1.0F)).floatValue();
                           var17.dX = var11.a(var15, "moveSlidingMode", Boolean.valueOf(false)).booleanValue();
                           var17.dY = var11.a(var15, "moveIgnoringBody", Boolean.valueOf(false)).booleanValue();
                           var17.dZ = var11.b(var15, "moveSlidingDir", Integer.valueOf(-1)).intValue();
                           var17.ei = var11.a(var15, "joinsGroupFormations", Boolean.valueOf(true)).booleanValue();
                           var17.ea = var11.a(var14, "turretSize", Float.valueOf(1.0F)).floatValue() * var17.aG;
                           var17.eb = var11.a(var14, "turretTurnSpeed", Float.valueOf(8.0F)).floatValue();
                           var17.dL = var11.a(var14, "turretRotateWithBody", Boolean.valueOf(true)).booleanValue();
                           String var64 = var11.b(var14, "attackMovement", "normal");
                           var17.ec = com.corrodinggames.rts.game.units.b.a;
                           if(var64.equalsIgnoreCase("normal")) {
                              var17.ec = com.corrodinggames.rts.game.units.b.a;
                           }

                           if(var64.equalsIgnoreCase("strafing")) {
                              var17.ec = com.corrodinggames.rts.game.units.b.b;
                           }

                           if(var64.equalsIgnoreCase("bomber")) {
                              var17.ec = com.corrodinggames.rts.game.units.b.d;
                           }

                           var17.ef = var11.a(var14, "disablePassiveTargeting", Boolean.valueOf(false)).booleanValue();
                           var17.eg = var11.a(var14, "stopTargetingAfterFiring", Boolean.valueOf(false)).booleanValue();
                           var17.eh = var11.a(var14, "turretMultiTargeting", Boolean.valueOf(false)).booleanValue();
                           var17.ed = var11.a(var14, "attackMovementSpeed", Float.valueOf(1.0F)).floatValue();
                           var17.ee = var11.a(var14, "attackMovementSpread", Float.valueOf(1.0F)).floatValue();
                           Float var65 = var11.a(var14, "maxAttackRange", (Float)null);
                           boolean var66;
                           if(var65 != null) {
                              var66 = true;
                              var17.cL.i = var65.floatValue() * var17.aG;
                           } else {
                              var66 = false;
                              var17.cL.i = 100.0F * var17.aG;
                           }

                           var17.ez = var11.a(var14, "aimOffsetSpread", Float.valueOf(0.6F)).floatValue();
                           var17.dM = var11.b(var14, "shootDelay", Float.valueOf(50.0F)).floatValue();
                           var17.cL.e = var11.a(var14, "shootDelayMultiplier", Float.valueOf(1.0F)).floatValue();
                           var17.cL.f = var11.a(var14, "shootDamageMultiplier", Float.valueOf(1.0F)).floatValue();
                           var17.dK = var11.a(var14, "showRangeUIGuide", (Boolean)null);
                           var17.eF = var11.a(var14, "isMelee", Boolean.valueOf(false)).booleanValue();
                           var17.eG = 0.0F;
                           Float var67 = var11.a(var14, "meleeEngangementDistance", (Float)null);
                           if(var17.eF) {
                              var17.eG = 250.0F;
                              if(var67 != null) {
                                 var17.eG = var67.floatValue();
                              }
                           } else if(var67 != null) {
                              throw new RuntimeException("[attack]meleeEngangementDistance can only be used with isMelee:true");
                           }

                           a(var94, ah.k);
                           Iterator var68 = var11.e("projectile_").iterator();

                           while(var68.hasNext()) {
                              String var69 = (String)var68.next();
                              String var70 = var69.substring("projectile_".length());
                              if(var17.f(var70) != null) {
                                 throw new RuntimeException("Two projectiles found with the same name:" + var70);
                              }

                              bh var71 = new bh();
                              var71.bh = var70;
                              var71.bj = var17;
                              bh.a(var71, var17, var11, var69);
                           }

                           int var133 = var17.fT.size();
                           if(var133 < 1) {
                              var133 = 1;
                           }

                           var17.fR = new bh[var133];

                           int var134;
                           bh var135;
                           for(var134 = 0; var134 < var17.fT.size(); ++var134) {
                              var135 = (bh)var17.fT.get(var134);
                              var135.bi = var134;
                              var17.fR[var134] = var135;
                           }

                           for(var134 = 0; var134 < var17.fR.length; ++var134) {
                              var135 = var17.fR[var134];
                              if(var135 != null) {
                                 var135.w *= var17.aG;
                                 var135.au *= var17.aG;
                                 var135.aF *= var17.aG;
                              }
                           }

                           if(var17.fR[0] == null) {
                              bh var136 = new bh();
                              var136.bi = 0;
                              var136.bh = "1";
                              var136.b = 10;
                              var17.fT.add(var136);
                              var17.fR[0] = var136;
                           }

                           ArrayList var137 = var17.fS;
                           Iterator var138 = var11.e("turret_").iterator();

                           while(var138.hasNext()) {
                              String var139 = (String)var138.next();
                              String var72 = var139.substring("turret_".length());
                              if(var17.e(var72) != null) {
                                 throw new RuntimeException("Two turrets found with the same name:" + var72);
                              }

                              bn var73 = new bn();
                              var73.a = var72;
                              var73.b = var139;
                              var137.add(var73);
                           }

                           var138 = var137.iterator();

                           bn var140;
                           while(var138.hasNext()) {
                              var140 = (bn)var138.next();
                              bn.a(var140, var17, var11, var140.b);
                           }

                           if(var137.size() == 0) {
                              bn var141 = new bn();
                              var141.f = 0.0F;
                              var141.g = 0.0F;
                              var141.a = "1";
                              var141.m = var17.dM;
                              var137.add(var141);
                           }

                           int var142;
                           for(var142 = var137.size() - 1; var142 >= 0; --var142) {
                              if(var137.get(var142) != null) {
                                 ((bn)var137.get(var142)).e = var142;
                              }
                           }

                           for(var142 = var137.size() - 1; var142 >= 0; --var142) {
                              if(var137.get(var142) != null) {
                                 var140 = (bn)var137.get(var142);
                                 if(var140.y != null) {
                                    var140.w = var140.y.e;
                                    if(var140.y.y != null) {
                                       throw new RuntimeException(var140.a + ": Turret can not be attached to turret that is also attached to a turret");
                                    }
                                 }

                                 if(var140.z != null) {
                                    var140.x = var140.z.e;
                                 }

                                 if(var140.W < 0.0F) {
                                    var140.W = var140.V;
                                 }
                              }
                           }

                           if(var137.size() > 31) {
                              throw new RuntimeException("Turret max count per unit is: 31");
                           }

                           var17.fQ = (bn[])var137.toArray(new bn[0]);
                           var17.dJ = var17.cL.i;
                           float var145 = -1.0F;
                           boolean var143 = true;
                           boolean var144 = false;
                           Iterator var146 = var137.iterator();

                           while(var146.hasNext()) {
                              bn var74 = (bn)var146.next();
                              var74.X *= var17.aG;
                              var74.f *= var17.aG;
                              var74.g *= var17.aG;
                              var74.Y *= var17.aG;
                              var74.Z *= var17.aG;
                              boolean var75 = false;
                              if(var74.B) {
                                 if(var74.ab >= 99999.0F) {
                                    var143 = false;
                                 } else {
                                    var144 = true;
                                    if(var17.dJ > var74.ab) {
                                       var17.dJ = var74.ab;
                                    }

                                    if(var145 < var74.ab) {
                                       var145 = var74.ab;
                                    }

                                    if(com.corrodinggames.rts.gameFramework.f.c(var74.ab - var17.cL.i) > 5.0F) {
                                       boolean var76 = false;
                                       Iterator var77 = var17.o.iterator();

                                       while(var77.hasNext()) {
                                          y var78 = (y)var77.next();
                                          if(com.corrodinggames.rts.gameFramework.f.c(var74.ab - var78.a) < 5.0F) {
                                             var76 = true;
                                          }
                                       }

                                       if(!var76) {
                                          var75 = true;
                                       }
                                    }
                                 }
                              }

                              if(var74.ac != null) {
                                 var75 = var74.ac.booleanValue();
                              }

                              if(var75) {
                                 y var149 = new y();
                                 var149.a = var74.ab;
                                 var17.o.add(var149);
                              }
                           }

                           if(var144 && var143) {
                              if(!var66) {
                                 var17.cL.i = var145;
                              } else if(var145 < var17.cL.i) {
                                 throw new RuntimeException("limitingRange as been applied to all turrets but is less than maxAttackRange (hint: unset maxAttackRange or a limitingRange, or make values match)");
                              }
                           }

                           String var147 = var11.b(var14, "setMainTurretAs", (String)null);
                           if(var147 != null) {
                              var17.dF = var17.e(var147);
                              if(var17.dF == null) {
                                 throw new RuntimeException("[attack] Could not find setMainTurretAs with name: " + var147);
                              }
                           } else {
                              var17.dF = var17.e("1");
                              if(var17.dF == null) {
                                 var17.dF = var17.fQ[0];
                              }
                           }

                           var17.dG = var17.dF.e;
                           a(var94, ah.l);
                           long var148 = br.a();
                           if(var11.l(var12, "action_")) {
                              for(int var150 = 0; var150 <= 50; ++var150) {
                                 a(var17, var11, var12, "action_" + var150 + "_", "" + var150, false, false);
                              }
                           }

                           Iterator var152 = var11.e("action_").iterator();

                           String var151;
                           String var154;
                           while(var152.hasNext()) {
                              var151 = (String)var152.next();
                              var154 = var151.substring("action_".length());
                              if(var17.g(var154) != null) {
                                 throw new RuntimeException("Two actions found with the same name:" + var154);
                              }

                              a(var17, var11, var151, "", var154, true, false);
                           }

                           var152 = var11.e("hiddenAction_").iterator();

                           while(var152.hasNext()) {
                              var151 = (String)var152.next();
                              var154 = var151.substring("hiddenAction_".length());
                              if(var17.g(var154) != null) {
                                 throw new RuntimeException("Two actions found with the same name:" + var154);
                              }

                              a(var17, var11, var151, "", var154, true, true);
                           }

                           a(var148, ah.j);
                           ArrayList var155 = new ArrayList();
                           ArrayList var153 = new ArrayList();

                           for(int var156 = 0; var156 <= 1; ++var156) {
                              boolean var79 = var156 == 0;
                              ArrayList var80 = var79?var155:var153;

                              for(int var81 = 1; var81 < 21; ++var81) {
                                 String var82 = var79?"leg_" + var81:"arm_" + var81;
                                 if(var11.g(var82)) {
                                    ba var83 = new ba();
                                    ba.a(var83, var17, var11, var82, var79, var80);
                                    var80.add(var83);
                                 } else {
                                    var80.add((Object)null);
                                 }
                              }
                           }

                           ArrayList var158 = new ArrayList();
                           Iterator var157 = var155.iterator();

                           ba var160;
                           while(var157.hasNext()) {
                              var160 = (ba)var157.next();
                              if(var160 != null) {
                                 var158.add(var160);
                              }
                           }

                           var157 = var153.iterator();

                           while(var157.hasNext()) {
                              var160 = (ba)var157.next();
                              if(var160 != null) {
                                 var158.add(var160);
                              }
                           }

                           for(int var159 = var158.size() - 1; var159 >= 0; var160.a = var159--) {
                              var160 = (ba)var158.get(var159);
                           }

                           var17.ax = (ba[])var158.toArray(new ba[0]);
                           if(var17.ax.length > 0) {
                              var17.a(com.corrodinggames.rts.game.units.custom.b.h.a);
                           }

                           var157 = var17.dr.iterator();

                           while(var157.hasNext()) {
                              f var162 = (f)var157.next();
                              var162.a(var17);
                           }

                           b(var17);
                           String var161 = var11.b(var12, "fireTurretXAtSelfOnDeath", (String)null);
                           if(var161 != null && !"NONE".equalsIgnoreCase(var161)) {
                              bn var163 = var17.e(var161);
                              if(var163 == null) {
                                 throw new RuntimeException("Cannot find turret:" + var161 + " for [" + var12 + "]fireTurretXAtSelfOnDeath");
                              }

                              var17.bB = var163.e;
                           }

                           com.corrodinggames.rts.game.units.custom.b.c.a(var17, var11);
                           var17.bj = var11.a(var14, "dieOnAttack", Boolean.valueOf(false)).booleanValue();
                           var17.bl = var11.a(var14, "removeOnAttack", Boolean.valueOf(false)).booleanValue();
                           var17.ep = var11.d(var14, "canAttack");
                           if(var17.ep) {
                              var17.eq = var11.a(var17, var14, "canAttackFlyingUnits");
                              var17.er = var11.a(var17, var14, "canAttackLandUnits");
                              var17.es = var11.a(var17, var14, "canAttackUnderwaterUnits");
                           } else {
                              var17.eq = var11.a(var17, var14, "canAttackFlyingUnits", (LogicBoolean)LogicBoolean.falseBoolean);
                              var17.er = var11.a(var17, var14, "canAttackLandUnits", (LogicBoolean)LogicBoolean.falseBoolean);
                              var17.es = var11.a(var17, var14, "canAttackUnderwaterUnits", (LogicBoolean)LogicBoolean.falseBoolean);
                           }

                           var17.et = var11.a(var17, var14, "canAttackNotTouchingWaterUnits", (LogicBoolean)null);
                           if(LogicBoolean.isStaticTrue(var17.et)) {
                              var17.et = null;
                           }

                           var17.ev = var11.a(var17, var14, "canOnlyAttackUnitsWithTags", (h)null);
                           var17.ew = var11.a(var17, var14, "canOnlyAttackUnitsWithoutTags", (h)null);
                           if(var17.ev != null || var17.ew != null) {
                              var17.eu = true;
                           }

                           boolean var165 = false;
                           boolean var164 = false;
                           Iterator var166 = var137.iterator();

                           while(var166.hasNext()) {
                              bn var168 = (bn)var166.next();
                              if(var168.O != null && var168.O.a(var17.ev)) {
                                 var168.O = null;
                              }

                              if(var168.P != null && var168.P.a(var17.ew)) {
                                 var168.P = null;
                              }

                              if(var168.B) {
                                 if(var168.O == null && var168.P == null) {
                                    var164 = true;
                                 } else {
                                    var165 = true;
                                 }
                              }
                           }

                           if(var165 && !var164) {
                              var17.ex = true;
                              var17.eu = true;
                           }

                           var17.ey = var11.a(var14, "isFixedFiring", Boolean.valueOf(false)).booleanValue();
                           var17.fM = var11.a(var16, "lowPriorityTargetForOtherUnits", Boolean.valueOf(false)).booleanValue();
                           var17.fN = var11.a(var16, "notPassivelyTargetedByOtherUnits", Boolean.valueOf(false)).booleanValue();
                           if(var17.ep && var17.fN) {
                              throw new RuntimeException("[ai]notPassivelyTargetedByOtherUnits is cannot currently supported on units that can attack");
                           }

                           var17.fv = var11.a(var17, var16, "aiTags", (h)null);
                           var17.fw = var11.a(var16, "disableUse", Boolean.valueOf(false)).booleanValue();
                           var17.fz = var11.a(var16, "buildPriority", Float.valueOf(0.05F)).floatValue();
                           var17.fA = var11.b(var16, "recommendedInEachBaseNum", Integer.valueOf(0)).intValue();
                           var17.fB = var11.a(var16, "recommendedInEachBasePriorityIfUnmet", Float.valueOf(0.5F)).floatValue();
                           var17.fy = var11.b(var16, "maxEachBase", Integer.valueOf(com.corrodinggames.rts.gameFramework.f.b(2, var17.fA))).intValue();
                           var17.fx = var11.b(var16, "maxGlobal", Integer.valueOf(-1)).intValue();
                           if(var17.fy < var17.fA) {
                              throw new RuntimeException("[ai]recommendedInEachBaseNum is smaller than maxEachBase");
                           }

                           if(!var17.aH) {
                              if(var11.n(var16, "recommendedInEachBaseNum")) {
                                 throw new RuntimeException("[ai]recommendedInEachBaseNum currently only applies to buildings");
                              }

                              if(var11.n(var16, "recommendedInEachBasePriorityIfUnmet")) {
                                 throw new RuntimeException("[ai]recommendedInEachBasePriorityIfUnmet currently only applies to buildings");
                              }
                           }

                           var17.fE = var11.b(var16, "whenUsingAsHarvester_recommendedInEachBase", Integer.valueOf(-1)).intValue();
                           var17.fF = var11.b(var16, "whenUsingAsHarvester_recommendedGlobal", Integer.valueOf(-1)).intValue();
                           var17.fG = var11.a(var16, "whenUsingAsHarvester_includeOtherHarvesterCounts", Boolean.valueOf(false)).booleanValue();
                           var17.fH = var11.a(var17, var16, "onlyUseAsHarvester_ifBaseHasUnitTagged", (h)null);
                           var17.fC = var11.a(var16, "nonInBaseExtraPriority", Float.valueOf(0.04F)).floatValue();
                           var17.fC = var11.a(var16, "noneInBaseExtraPriority", Float.valueOf(var17.fC)).floatValue();
                           var17.fD = var11.a(var16, "nonGlobalExtraPriority", Float.valueOf(0.0F)).floatValue();
                           var17.fD = var11.a(var16, "noneGlobalExtraPriority", Float.valueOf(var17.fD)).floatValue();
                           var17.fI = var11.b(var16, "upgradedFrom", (String)null);
                           Float var167 = var11.a(var16, "ai_upgradePriority", (Float)null);
                           if(var167 != null && var167.floatValue() != -1.0F) {
                              if(var167.floatValue() < 0.0F || var167.floatValue() > 1.0F) {
                                 throw new RuntimeException("[ai]ai_upgradePriority: " + var17.fK + " must be between 0-1 or -1 for default");
                              }

                              var17.fK = var167.floatValue() * 100.0F;
                           }

                           if(var17.ep) {
                              for(int var169 = 0; var169 < var17.fQ.length; ++var169) {
                                 bn var84 = var17.fQ[var169];
                                 if(var84.B && var84.ao == null && var17.w) {
                                    if(var84.m > 140.0F && (var17.em == -1 || var17.fQ[var17.em].m < var84.m)) {
                                       var17.em = var169;
                                    }

                                    if(var84.n > 80.0F) {
                                       var17.en = var169;
                                    }
                                 }
                              }
                           }

                           if(var17.cI == -2) {
                              if(var17.fg == com.corrodinggames.rts.game.units.ao.d) {
                                 var17.cI = 5;
                              } else if(var17.j()) {
                                 if(var17.al != null) {
                                    var17.cI = 3;
                                 } else {
                                    var17.cI = 2;
                                 }
                              } else if(var17.cL.q < -2.0F) {
                                 var17.cI = 1;
                              } else if(var17.eM > 0) {
                                 var17.cI = 3;
                              } else {
                                 var17.cI = 2;
                              }
                           }

                           if(var17.fW.size() > 0) {
                              var17.fX = true;
                              com.corrodinggames.rts.gameFramework.utility.m var170 = new com.corrodinggames.rts.gameFramework.utility.m();
                              com.corrodinggames.rts.gameFramework.utility.m var171 = new com.corrodinggames.rts.gameFramework.utility.m();
                              com.corrodinggames.rts.gameFramework.utility.m var85 = new com.corrodinggames.rts.gameFramework.utility.m();
                              Iterator var86 = var17.fW.iterator();

                              while(var86.hasNext()) {
                                 r var87 = (r)var86.next();
                                 if(var87.c == s.a) {
                                    var170.add(var87);
                                 } else if(var87.c == s.b) {
                                    var171.add(var87);
                                 } else {
                                    if(var87.c != s.c) {
                                       throw new RuntimeException("Unknown check rate:" + var87.c);
                                    }

                                    var85.add(var87);
                                 }
                              }

                              var17.fY = (r[])var170.toArray(new r[0]);
                              var17.fZ = (r[])var171.toArray(new r[0]);
                              var17.ga = (r[])var85.toArray(new r[0]);
                           }

                           Iterator var172;
                           if(var17.gp != null && var17.gp.size() > 0) {
                              var172 = var17.gp.iterator();

                              while(var172.hasNext()) {
                                 u var173 = (u)var172.next();
                                 var173.a(var17);
                              }
                           }

                           if(var17.gb.a > 0) {
                              var172 = var17.gb.iterator();

                              while(var172.hasNext()) {
                                 t var174 = (t)var172.next();
                                 var174.a(var17);
                              }

                              var17.gb.clear();
                           }

                           a(var94, ah.m);
                           var11.b();
                           var172 = var11.d.iterator();

                           String var176;
                           do {
                              if(!var172.hasNext()) {
                                 var172 = var11.e.iterator();

                                 do {
                                    if(!var172.hasNext()) {
                                       if(var4 != null) {
                                          ++var4.E;
                                       }

                                       ArrayList var178 = l.c;
                                       synchronized(l.c) {
                                          l.c.add(var17);
                                       }

                                       a(var94, ah.n);
                                       return var17;
                                    }

                                    String var177 = (String)var172.next();
                                    var176 = "Skipping line, unexpected format: \'" + var177 + "\'";
                                    var17.r(var176);
                                 } while(var17.R < 1);

                                 com.corrodinggames.rts.gameFramework.l.e("Converting warning to error (meta.strictLevel=" + var17.R + ")");
                                 throw new bo(var176);
                              }

                              com.corrodinggames.rts.gameFramework.utility.ac var175 = (com.corrodinggames.rts.gameFramework.utility.ac)var172.next();
                              if(var175.a() != null && (var175.a().startsWith("hiddenAction_") || var175.a().startsWith("canBuild_"))) {
                                 throw new RuntimeException("Error [" + var175.a() + "]" + var175.b() + " has been repeated");
                              }

                              var176 = "Repeated key " + var175;
                              var17.r(var176);
                           } while(var17.R < 1);

                           com.corrodinggames.rts.gameFramework.l.e("Converting warning to error (meta.strictLevel=" + var17.R + ")");
                           throw new bo(var176);
                        }
                     }
                  }
               }
            }
         }
      } catch (RuntimeException var91) {
         a(var0, (Exception)var91, var4);
         return null;
      } catch (OutOfMemoryError var92) {
         ++l;
         a(var0, (Exception)(new RuntimeException(var92)), var4);
         return null;
      } catch (bo var93) {
         a(var0, (Exception)var93, var4);
         return null;
      }
   }

   public static void a(String var0, Exception var1, com.corrodinggames.rts.game.units.as var2) {
      com.corrodinggames.rts.gameFramework.i.b var3 = null;
      if(var2 instanceof l) {
         l var4 = (l)var2;
         var3 = var4.J;
      }

      a(var0, var1, var3);
   }

   public static String a(com.corrodinggames.rts.gameFramework.i.b var0, String var1, boolean var2) {
      if(var0 != null) {
         String var3 = var0.q;
         var3 = com.corrodinggames.rts.gameFramework.e.a.o(var3);
         var1 = com.corrodinggames.rts.gameFramework.e.a.o(var1);
         if(var1.startsWith(var3)) {
            var1 = var1.substring(var3.length());
            if(var1.startsWith("/")) {
               var1 = var1.substring(1);
            }

            if(var1.startsWith("\\")) {
               var1 = var1.substring(1);
            }
         }

         if(var2) {
            var1 = var1 + " (in mod " + var0.a() + ")";
         }
      }

      return var1;
   }

   public static void a(String var0, Exception var1, com.corrodinggames.rts.gameFramework.i.b var2) {
      com.corrodinggames.rts.gameFramework.l.b("Error while loading unit:" + var0);
      com.corrodinggames.rts.gameFramework.l.c((Throwable)var1);
      if(var0 == null) {
         var0 = "<null>";
      }

      String var3;
      if(var1 instanceof bo) {
         var3 = var1.getMessage();
      } else {
         var3 = com.corrodinggames.rts.gameFramework.f.b(var1);
      }

      if(var3 == null) {
         var3 = "<No error cause>";
      }

      if(!var3.contains("unit config file")) {
         var3 = var3.replace(var0 + ": ", "");
         var3 = var3.replace(var0, "");
      }

      var0 = a(var2, var0, true);
      String var4;
      if(var2 != null) {
         var4 = "Error loading unit: " + var0 + ": \n" + var3;
      } else if(var3.contains("Error loading core unit")) {
         var4 = var3;
      } else {
         var4 = "Error loading core unit: " + var0 + ": \n" + var3 + " (This might be from placing a mod in \'assets/\', they should go under \'mods/\')";
      }

      if(var1 instanceof bo) {
         bo var5 = (bo)var1;
         if(var5.c != null || var5.d != null) {
            var4 = var4 + " (section:" + var5.c + ", key:" + var5.d + ")";
         }
      }

      boolean var8 = false;
      if(var2 != null) {
         var8 = var2.f;
      }

      if(!var8) {
         ;
      }

      if(s != null) {
         s = var4;
      }

      if(var2 != null) {
         var2.a(var4);
      } else {
         try {
            Thread.sleep(2L);
         } catch (InterruptedException var7) {
            ;
         }

         throw new RuntimeException(var4, var1);
      }
   }

   public static void b(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3, boolean var4) {
      String var5 = var1.b(var2, var3 + "name", (String)null);
      if(var5 != null) {
         String[] var6 = var5.split(",");
         String[] var7 = var6;
         int var8 = var6.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            String var10 = var7[var9];
            var10 = var10.trim();
            com.corrodinggames.rts.game.units.custom.a.d var11 = new com.corrodinggames.rts.game.units.custom.a.d();
            var11.k = var10;
            var11.o = var1.a(var2, var3 + "extraLagHidingInUI", Boolean.valueOf(false)).booleanValue();
            var11.p = var1.a(var2, var3 + "pos", Float.valueOf(999.0F)).floatValue();
            var11.aJ = var1.b(var2, var3 + "tech", Integer.valueOf(1)).intValue();
            var11.aK = var1.a(var2, var3 + "forceNano", Boolean.valueOf(false)).booleanValue();
            var11.aL = var1.b(var2, var3 + "type", (String)null);
            var11.q = com.corrodinggames.rts.game.units.custom.d.b.a(var0, var1, var2, var3 + "price", (com.corrodinggames.rts.game.units.custom.d.b)null);
            var11.aF = var1.a(var0, var2, var3 + "isGuiBlinking", (LogicBoolean)null);
            var11.v = var1.a(var0, var2, var3 + "isVisible", (LogicBoolean)null);
            var11.z = var1.a(var0, var2, var3 + "isLocked", (LogicBoolean)null);
            var11.A = a(var0, var1, var2, var3 + "isLockedMessage", (String)null);
            if(var11.z != null) {
               var11.y = true;
            }

            if(var11.z == LogicBoolean.falseBoolean) {
               var11.z = null;
            }

            var11.B = var1.a(var0, var2, var3 + "isLockedAlt", (LogicBoolean)null);
            var11.C = a(var0, var1, var2, var3 + "isLockedAltMessage", (String)null);
            if(var11.B != null) {
               var11.y = true;
            }

            if(var11.B == LogicBoolean.falseBoolean) {
               var11.B = null;
            }

            var11.D = var1.a(var0, var2, var3 + "isLockedAlt2", (LogicBoolean)null);
            var11.E = a(var0, var1, var2, var3 + "isLockedAlt2Message", (String)null);
            if(var11.D != null) {
               var11.y = true;
            }

            if(var11.D == LogicBoolean.falseBoolean) {
               var11.D = null;
            }

            com.corrodinggames.rts.game.units.custom.d.b var12 = com.corrodinggames.rts.game.units.custom.d.b.a(var0, var1, var2, var3 + "addResources", true);
            if(var12 != null && var12.d()) {
               var11.ae = var12;
            }

            var11.aM = com.corrodinggames.rts.game.units.custom.a.f.a;
            if(!"NONE".equalsIgnoreCase(var10)) {
               var0.gh.add(var11);
            }
         }

      }
   }

   public static void a(l var0, com.corrodinggames.rts.gameFramework.utility.ab var1, String var2, String var3, String var4, boolean var5, boolean var6) {
      com.corrodinggames.rts.game.units.custom.a.d var7 = new com.corrodinggames.rts.game.units.custom.a.d();
      String var8 = var1.b(var2, var3 + "convertTo", (String)null);
      String var9 = var1.b(var2, var3 + "whenBuilding_temporarilyConvertTo", (String)null);
      at[] var10 = as.a(var1, var2, var3 + "whenBuilding_temporarilyConvertTo_keepFields", (at[])null);
      Float var11 = var1.a(var2, var3 + "addEnergy", (Float)null);
      com.corrodinggames.rts.game.units.custom.d.b var12 = com.corrodinggames.rts.game.units.custom.d.b.a(var0, var1, var2, var3 + "addResources", true);
      var0.a(var12);
      com.corrodinggames.rts.game.units.custom.d.b var13 = com.corrodinggames.rts.game.units.custom.d.b.a(var0, var1, var2, var3 + "addResourcesScaledByAIHandicaps", true);
      var0.a(var13);
      String var14 = var1.b(var2, var3 + "fireTurretXAtGround", (String)null);
      LogicBoolean var15 = var1.b(var0, var2, var3 + "alsoTriggerOrQueueActionWithTarget", (LogicBoolean)null);
      LogicBoolean var16 = var1.a(var0, var2, var3 + "alsoTriggerOrQueueActionConditional", (LogicBoolean)null);
      String var17 = var1.b(var2, var3 + "alsoTriggerAction", (String)null);
      LogicBoolean var18 = var1.c(var0, var2, var3 + "alsoTriggerActionRepeat", (LogicBoolean)null);
      Object var19 = null;
      String var20 = var1.b(var2, var3 + "alsoQueueAction", (String)null);
      String var21 = var1.b(var2, var3 + "spawnEffects", (String)null);
      String var22 = var1.b(var2, var3 + "spawnEffectsOnQueue", (String)null);
      String var23 = var1.b(var2, var3 + "playSoundAtUnit", (String)null);
      String var24 = var1.b(var2, var3 + "playSoundGlobally", (String)null);
      String var25 = var1.b(var2, var3 + "playSoundToPlayer", (String)null);
      String var26 = var1.b(var2, var3 + "playSoundToPlayerOnQueue", (String)null);
      com.corrodinggames.rts.game.units.custom.a.a.o.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.e.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.h.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.a.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.k.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.b.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.d.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.l.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.g.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.m.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.f.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.j.a(var0, var1, var2, var3, var7, var4, var5);
      com.corrodinggames.rts.game.units.custom.a.a.i.a(var0, var1, var2, var3, var7, var4, var5);
      LogicBoolean var27 = var1.a(var0, var2, var3 + "resetCustomTimer", (LogicBoolean)null);
      boolean var28 = false;
      if(var5) {
         var28 = true;
      } else {
         if(var8 != null || var9 != null || var11 != null || var14 != null) {
            var28 = true;
         }

         if(var12.d() || var13.d()) {
            var28 = true;
         }

         if(var17 != null || var20 != null || var21 != null || var19 != null) {
            var28 = true;
         }

         if(var23 != null || var24 != null || var25 != null || var26 != null) {
            var28 = true;
         }

         if(var7.ac.size() > 0) {
            var28 = true;
         }
      }

      if(var28) {
         if("NONE".equalsIgnoreCase(var8)) {
            var8 = null;
         }

         if("NONE".equalsIgnoreCase(var9)) {
            var9 = null;
         }

         if(var14 != null && var14.equalsIgnoreCase("NONE")) {
            var14 = null;
         }

         var7.a = var0.gh.size();
         String var29 = var1.b(var2, var3 + "id", (String)null);
         if(var29 != null) {
            var7.b = "c" + var29;
            if(var7.b.contains(" ")) {
               throw new RuntimeException("[" + var2 + "]id cannot contain space");
            }

            if(var7.b.contains(",")) {
               throw new RuntimeException("[" + var2 + "]id cannot contain ,");
            }

            if(var7.b.contains(":")) {
               throw new RuntimeException("[" + var2 + "]id cannot contain :");
            }

            if(var7.b.contains("(")) {
               throw new RuntimeException("[" + var2 + "]id cannot contain (");
            }

            if(var7.b.contains(" ")) {
               throw new RuntimeException("[" + var2 + "]id cannot contain null");
            }

            if(var7.b.length() > 15) {
               throw new RuntimeException("[" + var2 + "]id cannot be longer than 15 characters");
            }

            Iterator var30 = var0.gh.iterator();

            while(var30.hasNext()) {
               com.corrodinggames.rts.game.units.custom.a.d var31 = (com.corrodinggames.rts.game.units.custom.a.d)var30.next();
               if(var7.b.equalsIgnoreCase(var31.b)) {
                  throw new RuntimeException("[" + var2 + "]id more than one action exists with id: " + var29);
               }
            }
         }

         var7.c = var4;
         var7.o = var1.a(var2, var3 + "extraLagHidingInUI", Boolean.valueOf(false)).booleanValue();
         var7.s = g.a(var1.b(var2, var3 + "tags", (String)null));
         var7.p = var1.a(var2, var3 + "pos", Float.valueOf(999.0F)).floatValue();
         var7.q = com.corrodinggames.rts.game.units.custom.d.b.a(var0, var1, var2, var3 + "price", true);
         var7.r = com.corrodinggames.rts.game.units.custom.d.b.b(var0, var1, var2, var3 + "streamingCost", (com.corrodinggames.rts.game.units.custom.d.b)null);
         boolean var49 = var1.a(var2, var3 + "switchPriceWithStreamingCost", Boolean.valueOf(false)).booleanValue();
         if(var49) {
            if(var7.r != null) {
               throw new RuntimeException("[" + var2 + "]streamingCost and switchPriceWithStreamingCost=true cannot be used at the same time");
            }

            var7.r = com.corrodinggames.rts.game.units.custom.d.b.b(var0, var1, var2, var3 + "price", (com.corrodinggames.rts.game.units.custom.d.b)null);
            var7.q = com.corrodinggames.rts.game.units.custom.d.b.a;
         }

         var0.a(var7.q);
         if(var7.r != null) {
            var0.a(var7.r);
         }

         var7.K = var1.a(var2, var3 + "highPriorityQueue", Boolean.valueOf(false)).booleanValue();
         var7.L = var1.a(var2, var3 + "onlyOneUnitAtATime", Boolean.valueOf(false)).booleanValue();
         var7.M = var1.a(var2, var3 + "canPlayerCancel", Boolean.valueOf(true)).booleanValue();
         var7.O = var1.a(var2, var3 + "alwaysSinglePress", Boolean.valueOf(false)).booleanValue();
         var7.N = var1.a(var2, var3 + "allowMultipleInQueue", Boolean.valueOf(true)).booleanValue();
         if(!var7.M && !var7.N && var7.O) {
            var7.P = true;
         }

         if(!var7.M) {
            var7.j = com.corrodinggames.rts.game.units.a.u.a;
         } else {
            var7.j = com.corrodinggames.rts.game.units.a.u.c;
         }

         var7.t = var1.a(var0, var2, var3 + "requireConditional", (LogicBoolean)null);
         var7.u = var1.a(var0, var2, var3 + "isActive", (LogicBoolean)null);
         var7.v = var1.a(var0, var2, var3 + "isVisible", (LogicBoolean)null);
         var7.x = var1.a(var2, var3 + "isAlsoViewableByEnemies", Boolean.valueOf(false)).booleanValue();
         var7.w = var1.a(var2, var3 + "isAlsoViewableByAllies", Boolean.valueOf(var7.x)).booleanValue();
         if(var6) {
            if(var7.v != null && !LogicBoolean.isStaticFalse(var7.v)) {
               throw new RuntimeException("[" + var2 + "]isVisible doesn\'t make sense to use in hidden actions");
            }

            var7.v = LogicBoolean.falseBoolean;
         }

         var7.z = var1.a(var0, var2, var3 + "isLocked", (LogicBoolean)null);
         var7.A = a(var0, var1, var2, var3 + "isLockedMessage", (String)null);
         if(var7.z != null) {
            var7.y = true;
         }

         if(var7.z == LogicBoolean.falseBoolean) {
            var7.z = null;
         }

         var7.B = var1.a(var0, var2, var3 + "isLockedAlt", (LogicBoolean)null);
         var7.C = a(var0, var1, var2, var3 + "isLockedAltMessage", (String)null);
         if(var7.B != null) {
            var7.y = true;
         }

         if(var7.B == LogicBoolean.falseBoolean) {
            var7.B = null;
         }

         var7.D = var1.a(var0, var2, var3 + "isLockedAlt2", (LogicBoolean)null);
         var7.E = a(var0, var1, var2, var3 + "isLockedAlt2Message", (String)null);
         if(var7.D != null) {
            var7.y = true;
         }

         if(var7.D == LogicBoolean.falseBoolean) {
            var7.D = null;
         }

         var7.F = LogicBoolean.create(var0, var1.b(var2, var3 + "ai_isHighPriority", (String)null), (LogicBoolean)null);
         if(var7.F == LogicBoolean.falseBoolean) {
            var7.F = null;
         }

         if(var7.F != null) {
            var0.fJ = true;
         }

         var7.G = var1.a(var0, var2, var3 + "ai_isDisabled", (LogicBoolean)LogicBoolean.falseBoolean);
         var7.aN = (com.corrodinggames.rts.game.units.custom.a.e)var1.a(var2, var3 + "aiUse", (Enum)var7.aN, com.corrodinggames.rts.game.units.custom.a.e.class);
         var7.J = var0.a(var1.b(var2, var3 + "guiBuildUnit", (String)null), var3 + "guiBuildUnit", var2);
         if(var7.J != null) {
            var7.j = com.corrodinggames.rts.game.units.a.u.b;
            if(var8 != null) {
               throw new RuntimeException("[" + var2 + "]guiBuildUnit and convertTo cannot currently be used the same action");
            }
         }

         var7.I = var0.a(var1.b(var2, var3 + "ai_considerSameAsBuilding", (String)null), var3 + "ai_considerSameAsBuilding", var2);
         var7.aF = var1.a(var0, var2, var3 + "isGuiBlinking", (LogicBoolean)null);
         var7.ay = a(var0.F, var1.b(var2, var3 + "iconImage", "NONE"), var0.ab, var0, var2, var3 + "iconImage");
         var7.aB = var1.a(var0, var2, var3 + "iconExtraIsVisible", (LogicBoolean)null);
         if(var7.aB == LogicBoolean.trueBoolean) {
            var7.aB = null;
         }

         var7.az = var0.a(var1, var2, var3 + "iconExtraImage");
         var7.aA = var1.a(var2, var3 + "iconExtraColor", Integer.valueOf(Color.a(100, 255, 255, 255))).intValue();
         var7.aC = UnitReference.parseUnitTypeOrReferenceFromConf(var0, var1, var2, var3 + "unitShownInUI", (UnitReference$UnitReferenceOrUnitType)null);
         if(var7.aC != null && var7.ay != null) {
            throw new RuntimeException("[" + var2 + "]unitShownInUI and iconImage: doesn\'t make sense to use both at the same time");
         }

         var7.aD = var1.a(var2, var3 + "unitShownInUIWithHpBar", Boolean.valueOf(true)).booleanValue();
         var7.aE = var1.a(var2, var3 + "unitShownInUIWithProgressBar", Boolean.valueOf(true)).booleanValue();
         var7.aG = (com.corrodinggames.rts.game.units.a.t)var1.a(var2, var3 + "displayType", (Enum)var7.aG, com.corrodinggames.rts.game.units.a.t.class);
         var7.aI = var1.a(var2, var3 + "displayRemainingStockpile", Boolean.valueOf(false)).booleanValue();
         var7.d = a(var0, var1, var2, var3 + "text", "");
         var7.e = UnitReference.parseUnitTypeOrReferenceFromConf(var0, var1, var2, var3 + "textAddUnitName", (UnitReference$UnitReferenceOrUnitType)null);
         var7.h = a(var1, var2, var3 + "textPostFix", (String)null);
         var7.f = UnitReference.parseUnitTypeOrReferenceFromConf(var0, var1, var2, var3 + "descriptionAddFromUnit", (UnitReference$UnitReferenceOrUnitType)null);
         var7.g = UnitReference.parseUnitTypeOrReferenceFromConf(var0, var1, var2, var3 + "descriptionAddUnitStats", (UnitReference$UnitReferenceOrUnitType)null);
         var7.i = a(var0, var1, var2, var3 + "description", "");
         var7.S = var1.d(var2, var3 + "buildSpeed", Float.valueOf(var7.S)).floatValue();
         if(var7.S == 0.0F) {
            var7.S = 50.0F;
         }

         var7.T = var1.a(var2, var3 + "buildSpeed_ignoreFactorySpeedModifiers", Boolean.valueOf(var7.T)).booleanValue();
         boolean var50 = false;
         var7.U = var1.a(var2, var3 + "whenBuilding_cannotMove", Boolean.valueOf(var7.U)).booleanValue();
         var7.V = var0.a(var1.b(var2, var3 + "whenBuilding_playAnimation", (String)null), var7.V);
         var7.W = var1.a(var2, var3 + "whenBuilding_rotateTo", var7.W);
         var7.X = var1.a(var2, var3 + "whenBuilding_rotateTo_orBackwards", Boolean.valueOf(var7.X)).booleanValue();
         var7.Y = var1.a(var2, var3 + "whenBuilding_rotateTo_waitTillRotated", Boolean.valueOf(var7.Y)).booleanValue();
         var7.Z = var1.a(var2, var3 + "whenBuilding_rotateTo_aimAtActionTarget", Boolean.valueOf(var7.Z)).booleanValue();
         String var32 = var1.b(var2, var3 + "whenBuilding_rotateTo_rotateTurretX", (String)null);
         if(var32 != null) {
            var7.aa = var0.e(var32);
            if(var7.aa == null) {
               throw new RuntimeException("Cannot find turret:" + var32 + " for [" + var2 + "]" + var3 + "whenBuilding_rotateTo_rotateTurretX");
            }

            if(var7.X) {
               throw new RuntimeException("whenBuilding_rotateTo_orBackwards:true not supported with [" + var2 + "]" + var3 + "whenBuilding_rotateTo_rotateTurretX");
            }
         }

         if(var7.Z && var7.W == null) {
            var7.W = Float.valueOf(0.0F);
         }

         var7.ab = var1.a(var0, var2, var3 + "whenBuilding_triggerAction", (u)null);
         var7.Q = var1.a(var2, var3 + "convertTo_keepCurrentTags", Boolean.valueOf(var7.Q)).booleanValue();
         var7.R = as.a(var1, var2, var3 + "convertTo_keepCurrentFields", (at[])null);
         if(var9 != null && !"NONE".equalsIgnoreCase(var9)) {
            var7.l = var0.a(var9, var3 + "whenBuilding_temporarilyConvertTo", var2);
            var7.m = var10;
            var50 = true;
         }

         if(var7.U || var7.V != null || var7.W != null || var7.l != null || var7.ab != null) {
            var0.bg = true;
         }

         var7.aM = com.corrodinggames.rts.game.units.custom.a.f.b;
         if(var8 != null && !"NONE".equalsIgnoreCase(var8)) {
            var7.H = var0.a(var8, var3 + "convertTo", var2);
            var7.k = var8;
            var7.N = false;
            var50 = true;
         }

         if(var11 != null) {
            var7.ad = var11;
            var50 = true;
         }

         if(var12 != null && var12.d()) {
            var7.ae = var12;
            var50 = true;
         }

         if(var13 != null && var13.d()) {
            var7.af = var13;
            var50 = true;
         }

         var7.ah = var1.a(var2, var3 + "fireTurretXAtGround_withOffset", (PointF)null);
         var7.ai = var1.b(var0, var2, var3 + "fireTurretXAtGround_withTarget", (LogicBoolean)null);
         var7.ak = var1.b(var2, var3 + "fireTurretXAtGround_count", Integer.valueOf(1)).intValue();
         var7.am = com.corrodinggames.rts.game.units.custom.b.c.a(var0, var1.b(var2, "fireTurretXAtGround_showGuideDecals", (String)null));
         if(var7.ai != null && var7.ah == null) {
            var7.ah = new PointF(0.0F, 0.0F);
         }

         String var33 = var1.b(var2, var3 + "fireTurretXAtGround_withProjectile", (String)null);
         if(var33 != null) {
            var7.aj = var0.f(var33);
            if(var7.aj == null) {
               throw new RuntimeException("Cannot find projectile:" + var33 + " for [" + var2 + "]" + var3 + "fireTurretXAtGround_withProjectile");
            }
         }

         String var34 = var1.b(var2, var3 + "fireTurretXAtGround_onlyOverPassableTileOf", (String)null);
         if(var34 != null) {
            var7.al = com.corrodinggames.rts.game.units.ao.a(var34, var3 + "fireTurretXAtGround_overPassableTileOf");
         }

         if(var14 != null) {
            bn var35 = var0.e(var14);
            if(var35 == null) {
               throw new RuntimeException("Cannot find turret:" + var14 + " for [" + var2 + "]" + var3 + "fireTurretXAtGround");
            }

            var7.ag = Integer.valueOf(var35.e);
            if(var7.ah == null) {
               var7.j = com.corrodinggames.rts.game.units.a.u.g;
               if(var7.J != null) {
                  throw new RuntimeException("[" + var2 + "]guiBuildUnit and fireTurretXAtGround (without withOffset) cannot be used in the same action");
               }
            }

            var50 = true;
         }

         var7.an = var15;
         var7.ao = var16;
         if(var17 != null && !"NONE".equalsIgnoreCase(var17)) {
            var7.ap = var0.c(var17, "alsoTriggerAction", var2);
            if(var18 != null) {
               if(LogicBoolean.isStaticNumber(var18)) {
                  float var51 = LogicBoolean.getKnownStaticNumber(var18);
                  if(var51 == 0.0F) {
                     var7.ap = null;
                  } else if(var51 != 1.0F) {
                     var7.ar = var18;
                  }
               } else {
                  var7.ar = var18;
               }
            }

            var50 = true;
         }

         if(var20 != null && !"NONE".equalsIgnoreCase(var20)) {
            var7.aq = var0.c(var20, "alsoQueueAction", var2);
            var50 = true;
         }

         if(var21 != null) {
            var7.as = var0.a(var21, (z)null);
            var50 = true;
         }

         if(var22 != null) {
            var7.at = var0.a(var22, (z)null);
            var50 = true;
         }

         if(var23 != null) {
            var7.au = bl.a(var0, var23);
            var50 = true;
         }

         if(var24 != null) {
            var7.av = bl.a(var0, var24);
            var50 = true;
         }

         if(var25 != null) {
            var7.aw = bl.a(var0, var25);
            var50 = true;
         }

         if(var26 != null) {
            var7.ax = bl.a(var0, var26);
            var50 = true;
         }

         if(var27 != null) {
            var7.aH = var27;
            var50 = true;
         }

         if(var7.ac.size() > 0) {
            var50 = true;
         }

         ArrayList var52 = null;
         String var36 = var1.b(var2, var3 + "autoTriggerOnEvent", (String)null);
         Integer var37 = var1.b(var2, var3 + "autoTriggerOnEventRecursionLimit", (Integer)null);
         if(var37 != null) {
            if(var37.intValue() < 0) {
               throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEventRecursionLimit: Cannot be < 0");
            }

            if(var37.intValue() > 50) {
               throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEventRecursionLimit: Cannot be > 100");
            }
         }

         Iterator var43;
         if(var36 != null) {
            ArrayList var38 = a(var2, var3 + "autoTriggerOnEvent", var36);
            if(var38 != null) {
               if(var38.size() < 1) {
                  throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEvent: Expected 1 or more options, got:" + var38.size());
               }

               ae var42;
               for(Iterator var39 = var38.iterator(); var39.hasNext(); var52.add(var42)) {
                  ai var40 = (ai)var39.next();

                  af var41;
                  try {
                     var41 = (af)com.corrodinggames.rts.gameFramework.utility.ab.a(var40.a, (Enum)null, af.class);
                  } catch (bo var48) {
                     throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEvent: " + var48.getMessage(), var48);
                  }

                  if(var52 == null) {
                     var52 = new ArrayList();
                  }

                  var42 = new ae();
                  var42.a = var41;
                  if(var37 != null) {
                     var42.e = var37.intValue();
                  } else if(var42.a == af.q) {
                     var42.e = 4;
                  }

                  String var47;
                  if(var40.b != null) {
                     for(var43 = var40.b.keySet().iterator(); var43.hasNext(); var42.d = com.corrodinggames.rts.gameFramework.utility.ab.j(var2, var3 + "autoTriggerOnEvent", var47)) {
                        String var44 = (String)var43.next();
                        String var45 = (String)var40.b.get(var44);
                        boolean var46 = false;
                        if(var44.equalsIgnoreCase("withtag")) {
                           if(var42.a != af.n && var42.a != af.q) {
                              throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEvent: " + var42.a.name() + " doesn\'t support parameter: " + var44);
                           }

                           var46 = true;
                        }

                        if(var44.equalsIgnoreCase("withprojectiletag")) {
                           if(var42.a != af.n) {
                              throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEvent: " + var42.a.name() + " doesn\'t support parameter: " + var44);
                           }

                           var46 = true;
                        }

                        if(var44.equalsIgnoreCase("withactiontag")) {
                           if(var42.a != af.f && var42.a != af.g) {
                              throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEvent: " + var42.a.name() + " doesn\'t support parameter: " + var44);
                           }

                           var46 = true;
                        }

                        if(!var46) {
                           throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEvent: Unknown parameter: " + var44);
                        }

                        var47 = com.corrodinggames.rts.gameFramework.f.p(var45);
                        if(var47 == null) {
                           throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEvent: " + var42.a.name() + " expected quoted string, got: " + var45);
                        }

                        if(var42.d != null) {
                           throw new bo("[" + var2 + "]" + var3 + "autoTriggerOnEvent: " + var42.a.name() + " tag was set twice");
                        }
                     }
                  }
               }
            }
         }

         LogicBoolean var53 = var1.a(var0, var2, var3 + "autoTrigger", (LogicBoolean)null);
         String var54 = var1.b(var2, var3 + "autoTrigger", (String)null);
         s var55 = (s)var1.a(var2, var3 + "autoTriggerCheckRate", (Enum)var0.cb, s.class);
         var7.n = var50;
         if(var50 || var7.v != null) {
            if(var53 != null && var50) {
               r var56 = new r();
               var56.a = var53;
               var56.b = var54;
               var56.c = var55;
               var56.d = new com.corrodinggames.rts.game.units.custom.a.g(var7, var0.a(var7.k, "[" + var2 + "]" + var3, var2));
               var0.fW.add(var56);
            }

            if(var52 != null && var50) {
               com.corrodinggames.rts.game.units.custom.a.g var57 = new com.corrodinggames.rts.game.units.custom.a.g(var7, var0.a(var7.k, "[" + var2 + "]" + var3, var2));
               l var58 = var0;
               var43 = var52.iterator();

               while(var43.hasNext()) {
                  ae var59 = (ae)var43.next();
                  var59.b = var57;
                  var59.c = var58;
                  var0.gq.add(var59);
               }
            }

            if(var7.k != null && var7.q != null && var7.q.b > 0) {
               var0.gi = true;
            }

            var0.gh.add(var7);
         }
      }

   }

   public static String a(l var0, String var1, String var2) {
      if(var2.startsWith("SHARED:")) {
         var2 = var2.substring("SHARED:".length());
         var1 = "units/shared/common.ini";
      }

      if(var2.startsWith("CORE:")) {
         var2 = var2.substring("CORE:".length());
         var1 = "units/common.ini";
      }

      if(var2.startsWith("ROOT:")) {
         var2 = var2.substring("ROOT:".length());
         if(var0.J == null) {
            var1 = "units/common.ini";
         } else {
            var1 = var0.J.q + "/common.ini";
         }
      }

      String var3;
      for(var3 = com.corrodinggames.rts.gameFramework.f.h(var1) + "/"; var2.startsWith("/") || var2.startsWith("\\"); var2 = var2.substring(1)) {
         ;
      }

      String var4 = var3 + var2;
      return var4;
   }

   public static void a(long var0, ah var2) {
      double var3 = (double)br.a(var0);
      var2.o += var3;
   }

   public static void i() {
      com.corrodinggames.rts.gameFramework.l.e("==Timing==");
      ah[] var0 = ah.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         ah var3 = var0[var2];
         com.corrodinggames.rts.gameFramework.l.e(var3.name() + ": " + br.a(var3.o));
      }

   }

   public static void j() {
      ah[] var0 = ah.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         ah var3 = var0[var2];
         var3.o = 0.0D;
      }

   }

   public static com.corrodinggames.rts.gameFramework.m.e a(String var0, String var1, boolean var2, l var3, String var4, String var5) {
      try {
         return a(var0, var1, var2, var3);
      } catch (RuntimeException var7) {
         var7.printStackTrace();
         throw new RuntimeException("[" + var4 + "]" + var5 + ": " + var7.getMessage(), var7);
      }
   }

   public static com.corrodinggames.rts.gameFramework.m.e a(String var0, String var1, boolean var2, l var3) {
      long var4 = br.a();
      com.corrodinggames.rts.gameFramework.m.e var6 = b(var0, var1, var2, var3);
      a(var4, ah.b);
      return var6;
   }

   public static com.corrodinggames.rts.gameFramework.m.e b(String var0, String var1, boolean var2, l var3) {
      if(var1 == null) {
         return null;
      } else if(var1.equalsIgnoreCase("NONE")) {
         return null;
      } else if(var1.equals("")) {
         return null;
      } else {
         boolean var4 = false;
         if(var1.startsWith("SHADOW:")) {
            var1 = var1.substring("SHADOW:".length());
            var4 = true;
         }

         if(var1.startsWith("SHARED:")) {
            var1 = var1.substring("SHARED:".length());
            var0 = "units/shared/common.ini";
         }

         if(var1.startsWith("CORE:")) {
            var1 = var1.substring("CORE:".length());
            var0 = "units/common.ini";
         }

         if(var1.startsWith("ROOT:")) {
            var1 = var1.substring("ROOT:".length());
            if(var3.J == null) {
               var0 = "units/common.ini";
            } else {
               var0 = var3.J.q + "/common.ini";
            }
         }

         if(var1.startsWith("SHADOW:")) {
            var1 = var1.substring("SHADOW:".length());
            var4 = true;
         }

         com.corrodinggames.rts.gameFramework.l var5 = com.corrodinggames.rts.gameFramework.l.B();
         String var7 = com.corrodinggames.rts.gameFramework.f.h(var0) + "/";
         String var8 = "[" + var2 + "," + var4 + "]" + var7 + var1;
         com.corrodinggames.rts.gameFramework.m.e var9 = c(var8);
         if(var9 != null) {
            return var9;
         } else {
            com.corrodinggames.rts.gameFramework.utility.j var6 = c(var7, var1, var3);
            int var11 = 0;
            if(e != null) {
               var11 = e.I;
            }

            com.corrodinggames.rts.gameFramework.m.e var10;
            if(var11 > 5) {
               com.corrodinggames.rts.gameFramework.l.e("Fast failing to oom image for this mod");
               var10 = var5.bO.r();
            } else {
               long var12 = br.a();

               try {
                  var10 = var5.bO.a(var6, true);
               } catch (RuntimeException var16) {
                  com.corrodinggames.rts.gameFramework.l.e("imageStream:" + var6);
                  throw new RuntimeException("Error decode image from: " + com.corrodinggames.rts.gameFramework.e.a.d(var7 + var1), var16);
               }

               a(var12, ah.a);
               if(var10.A()) {
                  com.corrodinggames.rts.gameFramework.l.e("oomErrors:" + l);
                  ++l;
                  if(e != null) {
                     ++e.I;
                     ++e.J;
                  }
               } else if(e != null && !e.z && com.corrodinggames.rts.gameFramework.l.aZ) {
                  var10.z();
               }
            }

            try {
               var6.close();
            } catch (Exception var15) {
               var15.printStackTrace();
            }

            if(var10 == null) {
               throw new RuntimeException("Failed to decode image: " + com.corrodinggames.rts.gameFramework.e.a.e(var7 + var1));
            } else {
               var10.a(var2);
               if(var4) {
                  var10 = com.corrodinggames.rts.game.units.am.a(var10, var10.p, var10.q);
               }

               a(var10);
               a(var8, var10);
               return var10;
            }
         }
      }
   }

   public static void a(String var0, com.corrodinggames.rts.gameFramework.m.e var1) {
      g.put(var0, var1);
   }

   public static com.corrodinggames.rts.gameFramework.m.e c(String var0) {
      com.corrodinggames.rts.gameFramework.m.e var1 = (com.corrodinggames.rts.gameFramework.m.e)g.get(var0);
      if(var1 != null) {
         ++j;
         a(var1);
         var1.t();
         return var1;
      } else {
         if(k) {
            com.corrodinggames.rts.gameFramework.l.e("loadImageInConf: cache miss: " + var0);
         }

         ++i;
         return null;
      }
   }

   public static com.corrodinggames.rts.gameFramework.a.i a(String var0, String var1, l var2) {
      long var3 = br.a();
      com.corrodinggames.rts.gameFramework.a.i var5 = b(var0, var1, var2);
      a(var3, ah.d);
      return var5;
   }

   public static com.corrodinggames.rts.gameFramework.a.i b(String var0, String var1, l var2) {
      if(var1 == null) {
         return null;
      } else if(var1.equalsIgnoreCase("NONE")) {
         return null;
      } else {
         com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
         if(!var1.contains(".")) {
            com.corrodinggames.rts.gameFramework.a.i var15 = var3.bM.a(var1);
            return var15;
         } else {
            if(var1.startsWith("ROOT:")) {
               var1 = var1.substring("ROOT:".length());
               if(var2.J == null) {
                  var0 = "units/common.ini";
               } else {
                  var0 = var2.J.q + "/common.ini";
               }
            }

            if(var1.startsWith("CORE:")) {
               var1 = var1.substring("CORE:".length());
               var0 = "units/common.ini";
            }

            if(var1.startsWith("SHARED:")) {
               var1 = var1.substring("SHARED:".length());
               var0 = "units/shared/common.ini";
            }

            boolean var4 = false;
            String var5 = com.corrodinggames.rts.gameFramework.f.h(var0) + "/";
            String var6 = var5 + var1;
            com.corrodinggames.rts.gameFramework.a.i var7 = (com.corrodinggames.rts.gameFramework.a.i)h.get(var6);
            if(var7 != null) {
               a(var7);
               return var7;
            } else if(!var1.toLowerCase(Locale.ROOT).endsWith(".ogg") && !var1.toLowerCase(Locale.ROOT).endsWith(".wav")) {
               throw new RuntimeException("Failed to open sound: " + var5 + "" + var1 + " only the ogg & wav sound formats are supported.");
            } else {
               com.corrodinggames.rts.gameFramework.utility.j var8 = c(var5, var1, var2);
               long var9 = br.a();
               com.corrodinggames.rts.gameFramework.a.i var11 = var3.bM.a(var1, var8, var4);

               try {
                  var8.close();
               } catch (Exception var14) {
                  var14.printStackTrace();
               }

               a(var9, ah.c);
               if(var11 == null) {
                  boolean var12 = var1.toLowerCase(Locale.ROOT).endsWith(".ogg");
                  String var13 = "Sound file found but failed to load: " + var6;
                  if(var12) {
                     var13 = var13 + " - Check if this file is truly a ogg";
                  }

                  var2.r(var13);
                  return var3.bM.b("Failed to load");
               } else {
                  a(var11);
                  h.put(var6, var11);
                  return var11;
               }
            }
         }
      }
   }

   public static boolean a(String var0, String var1, String var2, com.corrodinggames.rts.gameFramework.i.b var3) {
      if(var1 == null) {
         return true;
      } else if(!var1.contains("..")) {
         return true;
      } else if(com.corrodinggames.rts.gameFramework.l.at()) {
         return true;
      } else {
         File var4 = new File(com.corrodinggames.rts.gameFramework.e.a.e(var2));
         String var5 = var4.getCanonicalPath();
         String var6 = (new File(com.corrodinggames.rts.gameFramework.e.a.e("units"))).getCanonicalPath();
         if(var5.startsWith(var6)) {
            return true;
         } else {
            String var7 = var3.k();
            boolean var8 = var5.startsWith(var7);
            if(!var8) {
               com.corrodinggames.rts.gameFramework.l.b("File: \'" + var5 + "\' is not within mod: \'" + var7 + "\'");
            }

            return var8;
         }
      }
   }

   public static String a(String var0, String var1) {
      if(!var0.endsWith("/")) {
         var0 = var0 + "/";
      }

      while(var1.startsWith("/") || var1.startsWith("\\")) {
         var1 = var1.substring(1);
      }

      return var0 + var1;
   }

   public static com.corrodinggames.rts.gameFramework.utility.j c(String var0, String var1, l var2) {
      String var3 = a(var0, var1);
      com.corrodinggames.rts.gameFramework.i.b var4 = null;
      if(var2 != null) {
         var4 = var2.J;
      } else {
         com.corrodinggames.rts.gameFramework.l.g("findAssetSteam meta==null");
      }

      try {
         if(var4 != null && !a(var0, var1, var3, var4)) {
            throw new RuntimeException("File is outside mod: " + var3);
         }
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      }

      com.corrodinggames.rts.gameFramework.utility.j var5 = com.corrodinggames.rts.gameFramework.e.a.k(var3);
      if(var5 == null) {
         com.corrodinggames.rts.gameFramework.l.e("Orginal path: " + var3);
         throw new RuntimeException("IO Error: Failed to open: " + a(var4, var3, true));
      } else {
         return var5;
      }
   }

   public static void b(l var0) {
      ba[] var1 = var0.ax;

      for(int var2 = 0; var2 < var1.length; ++var2) {
         ba var3 = var1[var2];
         float var4 = -1.0F;
         ba var5 = null;
         float var6 = 1.0F;
         if(var3.o) {
            var6 = 0.1F;
         }

         for(int var7 = 0; var7 < var1.length; ++var7) {
            ba var8 = var1[var7];
            if(var3 != var8 && !var8.l) {
               float var9 = com.corrodinggames.rts.gameFramework.f.a(var3.d * var6, var3.e, var8.d * var6, var8.e);
               if(var5 == null || var9 < var4) {
                  var4 = var9;
                  var5 = var8;
               }
            }
         }

         var4 = com.corrodinggames.rts.gameFramework.f.a(var4) + 2.0F;
         var4 *= var4;
         ArrayList var11 = new ArrayList();

         int var12;
         for(var12 = 0; var12 < var1.length; ++var12) {
            ba var13 = var1[var12];
            if(var3 != var13 && !var13.l) {
               float var10 = com.corrodinggames.rts.gameFramework.f.a(var3.d * var6, var3.e, var13.d * var6, var13.e);
               if(var10 <= var4) {
                  var11.add(Integer.valueOf(var13.a));
               }
            }
         }

         var3.S = new int[var11.size()];

         for(var12 = 0; var12 < var11.size(); ++var12) {
            var3.S[var12] = ((Integer)var11.get(var12)).intValue();
         }
      }

   }

   public static String k() {
      return "builtin_mods";
   }

   public static String l() {
      return "builtin_mods_enabled";
   }

   public static String m() {
      String var0;
      if(com.corrodinggames.rts.gameFramework.l.aU) {
         var0 = "/SD/mods/units";
      } else {
         var0 = "/SD/rustedWarfare/units";
      }

      return var0;
   }

   public static ArrayList a(String var0, String var1, String var2) {
      if(var2 != null && !"".equals(var2) && !"NONE".equalsIgnoreCase(var2)) {
         ArrayList var3 = new ArrayList();
         ArrayList var4 = com.corrodinggames.rts.gameFramework.utility.al.a(var2, ",", false);
         Iterator var5 = var4.iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            var6 = var6.trim();
            if(!"".equals(var6)) {
               String var7 = var6;
               String var8 = null;
               if(var6.contains("(") && var6.contains(")")) {
                  String[] var9 = com.corrodinggames.rts.gameFramework.utility.al.b(var6, "(");
                  if(var9 == null) {
                     throw new bo("[" + var0 + "]" + var1 + ": Unexpected format for \'" + var6 + "\' of " + var2);
                  }

                  var6 = var9[0];
                  var8 = var9[1].trim();
               }

               ai var16 = new ai();
               var16.a = var6;
               if(var8 != null) {
                  if(!var8.endsWith(")")) {
                     throw new bo("[" + var0 + "]" + var1 + ": Expected \')\' in \'" + var7 + "\' of " + var2);
                  }

                  var8 = var8.substring(0, var8.length() - 1);
                  ArrayList var10 = com.corrodinggames.rts.gameFramework.utility.al.a(var8, ",", false, false);
                  Iterator var11 = var10.iterator();

                  while(var11.hasNext()) {
                     String var12 = (String)var11.next();
                     if(!var12.trim().equals("")) {
                        String[] var13 = com.corrodinggames.rts.gameFramework.utility.al.b(var12, "=");
                        if(var13 == null) {
                           throw new RuntimeException("[" + var0 + "]" + var1 + ": Unexpected key format for \'" + var7 + "\' of " + var2);
                        }

                        String var14 = var13[0].trim();
                        String var15 = var13[1].trim();
                        if(var16.b == null) {
                           var16.b = new HashMap();
                        }

                        var16.b.put(var14, var15);
                     }
                  }
               }

               var3.add(var16);
            }
         }

         return var3;
      } else {
         return null;
      }
   }

}
