package com.corrodinggames.rts.game.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.i;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.j.as;
import java.util.ArrayList;

public abstract class o extends bq {

   public int Q;
   protected final a R;
   public float S;
   public float T;
   public float U;
   public boolean V;
   static final ArrayList W = new ArrayList();


   public void a(as var1) {
      var1.a(this.S);
      var1.a(this.T);
      var1.a(this.U);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      this.S = var1.g();
      this.T = var1.g();
      this.U = var1.g();
   }

   public o(a var1) {
      ++var1.aI;
      this.Q = var1.aI;
      this.R = var1;
      this.R.bm.add(this);
      this.R.bn.add(this);
   }

   public o(a var1, float var2, float var3) {
      this(var1);
      this.S = var2;
      this.T = var3;
   }

   public void p() {
      this.R.bm.remove(this);
      this.R.bn.remove(this);
      this.V = true;
   }

   public boolean c(float var1, float var2) {
      float var3 = com.corrodinggames.rts.gameFramework.f.a(this.S, this.T, var1, var2);
      float var4 = this.U;
      return var3 < var4 * var4;
   }

   public boolean b(am var1) {
      float var2 = com.corrodinggames.rts.gameFramework.f.a(this.S, this.T, var1.eo, var1.ep);
      float var3 = this.U + var1.cj;
      return var2 < var3 * var3;
   }

   public boolean a(am var1, float var2) {
      float var3 = com.corrodinggames.rts.gameFramework.f.a(this.S, this.T, var1.eo, var1.ep);
      float var4 = this.U + var1.cj + var2;
      return var3 < var4 * var4;
   }

   public float c(am var1) {
      return com.corrodinggames.rts.gameFramework.f.a(this.S, this.T, var1.eo, var1.ep);
   }

   public float a(i var1) {
      return com.corrodinggames.rts.gameFramework.f.a(this.S, this.T, var1.S, var1.T);
   }

   public float d(float var1, float var2) {
      return com.corrodinggames.rts.gameFramework.f.a(this.S, this.T, var1, var2);
   }

   public PointF w() {
      PointF var1 = new PointF();
      float var2 = (float)(Math.random() * 360.0D);
      float var3 = (float)(Math.random() * (double)this.U);
      var1.a(this.S + com.corrodinggames.rts.gameFramework.f.k(var2) * var3, this.T + com.corrodinggames.rts.gameFramework.f.j(var2) * var3);
      return var1;
   }

   public PointF e(com.corrodinggames.rts.game.units.as var1) {
      com.corrodinggames.rts.gameFramework.l var2 = com.corrodinggames.rts.gameFramework.l.B();
      PointF var3 = new PointF();
      float var4 = this.U;
      ao var5 = ao.b;
      am var6 = null;
      if(var1 == ar.d) {
         var4 = 600.0F;
         var5 = ao.e;
      }

      for(int var7 = 0; var7 < 15; ++var7) {
         ar var8 = null;
         boolean var9 = false;
         boolean var10 = false;
         int var13;
         if(this instanceof i) {
            i var11 = (i)this;
            if(var7 < 6 && var1 == ar.J) {
               var8 = ar.J;
            }

            if(var8 != null) {
               y var12 = null;
               if(var6 == null) {
                  var6 = am.c(var1);
               }

               if(var6 instanceof y) {
                  var12 = (y)var6;
               }

               var13 = var11.c(var8);
               if(var12 != null && var13 > 1) {
                  int var14 = -1;
                  int var15 = com.corrodinggames.rts.gameFramework.f.a(0, var13 - 1);
                  am[] var16 = am.bE.a();
                  int var17 = 0;

                  for(int var18 = am.bE.size(); var17 < var18; ++var17) {
                     am var19 = var16[var17];
                     if(var19.bX == this.R && var11.a(var19) && var19.bT() && this.R.i(var19) && var19.r() == var8) {
                        ++var14;
                        if(var14 == var15) {
                           float var20 = var19.eo;
                           float var21 = var19.ep;
                           boolean var22 = com.corrodinggames.rts.gameFramework.f.a(0, 1) == 0;
                           float var23 = var20;
                           float var24 = var21;
                           if(var22) {
                              var24 = var21 + com.corrodinggames.rts.gameFramework.f.c(-150.0F, 150.0F);
                           } else {
                              var23 = var20 + com.corrodinggames.rts.gameFramework.f.c(-150.0F, 150.0F);
                           }

                           boolean var25 = false;
                           W.clear();
                           Object var26 = null;
                           var2.bS.a(var12, var20, var21, var23, var24, var25, W, (am)var26);
                           if(W.size() > 0) {
                              PointF var27 = (PointF)W.get(0);
                              var3.a(var27.a, var27.b);
                              var9 = true;
                           } else {
                              var10 = true;
                           }
                        }
                     }
                  }
               }
            }
         }

         if(!var10) {
            if(!var9) {
               float var28 = (float)(Math.random() * 360.0D);
               float var30 = (float)(Math.random() * (double)var4);
               var3.a(this.S + com.corrodinggames.rts.gameFramework.f.k(var28) * var30, this.T + com.corrodinggames.rts.gameFramework.f.j(var28) * var30);
            }

            var2.bL.a(var3.a, var3.b);
            int var29 = var2.bL.T;
            int var31 = var2.bL.U;
            if(var2.bL.c(var29, var31)) {
               var13 = com.corrodinggames.rts.gameFramework.utility.y.c((float)var29, (float)var31, var5);
               if((var13 > 5 || var13 == 0) && com.corrodinggames.rts.game.units.d.d.a(var1, var3.a, var3.b, this.R)) {
                  return var3;
               }
            }

            if(var1 == ar.d) {
               var4 += 100.0F;
            }
         }
      }

      return null;
   }

}
