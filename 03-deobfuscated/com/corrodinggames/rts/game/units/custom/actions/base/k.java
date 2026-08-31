/*
 * v19.115p 批5 重建: 02b custom/a/a/k.java (produceUnits/spawnUnits) 52 行直译
 * 类型映射: bp=bp(生成列表), m=CustomArrayList, n=PlayerState
 * 依赖补缺: CustomUnitType.E(UnitInstance)/F(UnitInstance);
 *   bp.a(CustomArrayList,PlayerState,CustomUnitType,boolean)+a(FFFF,PlayerState,boolean,CustomUnitType) 签名修复
 */
package com.corrodinggames.rts.game.units.custom.actions.base;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class k
extends CustomActionBase {
    public bp a;
    public bp b;

    public static void a(ModUnitRegistry l2, ab ab2, String string, String string2, com.corrodinggames.rts.game.units.custom.actions.d d2, String string3, boolean bl2) throws bo {
        // 02b L16-31: produceUnits/spawnUnits 双解析
        bp var7 = bp.a(l2, ab2, string, string2 + "produceUnits");
        if (!var7.b()) {
            k k2 = new k();
            k2.a = var7;
            d2.ac.add(k2);
        }
        bp var10 = bp.a(l2, ab2, string, string2 + "spawnUnits");
        if (!var10.b()) {
            k k3 = new k();
            k3.b = var10;
            d2.ac.add(k3);
        }
    }

    @Override
    public boolean a(CustomUnitType j2, GameAction s2, PointF pointF, UnitInstance am2, int n2) {
        // 02b L33-51: 生产列表单位 → E/F 回调; spawnUnits 就地生成
        if (this.a != null) {
            CustomArrayList m2 = new CustomArrayList();
            this.a.a(m2, j2.player, j2, false);
            for (Object object : m2) {
                UnitInstance var8 = (UnitInstance)object;
                j2.E(var8);  // 02b: j.E(am) 生产回调
                j2.F(var8);  // 02b: j.F(am) 生产回调
            }
        }
        if (this.b != null) {
            this.b.a(j2.eo, j2.ep, j2.eq, j2.cg, j2.player, false, j2);
        }
        return true;
    }
}
