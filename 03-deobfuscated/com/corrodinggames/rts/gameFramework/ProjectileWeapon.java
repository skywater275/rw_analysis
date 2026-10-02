/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ProjectileManager;
import com.corrodinggames.rts.gameFramework.BaseGameObject;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;

public class ProjectileWeapon
extends BaseGameObject {
    int a;
    public void a(OutputNetStream as2) {  // 02b aa.java L19-22
        as2.a(0);
        as2.a(this.a);
    }

    PointF b = new PointF();

    public void a() {
        this.a = 1;
    }


    @Override
    public void serializeToStream(com.corrodinggames.rts.gameFramework.network.OutputNetStream as2) {  // 02 铁证: a(j.as)
        as2.a(0);
        as2.a(this.a);
    }

    public void a(InputNetStream k2) {
        k2.readInt();
        this.a = k2.readInt();
    }

    public void a(float f) {
    }

    public ProjectileManager b() {
        ProjectileManager ab2 = new ProjectileManager(this);
        ab2.e = this.a++;
        return ab2;
    }

    public ProjectileManager c() {
        ProjectileManager ab2 = new ProjectileManager(this);
        ab2.e = -1;
        ab2.b = true;
        return ab2;
    }



    public CustomArrayList a(int n2, float f2, float f3) {   // 02b aa.a(int,f,f) 返回 utility.m (v19.115i: 原 WorldGenerator 错位)
        int n3 = 1;
        int n4 = 0;
        int n5 = 6;
        int n6 = n5 / 2;
        float f4 = 2.0f + f2 * 2.0f * 1.5f;
        CustomArrayList m2 = new CustomArrayList();
        int n7 = n2;
        if (n7 % 2 != 0) {
            ++n7;
        }
        float f5 = GameUtils.cosFast(f3);
        float f6 = GameUtils.sinFast(f3);
        for (int i2 = 0; i2 < n7; ++i2) {
            int n8 = n3 % 2 == 0 ? n6 + n3 / 2 : n6 - (n3 + 1) / 2;
            float f7 = (float)(n8 - n6) * f4;
            float f8 = (float)(-n4) * f4;
            float f9 = f8 * f5 - f7 * f6;
            float f10 = f7 * f5 + f8 * f6;
            m2.add(new PointF(f9, f10));
            if (++n3 <= n5) continue;
            n3 = 0;
            ++n4;
        }
        return m2;
    }


   // 02b aa.a(m,y,m,f,int) L44-98 直译: 分配单位到轨迹 (03 现有 WorldGenerator 版参数错位, 补 CustomArrayList 版共存)
   public void a(com.corrodinggames.rts.gameFramework.utility.CustomArrayList var1, UnitType var2,
                 com.corrodinggames.rts.gameFramework.utility.CustomArrayList var3, float var4, int var5) {
      int var6 = 0;
      while(!var3.isEmpty()) {
         UnitType var7 = null;
         float var8 = -1.0F;
         android.graphics.PointF var9 = null;
         int var10 = -1;
         Object[] var11 = var3.a();
         Object[] var12 = var1.a();
         int var13 = 0;
         for(int var14 = var1.size(); var13 < var14; ++var13) {
            UnitType var15 = (UnitType)var12[var13];
            if(var15.ad == var2 && !var15.aj) {
               float var16 = -1.0F;
               android.graphics.PointF var17 = null;
               int var18 = -1;
               for(int var19 = 0; var19 < var3.a; ++var19) {
                  android.graphics.PointF var20 = (android.graphics.PointF)var11[var19];
                  float var21 = var2.eo + var20.a;
                  float var22 = var2.ep + var20.b;
                  float var23 = GameUtils.a(var15.eo, var15.ep, var21, var22);
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

   // 02b aa.a(m,y) L100-151 直译: 轨迹重排 (与现有 a(WorldGenerator,UnitType) 共存)
   public void a(com.corrodinggames.rts.gameFramework.utility.CustomArrayList var1, UnitType var2) {
      while(true) {
         UnitType var4 = null;
         java.util.Iterator var5 = var1.iterator();
         while(var5.hasNext()) {
            UnitType var6 = (UnitType)var5.next();
            if(var6.ad == var2 && var6.ao > 0.0F && (var4 == null || var6.ao > var4.ao) && var6.aj && var6.ao > 100.0F) {
               var4 = var6;
            }
         }
         if(var4 == null) {
            return;
         }
         var4.aj = false;
         UnitType var14 = null;
         float var15 = 0.0F;
         UnitType var7 = var4;
         int var8 = GameUtils.a((int)var4.ao);
         java.util.Iterator var9 = var1.iterator();
         while(var9.hasNext()) {
            UnitType var10 = (UnitType)var9.next();
            if(var10.ad == var2 && var10.ao > 0.0F && var10 != var7) {
               int var11 = GameUtils.a((int)var10.ao) + var8;
               int var18 = 0 + GameUtils.c(var7.eo, var7.ep, var2.eo + var10.ak, var2.ep + var10.al);
               var18 += GameUtils.c(var10.eo, var10.ep, var2.eo + var7.ak, var2.ep + var7.al);
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
            var7.ao = GameUtils.a(var7.eo, var7.ep, var2.eo + var7.ak, var2.ep + var7.al);
            var14.ak = var16;
            var14.al = var17;
            var14.ao = GameUtils.a(var14.eo, var14.ep, var2.eo + var14.ak, var2.ep + var14.al);
         }
      }
   }

}
