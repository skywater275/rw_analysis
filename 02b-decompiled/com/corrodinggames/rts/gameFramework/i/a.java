package com.corrodinggames.rts.gameFramework.i;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.custom.ag;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.i.b;
import com.corrodinggames.rts.gameFramework.i.c;
import com.corrodinggames.rts.gameFramework.utility.ae;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

public class a {

   public static String a;
   public static String b;
   public b c = new b();
   Object d = new Object();
   ArrayList e = new ArrayList();
   ArrayList f = new ArrayList();


   public a() {
      try {
         a(l.B().u());
      } catch (bo var2) {
         throw new RuntimeException(var2);
      }
   }

   private static int a(String var0, int var1) {
      String[] var2 = f.c(var0, '.');
      if(var2 == null) {
         throw new bo("Unexpected version format (Missing " + var1 + ")");
      } else if(var2.length > 3) {
         throw new bo("Unexpected version format (" + var0 + ")");
      } else if(var2.length <= var1) {
         return 0;
      } else {
         try {
            return Integer.valueOf(var2[var1]).intValue();
         } catch (NumberFormatException var4) {
            throw new bo("Unexpected version format (Bad " + var1 + ")", var4);
         }
      }
   }

   public static void a(String var0) {
      String var1 = l.B().u();
      a(var0, var1);
   }

   public static String b(String var0) {
      var0 = f.a(var0, "v", "");
      var0 = var0.trim();
      var0 = f.a(var0, "a", "");
      var0 = f.a(var0, "b", "");
      var0 = f.a(var0, "c", "");
      var0 = f.a(var0, "d", "");
      var0 = f.a(var0, "e", "");
      var0 = f.a(var0, "f", "");
      var0 = f.a(var0, "g", "");
      var0 = f.a(var0, "h1", "");
      var0 = f.a(var0, "h2", "");
      var0 = f.a(var0, "h3", "");
      var0 = f.a(var0, "h4", "");
      return var0;
   }

   public static void a(String var0, String var1) {
      var1 = b(var1);
      var0 = b(var0);
      String var2 = var0;
      String var3 = var1;

      try {
         int var4 = 1000;
         int var5 = 1000;
         String[] var6;
         if(var1.contains("p")) {
            var6 = al.b(var1, "p");

            try {
               var4 = Integer.valueOf(var6[1]).intValue();
            } catch (NumberFormatException var15) {
               throw new bo("Unexpected min version:" + var2 + " (Bad build number)", var15);
            }

            var1 = var6[0];
         }

         if(var0.contains("p")) {
            var6 = al.b(var0, "p");

            try {
               var5 = Integer.valueOf(var6[1]).intValue();
            } catch (NumberFormatException var14) {
               throw new bo("Unexpected min version:" + var2 + "(Bad build number)", var14);
            }

            var0 = var6[0];
         }

         int var7;
         int var8;
         int var9;
         int var10;
         int var11;
         int var17;
         try {
            var17 = a(var1, 0);
            var7 = a(var0, 0);
            var8 = a(var1, 1);
            var9 = a(var0, 1);
            var10 = a(var1, 2);
            var11 = a(var0, 2);
         } catch (bo var13) {
            throw new bo("Requires version: " + var2 + " or higher. " + var13.getMessage(), var13);
         }

         if(var7 < 1) {
            throw new bo("Min version cannot be less than v1.10");
         } else if(var7 > var17) {
            throw new bo("Requires version: " + var2 + " or higher. (You have: " + var3 + ")");
         } else if(var17 <= var7) {
            if(var9 < 10 && var7 == 1) {
               throw new bo("Min version cannot be less than v1.10");
            } else if(var9 > var8) {
               throw new bo("Requires version: " + var2 + " or higher. (You have: " + var3 + ")");
            } else if(var8 <= var9) {
               if(var11 > var10) {
                  throw new bo("Requires version: " + var2 + " or higher. (You have: " + var3 + ")");
               } else if(var10 <= var11) {
                  if(var5 > var4) {
                     throw new bo("Requires newer build: " + var2 + " or higher. (You have: " + var3 + ")");
                  }
               }
            }
         }
      } catch (RuntimeException var16) {
         throw new bo("Requires version: " + var0 + " or higher." + var16.getMessage(), var16);
      }
   }

   public void a() {
      this.k();
      this.f();
   }

   public int a(boolean var1) {
      int var2 = 0;
      Iterator var3 = this.e.iterator();

      while(var3.hasNext()) {
         b var4 = (b)var3.next();
         if(!var4.f && !var4.D && (!var1 || var4.R == null)) {
            ++var2;
         }
      }

      return var2;
   }

