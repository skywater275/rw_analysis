package com.corrodinggames.rts.gameFramework.k;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.k.l;
import com.corrodinggames.rts.gameFramework.utility.ak;
import java.util.HashMap;

public final class i {

   private final l q;
   ao a;
   public final int b;
   public final int c;
   public byte[] d;
   public byte[] e;
   public byte[] f;
   public short[] g;
   public HashMap h;
   public int i;
   public byte[] j;
   public int k = -99;
   public int l = 0;
   public boolean m;
   Point n = new Point();
   public boolean o;
   public int p;


   strictfp i(l var1, ao var2, int var3, int var4) {
      this.b = var3;
      this.c = var4;
      this.q = var1;
      this.a = var2;
      this.d = new byte[var3 * var4];
      this.q.u.add(this);
      this.q.v = (i[])this.q.u.toArray(new i[0]);
      this.a();
   }

   strictfp void a() {
      this.d();
      this.c((y)null);
      this.e();
   }

   strictfp void a(y var1) {
      if(var1 != null) {
         ++this.l;
         if(this.l > 50) {
            if(!this.m) {
               com.corrodinggames.rts.gameFramework.l.e("buildAndReplaceClearanceCost being skipped");
            }

            this.m = true;
            return;
         }
      }

      if(var1 != null) {
         this.b(var1);
      } else {
         long var2 = 0L;
         if(var1 == null) {
            var2 = br.a();
         }

         com.corrodinggames.rts.game.b.b var4 = this.q.q;
         byte[] var5 = this.j;
         if(this.j == null) {
            var1 = null;
         }

         this.j = new byte[this.b * this.c];
         short var6 = 0;
         short var7 = 0;
         short var8 = this.q.s;
         short var9 = this.q.t;
         short var11;
         if(var1 != null) {
            if(var5 != null) {
               com.corrodinggames.rts.gameFramework.f.a(var5, this.j);
            }

            var4.a(var1.eo, var1.ep);
            Rect var10 = var1.cc();
            var11 = (short)var4.T;
            short var12 = (short)var4.U;
            var6 = (short)(var11 - 5 + var10.a);
            var7 = (short)(var12 - 5 + var10.b);
            var8 = (short)(var11 + 5 + var10.c);
            var9 = (short)(var12 + 5 + var10.d);
         }

         if(var6 < 0) {
            var6 = 0;
         }

         if(var7 < 0) {
            var7 = 0;
         }

         if(var8 > this.q.s) {
            var8 = this.q.s;
         }

         if(var9 > this.q.t) {
            var9 = this.q.t;
         }

         short var13;
         for(var13 = var6; var13 < var8; ++var13) {
            for(var11 = var7; var11 < var9; ++var11) {
               boolean var14 = false;
               if(this.d[var13 * this.c + var11] == -1) {
                  var14 = true;
               }

               if(this.e[var13 * this.c + var11] == -1) {
                  var14 = true;
               }

               if(var14) {
                  this.j[var13 * this.c + var11] = 0;
               } else {
                  this.j[var13 * this.c + var11] = 4;
               }
            }
         }

         for(var13 = var6; var13 < var8; ++var13) {
            for(var11 = var7; var11 < var9; ++var11) {
               if(this.j[var13 * this.c + var11] == 0) {
                  this.a(var4, var13, var11, this.j);
               }
            }
         }

         for(var13 = var6; var13 < var8; ++var13) {
            this.a(var4, var13, (short)-1, this.j);
            this.a(var4, var13, (short)(this.q.t + 1), this.j);
         }

         for(var13 = var7; var13 < var9; ++var13) {
            this.a(var4, (short)-1, var13, this.j);
            this.a(var4, (short)(this.q.s + 1), var13, this.j);
         }

         if(var1 == null) {
            double var15 = (double)br.a(var2);
            if(var15 > 30.0D) {
               com.corrodinggames.rts.gameFramework.l.e("buildAndReplaceClearanceCostNew took:" + br.a(var15) + " for:" + this.a);
            }
         }

      }
   }

