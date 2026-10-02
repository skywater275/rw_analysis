/*
 * v19.115m 重建: 02b custom/a/a/j.java 236 行直译 (CustomTransportAction 状态/冷却/传送动作)
 * 类型映射: as=WeaponConfig, u=UnitActionDef, ai=utility.ai, g.e=weapons.TimerComponent,
 * d.b=resources.CustomActionBase, s=GameAction, n=PlayerState
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.UnitActionDef;
import com.corrodinggames.rts.game.units.custom.WeaponConfig;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.weapons.TimerComponent;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.ai;
import java.util.Iterator;

public class CustomTransportAction extends ActionBase {

   boolean a;
   boolean b;
   boolean c;
   LogicBoolean d;
   LogicBoolean e;
   LogicBoolean f;
   LogicBoolean g;
   boolean h;
   float i;
   float j;
   UnitActionDef k;
   boolean l;
   boolean m;
   float n = -1.0F;
   ai o;
   boolean p;
   VariableScope$CachedWriter q;

   public static void a(ModUnitRegistry var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.actions.d var4, String var5, boolean var6) throws bo {
      boolean var7 = var1.a(var2, var3 + "resetUnitStats", Boolean.valueOf(false)).booleanValue();
      String var8 = var1.b(var2, var3 + "setUnitStats", (String)null);
      if(var7 || var8 != null) {
         CustomTransportAction var9 = new CustomTransportAction();
         var9.p = var7;
         if(var8 != null) {
            var9.q = WeaponConfig.a(var8, var0, var2, var3 + "setUnitStats");
         }
         var4.ac.add(var9);
      }
      boolean var25 = var1.a(var2, var3 + "deleteSelf", Boolean.valueOf(false)).booleanValue();
      if(var25) {
         CustomTransportAction var10 = new CustomTransportAction();
         var10.a = var25;
         var4.ac.add(var10);
      }
      boolean var26 = var1.a(var2, var3 + "switchToNeutralTeam", Boolean.valueOf(false)).booleanValue();
      boolean var11 = var1.a(var2, var3 + "switchToAggressiveTeam", Boolean.valueOf(false)).booleanValue();
      LogicBoolean var12 = var1.a(var0, var2, var3 + "switchToTeam", (LogicBoolean)null, LogicBoolean$ReturnType.number);
      if(var26 || var11 || var12 != null) {
         CustomTransportAction var13 = new CustomTransportAction();
         var13.b = var26;
         var13.c = var11;
         var13.d = var12;
         var4.ac.add(var13);
      }
      LogicBoolean var27 = var1.c(var0, var2, var3 + "setBodyRotation", (LogicBoolean)null);
      if(var27 != null) {
         CustomTransportAction var14 = new CustomTransportAction();
         var14.e = var27;
         var4.ac.add(var14);
      }
      LogicBoolean var28 = var1.c(var0, var2, var3 + "setHeight", (LogicBoolean)null);
      if(var28 != null) {
         CustomTransportAction var15 = new CustomTransportAction();
         var15.f = var28;
         var4.ac.add(var15);
      }
      LogicBoolean var29 = var1.b(var0, var2, var3 + "teleportTo", (LogicBoolean)null);
      if(var29 != null) {
         CustomTransportAction var16 = new CustomTransportAction();
         var16.g = var29;
         var4.ac.add(var16);
      }
      float var30 = var1.a(var2, var3 + "setBuilt", Float.valueOf(-1.0F)).floatValue();
      if(var30 > 1.0F) {
         throw new bo("[" + var2 + "] setBuilt cannot be greater than 1");
      } else {
         boolean var17 = var1.a(var2, var3 + "clearAllActionCooldowns", Boolean.valueOf(false)).booleanValue();
         float var18 = var1.c(var2, var3 + "addAllActionCooldownsTime", Float.valueOf(0.0F)).floatValue();
         if(var18 == 0.0F) {
            var18 = var1.c(var2, var3 + "addAllActionCooldownsFor", Float.valueOf(0.0F)).floatValue();
         }
         float var19 = var1.c(var2, var3 + "addActionCooldownTime", Float.valueOf(0.0F)).floatValue();
         if(var19 == 0.0F) {
            var19 = var1.c(var2, var3 + "addActionCooldownFor", Float.valueOf(0.0F)).floatValue();
         }
         UnitActionDef var20 = var1.a(var0, var2, var3 + "addActionCooldownApplyToActions", (UnitActionDef)null);
         ai var21 = var1.a(var2, var3 + "offsetSelfAbsolute", (ai)null);
         if(var20 != null && var19 <= 0.0F) {
            throw new bo("[" + var2 + "]addActionCooldownApplyToActions requires addActionCooldownTime to be set");
         } else {
            boolean var22 = var1.a(var2, var3 + "removeAllQueuedItemsWithoutRefund", Boolean.valueOf(false)).booleanValue();
            boolean var23 = var1.a(var2, var3 + "refundAllQueuedItems", Boolean.valueOf(false)).booleanValue();
            if(var22 && var23) {
               throw new bo("[" + var2 + "]Cannot set removeAllQueuedActionsWithoutRefund and refundAllQueuedActions at the same time, pick one.");
            } else {
               if(var19 > 0.0F || var18 > 0.0F || var17 || var30 >= 0.0F || var21 != null || var22 || var23) {
                  CustomTransportAction var24 = new CustomTransportAction();
                  var24.h = var17;
                  var24.i = var18;
                  var24.j = var19;
                  var24.k = var20;
                  var24.n = var30;
                  var24.o = var21;
                  var24.l = var22;
                  var24.m = var23;
                  var4.ac.add(var24);
               }
            }
         }
      }
   }

   public boolean a(CustomUnitType var1, GameAction var2, PointF var3, UnitInstance var4, int var5) {
      if(this.p) {
         // 02b: var1.y = var1.x.cL; var1.cv = (float)var1.y.c; ... -- ModUnitRegistry.cL 03 待战役, 简化
      }
      if(this.q != null) {
         try {
            this.q.writeToUnit(var1);
         } catch (bo var6) {
            throw new RuntimeException(var6);
         }
         CustomActionBase.d(var1);
      }
      if(this.a) {
         var1.ci();
         if(var1.bI()) {
            GlobalState var6 = GlobalState.B();
            var6.bU.a(var1);
         }
      }
      if(this.b) {
         var1.e(PlayerState.i);
      }
      if(this.c) {
         var1.e(PlayerState.h);
      }
      if(this.d != null) {
         int var8 = (int)this.d.readNumber(var1);
         PlayerState var7 = PlayerState.k(var8);
         if(var7 != null) {
            var1.e(var7);
         }
      }
      float var9;
      if(this.e != null) {
         var9 = this.e.readNumber(var1);
         var1.h(var9);
      }
      if(this.f != null) {
         var9 = this.f.readNumber(var1);
         var1.eq = var9;
      }
      if(this.g != null) {
         UnitInstance var10 = this.g.readUnit(var1);
         if(var10 != null) {
            var1.f(var10.eo, var10.ep);
         }
      }
      if(this.h) {
         TimerComponent.c(var1, GameAction.i);
      }
      if(this.l) {
         var1.i(false);
      }
      if(this.m) {
         var1.i(true);
      }
      if(this.i > 0.0F) {
         TimerComponent.a(var1, GameAction.i, (int)this.i);
      }
      if(this.j > 0.0F) {
         if(this.k == null) {
            TimerComponent.a(var1, var2.N(), (int)this.j);
         } else {
            Iterator var12 = this.k.a().iterator();
            while(var12.hasNext()) {
               GameAction var11 = (GameAction)var12.next();
               TimerComponent.a(var1, var11.N(), (int)this.j);
            }
         }
      }
      if(this.n >= 0.0F) {
         var1.r(this.n);
         var1.cn = this.n;
      }
      if(this.o != null) {
         var1.b(var1.eo + this.o.a, var1.ep + this.o.b);
         var1.eq += this.o.c;
         var1.cK = true;
      }
      return true;
   }
}