   public int b() {
      int var1 = 0;
      Iterator var2 = this.e.iterator();

      while(var2.hasNext()) {
         b var3 = (b)var2.next();
         if(!var3.f && var3.R != null) {
            ++var1;
         }
      }

      return var1;
   }

   public int c() {
      int var1 = 0;
      Iterator var2 = this.e.iterator();

      while(var2.hasNext()) {
         b var3 = (b)var2.next();
         if(!var3.z) {
            ++var1;
         }
      }

      return var1;
   }

   public void d() {
      b var2;
      for(Iterator var1 = this.e.iterator(); var1.hasNext(); var2.h = false) {
         var2 = (b)var1.next();
         var2.g = var2.f;
      }

   }

   public void e() {
      l var1 = l.B();
      String var2 = "";

      b var4;
      String var5;
      String var6;
      for(Iterator var3 = this.e.iterator(); var3.hasNext(); var2 = var2 + var5 + "|" + var4.e + "|" + var6) {
         var4 = (b)var3.next();
         var5 = var4.c;
         var5 = var5.replace(",", " ");
         var5 = var5.replace("|", " ");
         if(var5.length() > 15) {
            var5 = var5.substring(12) + "...";
         }

         if(var2.length() != 0) {
            var2 = var2 + ",";
         }

         var6 = var4.f?"disabled":"enabled";
      }

      var1.bQ.modSettingsVersion = 1;
      var1.bQ.modSettings = var2;
   }

