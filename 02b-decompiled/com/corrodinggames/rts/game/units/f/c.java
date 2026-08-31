package com.corrodinggames.rts.game.units.f;

import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.game.units.f.a;
import com.corrodinggames.rts.game.units.f.b;
import com.corrodinggames.rts.game.units.f.d;
import com.corrodinggames.rts.game.units.f.e;
import com.corrodinggames.rts.game.units.f.f;
import com.corrodinggames.rts.game.units.f.g;
import com.corrodinggames.rts.game.units.f.h;
import com.corrodinggames.rts.game.units.f.i;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.u;

public final class c {

   int a;
   int b;
   float c;
   float d;
   public a[][] e = (a[][])null;
   d f = new d();
   g g = new g();
   h h = new h();
   final u i = new u();
   final f j = new f();
   final Rect k = new Rect();
   final int l = 32;
   int m;


   public void a(float var1, float var2, float var3, y var4, float var5, i var6) {
      float var7 = var1 - var3;
      float var8 = var2 - var3;
      float var9 = var1 + var3;
      float var10 = var2 + var3;
      this.g.a(var7, var8, var9, var10);
      this.a(this.g.a, this.g, var4, var5, var6);
   }

   public final f a(float var1, float var2, float var3) {
      u var4 = this.i;
      var4.clear();
      this.a(var1, var2, var3, var4);
      this.j.a(var4);
      return this.j;
   }

   public final void a(float var1, float var2, float var3, u var4) {
      a[][] var5 = this.e;
      float var6 = var1 - var3;
      float var7 = var1 + var3;
      float var8 = var2 - var3;
      float var9 = var2 + var3;
      int var10 = this.a(var6);
      int var11 = this.a(var7);
      int var12 = this.b(var8);
      int var13 = this.b(var9);

      for(int var14 = var10; var14 <= var11; ++var14) {
         for(int var15 = var12; var15 <= var13; ++var15) {
            b var16 = var5[var14][var15].a;
            am[] var17 = var16.a();
            int var18 = 0;

            for(int var19 = var16.b; var18 < var19; ++var18) {
               am var20 = var17[var18];
               float var21 = var20.eo;
               float var22 = var20.ep;
               if(var6 <= var21 && var21 <= var7 && var8 <= var22 && var22 <= var9) {
                  var4.a(var20);
               }
            }
         }
      }

   }

   public final f b(float var1, float var2, float var3) {
      u var4 = this.i;
      var4.clear();
      this.b(var1, var2, var3, var4);
      this.j.a(var4);
      return this.j;
   }

   public final void b(float var1, float var2, float var3, u var4) {
      a[][] var5 = this.e;
      float var6 = var1 - var3;
      float var7 = var1 + var3;
      float var8 = var2 - var3;
      float var9 = var2 + var3;
      float var10 = 50.0F;
      int var11 = this.a(var6 - 50.0F);
      int var12 = this.a(var7 + 50.0F);
      int var13 = this.b(var8 - 50.0F);
      int var14 = this.b(var9 + 50.0F);

      for(int var15 = var11; var15 <= var12; ++var15) {
         for(int var16 = var13; var16 <= var14; ++var16) {
            b var17 = var5[var15][var16].a;
            am[] var18 = var17.a();
            int var19 = 0;

            for(int var20 = var17.b; var19 < var20; ++var19) {
               am var21 = var18[var19];
               float var22 = var21.eo;
               float var23 = var21.ep;
               float var24 = var21.cj;
               if(var6 - var24 <= var22 && var22 <= var7 + var24 && var8 - var24 <= var23 && var23 <= var9 + var24) {
                  var4.b(var21);
               }
            }
         }
      }

   }

