/*
 * v19.115m 重建: 02b custom/u.java 76 行直译 (UnitActionDef 动作列表定义)
 * 02b 字段: a/b = utility.m (03 原 DirectionConfig 错位); 依赖 l.h(String)/j.dL 03 待战役 (简化)
 */
package com.corrodinggames.rts.game.units.custom;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;
import java.util.Iterator;

public strictfp class UnitActionDef {
   CustomArrayList a = new CustomArrayList();   // 02b u.a: 动作名列表
   CustomArrayList b;                            // 02b u.b: 动作对象列表
   String c;
   String d;

   public void a(ModUnitRegistry var1) {
   }

   public void b(ModUnitRegistry var1) {
      // 02b L19-38: 解析动作名列表 -> 动作对象 (依赖 ModUnitRegistry.h(String) 03 待战役, 简化)
   }

   public void a(CustomUnitType var1, PointF var2, UnitInstance var3, int var4, int var5) {
      if(this.b == null) {
         NetEngine.g("Action on " + var1.dt() + " has not been linked");
      } else {
         Iterator var6 = this.b.iterator();
         while(var6.hasNext()) {
            GameAction var7 = (GameAction)var6.next();
            var1.a(var7, var2, var3, var4, var5);
         }
      }
   }

   public CustomArrayList a() {
      if(this.b == null) {
         NetEngine.g("Action on [" + this.d + "]" + this.c + " has not been linked");
         return new CustomArrayList();
      } else {
         return this.b;
      }
   }

   public void a(CustomUnitType var1, PointF var2, UnitInstance var3) {
      if(this.b == null) {
         NetEngine.g("Action on " + var1.dt() + " has not been linked");
      } else {
         Iterator var4 = this.b.iterator();
         while(var4.hasNext()) {
            GameAction var5 = (GameAction)var4.next();
            // 02b: var1.dL.a((a.w)var5, false, var2, var3); -- CustomUnitType.dL 03 待战役, 简化
         }
      }
   }
}