   public void f() {
      l.e("Loading mod selection");
      l var1 = l.B();
      String var2 = var1.bQ.modSettings;
      String[] var3 = var2.split(",");
      String[] var4 = var3;
      int var5 = var3.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String var7 = var4[var6];
         String[] var8 = var7.split("\\|");
         if(var8.length != 3) {
            l.e("loadSelection: wrong count (" + var8.length + "):" + var7);
         } else {
            String var9 = var8[0];
            String var10 = var8[1];
            String var11 = var8[2];
            boolean var12;
            if(var11.equals("enabled")) {
               var12 = false;
            } else {
               if(!var11.equals("disabled")) {
                  l.e("loadSelection: Unknown option:" + var7);
                  continue;
               }

               var12 = true;
            }

            b var13 = this.c(var10);
            if(var13 == null) {
               l.e("loadSelection: Did not find mod in settings:" + var9);
            } else {
               var13.f = var12;
               var13.i = true;
            }
         }
      }

   }

   public b c(String var1) {
      Iterator var2 = this.e.iterator();

      b var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (b)var2.next();
      } while(!var3.e.equals(var1));

      return var3;
   }

   public int d(String var1) {
      if(var1 == null) {
         return 0;
      } else {
         int var2 = 0;
         Iterator var3 = this.e.iterator();

         while(var3.hasNext()) {
            b var4 = (b)var3.next();
            if(var1.equals(var4.c())) {
               ++var2;
            }
         }

         return var2;
      }
   }

   public b a(int var1) {
      Iterator var2 = this.e.iterator();

      b var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (b)var2.next();
      } while(var3.L != var1);

      return var3;
   }

   public void g() {
      b var2;
      for(Iterator var1 = this.e.iterator(); var1.hasNext(); var2.f = true) {
         var2 = (b)var1.next();
      }

   }

   public int h() {
      int var1 = 0;
      Iterator var2 = this.e.iterator();

      while(var2.hasNext()) {
         b var3 = (b)var2.next();
         if(!var3.f || var3.D) {
            ++var1;
         }
      }

      return var1;
   }

   public b e(String var1) {
      Iterator var2 = this.e.iterator();

      b var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (b)var2.next();
      } while(!var3.d.equals(var1));

      return var3;
   }

   public b f(String var1) {
      Iterator var2 = this.e.iterator();

      b var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (b)var2.next();
      } while(!var3.a().equals(var1));

      return var3;
   }

   public b a(String var1, String var2, String var3, String var4, boolean var5, boolean var6, boolean var7, int var8) {
      b var9 = this.c(var4);
      if(var9 == null) {
         var9 = new b();
         var9.c = var1;
         var9.d = var2;
         var9.e = var4;
         var9.f = !var5;
      }

      if(var9.q == null && var3 != null) {
         var9.q = var3;
         var9.p = var9.q;
         var9.n();
         if(var9.q != null && var9.q.toLowerCase(Locale.ROOT).contains("rwmod")) {
            var9.j = true;
         }
      }

      var9.x = var8;
      var9.l = true;
      var9.y = var6;
      var9.z = var7;
      if(!var9.z) {
         var9.o = "Storage: " + com.corrodinggames.rts.gameFramework.e.a.d(var9.q);
      }

      var9.r();
      Object var10 = this.d;
      synchronized(this.d) {
         if(!this.e.contains(var9)) {
            ArrayList var11 = new ArrayList();
            var11.addAll(this.e);
            var11.add(var9);
            Collections.sort(var11);
            this.e = var11;
         }

         return var9;
      }
   }

   public void a(b var1) {
      Object var2 = this.d;
      synchronized(this.d) {
         ArrayList var3 = new ArrayList();
         var3.addAll(this.e);
         var3.remove(var1);
         this.e = var3;
      }
   }

   public void a(String var1, boolean var2, boolean var3) {
      l.e("loading mod custom units at:" + var1);
      String[] var4 = com.corrodinggames.rts.gameFramework.e.a.h(var1);
      if(var4 == null) {
         l.b("getAllModList: ERROR");
         l.b("getAllModList: Failed to load:" + var1);
      } else {
         String[] var5 = var4;
         int var6 = var4.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            String var8 = var5[var7];
            String var9 = var1 + "/" + var8;
            if(com.corrodinggames.rts.gameFramework.e.a.f(var9) || var8.endsWith(".ini")) {
               String var10 = f.e(var8);
               String var11 = var8;
               if(var8.contains("/")) {
                  var11 = var8.substring(var8.lastIndexOf("/") + 1);
               }

               boolean var12 = false;
               this.a(var11, var8, var9, var10, var2, var12, var3, 0);
            }
         }

      }
   }

   public ArrayList i() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this.e.iterator();

      while(var2.hasNext()) {
         b var3 = (b)var2.next();
         if(var3.m()) {
            var1.addAll(var3.q());
         }
      }

      return var1;
   }

   public ArrayList j() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this.e.iterator();

      while(var2.hasNext()) {
         b var3 = (b)var2.next();
         if(var3.m()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public ArrayList k() {
      Iterator var1 = this.e.iterator();

      while(var1.hasNext()) {
         b var2 = (b)var1.next();
         var2.l = false;
         if(var2.m) {
            var2.l = true;
         }
      }

      com.corrodinggames.rts.gameFramework.o.a var8 = com.corrodinggames.rts.gameFramework.o.a.a();
      if(var8 != null) {
         var8.l();
      } else {
         l.e("getAllModList: SteamEngine==null");
      }

      String var9 = ag.m();
      if(!com.corrodinggames.rts.gameFramework.e.a.f(var9)) {
         l.e("Modded Custom \'" + var9 + "\' directory not found");
      } else {
         boolean var3 = false;
         this.a(var9, true, var3);
      }

      String var10 = ag.k();
      if(!com.corrodinggames.rts.gameFramework.e.a.f(var10)) {
         l.e("Modded Custom \'" + var10 + "\' directory not found");
      } else {
         boolean var4 = true;
         this.a(var10, false, var4);
      }

      String var11 = ag.l();
      if(!com.corrodinggames.rts.gameFramework.e.a.f(var11)) {
         l.e("Modded Custom \'" + var11 + "\' directory not found");
      } else {
         boolean var5 = true;
         this.a(var11, true, var5);
      }

      Iterator var12 = this.e.iterator();

      b var6;
      while(var12.hasNext()) {
         var6 = (b)var12.next();
         if(!var6.l) {
            l.e("Removing mod no longer found on system: " + var6.a());
            this.a(var6);
         }
      }

      l.e("========= Mods ===========");
      l.e("Number of mods:" + this.e.size());
      var12 = this.e.iterator();

      while(var12.hasNext()) {
         var6 = (b)var12.next();
         l.e("Mod: \'" + var6.a());
      }

      l.e("================================");
      l var13 = l.B();
      b var7;
      Iterator var14;
      if(var13.bQ.lastModCount != -1 && var13.bQ.modSettingsVersion >= 1) {
         if(this.e.size() > var13.bQ.lastModCount + 4) {
            l.e("Too many new mods found, not enabling new mods");
            l.e("Number of mods:" + this.e.size() + " vs " + var13.bQ.lastModCount);
            var14 = this.e.iterator();

            while(var14.hasNext()) {
               var7 = (b)var14.next();
               if(!var7.i) {
                  var7.f = true;
               }
            }

            this.e();
            var13.bQ.save();
         }
      } else {
         l.e("Disabling all new mods for first/new load");

         for(var14 = this.e.iterator(); var14.hasNext(); var7.f = true) {
            var7 = (b)var14.next();
         }

         this.e();
         var13.bQ.save();
      }

      var13.bQ.lastModCount = this.e.size();
      return this.e;
   }

   public void l() {
      l var1 = l.B();

      try {
         var1.br = true;
         var1.e();
         this.a(false, false);
      } finally {
         var1.br = false;
      }

      var1.x();
   }

   public void a(boolean var1, boolean var2) {
      l var3 = l.B();
      ae.b();
      b var5;
      if(!var2) {
         for(Iterator var4 = this.e.iterator(); var4.hasNext(); var5.w = 0) {
            var5 = (b)var4.next();
            if(var5.R != null) {
               l.e("re-enabling mod: " + var5.a());
            }

            var5.R = null;
            var5.V.clear();
            var5.S = null;
            var5.U.clear();
            var5.C = false;
            var5.D = false;
            var5.E = 0;
            var5.F = 0;
            var5.G = 0L;
            var5.H = 0L;
            var5.I = 0;
            var5.J = 0;
         }
      }

      this.k();
      ArrayList var8 = new ArrayList(com.corrodinggames.rts.game.units.custom.l.d);
      if(!var2) {
         ag.h();
      } else {
         ag.b();
      }

      if(var1) {
         int var9 = 0;
         Iterator var6 = var8.iterator();

         while(var6.hasNext()) {
            com.corrodinggames.rts.game.units.custom.l var7 = (com.corrodinggames.rts.game.units.custom.l)var6.next();
            if(var7.J != null && !var7.J.f && var7.J.R != null && com.corrodinggames.rts.game.units.custom.l.a(var7) == null) {
               l.e("Was missing: " + var7.M);
               com.corrodinggames.rts.game.units.custom.l.d.add(var7);
               ++var9;
            }
         }

         if(var9 > 0) {
            ag.e();
         }
      }

      com.corrodinggames.rts.game.units.custom.l.A();
      n.P();
      g.K();
   }

   public void m() {
      l var1 = l.B();
      if(var1.dH != null) {
         var1.dH.d();
      } else {
         l.e("No active callbacks");
      }

   }

   public String[] a(String[] var1, String var2) {
      l.e("addExtraMapsForPath: " + var2);
      ArrayList var3 = new ArrayList();
      String[] var4;
      int var6;
      if(var1 != null) {
         var4 = var1;
         int var5 = var1.length;

         for(var6 = 0; var6 < var5; ++var6) {
            String var7 = var4[var6];
            var3.add(var7);
         }
      }

      if(l.at() && "/SD/rusted_warfare_maps".equals(var2)) {
         var4 = com.corrodinggames.rts.gameFramework.e.a.a("/SD/rustedWarfare/maps", true);
         if(var4 != null) {
            String[] var10 = var4;
            var6 = var4.length;

            for(int var12 = 0; var12 < var6; ++var12) {
               String var8 = var10[var12];
               var3.add("NEW_PATH|maps2/" + var8);
            }
         }
      }

      Iterator var9 = this.g(var2).iterator();

      while(var9.hasNext()) {
         c var11 = (c)var9.next();
         var3.add("MOD|" + var11.c.e + "/" + var11.b);
      }

      return var1 == null && var3.size() == 0?null:(String[])var3.toArray(new String[0]);
   }

   public ArrayList g(String var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.f.iterator();

      while(var3.hasNext()) {
         c var4 = (c)var3.next();
         boolean var5 = false;
         if(var1.startsWith("mod/") && var1.startsWith("mod/" + var4.c.e)) {
            var5 = true;
         }

         if(!var4.c.f && var1.startsWith("/SD/rusted_warfare_maps")) {
            var5 = true;
         }

         if(var5) {
            l.e("Adding extra map:" + var4.a);
            var2.add(var4);
         }
      }

      return var2;
   }

   public void n() {
      this.f.clear();
   }

   public void a(String var1, b var2) {
      c var3 = new c(this);
      var3.a = var1;
      var3.c = var2;
      if(var2.q == null) {
         l.a("Skipping:" + var1 + " as mod sourceFolder is null");
      } else {
         String var4 = var1;
         String var5 = var2.q;
         if(var1.startsWith(var5)) {
            var4 = var1.substring(var5.length());
         } else {
            String var6 = com.corrodinggames.rts.gameFramework.e.a.o(var1);
            if(var6.startsWith(var5)) {
               var4 = var6.substring(var5.length());
               l.e("Mod path:" + var2.q + " in map path without tag:" + var4);
            } else {
               l.a("Mod path:" + var2.q + " not in map path:" + var1);
            }
         }

         var3.b = var4;
         var2.A = true;
         ++var2.F;
         this.f.add(var3);
      }
   }

   public b h(String var1) {
      if(var1.contains("MOD|")) {
         String[] var2 = var1.split("/");
         if(var2.length >= 2) {
            for(int var3 = var2.length - 2; var3 >= 0; --var3) {
               String var4 = var2[var3];
               if(var4.startsWith("MOD|")) {
                  String var5 = var4.substring("MOD|".length());
                  b var6 = this.c(var5);
                  if(var6 == null) {
                     l.e("getLinkedModForFile: Failed to find mod with hash:" + var5);
                     return null;
                  }

                  return var6;
               }
            }
         }
      }

      return null;
   }
}
