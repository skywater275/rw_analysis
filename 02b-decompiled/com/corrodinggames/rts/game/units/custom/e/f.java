package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.e.e;
import com.corrodinggames.rts.game.units.custom.e.f$1;
import com.corrodinggames.rts.gameFramework.f.ae;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.Collections;

public final class f {

   public static final f a = (new f()).a();
   public final m b = new m();
   boolean c;


   public strictfp f a() {
      this.c = true;
      return this;
   }

   public strictfp void b() {
      this.b.clear();
   }

   public strictfp boolean c() {
      if(this.b.a == 0) {
         return true;
      } else {
         int var1 = this.b.a;
         Object[] var2 = this.b.a();

         for(int var3 = 0; var3 < var1; ++var3) {
            e var4 = (e)var2[var3];
            if(var4.b != 0.0D) {
               return false;
            }
         }

         return true;
      }
   }

   public strictfp double a(a var1) {
      int var2 = this.b.a;
      Object[] var3 = this.b.a();

      for(int var4 = 0; var4 < var2; ++var4) {
         e var5 = (e)var3[var4];
         if(var5.a == var1) {
            return var5.b;
         }
      }

      return 0.0D;
   }

   public strictfp double b(a var1) {
      int var2 = this.b.a;
      Object[] var3 = this.b.a();
      int var4 = 0;

      for(int var5 = 0; var5 < var2; ++var5) {
         e var6 = (e)var3[var5];
         if(var6.a == var1) {
            var4 = (int)((double)var4 + var6.b);
         }

         if(var6.a.v == var1) {
            var4 = (int)((double)var4 + var6.b);
         }
      }

      return (double)var4;
   }

   public strictfp void a(f var1) {
      this.b();
      this.b(var1);
   }

   public strictfp void a(a var1, double var2) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         int var4 = this.b.a;
         Object[] var5 = this.b.a();

         for(int var6 = 0; var6 < var4; ++var6) {
            e var7 = (e)var5[var6];
            if(var7.a == var1) {
               var7.b = var2;
               return;
            }
         }

