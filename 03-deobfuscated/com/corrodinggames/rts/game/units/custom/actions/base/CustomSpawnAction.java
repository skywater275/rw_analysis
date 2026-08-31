/*
 * v19.115l 重建: 02b custom/a/a/m.java 269 行直译 (CustomSpawnAction takeResources 动作)
 * 类型映射: q=UnitTypeComparator, d.b=resources.CustomActionBase, h=UnitConfig, u=UnitActionDef,
 * n=base/n.java, g=TeamTag, bo=bo, l=GlobalState, m=CustomArrayList
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.ResourceType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitActionDef;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.UnitTypeComparator;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import com.corrodinggames.rts.gameFramework.utility.ab;
import java.util.Iterator;

public class CustomSpawnAction extends ActionBase {

   public CustomActionBase a;
   public boolean b;
   public boolean c;
   public float d = -1.0F;
   public UnitTypeComparator e;
   public LogicBoolean f;
   public UnitConfig g;
   public boolean h = true;
   public CustomActionBase i;
   public UnitReference j;
   public int k = 1;
   public UnitActionDef l;
   public UnitActionDef m;
   public UnitActionDef n;
   public boolean o;
   public boolean p;
   public boolean q;
   public boolean r;
   public boolean s = false;
   public static final CustomArrayList t = new CustomArrayList();
   public static final n u = new n();

   public static void a(ModUnitRegistry var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.actions.d var4, String var5, boolean var6) throws bo {
      boolean var7 = var1.a(var2, var3 + "takeResources_includeUnitsInTransport", Boolean.valueOf(false)).booleanValue();
      boolean var8 = var1.a(var2, var3 + "takeResources_includeParent", Boolean.valueOf(false)).booleanValue();
      LogicBoolean var9 = var1.b(var0, var2, var3 + "takeResources_includeReference", (LogicBoolean)null);
      float var10 = var1.a(var2, var3 + "takeResources_includeUnitsWithinRange", Float.valueOf(-1.0F)).floatValue();
      boolean var11 = var1.a(var2, var3 + "takeResources_searchOnly", Boolean.valueOf(false)).booleanValue();
      CustomActionBase var12 = CustomActionBase.a(var0, var1, var2, var3 + "takeResources", true);
      if(var11 && var12 != null && !var12.c()) {
         throw new bo("[" + var2 + "]takeResources not supported with takeResources_searchOnly");
      } else {
         boolean var13 = var1.a(var2, var3 + "takeResources_saveFirstUnitToCustomTarget1", Boolean.valueOf(false)).booleanValue();
         boolean var14 = var1.a(var2, var3 + "takeResources_saveFirstUnitToCustomTarget2", Boolean.valueOf(false)).booleanValue();
         if(!var7 && !var8 && var10 < 0.0F && var9 == null) {
            if(var12 != null && !var12.c()) {
               throw new bo("[" + var2 + "]takeResources requires an include (eg: takeResources_includeUnitsWithinRange, takeResources_includeUnitsInTransport) otherwise no results would be found");
            } else if(var11) {
               throw new bo("[" + var2 + "]takeResources_searchOnly requires an include (eg: takeResources_includeUnitsWithinRange) otherwise no results would be found");
            }
         } else {
            CustomSpawnAction var15 = new CustomSpawnAction();
            var4.ac.add(var15);
            var15.b = var7;
            var15.d = var10;
            var15.c = var8;
            var15.f = var9;
            var15.a = var12;
            var15.s = var1.a(var2, var3 + "takeResources_directTransferStoppingAtZero", Boolean.valueOf(var15.s)).booleanValue();
            var15.e = (UnitTypeComparator)var1.a(var2, "takeResources_includeUnitsWithinRange_team", (Enum)UnitTypeComparator.own, UnitTypeComparator.class);
            var15.g = var1.a(var0, var2, var3 + "takeResources_excludeUnitsWithoutTags", (UnitConfig)null);
            var15.j = UnitReference.parseUnitReferenceFromConf(var0, var1, var2, var3 + "takeResources_excludeUnitsWithoutCustomTarget1EqualTo", (UnitReference)null);
            if(var15.s) {
               var15.h = false;
            }
            var15.h = var1.a(var2, var3 + "takeResources_excludeUnitsWithoutAllResources", Boolean.valueOf(var15.h)).booleanValue();
            if(var11) {
               var15.k = 200;
               var15.q = true;
               var15.r = true;
            }
            var15.k = var1.b(var2, var3 + "takeResources_maxUnits", Integer.valueOf(var15.k)).intValue();
            var15.l = var1.a(var0, var2, var3 + "takeResources_triggerActionIfAnyCollected", (UnitActionDef)null);
            var15.m = var1.a(var0, var2, var3 + "takeResources_triggerActionIfNoneCollected", (UnitActionDef)null);
            var15.n = var1.a(var0, var2, var3 + "takeResources_triggerActionForEach", (UnitActionDef)null);
            var15.o = var13;
            var15.p = var14;
            var15.q = var1.a(var2, var3 + "takeResources_discardCollected", Boolean.valueOf(var15.q)).booleanValue();
            var15.r = var1.a(var2, var3 + "takeResources_keepResourcesOnTarget", Boolean.valueOf(var15.r)).booleanValue();
            if(var11 && (!var15.q || !var15.r)) {
               throw new bo("[" + var2 + "]takeResources_searchOnly shortcut expects takeResources_discardCollected and takeResources_keepResourcesOnTarget to be true");
            } else {
               var15.i = CustomActionBase.a(var0, var1, var2, var3 + "takeResources_excludeUnitsWithTheseResources", true);
               if(var15.i != null && var15.i.c()) {
                  var15.i = null;
               }
               if(var15.s) {
                  if(var15.a.e()) {
                     throw new bo("[" + var2 + "]takeResources_directTransferStoppingAtZero:true only supports custom resources right now");
                  }
                  if(var15.h) {
                     throw new bo("[" + var2 + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_excludeUnitsWithoutAllResources:true");
                  }
                  if(var15.r) {
                     throw new bo("[" + var2 + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_keepResourcesOnTarget:true");
                  }
                  if(var15.q) {
                     throw new bo("[" + var2 + "]takeResources_directTransferStoppingAtZero:true is not supported at the same time as takeResources_discardCollected:true");
                  }
               }
            }
         }
      }
   }

   public boolean a(CustomUnitType var1, GameAction var2, PointF var3, UnitInstance var4, int var5) {
      CustomArrayList var6 = t;
      var6.clear();
      UnitInstance var8;
      if(this.b) {
         Iterator var7 = var1.B.iterator();
         while(var7.hasNext()) {
            var8 = (UnitInstance)var7.next();
            if(var8 != null && !var8.isDead) {
               var6.add(var8);
            }
         }
      }
      if(this.c) {
         if(var1.cO != null) {
            var6.add(var1.cO);
         } else if(var1.cN != null) {
            var6.add(var1.cO);
         }
      }
      UnitInstance var15;
      if(this.f != null) {
         var15 = this.f.readUnit(var1);
         if(var15 != null && !var15.isDead) {
            var6.add(var15);
         }
      }
      if(this.d > 0.0F) {
         u.b = this.d * this.d;
         u.a = this.g;
         u.c = true;
         u.d = this.e;
         u.e = var6;
         GlobalState var16 = GlobalState.B();
         var16.cc.a(var1.eo, var1.ep, this.d, var1, 0.0F, u);
      }
      int var17;
      if(this.g != null) {
         for(var17 = var6.size() - 1; var17 >= 0; --var17) {
            var8 = (UnitInstance)var6.get(var17);
            if(!TeamTag.b(this.g, var8.de())) {
               var6.remove(var17);
            }
         }
      }
      if(this.h) {
         for(var17 = var6.size() - 1; var17 >= 0; --var17) {
            var8 = (UnitInstance)var6.get(var17);
            if(!this.a.b(var8)) {
               var6.remove(var17);
            }
         }
      }
      if(this.i != null) {
         for(var17 = var6.size() - 1; var17 >= 0; --var17) {
            var8 = (UnitInstance)var6.get(var17);
            if(this.i.b(var8)) {
               var6.remove(var17);
            }
         }
      }
      UnitInstance var9;
      if(this.j != null) {
         var15 = this.j.get(var1);
         for(int var18 = var6.size() - 1; var18 >= 0; --var18) {
            var9 = (UnitInstance)var6.get(var18);
            if(var9.bu != var15) {
               var6.remove(var18);
            }
         }
      }
      if(this.n != null) {
         var6 = new CustomArrayList(var6);
      }
      boolean var20 = false;
      boolean var19 = false;
      var9 = null;
      int var10 = 0;
      int var11 = 0;
      for(int var12 = 0; var12 < var6.size(); ++var12) {
         UnitInstance var13 = (UnitInstance)var6.get(var12);
         if(var9 == null) {
            var9 = var13;
         }
         if(this.s) {
            boolean var14 = this.a.a(var13, (UnitInstance)var1);
            if(!var14) {
               continue;
            }
         } else {
            if(!this.r) {
               this.a.a(var13);
            }
            if(!this.q) {
               this.a.h(var1);
            }
         }
         if(this.n != null && var13 != null) {
            PointF var21 = new PointF(var13.eo, var13.ep);
            this.n.a(var1, var21, var13, var5 + 1, var11);
            ++var11;
         }
         var20 = true;
         ++var10;
         if(var10 >= this.k) {
            var19 = true;
            break;
         }
      }
      if(var9 != null) {
         if(this.o) {
            var1.bu = var9;
         }
         if(this.p) {
            var1.bv = var9;
         }
      }
      if(var20) {
         if(this.l != null) {
            this.l.a(var1, var3, var4, var5 + 1, 0);
         }
      } else if(this.m != null) {
         this.m.a(var1, var3, var4, var5 + 1, 0);
      }
      var6.clear();
      return true;
   }
}
