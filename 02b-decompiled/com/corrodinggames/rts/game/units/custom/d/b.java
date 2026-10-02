package com.corrodinggames.rts.game.units.custom.d;

import android.graphics.Color;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.d.a;
import com.corrodinggames.rts.game.units.custom.e.e;
import com.corrodinggames.rts.game.units.custom.e.f;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.f.an;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class b extends a implements Comparable {

   private static final f m = (new f()).a();
   public static final b a = a(0);
   public int b;
   public float c;
   public float d;
   public float e;
   public int f;
   public int g;
   public int h;
   public int i;
   public int j;
   public f k;
   static final int l = Color.a(255, 0, 100, 0);


   public b() {
      this.k = m;
   }

   public int a() {
      return this.b;
   }

   public int b() {
      if(this.k == m) {
         return this.b;
      } else {
         int var1 = this.b;
         int var2 = this.k.b.a;
         Object[] var3 = this.k.b.a();

         for(int var4 = 0; var4 < var2; ++var4) {
            e var5 = (e)var3[var4];
            if(var5.b > 0.0D) {
               float var6 = var5.a.b();
               if(var6 != 0.0F) {
                  var1 += (int)((double)var6 * var5.b);
               }
            }
         }

         return var1;
      }
   }

   public static b a(b var0, b var1) {
      b var2 = new b();
      var2.b = var0.b + var1.b;
      var2.c = var0.c + var1.c;
      var2.d = var0.d + var1.d;
      var2.e = var0.e + var1.e;
      var2.f = var0.f + var1.f;
      if(!var0.k.c() || !var1.k.c()) {
         var2.k = f.a(var0.k, var1.k);
      }

      return var2;
   }

   public static b a(b var0, float var1) {
      b var2 = new b();
      var2.b = (int)((float)var0.b * var1);
      var2.c = var0.c * var1;
      var2.d = var0.d * var1;
      var2.e = var0.e * var1;
      var2.f = (int)((float)var0.f * var1);
      if(!var0.k.c()) {
         var2.k = f.b(var0.k, (double)var1);
      }

      return var2;
   }

   public static b a(int var0) {
      b var1 = new b();
      var1.b = var0;
      return var1;
   }

   public static b a(l var0, ab var1, String var2, String var3, boolean var4) {
      String var5 = var1.b(var2, var3, (String)null);
      if(var5 == null && !var4) {
         throw new RuntimeException("Could not find " + var3 + " in configuration file under:" + var2);
      } else {
         try {
            b var6 = b(var0, var5);
            return var6;
         } catch (bo var7) {
            throw new bo("[" + var2 + "]" + var3 + ": " + var7.getMessage());
         }
      }
   }

   public static b a(l var0, ab var1, String var2, String var3, b var4) {
      String var5 = var1.b(var2, var3, (String)null);
      if(var5 == null) {
         return var4;
      } else {
         try {
            b var6 = b(var0, var5);
            return var6;
         } catch (bo var7) {
            throw new bo("[" + var2 + "]" + var3 + ": " + var7.getMessage());
         }
      }
   }

   public static b b(l var0, ab var1, String var2, String var3, b var4) {
      String var5 = var1.b(var2, var3, (String)null);
      if(var5 == null) {
         return var4;
      } else {
         try {
            b var6 = a(var0, var5);
            return var6;
         } catch (bo var7) {
            throw new bo("[" + var2 + "]" + var3 + ": " + var7.getMessage());
         }
      }
   }

   public static void b(int var0) {
      if(var0 < 0 || var0 > 31) {
         throw new bo("Flag id must be between 0-31 (is:" + var0 + ")");
      }
   }

   public static int a(int var0, String var1) {
      if(!var1.contains("-")) {
         int var6 = Integer.parseInt(var1);
         b(var6);
         var0 |= 1 << var6;
         return var0;
      } else {
         String[] var2 = com.corrodinggames.rts.gameFramework.f.c(var1, '-');
         if(var2.length != 2) {
            throw new bo("Unexpected flag id: " + var1);
         } else {
            int var3 = Integer.parseInt(var2[0]);
            int var4 = Integer.parseInt(var2[1]);
            b(var3);
            b(var4);
            if(var4 < var3) {
               throw new bo("end<start in flag id: " + var1);
            } else {
               for(int var5 = var3; var5 <= var4; ++var5) {
                  var0 |= 1 << var5;
               }

               return var0;
            }
         }
      }
   }

   public static b a(l var0, String var1) {
      b var2 = b(var0, var1);
      if(var2 != null && var2.f != 0) {
         throw new bo("Ammo not supported on streaming price:" + var1);
      } else {
         return var2;
      }
   }

   public static b b(l var0, String var1) {
      if(var1 == null) {
         return a;
      } else {
         b var2 = new b();
         String[] var3 = var1.split(",|\\|");
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            var6 = var6.trim();
            if(!var6.equals("")) {
               String[] var9 = var6.split("=|:");
               String var7;
               String var8;
               if(var9.length == 1) {
                  var7 = "credits";
                  var8 = var9[0];
               } else {
                  if(var9.length != 2) {
                     throw new bo("Unknown price format:" + var1);
                  }

                  var7 = var9[0].trim();
                  var8 = var9[1].trim();
               }

               boolean var10 = false;

               try {
                  int var11;
                  if(var7.equals("credits")) {
                     var10 = true;
                     var11 = Integer.parseInt(var8);
                     var2.b = var11;
                  } else {
                     float var14;
                     if(var7.equals("energy")) {
                        var14 = Float.parseFloat(var8);
                        var2.c = var14;
                     } else if(var7.equals("hp")) {
                        var14 = Float.parseFloat(var8);
                        var2.d = var14;
                     } else if(var7.equals("shield")) {
                        var14 = Float.parseFloat(var8);
                        var2.e = var14;
                     } else if(var7.equals("ammo")) {
                        var10 = true;
                        var11 = Integer.parseInt(var8);
                        var2.f = var11;
                     } else if(var7.equals("hasFlag")) {
                        var10 = true;
                        var2.i = a(var2.i, var8);
                     } else if(var7.equals("hasMissingFlag")) {
                        var10 = true;
                        var2.j = a(var2.j, var8);
                     } else if(var7.equals("setFlag")) {
                        var10 = true;
                        var2.g = a(var2.g, var8);
                     } else if(var7.equals("unsetFlag")) {
                        var10 = true;
                        var2.h = a(var2.h, var8);
                     } else {
                        com.corrodinggames.rts.game.units.custom.e.a var15 = var0.k(var7);
                        if(var15 == null) {
                           throw new bo("Unknown price type:" + var7);
                        }

                        float var16 = Float.parseFloat(var8);
                        if(var2.k == m) {
                           var2.k = new f();
                        }

                        var2.k.a(var15, (double)var16);
                     }
                  }
               } catch (NumberFormatException var13) {
                  var13.printStackTrace();
                  String var12 = "Bad price number:" + var8 + " in " + var1;
                  if(var10) {
                     var12 = var12 + " (Hint: A whole number was expected)";
                  }

                  throw new bo(var12);
               }
            }
         }

         if(var2.k != m) {
            var2.k.a();
         }

         if(!var2.d()) {
            return a;
         } else {
            return var2;
         }
      }
   }

   public int a(am var1, boolean var2) {
      int var3 = 9999;
      int var4;
      if(!var2 && this.b > 0) {
         var4 = (int)(var1.bX.o / (double)this.b);
         var3 = com.corrodinggames.rts.gameFramework.f.c(var3, var4);
      }

      if(this.c > 0.0F) {
         var4 = (int)(var1.cB / this.c);
         var3 = com.corrodinggames.rts.gameFramework.f.c(var3, var4);
      }

      if(this.d > 0.0F) {
         var4 = (int)(var1.cu / this.d);
         var3 = com.corrodinggames.rts.gameFramework.f.c(var3, var4);
      }

      if(this.e > 0.0F) {
         var4 = (int)(var1.cx / this.e);
         var3 = com.corrodinggames.rts.gameFramework.f.c(var3, var4);
      }

      if(this.f > 0) {
         var4 = var1.cE / this.f;
         var3 = com.corrodinggames.rts.gameFramework.f.c(var3, var4);
      }

      if(!this.k.c()) {
         var4 = f.a(this.k, var1);
         var3 = com.corrodinggames.rts.gameFramework.f.c(var3, var4);
      }

      if(!this.f(var1)) {
         var3 = 0;
      }

      return var3;
   }

   public boolean b(am var1, double var2) {
      return this.b > 0 && !var1.bX.a((double)this.b * var2)?false:(this.c > 0.0F && (double)var1.cB < (double)this.c * var2?false:(this.d > 0.0F && (double)var1.cu < (double)this.d * var2?false:(this.e > 0.0F && (double)var1.cx < (double)this.e * var2?false:(this.f > 0 && (double)var1.cE < (double)this.f * var2?false:(!this.f(var1)?false:this.k.c() || f.a(this.k, var1, var2))))));
   }

   public boolean b(am var1) {
      return this.b > 0 && !var1.bX.a((double)this.b)?false:(this.c > 0.0F && var1.cB < this.c?false:(this.d > 0.0F && var1.cu < this.d?false:(this.e > 0.0F && var1.cx < this.e?false:(this.f > 0 && var1.cE < this.f?false:(!this.f(var1)?false:this.k.c() || f.b(this.k, var1))))));
   }

   public boolean a(am var1, am var2) {
      boolean var3 = false;
      if(!this.k.c() && f.a(this.k, var1, var2)) {
         var3 = true;
      }

      return var3;
   }

   public static void d(am var0) {
      if(var0.cB < 0.0F) {
         var0.cB = 0.0F;
      }

      if(var0.cB > var0.bd()) {
         var0.cB = var0.bd();
      }

      if(var0.cx < 0.0F) {
         var0.cx = 0.0F;
      }

      if(var0.cx > var0.cA) {
         var0.cx = var0.cA;
      }

      if(var0.cu > var0.cv) {
         var0.cu = var0.cv;
      }

      if(var0.cE < 0) {
         var0.cE = 0;
      }

   }

   public void e(am var1) {
      if(this.h != 0) {
         var1.cF &= ~this.h;
      }

      if(this.g != 0) {
         var1.cF |= this.g;
      }

   }

   public int c(int var1) {
      if(this.h != 0) {
         var1 &= ~this.h;
      }

      if(this.g != 0) {
         var1 |= this.g;
      }

      return var1;
   }

   public static boolean a(int var0, int var1) {
      int var2 = 1 << var1;
      return (var0 & var2) != 0;
   }

   public boolean f(am var1) {
      return this.i != 0 && !b(var1.cF, this.i)?false:this.j == 0 || !c(var1.cF, this.j);
   }

   public static boolean b(int var0, int var1) {
      return (var1 & var0) == var1;
   }

   public static boolean c(int var0, int var1) {
      return (var1 & var0) != 0;
   }

   public void a(am var1) {
      var1.bX.o -= (double)this.b;
      var1.cB -= this.c;
      var1.cu -= this.d;
      var1.cx -= this.e;
      var1.cE -= this.f;
      this.e(var1);
      if(!this.k.c()) {
         f.c(this.k, var1);
      }

      d(var1);
   }

   public void a(am var1, double var2) {
      var1.bX.o -= (double)this.b * var2;
      var1.cB = (float)((double)var1.cB - (double)this.c * var2);
      var1.cu = (float)((double)var1.cu - (double)this.d * var2);
      var1.cx = (float)((double)var1.cx - (double)this.e * var2);
      var1.cE = (int)((double)var1.cE - (double)this.f * var2);
      this.e(var1);
      if(!this.k.c()) {
         f.b(this.k, var1, var2);
      }

      d(var1);
   }

   public void g(am var1) {
      if(this.b > 0) {
         var1.bX.b((float)this.b);
      } else {
         var1.bX.o += (double)this.b;
      }

      var1.cB += this.c;
      var1.cu += this.d;
      var1.cx += this.e;
      var1.cE += this.f;
      this.e(var1);
      if(!this.k.c()) {
         f.d(this.k, var1);
      }

      d(var1);
   }

   public void h(am var1) {
      var1.bX.o += (double)this.b;
      var1.cB += this.c;
      var1.cu += this.d;
      var1.cx += this.e;
      var1.cE += this.f;
      this.e(var1);
      if(!this.k.c()) {
         f.d(this.k, var1);
      }

      d(var1);
   }

   public void a(am var1, double var2, boolean var4) {
      if(var4) {
         var1.bX.o += (double)this.b * var2;
      }

      var1.cB = (float)((double)var1.cB + (double)this.c * var2);
      var1.cu = (float)((double)var1.cu + (double)this.d * var2);
      var1.cx = (float)((double)var1.cx + (double)this.e * var2);
      var1.cE = (int)((double)var1.cE + (double)this.f * var2);
      this.e(var1);
      if(!this.k.c()) {
         f.c(this.k, var1, var2);
      }

      d(var1);
   }

   public boolean c() {
      return this == a?true:(this.b == 0 && this.c == 0.0F && this.d == 0.0F && this.e == 0.0F && this.f == 0?this.k.c():false);
   }

   public boolean d() {
      return this == a?false:(this.b == 0 && this.c == 0.0F && this.d == 0.0F && this.e == 0.0F && this.f == 0?(this.g == 0 && this.h == 0 && this.i == 0 && this.j == 0?!this.k.c():true):true);
   }

   public boolean e() {
      return this == a?false:(this.b == 0 && this.c == 0.0F && this.d == 0.0F && this.e == 0.0F && this.f == 0?this.g != 0 || this.h != 0:true);
   }

   public String a(boolean var1, boolean var2, int var3, boolean var4) {
      ae var5 = new ae();
      this.a(var5, var1, var2, var3, var4);
      return var5.a();
   }

   public void a(ae var1, boolean var2, boolean var3, int var4, boolean var5, am var6, int var7) {
      this.b(var1, var2, var3, var4, var5, var6, var7);
   }

   private void a(ae var1, boolean var2, boolean var3, int var4, boolean var5) {
      this.b(var1, var2, var3, var4, var5, (am)null, 0);
   }

   private void b(ae var1, boolean var2, boolean var3, int var4, boolean var5, am var6, int var7) {
      String var8;
      if(var2) {
         var8 = "\n";
      } else {
         var8 = " | ";
      }

      int var9 = 0;
      if(this.b > 0 && var9 < var4) {
         int var10 = l;
         if(var6 != null) {
            double var11 = var6.bX.o;
            if(var11 < (double)this.b) {
               var10 = var7;
            }
         }

         var1.a("$" + this.b + var8, var10);
         ++var9;
      }

      if(var3) {
         if(this.c > 0.0F && var9 < var4) {
            var1.b(com.corrodinggames.rts.gameFramework.f.g(this.c) + " energy" + var8);
            ++var9;
         }

         if(this.d > 0.0F && var9 < var4) {
            var1.b(com.corrodinggames.rts.gameFramework.f.g(this.d) + " hp" + var8);
            ++var9;
         }

         if(this.e > 0.0F && var9 < var4) {
            var1.b(com.corrodinggames.rts.gameFramework.f.g(this.e) + " shield" + var8);
            ++var9;
         }

         if(this.f > 0 && var9 < var4) {
            var1.b(com.corrodinggames.rts.gameFramework.f.g((float)this.f) + " ammo" + var8);
            ++var9;
         }
      }

      if(!this.k.c()) {
         this.k.a(var1, var2, var3, var4 - var9, var5, false, var6, var7);
      }

      var1.a(var8);
   }

   public b i(am var1) {
      b var2 = new b();
      if(this.b > 0 && var1.bX.o < (double)this.b) {
         var2.b = this.b - (int)var1.bX.o;
      }

      if(!this.k.c()) {
         var2.k = this.k.a(var1);
      }

      return var2;
   }

   public String a(am var1, int var2, boolean var3) {
      String var4 = null;
      String var5 = ", ";
      byte var6 = 0;
      if(this.b > 0 && var6 < var2 && var1.bX.o < (double)this.b) {
         if(var4 == null) {
            var4 = "";
         }

         var4 = var4 + "credits" + var5;
         int var8 = var6 + 1;
      }

      if(!this.k.c()) {
         String var7 = this.k.a(var1, var5, var2, var3);
         if(var7 != null) {
            if(var4 == null) {
               var4 = "";
            }

            var4 = var4 + var7;
         }
      }

      if(var4 == null) {
         return null;
      } else {
         var4 = com.corrodinggames.rts.gameFramework.f.a(var4, var5);
         return var4;
      }
   }

   public int a(b var1) {
      return this.b - var1.b;
   }

   public static void a(as var0, b var1) {
      var0.a(var1 != null);
      if(var1 != null) {
         var1.a(var0);
      }

   }

   public void a(as var1) {
      boolean var2 = false;
      boolean var3 = false;
      if(this.c != 0.0F || this.d != 0.0F || this.e != 0.0F || this.f != 0) {
         var2 = true;
      }

      if(this.g != 0 || this.h != 0 || this.i != 0 || this.j != 0) {
         var2 = true;
      }

      if(!this.k.c()) {
         var3 = true;
      }

      byte var4 = 0;
      if(var2) {
         var4 = (byte)(var4 | 1);
      }

      if(var3) {
         var4 = (byte)(var4 | 2);
      }

      var1.c(var4);
      var1.a(this.b);
      if(var2) {
         var1.a(this.c);
         var1.a(this.d);
         var1.a(this.e);
         var1.a(this.f);
         var1.a(this.g);
         var1.a(this.h);
         var1.a(this.i);
         var1.a(this.j);
      }

      if(var3) {
         this.k.a(var1);
      }

   }

   public static b a(k var0) {
      boolean var1 = var0.e();
      return var1?b(var0):null;
   }

   public static b b(k var0) {
      b var1 = new b();
      byte var2 = var0.d();
      boolean var3 = b(var2, 1);
      boolean var4 = b(var2, 2);
      var1.b = var0.f();
      if(var3) {
         var1.c = var0.g();
         var1.d = var0.g();
         var1.e = var0.g();
         var1.f = var0.f();
         var1.g = var0.f();
         var1.h = var0.f();
         var1.i = var0.f();
         var1.j = var0.f();
      }

      if(var4) {
         var1.k = new f();
         var1.k.a(var0);
      }

      return var1;
   }

   public boolean b(am var1, boolean var2) {
      if(this.c(var1, var2)) {
         this.d(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   public boolean c(am var1, boolean var2) {
      return this.b > 0 && !var1.bX.g(this.b)?false:(var2?an.c(var1, this):this.b(var1));
   }

   public void d(am var1, boolean var2) {
      var1.bX.p -= (double)this.b;
      var1.bX.q = 0;
      if(var2) {
         an.a(var1, this);
      }

   }

   public void e(am var1, boolean var2) {
      var1.bX.p += (double)this.b;
      var1.bX.q = 0;
      if(var2) {
         an.b(var1, this);
      }

   }

   public static boolean b(b var0, b var1) {
      return var1 == var0?true:(var1 != null && var0 != null?var1.b(var0):false);
   }

   public boolean b(b var1) {
      return this.b != var1.b?false:(this.d != var1.d?false:(this.e != var1.e?false:(this.f != var1.f?false:(this.k.c() != var1.k.c()?false:this.k.c() || var1.k.c() || this.k.e(var1.k)))));
   }

   public boolean c(b var1) {
      return this.b > 0 && var1.b > 0?true:(this.d > 0.0F && var1.d > 0.0F?true:(this.e > 0.0F && var1.e > 0.0F?true:(this.f > 0 && var1.f > 0?true:!this.k.c() && !var1.k.c() && this.k.f(var1.k))));
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.a((b)var1);
   }

}
