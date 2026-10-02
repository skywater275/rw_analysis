package com.corrodinggames.rts.gameFramework.i;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.e.g;
import com.corrodinggames.rts.gameFramework.i.a;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class b implements Comparable {

   public int a;
   public static int b = 1;
   public String c;
   public String d;
   public String e;
   public boolean f;
   public boolean g;
   public boolean h;
   public boolean i;
   public boolean j;
   public long k;
   boolean l;
   public boolean m;
   public String n;
   public String o;
   public String p;
   public String q;
   public boolean r;
   public String s;
   public String t;
   public String u;
   public String v;
   public int w;
   public int x;
   public boolean y;
   public boolean z;
   public boolean A = false;
   public boolean B = true;
   public boolean C;
   public boolean D;
   public int E;
   public int F;
   public long G;
   public long H;
   public int I;
   public int J;
   public static int K = 1;
   public int L;
   public String M;
   public boolean N;
   public boolean O;
   public int P;
   ArrayList Q;
   public String R;
   public String S;
   public String T;
   public ArrayList U;
   public ArrayList V;


   public b() {
      this.L = K++;
      this.Q = new ArrayList();
      this.U = new ArrayList();
      this.V = new ArrayList();
      this.a = b++;
   }

   public String a() {
      return this.s != null?this.s:(this.t != null?this.t:this.c);
   }

   public String b() {
      String var1 = this.a();
      return f.a(var1, (int)25);
   }

   public String c() {
      String var1 = this.a();
      return f.a(var1, (int)40);
   }

   public int d() {
      return this.L;
   }

   public String e() {
      return this.f();
   }

   public String f() {
      String var1 = "";
      if(this.u != null) {
         var1 = var1 + this.u;
      }

      String var2 = "RAM:" + this.s();
      if(this.n != null) {
         var2 = var2 + " Storage: slow external unpacked";
      }

      if(l.at() && this.q != null && com.corrodinggames.rts.gameFramework.e.a.a(this.q) && !this.j) {
         var2 = var2 + " Warning: slow external storage";
      }

      var1 = var1 + "\n (" + var2 + ")";
      return var1;
   }

   public String g() {
      return this.y?this.q:com.corrodinggames.rts.gameFramework.e.a.e(this.q);
   }

   public String h() {
      return this.y?this.p:com.corrodinggames.rts.gameFramework.e.a.e(this.p);
   }

   public String i() {
      File var1 = new File(this.g());
      return var1.getAbsolutePath();
   }

   public String j() {
      return this.q;
   }

   public String k() {
      File var1 = new File(this.g());
      return var1.getCanonicalPath();
   }

   public String l() {
      String var1 = this.S;
      if(this.T != null) {
         if(var1 == null) {
            var1 = "";
         }

         var1 = var1 + this.T;
      }

      if(var1 == null && this.U.size() > 0) {
         var1 = "";
         int var2 = 0;
         int var3 = 0;

         for(Iterator var4 = this.U.iterator(); var4.hasNext(); ++var3) {
            String var5 = (String)var4.next();
            if(var3 <= 2) {
               if(var1 == null) {
                  var1 = var5;
               } else {
                  var1 = var1 + "\n" + var5;
               }
            } else {
               ++var2;
            }
         }

         if(var2 > 0) {
            var1 = var1 + "\n" + var2 + " more warnings...";
         }
      }

      if((!this.C || var1 != null) && !this.D && var1 == null) {
         if(var1 == null) {
            var1 = "";
         }

         var1 = var1 + "Not yet loaded, reload needed";
      }

      return var1;
   }

   public boolean m() {
      return !this.f && this.R == null;
   }

   public void a(String var1) {
      l.b("Adding error for mod: " + this.b() + (this.m()?"":"(disabled)") + ": " + var1);
      if(this.R == null) {
         if(!this.f) {
            l var2 = l.B();
            String var3 = var1;
            if(var1 != null && (!var1.contains(this.a()) || var1.contains(this.b()))) {
               var3 = "Error loading mod \'" + this.b() + "\': " + var1;
            }

            int var4 = var2.bZ.d(this.b());
            if(var4 > 1) {
               var3 = var3 + " (NOTE: You have " + var4 + " mods with the same title: \'" + this.b() + "\' this might make debugging tricky)";
            }

            var2.i(var3);
         }

         l.e("Disabling mod due to error: " + this.b() + " path:" + this.i());
         this.R = var1;
      }

      this.V.add(var1);
   }

   public void b(String var1) {
      if(!this.U.contains(var1)) {
         this.U.add(var1);
      }
   }

   public String a(String var1, int var2) {
      if(var2 > 4) {
         return null;
      } else {
         String[] var3 = com.corrodinggames.rts.gameFramework.e.a.h(var1);
         if(var3 == null) {
            return null;
         } else {
            String[] var4 = var3;
            int var5 = var3.length;

            int var6;
            String var7;
            for(var6 = 0; var6 < var5; ++var6) {
               var7 = var4[var6];
               if(var7.equalsIgnoreCase("mod-info.txt")) {
                  return var1 + "/" + "mod-info.txt";
               }
            }

            if(var3.length > 5) {
               return null;
            } else {
               var4 = var3;
               var5 = var3.length;

               for(var6 = 0; var6 < var5; ++var6) {
                  var7 = var4[var6];
                  String var8 = var1 + "/" + var7;
                  if(com.corrodinggames.rts.gameFramework.e.a.f(var8)) {
                     String var9 = this.a(var8, var2 + 1);
                     if(var9 != null) {
                        return var9;
                     }
                  }
               }

               return null;
            }
         }
      }
   }

   public void n() {
      if(l.B().d()) {
         l.e("SAFE MODE: skipping setSourceFolder");
      } else {
         String var1 = this.q + "/" + "mod-info.txt";
         if(!g.f("mods-info", var1)) {
            String var2 = this.q;
            if(var2 == null) {
               l.e("setSourceFolder: sourceFolder==null");
               return;
            }

            String[] var3 = g.d("mods-dir-search", var2);
            if(var3 != null && var3.length == 1) {
               String var4 = var3[0];
               String var5 = var2 + "/" + var4;
               String var6 = var5 + "/" + "mod-info.txt";
               if(com.corrodinggames.rts.gameFramework.e.a.f(var5) && com.corrodinggames.rts.gameFramework.e.a.i(var6)) {
                  l.e("Changing mod sourceFolder to:" + var5);
                  this.q = var5;
               }
            }
         }

      }
   }

   public ab o() {
      if(this.q == null) {
         l.e("No source yet for mod: " + this.c);
         return null;
      } else {
         String var1 = this.q + "/" + "mod-info.txt";

         Object var2;
         try {
            if(this.j) {
               var2 = g.e("mods-info", var1);
            } else {
               var2 = com.corrodinggames.rts.gameFramework.e.a.k(var1);
            }
         } catch (Exception var7) {
            l.e("Error loading mod info for: " + this.c + " at " + var1);
            var7.printStackTrace();
            this.b("Error loading mod-info.txt: " + var7.getMessage());
            return null;
         }

         if(var2 == null) {
            String var3 = this.a(this.q, 1);
            if(var3 != null) {
               j var4 = com.corrodinggames.rts.gameFramework.e.a.k(var1);
               if(var4 != null) {
                  l.a("mod-info.txt cache seems to be invalid for: " + var1);
                  g.c("mods-info", var1);
                  var2 = var4;
               } else {
                  String var5 = "No mod info at " + com.corrodinggames.rts.gameFramework.e.a.d(var1) + " but found one nested at: " + com.corrodinggames.rts.gameFramework.e.a.d(var3) + " (Hint: This mod might have been extracted with an extra folder)";
                  this.a(var5);
               }
            }
         }

         if(var2 == null) {
            l.e("No mod info for: " + this.c + " at " + var1);
            return null;
         } else {
            try {
               ab var8 = new ab((InputStream)var2, var1);
               return var8;
            } catch (IOException var6) {
               l.e("Error loading mod info for: " + this.c + " at " + var1);
               var6.printStackTrace();
               this.b("Error loading mod-info.txt: " + var6.getMessage());
               return null;
            }
         }
      }
   }

   public String p() {
      String var1 = this.c("thumbnail");
      if(var1 != null) {
         var1 = this.i() + "/" + var1;
         return var1;
      } else {
         return null;
      }
   }

   public ArrayList q() {
      return this.Q;
   }

   public String c(String var1) {
      String var2 = "mod";
      ab var3 = this.o();
      return var3 == null?null:var3.b(var2, var1, (String)null);
   }

   public void r() {
      if(l.B().d()) {
         l.e("SAFE MODE: refreshData: Skipping mod read");
         this.u = "<< SAFE MODE ACTIVE: MOD DATA SKIPPED. RESTART IN NORMAL MODE. >>";
      } else {
         ab var1 = this.o();
         String var5;
         if(var1 != null) {
            String var3 = "mod";
            String var4 = "music";
            this.s = var1.b(var3, "title", (String)null);
            this.u = var1.b(var3, "description", (String)null);
            if(this.u != null && this.u.contains("\\n")) {
               this.u = this.u.replace("\\n", "\n");
            }

            this.v = var1.b(var3, "minVersion", (String)null);
            if(this.v != null && !this.v.trim().equals("")) {
               try {
                  a.a(this.v);
               } catch (bo var14) {
                  String var6 = var14.getMessage();
                  this.a(var6);
               }
            }

            this.M = var1.b(var4, "sourceFolder", (String)null);
            this.N = var1.a(var4, "whenUsingUnitsFromThisMod_playExclusively", Boolean.valueOf(false)).booleanValue();
            this.O = var1.a(var4, "addToNormalPlaylist", Boolean.valueOf(false)).booleanValue();
            if(this.M != null && this.m()) {
               l.e("Loading music for: " + this.a());
               var5 = f.b(this.q, this.M);
               String[] var17 = g.d("mods-dir-music", var5);
               if(var17 == null) {
                  this.b("Could not read target music folder: " + com.corrodinggames.rts.gameFramework.e.a.e(var5));
               } else {
                  ArrayList var7 = new ArrayList();
                  String[] var8 = var17;
                  int var9 = var17.length;

                  for(int var10 = 0; var10 < var9; ++var10) {
                     String var11 = var8[var10];
                     if(var11.toLowerCase().endsWith(".ogg")) {
                        String var12 = f.b(var5, var11);
                        if(!this.Q.contains(var12)) {
                           l.e("Found music track: " + var11);
                        }

                        var7.add(var12);
                     }
                  }

                  this.Q = var7;
                  if(this.Q.size() == 0) {
                     this.b("Could not find any .ogg files in music folder: " + com.corrodinggames.rts.gameFramework.e.a.e(var5));
                  }
               }
            }

            this.r = true;
         }

         String var2 = this.w();
         File var15 = new File(var2);
         if(var15.exists() && !var15.isDirectory()) {
            ab var16 = null;

            try {
               var16 = new ab(var2);
            } catch (IOException var13) {
               var13.printStackTrace();
               this.b("IO error reading: " + com.corrodinggames.rts.gameFramework.e.a.e(var2));
            }

            if(var16 != null) {
               var5 = "steam";
               this.k = var16.a(var5, "id", 0L);
            }
         }

      }
   }

   private String w() {
      return this.g() + "/steam.dat";
   }

   public boolean a(long var1) {
      this.k = var1;
      String var3 = this.w();

      try {
         PrintWriter var4 = new PrintWriter(var3);
         var4.println("[steam]");
         var4.println("id: " + var1);
         var4.close();
         return true;
      } catch (FileNotFoundException var6) {
         var6.printStackTrace();
         l.B().i("IO error: Failed to save workshop id for mod at: " + var3);
         return false;
      }
   }

   public String s() {
      String var1 = "";
      var1 = var1 + String.format("%.2f", new Object[]{Float.valueOf((float)((double)(this.G + this.H) / 1000.0D / 1000.0D))}) + " mb" + (this.C?" - disabled":"");
      return var1;
   }

   public void t() {
      l.e("Mod: \'" + this.a() + "\' - Memory use:" + this.s() + " " + (this.m()?"":" (disabled)"));
   }

   public boolean u() {
      l.e("Trying to delete mod: \'" + this.a() + "\'");
      String var1 = this.h();
      l.e("sourceFolder: \'" + var1 + "\'");
      if(!this.v()) {
         l.e("Mod: \'" + this.a() + "\' - Cannot be deleted");
         return false;
      } else {
         File var2 = new File(var1);
         if(!com.corrodinggames.rts.gameFramework.e.a.i(var2.getAbsolutePath())) {
            l.e("Mod: \'" + this.a() + "\' - cannot delete: Not a file");
            return false;
         } else {
            boolean var3 = com.corrodinggames.rts.gameFramework.e.a.b(var2);
            l.e("Delete result: " + var3);
            return var3;
         }
      }
   }

   public boolean v() {
      return this.z?false:(l.aZ && this.j?true:l.at() && this.j);
   }

   public int a(b var1) {
      if(var1 == null) {
         return 0;
      } else {
         int var3 = this.x;
         int var4 = var1.x;
         if(var3 != var4) {
            return var3 - var4;
         } else {
            String var5 = this.a();
            String var6 = var1.a();
            if(var5 == null) {
               var5 = "";
            }

            if(var6 == null) {
               var6 = "";
            }

            return var5.compareTo(var6);
         }
      }
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((b)var1);
   }

}
