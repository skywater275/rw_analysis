/*
 * v19.115m 重建: 02b custom/a/a/a.java 67 行直译 (CustomActionBase 资源量动作)
 * 类型映射: e.a=effects.LogicBoolean, d.c=resources.d (ResourceStorage 03 名)
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.effects.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.resources.d;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class CustomActionBase extends ActionBase {

   LogicBoolean a;
   double b = -1.7976931348623157E308D;
   LogicBoolean c;
   float d = 1.0F;
   com.corrodinggames.rts.game.units.custom.resources.d e;
   com.corrodinggames.rts.game.units.custom.resources.d f;

   /* 02b a/a/a.java L18: 子类 e/CustomRepairAction 等覆写抛 checked bo → 父类需声明 (R8 移除 throws) */
   public static void a(ModUnitRegistry var0, ab var1, String var2, String var3, com.corrodinggames.rts.game.units.custom.actions.d var4, String var5, boolean var6) throws bo {
      LogicBoolean var7 = var1.a(var0, var2, var3 + "resourceAmount", (LogicBoolean)null, true);
      if(var7 != null) {
         CustomActionBase var8 = new CustomActionBase();
         var8.a = var7;
         var8.b = var1.a(var2, var3 + "resourceAmount_setValue", -1.7976931348623157E308D);
         var8.c = var1.a(var0, var2, var3 + "resourceAmount_addOtherResource", (LogicBoolean)null, true);
         var8.d = var1.a(var2, var3 + "resourceAmount_multiplyBy", Float.valueOf(1.0F)).floatValue();
         var4.ac.add(var8);
      }
      com.corrodinggames.rts.game.units.custom.resources.d var11 = null;
      com.corrodinggames.rts.game.units.custom.resources.d var9 = null;
      // 02b: d.c.a(var0,var1,var2,var3+"addResourcesWithLogic",null) 静态工厂 03 resources 待战役, 简化
      if(var11 != null || var9 != null) {
         CustomActionBase var10 = new CustomActionBase();
         var10.f = var9;
         var10.e = var11;
         var4.ac.add(var10);
      }
   }

   public boolean a(CustomUnitType var1, GameAction var2, PointF var3, UnitInstance var4, int var5) {
      if(this.a != null) {
         double var6;
         if(this.b != -1.7976931348623157E308D) {
            var6 = this.b;
         } else {
            var6 = this.a.a((UnitInstance)var1);
         }
         if(this.c != null) {
            var6 += this.c.a((UnitInstance)var1);
         }
         var6 *= (double)this.d;
         this.a.a(var1, var6);
      }
      if(this.f != null) {
         this.f.d(var1);
      }
      if(this.e != null) {
         this.e.e(var1);
      }
      return true;
   }
}
