/*
 * v19.115i 重建: 02b gameFramework.ab.java 259 行直译 (原 03 CFR 版字段/参数类型错位:
 * a/g 字段误标 WorldGenerator, a(GameSaver,RenderThread) 参数错位; 02b 锚点: javap ab)
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.Iterator;

public class ProjectileManager {

   com.corrodinggames.rts.gameFramework.utility.CustomArrayList a;
   boolean b;
   float c;
   float d;
   int e;
   boolean f;
   public com.corrodinggames.rts.gameFramework.utility.CustomArrayList g;
   // $FF: synthetic field
   final ProjectileWeapon h;

   public ProjectileManager(ProjectileWeapon var1) {
      this.h = var1;
      this.a = new com.corrodinggames.rts.gameFramework.utility.CustomArrayList();
   }

   public void a(UnitType var1, WeaponAction var2) {   // 02b L27: 绑定武器
      var2.i = this;
      this.f = var2.j;
   }

   public void a(WeaponAction var1) {   // 02b L32: 武器射击命中检测
      Iterator var2 = this.a.iterator();
      while(var2.hasNext()) {
         UnitType var3 = (UnitType)var2.next();
         if(!var3.isDead) {   // 02b bV
            WeaponAction var4 = var3.ar();
            if(var4 != null && var4.b(var1)) {
               var3.ay();
            }
         }
      }
   }

   public void a() {   // 02b L47: 收集活跃武器单位
      this.a.clear();
      UnitInstance[] var1 = UnitInstance.bE.a();
      int var2 = 0;
      for(int var3 = UnitInstance.bE.size(); var2 < var3; ++var2) {
         UnitInstance var4 = var1[var2];
         if(var4 instanceof UnitType) {
            UnitType var5 = (UnitType)var4;
            if(var5.I()) {
               WeaponAction var6 = var5.ar();
               if(var6 != null && var6.i == this && var5.bg()) {
                  this.a.add(var5);
                  this.c = var6.g();
                  this.d = var6.h();
               }
            }
         }
      }
   }

   public void a(UnitType var1) {   // 02b L69
      var1.ac = this.e;
      WeaponAction var2 = var1.ar();
      if(var2 != null) {
         var2.i = this;
      }
   }

   public void b() {   // 02b L78
      TimeUtils.a();
      this.c();
   }

   public UnitType a(CustomArrayList var1, float var2, float var3, boolean var4) {   // 02b L83: 最近单位
      float var5 = -1.0F;
      UnitType var6 = null;
      Iterator var7 = var1.iterator();
      while(var7.hasNext()) {
         UnitType var8 = (UnitType)var7.next();
         if(var4 || var8.ad == null && !var8.ae) {
            float var9 = GameUtils.b(var2, var3, var8.eo, var8.ep);
            if(var8.af) {
               var9 -= 160.0F;
            }
            if(var5 == -1.0F || var9 < var5) {
               var5 = var9;
               var6 = var8;
            }
         }
      }
      return var6;
   }

   public CustomArrayList a(float var1, float var2, boolean var3) {   // 02b L106: 范围内单位组
      CustomArrayList var4 = new CustomArrayList(1);
      CustomArrayList var5 = new CustomArrayList();
      var5.clear();
      var5.addAll(this.a);
      while(true) {
         UnitType var6 = this.a(var5, var1, var2, true);
         if(var6 == null) {
            return var4;
         }
         var4.add(var6);
         var5.remove(var6);
         CustomArrayList var7 = this.a(var5, var6, true, var3);
         var5.removeAll(var7);
      }
   }

   public CustomArrayList a(CustomArrayList var1, UnitType var2, boolean var3, boolean var4) {   // 02b L125: 邻接分组
      CustomArrayList var5 = new CustomArrayList(1);
      var5.clear();
      int var6 = 0;
      Object[] var9 = var1.a();
      int var10 = 0;
      int var11;
      for(var11 = var1.size(); var10 < var11; ++var10) {
         UnitType var12 = (UnitType)var9[var10];
         var12.ap = false;
      }
      for(var10 = 0; var10 <= 2; ++var10) {
         var11 = 0;
         for(int var15 = var1.size(); var11 < var15; ++var11) {
            UnitType var13 = (UnitType)var9[var11];
            if(!var13.ap && var13 != var2 && (var3 || var13.ad == null && !var13.ae) && var13.h() == var2.h()) {
               float var14 = GameUtils.a(var13.eo, var13.ep, var2.eo, var2.ep);
               if((var10 != 0 || var14 <= 3600.0F) && (var10 != 1 || var14 <= 14400.0F)
                     && (var4 && var14 < 160000.0F || var14 < 40000.0F && var6 < 25)
                     && (var4 || GameUtils.c(var13.z() - var2.z()) < 0.4F)) {
                  var13.ap = true;
                  var5.add(var13);
                  ++var6;
               }
            }
         }
      }
      return var5;
   }

   public void c() {   // 02b L159: 分组中心与再分配
      GlobalState var1 = GlobalState.B();
      long var6 = TimeUtils.a();
      this.a();
      this.h.b.a(0.0F, 0.0F);
      Iterator var8 = this.a.iterator();
      while(var8.hasNext()) {
         UnitType var9 = (UnitType)var8.next();
         this.h.b.b(var9.eo, var9.ep);
      }
      this.h.b.a(this.h.b.a / (float)this.a.size(), this.h.b.b / (float)this.a.size());
      float var24 = GameUtils.d(this.h.b.a, this.h.b.b, this.c, this.d);
      UnitType var10;
      for(Iterator var25 = this.a.iterator(); var25.hasNext(); var10.ac = this.e) {
         var10 = (UnitType)var25.next();
         if(var10.ah > 1) {
            var10.af = var10.ae;
         } else {
            var10.af = false;
         }
         if(var10.af && var10.ah > 7) {
            float var11 = GameUtils.c(var10.am, var24, 360.0F);
            if(GameUtils.c(var11) > 80.0F) {
               var10.af = false;
            }
         }
         var10.aB();
         var10.ah = 0;
         var10.an = var1.by;
      }
      int var26 = 0;
      while(true) {
         var10 = null;
         long var27 = TimeUtils.a();
         var10 = this.a(this.a, this.c, this.d, false);
         if(var10 == null) {
            return;
         }
         var10.ae = true;
         ProjectileManager var13 = null;
         if(var26 > 0) {
            var13 = this.h.b();
         }
         if(var13 != null) {
            var13.g = this.g;
            var13.a(var10);
         }
         CustomArrayList var14 = this.a(this.a, var10, false, this.f);
         int var15 = 0;
         float var16 = 0.0F;
         for(Iterator var17 = var14.iterator(); var17.hasNext(); ++var15) {
            UnitType var18 = (UnitType)var17.next();
            if(var18.cj > var16) {
               var16 = var18.cj;
            }
            var18.a(var10);
            if(var13 != null) {
               var13.a(var18);
            }
         }
         if(var10 != null) {
            var10.ah = (short)(var15 + 1);
         }
         CustomArrayList var28 = new CustomArrayList();
         Object[] var29 = this.a.a();
         int var19 = 0;
         for(int var20 = this.a.size(); var19 < var20; ++var19) {
            UnitType var21 = (UnitType)var29[var19];
            if(var21.ad == var10) {
               var28.add(var21);
            }
         }
         CustomArrayList var30 = this.h.a(var15, var16, var24);
         long var31 = TimeUtils.a();
         this.h.a(var28, var10, var30, var24, var15);
         long var22 = TimeUtils.a();
         this.h.a(var28, var10);
         ++var26;
      }
   }
}
