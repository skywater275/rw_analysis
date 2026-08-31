package com.corrodinggames.rts.gameFramework.k;

import com.corrodinggames.rts.gameFramework.k.n;
import com.corrodinggames.rts.gameFramework.k.o;

public final class p {

   public short a;
   public short b;


   public strictfp p() {}

   public strictfp p(short var1, short var2) {
      this.a(var1, var2);
   }

   public final strictfp p a(short var1, short var2) {
      this.a = var1;
      this.b = var2;
      return this;
   }

   public final strictfp p a(p var1) {
      this.a = var1.a;
      this.b = var1.b;
      return this;
   }

   public final strictfp p a(n var1) {
      this.a = var1.a;
      this.b = var1.b;
      return this;
   }

   public final strictfp int a(o var1) {
      short var2 = this.a;
      short var3 = this.b;
      return var1.b[var2 * var1.h + var3] != -1 && var1.c[var2 * var1.h + var3] != -1 && var1.d[var2 * var1.h + var3] != -1?var1.b[var2 * var1.h + var3] + var1.c[var2 * var1.h + var3] + var1.d[var2 * var1.h + var3] * 10:-1;
   }

   public final strictfp int a(o var1, byte var2) {
      return var1.l[var2][this.a * var1.h + this.b];
   }

   public final strictfp void a(o var1, byte var2, int var3) {
      var1.l[var2][this.a * var1.h + this.b] = var3;
   }

   public final strictfp void a(o var1, byte var2, boolean var3) {
      if(var3) {
         var1.m[var2][this.a * var1.h + this.b] = (byte)(var1.m[var2][this.a * var1.h + this.b] | 16);
      } else {
         var1.m[var2][this.a * var1.h + this.b] &= -17;
      }

   }

   public final strictfp boolean b(o var1, byte var2) {
      return var1.l[var2][this.a * var1.h + this.b] < var1.i?false:(var1.m[var2][this.a * var1.h + this.b] & 16) != 0;
   }

   public final strictfp byte c(o var1, byte var2) {
      return (byte)(var1.m[var2][this.a * var1.h + this.b] & 7);
   }

   public final strictfp boolean d(o var1, byte var2) {
      return (var1.m[var2][this.a * var1.h + this.b] & 8) != 0;
   }

   public final strictfp void b(o var1, byte var2, boolean var3) {
      if(var3) {
         var1.m[var2][this.a * var1.h + this.b] = (byte)(var1.m[var2][this.a * var1.h + this.b] | 8);
      } else {
         var1.m[var2][this.a * var1.h + this.b] &= -9;
      }

   }

   public final strictfp void a(o var1, byte var2, byte var3) {
      var1.m[var2][this.a * var1.h + this.b] &= -16;
      var1.m[var2][this.a * var1.h + this.b] = (byte)(var1.m[var2][this.a * var1.h + this.b] | var3 & 15);
   }

   public final strictfp void a(o var1, byte var2, float var3) {
      int var4 = (int)(var3 / 360.0F * 8.0F + 0.5F);
      if(var4 < 0) {
         var4 += 8;
      }

      if(var4 > 7) {
         var4 -= 8;
      }

      if(var4 < 0) {
         var4 += 8;
      }

      if(var4 > 7) {
         var4 -= 8;
      }

      if(var4 < 0 || var4 > 7) {
         com.corrodinggames.rts.gameFramework.l.e("setCurrentDirectionFromAngle: dir:" + var4 + " direction:" + var3);
         var4 = 0;
      }

      this.a(var1, var2, (byte)var4);
   }

   public final strictfp boolean e(o var1, byte var2) {
      return var1.l[var2][this.a * var1.h + this.b] >= var1.i;
   }

   public final strictfp p f(o var1, byte var2) {
      p var3 = new p();
      return this.a(var1, var2, var3)?var3:null;
   }

   public final strictfp boolean a(o var1, byte var2, p var3) {
      if(!this.e(var1, var2)) {
         var3.a((short)-1, (short)-1);
         return false;
      } else {
         byte var4 = this.c(var1, var2);
         if(this.d(var1, var2)) {
            var3.a((short)-1, (short)-1);
            return false;
         } else {
            int var5 = 0;
            int var6 = 0;
            if(var4 == 0) {
               ++var5;
            }

            if(var4 == 1) {
               ++var5;
               ++var6;
            }

            if(var4 == 2) {
               ++var6;
            }

            if(var4 == 3) {
               ++var6;
               --var5;
            }

            if(var4 == 4) {
               --var5;
            }

            if(var4 == 5) {
               --var5;
               --var6;
            }

            if(var4 == 6) {
               --var6;
            }

            if(var4 == 7) {
               --var6;
               ++var5;
            }

            short var7 = (short)(this.a - var5);
            short var8 = (short)(this.b - var6);
            var3.a(var7, var8);
            return true;
         }
      }
   }
}