   public final void a(n var1, float var2, float var3, float var4, u var5) {
      a[][] var6 = this.e;
      float var7 = var2 - var4;
      float var8 = var2 + var4;
      float var9 = var3 - var4;
      float var10 = var3 + var4;
      float var11 = 50.0F;
      int var12 = this.a(var7 - 50.0F);
      int var13 = this.a(var8 + 50.0F);
      int var14 = this.b(var9 - 50.0F);
      int var15 = this.b(var10 + 50.0F);
      int var16 = var1.k;

      for(int var17 = var12; var17 <= var13; ++var17) {
         for(int var18 = var14; var18 <= var15; ++var18) {
            b var19 = var6[var17][var18].b[var16];
            am[] var20 = var19.a();
            int var21 = 0;

            for(int var22 = var19.b; var21 < var22; ++var21) {
               am var23 = var20[var21];
               float var24 = var23.eo;
               float var25 = var23.ep;
               float var26 = var23.cj;
               if(var7 - var26 <= var24 && var24 <= var8 + var26 && var9 - var26 <= var25 && var25 <= var10 + var26) {
                  var5.b(var23);
               }
            }
         }
      }

   }

   public void a(RectF var1, e var2, y var3, float var4, i var5) {
      a[][] var6 = this.e;
      int var7 = this.a(var1.a);
      int var8 = this.a(var1.c);
      int var9 = this.b(var1.b);
      int var10 = this.b(var1.d);
      n var11 = null;
      int var12 = var5.excludeTeam(var3);
      if(var12 != -2 && var12 != -3) {
         var11 = n.k(var12);
      }

      n var13 = var5.onlyEnemiesOfTeam(var3);
      n var14 = var5.onlyTeam(var3);
      var5.setup(var3, var4);
      int var15;
      int var16;
      int var20;
      b var25;
      am[] var26;
      int var29;
      am var30;
      if(var13 == null && var14 == null) {
         for(var15 = var7; var15 <= var8; ++var15) {
            for(var16 = var9; var16 <= var10; ++var16) {
               var25 = var6[var15][var16].a;
               var26 = var25.a();
               var29 = 0;

               for(var20 = var25.b; var29 < var20; ++var29) {
                  var30 = var26[var29];
                  if((var11 == null || var30.bX != var11) && var2.a(var30)) {
                     var5.callback(var3, var4, var30);
                  }
               }
            }
         }
      } else {
         int var21;
         if(var14 != null) {
            var15 = var14.k;
            int var17;
            b var18;
            am[] var19;
            am var22;
            if(var15 == -1) {
               for(var16 = var7; var16 <= var8; ++var16) {
                  for(var17 = var9; var17 <= var10; ++var17) {
                     var18 = var6[var16][var17].d;
                     if(var18.b > 0) {
                        var19 = var18.a();
                        var20 = 0;

                        for(var21 = var18.b; var20 < var21; ++var20) {
                           var22 = var19[var20];
                           if(var2.a(var22)) {
                              var5.callback(var3, var4, var22);
                           }
                        }
                     }
                  }
               }
            } else if(var15 == -2) {
               for(var16 = var7; var16 <= var8; ++var16) {
                  for(var17 = var9; var17 <= var10; ++var17) {
                     var18 = var6[var16][var17].c;
                     if(var18.b > 0) {
                        var19 = var18.a();
                        var20 = 0;

                        for(var21 = var18.b; var20 < var21; ++var20) {
                           var22 = var19[var20];
                           if(var2.a(var22)) {
                              var5.callback(var3, var4, var22);
                           }
                        }
                     }
                  }
               }
            } else {
               for(var16 = var7; var16 <= var8; ++var16) {
                  for(var17 = var9; var17 <= var10; ++var17) {
                     var18 = var6[var16][var17].b[var15];
                     if(var18.b > 0) {
                        var19 = var18.a();
                        var20 = 0;

                        for(var21 = var18.b; var20 < var21; ++var20) {
                           var22 = var19[var20];
                           if(var2.a(var22)) {
                              var5.callback(var3, var4, var22);
                           }
                        }
                     }
                  }
               }
            }
         } else {
            if(var13 != n.h) {
               for(var15 = var7; var15 <= var8; ++var15) {
                  for(var16 = var9; var16 <= var10; ++var16) {
                     var25 = var6[var15][var16].c;
                     if(var25.b > 0) {
                        var26 = var25.a();
                        var29 = 0;

                        for(var20 = var25.b; var29 < var20; ++var29) {
                           var30 = var26[var29];
                           if(var2.a(var30)) {
                              var5.callback(var3, var4, var30);
                           }
                        }
                     }
                  }
               }
            }

            var15 = this.m;

            for(var16 = 0; var16 <= var15; ++var16) {
               n var27 = n.k(var16);
               if(var27 != null && var13 != var27 && var13.c(var27)) {
                  for(int var28 = var7; var28 <= var8; ++var28) {
                     for(var29 = var9; var29 <= var10; ++var29) {
                        b var31 = var6[var28][var29].b[var16];
                        var21 = var31.b;
                        if(var21 > 0) {
                           am[] var32 = var31.a();

                           for(int var23 = 0; var23 < var21; ++var23) {
                              am var24 = var32[var23];
                              if(var2.a(var24)) {
                                 var5.callback(var3, var4, var24);
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

   public final int a(float var1) {
      int var2 = (int)(var1 * this.c);
      if(var2 < 0) {
         var2 = 0;
      }

      if(var2 >= 32) {
         var2 = 31;
      }

      return var2;
   }

   public final int b(float var1) {
      int var2 = (int)(var1 * this.d);
      if(var2 < 0) {
         var2 = 0;
      }

      if(var2 >= 32) {
         var2 = 31;
      }

      return var2;
   }

   public void a() {
      float var1 = this.c;
      float var2 = this.d;
      am[] var3 = am.bE.a();
      int var4 = 0;

      for(int var5 = am.bE.size(); var4 < var5; ++var4) {
         am var6 = var3[var4];
         if(var6.bV || (int)(var6.eo * var1) != var6.dl || (int)(var6.ep * var2) != var6.dm || var6.bX == null || var6.dn != var6.bX.k) {
            this.a(var6);
         }
      }

   }

   public void a(am var1) {
      if(this.e == null) {
         if(l.B().bx != 0) {
            l.b("updateUnitGeoIndex: areaList not active");
         }

         var1.dl = -1;
         var1.dm = -1;
      } else if(var1.bV) {
         if(var1.dl != -1 && var1.dm != -1) {
            this.e[var1.dl][var1.dm].b(var1);
            var1.dl = -1;
            var1.dm = -1;
         }

      } else {
         int var2 = this.a(var1.eo);
         int var3 = this.b(var1.ep);
         int var4 = -2;
         if(var1.bX != null) {
            var4 = var1.bX.k;
         }

         if(var1.dl != var2 || var1.dm != var3 || var1.dn != var4) {
            if(var1.dl != -1 && var1.dm != -1) {
               this.e[var1.dl][var1.dm].b(var1);
            }

            var1.dl = var2;
            var1.dm = var3;
            var1.dn = var4;
            if(var4 > this.m && this.m < n.c) {
               this.m = var4;
            }

            this.e[var1.dl][var1.dm].a(var1);
         }
      }
   }

   public void a(com.corrodinggames.rts.game.b.b var1) {
      this.e = new a[32][32];
      this.m = 0;

      for(int var2 = 0; var2 < 32; ++var2) {
         for(int var3 = 0; var3 < 32; ++var3) {
            this.e[var2][var3] = new a();
         }
      }

      this.a = var1.C * var1.n / 32;
      this.b = var1.D * var1.o / 32;
      this.c = 1.0F / (float)this.a;
      this.d = 1.0F / (float)this.b;
   }

   public void b() {
      this.e = (a[][])null;
   }

   public void c(float var1) {}
}
