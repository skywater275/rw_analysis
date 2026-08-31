package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.e.b;
import com.corrodinggames.rts.game.units.custom.e.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class a {

   public boolean a;
   protected String b;
   protected bb c;
   protected bb d;
   protected boolean e;
   protected boolean f;
   protected bb g;
   protected bb h;
   public a i;
   public boolean j;
   public boolean k;
   public boolean l = true;
   Integer m;
   public boolean n;
   public boolean o;
   public boolean p;
   public b q;
   boolean r;
   float s;
   protected boolean t;
   protected boolean u;
   a v;
   public boolean w;
   public float x;
   public com.corrodinggames.rts.gameFramework.m.e y;
   public boolean z;
   static ArrayList A = new ArrayList();
   static ArrayList B = new ArrayList();
   public static ArrayList C = new ArrayList();
   public static final a D = a((a)(new com.corrodinggames.rts.game.units.custom.e.a.c()));
   public static final a E = a((a)(new com.corrodinggames.rts.game.units.custom.e.a.d()));
   public static final a F = a((a)(new com.corrodinggames.rts.game.units.custom.e.a.b()));
   public static final a G = a((a)(new com.corrodinggames.rts.game.units.custom.e.a.e()));
   public static final a H = a((a)(new com.corrodinggames.rts.game.units.custom.e.a.f()));


   public strictfp boolean a() {
      return this.r;
   }

   public strictfp float b() {
      return this.s;
   }

   public strictfp boolean c() {
      return this.u;
   }

   public strictfp boolean d() {
      return this.t;
   }

   public static strictfp void e() {
      Iterator var0 = A.iterator();

      while(var0.hasNext()) {
         a var1 = (a)var0.next();
         var1.g();
      }

      ArrayList var3 = new ArrayList();
      Iterator var4 = A.iterator();

      while(var4.hasNext()) {
         a var2 = (a)var4.next();
         if(var2.a) {
            var3.add(var2);
         }
      }

      B = var3;
   }

   public static strictfp ArrayList f() {
      return B;
   }

   public strictfp void g() {
      if(this.u) {
         this.a = true;
      } else {
         d var1 = null;
         Iterator var2 = l.d.iterator();

         while(var2.hasNext()) {
            l var3 = (l)var2.next();
            d var4 = var3.a(this);
            if(var4 != null && (var1 == null || var1.c < var4.c)) {
               var1 = var4;
            }
         }

         this.a = var1 != null;
         if(var1 != null) {
            this.c = var1.g;
            this.d = var1.h;
            this.e = var1.i;
            this.f = var1.j;
            this.m = var1.d;
            this.n = var1.e;
            this.o = var1.o;
            this.p = var1.p;
            this.q = var1.r;
            this.g = var1.t;
            this.h = var1.u;
            this.i = var1.w;
            this.j = var1.y;
            this.l = var1.q;
            this.k = var1.x;
            this.r = var1.l;
            this.s = var1.m;
            this.v = var1.A;
            this.w = var1.k;
            this.x = (float)var1.s;
            this.y = var1.B;
            this.z = var1.C;
         }

      }
   }

   public strictfp Integer h() {
      return this.m;
   }

   public strictfp String i() {
      return this.c == null?this.b:this.c.b();
   }

   public strictfp String j() {
      return this.d != null?this.d.b():this.i();
   }

   public strictfp String a(double var1, boolean var3) {
      String var4;
      if(this.o) {
         var4 = "" + (int)var1;
      } else {
         var4 = com.corrodinggames.rts.gameFramework.f.c(var1);
      }

      var4 = a(var4, this.q);
      return this.a(var3) + var4 + this.b(var3);
   }

   public static strictfp String a(String var0, b var1) {
      if(var1 == b.a) {
         return var0;
      } else {
         com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
         if(var2.bQ.disableDigitGrouping) {
            return var0;
         } else {
            String var3 = var0;
            String var4 = "";
            String var5 = "";
            int var6 = var0.indexOf(".");
            if(var6 != -1) {
               var5 = var0.substring(var6);
               var3 = var0.substring(0, var6);
            }

            if(var3.length() <= 3) {
               return var0;
            } else {
               String var7;
               if(var1 == b.b) {
                  var7 = " ";
               } else {
                  if(var1 != b.c) {
                     throw new RuntimeException("Unhandled grouping style: " + var1);
                  }

                  var7 = ",";
               }

               StringBuilder var8 = new StringBuilder();
               int var9 = var3.length() % 3;
               if(var9 != 0) {
                  var8.append(var3.substring(0, var9));
               }

               for(int var10 = var9; var10 < var3.length(); var10 += 3) {
                  if(var10 != 0) {
                     var8.append(var7);
                  }

                  var8.append(var3.substring(var10, var10 + 3));
               }

               if(var5 == "") {
                  return var8.toString();
               } else {
                  return var8.toString() + var5;
               }
            }
         }
      }
   }

   public static strictfp String a(long var0, b var2) {
      if(var2 == b.a) {
         return "" + var0;
      } else if(var2 == b.b) {
         return String.format(Locale.US, "%,d", new Object[]{Long.valueOf(var0)}).replace(",", " ");
      } else if(var2 == b.c) {
         return String.format(Locale.US, "%,d", new Object[]{Long.valueOf(var0)});
      } else {
         throw new RuntimeException("Unhandled grouping style: " + var2);
      }
   }

   public strictfp String a(boolean var1) {
      return this.g != null?this.g.b():(var1 && this.e?"":this.i() + ": ");
   }

   public strictfp String b(boolean var1) {
      return this.h != null?this.h.b():"";
   }

   public strictfp com.corrodinggames.rts.gameFramework.m.e k() {
      return this.y;
   }

   protected strictfp a() {
      this.q = b.a;
   }

   public static strictfp a a(String var0) {
      var0 = var0.toLowerCase(Locale.ENGLISH);
      Iterator var1 = C.iterator();

      a var2;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         var2 = (a)var1.next();
      } while(!var2.b.equalsIgnoreCase(var0));

      return var2;
   }

   public static strictfp a a(a var0) {
      Iterator var1 = A.iterator();

      a var2;
      do {
         if(!var1.hasNext()) {
            A.add(var0);
            C.add(var0);
            return var0;
         }

         var2 = (a)var1.next();
      } while(!var2.b.equals(var0.b));

      throw new RuntimeException("Built in resource already exists:" + var0.b);
   }

   public static strictfp a a(String var0, boolean var1, boolean var2) {
      Iterator var3 = A.iterator();

      a var4;
      do {
         if(!var3.hasNext()) {
            a var5 = new a();
            var5.b = var0;
            var5.u = var1;
            var5.t = var2;
            A.add(var5);
            return var5;
         }

         var4 = (a)var3.next();
      } while(!var4.b.equals(var0));

      return var4;
   }

   public static strictfp a b(String var0) {
      Iterator var1 = A.iterator();

      a var2;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         var2 = (a)var1.next();
      } while(!var2.b.equals(var0));

      return var2;
   }

   private strictfp String a(double var1) {
      String var3 = com.corrodinggames.rts.gameFramework.f.a(var1, 1);
      return a(var3, this.q);
   }

   public strictfp String a(double var1, boolean var3, boolean var4) {
      String var5;
      if(var4 && this.f) {
         var5 = "";
      } else {
         var5 = this.j() + ": ";
      }

      if(this == D) {
         var5 = "$";
      }

      return var3?(var1 > 0.0D?"+" + var5 + this.a(var1):"-" + var5 + this.a(-var1)):(var1 > 0.0D?var5 + this.a(var1):var5 + this.a(var1));
   }

   public strictfp String toString() {
      return "resource(" + this.b + ")";
   }

   public strictfp double a(am var1) {
      return this.t?var1.bX.c(this):var1.a(this);
   }

   public strictfp void a(am var1, double var2) {
      if(this.t) {
         var1.bX.V().a(this, var2);
      } else {
         var1.df().a(this, var2);
      }

   }

   public strictfp void b(am var1, double var2) {
      if(this.t) {
         var1.bX.V().b(this, var2);
      } else {
         var1.df().b(this, var2);
      }

   }

}
