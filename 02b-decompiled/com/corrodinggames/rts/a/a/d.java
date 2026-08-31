package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.a.a.n;
import com.corrodinggames.rts.gameFramework.br;

public class d extends l {

   int a;


   public void a() {
      com.corrodinggames.rts.gameFramework.l.e("Running unit tests - maths (v3)");
      com.corrodinggames.rts.gameFramework.f.i(100.0F, 100.0F);
      com.corrodinggames.rts.gameFramework.f.i(0.0F, 100.0F);
      com.corrodinggames.rts.gameFramework.f.i(100.0F, 0.0F);
      com.corrodinggames.rts.gameFramework.f.i(0.0F, -100.0F);
      com.corrodinggames.rts.gameFramework.f.i(-100.0F, 0.0F);
      com.corrodinggames.rts.gameFramework.f.i(0.0F, 0.0F);
      com.corrodinggames.rts.gameFramework.l.e("fast_atan2 - NaN");
      com.corrodinggames.rts.gameFramework.f.i(Float.NaN, 0.0F);
      com.corrodinggames.rts.gameFramework.f.i(0.0F, Float.NaN);
      com.corrodinggames.rts.gameFramework.f.i(Float.NaN, Float.NaN);
      com.corrodinggames.rts.gameFramework.l.e("fast_atan2 - Max");
      com.corrodinggames.rts.gameFramework.f.i(Float.MAX_VALUE, 0.0F);
      com.corrodinggames.rts.gameFramework.f.i(Float.MIN_VALUE, 0.0F);
      com.corrodinggames.rts.gameFramework.f.i(0.0F, Float.MAX_VALUE);
      com.corrodinggames.rts.gameFramework.f.i(0.0F, Float.MIN_VALUE);
      com.corrodinggames.rts.gameFramework.l.e("fast_atan2 - NaN+Max");
      com.corrodinggames.rts.gameFramework.f.i(Float.MAX_VALUE, Float.NaN);
      com.corrodinggames.rts.gameFramework.f.i(Float.MIN_VALUE, Float.MAX_VALUE);
      com.corrodinggames.rts.gameFramework.f.i(Float.MAX_VALUE, Float.MIN_VALUE);
      com.corrodinggames.rts.gameFramework.f.i(900000.0F, 900000.0F);
      com.corrodinggames.rts.gameFramework.f.i(3.4028236E33F, 3.4028236E33F);
      com.corrodinggames.rts.gameFramework.f.i(3.4028236E34F, 3.4028236E34F);
      com.corrodinggames.rts.gameFramework.f.i(3.4028234E35F, 3.4028234E35F);
      com.corrodinggames.rts.gameFramework.f.i(3.4028236E36F, 3.4028236E36F);
      com.corrodinggames.rts.gameFramework.f.i(3.4028235E37F, 3.4028235E37F);
      com.corrodinggames.rts.gameFramework.f.i(Float.MAX_VALUE, Float.MAX_VALUE);
      com.corrodinggames.rts.gameFramework.l.e("fast_atan2 - max,max");
      com.corrodinggames.rts.gameFramework.f.i(Float.MAX_VALUE, Float.MAX_VALUE);
      com.corrodinggames.rts.gameFramework.f.i(Float.MIN_VALUE, Float.MIN_VALUE);
      com.corrodinggames.rts.gameFramework.l.e("cos/sin");
      n.a(com.corrodinggames.rts.gameFramework.f.k(0.0F), 1.0F);
      n.a(com.corrodinggames.rts.gameFramework.f.k(360.0F), 1.0F);
      n.a(com.corrodinggames.rts.gameFramework.f.k(10800.0F), 1.0F);
      n.a(com.corrodinggames.rts.gameFramework.f.k(45.0F), 0.70710677F);
      n.a(com.corrodinggames.rts.gameFramework.f.k(90.0F), 0.0F);
      n.a(com.corrodinggames.rts.gameFramework.f.k(450.0F), 0.0F);
      n.a(com.corrodinggames.rts.gameFramework.f.k(10890.0F), 0.0F);
      n.a(com.corrodinggames.rts.gameFramework.f.j(0.0F), 0.0F);
      n.a(com.corrodinggames.rts.gameFramework.f.j(90.0F), 1.0F);
      com.corrodinggames.rts.gameFramework.f.k(-999999.0F);
      com.corrodinggames.rts.gameFramework.f.k(999999.0F);
      com.corrodinggames.rts.gameFramework.f.k(Float.MAX_VALUE);
      com.corrodinggames.rts.gameFramework.f.k(Float.MIN_VALUE);
      com.corrodinggames.rts.gameFramework.f.j(Float.MAX_VALUE);
      com.corrodinggames.rts.gameFramework.f.j(Float.MIN_VALUE);
      com.corrodinggames.rts.gameFramework.l.e("diff sin(0):  " + String.format("%.12f", new Object[]{Float.valueOf(com.corrodinggames.rts.gameFramework.f.j(0.0F) - (float)StrictMath.sin(0.0D))}));
      com.corrodinggames.rts.gameFramework.l.e("diff sin(45): " + String.format("%.12f", new Object[]{Float.valueOf(com.corrodinggames.rts.gameFramework.f.j(45.0F) - (float)StrictMath.sin(0.7853981633974483D))}));
      com.corrodinggames.rts.gameFramework.l.e("diff sin(90): " + String.format("%.12f", new Object[]{Float.valueOf(com.corrodinggames.rts.gameFramework.f.j(90.0F) - (float)StrictMath.sin(1.5707963267948966D))}));
      com.corrodinggames.rts.gameFramework.l.e("diff sin(180):" + String.format("%.12f", new Object[]{Float.valueOf(com.corrodinggames.rts.gameFramework.f.j(180.0F) - (float)StrictMath.sin(3.141592653589793D))}));
      com.corrodinggames.rts.gameFramework.l.e("diff sin(360):" + String.format("%.12f", new Object[]{Float.valueOf(com.corrodinggames.rts.gameFramework.f.j(360.0F) - (float)StrictMath.sin(6.283185307179586D))}));
      com.corrodinggames.rts.gameFramework.l.e("Testing squareroot");

      for(int var1 = 0; var1 < 1005; ++var1) {
         n.a((float)com.corrodinggames.rts.gameFramework.f.a(var1), com.corrodinggames.rts.gameFramework.f.d(com.corrodinggames.rts.gameFramework.f.a((float)var1)));
      }

      byte var7 = 5;
      int var2 = 0;
      com.corrodinggames.rts.gameFramework.l.e("=== cos/sin tests (runs:" + var7 + ")");
      Long var3 = Long.valueOf(br.a());

      for(int var4 = 0; var4 < var7; ++var4) {
         for(int var5 = 0; var5 < 2000; ++var5) {
            if(com.corrodinggames.rts.gameFramework.f.k((float)var5) == 0.0F) {
               ++var2;
            }

            if(com.corrodinggames.rts.gameFramework.f.j((float)var5) == 0.0F) {
               ++var2;
            }
         }
      }

      Long var8 = Long.valueOf(br.a());
      double var9 = br.a(var3.longValue(), var8.longValue());
      this.a += var2;
      com.corrodinggames.rts.gameFramework.l.e("Took: " + var9);
   }
}