   final strictfp void a(com.corrodinggames.rts.game.b.b var1, short var2, short var3, byte[] var4) {
      int var5 = var2 - 3;
      int var6 = var3 - 3;
      int var7 = var2 + 3;
      int var8 = var3 + 3;
      if(var5 < 0) {
         var5 = 0;
      }

      if(var6 < 0) {
         var6 = 0;
      }

      if(var7 > var1.C - 1) {
         var7 = var1.C - 1;
      }

      if(var8 > var1.D - 1) {
         var8 = var1.D - 1;
      }

      for(int var9 = var5; var9 <= var7; ++var9) {
         for(int var10 = var6; var10 <= var8; ++var10) {
            byte var11 = var4[var9 * this.c + var10];
            if(var11 != 0) {
               int var12 = com.corrodinggames.rts.gameFramework.f.a(var2, var3, var9, var10);
               if(var12 < var11) {
                  var4[var9 * this.c + var10] = (byte)var12;
               }
            }
         }
      }

   }

   strictfp void b(y var1) {
      long var2 = 0L;
      if(var1 == null) {
         var2 = br.a();
      }

      com.corrodinggames.rts.game.b.b var4 = this.q.q;
      byte[] var5 = this.j;
      if(this.j == null) {
         var1 = null;
      }

      this.j = new byte[this.b * this.c];
      short var6 = 0;
      short var7 = 0;
      short var8 = this.q.s;
      short var9 = this.q.t;
      short var11;
      if(var1 != null) {
         if(var5 != null) {
            com.corrodinggames.rts.gameFramework.f.a(var5, this.j);
         }

         var4.a(var1.eo, var1.ep);
         Rect var10 = var1.cc();
         var11 = (short)var4.T;
         short var12 = (short)var4.U;
         var6 = (short)(var11 - 5 + var10.a);
         var7 = (short)(var12 - 5 + var10.b);
         var8 = (short)(var11 + 5 + var10.c);
         var9 = (short)(var12 + 5 + var10.d);
      }

      if(var6 < 0) {
         var6 = 0;
      }

      if(var7 < 0) {
         var7 = 0;
      }

      if(var8 > this.q.s) {
         var8 = this.q.s;
      }

      if(var9 > this.q.t) {
         var9 = this.q.t;
      }

      for(short var13 = var6; var13 < var8; ++var13) {
         for(var11 = var7; var11 < var9; ++var11) {
            this.j[var13 * this.c + var11] = this.a(var4, var13, var11);
         }
      }

      if(var1 == null) {
         double var14 = (double)br.a(var2);
         com.corrodinggames.rts.gameFramework.l.e("buildAndReplaceClearanceCost took:" + br.a(var14) + " for:" + this.a);
      }

   }

   final strictfp byte a(com.corrodinggames.rts.game.b.b var1, short var2, short var3) {
      if(this.d[var2 * this.c + var3] == -1) {
         return (byte)0;
      } else {
         int var4 = var2 - 3;
         int var5 = var3 - 3;
         int var6 = var2 + 3;
         int var7 = var3 + 3;
         int var8 = 4;

         for(int var9 = var4; var9 <= var6; ++var9) {
            for(int var10 = var5; var10 <= var7; ++var10) {
               boolean var11 = false;
               if(var1.c(var9, var10)) {
                  if(this.d[var9 * this.c + var10] == -1) {
                     var11 = true;
                  }

                  if(this.e[var9 * this.c + var10] == -1) {
                     var11 = true;
                  }
               } else {
                  var11 = true;
               }

               if(var11) {
                  int var12 = com.corrodinggames.rts.gameFramework.f.a(var2, var3, var9, var10);
                  if(var12 < var8) {
                     var8 = var12;
                  }
               }
            }
         }

         return (byte)var8;
      }
   }

   strictfp void b() {
      int var1 = this.b;
      int var2 = this.c;
      this.g = new short[var1 * var2];
      this.h = new HashMap();
      short var3 = 1;

      for(short var4 = 0; var4 < var1; ++var4) {
         for(short var5 = 0; var5 < var2; ++var5) {
            if(this.g[var4 * var2 + var5] == 0) {
               if(var3 <= 0) {
                  Log.d("RustedWarfare", "warning buildIsolatedGroups looped, ending");
                  return;
               }

               int var6 = this.a(var4, var5, var3);
               if(var6 > 0) {
                  this.h.put(Short.valueOf(var3), Integer.valueOf(var6));
                  if(this.i < var6) {
                     this.i = var6;
                  }

                  ++var3;
               }
            }
         }
      }

   }

