package com.corrodinggames.rts.game.units.custom.c;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.c.a;
import com.corrodinggames.rts.game.units.custom.c.d;
import com.corrodinggames.rts.game.units.custom.c.e;
import com.corrodinggames.rts.game.units.custom.c.f;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.j.m;
import com.corrodinggames.rts.gameFramework.m.ag;
import java.util.Iterator;

public class c {

   static final Rect a = new Rect();
   static final RectF b = new RectF();
   static final Paint c = new Paint();
   com.corrodinggames.rts.gameFramework.utility.m d = new com.corrodinggames.rts.gameFramework.utility.m();
   static Paint e = new Paint();
   public static f f;


   public strictfp void a(l var1) {
      Object[] var2 = this.d.a();

      for(int var3 = this.d.a - 1; var3 >= 0; --var3) {
         e var4 = (e)var2[var3];
         a var5 = var1.a(var4.a.g);
         if(var5 != null) {
            var4.a = var5;

            while(var4.b.size() > var4.a.d) {
               var4.b.remove(var4.b.size() - 1);
            }
         } else {
            this.d.remove(var3);
         }
      }

   }

   public strictfp e a(a var1, boolean var2) {
      int var3 = this.d.a;
      Object[] var4 = this.d.a();

      for(int var5 = 0; var5 < var3; ++var5) {
         e var6 = (e)var4[var5];
         if(var6.a == var1) {
            return var6;
         }
      }

      if(var2) {
         e var7 = new e(var1);
         this.d.add(var7);
         return var7;
      } else {
         return null;
      }
   }

   public strictfp int a(a var1) {
      e var2 = this.a(var1, false);
      return var2 == null?0:var2.b.a;
   }

   public strictfp void a(float var1, am var2) {
      com.corrodinggames.rts.gameFramework.l var3 = com.corrodinggames.rts.gameFramework.l.B();
      int var4 = this.d.a;
      if(var4 != 0) {
         Object[] var5 = this.d.a();

         for(int var6 = 0; var6 < var4; ++var6) {
            e var7 = (e)var5[var6];
            a var8 = var7.a;
            int var9 = var7.b.a;
            Object[] var10 = var7.b.a();

            for(int var11 = var9 - 1; var11 >= 0; --var11) {
               d var12 = (d)var10[var11];
               if(var12.c) {
                  am var13 = var12.a;
                  if(var8.e != null) {
                     com.corrodinggames.rts.gameFramework.m.e var14 = var8.e;
                     float var15 = var2.eo - com.corrodinggames.rts.gameFramework.l.B().cw;
                     float var16 = var2.ep - com.corrodinggames.rts.gameFramework.l.B().cx - var2.eq - 10.0F;
                     float var17 = var14.u;
                     float var18 = com.corrodinggames.rts.gameFramework.f.d(var2.eo, var2.ep - var2.eq, var13.eo, var13.ep - var13.eq);
                     float var19 = com.corrodinggames.rts.gameFramework.f.a(var2.eo, var2.ep - var2.eq, var13.eo, var13.ep - var13.eq);
                     if(var19 < (float)((var14.q - 2) * (var14.q - 2))) {
                        var17 = (float)com.corrodinggames.rts.gameFramework.f.a((int)var19);
                     }

                     var3.bO.k();
                     var3.bO.a(var18 + 90.0F, var15, var16);
                     a.a(0, (int)((float)var14.q - var17), var14.p, var14.q);
                     b.a(var15 - (float)var14.r, var16 - var17, var15 + (float)var14.r, var16);
                     Object var20 = ag.r;
                     if(var12.d != 0.0F) {
                        var20 = c;
                        int var21 = (int)Math.abs(var12.d * 5.0F);
                        if(var21 > 250) {
                           var21 = 250;
                        }

                        ((Paint)var20).a(255, 255, 255 - var21, 255 - var21);
                     }

                     var3.bO.a(var14, a, b, (Paint)var20);
                     var3.bO.l();
                  }

                  if(var8.f != null) {
                     var3.bO.a(var2.eo - var3.cw, var2.ep - var3.cx - var2.eq, var13.eo - var3.cw, var13.ep - var3.cx - var13.eq, var8.f);
                  }
               }
            }
         }

      }
   }

   public strictfp void a(as var1) {
      if(this.d.a == 0) {
         var1.c(-1);
      } else {
         var1.c(0);
         short var2 = (short)this.d.size();
         var1.a(var2);
         Object[] var3 = this.d.a();

         for(int var4 = 0; var4 < var2; ++var4) {
            e var5 = (e)var3[var4];
            var1.a(var5.a.g);
            var1.a((short)var5.b.size());
            Iterator var6 = var5.b.iterator();

            while(var6.hasNext()) {
               d var7 = (d)var6.next();
               var1.a(var7.a);
               var1.a(var7.b);
               var1.a(var7.c);
            }
         }

      }
   }

   public strictfp void a(am var1, k var2) {
      byte var3 = var2.d();
      if(var3 != -1) {
         short var4 = var2.v();
         this.d.clear();

         for(int var5 = 0; var5 < var4; ++var5) {
            g var6 = var2.m();
            a var7 = null;
            if(var1 instanceof j) {
               var7 = ((j)var1).x.a(var6);
            }

            e var8 = null;
            if(var7 != null) {
               var8 = new e(var7);
               this.d.add(var8);
            }

            short var9 = var2.v();

            for(int var10 = 0; var10 < var9; ++var10) {
               d var11 = new d();
               var11.a = var2.a(m.a);
               var11.b = var2.e();
               var11.c = var2.e();
               if(var11.a != null && var8 != null) {
                  var8.b.add(var11);
               }
            }
         }

      }
   }

   static {
      e.a(255, 0, 0, 200);
      f = new f();
   }
}