         e var8 = new e(var1);
         var8.b = var2;
         this.b.add(var8);
      }
   }

   public strictfp void a(double var1) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         int var3 = this.b.a;
         Object[] var4 = this.b.a();

         for(int var5 = 0; var5 < var3; ++var5) {
            e var6 = (e)var4[var5];
            var6.b *= var1;
         }

      }
   }

   public strictfp void b(a var1, double var2) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else if(var2 != 0.0D) {
         m var4 = this.b;
         int var5 = var4.a;
         Object[] var6 = var4.a();

         for(int var7 = 0; var7 < var5; ++var7) {
            e var8 = (e)var6[var7];
            if(var8.a == var1) {
               var8.b += var2;
               return;
            }
         }

         e var9 = new e(var1);
         var9.b = var2;
         var4.add(var9);
      }
   }

   public strictfp void c(a var1, double var2) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else if(var2 != 0.0D) {
         m var4 = this.b;
         int var5 = var4.a;
         Object[] var6 = var4.a();

         for(int var7 = 0; var7 < var5; ++var7) {
            e var8 = (e)var6[var7];
            if(var8.a == var1) {
               var8.b += var2;
               return;
            }
         }

         e var9 = new e(var1);
         var9.b = var2;
         var4.add(var9);
      }
   }

   public strictfp void d(a var1, double var2) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         this.b(var1, -var2);
      }
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.d.b var1, double var2, double var4) {
      if((double)var1.b >= var2 && (double)var1.b <= var4) {
         this.c(a.D, (double)var1.b);
      }

      this.a(var1.k, var2, var4);
   }

   public strictfp void b(com.corrodinggames.rts.game.units.custom.d.b var1, double var2, double var4) {
      if((double)var1.b >= var2 && (double)var1.b <= var4) {
         this.c(a.D, (double)(-var1.b));
      }

      this.b(var1.k, var2, var4);
   }

   public strictfp void a(com.corrodinggames.rts.game.units.custom.d.b var1) {
      this.c(a.D, (double)var1.b);
      this.b(var1.k);
   }

   public strictfp void b(f var1) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         int var2 = var1.b.a;
         Object[] var3 = var1.b.a();

         for(int var4 = 0; var4 < var2; ++var4) {
            e var5 = (e)var3[var4];
            this.b(var5.a, var5.b);
         }

      }
   }

   public strictfp void a(f var1, double var2, double var4) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         int var6 = var1.b.a;
         Object[] var7 = var1.b.a();

         for(int var8 = 0; var8 < var6; ++var8) {
            e var9 = (e)var7[var8];
            if(var9.b >= var2 && var9.b <= var4) {
               this.b(var9.a, var9.b);
            }
         }

      }
   }

   public strictfp void a(f var1, double var2) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         int var4 = var1.b.a;
         Object[] var5 = var1.b.a();

         for(int var6 = 0; var6 < var4; ++var6) {
            e var7 = (e)var5[var6];
            this.b(var7.a, var7.b * var2);
         }

      }
   }

   public strictfp void c(f var1) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         int var2 = var1.b.a;
         Object[] var3 = var1.b.a();

         for(int var4 = 0; var4 < var2; ++var4) {
            e var5 = (e)var3[var4];
            this.d(var5.a, var5.b);
         }

      }
   }

   public strictfp void b(f var1, double var2, double var4) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         int var6 = var1.b.a;
         Object[] var7 = var1.b.a();

         for(int var8 = 0; var8 < var6; ++var8) {
            e var9 = (e)var7[var8];
            if(var9.b >= var2 && var9.b <= var4) {
               this.d(var9.a, var9.b);
            }
         }

      }
   }

   public static strictfp f a(f var0, f var1) {
      f var2 = new f();
      var2.b(var0);
      var2.b(var1);
      return var2;
   }

   public static strictfp f b(f var0, f var1) {
      f var2 = new f();
      var2.b(var0);
      var2.c(var1);
      return var2;
   }

   public static strictfp f b(f var0, double var1) {
      f var3 = new f();
      var3.a(var0, var1);
      return var3;
   }

   public static strictfp f d(f var0) {
      f var1 = new f();
      var1.b(var0);
      return var1;
   }

   public static strictfp int a(f var0, am var1) {
      int var2 = 9999;
      int var3 = var0.b.a;
      Object[] var4 = var0.b.a();

      for(int var5 = 0; var5 < var3; ++var5) {
         e var6 = (e)var4[var5];
         if(var6.b > 0.0D) {
            double var7;
            if(var6.a.t) {
               var7 = var1.bX.c(var6.a);
            } else {
               var7 = var1.a(var6.a);
            }

            int var9 = (int)(var7 / var6.b);
            var2 = com.corrodinggames.rts.gameFramework.f.c(var2, var9);
         }
      }

      return var2;
   }

   public static strictfp boolean b(f var0, am var1) {
      int var2 = var0.b.a;
      Object[] var3 = var0.b.a();

      for(int var4 = 0; var4 < var2; ++var4) {
         e var5 = (e)var3[var4];
         double var6;
         if(var5.a.t) {
            var6 = var1.bX.c(var5.a);
         } else {
            var6 = var1.a(var5.a);
         }

         if(var5.b > var6) {
            return false;
         }
      }

      return true;
   }

   public static strictfp boolean a(f var0, am var1, double var2) {
      int var4 = var0.b.a;
      Object[] var5 = var0.b.a();

      for(int var6 = 0; var6 < var4; ++var6) {
         e var7 = (e)var5[var6];
         double var8;
         if(var7.a.t) {
            var8 = var1.bX.c(var7.a);
         } else {
            var8 = var1.a(var7.a);
         }

         if(var7.b * var2 > var8) {
            return false;
         }
      }

      return true;
   }

   public static strictfp void c(f var0, am var1) {
      int var2 = var0.b.a;
      Object[] var3 = var0.b.a();

      for(int var4 = 0; var4 < var2; ++var4) {
         e var5 = (e)var3[var4];
         f var6;
         if(var5.a.t) {
            var6 = var1.bX.V();
            var6.d(var5.a, var5.b);
         } else {
            var6 = var1.df();
            var6.d(var5.a, var5.b);
         }
      }

   }

   public static strictfp void b(f var0, am var1, double var2) {
      int var4 = var0.b.a;
      Object[] var5 = var0.b.a();

      for(int var6 = 0; var6 < var4; ++var6) {
         e var7 = (e)var5[var6];
         f var8;
         if(var7.a.t) {
            var8 = var1.bX.V();
            var8.d(var7.a, var7.b * var2);
         } else {
            var8 = var1.df();
            var8.d(var7.a, var7.b * var2);
         }
      }

   }

   public static strictfp void d(f var0, am var1) {
      int var2 = var0.b.a;
      Object[] var3 = var0.b.a();

      for(int var4 = 0; var4 < var2; ++var4) {
         e var5 = (e)var3[var4];
         f var6;
         if(var5.a.t) {
            var6 = var1.bX.V();
            var6.b(var5.a, var5.b);
         } else {
            var6 = var1.df();
            var6.b(var5.a, var5.b);
         }
      }

   }

   public static strictfp void c(f var0, am var1, double var2) {
      int var4 = var0.b.a;
      Object[] var5 = var0.b.a();

      for(int var6 = 0; var6 < var4; ++var6) {
         e var7 = (e)var5[var6];
         f var8;
         if(var7.a.t) {
            var8 = var1.bX.V();
            var8.b(var7.a, var7.b * var2);
         } else {
            var8 = var1.df();
            var8.b(var7.a, var7.b * var2);
         }
      }

   }

   public static strictfp boolean a(f var0, am var1, am var2) {
      boolean var3 = false;
      int var4 = var0.b.a;
      Object[] var5 = var0.b.a();

      for(int var6 = 0; var6 < var4; ++var6) {
         e var7 = (e)var5[var6];
         a var8 = var7.a;
         double var9 = var7.b;
         if(var9 != 0.0D) {
            double var11 = var8.a(var1);
            double var13 = var8.a(var2);
            double var15;
            if(var9 >= 0.0D) {
               if(var11 > 0.0D) {
                  var15 = com.corrodinggames.rts.gameFramework.f.a(var11, var9);
                  var8.b(var1, -var15);
                  var8.b(var2, var15);
                  var3 = true;
               }
            } else if(var13 > 0.0D) {
               var9 = -var9;
               var15 = com.corrodinggames.rts.gameFramework.f.a(var13, var9);
               var8.b(var2, -var15);
               var8.b(var1, var15);
               var3 = true;
            }
         }
      }

      return var3;
   }

   public strictfp String a(boolean var1, boolean var2, int var3, boolean var4, boolean var5) {
      ae var6 = new ae();
      this.a(var6, var1, var2, var3, var4, var5, (am)null, 0);
      return var6.a();
   }

   public strictfp void a(ae var1, boolean var2, boolean var3, int var4, boolean var5, boolean var6, am var7, int var8) {
      int var9 = this.b.a;
      if(var9 != 0) {
         String var10;
         if(var2) {
            var10 = "\n";
         } else {
            var10 = " | ";
         }

         int var11 = 0;
         Object[] var12 = this.b.a();

         for(int var13 = 0; var13 < var9; ++var13) {
            e var14 = (e)var12[var13];
            if((var14.b > 0.0D || var6) && var11 < var4) {
               a var15 = var14.a;
               if(var5 || !var15.a()) {
                  boolean var16 = false;
                  if(var15.y != null && var15.z) {
                     var16 = true;
                     int var17 = var1.c() - 2;
                     if(var17 < 2) {
                        var17 = 2;
                     }

                     var1.a(var15.y, var17 * 3, var17);
                  }

                  String var22 = var15.a(var14.b, false, var16) + var10;
                  boolean var18 = false;
                  int var19 = 0;
                  if(var15.m != null && var15.n) {
                     var18 = true;
                     var19 = var15.m.intValue();
                  }

                  if(var7 != null) {
                     double var20 = var15.a(var7);
                     if(var20 < var14.b) {
                        var18 = true;
                        var19 = var8;
                     }
                  }

                  if(var18) {
                     var1.a(var22, var19);
                  } else {
                     var1.b(var22);
                  }

                  ++var11;
               }
            }
         }

      }
   }

   public strictfp void a(as var1) {
      if(this.b.a == 0) {
         var1.c(-1);
      } else {
         var1.c(0);
         var1.a((short)this.b.a);
         int var2 = this.b.a;
         Object[] var3 = this.b.a();

         for(int var4 = 0; var4 < var2; ++var4) {
            e var5 = (e)var3[var4];
            var1.c(var5.a.b);
            var1.a(var5.b);
         }

      }
   }

   public strictfp void a(k var1) {
      if(this.c) {
         throw new RuntimeException("StoredResources are locked");
      } else {
         byte var2 = var1.d();
         if(var2 != -1) {
            short var3 = var1.v();
            this.b.clear();

            for(int var4 = 0; var4 < var3; ++var4) {
               a var5 = a.b(var1.l());
               double var6 = var1.h();
               if(var5 != null && var6 != 0.0D) {
                  e var8 = new e(var5, var6);
                  this.b.add(var8);
               }
            }

         }
      }
   }

   public strictfp int d() {
      int var1 = 0;
      int var2 = this.b.a;
      Object[] var3 = this.b.a();

      for(int var4 = 0; var4 < var2; ++var4) {
         e var5 = (e)var3[var4];
         if(var5.b != 0.0D) {
            ++var1;
         }
      }

      return var1;
   }

   public strictfp boolean e(f var1) {
      if(this.d() != var1.d()) {
         return false;
      } else {
         int var2 = this.b.a;
         Object[] var3 = this.b.a();

         for(int var4 = 0; var4 < var2; ++var4) {
            e var5 = (e)var3[var4];
            double var6 = var1.a(var5.a);
            if(!com.corrodinggames.rts.gameFramework.f.b(var5.b, var6)) {
               return false;
            }
         }

         return true;
      }
   }

   public strictfp boolean f(f var1) {
      int var2 = this.b.a;
      Object[] var3 = this.b.a();

      for(int var4 = 0; var4 < var2; ++var4) {
         e var5 = (e)var3[var4];
         if(var5.b > 0.0D) {
            double var6 = var1.b(var5.a);
            if(var6 > 0.0D) {
               return true;
            }
         }
      }

      return false;
   }

   public strictfp f a(am var1) {
      f var2 = new f();
      int var3 = this.b.a;
      Object[] var4 = this.b.a();

      for(int var5 = 0; var5 < var3; ++var5) {
         e var6 = (e)var4[var5];
         double var7;
         if(var6.a.t) {
            var7 = var1.bX.c(var6.a);
         } else {
            var7 = var1.a(var6.a);
         }

         if(var7 < var6.b) {
            double var9 = var6.b - var7;
            var2.b(var6.a, var9);
         }
      }

      if(var2.c()) {
         return a;
      } else {
         return var2;
      }
   }

   public strictfp String a(am var1, String var2, int var3, boolean var4) {
      String var5 = null;
      int var6 = 0;
      int var7 = this.b.a;
      Object[] var8 = this.b.a();

      for(int var9 = 0; var9 < var7; ++var9) {
         e var10 = (e)var8[var9];
         if(var4 || !var10.a.a()) {
            double var11;
            if(var10.a.t) {
               var11 = var1.bX.c(var10.a);
            } else {
               var11 = var1.a(var10.a);
            }

            if(var11 < var10.b) {
               double var10000 = var10.b - var11;
               String var15 = var10.a.i();
               if(var5 == null) {
                  var5 = var15;
               } else {
                  var5 = var5 + var2 + var15;
               }

               ++var6;
               if(var6 > var3) {
                  break;
               }
            }
         }
      }

      return var5;
   }

   public strictfp void g(f var1) {
      this.b();
      this.b(var1);
   }

   public strictfp void c(a var1) {
      m var2 = this.b;
      int var3 = var2.a;
      Object[] var4 = var2.a();

      for(int var5 = 0; var5 < var3; ++var5) {
         e var6 = (e)var4[var5];
         if(var6.a == var1) {
            return;
         }
      }

      e var7 = new e(var1);
      var7.b = 0.0D;
      var2.add(var7);
   }

   public strictfp void e() {
      Collections.sort(this.b, new f$1(this));
   }

}