   strictfp int a(short var1, short var2, short var3) {
      int var4 = this.c;
      com.corrodinggames.rts.game.b.b var5 = this.q.q;
      short[] var6 = this.g;
      byte[] var7 = this.d;
      if(var7[var1 * var4 + var2] == -1) {
         var6[var1 * var4 + var2] = -1;
         return 0;
      } else if(var3 == 0) {
         throw new RuntimeException("id cannot be 0 is will cause can endless loop");
      } else {
         int var8 = 0;
         com.corrodinggames.rts.gameFramework.utility.g var9 = new com.corrodinggames.rts.gameFramework.utility.g();
         ak var10 = new ak(var1, var2);
         var9.add(var10);

         while(!var9.isEmpty()) {
            ak var11 = (ak)var9.a();
            short var12 = var11.a;
            short var13 = var11.b;
            if(var5.c(var12, var13)) {
               int var14 = var12 * var4 + var13;
               if(var6[var14] == 0 && var7[var14] != -1) {
                  var6[var14] = var3;
                  ++var8;
                  var9.add(new ak((short)(var12 - 1), (short)var13));
                  var9.add(new ak((short)(var12 + 1), (short)var13));
                  var9.add(new ak((short)var12, (short)(var13 - 1)));
                  var9.add(new ak((short)var12, (short)(var13 + 1)));
               }
            }
         }

         return var8;
      }
   }

   strictfp boolean c() {
      return !this.a.equals(ao.d) && !this.a.equals(ao.a);
   }

   public static strictfp int a(com.corrodinggames.rts.game.b.b var0) {
      com.corrodinggames.rts.game.b.e var1 = var0.u;
      int var2 = 0;

      for(int var3 = 0; var3 < var1.n; ++var3) {
         for(int var4 = 0; var4 < var1.o; ++var4) {
            com.corrodinggames.rts.game.b.g var5 = var1.a(var3, var4);
            if(var5 != null) {
               byte var6 = 0;
               int var7 = var6 + (var5.e?1:0);
               var7 += var5.h?2:0;
               var7 += var5.k?4:0;
               var7 += var5.g?8:0;
               var7 += var5.f?16:0;
               var2 += var7 * (var3 + var4);
            }
         }
      }

      return var2;
   }

   strictfp void d() {
      com.corrodinggames.rts.game.b.b var1 = this.q.q;
      byte[] var2 = this.d;
      short[] var3 = var1.u.a();
      com.corrodinggames.rts.game.b.g[] var4 = var1.B;
      ao var5 = this.a;
      int var6 = this.b;
      int var7 = this.c;
      boolean var8 = this.c();
      if(var8) {
         boolean var9 = var5.equals(ao.e) || var5.equals(ao.f) || var5.equals(ao.h);
         boolean var10 = var5.equals(ao.f) || var5.equals(ao.g) || var5.equals(ao.h);
         boolean var11 = var5.equals(ao.g) || var5.equals(ao.h);
         boolean var12 = false;

         int var13;
         int var14;
         for(var13 = 0; var13 < var6; ++var13) {
            for(var14 = 0; var14 < var7; ++var14) {
               int var15 = var13 * var7 + var14;
               var2[var15] = 0;
               short var16 = var3[var15];
               com.corrodinggames.rts.game.b.g var17 = var4[var16];
               if(var17 != null) {
                  if(var17.e && !var9) {
                     var2[var15] = -1;
                  }

                  if(var17.h && !var10) {
                     var2[var15] = -1;
                  }

                  if(var17.k && !var11) {
                     var2[var15] = -1;
                  }

                  if(var17.g && !var12) {
                     var2[var15] = -1;
                  }

                  if(var5 == ao.e && !var17.e && !var17.f) {
                     var2[var15] = -1;
                  }
               }

               com.corrodinggames.rts.game.b.g var18 = var1.e(var13, var14);
               if(var18 != null) {
                  if(var5 == ao.b && var18.i) {
                     var2[var15] = -1;
                  }

                  if(var18.k && !var11) {
                     var2[var15] = -1;
                  }

                  if(var2[var15] == 0) {
                     var2[var15] = var18.j;
                  }
               }

               if(var17 != null && var2[var15] == 0) {
                  var2[var15] = var17.j;
               }
            }
         }

         if(var1.x != null) {
            for(var13 = 0; var13 < var6; ++var13) {
               for(var14 = 0; var14 < var7; ++var14) {
                  com.corrodinggames.rts.game.b.g var19 = var1.x.a(var13, var14);
                  if(var19 != null) {
                     var2[var13 * var7 + var14] = 0;
                     if(var19.e && !var9) {
                        var2[var13 * var7 + var14] = -1;
                     }

                     if(var19.h && !var10) {
                        var2[var13 * var7 + var14] = -1;
                     }

                     if(var19.k && !var11) {
                        var2[var13 * var7 + var14] = -1;
                     }

                     if(var19.g && !var12) {
                        var2[var13 * var7 + var14] = -1;
                     }

                     if(var2[var13 * var7 + var14] == 0) {
                        var2[var13 * var7 + var14] = var19.j;
                     }

                     if(var5 == ao.e && !var19.e && !var19.f) {
                        var2[var13 * var7 + var14] = -1;
                     }
                  }
               }
            }
         }

      }
   }

