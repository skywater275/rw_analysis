/*
 * v19.115p 批5 重建: 02b custom/a/a/g.java (convertResource 资源转换) 60 行直译
 * 类型映射: e.a=effects.LogicBoolean, f(gf)=GameUtils, l=custom ModUnitRegistry
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.effects.LogicBoolean;
import com.corrodinggames.rts.gameFramework.GameUtils;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class g
extends CustomActionBase {
    LogicBoolean a;
    LogicBoolean b;
    double c;
    double d;
    float e;

    public static void a(ModUnitRegistry l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.actions.d d2, String string3, boolean bl2) throws bo {
        // 02b L18-47: convertResource_from/to 解析与校验
        LogicBoolean var7 = ab2.a(l2, string, "convertResource_from", (LogicBoolean)null, true);
        LogicBoolean var8 = ab2.a(l2, string, "convertResource_to", (LogicBoolean)null, true);
        if ((var7 != null || var8 != null) && (var7 == null || var8 == null)) {
            throw new bo("[" + string + "] Both convertResource_from and convertResource_to are required together");
        }
        if (var7 != null && var8 != null) {
            g g2 = new g();
            g2.a = var7;
            g2.b = var8;
            g2.c = ab2.a(string, "convertResource_minAmount", 0.0D);
            g2.d = ab2.j(string, "convertResource_maxAmount");
            if (g2.c < 0.0D) {
                throw new bo("[" + string + "] convertResource_minAmount cannot be < 0");
            }
            if (g2.d < 0.0D) {
                throw new bo("[" + string + "] convertResource_maxAmount cannot be < 0");
            }
            if (g2.d < g2.c) {
                throw new bo("[" + string + "] convertResource_maxAmount cannot be < convertResource_minAmount");
            }
            g2.e = ab2.a(string, "convertResource_multiplyAmountBy", Float.valueOf(1.0F)).floatValue();
            d2.ac.add(g2);
        }
    }

    @Override
    public boolean a(CustomUnitType j2, GameAction s2, PointF pointF, UnitInstance am2, int n2) {
        // 02b L49-59: 超出最小值则按量转换
        double d2 = this.a.a((UnitInstance)j2);
        if (d2 > this.c) {
            double d3 = GameUtils.a(d2, this.d);  // 02b: f.a(double,double) 随机取值
            this.a.b(j2, -d3);
            d3 *= (double)this.e;
            this.b.b(j2, d3);
        }
        return true;
    }
}
