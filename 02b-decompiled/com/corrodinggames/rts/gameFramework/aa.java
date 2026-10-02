package com.corrodinggames.rts.gameFramework;

import android.graphics.PointF;
import com.corrodinggames.rts.gameFramework.ab;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.f;
import java.util.Iterator;

public class aa extends bq {

   int a;
   PointF b = new PointF();


   public void a() {
      this.a = 1;
   }

   public void a(com.corrodinggames.rts.gameFramework.j.as var1) {
      var1.a((int)0);
      var1.a(this.a);
   }

   public void a(com.corrodinggames.rts.gameFramework.j.k var1) {
      var1.f();
      this.a = var1.f();
   }

   public void a(float var1) {}

   public ab b() {
      ab var1 = new ab(this);
      var1.e = this.a++;
      return var1;
   }

   public ab c() {
      ab var1 = new ab(this);
      var1.e = -1;
      var1.b = true;
      return var1;
   }

   public void a(com.corrodinggames.rts.gameFramework.utility.m var1, com.corrodinggames.rts.game.units.y var2, com.corrodinggames.rts.gameFramework.utility.m var3, float var4, int var5) {
      int var6 = 0;

      while(!var3.isEmpty()) {
         com.corrodinggames.rts.game.units.y var7 = null;
         float var8 = -1.0F;
         PointF var9 = null;
         int var10 = -1;
         Object[] var11 = var3.a();
         Object[] var12 = var1.a();
         int var13 = 0;

         for(int var14 = var1.size(); var13 < var14; ++var13) {
            com.corrodinggames.rts.game.units.y var15 = (com.corrodinggames.rts.game.units.y)var12[var13];
            if(var15.ad == var2 && !var15.aj) {
               float var16 = -1.0F;
               PointF var17 = null;
               int var18 = -1;

               for(int var19 = 0; var19 < var3.a; ++var19) {
                  PointF var20 = (PointF)var11[var19];
                  float var21 = var2.eo + var20.a;
                  float var22 = var2.ep + var20.b;
                  float var23 = f.a(var15.eo, var15.ep, var21, var22);
                  if(var16 == -1.0F || var23 < var16) {
                     var16 = var23;
                     var17 = var20;
                     var18 = var19;
                  }
               }

               if(var16 > var8) {
                  var7 = var15;
                  var8 = var16;
                  var9 = var17;
                  var10 = var18;
               }
            }
         }

         if(var7 == null) {
            break;
         }

         ++var6;
         var7.aj = true;
         var7.ak = var9.a;
         var7.al = var9.b;
         var7.am = var4;
         var7.ao = var8;
         var7.ah = (short)(var5 + 1);
         var3.remove(var10);
      }

   }

   public void a(com.corrodinggames.rts.gameFramework.utility.m var1, com.corrodinggames.rts.game.units.y var2) {
      boolean var3 = false;

      while(true) {
         com.corrodinggames.rts.game.units.y var4 = null;
         Iterator var5 = var1.iterator();

         while(var5.hasNext()) {
            com.corrodinggames.rts.game.units.y var6 = (com.corrodinggames.rts.game.units.y)var5.next();
            if(var6.ad == var2 && var6.ao > 0.0F && (var4 == null || var6.ao > var4.ao) && var6.aj && var6.ao > 100.0F) {
               var4 = var6;
            }
         }

         if(var4 == null) {
            return;
         }

         var4.aj = false;
         com.corrodinggames.rts.game.units.y var14 = null;
         float var15 = 0.0F;
         com.corrodinggames.rts.game.units.y var7 = var4;
         int var8 = f.a((int)var4.ao);
         Iterator var9 = var1.iterator();

         while(var9.hasNext()) {
            com.corrodinggames.rts.game.units.y var10 = (com.corrodinggames.rts.game.units.y)var9.next();
            if(var10.ad == var2 && var10.ao > 0.0F && var10 != var7) {
               int var11 = f.a((int)var10.ao) + var8;
               byte var12 = 0;
               int var18 = var12 + f.c(var7.eo, var7.ep, var2.eo + var10.ak, var2.ep + var10.al);
               var18 += f.c(var10.eo, var10.ep, var2.eo + var7.ak, var2.ep + var7.al);
               float var13 = (float)(var18 - var11);
               if(var13 < var15) {
                  var15 = var13;
                  var14 = var10;
               }
            }
         }

         if(var14 != null) {
            float var16 = var7.ak;
            float var17 = var7.al;
            var7.ak = var14.ak;
            var7.al = var14.al;
            var7.ao = f.a(var7.eo, var7.ep, var2.eo + var7.ak, var2.ep + var7.al);
            var14.ak = var16;
            var14.al = var17;
            var14.ao = f.a(var14.eo, var14.ep, var2.eo + var14.ak, var2.ep + var14.al);
         }
      }
   }

   public com.corrodinggames.rts.gameFramework.utility.m a(int var1, float var2, float var3) {
      int var4 = 1;
      int var5 = 0;
      byte var6 = 6;
      int var7 = var6 / 2;
      float var8 = 2.0F + var2 * 2.0F * 1.5F;
      com.corrodinggames.rts.gameFramework.utility.m var9 = new com.corrodinggames.rts.gameFramework.utility.m();
      int var10 = var1;
      if(var1 % 2 != 0) {
         var10 = var1 + 1;
      }

      float var11 = f.k(var3);
      float var12 = f.j(var3);

      for(int var13 = 0; var13 < var10; ++var13) {
         int var14;
         if(var4 % 2 == 0) {
            var14 = var7 + var4 / 2;
         } else {
            var14 = var7 - (var4 + 1) / 2;
         }

         float var15 = (float)(var14 - var7) * var8;
         float var16 = (float)(-var5) * var8;
         float var17 = var16 * var11 - var15 * var12;
         float var18 = var15 * var11 + var16 * var12;
         var9.add(new PointF(var17, var18));
         ++var4;
         if(var4 > var6) {
            var4 = 0;
            ++var5;
         }
      }

      return var9;
   }
}