   public strictfp void c(y var1) {
      if(var1 != null) {
         ++this.p;
         if(this.p > 50) {
            this.o = true;
            return;
         }
      }

      long var2 = com.corrodinggames.rts.gameFramework.l.V();
      this.e = new byte[this.b * this.c];
      byte[] var4 = this.e;
      if(!this.a.equals(ao.d)) {
         com.corrodinggames.rts.game.b.b var5 = this.q.q;
         am[] var6 = am.bE.a();
         int var7 = 0;

         for(int var8 = am.bE.size(); var7 < var8; ++var7) {
            am var9 = var6[var7];
            if(var9.bI() && !var9.bV) {
               Point var10 = var9.a(var5, this.n);
               int var11 = var10.a;
               int var12 = var10.b;
               Rect var13;
               if(this.a.equals(ao.c)) {
                  var13 = var9.cd();
               } else {
                  var13 = var9.cc();
               }

               for(int var14 = var11 + var13.a; var14 <= var11 + var13.c; ++var14) {
                  for(int var15 = var12 + var13.b; var15 <= var12 + var13.d; ++var15) {
                     if(var5.c(var14, var15)) {
                        var4[var14 * this.c + var15] = -1;
                     }
                  }
               }
            }
         }

      }
   }

   public strictfp void e() {
      long var1 = com.corrodinggames.rts.gameFramework.l.V();
      int var3 = this.c;
      this.f = new byte[this.b * var3];
      if(!this.a.equals(ao.d)) {
         com.corrodinggames.rts.game.b.b var4 = this.q.q;
         int var5 = var4.p;
         int var6 = var4.q;
         am[] var7 = am.bE.a();
         int var8 = 0;

         for(int var9 = am.bE.size(); var8 < var9; ++var8) {
            am var10 = var7[var8];
            if(var10.bT && !var10.bI() && !var10.cK && !(var10 instanceof al) && !var10.bV && !var10.i() && var10.cN == null && !var10.Q()) {
               byte var11 = 2;
               var4.a(var10.eo, var10.ep);
               int var12 = var4.T;
               int var13 = var4.U;
               float var14 = var10.cj + 5.0F;
               float var15 = var10.cj + 10.0F;
               if(var15 < 10.0F) {
                  var11 = 0;
               } else if(var15 < 20.0F) {
                  var11 = 1;
               }

               for(int var16 = var12 - var11; var16 <= var12 + var11; ++var16) {
                  for(int var17 = var13 - var11; var17 <= var13 + var11; ++var17) {
                     if(var4.c(var16, var17)) {
                        var4.a(var16, var17);
                        float var18 = (float)(var4.T + var5);
                        float var19 = (float)(var4.U + var6);
                        float var20 = com.corrodinggames.rts.gameFramework.f.a(var18, var19, var10.eo, var10.ep);
                        byte var21 = 6;
                        int var22 = var16 * var3 + var17;
                        if(var20 < var14 * var14) {
                           this.f[var22] += var21;
                        } else if(var20 < var15 * var15) {
                           this.f[var22] = (byte)((int)((double)this.f[var22] + (double)var21 * 0.333D));
                        }

                        if(this.f[var22] < -1) {
                           this.f[var22] = 127;
                        }
                     }
                  }
               }
            }
         }

      }
   }

   public strictfp void a(boolean var1) {
      if(var1) {
         if(this.m) {
            this.l = 0;
            this.m = false;
            this.c((y)null);
            if(this.j != null) {
               this.a((y)null);
            }
         }

         this.l = 0;
      }
   }
}
